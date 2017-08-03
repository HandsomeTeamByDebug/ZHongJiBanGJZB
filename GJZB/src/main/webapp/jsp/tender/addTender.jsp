<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta charset="UTF-8"> 
		<title>添加项目</title>
		<%@include file="/js/common_jsAndcss.jsp"%>
	</head>
	<body>
	<div style="padding:3px 2px;border-bottom:1px solid #ccc">发布项目</div>
	<!-- basePath  -->
     <form action="/GJZB/ReadWord/addProjectResource" method="post" id="addUserForm" enctype="multipart/form-data" >
	<table>
				<%-- 项目id  设置默认--%>
			<tr>
				<td><input type="hidden" name="projectId" id="projectId" value="1" /></td>
			</tr>
			<tr>
				<td>项目名称:</td>
				<td><input name="projectName" id="projectName" type="text"></input></td>
			</tr>
			<tr>
				<td>价格：</td>
				<td><input name="price"  id="price" type="text"></input></td>
			</tr>
			 
			<tr>
				<td>项目类型：</td>
				<td>
				  <div name="type.typeId" class="type"></div>
				</td>
			</tr>
			
			  <tr>
				<td>发布时间:</td>
			   <td><input name="releaseTime" id="releaseTime"  type="date" ></input></td>
			</tr>
			<tr>
			<td>截止时间：</td>
			<td><input name="lastTime" id="lastTime" type="date"></input></td>
			</tr>
			
			<tr> 	
				<td>上传文档：</td>
				<td><input type="file" name="file"   id='projectName'></td>
			</tr>
			<tr> 	
				<td>发布人：</td>
				<td><input type="text" name="user.userId"  id='user.userId'></td>
			</tr>
			<tr>
				
				<td><input type="button" id="add_submit" value="确认发布"></input></td>
				<td><input type="reset" value="重置"/></td>
			</tr>
		</table>
	</form>

	<script type="text/javascript">
			$(function(){
				$("#add_submit").click(function(){
					jquerySubByFId('addUserForm',addProject_callback,null,"json");
				});
			});
			$('.type').combobox({
				valueField : 'typeId',
				textField : 'projectType',
				url : '/GJZB/type/test/getTypes',
			});
			
			function addProject_callback(result){
				alert("ok " + result + "dkg");
			}
		
	</script>
	</body>
</html>
