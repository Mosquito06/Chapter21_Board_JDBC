<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
	$(function(){
		$.ajax({
			url : "article/readJson.do",
			type : "get",
			data: {"no" : 11},
			dataType : "json",
			success: function(json){
				console.log(json);
				var date = new Date(json.article.regDate);
				$("#wrap").text(date);
			}
		})
		
	})
</script>
</head>
<body>
	<div id="wrap">
		나옵니다.
	</div>
</body>
</html>