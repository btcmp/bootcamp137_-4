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
				<h2 style="text-align: center;">Main Menu</h2>
				<%-- <ol class="breadcrumb">
					<li><a href="${pageContext.request.contextPath}/master"><i
							class="fa fa-dashboard"></i> Master</a></li>
					<li class="active">Transfer-stock</li>
				</ol> --%>
			</section>

			<!-- Main content -->
			<section class="content" style="background-color:;">
				<div class="row welcome">
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
				<!-- Small boxes (Stat box) -->
			      <div class="row dashboard">
			        <div class="col-lg-3 col-xs-6">
			          <!-- small box -->
			          <div class="small-box bg-aqua">
			            <div class="inner">
			              <h3>${jmlAdj }<sup style="font-size: 20px">New</sup></h3>
			
			              <p>Adjustment</p>
			            </div>
			            <div class="icon">
			              <i class="ion ion-archive"></i>
			            </div>
			            <a href="${pageContext.request.contextPath}/transaction/adjustment" class="small-box-footer">Go to Adjustment <i class="fa fa-arrow-circle-right"></i></a>
			          </div>
			        </div>
			        <!-- ./col -->
			        <div class="col-lg-3 col-xs-6">
			          <!-- small box -->
			          <div class="small-box bg-green">
			            <div class="inner">
			              <h3>${jmlTs }<sup style="font-size: 20px">New</sup></h3>
			
			              <p>Transfer Stock</p>
			            </div>
			            <div class="icon">
			              <i class="ion ion-share"></i>
			            </div>
			            <a href="${pageContext.request.contextPath}/transaction/transfer-stock" class="small-box-footer">Go to Transfer Stock <i class="fa fa-arrow-circle-right"></i></a>
			          </div>
			        </div>
			        <!-- ./col -->
			        <div class="col-lg-3 col-xs-6">
			          <!-- small box -->
			          <div class="small-box bg-yellow">
			            <div class="inner">
			              <h3>${jmlPr }<sup style="font-size: 20px">New</sup></h3>
			
			              <p>Purchase Request</p>
			            </div>
			            <div class="icon">
			              <i class="ion ion-clipboard"></i>
			            </div>
			            <a href="${pageContext.request.contextPath}/transaction/purchase-request" class="small-box-footer">Go to Purchase Request <i class="fa fa-arrow-circle-right"></i></a>
			          </div>
			        </div>
			        <!-- ./col -->
			        <div class="col-lg-3 col-xs-6">
			          <!-- small box -->
			          <div class="small-box bg-red">
			            <div class="inner">
			              <h3>${jmlPo }<sup style="font-size: 20px">New</sup></h3>
			
			              <p>Purchase Order</p>
			            </div>
			            <div class="icon">
			              <i class="ion ion-android-cart"></i>
			            </div>
			            <a href="${pageContext.request.contextPath}/transaction/purchase-order" class="small-box-footer">Go to Purchase Order <i class="fa fa-arrow-circle-right"></i></a>
			          </div>
			        </div>
			        <!-- ./col -->
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
	var userRole = "${userLogin.role.name}";
	if (userRole!=="ROLE_ADMIN") {
		$('.dashboard').hide();
	}
});
</script>
</html>