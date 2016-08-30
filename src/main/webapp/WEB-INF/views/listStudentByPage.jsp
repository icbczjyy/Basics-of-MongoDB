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
    <c:choose>
        <c:when test="${null!=error}">
            ${error}
        </c:when>
        <c:when test="${students.size()==0}">
            no students
        </c:when>
        <c:otherwise>
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
            <c:if test="${currentPage!=1}">
                <a href="listStudentByPage?currentPage=1">首页</a>
                <a href="listStudentByPage?currentPage=${currentPage-1}">上一页</a>
            </c:if>
            ${currentPage}/${totalPage}页
            <c:if test="${currentPage!=totalPage}">
                <a href="listStudentByPage?currentPage=${currentPage+1}">下一页</a>
                <a href="listStudentByPage?currentPage=${totalPage}">尾页</a>
            </c:if>
        </c:otherwise>
    </c:choose>
</table>
</body>
</html>
