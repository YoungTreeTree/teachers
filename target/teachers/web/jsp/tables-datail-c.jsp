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
                                <h2><input type="number" value="${main_table.year}" id="year" readonly="readonly">年派出教师/志愿者及本土教师培训情况统计表（省市/直属高校）</h2>
                                <div class="table-header-row row">
                                    <div class="col-md-12">
                                        <div class="table-header-left">
                                            <input id="province" type="text" value="${main_table.tProvince}">省（自治区/市）/直属高校<input id="school" type="text" value="${main_table.tSchool}">
                                        </div>
                                        <div class="table-header-right">
                                            联系人<input id="name" type="text" value="${main_table.tName}">
                                            联系电话<input id="phone" type="text" value="${main_table.tPhone}">
                                            传真<input id="fax" type="text" value="${main_table.tFax}">
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
                                <tbody id="my-tbody">

                                <c:if test="${main_table.state == 1||main_table.state == null||main_table.state == 3}">
                                    <c:forEach items="${per_tables}" var="per_table"    varStatus="id" >
                                        <tr id="${per_table.pId}">
                                            <td>${id.index+1}</td>
                                            <td><input type="number" value="${per_table.longA}"></td>
                                            <td><input type="number" value="${per_table.longB}"></td>
                                            <td><input type="number" value="${per_table.longC}"></td>
                                            <td><input type="number"   readonly="readonly" value="${per_table.longTotal}"></td>
                                            <td><input type="number" value="${per_table.shortA}"></td>
                                            <td><input type="number" value="${per_table.shortB}"></td>
                                            <td><input type="number" value="${per_table.shortC}"></td>
                                            <td><input type="number"  readonly="readonly" value="${per_table.shortTotal}"></td>
                                            <td><input type="number" value="${per_table.volA}"></td>
                                            <td><input type="number" value="${per_table.volB}"></td>
                                            <td><input type="number" value="${per_table.volC}"></td>
                                            <td><input type="number" readonly="readonly" value="${per_table.volTotal}"></td>
                                            <td><input type="number" value="${per_table.localA}"></td>
                                            <td><input type="number" value="${per_table.localB}"></td>
                                            <td><input type="number" value="${per_table.localC}"></td>
                                            <td><input type="number" value="${per_table.localD}"></td>
                                            <td><input type="number" readonly="readonly" value="${per_table.total}"></td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${main_table.state == 2}">
                                    <c:forEach items="${per_tables}" var="per_table"    varStatus="id" >
                                        <tr id="${per_table.pId}">
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
                                </c:if>
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
                        <div class="box-footer with-border text-right">
                            <div class="btn-group">
                                <c:if test="${main_table.state == 3}">
                                    <a href="javascript:;" data-toggle="modal" data-target="#deny-alert"  class="btn btn-default">查看驳回原因</a>
                                </c:if>
                                <c:if test="${main_table.state == 1||main_table.state == null||main_table.state == 3}">
                                    <a href="javascript:;" class="btn btn-default" onclick="f(1)">暂存</a>
                                    <a href="javascript:;" class="btn btn-default" onclick="f(2)">提交</a>
                                </c:if>
                                <c:if test="${main_table.state == 2}">
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
                            <textarea class="deny-reason">${main_table.tText}</textarea>
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


<form hidden="hidden" action="/young/up_table_temp" method="post" id="up_table">
    <input name="year" id="year2" type="text" hidden="hidden" value="">
    <input name="province" id="province2" type="text" hidden="hidden" value="${main_table.tProvince}">
    <input name="school" id="school2" type="text" hidden="hidden" value="${main_table.tSchool}">
    <input name="name" id="name2" type="text" hidden="hidden" value="${main_table.tName}">
    <input name="phone" id="phone2" type="text" hidden="hidden" value="${main_table.tPhone}">
    <input name="fax" id="fax2" type="text" hidden="hidden" value="${main_table.tFax}">
    <input name="type" id="type" type="text" hidden="hidden" value="1">
    <input name="per_table" id="i0" type="text" hidden="hidden" value="">
    <input name="per_table" id="i1" type="text" hidden="hidden" value="">
    <input name="per_table" id="i2" type="text" hidden="hidden" value="">
    <input name="per_table" id="i3" type="text" hidden="hidden" value="">
    <input name="per_table" id="i4" type="text" hidden="hidden" value="">
    <input name="per_table" id="i5" type="text" hidden="hidden" value="">
    <input name="per_table" id="i6" type="text" hidden="hidden" value="">
    <input name="per_table" id="i7" type="text" hidden="hidden" value="">
    <input name="per_table" id="i8" type="text" hidden="hidden" value="">
    <input name="per_table" id="i9" type="text" hidden="hidden" value="">
</form>
<script src="${pageContext.request.contextPath}/web/plugin/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/web/plugin/bootstrap/dist/js/bootstrap.min.js"></script>
</body>
</html>

<script>
    $(document).ready(function(){
        var $tbody = $("#my-tbody");
        var rowLen = $tbody.find("tr").length - 1; //行数 最后一行不算
        for (var i = 0; i < rowLen; i++) {
            var $thisRow = $tbody.find("tr").eq(i);
            var colLen = $thisRow.find("td").length;
            for (var j = 1; j < colLen; j++) { //列数 第一列不算
                var $input = $thisRow.find("td").eq(j).find("input");
                $input.bind('input porpertychange',function chech_num() {
                    var rowLen =$("#my-tbody").find("tr").length - 1; //行数 最后一行不算
                    for (var i = 0; i < rowLen; i++) {
                        var $thisRow = $("#my-tbody").find("tr").eq(i);
                        var colLen = $thisRow.find("td").length;
                        var num = new Array();
                        for (var j = 1; j < colLen; j++) { //列数 第一列不算
                            if( $thisRow.find("td").eq(j).find("input").val()==null||
                                $thisRow.find("td").eq(j).find("input").val()==''
                            ){
                                num[j]=0;
                            }else{
                                num[j]=parseInt( $thisRow.find("td").eq(j).find("input").val());
                            }
                        }
                        $thisRow.find("td").eq(4).find("input").attr("value",num[1]+num[2]+num[3]);
                        $thisRow.find("td").eq(8).find("input").attr("value",num[5]+num[6]+num[7]);
                        $thisRow.find("td").eq(12).find("input").attr("value",num[9]+num[10]+num[11]);
                        $thisRow.find("td").eq(17).find("input").attr("value",num[1]+num[2]+num[3]+num[5]+num[6]+num[7]+num[9]+num[10]+num[11]+num[16]+num[15]+num[14]+num[13]);
                    }
                });
            }
        }
    });



    function  f (flag) {
        document.getElementById('province2').setAttribute("value",document.getElementById('province').value);
        document.getElementById('phone2').setAttribute("value",document.getElementById('phone').value);
        document.getElementById('fax2').setAttribute("value",document.getElementById('fax').value);
        document.getElementById('name2').setAttribute("value",document.getElementById('name').value);
        document.getElementById('school2').setAttribute("value",document.getElementById('school').value);
        document.getElementById('year2').setAttribute("value",document.getElementById('year').value);
        document.getElementById('type').setAttribute("value",flag);
        var $tbody = $("#my-tbody");
        var rowLen = $tbody.find("tr").length - 1; //行数 最后一行不算
        console.log("tr size:" + rowLen);
        for (var i = 0; i < rowLen; i++) {
            var $thisRow = $tbody.find("tr").eq(i),
                result = $thisRow.attr("id");
            var colLen = $thisRow.find("td").length;
            for (var j = 1; j < colLen; j++) { //列数 第一列不算
                var data = $thisRow.find("td").eq(j).find("input").val();
                if (data == "") {
                    data = "0";
                }
                result += "_" + data;
            }
            document.getElementById('i'+i).setAttribute("value",result);
            console.log(123);
            console.log("data:" + result);
        }
        document.getElementById('up_table').submit();
    }


</script>

