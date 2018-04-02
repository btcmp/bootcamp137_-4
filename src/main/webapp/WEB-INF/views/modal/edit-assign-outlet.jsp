<!-- Modal -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal fade" id="edit-assign-outlet" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>				
				<h4 class="modal-title" id="exampleModalLabel"><b>Edit Assign Outlet</b></h4>
			</div>
			<div class="modal-body">
				<form action="#">
					<table class="table" id="outlet-table">
							<tbody id="list-edit-outlet">
								<c:forEach items="${outlets}" var="outlet">
								<tr>
									<td>${outlet.name}</td>
									<td><input type="checkbox" class="edit-outlet" name="edit-outlet" id="${outlet.id}"></td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					
					
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" id="btn-edit-outlet">Select</button>
			</div>
		</div>
	</div>
</div> <!-- a -->

