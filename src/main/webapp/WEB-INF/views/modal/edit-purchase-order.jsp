<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

    
<div class="modal fade" id="modal-edit-po" data-modal-index="1">
  <div class="modal-dialog modal-md">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title">Purchase Order</h4>
      </div>
      <div class="modal-body">
      		<label for="select-outlet">Outlet Login</label>
			<select disabled class="selectpicker" name="select-outlet" id="select-outlet">
				<c:forEach var="outlet" items="${outlets }">
					<option value="${outlet.id }">${outlet.name }</option>
				</c:forEach>
			</select>
			
      	<form class="form-all">
			<div class="form-group" id = "edit-po-id">
                <br>
                <label for="select-outlet">Select Supplier</label>
                <select disabled class="selectpicker" name="select-supplier" id="select-supplier">
                    <c:forEach var="supplier" items="${suppliers }">
                        <option value="${supplier.id }">${supplier.name }</option>
                    </c:forEach>
                </select>
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

<!-- ================================================================================================================ -->

<div class="modal fade" id="modal-pr-detail" data-modal-index="1">
  <div class="modal-dialog modal-md">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title">Purchase Request Detail</h4>
      </div>
      <div class="modal-body">
      	<form class="form-all">
			<div class="form-group" id = "add-pr-id">
				<br>
				<p> PR Number   : </p>
				<p> Created By  : </p>
				<p> Target Time : </p>
				<p> PR Status   : </p>
				<label for="add-pr-notes">Notes</label><br>
				<textarea rows="4" id="add-pr-notes" placeholder=" Notes . . . " style="min-width: 100%"></textarea>
				<p></p>
			</div>
		</form>
		
			<label for="pr-detail">Purchase Request History</label>
			<p> PR is Created</p>
		    <label for="pr-detail">Purchase Item</label>
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