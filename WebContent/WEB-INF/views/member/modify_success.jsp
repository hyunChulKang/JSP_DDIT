<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>

<script>
	alert("${param.id}님 회원정보수정이 정상적으로 완료되었습니다.")
	location.href ="/member/detailForm.do?id=${param.id}";
	window.close();
	window.opener.location.reload(true);
</script>