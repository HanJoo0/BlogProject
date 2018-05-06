<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script src="<c:url value="/resources/js/rest.js"/>"></script>
<script src="<c:url value="/resources/js/reply.templ.js"/>"></script>
<script src="<c:url value="/resources/js/reply.js"/>"></script><script>
<c:url value="/api/reply/${board.boardId}" var="apiUrl"/>
var api = new Rest('${apiUrl}');
$(function() {
	var opt ={
		 api : api,
		 boardId : ${board.boardId},
		 writer : '${USER.userId}',
		 replyList : $('#reply-list')
	 }; 
	 
	$('#reply-board').replyBoard(opt);
	$('#reply-list').replyList(opt);
});
</script>

<h2 class="mt-5 mb-4">게시글 보기</h2>

<div class="row">
	<div class="col-md-2">제목</div>
	<div class="col-md-10">${board.title}</div>	
</div>	
<div class="row">
	<div class="col-md-2">작성자</div>
	<div class="col-md-10">${board.writer}</div>
</div>
<div class="row">
	<div class="col-md-2">조회수</div>
	<div class="col-md-10">${board.readCnt}</div>	
</div>	

<div class="row">
	<div class="col-md-2">첨부파일</div>
	<div class="col-md-10">
		<c:forEach var="file" items="${board.attachments}">
			<div>
				<a href="../download/${file.attachmentId}">
					<i class="fa fa-download"></i> ${file.fileName}
				</a>
			</div>
		</c:forEach>	
	</div>	
</div>		
<div class="row">
	<div class="col-md-2">작성일</div>
	<div class="col-md-10">
		<fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd"/>
	</div>	
</div>
<div class="row">
	<div class="col-md-2">수정일</div>
	<div class="col-md-10">
		<fmt:formatDate value="${board.updateDate}" pattern="yyyy-MM-dd"/>
	</div>	
</div>
<hr/>
<div>
	${board.content}
</div>

<c:if test="${not empty USER.userId}">
	<div id="reply-board"></div>
</c:if>

<div id="reply-list"></div>


<div class="text-center mt-4">
	<c:if test="${USER.userId == board.writer }">
		<a href="../edit/${board.boardId}" class="mr-4"><i class="fa fa-edit mr-2"></i> 수정</a>
		<a href="../delete/${board.boardId}" class="mr-4"><i class="fa fa-trash mr-2"></i> 삭제</a>
	</c:if>
	<a href="javascript:history.back()"><i class="fa fa-undo mr-2"></i> 돌아가기</a>
</div>
