<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <link rel="stylesheet" href="<c:url value="/css/header.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/home.css"/>">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
          integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
            integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
            crossorigin="anonymous"></script>
    <title>Геодезія</title>
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
                <a href="/sign-in">Вхід</a>
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
    <div class="background_main"></div>
    <div class="index_container">
        <ul class="nav nav-tabs" id="myTab" role="tablist">
            <li class="nav-item tabs_home_item">
                <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home"
                   aria-selected="true">
                    Метод послідовних наближень
                </a>
            </li>
            <li class="nav-item tabs_home_item">
                <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab"
                   aria-controls="profile" aria-selected="false">
                    Метод полігонів
                </a>
            </li>
            <%--<li class="nav-item tabs_home_item">--%>
            <%--<a class="nav-link" id="contact-tab" data-toggle="tab" href="#contact" role="tab"--%>
            <%--aria-controls="contact" aria-selected="false">--%>
            <%--Test 3--%>
            <%--</a>--%>
            <%--</li>--%>
        </ul>
        <div class="tab-content" id="myTabContent">
            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                <form:form method="post" action="/calculation/result-1" class="file_container main-mb"
                           enctype="multipart/form-data">
                    <img class="icon_xls" src="/image/excel-xls-icon.png" alt="">
                    <input type="text" name="classSystem" hidden value="FIRST">
                    <label style="display:none;">
                        <input style="display: none" onchange="changeTitleFile(1)" id="file1" name="file" type="file" accept="application/vnd.ms-excel" placeholder="Виберіть файл">
                    </label>
                    <label for="file1" class="file_form" id="label1">
                        Виберіть файл *.xls</label>
                    <%--<label>--%>
                    <%--<select>--%>
                    <%--<option>test</option>--%>
                    <%--<option>test</option>--%>
                    <%--<option>test</option>--%>
                    <%--<option>test</option>--%>
                    <%--</select>--%>
                    <%--</label>--%>
                    <label>
                        <select name="type">
                            <option value="FIRST">FIRST</option>
                            <option value="SECOND">SECOND</option>
                            <option value="THIRD">THIRD</option>
                            <option value="FOURTH">FOURTH</option>
                        </select>
                    </label>
                    <label>
                        <button type="submit">Обрахувати</button>
                    </label>
                </form:form>

                <div style="background: #3e959dab; margin: 0 25%">
                    <form class="file_container main-w-100" id="first-metod-main-form">
                        <div style="display: flex; align-items: center; justify-content: flex-start" class="main-mb">
                            <label style="display:block; width:25%;" class="main-mb">
                                Назва:
                                <input required name="name" type="text">
                            </label>
                             <label style="display:block; width:25%;">
                                Тип:
                                <select required name="type" style="margin: auto">
                                    <option value="FIRST">FIRST</option>
                                    <option value="SECOND">SECOND</option>
                                    <option value="THIRD">THIRD</option>
                                    <option value="FOURTH">FOURTH</option>
                                </select>
                             </label>
                        </div>
                    </form>
                    
                    <form onsubmit="addNewReper()" class="file_container main-w-100" id="first-metod-reper-form">
                        <h5 style="color: white; margin: 0 1vw; width: 100%">Репери:</h2>
                        <div style="display: flex; align-items: flex-end; justify-content: flex-start; margin-bottom: 10px; width: 100%;">
                            <label style="display:block; width:25%;">
                                Назва:
                                <input required name="name" type="text">
                            </label>
                            <label style="display:block; width:25%;">
                                Висота:
                                <input required name="height" type="number" step="0.001">
                            </label>
                            <label>
                                <button type="submit">+</button>
                            </label>
                        </div>
                        <div id="first-method-repers" style="width:100%; margin: 0 1vw"></div>
                    </form>
                    
                    <form onsubmit="addNewStep()" class="file_container main-w-100" id="first-metod-step-form">
                        <h5 style="color: white; margin: 0 1vw; width: 100%">Кроки:</h2>
                        <div style="display: flex; align-items: flex-end; justify-content: flex-start; margin-bottom: 10px">
                            <label style="display:block; width:25%;">
                                Назва:
                                <input required name="name">
                            </label>
                            <label style="display:block; width:25%;">
                                Різниця:
                                <input required name="difference" type="number" step="0.001">
                            </label>
                            <label style="display:block; width:25%;">
                                Кількість станцій:
                                <input required name="stationCount" type="number" step="0.001">
                            </label>
                            <label style="display:block; width:25%;">
                                Дистанція:
                                <input required name="distance" type="number" step="0.001">
                            </label>
                            <label>
                                <button type="submit">+</button>
                            </label>
                        </div>
                        <div id="first-method-steps" style="width:100%; margin: 0 1vw"></div>
                    </form>
                    
                    <div class="file_container main-w-100" style="justify-content: flex-end">
                        <label>
                            <button onclick="submitFirstMethod()" type="button">Обрахувати</button>
                        </label>   
                    </div>
                </div>

                <%--<div class="file_dop_container">--%>
                    <%--<div class="file_dop_container_item">--%>
                        <%--<img src="/image/file.png" alt="">--%>
                        <%--<p>Переглянути</p>--%>
                    <%--</div>--%>
                    <%--<div class="file_dop_container_item">--%>
                        <%--<img src="/image/download.png" alt="">--%>
                        <%--<p>Завантажити</p>--%>
                    <%--</div>--%>
                <%--</div>--%>
            </div>
            <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                <form:form method="post" action="/calculation/result-2" class="file_container"
                           enctype="multipart/form-data">
                    <img class="icon_xls" src="/image/excel-xls-icon.png" alt="">
                    <input type="text" name="classSystem" hidden value="FIRST">
                    <label style="display:none;">
                        <input style="display: none" onchange="changeTitleFile(1)" id="file2" name="file" type="file" accept="application/vnd.ms-excel" placeholder="Виберіть файл">
                    </label>
                    <label for="file2" class="file_form" id="label2">
                        Виберіть файл *.xls</label>
                    <%--<label>--%>
                    <%--<select>--%>
                    <%--<option>test</option>--%>
                    <%--<option>test</option>--%>
                    <%--<option>test</option>--%>
                    <%--<option>test</option>--%>
                    <%--</select>--%>
                    <%--</label>--%>
                    <label>
                        <select name="type">
                            <option value="FIRST">FIRST</option>
                            <option value="SECOND">SECOND</option>
                            <option value="THIRD">THIRD</option>
                            <option value="FOURTH">FOURTH</option>
                        </select>
                    </label>
                    <label>
                        <button type="submit">Обрахувати</button>
                    </label>
                </form:form>
                <%--<div class="file_dop_container">--%>
                    <%--<div class="file_dop_container_item">--%>
                        <%--<img src="/image/file.png" alt="">--%>
                        <%--<p>Переглянути</p>--%>
                    <%--</div>--%>
                    <%--<div class="file_dop_container_item">--%>
                        <%--<img src="/image/download.png" alt="">--%>
                        <%--<p>Завантажити</p>--%>
                    <%--</div>--%>
                <%--</div>--%>
            </div>
            <%--<div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">--%>
            <%--<form class="file_container">--%>
            <%--<label style="display:none;">--%>
            <%--<input style="display: none" id="file1" type="file" placeholder="Виберіть файл">--%>
            <%--</label>--%>
            <%--<label for="file1" class="file_form"></label>--%>
            <%--<label>--%>
            <%--<button>Обрахувати</button>--%>
            <%--</label>--%>
            <%--</form>--%>
            <%--<div class="file_dop_container">--%>
            <%--<div class="file_dop_container_item">--%>
            <%--<img src="/image/file.png" alt="">--%>
            <%--<p>Preview</p>--%>
            <%--</div>--%>
            <%--<div class="file_dop_container_item">--%>
            <%--<img src="/image/download.png" alt="">--%>
            <%--<p>Preview</p>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--</div>--%>
        </div>
<%--        <div class="file_step_container">--%>
<%--            <div class="file_step_container_item">--%>
<%--                <h2>Step 1</h2>--%>
<%--                <p>--%>
<%--                    Виберіть файл з розширенням .xls--%>
<%--                    <a href="#" data-toggle="modal" data-target="#exampleModal">Приклад завантажуваного файлу для--%>
<%--                        "Методу послідовних наближень"</a>--%>
<%--                    <a href="#" data-toggle="modal" data-target="#exampleModal1">Приклад завантажуваного файлу для--%>
<%--                        "Методу полігонів"</a>--%>
<%--                </p>--%>
<%--            </div>--%>
<%--            <hr class="step_hr">--%>
<%--            <div class="file_step_container_item">--%>
<%--                <h2>Step 2</h2>--%>
<%--                <p>--%>
<%--                    Виберіть клас з яким проводилось вимірювання.--%>
<%--                    та натисніть кнопку обрахувати--%>
<%--                </p>--%>
<%--            </div>--%>
<%--            <hr class="step_hr">--%>
<%--            <div class="file_step_container_item">--%>
<%--                <h2>Step 3</h2>--%>
<%--                <p>--%>
<%--                    Натисніть кнопку обрахувати--%>

<%--                </p>--%>
<%--            </div>--%>
<%--        </div>--%>
        <div class="modal" id="exampleModal" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5>Приклад завантажуваного файлу для "Методу послідовних наближень"</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <img width="100%" src="/image/example1.png" alt="">
                    </div>
                </div>
            </div>
        </div>
        <div class="modal" id="exampleModal1" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5>Приклад завантажуваного файлу для "Методу полігонів"</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <img width="100%" src="/image/example1.png" alt="">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script src="<c:url value="/js/home.js"/>"></script>
</body>

</html>
