<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.6/handlebars.js" ></script>
<script type="text/x-handlebars-template" id="reply-list-template">
{{#each .}}
<div class="replyLi" data-rno={{rno}}>
	<i class="fas fa-comments bg-yellow"></i>
 	<div class="timeline-item" >
  		<span class="time">
    		<i class="fa fa-clock"></i>{{prettifyDate regdate}}
	 		<a class="btn btn-primary btn-xs" id="modifyReplyBtn"
	    		data-replyer={{replyer}} data-toggle="modal" data-target="#modifyModal">Modify</a>
  		</span>
	
  		<h3 class="timeline-header"><strong style="display:none;">{{rno}}</strong>{{replyer}}</h3>
  		<div class="timeline-body">{{replytext}} </div>
	</div>
</div>
{{/each}}
</script>
<script>


Handlebars.registerHelper("prettifyDate",function(timeValue){
	var dateObj = new Date(timeValue);
	var year=dateObj.getFullYear();
	var month=dateObj.getMonth()+1;
	var date=dateObj.getDate();
	return year+"/"+month+"/"+date;
});





	var replyPage=1;

	getPage("<%=request.getContextPath()%>/replies/list.do?bno=${board.bno}&page="+replyPage);
	
	<%--스크립트 자체도 태그이기때문에  handlebars-template의 id값을 가져온다.--%>
	var printData= function(replyArr,target,templateObject){
		var template=Handlebars.compile(templateObject.html());
		var html =template(replyArr);
		$('.replyLi').remove();
		target.after(html); 
	}
	//reply list
function getPage(pageInfo){	
	$.getJSON(pageInfo,function(data){
		printData(data.replyList,$('#repliesDiv'),$('#reply-list-template'));
		printPaging(data.pageMaker,$('.pagination'));
		if(data.pageMaker.realEndPage>0){
			realEndPage=data.pageMaker.realEndPage;
		}
	});
}
	var printPaging=function(pageMaker,target){
		var str="";
		if(pageMaker.prev){
			str+="<li class='page-item'><a class='page-link' href='"+(pageMaker.startPage-1)+"'> <i class='fas fa-angle-left'/></a></li>";
		}	
		for(var i=pageMaker.startPage;i<=pageMaker.endPage;i++){
			var strClass = pageMaker.cri.page==i?'active':'';
			str+="<li class='page-item "+strClass+"'><a class='page-link' href='"+i+"'>"+i+"</a></li>";
		}
		if(pageMaker.next){
			str+="<li class='page-item'><a class='page-link' href='"+(pageMaker.endPage+1)+"'> <i class='fas fa-angle-right'/></a></li>";
		}
		target.html(str);
	}
	//페이지 네이션
	$('.pagination').on('click','li a',function(event){
		event.preventDefault();
		replyPage=$(this).attr("href");
		getPage("<%=request.getContextPath()%>/replies/list.do?bno=${board.bno}&page="+replyPage);
	});
	
	//댓글 등록
	$('#replyAddBtn').on('click',function(e){
		var replyer=$('#newReplyWriter').val();
		var replytext=$('#newReplyText').val();
		
		if(replytext==""){
			alert('댓글 내용은 필수 입니다.');
			$('#newReplyText').focus().css("board-color","red").attr("placeholder","내용은 필수 입니다.");
			return;
		}
		var data={
				"bno":"${board.bno}",
				"replyer":replyer,
				"replytext":replytext
		}
		
		$.ajax({
			url:"<%=request.getContextPath()%>/replies/regist.do",
			type:"post",
			data:JSON.stringify(data),	
			contentType:"application/json", //보내는 data 형식 지정
			dataType:"text", //받는 data 형식 지정
			
			success:function(data){
				var result=data.split(',');
				if(result[0]=="SUCCESS"){
					alert('댓글이 등록되었습니다.');	
					getPage("<%=request.getContextPath()%>/replies/list.do?bno=${board.bno}&page="+result[1]);
					$('#newReplyText').val("");
				}else{
					alert('댓글 등록이 취소되었습니다.');
				}	
			}
		});
	});
	
	//reply modify 권한체크
	$('div.timeline').on('click','#modifyReplyBtn',function(event){	
		//로그인 사용자 확인	
		var replyer=$(event.target).attr("data-replyer");
		if(replyer!="${loginUser.id}"){
			alert("수정 권한이 없습니다.");
			$(this).attr("data-toggle","");
		}
	});	
	
	//수정창에 data 표시
	$('div.timeline').on('click','.replyLi',function(event){
		var reply=$(this);
		$('#replytext').val(reply.find('.timeline-body').text());
		$('.modal-title').html(reply.attr('data-rno'));
	});	

	
	$('#replyModBtn').on('click',function(event){
		var rno =$('.modal-title').text();
		var replytext=$('#replytext').val();
		
		var sendData={
				rno:rno,
				replytext:replytext
		}
	
		$.ajax({
			url:"<%=request.getContextPath()%>/replies/modify.do",
			type:"post",
			data:JSON.stringify(sendData),
			contentType:"application/json", //보내는 data 형식 지정
			dataType:"text", //받는 data 형식 지정
			success:function(result){
				if(result == "SUCCESS"){
					alert("수정완료");
					getPage("<%=request.getContextPath()%>/replies/list.do?bno=${board.bno}&page="+replyPage);
					$('#newReplyText').val("");
				}else{
					alert("수정실패");
				}
				
			},
			complete:function(){
				$('#modifyModal').modal('hide');
			}
		});
	
	});	
	
		$('#replyDelBtn').on('click',function(event){
			var rno = $('.modal-title').text();
			$.ajax({
				url:"<%=request.getContextPath()%>/replies/remove.do?rno="+rno,
				type:'post',
				success:function(result){
					if(result == "SUCCESS"){
						alert("삭제성공");
						getPage("<%=request.getContextPath()%>/replies/list.do?bno=${board.bno}&page="+replyPage);
						$('#newReplyText').val("");
					}else{
						alert("삭제실패");
					}
				},
				complete:function(){
					$('#modifyModal').modal('hide');
				}
			})
			
		});	
</script>