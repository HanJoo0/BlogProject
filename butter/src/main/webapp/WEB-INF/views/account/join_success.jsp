
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="mt-4 mb-4">
	<h2>회원 가입 완료</h2>
	<p>${member.userId}님의 회원가입이 완료되었습니다.</p>
</div>


<c:url value="/login" var="login"/>
<a href="${login}" class="btn btn-primary btn-md">
 	login
</a>


