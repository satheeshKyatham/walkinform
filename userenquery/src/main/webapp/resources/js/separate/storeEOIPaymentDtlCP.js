$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});
var amIEoi = 0;
var amNumEoi = 4; 



function addMorePtBtnEoi () {
	if(amIEoi < amNumEoi) {
		$('#csPtColEoi tr:last-child').after('<tr class="csPtDataRowEoi"><td><input class="csPtEnqSfidEoi" style="display:none;" value="'+$('#enquirysfid').val()+'"/>  <select onchange="csPtDdEoi(this)" class="full form-control input-sm csPtDropDownEoi requiredField"><option value="">Select</option><option value="Cheque">Cheque</option><option value="NEFT">NEFT/Credit</option><option value="Swipe">Swipe</option></select></td><td><input class="full form-control input-sm csPtBankNameEoi requiredField" placeholder="Bank Name"/></td><td><input class="full form-control input-sm csPtBranchEoi requiredField" placeholder="Branch Name"/></td><td><input class="full form-control input-sm csPtTransactionIdEoi requiredField" placeholder="Transaction ID" /></td><td><input type="date" class="full form-control input-sm csPtTransactionDateEoi requiredField" placeholder="Transaction Date"/></td><td><input  class="numericField full form-control input-sm csPtTransactionAmountEoi requiredField" onkeyup="csPtcalculateGrandTotalEoi()" placeholder="Transaction Amount" name="amount"/></td> <td> <input type="file" class="full form-control input-sm panAttachEoi requiredField"> </td> <td> <input type="file" class="requiredField full form-control input-sm receiptAttachEoi"/>  <td><textarea class="full form-control input-sm csPtDescriptionEoi" placeholder="Description"></textarea></td><td class="removeCsPtColEoi txtCenter"><i onclick="removeCsPtColEoi(this)" class="fa fa-times-circle"></i></td></tr>');
		amIEoi++;
	}else {
		swal({
			title: "You've reached the limit",
		    text: "",
		    timer: 2000,
		    type: "error",
		});
	}
}

function removeCsPtColEoi (e) {
	$(e).closest("tr").remove();
	amIEoi -= 1;
	
	csPtcalculateGrandTotalEoi ();
}

function csPtDdEoi (e) {
	//if ($(e).val() == 'Bank') {
	if ($(e).val() == 'Cheque') {
		
		$(e).closest("tr").find('.panAttachEoi').prop('disabled', false);
	
		$(e).closest("tr").find('.csPtBankNameEoi').prop('disabled', false);
		$(e).closest("tr").find('.csPtBranchEoi').prop('disabled', false);
	
	} else  if ($(e).val() == 'NEFT') {
		
		//$(e).closest("tr").find('.panAttachEoi').prop('disabled', true);
		
		$(e).closest("tr").find('.csPtBankNameEoi').prop('disabled', false);
		$(e).closest("tr").find('.csPtBranchEoi').prop('disabled', false);
	}else  if ($(e).val() == 'Swipe') {
		
		//$(e).closest("tr").find('.panAttachEoi').prop('disabled', true);
		
		$(e).closest("tr").find('.csPtBankNameEoi').prop('disabled', false);
		$(e).closest("tr").find('.csPtBranchEoi').prop('disabled', false);
	}
	else {
		$(e).closest("tr").find('.panAttachEoi').val("");
		$(e).closest("tr").find('.csPtBankNameEoi').val("");
		$(e).closest("tr").find('.csPtBranchEoi').val("");
		
		$(e).closest("tr").find('.csPtBankNameEoi').prop('disabled', true);
		$(e).closest("tr").find('.csPtBranchEoi').prop('disabled', true);
		//$(e).closest("tr").find('.panAttachEoi').prop('disabled', true);
	}	
}

function csPtcalculateGrandTotalEoi() {
	var grandTotal = 0;
    $("#csPtColEoi").find('input[name="amount"]').each(function () {
        grandTotal += +$(this).val();
    });
    $("#csPtGrandtotalEoi").text(grandTotal.toFixed(2));
}

function cpPymtDataEoi () {
	debugger;
	var pageContext = $("#contextPath").val()+"/";
	
	var i = 0;
	var k = 0;
	debugger;
	$("#csPtColEoi .csPtDataRowEoi").each(function () {
		var formData = new FormData();
	 	formData.append('panAttachEoi', $(this).find('.panAttachEoi')[0].files[0]);
	 	formData.append('receiptAttachEoi', $(this).find('.receiptAttachEoi')[0].files[0]);
	 	formData.append('rowCount', i);
	 	
	 	formData.append('enqID', $('#enquirysfid').val());
	 	
		$.ajax({
			url : 'EOIPaymentFileUploadServlet',
			type : 'POST',
			data : formData,
			processData : false,
			contentType : false,
			success : function(data) {
			
			}
		});
		
		i++
	});
	
	
	var arrayData = [];
	$("#csPtColEoi .csPtDataRowEoi").each(function () {
	    var csPtData = {};
	    //csPtData.enq_sfid = $(this).find('.csPtEnqSfidEoi').val();
	    csPtData.payment_type = $(this).find('.csPtDropDownEoi').val();
	    csPtData.bank_name = $(this).find('.csPtBankNameEoi').val();
	    csPtData.branch = $(this).find('.csPtBranchEoi').val();
	    csPtData.transaction_id = $(this).find('.csPtTransactionIdEoi').val();
	    csPtData.transaction_date = $(this).find('.csPtTransactionDateEoi').val();
	    csPtData.transaction_amount = $(this).find('.csPtTransactionAmountEoi').val();
	    csPtData.description = $(this).find('.csPtDescriptionEoi').val();
	    csPtData.total_amount = $('#csPtGrandtotalEoi').text().trim();
	    csPtData.enq_sfid = $('#enquirysfid').val();
	    csPtData.project_sfid = $('#projectSfid').val();
	    
	    csPtData.project_name = $('#projectname').val();
	    csPtData.user_email = $('#useremailID').val();
	    csPtData.user_name = $('#username').val();
	    csPtData.isfromcp='Y';
	    // csPtData.gpl_cs_balance_details_id = balance_details_primeryId.id;
	    //csPtData.offerid = balance_details_primeryId.offer_sfid;
	    
	    if($(this).find('.panAttachEoi').val() != "") {
	    	csPtData.pan_attach = k+"PAN_"+$(this).find('.panAttachEoi')[0].files[0].name;
	    }else {
	    	csPtData.pan_attach ="";
	    }
	    
	    if ($(this).find('.receiptAttachEoi').val() != "") {
	    	csPtData.cheque_attach = k+"Receipt_"+$(this).find('.receiptAttachEoi')[0].files[0].name;
	    }else {
	    	csPtData.cheque_attach ="";
	    }
	    
	    arrayData.push(csPtData);
	    k++
	});
	
	$.post(pageContext+"insertEOIPaymentDtl",{"paymentDtlJson" : JSON.stringify(arrayData)},function(data){				 
	}).done(function(data){
		
	});
}


// function call in salesRequest.js in "populateBasicInfo" function end 
function getEOITabPaymentRecord () {
	
	//$('#csPtCol tbody').empty();
	
	$('#csPtGrandtotalEoi').text("");
	
	$.post(pageContext+"getEOIPaymentRecord",{"enqSfid":$('#enquirysfid').val()},function(data){
		
		var html = '';
		var obj =JSON.parse(data);
		
		
		var trans_date = '';
		
		
		var panTarget = '';
		var reciptTarget = '';
		
		var eoiTransactionTotalAmount = 0;
		
		if(obj!=null){
			
			for(i = 0; i< obj.length; i++){    
				
				panTarget = pageContext+"file?name="+obj[i].pan_attach+"&from=EOIbookingReference&eid="+obj[i].enq_sfid+"&fid="+obj[i].pan_attach.charAt(0);
				reciptTarget = pageContext+"file?name="+obj[i].cheque_attach+"&from=EOIbookingReference&eid="+obj[i].enq_sfid+"&fid="+obj[i].cheque_attach.charAt(0);
				
				if (obj[i].transaction_date != '') {
					var date = new Date(obj[i].transaction_date);

					var curr_date = date.getDate();
					var curr_month = date.getMonth() + 1; //Months are zero based
					var curr_year = date.getFullYear();
					
					trans_date = curr_date + "-" + curr_month + "-" + curr_year;
				}else {
					trans_date = '';
				}
				
				
				
				
				eoiTransactionTotalAmount = parseFloat(parseFloat(obj[i].transaction_amount)+parseFloat(eoiTransactionTotalAmount)).toFixed(2);
				
				html += 	'<tr>'
								+ '<td style="text-align:center;">'+obj[i].payment_type+'</td>' 
								+ '<td style="text-align:center;">'+obj[i].bank_name+'</td>' 
								+ '<td style="text-align:center;">'+obj[i].branch+'</td>' 
								+ '<td style="text-align:center;">'+obj[i].transaction_id+'</td>' 
								+ '<td style="text-align:center;">'+trans_date+'</td>' 
								+ '<td style="text-align:center;">'+obj[i].transaction_amount+'</td>' 
								+ '<td> <a target="_blank" href="'+panTarget+'">'+obj[i].pan_attach+'</a></td>' 
								+ '<td> <a target="_blank" href="'+reciptTarget+'">'+obj[i].cheque_attach+'</a></td>'
								+ '<td style="text-align:center;">'+obj[i].description+'</td>'
								+ '<td></td>'
							"</tr>";
			}
			
			$('#csPtGrandtotalEoi').text(eoiTransactionTotalAmount);
			
			html = html.replace(/undefined/g, "");
		
			$("#csPtColEoi tbody tr:first-child").after(html);
		}
	}).done(function(obj){
		
	});	
}