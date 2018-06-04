<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/header.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/myResults.css"/>">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
          integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
            integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
            crossorigin="anonymous"></script>
    <title>Мої результати</title>
</head>
<body>
<div class="container-fluid p-0 m-0">
    <div class="main_header">
        <img src="/image/logo.png" class="logo" alt="">
        <div class="up_container">
            <div class="nav_up">
                <a href="/">Головна</a>
                <a href="/my-results">Мої результати</a>
                <a href="/about-us">Про нас</a>
                <a href="/contacts">Контакти</a>
                <a href="/sign-in">Вхід<img src="#" class="sign_in_img"></a>
            </div>
        </div>
        <div class="down_container">
            <nav class="navbar navbar-expand-lg navbar-light nav_down">
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <%--<li class="nav-item active">--%>
                        <%--<a class="nav-link" href="#"></a>--%>
                        <%--</li>--%>
                        <%--<li class="nav-item">--%>
                        <%--<a class="nav-link" href="#"></a>--%>
                        <%--</li>--%>
                        <%--<li class="nav-item">--%>
                        <%--<a class="nav-link" href="#">Analysis of resistance stability</a>--%>
                        <%--</li>--%>
                        <%--<li class="nav-item">--%>
                        <%--<a class="nav-link" href="#">Make graphics</a>--%>
                        <%--</li>--%>
                    </ul>
                </div>
            </nav>
        </div>
    </div>
    <div class="col-md-10 container_index">
        <h1>Ваші результати:</h1>
        <ul class="container_result">
            <li>
                <c:forEach var="calcs" items="${calcss}">
                    <div class="tab">
                        <p class="title">Назва класу обрахунків: ${calcs.calculationTypeName}</p>
                        <p class="title">Назва типу: 1</p>
                        <p class="title">Назва файлу: <a download
                                                         href="/calculation/download/file/${calcs.idData}">exls</a></p>
                        <div class="table-responsive">
                            <table class="table">
                                <tr>
                                    <td rowspan="2">ходи які сходяться у вузловій точці</td>
                                    <td rowspan="2">Назви Вузлових Реперів</td>
                                    <td rowspan="2">висоти вузлових реперів</td>
                                    <td rowspan="2">Суми перевищень ходу</td>
                                    <td rowspan="2">Кількість Штативів</td>
                                    <td colspan="2">Ваги</td>
                                    <td colspan="${calcs.pointDtos.size()}">
                                        Наближення
                                    </td>
                                        <%--зміна calspan--%>
                                    <td rowspan="2">V m</td>
                                    <td rowspan="2">P`v m</td>
                                    <td rowspan="2">P`v m2</td>
                                </tr>
                                <tr>
                                    <td>PJI</td>
                                    <td>P`JI</td>
                                    <c:forEach begin="1" var="i"
                                               end="${calcs.pointDtos.size()}">
                                        <td>${i}</td>
                                    </c:forEach>
                                </tr>
                                <tr>
                                    <c:forEach begin="1" var="i"
                                               end="${calcs.pointDtos.size()+10}">
                                        <td>${i}</td>
                                    </c:forEach>
                                </tr>
                                <c:forEach items="${calcs.pointDtos}" var="calc" varStatus="loop">
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
                    </div>
                    <br>
                    <hr>
                    <br>
                </c:forEach>

            </li>
        </ul>
    </div>
</div>
</body>
</html>
