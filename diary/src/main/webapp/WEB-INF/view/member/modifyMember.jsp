<!-- modifyMember.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<c:set var="path" value="${pageContext.request.contextPath}" /> <!-- request.getContextPath 간소화 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<h1>비밀번호 수정</h1>
	<span>${msg}</span>
	<form method="post" id="updateForm" action="${path}/member/modifyMember">
		<table>
			<tr>
				<td>이전 비밀번호</td>
				<td><input type="password" id="memberPwHis" name="memberPwHis" /></td>
			</tr>
			<tr>
				<td>변경할 비밀번호</td>
				<td><input type="password" id="memberPw" name="memberPw" /></td>
			</tr>
			<tr>
				<td>변경할 비밀번호</td>
				<td><input type="password" id="memberPwCk" name="memberPwCk" /></td>
			</tr>
		</table>
		<button type="button" id="updateBtn"> 수정 </button>
	</form>
</body>
<script type="text/javascript">
	$('#updateBtn').click(function(){
		
		if($('#memberPwHis').val().length < 1){
			alert('이전 비밀번호를 입력하세요');
		}else if($('#memberPw').val().length < 1){
			alert('새로운 비밀번호를 입력하세요');
		}else if($('#memberPw').val() != $('#memberPwCk').val()){
			alert('새로운 비밀번호를 확인하세요');
		}else{
			$('#updateForm').submit();
		}
	});
	
</script>
</html>