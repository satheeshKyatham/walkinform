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
var pageContext = $("#pageContext").val();
function enqDtlForAdminRefund () {
	var enqName = "ENQ - " + $('#enqNameInputRefund').val().trim();
	
	$.post(pageContext+"/getEnqForAdminInventoryHold",{"enqName":enqName, "projectSFID":$('#projectid').val()},function(data){                      
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
	$.post(pageContext+"/getEOIPaymentRecordForRefund",{"enqSfid":enqSFIDforEOI},function(data){
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
					reciptTarget = pageContext+"/file?name="+obj[i].cheque_attach+"&from=EOIbookingReference&eid="+obj[i].enq_sfid+"&fid="+obj[i].cheque_attach.substring(0, obj[i].cheque_attach.indexOf("Receipt_"));
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
			$("#eoi_trx_amount").val(eoiTransactionTotalAmount);
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


function initiateRefundRequest()
{
	var validate=checkValidationOnSubmit('refund_div_id');
	if(validate) {
		
		//var tower=$('#towerMst').find('option:selected').attr('name');
		var formData = new FormData();	
		formData.append('ac_holder_name' ,$('#ref_acholder_name').val());
		//formData.append("project_name",$('#projectDataList :selected').text());
		formData.append("bank_name",$('#ref_bank_name').val());
		formData.append("branch_name",$('#ref_branch_name').val());
		formData.append("account_no",$('#ref_ac_no').val());
		formData.append("ifsc_code",$('#ref_ifsc_code').val());
		formData.append("account_type",$('#ref_ac_type').val());
//		formData.append("cancelled_check_path",$('#ref_cncelled_check').val());
		//formData.append("cancelledCheck",$('#ref_cncelled_check')[0].files[0]);
		
		formData.append("reason_for_cancel_refund",$('#ref_reason_cancellation').val());
		formData.append("description",$('#ref_description').val());
		formData.append("enquiry_sfid",$('#enquirysfid').val());
		formData.append("refund_initiated_by",$('#userid').val());
		formData.append("refund_amount",$('#eoi_trx_amount').val());
		
		formData.append("cancelled_check_file",$("#ref_cncelled_check")[0].files[0]);
		formData.append("project_sfid",$('#projectid').val());
		formData.append("region_name",$('#region_name').val());
		formData.append("projectname",$('#projectname').val());
		
		//formData.append("tower_name",$('#towerMst').val());
		if($('#eoi_trx_amount').val()== 0 || $('#eoi_trx_amount').val()==null || $('#eoi_trx_amount').val()=='null')
			{
				swal({
					title: "Payment Details are not available OR Refund Amount Showing 0.00",
				    text: "",
				    timer: 3000,
				    type: "error",
				});
			}
		else
			{
			var submitURL="refund_initiateData";
				$.ajax({
					url : submitURL,
					type : 'POST',
					data : formData,
					dataType: 'text',
					processData : false, 
					contentType : false,
					
			        success: function (data) {
			        	callEOIREFUND();
			        		swal({
			        			title: "Successfully Submitted",
			        		    text: "",
			        		    timer: 2000,
			        		    type: "success",
			    			});
			        		$("#enqDtlTableRefundEOI tbody").empty();
			        		$("#csPtColRefundEoi tbody").empty();
			        		
			        		$('#ref_acholder_name').val('');
			        		$('#ref_bank_name').val('');
			        		$('#ref_branch_name').val('');
			        		$('#ref_ac_no').val('');
			        		$('#ref_ifsc_code').val('');
			        		$('#ref_ac_type').val('');
			        		$('#ref_reason_cancellation').val('');
			        		$('#ref_description').val('');
			        		//$('#enquirysfid').val('');
			        		$("#ref_cncelled_check").val('');
			        		$("#enqNameInputRefund").val('');
			        }
				});
			}
	}
}

function callEOIREFUND()
{
	
	$.get(pageContext+"/getInitiateRefundData",{"userid":$('#userid').val(), "project_sfid":$('#projectid').val()},function(data){                      
		$("#csPtColRefundIntiatedEoi tbody").empty();
		var obj =JSON.stringify(data);
		var objJson =JSON.parse(obj);
		
		 var html = '';
         if (objJson != null) {
        	 for(var i=0;i<objJson.length;i++){
        		 html += "<tr>" +
        		 	" <td>"+objJson[i].ac_holder_name+"</td>" +
        		 	" <td>"+objJson[i].bank_name+"</td>" +
					" <td>"+objJson[i].branch_name+"</td>" +
					" <td>"+objJson[i].account_no+"</td>" +
					" <td>"+objJson[i].ifsc_code+"</td>" +
					" <td>"+objJson[i].trx_id+"</td>" +
					" <td>"+objJson[i].account_type+"</td>" +
					" <td>"+objJson[i].reason_for_cancel_refund+"</td>" +
					" <td>"+objJson[i].description+"</td>" +
					" <td>"+objJson[i].refund_amount+"</td>" +
					" <td>"+objJson[i].approval_refund_status+"</td>" +
				" </tr>";
        	 }
        	 $("#csPtColRefundIntiatedEoi tbody").append(html);
         } else {
        	 enqSFIDforEOI = '';
        	 $("#csPtColRefundIntiatedEoi tbody").append("<tr><td colspan='4'>No records found</td></tr>");
         }
	}).done(function(data){
		
	}).fail(function(xhr, status, error) {
		alert (error);
    });
	
}

function getEOIREFUNDDetails()
{
	$("#mainPageLoad4").show();
	$.get($('#contextPath').val()+"/getInitiateRefundData",{"userid":null, "project_sfid":$('#projectid').val()},function(data){                      
		$("#EOI_Payment_Refund_List").dataTable().fnDestroy();
		$("#EOI_Payment_Refund_List tbody").empty();
		 var html = '';
         if (data != null) {
        	 for(var i=0;i<data.length;i++){
        		 var statusSelect="";
        		 if(data[i].approval_refund_status!=null)
					{
        			 statusSelect = "<td>"+data[i].neft_rtgs_utr_no+"</td><td>"+data[i].refund_comments+"</td><td>"+data[i].approval_refund_status+"</td><td></td>";
					}
				else
					{
						statusSelect = 
								"<td><input type='text' class='neft_rtgs_utr_no"+data[i].id+"'></td>" +
								"<td><textarea class='refund_comments"+data[i].id+"'></textarea></td>" +
								"<td><select class='selection' id='srefund"+data[i].id+"' class='form-control'>" +
								"<option value='Pending with Accounts'>Pending with Accounts</option><option value='Partial Processed'>Partial Processed</option><option value='Fully Processed'>Fully Processed</option><option value='Rejected'>Rejected</option></select></td>" +
								" <td><button onclick='RefundSumbit("+data[i].id+")'>Submit</button></td>" ;
					}
        		 html += "<tr>" +
        		 	" <td>"+data[i].ac_holder_name+"</td>" +
        		 	" <td>"+data[i].bank_name+"</td>" +
					" <td>"+data[i].branch_name+"</td>" +
					" <td>"+data[i].account_no+"</td>" +
					" <td>"+data[i].ifsc_code+"</td>" +
					" <td id='trx_id"+data[i].id+"'>"+data[i].trx_id+"</td>" +
					" <td>"+data[i].account_type+"</td>" +
					" <td>"+data[i].reason_for_cancel_refund+"</td>" +
					" <td>"+data[i].description+"</td>" +
					" <td>"+data[i].refund_amount+"</td>" +
					statusSelect+
				" </tr>";
        	 }
        	 $("#EOI_Payment_Refund_List tbody").append(html);
         } else {
        	 enqSFIDforEOI = '';
        	 $("#EOI_Payment_Refund_List tbody").append("<tr><td colspan='4'>No records found</td></tr>");
         }
         $('#EOI_Payment_Refund_List').DataTable(
 				{
 					destroy: true,
 					dom: 'Bfrtip',
 					 "buttons": [
 						 { "extend": 'excel', "text":'Export To Excel',"className": 'btn btn-default btn-xs',title: 'EOI Refund List' }
 				      ],
 				      "order": []
 				});
	}).done(function(data){
		$("#mainPageLoad4").hide();
	}).fail(function(xhr, status, error) {
		alert (error);
		$("#mainPageLoad4").hide();
    });
	
}

function RefundSumbit(id)
{
	if($('#srefund'+id).val()=='Pending with Accounts')
	{
		//alert("Please change the status before Submit");
		swal({
			title: "Please change the status before Submit",
		    text: "",
		    timer: 3000,
		    type: "error",
		});
	}
	else
		{
		if($('#srefund'+id).val()=='Rejected')
		{
			if($('.refund_comments'+id).val()=='')
				{
					//alert("Comments should be mandatory on Rejected Refund");
					swal({
						title: "Comments should be mandatory on Rejected Refund",
					    text: "",
					    timer: 3000,
					    type: "error",
					});
				}
			else
				approvalInitiateRefundProcess(id);
		}
		else
			{
			if($('.neft_rtgs_utr_no'+id).val()=='')
				{
					//alert("NEFT RTGS No. Mandatory");
					swal({
						title: "NEFT RTGS No. Mandatory",
					    text: "",
					    timer: 3000,
					    type: "error",
					});
				}
			else
				approvalInitiateRefundProcess(id);
			}
		
		}
	
	
	}
function approvalInitiateRefundProcess(id)
{
	$.post($('#contextPath').val()+"/approvalInitiateRefundProcess",{"id":id, "rtgs_no":$('.neft_rtgs_utr_no'+id).val(),"comments":$('.refund_comments'+id).val(), "status":$('#srefund'+id).val()
		,"loged_useremail":$('#loged_useremail').val(), "loged_userid":$('#loged_userid').val(),"trx_id":$('#trx_id'+id).text()},function(data){                      
		//refresh table
	}).done(function(data){
		getEOIREFUNDDetails();
		swal({
			title: "Refund status has been submitted",
		    text: "",
		    timer: 3000,
		    type: "success",
		});
	}).fail(function(xhr, status, error) {
		getEOIREFUNDDetails();
		alert (error);
    });
	
	}

/*$(document).ready(function() {
var table;


$("#EOI_Payment_Refund_List").on('mousedown.edit', "i.fa.fa-pencil-square", function(e) {

  $(this).removeClass().addClass("fa fa-envelope-o");
  var $row = $(this).closest("tr").off("mousedown");
//  var $tds = $row.find("td").not(':first').not(':last');
  var $tds = $row.find("td:nth-last-child(-n+2)").not(':last');

  $.each($tds, function(i, el) {
    var txt = $(this).text();
    $(this).html("").append("<input type='text' value=\""+txt+"\">");
  });
  
  var $firstTdSelect = $row.find("td:first");
  var selectOptions = $("#selectbasic").clone();
 
  $row.find("td:nth-last-child(-n+2)").not(':last').html(selectOptions.show());

});

$("#EOI_Payment_Refund_List").on('mousedown', "input", function(e) {
  e.stopPropagation();
});

$("#EOI_Payment_Refund_List").on('mousedown.save', "i.fa.fa-envelope-o", function(e) {
  
  $(this).removeClass().addClass("fa fa-pencil-square");
  var $row = $(this).closest("tr");
  var $tds = $row.find("td:nth-last-child(-n+2)").not(':last');
  
  $.each($tds, function(i, el) {
    var txt = $(this).find("input").val()
    $(this).html(txt);
  });
  
  var $firstTdSelect = $row.find("td:first");
  var selectValue = $firstTdSelect.find("select").val();
  $row.find("td:nth-last-child(-n+2)").not(':last').html(selectValue);
  alert(selectValue);
  //table.cell($firstTdSelect).data(selectValue);
});


 $("#EOI_Payment_Refund_List").on('mousedown', "#selectbasic", function(e) {
  e.stopPropagation();
});
});
*/

