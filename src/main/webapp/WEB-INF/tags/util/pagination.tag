<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="pagination" required="true"
		type="edu.iot.butter.model.Pagination" %>
<%@ attribute name="link" required="true" type="String" %>
<%@ attribute name="params" required="false" type="String" %>


<ul class="pagination pagination-circle pg-blue mt-3 mb-3
			justify-content-center">
			
	<c:if test="${pagination.currentBlock > 1}">
		<li class="page-item">
			<a class="page-link" href="${link}?page=1${params}">처음</a></li>
		<a class="page-link" href="${link}?page=${pagination.prevBlockPage}${params}">◀</a>
	</c:if>
	<c:forEach begin="${pagination.startPage}" 
				end="${pagination.endPage}" var="i">
		<c:if test="${i == pagination.page}">
			<li class="page-item active"> 
				<a class="page-link">${i}</a></li>
		</c:if>
		<c:if test="${i != pagination.page}">
			<li class="page-item">
				<a class="page-link" href="${link}?page=${i}${params}">${i}</a>
			</li>
		</c:if>
	</c:forEach>		
	
	<c:if test="${pagination.currentBlock < pagination.totalBlock}">
		<li class="page-item">
			<a class="page-link" href="${link}?page=${pagination.nextBlockPage}${params}">▶</a>
		</li>
		<li class="page-item">
			<a class="page-link" href="${link}?page=${pagination.totalPage}${params}">마지막</a></li>
		
	</c:if>
	
</ul>
