<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="test.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../index.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="/InstagramTest/css/styles.css">
</head>
<script type="text/javascript">
	function checkPw() {
		var pw = document.getElementById("pw").value;
		var pw2 = document.getElementById("pw2").value;
		var id = document.getElementById("id").value;
		var name = document.getElementById("name").value;

		if (id.length == 0) {
			alert("id 미입력");
			return;
		}

		if (name.length == 0) {
			alert("name 미입력");
			return;
		}

		if (pw.length == 0) {
			alert("pw 미입력");
			return;
		}

		if (pw2.length == 0) {
			alert("pw2 미입력");
			return;
		}

		if (pw != pw2) {
			alert("비밀번호 불일치");
			document.getElementById("joinform").reset();
			return;
		} else {
			document.getElementById("joinform").submit();
		}
	}
	function reset(){
		document.getElementById("joinform").reset();
	}
</script>
<body>
	<div class="container">
		<div class="d-flex justify-content-center h-100">
			<div class="card">
				<div class="card-header">

					<h3>Sign Up</h3>

				</div>
				<div class="card-body">
					<form id="joinform" method="get" action="/InstagramTest/main.do">
						<input type="hidden" name="action" value="JOIN">
						<div class="input-group form-group">

							<div id="customBtn" class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-user"></i></span>
							</div>
							<input type="text" class="form-control" placeholder="your id"
								name="id" id="id" value=${id }>
						</div>
						<div class="input-group form-group">

							<div id="customBtn" class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-user"></i></span>
							</div>
							<input type="text" class="form-control" placeholder="your name"
								name="name" id="name" value=${name }>
						</div>
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-key"></i></span>
							</div>
							<input type="password" class="form-control"
								placeholder="password" name="pw" id="pw">
						</div>
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-key"></i></span>
							</div>
							<input type="password" class="form-control"
								placeholder="password check" name="pw2" id="pw2">
						</div>
						<div class="form-group">
							<input type="button" value="reset"
								class="btn float-left login_btn" onclick="reset()">
						</div>
						<div class="form-group">
							<input type="button" value="Join"
								class="btn float-right login_btn" onclick="checkPw()">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>