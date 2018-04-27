<%--
  Created by IntelliJ IDEA.
  User: YoungTree
  Date: 2017/10/15
  Time: 16:40
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
            <ul class="sidebar-menu" data-widget="tree">
                <li><a href="/young/top-company-management"><span>单位管理</span></a></li>
                <li class="active"><a href="/young/top-table-management"><span>表格管理</span></a></li>
                <li><a href="/young/top-statistics"><span>汇总统计</span></a></li>
                <li><a href="/young/top-psd-for-manager"><span>修改密码</span></a></li>
            </ul>
        </section>
    </aside>

    <div class="content-wrapper">
        <section class="content-header">
            <h1>表格管理</h1>
        </section>

        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="box">
                        <div class="box-header with-border">
                            <label class="control-label">年份：</label>
                            <select class="form-control" id="years">
                                <c:forEach items="${years}" var="year"    varStatus="id">
                                    <option value="${year.year}"> ${year.year}</option>
                                </c:forEach>
                                <option value="-1">全部</option>
                            </select>
                            <label class="control-label">账号：</label>
                            <select class="form-control" id="codes">
                                <c:forEach items="${codes}" var="code"    varStatus="id">
                                    <option value="${code.code}">${code.code} ${code.name}</option>
                                </c:forEach>
                                <option value="-1">全部</option>
                            </select>
                            <a href="javascript:;" class="btn btn-primary pull-right" onclick="f()">搜索</a>
                        </div>
                        <div class="box-body">
                            <table class="table table-bordered">
                                <tbody>
                                <tr>
                                    <th>年份</th>
                                    <th>单位名称</th>
                                    <th>账号</th>
                                    <th>状态</th>
                                    <th>详情</th>
                                </tr>
                                <c:forEach items="${list}" var="item"    varStatus="id">
                                    <tr>
                                        <td>${item.year}</td>
                                        <td>${item.name}</td>
                                        <td>${item.code}</td>
                                        <td>${item.stateString}</td>
                                        <td>
                                            <a href="/young/${item.tId}/table-detail/1" target="_blank" class="btn btn-default">查看详情</a>
                                            <a href="javascript:;" data-toggle="modal" data-target="#deny-alert"  class="btn btn-default" onclick="f2(${item.tId})" >驳回</a>
                                            <a href="/young/downloadFile/1/${item.tId}"  class="btn btn-default">导出Excel</a>
                                        </td>
                                    </tr>
                                </c:forEach>


                                </tbody>
                            </table>
                        </div>
                        <%--<div class="box-footer clearfix">--%>
                            <%--<ul class="pagination no-margin pull-right">--%>
                                <%--<li><a href="#">«</a></li>--%>
                                <%--<li><a href="#">1</a></li>--%>
                                <%--<li><a href="#">2</a></li>--%>
                                <%--<li><a href="#">3</a></li>--%>
                                <%--<li><a href="#">»</a></li>--%>
                            <%--</ul>--%>
                        <%--</div>--%>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="deny-alert">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-body">
                            请输入驳回原因：
                            <textarea class="deny-reason" id="cancel_text"></textarea>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default pull-left" data-dismiss="modal">取消</button>
                            <button type="button" class="btn btn-primary" onclick="cancel()">驳回</button>
                        </div>
                    </div>
                </div>
            </div>
            <form action="" method="post" id="cancel_form" hidden="hidden" >
                <input type="text" id="form_cancel_text" name ="form_cancel_text" value="">
            </form>
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

<script>
    var l = document.getElementById('years').options.length;
    var o = document.getElementById('years').options;
    for(i=0;i<Number(l);i++){
        var aaa = o[i].value;
        console.log(aaa);
        if(aaa=="${year}"){
            console.log(aaa);
            o[i].selected =true;
        }
    }
    var l2 = document.getElementById('codes').options.length;
    var o2 = document.getElementById('codes').options;
    for(i=0;i<Number(l2);i++){
        var aaa = o2[i].value;
        console.log(aaa);
        if(aaa=="${code}"){
            console.log(aaa);
            o2[i].selected =true;
        }
    }
    function f() {
        var url = '/young/'+document.getElementById('years').value+'/'+document.getElementById('codes').value+'/top-table-management';
        window.location.href=url;
    }
    function f2(code) {
        document.getElementById('cancel_form').setAttribute("action",'/young/'+code+'/cancel');
    }
    function  cancel() {
        document.getElementById('form_cancel_text').setAttribute("value",$("#cancel_text").val());
        document.getElementById('cancel_form').submit();
    }
</script>

