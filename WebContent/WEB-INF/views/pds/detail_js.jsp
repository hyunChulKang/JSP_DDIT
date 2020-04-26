<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<script>
	$('#modifyBtn').on('click',function(e){
		location.href="modifyForm.do?pno=${pds.pno}"
	});
	$('#removeBtn').on('click',function(e){
		location.href="remove.do?pno=${pds.pno}";
	});
	$("#listBtn").on("click", function(){
		window.close();
		window.opener.location.reload(true);
	});
</script>