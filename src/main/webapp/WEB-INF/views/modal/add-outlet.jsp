<!-- Modal -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal fade" id="save-outlet" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="exampleModalLabel"><b>Add New Outlet</b></h4>
			</div>
			<div class="modal-body">


				<form id="form-add-outlet" class="form-all">
					<div class="form-group">
						<label for="entry-name">Outlet Name</label> <input type="text"
							class="form-control" id="entry-name" placeholder="Outlet Name" required>
					</div>
					<div class="form-group">
						<label for="entry-address">Address</label> <input type="text"
							class="form-control" id="entry-address" placeholder="Address" required>
					</div>
					<div class="form-group">
						<label for="entry-phone">Phone</label> <input type="number"
							class="form-control" id="entry-phone" placeholder="Phone" required data-parsley-length="[11, 14]">
					</div>
					<div class="form-group">
						<label for="entry-email">Email</label> <input type="text"
							class="form-control" id="entry-email" placeholder="Email" required data-parsley-type="email">
					</div>
					<div class="form-group">
						<label for="entry-province">Province</label> 
						<select	class="form-control" name="entry-province" id="entry-province" required>
							<option value="">Choose Province</option>
							<c:forEach var="province" items="${provinces }">
								<option value="${province.id }">${province.name }</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="entry-region">Region</label> 
						<select class="form-control" name="entry-region" id="entry-region" required>
								<option value="">Choose Region</option>
						</select>
					</div>
					<div class="form-group">
						<label for="entry-district">District</label> 
						<select class="form-control" name="entry-district" id="entry-district" required>
								<option value="">Choose District</option>
						</select>
					</div>
					<div class="form-group">
						<label for="entry-postalCode">Postal Code</label> 
						<input type="text" class="form-control" id="entry-postalCode" placeholder="Postal Code" required data-parsley-maxlength="6">
					</div>
<!-- 					<div class="checkbox">
						<label id="entry-active"><input id="active" type="checkbox"
							value="true">Active</label>
					</div> -->
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" id="btn-reset" class="btn btn-secondary" >Close</button>
				<button type="button" id="btn-entry" class="btn btn-primary">Save</button>
			</div>
		</div>
	</div>
</div>