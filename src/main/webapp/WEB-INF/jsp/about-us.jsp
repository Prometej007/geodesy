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
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
            integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
            crossorigin="anonymous"></script>
    <title>Title</title>
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
                <a href="#">Вхід<img src="#" class="sign_in_img"></a>
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
        <h1>Про нас</h1>
    </div>
    <div class="index_container">
        <p class="description">
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dignissimos dolor dolores, enim est ex ipsum nemo
            nostrum obcaecati veritatis! Atque ipsam itaque labore minus neque nulla quasi recusandae tenetur ut!
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dignissimos dolor dolores, enim est ex ipsum nemo
            nostrum obcaecati veritatis! Atque ipsam itaque labore minus neque nulla quasi recusandae tenetur ut!
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dignissimos dolor dolores, enim est ex ipsum nemo
            nostrum obcaecati veritatis! Atque ipsam itaque labore minus neque nulla quasi recusandae tenetur ut!
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dignissimos dolor dolores, enim est ex ipsum nemo
            nostrum obcaecati veritatis! Atque ipsam itaque labore minus neque nulla quasi recusandae tenetur ut!
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dignissimos dolor dolores, enim est ex ipsum nemo
            nostrum obcaecati veritatis! Atque ipsam itaque labore minus neque nulla quasi recusandae tenetur ut!
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dignissimos dolor dolores, enim est ex ipsum nemo
            nostrum obcaecati veritatis! Atque ipsam itaque labore minus neque nulla quasi recusandae tenetur ut!
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dignissimos dolor dolores, enim est ex ipsum nemo
            nostrum obcaecati veritatis! Atque ipsam itaque labore minus neque nulla quasi recusandae tenetur ut!
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dignissimos dolor dolores, enim est ex ipsum nemo
            nostrum obcaecati veritatis! Atque ipsam itaque labore minus neque nulla quasi recusandae tenetur ut!
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dignissimos dolor dolores, enim est ex ipsum nemo
            nostrum obcaecati veritatis! Atque ipsam itaque labore minus neque nulla quasi recusandae tenetur ut!
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dignissimos dolor dolores, enim est ex ipsum nemo
            nostrum obcaecati veritatis! Atque ipsam itaque labore minus neque nulla quasi recusandae tenetur ut!
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dignissimos dolor dolores, enim est ex ipsum nemo
            nostrum obcaecati veritatis! Atque ipsam itaque labore minus neque nulla quasi recusandae tenetur ut!
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dignissimos dolor dolores, enim est ex ipsum nemo
            nostrum obcaecati veritatis! Atque ipsam itaque labore minus neque nulla quasi recusandae tenetur ut!
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dignissimos dolor dolores, enim est ex ipsum nemo
            nostrum obcaecati veritatis! Atque ipsam itaque labore minus neque nulla quasi recusandae tenetur ut!
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dignissimos dolor dolores, enim est ex ipsum nemo
            nostrum obcaecati veritatis! Atque ipsam itaque labore minus neque nulla quasi recusandae tenetur ut!
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dignissimos dolor dolores, enim est ex ipsum nemo
            nostrum obcaecati veritatis! Atque ipsam itaque labore minus neque nulla quasi recusandae tenetur ut!
        </p>
    </div>
</div>

</body>

</html>