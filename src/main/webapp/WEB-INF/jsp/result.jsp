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
            <td rowspan="2">№ новмер вузлової точки репера</td>
            <td rowspan="2">ходи які сходяться у вузловій точці</td>
            <td rowspan="2">Назви Вузлових Реперів</td>
            <td rowspan="2">висоти вузлових реперів</td>
            <td rowspan="2">Суми перевищень ходу</td>
            <td rowspan="2">Кількість Штативів</td>
            <td colspan="2">Ваги</td>
            <td colspan="5">Наближення</td>
            <%--зміна calspan--%>
            <td rowspan="2">V m,m</td>
            <td rowspan="2">P`v m,m</td>
            <td rowspan="2">P`v m,m2</td>
        </tr>
        <tr>
            <td>PJI</td>
            <td>P`JI</td>
            <td>1</td>
            <td>2</td>
            <td>3</td>
            <td>4</td>
            <td>5</td>
        </tr>
        <tr>
            <td>1</td>
            <td>2</td>
            <td>3</td>
            <td>4</td>
            <td>5</td>
            <td>6</td>
            <td>7</td>
            <td>8</td>
            <td>9</td>
            <td>10</td>
            <td>11</td>
            <td>12</td>
            <td>13</td>
            <td>14</td>
            <td>15</td>
            <td>15</td>
        </tr>
        <c:forEach items="${calcs}" var="calc" varStatus="loop">
            <tr>
                <c:if test="${loop.index==0}">
                    <td rowspan="${calc.pointOne.size()}">
                            ${calc.number}
                    </td>
                </c:if>
                <td>
                        ${calc.nameMuve}
                </td>
                <td>
                        ${calc.nameReper}
                </td>
                <td>
                        ${calc.height}
                </td>
                <td>
                        ${calc.sum}
                </td>
                <td>
                        ${calc.station}
                </td>
                <td>
                        ${calc.weight}
                </td>
                <td>
                        ${calc._weight}
                </td>

                <c:forEach items="${calc.pointOne}" var="row">
                    <td>
                            ${calc}
                    </td>
                </c:forEach>
                <td>
                        ${calc.correction}
                </td>
                <td>
                        ${calc.weightCorrection}
                </td>
                <td>
                        ${calc.weightCorrectionCorrection}
                </td>
            </tr>
            <c:forEach items="${calc.pointOne}" var="row">

                <tr>

                </tr>
            </c:forEach>

        </c:forEach>

    </table>
</body>
</html>