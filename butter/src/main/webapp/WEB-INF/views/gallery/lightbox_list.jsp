<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table class="table">
<tbody>
<c:forEach var="image" items="${list}" varStatus="status">
	<tr>
		<td style="width:100px">
			<a href="image/${image.imageId}" data-lightbox="roadtrip">
				<img src="thumb/${image.imageId}"
						width="100" 
						alt="${image.title}"
					 class="z-depth-2 rounded"/> 
			</a>
		</td>
		<td>		
			<p>
				<b>${image.title}</b>
				<a href="download/${image.imageId}">
					<i class="fa fa-download"></i>
				</a>
				(등록일 : <fmt:formatDate value="${image.regDate}" pattern="yyyy-MM-dd"/>)
			</p>
			<p>${image.description}</p>
			
		</td>
	</tr>
</c:forEach>
</tbody>
</table>
</div>





