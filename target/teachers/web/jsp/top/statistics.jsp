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
                <li><a href="/young/top-company-management"><span>单位管理</span></a></li>
                <li><a href="/young/top-table-management"><span>表格管理</span></a></li>
                <li class="active"><a href="/young/top-statistics"><span>汇总统计</span></a></li>
                <li><a href="/young/top-psd-for-manager"><span>修改密码</span></a></li>
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
                                <c:forEach items="${years}" var="year"    varStatus="id">
                                    <option value="${year.year}">${year.year}</option>
                                </c:forEach>
                            </select>

                            <label class="control-label">省厅</label>
                            <select class="form-control" id="codes">
                                <option value="-1">全部</option>
                                <c:forEach items="${codes}" var="code"    varStatus="id">
                                    <option value="${code.code}">${code.code} ${code.name}</option>
                                </c:forEach>
                            </select>


                            <button class="btn btn-primary pull-right" onclick="f2()" style="margin-right:10px;">导出Excel文件</button>
                            <button class="btn btn-primary pull-right" onclick="f()"  style="margin-right:10px;">统计</button>
                        </div>
                        <div class="box-body">
                            <table class="table table-bordered table-input">
                                <thead>
                                <tr>
                                    <th rowspan="3">序号</th>
                                    <th rowspan="3">单位</th>
                                    <th colspan="12">外派汉语教师</th>
                                    <th colspan="4">本土教师培训</th>
                                    <th rowspan="3">合计<br>人数</th>
                                </tr>
                                <tr>
                                    <th colspan="4">长期派出教师</th>
                                    <th colspan="4">短期教师培训</th>
                                    <th colspan="4">派出志愿者人数</th>
                                    <th rowspan="2">来华培训</th>
                                    <th rowspan="2">赴外培训</th>
                                    <th rowspan="2">网络培训</th>
                                    <th rowspan="2">函授、自学</th>
                                </tr>
                                <tr>
                                    <th>省厅<br>派出</th>
                                    <th>校际<br>派出</th>
                                    <th>其他形式<br>派出</th>
                                    <th>总计</th>
                                    <th>省厅<br>派出</th>
                                    <th>校际<br>派出</th>
                                    <th>其他形式<br>派出</th>
                                    <th>总计</th>
                                    <th>省厅<br>派出</th>
                                    <th>校际<br>派出</th>
                                    <th>其他形式<br>派出</th>
                                    <th>总计</th>
                                </tr>
                                </thead>
                                <tbody>

                                <c:forEach items="${perTables}" var="per_table"    varStatus="id" >
                                    <tr>
                                        <td>${id.index+1}</td>
                                        <td>${per_table.uCode} ${per_table.name}</td>
                                        <td>${per_table.longA}</td>
                                        <td>${per_table.longB}</td>
                                        <td>${per_table.longC}</td>
                                        <td>${per_table.longTotal}</td>
                                        <td>${per_table.shortA}</td>
                                        <td>${per_table.shortB}</td>
                                        <td>${per_table.shortC}</td>
                                        <td>${per_table.shortTotal}</td>
                                        <td>${per_table.volA}</td>
                                        <td>${per_table.volB}</td>
                                        <td>${per_table.volC}</td>
                                        <td>${per_table.volTotal}</td>
                                        <td>${per_table.localA}</td>
                                        <td>${per_table.localB}</td>
                                        <td>${per_table.localC}</td>
                                        <td>${per_table.localD}</td>
                                        <td>${per_table.total}</td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td>总计</td>
                                    <td> </td>
                                    <td>${totalTable.longA}</td>
                                    <td>${totalTable.longB}</td>
                                    <td>${totalTable.longC}</td>
                                    <td>${totalTable.longTotal}</td>
                                    <td>${totalTable.shortA}</td>
                                    <td>${totalTable.shortB}</td>
                                    <td>${totalTable.shortC}</td>
                                    <td>${totalTable.shortTotal}</td>
                                    <td>${totalTable.volA}</td>
                                    <td>${totalTable.volB}</td>
                                    <td>${totalTable.volC}</td>
                                    <td>${totalTable.volTotal}</td>
                                    <td>${totalTable.localA}</td>
                                    <td>${totalTable.localB}</td>
                                    <td>${totalTable.localC}</td>
                                    <td>${totalTable.localD}</td>
                                    <td>${totalTable.total}</td>
                                </tr>
                                <tr>
                                    <td class="td-left" colspan="18">
                                        说明: <br>
                                        1. “长期派出教师”是统计派往国外各教学机构从事汉语、艺术、体育及各人文学科从事教学、讲座和研究等六个月及以上的教师人数。<br>
                                        2. “短期派出教师”是统计派往国外各教学机构从事汉语、艺术、体育及各人文学科从事教学、讲座和研究等六个月以内的教师人数。<br>
                                        3. 填表后请加盖本单位公章后扫描，以电子邮件方式发送至国家汉办师资处。
                                    </td>
                                </tr>
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
        <strong>Copyright &copy; 2017 <a href="#">师资派遣汇总系统</a>。</strong> 版权所有
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

    var l = document.getElementById('codes').options.length;
    var o = document.getElementById('codes').options;
    for(i=0;i<Number(l);i++){
        var aaa = o[i].value;
        console.log(aaa);
        if(aaa=="${code}"){
            console.log(aaa);
            o[i].selected =true;
        }
    }
    function f() {
        var url = '/young/'+document.getElementById('year').value+'/'+document.getElementById('codes').value+'/top-statistics';
        window.location.href=url;
    }
    function f2() {
        var url = '/young/downloadFile/2/'+document.getElementById('year').value+'/'+document.getElementById('codes').value;
        window.location.href=url;
    }
</script>

