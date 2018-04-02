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

		<%@ include file="template/template.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">

			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h2 style="text-align: center;">Transfer Stock Detail</h2>
				<ol class="breadcrumb">
					<li><a href="${pageContext.request.contextPath}/master"><i
							class="fa fa-dashboard"></i> Master</a></li>
					<li class="active">Transfer-stock-detail</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content" style="background-color:;">
				<div class="row">
					<div class="col-xs-12">
						<!-- /.box -->
						<div class="box">
							<!-- /.box-header -->

							<div class="box-body">
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
				$('#view-hidden-id').val(data.id);
				$('#view-created-by').val(data.createdBy);
				$('#view-status').val(data.status);
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
				$('#more-option').html(option);
				$.ajax({
					url : '${pageContext.request.contextPath }/transaction/transfer-stock/search-transfer-stock-detail?search='+data.id,
					type : 'GET',
					dataType: 'json',
					success : function(data2){
						$.each(data2, function(key, val) {
						$('#view-transfer-stock-detail-tbl').append('<tr><td>'+ val.itemVariant.item.name +'-'+ val.itemVariant.name +'</td><td>'
								+ val.instock +'</td><td>'+ val.transferQty +'</td></tr>');
						});
						
						$('#modal-view-transfer-stock-detail').modal();
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
	
	$('#more-option').change(function(){
		var newStatus = $(this).val();
		if (newStatus=="Approved") {
			transferStockId = $('#view-hidden-id').val();
			$.ajax({
				url : '${pageContext.request.contextPath }/transaction/transfer-stock/update-status-and-stock/'+transferStockId,
				type : 'PUT',
				data : JSON.stringify(newStatus),
				contentType : 'application/json',
				success : function(){
					alert('update status successfully');
					window.location='${pageContext.request.contextPath}/transaction/transfer-stock/detail';
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
					window.location='${pageContext.request.contextPath}/transaction/transfer-stock/detail';
				}, error : function(){
					alert('update status failed');
				}
				
			})
		}else if (newStatus=="Print") {
			window.location='${pageContext.request.contextPath}/transaction/transfer-stock/detail';
		}
	})
});
</script>
</html>