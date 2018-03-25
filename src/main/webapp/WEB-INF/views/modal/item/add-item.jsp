<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

    
<div class="modal fade" id="modal-addItem" data-modal-index="1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title">Add New Item</h4>
      </div>
      <div class="modal-body">
      	<form class="form-all">
			<div class="form-group">
				<label for="add-item-name">Item Name</label>
				<input type="text" class="form-control" id="add-item-name" placeholder="Item name">
				<p></p>
				<label for="add-category">Category</label>
				<select class="form-control" name="add-category" id="add-category">
					<c:forEach var="category" items="${categories }">
						<option value="${category.id }">${category.name }</option>
					</c:forEach>
				</select>
				<p></p>
			</div>
		</form>
		<label for="add-item-variant">Variant</label>	
		<button style="float:right;" id="btn-add-variant" class="btn btn-primary" data-toggle="modal" data-target="#modal-addVariant">Add Variant</button>
        
			<table id="table-variant" class="table table-bordered table-striped">
                <thead>
					<th class="col-md-3">Variant Name</th>
					<th class="col-md-2">Unit Price </th>
					<th class="col-md-2">SKU </th>
					<th class="col-md-3">Beginning Stock </th>
					<th class="col-md-3">Stock Alert </th>
					<th class="col-md-2">Action</th>
				</thead>
				<tbody id="table-body-variant">
				</tbody>
            </table>
        
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button id="btn-add-item" state="create" type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<div class="modal fade" id="modal-addVariant" data-modal-index="2">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title">Add Item Variant</h4>
      </div>
      <div class="modal-body">
      		<form class="form-all">
			<div class="form-group">
				<label for="add-variant-name">Variant Name</label>
				<input type="text" class="form-control" id="add-variant-name" placeholder="Item Name"> <p></p>
				<label for="add-unit-price">Unit Price</label>
				<input type="text" class="form-control" value= "0" id="add-unit-price" placeholder="Unit Price"> <p></p>
				<label for="add-sku">SKU</label>
				<input type="text" class="form-control" id="add-sku" placeholder="SKU"> <p></p>
				<hr>
				<label for="add-beginning-stock">Beginning Stock</label>
				<input type="text" class="form-control" id="add-beginning-stock" placeholder="Beginning Stock"> <p></p>
				<label for="add-alert-at">Alert At</label>
				<input type="text" class="form-control" id="add-alert-at" placeholder="Alert At">
				
			</div>
		</form>
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
        <button id="btn-save-variant" state="create" type="button" class="btn btn-primary">Add</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- ================================================================================================================ -->