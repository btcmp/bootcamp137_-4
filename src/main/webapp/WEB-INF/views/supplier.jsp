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
        Master Supplier
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
    <section class="content"  style="background-color: ;">
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
  
  <%@ include file="template/footer.jsp" %>

  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->
<div>
	<%@ include file="modal/edit-supplier.jsp" %>
	<%@ include file="modal/add-supplier.jsp" %>
	<%@ include file="modal/del-supplier.html" %>
</div>



</body>

<script>
$(document).ready(function(){
	//setup datatables barang table
	$('#supplier-tbl').DataTable({
	      paging      : true,
	      searching   : false
	    })
	$("#supplier-side-option").addClass('active');
	$("#treeview-master").addClass('active');
	
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

</html>

