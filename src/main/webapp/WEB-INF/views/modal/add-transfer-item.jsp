<!-- Modal -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal fade" id="modal-add-transfer-item" tabindex="-1" role="dialog"
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
						<label for="search-item">Search Item - Variant</label> <input type="text"
							class="form-control" id="search-item-varian" placeholder="search items...">
					</div>
					<div>
						<table class="table table-striped table-bordered" cellspacing="0" width="100%">
							<thead>
								<th>Item</th>
								<th>In Stock</th>
								<th>Trans. Qty</th>
								<th>Action</th>
							</thead>
							<tbody id="add-item-transfer-tbl">
								<c:forEach items="${itemInventorys }" var="itemInventory">
									<tr>
										<td>${itemInventory.itemVariant.item.name }-${itemInventory.itemVariant.name }</td>
										<td>${itemInventory.endingQty }</td>
										<td id="td-qty${itemInventory.id}">
											<input class="add-transfer-stock-qty${itemInventory.id}" value="0"/>
										</td>
										<td>
											<button type="button" id="${itemInventory.id}" class="btn-add-item${itemInventory.id} btn-add-item btn btn-primary">Add</button>
											<button type="button" id="${itemInventory.id}" class="btn-added-item${itemInventory.id} btn-added-item btn">Added</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</form>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
</div>