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
					<li><a href="${pageContext.request.contextPath}/main-menu"><i
							class="fa fa-dashboard"></i> Main-menu</a></li>
					<li class="active">Transfer-stock</li>
				</ol>
			</section>

			<section class="content-header row">
				<!-- search form -->
				<div class="col-xs-4">
					<div class="form-group">
					<select class="form-control col-sm-4" name="outlet-search"
						id="outlet-search">
						<option value="kosong">Search By To Outlet</option>
						<c:forEach var="outlets" items="${outlets }">
							<script>
								var fromOutletId = "${outlet.id}";
								var toOutletId = "${outlets.id}";
								if (fromOutletId!==toOutletId) {
									document.write("<option value="+toOutletId+">"+"To ${outlets.name}" +"</option>");
								}
							</script>
						</c:forEach>
						<option value="all">All Outlet</option>
					</select>
				</div>
				</div>
				<div style="text-align: right;">
					<div class="col-xs-7">
						<a id="create" class="btn btn-primary">Create</a>
					</div>
					<div class="col-xs-1">
						<a id="export" class="btn btn-primary">Export</a>
					</div>
				</div>
			</section>

			<!-- Main content -->
			<section class="content" style="background-color:;">
				<div class="row">
					<div class="col-xs-12">
						<!-- /.box -->
						<div class="box">
							<!-- /.box-header -->

							<div class="box-body">
								<table id="transferStock-tbl"
									class="table table-striped table-bordered" cellspacing="0"
									width="100%">
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
												<td><a id="${transferStock.id }"
													class="view btn btn-primary">View</a></td>
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

		<%@ include file="template/footer.jsp"%>

		<div class="control-sidebar-bg"></div>
	</div>
	<!-- ./wrapper -->
	<div>
		<%@ include file="modal/add-transfer-stock.jsp"%>
		<%@ include file="modal/add-transfer-item.jsp"%>
		<%@ include file="modal/view-transfer-stock-detail.jsp" %>
	</div>

</body>
<script>
$(document).ready(function(){
	//setup datatables barang table
	$('#transferStock-tbl').DataTable({
		paging : true,
		searching : false
	});
	
	$("#transfer-stock-side-option").addClass('active');
	$("#treeview-transaction").addClass('active');
	
	var role = "${employee.user.role.name}";
	if (role!=="ROLE_ADMIN") {
		$(".more-option").addClass('hidden');
	}
	
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
				console.log(data);
				$('#view-hidden-id').val(data.id);
				$('#view-created-by').text(data.createdBy.username);
				$('#view-status').text(data.status);
				$('#view-notes').val(data.notes);
				$('#view-status-history').val();
				$('#view-transfer-stock-detail-tbl').empty();
				var option = [];
				if (data.status=="Submitted") {
					option.push("<option value=\"Kosong\">Action</option>");
					option.push("<option value=\"Approved\">Approve</option>");
					option.push("<option value=\"Rejected\">Reject</option>");
					option.push("<option value=\"Print\">Print</option>");
				} else {
					option.push("<option value=\"Kosong\">Action</option>");
					option.push("<option value=\"Print\">Print</option>");
				}
				$('.more-option').html(option);
				$('.more-option').attr("id",id);
				$.ajax({
					url : '${pageContext.request.contextPath }/transaction/transfer-stock/search-transfer-stock-detail?search='+data.id,
					type : 'GET',
					dataType: 'json',
					success : function(data2){
						$.each(data2, function(key, val) {
						$('#view-transfer-stock-detail-tbl').append('<tr><td>'+ val.itemVariant.item.name +'-'+ val.itemVariant.name +'</td><td>'
								+ val.instock +'</td><td>'+ val.transferQty +'</td></tr>');
						});
						$.ajax({
							url : '${pageContext.request.contextPath }/transaction/transfer-stock/search-transfer-stock-history?search='+data.id,
							type : 'GET',
							dataType: 'json',
							success : function(data3){
								$('#view-status-history').empty();
								$.each(data3, function(key, val) {
								$('#view-status-history').append('<tr><td>On '+ getDateFormat(val.createdOn) +' - '+ val.status +'</td></tr>');
								});
								$('#modal-view-transfer-stock-detail').modal();
							}, error : function(){
								alert('get transfer stock detail failed');
							}
							
						})
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
	
	function getDateFormat(date) {
		var d = new Date(Number(date)),
		month = '' + (d.getMonth() + 1),
		day = '' + d.getDate(),
		year = d.getFullYear();

		if (month.length < 2)
		    month = '0' + month;
		if (day.length < 2)
		    day = '0' + day;
		var date = new Date();
		date.toLocaleDateString();

		return [year, month, day].join('-');
	};
	
	$('.more-option').change(function(){
		var newStatus = $(this).val();
		var id = $(this).attr('id');
		if (newStatus=="Approved") {
			transferStockId = $('#view-hidden-id').val();
			$.ajax({
				url : '${pageContext.request.contextPath }/transaction/transfer-stock/update-status-and-stock/'+transferStockId,
				type : 'PUT',
				data : JSON.stringify(newStatus),
				contentType : 'application/json',
				success : function(){
					alert('update status successfully');
					window.location='${pageContext.request.contextPath}/transaction/transfer-stock';
				}, error : function(){
					alert('update status failed');
				}
				
			})
		} else if (newStatus=="Rejected") {
			transferStockId = $('#view-hidden-id').val();
			$.ajax({
				url : '${pageContext.request.contextPath }/transaction/transfer-stock/update-status/'+transferStockId,
				type : 'PUT',
				data : JSON.stringify(newStatus),
				contentType : 'application/json',
				success : function(){
					alert('update status successfully');
					window.location='${pageContext.request.contextPath}/transaction/transfer-stock';
				}, error : function(){
					alert('update status failed');
				}
				
			})
		}else if (newStatus=="Print") {
			window.location = '${pageContext.request.contextPath}/generate/ts-detail/'+id; 
		}
	})
	
	$('#create').click(function(){
		$('#modal-add-transferStock').modal();
		if (document.getElementById("add-transferStock-tbl").rows.length>0) {
			document.getElementById("btn-save").disabled = false;
		}else {
			document.getElementById("btn-save").disabled = true;
		}
	})
	
	$('#btn-add-transfer-item').click(function(){
		$('#modal-add-transfer-item').modal();
	})
	
	$('#btn-save1').click(function(){
		var outId = "${outlet.id}";
		var userId = "${employee.user.id}";
		if (outId==$('#add-to-outlet').val()) {
			alert("you can't transfer stock to same outlet")
		} else {
			var transferStockDetail=[];
			
			$('#add-transferStock-tbl > tr').each(function(index, data){
				var tsd = {
						itemVariant : {
							id : $(data).find('td').eq(0).attr('id')
						},
						itemInventory :{
							id : $(data).find('td').eq(1).attr('id')
						},
						instock : $(data).find('td').eq(1).text(),
						transferQty : $(data).find('td').eq(2).text(),
						createdBy : {
							id : userId
						},
						modifiedBy : {
							id : userId
						}
				}
				transferStockDetail.push(tsd);
			});
			
			var transferStock = {
					fromOutlet : {
						id : outId
					},
					toOutlet : {
						id : $('#add-to-outlet').val()
					},
					transferStockDetail : transferStockDetail,
					notes : $('#add-notes').val(),
					status : "Submitted",
					createdBy : {
						id : userId
					},
					modifiedBy : {
						id : userId
					}
			};
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
		}
	})
	var toOutletIdSearch = "${toOutletId}";
	if (toOutletIdSearch!=="kosong") {
		$('#outlet-search').val(toOutletIdSearch);
	}
	$('#outlet-search').change(function(){
		var word = $(this).val();
		if (word=="all") {
			window.location = "${pageContext.request.contextPath}/transaction/transfer-stock";
		} else if (word!=="kosong") {
			window.location = "${pageContext.request.contextPath}/transaction/transfer-stock/search-outlet?search="+word;
		}
	})
	//
	$('body').on('click', 'button.btn-add-item', function(){
		var id = $(this).attr('id');
		var inStock = parseInt($('#inStock'+id).text());
		var transQtyString = $('.add-transfer-stock-qty'+id).val();
		var transQty = parseInt($('.add-transfer-stock-qty'+id).val());
		if (transQty>inStock) {
			alert("not enough stock");
		} else if(transQtyString == ""){
			alert("transfer qty can't be empty");
		}
		else if (transQty<1) {
			alert("at least 1");
		}else {
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
					$('#add-transferStock-tbl').append('<tr id="tr-transferStock'+ data.id +'"><td id="'+ data.itemVariant.id +'">'+ data.itemVariant.item.name +'-'+ data.itemVariant.name +'</td><td id="'+ data.id +'">'
							+ data.endingQty +'</td><td>'+ transQty +'</td><td><button type="button" id="'+ data.id +'" class="btn-cancel-item'
							+ data.id +' btn-cancel-item btn btn-primary">Cancel</button></td></tr>');
				},
				error : function(){
					alert('get one item inventory failed');
				}
			})
		}
	})
	
	$('body').on('click', 'button.btn-cancel-item', function(){
		var id = $(this).attr('id');
		$('#tr-transferStock'+id).remove();
		$('.btn-added-item'+id).hide();
		$('.btn-add-item'+id).show();
		$('#td-qty'+id).html('<input type="number" class="add-transfer-stock-qty'+ id +'" value="1" />');
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
		if (word=="") {
			$('#add-item-transfer-tbl').empty();
		} else {
			$.ajax({
				type : 'GET',
				url : '${pageContext.request.contextPath}/transaction/transfer-stock/search-item?search='+word,
				dataType: 'json',
				success : function(data){
					$('#add-item-transfer-tbl').empty();
					$.each(data, function(key, val) {
						if(added.indexOf(val.id.toString()) == -1) {
							$('#add-item-transfer-tbl').append('<tr><td>'+ val.itemVariant.item.name +'-'+ val.itemVariant.name +'</td><td id="inStock'+ val.id +'">'
									+ val.endingQty +'</td><td id="td-qty'+ val.id +'"><input type="number" class="add-transfer-stock-qty'+ val.id +'" value="1" /></td><td><button type="button" id="'+ val.id +'" class="btn-add-item'
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
		}
	})
	
	$('#export').click(function(){
		window.location = '${pageContext.request.contextPath}/generate/ts'; 
	})
});
</script>
</html>