package com.geodesy.web.geodesy.dto.utils;

import com.geodesy.web.geodesy.dto.ApproximationDto;
import com.geodesy.web.geodesy.dto.PointDto;
import com.geodesy.web.geodesy.dto.PointOneDto;
import com.geodesy.web.geodesy.model.approximation.Approximation;
import com.geodesy.web.geodesy.model.approximation.ApproximationMove;
import com.geodesy.web.geodesy.model.approximation.ApproximationReper;
import com.geodesy.web.geodesy.model.approximation.CalculationData;
import com.geodesy.web.geodesy.model.utils.enums.ReperType;
import com.geodesy.web.geodesy.model.utils.ApproximationStepComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PointDtoParser {
    public List<PointDto> parse(CalculationData calculationData) {
        List<PointDto> result = new ArrayList<>();
        for (ApproximationReper point :
                calculationData.getReperList().stream().filter(reper -> reper.getReperType().equals(ReperType.POINT)).collect(Collectors.toList())) {
            PointDto pointDto = new PointDto();
            pointDto.setNumber(Long.valueOf(point.getName()));
            for (ApproximationMove approximationMove :
                    calculationData.getApproximationMoveList().stream().filter(move -> move.getName().contains("-" + point.getName())).collect(Collectors.toList())) {
                PointOneDto pointOneDto = new PointOneDto();
                pointOneDto
                        .setNameMuve(approximationMove.getName())
                        .setNameReper(approximationMove.getName().split("-")[0])
                        .setHeight(calculationData.getReperList().stream().filter(reper -> reper.getName().equals(approximationMove.getName().split("-")[0])).findFirst().orElseThrow(RuntimeException::new).getHeight().toString())
                        .setSum(approximationMove.getDifference().toString())
                        .setStation(approximationMove.getStationCount().toString())
                        .setWeight(approximationMove.getWeight().toString())
                        .set_weight(approximationMove.getWeightStroke().toString())
                        .setCorrection(approximationMove.getCorrection().toString())
                        .setWeightCorrection(approximationMove.getWeightStrokeCorrection().toString())
                        .setWeightCorrectionCorrection(approximationMove.getWeightStrokeCorrectionCorrection().toString())
                        .setApproximationDto(approximationMove.getApproximations().stream().sorted(new ApproximationStepComparator()).map(approximation -> new ApproximationDto().setValue(approximation.getValue().toString())).collect(Collectors.toList()));
                pointDto.addPointOne(pointOneDto);
            }
            ApproximationMove approximationMove = calculationData.getApproximationMoveList().stream().filter(move1 -> move1.getName().equals(point.getName())).findFirst().orElseThrow(RuntimeException::new);
            List<String> params = new ArrayList<>();
            params.add(approximationMove.getDifference().toString());
            params.add(approximationMove.getWeight().toString());
            params.add(approximationMove.getWeightStroke().toString());
            for (Double aDouble :
                    approximationMove.getApproximations().stream().sorted(new ApproximationStepComparator()).map(Approximation::getValue).collect(Collectors.toList())) {
                params.add(aDouble.toString());
            }
            params.add(approximationMove.getCorrection().toString());
            params.add(approximationMove.getWeightStrokeCorrection().toString());
            params.add(approximationMove.getWeightStrokeCorrectionCorrection().toString());
            pointDto.setCheckParams(params);
            result.add(pointDto);
        }
        return result;
    }
}
