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
    <!-- FORM EMPLOYEE -->   
        <div class="col-xs-12">
<%--          <form id="form-all-employee">  --%>
      	<form id="form-add-employee" class="row" >
		<div class="col-md-3">
			<div class="form-group">
				<input type="text" class="form-control"
					id="add-firstName" placeholder="First Name" required="">
			</div>
		</div>
		<div class="col-md-3">
			<div class="form-group">
				<input type="text" class="form-control"
					id="add-lastName" placeholder="Last Name" required>
			</div>
		</div>
		<div class="col-md-3">
			<div class="form-group">
				<input type="email" class="form-control" id="add-email"
					placeholder="Email" required>
			</div>
		</div>
		<div class="col-md-3">
			<div class="form-group">
				<select name="title" id="add-title"
					class="custom-select custom-select-md form-control" required>
					<option value="">Title</option>
					<option value="Mr.">Mr.</option>
					<option value="Mrs.">Mrs.</option>
				</select>
			</div>
		</div>
	</form>

	<!-- BUTTON ASSIGN OUTLET -->	
	<div class="row" >
      <div>
		<div class="col-md-3">
			<div class="form-group">
				<button type="button" id="btn-assign-outlet"
					class="add-outlet btn btn-primary btn-block">Assign Outlet</button>
			</div>
		</div>
	</div> 

		
		
		<!-- CHECK BOX - CREATE ACCOUNT? -->
	  <form id="form-create-account" >
 		<div class="col-md-3 row">	
			<input type="checkbox" id="create-account" name="create-account" ></input> Create Account?</a><br><br>
		</div> 		
	  </form>

</div>
	
	<!-- FORM USER -->
	<form id="form-add-user" class="row" style="display: none">
		<div class="col-md-3">
			<div class="form-group">
				<select name="role" id="add-role" class="form-control custom-select custom-select-md" placeholder="Role" required>
					<option value="">Role</option>
					<c:forEach var="role" items="${roles }">
						<option value="${role.id }">${role.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="col-md-3">
			<div class="form-group">
				<input type="text" class="form-control" id="add-username"
					placeholder="Username" required>
			</div>
		</div> 
		<div class="col-md-3">
			<div class="form-group">
				<input type="password" class="form-control" id="add-password"
					placeholder="Password" required>
			</div>
		</div>
 		<%-- </form> --%> <!-- end form-all -->
 	</form>

	<!-- BUTTON ACTION -->	
	<div style="float: right;">
		<a id="btn-reset" class="btn btn-danger" style="color:white;">Cancel</a>
		<a id="btn-save-emp" class="btn btn-primary" style="color:white;">Save</a>
	</div>
	
</div> 
    </section> 

    <!-- Main content -->
    <section class="content"  style="background-color: ;">
	<div><h3><b>Staff List</b></h3></div>
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
										<a>${emp.firstName } ${emp.lastName }</a>
									</td>
									<td>${emp.email }</td>
									<%-- <td>${emp.haveAccount }</td> --%>
									<td><center>
										<script type="text/javascript">
											if ("${emp.haveAccount }" === "true") {
												document.write("&#10004;");
											}
										</script>
									</center></td>
									<td>
											
												<c:forEach items="${emp.employeeOutlets }" var="empo">
													<li>${empo.outlet.name }</li>
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
	<%@ include file="modal/edit-assign-outlet.jsp" %>
</div>



</body>

<script type="text/javascript">
$(function () {
	$('#emp-tbl').DataTable({
		searching : false
	})	
	
	$("#employee-side-option").addClass('active');
	$("#treeview-master").addClass('active');
	
	
//========================================================================================
//ADD DATA EMPLOYEE
	 $('#btn-save-emp').on('click', function(){
		var idUser = "${employee.user.id}";
//=========================================
//HAVE ACCOUNT?		 
		var haveAccounts  = $('#create-account').is(':checked') ? true : false;
		
//=========================================		 
//USERS
		 var users  = {
			 username : $('#add-username').val(),
			 password : $('#add-password').val(),
			 role : {
				 id :  $('#add-role').val()
			 },
			createdBy : {
				id : idUser
			},
			modifiedBy : {
				id : idUser
			},
			active : 1
		}	
/* console.log(user); */
		 
//=========================================
//EMPLOYEE OUTLETS
		 var employeeOutlets = [];
		 $('#list-add-outlet input:checked').each(function() {
				
			 var employeeOutlet = {
						outlet : {
							id : $(this).val()
						}
					 } 
					  employeeOutlets.push(employeeOutlet);
		});
/* console.log(employeeOutlet); */
   
//=========================================
//EMPLOYEE		 
		 var emp = {
			title : $('#add-title').val(),
			firstName : $('#add-firstName').val(),
			lastName : $('#add-lastName').val(),
			email : $('#add-email').val(),
			haveAccount : haveAccounts,
			user : users,
			employeeOutlets : employeeOutlets,
			createdBy : {
				id : idUser
			},
			modifiedBy : {
				id : idUser
			},
			active : 1
		}
		 console.log(emp);
		 		
//validasi parsley data gak boleh kosong 
			var okEmp = $('#form-add-employee').parsley().validate();
			var okOutlet = false;
			if (employeeOutlets.length > 0) {
				okOutlet = true
			}
			var okAccount = $('#create-account').is(':checked') ? true : false;
			var okUser = false;
			if (okAccount) {
				okUser = $('#form-add-user').parsley().validate();			
			}
			 console.log("okEmp : "+okEmp+", okOutlet : "+okOutlet+", okAccount : "+okAccount+", okUser : "+okUser)


	 $.ajax({
				url : '${pageContext.request.contextPath}/master/employee/get-all',	
				type : 'GET',
				success : function(data) {
					console.log(data);
					var sameName = 0;
					var sameEmail = 0;
					$(data).each(function(index, data2) {
						
						if (data2.user !== null && data2.email !== null) {
							if (data2.user.username !== null) {
								if (emp.user.username.toLowerCase() == data2.user.username.toLowerCase()) {
									sameName++;
								} else if (emp.email.toLowerCase() == data2.email.toLowerCase()){
									sameEmail++;
								}		
							}				
					}
					})
					
					if (sameName > 0) {
						alert('this name has been taken, please change!')
					} else if (sameEmail > 0) {
						alert('this email has been taken, please change!')
					}else if (!okEmp) {
						alert('fix employee data input !')
					}else if (okAccount && !okUser) {
						alert('fix user data input !')
					}else if (okUser && !okOutlet) {
						alert('please assign outlet !')
					}else {  
// 					alert('saved');  
  				 			$.ajax({
							url : '${pageContext.request.contextPath}/master/employee/save',	
							type : 'POST',
							contentType : 'application/json',
							data : JSON.stringify(emp),
							success : function() {
								alert('Save Successfully!');
				 				 window.location = '${pageContext.request.contextPath}/master/employee';  
							}, error : function() {
								alert('Save Failed!');
							}
						});       
												
 					 }
					
				}, error : function() {
					alert('get failed!');
				}
			});	 
	}); 
	
	
	
//=========================================
//RESET FORM		 	
	$('#btn-reset').on('click', function(){
		$('#add-title').val(''),
		$('#add-firstName').val(''),
		$('#add-lastName').val(''),
		$('#add-email').val(''),
		$('#add-username').val(''),
		$('#add-password').val(''),
		$('#add-role').val(''),	
		$('input[name="create-account"]').prop('checked', false),
		$('#form-add-user').fadeOut('fast')
		
	});
	
//========================================================================================
//EDIT DATA
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
		console.log(emp);
		$('#edit-id-emp').val(emp.id),
		$('#edit-title').val(emp.title),
		$('#edit-firstName').val(emp.firstName),
		$('#edit-lastName').val(emp.lastName),
		$('#edit-email').val(emp.email),
		$('#edit-createdOn-emp').val(emp.createdOn)
		$('#edit-createdBy-emp').val(emp.createdBy.id)
 		if(emp.haveAccount==true){
			$('#form-edit-user').show();
        	$('#edit-account').prop('checked',true);
    	} else {
			$('#form-edit-user').hide();
    		$('#edit-account').prop('checked',false);
    	}
    	
     	if(emp.user != null){
           	$('#edit-id-user').val(emp.user.id);
     		$('#edit-role').val(emp.user.role.id);
        	$('#edit-username').val(emp.user.username);
        	$('#edit-password').val(emp.user.password);
      		$('#edit-createdOn-user').val(emp.user.createdOn);
    		$('#edit-createdBy-user').val(emp.user.createdBy.id);  
     	
     	}
    	
      	$.each(emp.employeeOutlets, function(index, empOutlet){
    		$.each($('#list-edit-outlet > tr > td > input[type="checkbox"]'), function(){
    			if($(this).attr('id') == empOutlet.outlet.id){
    				
    				$(this).prop('checked', true);
    			}
    		});
    	});
      	
	}
	
	//eksekusi button
 	$('#btn-edit-emp').on('click', function(){
 		var idUser = "${employee.user.id}";		
		var haveAccounts  = $('#edit-account').is(':checked') ? true : false;
		
	  var employeeOutlets = [];
		 $('#list-edit-outlet input:checked').each(function() {
				
			 var employeeOutlet = {	
					 outlet : {
							id : $(this).attr('id')
						}
					 } 
					  employeeOutlets.push(employeeOutlet);
		}); 
 
  		
 		 var users  = {
 				 id : $('#edit-id-user').val(),
				 username : $('#edit-username').val(),
				 password : $('#edit-password').val(),
				 role : {
					 id :  $('#edit-role').val()
				 },
				createdOn : $('#edit-createdOn-user').val(),
				createdBy : {
					id : idUser
				},
				modifiedBy : {						
					id : idUser
				},
				active : 1
			}	
  		
		var emp = {
			id : $('#edit-id-emp').val(),
			title : $('#edit-title').val(),
			firstName : $('#edit-firstName').val(),
			lastName : $('#edit-lastName').val(),
			email : $('#edit-email').val(), 
			createdOn : $('#edit-createdOn-emp').val(),
			createdBy : {
				id : idUser
			},
			modifiedBy : {
				id : idUser
			},
 			user : users, 			
 			employeeOutlets : employeeOutlets,  
			haveAccount : haveAccounts,
			active : 1
		}
		
		console.log(emp)

		//validasi parsley data gak boleh kosong 
			var okEmpEdit = $('#form-edit-employee').parsley().validate();
			var okOutletEdit = false;
			if (employeeOutlets.length > 0) {
				okOutletEdit = true
			}
			var okAccountEdit = $('#edit-account').is(':checked') ? true : false;
			var okUserEdit = false;
			if (okAccountEdit) {
				okUserEdit = $('#form-edit-user').parsley().validate();			
			}
			 console.log("okEmpEdit : "+okEmpEdit+", okOutletEdit : "+okOutletEdit+", okAccountEdit : "+okAccountEdit+", okUserEdit : "+okUserEdit)		
		
		
		//validasi nama dan email sama 
			 $.ajax({
				url : '${pageContext.request.contextPath}/master/employee/get-all',	
				type : 'GET',
				success : function(data) {
					console.log(data);
					var sameName = 0;
					var sameEmail = 0;
					$(data).each(function(index, data2) {
						if (parseInt(data2.id)!==parseInt(emp.id)) {
							if (data2.user !== null && data2.email !== null) {
								if (data2.user.username !== null) {
									if (emp.user.username.toLowerCase() == data2.user.username.toLowerCase()) {
										sameName++;
									} else if (emp.email.toLowerCase() == data2.email.toLowerCase()){
										sameEmail++;
									}		
								}				
							}
						}
					})
					
					if (sameName > 0) {
						alert('this name has been taken, please change !')
					} else if (sameEmail > 0) {
						alert('this email has been taken, please change !')
					}else if (!okEmpEdit) {
						alert('fix employee data input !')
					}else if (okAccountEdit && !okUserEdit) {
						alert('fix user data input !')
					}else if (okUserEdit && !okOutletEdit) {
						alert('please assign outlet !')
					}else {  
 					//alert('saved');  
					$.ajax({
						url : '${pageContext.request.contextPath}/master/employee/update',
						type : 'PUT',
						data : JSON.stringify(emp),
						contentType : 'application/json',
						success : function () {
							alert('Update Successfuly!');
							window.location = '${pageContext.request.contextPath}/master/employee';
						}, error : function () {
							alert('Update Failed!');
						}
						
					});												
 					 }
					
				}, error : function() {
					alert('get failed!');
				}
			});	 		
												
 
	});
	
	
//========================================================================================
//DELETE Ubah status menjadi tidak aktif(0)		
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
		$('#edit-id-emp').val(emp.id),
		$('#edit-title').val(emp.title),
		$('#edit-firstName').val(emp.firstName),
		$('#edit-lastName').val(emp.lastName),
		$('#edit-email').val(emp.email),
		$('#edit-createdOn').val(emp.createdOn)
	}
	
	//eksekusi button
	$('#btn-delete').on('click', function(){
		var emp = {
			id : $('#edit-id-emp').val(),			//val() artinya dia lagi ngambil value/data dari id yang udah di-set tadi
			title : $('#edit-title').val(),
			firstName : $('#edit-firstName').val(),
			lastName : $('#edit-lastName').val(),
			email : $('#edit-email').val(), 
			createdOn : $('#edit-createdOn').val(),
			active : 0
			
		}
		
		$.ajax({
			url : '${pageContext.request.contextPath}/master/employee/update-delete',
			type : 'PUT',
			data : JSON.stringify(emp),
			contentType : 'application/json',
			success : function () {
				alert('Delete Successfuly!');
				window.location = '${pageContext.request.contextPath}/master/employee';
			}, error : function () {
				alert('Delete Failed!');
			}
			
		})
		
	});
	
//==================================
//BUTTON ADD OUTLET dari modal add-assign-outlet	
	 $('#btn-add-outlet').on('click', function(){
	    	var employeeOutlets=[];
	    	$('#list-add-outlet').find('input[type="checkbox"]:checked').each(function(){
	    		employeeOutlets.push({id : $(this).attr('id')});
	    	});
	    	$('#btn-save-emp').attr('employeeOutlets',JSON.stringify(employeeOutlets));
	    	$('#save-assign-outlet').modal('hide');
	    });	
//==================================
//BUTTON EDIT OUTLET dari modal edit-assign-outlet	
	 $('#btn-edit-outlet').on('click', function(){
	    	var employeeOutlets=[];
	    	$('#list-edit-outlet').find('input[type="checkbox"]:checked').each(function(){
	    		employeeOutlets.push({id : $(this).attr('id')});
	    	});
	    	$('#btn-edit-emp').attr('employeeOutlets',JSON.stringify(employeeOutlets));
	    	$('#save-assign-outlet').modal('hide');
    	
	    	$('#edit-employee').modal();
	    	$('#edit-assign-outlet').modal('hide');
	    });
		
//==================================
//MODAL ASSIGN-OUTLET - add outlet
	$('.add-outlet').on('click', function(){
		$('#save-assign-outlet').modal();
	})	
	
//==================================
//MODAL ASSIGN-OUTLET - edit outlet
	$('.edit-outlet').on('click', function(){
		$('#edit-assign-outlet').modal();
	})	
	
	
//==================================
//CHECK BOX (SHOW - HIDE) - CREATE ACCOUNT	
	$('#create-account').val('false');
	$('#create-account').change(function() {
		if (this.checked) {
			$('#form-add-user').show(1000);           //1000 milidetik= 1detik,  maksudnya waktu transisi dalam milidetik
			$('#create-account').val('true');
		} else {
			$('#form-add-user').hide(1000);
			$('#create-account').val('false');
			$('#add-username').val('');
			$('#add-password').val('');
			$('#add-role').val('');
		}
	});
	
//==================================
//CHECK BOX (SHOW - HIDE) - EDIT ACCOUNT	
$('#edit-account').on('click',function(){
		if(this.checked){
			$('#form-edit-user').show(1000);
			$('#edit-account').val('true');
		} else {
			$('#form-edit-user').hide(1000);
			$('#edit-account').val('false');
		}
    });
	
	
	
}); /* end */
</script>

</html>