<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<script>
	alert("글등록성공했습니다.");
	window.close();
	window.opener.location.href="list.do?page=1&perPageNum=10";
</script>