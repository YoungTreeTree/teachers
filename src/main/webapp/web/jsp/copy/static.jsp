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
</head>

<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <div class="content-wrapper" style="margin-left: 0;">
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="box">
                        <div class="box-body">
                            <form id="table_up_form" enctype='multipart/form-data' method="post" action="/teacher/up_table">
                                <div class="table-header">
                                    <h2>${table.tUserInputYear}（年份）荣誉证书登记表</h2>
                                </div>
                                <table class="table table-bordered" style="margin-top: -30px">
                                    <thead style="visibility: hidden">
                                    <td>1</td>
                                    <td>2</td>
                                    <td>3</td>
                                    <td>4</td>
                                    <td>5</td>
                                    <td>6</td>
                                    <td>7</td>
                                    <td>8</td>
                                    <td>9</td>
                                    <td>10</td>
                                    <td>11</td>
                                    <td>12</td>

                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td colspan="2">姓名</th>
                                        <td colspan="3">${table.tName}</td>
                                        <td colspan="2">身份证号</td>
                                        <td colspan="5">${table.tIdCard}</td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">国内单位</td>
                                        <td colspan="10">
                                          ${table.tChinaQ}
                                            <p class="inline-text">（省）</p>
                                                 ${table.tChinaW}
                                            <p class="inline-text">（市）</p>
                                                 ${table.tChinaE}
                                            <p class="inline-text">（单位名称）</p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" rowspan="2">
                                            <p>国外任教单位</p>
                                            <p>（中/英文）</p>
                                        </td>
                                        <td colspan="10">
                                            <p class="tip">如您完成两个或多个任期，请填写最后一个任期的信息。</p>
                                            ${table.tWorldQ}
                                            <p class="inline-text">（洲）</p>
                                            ${table.tWorldW}
                                            <p class="inline-text">（国）</p>
                                            ${table.tWorldE}
                                            <p class="inline-text">（国外单位）</p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="10">
                                            ${table.tWorldQE}
                                            <p class="inline-text" style="width: 15%">（continent）</p>
                                         ${table.tWorldWE}
                                            <p class="inline-text" style="width: 15%">（country）</p>
                                            <br/>
                                         ${table.tWorldEE}
                                            <p class="inline-text" style="width: 45%">（overseas educational institution）</p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">任期</td>
                                        <td colspan="10">
                                           ${table.tStartY}
                                            <p class="inline-text">年</p>
                                           ${table.tStartM}
                                            <p class="inline-text">月</p>
                                           ${table.tStartD}
                                            <p class="inline-text">日</p>
                                            <p class="inline-text"> —— </p>
                                           ${table.tEndY}
                                            <p class="inline-text">年</p>
                                          ${table.tEndM}
                                            <p class="inline-text">月</p>
                                           ${table.tEndD}
                                            <p class="inline-text">日</p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" rowspan="3">任期</td>
                                        <td colspan="5">
                                            <p class="inline-text" style="width: 30%">申请人手机：</p>
                                           ${table.tPhone}
                                        </td>
                                        <td colspan="5">
                                            <p class="inline-text" style="width: 30%">申请邮箱：</p>
                                           ${table.tMail}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="5">
                                            <p class="inline-text" style="width: 30%">单位联系人：</p>
                                           ${table.tWho}
                                        </td>
                                        <td colspan="5">
                                            <p class="inline-text" style="width: 30%">联系人电话：</p>
                                            ${table.tZuoji}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="7">
                                            <p class="inline-text" style="width: 25%">单位通讯地址：</p>
                                            ${table.tAddress}
                                        </td>
                                        <td>
                                            <p class="inline-text" style="width: 95%">邮编</p>
                                        </td>
                                        <td colspan="2">
                                            ${table.tEms}
                                        </td>
                                    </tr>
                                    <tr>
                                        <th colspan="12">任期情况</th>
                                    </tr>
                                    <tr>
                                        <td colspan="4">是否提前离任</td>
                                        <td colspan="8">
                                            <div class="radio-wrap">
                                                <input readonly="readonly" type="radio" name="p" value="1"   <c:if test="${table.tIfGo=='1'}"> checked </c:if> /><p>是</p>
                                                <input readonly="readonly" type="radio" name="p" value="0"   <c:if test="${table.tIfGo=='0'}"> checked </c:if> /><p>否</p>
                                            </div>
                                            <p class="inline-text" style="width: 12%">原因：</p>
                                           ${table.tGoReason}
                                        </td>
                                    </tr>


                                    <tr>
                                        <th colspan="12">审批意见</th>
                                    </tr>
                                    <tr>
                                        <td colspan="2">国内单位审批意见</td>
                                        <td colspan="10">
                                            <c:if test="${table.tIfQOk==2}"> ${table.tQNoReason} </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">省、自治区、直辖市教育厅（委）意见</td>
                                        <td colspan="10">
                                            <c:if test="${table.tIfWOk==2}"> ${table.tWNoReason} </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">孔子学院总部/国家汉办审批意见</td>
                                        <td colspan="10">
                                            <c:if test="${table.tIfEOk==2}"> ${table.tENoReason} </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">证书编号</td>
                                        <td colspan="5">
                                            ${table.tNumber}
                                        </td>
                                        <td colspan="2">证书发放时间</td>
                                        <td colspan="3">
                                            ${table.tDateY} - ${table.tDateM} - ${table.tDateD}

                                        </td>
                                    </tr>


                                    </tbody>
                                </table>
                                <input hidden="hidden" id="if_temp" name="if_temp" value="1"/>
                            </form>
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

<script>






</script>

