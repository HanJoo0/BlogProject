<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="row">
<c:forEach var="image" items="${list}" varStatus="status">
	<div class="text-center col-md-3 col-sm-4">
		<a href="image/${image.imageId}" data-lightbox="roadtrip">
			<img src="thumb/${image.imageId}"
					width="150" 
					alt="${image.title}"
				 class="z-depth-2 rounded"/> 
		</a>
		<p>
			<a href="download/${image.imageId}">
				<i class="fa fa-download"></i>
				${image.title}
			</a>
		</p>
	</div>
</c:forEach>
</div>





