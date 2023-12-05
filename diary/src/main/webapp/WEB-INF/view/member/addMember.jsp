<!-- addMember.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath}" /> <!-- request.getContextPath 간소화 -->
<!-- 각종 메세지 출력 -->
<c:if test="${param.msg != null}">
	<script type="text/javascript">
		alert("${param.msg}");
	</script>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script type="text/javascript" src="${path}/js/jquery-3.7.1.min.js"></script>
</head>

<body>
	<h1>회원가입</h1>
	<form id="addForm" method="post" action="${path}/member/addMember">
		<table border="1">
			<tr>
				<td>memberId</td>
				<td><input type="text" id="memberId" name="memberId"/></td>
			</tr>
			<tr>
				<td>memberPw</td>
				<td><input type="text" id="memberPw" name="memberPw"/></td>
			</tr>
			<tr>
				<td>memberPw확인</td>
				<td><input type="text" id="memberPw2" name="memberPw2"/></td>
			</tr>
		</table>
		<button type="submit" id="addBtn">회원가입</button>
	</form>
</body>
<script type="text/javascript">
	console.log('ffff');
	$('#addBtn').click(function(){
		if($('#memberId').val().length < 1){
			alert('아이디를 입력하세요');
		}else if($('#memberPw').val().length < 1){
			alert('비밀번호를 입력하세요');
		}else if($('#memberPw').val() != $('#memberPw2').val()){
			alert('비밀번호를 확인하세요');
		}else{
			$('#addForm').submit();
		}
	});
	
</script>
</html>