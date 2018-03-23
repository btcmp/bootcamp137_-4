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
<title>Entry Supplier</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap4.min.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/css/bootstrap.css"/>
<script type="text/javascript" src="${jq}"></script>
<script type="text/javascript" src="${parsley}"></script>
<script type="text/javascript" src="http://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap4.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</head>
<body>
<div class="container">
<h3>Supplier</h3>
	<div style="margin-left: 30px; margin-top: 30px;" id="search-box" class="row"> 
		<div clas="col-sm-4"><input  class="form-control" type="text" id="search" placeholder="search transferStock"/></div>
		<div clas="col-sm-4"><a id="btn-search" class="btn btn-primary">Search</a></div>
	</div>
	</br>
	<table id="transferStock-tbl" class="table table-striped table-bordered" cellspacing="0" width="100%">
		<thead>
			<th>Transfer Date</th>
			<th>From Outlet</th>
			<th>To Outlet</th>
			<th>Status</th>
			<th>Action</th>
		</thead>
		<tbody>
			<c:forEach items="${transferStocks }" var="transferStock">
				<tr>
					<td>${transferStock.modifiedOn }</td>
					<td>${transferStock.fromOutlet }</td>
					<td>${transferStock.toOutlet }</td>
					<td>${transferStock.status }</td>
					<td>
						<a id="${transferStock.id }" class="update btn btn-primary">Update</a> |
						<a id="${transferStock.id }" class="delete btn btn-danger">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<button id="create" class="btn btn-block btn-success btn-lg">Create</button>
</div>
	<%@ include file="modal/edit-transfer-stock.jsp" %>
	<%@ include file="modal/add-transfer-stock.jsp" %>
	<%@ include file="modal/add-transfer-item.jsp" %>
	<%@ include file="modal/del-transfer-stock.html" %>
</body>
<script>
$(document).ready(function(){
	//setup datatables barang table
	$('#transferStock-tbl').DataTable({
		paging : true,
		searching : false
	});
	
	$('.delete').click(function(){
		var id = $(this).attr('id');
		$('#delete-id').val(id);
		$('#delete-transferStock').modal();
	})
	
	$('#btn-delete').click(function(){
		var id = $('#delete-id').val()
		$.ajax({
			url : '${pageContext.request.contextPath}/transaction/transfer/delete/'+id,
			type : 'DELETE',
			success : function(){
				alert('delete successfully');
				window.location='${pageContext.request.contextPath}/transaction/transfer';
			}, error : function(){
			alert('delete failed');
				}
		})
	})
	
	$('.update').click(function(){
		var id = $(this).attr('id');
		$.ajax({
			type : 'GET',
			url : '${pageContext.request.contextPath}/transaction/transfer/get-one/'+id,
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
				$('#modal-update-transferStock').modal();
				
			}, 
			error : function(){
				alert('show selected transferStock data in modal failed');
			}
		})
	})
	
	$('#btn-update').click(function(){
		var active = "false";
		$('#update-active input:checked').each(function(){
			active = $(this).val()
		})
		var transferStock={
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
				url : '${pageContext.request.contextPath}/transaction/transfer/update',
				type : 'PUT',
				data : JSON.stringify(transferStock),
				contentType : 'application/json',
				success : function(){
					alert('update successfully');
					window.location='${pageContext.request.contextPath}/transaction/transfer';
				}, error : function(){
					alert('update failed');
				}
				
			})
		})
	
	//add
	$('#create').click(function(){
		$('#modal-add-transferStock').modal();
	})
	
	
	$('#btn-add-transfer-item').click(function(){
		$('#modal-add-transfer-item').modal();
	})
	
	$('#btn-add').click(function(){
		var active = "false";
		$('#add-active input:checked').each(function(){
			active = $(this).val()
		})
		var transferStock={
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
			url : '${pageContext.request.contextPath }/transaction/transfer/save',
			type : 'POST',
			data : JSON.stringify(transferStock),
			contentType : 'application/json',
			success : function(){
				alert('save successfully');
				window.location='${pageContext.request.contextPath}/transaction/transfer';
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
	
	$('#btn-search').click(function(){
		var word = $('#search').val();
		window.location = "${pageContext.request.contextPath}/transaction/transfer/search?search="+word;
	})
	
	$('#search-item-varian').on('input',function(e){
		var word = $(this).val();
		$.ajax({
			type : 'GET',
			//url : '${pageContext.request.contextPath}/transaction/transfer/search-item?search='+word,
			url : '${pageContext.request.contextPath}/transaction/transfer/search-item?search='+word,
			dataType: 'json',
			success : function(data){
				console.log(data);
			}, 
			error : function(){
				alert('show selected transferStock data in modal failed');
			}
		})
	})
});
</script>
</html>