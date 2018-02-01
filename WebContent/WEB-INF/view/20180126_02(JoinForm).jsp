<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			$(".error, .error2, .error3").css("display", "none");
			
			if(checkInputEmpty($(this).find("input[name]")) == false){
				return false;
			};
			
			var pass = $(this).find("input[name='pass']").val();
			var confirmPass = $(this).find("input[name='confirmPassword']").val();
			
			if(pass != confirmPass){
				$(".error2").css("display", "block");
				return false;
			}
			
			
			
			/* if($("input[name='id']").val() == ""){
				$("input[name='id']").next(".error").css("display", "block");
				$("input[name='id']").focus();
				return false;
			}
			
			if($("input[name='name']").val() == ""){
				$("input[name='name']").next(".error").css("display", "block");
				$("input[name='name']").focus();
				return false;
			}
			
			if($("input[name='pass']").val() == ""){
				$("input[name='pass']").next(".error").css("display", "block");
				$("input[name='pass']").focus();
				return false;
			}
			
			if($("input[name='confirmPassword']").val() == ""){
				$("input[name='confirmPassword']").next(".error").css("display", "block");
				$("input[name='confirmPassword']").focus();
				return false;
			} */
		})
	})
	
	
	
</script>
</head>
<body>
	<form action="join.do" method="post" name="f1">
		<p>
			<label>아이디</label>
			<input type="text" name="id">
			<span class="error">ID를 입력하세요.</span>
			<c:if test="${duplicateId != null }">
				<span class="error3">${duplicateId }</span>
			</c:if>
			
		</p>
		<p>
			<label>이름</label>
			<input type="text" name="name">
			<span class="error">이름을 입력하세요.</span>
		</p>
		<p>
			<label>암호</label>
			<input type="password" name="pass">
			<span class="error">비밀번호를 입력하세요.</span>
		</p>
		<p>
			<label>암호 확인</label>
			<input type="password" name="confirmPassword">
			<span class="error">비밀번호 확인란을 입력하세요.</span>
			<span class="error2">비밀번호가 일치하지 않습니다.</span>
		</p>
		<p>
			<input type="submit" value="가입">
		</p>
	</form>
	
	<c:if test="${notInsert != null }">
		${ notInsert}
	</c:if>
</body>
</html>