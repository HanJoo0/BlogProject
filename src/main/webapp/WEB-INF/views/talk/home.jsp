<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet" href="<c:url value="/resources/css/chat.css"/>"/>
<div class="m-4">
	<h2><i class="fa fa-comments"></i> Talk</h2>
	<hr/>
	<div class="row" >
		<!-- 멤버 리스트 -->
		<div class="col-md-6">
			<c:forEach var="member" items="${memberList}">		
				<div class="media my-1">
				    <img class="d-flex mr-3 rounded-circle avata" 
				    	src="${root}member/avata?userId=${member.userId}">
				    	
				    <div class="media-body">
						<a href="with?userId=${USER.userId}&withTalk=${member.userId}">
							${member.userId}
							<c:if test="${member.newMessages>0}">
								<span class="badge badge-pill" 
									style="background:tomato">
									${member.newMessages}
								</span>
							</c:if> 
						</a>
				    </div>
				</div>		
			</c:forEach>
		</div>
		<!-- talk 리스트 -->
		<div class="col-md-6">
			<c:forEach var="talk" items="${talkList}">		
				<div class="media my-1">
				    <img class="d-flex mr-3 rounded-circle avata" 
				    	src="${root}member/avata?userId=${member.userId}">
				    <div class="media-body talk-message">
						<a href="with?userId=${USER.userId}&withTalk=${talk.withTalk}">
							<div style="overflow:hidden">
								<div class="font-weight-bold float-left">${talk.withTalk}</div>
								<div class="small float-right">
									<fmt:formatDate value="${talk.regDate}" 
											pattern="yyyy-MM-dd hh:mm:ss"/>
								</div>
							</div>
							<div>${talk.message }</div>
						</a>
				    </div>
				</div>		
			</c:forEach>			
			
		</div>
	</div>
</div>
