<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<link rel="stylesheet" href="<c:url value="/resources/css/talk.css"/>"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.js"></script>
<script src="<c:url value="/resources/js/talk.js"/>"></script>
<script src="<c:url value="/resources/js/rest.js"/>"></script>
<script>

$(function(){
	$('#messages').talk({
		userId : '${USER.userId}',			// 사용자 ID
		withTalk : '${param.withTalk}',		// 대화 상대방 ID
		url : '/butter/talk', 				// 웹 소켓 url
		sendBtn : $('#send-btn'),			// 전송 버튼 
		sendMessage : $('#send-message'),// 전송 메시지 엘리먼트
	});
});
</script>

<div class="m-4 mx-auto" style="width:500px">
	<div class="card border-primary mb-3" >
		<div class="card-header">
			<h3>
				<i class="fa fa-comments primary"></i> ${param.withTalk}
			</h3>
		</div>
		
		<div class="card-body scroll" style="height:20rem; overflow : scroll">
			<div id="messages"></div>
		</div>
	
		<div class="card-footer py-2">
			<div class="md-form input-group my-0">
    			<input type="text" id="send-message" class="form-control my-0 py-0 ">
    			<span class="input-group-btn">
        			<button id="send-btn" class="my-0 btn btn-primary btn-sm">
        				<i class="fa fa-paper-plane"></i>
        			</button>
			    </span>
			</div>
		</div>
	</div>
</div>

