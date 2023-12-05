<!-- noticeOne.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<a class="btn btn-outline-success" href="${path}/notice/noticeHome"><b>HOME</b></a>
	<br>
	<h1>NOTICEONE 화면</h1>
	<br>
		
		<h2>제목</h2>
		<textarea  style="resize: none;" rows="1" cols="110" readonly> ${noticeOne.noticeTitle}</textarea>
		<h2>내용</h2>
		<textarea  style="resize: none;" rows="18" cols="110" readonly> ${noticeOne.noticeContent}</textarea>
		<br>
		비밀번호
	
		<form method="post" action="${path}/notice/deleteNotice">
			<input type="password" id="noticePw" name="noticePw" />
			<br><br>
			<a href="${path}/notice/noticeUpdate?noticeNo=${noticeOne.noticeNo}">수정</a>
			
			<input type="hidden" name="noticeNo" value="${noticeOne.noticeNo}" />
			<button type="submit">삭제</button>
		</form>
				
		<br>
		<h2>COMMENT</h2>
		<c:forEach var="comment" items="${list}">
			<br>
			<!-- 댓글 본인 -->
			<!-- 매니저 -->
			<!-- 비밀댓글 타인 -->
			<!-- 공개댓글 타인 -->
			<c:choose>
			
			<c:when test="${comment.memberId eq memberId}">
			<span>ID : ${comment.memberId} </span>
			<br>
			<span>COMMENT : ${comment.commentContent} </span>&nbsp;<a href="${path}/notice/commentUpdate?commentNo=${comment.commentNo}">수정</a>&nbsp; <a href="${path}/notice/commentDelete?commentNo=${comment.commentNo}">삭제</a>
			</c:when>
			
			<c:when test="${result eq true}">				
			<span>ID : ${comment.memberId} </span>
			<br>
			<span>COMMENT : ${comment.commentContent} </span><a href="${path}/notice/commentDelete?commentNo=${comment.commentNo}"> 삭제</a>
			</c:when>
			
			<c:when test="${comment.isSecret eq 'Y'}">
			<span>비밀댓글입니다</span>
			<br>
			<span>---------------- </span>
			</c:when>
			
			<c:otherwise>
			<span>ID : ${comment.memberId} </span>
			<br>
			<span>COMMENT : ${comment.commentContent} </span>
			</c:otherwise>
			</c:choose>
			<br>
			
		</c:forEach>
		<br>
		<c:if test="${currentPage > 1}">
			<a href="${path}/notice/noticeOne?noticeNo=${noticeOne.noticeNo}&currentPage=${currentPage - 1}">이전</a>
		</c:if>

		<c:if test="${currentPage < lastPage}">
			<a href="${path}/notice/noticeOne?noticeNo=${noticeOne.noticeNo}&currentPage=${currentPage + 1}">다음</a>
		</c:if>
		<br>
		<br>
		
		<form method="post" action="${path}/notice/commentInsert">
		<input type="checkbox" name="isSecret" value="Y"/> &nbsp;비밀댓글
		<br>
		<input type="hidden" name="noticeNo" value="${noticeOne.noticeNo}" />
		<textarea rows="3" cols="110" name="commentContent"></textarea>
		<br><br>
		<button type="submit">댓글달기</button>
		</form>
		
		
</div>
</body>
</html>