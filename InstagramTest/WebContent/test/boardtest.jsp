<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true" import="test.*, java.util.*"%>
<%@include file="../index.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	pageContext.setAttribute("newLineChar", "\n");
%>

<c:out value="${cout}" escapeXml="false" />
<jsp:useBean id="fileVO" class="test.FileVO"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.menubar ul {
	list-style: none;
}

.menubar li ul {
	background-color: white;
	display: none; /* 평상시에는 서브메뉴가 안보이게 하기 */
	height: auto;
	padding: 0px;
	margin: 0px;
	border: 0px;
	position: absolute;
	width: 200px;
	z-index: 200;
	list-style: none;
}

.menubar li:hover ul {
	display: block; /* 마우스 커서 올리면 서브메뉴 보이게 하기 */
	list-style: none;
}
</style>
</head>
<%
	if (request.getAttribute("list") == null) {
		request.getRequestDispatcher("/select.do").forward(request, response);
	}

	if (request.getAttribute("msg") != null) {
		String msg = (String) request.getAttribute("msg");
		out.println("<script>alert('" + msg + "');</script>");
	}
%>
<body>
	<button type="button"
		onclick="location.href='/InstagramTest/main.do?action=UPLOAD'">Upload</button>
	<c:forEach items="${list }" var="i" varStatus="stat">
		<table style="margin-left: auto; margin-right: auto">
			<tr>
				<td><br>
					<hr><br><a href="/InstagramTest/select.do?action=SEARCH&userId=${i.author}">${i.author }</a></td>
				<c:set var="userId" value="${i.author }"></c:set>
				<td>
					<div class="menubar">
						<ul>
							<li><a href="#" id="current">menu</a>
								<ul>
									<li><a
										href="/InstagramTest/main.do?action=DETAIL&num=${i.num }">comment</a></li>
									<%
										String userId = (String) pageContext.getAttribute("userId");
											System.out.println("userId = " + userId);
											if (request.getSession().getAttribute("user") != null) {
												Member m = (Member) request.getSession().getAttribute("user");

												if (m.getId().equals(userId)) {
									%>


									<li><a
										href="/InstagramTest/main.do?action=REMOVE&num=${i.num }"
										onClick="return confirm('are you sure?')">remove</a></li>
									<li><a
										href="/InstagramTest/main.do?action=MODIFY&num=${i.num }">modify</a></li>

									<%
										}
											}
									%>
								</ul></li>
						</ul>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div id="carouselExampleIndicators-${stat.index }"
						class="carousel slide" data-ride="carousel">
						<ol class="carousel-indicators">
							<c:forEach items="${i.files }" var="file" varStatus="status">
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
							<c:forEach items="${i.files }" var="file" varStatus="status">
								<c:choose>
									<c:when test="${status.index eq 0}">
										<div class="carousel-item active">
											<c:choose>
												<c:when test="${fileVO.isVideo(file)}">
													<video class="video-fuild" autoplay loop muted>
														<source
															src="/InstagramTest/Upload/${i.author }/${i.day}/${file}"
															type="video/mp4" />
													</video>

												</c:when>
												<c:otherwise>
													<img class="d-block w-100"
														src="/InstagramTest/Upload/${i.author }/${i.day}/${file}"
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
															src="/InstagramTest/Upload/${i.author }/${i.day}/${file}"
															type="video/mp4" />
													</video>
												</c:when>
												<c:otherwise>
													<img class="d-block w-100"
														src="/InstagramTest/Upload/${i.author }/${i.day}/${file}"
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
					</div>
				</td>
			</tr>
			<tr>
				<td>${fn:replace(i.memo, newLineChar, "<br/>") }</td>
			</tr>
			<tr align="right" style="font-size: x-small">
				<td><br>uploaded : ${i.day }</td>
			</tr>
			<tr align = "center">
				<td><a href="/InstagramTest/main.do?action=DETAIL&num=${i.num }">comment</a></td>
			</tr>
		</table>
	</c:forEach>
</body>
</html>