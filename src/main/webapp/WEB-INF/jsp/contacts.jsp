<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <link rel="stylesheet" href="<c:url value="/css/header.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/contacts.css"/>">
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
    <title>Контакти</title>
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
    <div class="background_main">
        <h1>Контакти</h1>
    </div>
    <div class="index_container">
        <iframe
                src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d643.228252840302!2d24.02730197089522!3d49.844091994532654!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x473add72f9bc6a89%3A0xf9783fecb4fecf1f!2sLviv+National+Academic+Opera+and+Ballet+Theatre+named+after+Solomiya+Krushelnytska!5e0!3m2!1sen!2sua!4v1527675751148"
                width="100%" frameborder="0" style="border:0" allowfullscreen></iframe>
        <div class="contact">
            <div>
                <h2>Geodesy</h2>
                <table>
                    <tr>
                        <td class="first_td">Адреса:</td>
                        <td>вул. Залізнична, 72, м. Львів 79015, Україна</td>
                    </tr>
                    <tr>
                        <td class="first_td">Тел:</td>
                        <td>(032) 000-00-00<br>(032) 000-00-00</td>
                    </tr>
                    <tr>
                        <td class="first_td">Факс:</td>
                        <td>(032) 000-00-00</td>
                    </tr>
                    <!--<tr>-->
                    <!--<td>Facebook: </td>-->
                    <!--<td></td>-->
                    <!--</tr>-->
                    <!--<tr>-->
                    <!--<td>Skype: </td>-->
                    <!--<td></td>-->
                    <!--</tr>-->
                </table>
                <button class="callBack">Зворотній зв'язок</button>
            </div>
        </div>
    </div>
</div>

</body>

</html>