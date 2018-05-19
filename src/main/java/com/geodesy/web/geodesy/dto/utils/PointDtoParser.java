package com.geodesy.web.geodesy.dto.utils;

import com.geodesy.web.geodesy.dto.ApproximationDto;
import com.geodesy.web.geodesy.dto.PointDto;
import com.geodesy.web.geodesy.dto.PointOneDto;
import com.geodesy.web.geodesy.model.Approximation;
import com.geodesy.web.geodesy.model.CalculationData;
import com.geodesy.web.geodesy.model.Move;
import com.geodesy.web.geodesy.model.Reper;
import com.geodesy.web.geodesy.model.enums.ReperType;
import com.geodesy.web.geodesy.model.utils.ApproximationStepComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PointDtoParser {
    public List<PointDto> parse(CalculationData calculationData) {
        List<PointDto> result = new ArrayList<>();
        for (Reper point :
                calculationData.getReperList().stream().filter(reper -> reper.getReperType().equals(ReperType.POINT)).collect(Collectors.toList())) {
            PointDto pointDto = new PointDto();
            pointDto.setNumber(Long.valueOf(point.getName()));
            for (Move move :
                    calculationData.getMoveList().stream().filter(move -> move.getName().contains("-" + point.getName())).collect(Collectors.toList())) {
                PointOneDto pointOneDto = new PointOneDto();
                pointOneDto
                        .setNameMuve(move.getName())
                        .setNameReper(move.getName().split("-")[0])
                        .setHeight(calculationData.getReperList().stream().filter(reper -> reper.getName().equals(move.getName().split("-")[0])).findFirst().orElseThrow(RuntimeException::new).getHeight().toString())
                        .setSum(move.getDifference().toString())
                        .setStation(move.getStationCount().toString())
                        .setWeight(move.getWeight().toString())
                        .set_weight(move.getWeightStroke().toString())
                        .setCorrection(move.getCorrection().toString())
                        .setWeightCorrection(move.getWeightStrokeCorrection().toString())
                        .setWeightCorrectionCorrection(move.getWeightStrokeCorrectionCorrection().toString())
                        .setApproximationDto(move.getApproximations().stream().sorted(new ApproximationStepComparator()).map(approximation -> new ApproximationDto().setValue(approximation.getValue().toString())).collect(Collectors.toList()));
                pointDto.addPointOne(pointOneDto);
            }
            Move move = calculationData.getMoveList().stream().filter(move1 -> move1.getName().equals(point.getName())).findFirst().orElseThrow(RuntimeException::new);
            List<String> params = new ArrayList<>();
            params.add(move.getDifference().toString());
            params.add(move.getWeight().toString());
            params.add(move.getWeightStroke().toString());
            for (Double aDouble :
                    move.getApproximations().stream().sorted(new ApproximationStepComparator()).map(Approximation::getValue).collect(Collectors.toList())) {
                params.add(aDouble.toString());
            }
            params.add(move.getCorrection().toString());
            params.add(move.getWeightStrokeCorrection().toString());
            params.add(move.getWeightStrokeCorrectionCorrection().toString());
            pointDto.setCheckParams(params);
            result.add(pointDto);
        }
        return result;
    }
}
