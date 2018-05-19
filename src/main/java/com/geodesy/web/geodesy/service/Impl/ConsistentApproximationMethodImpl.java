package com.geodesy.web.geodesy.service.Impl;

import com.geodesy.web.geodesy.model.Approximation;
import com.geodesy.web.geodesy.model.CalculationData;
import com.geodesy.web.geodesy.model.Move;
import com.geodesy.web.geodesy.model.Reper;
import com.geodesy.web.geodesy.model.constants.CalculationType;
import com.geodesy.web.geodesy.model.enums.MoveType;
import com.geodesy.web.geodesy.model.enums.ReperType;
import com.geodesy.web.geodesy.model.utils.MoveNameComparator;
import com.geodesy.web.geodesy.service.ConsistentApproximationMethod;
import com.geodesy.web.geodesy.service.DefaultConfiguration;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.aspectj.runtime.internal.Conversions.doubleValue;
import static org.aspectj.runtime.internal.Conversions.intValue;

@Service
public class ConsistentApproximationMethodImpl implements ConsistentApproximationMethod, DefaultConfiguration {
    private static final Logger LOGGER = Logger.getLogger(ConsistentApproximationMethodImpl.class);

    @Override
    public CalculationData calculate(CalculationData calculationData) {
        return calculateApproximationFull(
                createCheckMoves(
                        getWeightStroke(normilize(
                                getWeight(normilize(
                                        fulfillMoves(normilize(
                                                getApproximatePointHeight(
                                                        normilize(calculationData))))))))));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CalculationData normilize(CalculationData calculationData) {
        LOGGER.info(calculationData.getReperList());
        return calculationData
                .setMoveList(calculationData.getMoveList().stream().peek(LOGGER::info).map(move -> move.getMoveType() == null ? move.setMoveType(MoveType.DEFAULT) : move).peek(move -> LOGGER.info("----------------------------\n")).peek(LOGGER::info).collect(toList()))
                .setReperList(calculationData.getReperList().stream().peek(LOGGER::info).map(reper -> reper.setReperType(reper.getName().matches("\\w+\\d+") ? ReperType.REPER : ReperType.POINT)).peek(move -> LOGGER.info("============================\n")).peek(LOGGER::info).collect(toList()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CalculationData getApproximatePointHeight(CalculationData calculationData) {
        List<Reper> repers = new ArrayList<>(calculationData.getReperList());
        List<Move> moves = new ArrayList<>(calculationData.getMoveList());
        for (Reper reper : repers) {
            Move move = moves.stream().filter(move1 -> move1.getName().contains(reper.getName())).findFirst().orElse(new Move());
            if (move.getName() == null)
                continue;
            String name = move.getName().replace(reper.getName(), "").replace("-", "");
            if (calculationData.getReperList().stream().noneMatch(reper1 -> reper1.getName().equals(name))) {
                calculationData.addReper(
                        new Reper()
                                .setId(1L)
                                .setName(name)
                                .setHeight(reper.getHeight() + move.getDifference())
                                .setReperType(ReperType.POINT)
                );
            }
        }
        return calculationData;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CalculationData fulfillMoves(CalculationData calculationData) {
        List<Move> moves = calculationData.getMoveList().stream().filter(move -> !move.getName().contains("Rp")).collect(toList());
        System.err.println(moves.stream().map(Move::getName).collect(toList()));
        for (Move move :
                moves) {
            Move newMove = new Move()
                    .setId(1L)
                    .setDifference(.0 - move.getDifference())
                    .setName(move.getName().split("-")[1] + "-" + move.getName().split("-")[0])
                    .setStationCount(move.getStationCount())
                    .setDistance(move.getDistance());
            if (calculationData.getMoveList().stream().noneMatch(move1 -> move1.getName().equals(newMove.getName())))
                calculationData.addMove(newMove);
        }
        return calculationData;
    }

    /**
     * P[i] = 1/stationCount
     * {@inheritDoc}
     */
    @Override
    public CalculationData getWeight(CalculationData calculationData) {
        List<Move> moveList = calculationData.getMoveList().stream()
                .map(move -> move.setWeight(doubleValue(1.0 / move.getStationCount())))
                .sorted(new MoveNameComparator()).collect(toList());
        calculationData.setMoveList(moveList);
        return calculationData;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CalculationData createCheckMoves(CalculationData calculationData) {
        for (Reper point : calculationData.getReperList().stream().filter(reper -> reper.getReperType().equals(ReperType.POINT)).collect(toList())) {
            List<Move> moves = calculationData.getMoveList().stream().filter(move -> move.getName().contains("-" + point.getName())).collect(toList());
            Double chDifference = .0, chWeight = .0, chWeightStroke = .0, chDistance = .0;
            for (Move move : moves) {
                chDifference += move.getDifference();
                chWeight += move.getWeight();
                chWeightStroke += move.getWeightStroke();
                chDistance += move.getDistance();
            }
            calculationData.addMove(
                    new Move()
                            .setId(1L)
                            .setMoveType(MoveType.CHECK)
                            .setName(point.getName())
                            .setDistance(chDistance)
                            .setDifference(chDifference)
                            .setWeight(chWeight)
                            .setWeightStroke(chWeightStroke)
            );
        }
        return calculationData;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CalculationData getWeightStroke(CalculationData calculationData) {
        calculationData.getMoveList().sort(new MoveNameComparator());
        Long pointsCount = calculationData.getReperList().stream().filter(reper -> reper.getReperType().equals(ReperType.POINT)).count();
        Long movesPerPoint = calculationData.getMoveList().size() / pointsCount;
        Long tempMPP = movesPerPoint;
        Double sumWeight = .0;
        for (int i = 0; i <= calculationData.getMoveList().size(); i++) {
            if (tempMPP-- > 0 && i < calculationData.getMoveList().size()) {
                sumWeight += calculationData.getMoveList().get(i).getWeight();
            } else if (tempMPP <= 0) {
                for (int j = intValue(i - movesPerPoint); j < i; j++) {
                    calculationData.getMoveList().get(j).setWeightStroke(calculationData.getMoveList().get(j).getWeight() / sumWeight);
                }
                tempMPP = movesPerPoint;
                i--;
                sumWeight = .0;
            }
        }
        return calculationData;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CalculationData calculateApproximation(CalculationData calculationData) {
        List<Reper> points = calculationData.getReperList().stream().filter(reper -> reper.getReperType().equals(ReperType.POINT)).peek(LOGGER::info).collect(toList());
        Integer step = getStep(calculationData);
        for (int i = 0; i < points.size(); i++) {
            Integer tI = i;
            Move checkMove = calculationData.getMoveList().stream().filter(move -> move.getName().equals(points.get(tI).getName()) && move.getMoveType().equals(MoveType.CHECK)).findFirst().orElseThrow(RuntimeException::new);
            List<Move> moves = calculationData.getMoveList().stream().filter(move -> move.getMoveType().equals(MoveType.DEFAULT) && move.getName().contains("-" + points.get(tI).getName())).collect(toList());
            setApproximations(step, moves, getConnectedRepers(calculationData, moves));
            Double checkHeight = getCheckHeight(step, moves);
            checkMove.addApproximation(new Approximation().setId(1L).setValue(checkHeight).setStep(step));
            points.get(i).setHeight(checkHeight);
        }
        return calculationData;
    }

    /**
     * @param step  current calculation step
     * @param moves moves where calculating point is "in"
     * @return approximate height for point
     */
    private Double getCheckHeight(Integer step, List<Move> moves) {
        Double checkHeight = .0;
        for (Move move :
                moves) {
            checkHeight += move.getApproximations().get(step - 1).getValue() * move.getWeightStroke();
        }
        return checkHeight;
    }

    /**
     * @param step            current calculation step
     * @param moves           default moves
     * @param connectedRepers "out" repers which connected with calculating point
     * @return moves with approximation for current step
     */
    private List<Move> setApproximations(Integer step, List<Move> moves, List<Reper> connectedRepers) {
        for (int i = 0; i < connectedRepers.size(); i++) {
            moves.get(i).addApproximation(new Approximation().setId(1L).setStep(step).setValue(connectedRepers.get(i).getHeight() + moves.get(i).getDifference()));
        }
        return moves;
    }

    /**
     * @param calculationData calculationData
     * @param moves           default moves where calculating point is "in"
     * @return repers which have the same moves as calculating point
     */
    private List<Reper> getConnectedRepers(CalculationData calculationData, List<Move> moves) {
        List<Reper> connectedRepers = new ArrayList<>();
        for (Move move :
                moves) {
            connectedRepers.add(calculationData.getReperList().stream().filter(reper -> reper.getName().equals(move.getName().split("-")[0])).findFirst().orElseThrow(RuntimeException::new));
        }
        return connectedRepers;
    }

    /**
     * @param calculationData calculationData which points will be calculated
     * @return value of next step to be calculated
     */
    private Integer getStep(CalculationData calculationData) {
        Integer step = 1;
        if (calculationData.getMoveList().get(0).getApproximations().size() > 0)
            step = calculationData.getMoveList().get(0).getApproximations().get(calculationData.getMoveList().get(0).getApproximations().size() - 1).getStep() + 1;
        return step;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CalculationData calculateApproximationFull(CalculationData calculationData) {
        while (!checkApproximations(calculationData, CalculationType.TYPES.get(calculationData.getCalculationTypeName()))) {
            calculationData = calculateApproximation(calculationData);
        }
        return fulfillCorrections(calculationData);
    }

    /**
     * @param calculationData calculationData
     * @param maxDifference   maxDifference between last approx. and one before that
     * @return approximations' correctness
     */
    private Boolean checkApproximations(CalculationData calculationData, Double maxDifference) {
        if (calculationData.getMoveList().get(0).getApproximations().size() <= 1)
            return false;
        Integer step = getStep(calculationData);
        if (step == 1)
            return false;
        List<Reper> points = calculationData.getReperList().stream().filter(reper -> reper.getReperType().equals(ReperType.POINT)).collect(toList());
        for (Reper point :
                points) {
            List<Move> moves = calculationData.getMoveList().stream().filter(move -> move.getName().contains("-" + point.getName())).collect(toList());
            if (checkPointApproximations(maxDifference, moves)) return false;
        }
        return true;
    }

    /**
     * @param maxDifference maxDifference between last approx. and one before that
     * @param moves         moves of exact point
     * @return approximations' correctness
     */
    private Boolean checkPointApproximations(Double maxDifference, List<Move> moves) {
        for (Move move :
                moves) {
            Integer approximationsCount = move.getApproximations().size();
            if (Math.abs(move.getApproximations().get(approximationsCount - 1).getValue() - move.getApproximations().get(approximationsCount - 2).getValue()) > maxDifference) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CalculationData fulfillCorrections(CalculationData calculationData) {
        List<Reper> repers = calculationData.getReperList().stream().filter(reper -> reper.getReperType().equals(ReperType.POINT)).collect(toList());
        Double sumPVV = .0;
        Integer sumStations = 0;
        Double sumDistance = .0;
        for (Reper point :
                repers) {
            Double sumPVVOne = .0;
            Integer sumStationsOne = 0;
            Double sumDistanceOne = .0;
            Double sumCorrectionOne = .0;
            Double sumPVOne = .0;
            Move checkMove = calculationData.getMoveList().stream().filter(move -> move.getName().equals(point.getName())).findFirst().orElseThrow(RuntimeException::new);
            List<Move> moves = calculationData.getMoveList().stream().filter(move -> move.getName().contains("-" + point.getName())).collect(toList());
            for (Move move : moves) {
                move.setCorrection(checkMove.getApproximations().get(checkMove.getApproximations().size() - 1).getValue() - move.getApproximations().get(move.getApproximations().size() - 1).getValue());
                move.setWeightStrokeCorrection(move.getWeightStroke() * move.getCorrection());
                move.setWeightStrokeCorrectionCorrection(move.getWeightStrokeCorrection() * move.getCorrection());
                sumPVVOne += move.getWeightStrokeCorrectionCorrection();
                sumDistanceOne += move.getDistance();
                sumStationsOne += move.getStationCount();
                sumCorrectionOne += move.getCorrection();
                sumPVOne += move.getWeightStrokeCorrection();
            }
            checkMove.setWeightStrokeCorrectionCorrection(sumPVVOne);
            checkMove.setDistance(sumDistanceOne);
            checkMove.setStationCount(sumStationsOne);
            checkMove.setCorrection(sumCorrectionOne);
            checkMove.setWeightStrokeCorrection(sumPVOne);
            sumPVV += checkMove.getWeightStrokeCorrectionCorrection();
            sumDistance += checkMove.getDistance();
            sumStations += checkMove.getStationCount();
        }
        calculationData.setNiu(Math.sqrt(sumPVV / (calculationData.getReperList().size() - calculationData.getReperList().stream().filter(reper -> reper.getReperType().equals(ReperType.POINT)).count())));
        calculationData.setM((calculationData.getNiu() / 10) * Math.sqrt(sumStations / sumDistance));
        return calculationData;
    }
}
