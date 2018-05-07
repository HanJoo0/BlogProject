<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    

<script src="<c:url value="/resources/js/rest.js"/>"></script>
<script src="<c:url value="/resources/js/gallery.js"/>"></script>
<c:url value="/api/gallery" var="apiUrl"/>

<script>
$(function(){
	$('#gallery').gallery({
		url : '${apiUrl}'
	});
});
</script>

<h1>Gallery</h1>
<hr>
<div id="gallery"></div>


