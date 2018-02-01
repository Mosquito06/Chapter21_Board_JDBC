<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<th>번호</th>
			<td>${content.number }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text">${article.title}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${article.name }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea cols="10" rows="50">${content.content }</textarea></td>
		</tr>
		<tr>
			<td colspan="2">
				
				<a href="">수정하기</a>
				
			</td>
		</tr>
	</table>
</body>
</html>