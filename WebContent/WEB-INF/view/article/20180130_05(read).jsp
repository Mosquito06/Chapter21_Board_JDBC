<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
	$(function(){
		$("a").eq(2).click(function(){
			deleteSelect = confirm("정말로 삭제하시겠습니까?");
			if(!deleteSelect){
				return false;
			}
		})
	})
</script>
<style type="text/css">
table {
	border-collapse: collapse;
}

th, td {
	border: 1px solid black;
}
th{
	width:100px;
	text-align: center;
}
td{
	width:200px;
	padding-left:20px;
}
tr:last-child td{
	text-align:center;
}
</style>
</head>
<body>
	<table>
		<tr>
			<th>번호</th>
			<td>${content.number }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${article.title}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${article.name }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${content.content }</td>
		</tr>
		<tr>
			<td colspan="2">
				<a href="list.do">목록보기</a>
				<c:if test="${auth.id == article.id }">
					<a href="update.do?no=${content.number }">수정하기</a>
					<a href="delete.do?no=${content.number }">삭제하기</a>
				</c:if>
			</td>
		</tr>
	</table>
</body>
</html>