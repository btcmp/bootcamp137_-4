$(document).ready(function(){
	var alamatUrl = window.location.href;
	listVariant = [];
	$('#create').click(function(){
		$('#modal-addItem').modal();
	});
	
	$('#btn-export').click(function(){
		console.log('clicked')
		
	});
	$('#btn-add-item').click(function(e){
		console.log('clicked');
		console.log(listVariant);
		save(e);
	});
	
	$('#btn-save-variant').click(function(e){
		console.log('clicked');
		var state= $(this).attr('state');
		if (state == 'create'){
			variant = {
					name : $('#add-variant-name').val(),
					price: parseInt($('#add-unit-price').val()),
					sku: $('#add-sku').val(),
					itemInventory : [{
							beginning : parseInt($('#add-beginning-stock').val()),
							alertAtQty : parseInt($('#add-alert-at').val()),
					}]
			}
			console.log(variant);
			listVariant.push(variant);
		} else{
			var index = $(this).attr("data-id");
    		var variant = listVariant[index];
    		
    		variant.name = $('#add-variant-name').val();
    		variant.price = parseInt($('#add-unit-price').val());
    		variant.sku = $('#add-sku').val();
    		variant.itemInventory.beginning = parseInt($('#add-beginning-stock').val());
    		variant.itemInventory.alertAtQty = parseInt($('#add-alert-at').val());
    		listVariant[index] = variant;
		}
		
		createVariantTable(listVariant);
		HideVariantForm();
	});
	
	
	
	$('#table-body-variant').on('click','.edit-variant',function(e){	
		console.log('clicked')
    	var id = $(this).attr("data-id");
    	var data = listVariant[id];
    	$('#add-variant-name').val(data.name);
    	console.log(data.price);
    	$('#add-unit-price').val(data.price);
    	$('#add-sku').val(data.sku);
    	$('#add-beginning-stock').val(data.itemInventory[0].beginning);
    	$('#add-alert-at').val(data.itemInventory[0].alertAtQty);
    	$('#btn-save-variant').attr("state", "update")
    	$('#btn-save-variant').attr("data-id", id);
        $('#modal-addVariant').modal('show');
    });
	
	function save(e, alamatUrl){
		e.preventDefault();
		alamatUrl = window.location.href;
		var item = {
			name: $('#add-item-name').val(),
			active : 1,
			category :{
				id : parseInt($('#add-category').val())
			},
			itemVariant : listVariant
		};
		console.log(item);
		$.ajax({
			type : "POST",
			url : alamatUrl+'/save',
			data : JSON.stringify(item),
			contentType : 'application/json',
			success : function(data){
				console.log("berhasil");
				
			}, error : function(data){
				console.log("gagal");
			}
			
		});
	};
	
	function getDataById(id){
		$.ajax({
			type : "GET",
			url : alamatUrl+'/getOne/'+id,
			success : function(data){
				console.log("berhasil");
				listVariant = [];
				
				
			}, error : function(data){
				console.log("gagal");
			}
			
		});
	}
	
	$('#table-item').on('click','.update-item',function(e){
		var id = $(this).attr("id");
		console.log(id);
	});
		
	// =========================================== Utilities =========================================== //
	function HideVariantForm(){
    	$('#modal-addVariant').modal('hide');
    	resetVariantForm();
    }
    
    function ShowVariantForm(){
    	$('#modal-addVariant').modal('show');
    	resetVariantForm();
    }
	function resetVariantForm(){
    	$("#add-variant-name").val("");
    	$("#add-unit-price").val("0");
    	$("#add-sku").val("");
    	$("#add-beginning-stock").val("");
    	$("#add-alert-at").val("");
    }
	function createVariantTable(data){
		var index = 0;
		console.log(data);
		$("#table-body-variant").empty();
		$.each(data, function(key, row){
			$('#table-body-variant').append('<tr class="child"><td>'+row.name+'</td><td>'+row.price+'</td><td>'+row.sku+'</td><td>'
					+row.itemInventory[0].beginning+'</td><td>'+row.itemInventory[0].alertAtQty+'</td>'
					+'<td><button type="button" id="edit-variant" class="btn btn-info btn-xs edit-variant" data-id='+index+'>Edit</button> | ' 
					+'<button type="button" class="btn btn-danger btn-xs delete-variant" data-id='+index+'>X</button></td></tr>');
			index++;
		});
	}
});

