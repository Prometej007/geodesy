package com.geodesy.web.geodesy.service.Impl;

import com.geodesy.web.geodesy.model.Move;
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

@RunWith(MockitoJUnitRunner.class)
public class DefaultNetworkServiceImplTest {

    private static final Logger LOGGER = Logger.getLogger(DefaultNetworkServiceImplTest.class);

    private static final NumberFormat format = new DecimalFormat("#.####");

    private static final List<Move> MOVE_LIST = new ArrayList<>();
    private static final List<Move> EXPECTED_MOVE_LIST = new ArrayList<>();

    @InjectMocks
    private DefaultNetworkServiceImpl defaultNetworkService;

    @Before
    public void setUp() throws Exception {
        MOVE_LIST.add(new Move().setStationCount(47));
        MOVE_LIST.add(new Move().setStationCount(51));
        MOVE_LIST.add(new Move().setStationCount(56));
        MOVE_LIST.add(new Move().setStationCount(31));

        EXPECTED_MOVE_LIST.add(new Move().setStationCount(47).setWeight(.021).setWeightStroke(.234));
        EXPECTED_MOVE_LIST.add(new Move().setStationCount(51).setWeight(.02).setWeightStroke(.215));
        EXPECTED_MOVE_LIST.add(new Move().setStationCount(56).setWeight(.018).setWeightStroke(.197));
        EXPECTED_MOVE_LIST.add(new Move().setStationCount(31).setWeight(.032).setWeightStroke(.355));
    }

    @Test
    public void getWeight() {
        List<Move> moves = defaultNetworkService.getWeight(MOVE_LIST);
        for (int i = 0; i < EXPECTED_MOVE_LIST.size(); i++) {
            LOGGER.info("\nP[exp] : [ " + EXPECTED_MOVE_LIST.get(i).getWeight() + " ], P`[exp] : [ " + EXPECTED_MOVE_LIST.get(i).getWeightStroke() + " ]\n" +
                    "P[act] : [ " + format.format(moves.get(i).getWeight()) + " ], P`[act] : [ " + format.format(moves.get(i).getWeightStroke()) + " ]\n");
        }
    }
}