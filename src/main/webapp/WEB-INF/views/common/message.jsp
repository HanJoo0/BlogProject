<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:url value="/" var="root"/>
<div class="text-center">
	<h1 mb-4>${message.title}</h1>
	<div mb-4>
		${message.message}
	</div>
	<div mb-4>
		<a href="${root}${message.link}" class="btn btn-primary btn-md">
			${message.linkTitle}
		</a>
	</div>
</div>


