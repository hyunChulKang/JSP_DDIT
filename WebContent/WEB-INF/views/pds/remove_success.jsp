<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<script>
	alert("글삭제 성공!!");
	window.close();
	opener.parent.searchList_go=(${pageMaker.cri.page}"<%=request.getContentType()%>/pds/list.do");
</script>