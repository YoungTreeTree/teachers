<%--
  Created by IntelliJ IDEA.
  User: YoungTree
  Date: 2017/10/14
  Time: 2:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
ok
<c:forEach items="${namelist}" var="name"    varStatus="id">
    ${id.index} ${name}<br>
</c:forEach>
</body>
</html>
