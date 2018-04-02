<!-- Modal -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal fade" id="modal-view-adjustment" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">View Transfer Stock</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form class="form-all">
					<div class="form-group">
						<label for="view-outlet">Outlet Name</label> <input type="text"
							class="form-control" id="view-outlet" disabled>
					</div>
					<div class="form-group">
						<label for="view-status">Status</label> <input type="text"
							class="form-control" id="view-status" disabled>
					</div>
					<div class="form-group">
						<label for="view-notes">Notes</label> <input type="text"
							class="form-control" id="view-notes" disabled>
					</div>
					<div>
						<table class="table table-striped table-bordered" cellspacing="0" width="100%">
							<thead>
								<th>Item</th>
								<th>In Stock</th>
								<th>Adj. Qty</th>
							</thead>
							<tbody id="view-adjustment-tbl">
							</tbody>
						</table>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>