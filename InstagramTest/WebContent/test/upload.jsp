<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<%@include file="../index.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<fieldset>
		<legend>파일 업로드</legend>
		<form action="/InstagramTest/upload.do" method="post"
			enctype="multipart/form-data">
			<table>
				<tr>
					<td><input type="hidden" name="author" value="${userId }" /></td>
				</tr>
				<tr>
					<td>파일 :</td>
					<td><input multiple="multiple" type="file" value="파일 선택"
						name="filename[]" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="업로드" /></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea rows="10" cols="50" name="memo"></textarea></td>
				</tr>
			</table>
		</form>
	</fieldset>
</body>
</html>