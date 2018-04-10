<!-- Modal -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal fade" id="modal-update-supplier" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h3 class="modal-title" id="exampleModalLabel">Update Supplier</h3>
			</div>
			<div class="modal-body">
				<form class="form-edit">
					<input type="hidden" id="update-id">
					<input type="hidden" id="update-created-on">
					<input type="hidden" id="update-created-by">
					<div class="form-group">
						<label for="update-name">Supplier Name</label> <input type="text"
							class="form-control" id="update-name" placeholder="Supplier Name"
							required="">
					</div>
					<div class="form-group">
						<label for="update-address">Address</label> <input type="text"
							class="form-control" id="update-address" placeholder="Address"
							required="">
					</div>
					<div class="form-group">
						<label for="update-postal-code">Postal Code</label> <input type="text"
							class="form-control" id="update-postal-code" placeholder="Postal Code"
							required="" data-parsley-length="[5,5]">
					</div>
					<div class="form-group">
						<label for="update-phone">Phone</label> <input type="text"
							class="form-control" id="update-phone" placeholder="Phone"
							required="" data-parsley-length="[11,12]">
					</div>
					<div class="form-group">
						<label for="update-email">Email</label> <input type="text"
							class="form-control" id="update-email" placeholder="Email"
							required="">
					</div>
					<div class="form-group">
						<label for="update-province">Province</label> <select
							class="form-control" name="update-province" id="update-province"required="">
							<option value="">Choose Province</option>
							<c:forEach var="province" items="${provinces }">
								<option value="${province.id }">${province.name }</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="update-region">Region</label> <select
							class="form-control" name="update-region" id="update-region"required="">
							<option value="">Choose Region</option>
							<c:forEach var="region" items="${regions }">
								<option value="${region.id }">${region.name }</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="update-district">District</label> <select
							class="form-control" name="update-district" id="update-district"required="">
							<option value="">Choose District</option>
							<c:forEach var="district" items="${districts }">
								<option value="${district.id }">${district.name }</option>
							</c:forEach>
						</select>
					</div>
					<!-- <div class="checkbox">
						<label for="update-active"><input id="update-active" type="checkbox"
							value="true">Active</label>
					</div> -->
				</form>
			</div>
			<div class="modal-footer">
				<button style="float:left;" type="button" id="btn-deactivated" class="btn btn-danger">X</button>
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<input type="hidden" id="btn-update"/>
				<button type="button" id="btn-update1" class="btn btn-primary">Update</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$('#btn-update1').click(function(){
		var ok = $('.form-edit').parsley().validate();
		if(ok){
			$('#btn-update').click();
		}
	})
</script>