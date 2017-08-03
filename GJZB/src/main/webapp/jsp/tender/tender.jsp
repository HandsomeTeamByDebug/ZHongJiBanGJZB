<%@page import="com.lnsf.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/js/common_jsAndcss.jsp" %>
<title>招标模块管理</title>
</head>
<body>
	
		<!-- User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("index.jsp");
		} -->
	
	//招标模块管理模块的DataGrid 数据表
	<table id="manage"></table>

	//招标模块管理模块的工具栏
	<div id="manage_tool" style="padding: 5px;">
		<div style="margin-bottom: 5px;">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add-new"
				plain="true" onclick="manage_tool.add();">添加</a> <a href="#"
				class="easyui-linkbutton" iconCls="icon-edit-new" plain="true"
				onclick="manage_tool.edit();">修改</a> <a href="#"
				class="easyui-linkbutton" iconCls="icon-delete-new" plain="true"
				onclick="manage_tool.remove();">删除</a> <a href="#"
				class="easyui-linkbutton" iconCls="icon-reload" plain="true"
				onclick="manage_tool.reload();">刷新</a> <a href="#"
				class="easyui-linkbutton" iconCls="icon-redo" plain="true" id="redo"
				onclick="manage_tool.redo();">取消选择</a>
		</div>
		<div style="padding: 0 0 0 7px; color: #333;">
			根据类型查询：<input type="text" class="textbox" name="search_manager"
				style="width: 110px"> 创建时间从：<input type="text"
				name="date_from" class="easyui-datebox" editable="false"
				style="width: 110px"> 到：<input type="text" name="date_to"
				class="easyui-datebox" editable="false" style="width: 110px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-search"
				onclick="manage_tool.search();">查询</a>
		</div>
	</div>


	<script type="text/javascript">
		$(function(){
			$('#manage').datagrid({
				url : 'ht/manager',
				fit : true,
				fitColumns : true,
				striped : true,
				rownumbers : true,
				border : false,
				pagination : true,
				pageSize : 3,
				pageList : [ 3,6,10 ],
				pageNumber : 1,
				sortName : 'date',
				sortOrder : 'desc',
				toolbar : '#manage_tool',
				columns : [ [ {
					field : 'id',
					title : '自动编号',
					width : 100,
					checkbox : true,
				}, {
					field : 'name',
					title : '管理员帐号',
					width : 100,
				}, {
					field : 'auth',
					title : '拥有权限',
					width : 100,
					formatter : function(value, row, index) {//通过此方法格式化显示内容,value表示从json中取出该单元格的值，row表示这一行的数据，是一个对象,index:行的序号
						var str = "";
						if(value.search("1")){
							str += "添加管理员";
						}
						if(value.search("2")){
							str += "删除管理员";
						}
						if(value.search("3")){
							str += "修改管理员";
						}
						return str;
					}
				}, {
					field : 'date',
					title : '创建时间',
					width : 100,
				}, ] ],
			});
		})
	</script>

</body>
</html>