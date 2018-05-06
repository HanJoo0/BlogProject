<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="mt-4 mb-4">
	<h2>회원 정보</h2>
	<hr>
	
	<div class="row">
		<div class="col-md-2">사용자 ID</div>
		<div class="col-md-10">${member.userId}</div>
	</div>
	
	<div class="row">
		<div class="col-md-2">이름</div>
		<div class="col-md-10">${member.name}</div>
	</div>

	<div class="row">
		<div class="col-md-2">전화번호</div>
		<div class="col-md-10">${member.cellPhone}</div>
	</div>

	<div class="row">
		<div class="col-md-2">이메일</div>
		<div class="col-md-10">${member.email}</div>
	</div>

	<div class="row">
		<div class="col-md-2">주소</div>
		<div class="col-md-10">${member.address}</div>
	</div>

	<div class="row">
		<div class="col-md-2">가입일</div>
		<div class="col-md-10">
			<fmt:formatDate value="${member.regDate}" pattern="yyyy-MM-dd"/>
		</div>
	</div>

	<div class="row">
		<div class="col-md-2">수정일</div>
		<div class="col-md-10">
			<fmt:formatDate value="${member.updateDate}" pattern="yyyy-MM-dd"/>
		</div>
	</div>

	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-10">
			<a href="../changepassword/${member.userId}?page=${param.page}" 
				class="btn btn-primary btn-sm">비밀번호 변경</a>
			<a href="../edit/${member.userId}?page=${param.page}" 
					class="btn btn-primary btn-sm">수정</a>
			<a href="../delete/${member.userId}?page=${param.page}" 
					class="btn btn-primary btn-sm">삭제</a>	
			<a href="../list?page=${param.page}" 
					class="btn btn-primary btn-sm">
					돌아가기</a>	
		</div>
	</div>
</div>