<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:url value="/j_spring_security_logout" var="logoutUrl" />
<spring:url value="/resources/adminLTE" var="url"></spring:url>
<spring:url value="/resources/js/jquery-3.3.1.js" var="jq"></spring:url>
<spring:url value="/resources/js/parsley.js" var="parsley"></spring:url>
<c:url value="/j_spring_security_logout" var="logoutUrl" />
<!DOCTYPE html>
<html>
<%@ include file="template/head.jsp" %>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<%@ include file="template/template.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">

			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h2 style="text-align: center;">Transfer Stock</h2>
				<ol class="breadcrumb">
					<li><a href="${pageContext.request.contextPath}/master"><i
							class="fa fa-dashboard"></i> Master</a></li>
					<li class="active">Transfer-stock</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content" style="background-color:;">
				<div class="row">
					<div class="col-xs-12">
						<!-- /.box -->
						<div class="box">
							<!-- /.box-header -->

							<div class="box-body">
							<p>Welcome ${employee.firstName} ${employee.lastName}</p>
							<p>Oulet : ${outlet.name}</p>
							<p></p>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<%@ include file="template/footer.jsp"%>

		<div class="control-sidebar-bg"></div>
	</div>
	<!-- ./wrapper -->

</body>
<script>
$(document).ready(function(){
	
});
</script>
</html>