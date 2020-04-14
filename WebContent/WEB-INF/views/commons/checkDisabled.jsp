<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<script>
alert("접근이 제한되었습니다.");
if(window.opener){
	window.close();
}else{
	histroy.go(-1);
}
</script>