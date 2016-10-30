<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.servletContext.contextPath}/pages/">
    <title>Basic Form - jQuery EasyUI Demo</title>
    <link rel="stylesheet" type="text/css" href="../themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../themes/icon.css">
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
    <script type="text/javascript">
    </script>
</head>

<body>
<div style="width: 400px;margin: 0 auto">
<div style="margin:20px 0"></div>
    <div class="easyui-panel" title="用户登录" style="width:100%;max-width:400px;padding:30px 30px">
        <form id="ff" method="post">
            <div style="margin-bottom:10px">
                <input class="easyui-textbox" name="name" style="width:100%"
                       data-options="label:'用户名称:',required:true,missingMessage:'客户名称不可以为空'">
            </div>
            <div style="margin-bottom:10px">
                <input type="password" class="easyui-textbox" name="password" style="width:100%"
                       data-options="label:'用户密码:',required:true,missingMessage:'水印信息不可为空'">
            </div>
        </form>
        <div style="text-align:center;padding:5px 0">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">提交</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">重置</a>
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