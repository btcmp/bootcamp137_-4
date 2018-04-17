$(document).ready(function(){
	var alamatUrl = window.location.href;
	$("#purchase-order-side-option").addClass('active');
	$("#treeview-transaction").addClass('active');
	showPOByOutlet();
	$('#select-outlet').val($('#select-outlet-main').val());
	
	//=============== Utility ===================//
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
	function resetTable(){
		$('#table-po-detail-body').empty();
		$('#table-edit-po-body').empty();
		$('#table-po-history').empty();
		$('#table-edit-po-body-total').empty();
	};
	// ============================================//
	$('#select-outlet-main').on('change', function() {
		$('#select-outlet').val($('#select-outlet-main').val());
		showPOByOutlet();
	});
	
	$('#btn-export').click(function(){
		console.log('clicked');
		window.location = '/miniproject/generate/purchase-order';
	});
	
	// ============ Get PO by Outlet ===========//
	function showPOByOutlet(){
		outletId = $('#select-outlet-main').val();
		//$('#table-add-po-body').empty();
		//$('#table-view-po-body').empty();
		$.ajax({
			dataType : "json",
		    url : alamatUrl+'/getAllPo?outletId='+outletId,
			headers : {
		    	'Accept' : 'application/json',
		        'Content-Type' : 'application/json'
		    },
		    type : 'GET',
		    success : function(data){
		    	console.log(data);
		    	$.each(data, (key, data) =>{
		    		if (data.supplier == null){
		    			namaSupplier = "Belum dipilih";
		    		} else{
		    			namaSupplier = data.supplier.name;
		    		}
					$('#table-view-po-body').append('<tr> <td>'+getDateFormat(data.createdOn)+'</td>'+
							'<td>'+namaSupplier+'</td>'+
							'<td>'+data.poNo+'</td>'+
							
							'<td>'+data.grandTotal+'</td>'+
							'<td>'+data.status+'</td>'+
							'<td><a id='+data.id+' class="btn-update-po btn btn-primary">Edit</a>'+
							'<a id='+data.id+' class="btn-detail-po btn btn-success">View</a></td>'
					)
					if (data.status != 'Created'){
						$('#'+data.id+'').prop('disabled',true);
						$('#'+data.id+'').prop('hide',true);
					}
				});
		    }
		});
	}
	
	$('#table-view-po').on('click','.btn-update-po',function(e){
		resetTable();
		var row = $(this).closest("tr");
		var poNo = row.find('td').eq(2).text();
		
		$(this).attr('state','update');
		var id = $(this).attr('id');
		dataku = getPurchaseOrderById(id);
		$('#modal-edit-po').modal({backdrop: 'static', keyboard: false});
		$('#btn-po-save').attr('po-no',poNo);
		$('#btn-po-save').attr('po-id', id);
		$('#btn-po-submit').attr('po-id', id);
		$('#btn-po-save').attr('state','update');	
	});
	$('#table-view-po').on('click','.btn-detail-po',function(e){
		resetTable();
		var id = $(this).attr('id');
		dataku = getPurchaseOrderById(id);
		$('#modal-po-detail').modal({backdrop: 'static', keyboard: false});
		
	});
	$('#table-edit-po-body').on('change', '.index', function(e){
		index = $(this).attr('idx');
		unitCost = $(this).val();
		qty = parseInt($(this).closest("tr").find('td').eq(2).text());
		subTotal = unitCost * qty;
		$('#subTotal-idx'+index+'').html(subTotal)
		grandTotal = 0;
		$('#table-edit-po-body').find('.subTotal').each(function(e){
			grandTotal += parseInt($(this).text());
		})
		$('#grand-total').html(grandTotal);
		//console.log(grandTotal);
	});
	$('#btn-po-save').click(function(e){
		var state = $(this).attr('state');
		status = 'Created';
		console.log('clicked '+state);
		if (state == 'create'){
			save(e, status);
		} else{
			poNo = $('#btn-po-save').attr('po-no');
			poId = parseInt($('#btn-po-save').attr('po-id'));
			console.log('di button save'+poNo);
			update(e, status, poNo, poId);
		}
	});
	$('#btn-po-submit').click(function(e){
		//var state = $(this).attr('state');
		console.log('Submitted')
		poNo = $('#btn-po-save').attr('po-no');
		poId = parseInt($(this).attr('po-id'));
		status = 'Submitted';
		update(e, status, poNo, poId);
		
	});
	
	$('#select-po-action').change(function(){
		var action = $(this).val();
		var id = $(this).attr('po-id');
		console.log('po-id : '+id);
		if(action == 'print'){
			window.print();
		}else{
			$.ajax({
				type : 'GET',
				url : alamatUrl+'/'+action+'/'+id,
				success : function(){
					console.log('Success');
					resetTable();
					showPOByOutlet();
				},
				error : function(){
					console.log('Failed');
					resetTable();
					showPOByOutlet();
				}
			});
		}
		$('#modal-po-detail').modal('hide');
	});
	
	
	getPurchaseOrderById = (id) => {
		var dataTemp;
		function myCallback(response){
			console.log("berhasil");
			dataTemp = response;
			outletId = $('#select-outlet-main').val();
			tanggal = getDateFormat(dataTemp.readyTime);
			grandTotal = dataTemp.grandTotal;
			console.log(grandTotal);
			$('#edit-po-notes').val(dataTemp.notes);
			$('#edit-po-date').val(tanggal);
			
			// ====== Modal Detail ======//
			if (dataTemp.supplier == null){
				$('#detail-supplier-name').html('Supplier belum dipilih');
				$('#detail-supplier-phone').html('Supplier belum dipilih');
				$('#detail-supplier-address').html('Supplier belum dipilih');
				$('#detail-supplier-region').html('Supplier belum dipilih');
				$('#detail-supplier-province').html('Supplier belum dipilih');
				$('#detail-supplier-postalCode').html('Supplier belum dipilih');
			} else{
				$('#detail-supplier-name').html(dataTemp.supplier.name);
				$('#detail-supplier-phone').html(dataTemp.supplier.phone);
				$('#detail-supplier-address').html(dataTemp.supplier.address);
				$('#detail-supplier-region').html();
				$('#detail-supplier-province').html();
				$('#detail-supplier-postalCode').html();
			}
			
			$('#view-pod-num').html(dataTemp.poNo);
			$('#view-pod-created').html('Belum Integrasi dengan user');
			$('#view-pod-time').html(tanggal);
			$('#view-pod-status').html(dataTemp.status);
			let option = [];
			if (dataTemp.status == 'Created'){
				$('#select-po-action').attr('po-id', id);
				option.push('<option disabled selected value="more">More</option>');
				option.push('<option disabled value="approve">Approve</option>');
				option.push('<option disabled value="reject">Reject</option>');
				option.push('<option value="print">Print</option>');
				option.push('<option disabled disabled value="process" disabled>Process</option>');
			} else if (dataTemp.status == 'Submitted'){
				$('#select-po-action').attr('po-id', id);
				option.push('<option disabled selected value="more">More</option>');
				option.push('<option value="approve">Approve</option>');
				option.push('<option value="reject">Reject</option>');
				option.push('<option value="print">Print</option>');
				option.push('<option disabled value="process" disabled>Process</option>');
			} else if (dataTemp.status == 'Approved'){
				$('#select-po-action').attr('po-id', id);
				option.push('<option disabled selected value="more">More</option>');
				option.push('<option disabled value="approve">Approve</option>');
				option.push('<option disabled value="reject">Reject</option>');
				option.push('<option value="print">Print</option>');
				option.push('<option value="process">Process</option>');
			} else if (dataTemp.status == 'Rejected'){
				$('#select-po-action').attr('po-id', id);
				option.push('<option disabled selected value="more">More</option>');
				option.push('<option disabled value="approve">Approve</option>');
				option.push('<option disabled value="reject">Reject</option>');
				option.push('<option value="print">Print</option>');
				option.push('<option disabled value="create-po" disabled>Create PO</option>');
			} else if (dataTemp.status == 'Processed'){
				$('#select-po-action').attr('pr-id', id);
				option.push('<option disabled selected value="more">More</option>');
				option.push('<option disabled value="approve">Approve</option>');
				option.push('<option disabled value="reject">Reject</option>');
				option.push('<option value="print">Print</option>');
				option.push('<option disabled value="process" disabled>Process</option>');
			}
			$('#select-po-action').html(option);
			
			//========= Get PO History ==========//
			$.ajax({
				type: 'GET',
				url : alamatUrl+'/getHistory/'+id,
				datatype : 'json',
				success : function(data){
					$.each(data, function(key,val){
						$('#table-po-history').append('<tr><td>On '+ getDateFormat(val.createdOn) +' - Purchase Order '+dataTemp.poNo+ ' is '+ val.status.toLowerCase()+'</td> </tr>')
					});
					
				}
			});
			//console.log(dataTemp);
			//createVariantTable(listVariant);
			//========= Get PR Detail ==========//
			$.ajax({
				type : 'GET',
				url : alamatUrl+'/findPODetailAndQty?outletId='+outletId+'&poId='+id,
				dataType: 'json',
				success : function(data){
					//console.log(data);
					added = [];
					addedQty = [];
					index = 0;
					$.each(data, function(key, val) {
						$('#table-edit-po-body').append('<tr var-id='+val[1].itemVariant.id+' data-idx='+index+' pod-Id='+val[0].id+' id="po-PurchaseOrder'+ val[1].id +'">'
								+'<td id="'+ val[1].itemVariant.id +'">'+ val[1].itemVariant.item.name +'-'+ val[1].itemVariant.name +'</td>'
								+'<td> '+val[1].endingQty+'</td>'
								+'<td> '+val[0].requestQty+'</td>'
								+'<td><input id=input-idx'+index+' idx='+index+' class="index" type="number" min="0" value='+ val[0].unitCost +'></td>'
								+'<td id=subTotal-idx'+index+' class="subTotal"> '+val[0].subTotal+'</td>'
								+'</tr>');
						
						index++;
						$('#table-po-detail-body').append('<tr prd-Id='+val[0].id+' id="pr-PurchaseRequest'+ val[0].id +'">'
								+'<td id="'+ val[1].itemVariant.id +'">'+ val[1].itemVariant.item.name +'-'+ val[1].itemVariant.name +'</td>'
								+'<td>'+ val[1].endingQty +'</td><td>'+ val[0].requestQty +'</td></tr>');
						
						
					});
					$('#table-edit-po-body-total').append('<tr> <td> Total </td><td></td><td></td><td></td><td id="grand-total">'+ grandTotal+'</td>');
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
	
	//======================== Fungsi Update ========================//
	update = (e, poStatus, getPoNo, poId) => {
		e.preventDefault();
		alamatUrl = window.location.href;
		
		purchaseOrderDetail =[];
		
		$('#table-edit-po-body > tr').each(function(index, data){
			var pod = {
				id : parseInt($(data).attr('pod-id')),
				requestQty : parseInt($(data).find('td').eq(2).text()),
				unitCost : parseInt($(data).find('input').val()),
				subTotal : parseInt($(data).find('td').eq(4).text()),
				variant : {
					id : parseInt($(data).attr('var-id'))
				}
			}
			purchaseOrderDetail.push(pod);
			
		});
		console.log(purchaseOrderDetail);
		
		var po = {
			id : poId,
			poNo : getPoNo,
			notes : $('#edit-po-notes').val(),
			status : poStatus,
			grandTotal : parseInt($('#grand-total').text()),
			outlet : {
				id : parseInt($('#select-outlet-main').val())
			},
			supplier :{
				id : parseInt($('#select-supplier').val())
			},
			purchaseOrderDetail: purchaseOrderDetail
		}
		console.log(po);
		$.ajax({
			type : "PUT",
			url : alamatUrl+'/update',
			data : JSON.stringify(po),
			contentType : 'application/json',
			success : function(data){
				purchaseRequestDetail = [];
				console.log("berhasil save");
				//resetModalAddPr();
				showPOByOutlet();
				//alert('Success');
				$('#table-view-po-body').empty();
			}, error : function(data){
				purchaseRequestDetail = [];
				console.log("gagal");
				//resetModalAddPr();
				showPOByOutlet();
				$('#table-view-po-body').empty();
				//alert('Failed');
			}
			
		});
		$('#modal-edit-po').modal('hide');
		//showPOByOutlet();
//		HideItemForm();
	};
	//======================== End of Update ========================//
	
	//======================== Select Toggle For Search By Status ========================//
	$('#select-search-by-status').change(function(){
		var status = $(this).val();
		if(status == 'All'){
			$('#table-view-po-body').empty();
			showPOByOutlet();
		} else {
			outletId = $('#select-outlet-main').val();
			//outletId = '${outlet.id }';
			url = alamatUrl+'/search-status?outletId='+outletId+'&status='+status;
			search(url);
		}
	});
	//======================== End of Select Toggle For Search By Status ========================//
	//======================== Search By Status Function ========================//
	function search(url){
		outletId = $('#select-outlet-main').val();
		$('#table-add-po-body').empty();
		$('#table-view-po-body').empty();
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
		    		$('#table-view-po-body').append('<tr> <td>'+getDateFormat(data.createdOn)+'</td>'+
							'<td>'+namaSupplier+'</td>'+
							'<td>'+data.poNo+'</td>'+
							
							'<td>'+data.grandTotal+'</td>'+
							'<td>'+data.status+'</td>'+
							'<td><a id='+data.id+' class="btn-update-po btn btn-primary">Edit</a>'+
							'<a id='+data.id+' class="btn-detail-po btn btn-success">View</a></td>'
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
		    	$("#table-view-po-body").empty();
		    	$.each(data, (key, data) =>{
		    		$('#table-view-po-body').append('<tr> <td>'+getDateFormat(data.createdOn)+'</td>'+
							'<td>'+namaSupplier+'</td>'+
							'<td>'+data.poNo+'</td>'+
							
							'<td>'+data.grandTotal+'</td>'+
							'<td>'+data.status+'</td>'+
							'<td><a id='+data.id+' class="btn-update-po btn btn-primary">Edit</a>'+
							'<a id='+data.id+' class="btn-detail-po btn btn-success">View</a></td>'
					)
				});
		    }
		});
	});
});
