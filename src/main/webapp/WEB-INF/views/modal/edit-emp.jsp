<!-- Modal -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="modal fade" id="edit-employee" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			<h4 class="modal-title" id="exampleModalLabel"><b>Edit Employee</b></h4>					
			</div>
			<div class="modal-body">
				<form class="form-all">
					<input type="hidden" id="edit-id-emp">
					<input type="hidden" id="edit-createdOn-emp">
					<input type="hidden" id="edit-createdBy-emp">
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
					
					<div class="form-group">
						<div class="col-md-3">
							<button type="button" id="btn-assign-outlet-edit" class="edit-outlet btn btn-primary btn-block">Assign Outlet</button>
						</div>
						
						<div>	
							<input type="checkbox" id="edit-account" name="edit-account" ></input> Create Account?</a><br><br>
						</div> 
					</div>	
					
					
<!-- style="display: none" -->					
						<!-- FORM USER -->
	<div class="row" id="form-edit-user">
		<div class="col-md-4">
			<div class="form-group">
					<input type="hidden" id="edit-id-user">
					<input type="hidden" id="edit-createdOn-user">
					<input type="hidden" id="edit-createdBy-user">
				<select name="edit-role" id="edit-role" class="form-control custom-select custom-select-md" placeholder="Role">
					<option selected>Role</option>
					<c:forEach var="role" items="${roles }">
						<option value="${role.id }">${role.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="col-md-4">
			<div class="form-group">
				<input type="text" class="form-control" id="edit-username"
					placeholder="Username">
			</div>
		</div>
		<div class="col-md-4">
			<div class="form-group">
				<input type="password" class="form-control" id="edit-password"
					placeholder="Password">
			</div>
		</div>
	</div>
					
					
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" id="btn-edit-emp" class="btn btn-primary"
					data-dismiss="modal">Update</button>
			</div>
		</div>
	</div>
</div>