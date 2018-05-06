<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script>
$(function(){
	$('#check-btn').click(function(e) {
		e.preventDefault();
		// 중복체크
		var userId = $('#userId').val();
		if(userId == '') {
			alert('사용자 ID를 입력하세요');
			return;
		}
		
		<c:url value="/idcheck" var="idcheck"/>
		$.get('${idcheck}' , {userId : userId} ,
				function(data) {
			console.log(data)
			if(!data) {	// 사용자 ID 중복 아님
				$(':submit').prop('disabled', false);
				$('#check-message').removeClass('error')
					.html('사용 가능한 ID 입니다.')
			
			} else {	// 사용자 ID 중복
				$(':submit').prop('disabled', true);
				$('#check-message').addClass('error')
					.html('이미 사용 중인 ID 입니다.')				
			}
		});
	});
	
	$('#join-form').submit(function(e) {
		// 비밀번호 일치 검사
		var password1 = $(':password').eq(0).val();
		var password2 = $(':password').eq(1).val();

		if(password1 != password2) {
			alert('비밀번호가 일치하지 않습니다.')
			$(':password').eq(0).focus();
			e.preventDefault();	
		}
	});
	
});
</script>

<div id="join-form" class="join-form">
	<h1 class="pb-4"><i class="fa fa-user-plus"></i> 회원가입</h1>
	<form:form commandName="member">
		<div class="md-form">
			<label for="userId">사용자 ID</label>
			<form:input path="userId"/>
			<form:errors path="userId"/>
		</div>

		<div class="md-form">
			<a id="check-btn" 
				class="btn-floating btn btn-primary btn-sm ">중복 체크</a>
			<span id="check-message"></span>
		</div>

		<div class="md-form">
			<label for="name">이름</label>
			<form:input path="name"/>
			<form:errors path="name"/>
		</div>

		<div class="md-form">
			<label for="password">비밀번호</label>
			<form:password path="password"/>
			<form:errors path="password"/>
		</div>

		<div class="md-form">
			<label for="password2">비밀번호 확인</label>
			<input type="password" name="password2" id="password2" 
				class="form-control" required>
		</div>
		
		<div class="md-form">
			<label for="cellPhone">전화번호</label>
			<form:input path="cellPhone"/>
			<form:errors path="cellPhone"/>
		</div>
				
		<div class="md-form">
			<label for="email">email</label>
			<form:input path="email"/>
			<form:errors path="email"/>
		</div>

		<div class="md-form">
			<label for="address">주소</label>
			<form:input path="address"/>
		</div>
			
		<form:errors element="div" cssClass="error"/>
		
		<div  class="md-form text-center">
			<button type="submit" class="btn btn-primary" disabled>
				<i class="fa fa-check"></i> 확인
			</button>
			<a href="list?page=${param.page}" class="btn btn-primary" >
				<i class="fa fa-list"></i> 목록
			</a>
		</div>
	</form:form>
</div>




