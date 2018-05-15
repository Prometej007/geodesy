package com.geodesy.web.geodesy.service.Impl;

import com.geodesy.web.geodesy.model.CalculationData;
import com.geodesy.web.geodesy.model.Move;
import com.geodesy.web.geodesy.model.Reper;
import com.geodesy.web.geodesy.model.enums.MoveType;
import com.geodesy.web.geodesy.model.enums.ReperType;
import com.geodesy.web.geodesy.service.DefaultNetworkService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.aspectj.runtime.internal.Conversions.doubleValue;

@Service
public class DefaultNetworkServiceImpl implements DefaultNetworkService {
    @Override
    public Double averageReperWeight(Double weight, Double approximation) {
        return null;
    }

    @Override
    public Double getApproxamation(Double height, Double difference) {
        return null;
    }

    @Override
    public CalculationData normilize(CalculationData calculationData) {
        return calculationData
                .setMoveList(calculationData.getMoveList().stream().map(move -> move.setMoveType(MoveType.DEFAULT)).collect(toList()))
                .setReperList(calculationData.getReperList().stream().map(reper -> reper.setReperType(reper.getName().matches("Rp\\d") ? ReperType.REPER : ReperType.POINT)).collect(toList()));
    }

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
        List<Reper> restPoints = points.stream().filter(reper -> !reper.getName().equals(point.getName())).collect(toList());
        if (moves.stream().filter(move -> move.getName().contains(point.getName())).count() < ((points.size() - 1) * 2)) {
            calculationData = fulfillPointMoves(calculationData, moves, point, restPoints);
        }
        return calculationData;
    }

    private CalculationData fulfillPointMoves(CalculationData calculationData, List<Move> moves, Reper point, List<Reper> restPoints) {
        List<Move> pointMoves = moves.stream().filter(move -> move.getName().contains(point.getName())).collect(toList());
        for (Reper rPoint : restPoints) {
            if (pointMoves.stream().noneMatch(move -> move.getName().contains(point.getName() + "-"))) {
                calculationData = createMove(calculationData, point, pointMoves, rPoint);
            } else if (pointMoves.stream().noneMatch(move -> move.getName().contains(rPoint.getName() + "-"))) {
                calculationData = createMove(calculationData, rPoint, pointMoves, point);
            }
        }
        return calculationData;
    }

    private CalculationData createMove(CalculationData calculationData, Reper point, List<Move> pointMoves, Reper rPoint) {
        Move rMove = pointMoves.stream().filter(move -> move.getName().contains(rPoint.getName() + "-")).findFirst().orElseThrow(RuntimeException::new);
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

    @Override
    public List<Move> getWeight(List<Move> moveList) {
        Double weight = .0;
        moveList = moveList.stream().map(move -> move.setWeight(doubleValue(1.0 / move.getStationCount()))).collect(toList());
        for (Move move : moveList) {
            weight += move.getWeight();
        }
        Double we = weight;
        return moveList.stream().map(move -> move.setWeightStroke(move.getWeight() / we)).collect(toList());
    }
}
