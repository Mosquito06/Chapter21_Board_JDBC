<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="../js/common.js"></script>
<style type="text/css">
	form{
		width:500px;
	}
</style>
<script type="text/javascript">
	$(function(){
		$("input[name='f1']").submit(function(){
			if(checkInputEmpty($(this).find("input[name]"))==false){
				return false;
			}
		});
	});
</script>
</head>
<body>
	<form method="post" action="update.do?no=${article.number }" name="f1">
		<p>
			<label>제목</label> 
			<input type="text" name="title" value="${article.title }">
			<span class="error">제목을 입력해 주세요.</span>
		</p>
		<p>
			<label>내용</label>
			<textarea rows="10" cols="50" name="content">${content.content }</textarea>
		</p>
		<p>
			<input type="submit" value="수정">
		</p>
	</form>
</body>
</html>