
$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});
var flag = true;

var channelPartnerArray=[];
var channelPartners = [];
var enqArray=[];
$(document).ready(function(){
	$("#enMobileNo").attr("maxlength","10");
	$(document).on('focus', '.autocomplete-off:input', function(){
	        $( this ).attr( 'autocomplete', 'nope' );
	});
	onPageLoad();
	
   // $('#mainPageLoad').hide();
	$("#enquiryRequestBasicInfoForm").find("input,select,textarea").attr("tabIndex","-1");
	 $(this).scrollTop(0);
	 
	 $("#getEnquiry").on("click",callForSearch);
	 
});


function getEnquiry(){
	$("#mainPageLoad").show();
	/*$("#enMobileNo").addClass('disableInputs');
	$("#otpInputDiv").addClass('disableInputs');
	$("#generateOTPDiv").addClass('disableInputs');*/
	//$("#enMobileNo").attr("disabled","disabled");
	//$(".otpInput").attr("disabled","disabled");
	//$(".otpInput_btn_Div").attr("disabled","disabled");
	//$(".getEnquiry_btn").attr("disabled","disabled");
	
	
	//$('#tab_offline_enq').show();
	//$('#reset_btn_ColDiv').show();
	//$("#getEnquiry").attr('disabled',true);
	var inputMobile =  $('#enMobileNo').val();
	var inputCountryCode =  $('.selected-dial-code').text();
	//inputCountryCode = inputCountryCode.substring(3);
	inputCountryCode = inputCountryCode.slice(0, -3);
	$('#inputMobileNo').val(inputMobile);
	$('#countryCode').val(inputCountryCode);
	getExistingInfoByMobileAndProject();
	
}

function onPageLoad(){
	if($("#etokentaballow").val()=="Y")
	{
		$("#etokentabli").show();
	}
else
	$("#etokentabli").hide();
	$(".referred_by_name").hide();
		var projectName=$('#projectName').val();
	    if(projectName!=""){
	    	$("#projectTitle").html(projectName);
	    }
	    
	var hasParam=$('#hasParam').val();
	if(hasParam==="true"){
	    var enquiryType=$("#hiddenEnquiryType").val();
	    $("#isReferredByChannelPartnerRadio"+enquiryType).trigger("click");
	    hideEnquirySourceByEnquiryType(enquiryType,null);
	}
   
    var projectSfid=$('.projectSfid').val();
    if(projectSfid==""){
    }
    var countryCode=$('#countryCode').val();
    if(countryCode==""){
    	$('#countryCode').val("+91");
    }
    
    var mobileNo=$('#hiddenMobileNo').val();
    if(mobileNo!=""){
      $("#inputMobileNo").val(mobileNo);
      getExistingInfoByMobileAndProject();
      $("#contactDiv").addClass('disableCol');
    }else{
      $("#contactDiv").removeClass('disableCol');
    }  
    fetchChannelPartners();
    
}

function fetchChannelPartners(){
	var url=$("#contextPath").val();
	$.get(url+"/getChannelPartners",function(data){//,"cpflag":enq.isReferredByChannelPartnerFlag,"cpname":cpname				 
	}).done(function(data){
		channelPartners=data.objectMap.channelPartnerList;
		console.log(channelPartners);
	});

}
function getExistingInfoByMobileAndProject(){
	var mobileNo=$("#inputMobileNo").val();
	var project=$('.projectSfid').val();
	if(project!=""){
		var countryCode=$('#countryCode').val();
		var resp=fetchData("getExistingRecords/"+countryCode+"/"+mobileNo+"/"+project,null,"GET");
		populateEnquiryAndContact(resp);
	}
}
function populateEnquiryAndContact(resp){
	if(resp ==null){
		return;
	}
	if(resp.objectMap==null){
		return;
	}
	var userToken =  resp.objectMap.userToken;
	if(userToken !=null){
		$("#tokenId").val(userToken.tokenId);
		$("#tokenNo").val(userToken.tokenNo);
	}
	var enquiryList=resp.objectMap.enquiryList;
	var contact=resp.objectMap.contact;
	if(!isEmpty(enquiryList)){
		/*populateEnquiryList(enquiryList);*/
		var enq=enquiryList[0];
		var enqContact=enq.contact;
		populateBasicInfo(enq,enqContact);
		populateAddressInfo(enq,enqContact);
	}else if(!isEmpty(contact)){
		$("#enquiryTable tbody").empty();
		$('.enquiryId').val("");
		$('.enquiryReportId').val("");
	  	$('.enquiryId').removeAttr("disabled");
		$('#channelPartnerName').val("");
		$("#channelPartnerNameSearch").val("");
		$("#channelPartnerNameSearch").attr('readonly', false);
		$('#channelPartnerSfid').val("");
		$('#channelPartnerId').val("");
		$('#brokerContact').val("");
		$('#hdrEnqNameoffline_div').hide();
		var hasParam=$('#hasParam').val();
		if(hasParam==="true"){
			/*Added By Satheesh K - Enquiry Type Blank - 08-06-2020 */ 
		   var enquiryType="";
		   if($("#hiddenEnquiryType").val()!=null)
			   enquiryType="CP";
		   else
			   enquiryType=$("#hiddenEnquiryType").val();
		   $("#isReferredByChannelPartnerRadio"+enquiryType).trigger("click");
		   hideEnquirySourceByEnquiryType(enquiryType,null);
		}else{
		   $("#isReferredByChannelPartnerRadioCP").trigger("click");			   
		   hideEnquirySourceByEnquiryType(null,null);
		}
		$('#walkInSource').val("");
		$('#phasedto').val("");
	    $('#walkInSourceDetail').val("");
	    $('#otherChannelPartnerName').val("");
	    $("#desiredUnitType0").trigger("click");
	    		
		populateBasicInfo(null,contact);
		populateAddressInfo(null,contact);
		 
		if(contact.hasError && contact.recordType==$("#recordTypeCustomer").val())/*Record Type Customer-"01290000000uHV8AAM"*/{
			$(".commonMessageDiv").css('display','block');
			$('#commonBoldMessage').html("");
			$('#commonBoldMessage').html(contact.message);
		}else{
			$(".commonMessageDiv").css('display','none');
			$('#commonBoldMessage').html("");
		}
	}else{			
	    $("#budget").val("");
		enqSlider();
			    $('.enquiryId').val("");
			    $('.enquiryReportId').val("");
			    $('.contactReportId').val("");
			    $('.enquiryId').removeAttr("disabled");				
				$('#channelPartnerName').val("");
				$("#channelPartnerNameSearch").val("");
				$('#channelPartnerSfid').val("");
				$('#channelPartnerId').val("");
				$('#brokerContact').val("");
				$("#channelPartnerNameSearch").removeClass('disableInputs');
				$("#channelPartnerNameSearch").attr('readonly', false);
				var hasParam=$('#hasParam').val();
				if(hasParam==="true"){
				   var enquiryType="";
				   if($("#hiddenEnquiryType").val()!=null)
					   enquiryType="CP";
				   else
					   enquiryType=$("#hiddenEnquiryType").val();
				   $("#isReferredByChannelPartnerRadio"+enquiryType).trigger("click");
				   hideEnquirySourceByEnquiryType(enquiryType,null);
				}else{
				   $("#isReferredByChannelPartnerRadioCP").trigger("click");
				   hideEnquirySourceByEnquiryType(null,null);
				}
				$('#walkInSource').val("");
				$('#phasedto').val("");
			    $('#walkInSourceDetail').val("");
			    $('#otherChannelPartnerName').val("");
			    $("#desiredUnitType0").trigger("click");
			$('.contactId').val("");
			$('.contactId').removeAttr("disabled");				
			$('#firstName').val("");
			$('#lastName').val("");
			$('#email').val("");
			$('#dateOfBirth').val("");
			$('#ageGroup').val("");
			$('#industry').val("");				
			$('#addressLine1').val("");
			$('#addressLine2').val("");
			$('#residentialState').val("");
			$('#residentialCountry').val("");
			$('#city').val("");
			$('#pinCode').val("");
			$('#locality').val("");
			$('#locality').val("");
		    $("#purpose").val("");
		    $("#carpetAreaRequirement").val("");
		    $("#employmentStatus").val("");
		    $("#companyName").val("");
		    $("#officeAddress").val("");
		    
		    $('#officelat').val("");
			$('#officelng').val("");
			$('#reslat').val("");
			$('#reslng').val("");
			
		    $(".enquiryFields").removeAttr("readonly","readonly");
		    $("#enquiryComments").val("");
		    $(".enquiryFields").removeClass('disableInputs');

		    
		    $("#officeCity").val("");
		    $("#officePinCode").val("");
		    $('#hdrEnqNameoffline_div').hide();
	}
	//$("#getEnquiry").attr('disabled',false);
}
function saveBaseInfo(event,el){
    event.preventDefault();
	submitIt("enquiryRequestBasicInfoForm","saveOfflineEnquiry","savebasicInfoResp");
	 $(this).scrollTop(0);
}

function saveRequirementRequest(event,el){
	submitIt("enquiryRequestRequirementInfoForm","saveRequirementInfo","saveRequirementInfoResp");
	 $(this).scrollTop(0);
}

function saveOtherRequest(event,el){
	submitIt("enquiryRequestOtherInfoForm","saveOtherInfo","saveOtherInfoResp");
	 $(this).scrollTop(0);
}

function populateBasicInfo(enq,contact){
		if(!isEmpty(enq)){
			$('#hdrEnqNameoffline_div').show();
			$('#hdrEnqNameoffline').val(enq.name);
			$('.enquiryId').val(enq.enquiryId);
			$('.enquiryId').removeAttr("disabled");
			var projectSfid=enq.project==null?"":enq.project.sfid;
			$('.projectSfid').val(projectSfid);
			var channelPartner=enq.channelPartner;
			
			var brokerContact=enq.brokerContact;
			if(!isEmpty(channelPartner)){
				$("#channelPartnerNameSearch").val(channelPartner.name);
				$('#channelPartnerName').val(channelPartner.name);
				$('#channelPartnerSfid').val(channelPartner.sfid);
				$('#channelPartnerId').val(channelPartner.channelPartnerId);
				loadBrokerContacts();
			}
			if(!isEmpty(brokerContact)){
				$('#brokerContact').val(brokerContact.sfid);
				$('#brokerContactId').val(brokerContact.contactId);
			}
			$('#isReferredByChannelPartnerInput').val(enq.isReferredByChannelPartner);
			var cpHS=enq.isReferredByChannelPartnerFlag;			 
			if(cpHS!=null && cpHS!=""){
			   $('label[labelName="isReferredByChannelPartner"][value="'+cpHS+'"]').trigger("click");
			   hideEnquirySourceByEnquiryType(cpHS,null);
			}
			else if(cpHS=="NSP"){
				hideEnquirySourceByEnquiryType(null,enq);
				
			}
			else{
				
				if(!isEmpty(channelPartner) && !isEmpty(brokerContact) && enq.isReferredByChannelPartner==='Partner'){
					 $("#isReferredByChannelPartnerRadioCP").trigger("click");
				}else if(enq.isReferredByChannelPartner==='Direct' && !isEmpty(enq.walkInSource)){
					 $("#isReferredByChannelPartnerRadioD").trigger("click");
				}else if(enq.isReferredByChannelPartner==='Partner' && !isEmpty(enq.otherChannelPartner)){
					 $("#isReferredByChannelPartnerRadioO").trigger("click");
					
			    }
				hideEnquirySourceByEnquiryType(null,enq);
			}
			$('#walkInSource').val(enq.walkInSource);			
			$('#walkInSourceDetail').val(enq.walkInSourceDetail);
			if(enq.phasedto!=null)
				$('#phasedto').val(enq.phasedto.sfid);			
			$('.enquiryId').val(enq.enquiryId);
			loadEnquiryReport(enq);
			if(!isEmpty(contact=enq.contact)){
				contact=enq.contact;
			}else if(!isEmpty(enq.contactId)){
				contact=enq.contactId;
			}
			 if(enq.hasError && enq.nonEdit==="ENQUIRY")
			 {
			    $(".enquiryFields").attr("readonly","readonly");
			    $(".enquiryFields").addClass('disableInputs');
			 }else{
			    $(".enquiryFields").removeAttr("readonly","readonly");
			    $("#enquiryComments").val("");
			    $(".enquiryFields").removeClass('disableInputs');
			    
			 }	
			 if(enq.hasError){
					$(".commonMessageDiv").css('display','block');
					$('#commonBoldMessage').html("");
					$('#commonBoldMessage').html(enq.message);
			 }else{
					$(".commonMessageDiv").css('display','none');
					$('#commonBoldMessage').html("");
			 }
		} else{
			enqSlider();
		}
		
		if(!isEmpty(contact)){			
		    if(contact.recordType==$("#recordTypeCustomer").val())
		    {
		    	$(".contactFields").attr("readonly","readonly");
		    	 $(".contactFields").addClass('disableInputs');
		    }else{
		    	$(".contactFields").removeAttr("readonly","readonly");
		    	$(".contactFields").removeClass('disableInputs');
		    }
			$('.contactId').val(contact.contactId);
			$('.contactId').removeAttr("disabled");
			
			$('#firstName').val(contact.firstName);
			$('#lastName').val(contact.lastName);
			$('#email').val(contact.otherEmail);
			$('#dateOfBirth').val(getDate(contact.dateOfBirth));
			$('#addressLine1').val(contact.addressLine1);
			var addressLine3=contact.addressLine3==null?"":contact.addressLine3;
			var addressLine2=(contact.addressLine2==null || contact.addressLine2=='null')?"":contact.addressLine2;
			
			$('#addressLine2').val(addressLine2+addressLine3);
			$('#residentialState').val(contact.residentialState);
			$('#residentialCountry').val(contact.residentialCountry);
			$('#city').val(contact.city);
			$('#pinCode').val(contact.pinCode);
			$('#locality').val(contact.locality);
			loadContactReport(contact);
		}
		 $(this).scrollTop(0);
}
function loadEnquiryReport(enq){
	if(!isEmpty(enq) && !isEmpty(enq.enquiryReport)){
		var enquiryReportId=enq.enquiryReport==null?'':enq.enquiryReport.enquiryReportId;
		$('.enquiryReportId').val(enquiryReportId);	
		$('input.vastuPreference[value="'+enq.enquiryReport.vastuPreference+'"]').trigger('click');
		$('input.unitAvailability[value="'+enq.enquiryReport.unitAvailability+'"]').trigger('click');
		$('input.accompaniedBy[value="'+enq.enquiryReport.accompaniedBy+'"]').trigger('click');
		$('input.dealNegotiation[value="'+enq.enquiryReport.dealNegotiation+'"]').trigger('click');
		$('input.constructionStatus[value="'+enq.enquiryReport.constructionStatus+'"]').trigger('click');
		$('input.timeframeToBook[value="'+enq.enquiryReport.timeframeToBook+'"]').trigger('click');
		$('input.sourceOfFunding[value="'+enq.enquiryReport.sourceOfFunding+'"]').trigger('click');
		$('#allocatedSalesManager').val(enq.enquiryReport.allocatedSalesManager);
		$('input.desiredUnitType[value="'+enq.enquiryReport.desiredUnitType+'"]').trigger('click');
		$('input.haveWeMetBefore[value="'+enq.enquiryReport.haveWeMetBefore+'"]').trigger('click');
		$('#purpose').val(enq.enquiryReport.purpose);
		$('#budget').val(enq.enquiryReport.budget);
		$("#carpetAreaRequirement").val(enq.enquiryReport.carpetAreaRequirement);
		$('#otherChannelPartnerName').val(enq.enquiryReport.cpComments);
		if(enq.enquiryReport.is_revisit=="Yes")
		{
			$('#lastvisitdate_div').show();
			$('.is_revisit').val(enq.enquiryReport.is_revisit);
			var date = new Date(enq.dateOfSiteVisit);
			var newdate= (date.getMonth() + 1) + '/' + date.getDate() + '/' +  date.getFullYear();
			$('#lastvisitdate').val(newdate);
			$('#lastvisitdate').attr('name', 'enquiryReport.lastvisitdate');
		}
		var visitType = enq.enquiryReport.visitType;
		$("#visitType").val(visitType);
	}
	 $(this).scrollTop(0);
	 enqSlider();
}



function enqSlider(){
	if(flag){  
		try{
			 $('#ex13').bootstrapSlider('destroy');
		}catch(err){
			/*console.log(err);*/
		}
			
		 $("#ex13").bootstrapSlider({
		    ticks: [2000000, 10000000, 20000000, 30000000, 40000000, 50000000],
		    ticks_labels: ['20L', '1Cr', '2Cr', '3Cr', '4Cr', '5Cr' ],
		    ticks_positions: [0, 20, 40, 60, 80, 100],
		    //ticks_snap_bounds: 60,
		    tooltip: 'always',
		   step: 100000,
		   value: $("#budget").val(),
		   formatter: function(value) {
			   			   
				if(value >= 10000000) value = (value/10000000).toFixed(2) + ' Cr';
				else if(value >= 100000) value = (value/100000).toFixed(2) + ' Lac';
				else if(value >= 1000) value = (value/1000).toFixed(2) + ' K';
							   
			   return value;
			}    
		});
		var slider = new Slider("#ex14", {
		    ticks: [200, 500, 1000, 1500, 2000, 2500],
		    ticks_labels: ['200', '500', '1000', '1500', '2000', '2500' ],
		    ticks_positions: [0, 20, 40, 60, 80, 100],
		    tooltip: 'always',
		   step: 50,
		   value: $("#carpetAreaRequirement").val(),
			formatter: function(value) {  
			   return value + ' sqft';
			}		    
		});
	}
}


function loadContactReport(contact){
	if(!isEmpty(contact) && !isEmpty(contact.contactReport)){
		var contactReportId=contact.contactReport==null?'':contact.contactReport.contactReportId;
		$('.contactReportId').val(contactReportId);
		$('input.customerGender[value="'+contact.contactReport.gender+'"]').trigger('click');
		$('input.customerClassification[value="'+contact.contactReport.customerClassification+'"]').trigger('click');
		$("#ethnicity").val(contact.contactReport.ethnicity);
		$('#currentResidence').val(contact.contactReport.currentResidenceType);
		$('#currentResidenceOwnership').val(contact.contactReport.currentResidenceOwnership);
		$('#occupation').val(contact.contactReport.occupation);
		
		$('#officeCity').val(contact.contactReport.officeCity);
		$('#officePinCode').val(contact.contactReport.officePincode);
		$('#officeAddress').val(contact.contactReport.officeAddress);
		
		$('#officelat').val(contact.contactReport.officelat);
		$('#officelng').val(contact.contactReport.officelng);
		$('#reslat').val(contact.contactReport.reslat);
		$('#reslng').val(contact.contactReport.reslng);
		
		$('#ageGroup').val(contact.contactReport.ageGroup);
		$('#employmentStatus').val(contact.contactReport.employmentStatus);
	}
}
function populateAddressInfo(enq,contact){
		if(!isEmpty(enq)){
			$('.enquiryId').val(enq.enquiryId);
			if(!isEmpty(enq.contact)){
				contact=enq.contact;
			}else if(!isEmpty(enq.contactId)){
				contact=enq.contactId;
			}
			
		}
		
		if(!isEmpty(contact)){
			$('#companyName').val(contact.companyName);
			$('.contactId').val(contact.contactId);
			$('.contactId').removeAttr("disabled");
		}
	}


function savebasicInfoResp(data){
	var enq=data.objectMap.EnquiryRequest;
	var contact =enq.contact;
	console.log("mobileNo:-"+contact.mobileNo);
	console.log("countryCode:-"+contact.countryCode);
	console.log("mobile:-"+contact.mobile);
	
	$(".enquiryId").removeAttr('disabled');
	$(".contactId").removeAttr('disabled');
	$(".enquiryId").val(enq.enquiryId);
	$(".contactId").val(contact.contactId);
	
	var enquiryReport=enq.enquiryReport;
	var contactReport=enq.contact.contactReport;
	$(".enquiryReportId").val(enquiryReport.enquiryReportId);
	$(".contactReportId").val(contactReport.contactReportId);
	
	if(data.success){
		$("#mainPageLoad").hide();
		var hasParam=$('#hasParam').val();
		if(hasParam==="true"){
			//alert("Success");	
			var url=$("#contextPath").val();
			var countryCode=$("#countryCode").val();
        	var mobileNo=$("#inputMobileNo").val();
        	countryCode=countryCode.replace("+", "%2B");
        	var code=countryCode+mobileNo;
        	code = contact.mobileNo;
        	code=code.replace("+", "%2B");
			$.get(url+"/generateWalkinTokenOffline",{"enquiryid":enq.enquiryId,"mobileno":contact.mobile,"projectSFID":$("#projectSfid").val(),"projectName":$("#projectName").val()
        		,"countryCode":contact.countryCode},function(data){//,"cpflag":enq.isReferredByChannelPartnerFlag,"cpname":cpname				 
        	}).done(function(data){
        		console.log(data);
	        	$("#tokenId").val(data.tokenId);
	        	$("#tokenNo").val(data.tokenNo);
        	});
			
			swal({
		        title: "Done",
		        text: "Thank You. Your token no. sent on your mobile number",
		        icon: "success",
		        type: "success",
		        dangerMode: true,
		      }).then(function(isConfirm) {
		        if (isConfirm) {
		        	console.log('OK');
		        } 
		      });	 		
		}else{
			if(data.success){
				switchToNextTab();
			}
		}
	}else{
		$("#mainPageLoad").hide();
		showMessage(data);
	}
	
}

function saveRequirementInfoResp(data){
	showMessage(data);
	if(data.success){
		switchToNextTab();
	}
}

function saveOtherInfoResp(data){
	showMessage(data);
}

function showMessage(data){
	var respMessage=data.message;
	swal(respMessage);
}

function switchToNextTab(){
	$('.nav-tabs > .active').next('li').find('a').trigger('click');
	$(".commonErrorDiv").css('display','none');
}

function getExistingInfo(){
	
	var mobileNo=$('#inputMobileNo').val();
	if(mobileNo.length==10){		
		var countryCode=$('#countryCode').val();
		var resp=fetchData("getExistingRecords",countryCode+"/"+mobileNo,"GET");
		populateEnquiryAndContact(resp);
	}
}

function populateEnquiry(event){
	$('#mainPageLoad').show();
	$("#notSelectError").hide();
	var selectedEnq=$("input[name='enquiryRowId']:checked").val();
	    $('#multiEnq').modal('hide');
		var enq=enqArray["enquiry"+selectedEnq];
		var contact;
		if(!(isEmpty(enq.contact))){
			contact=enq.contact;
		}else{
			contact=enq.contactId;
		}		
		populateBasicInfo(enq,contact);
		populateAddressInfo(enq,contact);
		$("html, body").animate({ scrollTop: 0 }, "slow");
		$(".commonErrorDiv").css('display','none');
	$('#mainPageLoad').hide();
}

function populateEnquiryList(enquiryList){
	var tr="";
	
	$("#enquiryTable tbody").empty();
	$.each(enquiryList,function(idx,enquiry){
		
		var enquiryDate=enquiry.dateOfEnquiry==null?'':formatDate(enquiry.dateOfEnquiry);
		var enquiryId=enquiry.enquiryId==null?'':enquiry.enquiryId;
		var type=enquiry.isReferredByChannelPartner==null?'':enquiry.isReferredByChannelPartner;
		var status=enquiry.enquiryStatus==null?'':enquiry.enquiryStatus;
		enqArray["enquiry"+enquiryId]=enquiry;
		
		tr=	"<tr>"+
		"<td class='col-sm-3'><input type='radio' name='enquiryRowId' value='"+enquiryId+"'/></td>" +
		"<td class='col-sm-3'>"+ enquiryDate +"</td>" +
		"<td class='col-sm-3'>"+ enquiryId +"</td>" +
		"<td class='col-sm-3'>"+ type +"</td>" +
		"<td class='col-sm-3'>"+ status +"</td>" +
		"<td class='col-sm-3'>"+ "" +"</td>" +
		"<td class='col-sm-3'>"+ "" +"</td>" +
		"</tr>";
		
		$("#enquiryTable tbody").append(tr);
	});
	$('#multiEnq').modal({backdrop: 'static', keyboard: false})  
}
function hideEnquirySourceByEnquiryType(enquiryTypeCode,enq){
	
   if(!isEmpty(enquiryTypeCode)){
	if(enquiryTypeCode=='CP'){
		//$(".hideDirectType").hide();
		$(".hideDirectType").show();
		$(".hideChannelPartnerType").show();
		$("#enquirySourceTextDiv").show();
	}else if(enquiryTypeCode=='D'){
		$(".hideChannelPartnerType").hide();
		if($("#isReferredByChannelPartnerInput").val()!="Direct"){
			isReferredChanged("D");
		}
		$(".hideDirectType").hide();
		$("#enquirySourceTextDiv").hide();
	}else if(enquiryTypeCode=='O'){
		$(".hideDirectType").hide();
		$(".hideChannelPartnerType").show();
		$("#enquirySourceTextDiv").show();
	}
  }else if(!isEmpty(enq)){
	    var channelPartner=enq.channelPartner;
		var brokerContact=enq.brokerContact;
		 /* Changed by Satheesh K - Date : 10-06-2020 - Requested By: Prakash Idnani - Comments: Source protection Change for Enquiry Source to Walk-in Source*/
	    /*if(!isEmpty(channelPartner) && !isEmpty(brokerContact) && enq.isReferredByChannelPartner==='Partner'){
		    $(".hideDirectType").hide();
			$(".hideChannelPartnerType").show();
			$("#enquirySourceTextDiv").show();
		}else if(enq.isReferredByChannelPartner==='Direct' && !isEmpty(enq.walkInSource)){
			$(".hideChannelPartnerType").hide();
			if($("#isReferredByChannelPartnerInput").val()!="Direct"){
				isReferredChanged("D");
			}
			$(".hideDirectType").hide();$(".hideDirectType").show();
			$("#enquirySourceTextDiv").hide();
		}else if(enq.isReferredByChannelPartner==='Partner' && !isEmpty(enq.otherChannelPartner)){
			$(".hideDirectType").hide();
			$(".hideChannelPartnerType").show();
			$("#enquirySourceTextDiv").show();
	    }*/
		
		if(!isEmpty(channelPartner) && !isEmpty(brokerContact) && enq.walkInSource==='Channel Partner'){
		    $(".hideDirectType").hide();
			$(".hideChannelPartnerType").show();
			$("#enquirySourceTextDiv").show();
		}else if((enq.walkInSource==='Digital' || enq.walkInSource==='Exhibition' || enq.walkInSource==='Newspaper' || enq.walkInSource==='Hoarding' || enq.walkInSource==='Radio' || enq.walkInSource==='Word of mouth' 
			|| enq.walkInSource==='SMS' || enq.walkInSource==='Referral' || enq.walkInSource==='Godrej Employee' || enq.walkInSource==='Corporate' || enq.walkInSource==='Existing Customer' || enq.walkInSource==='Other BTL activities' || enq.walkInSource==='Affiliate Sales' || enq.walkInSource==='Telemarketing' 
				|| enq.walkInSource==='VDNB Referral'	
		) && !isEmpty(enq.walkInSource)){
			$(".hideChannelPartnerType").hide();
			if($("#isReferredByChannelPartnerInput").val()!="Direct"){
				isReferredChanged("D");
			}
			$(".hideDirectType").hide();/*$(".hideDirectType").show();*/
			$("#enquirySourceTextDiv").hide();
		}else if(enq.walkInSource==='Channel Partner' && !isEmpty(enq.otherChannelPartner)){
			$(".hideDirectType").hide();
			$(".hideChannelPartnerType").show();
			$("#enquirySourceTextDiv").show();
	    }
  }else{
	  $(".hideChannelPartnerType").show();
	  $(".hideDirectType").show();
	  $("#enquirySourceTextDiv").show();
  }
}
function isReferredChanged(cpHS){
	//
	$("div.sourceCol").hide();
	$("#isReferredByChannelPartner"+cpHS).show();
	$(".isReferredByChannelPartner"+cpHS).removeAttr("disabled");
	$("#isReferredByChannelPartnerRadio"+cpHS).prop("checked",true);
	var propVal=$("#isReferredByChannelPartnerRadio"+cpHS).attr("propValue");
	$("#isReferredByChannelPartnerInput").val(propVal);
	
	if(cpHS=='CP'){
		$("#isReferredByChannelPartnerRadioD").prop("checked",false);
		$("#isReferredByChannelPartnerRadioO").prop("checked",false);
		$(".isReferredByChannelPartnerD").attr("disabled","disabled");
		$(".isReferredByChannelPartnerO").attr("disabled","disabled");
	}else if(cpHS=='D'){
		$("#isReferredByChannelPartnerRadioCP").prop("checked",false);
		$("#isReferredByChannelPartnerRadioO").prop("checked",false);
		
		$(".isReferredByChannelPartnerCP").attr("disabled","disabled");
		$(".isReferredByChannelPartnerO").attr("disabled","disabled");
	}else if(cpHS=='O'){
		$("#isReferredByChannelPartnerRadioD").prop("checked",false);
		$("#isReferredByChannelPartnerRadioCP").prop("checked",false);
		
		$(".isReferredByChannelPartnerD").attr("disabled","disabled");
		$(".isReferredByChannelPartnerCP").attr("disabled","disabled");
	}
}

/* END Auto fill address*/
function getChannelPartners(event,el){
	
	event.preventDefault();
	$("#brokerContact").empty();
    //$("#channelPartnerName_Err").val("");
    var text=$(el).val();
    if(channelPartners.length>0){
    	
    	var partnerList = getMatchingTen(text);
    	filterChannelPartners(partnerList);
    }
    else{
    
    	if(text.length>=3){		
    		fetchAsyncData("getChannelPartnerList",text,"GET","loadChannelPartners");		
    	}else if(text.length==0){
    		channelPartnerArray=[];
    	}

    }
}

function getMatchingTen(word){
	if(word ==null){
		return;
	}
	var counter =0;
	var result = channelPartners.filter(function (partner){
		var matcher = word.replace(/ /g,"");
		matcher = matcher.toLowerCase();
		var partnerName = partner.name== null? "":partner.name.replace(/ /g,"").toLowerCase();
		var wordLength = matcher.length;
		var name = partnerName.substring(0,wordLength)
		/*var matchedName =partnerName.includes(matcher);*/
		var matchedName = name==matcher;
		if(matchedName){
			counter++;
		}
		return matchedName && counter <11;
	});
	var includedValues =[];
	if(result ==null || result.length<10){
		includedValues = channelPartners.filter(function (partner){
			var matcher = word.replace(/ /g,"");
			matcher = matcher.toLowerCase();
			var partnerName = partner.name== null? "":partner.name.replace(/ /g,"").toLowerCase();
			var matchedName =partnerName.includes(matcher);
			if(matchedName && !result.includes(partner)){
				counter++;
			}
			return matchedName && counter <11 && !result.includes(partner);
		});
	}
	if(result ==null){
		result=[];
	}
	if(includedValues !=null && includedValues.length>0){
		result =  result.concat(includedValues);
	}
	return result;
/*	 var uniqueArray = result.filter((item,index) => result.indexOf(item) === index)
	return uniqueArray;*/
}
function loadChannelPartners(resp){
	var partnerList=resp.objectMap.channelPartnerList;
	channelPartnerArray=partnerList;
	refreshChannelPartnerList();
	$("#channelPartnerLoader").hide();
	$("#channelPartnerNameSearch").attr('readonly', false);

}

function filterChannelPartners(partnerList){
	channelPartnerArray=partnerList;
	refreshChannelPartnerList();
	$("#channelPartnerLoader").hide();
	$("#channelPartnerNameSearch").attr('readonly', false);
}

function refreshChannelPartnerList(){
	$("#channelPartnerNameSearch").autocomplete({
        source: function (request, response) {
            response($.map(channelPartnerArray, function (value, key) {
                 return {
                     label: value.name,
                     value: value.sfid,
                     attr: value.channelPartnerId
                 }
             }));
        },
        select: function (event, el) {  
        	event.preventDefault();
        	$("#channelPartnerNameSearch").val(el.item.label);
        	$("#channelPartnerName").val(el.item.label);
        	$("#channelPartnerSfid").val(el.item.value);
        	$("#channelPartnerId").val(el.item.attr);
        	loadBrokerContacts();
            return false;
        }
    });

}
function refreshChannelPartners(channelPartners){
	$("#channelPartnerNameSearch").autocomplete({
        source: function (request, response) {
            response($.map(channelPartners, function (value, key) {
                 return {
                     label: value.name,
                     value: value.sfid,
                     attr: value.channelPartnerId
                 }
             }));
        },
        select: function (event, el) {  
        	event.preventDefault();
        	$("#channelPartnerNameSearch").val(el.item.label);
        	$("#channelPartnerName").val(el.item.label);
        	$("#channelPartnerSfid").val(el.item.value);
        	$("#channelPartnerId").val(el.item.attr);
        	loadBrokerContacts();
            return false;
        }
    });

}

function loadBrokerContacts(){
	var channelPartner=$("#channelPartnerSfid").val();
	var resp=fetchData("getBrokerContactByCPSfid",channelPartner,"GET");
	var list=resp.objectMap.brokerList;
	var id="brokerContact";
	var firstName='';
	var lastName='';
	$("#"+id).empty();
	$("#"+id).append("<option value=''></option>" );
	$.each(list,function(index,object){
		firstName=object.firstName==null?'':object.firstName;
	    lastName=object.lastName==null?'':object.lastName;
		$("#"+id).append("<option value='"+object.sfid+"' id='brkCont"+object.sfid+"' idVal='"+object.contactId+"'>"+firstName+" "+lastName+" </option>" );
	});

}

function brokerContactChanged(event,el){
	var val=$(el).val();
	var brkContId=$("#brkCont"+val).attr('idVal');
	$("#brokerContactId").val(brkContId);
}


/*Auto fill address*/
var autocomplete = {};
var autocompletesWraps = ['address','address2'];

var address_form = 	{xyz:'', street_number: 'long_name', route: 'long_name', sublocality_level_1:'long_name', sublocality_level_2:'long_name', sublocality_level_3:'long_name', locality: 'long_name',country:'long_name',administrative_area_level_1: 'long_name', administrative_area_level_2: 'long_name',  postal_code: 'short_name' };
var address2_form = {xyz:'', street_number: 'long_name', route: 'long_name', sublocality_level_1:'long_name', sublocality_level_2:'long_name', sublocality_level_3:'long_name', locality: 'long_name',country:'long_name',administrative_area_level_1: 'long_name', administrative_area_level_2: 'long_name',  postal_code: 'short_name' };

function initialize() {
    $.each(autocompletesWraps, function(index, name) {
    
          if($('#'+name).length == 0) {
                 return;
          }
          
          autocomplete[name] = new google.maps.places.Autocomplete($('#'+name+' .autocomplete')[0], { types: ['geocode','establishment'] });
          
          google.maps.event.addListener(autocomplete[name], 'place_changed', function() {
                 
                 var place = autocomplete[name].getPlace();
                 var form = eval(name+'_form');

                 if (name == "address") {
                        $('#reslat').val("");
                        $('#reslng').val("");
                        
                        
                        $('#reslat').val(place.geometry.location.lat());
                        $('#reslng').val(place.geometry.location.lng());
                 } else if (name == "address2") {
                        $('#officelat').val("");
                        $('#officelng').val("");
                        
                        
                        $('#officelat').val(place.geometry.location.lat());
                        $('#officelng').val(place.geometry.location.lng());
                 }


                 for (var component in form) {
                        
                        $('#'+name+' .'+component).val('');
                        $('#'+name+' .'+component).attr('disabled', false);
                        $('#'+name+' .'+component).addClass('hasVal');
                 }
                 
                 $('#'+name+ ' .autocomplete').val('');         
                 
                 $('#'+name+ ' .autocomplete').val(place.name);
                 
                 /*New code*/
                 var placeVar = '';
                 var routeVar = '';
                 var sublocality_level_2Var = '';
                 var sublocality_level_3Var = '';
                 var sublocality_level_1Var = '';
                 
                 var street_numberVar = '';
                 var neighborhoodVar = '';
                 /*END New Code*/
                 
                 for (var i = 0; i < place.address_components.length; i++) {
                        var addressType = place.address_components[i].types[0];
                        
                        if (typeof form[addressType] !== 'undefined') {
                              placeVar = place.name;
                              
                              if (addressType == "route"){
                                     routeVar = place.address_components[i][form[addressType]];
                                     if (neighborhoodVar != routeVar && street_numberVar != routeVar &&            placeVar !=  routeVar && sublocality_level_2Var != routeVar && sublocality_level_3Var != routeVar && sublocality_level_1Var != routeVar) {
                                            $('#'+name+ ' .autocomplete').val($('#'+name+ ' .autocomplete').val()+', '+place.address_components[i][form[addressType]]);
                                     }
                              } 
                              
                              if (addressType == "sublocality_level_2"){
                                     sublocality_level_2Var = place.address_components[i][form[addressType]];
                                     if (neighborhoodVar != sublocality_level_2Var && street_numberVar != sublocality_level_2Var &&         routeVar != sublocality_level_2Var && placeVar != sublocality_level_2Var &&  sublocality_level_3Var != sublocality_level_2Var && sublocality_level_1Var != sublocality_level_2Var) {
                                            $('#'+name+ ' .autocomplete').val($('#'+name+ ' .autocomplete').val()+', '+place.address_components[i][form[addressType]]);
                                     }
                              }
                              
                              if (addressType == "sublocality_level_3"){
                                     sublocality_level_3Var = place.address_components[i][form[addressType]];
                                     if (neighborhoodVar != sublocality_level_3Var && street_numberVar != sublocality_level_3Var &&   routeVar != sublocality_level_3Var && placeVar != sublocality_level_3Var &&  sublocality_level_2Var != sublocality_level_3Var && sublocality_level_1Var != sublocality_level_3Var) {
                                            $('#'+name+ ' .autocomplete').val($('#'+name+ ' .autocomplete').val()+', '+place.address_components[i][form[addressType]]);
                                     }
                              } 
                              
                              if (addressType == "sublocality_level_1"){
                                     sublocality_level_1Var = place.address_components[i][form[addressType]];
                                     if (neighborhoodVar != sublocality_level_1Var && street_numberVar != sublocality_level_1Var &&   routeVar != sublocality_level_1Var && placeVar != sublocality_level_1Var &&  sublocality_level_2Var != sublocality_level_1Var && sublocality_level_3Var != sublocality_level_1Var) {
                                            $('#'+name+ ' .autocomplete').val($('#'+name+ ' .autocomplete').val()+', '+place.address_components[i][form[addressType]]);
                                     }
                              } 
                              
                              if (addressType == "street_number"){
                                     street_numberVar = place.address_components[i][form[addressType]];
                                     if ( neighborhoodVar != street_numberVar && routeVar != street_numberVar && placeVar != street_numberVar &&  sublocality_level_1Var  != street_numberVar && sublocality_level_2Var != street_numberVar && sublocality_level_3Var != street_numberVar) {
                                            $('#'+name+ ' .autocomplete').val($('#'+name+ ' .autocomplete').val()+', '+place.address_components[i][form[addressType]]);
                                     }
                              }
                              
                              if (addressType == "neighborhood"){
                                     neighborhoodVar = place.address_components[i][form[addressType]];
                                     if ( street_numberVar != neighborhoodVar && routeVar != neighborhoodVar && placeVar != neighborhoodVar &&  sublocality_level_1Var  != neighborhoodVar && sublocality_level_2Var != neighborhoodVar && sublocality_level_3Var != neighborhoodVar) {
                                            $('#'+name+ ' .autocomplete').val($('#'+name+ ' .autocomplete').val()+', '+place.address_components[i][form[addressType]]);
                                     }
                              }
                        
                              var val = place.address_components[i][form[addressType]];
                              $('#' +name+ '.'+addressType).val(val);
                              
                              var val = place.address_components[i][form[addressType]];
                              $('#'+name+' .'+addressType).val(val);
                        }
                 }
          });
    });
}


 /* Referred by added on Enquiry page, on select of walk-in source as referral -  
	* Change By Satheesh Kyatham- 25-12-2019
	* Request From - Prakash Idnani*/
		/*  Start  */
function onSelectWalkinSrcReferral(event,el)
{
		var val=$(el).val();
		if(val=="Referral")
			{
			$('.referred_by_name').show();
			}
		else
			{
			$('.referred_by_name').hide();
			}
	}
/*  END  */

/*Code for Offline EOI to Closing Manager Page Redirect */
function openClosingMDashboard()
{
	var countryCodeEN = $('.selected-dial-code').text();
	countryCodeEN =countryCodeEN.replace("+","%2B");
	countryCodeEN = countryCodeEN.slice(0, -3);
	window.location.href = "salesDetails?tokenid="+$("#tokenId").val()
		+"&countrycode="+ countryCodeEN
		+"&mobileno="+$("#inputMobileNo").val() 
		+"&userId=" +$('#logginUserId').val()
		+"&roleid=" +$('#logginRoleId').val()
		+"&projectSfid="+$('#projectSfid').val()
		+"&projectName="+$('#projectName').val()
		+"&token="+ $("#tokenNo").val()
		+"&isView=N&salesViewType=N&roleid="+ $("#roleid").val();
	}

/*added by Satheesh - For Add OTP option on offline EOI */
function generateOTP(no) {
	
	if($("#loged_userid").val()=="null")
		{
			alert("Session Out, Please re-login");
			var url=$("#contextPath").val();
			window.location.href=url+"/saleslogin";
		}
	else
		{
		if($("#enMobileNo").val()!="")
			{
			//Channel Partner (CP name)
			var sourcename="";
			if($("#isReferredByChannelPartnerInput").val()=="Direct")
				{
					if($("#walkInSource").val().length>0)
						sourcename="Direct ("+$("#walkInSource").val()+")";
				}
			else
				{ 
				if($("#channelPartnerName").val().length>0)
					sourcename="Channel Partner ("+$("#channelPartnerName").val()+")";
				else if($("#otherChannelPartnerName").val().length>0)
					sourcename="Channel Partner ("+$("#otherChannelPartnerName").val()+")";
				}
			if(sourcename.length>0)
				{
				$('#generateOTPDiv').show();
				$('#generateOTPColDiv').show();
				$(".errorOTP").text('');
				$("#respo").val("Invalid");
				var countrycode = $('.selected-dial-code').text();
				countrycode=countrycode.slice(0, -3);
					$.get("getdetailsCountry", {
						"countryCode" : countrycode,
						"mobileno" : $("#enMobileNo").val(),
						"cpdirectname":sourcename,
					}, function(data) {
					});
					/*$('.otpColCnt .square_btn span').text('Resend OTP');*/
					$('.filterCol .otpInput_btn_Div span').text('Resend Access Code');
				}
			else
				alert("Please Select Walk-in source Name.");
			}
		else
			{
				alert("Enter Mobile No.");
			}
		}
	/*encryptStr();
	var appStatus = validateApplicantType(no);
	var email = $("#emailID1").val();
	if (appStatus == true) {
		$.get("getdeatails", {
			"mobileno" : encryptStr(),
			"email" : email,
		}, function(data) {
		});
		$('.otpColCnt .square_btn span').text('Resend OTP');
	} else {

	}*/

}
function otpvalidate(e) {

	if ($(e).val().length == 6) {
		//Validate OTP
		validateOTP(e);
		//$(".errorOTP").text("Valid");
		//alert($('#otp_verify').val());
		//_search_data($('#mobileno').val(),encStr);
		
		
	} else {
		//alert("Enter valid no");	
		$(".errorOTP").text("Invalid OTP");
		$("#respo").val("Invalid");
		$('#getEnquiry_search_btn').hide();
		$('#tab_offline_enq').hide();
		//errorOTP

	}
}
function validateOTP(e) {
	var otp_verify = $(e).val();
	if($("#enMobileNo").val()!="")
			{
		$.get("getotpvalid", {
			"mobileno" : $("#enMobileNo").val(),
			"OTP" : otp_verify
		}, function(data) {
			if (data.otp == undefined) {
				$(".errorOTP").text("Invalid OTP");
				$("#respo").val("Invalid");
				
			} else {
				$(".errorOTP").text("Valid");
				$("#respo").val("Valid");
				$('#getEnquiry_search_btn').show();
				$('#getEnquiry').show();
				$(".getEnquiry_btn").removeAttr("disabled");
				
				
				//$('#tab_offline_enq').show();
			}
		});
		}
}

function resetOfflineEnq(e)
{
	location.reload(true);
	//$('#tab_offline_enq').hide();
	//$('#reset_btn_ColDiv').hide();
	/*$('#cpdotp_div').hide();
	$('#nextbtnDiv').show();
	$("#otpInputColDiv").hide();*/
	//$('#getEnquiry_search_btn').hide();
	//$('.filterCol .otpInput_btn_Div span').text('Access Code');
	
	/*$("#enMobileNo").removeAttr("disabled");*/
	/*$(".otpInput").removeAttr("disabled");
	$(".otpInput_btn_Div").removeAttr("disabled");
	$(".otpInput").val('');*/
	 
}

 /*added by Satheesh - For restrict inspect element option on browser*/
$(document).bind("contextmenu",function(e) {
	 e.preventDefault();
});
/*$(document).keydown(function(e){
    if(e.which === 123){
       return false;
    }
});*/

function clickForNext()
{
	$("#mainPageLoad").show();
	if($("#enMobileNo").val()!="")
	{
		var inputCountryCode =  $('.selected-dial-code').text();
		inputCountryCode = inputCountryCode.slice(0, -3);
		$("#enMobileNo").attr("disabled","disabled");
		$("#nextbtnDiv").hide();
		$("#cpdotp_div").show();
		if(inputCountryCode=="+91")
			{
				$("#otpInputColDiv").show();
				$("#getEnquiry_search_btn").hide();
			}
		else
			{
			
				$("#getEnquiry_search_btn").show();
				$(".getEnquiry_btn").removeAttr("disabled");
			}
		$("#reset_btn_ColDiv").show();
		getEnquiry();	
	}
	else
		{
		 alert("Please Enter Mobile No.");
		}
}
/*$("label[labelName$='isReferredByChannelPartner']").click(function() {			
	debugger;
	var cpHS = $(this).attr('value');
	alert("***"+cpHS)
	$("#hiddenEnquiryTypeOTP").val(cpHS);
	isReferredChanged(cpHS);			
	//$("#cp" + cpHS).addClass("shake");						
});*/
function callForSearch()
{
	//$("label[labelName$='isReferredByChannelPartner']").trigger('click');
	
	var cpHS="";
	var sourcename="";
	if($("#isReferredByChannelPartnerInput").val()=="Direct")
		{
			if($("#walkInSource").val().length>0)
				sourcename="Direct ("+$("#walkInSource").val()+")";
			$(".hideChannelPartnerTypeOTP").hide();
			$("#enquirySourceTextDivOTP").hide();
			if($("#isReferredByChannelPartnerInput").val()!="Direct"){
				isReferredChanged("D");
			}
			$(".hideDirectTypeOTP").hide();
			$('#walkInSourceOTP').val($("#walkInSource").val());
			$('#referredbyIdOTP').val($("#referredbyId").val());
			$("#walkInSourceOTP").addClass('disableInputs');
			$("#referredbyIdOTP").addClass('disableInputs');
			$('#isReferredByChannelPartnerInputOTP').val($("#isReferredByChannelPartnerInput").val());
			cpHS="D";
			
		}
	else
		{ 
		if($("#channelPartnerName").val().length>0)
			{
				sourcename="Channel Partner ("+$("#channelPartnerName").val()+")";
				$(".hideDirectTypeOTP").hide();
				$(".hideChannelPartnerTypeOTP").show();
				$("#enquirySourceTextDivOTP").show();
				$("#channelPartnerNameSearchOTP").val($("#channelPartnerNameSearch").val());
				$('#channelPartnerNameOTP').val($("#channelPartnerName").val());
				$('#channelPartnerSfidOTP').val($("#channelPartnerSfid").val());
				$('#channelPartnerIdOTP').val($("#channelPartnerId").val());
				$('#isReferredByChannelPartnerInputOTP').val($("#isReferredByChannelPartnerInput").val());
				
				$("#channelPartnerNameSearchOTP").addClass('disableInputs');
				$("#isReferredByChannelPartnerRadioOTPCP").addClass('disableInputs');
				cpHS="CP";
			}
		else if($("#otherChannelPartnerName").val().length>0)
			{
				sourcename="Channel Partner ("+$("#otherChannelPartnerName").val()+")";
				$("#isReferredByChannelPartnerRadioOTPO").prop("checked",true);
				$(".hideDirectTypeOTP").hide();
				$(".hideChannelPartnerTypeOTP").show();
				$("#enquirySourceTextDivOTP").show();
				$("#otherChannelPartnerNameOTP").val($("#otherChannelPartnerName").val());
				$("#otherChannelPartnerNameOTP").addClass('disableInputs');
				$("#isReferredByChannelPartnerRadioOTPO").addClass('disableInputs');
				$('#isReferredByChannelPartnerInputOTP').val($("#isReferredByChannelPartnerInput").val());
				cpHS="O";
			}
		}
	
	if(sourcename.length>0)
		{
		$("#offline_header_div").hide();
		$("#fornext_enq_ColDiv").show();
		$('#tab1 .filterCol').css({'padding-top': '0px', 'background-color' : 'initial'});
		
		if($("#hiddenEnquiryTypeOTP").val()=='CP'){
				/*$(".hideDirectTypeOTP").hide();
				$(".hideChannelPartnerTypeOTP").show();
				$("#enquirySourceTextDivOTP").show();
				$("#channelPartnerNameSearchOTP").val($("#channelPartnerNameSearch").val());
				$('#channelPartnerNameOTP').val($("#channelPartnerName").val());
				$('#channelPartnerSfidOTP').val($("#channelPartnerSfid").val());
				$('#channelPartnerIdOTP').val($("#channelPartnerId").val());
				$('#isReferredByChannelPartnerInputOTP').val($("#isReferredByChannelPartnerInput").val());
				
				$("#channelPartnerNameSearchOTP").addClass('disableInputs');
				$("#isReferredByChannelPartnerRadioOTPCP").addClass('disableInputs');
				cpHS="CP";*/
		}
		else if($("#hiddenEnquiryTypeOTP").val()=='D')
			{
				/*$(".hideChannelPartnerTypeOTP").hide();
				$("#enquirySourceTextDivOTP").hide();
				if($("#isReferredByChannelPartnerInput").val()!="Direct"){
					isReferredChanged("D");
				}
				$(".hideDirectTypeOTP").hide();
				$('#walkInSourceOTP').val($("#walkInSource").val());
				$('#referredbyIdOTP').val($("#referredbyId").val());
				$("#walkInSourceOTP").addClass('disableInputs');
				$("#referredbyIdOTP").addClass('disableInputs');
				$('#isReferredByChannelPartnerInputOTP').val($("#isReferredByChannelPartnerInput").val());
				cpHS="D";*/
			}
		else if($("#hiddenEnquiryTypeOTP").val()=='O')
			{
				/*$("#isReferredByChannelPartnerRadioOTPO").prop("checked",true);
				$(".hideDirectTypeOTP").hide();
				$(".hideChannelPartnerTypeOTP").show();
				$("#enquirySourceTextDivOTP").show();
				$("#otherChannelPartnerNameOTP").val($("#otherChannelPartnerName").val());
				$("#otherChannelPartnerNameOTP").addClass('disableInputs');
				$("#isReferredByChannelPartnerRadioOTPO").addClass('disableInputs');
				$('#isReferredByChannelPartnerInputOTP').val($("#isReferredByChannelPartnerInput").val());
				cpHS="O";*/
			}
		
		$("div.sourceColOTP").hide();
		$("#isReferredByChannelPartnerOTP"+cpHS).show();
		$(".hideChannelPartnerTypeOTP").addClass('disableInputs');
		
		$('#tab_offline_enq').show();
		$('#cpdotp_div').hide();
		$("#isReferredByChannelPartnerRadioOTP"+cpHS).prop("checked",true);
		$("#isReferredByChannelPartnerRadioOTP"+cpHS).trigger("click");
		}
	else
		alert("Please Select Walk-in source Name.");
}

function forNextEnquiryCall()
{
	$("#offline_header_div").show();
	$("#enMobileNo").val('');
	$("#enMobileNo").removeAttr("disabled");
	$("#otpInputColDiv").hide();
	$("#generateOTPDiv").hide();
	$(".otpInput").val('');
	$("#getEnquiry").hide();
	
	$("#tab_offline_enq").hide();
	$("#fornext_enq_ColDiv").hide();
	$("#nextbtnDiv").show();
	$('.filterCol .otpInput_btn_Div span').text('Access Code');
	$("#reset_btn_ColDiv").hide();
	
	
}