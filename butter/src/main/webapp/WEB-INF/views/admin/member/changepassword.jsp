<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script>
$(function(){
	$('#password').submit(function(e) {
		var password1 = $('#newPassword').val();
		var password2 = $('#newPassword2').val();
		
		if(password1 != password2) {
			alert('비밀번호 확인이 일치하지 않습니다.');
			e.preventDefault();
		}		
	});
});
</script>

<div class="login-form">
	<h1 class="pb-4"><i class="fa fa-sign-in-alt"></i> 
			비밀번호 변경</h1>

	<form:form commandName="password">
		<form:hidden path="userId"/>
		<div class="md-form">
			<label for="oldPassword">관리자 비밀번호</label>
			<form:password path="oldPassword"/>
			<form:errors path="oldPassword" />
		</div>

		<div class="md-form">
			<label for="newPassword">새 비밀번호</label>
			<form:password path="newPassword"/>
			<form:errors path="newPassword" />
		</div>

		<div class="md-form">
			<label for="newPassword2">새 비밀번호 확인</label>
			<input type="password" id="newPassword2" />
		</div>
		
		<form:errors element="div" cssClass="error"/>
		
		<div class="md-form text-center">
			<button type="submit" class="btn btn-primary">
				<i class="fa fa-check"></i> 확인
			</button>
			<a href="../view/${password.userId}?page=${param.page}" 
					class="btn btn-primary">돌아가기</a>	
		</div>
	</form:form>
</div>
