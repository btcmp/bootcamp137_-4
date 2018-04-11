<!-- Modal -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal fade" id="modal-view-transfer-stock-detail" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button> -->
				<div class="form-group pull-right">
					<select class="form-control more-option" name="more">
					</select>
				</div>
				<h3 class="modal-title" id="exampleModalLabel">Transfer Stock Detail</h3>
			</div>
			<div class="modal-body">
				<form class="form-all">
					<input type="hidden" id="view-hidden-id">
					<div>
						<table>
							<tr>
								<td style="font-weight: bold;">Created By </td>
								<td> : </td>
								<td id="view-created-by"></td></tr>
							<tr>
								<td style="font-weight: bold;">Transfer Status </td>
								<td> : </td>
								<td id="view-status"></td></tr>
						</table>
						<br/>
					</div>
					<div class="form-group">
						<label for="view-name">Notes</label> <textarea type="text"
							class="form-control" id="view-notes" disabled></textarea>
					</div>
					<div class="form-group">
						<label for="view-status-history">Status History</label>
							<table id="view-status-history">
							</table>
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