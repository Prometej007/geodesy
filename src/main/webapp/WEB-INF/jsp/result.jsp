<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <link rel="stylesheet" href="/css/">
</head>
<body>
<style>
    table {
        width: 100vw;
    }

    td {
        border: 1px solid black;
    }
</style>
<div class="tab">
    <p>name classSystem ${classSystem}</p>
    <p>name type ${type}</p>
    <p>name file ${file}</p>

    <table>
        <tr>
            <td rowspan="2">ходи які сходяться у вузловій точці</td>
            <td rowspan="2">Назви Вузлових Реперів</td>
            <td rowspan="2">висоти вузлових реперів</td>
            <td rowspan="2">Суми перевищень ходу</td>
            <td rowspan="2">Кількість Штативів</td>
            <td colspan="2">Ваги</td>
            <td colspan="${length}">Наближення</td>
            <%--зміна calspan--%>
            <td rowspan="2">V m</td>
            <td rowspan="2">P`v m</td>
            <td rowspan="2">P`v m2</td>
        </tr>
        <tr>
            <td>PJI</td>
            <td>P`JI</td>
            <c:forEach begin="1" var="i" end="${length}">
                <td>${i}</td>
            </c:forEach>
        </tr>
        <tr>
            <c:forEach begin="1" var="i" end="${length+10}">
                <td>${i}</td>
            </c:forEach>
        </tr>
        <c:forEach items="${calcs}" var="calc" varStatus="loop">
            <tr>
                <td colspan="1000">
                        ${calc.number}
                </td>
            </tr>
            <c:forEach items="${calc.pointOne}" var="pointOne" varStatus="loop">
                <tr>
                    <td>
                            ${pointOne.nameMuve}
                    </td>
                    <td>
                            ${pointOne.nameReper}
                    </td>
                    <td>
                            ${pointOne.height}
                    </td>
                    <td>
                            ${pointOne.sum}
                    </td>
                    <td>
                            ${pointOne.station}
                    </td>
                    <td>
                            ${pointOne.weight}
                    </td>
                    <td>
                            ${pointOne._weight}
                    </td>

                    <c:forEach items="${pointOne.approximationDto}" var="col">
                        <td>
                                ${col.value}
                        </td>
                    </c:forEach>
                    <td>
                            ${pointOne.correction}
                    </td>
                    <td>
                            ${pointOne.weightCorrection}
                    </td>
                    <td>
                            ${pointOne.weightCorrectionCorrection}
                    </td>
                </tr>
            </c:forEach>


            <tr>
                <td></td>
                <td></td>

                <td>Eh=</td>
                <c:forEach items="${calc.checkParams}" var="row" varStatus="loops">
                    <c:if test="${loops.index==1}">
                        <td>

                        </td>
                    </c:if>
                    <td>${row} </td>
                </c:forEach>
            </tr>

        </c:forEach>

    </table>
</div>

</body>
</html>