<%--
  Created by IntelliJ IDEA.
  User: YoungTree
  Date: 2017/10/15
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>消息</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/plugin/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/css/AdminLTE.min.css">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <style>
        .login-box {
            margin-top: 150px;
        }

        .note {
            text-align: center;
        }

        .note p {
            color: #555;
            font-size: 18px;
            letter-spacing: 1px;
        }

        .note .back {
            margin: 0 auto;
            width: 30%;
        }

    </style>

</head>

<body class="hold-transition login-page">

<div class="login-box">
    <div class="login-box-body">

        <div class="row">
            <div class="col-xs-12 note">
                <p>${msg}</p>
                <a href="${pageContext.request.contextPath}${url}" class="btn btn-primary btn-block btn-flat back">返回</a>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/web/plugin/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/web/plugin/bootstrap/dist/js/bootstrap.min.js"></script>
</body>

</html>
