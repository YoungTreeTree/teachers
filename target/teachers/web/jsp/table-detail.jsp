<%--
  Created by IntelliJ IDEA.
  User: YoungTree
  Date: 2017/10/15
  Time: 16:39
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
    <div class="content-wrapper" style="margin-left: 0;">
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="box">
                        <div class="box-body">
                            <div class="table-header">
                                <h2>${main_table.year}年派出教师/志愿者及本土教师培训情况统计表（省市/直属高校）</h2>
                                <div class="table-header-row row">
                                    <div class="col-md-12">
                                        <div class="table-header-left">
                                            ${main_table.tProvince} 省（自治区/市）/直属高校 ${main_table.tSchool}
                                        </div>
                                        <div class="table-header-right">
                                            联系人：${main_table.tName}
                                            联系电话：${main_table.tPhone}
                                            传真：${main_table.tFax}
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <table class="table table-bordered table-input">
                                <thead>
                                <tr>
                                    <th rowspan="3">序号</th>
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
                                <c:forEach items="${per_tables}" var="per_table"    varStatus="id" >
                                    <tr>
                                        <td>${id.index+1}</td>
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
                                    <td>合计</td>
                                    <td>${main_table.longA}</td>
                                    <td>${main_table.longB}</td>
                                    <td>${main_table.longC}</td>
                                    <td>${main_table.longTotal}</td>
                                    <td>${main_table.shortA}</td>
                                    <td>${main_table.shortB}</td>
                                    <td>${main_table.shortC}</td>
                                    <td>${main_table.shortTotal}</td>
                                    <td>${main_table.volA}</td>
                                    <td>${main_table.volB}</td>
                                    <td>${main_table.volC}</td>
                                    <td>${main_table.volTotal}</td>
                                    <td>${main_table.localA}</td>
                                    <td>${main_table.localB}</td>
                                    <td>${main_table.localC}</td>
                                    <td>${main_table.localD}</td>
                                    <td>${main_table.total}</td>
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
        </section>
    </div>

    <div class="control-sidebar-bg"></div>
</div>

<script src="${pageContext.request.contextPath}/web/plugin/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/web/plugin/bootstrap/dist/js/bootstrap.min.js"></script>
</body>

</html>
