<!-- Modal -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal fade" id="modal-view-transfer-stock-detail" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Transfer Stock Detail</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<select class="form-control" name="more" id="more-option">
					</select>
				</div>
				<form class="form-all">
					<input type="hidden" id="view-hidden-id">
					<div class="form-group">
						<label for="view-created-by">Created By</label> <input type="text"
							class="form-control" id="view-created-by" disabled>
					</div>
					<div class="form-group">
						<label for="view-status">Transfer Status</label> <input type="text"
							class="form-control" id="view-status" disabled>
					</div>
					<div class="form-group">
						<label for="view-name">Notes</label> <input type="text"
							class="form-control" id="view-notes" disabled>
					</div>
					<div class="form-group">
						<label for="view-status-history">Status History</label> <input type="text"
							class="form-control" id="view-status-history" disabled>
					</div>
					<div>
						<table class="table table-striped table-bordered" cellspacing="0" width="100%">
							<thead>
								<th>Item</th>
								<th>In Stock</th>
								<th>Trans. Qty</th>
							</thead>
							<tbody id="view-transfer-stock-detail-tbl">
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