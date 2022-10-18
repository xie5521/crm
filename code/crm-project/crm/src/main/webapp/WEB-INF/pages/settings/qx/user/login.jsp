<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
	<base href="<%=basePath%>">
	<meta charset="UTF-8">
	<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
	<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(function (){

			//添加键盘事件，回车登陆
			$(window).keydown(function (e){
				if(e.keyCode == 13){
					$("#loginBtn").click();
				}
			})
			//登陆按钮添加单击事件
			$("#loginBtn").click(function (){
				var loginAct = $.trim($("#loginAct").val());
				var loginPwd = $.trim($("#loginPwd").val());
				var idRemPwd = $("#idRemPwd").prop("checked");
				//表单验证
				if(loginBtn == ""){
					alert("用户名不能为空")
					return;
				}
				if(loginPwd == ""){
					alert("密码不能为空")
					return;
				}

				//显示正在验证
				//$("#msg").html("正在验证登陆中。。");

				$.ajax({
					url:'settings/qx/user/login.do',
					data:{
						loginAct:loginAct,
						loginPwd:loginPwd,
						idRemPwd:idRemPwd,
					},
					type:'post',
					dataType:'json',
					success:function (data){
						if(data.code == "1"){
							//登陆成功，跳转到业务的主页面
							window.location.href = "workbench/index.do";
						}else{
							//登陆失败，提示信息
							$("#msg").text(data.message);
						}
					},
					beforeSend:function (){
						$("#msg").html("正在验证登陆中。。");
						return true;
					}
				})
			})
		})
	</script>
</head>
<body>
<div style="position: absolute; top: 0px; left: 0px; width: 60%;">
	<img src="image/IMG_7114.JPG" style="width: 100%; height: 90%; position: relative; top: 50px;">
</div>
<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
	<div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">CRM &nbsp;<span style="font-size: 12px;">&copy;2019&nbsp;动力节点</span></div>
</div>

<div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
	<div style="position: absolute; top: 0px; right: 60px;">
		<div class="page-header">
			<h1>登录</h1>
		</div>
		<form action="workbench/index.html" class="form-horizontal" role="form">
			<div class="form-group form-group-lg">
				<div style="width: 350px;">
					<input class="form-control" type="text" id="loginAct" value="${cookie.loginAct.value}"  placeholder="用户名">
				</div>
				<div style="width: 350px; position: relative;top: 20px;">
					<input class="form-control" type="password" id="loginPwd" value="${cookie.loginPwd.value}" placeholder="密码">
				</div>
				<div class="checkbox"  style="position: relative;top: 30px; left: 10px;">
					<label>
						<c:if test="${not empty cookie.loginAct and not empty cookie.loginPwd}">
							<input type="checkbox" id="idRemPwd" checked>
						</c:if>
						<c:if test="${empty cookie.loginAct or empty cookie.loginPwd}">
							<input type="checkbox" id="idRemPwd">
						</c:if>
						 十天内免登录
					</label>
					&nbsp;&nbsp;
					<span id="msg"></span>
				</div>
				<button type="button" id="loginBtn"  class="btn btn-primary btn-lg btn-block"  style="width: 350px; position: relative;top: 45px;">登录</button>
			</div>
		</form>
	</div>
</div>
</body>
</html>
