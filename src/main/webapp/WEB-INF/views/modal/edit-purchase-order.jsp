<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

    
<div class="modal fade" id="modal-edit-po" data-modal-index="1">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title">Purchase Order</h4>
      </div>
      <div class="modal-body">
      		<label for="select-outlet">Outlet Login</label>
			<select disabled class="selectpicker" name="select-outlet" id="select-outlet">
				
			</select>
			
      	<form class="form-all">
			<div class="form-group" id = "edit-po-id">
                <br>
                <label for="select-supplier">Select Supplier</label>
                <select class="selectpicker" name="select-supplier" id="select-supplier">
                    <c:forEach var="supplier" items="${suppliers }">
                        <option value="${supplier.id }">${supplier.name }</option>
                    </c:forEach>
                </select>
				<p></p>
				<label for="edit-po-notes">PO Notes</label><br>
				<textarea rows="4" id="edit-po-notes" placeholder=" Notes . . . " style="min-width: 100%"></textarea>
				<p></p>
			</div>
		</form>
		    <label for="edit-po">Purchase Order</label>
			<table id="table-edit-po" class="table table-bordered table-striped">
                <thead>
                	<th class="col-xl-3">Item</th>
					<th class="col-xl-3">In Stock</th>
					<th class="col-xl-3">Quantity </th>
					<th class="col-xl-3">Unit Cost </th>
					<th class="col-xl-3">Sub Total</th>
			
				</thead>
				<tbody id="table-edit-po-body">
				</tbody>
            </table> 
            <table id="table-edit-po-body-total" class="table table-bordered table-striped">
            </table>
            
      </div>
      <div class="modal-footer">
        <button id="btn-po-submit" state="create" type="button " class="btn btn-success">Submit</button>
        
        <button id="btn-po-save" state="create" type="button" class="btn btn-primary">Save changes</button>
        
        <button id="btn-po-cancel" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- ================================================================================================================ -->

<div class="modal fade" id="modal-po-detail" data-modal-index="1">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
            <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				<div class="col-xs-4" style="float:right;">
					<select id="select-po-action" class="btn form-control">
						<!-- <option disabled selected>More</option>
						<option value="approve">Approve</option>	
						<option value="reject">Reject</option>
						<option value="print">Print</option>
						<option value="create-po" disabled>Create PO</option> -->
					</select>
				</div>	
				
				<h4 class="modal-title">Purchase Order Detail</h4>
				
			</div>

			<div class="modal-body">
      	<form class="form-all">
			<div class="form-group" id = "view-po-detail-id">
				<div id="detail-supplier-name" class="col-xs-12">Hi</div>
				<div id="detail-supplier-phone" class="col-xs-12"></div>
				<div id="detail-supplier-email" class="col-xs-12"></div>
				<div id="detail-supplier-address" class="col-xs-12"></div>
				<div id="detail-supplier-region" class="col-xs-12"></div>
				<div id="detail-supplier-province" class="col-xs-12"></div>
				<div id="detail-supplier-postalCode" class="col-xs-12"></div>
				<br>
				<div class="col-xs-12">
					<label for="add-po-notes">Notes</label><br>
					<textarea disabled rows="4" id="view-po-detail-notes" placeholder=" Notes . . . " style="min-width: 100%"></textarea>
					
					
				</div>
					<div class="col-xs-3"> Created by   :</div> <div id="view-pod-created" class="col-xs-9"> </div><br>
					<!-- <div class="col-xs-3"> Email  :</div> <div id="view-pod-email" class="col-xs-9"> </div><br>
					<div class="col-xs-3"> Outlet   :</div> <div id="view-pod-outlet" class="col-xs-9"> </div><br>
					<div class="col-xs-3"> Phone  :</div> <div id="view-pod-phone" class="col-xs-9"> </div><br>
					<div class="col-xs-3"> Address   :</div> <div id="view-pod-address" class="col-xs-9"> </div><br>
					 -->
					 <div class="col-xs-3"> PO Status   :</div> <div id="view-pod-status" class="col-xs-9"> </div><br>

				<p></p>
			</div>
			
			<label for="po-history">Status History</label>
			<div class="container">
				
				<table id="table-po-history" class="table col-xl-12">
				</table>
				
			</div>
				
			    <label for="po-detail">Purchase Item</label>
				<table id="table-po-detail" class="table table-bordered table-striped">
					<thead>
						<th class="col-xl-3">Item</th>
						<th class="col-xl-3">In Stock</th>
						<th class="col-xl-3">Request Quantity </th>	
					</thead>
					<tbody id="table-po-detail-body">
					</tbody>
				</table>	
		</form>
				
			
        
      </div>
      <div class="modal-footer">
        <button id="btn-pr-cancel" type="button" class="btn btn-default" data-dismiss="modal">Done</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->