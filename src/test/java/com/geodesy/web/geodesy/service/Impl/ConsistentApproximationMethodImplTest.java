package com.geodesy.web.geodesy.service.Impl;

import com.geodesy.web.geodesy.dto.utils.PointDtoParser;
import com.geodesy.web.geodesy.model.CalculationData;
import com.geodesy.web.geodesy.model.Move;
import com.geodesy.web.geodesy.model.Reper;
import com.geodesy.web.geodesy.model.enums.CalculationTypeName;
import com.geodesy.web.geodesy.model.enums.MoveType;
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

    //    private static final List<Move> MOVE_LIST = new ArrayList<>();
//    private static final List<Move> EXPECTED_MOVE_LIST = new ArrayList<>();
    private static final CalculationData CALCULATION_DATA = new CalculationData();

    @InjectMocks
    private ConsistentApproximationMethodImpl defaultNetworkService;

    @Before
    public void setUp() throws Exception {
        if (CALCULATION_DATA.getMoveList() == null)
            CALCULATION_DATA
                    .setId(1L)

                    .setCalculationTypeName(CalculationTypeName.FIRST)

                    .addReper(new Reper().setId(1L).setName("Rp1").setHeight(261.837))
                    .addReper(new Reper().setId(1L).setName("Rp2").setHeight(269.508))
                    .addReper(new Reper().setId(1L).setName("Rp3").setHeight(260.705))
                    .addReper(new Reper().setId(1L).setName("Rp4").setHeight(254.019))
                    .addReper(new Reper().setId(1L).setName("Rp5").setHeight(257.203))
                    .addReper(new Reper().setId(1L).setName("Rp6").setHeight(254.825))

                    .addMove(new Move().setId(1L).setName("Rp1-1").setDifference(3.401).setStationCount(47).setDistance(4.0))
                    .addMove(new Move().setId(1L).setName("Rp2-1").setDifference(-4.269).setStationCount(51).setDistance(3.4))
                    .addMove(new Move().setId(1L).setName("Rp3-2").setDifference(-3.223).setStationCount(47).setDistance(3.7))
                    .addMove(new Move().setId(1L).setName("Rp4-2").setDifference(3.448).setStationCount(86).setDistance(3.9))
                    .addMove(new Move().setId(1L).setName("Rp5-3").setDifference(3.681).setStationCount(53).setDistance(2.9))
                    .addMove(new Move().setId(1L).setName("Rp6-3").setDifference(6.050).setStationCount(29).setDistance(4.4))
                    .addMove(new Move().setId(1L).setName("1-2").setDifference(-7.802).setStationCount(56).setDistance(4.0))
                    .addMove(new Move().setId(1L).setName("2-3").setDifference(3.423).setStationCount(48).setDistance(2.9))
                    .addMove(new Move().setId(1L).setName("3-1").setDifference(4.393).setStationCount(31).setDistance(3.2));

//        MOVE_LIST.add(new Move().setStationCount(47));
//        MOVE_LIST.add(new Move().setStationCount(51));
//        MOVE_LIST.add(new Move().setStationCount(56));
//        MOVE_LIST.add(new Move().setStationCount(31));

//        EXPECTED_MOVE_LIST.add(new Move().setStationCount(47).setWeight(.021).setWeightStroke(.234));
//        EXPECTED_MOVE_LIST.add(new Move().setStationCount(51).setWeight(.02).setWeightStroke(.215));
//        EXPECTED_MOVE_LIST.add(new Move().setStationCount(56).setWeight(.018).setWeightStroke(.197));
//        EXPECTED_MOVE_LIST.add(new Move().setStationCount(31).setWeight(.032).setWeightStroke(.355));

//        EXPECTED_MOVE_LIST.add(new Move().setStationCount(47).setWeight(.021).setWeightStroke(.234));
//        EXPECTED_MOVE_LIST.add(new Move().setStationCount(51).setWeight(.02).setWeightStroke(.215));
//        EXPECTED_MOVE_LIST.add(new Move().setStationCount(56).setWeight(.018).setWeightStroke(.197));
//        EXPECTED_MOVE_LIST.add(new Move().setStationCount(31).setWeight(.032).setWeightStroke(.355));
    }

    @Test
    public void getWeight() {
        defaultNetworkService.normilize(CALCULATION_DATA);
        defaultNetworkService.getWeight(CALCULATION_DATA);
        CALCULATION_DATA.getMoveList().forEach(move -> LOGGER.info("MV.NAME : [ " + move.getName() + " ] WEIGHT : [ " + format.format(move.getWeight()) + " ]"));
        assertEquals(12, CALCULATION_DATA.getMoveList().size());
    }

    @Test
    public void getApproximatePointHeight() {
        defaultNetworkService.normilize(CALCULATION_DATA);
        defaultNetworkService.getApproximatePointHeight(CALCULATION_DATA);
        CALCULATION_DATA.getReperList().forEach(reper -> LOGGER.info("\nR.NAME : [ " + reper.getName() + " ] height : [ " + format.format(reper.getHeight()) + " ] type : [ " + reper.getReperType() + " ]"));
        CALCULATION_DATA.getMoveList().forEach(reper -> LOGGER.info("M.NAME : [ " + reper.getName() + " ]"));
        assertEquals(9, CALCULATION_DATA.getMoveList().size());
        assertEquals(9, CALCULATION_DATA.getReperList().size());
    }

    @Test
    public void fulfillMoves() {
        defaultNetworkService.normilize(CALCULATION_DATA);
        defaultNetworkService.fulfillMoves(CALCULATION_DATA);
        CALCULATION_DATA.getMoveList().sort(new MoveNameComparator());
        CALCULATION_DATA.getMoveList().forEach(move -> LOGGER.info("NAME : [ " + move.getName() + " ]"));
        assertEquals(12, CALCULATION_DATA.getMoveList().size());
    }

    @Test
    public void createCheckMoves() {
        defaultNetworkService.createCheckMoves(CALCULATION_DATA);
        CALCULATION_DATA.getMoveList().sort(new MoveNameComparator());
        CALCULATION_DATA.getMoveList().forEach(move -> LOGGER.info("NAME : [ " + move.getName() + " ] WEIGHT : [ " + format.format(move.getWeight()) + " ] WEIGHT` : [ " + format.format(move.getWeightStroke()) + " ]"));
        assertEquals(15, CALCULATION_DATA.getMoveList().size());
    }

    @Test
    public void normilize() {
    }

    @Test
    public void getWeightStroke() {
        defaultNetworkService.getWeightStroke(CALCULATION_DATA);
        CALCULATION_DATA.getMoveList().forEach(move -> LOGGER.info("NAME : [ " + move.getName() + " ] WEIGHT : [ " + format.format(move.getWeight()) + " ] WEIGHT` : [ " + format.format(move.getWeightStroke()) + " ]"));
        assertEquals(12, CALCULATION_DATA.getMoveList().size());
    }

    @Test
    public void calculateApproximation() {
        defaultNetworkService.calculateApproximationFull(CALCULATION_DATA);
        CALCULATION_DATA.getMoveList().stream()
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
        CALCULATION_DATA.getMoveList().stream()
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
        LOGGER.info(CALCULATION_DATA.getMoveList().get(0).getApproximations().size());
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