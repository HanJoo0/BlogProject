<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<h2 class="mt-5 mb-3">회원 목록</h2>
<div class="text-right">
	(총 ${pagination.totalCount}명 / 
	<a href="create?page=${param.page}"><i class="fa fa-user-plus"></i> 추가</a>)
</div>

<table class="table table-stripped">
	<thead>
		<tr>
			<th style="width:60">No</th>
			<th>사용자 ID</th>
			<th>이름</th>
			<th>email</th>
			<th>전화번호</th>
			<th>등록일</th>
		</tr>
	</thead>
	<tbody>
		<fmt:formatDate value="${today}" pattern="yyyy-MM-dd" 
				var="todayStr"/>
		<c:forEach var="member" items="${list}" varStatus="status">
			<fmt:formatDate value="${member.regDate}"
				pattern="yyyy-MM-dd" var="regDate"/>
			<tr>
				<td>${pagination.start + status.index}</td>
				<td>
					<a href="view/${member.userId}?page=${param.page}">
						${member.userId}</a>
					<c:if test="${todayStr==regDate}">
						<span class="badge badge-danger">
							<i class="fa fa-tag"></i>
							New</span>
					</c:if>
				</td>
				<td>${member.name}</td>
				<td>${member.email}</td>
				<td>${member.cellPhone}</td>
				<td>${regDate}</td>
			</tr>			
		</c:forEach>
	</tbody>
</table>

<%@ taglib tagdir="/WEB-INF/tags/util" prefix="iot"%>
<iot:pagination pagination="${pagination}" link="list"/>




