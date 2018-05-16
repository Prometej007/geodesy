package com.geodesy.web.geodesy.service.Impl;

import com.geodesy.web.geodesy.model.Approximation;
import com.geodesy.web.geodesy.model.CalculationData;
import com.geodesy.web.geodesy.model.Move;
import com.geodesy.web.geodesy.model.Reper;
import com.geodesy.web.geodesy.model.enums.MoveType;
import com.geodesy.web.geodesy.model.enums.ReperType;
import com.geodesy.web.geodesy.model.utils.MoveNameComparator;
import com.geodesy.web.geodesy.service.DefaultNetworkService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.aspectj.runtime.internal.Conversions.doubleValue;
import static org.aspectj.runtime.internal.Conversions.intValue;

@Service
public class DefaultNetworkServiceImpl implements DefaultNetworkService {
    /**
     * {@inheritDoc}
     */
    @Override
    public CalculationData normilize(CalculationData calculationData) {
        return calculationData
                .setMoveList(calculationData.getMoveList().stream().map(move -> move.getMoveType() == null ? move.setMoveType(MoveType.DEFAULT) : move).collect(toList()))
                .setReperList(calculationData.getReperList().stream().map(reper -> reper.setReperType(reper.getName().matches("Rp\\d") ? ReperType.REPER : ReperType.POINT)).collect(toList()));
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
        List<Reper> points = calculationData.getReperList().stream().filter(reper -> reper.getReperType().equals(ReperType.POINT)).collect(toList());
        List<Move> moves = calculationData.getMoveList().stream().filter(move -> !move.getName().contains("Rp")).collect(toList());
        for (Reper point : points) {
            calculationData = checkPointMoves(calculationData, points, moves, point);
            moves = calculationData.getMoveList().stream().filter(move -> !move.getName().contains("Rp")).collect(toList());
        }
        return calculationData;
    }

    private CalculationData checkPointMoves(CalculationData calculationData, List<Reper> points, List<Move> moves, Reper point) {
        List<Reper> restPoints = points.stream().filter(reper -> reper.getReperType().equals(ReperType.POINT) && !reper.getName().equals(point.getName())).collect(toList());
        if (moves.stream().filter(move -> move.getName().contains(point.getName())).count() < ((points.size() - 1) * 2)) {
            calculationData = fulfillPointMoves(calculationData, moves, point, restPoints);
        }
        return calculationData;
    }

    /**
     * @param calculationData calculationData
     * @param moves           moves with only points
     * @param point           point for which will be created moves
     * @param restPoints      other points
     * @return new moves closing all necessary connections for point
     */
    private CalculationData fulfillPointMoves(CalculationData calculationData, List<Move> moves, Reper point, List<Reper> restPoints) {
        List<Move> pointMoves = moves.stream().filter(move -> move.getName().contains(point.getName())).collect(toList());
        for (Reper rPoint : restPoints) {
            if (pointMoves.stream().noneMatch(move -> move.getName().contains(point.getName() + "-" + rPoint.getName()))) {
                calculationData = createMove(calculationData, point, pointMoves, rPoint);
            } else if (pointMoves.stream().noneMatch(move -> move.getName().contains(rPoint.getName() + "-" + point.getName()))) {
                calculationData = createMove(calculationData, rPoint, pointMoves, point);
            }
        }
        return calculationData;
    }

    /**
     * @param calculationData calculationData
     * @param point           in point
     * @param pointMoves      all moves with only points
     * @param rPoint          out point
     * @return move with name rPoint-point
     */
    private CalculationData createMove(CalculationData calculationData, Reper point, List<Move> pointMoves, Reper rPoint) {
        Move rMove = pointMoves.stream().filter(move -> move.getName().contains(rPoint.getName() + "-" + point.getName())).findFirst().orElseThrow(RuntimeException::new);
        calculationData.addMove(
                new Move()
                        .setDifference(.0 - rMove.getDifference())
                        .setStationCount(rMove.getStationCount())
                        .setDistance(rMove.getDistance())
                        .setName(point.getName() + "-" + rPoint.getName())
                        .setMoveType(MoveType.DEFAULT)
        );
        return calculationData;
    }

    /**
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
            Double chDifference = .0, chWeight = .0, chWeightStroke = .0;
            for (Move move : moves) {
                chDifference += move.getDifference();
                chWeight += move.getWeight();
                chWeightStroke += move.getWeightStroke();
            }
            calculationData.addMove(
                    new Move()
                            .setMoveType(MoveType.CHECK)
                            .setName(point.getName())
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
        List<Reper> points = calculationData.getReperList().stream().filter(reper -> reper.getReperType().equals(ReperType.POINT)).collect(toList());
        Integer step = getStep(calculationData);
        for (int i = 0; i < points.size(); i++) {
            Integer tI = i;
            Move checkMove = calculationData.getMoveList().stream().filter(move -> move.getName().equals(points.get(tI).getName()) && move.getMoveType().equals(MoveType.CHECK)).findFirst().orElseThrow(RuntimeException::new);
            List<Move> moves = calculationData.getMoveList().stream().filter(move -> move.getMoveType().equals(MoveType.DEFAULT) && move.getName().contains("-" + points.get(tI).getName())).collect(toList());
            List<Reper> connectedRepers = getConnectedRepers(calculationData, moves);
            setApproximations(step, moves, connectedRepers);
            Double checkHeight = getCheckHeight(step, moves);
            checkMove.addApproximation(new Approximation().setValue(checkHeight).setStep(step));
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
            moves.get(i).addApproximation(new Approximation().setStep(step).setValue(connectedRepers.get(i).getHeight() + moves.get(i).getDifference()));
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
    public CalculationData calculateApproximation(CalculationData calculationData, Double maxDifference) {
        while (!checkApproximations(calculationData, maxDifference)) {
            calculationData = calculateApproximation(calculationData);
        }
        return calculationData;
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
}
