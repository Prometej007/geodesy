package com.geodesy.web.geodesy.service.Impl;

import com.geodesy.web.geodesy.dto.utils.PointDtoParser;
import com.geodesy.web.geodesy.model.approximation.ApproximationMove;
import com.geodesy.web.geodesy.model.approximation.ApproximationReper;
import com.geodesy.web.geodesy.model.approximation.CalculationData;
import com.geodesy.web.geodesy.model.utils.enums.CalculationTypeName;
import com.geodesy.web.geodesy.model.utils.enums.MoveType;
import com.geodesy.web.geodesy.model.utils.MoveNameComparator;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ConsistentApproximationMethodImplTest {

    private static final Logger LOGGER = Logger.getLogger(ConsistentApproximationMethodImplTest.class);

    private static final NumberFormat format = new DecimalFormat("#.####");

    //    private static final List<ApproximationMove> MOVE_LIST = new ArrayList<>();
//    private static final List<ApproximationMove> EXPECTED_MOVE_LIST = new ArrayList<>();
    private static final CalculationData CALCULATION_DATA = new CalculationData();

    @InjectMocks
    private ConsistentApproximationMethodImpl defaultNetworkService;

    @Before
    public void setUp() throws Exception {
        if (CALCULATION_DATA.getApproximationMoveList() == null)
            CALCULATION_DATA
                    .setId(1L)

                    .setCalculationTypeName(CalculationTypeName.FIRST)

                    .addReper(new ApproximationReper().setId(1L).setName("Rp1").setHeight(261.837))
                    .addReper(new ApproximationReper().setId(1L).setName("Rp2").setHeight(269.508))
                    .addReper(new ApproximationReper().setId(1L).setName("Rp3").setHeight(260.705))
                    .addReper(new ApproximationReper().setId(1L).setName("Rp4").setHeight(254.019))
                    .addReper(new ApproximationReper().setId(1L).setName("Rp5").setHeight(257.203))
                    .addReper(new ApproximationReper().setId(1L).setName("Rp6").setHeight(254.825))

                    .addMove(new ApproximationMove().setId(1L).setName("Rp1-1").setDifference(3.401).setStationCount(47).setDistance(4.0))
                    .addMove(new ApproximationMove().setId(1L).setName("Rp2-1").setDifference(-4.269).setStationCount(51).setDistance(3.4))
                    .addMove(new ApproximationMove().setId(1L).setName("Rp3-2").setDifference(-3.223).setStationCount(47).setDistance(3.7))
                    .addMove(new ApproximationMove().setId(1L).setName("Rp4-2").setDifference(3.448).setStationCount(86).setDistance(3.9))
                    .addMove(new ApproximationMove().setId(1L).setName("Rp5-3").setDifference(3.681).setStationCount(53).setDistance(2.9))
                    .addMove(new ApproximationMove().setId(1L).setName("Rp6-3").setDifference(6.050).setStationCount(29).setDistance(4.4))
                    .addMove(new ApproximationMove().setId(1L).setName("1-2").setDifference(-7.802).setStationCount(56).setDistance(4.0))
                    .addMove(new ApproximationMove().setId(1L).setName("2-3").setDifference(3.423).setStationCount(48).setDistance(2.9))
                    .addMove(new ApproximationMove().setId(1L).setName("3-1").setDifference(4.393).setStationCount(31).setDistance(3.2));

//        MOVE_LIST.add(new ApproximationMove().setStationCount(47));
//        MOVE_LIST.add(new ApproximationMove().setStationCount(51));
//        MOVE_LIST.add(new ApproximationMove().setStationCount(56));
//        MOVE_LIST.add(new ApproximationMove().setStationCount(31));

//        EXPECTED_MOVE_LIST.add(new ApproximationMove().setStationCount(47).setWeight(.021).setWeightStroke(.234));
//        EXPECTED_MOVE_LIST.add(new ApproximationMove().setStationCount(51).setWeight(.02).setWeightStroke(.215));
//        EXPECTED_MOVE_LIST.add(new ApproximationMove().setStationCount(56).setWeight(.018).setWeightStroke(.197));
//        EXPECTED_MOVE_LIST.add(new ApproximationMove().setStationCount(31).setWeight(.032).setWeightStroke(.355));

//        EXPECTED_MOVE_LIST.add(new ApproximationMove().setStationCount(47).setWeight(.021).setWeightStroke(.234));
//        EXPECTED_MOVE_LIST.add(new ApproximationMove().setStationCount(51).setWeight(.02).setWeightStroke(.215));
//        EXPECTED_MOVE_LIST.add(new ApproximationMove().setStationCount(56).setWeight(.018).setWeightStroke(.197));
//        EXPECTED_MOVE_LIST.add(new ApproximationMove().setStationCount(31).setWeight(.032).setWeightStroke(.355));
    }

    @Test
    public void getWeight() {
        defaultNetworkService.normilize(CALCULATION_DATA);
        defaultNetworkService.getWeight(CALCULATION_DATA);
        CALCULATION_DATA.getApproximationMoveList().forEach(move -> LOGGER.info("MV.NAME : [ " + move.getName() + " ] WEIGHT : [ " + format.format(move.getWeight()) + " ]"));
        assertEquals(12, CALCULATION_DATA.getApproximationMoveList().size());
    }

    @Test
    public void getApproximatePointHeight() {
        defaultNetworkService.normilize(CALCULATION_DATA);
        defaultNetworkService.getApproximatePointHeight(CALCULATION_DATA);
        CALCULATION_DATA.getApproximationReperList().forEach(reper -> LOGGER.info("\nR.NAME : [ " + reper.getName() + " ] height : [ " + format.format(reper.getHeight()) + " ] type : [ " + reper.getReperType() + " ]"));
        CALCULATION_DATA.getApproximationMoveList().forEach(reper -> LOGGER.info("M.NAME : [ " + reper.getName() + " ]"));
        assertEquals(9, CALCULATION_DATA.getApproximationMoveList().size());
        assertEquals(9, CALCULATION_DATA.getApproximationReperList().size());
    }

    @Test
    public void fulfillMoves() {
        defaultNetworkService.normilize(CALCULATION_DATA);
        defaultNetworkService.fulfillMoves(CALCULATION_DATA);
        CALCULATION_DATA.getApproximationMoveList().sort(new MoveNameComparator());
        CALCULATION_DATA.getApproximationMoveList().forEach(move -> LOGGER.info("NAME : [ " + move.getName() + " ]"));
        assertEquals(12, CALCULATION_DATA.getApproximationMoveList().size());
    }

    @Test
    public void createCheckMoves() {
        defaultNetworkService.createCheckMoves(CALCULATION_DATA);
        CALCULATION_DATA.getApproximationMoveList().sort(new MoveNameComparator());
        CALCULATION_DATA.getApproximationMoveList().forEach(move -> LOGGER.info("NAME : [ " + move.getName() + " ] WEIGHT : [ " + format.format(move.getWeight()) + " ] WEIGHT` : [ " + format.format(move.getWeightStroke()) + " ]"));
        assertEquals(15, CALCULATION_DATA.getApproximationMoveList().size());
    }

    @Test
    public void normilize() {
    }

    @Test
    public void getWeightStroke() {
        defaultNetworkService.getWeightStroke(CALCULATION_DATA);
        CALCULATION_DATA.getApproximationMoveList().forEach(move -> LOGGER.info("NAME : [ " + move.getName() + " ] WEIGHT : [ " + format.format(move.getWeight()) + " ] WEIGHT` : [ " + format.format(move.getWeightStroke()) + " ]"));
        assertEquals(12, CALCULATION_DATA.getApproximationMoveList().size());
    }

    @Test
    public void calculateApproximation() {
        defaultNetworkService.calculateApproximationFull(CALCULATION_DATA);
        CALCULATION_DATA.getApproximationMoveList().stream()
//                .filter(move -> move.getMoveType().equals(MoveType.CHECK))
                .forEach(move -> LOGGER.info("NAME : [ " + move.getName() +
                        " ] DIFFERENCE : [ " + move.getDifference() +
                        " ] WEIGHT : [ " + format.format(move.getWeight()) +
                        " ] WEIGHT` : [ " + format.format(move.getWeightStroke()) +
                        " ] APPROXIMATIONS : " + move.getApproximations().stream().map(approximation -> format.format(approximation.getValue())).collect(toList())));
        defaultNetworkService.fulfillCorrections(CALCULATION_DATA);
        LOGGER.info(CALCULATION_DATA);
    }

    //    @Test
    public void calculateApproximation1() {
        defaultNetworkService.calculateApproximationFull(CALCULATION_DATA);
        CALCULATION_DATA.getApproximationMoveList().stream()
                .filter(move -> move.getMoveType().equals(MoveType.CHECK))
                .forEach(move -> LOGGER.info("NAME : [ " + move.getName() +
                        " ] DISTANCE : [ " + format.format(move.getDistance()) +
                        " ] WEIGHT : [ " + format.format(move.getWeight()) +
                        " ] WEIGHT` : [ " + format.format(move.getWeightStroke()) +
                        " ] APPROXIMATIONS : " + move.getApproximations().stream().map(approximation -> format.format(approximation.getValue())).collect(toList())));
        defaultNetworkService.fulfillCorrections(CALCULATION_DATA);
        LOGGER.info(CALCULATION_DATA);
    }

    @Test
    public void calculate() {
        defaultNetworkService.calculate(CALCULATION_DATA);
//        LOGGER.info("+++++++++++++++++++++++++++++++++++++++++++");
//        LOGGER.info("+++++++++++++++++++++++++++++++++++++++++++");
//        LOGGER.info("+++++++++++++++++++++++++++++++++++++++++++");
//        LOGGER.info("+++++++++++++++++++++++++++++++++++++++++++");
//        LOGGER.info("+++++++++++++++++++++++++++++++++++++++++++");
//        LOGGER.info("+++++++++++++++++++++++++++++++++++++++++++");
//        LOGGER.info("+++++++++++++++++++++++++++++++++++++++++++");
        LOGGER.info(CALCULATION_DATA.getApproximationMoveList().get(0).getApproximations().size());
//        LOGGER.info("+++++++++++++++++++++++++++++++++++++++++++");
//        LOGGER.info("+++++++++++++++++++++++++++++++++++++++++++");
//        LOGGER.info("+++++++++++++++++++++++++++++++++++++++++++");
//        LOGGER.info("+++++++++++++++++++++++++++++++++++++++++++");
//        LOGGER.info("+++++++++++++++++++++++++++++++++++++++++++");
//        LOGGER.info("+++++++++++++++++++++++++++++++++++++++++++");
        LOGGER.info(new PointDtoParser().parse(CALCULATION_DATA));
    }

//    @Test
//    public void fulfillCorrections() {
//    }
}