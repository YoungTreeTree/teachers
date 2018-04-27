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
                            <form id="table_up_form" enctype='multipart/form-data' method="post" action="${pageContext.request.contextPath}/u/up_table">
                                <div class="table-header">
                                    <h2><input  type="number" id="year" name="year" value="${table.tUserInputYear}"/>（离任年份）荣誉证书登记表</h2>
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
                                        <td colspan="3"><input class="table-inline-input" id="tName" name="p" value="${table.tName}"/></td>
                                        <td colspan="2">身份证号</td>
                                        <td colspan="5"><input class="table-inline-input" id="tIdCard" name="p" value="${table.tIdCard}"/></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">国内单位（若没有则不填）</td>
                                        <td colspan="10">
                                            <input class="table-inline-input" style="width: 12%" id="tChinaQ" name="p" value="${table.tChinaQ}"/>
                                            <%--<select style="width: 12%">
                                                <option>abc</option>
                                                <option>123</option>
                                            </select>--%>
                                            <p class="inline-text">（省）</p>
                                            <input class="table-inline-input" style="width: 15%" id="tChinaW" name="p" value="${table.tChinaW}"/>

                                            <p class="inline-text">（市）</p>
                                            <input class="table-inline-input" style="width: 35%" id="tChinaE" name="p" value="${table.tChinaE}"/>
                                            <p class="inline-text">（单位名称）</p>
                                            <br/>
                                            <input type="checkbox" class="table-checkbox"/>
                                            <p class="inline-text">无单位</p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" rowspan="2">
                                            <p>国外任教单位</p>
                                            <p>（中/英文）</p>
                                            <p class="tip">如您完成两个或多个任期，请填写最后一个任期的信息。</p>
                                        </td>
                                        <td colspan="10">
                                            <input class="table-inline-input" style="width: 12%" id="tWorldQ" name="p" value="${table.tWorldQ}"/>
                                            <p class="inline-text">（洲）</p>
                                            <input class="table-inline-input" style="width: 15%" id="tWorldW" name="p" value="${table.tWorldW}"/>
                                            <p class="inline-text">（国）</p>
                                            <input class="table-inline-input" style="width: 35%" id="tWorldE" name="p" value="${table.tWorldE}"/>
                                            <p class="inline-text">（国外单位）</p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="10">
                                            <input class="table-inline-input" style="width: 25%" id="tWorldQE" name="p" value="${table.tWorldQE}"/>
                                            <p class="inline-text" style="width: 15%">（continent）</p>
                                            <input class="table-inline-input" style="width: 40%" id="tWorldWE" name="p" value="${table.tWorldWE}"/>
                                            <p class="inline-text" style="width: 15%">（country）</p>
                                            <br/>
                                            <input class="table-inline-input" style="width: 50%" id="tWorldEE" name="p" value="${table.tWorldEE}"/>
                                            <p class="inline-text" style="width: 45%">（overseas educational institution）</p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">任期时间</td>
                                        <td colspan="10">
                                            <input class="table-inline-input" style="width: 40px" id="tStartY" name="p" value="${table.tStartY}"/>
                                            <p class="inline-text">年</p>
                                            <input class="table-inline-input" style="width: 30px" id="tStartM" name="p" value="${table.tStartM}"/>
                                            <p class="inline-text">月</p>
                                            <input class="table-inline-input" style="width: 30px" id="tStartD" name="p" value="${table.tStartD}"/>
                                            <p class="inline-text">日</p>
                                            <p class="inline-text"> —— </p>
                                            <input class="table-inline-input" style="width: 40px" id="tEndY" name="p" value="${table.tEndY}"/>
                                            <p class="inline-text">年</p>
                                            <input class="table-inline-input" style="width: 30px" id="tEndM" name="p" value="${table.tEndM}"/>
                                            <p class="inline-text">月</p>
                                            <input class="table-inline-input" style="width: 30px" id="tEndD" name="p" value="${table.tEndD}"/>
                                            <p class="inline-text">日</p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" rowspan="3">联系方式</td>
                                        <td colspan="5">
                                            <p class="inline-text" style="width: 30%">申请人手机  |</p>
                                            <input class="table-inline-input" style="width: 65%" id="tPhone" name="p" value="${table.tPhone}"/>
                                        </td>
                                        <td colspan="5">
                                            <p class="inline-text" style="width: 30%">申请邮箱  |</p>
                                            <input class="table-inline-input" style="width: 68%" id="tMail" name="p" value="${table.tMail}"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="5">
                                            <p class="inline-text" style="width: 30%">单位联系人  |</p>
                                            <input class="table-inline-input" style="width: 65%" id="tWho" name="p" value="${table.tWho}"/>
                                        </td>
                                        <td colspan="5">
                                            <p class="inline-text" style="width: 30%">联系人电话  |</p>
                                            <input class="table-inline-input" style="width: 68%" id="tZuoji" name="p" value="${table.tZuoji}"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="7">
                                            <p class="inline-text" style="width: 30%">单位通讯地址  | </p>
                                            <input class="table-inline-input" style="width: 67%" id="tAddress" name="p" value="${table.tAddress}"/>
                                        </td>
                                        <td>
                                            <p class="inline-text" style="width: 95%">邮编</p>
                                        </td>
                                        <td colspan="2">
                                            <input class="table-inline-input" style="width: 95%" id="tEms" name="p" value="${table.tEms}"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th colspan="12">任期情况</th>
                                    </tr>
                                    <tr>
                                        <td colspan="4">是否提前离任</td>
                                        <td colspan="8">
                                            <div class="radio-wrap" style="line-height: 35px">
                                                <input type="radio" name="p" value="1"   <c:if test="${table.tIfGo=='1'}"> checked </c:if> />
                                                <p>是</p>
                                                <p class="inline-text" style="width: 12%;vertical-align: top;margin: 0">原因：</p>
                                                <input class="table-inline-input" style="width: 66%;vertical-align: top" id="tGoReason" name="p" value="${table.tGoReason}"/>
                                            </div>
                                            <%--<div class="radio-wrap">--%>
                                            <%--<input type="radio" name="p" value="1"   <c:if test="${table.tIfGo=='1'}"> checked </c:if> /><p>是</p>--%>
                                            <%--<input type="radio" name="p" value="0"   <c:if test="${table.tIfGo=='0'}"> checked </c:if> /><p>否</p>--%>
                                            <%--</div>--%>

                                            <div class="radio-wrap">
                                                <input type="radio" name="p" value="0"   <c:if test="${table.tIfGo=='0'}"> checked </c:if> /><p>否</p>
                                            </div>
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
                                <p>备注：此表仅限申请《国家公派出国教师荣誉证书》</p>
                                <input hidden="hidden" id="if_temp" name="if_temp" value="1"/>
                            </form>
                        </div>
                        <div class="box-footer with-border text-right">
                            <div class="btn-group">
                                <c:if test="${table.tIfQOk != 1 || table.tIfWOk != 1 || table.tIfEOk != 1}">
                                    <a href="javascript:;" data-toggle="modal" data-target="#deny-alert"  class="btn btn-default">查看驳回原因</a>
                                </c:if>
                                <c:if test="${table.tIfTemp==1}">
                                    <a href="javascript:;" class="btn btn-default" onclick="f(1)">暂存</a>
                                    <a href="javascript:;" class="btn btn-default" onclick="f(0)">提交</a>
                                </c:if>
                                <c:if test="${table.tIfTemp==2}">
                                    已提交
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="deny-alert">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-body">
                            <textarea class="deny-reason">${reason}</textarea>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default pull-left" data-dismiss="modal">返回</button>
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

function  f(type) {
    $("#if_temp").val(type);
    $("#table_up_form").submit();
}




</script>

