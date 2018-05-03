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
    <title>汉语国际荣誉证书统计系统</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/plugin/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/css/AdminLTE.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/css/skin-blue.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/css/tableAppend.css">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <style>
        .table-inline-input{
            display: inline-block;
        }
        .inline-text{
            display: inline-block;
        }
        .inline-text-area{
            width: 70%;
            height: 100px;
        }
    </style>

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
            <ul class="sidebar-menu" data-widget="tree">
                <li><a href="${pageContext.request.contextPath}/r/management"><span>省厅账号信息</span></a></li>
                <li><a href="${pageContext.request.contextPath}/r/management2"><span>部属院校账号信息</span></a></li>

                <li><a href="${pageContext.request.contextPath}/r/-1/-1/table-management"><span>省厅表格管理</span></a></li>
                <li class="active"><a href="${pageContext.request.contextPath}/r/-1/-1/table-management2"><span>部属院校表格管理</span></a></li>
                <li><a href="${pageContext.request.contextPath}/r/-1/-1/table-management3"><span>个体教师表格管理</span></a></li>

                <li><a href="${pageContext.request.contextPath}/r/-1/-1/table-list"><span>汇总统计</span></a></li>

                <li><a href="${pageContext.request.contextPath}/r/psd"><span>修改密码</span></a></li>
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
                                <c:forEach items="${users}" var="user"    varStatus="id">
                                    <option value="${user.uId}"> ${user.uCodeQ} ${user.uName}</option>
                                </c:forEach>
                                <option value="-1">全部</option>
                            </select>
                            <a href="javascript:;" class="btn btn-primary pull-right" onclick="f()">搜索</a>
                            <a href="javascript:;" class="btn btn-primary pull-right"  id="submit-all-id" style="margin-right: 20px">批量通过</a>
                        </div>
                        <div class="box-body">
                            <table class="table table-bordered">
                                <tbody>
                                <tr>
                                    <th>年份</th>
                                    <th>单位名称</th>
                                    <th>账号</th>
                                    <th>状态</th>
                                    <th>操作</th>
                                    <th>批量审核</th>
                                </tr>
                                <c:forEach items="${tables}" var="item"    varStatus="id">
                                    <tr>
                                        <td>${item.tableCopy.tUserInputYear}</td>
                                        <td>${item.user.uName}</td>
                                        <td>${item.user.uV2Name}</td>
                                        <td>
                                            <c:if test="${item.tableCopy.tIfEOk==1}"> -- </c:if>
                                            <c:if test="${item.tableCopy.tIfEOk==2}"> 通过 </c:if>
                                            <c:if test="${item.tableCopy.tIfEOk==3}"> 被驳回 </c:if></td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/common/${item.tableCopy.tId}/statics2" target="_blank" class="btn btn-default">查看详情</a>
                                            <c:if test="${item.tableCopy.tIfEOk==1}">
                                                <a href="javascript:;" data-toggle="modal" data-target="#deny-alert"  class="btn btn-default" onclick="f2(${item.tableCopy.tId})" >驳回</a>
                                                <a href="javascript:;" data-toggle="modal" data-target="#up-alert"  class="btn btn-default" onclick="f3(${item.tableCopy.tId})" >通过</a>
                                            </c:if>
                                           <%-- <a href="javascript:;" data-toggle="modal" data-target="#deny-alert"  class="btn btn-default" onclick="f2(${item.tId})" >驳回</a>--%>
                                        </td>
                                        <td>
                                            <c:if test="${item.tableCopy.tIfEOk==1}">
                                                <input type="checkbox" class="table-checkbox" data-id="${item.tableCopy.tId}"/>
                                            </c:if>
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
            <div class="modal fade" id="up-alert">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-body">
                            <p class="inline-text" style="vertical-align: top">请给出意见：</p>
                            <textarea class="deny-reason inline-text-area" id="up_text"></textarea>
                            <br/>
                            <br/>
                            <p class="inline-text">证书编号:</p>
                            <input class="table-inline-input" style="width: 300px" id="tNumber" name="tNumber" value=""/>
                            <br/>
                            <br/>
                            <input class="table-inline-input" style="width: 40px" id="tDateY" name="tDateY" value=""/>
                            <p class="inline-text">年</p>
                            <input class="table-inline-input" style="width: 30px" id="tDateM" name="tDateM" value=""/>
                            <p class="inline-text">月</p>
                            <input class="table-inline-input" style="width: 30px" id="tDateD" name="tDateD" value=""/>
                            <p class="inline-text">日</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="button" class="btn btn-primary" onclick="up()">审核通过</button>
                        </div>
                    </div>
                </div>
            </div>
            <form action="" method="post" id="cancel_form" hidden="hidden" enctype='multipart/form-data' >
                <input type="text" id="form_cancel_text" name ="form_cancel_text" value="">
                <input type="text" id="tNumber2" name ="tNumber" value="">
                <input type="text" id="tDateY2" name ="tDateY" value="">
                <input type="text" id="tDateM2" name ="tDateM" value="">
                <input type="text" id="tDateD2" name ="tDateD" value="">
            </form>
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
        var url = '${pageContext.request.contextPath}/r/'+document.getElementById('years').value+'/'+document.getElementById('codes').value+'/table-management2';
        window.location.href=url;
    }


    function f2(code) {
        document.getElementById('cancel_form').setAttribute("action",'${pageContext.request.contextPath}/r/'+code+'/cancel2');
    }
    function f3(code) {
        document.getElementById('cancel_form').setAttribute("action",'${pageContext.request.contextPath}/r/'+code+'/up2');
    }
    function  cancel() {
        document.getElementById('form_cancel_text').setAttribute("value",$("#cancel_text").val());
        document.getElementById('cancel_form').submit();
    }

    function  up() {
        document.getElementById('form_cancel_text').setAttribute("value",$("#up_text").val());

        $("#tNumber2").val($("#tNumber").val());
        $("#tDateY2").val($("#tDateY").val());
        $("#tDateM2").val($("#tDateM").val());
        $("#tDateD2").val($("#tDateD").val());
        document.getElementById('cancel_form').submit();
    }
</script>

<script>
    $("#submit-all-id").on("click",function () {
        var $table=$("table");
        var idList=[];
        $table.find(".table-checkbox").each(function () {
            if($(this).prop("checked")===true){
                idList.push($(this).attr("data-id"));
            }
        });
        var idString=idList.join("-");
        console.log(idString);
        if(idString==''){
            alert('请勾选以后操作');
        }else{
            var url = '${pageContext.request.contextPath}/r/'+idString+'/up_group2';
            window.location.href=url;
        }
    });
</script>

