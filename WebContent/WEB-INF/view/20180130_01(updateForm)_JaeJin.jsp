<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="update.do">
		<p>
			<label>기존 비밀번호</label>
			<input type="text" name="pw">
		</p>
		<p>
			<label>변경 비밀번호</label>
			<input type="text" name="newPw">
		</p>
		<input type="submit" value="변경">
	</form>
</body>
</html>