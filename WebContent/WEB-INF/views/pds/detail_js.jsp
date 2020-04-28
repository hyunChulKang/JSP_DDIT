<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<script>
	var formObj = $("form[role='form']");

	$('#modifyBtn').on('click',function(e){
		formObj.attr("action", "modifyForm.do");
		formObj.attr("method", "post");
		formObj.submit();
	});
	$('#removeBtn').on('click',function(e){
		formObj.attr("action", "remove.do");
		formObj.attr("method", "post");
		formObj.submit();
	});
	$("#listBtn").on("click", function(){
		window.close();
		window.opener.location.reload(true);
	});
</script>