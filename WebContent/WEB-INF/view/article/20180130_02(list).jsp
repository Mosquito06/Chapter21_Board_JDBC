<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	response.setHeader("Cache-Control", "no-cache");
	response.addHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "No-cache");
	response.setDateHeader("Expires", 1L);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table {
	border-collapse: collapse;
	text-align: center;
}

td, th {
	border: 1px solid black;
}

th:nth-child(2) {
	width: 300px; 
}
#container p{
	margin-left:370px;
}
</style>
</head>
<body>
	<c:if test="${param.deleteMessage == 1 }">
		<script>
			alert("삭제가 완료되었습니다.");
		</script>
	</c:if>
	
	<c:if test="${param.deleteMessage == 2 }">
		<script>
			alert("삭제하지 못했습니다.");
		</script>
	</c:if>
	
	<c:if test="${updateResult != null }">
		<script>
			alert("${updateResult}");
		</script>
	</c:if>


	<div id="container">
		<p>
			<a id="goWrite" href="write.do">[글쓰기]</a>
			<a href="${pageContext.request.contextPath}/20180129_01(index).jsp">[메인으로 돌아가기]</a>
		</p>
		<table>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성 날짜</th>
				<th>읽은 횟수</th>
			</tr>
			<c:forEach var="item" items="${list }">
				<tr>
					<td>${item.number }</td>
					<td class="title"><a href="read.do?no=${item.number}">${item.title }</a></td>
					<td>${item.name }</td>
					<td><fmt:formatDate value="${item.regDate }" pattern="yyyy.MM.dd h:mm"/></td>
					<td>${item.readCount }</td>
				</tr>
			</c:forEach>
		</table>	
	</div>
</body>
</html>