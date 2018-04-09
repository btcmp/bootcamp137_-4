<!-- Modal -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal fade" id="modal-receipt-sales-order" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button> -->
				<h3 class="modal-title" id="exampleModalLabel">Receipt Sales Order</h3>
			</div>
			<div class="modal-body">
				<form class="form-all">
					<div class="form-group">
						<div class="row">
							<div class="col-lg-2"></div>
							<div class="col-lg-8">
								<input style="text-align: center; font-size:xx-large;"="text" class="form-control" id="receipt-change" disabled>
							</div>
							<div class="col-lg-2 "></div>
						</div>
						<div class="form-group">
							<h4 style="text-align: center;" id="receipt-cash"></h4>
							<hr>
						</div>
						<!-- <input type="text" class="form-control" id="receipt-cash" disabled> -->
					</div>
					<div class="form-group">
						<label for="receipt-email">How do you want to receive your receipt?</label>
						<div>
					        <div class="input-group ">
					          <input type="email"  class="form-control" id="receipt-email" placeholder="customer mail">
					          <span class="input-group-btn">
					                <button type="button" id="receipt-email-btn" class="btn btn-primary" data-dismiss="modal">Send</button>
					              </span>
					        </div>
					    </div>
					    <div class="modal-footer">
					    	<div>
					    		<button type="button" id="receipt-print" class="btn btn-primary form-control" data-dismiss="modal">Print Receipt</button>
					    	</div>
					    	<div style="margin-top: 10px">
					    		<button type="button" id="receipt-done" class="btn btn-danger form-control" data-dismiss="modal">No, Thanks & Done</button>
					    	</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>