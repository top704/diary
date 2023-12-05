<!-- commentUpdate.jsp -->
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
<h1>댓글수정</h1>
<body>
	<form method="post" action="${path}/notice/commentUpdate">
	<input type="checkbox" name="isSecret" value="Y"/> &nbsp;비밀댓글
	<br>
	<input type="hidden" name="commentNo" value="${comment.commentNo}" />
	<textarea rows="3" cols="110" name="commentContent">${comment.commentContent}</textarea>
	<br><br>
	<button type="submit">수정하기</button>
	</form>
</body>
</html>