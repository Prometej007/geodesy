package com.geodesy.web.geodesy.service.Impl;

import com.geodesy.web.geodesy.model.base.Reper;
import com.geodesy.web.geodesy.model.poligon.Poligon;
import com.geodesy.web.geodesy.model.poligon.PoligonData;
import com.geodesy.web.geodesy.model.poligon.PoligonMove;
import com.geodesy.web.geodesy.model.poligon.PoligonReper;
import com.geodesy.web.geodesy.model.utils.DoubleFormatter;
import com.geodesy.web.geodesy.model.utils.PoligonMischiefComparator;
import com.geodesy.web.geodesy.model.utils.PoligonNameComparator;
import com.geodesy.web.geodesy.model.utils.constants.CalculationType;
import com.geodesy.web.geodesy.service.PoligonMethod;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PoligonMethodImpl implements PoligonMethod {
    @Override
    public PoligonData calculate(PoligonData poligonData) {
//        System.err.println(poligonData);
        getWeight(poligonData);
        mainCalculation(poligonData);
        getHeights(poligonData);
        return poligonData;
    }

    @Override
    public PoligonData getWeight(PoligonData poligonData) {
        if(poligonData.getPoligonList()==null)
            poligonData.setPoligonList(new ArrayList<>());
        getMischief(getPerimeter(poligonData));
        if (!checkMischiefValidity(poligonData))
            throw new RuntimeException();
        for (Poligon poligon :
                poligonData.getPoligonList()) {
            for (PoligonMove poligonMove :
                    poligon.getPoligonMoves()) {
                poligonMove.setWeight(poligonMove.getDistance() / poligon.getPerimeter());
            }
        }
        return getCorrection(poligonData);
    }

    @Override
    public PoligonData mainCalculation(PoligonData poligonData) {
        Integer count = 0;
        poligonData.getPoligonList().sort(new PoligonMischiefComparator());
        Poligon poligon1 = poligonData.getPoligonList().get(0);
        poligonData.getPoligonList().sort(new PoligonNameComparator());
        Integer startIndex = poligonData.getPoligonList().indexOf(poligon1);
//        System.err.println("---------------=============+++++++++++++++++++++++++++++=================------------------------");
        while (!checkMischief(poligonData) && count < 10) {
            for (int j = 0, i = startIndex; i < poligonData.getPoligonList().size(); i++, j++) {
//                System.err.println("---------------==============================------------------------");
                setCorrectionOne(poligonData.getPoligonList().get(i));
                for (PoligonMove poligonMove :
                        poligonData.getPoligonList().get(i).getPoligonMoves()) {
//                    System.err.println(String.format("E(%s) - C(%s) = %s", DoubleFormatter.format(poligonMove.setDifference()), DoubleFormatter.format(poligonMove.getCorrection()), DoubleFormatter.format(poligonMove.setDifference() - poligonMove.getCorrection())));
                    poligonMove.setDifference(poligonMove.getDifference() - poligonMove.getCorrection());
                }
                setNewMischief(poligonData.getPoligonList().get(i), poligonData);
//                System.err.println(String.format("%d (%d)", i, startIndex));
                if ((j > 0) && (i == (startIndex - 1))) {
//                    System.err.println(String.format("break at %d (%d)", i, startIndex));
                    i = poligonData.getPoligonList().size();
                }
                if (i == poligonData.getPoligonList().size() - 1 && startIndex > 0) {
//                    System.err.println(String.format("end but continue at %d (%d)", i, poligonData.getPoligonList().size() - 1));
                    i = -1;
                }
            }
//            System.err.println("---------------===============7777777777777777777777777777777===============------------------------");
//            System.err.println(count++);
//            System.err.println("---------------===============7777777777777777777777777777777===============------------------------");
        }
        return poligonData;
    }

    @Override
    public PoligonData getHeights(PoligonData poligonData) {
        for (Poligon poligon : poligonData.getPoligonList()) {
//            System.err.println("------------------POLIGON---------------------");
//            System.err.println(poligon.getName());
            for (PoligonMove move : poligon.getPoligonMoves()) {
//                System.err.println("---------------MOVE----------------");
//                System.err.println(move.getName());
                Reper reper = poligonData.getReperList().stream().filter(poligonReper -> move.getName().contains(poligonReper.getName() + "-")).findFirst().orElse(new PoligonReper());
                if (reper.getName() == null)
                    continue;
//                System.err.println("-----------------REPER--------------------");
//                System.err.println(reper.getName());
                createNewReper(poligonData, move, reper);
            }
        }
        return poligonData;
    }

    private void createNewReper(PoligonData poligonData, PoligonMove move, Reper reper) {
        if (poligonData.getReperList().stream().noneMatch(poligonReper -> poligonReper.getName().equals(move.getName().split("-")[1]))) {
//            System.err.println("---------------NONE-MATCH--------------");
            PoligonReper poligonReper = new PoligonReper().setHeight(reper.getHeight() + move.getDifference()).setName(move.getName().split("-")[1]);
            List<PoligonReper> temp = new ArrayList<>(poligonData.getReperList());
            temp.add(poligonReper);
            poligonData.setReperList(temp);
        } else {
//            System.err.println("---------------FOUND-MATCH------------------");
        }
    }

    private void setNewMischief(Poligon current, PoligonData poligonData) {
        for (Poligon poligon : poligonData.getPoligonList()) {
            PoligonMove similar = null;
            if (!Objects.equals(current.getId(), poligon.getId())) {
                for (PoligonMove poligonMove : poligon.getPoligonMoves()) {
                    similar = getSimilar(current, poligonMove);
                    if (similar != null) {
//                        System.err.println("found similar [" + poligon.getId() + "] C[" + DoubleFormatter.format(similar.getCorrection()) + "] new M(" + DoubleFormatter.format(poligon.getMischief() + similar.getCorrection()) + ")");
                        poligon.setMischief(poligon.getMischief() + similar.getCorrection());
                        break;
                    }
                }
            }
            if (similar == null) {
//                System.err.println("no similar [" + poligon.getId() + "][curr : " + current.getId() + "]");
                getMischiefOne(poligon);
            }
        }
    }

    private PoligonData getCorrection(PoligonData poligonData) {
        for (Poligon poligon :
                poligonData.getPoligonList()) {
            setCorrectionOne(poligon);
        }
        return poligonData;
    }

    private void setCorrectionOne(Poligon poligon) {
//        System.err.println(poligon.getName());
//        System.err.println("---------------------------------------");
        for (PoligonMove poligonMove :
                poligon.getPoligonMoves()) {
//            System.err.println(String.format("for %s W(%s) * M(%s) = %s", poligonMove.getName(), DoubleFormatter.format(poligonMove.getWeight()), DoubleFormatter.format(poligon.getMischief()), DoubleFormatter.format(poligonMove.getWeight() * poligon.getMischief())));
            poligonMove.setCorrection(poligonMove.getWeight() * poligon.getMischief());
        }
//        System.err.println("---------------------------------------");
    }

    private PoligonMove getSimilar(Poligon first, PoligonMove poligonMove) {
        for (PoligonMove move : first.getPoligonMoves()) {
            if (poligonMove.equals(move)) {
//                System.err.println("return " + move.getName());
                return move;
            }
        }
        return null;
    }

    private Boolean checkMischief(PoligonData poligonData) {
        for (Poligon poligon : poligonData.getPoligonList()) {
            if (Math.abs(poligon.getMischief()) > CalculationType.TYPES_P.get(poligonData.getCalculationTypeName())) {
//                System.err.println(String.format("%s > %s", DoubleFormatter.format(Math.abs(poligon.getMischief())), DoubleFormatter.format(CalculationType.TYPES_P.get(poligonData.getCalculationTypeName()))));
                return false;
            }
        }
        return true;
//        return false;
    }

    private Boolean checkMischiefValidity(PoligonData poligonData) {
        System.err.println(poligonData);
        for (Poligon poligon : poligonData.getPoligonList()) {
            System.err.println(DoubleFormatter.format(poligon.getMischief()));
            System.err.println((CalculationType.TYPES_P.get(poligonData.getCalculationTypeName()) * 100) * Math.sqrt(poligon.getPerimeter()));
            if (Math.abs(poligon.getMischief()) > (CalculationType.TYPES_P.get(poligonData.getCalculationTypeName()) * 100) * Math.sqrt(poligon.getPerimeter())) {
                System.err.println(DoubleFormatter.format(poligon.getMischief()) + ">" + DoubleFormatter.format((CalculationType.TYPES_P.get(poligonData.getCalculationTypeName()) * 100) * Math.sqrt(poligon.getPerimeter())));
                return false;
            }
        }
        return true;
    }

    private PoligonData getMischief(PoligonData poligonData) {
        for (Poligon poligon : poligonData.getPoligonList()) {
            getMischiefOne(poligon);
        }
        return poligonData;
    }

    private void getMischiefOne(Poligon poligon) {
        Double mis = .0;
//        System.err.println("---------------------------------------");
        for (PoligonMove poligonMove :
                poligon.getPoligonMoves()) {
//            System.err.println(String.format("mis += %s (mis = %s)", DoubleFormatter.format(poligonMove.setDifference()), DoubleFormatter.format(mis)));
            mis += poligonMove.getDifference();
        }
        poligon.setMischief(mis);
//        System.err.println(String.format("id : [%d] mis : [%s]", poligon.getId(), DoubleFormatter.format(mis)));
//        System.err.println("---------------------------------------");
    }

    private PoligonData getPerimeter(PoligonData poligonData) {
        for (Poligon poligon : poligonData.getPoligonList()) {
            Double per = .0;
            for (PoligonMove poligonMove :
                    poligon.getPoligonMoves()) {
                per += poligonMove.getDistance();
            }
            poligon.setPerimeter(per);
        }
        return poligonData;
    }
}
