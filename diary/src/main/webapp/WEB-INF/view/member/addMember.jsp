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
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script>
		$(document).ready(function() {
			$('#idCkBtn').click(function(){
				console.log('#idCkBtn 클릭!');
				
				if($('#memberId').val().length < 4) {
					alert('3자리이상 아이디를 입력하세요');
				} else {
					// DB 없으면 ajax 
					$.ajax({
						url:'/diary/member/idCk',
						method : 'post',
						data : {'memberId': $('#memberId').val()},
						success : function(json) {
							if(json == 0) {
								alert('사용 가능한 아이디 입니다');
								
							} else {
								alert('중복된 아이디 입니다');
								$('#memberId').val('');
							}
						},
						error : function(err) {
							console.log(err);
						}
					});
				}
				
			});
		});
</script>
<body>
	<h1>회원가입</h1>
	<form id="addForm" method="post" action="${path}/member/addMember">
		<table border="1">
			<tr>
				<td>memberId</td>
				<td><input type="text" id="memberId" name="memberId"/></td><button type="button" id="idCkBtn">중복확인</button>
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
