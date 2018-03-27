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
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AdminLTE 2 | Data Tables</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="${url}/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="${url}/bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="${url}/bower_components/Ionicons/css/ionicons.min.css">
  <!-- DataTables -->
  <link rel="stylesheet" href="${url}/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${url}/dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="${url}/dist/css/skins/_all-skins.min.css">
  <script type="text/javascript" src="${jq}"></script>
	<script type="text/javascript" src="${parsley}"></script>

  <!-- Google Font -->
  <link rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
<script>
$(document).ready(function(){
	//setup datatables barang table
	$('#supplier-tbl').DataTable({
	      paging      : true,
	      searching   : false
	    })
	
	$('.delete').click(function(){
		var id = $(this).attr('id');
		$('#delete-id').val(id);
		$('#delete-supplier').modal();
	})
	
	$('#btn-delete').click(function(){
		var id = $('#delete-id').val()
		$.ajax({
			url : '${pageContext.request.contextPath}/master/supplier/delete/'+id,
			type : 'DELETE',
			success : function(){
				alert('delete successfully');
				window.location='${pageContext.request.contextPath}/master/supplier';
			}, error : function(){
			alert('delete failed');
				}
		})
	})
	
	$('.update').click(function(){
		var id = $(this).attr('id');
		$.ajax({
			type : 'GET',
			url : '${pageContext.request.contextPath}/master/supplier/get-one/'+id,
			dataType: 'json',
			success : function(data){
				console.log(data);
				$('#update-id').val(data.id);
				$('#update-name').val(data.name);
				$('#update-address').val(data.address);
				$('#update-postal-code').val(data.postalCode);
				$('#update-phone').val(data.phone);
				$('#update-email').val(data.email);
				$('#update-created-on').val(data.createdOn);
				var active = data.active;
				if (active==true) {
					$('#update-active').prop('checked', true);
				} else {
					$('#update-active').prop('checked', false);
				}
				$('#update-province').val(data.province.id);
				$('#update-region').val(data.region.id);
				$('#update-district').val(data.district.id);
				
				//call modal
				$('#modal-update-supplier').modal();
				
			}, 
			error : function(){
				alert('show selected supplier data in modal failed');
			}
		})
	})
	
	$('#btn-update').click(function(){
		var active = "false";
		$('#update-active input:checked').each(function(){
			active = $(this).val()
		})
		var supplier={
				id : $('#update-id').val(),
				name : $('#update-name').val(),
				address : $('#update-address').val(),
				postalCode : $('#update-postal-code').val(),
				phone : $('#update-phone').val(),
				email : $('#update-email').val(),
				province : {
					id : $('#update-province').val()
				},
				region : {
					id : $('#update-region').val()
				},
				district : {
					id : $('#update-district').val()
				},
				createdOn : $('#update-created-on').val(),
				active : active
			}
			$.ajax({
				url : '${pageContext.request.contextPath}/master/supplier/update',
				type : 'PUT',
				data : JSON.stringify(supplier),
				contentType : 'application/json',
				success : function(){
					alert('update successfully');
					window.location='${pageContext.request.contextPath}/master/supplier';
				}, error : function(){
					alert('update failed');
				}
				
			})
		})
	
	//add
	$('#create').click(function(){
		$('#modal-add-supplier').modal();
	})
	
	$('#btn-add').click(function(){
		var active = "false";
		$('#add-active input:checked').each(function(){
			active = $(this).val()
		})
		var supplier={
				name : $('#add-name').val(),
				address : $('#add-address').val(),
				postalCode : $('#add-postal-code').val(),
				phone : $('#add-phone').val(),
				email : $('#add-email').val(),
				province : {
					id : $('#add-province').val()
				},
				region : {
					id : $('#add-region').val()
				},
				district : {
					id : $('#add-district').val()
				},
				active : active
			}
		$.ajax({
			url : '${pageContext.request.contextPath }/master/supplier/save',
			type : 'POST',
			data : JSON.stringify(supplier),
			contentType : 'application/json',
			success : function(){
				alert('save successfully');
				window.location='${pageContext.request.contextPath}/master/supplier';
			}, error : function(){
				alert('save failed');
			}
			
		})
	})
	
	$('#add-province').change(function(){
		var id = $(this).val();
		if (id!=="") {
			$.ajax({
				url : '${pageContext.request.contextPath }/additional/region/get-region?id='+id,
				type : 'GET',
				success : function(data){
					var region = [];
					var reg = "<option value=\"\">Choose Region</option>";
					region.push(reg);
					$(data).each(function(index, data2){
					reg = "<option value=\""+data2.id+"\">"+data2.name+"</option>";
					region.push(reg);
					})
					$('#add-region').html(region);
				}, error : function(){
					alert('get failed');
				}
			})
		}
	});
	
	$('#add-region').change(function(){
		var id = $(this).val();
		if (id!=="") {
			$.ajax({
				url : '${pageContext.request.contextPath }/additional/district/get-district?id='+id,
				type : 'GET',
				success : function(data){
					var district = [];
					var dis = "<option value=\"\">Choose District</option>";
					district.push(dis);
					$(data).each(function(index, data2){
					dis = "<option value=\""+data2.id+"\">"+data2.name+"</option>";
					district.push(dis);
					})
					$('#add-district').html(district);
				}, error : function(){
					alert('get failed');
				}
			})
		}
	});
	
	$('#update-province').change(function(){
		var id = $('#update-province').val();
		if (id!=="") {
			$.ajax({
				url : '${pageContext.request.contextPath }/additional/region/get-region?id='+id,
				type : 'GET',
				success : function(data){
					var region = [];
					var reg = "<option value=\"\">Choose Region</option>";
					region.push(reg);
					$(data).each(function(index, data2){
					reg = "<option value=\""+data2.id+"\">"+data2.name+"</option>";
					region.push(reg);
					})
					$('#update-region').html(region);
				}, error : function(){
					alert('get failed');
				}
			})
		}
	});
	
	$('#update-region').change(function(){
		var id = $('#update-region').val();
		if (id!=="") {
			$.ajax({
				url : '${pageContext.request.contextPath }/additional/district/get-district?id='+id,
				type : 'GET',
				success : function(data){
					var district = [];
					var dis = "<option value=\"\">Choose District</option>";
					district.push(dis);
					$(data).each(function(index, data2){
					dis = "<option value=\""+data2.id+"\">"+data2.name+"</option>";
					district.push(dis);
					})
					$('#update-district').html(district);
				}, error : function(){
					alert('get failed');
				}
			})
		}
	});
	
	$('#search-btn').click(function(){
		var word = $('#search').val();
		window.location = "${pageContext.request.contextPath}/master/supplier/search?search="+word;
	})
});
</script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <header class="main-header">
    <!-- Logo -->
    <a href="#" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>A</b>LT</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>Admin</b>LTE</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </a>

      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- User Account: style can be found in dropdown.less -->
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <img src="${url}/dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
              <span class="hidden-xs">Alexander Pierce</span>
            </a>
            <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header">
                <img src="${url}/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">

                <p>
                  Alexander Pierce - Web Developer
                  <small>Member since Nov. 2012</small>
                </p>
              </li>
              <!-- Menu Body -->
              <li class="user-footer">
                <div class="pull-left">
                  <a href="#" class="btn btn-default btn-flat">Profile</a>
                </div>
                <div class="pull-right">
                  <a href="#" class="btn btn-default btn-flat">Sign out</a>
                </div>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </nav>
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="${url}/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>Alexander Pierce</p>
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>
      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu" data-widget="tree">
        <li class="treeview active">
          <a href="#">
            <i class="fa fa-table"></i> <span>Master Tables</span>
            <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="${pageContext.request.contextPath}/master/emlpoyee"><i class="fa fa-circle-o"></i> Employee</a></li>
            <li><a href="${pageContext.request.contextPath}/master/item"><i class="fa fa-circle-o"></i> Item</a></li>
            <li><a href="${pageContext.request.contextPath}/master/outlet"><i class="fa fa-circle-o"></i> Outlet</a></li>
            <li class="active"><a href="#"><i class="fa fa-circle-o"></i> Supplier</a></li>
            <li><a href="${pageContext.request.contextPath}/master/category"><i class="fa fa-circle-o"></i> Category</a></li>
          </ul>
        </li>
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h2 style="text-align: center;">
        Supplier
      </h2>
      <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/master"><i class="fa fa-dashboard"></i> Master</a></li>
        <li class="active">Supplier</li>
      </ol>
    </section>
    <section class="content-header row">
      <!-- search form -->
      <div class="col-xs-4">
        <div class="input-group ">
          <input type="text" id="search" name="q" class="form-control" placeholder="Search...">
          <span class="input-group-btn">
                <button name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </div>
      <div style="text-align: right;">
        <div class="col-xs-7">
        <a id="create" class="btn btn-primary">Create</a>
      </div>
      <div class="col-xs-1">
        <a class="btn btn-primary">Export</a>
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
              <table id="supplier-tbl" class="table table-bordered table-striped">
                <thead>
					<th>Name</th>
					<th>Address</th>
					<th>Phone</th>
					<th>Email</th>
					<th>Action</th>
				</thead>
				<tbody>
					<c:forEach items="${suppliers }" var="supplier">
						<tr>
							<td>${supplier.name }</td>
							<td>${supplier.address }</td>
							<td>${supplier.phone }</td>
							<td>${supplier.email }</td>
							<td>
						<a id="${supplier.id }" class="update btn btn-primary">Update</a> |
						<a id="${supplier.id }" class="delete btn btn-danger">Delete</a>
					</td>
				</tr>
			</c:forEach>
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
  <footer class="main-footer">
    <div class="pull-right hidden-xs">
    </div>
    <strong>Copyright &copy; 2018 <a>Mini Project Kelompok 4</a>.</strong>
  </footer>

  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->
<div>
	<%@ include file="modal/edit-supplier.jsp" %>
	<%@ include file="modal/add-supplier.jsp" %>
	<%@ include file="modal/del-supplier.html" %>
</div>

<!-- jQuery 3 -->
<script src="${url}/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="${url}/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- DataTables -->
<script src="${url}/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="${url}/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="${url}/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="${url}/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="${url}/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${url}/dist/js/demo.js"></script>
<!-- page script -->

</body>
</html>

