<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/pages/">
    <title>明细片裁切工具</title>
    <link rel="stylesheet" type="text/css" href="../themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../css/orange.css"/>
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../js/postCardTailor_v1.1.js"></script>
    <script type="text/javascript">
        var postCardTailor;
        $(function () {
            postCardTailor = new PostCard($("#postCard_Area"));
            //noinspection JSUnresolvedFunction
            $(document).keydown(function () {
                //设置不允许移动
                postCardTailor.disabledMove();
            }).keyup(function () {
                //设置允许移动
                postCardTailor.enabledMove();
            });
        });
    </script>
</head>

<body class="easyui-layout">
<div data-options="region:'north',border:false" style="background-color:lightgreen; height: 55px">
    <div style="padding:5px;">
        <jsp:include page="header.jsp"/>
    </div>
</div>
<div data-options="region:'west',split:true,title:'历史记录'" style="width:250px;padding:10px"></div>
<div data-options="region:'east',split:true,title:'缩略图及信息'" style="width:200px;padding:10px">east region</div>
<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px">south region</div>
<div data-options="region:'center',title:'明信片裁切'">
    <div id="postCard_Area" style="width: 100%;height: 100%"></div>
</div>
</body>

</html>