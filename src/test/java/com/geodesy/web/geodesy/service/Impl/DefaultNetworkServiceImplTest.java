package com.geodesy.web.geodesy.service.Impl;

import com.geodesy.web.geodesy.model.CalculationData;
import com.geodesy.web.geodesy.model.Move;
import com.geodesy.web.geodesy.model.Reper;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class DefaultNetworkServiceImplTest {

    private static final Logger LOGGER = Logger.getLogger(DefaultNetworkServiceImplTest.class);

    private static final NumberFormat format = new DecimalFormat("#.####");

    private static final List<Move> MOVE_LIST = new ArrayList<>();
    private static final List<Move> EXPECTED_MOVE_LIST = new ArrayList<>();
    private static final CalculationData CALCULATION_DATA = new CalculationData();

    @InjectMocks
    private DefaultNetworkServiceImpl defaultNetworkService;

    @Before
    public void setUp() throws Exception {
        CALCULATION_DATA
                .addReper(new Reper().setName("Rp1").setHeight(284.322))
                .addReper(new Reper().setName("Rp2").setHeight(290.998))
                .addReper(new Reper().setName("Rp3").setHeight(291.472))

                .addMove(new Move().setName("Rp1-1").setDifference(4.251).setStationCount(51).setDistance(8.1))
                .addMove(new Move().setName("Rp2-2").setDifference(3.368).setStationCount(61).setDistance(9.1))
                .addMove(new Move().setName("Rp3-3").setDifference(5.68).setStationCount(49).setDistance(5.1))
                .addMove(new Move().setName("1-2").setDifference(5.796).setStationCount(71).setDistance(10.1))
                .addMove(new Move().setName("1-3").setDifference(8.569).setStationCount(45).setDistance(5.1))
                .addMove(new Move().setName("2-3").setDifference(2.785).setStationCount(50).setDistance(7.6))
        ;

        MOVE_LIST.add(new Move().setStationCount(47));
        MOVE_LIST.add(new Move().setStationCount(51));
        MOVE_LIST.add(new Move().setStationCount(56));
        MOVE_LIST.add(new Move().setStationCount(31));

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
        CalculationData data = defaultNetworkService.normilize(CALCULATION_DATA);
        data = defaultNetworkService.getApproximatePointHeight(data);
        data = defaultNetworkService.fulfillMoves(data);
        List<Move> moves = defaultNetworkService.getWeight(data.getMoveList());
        for (Move move : moves) {
            LOGGER.info("MV.NAME : [ " + move.getName() + " ] P[act] : [ " + format.format(move.getWeight()) + " ], P`[act] : [ " + format.format(move.getWeightStroke()) + " ]\n");
        }
    }

    @Test
    public void getApproximatePointHeight() {
        CalculationData data = defaultNetworkService.normilize(CALCULATION_DATA);
        data = defaultNetworkService.getApproximatePointHeight(data);
        data.getReperList().forEach(reper -> LOGGER.info("\nNAME : [ " + reper.getName() + " ] height : [ " + reper.getHeight() + " ] type : [ " + reper.getReperType() + " ]"));
    }

    @Test
    public void fulfillMoves() {
        CalculationData data = defaultNetworkService.normilize(CALCULATION_DATA);
        data = defaultNetworkService.getApproximatePointHeight(data);
        data = defaultNetworkService.fulfillMoves(data);
        data.getMoveList().forEach(move -> LOGGER.info("NAME : [ "+move.getName()+" ]"));
        assertEquals(9,data.getMoveList().size());
    }
}