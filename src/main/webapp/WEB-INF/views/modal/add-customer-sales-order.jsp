<!-- Modal -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal fade" id="modal-add-customer-sales-order" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h3 class="modal-title" id="exampleModalLabel">Add New Customer</h3>
			</div>
			<div class="modal-body">


				<form class="form-add-customer">
					<div class="form-group">
						<label for="add-name">Name</label> <input type="text"
							class="form-control" id="add-name" placeholder="Name"
							required="">
					</div>
					<div class="form-group">
						<label for="add-email">Email</label> <input type="email"
							class="form-control" id="add-email" placeholder="Email"
							required="">
					</div>
					<div class="form-group">
						<label for="add-phone">Phone</label> <input type="number"
							class="form-control" id="add-phone" placeholder="Phone"
							required="" data-parsley-length="[11,12]">
					</div>
					<div class="form-group">
						<label for="add-phone">Day Of Birth</label> <input type="date"
							class="form-control" id="add-dob" placeholder="day of birth"
							required="">
					</div>
					<div class="form-group">
						<label for="add-address">Address</label> <input type="text"
							class="form-control" id="add-address" placeholder="Address"
							required="">
					</div>
					<div class="form-group">
						<label for="add-province">Province</label> <select
							class="form-control" name="add-province" id="add-province" required="">
							<option value="">Choose Province</option>
							<c:forEach var="province" items="${provinces }">
								<option value="${province.id }">${province.name }</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="add-region">Region</label> <select
							class="form-control" name="add-region" id="add-region" required="">
								<option value="">Choose Region</option>
						</select>
					</div>
					<div class="form-group">
						<label for="add-district">District</label> <select
							class="form-control" name="add-district" id="add-district" required="">
								<option value="">Choose District</option>
						</select>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<input type="hidden" id="btn-add-customer"/>
				<button type="button" id="btn-add-customer1" class="btn btn-primary">Save</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$('#btn-add-customer1').click(function(){
		var ok = $('.form-add-customer').parsley().validate();
		if(ok){
			$('#btn-add-customer').click();
		}
	})
</script>