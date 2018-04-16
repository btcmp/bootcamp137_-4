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
        Master Outlet
      </h2>
      <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/master"><i class="fa fa-dashboard"></i> Master</a></li>
        <li class="active">Outlet</li>
      </ol>
    </section>
    
    <section class="content-header row">
      <!-- search form -->
      <div class="col-xs-4">
        <div class="input-group ">
          <input type="text" id="search" name="q" class="form-control" placeholder="Search...">
          <span class="input-group-btn">
                <button name="search" id="btn-search" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </div>
      <div style="text-align: right;">
        <div class="col-xs-8">
        <a id="create-outlet" class="btn btn-primary col-xs-3" style="float:right;">Create</a>
      </div>
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
              <table id="outlet-tbl" class="table table-bordered table-striped">
				<thead>
					<th>Name</th>
					<th>Address</th>
					<th>Phone</th>
					<th>Email</th>
					<th>Action</th>
				</thead>
				<tbody>
				<c:forEach items="${outlets }" var="outlet">
					<tr>
						<td>${outlet.name }</td>
						<td>${outlet.address }</td>
						<td>${outlet.phone }</td>
						<td>${outlet.email }</td>
						<td>
							<a id="${outlet.id }" class="edit btn btn-success" style="color:white;">Edit</a>
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
	<%@ include file="modal/add-outlet.jsp" %>
	<%@ include file="modal/edit-outlet.jsp" %>
</div>



</body>

<script type="text/javascript">
	$(function() {
		//data-tabel
		$('#outlet-tbl').DataTable({
			searching:false	
		});
		
		$("#outlet-side-option").addClass('active');
		$("#treeview-master").addClass('active');
		
//================================================== ADD DATA
	$('#create-outlet').on('click', function() {
		$('#save-outlet').modal();   //call modal
	});
	
	//execute button add/save
	$('#btn-entry').on('click', function() {
		var formAddOutlet = $('#form-add-outlet').parsley().validate();
		var idUser = "${employee.user.id}";
		var out = {
			name : $('#entry-name').val(),
			address : $('#entry-address').val(),
			phone : $('#entry-phone').val(),
			email : $('#entry-email').val(),
			province : {
				id : $('#entry-province').val()
			},
			region : {
				id : $('#entry-region').val()
			},
			district : {
				id : $('#entry-district').val()
			},
			postalCode : $('#entry-postalCode').val(),
			active : 1,
			createdBy : {
				id : idUser
			},
			modifiedBy : {
				id : idUser
			}
		}
		
		if (formAddOutlet == true) {  //validasi parsley
  			$.ajax({
				url : '${pageContext.request.contextPath }/master/outlet/get-all',
				type : 'GET',
				success : function(data){
					var sameName = 0;
					var sameEmail = 0;
					$(data).each(function(index, data2){
						if (out.name.toLowerCase() == data2.name.toLowerCase()) {
							sameName++;
						} else if (out.email.toLowerCase() == data2.email.toLowerCase()) {
							sameEmail++;
						}
					})
					if (sameName>0) {
						alert("This name has been taken, please change it!");
					} else if (sameEmail>0) {
						alert("This email has been used, please change it!");
					} else { 
 							$.ajax({
								url : '${pageContext.request.contextPath}/master/outlet/save',
								type : 'POST',
								contentType : 'application/json',
								data : JSON.stringify(out),
								success : function() {
									alert('save successfully');
									window.location = '${pageContext.request.contextPath}/master/outlet';
								}, error : function() {
									alert('save failed!');
								}
							});
 	 				}
				}, error : function(){
					alert('get all outlet failed');
				}
			})  
		} //end validasi parsley 
		
	});
	
	//===add province, region, district
	$('#entry-province').change(function(){
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
					$('#entry-region').html(region);
				}, error : function(){
					alert('get failed');
				}
			})
		}
	});
	
	$('#entry-region').change(function(){
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
					$('#entry-district').html(district);
				}, error : function(){
					alert('get failed');
				}
			})
		}
	});
//================================================== UPDATE DATA	
	$('.edit').on('click', function(){
		var id = $(this).attr('id');
		
		$.ajax({
			url : '${pageContext.request.contextPath}/master/outlet/get-one/'+id,
			type : 'GET',
			dataType : 'json',
			success : function(outlet) {
				setEditOutlet(outlet);
				$('#edit-outlet').modal();  //call modal
			}, error : function () {
				alert('failed getting data!');
			}
		});
	});
	
	//set upd data update
	function setEditOutlet(outlet){
		$('#edit-id').val(outlet.id);
		$('#edit-name').val(outlet.name);
		$('#edit-address').val(outlet.address);
		$('#edit-phone').val(outlet.phone);
		$('#edit-email').val(outlet.email);
		$('#edit-postalCode').val(outlet.postalCode);
		$('#edit-createdOn').val(outlet.createdOn);
		$('#edit-createdBy').val(outlet.createdBy.id);
		
		$('#edit-province').val(outlet.province.id);
		updateProvince(outlet.province.id, outlet.region.id);    // (1) updateProvince di eksekusi saat set Update
		updateRegion(outlet.region.id, outlet.district.id);
		
	}

	
	//eksekusi button update/edit
	$('#btn-edit').on('click', function() {
		var formEditOutlet = $('#form-edit-outlet').parsley().validate();
		var idUser = "${employee.user.id}";
		var outlet = {
			id : $('#edit-id').val(),
			name : $('#edit-name').val(),
			address : $('#edit-address').val(),
			phone : $('#edit-phone').val(),
			email : $('#edit-email').val(),
			province : {
				id : $('#edit-province').val()
			},
			region : {
				id : $('#edit-region').val()
			},
			district : {
				id : $('#edit-district').val()
			},
			postalCode : $('#edit-postalCode').val(),
			createdOn : $('#edit-createdOn').val(),
			createdBy : {
				id : $('#edit-createdBy').val(),
			},
			modifiedBy : {
				id : idUser
			},
			active : 1
		}
/* 		console.log(outlet); */

 		if (formEditOutlet == true) { 
			
			$.ajax({
				url : '${pageContext.request.contextPath }/master/outlet/get-all',
				type : 'GET',
				success : function(data){
					var sameName = 0;
					var sameEmail = 0;
					$(data).each(function(index, data2){
						if (parseInt(data2.id)!==parseInt(outlet.id)) {
							if (outlet.name.toLowerCase()==data2.name.toLowerCase()) {
								sameName++;
							} else if (outlet.email.toLowerCase()==data2.email.toLowerCase()) {
								sameEmail++;
							}
						}
					})
					if (sameName>0) {
						alert("This name has been taken, please change it!");
					} else if (sameEmail>0) {
						alert("This email has been used, please change it!");
					} else {
						
						$.ajax({
							url : '${pageContext.request.contextPath}/master/outlet/update',
							type : 'PUT',
							data : JSON.stringify(outlet),
							contentType : 'application/json',
							success : function () {
								alert('update successfully');
								window.location='${pageContext.request.contextPath}/master/outlet';
							}, error : function() {
								alert('updated failed')
							}
						});
						
		 			 }
				}, error : function() {
					alert('get all outlet failed');
				}
			});	
		}
	
	});
	
	
	//edit-data province, region, district
	$('#edit-province').change(function(){          
		var id = $('#edit-province').val();
		var id2 = "";
		updateProvince(id, id2)         // (2) updateProvince di eksekusi saat ganti provinsi
	});
	
	function updateProvince(id, id2) {
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
					$('#edit-region').html(region);
					$('#edit-region').val(id2);
					
					if (id2=="") {
						var district = [];
						var dis = "<option value=\"\">Choose District</option>";
						district.push(dis);
						$('#edit-district').html(district);
					}
				}, error : function(){
					alert('get failed');
				}
			})
		}

	}
	
	
	$('#edit-region').change(function(){
		var id = $('#edit-region').val();
		updateRegion(id); 
	});
	
	function updateRegion(id, id2) {
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
					$('#edit-district').html(district);
					$('#edit-district').val(id2);
					
					
				}, error : function(){
					alert('get failed');
				}
			})
		}
	}
	
	
	//delete
/* 	$('.delete').on('click', function(){
		var id = $(this).attr('id');
		$('#delete-id').val(id);
		$('#delete-outlet').modal();
	})
	
	$('#btn-delete').on('click', function(){
		var id = $('#delete-id').val()
		$.ajax({
			url : '${pageContext.request.contextPath}/master/outlet/delete/'+id,
			type : 'DELETE',
			success : function () {
				alert('delete successfully!');
				window.location = '${pageContext.request.contextPath}/master/outlet';
			}, error : function () {
				alert('delete failed !');
			}
		})
	}) */
	
	
	//Delete di Table. Data pada Database masih dan Mengubah Status Active-nya menjadi 1 (0 = active. 1 = tidak active);
	$('#btn-delete').on('click', function(){
		var idUser = "${employee.user.id}";
		var outlet = {
			id : $('#edit-id').val(),
			name : $('#edit-name').val(),
			address : $('#edit-address').val(),
			phone : $('#edit-phone').val(),
			email : $('#edit-email').val(),
			province : {
				id : $('#edit-province').val()
			},
			region : {
				id : $('#edit-region').val()
			},
			district : {
				id : $('#edit-district').val()
			},
			postalCode : $('#edit-postalCode').val(),
			createdOn : $('#edit-createdOn').val(),
			createdBy : {
				id : $('#edit-createdBy').val(),
			},
			modifiedBy : {
				id : idUser
			},
			active : 0
			
		}
		
	console.log(outlet);
		
	$.ajax({
			url : '${pageContext.request.contextPath}/master/outlet/update',
			type : 'PUT',
			data : JSON.stringify(outlet),
			contentType : 'application/json',
			
			success : function(data){
				alert('delete successfully!')
				window.location = '${pageContext.request.contextPath}/master/outlet'; 
			},
			error : function () {
				alert('delete failed')
			}
		
		});
		
	});
	
	
	//reset form
		$('#btn-reset').on('click', function(){
			$('#entry-id').val('');
			$('#entry-name').val('');
			$('#entry-address').val('');
			$('#entry-phone').val('');
			$('#entry-email').val('');
			$('#entry-province').val('');
			$('#entry-region').val('');
			$('#entry-district').val('');
			$('#entry-postalCode').val('');
			$('#entry-createdOn').val('');	
	})
	
	
	//searching by outlet name
	$('#btn-search').on('click', function(){
		var word = $('#search').val();
		window.location ="${pageContext.request.contextPath}/master/outlet/search?search="+word;
	})
	
		
		
		
	});
</script>

</html>