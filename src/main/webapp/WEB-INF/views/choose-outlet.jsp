<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:url value="/j_spring_security_logout" var="logoutUrl" />
<spring:url value="/resources/adminLTE" var="url"></spring:url>
<spring:url value="/resources/js/jquery-3.3.1.js" var="jq"></spring:url>
<spring:url value="/resources/js/parsley.js" var="parsley"></spring:url>
<!DOCTYPE html>
<html>
<%@ include file="template/head.jsp" %>
<body class="hold-transition login-page">
	<!-- comment -->
	<div class="login-box">
	  <div class="login-logo">
	    <a href="../../index2.html"><b>Admin</b>LTE</a>
	  </div>
	  <!-- /.login-logo -->
	  <div class="login-box-body">
	    <p class="login-box-msg">Choose your outlet</p>
		<select class="form-control col-sm-4" name="outlet-search"
			id="outlet-search">
			<option value="kosong">Search Outlet</option>
			<c:forEach var="outlet" items="${outlets }">
				<option value="${outlet.id }">${outlet.name }</option>
			</c:forEach>
		</select>
		<input type="button" class="btn btn-primary" id="choose-outlet" value="choose"/>
	  </div>
	  <!-- /.login-box-body -->
	</div>
	<!-- /.login-box -->
</body>
<script>
$(document).ready(function(){
	$('#choose-outlet').click(function(){
		var idOutlet = $('#outlet-search').val();
		if (idOutlet!=="kosong") {
			window.location = "${pageContext.request.contextPath}/free-autentication/home?id="+idOutlet;
		}
	})
})
/* ini comment */
</script>
</html>