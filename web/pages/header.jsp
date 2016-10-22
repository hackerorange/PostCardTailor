<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.servletContext.contextPath}/pages/">
<div style="padding:5px;">
    <a href="<c:url value="/order/new"/>" class="easyui-linkbutton" data-options="size:'large',plain:true,iconAlign:'top'" style="margin-left: 5px">上传文件 </a>
    <a href="<c:url value="/postCardTailor/"/>" class="easyui-linkbutton" data-options="size:'large',plain:true,iconCls:'icon-reload'" style="margin-left: 5px">裁切明信片</a>
    <a href="#" class="easyui-linkbutton" data-options="size:'large',plain:true,iconCls:'icon-search'" style="margin-left: 5px">我上传的明信片</a>
    <a href="#" class="easyui-linkbutton" style="float: right;margin-right: 5px" data-options="size:'large',plain:true">退出</a>
    <a href="<c:url value="/logout"/>" class="easyui-linkbutton" style="float: right;margin-right: 5px" data-options="size:'large',plain:true">当前登录：${sessionScope.operator.name}</a>
</div>