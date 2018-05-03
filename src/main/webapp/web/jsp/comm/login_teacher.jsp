<%--
  Created by IntelliJ IDEA.
  User: YoungTree
  Date: 2017/10/15
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>《国家公派出国教师荣誉证书》申请管理系统</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/plugin/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/css/AdminLTE.min.css">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->


    <style>
        .single-row{
            white-space: nowrap;
        }
    </style>
</head>

<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
        <p class="single-row" style="margin-left: -60px">《国家公派出国教师荣誉证书》</p>
        <p class="single-row" style="margin-left: -35px">申请管理系统</p>
    </div>
    <div class="login-box-body">

        <form action=" ${pageContext.request.contextPath}/common/login" method="post" id="loginForm">
            <input  name="login_type"  hidden="hidden" value="1">
        <div class="form-group has-feedback">
            <input id="login_num" name="login_num"  class="form-control" placeholder="用户名">
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
        </div>
        <div class="form-group has-feedback">
            <input id="pw" name="pw" type="password" class="form-control" placeholder="密码">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <a href="#" class="btn btn-primary btn-block btn-flat" onclick="document.getElementById('loginForm').submit();">登录</a>
                <%--<a href="company-management.html" class="btn btn-primary btn-block btn-flat" onclick="document.getElementById('loginForm').submit();">登录</a>--%>
            </div>
        </div>
            <div class="row" style="margin-top:10px">
                <div class="col-xs-6">
                    <a href="${pageContext.request.contextPath}/common/sc_register" class="btn btn-primary btn-block btn-flat">部署院校入口</a>
                </div>
                <div class="col-xs-6">
                    <a href="${pageContext.request.contextPath}/common/te_register" class="btn btn-primary btn-block btn-flat">无单位教师入口</a>
                </div>
            </div>
        </form>
    </div>
</div>
<script src="${pageContext.request.contextPath}/web/plugin/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/web/plugin/bootstrap/dist/js/bootstrap.min.js"></script>
<script>
    window.onload=function(){
        var msg = '${msg}' ;
        if (msg!=null&&msg!=''){
            alert(msg);
        }
    }
</script>
</body>
</html>

