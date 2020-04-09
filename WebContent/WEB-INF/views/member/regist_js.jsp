<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<script>
	$('#registBtn').on('click',function(e){
		var uploadCheck = $('input[name="checkUpload"]').val();
		if(!(uploadCheck>0)){
			alert("업로드한 사진이 없습니다.")
			//$('input[name="pictureFile"]').click();
			return;
		}
		var form = $('form[role="form"]');
		form.submit();
	});
</script>