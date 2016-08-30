<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/8/3
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title></title>
    <base href="<%=basePath%>"/>
</head>
<body>
<table border="1">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>age</th>
    </tr>
    <c:forEach items="${students}" var="student">
        <tr>
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td>${student.age}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
