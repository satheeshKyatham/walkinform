$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});

var enqSFIDforEOI = '';
var enqName = '';
var enqid = '';
var paymentDataisNull = true;
var preferencDataisNull = true;
function enqDtlForAdminRefund () {
	var enqName = "ENQ - " + $('#enqNameInputRefund').val().trim();
	
	$.post(pageContext+"getEnqForAdminInventoryHold",{"enqName":enqName, "projectSFID":$('#projectid').val()},function(data){                      
		$("#enqDtlTableRefundEOI tbody").empty();
		var obj =JSON.parse(data);
		 var html = '';
         if (obj != null) {
        	 for(var i=0;i<obj.length;i++){
        		 html += "<tr>" +
        		 	" <td>"+obj[i].id+"</td>" +
        		 	" <td>"+obj[i].enq_name+"</td>" +
					" <td>"+obj[i].mobile__c+"</td>" +
					" <td>"+obj[i].name+"</td>" +
				" </tr>";
        		 enqSFIDforEOI = obj[i].enq_sfid;
        		 enqName = obj[i].enq_name;
        		 enqid = obj[i].id;
        	 }
        	 $("#enqDtlTableRefundEOI tbody").append(html);
         } else {
        	 enqSFIDforEOI = '';
        	 $("#enqDtlTableRefundEOI tbody").append("<tr><td colspan='4'>No records found</td></tr>");
         }
	}).done(function(data){
		if (enqSFIDforEOI != ''){
			$('#enquirysfid').val(enqSFIDforEOI);
			$('#enquiry_name').val(enqName);
			getEOITabPaymentRecordRefund();
		} else {
			$("#csPtColRefundEoi tbody").empty();
			$("#csPtColRefundEoi tbody").append("<tr><td colspan='9'>No records found</td></tr>");
			$('#enquirysfid').val("");
		}
	}).fail(function(xhr, status, error) {
		alert (error);
    });
}

//---------------------------------------------------------------//

function csPtDdEoi (e) {
	$(e).closest("tr").find('.csPtReachMexLengthEOI').remove();
	if ($(e).val() == 'Cheque') {
		$(e).closest("tr").find(".csPtTransactionIdEoi ").val("");
		$(e).closest("tr").find(".csPtTransactionIdEoi ").attr("maxlength","10");       
        $(e).closest("tr").find(".csPtTransactionIdEoi ").after("<small class='csPtReachMexLengthEOI'>ID can be max 10 characters long.</small>");
        
		$(e).closest("tr").find('.panAttachEoi').prop('disabled', false);
		$(e).closest("tr").find('.csPtBankNameEoi').prop('disabled', false);
		$(e).closest("tr").find('.csPtBranchEoi').prop('disabled', false);
	} else  if ($(e).val() == 'NEFT') {
		$(e).closest("tr").find(".csPtTransactionIdEoi").val("");
  	   	$(e).closest("tr").find(".csPtTransactionIdEoi").attr("maxlength","30");
  	   	$(e).closest("tr").find(".csPtTransactionIdEoi").after("<small class='csPtReachMexLengthEOI'>ID can be max 30 characters long.</small>");
		
		$(e).closest("tr").find('.csPtBankNameEoi').prop('disabled', false);
		$(e).closest("tr").find('.csPtBranchEoi').prop('disabled', false);
	}else  if ($(e).val() == 'Swipe' || $(e).val() == 'Wire Transfer') {
		$(e).closest("tr").find(".csPtTransactionIdEoi").val("");
 	   	$(e).closest("tr").find(".csPtTransactionIdEoi").attr("maxlength","30");
 	   	$(e).closest("tr").find(".csPtTransactionIdEoi").after("<small class='csPtReachMexLengthEOI'>ID can be max 30 characters long.</small>"); 
		
		$(e).closest("tr").find('.csPtBankNameEoi').prop('disabled', false);
		$(e).closest("tr").find('.csPtBranchEoi').prop('disabled', false);
	}
	else {
		$(e).closest("tr").find(".csPtTransactionIdEoi").val("");
  	    $(e).closest("tr").find(".csPtTransactionIdEoi").removeAttr("maxlength");
		
		$(e).closest("tr").find('.panAttachEoi').val("");
		$(e).closest("tr").find('.csPtBankNameEoi').val("");
		$(e).closest("tr").find('.csPtBranchEoi').val("");
		$(e).closest("tr").find('.csPtBankNameEoi').prop('disabled', true);
		$(e).closest("tr").find('.csPtBranchEoi').prop('disabled', true);
	}	
}

// function call in salesRequest.js in "populateBasicInfo" function end 
function getEOITabPaymentRecordRefund() {
	$.post(pageContext+"getEOIPaymentRecord",{"enqSfid":enqSFIDforEOI},function(data){
		$('#csPtColRefundEoi tbody').empty();
		var html = '';
		var obj =JSON.parse(data);
		var trans_date = '';
		var panTarget = '';
		var reciptTarget = '';
		var eoiTransactionTotalAmount = 0;
		var status = '';
		var rowStatusColr = '';
		var isactive = '';
		
		if(obj!=null){
			for(i = 0; i< obj.length; i++){    
				//panTarget = pageContext+"file?name="+obj[i].pan_attach+"&from=EOIbookingReference&eid="+obj[i].enq_sfid+"&fid="+obj[i].pan_attach.charAt(0);
				
				if (obj[i].cheque_attach != undefined){
					reciptTarget = pageContext+"file?name="+obj[i].cheque_attach+"&from=EOIbookingReference&eid="+obj[i].enq_sfid+"&fid="+obj[i].cheque_attach.substring(0, obj[i].cheque_attach.indexOf("Receipt_"));
				} else {
					reciptTarget = "";
				}
				
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
				
				isactive = obj[i].isactive;
				
				if (obj[i].isactive == 'N') {
					status = 'Not Approved';
					rowStatusColr = 'style="background-color: #ffd4d8;"';
				} else if (obj[i].isactive == 'O') {
					status = 'Approved <br> & Offer Created';
					rowStatusColr = 'style="background-color: #e7ffd1;"';
				} else if (obj[i].isactive == 'Y'){
					status = 'Approved';
					rowStatusColr = '';
				} else if (obj[i].isactive == 'R'){
					status = 'Rejected';
					rowStatusColr = 'style="background-color: #ffd4d8;"';
				} else {
					status = 'NA';
					rowStatusColr = '';
				}
				
				
				html += 	'<tr class="paymentDataPlotRow" '+rowStatusColr+'  data-rowid= "'+obj[i].id+'">'
								+ '<td style="text-align:center; font-size:11px;"><input value="'+isactive+'" class="isactiveStatus" style="display:none;"><span class="eoieditOldRec">'+status+'</span></td>' 
								+ '<td class="paymentTypeColtd" style="text-align:center;"><span class="eoieditOldRec">'+obj[i].payment_type+'</span></td>' 
								+ '<td class="banknameColtd" style="text-align:center;"><span class="eoieditOldRec">'+obj[i].bank_name+'</span></td>' 
								+ '<td class="trxidColtd" style="text-align:center;"><span class="eoieditOldRec">'+obj[i].transaction_id+'</span></td>' 
								+ '<td class="trxdateColtd" style="text-align:center;"><span class="eoieditOldRec">'+trans_date+'</span></td>' 
								+ '<td class="trxamountColtd" style="text-align:center;"><span class="eoieditOldRec">'+obj[i].transaction_amount+'</span></td>' 
								+ '<td class="receiptColtd" style="word-break: break-word;"> <span class="eoieditOldRec"><a target="_blank" href="'+reciptTarget+'">'+obj[i].cheque_attach+'</a></span></td>'
								+ '<td class="descriptionColtd" style="text-align:center;"><span class="eoieditOldRec">'+obj[i].description+'</span></td>'
								+'</tr>';
			}
			
			html = html.replace(/undefined/g, "");
			$("#csPtColRefundEoi tbody").append(html);
			paymentDataisNull = false;
		} else {
			$("#csPtColRefundEoi tbody").append("<tr><td colspan='9'>No records found</td></tr>");
			paymentDataisNull = true;
		}
	}).done(function(obj){
		
	});	
}












