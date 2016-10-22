<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.servletContext.contextPath}/pages/">
    <title>创建新的订单</title>
    <link rel="stylesheet" type="text/css" href="../../themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../../themes/icon.css">
    <script type="text/javascript" src="../../js/jquery.min.js"></script>
    <script type="text/javascript" src="../../js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../js/upload.js"></script>
    <script type="text/javascript" src="../../js/webuploader.js"></script>
    <link rel="stylesheet" href="../../css/webuploader.css"/>
    <link rel="stylesheet" href="../../css/style_uploader.css"/>
</head>


<body class="easyui-layout">
<div data-options="region:'north',border:false" style="background-color:lightgreen; height: 55px">
    <div style="padding:5px;">
        <jsp:include page="../header.jsp"/>
    </div>
</div>
<div data-options="region:'west',split:true,title:'历史记录'" style="width:250px;padding:10px"></div>
<div data-options="region:'east',split:true,title:'缩略图及信息'" style="width:200px;padding:10px">east region</div>
<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px">south region</div>
<div data-options="region:'center',title:'明信片裁切'">
    <div style="margin:20px 0;"></div>
    <div style="width: 400px;margin: auto">
        <div class="easyui-panel" title="创建新订单"
             style="width:100%;max-width:400px;padding:30px 30px">
            <form id="ff" method="post">
                <div style="margin-bottom:10px">
                    <input class="easyui-textbox" name="customerName" style="width:100%"
                           data-options="label:'客户名称:',required:true,missingMessage:'客户名称不可以为空'" value="默认客户">
                </div>
                <div style="margin-bottom:10px">
                    <input class="easyui-textbox" name="waterMark" style="width:100%"
                           data-options="label:'水印信息:',required:true,missingMessage:'水印信息不可为空'" value="默认水印">
                </div>
                <div style="margin-bottom:10px">
                    <input type="hidden" name="uuid" value="${sessionScope.uuid}">
                    <select class="easyui-combobox" name="size" label="成品尺寸:" style="width:100%"
                            data-option="required:true">
                        <c:forEach items="${requestScope.sizes}" var="size">
                            <option value="${size}">${size}</option>
                        </c:forEach>
                    </select>
                </div>
                <div style="margin-bottom:10px">
                    <select class="easyui-combobox" name="type" label="默认版式:" style="width:100%"
                            data-option="required:true">
                        <option value="A">A版</option>
                        <option value="B" selected>B版</option>
                        <option value="C">C版</option>
                        <option value="AC">自动选择A版或C版</option>
                        <option value="BC">自动选择B版或C版</option>
                    </select>
                </div>
            </form>
            <div style="text-align:center;padding:5px 0">
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">提交</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">重置</a>
            </div>
        </div>
    </div>
</div>
<script>
    function submitForm() {
        if ($('#ff').form('validate')) {
            $('#ff').submit();
        }
    }

    function clearForm() {
        $('#ff').form('clear');
    }
</script>
</body>

</html>