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
    <title>Hui</title>
</head>
<body>
<p class="title">Назва класу обрахунків: ${classSystem}</p>
<p class="title">Назва типу: ${type}</p>
<p class="title">Назва файлу: ${file}</p>
<table border="1">
    <tr>
        <td>Reper</td>
        <td>Height</td>
    </tr>
    <c:forEach var="huj" items="${pointDto}">
        <tr>
            <td>${huj.name}</td>
            <td>${huj.height}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
