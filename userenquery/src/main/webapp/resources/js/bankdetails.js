 
$( document ).ready(function() {
	/*alert("Test");*/
	
	$('#paymentopt').change(function(){ 
	    var value = $(this).val();
	    showOnChangeFields(value);
	});
	
	
	
	$('.unit').change(function(){ 
	    var value = $(this).val();
	    $(".unitCustom").val($(this).val());
	   // getunittype($('#tower').val(),value);
	});

	
});
function showOnChangeFields(value){
	debugger;
	 if(value==='Bank'){
		    $("#bankdetails").show();
		    $("#neftdetails").hide();
		    $("#onlinedetails").hide();
		    $(".bankdetails").removeAttr('disabled','disabled');
		    $(".neftdetails").attr('disabled','disabled');
		    $(".onlinedetails").attr('disabled','disabled');
	    }else if(value==='NEFT'){
		    $("#bankdetails").hide();
		    $("#neftdetails").show();
		    $("#onlinedetails").hide();
		    $(".bankdetails").attr('disabled','disabled');
		    $(".neftdetails").removeAttr('disabled','disabled');
		    $(".onlinedetails").attr('disabled','disabled');
	    }else if(value==='Online'){
		    $("#bankdetails").hide();
		    $("#neftdetails").hide();
		    $("#onlinedetails").show();
		    $(".bankdetails").attr('disabled','disabled');
		    $(".neftdetails").attr('disabled','disabled');
		    $(".onlinedetails").removeAttr('disabled','disabled');
	    }
}
function populatePaymentDetails(enq){
	if(!isEmpty(enq)){
		var eoiBankName=enq.eoiBankName;
		var branch=enq.branch;
		var micRChbranchequeNoNEFTRTGS=enq.micRChequeNoNEFTRTGS;
		var nefttransactionID=enq.micRChequeNoNEFTRTGS;
		var transactionDate=enq.transactionDate;
		var transactionAmount=enq.transactionAmount;
		var transactionType=enq.transactionType;
		var transactionID=enq.transactionID;
		
		
		if(enq.transactionType!=undefined && enq.transactionType!=null && enq.transactionType!=''){
			$('.eoi_submitted').attr("readonly","readonly");
	    	$('.eoi_submitted').addClass('disableInputs');
	    	$("#eoisaveclose").hide();
		}
		 if(!isEmpty(enq.enquiryReport))
			 {
			 	$('input.desiredUnitType[value="'+enq.enquiryReport.desiredUnitType+'"]').trigger('click');
			 	$("#unit").val(enq.enquiryReport.unit);
				$("#fband").val(enq.enquiryReport.floorBand);
				
				$("#desiredUnitType").val(enq.enquiryReport.desiredUnitType);
				$("#bankotherinfo").val(enq.enquiryReport.bankOtherInfo);
				$(".unitname").val(enq.enquiryReport.unit);
			 }
		$("#paymentopt").val(transactionType);
		$("#eoiBankName").val(eoiBankName);
		$("#branch").val(branch);
		$("#chequeno").val(micRChbranchequeNoNEFTRTGS);
		$("#amount").val(transactionAmount);
	    $("#chequedate").val(transactionDate);
	   
	    	    
	    $("#transamt").val(transactionAmount);
		$("#transId").val(transactionID);
		$("#nefttransactionID").val(nefttransactionID);
		
		$("#transDate").val(transactionDate);
		
		
		 
		 
		$(".bandname").val(enq.eoi_Preferred_Floor_Band__c);
		$("#tower").val(enq.eoi_Tower_Series__c);
		
		
		$(".towername").val(enq.eoi_Tower_Series__c);
		$("#bankotherinfo").val(enq.eoi_Remarks__c);
		 
		if(transactionDate!='' && transactionDate!=null ){
			//alert(enq.enquiryReport.followDate);
		//	alert(formatDate(enq.enquiryReport.followDate));
			if(!isEmpty(enq.enquiryReport))
				{
					$("#transDatevalue").val(formatDate(enq.enquiryReport.followDate));
					$("#chequedatevalue").val(formatDate(enq.enquiryReport.followDate));
				}
			 
			$("#divchequedate").hide();
			$("#divtransDate").hide();
			$("#divchequevalue").show();
			$("#divtransDateValue").show();
			
		}
		
		
		
		
		
		
		
		
		
		
		
		showOnChangeFields(transactionType);
	}else{
		$(".bankdetails").val('');
		$(".neftdetails").val('');
	}

}
function savePaymentInfo(event,el){
	//debugger;
	   // event.preventDefault();
	    //$("#enquiryStatus").val("EOI");
	    submitIt("enquiryRequestBasicInfoForm","saveBaseInfo","savebasicInfoResp");
	    submitWithFile("bankPaymentForm","savePaymentInfo","savePaymentInfoResp");
	    
}
function savePaymentInfoResp(resp){
	
		//swal(resp.message);
		/*1 - Start - 17-12-2019 Requested from - Prakash -- Hide redirection button on EOI Tab*/
		
		var url=$("#contextPath").val();
		//Redirect stop from this function
		//window.location.href =url+ "/assignedusers?userId="+$("#userid").val()+"&projectid="+$("#projectid").val()+"&projectname="+$("#projectname").val()  ;
		
		/* END */
		/* Start - Page Refresh methods calling - After uncomment 1 */
		/*getEOITabPaymentRecord (); 
		getEOITabPreferencRecord();*/
		/* END */
	/*showOnChangeFields(resp);*/
}


function exitAndCloseEOI () {
	var url=$("#contextPath").val();
	window.location.href =url+ "/assignedusers?userId="+$("#userid").val()+"&projectid="+$("#projectid").val()+"&projectname="+$("#projectname").val()  ;
	
	/*swal({
		  title: "Are you sure?",
		  text: "You decided to not submit the form!",
		  type: "warning",
		  showCancelButton: true,
		  confirmButtonClass: "btn-danger",
		  confirmButtonText: "Yes",
		  cancelButtonText: "Cancel",
		  closeOnConfirm: true,
		  closeOnCancel: true
	},
	function(isConfirm) {
		  if (isConfirm) {
		    swal("Deleted!", "Your imaginary file has been deleted.", "success");
		  } else {
		    swal("Cancelled", "Your imaginary file is safe :)", "error");
		  }
	});*/
	
	/*swal({
	    title: 'Are you sure?',
	    text: "You won't be able to revert this!",
	    type: 'warning',
	    showCancelButton: true,
	    confirmButtonColor: '#3085d6',
	    cancelButtonColor: '#d33',
	    confirmButtonText: 'Confirm!'
	}).then(function(){
	    alert("The confirm button was clicked");
	}).catch(function(reason){
	    alert("The alert was dismissed by the user: "+reason);
	});*/
	
}