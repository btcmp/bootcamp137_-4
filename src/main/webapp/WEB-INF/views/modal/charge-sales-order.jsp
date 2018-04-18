<!-- Modal -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal fade" id="modal-charge-sales-order" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h3 class="modal-title" id="exampleModalLabel">Charge Sales Order</h3>
			</div>
			<div class="modal-body">
				<form class="form-all">
					<div class="form-group">
						<label for="charge-cash">Cash</label> <input type="text"
							class="form-control" id="charge-cash" value="Rp." >
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" id="charge-done" class="btn btn-primary"><i class="fa fa-credit-card"></i> Submit Payment</button>
			</div>
		</div>
	</div>
</div>
<script>
$('#charge-cash').on('input',function(){
	var charge = $(this).val().match(/\d/g);
	if (charge!==null) {
		if (charge[0]==0) {
			chargeRp = 'Rp.';
		} else {
			charge = charge.join('');
			var chargeRp = 'Rp.'+charge.toString().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,");
		}
	} else {
		chargeRp = 'Rp.';
	}
	$(this).val(chargeRp);
})
</script>