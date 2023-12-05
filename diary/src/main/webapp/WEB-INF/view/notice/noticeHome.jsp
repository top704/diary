<!-- noticeHome.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<c:set var="path" value="${pageContext.request.contextPath}" /><!-- request.getContextPath 간소화 -->
<!-- 각종 메세지 출력 -->
<c:if test="${msg != null}">
	<script type="text/javascript">
		alert("${msg}");
	</script>
</c:if>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h1>NOTICE HOME 화면</h1>
		<br>
		<div>
			<a class="btn btn-outline-success" href="${path}/notice/addNotice"><b>글쓰기</b></a>
			<a class="btn btn-outline-success" href="${path}/member/home"><b>홈으로</b></a>
		</div>
		<table class="table-bordered">
			<tr>
				<td>글번호</td>
				<td style="width: 83%;">제목</td>
				<td>작성자ID</td>
			</tr>
			<c:forEach var="notice" items="${list}">
				<tr>
					<td>no.${notice.noticeNo}</td>
					<td><a href="${path}/notice/noticeOne?noticeNo=${notice.noticeNo}">${notice.noticeTitle}</a></td>
					<td>${notice.memberId}</td>
				</tr>
			</c:forEach>

		</table>
		<c:if test="${currentPage > 1}">
			<a href="${path}/notice/noticeHome?currentPage=${currentPage - 1}">이전</a>
		</c:if>

		<c:if test="${currentPage < lastPage}">
			<a href="${path}/notice/noticeHome?currentPage=${currentPage + 1}">다음</a>
		</c:if>
	</div>
</body>
</html>