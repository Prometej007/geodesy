<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <link rel="stylesheet" href="<c:url value="/css/level-the-system/main.css"/>">
</head>
<body>

<div class="tab">
    <form:form method="post" action="/calculation/result-1" enctype="multipart/form-data">
        <h1>FIRST</h1>
        <input type="text" name="classSystem" hidden value="FIRST">
        <select name="type">
            <option value="FIRST">FIRST</option>
            <option value="SECOND">SECOND</option>
            <option value="THIRD">THIRD</option>
            <option value="FOURTH">FOURTH</option>
        </select>
        <div class="input-file-container">
            add file
            <input type="file" name="file" class="input-file-container-input">
        </div>
        <button type="submit">send</button>
    </form:form>
</div>
<div class="tab">
    <h1>SECOND</h1>
    <form:form method="post" action="/calculation/result-1" enctype="multipart/form-data">
        <input type="text" name="classSystem" hidden value="SECOND">
        <select name="type">
            <option value="FIRST">FIRST</option>
            <option value="SECOND">SECOND</option>
            <option value="THIRD">THIRD</option>
            <option value="FOURTH">FOURTH</option>
        </select>
        <div class="input-file-container">
            add file
            <input type="file" name="file" class="input-file-container-input">
        </div>
        <button type="submit">send</button>
    </form:form>
</div>
<div class="tab">
    <h1>THIRD</h1>
    <form:form method="post" action="/calculation/result-1" enctype="multipart/form-data">
        <input type="text" name="classSystem" hidden value="THIRD">
        <select name="type">
            <option value="FIRST">FIRST</option>
            <option value="SECOND">SECOND</option>
            <option value="THIRD">THIRD</option>
            <option value="FOURTH">FOURTH</option>
        </select>
        <div class="input-file-container">
            add file
            <input type="file" name="file" class="input-file-container-input">
        </div>
        <button type="submit">send</button>
    </form:form>
</div>
<div class="tab">
    <h1>FOURTH</h1>
    <form:form method="post" action="/calculation/result-1" enctype="multipart/form-data">
        <input type="text" name="classSystem" hidden value="FOURTH">
        <select name="type">
            <option value="FIRST">FIRST</option>
            <option value="SECOND">SECOND</option>
            <option value="THIRD">THIRD</option>
            <option value="FOURTH">FOURTH</option>
        </select>
        <div class="input-file-container">
            add file
            <input type="file" name="file" class="input-file-container-input">
        </div>
        <button type="submit">send</button>
    </form:form>
</div>
</body>
</html>