<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
      <h2 style="text-align: center;">
        Purchase Order
      </h2>
      <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/transaction"><i class="fa fa-dashboard"></i> Transaction</a></li>
        <li class="active">Purchase Order</li>
      </ol>
    </section>
    <section class="content-header row">
      <!-- search form -->
      <label for="select-outlet-main">Outlet Login</label>
      <select disabled class="selectpicker" name="select-outlet-main" id="select-outlet-main">
				<%-- <c:forEach var="outlet" items="${outlets }">
					<option value="${outlet.id }">${outlet.name }</option>
				</c:forEach> --%>
				<option value="${outlet.id }">${outlet.name }</option>
			</select>
      <div class="col-xs-4">
        <div class="input-group ">
          <input type="text" id="search" name="q" class="form-control" placeholder="Search...">
          <span class="input-group-btn">
                <button name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i></button>
          </span>
          
        </div>
      </div>
      <div class="col-xs-2">
		    <div class="form-group">
		    	<select id="select-search-by-status" class="form-control">
		    		<option value="All">All</option>
		    		<option value="Created">Created</option>
		    		<option value="Submitted">Submitted</option>
		    		<option value="Approved">Approved</option>
		    		<option value="Rejected">Rejected</option>
		    		<option value="Processed">Processed</option>
		    	</select>
		    </div>
	    </div>
      <div style="text-align: right;">
      <div class="col-xs-5">
      	<div class="center">
      	
      	</div>
      	<button id="create"  class="btn btn-primary invisible">Create</button>
      	
	  </div>
	  <div class="col-xs-1">
	  	<a id="btn-export"class="btn btn-primary">Export</a>
	  </div>
      </div>
    </section>


    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <!-- /.box -->

          <div class="box">
            <!-- /.box-header -->
            <div class="box-body">
              <table id="table-view-po" class="table table-bordered table-striped">
                <thead>
					<th>Created Date</th>
					<th>Supplier </th>
					<th>PO No.</th>
					<th>Total</th>
					<th>Status </th>
					<th>#</th>
				</thead>
				<tbody id= "table-view-po-body">
					
				</tbody>
            </table>
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
<div class = "container">
		
		<%@include file="modal/edit-purchase-order.jsp" %>
	</div>
	
	<%@ include file="template/footer.jsp"%>
	<script type="text/javascript" src="<c:url value="/resources/js/purchase-order.js" />" charset="utf-8"></script>
</div>
</body>
</html>


