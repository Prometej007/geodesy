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
<table>
    <c:forEach var="point" items="${pointDto}">
        <tr>
            <td>${point.height}</td>
            <td>${point.name}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
