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
<title>Entry Transfer Stock Detail</title>
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
<h3>Transfer Stock Detail</h3>
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
		if (newStatus=="Approved" || newStatus=="Rejected") {
			transferStockId = $('#view-hidden-id').val();
			console.log(newStatus);
			console.log(transferStockId);
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
		} else if (newStatus=="Print") {
			window.location='${pageContext.request.contextPath}/transaction/transfer-stock/detail';
		}
	})
});
</script>
</html>