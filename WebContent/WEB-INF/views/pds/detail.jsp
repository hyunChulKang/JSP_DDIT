<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<title>상세보기</title>

<body>
  <!-- Content Wrapper. Contains page content -->
  <div  style="max-width:800px;min-width:420px;margin:0 auto;min-height:812px;">
<%--    
   	<jsp:include page="content_header.jsp">
	   	<jsp:param value="자료실" name="subject"/>
	   	<jsp:param value="list.do" name="url"/>
	   	<jsp:param value="상세보기" name="item"/>
   </jsp:include> --%>
   
    <!-- Main content -->
    <section class="content container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="card card-outline card-info">
					<div class="card-header">
						<h3 class="card-title">상세보기</h3>
					</div>
					<div class="card-body">
					<input type='hidden' name='bno' value ="${pds.pno}">
						<div class="form-group col-sm-12">
							<label for="title">제 목</label>
							<input type="text" class="form-control" id="title" 
								value="${pds.title }" readonly />							
						</div>
						<div class="row">	
							<div class="form-group col-sm-4" >
								<label for="writer">작성자</label>
								<input type="text" class="form-control" id="writer" 
									value="${pds.writer }" readonly />
							</div>		
							
							<div class="form-group col-sm-4" >
								<label for="regDate">작성일</label>
								<input type="text" class="form-control" id="regDate" 
									value="<fmt:formatDate value="${pds.regDate }" pattern="yyyy-MM-dd" />" readonly />
							
							</div>
							<div class="form-group col-sm-4" >
								<label for="viewcnt">조회수</label>
								<input type="text" class="form-control" id="viewcnt" 
									value="${pds.viewcnt }" readonly />
							</div>
						</div>		
						<div class="form-group col-sm-12">
							<label for="content">내 용</label>
							<div id="content">${pds.content }</div>	
						</div>
						<div class="form-group">								
							<div class="card card-outline card-success">
								<div class="card-header">
									<h5 style="display:inline;line-height:40px;">첨부파일  </h5>
								</div>									
								<div class="card-footer fileInput">
								 <c:forEach items="${pds.attachList }" var="attach">
								 <div class="col-md-4 col-sm-4 col-xs-12" style="cursor: pointer;" onclick="location.href='<%=request.getContextPath() %>/attach/getFile.do?pno=${pds.pno }&ano=${attach.ano }';">
								 <div class="info-box">
									<span class="info-box-icon bg-red">
										<i class="fa fa-copy"></i>
									</span>
									<div class="info-box-content">
										<span class="info-box-text">
											<fmt:formatDate value="${attach.regDate }" pattern="yyyy-MM-dd"/>
										</span>
										<span class="info-box-number">${attach.fileName }</span>
									</div>
									<%-- <input type="button" value="${attach.fileName }"><br> --%>
									</div>
								</div>
								</c:forEach>
								</div>
							</div>
						</div>					
					</div>
					<div class="card-footer">
						<c:if test="${loginUser.id eq pds.writer }">
						<button type="button" id="modifyBtn" class="btn btn-warning">MODIFY</button>						
					    <button type="button" id="removeBtn" class="btn btn-danger">REMOVE</button>
					    </c:if>
					    <button type="button" id="listBtn" class="btn btn-primary">CLOSE</button>
					</div>									
				</div><!-- end card -->				
			</div><!-- end col-md-12 -->
		</div><!-- end row  -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  
  <form role="form">
  	<input type='hidden' name='pno' value ="${pds.pno}">
  	<input type='hidden' name='page' value ="${pageMaker.cri.page }">
  	<input type='hidden' name='perPageNum' value ="${pageMaker.cri.perPageNum }">
  	<input type='hidden' name='searchType' value ="${pageMaker.cri.searchType }">
  	<input type='hidden' name='keyword' value ="${pageMaker.cri.keyword }">
  	
  	
  </form>
	<jsp:include page="detail_js.jsp"></jsp:include>
	
 <%--	<%@ include file="reply_js.jsp" %> 
	 --%>
		
</body>