<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="js/20180129_01(common).js"></script>
<link rel="stylesheet" type="text/css" href="css/20180129_01(common).css">
<script>
	$(function(){
		$("form[name='f1']").submit(function(){
			if(checkInputEmpty($(this).find("input[name]")) == false){
				return false;	
			}
		})
	})

</script>
</head>
<body>
	<form action="login.do" method="post" name="f1">
		<p>
			<label>아이디</label>
			<input type="text" name="id">
			<span class="error">아이디를 입력하세요.</span>
		</p>
		<p>
			<label>비밀번호</label>
			<input type="password" name="password">
			<span class="error">비밀번호를 입력하세요.</span>
		</p>
		<p>
			<input type="submit" value="로그인">
		</p>	
	</form>
	<c:if test="${notJoin != null }">
		<span class="error3">${notJoin}</span>
	</c:if>
	<c:if test="${passNotMatch != null }">
		<span class="error3">${passNotMatch}</span>
	</c:if>
	
</body>
</html>