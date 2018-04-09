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

		<%@ include file="template/template.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">

			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h2 style="text-align: center;">Transfer Stock</h2>
				<ol class="breadcrumb">
					<li><a href="${pageContext.request.contextPath}/master"><i
							class="fa fa-dashboard"></i> Master</a></li>
					<li class="active">Transfer-stock</li>
				</ol>
			</section>

			<section class="content-header row">
				<div style="text-align: right;">
					<div class="col-xs-12">
						<a id="kosong" class="choose-customer btn btn-primary">Choose Customer</a>
					</div>
					<!-- <div class="col-xs-1">
						<a id="export" class="btn btn-primary">Export</a>
					</div> -->
				</div>
			</section>
			
			<div>
			<input id="email-customer" type="hidden"></input>
			</div>

			<!-- Main content -->
			<section class="content" style="background-color:;">
				<div class="row">
					<div class="col-xs-12">
						<!-- /.box -->
						<div class="box">
							<!-- /.box-header -->

							<div class="box-body" class="row">
								<div class="col-xs-6">
									<div style="margin-bottom: 10px;" id="search-box"> 
										<input  class="form-control" type="text" id="search" placeholder="search item-variant"/>
									</div>
									<table class="table table-striped table-bordered" cellspacing="0" width="100%">
										<tbody id="item-tbl">
											
										</tbody>
									</table>
									</div>
								<div class="col-xs-6">
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
									<button id="clear" class="btn btn-primary btn-lg">Clear Sale</button>
									<button id="charge" class="btn btn-primary btn-lg">Charge</button>
								</div>
								
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

		<%@ include file="template/footer.jsp"%>

		<div class="control-sidebar-bg"></div>
	</div>
	<!-- ./wrapper -->
	<div>
		<%@ include file="modal/charge-sales-order.jsp" %>
		<%@ include file="modal/receipt-sales-order.jsp" %>
		<%@ include file="modal/search-customer-sales-order.jsp" %>
		<%@ include file="modal/add-customer-sales-order.jsp" %>
	</div>

</body>
<script>
$(document).ready(function(){
	//setup datatables barang table
	$('#salesOrder-tabel').DataTable({
		paging : true,
		searching : false
	});
	
	$("#sales-order-side-option").addClass('active');
	$("#treeview-transaction").addClass('active');
	
	var added = [];
	var addedQty = [];
	$('.btn-added-item').hide();
	document.getElementById("charge").disabled = true;
	
	$('.choose-customer').click(function(){
		$('#modal-search-customer-sales-order').modal();
	})
	
	$('#charge').click(function(){
		if ($('.choose-customer').attr("id")=="kosong") {
			alert("choose customer first");
		}else {
			$('#modal-charge-sales-order').modal();
		}
	})
	
	$('#add-new-customer').click(function(){
		$('#modal-add-customer-sales-order').modal();
	})
	
	$('#btn-add-customer').click(function(){
		var customer={
				name : $('#add-name').val(),
				address : $('#add-address').val(),
				phone : $('#add-phone').val(),
				email : $('#add-email').val(),
				dob : $('#add-dob').val(),
				province : {
					id : $('#add-province').val()
				},
				region : {
					id : $('#add-region').val()
				},
				district : {
					id : $('#add-district').val()
				},
				active : true
			}
		if (customer.name=="" || customer.address=="" || customer.dob=="" || customer.phone=="" || customer.email=="" || customer.province.id=="" || customer.region.id=="" || customer.district.id=="") {
			alert("fill the form completely");
		} else {
			$.ajax({
				url : '${pageContext.request.contextPath }/master/customer/get-all',
				type : 'GET',
				success : function(data){
					var sameName = 0;
					var sameEmail = 0;
					$(data).each(function(index, data2){
						if (customer.name.toLowerCase()==data2.name.toLowerCase()) {
							sameName++;
						} else if (customer.email.toLowerCase()==data2.email.toLowerCase()) {
							sameEmail++;
						}
					})
					if (sameName>0) {
						alert("This name has been taken, please change it!");
					} else if (sameEmail>0) {
						alert("This email has been used, please change it!");
					} else {
						$.ajax({
							url : '${pageContext.request.contextPath }/master/customer/save',
							type : 'POST',
							data : JSON.stringify(customer),
							contentType : 'application/json',
							success : function(){
								var word = $('#search-customer').val();
								searchCustomer(word);
								$('#modal-add-customer-sales-order').modal('toggle');
							}, error : function(){
								alert('save new customer failed');
							}
						})
					}
				}, error : function(){
					alert('get all supplier failed');
				}
			})	
		}
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
									+ val.id +' btn-added-item btn">Added</button><input type="hidden" id="endingQty'+ val.id +'" value="'+ val.endingQty +'"/></td></tr>');
							$('.btn-added-item'+val.id).hide();
						} else {
							var a = added.indexOf(val.id.toString());
							$('#item-tbl').append('<tr><td>'+ val.itemVariant.item.name +'-'+ val.itemVariant.name +'</td><td>Rp.'
									+ val.itemVariant.price +'</td><td id="td-qty'+ val.id +'">'+addedQty[a]+'</td><td><button type="button" id="'+ val.id +'" class="btn-add-item'
									+ val.id +' btn-add-item btn btn-primary">Add</button><button type="button" id="'+ val.id +'" class="btn-added-item'
									+ val.id +' btn-added-item btn">Added</button><input type="hidden" id="endingQty'+ val.id +'" value="'+ val.endingQty +'"/></td></tr>');
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
		var inStock = parseInt($('#endingQty'+id).val());
		if (transQty<1) {
			alert("at least 1");
		} else if (transQty>inStock) {
			alert("not enough stock");
		}else {
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
					$('#salesOrder-tbl-body').append('<tr id="tr-salesOrder'+ data.id +'"><td id="'+ data.itemVariant.id +'">'+ data.itemVariant.item.name +'-'+ data.itemVariant.name +'</td><td id="'+ data.id +'">Rp.'
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
		}
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
	
	$('#search-customer').on('input',function(e){
		var word = $(this).val();
		searchCustomer(word);
	})
	
	function searchCustomer(word) {
		if (word=="") {
			$('#search-customer-tbl').empty();
		} else {
			$.ajax({
				type : 'GET',
				url : '${pageContext.request.contextPath}/transaction/sales-order/search-customer?search='+word,
				dataType: 'json',
				success : function(data){
					$('#search-customer-tbl').empty();
					$.each(data, function(key, val) {
						$('#search-customer-tbl').append('<tr><td id="customer-name'+ val.id+'">'+ val.name +'</td><td>'
								+ val.phone +'</td><td id="customer-email'+ val.id+'">'+ val.email +'</td><td><button type="button" id="'+ val.id +'" class="btn-choose-customer'
								+ val.id +' btn-choose-customer btn btn-primary"  data-dismiss="modal">Choose</button></td></tr>');
					});
				}, 
				error : function(){
					$('#search-customer-tbl').empty();
					//alert('show selected transferStock data in modal failed');
				}
			})
		}
	}
	
	$('body').on('click', 'button.btn-choose-customer', function(){
		var id = $(this).attr('id');
		var name = $('#customer-name'+id).text();
		$('#email-customer').val($('#customer-email'+id).text());
		$('.choose-customer').text(name);
		$('.choose-customer').attr("id",id);
	})
	
	$('#receipt-done').click(function(){
		window.location='${pageContext.request.contextPath}/transaction/sales-order';
	})
	
	$('#receipt-print').click(function(){
		//window.location = '${pageContext.request.contextPath}/generate/sales-order';
		window.location='${pageContext.request.contextPath}/transaction/sales-order';
	})
	
	$('#receipt-email-btn').click(function(){
		window.location='${pageContext.request.contextPath}/transaction/sales-order';
	})
	
	$('#charge-done').click(function(){
		var cash = parseInt($('#charge-cash').val());
		var total = parseInt($('#charge').text().split("Rp.")[1]);
		if (cash<total) {
			alert("bayarnya kurang coy!!!");
		} else {
			$('#receipt-cash').text("Out of Rp."+cash);
			$('#receipt-change').val("Rp."+(cash-total));
			var emailCust = $('#email-customer').val();
			$('#receipt-email').val(emailCust);
			done();
		}
	})
	
	function done(){
		var userId = "${employee.user.id}";
		var salesOrderDetail = [];
		$('#salesOrder-tbl-body > tr').each(function(index, data){
			var sod = {
					itemVariant : {
						id : $(data).find('td').eq(0).attr('id')
					},
					itemInventory :{
						id : $(data).find('td').eq(1).attr('id')
					},
					qty : $(data).find('td').eq(2).text(),
					unitPrice : $(data).find('td').eq(1).text().split("Rp.")[1],
					subTotal : $(data).find('td').eq(3).text().split("Rp.")[1],
					createdBy : {
						id : userId
					},
					modifiedBy : {
						id : userId
					}
			}
			salesOrderDetail.push(sod);
		})
		
		var salesOrder = {
				customer : {
					id : $('.choose-customer').attr('id')
				},
				grandTotal : $('#charge').text().split("Rp.")[1],
				salesOrderDetail : salesOrderDetail,
				createdBy : {
					id : userId
				},
				modifiedBy : {
					id : userId
				}
		}
		console.log(salesOrder);
		$.ajax({
			url : '${pageContext.request.contextPath }/transaction/sales-order/save',
			type : 'POST',
			data : JSON.stringify(salesOrder),
			contentType : 'application/json',
			success : function(){
				
				transferStockId = $('#view-hidden-id').val();
				$.ajax({
					url : '${pageContext.request.contextPath }/transaction/sales-order/update-stock/',
					type : 'PUT',
					data : JSON.stringify(salesOrder),
					contentType : 'application/json',
					success : function(){
						$('#modal-receipt-sales-order').modal({backdrop: 'static', keyboard: false});
					}, error : function(){
						alert('update stock failed');
					}
					
				})
				//window.location='${pageContext.request.contextPath}/transaction/sales-order';
				//alert("saved");
			}, error : function(){
				alert('save failed');
			}
			
		})
	}
	
	$('#export').click(function(){
		window.location = '${pageContext.request.contextPath}/generate/sales-order'; 
	})
});
</script>
</html>