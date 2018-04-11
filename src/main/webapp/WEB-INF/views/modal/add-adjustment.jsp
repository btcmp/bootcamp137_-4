<!-- Modal -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal fade" id="modal-add-adjustment" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Add New Adjustment</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form class="form-all">
					<div class="form-group">
						<label>Create New Adjustment : ${outlet.name }</label>
						</select>
					</div>
					 <div class="form-group">
						<label for="add-notes">Notes</label> <textarea type="text"
							class="form-control" id="add-notes" rows="5" placeholder="Notes. . ." required></textarea>
					</div> 

 					<div>	
						<table class="table table-striped table-bordered" cellspacing="0" width="100%">
							<thead>
								<th>Item</th>
								<th>In Stock</th>
								<th>Adj. Qty</th>
								<th>Action</th>
							</thead>
							<tbody id="add-adjustment-tbl">
							</tbody>
						</table>
					</div>  
					
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" id="btn-add-adjustment" class="form-control btn btn-primary">Add Adjustment</button>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" id="btn-save" class="btn btn-primary">Save & Submit</button>
			</div>
		</div>
	</div>
</div>