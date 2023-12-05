<!-- scheduleListByDay.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<c:set var="path" value="${pageContext.request.contextPath}" /> <!-- request.getContextPath 간소화 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스케줄 </title>
</head>
<body>
<div class="container">
	<br>
	<h1>${param.targetYear}년 ${param.targetMonth}월 ${param.toDay}일 스케줄 </h1>
	
	<br><br>
	<c:forEach var="m" items="${list}">
	
	
		No.${m.scheduleNo}
		
		<table width="450" height="120" class="table-bordered table-hover">
		<tr>
			<td>내용</td>
			<td>${m.scheduleMemo}</td>
		</tr>
		<tr>
			<td>작성일</td>
			<td>${m.createdate}</td>
		</tr>	
		</table>
		<br>
		<a class="btn btn-primary" href="${path}/schedule/scheduleUpdate?scheduleNo=${m.scheduleNo}">수정</a>
		
		<a class="btn btn-danger" href="${path}/schedule/scheduleDelete?scheduleNo=${m.scheduleNo}">삭제</a>
		<br>
		<br>
	</c:forEach>
	<br><br><br><br>
	<h1>일정 추가</h1>
	<form method="post" action="${path}/schedule/scheduleInsert">
	<input type="hidden" name="scheduleDate" value="${param.targetYear}-${param.targetMonth}-${param.toDay}">
	<textarea rows="3" cols="80" name="scheduleMemo"></textarea>
	<br><br>
	<button type="submit">일정추가</button>
	</form>
	
</div>
</body>
</html>