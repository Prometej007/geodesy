<%--
  Created by IntelliJ IDEA.
  User: Billy
  Date: 07.06.2018
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/security/tags"
           prefix="sec" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>Результат</title>
</head>
<body>
<style>
    * {
        font-family: "Century Gothic", sans-serif;
        margin: 0;
        padding: 0;
    }

    .main_header {
        width: 100%;
        height: auto;
        position: relative;
    }

    .logo {
        position: absolute;
        top: 12px;
        left: 22px;
        display: block;
        width: auto;
        height: 46px;
        z-index: 1;
    }

    .up_container {
        display: flex;
        justify-content: flex-end;
        align-items: center;
        height: 50px;
        padding: 16px;
    }

    .nav_up a {
        text-decoration: none;
        text-transform: uppercase;
        font-size: 20px;
        color: #000;
        margin-right: 2vw;
    }

    .down_container {
        display: flex;
        justify-content: flex-end;
        align-items: center;
        height: 20px;
        background: #3d969e;
    }

    .nav_down {
        width: auto;
    }

    .nav-link {
        font-size: 20px;
        color: #fff !important;
        text-transform: uppercase;
    }

    td {
        border: #000 1px solid;
        padding: 10px;
        font-size: 17px;
        text-align: center;
        margin: 0;
    }

    body {
        background: #3d969e;
    }

    table {
        margin: auto;
        width: 80vw;
        height: 80vh;
        margin: 10vh 10vw;
        margin-top: 5vh;
        background: white;
        border-collapse: collapse;
    }

    .head {
        font-size: 25px !important;
        text-align: center;
        text-decoration: black underline;
    }

    .main_head {
        font-size: 35px !important;
        text-align: center;
    }

    .main_header {
        background: white;
    }
</style>
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
    <table>
        <tr>
            <td class="main_head" colspan="2">РЕЗУЛЬТАТ</td>
        </tr>
        <tr>
            <td class="head">Назва репера</td>
            <td class="head">Висота репера</td>
        </tr>
        <c:forEach var="point" items="${pointDto}">
            <tr>
                <td>${point.name}</td>
                <td>${point.height}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
