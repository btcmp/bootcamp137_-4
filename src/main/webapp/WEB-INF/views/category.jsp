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
        Master Category
      </h2>
      <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/master"><i class="fa fa-dashboard"></i> Master</a></li>
        <li class="active">Category</li>
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
        <a id="create-category" class="btn btn-primary col-xs-3" style="float:right;">Create</a>
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
              <table id="category-tbl" class="table table-bordered table-striped">
				<thead>
					<th>Category Name</th>
					<th>Item Stocks</th>
					<th>Action</th>
				</thead>
				<tbody>
					<c:forEach items="${categorys }" var="category">
						<tr>
							<td>${category.name }</td>
							<td>${category.itemStock }</td>
							<td>
								<a id="${category.id }" class="edit btn btn-success" style="color:white;">View</a>
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
	<%@ include file="modal/add-category.jsp" %>
	<%@ include file="modal/view-category.jsp" %>
</div>



</body>

<script>
$(document).ready(function(){
	$('#category-tbl').DataTable({
		searching:false
	});
	
	$("#category-side-option").addClass('active');
	$("#treeview-master").addClass('active');

//ADD CATEGORY
$('#create-category').click(function(){
	$('#save-category').modal();
});

//EXECUTE
$('#btn-save').on('click', function(){
	var category = {
		name : $('#entry-category').val(),
		active : 1
	}
	
	
	
	
	$.ajax({
		url : '${pageContext.request.contextPath}/master/category/save',
		type : 'POST',
		contentType : 'application/json',
		data : JSON.stringify(category),
		success : function(data) {
			alert('save successfully');
			window.location = '${pageContext.request.contextPath}/master/category';
		}, error : function() {
			alert ('saving failed');
		}
	})	

});	
//========================================		
//EDIT CATEGORY
$('.edit').on('click', function(evt){
	evt.preventDefault();
	var id = $(this).attr('id');
	
	$.ajax({
		url : '${pageContext.request.contextPath}/master/category/get-one/'+id,
		type : 'GET',
		dataType : 'json',
		success : function(category){
			setEditCategory(category);
			$('#edit-category').modal();   //call modal
		},
		error : function(){
			alert('failed getting data update');
		}
		
	});
});
//SET UP DATA UPDATE
	function setEditCategory(category) {
		$('#edit-id').val(category.id);
		$('#edit-name').val(category.name);
		$('#edit-createdOn').val(category.createdOn);
	}


//EKSEKUSI BUTTON SAVE
$('#btn-update').on('click', function(){
	var category = {
		id : $('#edit-id').val(),
		name : $('#edit-name').val(),	
		createdOn : $('#edit-createdOn').val(),
		active : 1
	}

	$.ajax({
		url : '${pageContext.request.contextPath}/master/category/update',
		type : 'PUT',
		data : JSON.stringify(category),
		contentType : 'application/json',
		
		success : function(data){
			alert('update successfully!')
			window.location = '${pageContext.request.contextPath}/master/category';
		},
		error : function () {
			alert('update failed')
		}
	
	});
	
});
//===============================	
//DELETE IN TABLE and DATABASE
/*  	$('#btn-delete').on('click', function(){
	var	id = $('#edit-id').val();

	$.ajax({ 
	url : '${pageContext.request.contextPath}/master/category/delete/'+id,
	type : 'DELETE',
	success : function (){
		alert('delete successfully');
		window.location = '${pageContext.request.contextPath}/master/category'
	},	error : function(){
		alert('delete failed');
	}
	});  		
});  */


//Delete di Table. Data pada Database masih dan Mengubah Status Active-nya menjadi 0 (1 = active. 0 = tidak active);
$('#btn-delete').on('click', function(){
	var category = {
		id : $('#edit-id').val(),
		name : $('#edit-name').val(),	
		createdOn : $('#edit-createdOn').val(),
		active : 0
	}
console.log(category)
	$.ajax({
		url : '${pageContext.request.contextPath}/master/category/update',
		type : 'PUT',
		data : JSON.stringify(category),
		contentType : 'application/json',
		
		success : function(data){
			alert('delete successfully!')
			window.location = '${pageContext.request.contextPath}/master/category';
		},
		error : function () {
			alert('delete failed')
		}
	
	});
	
});
//==================================
//RESET FORM
$('#btn-reset-add').on('click', function(){
	$('#entry-category').val('');
})

$('#btn-reset-view').on('click', function(){
	$('#edit-name').val('');
})

//==================================
//SEARCH
$('#btn-search').click(function(){
	var word = $('#search').val();
	window.location = "${pageContext.request.contextPath}/master/category/search?search="+word;
})




});
</script>

</html>

