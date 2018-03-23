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
<title>Entry Outlet</title>
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
	$(function() {
		//data-tabel
		$('#outlet-tbl').DataTable({
			searching:false	
		});
		
//================================================== ADD DATA
	$('#create-outlet').on('click', function() {
		$('#save-outlet').modal();   //call modal
	});
	
	//execute button add/save
	$('#btn-entry').on('click', function() {
		var active = "false";
		$('#entry-active input:checked').each(function(){
			active = $(this).val()
		})
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
			active : active
		}
		
		
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
		})
		
	})
	
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
		$('#edit-province').val(outlet.province.id);
		$('#edit-region').val(outlet.region.id);
		$('#edit-district').val(outlet.district.id);
		$('#edit-postalCode').val(outlet.postalCode);
		$('#edit-createOn').val(outlet.createOn);
		var active = outlet.active;
		if(active==true){
			$('#edit-active').prop('checked', true);
		} else {
			$('#edit-active').prop('checked', false);
		}
	}
	

	//eksekusi button update/edit
	$('#btn-edit').on('click', function() {
		var active = "false";
		$('#edit-active input:checked').each(function() {
			active = $(this).val()
		})
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
			createOn : $('#edit-createOn').val(),
			active : active
		}
		
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
	});
	
	
	//edit-data province, region, district
	$('#edit-province').change(function(){
		var id = $('#edit-province').val();
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
				}, error : function(){
					alert('get failed');
				}
			})
		}
	});
	
	$('#edit-region').change(function(){
		var id = $('#edit-region').val();
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
				}, error : function(){
					alert('get failed');
				}
			})
		}
	});


	//delete
	$('.delete').on('click', function(){
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
	})
	
	
	
	//searching by outlet name
	$('#btn-search').on('click', function(){
		var word = $('#search').val();
		window.location ="${pageContext.request.contextPath}/master/outlet/search?search="+word;
	})
	
		
		
		
	});
</script>
</head>
<body>
<div class="container">
<p><h1>DATA OUTLET</h1></p>
<div id="search-box">
	<span>Search</span>
	<span><input type="text" id="search"/></span>
	<span><a id="btn-search" href="#" class="btn btn-primary">Search</a></span>
</div>
<p><a class="btn btn-info" id="create-outlet" style="color:white;">Create</a></p>
	<table class="table table-striped table-bordered" id="outlet-tbl">
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
					<a id="${outlet.id }" class="edit btn btn-primary" style="color:white;">Edit</a>
					<a id="${outlet.id }" class="delete btn btn-danger" style="color:white">Delete</a>
				</td>				
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>		
<%@ include file="modal/add-outlet.jsp" %>
<%@ include file="modal/edit-outlet.jsp" %>
<%@ include file="modal/del-outlet.html" %>

</body>
</html>