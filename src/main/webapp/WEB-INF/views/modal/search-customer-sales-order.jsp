<!-- Modal -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal fade" id="modal-search-customer-sales-order" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h3 class="modal-title" id="exampleModalLabel">Search Customer</h3>
			</div>
			<div class="modal-body">
				<form class="form-all">
					<div class="form-group">
						<input type="text" class="form-control" id="search-customer" placeholder="search custoomer">
					</div>
				</form>
			</div>
			<div>
				<table class="table table-striped table-bordered" cellspacing="0" width="100%">
					<tbody id="search-customer-tbl">
						
					</tbody>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" id="add-new-customer" class="btn btn-primary">Add New Customer</button>
			</div>
		</div>
	</div>
</div>