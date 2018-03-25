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
<title>Entry Sales Order</title>
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
<h3>Sales Order</h3>
	<div style="margin-left: 30px; margin-top: 30px;" id="search-box" class="row"> 
		<div clas="col-sm-4"><input  class="form-control" type="text" id="search" placeholder="search supplier"/></div>
	</div>
	<table class="table table-striped table-bordered" cellspacing="0" width="100%">
		<tbody id="item-tbl">
			
		</tbody>
	</table>
	
	<button id="create" class="btn btn-block btn-success btn-lg">Choose Customer</button>
	
	<table id="salesOrder-tabel" class="table table-striped table-bordered" cellspacing="0" width="100%">
		<thead>
			<th>Item</th>
			<th>Price</th>
			<th>Qty.</th>
			<th>Subtotal</th>
			<th>Action</th>
		</thead>
		<tbody id="salesOrder-tbl-body">
		</tbody>
		<tfoot id="salesOrder-tbl-foot">
			<tr>
				<th colspan="3">TOTAL</th>
				<th colspan="2">Rp. 0</th>
			</tr>
		</tfoot>
	</table>
	<button id="clear" class="btn btn-success btn-lg">Clear Sale</button>
	<button id="charge" class="btn btn-success btn-lg">Charge</button>
</div>
<div>
	<%-- <%@ include file="modal/view-transfer-stock.jsp" %>
	<%@ include file="modal/add-transfer-stock.jsp" %>
	<%@ include file="modal/add-transfer-item.jsp" %> --%>
</div>
	
</body>
<script>
$(document).ready(function(){
	//setup datatables barang table
	$('#salesOrder-tabel').DataTable({
		paging : true,
		searching : false
	});
	var added = [];
	var addedQty = [];
	$('.btn-added-item').hide();
	document.getElementById("charge").disabled = true;
	
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
	
	$('#search').on('input',function(e){
		var word = $(this).val();
		search(word);
	})
	
	function search(word) {
		if (word=="") {
			$('#item-tbl').empty();
		} else {
			$.ajax({
				type : 'GET',
				url : '${pageContext.request.contextPath}/transaction/sales-order/search-item?search='+word,
				dataType: 'json',
				success : function(data){
					$('#item-tbl').empty();
					$.each(data, function(key, val) {
						if(added.indexOf(val.id.toString()) == -1) {
							$('#item-tbl').append('<tr><td>'+ val.itemVariant.item.name +'-'+ val.itemVariant.name +'</td><td>Rp.'
									+ val.itemVariant.price +'</td><td id="td-qty'+ val.id +'"><input type="number" class="add-item-qty'+ val.id +'" value="1" /></td><td><button type="button" id="'+ val.id +'" class="btn-add-item'
									+ val.id +' btn-add-item btn btn-primary">Add</button><button type="button" id="'+ val.id +'" class="btn-added-item'
									+ val.id +' btn-added-item btn">Added</button></td></tr>');
							$('.btn-added-item'+val.id).hide();
						} else {
							var a = added.indexOf(val.id.toString());
							$('#item-tbl').append('<tr><td>'+ val.itemVariant.item.name +'-'+ val.itemVariant.name +'</td><td>Rp.'
									+ val.itemVariant.price +'</td><td id="td-qty'+ val.id +'">'+addedQty[a]+'</td><td><button type="button" id="'+ val.id +'" class="btn-add-item'
									+ val.id +' btn-add-item btn btn-primary">Add</button><button type="button" id="'+ val.id +'" class="btn-added-item'
									+ val.id +' btn-added-item btn">Added</button></td></tr>');
							$('.btn-add-item'+val.id).hide();
						}
					});
				}, 
				error : function(){
					$('#item-tbl').empty();
					//alert('show selected transferStock data in modal failed');
				}
			})
		}
	}
	
	$('body').on('click', 'button.btn-add-item', function(){
		var id = $(this).attr('id');
		var transQty = $('.add-item-qty'+id).val();
		added.push(id);
		addedQty.push(transQty);
		$('#td-qty'+id).html(transQty);
		$(this).hide();
		$('.btn-added-item'+id).show();
		$.ajax({
			type : 'GET',
			url : '${pageContext.request.contextPath}/transaction/sales-order/get-one-item/'+id,
			dataType: 'json',
			success : function(data){
				document.getElementById("charge").disabled = false;
				if (added.length=="1") {
					$('#salesOrder-tbl-body').empty();
				}
				$('#salesOrder-tbl-body').append('<tr id="tr-salesOrder'+ data.id +'"><td id="'+ data.itemVariant.id +'">'+ data.itemVariant.item.name +'-'+ data.itemVariant.name +'</td><td>Rp.'
						+ data.itemVariant.price +'</td><td>'+ transQty +'</td><td>Rp.'+ data.itemVariant.price*transQty +'</td><td><button type="button" id="'+ data.id +'" class="btn-cancel-item'
						+ data.id +' btn-cancel-item btn btn-primary">Cancel</button></td></tr>');
				$('#salesOrder-tbl-foot').empty();
				var total = 0;
				$('#salesOrder-tbl-body > tr').each(function(index, data){
					var price = $(data).find('td').eq(3).text().split("Rp.")[1];
					total = total + parseInt(price);
				})
				$('#salesOrder-tbl-foot').append('<tr id="tr-total-item"><th colspan="3">TOTAL</th><th colspan="2">Rp. '+ total +'</th></tr>');
				$('#charge').text("Charge Rp."+total)
			},
			error : function(){
				alert('get one item inventory failed');
			}
		})
	})
	
	$('body').on('click', 'button.btn-cancel-item', function(){
		var id = $(this).attr('id');
		$('#tr-salesOrder'+id).remove();
		$('.btn-added-item'+id).hide();
		$('.btn-add-item'+id).show();
		$('#td-qty'+id).html('<input type="number" class="add-item-qty'+ id +'" value="1" />');
		var a = added.indexOf(id.toString());
		added.splice(a,1);
		addedQty.splice(a,1);
		if (document.getElementById("salesOrder-tbl-body").rows.length>0) {
			document.getElementById("charge").disabled = false;
		}else {
			document.getElementById("charge").disabled = true;
		}
		$('#salesOrder-tbl-foot').empty();
		var total = 0;
		$('#salesOrder-tbl-body > tr').each(function(index, data){
			var price = $(data).find('td').eq(3).text().split("Rp.")[1];
			total = total + parseInt(price);
		})
		$('#salesOrder-tbl-foot').append('<tr id="tr-total-item"><th colspan="3">TOTAL</th><th colspan="2">Rp. '+ total +'</th></tr>');
		$('#charge').text("Charge Rp."+total)
	})
	
	$('#clear').click(function(){
		$('#salesOrder-tbl-body').empty();
		added = [];
		addedQty = [];
		$('#salesOrder-tbl-foot').empty();
		$('#salesOrder-tbl-foot').append('<tr id="tr-total-item"><th colspan="3">TOTAL</th><th colspan="2">Rp. '+ 0 +'</th></tr>');
		$('#charge').text("Charge");
		document.getElementById("charge").disabled = true;
		var word = $('#search').val();
		search(word);
	})
});
</script>
</html>