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
		
		$('#csPtColOP tr:last-child').after("<tr class='csPtDataRowOP'> " +
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
	    
	    csPtData.enquiry_name = enqSFDCName;
	    csPtData.enquiry_sfid = $('#enquirysfid').val();
	    csPtData.unit_no = "";
	    csPtData.unit_sfid = "";
	    csPtData.project_sfid = $('#projectId').val();
	    csPtData.project_name = $('#projectname').val();
	    csPtData.amount = $(this).find('.csPtTransactionAmountOP').val();
	    csPtData.createdby = $('#userid').val();
	    csPtData.updatedby = $('#userid').val();
	    csPtData.transaction_date_string = $(this).find('.csPtTransactionDateOP').val();
	    csPtData.description = $(this).find('.csPtDescriptionOP').val();  
	    csPtData.request_url = "request_url";  

	    /*csPtData.transaction_amount = $(this).find('.csPtTransactionAmountOP').val();
	    csPtData.description = $(this).find('.csPtDescriptionOP').val();
	    csPtData.enq_sfid = $('#enquirysfid').val();
	    csPtData.project_sfid = $('#projectId').val();
	    csPtData.project_name = $('#projectname').val();
	    csPtData.userid = $('#userid').val();*/
	    
	    arrayData.push(csPtData);
	    k++
	});
	
	$.post(pageContext+"insertPaymentRequest",{"paymentDtlJson" : JSON.stringify(arrayData)},function(data){				 
	}).done(function(data){
		var obj =JSON.parse(data);
		
		if(obj!=null){
			
			alert (obj.error_msg);
			//console.log (obj.status);
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