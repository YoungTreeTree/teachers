<%--
  Created by IntelliJ IDEA.
  User: YoungTree
  Date: 2017/10/15
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta http-equiv="X-UA-Compatible" content="IE=9" />
    <title>汉语国际荣誉证书统计系统</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/plugin/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/css/AdminLTE.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/css/skin-blue.min.css">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <header class="main-header">
        <a href="javascript:;" class="logo">
            <span class="logo-lg">证书统计</span>
        </a>
        <nav class="navbar navbar-static-top">
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <li class="user user-menu">
                        <a href="javascript:;" class="dropdown-toggle">
                            <span class="hidden-xs">汉办</span>
                        </a>
                    </li>
                    <li class="user user-menu">
                        <a href="${pageContext.request.contextPath}/common/logout" class="dropdown-toggle">
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
                <li ><a href="${pageContext.request.contextPath}/r/management"><span>单位管理</span></a></li>
                <li ><a href="${pageContext.request.contextPath}/r/-1/-1/table-management"><span>表格管理</span></a></li>
                <li><a href="${pageContext.request.contextPath}/r/-1/table-list"><span>汇总统计</span></a></li>


                <li ><a href="${pageContext.request.contextPath}/r/req2"><span>部署院校账号申请</span></a></li>
                <li><a href="${pageContext.request.contextPath}/r/management2"><span>部署院校账号信息</span></a></li>
                <li><a href="${pageContext.request.contextPath}/r/-1/-1/table-management2"><span>部署院校表格管理</span></a></li>
                <li><a href="${pageContext.request.contextPath}/r/-1/table-list2"><span>部署院校汇总统计</span></a></li>


                <li class="active"><a href="${pageContext.request.contextPath}/r/req3"><span>个体教师账号申请</span></a></li>
                <li><a href="${pageContext.request.contextPath}/r/management3"><span>个体教师账号管理</span></a></li>
                <li><a href="${pageContext.request.contextPath}/r/-1/-1/table-management3"><span>个体教师表格管理</span></a></li>
                <li><a href="${pageContext.request.contextPath}/r/-1/table-list3"><span>个体教师汇总统计</span></a></li>

                <li><a href="${pageContext.request.contextPath}/r/psd"><span>修改密码</span></a></li>
            </ul>
        </section>
    </aside>

    <div class="content-wrapper">
        <section class="content-header">
            <h1>部署院校申请</h1>
        </section>

        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="box">
                        <div class="box-header with-border">

                        </div>


                        <div class="box-body">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>个体教师姓名</th>
                                    <th>教师账号</th>
                                    <th>教师联系电话</th>
                                    <th>教师身份证号</th>
                                    <th>联系人邮箱</th>
                                    <th>通讯地址</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${users}" var="user"    varStatus="id">
                                    <tr>
                                        <td>${user.u2Name}</td>
                                        <td>te${user.u2Phone}</td>
                                        <td>${user.u2Phone}</td>
                                        <td>${user.u2IdCode}</td>
                                        <td>${user.u2Mail}</td>
                                        <td>${user.u2Address}</td>
                                        <td>
                                            <a class="btn btn-default"  href="${pageContext.request.contextPath}/r/${user.u2Id}/ok3">账号申请通过</a>
                                            <a  class="btn btn-default" href="${pageContext.request.contextPath}/r/${user.u2Id}/nook3">账号申请驳回</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
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
                            <p>确认重置密码为：123456?</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default pull-left" data-dismiss="modal">取消</button>
                            <button type="button" class="btn btn-primary"  ><a id="form_submit" href="">确认</a></button>
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
        <strong>Copyright &copy; 2017 <a href="#">汉语国际荣誉证书统计系统</a>。</strong> 版权所有
    </footer>

    <div class="control-sidebar-bg"></div>
</div>

<script src="${pageContext.request.contextPath}/web/plugin/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/web/plugin/bootstrap/dist/js/bootstrap.min.js"></script>
</body>

</html>
<script>
    /*function f(code) {
        document.getElementById('form_submit').setAttribute("href",'/young/'+code+'/recode');
        console.log(document.getElementById('form_submit').getAttribute('href'));
    }*/

</script>
