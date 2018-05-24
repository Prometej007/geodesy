package com.geodesy.web.geodesy.service.Impl;

import com.geodesy.web.geodesy.model.approximation.Approximation;
import com.geodesy.web.geodesy.model.approximation.ApproximationMove;
import com.geodesy.web.geodesy.model.approximation.ApproximationReper;
import com.geodesy.web.geodesy.model.approximation.CalculationData;
import com.geodesy.web.geodesy.model.utils.constants.CalculationType;
import com.geodesy.web.geodesy.model.utils.enums.MoveType;
import com.geodesy.web.geodesy.model.utils.enums.ReperType;
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
//        LOGGER.info(calculationData.getReperList());
        return calculationData
                .setApproximationMoveList(calculationData.getApproximationMoveList().stream().map(move -> move.getMoveType() == null ? move.setMoveType(MoveType.DEFAULT) : move).collect(toList()))
                .setReperList(calculationData.getReperList().stream().map(reper -> reper.setReperType(reper.getName().matches("\\w+\\d+") ? ReperType.REPER : ReperType.POINT)).collect(toList()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CalculationData getApproximatePointHeight(CalculationData calculationData) {
        List<ApproximationReper> approximationRepers = new ArrayList<>(calculationData.getReperList());
        List<ApproximationMove> approximationMoves = new ArrayList<>(calculationData.getApproximationMoveList());
        for (ApproximationReper approximationReper : approximationRepers) {
            ApproximationMove approximationMove = approximationMoves.stream().filter(move1 -> move1.getName().contains(approximationReper.getName())).findFirst().orElse(new ApproximationMove());
            if (approximationMove.getName() == null)
                continue;
            String name = approximationMove.getName().replace(approximationReper.getName(), "").replace("-", "");
            if (calculationData.getReperList().stream().noneMatch(reper1 -> reper1.getName().equals(name))) {
                List<ApproximationReper> temp = new ArrayList<>(calculationData.getReperList());
                temp.add(
                        new ApproximationReper()
                                .setId(1L)
                                .setName(name)
                                .setHeight(approximationReper.getHeight() + approximationMove.getDifference())
                                .setReperType(ReperType.POINT)
                );
                calculationData.setReperList(temp);
            }
        }
        return calculationData;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CalculationData fulfillMoves(CalculationData calculationData) {
        List<ApproximationMove> approximationMoves = calculationData.getApproximationMoveList().stream().filter(move -> !move.getName().contains("Rp")).collect(toList());
        System.err.println(approximationMoves.stream().map(ApproximationMove::getName).collect(toList()));
        for (ApproximationMove approximationMove :
                approximationMoves) {
            ApproximationMove newApproximationMove = new ApproximationMove()
                    .setId(1L)
                    .setDifference(.0 - approximationMove.getDifference())
                    .setName(approximationMove.getName().split("-")[1] + "-" + approximationMove.getName().split("-")[0])
                    .setStationCount(approximationMove.getStationCount())
                    .setDistance(approximationMove.getDistance());
            if (calculationData.getApproximationMoveList().stream().noneMatch(move1 -> move1.getName().equals(newApproximationMove.getName())))
                calculationData.addMove(newApproximationMove);
        }
        return calculationData;
    }

    /**
     * P[i] = 1/stationCount
     * {@inheritDoc}
     */
    @Override
    public CalculationData getWeight(CalculationData calculationData) {
        List<ApproximationMove> approximationMoveList = calculationData.getApproximationMoveList().stream()
                .map(move -> move.setWeight(doubleValue(1.0 / move.getStationCount())))
                .sorted(new MoveNameComparator()).collect(toList());
        calculationData.setApproximationMoveList(approximationMoveList);
        return calculationData;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CalculationData createCheckMoves(CalculationData calculationData) {
        for (ApproximationReper point : calculationData.getReperList().stream().filter(reper -> reper.getReperType().equals(ReperType.POINT)).collect(toList())) {
            List<ApproximationMove> approximationMoves = calculationData.getApproximationMoveList().stream().filter(move -> move.getName().contains("-" + point.getName())).collect(toList());
            Double chDifference = .0, chWeight = .0, chWeightStroke = .0, chDistance = .0;
            for (ApproximationMove approximationMove : approximationMoves) {
                chDifference += approximationMove.getDifference();
                chWeight += approximationMove.getWeight();
                chWeightStroke += approximationMove.getWeightStroke();
                chDistance += approximationMove.getDistance();
            }
            calculationData.addMove(
                    new ApproximationMove()
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
        calculationData.getApproximationMoveList().sort(new MoveNameComparator());
        Long pointsCount = calculationData.getReperList().stream().filter(reper -> reper.getReperType().equals(ReperType.POINT)).count();
        Long movesPerPoint = calculationData.getApproximationMoveList().size() / pointsCount;
        Long tempMPP = movesPerPoint;
        Double sumWeight = .0;
        for (int i = 0; i <= calculationData.getApproximationMoveList().size(); i++) {
            if (tempMPP-- > 0 && i < calculationData.getApproximationMoveList().size()) {
                sumWeight += calculationData.getApproximationMoveList().get(i).getWeight();
            } else if (tempMPP <= 0) {
                for (int j = intValue(i - movesPerPoint); j < i; j++) {
                    calculationData.getApproximationMoveList().get(j).setWeightStroke(calculationData.getApproximationMoveList().get(j).getWeight() / sumWeight);
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
        List<ApproximationReper> points = calculationData.getReperList().stream().filter(reper -> reper.getReperType().equals(ReperType.POINT)).collect(toList());
        Integer step = getStep(calculationData);
        for (int i = 0; i < points.size(); i++) {
            Integer tI = i;
            ApproximationMove checkApproximationMove = calculationData.getApproximationMoveList().stream().filter(move -> move.getName().equals(points.get(tI).getName()) && move.getMoveType().equals(MoveType.CHECK)).findFirst().orElseThrow(RuntimeException::new);
            List<ApproximationMove> approximationMoves = calculationData.getApproximationMoveList().stream().filter(move -> move.getMoveType().equals(MoveType.DEFAULT) && move.getName().contains("-" + points.get(tI).getName())).collect(toList());
            setApproximations(step, approximationMoves, getConnectedRepers(calculationData, approximationMoves));
            Double checkHeight = getCheckHeight(step, approximationMoves);
            checkApproximationMove.addApproximation(new Approximation().setId(1L).setValue(checkHeight).setStep(step));
            points.get(i).setHeight(checkHeight);
        }
        return calculationData;
    }

    /**
     * @param step  current calculation step
     * @param approximationMoves approximationMoves where calculating point is "in"
     * @return approximate height for point
     */
    private Double getCheckHeight(Integer step, List<ApproximationMove> approximationMoves) {
        Double checkHeight = .0;
        for (ApproximationMove approximationMove :
                approximationMoves) {
            checkHeight += approximationMove.getApproximations().get(step - 1).getValue() * approximationMove.getWeightStroke();
        }
        return checkHeight;
    }

    /**
     * @param step            current calculation step
     * @param approximationMoves           default approximationMoves
     * @param connectedApproximationRepers "out" repers which connected with calculating point
     * @return approximationMoves with approximation for current step
     */
    private List<ApproximationMove> setApproximations(Integer step, List<ApproximationMove> approximationMoves, List<ApproximationReper> connectedApproximationRepers) {
        for (int i = 0; i < connectedApproximationRepers.size(); i++) {
            approximationMoves.get(i).addApproximation(new Approximation().setId(1L).setStep(step).setValue(connectedApproximationRepers.get(i).getHeight() + approximationMoves.get(i).getDifference()));
        }
        return approximationMoves;
    }

    /**
     * @param calculationData calculationData
     * @param approximationMoves           default approximationMoves where calculating point is "in"
     * @return repers which have the same approximationMoves as calculating point
     */
    private List<ApproximationReper> getConnectedRepers(CalculationData calculationData, List<ApproximationMove> approximationMoves) {
        List<ApproximationReper> connectedApproximationRepers = new ArrayList<>();
        for (ApproximationMove approximationMove :
                approximationMoves) {
            connectedApproximationRepers.add(calculationData.getReperList().stream().filter(reper -> reper.getName().equals(approximationMove.getName().split("-")[0])).findFirst().orElseThrow(RuntimeException::new));
        }
        return connectedApproximationRepers;
    }

    /**
     * @param calculationData calculationData which points will be calculated
     * @return value of next step to be calculated
     */
    private Integer getStep(CalculationData calculationData) {
        Integer step = 1;
        if (calculationData.getApproximationMoveList().get(0).getApproximations().size() > 0)
            step = calculationData.getApproximationMoveList().get(0).getApproximations().get(calculationData.getApproximationMoveList().get(0).getApproximations().size() - 1).getStep() + 1;
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
        if (calculationData.getApproximationMoveList().get(0).getApproximations().size() <= 1)
            return false;
        Integer step = getStep(calculationData);
        if (step == 1)
            return false;
        List<ApproximationReper> points = calculationData.getReperList().stream().filter(reper -> reper.getReperType().equals(ReperType.POINT)).collect(toList());
        for (ApproximationReper point :
                points) {
            List<ApproximationMove> approximationMoves = calculationData.getApproximationMoveList().stream().filter(move -> move.getName().contains("-" + point.getName())).collect(toList());
            if (checkPointApproximations(maxDifference, approximationMoves)) return false;
        }
        return true;
    }

    /**
     * @param maxDifference maxDifference between last approx. and one before that
     * @param approximationMoves         approximationMoves of exact point
     * @return approximations' correctness
     */
    private Boolean checkPointApproximations(Double maxDifference, List<ApproximationMove> approximationMoves) {
        for (ApproximationMove approximationMove :
                approximationMoves) {
            Integer approximationsCount = approximationMove.getApproximations().size();
//            LOGGER.info("-----------==============-------------");
//            LOGGER.info((Math.abs(approximationMove.getApproximations().get(approximationsCount - 1).getValue() - approximationMove.getApproximations().get(approximationsCount - 2).getValue()) * 1000) + " > " + (maxDifference * 1000));
//            LOGGER.info("-----------==============-------------");
            if (Math.abs(approximationMove.getApproximations().get(approximationsCount - 1).getValue() - approximationMove.getApproximations().get(approximationsCount - 2).getValue()) > maxDifference) {
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
        List<ApproximationReper> approximationRepers = calculationData.getReperList().stream().filter(reper -> reper.getReperType().equals(ReperType.POINT)).collect(toList());
        Double sumPVV = .0;
        Integer sumStations = 0;
        Double sumDistance = .0;
        for (ApproximationReper point :
                approximationRepers) {
            Double sumPVVOne = .0;
            Integer sumStationsOne = 0;
            Double sumDistanceOne = .0;
            Double sumCorrectionOne = .0;
            Double sumPVOne = .0;
            ApproximationMove checkApproximationMove = calculationData.getApproximationMoveList().stream().filter(move -> move.getName().equals(point.getName())).findFirst().orElseThrow(RuntimeException::new);
            List<ApproximationMove> approximationMoves = calculationData.getApproximationMoveList().stream().filter(move -> move.getName().contains("-" + point.getName())).collect(toList());
            for (ApproximationMove approximationMove : approximationMoves) {
                approximationMove.setCorrection(checkApproximationMove.getApproximations().get(checkApproximationMove.getApproximations().size() - 1).getValue() - approximationMove.getApproximations().get(approximationMove.getApproximations().size() - 1).getValue());
                approximationMove.setWeightStrokeCorrection(approximationMove.getWeightStroke() * approximationMove.getCorrection());
                approximationMove.setWeightStrokeCorrectionCorrection(approximationMove.getWeightStrokeCorrection() * approximationMove.getCorrection());
                sumPVVOne += approximationMove.getWeightStrokeCorrectionCorrection();
                sumDistanceOne += approximationMove.getDistance();
                sumStationsOne += approximationMove.getStationCount();
                sumCorrectionOne += approximationMove.getCorrection();
                sumPVOne += approximationMove.getWeightStrokeCorrection();
            }
            checkApproximationMove.setWeightStrokeCorrectionCorrection(sumPVVOne);
            checkApproximationMove.setDistance(sumDistanceOne);
            checkApproximationMove.setStationCount(sumStationsOne);
            checkApproximationMove.setCorrection(sumCorrectionOne);
            checkApproximationMove.setWeightStrokeCorrection(sumPVOne);
            sumPVV += checkApproximationMove.getWeightStrokeCorrectionCorrection();
            sumDistance += checkApproximationMove.getDistance();
            sumStations += checkApproximationMove.getStationCount();
        }
        calculationData.setNiu(Math.sqrt(sumPVV / (calculationData.getReperList().size() - calculationData.getReperList().stream().filter(reper -> reper.getReperType().equals(ReperType.POINT)).count())));
        calculationData.setM((calculationData.getNiu() / 10) * Math.sqrt(sumStations / sumDistance));
        return calculationData;
    }
}
