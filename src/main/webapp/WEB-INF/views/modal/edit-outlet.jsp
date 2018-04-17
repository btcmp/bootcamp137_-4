<!-- Modal -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal fade" id="edit-outlet" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				 <button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="exampleModalLabel"><b>Edit Outlet</b></h4> 
			</div>
			<div class="modal-body">
				<form id="form-edit-outlet" class="form-all">
					<input type="hidden" id="edit-id">
					<input type="hidden" id="edit-createdOn">
					<input type="hidden" id="edit-createdBy">
					<div class="form-group">
						<label for="edit-name">Outlet Name</label> <input type="text"
							class="form-control" id="edit-name" placeholder="Oulet Name" required data-parsley-minlength="4">
					</div>
					<div class="form-group">
						<label for="edit-address">Address</label> <input type="text"
							class="form-control" id="edit-address" placeholder="Address" required data-parsley-minlength="4">
					</div>
					<div class="form-group">
						<label for="edit-phone">Phone</label> <input type="number"
							class="form-control" id="edit-phone" placeholder="Phone" required data-parsley-length="[11, 14]">
					</div>
					<div class="form-group">
						<label for="edit-email">Email</label> <input type="text"
							class="form-control" id="edit-email" placeholder="Email" required data-parsley-type="email">
					</div>
					<div class="form-group">
						<label for="edit-province">Province</label> <select
							class="form-control" name="edit-province" id="edit-province" required>
							<option value="">Choose Province</option>
							<c:forEach var="province" items="${provinces }">
								<option value="${province.id }">${province.name }</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="edit-region">Region</label> <select
							class="form-control" name="edit-region" id="edit-region" required>
							<option value="">Choose Region</option>
							<c:forEach var="region" items="${regions }">
								<option value="${region.id }">${region.name }</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="edit-district">District</label> <select
							class="form-control" name="edit-district" id="edit-district" required>
							<option value="">Choose District</option>
							<c:forEach var="district" items="${districts }">
								<option value="${district.id }">${district.name }</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="edit-postalCode">Postal Code</label> <input type="text"
							class="form-control" id="edit-postalCode" placeholder="Postal Code" required data-parsley-maxlength="6">
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" id="btn-edit" class="btn btn-primary">Update</button>
			</div>
		</div>
	</div>
</div>