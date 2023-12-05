<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
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
<title>Insert title here</title>
</head>
<body>
<div class="container">
	<h1>home</h1>
	<h2>${fn:substring(loginMemberId, 0, 3)}** 님 환영합니다</h2>
	<a class="btn btn-outline-success" href="${path}/notice/noticeHome">게시판홈으로</a>
	<a class="btn btn-outline-warning" href="${path}/member/logout">로그아웃</a>
	<a class="btn btn-outline-info" href="${path}/member/modifyMember"><b>비밀번호수정</b></a>
	<a class="btn btn-outline-danger" href="${path}/member/removeMember"><b>회원탈퇴</b></a>
	<br>
	
	<br><br><br>
	<!-- 캘린더 -->
	<div>
		<h2>${calendarMap.targetYear}년
		${calendarMap.targetMonth+1}월</h2>
	</div>
	<div>
		<a href="${path}/member/home?targetYear=${calendarMap.targetYear}&targetMonth=${calendarMap.targetMonth-1}">이전달</a>
		<a href="${path}/member/home?targetYear=${calendarMap.targetYear}&targetMonth=${calendarMap.targetMonth+1}">다음달</a>
	</div>
	
	<table border="1" width="900" height="600" class="table-bordered">
		<tr>
		<c:forEach var="i" begin="1" end="${calendarMap.totalTd}">
			<c:set var="d" value="${i- calendarMap.beginBlank}"/>
			<td>
			
				<c:if test="${d<1 || d>calendarMap.lastDate}">
					&nbsp;
				</c:if>
				
				<c:if test="${!(d<1 || d>calendarMap.lastDate)}">
						<c:if test="${i<calendarMap.totalTd && i%7==1}">
						<a style="color:red;" href="${path}/schedule/scheduleListByDay?ttargetYear=${calendarMap.targetYear}&targetMonth=${calendarMap.targetMonth+1}&toDay=${d}">
							${d}
						</a>
						<div>
							<c:forEach var="m" items="${list}">
								<c:if test="${m.scheduleDay == d }">
									<div>${m.cnt} 개의 일정</div>
									<div>${m.memo}</div>
								</c:if>
							
							</c:forEach>
						</div>
						</c:if>
						<c:if test="${i<calendarMap.totalTd && i%7==0}">
						<a style="color:blue;" href="${path}/schedule/scheduleListByDay?targetYear=${calendarMap.targetYear}&targetMonth=${calendarMap.targetMonth+1}&toDay=${d}">
							${d}
						</a>
						<div>
							<c:forEach var="m" items="${list}">
								<c:if test="${m.scheduleDay == (d) }">
									<div>${m.cnt} 개의 일정</div>
									<div>${m.memo}</div>
								</c:if>
							</c:forEach>
						</div>
						</c:if>
						<c:if test="${i<calendarMap.totalTd && i%7 > 1}">
						<a style="color:black;" href="${path}/schedule/scheduleListByDay?targetYear=${calendarMap.targetYear}&targetMonth=${calendarMap.targetMonth+1}&toDay=${d}">
							${d}
						</a>
						<div>
							<c:forEach var="m" items="${list}">
								<c:if test="${m.scheduleDay == (d) }">
									<div>${m.cnt} 개의 일정</div>
									<div>${m.memo}</div>
								</c:if>
							
							</c:forEach>
						</div>
						</c:if>
						
						
					</c:if>
					
				<!-- 한 행에 7열씩 출력하도록 <tr>추가 -->
				<c:if test="${i<calendarMap.totalTd && i%7 == 0}">
				</tr><tr>
				</c:if>
				
			</td>
		</c:forEach>
		</tr>
	</table>
	
</div>
</body>
</html>