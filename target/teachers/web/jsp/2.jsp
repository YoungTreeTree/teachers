<%--
  Created by IntelliJ IDEA.
  User: YoungTree
  Date: 2017/10/9
  Time: 5:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>

    <form action="/young/login" method="post" enctype="multipart/form-data">
        login_num:<input type="text" id="login_num" name="login_num"><br>
        pw:<input type="text" id="pw" name="pw"><br>
        file:<input type="file" id="file" name="file"><br>
        <input type="submit" value="submit"><br>
    </form>
</head>
<body>

</body>
</html>
