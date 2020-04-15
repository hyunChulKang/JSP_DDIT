<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<script>
	alert("${member.id} 회원을 삭제했습니다. ");
	window.close();
	window.opener.location.reload(true);
</script>