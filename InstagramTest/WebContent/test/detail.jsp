<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true" import="test.*, java.util.*"%>
<%@include file="../index.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="fileVO" class="test.FileVO"></jsp:useBean>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	pageContext.setAttribute("newLineChar", "\n");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
input.underlined {
	border: 0;
	border-bottom: solid 1px #000;
	outline: none;
}
</style>
</head>
<body>
	<%
		if (request.getSession().getAttribute("user") != null) {
			Member m = (Member) request.getSession().getAttribute("user");
			request.setAttribute("userId", m.getId());
		} else {
			request.setAttribute("userId", "gaon");
		}
	%>
	<table style="margin-left: auto; margin-right: auto">
		<tr>
			<td><br>
				<hr> <br><a href="/InstagramTest/select.do?action=SEARCH&userId=&{post.author}">${post.author }</a> <input type="hidden"
				name="action" value="UPDATE"> <input type="hidden"
				name="num" value="${post.num }"></td>
		</tr>
		<tr>
			<td><div id="carouselExampleIndicators-${stat.index }"
					class="carousel slide" data-ride="carousel">
					<ol class="carousel-indicators">
						<c:forEach items="${post.files }" var="file" varStatus="status">
							<c:choose>
								<c:when test="${status.index eq 0} ">
									<li data-target="#carouselExampleIndicators-${stat.index }"
										data-slide-to="0" class="active"></li>
								</c:when>
								<c:otherwise>
									<li data-target="#carouselExampleIndicators-${stat.index }"
										data-slide-to="${status.index }"></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</ol>
					<div class="carousel-inner" role="listbox">
						<c:forEach items="${post.files }" var="file" varStatus="status">
							<c:choose>
								<c:when test="${status.index eq 0}">
									<div class="carousel-item active">
										<c:choose>
											<c:when test="${fileVO.isVideo(file)}">
												<video class="video-fuild" autoplay loop muted>
													<source
														src="/InstagramTest/Upload/${post.author }/${post.day}/${file}"
														type="video/mp4" />
												</video>

											</c:when>
											<c:otherwise>
												<img class="d-block w-100"
													src="/InstagramTest/Upload/${post.author }/${post.day}/${file}"
													alt="First slide">
											</c:otherwise>
										</c:choose>
									</div>
								</c:when>
								<c:otherwise>
									<div class="carousel-item">
										<c:choose>
											<c:when test="${fileVO.isVideo(file)}">
												<video class="video-fuild" autoplay loop muted>
													<source
														src="/InstagramTest/Upload/${post.author }/${post.day}/${file}"
														type="video/mp4" />
												</video>
											</c:when>
											<c:otherwise>
												<img class="d-block w-100"
													src="/InstagramTest/Upload/${post.author }/${post.day}/${file}"
													alt="Other slide">
											</c:otherwise>
										</c:choose>
									</div>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
					<a class="carousel-control-prev"
						href="#carouselExampleIndicators-${stat.index }" role="button"
						data-slide="prev"> <span class="carousel-control-prev-icon"
						aria-hidden="true"></span> <span class="sr-only">Previous</span>
					</a> <a class="carousel-control-next"
						href="#carouselExampleIndicators-${stat.index }" role="button"
						data-slide="next"> <span class="carousel-control-next-icon"
						aria-hidden="true"></span> <span class="sr-only">Next</span>
					</a>
				</div></td>
		</tr>
		<tr>
			<td>${fn:replace(post.memo, newLineChar, "<br/>") }</td>
		</tr>
		<tr align="right">
			<td style="font-size: x-small">uploaded : ${post.day }</td>
		</tr>

		<form id="commentform" action="/InstagramTest/main.do" method="get">
			<input type="hidden" name="action" value="COMMENT"> <input
				type="hidden" name="num" value="${post.num }">

			<tr>
				<td>comments</td>
			</tr>
			<c:forEach var="comment" items="${post.comments }">
				<tr>
					<td><a href="/InstagramTest/select.do?action=SEARCH&userId=${comment.commentId}">${comment.commentId }</a>:&nbsp;&nbsp;&nbsp;
						&nbsp;${comment.memo }</td>

				</tr>
				<tr>
					<td align="right" style="font-size: x-small">${comment.day }</td>
				</tr>
			</c:forEach>
			<tr>
				<td>${userId }:&nbsp;&nbsp;&nbsp; &nbsp;<input
					class="underlined" type="text" name="memo"
					placeholder="input your feeling" size="30">
					<button type="button"
						onClick="document.getElementById('commentform').submit();">write</button>
					
				</td>
			</tr>
			<tr>
				<td align="right"><a href="/InstagramTest/select.do">list</a></td>
			</tr>
		</form>
	</table>

</body>
</html>