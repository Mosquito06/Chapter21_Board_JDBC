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
<script src="js/20180129_01(common).js"></script>
<script>
	$(function(){
		$("form[name='f1']").submit(function(){
			if(checkInputEmpty($(this).find("input[name]")) == false){
				return false;	
			}
						
			var pass = $("input[name='changePass']").val();
			var passComfirm = $("input[name='changePassConfirm']").val();
			
			if(pass != passComfirm){
				alert("변경할 비밀번호가 일치하는지 확인해주세요.");
				$("input[name='changePassConfirm']").val("");
				$("input[name='changePassConfirm']").focus();
				return false;
			}
			
		})
	})

</script>
</head>
<body>
	<form action="password.do" method="post" name="f1">
		<p>
			<label>현재 비밀번호</label>
			<input type="password" name="currentPass">
			<span class="error">현재 비밀번호를 입력하세요.</span>
			<c:if test="${notCorrect != null }">
				<span class="error3">${notCorrect }</span>
			</c:if>
		</p>
		<p>
			<label>비밀번호 변경</label>
			<input type="password" name="changePass">
			<span class="error">변경할 비밀번호를 입력하세요.</span>
		</p>
		<p>
			<label>비밀번호 확인</label>
			<input type="password" name="changePassConfirm">
			<span class="error">변경할 비밀번호를 입력하세요.</span>
		</p>
		<p>
			<input type="submit" value="변경">
		</p>	
	</form>
</body>
</html>