<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="login-form">
	<c:if test="${not empty login.url}">
		<div class="mt-4 mb-4">
			<h4>
				로그인이 필요한 서비스입니다. 로그인 하세요.
			</h4>
		</div>
	</c:if>

	<h1 class="pb-4"><i class="fa fa-sign-in-alt"></i> 로그인</h1>

	<form:form commandName="login">
		<input type="hidden" name="url" value="${url}">
		<div class="md-form">
			<label for="userId">사용자 ID</label>
			<form:input path="userId" 
				class="form-control" />
		</div>
		<div class="md-form">
			<label for="password">비밀번호</label>
			<form:password path="password" 
				class="form-control" />
		</div>
		<c:if test="${not empty error}">
			<div class="error" >
				${error}
			</div>
		</c:if>
		
		<div  class="md-form text-center">
			<button type="submit" class="btn btn-primary">
				<i class="fa fa-check"></i> 확인
			</button>
		</div>
	</form:form>
</div>
