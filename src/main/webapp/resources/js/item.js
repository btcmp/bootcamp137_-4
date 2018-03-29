$(document).ready(function(){
	var alamatUrl = window.location.href;
	listVariant = [];
	function showAll(alamatUrl){
		$("#table-body-item").empty();
		$.ajax({
			dataType : "json",
		    //url : alamatUrl+'/getAllVariants',
		    url : alamatUrl+'/get-all-item-inventories',
			headers : {
		    	'Accept' : 'application/json',
		        'Content-Type' : 'application/json'
		    },
		    type : 'GET',
		    success : function(data){
		    	console.log(data);
		    	$.each(data, (key, data) =>{
					$('#table-body-item').append('<tr> <td>'+data.itemVariant.item.name+' -- '+data.itemVariant.name+'</td>'+
							'<td>'+data.itemVariant.item.category.name+'</td>'+
							'<td>'+data.itemVariant.price+'</td>'+
							'<td>'+data.endingQty+'</td>'+
							'<td><a id='+data.itemVariant.item.id+' class="update-item btn btn-primary">Update</a>'
					)
				});
		    }
		});	
	}
	
	showAll(alamatUrl);
	
	$('#create').click(function(){
		$('#btn-add-item').attr('state','create');
		console.log('disini');
		resetItemForm();
		resetVariantForm();
		
		$('#modal-addItem').modal();
	});
	
	$('#btn-export').click(function(){
		console.log('clicked');
	});
	$('#btn-clear').click(function(){
		listVariant = [];
	});
	$('#btn-add-item').click(function(e){
		var state = $(this).attr('state');
		if (state == 'create'){
			save(e);
		} else{
			update(e);
		}
		showAll(alamatUrl);
		enableVariantProperty();
		listVariant = [];
	});
	
	$('#btn-add-variant').click(function(e){
		$(this).attr('state','create');
		var state = $(this).attr('state');
		console.log(state);
	});
	
	
	$('#btn-cancel-variant').click(function(e){
		enableVariantProperty();
		HideVariantForm();
	});
	$('#btn-save-variant').click(function(e){
		var state= $(this).attr('state');
		if (state == 'create'){
			variant = {
					name : $('#add-variant-name').val(),
					price: parseInt($('#add-unit-price').val()),
					sku: $('#add-sku').val(),
					active : 1,
					itemInventory : [{
							beginning : parseInt($('#add-beginning-stock').val()),
							alertAtQty : parseInt($('#add-alert-at').val()),
					}]
			}
			listVariant.push(variant);
		} else{
			var index = $(this).attr("data-id");
    		var variant = listVariant[index];
    		//console.log('var id = '+ parseInt($('#edit-variant').attr('var-id')));
    		variant = {
    				id : parseInt($('#btn-save-variant').attr('var-id')),
    				name : $('#add-variant-name').val(),
					price: parseInt($('#add-unit-price').val()),
					sku: $('#add-sku').val(),
					active : 1,
					itemInventory : [{
							id : parseInt($('#btn-save-variant').attr('inv-id')),
							beginning : parseInt($('#add-beginning-stock').val()),
							alertAtQty : parseInt($('#add-alert-at').val()),
					}]
			}
    		listVariant[index] = variant;
		}
		
		createVariantTable(listVariant);
		enableVariantProperty();
		resetVariantForm();
		HideVariantForm();
	});
	
	
	$('#table-body-variant').on('click','.edit-variant',function(e){	
		var idx = $(this).attr("data-id");
    	//console.log('var id = '+$(this).attr('var-id'));
		var data = listVariant[idx];
		if (data.id == null){
			enableVariantProperty();
		} else{
			disableVariantProperty();
		}
		
    	$('#add-variant-name').val(data.name);
    	$('#add-unit-price').val(data.price);
    	$('#add-sku').val(data.sku);
    	$('#add-beginning-stock').val(data.itemInventory[0].beginning);
    	$('#add-alert-at').val(data.itemInventory[0].alertAtQty);
    	
    	$('#btn-save-variant').attr("state", "update")
    	$('#btn-save-variant').attr("data-id", idx);
    	$('#btn-save-variant').attr("var-id", data.id);
    	$('#btn-save-variant').attr("inv-id", data.itemInventory[0].id);
        $('#modal-addVariant').modal('show');
    });
	$('#table-body-variant').on('click','.delete-variant',function(e){	
		idx = $(this).attr('data-id');
		var variant = listVariant[idx];
		
		if (variant.id == null){
			listVariant.splice(idx,1);
			createVariantTable(listVariant);
		} else{
			$.ajax({
				type : 'PUT',
				url : alamatUrl+'/deleteVariant/'+variant.id,
				success : function(data){
					listVariant.splice(idx, 1);
    	    		createVariantTable(listVariant);
    	    		console.log('Berhasil')
				},
				error : function(data){
					console.log('error')
				}
				
			});
		}
	});
	
	save = (e) => {
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
		// console.log(item);
		$.ajax({
			type : "POST",
			url : alamatUrl+'/save',
			data : JSON.stringify(item),
			contentType : 'application/json',
			success : function(data){
				console.log("berhasil save");
				
			}, error : function(data){
				console.log("gagal");
			}
			
		});
		HideItemForm();
	};
	
	update = (e) => {
		e.preventDefault();
		alamatUrl = window.location.href;
		var item = {
			id : parseInt($('#add-item-id').val()),
			name: $('#add-item-name').val(),
			active : 1,
			category :{
				id : parseInt($('#add-category').val())
			},
			itemVariant : listVariant
		};
		//console.log(item);
		$.ajax({
			type : "PUT",
			url : alamatUrl+'/update',
			data : JSON.stringify(item),
			contentType : 'application/json',
			success : function(data){
				console.log("berhasil update");
				
			}, error : function(data){
				console.log("gagal");
			}
			
		});
		resetItemForm();
		HideItemForm();
	};
	
	getInventoryByItemId = (id) => {
		$.ajax({
			type : "GET",
			url : alamatUrl+'/searchInventory/'+id,
			success : (data) => {
				console.log("berhasil");
				listVariant = [];
				var temp;
				$.each(data, (key, row) =>{
					temp = row.itemVariant.item;
					
					inventory = row;
					variant = row.itemVariant;
					inventory.itemVariant = null;
					variant.itemInventory = [inventory];
					variant.item = null;
					listVariant.push(variant);
					// console.log(variant.id)
				});
				
				//console.log(data);
				$('#add-item-id').val(parseInt(temp.id));
				$('#add-item-name').val(temp.name);
				$('#add-category').val(parseInt(temp.category.id));
				createVariantTable(listVariant);
				
			}, error : (data) => {
				console.log("gagal");
			}
			
		});
	}
	
	$('#table-item').on('click','.update-item',function(e){
		var id = $(this).attr('id');
		getInventoryByItemId(id);
		console.log('item id : '+id);
		$('#modal-addItem').modal();
		$('#btn-add-item').attr('state','update');
		
	});
		
	// =========================================== Utilities =========================================== //
	function HideItemForm() {
    	$('#modal-addItem').modal('hide');
    	resetItemForm();
    }
	function resetItemForm() {
		console.log('clear item form');
		$("#add-item-name").val("");
		$("#add-category").val("");
		$("#table-body-variant").empty();
		
	};
	function HideVariantForm() {
    	$('#modal-addVariant').modal('hide');
    	resetVariantForm();
    }
    
    function ShowVariantForm() {
    	$('#modal-addVariant').modal('show');
    	resetVariantForm();
    }
    function enableVariantProperty(){
    	$('#add-variant-name').prop('disabled', false);
		$('#add-sku').prop('disabled', false);
		$('#add-beginning-stock').prop('disabled', false);
    }
    function disableVariantProperty(){
    	$('#add-variant-name').prop('disabled', true);
		$('#add-sku').prop('disabled', true);
		$('#add-beginning-stock').prop('disabled', true);
    }
	function resetVariantForm(){
    	$("#add-variant-name").val("");
    	$("#add-unit-price").val("0");
    	$("#add-sku").val("");
    	$("#add-beginning-stock").val("");
    	$("#add-alert-at").val("");
    }
	createVariantTable = (data) => {
		
		$("#table-body-variant").empty();
		var index = 0;
		$.each(data, (key, row) =>{
			$('#table-body-variant').append('<tr class="child"><td>'+row.name+ '</td><td>'+row.price+'</td><td>'+row.sku+'</td><td>'
					+row.itemInventory[0].beginning+'</td><td>'+row.itemInventory[0].alertAtQty+ '</td>'
					+'<td><button type="button" id="edit-variant" class="btn btn-info btn-sm edit-variant" inv-id='+row.itemInventory[0].id+' var-id='+row.id+' data-id='+index+'>Edit</button> | ' 
					+'<button type="button" id="delete-variant" class="btn btn-sm delete-variant" inv-id='+row.itemInventory[0].id+' var-id='+row.id+' data-id='+index+'>X</button></td></tr>');
			index++;
			console.log(row.itemInventory[0].id);
		});
	}
});