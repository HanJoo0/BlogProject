<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script src="<c:url value="/resources/js/rest.js"/>"></script>
<c:url value="/api/gallery" var="apiUrl"/>


<script>
//var api = new Rest('${apiUri}');
$(function(){
//	api.list('', function(images){
//		console.log(images);
//	})
	
	$('#gallery').gallery({
		url : '${apiUrl}'
	})
	
	
})

</script>


<body>
<h1>Gallery</h1>
<hr>
<div id="gallery"></div>

</body>
