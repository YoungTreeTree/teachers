<%--
  Created by IntelliJ IDEA.
  User: YoungTree
  Date: 2017/10/15
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta http-equiv="X-UA-Compatible" content="IE=9" />
    <title>师资派遣汇总系统</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/plugin/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/css/AdminLTE.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/css/skin-blue.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/css/common.css">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <header class="main-header">
        <a href="javascript:;" class="logo">
            <span class="logo-lg">师资派遣</span>
        </a>
        <nav class="navbar navbar-static-top">
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <li class="user user-menu">
                        <a href="javascript:;" class="dropdown-toggle">
                            <span class="hidden-xs">${usercode}</span>
                        </a>
                    </li>
                    <li class="user user-menu">
                        <a href="/young/logout" class="dropdown-toggle">
                            <span class="hidden-xs">登出</span>
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>

    <aside class="main-sidebar">
        <section class="sidebar">
            <ul class="sidebar-menu">
                <li><a href="/young/company-management"><span>单位管理</span></a></li>
                <li><a href="/young/table-management"><span>表格管理</span></a></li>
                <li><a href="/young/statistics"><span>汇总统计</span></a></li>
                <li  class="active"><a href="/young/psd-for-manager"><span>修改密码</span></a></li>
            </ul>
        </section>
    </aside>

    <div class="content-wrapper">
        <section class="content-header">
            <h1>修改密码</h1>
        </section>


        <section class="content">
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <div class="box box-primary">
                        <div class="box-body">
                            <form action="/young/change_pw" method="post" id="change_pw">
                                <div class="form-group">
                                    <label for="oldPassword">旧密码</label>
                                    <input id="old_pw" name="old_pw" type="emaipasswordl" class="form-control" id="oldPassword" placeholder="请输入原密码">
                                </div>
                                <div class="form-group">
                                    <label for="newPassword1">新密码</label>
                                    <input id="new_pw1" name="new_pw1"  type="password" class="form-control" id="newPassword1" placeholder="请输入新密码">
                                </div>
                                <div class="form-group">
                                    <label for="newPassword2">新密码</label>
                                    <input id="new_pw" name="new_pw" type="password" class="form-control" id="newPassword2" placeholder="请再次输入新密码">
                                </div>
                            </form>
                        </div>
                        <div class="box-footer text-center">
                            <button class="btn btn-default">取消</button>
                            <button data-toggle="modal" data-target="#confirm-alert" class="btn btn-primary">确认</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="confirm-alert">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span>
                            </button>
                        </div>
                        <div class="modal-body text-center">
                            <p>是否确认修改密码</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default pull-left" data-dismiss="modal">取消</button>
                            <button type="button" class="btn btn-primary" onclick="document.getElementById('change_pw').submit();">确认</button>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>Version</b> 1.0.0
        </div>
        <strong>Copyright &copy; 2017 <a href="#">师资派遣汇总系统</a>。</strong> 版权所有
    </footer>

    <div class="control-sidebar-bg"></div>
</div>

<script src="${pageContext.request.contextPath}/web/plugin/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/web/plugin/bootstrap/dist/js/bootstrap.min.js"></script>
</body>

</html>
