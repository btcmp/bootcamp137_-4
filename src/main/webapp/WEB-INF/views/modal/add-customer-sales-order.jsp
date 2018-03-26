<!-- Modal -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal fade" id="modal-add-customer-sales-order" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Add New Supplier</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">


				<form class="form-all">
					<div class="form-group">
						<label for="add-name">Name</label> <input type="text"
							class="form-control" id="add-name" placeholder="Name">
					</div>
					<div class="form-group">
						<label for="add-email">Email</label> <input type="text"
							class="form-control" id="add-email" placeholder="Email">
					</div>
					<div class="form-group">
						<label for="add-phone">Phone</label> <input type="text"
							class="form-control" id="add-phone" placeholder="Phone">
					</div>
					<div class="form-group">
						<label for="add-phone">Day Of Birth</label> <input type="date"
							class="form-control" id="add-dob" placeholder="day of birth">
					</div>
					<div class="form-group">
						<label for="add-address">Address</label> <input type="text"
							class="form-control" id="add-address" placeholder="Address">
					</div>
					<div class="form-group">
						<label for="add-province">Province</label> <select
							class="form-control" name="add-province" id="add-province">
							<option value="">Choose Province</option>
							<c:forEach var="province" items="${provinces }">
								<option value="${province.id }">${province.name }</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="add-region">Region</label> <select
							class="form-control" name="add-region" id="add-region">
								<option value="">Choose Region</option>
						</select>
					</div>
					<div class="form-group">
						<label for="add-district">District</label> <select
							class="form-control" name="add-district" id="add-district">
								<option value="">Choose District</option>
						</select>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" id="btn-add-customer" class="btn btn-primary" data-dismiss="modal">Save</button>
			</div>
		</div>
	</div>
</div>