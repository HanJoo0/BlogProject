<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!--Navbar-->
<nav class="navbar navbar-expand-lg navbar-dark primary-color">

	<!-- Navbar brand -->
	<a class="navbar-brand" href="/butter">Butter</a>

	<!-- Collapse button -->
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#basicExampleNav" aria-controls="basicExampleNav"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<!-- Collapsible content -->
	<div class="collapse navbar-collapse" id="basicExampleNav">

		<!-- Links -->
		<ul class="navbar-nav mr-auto">
			<li class="nav-item"><a class="nav-link"
				href="${root}gallery/lightbox"> <i class="fa fa-images"></i>
					Gallery
			</a></li>
			<li class="nav-item"><a class="nav-link"
				href="${root}gallery/api_view"> <i class="fa fa-images"></i>
					Gallery2
			</a></li>
			<li class="nav-item"><a class="nav-link"
				href="${root}gallery/flickr"> <i class="fa fa-images"></i>
					Flickr
			</a></li>
			<li class="nav-item"><a class="nav-link"
				href="${root}board/list"> <i class="fa fa-list"></i> 게시판
			</a></li>
			<c:if test="${USER.grade==0}">
				<li class="nav-item"><a class="nav-link"
					href="${root}admin/member/list"><i class="fa fa-images"></i>
						회원관리</a></li>
			</c:if>


		</ul>
		<!-- Links -->

		<!-- Links -->
		<ul class="navbar-nav">
			<c:choose>
				<c:when test="${empty USER}">
					<li class="nav-item"><a class="nav-link" href="${root}login"><i
							class="fa fa-sign-in-alt"></i> 로그인</a></li>
					<li class="nav-item"><a class="nav-link" href="${root}join"><i
							class="fa fa-user-plus"></i> 회원가입</a></li>
				</c:when>
				<c:otherwise>
					<li class="nav-item"><a class="nav-link"
						href="${root}member/profile"> <img
							src="${root}member/avata?userId=${USER.userId}"
							class="rounded-circle avata-small"> ${USER.userId}
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${root}talk/home"> <i class="fa fa-comments"></i> <c:if
								test="${newTalks>0}">
								<span class="badge badge-pill pink"> ${newTalks} </span>
							</c:if>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="${root}logout"><i
							class="fa fa-sign-out-alt"></i> 로그아웃</a></li>

				</c:otherwise>
			</c:choose>

		</ul>
	</div>
	<!-- Collapsible content -->

</nav>
<!--/.Navbar-->
