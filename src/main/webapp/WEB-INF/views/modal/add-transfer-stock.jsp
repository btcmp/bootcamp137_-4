<!-- Modal -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal fade" id="modal-add-transferStock" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Add New Transfer Stock</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form class="form-all">
					<div class="form-group">
						<label for="add-from-outlet">From Outlet</label> <select
							class="form-control" name="add-from-outlet" id="add-from-outlet">
							<c:forEach var="outlet" items="${outlets }">
								<option value="${outlet.id }">${outlet.name }</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="add-to-outlet">To Outlet</label> <select
							class="form-control" name="add-to-outlet" id="add-to-outlet">
							<c:forEach var="outlet" items="${outlets }">
								<option value="${outlet.id }">${outlet.name }</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="add-name">Notes</label> <input type="text"
							class="form-control" id="add-notes" placeholder="input notes">
					</div>
					<div>
						<table class="table table-striped table-bordered" cellspacing="0" width="100%">
							<thead>
								<th>Item</th>
								<th>In Stock</th>
								<th>Trans. Qty</th>
								<th>Action</th>
							</thead>
							<tbody id="add-transferStock-tbl">
							</tbody>
						</table>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" id="btn-add-transfer-item" class="form-control btn btn-primary">Add Transfer Item</button>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" id="btn-save" class="btn btn-primary">Save & Submit</button>
			</div>
		</div>
	</div>
</div>