<!-- Modal -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal fade" id="modal-add-adjustment-item" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Add Item</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">


				<form class="form-all">
					<div class="form-group">
						<label for="search-item">Search Item - Variant</label> <input type="text"
							class="form-control" id="search-item-varian" placeholder="search items...">
					</div>
					<div>
						<table class="table table-striped table-bordered" cellspacing="0" width="100%">
							<thead>
								<th>Item</th>
								<th>Stock</th>
								<th>Adj. Qty</th>
								<th>Action</th>
							</thead>
							<tbody id="add-item-adjustment-tbl">
							</tbody>
						</table>
					</div>
				</form>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
</div>