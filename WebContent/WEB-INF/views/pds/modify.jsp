<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
	<title>수정페이지</title>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/summernote/summernote-bs4.css">
</head>
<body>
  <!-- Content Wrapper. Contains page content -->
<%--   <div >
   <jsp:include page="content_header.jsp">
   	<jsp:param value="자유게시판" name="subject"/>
   	<jsp:param value="수정" name="item"/>
   	<jsp:param value="modifyForm.do" name="url"/>
   </jsp:include>
 --%>
    <!-- Main content -->
    <section class="content container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="card card-outline card-info">
					<div class="card-header row">
						<h4 class="col-md-8">글수정</h4>
						<div class="col-md-4">
							<div class="float-right">
								<button type="button" class="btn btn-warning" id="modifyBtn">수 정</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button" class="btn btn-default" id="cancelBtn">취 소</button>
							</div>
						</div>
					</div><!--end card-header  -->
					<div class="card-body">
						<form enctype="multipart/form-data" role="form" method="post" action="modify.do" name="modifyForm">
							<input type="hidden" name="pno" value="${pds.pno }" />
							<div class="form-group">
								<label for="title">제 목</label> 
								<input type="text" id="title" value="${pds.title }"
									name='title' class="form-control" placeholder="제목을 쓰세요">
							</div>
							<div class="form-group">
								<label for="writer">작성자</label> 
								<input type="text" id="writer" readonly
									name="writer" class="form-control" value="${pds.writer }">
							</div>
							<div class="form-group">
								<label for="content">내 용</label>
								<textarea class="form-control" name="content" id="content" rows="3"
									placeholder="500자 내외로 작성하세요.">${pds.content }</textarea>
							</div>
							<div class="form-group">								
								<div class="card card-outline card-success">
									<div class="card-header">
										<h5 style="display:inline;line-height:40px;">첨부파일 : </h5>
										&nbsp;&nbsp;<button class="btn btn-xs btn-primary" 
										type="button" id="addFileBtn">Add File</button>
									</div>									
									<div class="card-footer fileInput">
							<%-- 		<c:set var="size" value="${fn:length(pds.attachList)}" /> --%>
									<%-- <c:if test="${!empty pds.attachList }" >
										<c:forEach var="pds" items="${pds.attachList}" >
										<div class="inputRow">
											<input type="text" role="cnt" name="uploadFile" style="display: inline; border: none;background: #f7f7f7;" value="${pds.fileName}" />
											<button style='border:0;outline:0;' class="badge bg-red" type="button">삭제</button>
											<hr>
										</div>
										</c:forEach>
									</c:if> --%>
									</div>
								</div>
							</div>
						</form>
					</div><!--end card-body  -->
					
				</div><!-- end card -->				
			</div><!-- end col-md-12 -->
		</div><!-- end row -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
	
<!-- Summernote -->
<%@ include file="/WEB-INF/views/commons/summernote.jsp" %>
	<jsp:include page="attach_js.jsp" /> 
<script>
	$(function(){
		$('#content').summernote({
	
			placeholder:'여기에 내용을 적으세요.',
			height:250,		
		});
	});
	
	$('#modifyBtn').on('click',function(e){				
		var title=$('input[name="title"]');
		if(title.val()==""){
			alert("제목은 필수입니다.");			
			title.focus();
			return;
		}
		var form=document.modifyForm;		
		form.submit();
	});
	
	$('#cancelBtn').on('click',function(e){
		history.go(-1);
	});
</script>
</body>






  