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

  <%@ include file="template/template.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
  
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h2 style="text-align: center;">
        Adjustment
      </h2>
      <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/master"><i class="fa fa-dashboard"></i>Transaction</a></li>
        <li class="active">Adjustment</li>
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
        <div class="col-xs-7">
        <a id="create-adjustment" class="btn btn-primary">Create</a>
      </div>
      <div class="col-xs-1">
        <a class="btn btn-primary">Export</a>
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
              <table id="adjust-tbl" class="table table-bordered table-striped">
				<thead>
					<th>Adjustment Date</th>
					<th>Notes</th>
					<th>Status</th>
					<th>Action</th>
				</thead>
				<tbody>
				<c:forEach items="${adjustments }" var="adjust">
					<tr>
						<td>${adjust.createdOn }</td>
						<td>${adjust.notes }</td>
						<td>${adjust.status }</td>
						<td>
							<a id="${adjust.id }" class="view btn btn-success" style="color:white;">View</a>
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
	<%@ include file="modal/add-adjustment.jsp" %>
	<%@ include file="modal/add-adjustment-item.jsp" %>
	<%@ include file="modal/view-adjustment.jsp" %>
</div>



</body>

<script type="text/javascript">
	$(function() {
		//data-tabel
		$('#adjust-tbl').DataTable({
			paging : true,
			searching:false	
		});
		
		$("#adjustment-side-option").addClass('active');
		$("#treeview-transaction").addClass('active');

//modal create adjustment
		$('#create-adjustment').on('click', function() {
			$('#modal-add-adjustment').modal();
			document.getElementById("btn-save").disabled = true;
		})

//modal add item adjustment		
		$('#btn-add-adjustment').click(function(){
			$('#modal-add-adjustment-item').modal();
		})

	var added = [];
	var addedQty = [];
	$('.btn-added-item').hide();		
		
//view adjustment detail
/* 	$('.view').click(function(){
		var id = $(this).attr('id');
		$.ajax({
			type : 'GET',
			url : '${pageContext.request.contextPath}/transaction/adjustment/get-one/'+id,
			dataType: 'json',
			success : function(data){
				$('#view-outlet').val(data.outlet.name);
				$('#view-status').val(data.status);
				$('#view-notes').val(data.notes);
				$('#view-adjustment-tbl').empty();
				$.ajax({
					url : '${pageContext.request.contextPath }/transaction/adjustment/search-adjustment-detail?search='+data.id,
					type : 'GET',
					dataType: 'json',
					success : function(data2){
						//console.log(data2);
						$.each(data2, function(key, val) {
						$('#view-adjustment-tbl').append('<tr><td>'+ val.itemVariant.item.name +'-'+ val.itemVariant.name +'</td><td>'
								+ val.inStock +'</td><td>'+ val.actualStock +'</td></tr>');
						});
						//call modal
						$('#modal-view-adjustment').modal();
					}, error : function(){
						alert('get transfer stock detail failed');
					}
					
				})			
				
			}, 
			error : function(){
				alert('show selected transferStock data in modal failed');
			}
		})
	}) */
	

	
//button save 
		$('#btn-save').click(function(){

//isi adjustment detail
			var adjustmentDetail=[];
			$('#add-adjustment-tbl > tr').each(function(index, data){
				var adjDet = {
						itemVariant : {
							id : $(data).find('td').eq(0).attr('id')
						},
						inStock : $(data).find('td').eq(1).text(),
						actualStock : $(data).find('td').eq(2).text()
				}
				adjustmentDetail.push(adjDet);
			});
			
//isi adjustment history			
/* 			var adjustmentHistory = {
				
			} */
			
			
			
//isi adjustment
			var adjustment = {
					outlet : {
						id : $('#add-outlet').val()
					},
					adjustmentDetails : adjustmentDetail, 
					notes : $('#add-notes').val(),
					status : "Submitted"
			};
			$.ajax({
				url : '${pageContext.request.contextPath }/transaction/adjustment/save',
				type : 'POST',
				data : JSON.stringify(adjustment),
				contentType : 'application/json',
				success : function(){
					alert('save successfully');
					window.location='${pageContext.request.contextPath}/transaction/adjustment';
				}, error : function(){
					alert('save failed');
				}
			})
	})

//button add item-variant
	$('body').on('click', 'button.btn-add-item', function(){
		var id = $(this).attr('id');
		var inStock = parseInt($('#inStock'+id).text());
		var actualStock = parseInt($('.add-adjustment-qty'+id).val());
		if (actualStock > inStock) {
			alert("not enough stock");
		} else if (actualStock < 1) {
			alert("at least 1");
		}else {
			added.push(id);
			addedQty.push(actualStock);
			$('#td-qty'+id).html(actualStock);
			$(this).hide();
			$('.btn-added-item'+id).show();
			document.getElementById("btn-save").disabled = false;
			$.ajax({
				type : 'GET',
				url : '${pageContext.request.contextPath}/transaction/adjustment/get-one-item/'+id,
				dataType: 'json',
				success : function(data){
					$('#add-adjustment-tbl').append('<tr id="tr-adjustment'+ data.id +'"><td id="'+ data.itemVariant.id +'">'+ data.itemVariant.item.name +'-'+ data.itemVariant.name +'</td><td>'
							+ data.endingQty +'</td><td>'+ actualStock +'</td><td><button type="button" id="'+ data.id +'" class="btn-cancel-item'
							+ data.id +' btn-cancel-item btn btn-primary">Cancel</button></td></tr>');
				},
				error : function(){
					alert('get one item inventory failed');
				}
			})
		}
	})

//button cancel item-variant
	$('body').on('click', 'button.btn-cancel-item', function(){
		var id = $(this).attr('id');
		$('#tr-adjustment'+id).remove();
		$('.btn-added-item'+id).hide();
		$('.btn-add-item'+id).show();
		$('#td-qty'+id).html('<input type="number" class="add-adjustment-qty'+ id +'" value="1" />');
		var a = added.indexOf(id.toString());
		added.splice(a,1);
		addedQty.splice(a,1);
		if (document.getElementById("add-adjustment-tbl").rows.length>0) {
			document.getElementById("btn-save").disabled = false;
		}else {
			document.getElementById("btn-save").disabled = true;
		}
	})
		
//search auto reload item-variant		
	$('#search-item-varian').on('input',function(e){
		var word = $(this).val();
		if (word=="") {
			$('#add-item-adjustment-tbl').empty();
		} else {
			$.ajax({
				type : 'GET',
				url : '${pageContext.request.contextPath}/transaction/adjustment/search-item?search='+word,
				dataType: 'json',
				success : function(data){
					$('#add-item-adjustment-tbl').empty();
					$.each(data, function(key, val) {
						if(added.indexOf(val.id.toString()) == -1) {
							$('#add-item-adjustment-tbl').append('<tr><td>'+ val.itemVariant.item.name +'-'+ val.itemVariant.name +'</td><td id="inStock'+ val.id +'">'
									+ val.endingQty +'</td><td id="td-qty'+ val.id +'"><input type="number" class="add-adjustment-qty'+ val.id +'" value="1" /></td><td><button type="button" id="'+ val.id +'" class="btn-add-item'
									+ val.id +' btn-add-item btn btn-primary">Add</button><button type="button" id="'+ val.id +'" class="btn-added-item'
									+ val.id +' btn-added-item btn">Added</button></td></tr>');
							$('.btn-added-item'+val.id).hide();
						} else {
							var a = added.indexOf(val.id.toString());
							$('#add-item-adjustment-tbl').append('<tr><td>'+ val.itemVariant.item.name +'-'+ val.itemVariant.name +'</td><td>'
									+ val.endingQty +'</td><td id="td-qty'+ val.id +'">'+addedQty[a]+'</td><td><button type="button" id="'+ val.id +'" class="btn-add-item'
									+ val.id +' btn-add-item btn btn-primary">Add</button><button type="button" id="'+ val.id +'" class="btn-added-item'
									+ val.id +' btn-added-item btn">Added</button></td></tr>');
							$('.btn-add-item'+val.id).hide();
						}
					});
				}, 
				error : function(){
					$('#add-item-adjustment-tbl').empty();
					//alert('show selected transferStock data in modal failed');
				}
			})
		}
	})	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	});
</script>

</html>

