<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/20180129_01(common).css">
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<style>
	table{
		border-collapse: collapse;
		width: 500px;
		text-align: center;
		
	}
	
	th, td{
		border: 1px solid black;
	}

</style>
</head>
<body>
	<c:if test="${memberList != null }">
		<table>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>가입날짜</th>
			</tr>
			<c:forEach var="member" items="${memberList }">
			<tr>
				<td>${member.id }</td>
				<td>${member.name }</td>
				<td>${member.regDate }</td>
			</tr>			
			</c:forEach>	
		</table>
	</c:if>
</body>
</html>