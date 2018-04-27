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
                            <span class="hidden-xs">${user.uCodeE} ${user.uCodeW} ${user.uCodeQ}</span>
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
                <li><a href="${pageContext.request.contextPath}/teacher/table"><span>表格录入</span></a></li>
                <li class="active"><a href="${pageContext.request.contextPath}/teacher/history"><span>历史表格</span></a></li>
                <li><a href="${pageContext.request.contextPath}/teacher/psd"><span>修改密码</span></a></li>
            </ul>
        </section>
    </aside>

    <div class="content-wrapper">
        <section class="content-header">
            <h1>历史表格</h1>
        </section>

        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="box">
                        <div class="box-body">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>年份</th>
                                    <th>状态</th>
                                    <th>单位审核</th>
                                    <th>省厅审核</th>
                                    <th>汉办审核</th>
                                    <th>详情</th>
                                </tr>
                                </thead>
                                <tbody>

                                <c:forEach items="${tables}" var="item"    varStatus="id">
                                    <tr>
                                        <td>${item.tUserInputYear}</td>
                                        <td>
                                            <c:if test="${item.tIfTemp==1}"> 暂存 </c:if>
                                            <c:if test="${item.tIfTemp==0}"> 已提交 </c:if>
                                        </td>
                                        <td>
                                            <c:if test="${item.tIfQOk==1}"> -- </c:if>
                                            <c:if test="${item.tIfQOk==2}"> 通过 </c:if>
                                            <c:if test="${item.tIfQOk==3}"> 被驳回 </c:if>
                                        </td>
                                        <td>
                                            <c:if test="${item.tIfWOk==1}"> -- </c:if>
                                            <c:if test="${item.tIfWOk==2}"> 通过 </c:if>
                                            <c:if test="${item.tIfWOk==3}"> 被驳回 </c:if>
                                        </td>
                                        <td>
                                            <c:if test="${item.tIfEOk==1}"> -- </c:if>
                                            <c:if test="${item.tIfEOk==2}"> 通过 </c:if>
                                            <c:if test="${item.tIfEOk==3}"> 被驳回 </c:if>
                                        </td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/teacher/${item.tId}/detail" target="_blank" class="btn btn-default">查看</a>
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
                            <button type="button" class="btn btn-primary">确认</button>
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
