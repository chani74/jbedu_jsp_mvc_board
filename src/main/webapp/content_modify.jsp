<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 내용 보기</title>
</head>
<body>
<h2> 글 내용 보기 </h2>
<hr>
<form action="modify_ok.do" method=post>
<input type=hidden name=bnum value="${boardDto.bnum}">

<table border=1 cellpadding=0 cellspacing=0 width=600 >
<tr>
	<th>제목</th>
	<td><input type=text name=btitle value="${boardDto.btitle}"></td>
</tr>
<tr>
	<th>작성자</th>
	<td><input type=text name=bname value="${boardDto.bname}"></td>
</tr>
<tr>
	<th height=250>내용</th>
	<td><textarea rows="10" cols="50" name="bcontent"> ${boardDto.bcontent}</textarea></td>
</tr>

</table>
<input type=submit value="등록">
<input type=button value="취소" onClick="location.href=list.do">
</form>

</body>
</html>