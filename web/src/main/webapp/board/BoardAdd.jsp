<%@ page 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판(by JSP)</title>
</head>
<body>
<h1>게시판-새 글</h1>
<form action='add.do' method='post'>
제목: <input type='text' name='title'><br>
내용: <textarea name='content' rows='5' cols='60'></textarea><br>
<button>등록</button>
<button type='reset'>초기화</button>
</form>
</body>
</html>
