<!-- Modal -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal fade" id="modal-update-supplier" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Update Supplier</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form class="form-all">
					<input type="hidden" id="update-id">
					<input type="hidden" id="update-created-on">
					<div class="form-group">
						<label for="update-name">Supplier Name</label> <input type="text"
							class="form-control" id="update-name" placeholder="Supplier Name">
					</div>
					<div class="form-group">
						<label for="update-address">Address</label> <input type="text"
							class="form-control" id="update-address" placeholder="Address">
					</div>
					<div class="form-group">
						<label for="update-postal-code">Postal Code</label> <input type="text"
							class="form-control" id="update-postal-code" placeholder="Postal Code">
					</div>
					<div class="form-group">
						<label for="update-phone">Phone</label> <input type="text"
							class="form-control" id="update-phone" placeholder="Phone">
					</div>
					<div class="form-group">
						<label for="update-email">Email</label> <input type="text"
							class="form-control" id="update-email" placeholder="Email">
					</div>
					<div class="form-group">
						<label for="update-province">Province</label> <select
							class="form-control" name="update-province" id="update-province">
							<option value="">Choose Province</option>
							<c:forEach var="province" items="${provinces }">
								<option value="${province.id }">${province.name }</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="update-region">Region</label> <select
							class="form-control" name="update-region" id="update-region">
							<option value="">Choose Region</option>
							<c:forEach var="region" items="${regions }">
								<option value="${region.id }">${region.name }</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="update-district">District</label> <select
							class="form-control" name="update-district" id="update-district">
							<option value="">Choose District</option>
							<c:forEach var="district" items="${districts }">
								<option value="${district.id }">${district.name }</option>
							</c:forEach>
						</select>
					</div>
					<div class="checkbox">
						<label for="update-active"><input id="update-active" type="checkbox"
							value="true">Active</label>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" id="btn-update" class="btn btn-primary">Update</button>
			</div>
		</div>
	</div>
</div>