<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/8/3
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<a href="listStudent">listStudent</a>
<a href="listMongo">listMongo</a>
<a href="listStudentByPage?currentPage=1">listStudentByPage</a>
</body>
</html>
