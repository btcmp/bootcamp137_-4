<!-- Modal -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal fade" id="save-category" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="exampleModalLabel"><b>Add New Category</b></h4>
			</div>
			<div class="modal-body">
				<form id="form-add-category">
					<div class="form-group">
						<label for="save-con">Category Name</label> 
						<input type="text" class="form-control" id="entry-category" placeholder="Category Name" required data-parsley-minlength="4"/>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button"  id="btn-reset-add" class="btn btn-secondary" >Close</button>
				<button type="button" id="btn-save" class="btn btn-primary">Save</button>
			</div>
		</div>
	</div>
</div> <!-- a -->