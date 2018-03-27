<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<spring:url value="/resources/js/jquery-3.3.1.js" var="jq"></spring:url>
<spring:url value="/resources/js/parsley.js" var="parsley"></spring:url>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Entry Category</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap4.min.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/css/bootstrap.css"/>
<script type="text/javascript" src="${jq}"></script>
<script type="text/javascript" src="${parsley}"></script>
<script type="text/javascript" src="http://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap4.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<script type="text/javascript">
$(document).ready(function(){
		$('#category-tbl').DataTable({
			searching:false
		});
	
	//ADD CATEGORY
	$('#create-category').click(function(){
		$('#save-category').modal();
	});

	//EXECUTE
	$('#btn-save').on('click', function(){
		var category = {
			name : $('#entry-category').val(),
			active : 0
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
/* 			$('#edit-item').val(category.id); */
		}
	
	
	//EKSEKUSI BUTTON SAVE
	$('#btn-update').on('click', function(){
		var category = {
			id : $('#edit-id').val(),
			name : $('#edit-name').val(),	
			createdOn : $('#edit-createdOn').val(),
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
	
	
//Delete di Table. Data pada Database masih dan Mengubah Status Active-nya menjadi 1 (0 = active. 1 = tidak active);
	$('#btn-delete').on('click', function(){
		var category = {
			id : $('#edit-id').val(),
			name : $('#edit-name').val(),	
			createdOn : $('#edit-createdOn').val(),
			active : 1
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
	$('#btn-reset').on('click', function(){
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
</head>
<body>
<div style="padding:50px">
<p><h1>DATA CATEGORY</h1></p>
<div id="search-box">
	<span>Search</span>
	<span><input type="text" id="search"/></span>
	<span><a id="btn-search" href="#" class="btn btn-primary">Search</a></span>

</div>
<p><a class="btn btn-info" id="create-category" style="color:white;">Create</a></p>
	<table class="table table-striped table-bordered" id="category-tbl">
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
	<%@ include file="modal/add-category.jsp" %>
	<%@ include file="modal/view-category.jsp" %>
</body>
</html>