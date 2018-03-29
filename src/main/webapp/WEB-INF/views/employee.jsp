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

  <%@ include file="template/template.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
  
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h2 style="text-align: center;">
        Master Employee
      </h2>
      <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/master"><i class="fa fa-dashboard"></i> Master</a></li>
        <li class="active">Employee</li>
      </ol>
    </section>
    
    <section class="content-header row">      
        <div class="col-xs-12">
      	<div class="row">
		<div class="col-md-3">
			<!-- <input type="hidden" id="insert-emp-id" name="insert-emp-id" /> -->
			<div class="form-group">
				<input type="text" class="form-control"
					id="add-firstName" placeholder="First Name">
			</div>
		</div>
		<div class="col-md-3">
			<div class="form-group">
				<input type="text" class="form-control"
					id="add-lastName" placeholder="Last Name">
			</div>
		</div>
		<div class="col-md-3">
			<div class="form-group">
				<input type="text" class="form-control" id="add-email"
					placeholder="Email">
			</div>
		</div>
		<div class="col-md-3">
			<div class="form-group">
				<select name="title" id="add-title"
					class="custom-select custom-select-md form-control">
					<option selected>Title</option>
					<option value="Mr.">Mr.</option>
					<option value="Mrs.">Mrs.</option>
				</select>
			</div>
		</div>
	</div>

		
      <div class="row">
		<div class="col-md-3">
			<div class="form-group">
				<button type="button" id="btn-assign-outlet"
					class="add-outlet btn btn-primary btn-block">Assign Outlet</button>
			</div>
		</div>
		
			<div>	
			<a id="create-account"><input type="checkbox" data-toggle="collapse" data-target="#demo"></input> Create Account?</a><br><br>
			</div>
	</div>
	
	
	 <div class="col-xs-12">
	 <div class=" row">
	   <div id="demo" class="collapse">
   		
   		<div class="col-md-3">
			<div class="form-group">
				<select name="title" id="select-role"
					class="custom-select custom-select-md form-control">
						<option value="">Role</option>
						<c:forEach items="${roles }" var="role">
							<option value="${role.id }"> ${role.name }</option>
						</c:forEach>
				</select>
			</div>
		</div>
      	
		<div class="col-md-3">
			<!-- <input type="hidden" id="insert-emp-id" name="insert-emp-id" /> -->
			<div class="form-group">
				<input type="text" class="form-control"
					id="add-username" placeholder="Username">
			</div>
		</div>
		
		<div class="col-md-3">
			<div class="form-group">
				<input type="password" class="form-control"
					id="add-password" placeholder="Password">
			</div>
		</div>
		
		</div>
	</div> <!-- end row -->	
	</div> <!-- end col-xs-12 -->
	
	
	<div>
		<a id="btn-reset" class="btn btn-danger" style="color:white;">Cancel</a>
		<a id="btn-save-emp" class="btn btn-primary" style="color:white;">Save</a>
	</div>
	
   
      
    </section> 

    <!-- Main content -->
    <section class="content"  style="background-color: ;">
      <div class="row">
        <div class="col-xs-12">
          <!-- /.box -->
          <div class="box">
            <!-- /.box-header -->
            
            
            <div class="box-body">
              <table id="emp-tbl" class="table table-bordered table-striped">
					<thead>
						<th>Name</th>
						<th>Email</th>
						<th>Have Account?</th>
						<th>Outlet Access</th>
						<th>Role</th>
						<th>Action</th>
					</thead>	
						<c:forEach items = "${employees }" var="emp">
							<tbody>
								<tr>
									<td>
										<a>${emp.title }</a>
										<a>${emp.firstName }</a>
										<a>${emp.lastName }</a>
									</td>
									<td>${emp.email }</td>
									<td>${emp.haveAccount }</td>
									<td>
											
												<c:forEach items="${emp.employeeOutlets }" var="empo">
													${empo.outlet.name }
												</c:forEach>
									
									
									</td>
									<td>${emp.user.role.name }</td>
									<td>
										<a style="color:white;" id="${emp.id }" class="edit btn btn-primary">Edit</a>
										<a style="color:white;" id="${emp.id }" class="delete btn btn-danger">X</a>
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
  
  <%@ include file="template/footer.jsp" %>

  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->
<div>
	<%@ include file="modal/edit-emp.jsp" %>
	<%@ include file="modal/del-emp.html" %>
	<%@ include file="modal/add-assign-outlet.jsp" %>
</div>



</body>

<script type="text/javascript">
$(function () {
	$('#emp-tbl').DataTable({
		searching : false
	})	
	
	$("#employee-side-option").addClass('active');
	$("#treeview-master").addClass('active');
	
	
	//add employee
	 $('#btn-save-emp').on('click', function(){
		 var haveAccount = 0;
		 $('#create-account input:checked').each(function(){
			 haveAccount = 1;
		 })
		 
		 //USERS
		 var users = [];
		 var user  = {
			 username : $('#add-username').val(),
			 password : $('#add-password').val(),
			 role : {
				 id :  $('#select-role').val(),
			 }
		 }
		 users.push(user);
		 /* console.log(user); */
		 
		 //EMPLOYEE OUTLETS
		 var employeeOutlets = [];
		 var employeeOutlet = {
			outlet : {
				id : $('input:radio[name=optRadio]:checked').val(),				
			}
		 } 
		  employeeOutlets.push(employeeOutlet);
		 /* console.log(employeeOutlet); */
		 
		 var emp = {
			title : $('#add-title').val(),
			firstName : $('#add-firstName').val(),
			lastName : $('#add-lastName').val(),
			email : $('#add-email').val(),
			haveAccount : haveAccount,
			active : 1,
			users : users,
			employeeOutlets : employeeOutlets
			 
		}
		 console.log(emp);
		 
		 
 		$.ajax({
			url : '${pageContext.request.contextPath}/master/employee/save',	
			type : 'POST',
			contentType : 'application/json',
			data : JSON.stringify(emp),
			success : function() {
				alert('save successfully!');
				 window.location = '${pageContext.request.contextPath}/master/employee'; 
			}, error : function() {
				alert('save failed!');
			}
		})  
	
	}); 
	
	
	
	
	//reset form
	$('#btn-reset').on('click', function(){
		$('#add-title').val(''),
		$('#add-firstName').val(''),
		$('#add-lastName').val(''),
		$('#add-email').val(''),
		$('#add-username').val(''),
		$('#add-password').val(''),
		$('#select-role').val(''),
		$('#create-account').prop('checked', false)
		
	});
	
	//====================================== EDIT DATA
	$('.edit').on('click', function() {
		var id = $(this).attr('id');
		
		$.ajax({
			url : '${pageContext.request.contextPath}/master/employee/get-one/'+id,
			type : 'GET',
			dataType : 'json',
			success : function (emp) {
				setEditEmp(emp);
				$('#edit-employee').modal();  //call modal
			}, error : function () {
				alert('failed getting data !');
			}
		});
		
	});
	
	//set data
	function setEditEmp(emp) {
		$('#edit-id').val(emp.id),
		$('#edit-title').val(emp.title),
		$('#edit-firstName').val(emp.firstName),
		$('#edit-lastName').val(emp.lastName),
		$('#edit-email').val(emp.email),
		$('#edit-createdOn').val(emp.createdOn)
	}
	
	//eksekusi button
	$('#btn-edit').on('click', function(){
		var emp = {
			id : $('#edit-id').val(),
			title : $('#edit-title').val(),
			firstName : $('#edit-firstName').val(),
			lastName : $('#edit-lastName').val(),
			email : $('#edit-email').val(), 
			createdOn : $('#edit-createdOn').val(),
			active : 1
		}
		
		$.ajax({
			url : '${pageContext.request.contextPath}/master/employee/update',
			type : 'PUT',
			data : JSON.stringify(emp),
			contentType : 'application/json',
			success : function () {
				alert('update successfuly!');
				window.location = '${pageContext.request.contextPath}/master/employee';
			}, error : function () {
				alert('update failed!');
			}
			
		})
		
	});
	
	//=========================================DELETE Ubah status menjadi tidak aktif(0)
	$('.delete').on('click', function() {
		var id = $(this).attr('id');
		
		$.ajax({
			url : '${pageContext.request.contextPath}/master/employee/get-one/'+id,
			type : 'GET',
			dataType : 'json',
			success : function (emp) {
				setEditEmpNonActive(emp);
				$('#delete-employee').modal();  //call modal
			}, error : function () {
				alert('failed getting data !');
			}
		});
		
	});
	
	//set data
	function setEditEmpNonActive(emp) {
		$('#edit-id').val(emp.id),
		$('#edit-title').val(emp.title),
		$('#edit-firstName').val(emp.firstName),
		$('#edit-lastName').val(emp.lastName),
		$('#edit-email').val(emp.email),
		$('#edit-createdOn').val(emp.createdOn)
	}
	
	//eksekusi button
	$('#btn-delete').on('click', function(){
		var emp = {
			id : $('#edit-id').val(),			//val() artinya dia lagi ngambil value/data dari id yang udah di-set tadi
			title : $('#edit-title').val(),
			firstName : $('#edit-firstName').val(),
			lastName : $('#edit-lastName').val(),
			email : $('#edit-email').val(), 
			createdOn : $('#edit-createdOn').val(),
			active : 0
			
		}
		
		$.ajax({
			url : '${pageContext.request.contextPath}/master/employee/update',
			type : 'PUT',
			data : JSON.stringify(emp),
			contentType : 'application/json',
			success : function () {
				alert('non-activated successfuly!');
				window.location = '${pageContext.request.contextPath}/master/employee';
			}, error : function () {
				alert('non-activated failed!');
			}
			
		})
		
	});
		
	$('.add-outlet').on('click', function(){
		$('#save-assign-outlet').modal();
	})	
	
}); /* end */
</script>

</html>