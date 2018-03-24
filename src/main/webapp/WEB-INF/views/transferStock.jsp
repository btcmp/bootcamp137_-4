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
<title>Entry Transfer Stock</title>
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
<h3>Transfer Stock</h3>
	<div class="form-group">
		<select
			class="form-control col-sm-4" name="outlet-search" id="outlet-search">
			<option value="kosong">Search Outlet</option>
			<c:forEach var="outlet" items="${outlets }">
				<option value="${outlet.id }">${outlet.name }</option>
			</c:forEach>
			<option value="all">All Outlet</option>
		</select>
	</div>
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
					<td>${transferStock.fromOutlet.name }</td>
					<td>${transferStock.toOutlet.name }</td>
					<td>${transferStock.status }</td>
					<td>
						<a id="${transferStock.id }" class="view btn btn-primary">View</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<button id="create" class="btn btn-block btn-success btn-lg">Create</button>
</div>
<div>
	<%@ include file="modal/view-transfer-stock.jsp" %>
	<%@ include file="modal/add-transfer-stock.jsp" %>
	<%@ include file="modal/add-transfer-item.jsp" %>
</div>
	
</body>
<script>
$(document).ready(function(){
	//setup datatables barang table
	$('#transferStock-tbl').DataTable({
		paging : true,
		searching : false
	});
	var added = [];
	var addedQty = [];
	$('.btn-added-item').hide();
	
	$('.view').click(function(){
		var id = $(this).attr('id');
		$.ajax({
			type : 'GET',
			url : '${pageContext.request.contextPath}/transaction/transfer-stock/get-one/'+id,
			dataType: 'json',
			success : function(data){
				$('#view-from-outlet').val(data.fromOutlet.name);
				$('#view-to-outlet').val(data.toOutlet.name);
				$('#view-notes').val(data.notes);
				$('#view-transferStock-tbl').empty();
				$.ajax({
					url : '${pageContext.request.contextPath }/transaction/transfer-stock/search-transfer-stock-detail?search='+data.id,
					type : 'GET',
					dataType: 'json',
					success : function(data2){
						//console.log(data2);
						$.each(data2, function(key, val) {
						$('#view-transferStock-tbl').append('<tr><td>'+ val.itemVariant.item.name +'-'+ val.itemVariant.name +'</td><td>'
								+ val.instock +'</td><td>'+ val.transferQty +'</td></tr>');
						});
						//call modal
						$('#modal-view-transferStock').modal();
					}, error : function(){
						alert('get transfer stock detail failed');
					}
					
				})
				
				
				
			}, 
			error : function(){
				alert('show selected transferStock data in modal failed');
			}
		})
	})
	
	$('#create').click(function(){
		$('#modal-add-transferStock').modal();
		document.getElementById("btn-save").disabled = true;
	})
	
	$('#btn-add-transfer-item').click(function(){
		$('#modal-add-transfer-item').modal();
	})
	
	$('#btn-save').click(function(){
		var transferStockDetail=[];
		
		$('#add-transferStock-tbl > tr').each(function(index, data){
			var tsd = {
					itemVariant : {
						id : $(data).find('td').eq(0).attr('id')
					},
					instock : $(data).find('td').eq(1).text(),
					transferQty : $(data).find('td').eq(2).text()
			}
			transferStockDetail.push(tsd);
		});
		
		var transferStock = {
				fromOutlet : {
					id : $('#add-from-outlet').val()
				},
				toOutlet : {
					id : $('#add-to-outlet').val()
				},
				transferStockDetail : transferStockDetail,
				notes : $('#add-notes').val(),
				status : "Submitted"
		};
		console.log(transferStock);
		$.ajax({
			url : '${pageContext.request.contextPath }/transaction/transfer-stock/save',
			type : 'POST',
			data : JSON.stringify(transferStock),
			contentType : 'application/json',
			success : function(){
				alert('save successfully');
				window.location='${pageContext.request.contextPath}/transaction/transfer-stock';
			}, error : function(){
				alert('save failed');
			}
			
		})
	})
	
	$('#outlet-search').change(function(){
		var word = $(this).val();
		if (word=="all") {
			window.location = "${pageContext.request.contextPath}/transaction/transfer-stock";
		} else if (word!=="kosong") {
			window.location = "${pageContext.request.contextPath}/transaction/transfer-stock/search-outlet?search="+word;
		}
	})
	
	$('body').on('click', 'button.btn-add-item', function(){
		var id = $(this).attr('id');
		var transQty = $('.add-transfer-stock-qty'+id).val();
		added.push(id);
		addedQty.push(transQty);
		$('#td-qty'+id).html(transQty);
		$(this).hide();
		$('.btn-added-item'+id).show();
		document.getElementById("btn-save").disabled = false;
		$.ajax({
			type : 'GET',
			url : '${pageContext.request.contextPath}/transaction/transfer-stock/get-one-item/'+id,
			dataType: 'json',
			success : function(data){
				$('#add-transferStock-tbl').append('<tr id="tr-transferStock'+ data.id +'"><td id="'+ data.itemVariant.id +'">'+ data.itemVariant.item.name +'-'+ data.itemVariant.name +'</td><td>'
						+ data.endingQty +'</td><td>'+ transQty +'</td><td><button type="button" id="'+ data.id +'" class="btn-cancel-item'
						+ data.id +' btn-cancel-item btn btn-primary">Cancel</button></td></tr>');
			},
			error : function(){
				alert('get one item inventory failed');
			}
		})
	})
	
	$('body').on('click', 'button.btn-cancel-item', function(){
		var id = $(this).attr('id');
		$('#tr-transferStock'+id).remove();
		$('.btn-added-item'+id).hide();
		$('.btn-add-item'+id).show();
		$('#td-qty'+id).html('<input class="add-transfer-stock-qty'+ id +'" value="0" />');
		var a = added.indexOf(id.toString());
		added.splice(a,1);
		addedQty.splice(a,1);
		if (document.getElementById("add-transferStock-tbl").rows.length>0) {
			document.getElementById("btn-save").disabled = false;
		}else {
			document.getElementById("btn-save").disabled = true;
		}
	})
	
	$('#search-item-varian').on('input',function(e){
		var word = $(this).val();
		$.ajax({
			type : 'GET',
			url : '${pageContext.request.contextPath}/transaction/transfer-stock/search-item?search='+word,
			dataType: 'json',
			success : function(data){
				$('#add-item-transfer-tbl').empty();
				$.each(data, function(key, val) {
					if(added.indexOf(val.id.toString()) == -1) {
						$('#add-item-transfer-tbl').append('<tr><td>'+ val.itemVariant.item.name +'-'+ val.itemVariant.name +'</td><td>'
								+ val.endingQty +'</td><td id="td-qty'+ val.id +'"><input class="add-transfer-stock-qty'+ val.id +'" value="0" /></td><td><button type="button" id="'+ val.id +'" class="btn-add-item'
								+ val.id +' btn-add-item btn btn-primary">Add</button><button type="button" id="'+ val.id +'" class="btn-added-item'
								+ val.id +' btn-added-item btn">Added</button></td></tr>');
						$('.btn-added-item'+val.id).hide();
					} else {
						var a = added.indexOf(val.id.toString());
						$('#add-item-transfer-tbl').append('<tr><td>'+ val.itemVariant.item.name +'-'+ val.itemVariant.name +'</td><td>'
								+ val.endingQty +'</td><td id="td-qty'+ val.id +'">'+addedQty[a]+'</td><td><button type="button" id="'+ val.id +'" class="btn-add-item'
								+ val.id +' btn-add-item btn btn-primary">Add</button><button type="button" id="'+ val.id +'" class="btn-added-item'
								+ val.id +' btn-added-item btn">Added</button></td></tr>');
						$('.btn-add-item'+val.id).hide();
					}
				});
			}, 
			error : function(){
				$('#add-item-transfer-tbl').empty();
				//alert('show selected transferStock data in modal failed');
			}
		})
	})
});
</script>
</html>