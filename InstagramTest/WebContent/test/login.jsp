<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<%@include file="../index.jsp"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="/InstagramTest/css/styles.css">
<script type="text/javascript">
	function login() {
		var id = document.getElementById("id").value;
		var pw = document.getElementById("pw").value;

		if (id.length == 0) {
			alert("id 입력");
			return;

		}
		if (pw.length == 0) {
			alert("pw 입력");
			return;
		} else {
			document.getElementById("loginform").submit();
		}
	}
</script>
<%
	if (request.getAttribute("msg") != null) {
		String msg = (String) request.getAttribute("msg");
		out.println("<script>alert('" + msg + "');</script>");
	}
%>
</head>
<body>
	<div class="container">
		<div class="d-flex justify-content-center h-100">
			<div class="card">
				<div class="card-header">

					<h3>Sign In</h3>

				</div>
				<div class="card-body">
					<form id="loginform" method="get" action="/InstagramTest/main.do">
						<input type="hidden" name="action" value="LOGIN">
						<div class="input-group form-group">
							<div id="customBtn" class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-user"></i></span>
							</div>
							<input type="text" class="form-control" placeholder="userid"
								name="id" id="id">

						</div>
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-key"></i></span>
							</div>
							<input type="password" class="form-control"
								placeholder="password" name="pw" id="pw">
						</div>
						<!-- 						<div class="row align-items-center remember">
							<input type="checkbox">Remember Me
						</div> -->
						<div class="form-group">
							<input type="button" value="Login"
								class="btn float-right login_btn" onclick="login()">
						</div>
					</form>
				</div>
				<div class="card-footer">
					<div class="d-flex justify-content-center">
						<div id="my-signin2"></div>
					</div>
					<div class="d-flex justify-content-center links">
						Don't have an account?<a
							href="/InstagramTest/main.do?action=JOINPAGE">Sign Up</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>