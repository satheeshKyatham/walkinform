$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});

var todayDate = getDate();
$('.csPtTransactionDateOP').val(todayDate);

function getDate() {
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0!
	var yyyy = today.getFullYear();
	if(dd<10) {
		dd = '0'+dd
	} 
	if(mm<10) {
		mm = '0'+mm
	} 
	today = yyyy + '-' + mm + '-' + dd;
	
	return today;
}

var amIEoi = 0;
var amNumEoi = 9; 

function addMorePtBtnOP () {
	if(amIEoi < amNumEoi) {
		
		$('#csPtColOP tr:last-child').after("<tr class='csPtDataRowOP addedRow'> " +
				" <td><input type='date' value='"+todayDate+"' class='form-control input-sm csPtTransactionDateOP requiredField' placeholder='Transaction Date' readonly/></td>" +
				" <td><input maxlength='10' class='numericWithoutDecimal  numericField full form-control input-sm csPtTransactionAmountOP requiredField' onkeyup='csPtcalculateGrandTotalOP()' placeholder='Transaction Amount' name='amount'/></td>" +
				" <td><textarea class='full form-control input-sm csPtDescriptionOP' placeholder='Description'></textarea></td> " +
				" <td class='removeCsPtColOP txtCenter'><i onclick='removeCsPtColOP(this)' class='fa fa-times-circle redColr cursorPoint'></i></td></tr> ");
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

function removeCsPtColOP (e) {
	$(e).closest("tr").remove();
	amIEoi -= 1;
	
	csPtcalculateGrandTotalOP ();
}

function csPtcalculateGrandTotalOP() {
	var grandTotal = 0;
    $("#csPtColOP").find('input[name="amount"]').each(function () {
        grandTotal += +$(this).val();
    });
    $("#csPtGrandtotalOP").text(grandTotal.toFixed(2));
}



function csPymtDataOP () {
	
	var pageContext = $("#pageContext").val()+"/";
	var i = 0;
	var k = 0;
	
	var arrayData = [];
	$("#csPtColOP .csPtDataRowOP").each(function () {
	    var csPtData = {};
	    
	    //csPtData.enquiry_name = enqSFDCName;
	    //csPtData.enquiry_sfid = $('#enquirysfid').val();
	    csPtData.unit_no = "";
	    csPtData.unit_sfid = "";
	    //csPtData.project_sfid = $('#projectId').val();
	    //csPtData.project_name = $('#projectname').val();
	    csPtData.amount = $(this).find('.csPtTransactionAmountOP').val();
	    //csPtData.createdby = $('#userid').val();
	    //csPtData.updatedby = $('#userid').val();
	    csPtData.transaction_date_string = $(this).find('.csPtTransactionDateOP').val();
	    csPtData.description = $(this).find('.csPtDescriptionOP').val();  
	    //csPtData.request_url = "request_url";  

	   
	    arrayData.push(csPtData);
	    k++
	});
	
	$.post(pageContext+"insertPaymentRequest",{"paymentDtlJson" : JSON.stringify(arrayData), 
		"userid" : $('#userid').val(),
		"enq_sfid" : $('#enquirysfid').val(),
		"enquiry_name" : enqSFDCName,
		"project_sfid" : $('#projectId').val(),
		"project_name" : $('#projectname').val(),
		"customer_mobile" : customerMobileNo1,
		"user_email" : $('#useremailID').val(),
		"user_name" : $('#username').val(),
		"customer_name" : customerFullname1,
		"customer_email" : customerEmail1
		
	},function(data){				 
	
	}).done(function(data){
		var obj =JSON.parse(data);
		
		if(obj!=null){
			alert (obj.error_msg);
			
			if (obj.status == "STATUS_OK") {
				$(".csPtDataRowOP .csPtTransactionAmountOP").val("")
				$(".csPtDataRowOP .csPtDescriptionOP").val("")
				getPaymentReqRecord ();
				
				$(".addedRow").remove();
				amIEoi = 0;
			}
			
			/*if (obj.status == "STATUS_NOTOK" && obj.error_id == "ER1001") {
				alert (obj.status);
			} else if (obj.status == "STATUS_NOTOK" && obj.error_id == "ER1002") {
				alert ();
			} else if (obj.status == "STATUS_NOTOK" && obj.error_id == "ER1003") {
				alert ();
			} else if (obj.status == "STATUS_NOTOK" && obj.error_id == "ER1003") {
				
			}*/
		}
		
	});
}


function getPaymentReqRecord () {
	
	$('#csPtColOP tbody tr.paymentDataPlotRow').remove();
	
	$.post(pageContext+"getPaymentReqRecord",{"enqSfid":$('#enquirysfid').val(), "projectSFID":$("#projectsfid").val()},function(data){
		
		var html = '';
		var obj =JSON.parse(data);
		var trans_date = '';
		
		if(obj!=null){
			if (obj.status != "STATUS_NOTOK") {
				for(i = 0; i< obj.length; i++){    
					
					if (obj[i].transaction_date != '') {
						var date = new Date(obj[i].transaction_date);
						var curr_date = date.getDate();
						var curr_month = date.getMonth() + 1; 
						var curr_year = date.getFullYear();
						
						trans_date = curr_date + "-" + curr_month + "-" + curr_year;
					}else {
						trans_date = '';
					}
					
					html += 	'<tr class="paymentDataPlotRow" data-rowid = "'+obj[i].id+'">'
									+ '<td style="text-align:center;">'+trans_date+'</td>' 
									+ '<td style="text-align:center;"> ' 
											+ ' <input onkeyup="csPtcalculateGrandTotalOP()" style="display:none;" name="amount"  class="submittedAmount numericWithoutDecimal numericField full form-control input-sm" value="'+obj[i].amount+'">'
											+ '<span class="oldReqAmount">'+obj[i].amount+'</span>' 
										+'</td>' 
									+ '<td style="text-align:center;">'
											+ '<textarea class="editDesPR full form-control input-sm" style="display:none;">'+obj[i].description+'</textarea>'
											+ '<span class="existingDes">'+obj[i].description+'</span>' 
									+ '</td>' 
									+ '<td class="crudRPBtn"> '
										+ '<button class="btn btn-primary blue_btn editPayReqBtn" onclick="editPaymentRequest(this)">Edit</button>'
										+ '<button class="btn btn-primary blue_btn updatePaymentBtn" style="display:none" onclick="">Update</button>'
										+ '<button class="btn btn-primary blue_btn deletePayReqBtn" >Delete</button>'
										+ '<button class="btn btn-primary blue_btn cancelPayReqBtn" style="display:none" onclick="cancelPayReq(this)">Cancel</button>'
									+ '</td>' 
								"</tr>";
				}
				
				html = html.replace(/undefined/g, "");
				
				$("#csPtColOP tbody tr:first-child").after(html);
			}
		}
	}).done(function(obj){
		var data =JSON.parse(obj);
		if(data!=null){
			if (data.status == "STATUS_NOTOK") {
				alert (data.error_msg);
			}
		}
		csPtcalculateGrandTotalOP();
	});	
}



function editPaymentRequest (e) {
	var existingDes = $(e).closest("td").closest("tr").find(".existingDes").text();
	
	$(e).hide();
	$(e).closest("td").find(".deletePayReqBtn").hide();
	$(e).closest("td").find(".updatePaymentBtn").show();
	$(e).closest("td").find(".cancelPayReqBtn").show();
	$(e).closest("td").closest("tr").find(".submittedAmount").show();
	$(e).closest("td").closest("tr").find(".oldReqAmount").hide();
	$(e).closest("td").closest("tr").find(".editDesPR").val(existingDes);
	$(e).closest("td").closest("tr").find(".existingDes").hide();
	$(e).closest("td").closest("tr").find(".editDesPR").show();
} 

function cancelPayReq (e) {
	var existingAmount = $(e).closest("td").closest("tr").find(".oldReqAmount").text();
	
	$(e).hide();
	$(e).closest("td").find(".deletePayReqBtn").show();
	$(e).closest("td").find(".updatePaymentBtn").hide();
	$(e).closest("td").find(".editPayReqBtn").show();
	$(e).closest("td").closest("tr").find(".submittedAmount").val("");
	$(e).closest("td").closest("tr").find(".submittedAmount").val(existingAmount);
	$(e).closest("td").closest("tr").find(".submittedAmount").hide();
	$(e).closest("td").closest("tr").find(".oldReqAmount").show();
	$(e).closest("td").closest("tr").find(".existingDes").show();
	$(e).closest("td").closest("tr").find(".editDesPR").hide();
	csPtcalculateGrandTotalOP();
}

//$('#getPln').find('option:selected').attr("data-valuepercentage")