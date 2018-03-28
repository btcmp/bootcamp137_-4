<!-- Modal -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal fade" id="edit-employee" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Edit Employee</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form class="form-all">
					<input type="hidden" id="edit-id">
					<input type="hidden" id="edit-createdOn">
					<div class="form-group">
						<label for="edit-firstName">First Name</label> <input type="text"
							class="form-control" id="edit-firstName" placeholder="First Name">
					</div>
					<div class="form-group">
						<label for="edit-lastName">Last Name</label> <input type="text"
							class="form-control" id="edit-lastName" placeholder="Last Name">
					</div>
					<div class="form-group">
						<label for="edit-email">Email</label> <input type="email"
							class="form-control" id="edit-email" placeholder="Email">
					</div>
					<div class="form-group">
						<label for="edit-title">Title</label> <select
							class="form-control" name="edit-title" id="edit-title">
							<option value="">Choose Title</option>
							<option value="Mr.">Mr.</option>
							<option value="Mrs.">Mrs.</option>														
						</select>
					</div>
					<!-- <div class="form-group">
					<div class="col-sm-3">
						<button type="button" id="btn-assign-outlet"
							class="add-outlet btn btn-primary btn-block">Assign Outlet</button>
					</div></div> -->
					
					
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" id="btn-edit" class="btn btn-primary"
					data-dismiss="modal">Update</button>
			</div>
		</div>
	</div>
</div>