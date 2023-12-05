<!-- scheduleUpdate.jsp -->
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
	<h1>스케줄 수정</h1>
	<form method="post" action="${path}/schedule/scheduleUpdate">
		<input type="hidden" id="scheduleNo" name="scheduleNo" value="${schedule.scheduleNo}">
		<textarea rows="10" cols="30" id="scheduleMemo" name="scheduleMemo">${schedule.scheduleMemo} </textarea>
		<br><br>
		<button type="submit" class="btn btn-primary">메모수정</button>
	</form>
</div>
</body>
</html>