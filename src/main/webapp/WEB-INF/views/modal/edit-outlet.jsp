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
				<form class="form-all">
					<input type="hidden" id="edit-id">
					<input type="hidden" id="edit-createdOn">
					<div class="form-group">
						<label for="edit-name">Outlet Name</label> <input type="text"
							class="form-control" id="edit-name" placeholder="Oulet Name">
					</div>
					<div class="form-group">
						<label for="edit-address">Address</label> <input type="text"
							class="form-control" id="edit-address" placeholder="Address">
					</div>
					<div class="form-group">
						<label for="edit-phone">Phone</label> <input type="text"
							class="form-control" id="edit-phone" placeholder="Phone">
					</div>
					<div class="form-group">
						<label for="edit-email">Email</label> <input type="text"
							class="form-control" id="edit-email" placeholder="Email">
					</div>
					<div class="form-group">
						<label for="edit-province">Province</label> <select
							class="form-control" name="edit-province" id="edit-province">
							<option value="">Choose Province</option>
							<c:forEach var="province" items="${provinces }">
								<option value="${province.id }">${province.name }</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="edit-region">Region</label> <select
							class="form-control" name="edit-region" id="edit-region">
							<option value="">Choose Region</option>
							<c:forEach var="region" items="${regions }">
								<option value="${region.id }">${region.name }</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="edit-district">District</label> <select
							class="form-control" name="edit-district" id="edit-district">
							<option value="">Choose District</option>
							<c:forEach var="district" items="${districts }">
								<option value="${district.id }">${district.name }</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="edit-postalCode">Postal Code</label> <input type="text"
							class="form-control" id="edit-postalCode" placeholder="Postal Code">
					</div>
<!-- 					<div class="checkbox">
						<label for="edit-active"><input id="edit-active" type="checkbox"
							value="true">Active</label>
					</div> -->
				</form>
			</div>
			<div class="modal-footer">
				<button style="float:left;" type="button" id="btn-delete" class="btn btn-danger">X</button>
				<button type="button" id="btn-reset" class="btn btn-secondary" >Close</button>
				<button type="button" id="btn-edit" class="btn btn-primary"
					data-dismiss="modal">Update</button>
			</div>
		</div>
	</div>
</div>