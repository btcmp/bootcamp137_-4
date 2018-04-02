<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<spring:url value="/resources/adminLTE" var="url"></spring:url>
<spring:url value="/resources/js/jquery-3.3.1.js" var="jq"></spring:url>
<spring:url value="/resources/js/parsley.js" var="parsley"></spring:url>
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
        Purchase Request
      </h2>
      <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/transaction"><i class="fa fa-dashboard"></i> Transaction</a></li>
        <li class="active">Purchase Request</li>
      </ol>
    </section>
    <section class="content-header row">
      <!-- search form -->
      <label for="select-outlet-main">Outlet Login</label>
      <select class="selectpicker" name="select-outlet-main" id="select-outlet-main">
				<c:forEach var="outlet" items="${outlets }">
					<option value="${outlet.id }">${outlet.name }</option>
				</c:forEach>
			</select>
      <div class="col-xs-4">
        <div class="input-group ">
          <input type="text" id="search" name="q" class="form-control" placeholder="Search...">
          <span class="input-group-btn">
                <button name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i></button>
                
          </span>
          
        </div>
      </div>
      <div style="text-align: right;">
      <div class="col-xs-7">
      	<div class="center">
      	
      	</div>
      	<button id="create"  class="btn btn-primary">Create</button>
      	
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
              <table id="table-view-pr" class="table table-bordered table-striped">
                <thead>
					<th>Created Date</th>
					<th>PR No.</th>
					<th>Notes </th>
					<th>Status </th>
					<th>#</th>
				</thead>
				<tbody id= "table-view-pr-body">
					<%-- <c:forEach items="${variant }" var="var">
						<tr>
							<td>${var.item.name } --- <b>${var.name }</b> </td>
							<td>${var.item.category.name }</td>
							<td>${var.price}</td>
							<td>
								<a id="${var.item.id }" class="update-item btn btn-primary">Edit</a> |
								<a id="${var.item.id }" class="delete btn btn-danger">View</a>
							</td>
						</tr>
					</c:forEach> --%>
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
		<%@include file="modal/add-purchase-request.jsp" %>
	</div>
	<%@ include file="template/footer.jsp"%>

<script type="text/javascript" src="<c:url value="/resources/js/purchase-request.js" />" charset="utf-8"></script></html>

