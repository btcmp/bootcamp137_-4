$(document).ready(function(){
	var alamatUrl = window.location.href;
	var listVariant = [];
	var added = [];
	var addedQty = [];
	showPRByOutlet();
	$('#create').click(function(){
		//$('#btn-add-item').attr('state','create');
		$(this).attr('state','create');
		if ($(this).attr('state') == 'create'){
			$('#btn-pr-submit').prop('disabled', true);
		}
		console.log('disini');
		
		$('#modal-add-pr').modal();
		$('#btn-pr-save').prop('disabled',true);
	});
	function showAll(alamatUrl){
		$('#table-add-pr-body').empty();
		$.ajax({
			dataType : "json",
		    url : alamatUrl+'/getAll',
			headers : {
		    	'Accept' : 'application/json',
		        'Content-Type' : 'application/json'
		    },
		    type : 'GET',
		    success : function(data){
		    	console.log(data);
		    	$.each(data, (key, data) =>{
					$('#table-view-pr-body').append('<tr> <td>'+getDateFormat(data.createdOn)+'</td>'+
							'<td>'+data.prNo+'</td>'+
							'<td>'+data.notes+'</td>'+
							'<td>'+data.status+'</td>'+
							'<td><a id='+data.id+' class="btn-update-pr btn btn-primary">Edit</a>'+
							'<a id='+data.id+' class="btn-detail-pr btn btn-success">View</a></td>'
					)
				});
		    }
		});	
	}
	//showAll(alamatUrl);
	function showPRByOutlet(){
		outletId = $('#select-outlet-main').val();
		$('#table-add-pr-body').empty();
		$('#table-view-pr-body').empty();
		$.ajax({
			dataType : "json",
		    url : alamatUrl+'/getAllPr?outletId='+outletId,
			headers : {
		    	'Accept' : 'application/json',
		        'Content-Type' : 'application/json'
		    },
		    type : 'GET',
		    success : function(data){
		    	console.log(data);
		    	$.each(data, (key, data) =>{
					$('#table-view-pr-body').append('<tr> <td>'+getDateFormat(data.createdOn)+'</td>'+
							'<td>'+data.prNo+'</td>'+
							'<td>'+data.notes+'</td>'+
							'<td>'+data.status+'</td>'+
							'<td><a id='+data.id+' class="btn-update-pr btn btn-primary">Edit</a>'+
							'<a id='+data.id+' class="btn-detail-pr btn btn-success">View</a></td>'
					)
				});
		    }
		});	
	}
	
	
	//================= Show Purchase Request By Outlet =================//
	//================= Select Option =================//
	$('#select-outlet-main').on('change', function() {
		$('#select-outlet').val($('#select-outlet-main').val());
		showPRByOutlet();
	});
	
	
	$('#search-item-variant').on('input',function(e){
		var word = $(this).val();
		console.log(alamatUrl);
		if (word=="") {
			$('#table-add-item-variant-body').empty();
		} else {
			$.ajax({
				type : 'GET',
				url : alamatUrl+'/search-item?name='+word,
				dataType: 'json',
				success : function(data){
					$('#table-add-item-variant-body').empty();
					$.each(data, function(key, val) {
						if(added.indexOf(val.id.toString()) == -1) {
							$('#table-add-item-variant-body').append('<tr id="'+val.itemVariant.id+'"><td>'+ val.itemVariant.item.name +'-'+ val.itemVariant.name +'</td>'
									+'<td id="inStock'+ val.id +'">'+ val.endingQty +'</td>'
									+'<td id="pr-qty'+ val.id +'">'
									+'<input type="number" class="add-purchase-request-qty'+ val.id +'" value="1" /></td><td>'
									+'<button type="button" id="'+ val.id +'" class="btn-add-item'+ val.id +' btn-add-item btn btn-primary">Add</button>'
									+'<button type="button" id="'+ val.id +'" class="btn-added-item'+ val.id +' btn-added-item btn">Added</button></td></tr>');
							$('.btn-added-item'+val.id).hide();
						} else {
							var a = added.indexOf(val.id.toString());
							$('#table-add-item-variant-body').append('<tr><td>'+ val.itemVariant.item.name +'-'+ val.itemVariant.name +'</td>'
									+'<td>'+ val.endingQty +'</td><td id="pr-qty'+ val.id +'">'+addedQty[a]+'</td>'
									+'<td><button type="button" id="'+ val.id +'" class="btn-add-item'+ val.id +' btn-add-item btn btn-primary">Add</button>'
									+'<button type="button" id="'+ val.id +'" class="btn-added-item'+ val.id +' btn-added-item btn">Added</button></td></tr>');
							$('.btn-add-item'+val.id).hide();
						}
					});
				}, 
				error : function(){
					$('#table-add-item-variant-body').empty();
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
			});
			$('#btn-pr-save').prop('disabled',false);
		}
	});
	
	
	$('body').on('click', 'button.btn-cancel-item', function(){
		var id = $(this).attr('id');
		$('#pr-PurchaseRequest'+id).remove();
		$('.btn-added-item'+id).hide();
		$('.btn-add-item'+id).show();
		$('#pr-qty'+id).html('<input type="number" class="add-purchase-request-qty'+ id +'" value="1" />');
		var a = added.indexOf(id.toString());
		added.splice(a,1);
		addedQty.splice(a,1);
		if ($('#table-add-pr-body tr').length > 0){
			$('#btn-pr-save').prop('disabled',false);
		} else{
			$('#btn-pr-save').prop('disabled',true);
		}
	})
	$('#btn-pr-save').click(function(e){
		console.log('clicked');
		var state = $(this).attr('state');
		if (state == 'create'){
			save(e);
		} else{
			update(e);
		}
		
	});
	$('#btn-pr-cancel').click(function(e){
		$('#add-pr-date').val('');
		$('#add-pr-notes').val('');
	});
	
//	function cleanPRModal(){
//		$('#add-pr-date').val('');
//		$('#add-pr-notes').val('');
//	}
//	cleanPRModal();
	
	save = (e) => {
		e.preventDefault();
		alamatUrl = window.location.href;
		
		purchaseRequestDetail =[];
		
		$('#table-add-pr-body > tr').each(function(index, data){
			console.log(data);
			var prd = {
					itemVariant : {
						id : parseInt($(data).find('td').eq(0).attr('id'))
					},
					requestQty : parseInt($(data).find('td').eq(2).text())
			}
			purchaseRequestDetail.push(prd);
		});
		console.log(purchaseRequestDetail);
		//======= Get Date ====== //
		matriks = $('#add-pr-date').val().split('-');
		// yyyy-MM-dd
		readyTime = matriks[0]+'-'+matriks[1]+'-'+matriks[2];
		pr = matriks[0]+matriks[1]+matriks[2];
		
		var pr = {
			prNo : 'PR'+pr,
			readyTime : readyTime,
			notes : $('#add-pr-notes').val(),
			status : 'Created',
			outlet : {
				id : parseInt($('#select-outlet').val())
			},
			purchaseRequestDetail: purchaseRequestDetail
		}
		console.log(pr);
		$.ajax({
			type : "POST",
			url : alamatUrl+'/save',
			data : JSON.stringify(pr),
			contentType : 'application/json',
			success : function(data){
				console.log("berhasil save");
				
			}, error : function(data){
				console.log("gagal");
			}
			
		});
		$('#modal-add-pr').modal('hide');
		showPRByOutlet();
//		HideItemForm();
	};
	function getDateFormat(date) {
		var d = new Date(Number(date)),
		month = '' + (d.getMonth() + 1),
		day = '' + d.getDate(),
		year = d.getFullYear();

		if (month.length < 2)
		    month = '0' + month;
		if (day.length < 2)
		    day = '0' + day;
		var date = new Date();
		date.toLocaleDateString();

		return [year, month, day].join('-');
	};
		
	getPurchaseRequestById = (id) => {
		var dataTemp;
		function myCallback(response){
			console.log("berhasil");
			dataTemp = response;
			tanggal = getDateFormat(dataTemp.readyTime);
			$('#select-outlet').val(dataTemp.outlet.id);
			$('#add-pr-notes').val(dataTemp.notes);
			$('#add-pr-date').val(tanggal);
			console.log(dataTemp)
			//createVariantTable(listVariant);
			
		}
		$.ajax({
			// async : false,
			type : "GET",
			dataType: 'json',
			url : alamatUrl+'/getOne/'+id,
			success : myCallback ,
			error : (data) => {
				console.log("gagal");
			}
		});
		
		return dataTemp;
	}
	
	$('#table-view-pr').on('click','.btn-update-pr',function(e){
		
		$(this).attr('state','update');
		var id = $(this).attr('id');
		
		console.log('pr id : '+id);
		dataku = getPurchaseRequestById(id);
		$('#modal-add-pr').modal();
		$('#btn-pr-save').attr('state','update');
		console.log('mode: ' +$(this).attr('state'));
		if ($('#table-add-pr-body tr').length > 0){
			$('#btn-pr-save').prop('disabled',false);
			$('#btn-pr-submit').prop('disabled',false);
		} else{
			$('#btn-pr-save').prop('disabled',true);
			$('#btn-pr-submit').prop('disabled',true);
		}
	});
});
