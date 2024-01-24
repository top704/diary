<!-- loginMember.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath}" /> <!-- request.getContextPath 간소화 -->
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
<title>로그인</title>
<script type="text/javascript" src="${path}/js/jquery-3.7.1.min.js"></script>
</head>
<body>
	<h1>로그인</h1>
	<span>${param.loginResult}</span>
	<form id="addForm" method="post" action="${path}/member/login">
	<table>
		<tr>
			<td>ID<td>
			<td><input type="text" id="memberId" name="memberId" value="test1"/></td>
		</tr>
		<tr>
			<td>PW<td>
			<td><input type="text" id="memberPw" name="memberPw" value="1234"/></td>
		</tr>
	</table>
	<button type="submit" id="loginBtn">로그인</button>
	</form>
	<a href="${path}/member/addMember">회원가입</a>
</body>
<script type="text/javascript">
	$('#loginBtn').click(function(){
		if($('#loginId').val().length < 1){
			alert('아이디를 입력하세요');
		}else if($('#loginPw').val().length < 1){
			alert('비밀번호를 입력하세요');
		}else{
			$('#addForm').submit();
		}
	});
	
</script>
</html>