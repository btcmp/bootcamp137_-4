$(document).ready(function(){
	var alamatUrl = window.location.href;
	var listVariant = [];
	var added = [];
	var addedQty = [];
	$('#create').click(function(){
		//$('#btn-add-item').attr('state','create');
		console.log('disini');
		
		$('#modal-add-pr').modal();
		$('#btn-save-pr').prop('disabled',true);
	});
	
	$('#search-item-variant').on('input',function(e){
		var word = $(this).val();
		console.log(alamatUrl);
		if (word=="") {
			$('#tabel-add-pr-body').empty();
		} else {
			$.ajax({
				type : 'GET',
				url : alamatUrl+'/search-item?name='+word,
				dataType: 'json',
				success : function(data){
					$('#tabel-add-pr-body').empty();
					$.each(data, function(key, val) {
						if(added.indexOf(val.id.toString()) == -1) {
							$('#table-add-pr-body').append('<tr><td>'+ val.itemVariant.item.name +'-'+ val.itemVariant.name +'</td>'
									+'<td id="inStock'+ val.id +'">'+ val.endingQty +'</td>'
									+'<td id="pr-qty'+ val.id +'">'
									+'<input type="number" class="add-purchase-request-qty'+ val.id +'" value="1" /></td><td>'
									+'<button type="button" id="'+ val.id +'" class="btn-add-item'+ val.id +' btn-add-item btn btn-primary">Add</button>'
									+'<button type="button" id="'+ val.id +'" class="btn-added-item'+ val.id +' btn-added-item btn">Added</button></td></tr>');
							$('.btn-added-item'+val.id).hide();
						} else {
							var a = added.indexOf(val.id.toString());
							$('#table-add-pr-body').append('<tr><td>'+ val.itemVariant.item.name +'-'+ val.itemVariant.name +'</td>'
									+'<td>'+ val.endingQty +'</td><td id="pr-qty'+ val.id +'">'+addedQty[a]+'</td>'
									+'<td><button type="button" id="'+ val.id +'" class="btn-add-item'+ val.id +' btn-add-item btn btn-primary">Add</button>'
									+'<button type="button" id="'+ val.id +'" class="btn-added-item'+ val.id +' btn-added-item btn">Added</button></td></tr>');
							$('.btn-add-item'+val.id).hide();
						}
					});
				}, 
				error : function(){
					$('#tabel-add-pr-body').empty();
				}
			})
		}
	});
	$('body').on('click', 'button.btn-add-item', function(){
		var id = $(this).attr('id');
		var inStock = parseInt($('#inStock'+id).text());
		var purchaseRequestQty = parseInt($('.add-purchase-request-qty'+id).val());
		if (purchaseRequestQty<1) {
			alert("at least 1");
		}else {
			added.push(id);
			addedQty.push(purchaseRequestQty);
			$('#pr-qty'+id).html(purchaseRequestQty);
			$(this).hide();
			$('.btn-added-item'+id).show();
			$('#btn-save-pr').prop('disabled',true);
			$.ajax({
				type : 'GET',
				url : alamatUrl+'/get-item/'+id,
				dataType: 'json',
				success : function(data){
					$('#table-add-pr-body').append('<tr id="pr-PurchaseRequest'+ data.id +'">'
							+'<td id="'+ data.itemVariant.id +'">'+ data.itemVariant.item.name +'-'+ data.itemVariant.name +'</td>'
							+'<td>'+ data.endingQty +'</td><td>'+ purchaseRequestQty +'</td>'
							+'<td><button type="button" id="'+ data.id +'" '
							+'class="btn-cancel-item'+ data.id +' btn-cancel-item btn btn-primary">Cancel</button></td></tr>');
				},
				error : function(){
					alert('get one item inventory failed');
				}
			})
		}
	});

	
});
