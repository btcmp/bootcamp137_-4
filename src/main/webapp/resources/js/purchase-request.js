$(document).ready(function(){
	var alamatUrl = window.location.href;
//	var tbi = $('#table-view-pr').DataTable({
//		'searching' : false,
//		'paging' : true, 
//		'info' : false
//	});
	
	$("#purchase-request-side-option").addClass('active');
	$("#treeview-transaction").addClass('active');
	var listVariant = [];
	var added = [];
	var addedQty = [];
	showPRByOutlet();
	
	$('#btn-export').click(function(){
		console.log('clicked');
		window.location = '/miniproject/generate/purchase-request';
	});
	
	$('#create').click(function(){
		//$('#btn-add-item').attr('state','create');
		$(this).attr('state','create');
		$('#btn-pr-save').attr('state','create');
		if ($(this).attr('state') == 'create'){
			$('#btn-pr-submit').prop('disabled', true);
		}
		
		
		
		$('#modal-add-pr').modal({backdrop: 'static', keyboard: false});
		$('#btn-pr-save').prop('disabled',true);
		
		resetModalAddPr();
		added = [];
		addedQty = [];
	});
	$('#btn-add-item-variant').click(function(){
		//console.log('di button add item variant')
		$('#search-item-variant').val('');
		$('#table-add-item-variant-body').empty();
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
		    	$.each(data, (key, data) =>{
					$('#table-view-pr-body').append('<tr> <td>'+getDateFormat(data.createdOn)+'</td>'+
							'<td>'+data.prNo+'</td>'+
							'<td>'+data.notes+'</td>'+
							'<td>'+data.status+'</td>'+
							'<td><a id='+data.id+' class="btn-update-pr btn btn-primary">Edit</a>'+
							'<a id='+data.id+' class="btn-detail-pr btn btn-success">View</a></td>'
					)
					if (data.status != 'Created'){
						$('#'+data.id+'').prop('disabled',true);
						$('#'+data.id+'').prop('hide',true);
					}
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
		outledId = $('#select-outlet-main').val();
		if (word=="") {
			$('#table-add-item-variant-body').empty();
		} else {
			$.ajax({
				type : 'GET',
				url : alamatUrl+'/search-item?outletId='+outletId+'&name='+word,
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
			//console.log(id);
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
//	
//	
	$('body').on('click', 'button.btn-cancel-item', function(){
		//console.log('di button cancel item')
		var id = $(this).attr('id');
		//console.log(id);
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
		var state = $(this).attr('state');
		status = 'Created';
		formAddPr = $('#form-add-pr').parsley().validate();
		if (formAddPr){
			if (state == 'create'){
				save(e, status);
			} else{
				PrNo = $('#btn-pr-save').attr('pr-no');
				prId = parseInt($('#btn-pr-save').attr('pr-id'));
				update(e, status, PrNo, prId);
			}
		}
		
	});
	$('#btn-pr-submit').click(function(e){
		//var state = $(this).attr('state');
		PrNo = $('#btn-pr-save').attr('pr-no');
		prId = parseInt($('#btn-pr-save').attr('pr-id'));
		status = 'Submitted';
		formAddPr = $('#form-add-pr').parsley().validate();
		if (formAddPr){
			update(e, status, PrNo, prId);
		}
		
		
	});

//	function cleanPRModal(){
//		$('#add-pr-date').val('');
//		$('#add-pr-notes').val('');
//	}
//	cleanPRModal();
	//======================== Fungsi Save ========================//
	save = (e, prStatus) => {
		e.preventDefault();
		alamatUrl = window.location.href;
		
		purchaseRequestDetail =[];
		
		$('#table-add-pr-body > tr').each(function(index, data){
			//console.log(data);
			var prd = {
					itemVariant : {
						id : parseInt($(data).find('td').eq(0).attr('id'))
					},
					requestQty : parseInt($(data).find('td').eq(2).text())
			}
			purchaseRequestDetail.push(prd);
		});
		//console.log(purchaseRequestDetail);
		//======= Get Date ====== //
		matriks = $('#add-pr-date').val().split('-');
		// yyyy-MM-dd
		readyTime = matriks[0]+'-'+matriks[1]+'-'+matriks[2];
		pr = matriks[0]+matriks[1]+matriks[2];
		
		var pr = {
			prNo : 'Apalah',
			readyTime : readyTime,
			notes : $('#add-pr-notes').val(),
			status : prStatus,
			outlet : {
				id : parseInt($('#select-outlet-main').val())
			},
			purchaseRequestDetail: purchaseRequestDetail
		}
		//console.log(pr);
		$.ajax({
			type : "POST",
			url : alamatUrl+'/save',
			data : JSON.stringify(pr),
			contentType : 'application/json',
			success : function(data){
				//console.log("berhasil save");
				purchaseRequestDetail =[];
				resetModalAddPr();
				showPRByOutlet();
			}, error : function(data){
				//console.log("gagal");
				purchaseRequestDetail =[];
				resetModalAddPr();
				showPRByOutlet();
			}
			
		});
		$('#modal-add-pr').modal('hide');
		//showPRByOutlet();
//		HideItemForm();
	};
	//======================== End of Save ========================//
	
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
			//console.log("berhasil");
			dataTemp = response;
			outletId = $('#select-outlet-main').val();
			tanggal = getDateFormat(dataTemp.readyTime);
			$('#select-outlet').val(dataTemp.outlet.id);
			$('#add-pr-notes').val(dataTemp.notes);
			$('#add-pr-date').val(tanggal);
			$('#view-pr-detail-notes').val(dataTemp.notes);
			$('#view-prd-num').html(dataTemp.prNo);
			$('#view-prd-created').html(dataTemp.createdBy.username);
			$('#view-prd-time').html(tanggal);
			$('#view-prd-status').html(dataTemp.status);
			let option = [];
			if (dataTemp.status == 'Created'){
				$('#select-pr-action').attr('pr-id', id);
				option.push('<option disabled selected value="more">More</option>');
				option.push('<option disabled value="approve">Approve</option>');
				option.push('<option disabled value="reject">Reject</option>');
				option.push('<option value="print">Print</option>');
				option.push('<option disabled disabled value="create-po" disabled>Create PO</option>');
			} else if (dataTemp.status == 'Submitted'){
				$('#select-pr-action').attr('pr-id', id);
				option.push('<option disabled selected value="more">More</option>');
				option.push('<option value="approve">Approve</option>');
				option.push('<option value="reject">Reject</option>');
				option.push('<option value="print">Print</option>');
				option.push('<option disabled value="create-po" disabled>Create PO</option>');
			} else if (dataTemp.status == 'Approved'){
				$('#select-pr-action').attr('pr-id', id);
				option.push('<option disabled selected value="more">More</option>');
				option.push('<option disabled value="approve">Approve</option>');
				option.push('<option disabled value="reject">Reject</option>');
				option.push('<option value="print">Print</option>');
				option.push('<option value="create-po">Create PO</option>');
			} else if (dataTemp.status == 'Rejected'){
				$('#select-pr-action').attr('pr-id', id);
				option.push('<option disabled selected value="more">More</option>');
				option.push('<option disabled value="approve">Approve</option>');
				option.push('<option disabled value="reject">Reject</option>');
				option.push('<option value="print">Print</option>');
				option.push('<option disabled value="create-po" disabled>Create PO</option>');
			} else if (dataTemp.status == 'PO Created'){
				$('#select-pr-action').attr('pr-id', id);
				option.push('<option disabled selected value="more">More</option>');
				option.push('<option disabled value="approve">Approve</option>');
				option.push('<option disabled value="reject">Reject</option>');
				option.push('<option value="print">Print</option>');
				option.push('<option disabled value="create-po" disabled>Create PO</option>');
			}
			$('#select-pr-action').html(option);
			
			//========= Get PR History ==========//
			$.ajax({
				type: 'GET',
				url : alamatUrl+'/getHistory/'+id,
				datatype : 'json',
				success : function(data){
					$.each(data, function(key,val){
						$('#table-pr-history').append('<tr><td>On '+ getDateFormat(val.createdOn) +' - Purchase Request '+dataTemp.prNo+ ' is '+ val.status.toLowerCase()+'</td> </tr>')
					});
					
				}
			});
			//========= Get PR Detail ==========//
			$.ajax({
				type : 'GET',
				url : alamatUrl+'/findPRDetailAndQty?outletId='+outletId+'&prId='+id,
				dataType: 'json',
				success : function(data){
					//console.log(data);
					added = [];
					addedQty = [];
					index = 0;
					$.each(data, function(key, val) {
						$('#table-add-pr-body').append('<tr data-idx='+index+' prd-Id='+val[0].id+' id="pr-PurchaseRequest'+ val[1].id +'">'
								+'<td id="'+ val[1].itemVariant.id +'">'+ val[1].itemVariant.item.name +'-'+ val[1].itemVariant.name +'</td>'
//								+'<td>'+ val[1].endingQty +'</td><td>'+ val[0].requestQty +'</td>'
								+'<td>'+ val[1].endingQty +'</td><td><input type="number" min="0" value='+ val[0].requestQty +'></td>'
								+'<td><button type="button" id="'+ val[1].id +'" '
								+'class="btn-cancel-item'+ val[1].id +' btn-cancel-item btn btn-primary">Cancel</button></td></tr>');
						index++;
						$('#table-pr-detail-body').append('<tr prd-Id='+val[0].id+' id="pr-PurchaseRequest'+ val[0].id +'">'
								+'<td id="'+ val[1].itemVariant.id +'">'+ val[1].itemVariant.item.name +'-'+ val[1].itemVariant.name +'</td>'
								+'<td>'+ val[1].endingQty +'</td><td>'+ val[0].requestQty +'</td></tr>');
								//+'<td>'+ val[1].endingQty +'</td><td><input type="number value='+ val[0].requestQty +'></td></tr>');
						
						added.push(val[1].id.toString());
						addedQty.push(val[0].requestQty);
						//console.log(added);
						$('.btn-add-item'+val[1].id).hide();
						$('.btn-added-item'+val[1].id).show();
						if ($('#table-add-pr-body tr').length > 0){
							$('#btn-pr-save').prop('disabled',false);
							$('#btn-pr-submit').prop('disabled',false);
						} else{
							$('#btn-pr-save').prop('disabled',true);
							$('#btn-pr-submit').prop('disabled',true);
						}
					});
				},
				error : function(data){
					alert('get one item inventory failed');
				}
			});
			
			//
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
	function dateToday(){
		let today = new Date();
		let dd = today.getDate();
		let mm = today.getMonth()+1;
		let yyyy = today.getFullYear();
		if(dd<10){
			dd='0'+dd;
		}
		if(mm<10){
			mm='0'+mm;
		}
		today = yyyy+'-'+mm+'-'+dd;
		$('#add-pr-date').setAttribute('min', today);
	}
	$('#table-view-pr').on('click','.btn-update-pr',function(e){
		resetTable();
		//dateToday();
		var row = $(this).closest("tr");
		var PrNo = row.find('td').eq(1).text();
		//console.log('di button edit : '+ PrNo);
		$(this).attr('state','update');
		var id = $(this).attr('id');
		
		console.log('pr id : '+id);
		dataku = getPurchaseRequestById(id);
		$('#modal-add-pr').modal({backdrop: 'static', keyboard: false});
		$('#btn-pr-save').attr('pr-no',PrNo);
		$('#btn-pr-save').attr('pr-id', id)
		$('#btn-pr-save').attr('state','update');
		//console.log('mode: ' +$(this).attr('state'));
		
	});
	$('#table-view-pr').on('click','.btn-detail-pr',function(e){
		resetTable();
		var id = $(this).attr('id');
		//console.log(id);
		dataku = getPurchaseRequestById(id);
		
		$('#modal-pr-detail').modal({backdrop: 'static', keyboard: false});
		
	});
	$('#btn-cancel-variant').click(function(e){
		//console.log('di button cancel')
		$('#search-item-variant').val('');
		$('#table-add-item-variant-body').empty();
	});
	
	$('#btn-pr-cancel').click(function(e){
		resetModalAddPr();
	});
	
	function resetModalAddPr(){
		$('#add-pr-date').val('');
		$('#add-pr-notes').val('');
		$('#table-add-pr-body').empty();
	}
	
	$('#btn-close-modal-add-pr').click(function(){
		//console.log('di button ini')
		resetModalAddPr();
	});
	
	
	function resetTable(){
		//console.log('mereset table')
		$('#table-pr-detail-body').empty();
		$('#table-add-pr-body').empty();
		$('#table-pr-history').empty();
	};
	
	//======================== Fungsi Update ========================//
	update = (e, prStatus, getPrNo, prId) => {
		e.preventDefault();
		alamatUrl = window.location.href;
		
		purchaseRequestDetail =[];
		
		$('#table-add-pr-body > tr').each(function(index, data){
			//console.log(($(data).attr('prd-id')) == null)
			if (($(data).attr('prd-id')) == null){
				var prd = {
						itemVariant : {
							id : parseInt($(data).find('td').eq(0).attr('id'))
						},
						requestQty : parseInt($(data).find('td').eq(2).text())
				}
				purchaseRequestDetail.push(prd);
			} else{
				var prd = {
						id : parseInt($(data).attr('prd-id')),
						itemVariant : {
							id : parseInt($(data).find('td').eq(0).attr('id'))
						},
						requestQty : parseInt($(data).find('input').val())
						}
				purchaseRequestDetail.push(prd);
			}
			
		});
		console.log(purchaseRequestDetail);
		//======= Get Date ====== //
		matriks = $('#add-pr-date').val().split('-');
		// yyyy-MM-dd
		readyTime = matriks[0]+'-'+matriks[1]+'-'+matriks[2];
		prUpdate = {
			id : prId,
			prNo : getPrNo,
			readyTime : readyTime,
			notes : $('#add-pr-notes').val(),
			status : prStatus,
			outlet : {
				id : parseInt($('#select-outlet-main').val())
			},
			purchaseRequestDetail: purchaseRequestDetail
		}
		console.log(prUpdate);
		$.ajax({
			type : "PUT",
			url : alamatUrl+'/update',
			data : JSON.stringify(prUpdate),
			contentType : 'application/json',
			success : function(data){
				purchaseRequestDetail = [];
				console.log("berhasil save");
				resetModalAddPr();
				showPRByOutlet();
				//alert('Success');
			}, error : function(data){
				console.log("gagal");
				resetModalAddPr();
				showPRByOutlet();
				purchaseRequestDetail = [];
				//alert('Failed');
			}
			
		});
		$('#modal-add-pr').modal('hide');
		//showPRByOutlet();
//		HideItemForm();
	};
	//======================== End of Update ========================//
	//======================== Select Admin Action ========================//
	$('#select-pr-action').change(function(){
		var action = $(this).val();
		var id = $(this).attr('pr-id');
		console.log('pr-id : '+id);
		if(action == 'print'){
			window.location = '/miniproject/generate/purchase-request-detail/'+id;
		}else{
			$.ajax({
				type : 'GET',
				url : alamatUrl+'/'+action+'/'+id,
				success : function(){
					console.log('Success');
					resetModalAddPr();
					showPRByOutlet();
				},
				error : function(){
					console.log('Failed');
					resetModalAddPr();
					showPRByOutlet();
				}
			});
		}
	});
	//======================== End of Admin Action ========================//
	
	//======================== Search By Status Function ========================//
	function search(url){
		outletId = $('#select-outlet-main').val();
		$('#table-add-pr-body').empty();
		$('#table-view-pr-body').empty();
		$.ajax({
			dataType : "json",
		    url : url,
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
					if (data.status != 'Created'){
						$('#'+data.id+'').prop('disabled',true);
						$('#'+data.id+'').prop('hide',true);
					}
				});
		    }
		});	
	}
	//======================== End of Search By Status Function ========================//
	
	//======================== Select Toggle For Search By Status ========================//
	$('#select-search-by-status').change(function(){
		var status = $(this).val();
		if(status == 'All'){
			showPRByOutlet();
		}else{
			outletId = $('#select-outlet-main').val();
			//outletId = '${outlet.id }';
			url = alamatUrl+'/search-status?outletId='+outletId+'&status='+status;
			search(url);
		}
	});
	//======================== End of Select Toggle For Search By Status ========================//
	
	$('#search').on('input', function(e){
		var keyword = $(this).val();
		outletId = $('#select-outlet-main').val();
		$.ajax({
			dataType : "json",
		    url : alamatUrl+'/search-global?outletId='+outletId+'&search='+keyword,
			headers : {
		    	'Accept' : 'application/json',
		        'Content-Type' : 'application/json'
		    },
		    type : 'GET',
		    success : function(data){
		    	$("#table-view-pr-body").empty();
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
	});
	
});
