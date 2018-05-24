package com.geodesy.web.geodesy.service.Impl;

import com.geodesy.web.geodesy.model.poligon.Poligon;
import com.geodesy.web.geodesy.model.poligon.PoligonData;
import com.geodesy.web.geodesy.model.poligon.PoligonMove;
import com.geodesy.web.geodesy.model.poligon.PoligonReper;
import com.geodesy.web.geodesy.model.utils.enums.CalculationTypeName;
import com.geodesy.web.geodesy.model.utils.PoligonMischiefComparator;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

@RunWith(MockitoJUnitRunner.class)
public class PoligonMethodImplTest {

    private static final Logger LOGGER = Logger.getLogger(PoligonMethodImplTest.class);

    private static final PoligonData POLIGON_DATA = new PoligonData();

    @InjectMocks
    private PoligonMethodImpl poligonMethod;

    @Before
    public void setUp() throws Exception {
        POLIGON_DATA
                .setId(1L)
                .setCalculationTypeName(CalculationTypeName.FIRST)
                .setReperList(Collections.singletonList(new PoligonReper().setName("E").setHeight(.0)))
                .addPoligon(
                        new Poligon().setId(1L).setName("1")
                                .addPoligonMove(new PoligonMove().setId(1L).setName("E-A").setDifference(-.0).setStationCount(11).setDistance(4.5))
                                .addPoligonMove(new PoligonMove().setId(2L).setName("A-B").setDifference(.0).setStationCount(6).setDistance(7.0))
                                .addPoligonMove(new PoligonMove().setId(3L).setName("B-E").setDifference(-.00011).setStationCount(10).setDistance(3.7))
                )
                .addPoligon(
                        new Poligon().setId(2L).setName("2")
                                .addPoligonMove(new PoligonMove().setId(5L).setName("E-B").setDifference(.0).setStationCount(13).setDistance(3.7))
                                .addPoligonMove(new PoligonMove().setId(6L).setName("B-C").setDifference(.0).setStationCount(10).setDistance(6.3))
                                .addPoligonMove(new PoligonMove().setId(7L).setName("C-E").setDifference(.00015).setStationCount(12).setDistance(5.0))
                )
                .addPoligon(
                        new Poligon().setId(3L).setName("3")
                                .addPoligonMove(new PoligonMove().setId(8L).setName("E-C").setDifference(.0).setStationCount(13).setDistance(5.0))
                                .addPoligonMove(new PoligonMove().setId(9L).setName("C-D").setDifference(.0).setStationCount(10).setDistance(7.0))
                                .addPoligonMove(new PoligonMove().setId(10L).setName("D-E").setDifference(-.00012).setStationCount(12).setDistance(4.2))
                )
                .addPoligon(
                        new Poligon().setId(4L).setName("4")
                                .addPoligonMove(new PoligonMove().setId(11L).setName("E-D").setDifference(.0).setStationCount(13).setDistance(4.2))
                                .addPoligonMove(new PoligonMove().setId(12L).setName("D-A").setDifference(.0).setStationCount(10).setDistance(7.4))
                                .addPoligonMove(new PoligonMove().setId(13L).setName("A-E").setDifference(-.00008).setStationCount(12).setDistance(4.5))
                );
    }

    @Test
    public void getWeight() {
        poligonMethod.getWeight(POLIGON_DATA);
        POLIGON_DATA.getPoligonList().sort(new PoligonMischiefComparator());
        LOGGER.info(POLIGON_DATA);
    }

    @Test
    public void mainCalculation() {
        poligonMethod.mainCalculation(poligonMethod.getWeight(POLIGON_DATA));
        LOGGER.info(POLIGON_DATA);
    }
}