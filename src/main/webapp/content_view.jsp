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
<table border=1 cellpadding=0 cellspacing=0 width=600 >
<tr>
	<th>제목</th>
	<td>${boardDto.btitle}</td>
</tr>
<tr>
	<th>작성자</th>
	<td>${boardDto.bname}</td>
</tr>
<tr>
	<th height=250>내용</th>
	<td>${boardDto.bcontent}</td>
</tr>
<tr>
	<th>등록일</th>
	<td>${boardDto.bdate}</td>
</tr>
<tr>
	<th>조회수</th>
	<td>${boardDto.bhit}</td>
</tr>
</table>
<input type=button value="수정" onClick="location.href='modify.do?bnum=${boardDto.bnum}'">
<input type=button value="삭제" onClick="location.href='delete.do?bnum=${boardDto.bnum}'">
<input type=button value="글목록" onClick="location.href='list.do';">


</body>
</html>