<!-- removeMember.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath}" /> <!-- request.getContextPath 간소화 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" id="delForm" name="delForm" action="${path}/member/removeMember">
	<table>
		<tr>
		<td>비밀번호</td>
		<td><input type="password" id="memberPw" name="memberPw"/></td>
		</tr>
	</table>
	<button type="submit" id="delBtn">계정삭제</button>
	</form>
</body>
</html>