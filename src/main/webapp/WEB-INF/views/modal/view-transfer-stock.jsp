<!-- Modal -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal fade" id="modal-view-transferStock" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h3 class="modal-title" id="exampleModalLabel">View Transfer Stock</h3>
			</div>
			<div class="modal-body">
				<form class="form-all">
					<div class="form-group">
						<label for="view-from-outlet">From Outlet</label> <input type="text"
							class="form-control" id="view-from-outlet" disabled>
					</div>
					<div class="form-group">
						<label for="view-to-outlet">To Outlet</label> <input type="text"
							class="form-control" id="view-to-outlet" disabled>
					</div>
					<div class="form-group">
						<label for="view-name">Notes</label> <input type="text"
							class="form-control" id="view-notes" disabled>
					</div>
					<div>
						<table class="table table-striped table-bordered" cellspacing="0" width="100%">
							<thead>
								<th>Item</th>
								<th>In Stock</th>
								<th>Trans. Qty</th>
							</thead>
							<tbody id="view-transferStock-tbl">
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