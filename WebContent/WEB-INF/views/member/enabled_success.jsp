<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<script>
	alert("${param.id}님은 정지가되었습니다.")
	location.href="detail?id=${param.id}";
</script>