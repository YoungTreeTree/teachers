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
                <li><a href="${pageContext.request.contextPath}/r/management"><span>省厅账号信息</span></a></li>
                <li><a href="${pageContext.request.contextPath}/r/management2"><span>部属院校账号信息</span></a></li>

                <li><a href="${pageContext.request.contextPath}/r/-1/-1/table-management"><span>省厅表格管理</span></a></li>
                <li><a href="${pageContext.request.contextPath}/r/-1/-1/table-management2"><span>部属院校表格管理</span></a></li>
                <li><a href="${pageContext.request.contextPath}/r/-1/-1/table-management3"><span>个体教师表格管理</span></a></li>

                <li class="active"><a href="${pageContext.request.contextPath}/r/-1/-1/table-list"><span>汇总统计</span></a></li>

                <li><a href="${pageContext.request.contextPath}/r/psd"><span>修改密码</span></a></li>
            </ul>
        </section>
    </aside>

    <div class="content-wrapper">
        <section class="content-header">
            <h1>汇总统计</h1>
        </section>

        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="box">
                        <div class="box-header with-border">
                            <label class="control-label">年份</label>
                            <select class="form-control" id="year">
                                <option value="-1">全部</option>
                                <c:forEach items="${years}" var="year"    varStatus="id">
                                    <option value="${year.year}">${year.year}</option>
                                </c:forEach>
                            </select>

                            <label class="control-label">类型</label>
                            <select class="form-control" id="type">
                                <option value="-1">全部</option>
                                <option value="2">省厅</option>
                                <option value="3">部属院校</option>
                                <option value="4">无单位教师</option>
                            </select>
                            <button class="btn btn-primary pull-right" onclick="f2()" style="margin-right:10px;">导出Excel文件</button>
                            <button class="btn btn-primary pull-right" onclick="f()"  style="margin-right:10px;">统计</button>
                        </div>
                        <div class="box-body">

                            <div class="table-header">
                                <h2>申请人信息汇总表（单位填写）</h2>
                            </div>
                            <table class="table table-bordered">

                                <thead>
                                <td colspan="2">单位名称</td>
                                <td colspan="4">${user.uName}</td>
                                <td colspan="2">联系人</td>
                                <td colspan="2">${user.uV2Name}</td>
                                <td colspan="2">联系电话</td>
                                <td colspan="2">${user.uV2Phone}</td>
                                <td colspan="1">联系邮箱</td>
                                <td colspan="6">${user.uV2Email}</td>
                                </thead>

                                <thead>
                                <td>序号</td>
                                <td>教师姓名</td>
                                <td>身份证号</td>
                                <td>国内单位</td>
                                <td>赴任时间</td>
                                <td>离任时间</td>
                                <td>赴任国家（中文）</td>
                                <td>国外岗位名称（中文）</td>
                                <td>赴任国家（英文）</td>
                                <td>国外岗位名称（英文）</td>
                                <td>申请人手机</td>
                                <td>申请人邮箱</td>
                                <td>单位联系人</td>
                                <td>联系人电话</td>
                                <td>邮寄地址（有单位填写单位地址）</td>
                                <td>邮编</td>
                                <td>是否提前离任</td>
                                <td>国内单位审批意见</td>
                                <td>汉办审批意见</td>
                                <td>证书编号</td>
                                <td>证书发放时间</td>

                                </thead>
                                <tbody>
                                <c:forEach items="${tables}" var="t"    varStatus="id">
                                    <tr>
                                        <td>${id.index+1}</td>
                                        <td>${t.tName}</td>
                                        <td>${t.tIdCard}</td>
                                        <td>${t.tChinaQ} ${t.tChinaW} ${t.tChinaE}</td>
                                        <td>${t.tStartY}-${t.tStartM}-${t.tStartD}</td>
                                        <td>${t.tEndY}-${t.tEndM}-${t.tEndD}</td>
                                        <td>${t.tWorldQ} ${t.tWorldW}</td>
                                        <td>${t.tWorldE}</td>
                                        <td>${t.tWorldQE} ${t.tWorldWE}</td>
                                        <td>${t.tWorldEE}</td>
                                        <td>${t.tPhone}</td>
                                        <td>${t.tMail}</td>
                                        <td>${t.tWho}</td>
                                        <td>${t.tZuoji}</td>
                                        <td>${t.tAddress}</td>
                                        <td>${t.tEms}</td>
                                        <td>
                                            <c:if test="${t.tIfGo==1}">
                                                是
                                            </c:if>
                                            <c:if test="${t.tIfGo!=1}">
                                                否
                                            </c:if>
                                        </td>
                                        <td>${t.tQNoReason}</td>
                                        <td>${t.tENoReason}</td>
                                        <td>${t.tNumber}</td>
                                        <td>${t.tDateY}-${t.tDateM}-${t.tDateD}</td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td colspan="22">
                                        <p style="float: right;margin-right: 10%;line-height: 30px">孔子学院总部/国家汉办师资处制表</p>
                                    </td>
                                </tr>

                                </tbody>
                            </table>

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
    var l = document.getElementById('year').options.length;
    var o = document.getElementById('year').options;
    for(i=0;i<Number(l);i++){
        var aaa = o[i].value;
        console.log(aaa);
        if(aaa=="${year}"){
            console.log(aaa);
            o[i].selected =true;
        }
    }

    var l = document.getElementById('type').options.length;
    var o = document.getElementById('type').options;
    for(i=0;i<Number(l);i++){
        var aaa = o[i].value;
        console.log(aaa);
        if(aaa=="${type}"){
            console.log(aaa);
            o[i].selected =true;
        }
    }
    function f() {
        var url = '${pageContext.request.contextPath}/r/'+document.getElementById('type').value+'/'+document.getElementById('year').value+'/table-list';
        window.location.href=url;
    }
    function f2() {
        var url = '${pageContext.request.contextPath}/r/'+document.getElementById('type').value+'/'+document.getElementById('year').value+'/downloadFile';
        window.location.href=url;
    }
</script>

