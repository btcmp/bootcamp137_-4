<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

    
<div class="modal fade" id="modal-add-pr" data-modal-index="1">
  <div class="modal-dialog modal-md">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title">Purchase Request</h4>
      </div>
      <div class="modal-body">
      		<label for="select-outlet">Outlet Login</label>
			<select disabled class="selectpicker" name="select-outlet" id="select-outlet">
			</select>
			
      	<form class="form-all">
			<div class="form-group" id = "add-pr-id">
				<br>
				<label for="add-pr-date">Item Ready Target Date</label>
				<input type="date" class="form-control" id="add-pr-date" placeholder="Date">
				<p></p>
				<label for="add-pr-notes">PR Notes</label><br>
				<textarea rows="4" id="add-pr-notes" placeholder=" Notes . . . " style="min-width: 100%"></textarea>
				<p></p>
			</div>
		</form>
		    <label for="add-pr">Purchase Request</label>
			<table id="table-add-pr" class="table table-bordered table-striped">
                <thead>
                	<th class="col-xl-3">Item</th>
					<th class="col-xl-3">In Stock</th>
					<th class="col-xl-3">Request Quantity </th>
					<th class="col-xl-3"> # </th>
                	
				</thead>
				<tbody id="table-add-pr-body">
				</tbody>
            </table>
        <button id="btn-add-item-variant" class="btn btn-primary btn-block" data-toggle="modal" data-target="#modal-add-item-variant">Add Item</button>
        
      </div>
      <div class="modal-footer">
        <button id="btn-pr-submit" state="create" type="button " class="btn btn-success">Submit</button>
        
        <button id="btn-pr-save" state="create" type="button" class="btn btn-primary">Save changes</button>
        
        <button id="btn-pr-cancel" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<div class="modal fade" id="modal-add-item-variant" data-modal-index="2">
  <div class="modal-dialog modal-xs">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title">Add Item</h4>
      </div>
      <div class="modal-body">
      	<form class="form-all">
			<div class="form-group">
				<label for="search-item-variant">Search Item - Variant</label> 
				<input type="text" class="form-control" id="search-item-variant" placeholder="Search items ">
				<br>
				<table id ="table-add-item-variant"class="table table-striped table-bordered" cellspacing="0" width="100%">
					<thead>
						<th>Item</th>
						<th>Stock</th>
						<th>Request Quantity</th>
						<th>Action</th>
					</thead>
					<tbody id="table-add-item-variant-body">
						
					</tbody>
				</table>
				
			</div>
		</form>
        
      </div>
      <div class="modal-footer">
        <button id="btn-cancel-variant"type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <!-- <button id="btn-save-variant" state="create" type="button" class="btn btn-primary">Add</button>-->      
 		</div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- ================================================================================================================ -->

<div class="modal fade" id="modal-pr-detail" data-modal-index="1">
  <div class="modal-dialog modal-md">
    <div class="modal-content">
      <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				<div class="col-xs-4" style="float:right;">
					<select id="select-pr-action" class="btn form-control">
						<option disabled selected>More</option>
						<option value="approve">Approve</option>	
						<option value="reject">Reject</option>
						<option value="print">Print</option>
						<option value="create-po" disabled>Create PO</option>
					</select>
				</div>	
				
				<h4 class="modal-title">Purchase Request Detail</h4>
				
			</div>

			<div class="modal-body">
      	<form class="form-all">
			<div class="form-group" id = "view-pr-detail-id">
				<br>
				<div class="col-xs-3"> PR Number   :</div> <div id="view-prd-num" class="col-xs-9"> PR Num</div><br>
				<div class="col-xs-3"> Created by   :</div> <div id="view-prd-created" class="col-xs-9"> PR Num</div><br>
				<div class="col-xs-3"> Item Ready Item   :</div> <div id="view-prd-time" class="col-xs-9"> PR Num</div><br>
				<div class="col-xs-3"> PR Status   :</div> <div id="view-prd-status" class="col-xs-9"> PR Num</div><br>
				
				<div class="col-xs-12">
					<label for="add-pr-notes">Notes</label><br>
					<textarea disabled rows="4" id="view-pr-detail-notes" placeholder=" Notes . . . " style="min-width: 100%"></textarea>
				
				</div>
				<p></p>
			</div>
		</form>
			<div class="col-xs-12">
				<label for="pr-detail">Purchase Request History</label>
				<p> PR is Created</p>
			    <label for="pr-detail">Purchase Item</label>
				
			</div>
			<table id="table-pr-detail" class="table table-bordered table-striped">
				<thead>
					<th class="col-xl-3">Item</th>
					<th class="col-xl-3">In Stock</th>
					<th class="col-xl-3">Request Quantity </th>	
				</thead>
				<tbody id="table-pr-detail-body">
				</tbody>
			</table>
        
      </div>
      <div class="modal-footer">
        
        <button id="btn-pr-cancel" type="button" class="btn btn-default" data-dismiss="modal">Done</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->