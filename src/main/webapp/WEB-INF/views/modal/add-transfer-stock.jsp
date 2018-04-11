<!-- Modal -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal fade" id="modal-add-transferStock" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h3 class="modal-title" id="exampleModalLabel">Add New Transfer Stock</h3>
			</div>
			<div class="modal-body">
				<form class="form-add-ts">
					<div class="form-group">
						<p>Create New Transfer Stock From: <a style="font-weight: bold;">${outlet.name}</a></p>
					</div>
					<div class="form-group">
						<label for="add-to-outlet">To Outlet</label> <select
							class="form-control" name="add-to-outlet" id="add-to-outlet">
							<c:forEach var="outlets" items="${outlets }">
								<script>
									var fromOutletId = "${outlet.id}";
									var toOutletId = "${outlets.id}";
									if (fromOutletId!==toOutletId) {
										document.write("<option value="+toOutletId+">"+"${outlets.name}" +"</option>");
									}
								</script>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="add-name">Notes</label> <textarea type="text"
							class="form-control" id="add-notes"  rows="5" placeholder="Notes. . ." required=""></textarea>
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
				<input type="hidden" id="btn-save1"/>
				<button type="button" id="btn-save" class="btn btn-primary">Save & Submit</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$('#btn-save').click(function(){
		var ok = $('.form-add-ts').parsley().validate();
		if(ok){
			$('#btn-save1').click();
		}
	})
</script>