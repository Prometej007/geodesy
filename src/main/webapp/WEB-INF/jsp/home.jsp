<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <link rel="stylesheet" href="<c:url value="/css/header.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/home.css"/>">
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
</head>
<body>
<div class="container-fluid">
    <div class="main_header">
        <img src="/image/logotype.png" class="logo" alt="">
        <div class="up_container">
            <div class="nav_up">
                <a href="#">About us</a>
                <a href="#">Contacts</a>
                <a href="#">Sign in <img src="#" class="sign_in_img"></a>
            </div>
        </div>
        <div class="down_container">
            <nav class="navbar navbar-expand-lg navbar-light nav_down">
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item active">
                            <a class="nav-link" href="#">My results</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Services</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Level the system</a>
                        </li>
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
                    Test 1
                </a>
            </li>
            <li class="nav-item tabs_home_item">
                <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab"
                   aria-controls="profile" aria-selected="false">
                    Test 2
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
                <form class="file_container">
                    <label style="display:none;">
                        <input style="display: none" id="file" type="file" placeholder="Виберіть файл">
                    </label>
                    <label for="file" class="file_form">Chose file</label>
                    <%--<label>--%>
                        <%--<select>--%>
                            <%--<option>test</option>--%>
                            <%--<option>test</option>--%>
                            <%--<option>test</option>--%>
                            <%--<option>test</option>--%>
                        <%--</select>--%>
                    <%--</label>--%>
                    <label>
                        <select>
                            <option>test</option>
                            <option>test</option>
                            <option>test</option>
                            <option>test</option>
                        </select>
                    </label>
                    <label>
                        <button>Обрахувати</button>
                    </label>
                </form>
                <div class="file_dop_container">
                    <div class="file_dop_container_item">
                        <img src="/image/file.png" alt="">
                        <p>Preview</p>
                    </div>
                    <div class="file_dop_container_item">
                        <img src="/image/download.png" alt="">
                        <p>Preview</p>
                    </div>
                </div>
            </div>
            <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                <form class="file_container">
                    <label style="display:none;">
                        <input style="display: none" id="file1" type="file" placeholder="Виберіть файл">
                    </label>
                    <label for="file1" class="file_form">Chose file</label>
                    <%--<label>--%>
                    <%--<select>--%>
                    <%--<option>test</option>--%>
                    <%--<option>test</option>--%>
                    <%--<option>test</option>--%>
                    <%--<option>test</option>--%>
                    <%--</select>--%>
                    <%--</label>--%>
                    <label>
                        <select>
                            <option>test</option>
                            <option>test</option>
                            <option>test</option>
                            <option>test</option>
                        </select>
                    </label>
                    <label>
                        <button>Обрахувати</button>
                    </label>
                </form>
                <div class="file_dop_container">
                    <div class="file_dop_container_item">
                        <img src="/image/file.png" alt="">
                        <p>Preview</p>
                    </div>
                    <div class="file_dop_container_item">
                        <img src="/image/download.png" alt="">
                        <p>Preview</p>
                    </div>
                </div>
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
        <div class="file_step_container">
            <div class="file_step_container_item">
                <h2>Step 1</h2>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Adipisci assumenda consequatur
                    dolores esse eum fugit in iste quaerat ratione voluptatem? Ab, qui, voluptate. Accusamus
                    consequatur explicabo quaerat sit soluta voluptas.</p>
            </div>
            <hr class="step_hr">
            <div class="file_step_container_item">
                <h2>Step 2</h2>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad aliquam assumenda beatae cumque
                    debitis dicta distinctio eum illo, maxime nesciunt obcaecati rem sunt suscipit? Commodi enim
                    est placeat quam voluptatum?</p>
            </div>
            <hr class="step_hr">
            <div class="file_step_container_item">
                <h2>Step 3</h2>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Consequatur in minus mollitia neque
                    officia provident tenetur! Animi, aperiam asperiores dolore ducimus eos nostrum odio odit
                    quibusdam quos reprehenderit sint veniam?</p>
            </div>
        </div>
    </div>
</div>

</body>

</html>