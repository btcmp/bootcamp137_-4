<!-- Modal -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="modal fade" id="edit-category" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="exampleModalLabel"><b>Update Category</b></h4>
			</div>
			<div class="modal-body">
				<form id="form-view-category" action="#">
					<input type="hidden"  id="edit-id"/>
					<input type="hidden"  id="edit-createdOn"/>
					<input type="hidden"  id="edit-createdBy"/>					
					<div class="form-group" id="form-categoryName">
						<label for="edit-name">Category Name</label> 
						<input type="text" class="form-control" id="edit-name"  placeholder="Category Name" required data-parsley-minlength="4"/>
					</div>
				</form>

			<div class="modal-footer">
			<button style="float:left;" type="button" id="btn-delete" class="btn btn-danger" >X</button>
			<button type="button" id="btn-reset-view" class="btn btn-secondary">Close</button>
			<button type="button" id="btn-update" class="btn btn-primary float-right">Save</button>
			</div>
		</div>
	</div>
</div>

<!-- // -->