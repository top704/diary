<!-- noticeUpdate.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<c:set var="path" value="${pageContext.request.contextPath}" /> <!-- request.getContextPath 간소화 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
	<br>
	<h1>글쓰기</h1>
	<br>
	<form method="post" action="${path}/notice/addNotice">
		<h2>제목</h2>
		<textarea  style="resize: none;" rows="1" cols="110" id="noticeTitle" name="noticeTitle"> </textarea>
		<h2>내용</h2>
		<textarea  style="resize: none;" rows="18" cols="110" id="noticeContent" name="noticeContent"> </textarea>
		<br>
		비밀번호
		<input type="password" id="noticePw" name="noticePw">
		<br><br>
		<button type="submit" class="btn btn-primary">등록</button>
	</form>
</div>
</body>
</html>