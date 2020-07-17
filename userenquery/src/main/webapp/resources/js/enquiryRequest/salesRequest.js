//Createing for EOI view button
$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});

var isEoiAllow=false;
var enqSFDCName = "";
var customerMobileNo1 = "";
var customerFullname1 = "";
var customerEmail1 = ""

var channelPartnerArray=[];
var enqArray=[];
$(document).ready(function(){
	$('[data-toggle="tooltip"]').tooltip();   
	
	$("#mainPageLoad").fadeOut("slow"); 
	$("#sourceFundingValue").hide();
	//if($("#projectid").val()==='a1l6F0000080iilQAA'||$("#projectid").val()==='a1l6F000004QtUZQA0'||$("#projectid").val()==='a1l6F000004Q3l6QAC'||$("#projectid").val()==='a1l6F0000080irTQAQ')
		
	//if($("#hdrEnqName").val())
	
//	$("#btneoi").show();
	//$("#desiredUnitType1").hide();
	
	$(document).on('focus', '.autocomplete-off:input', function(){
        $( this ).attr( 'autocomplete', 'nope' );
    });
	/*var resp=fetchData('getSalesDropDowns',null,"GET");
	loadReferenceListById("budget",resp.objectMap.budgetList);*/
	onPageLoad();
    /*$('#mainPageLoad').hide();*/	
	$("#enquiryRequestBasicInfoForm").find("input,select,textarea").attr("tabIndex","-1");
	$("#enquiryRequestAddressInfoForm").find("input,select,textarea").attr("tabIndex","-1");
	 getTower();
	 $('#tower').change(function(){ 
		    var value = $(this).val();
	
		    getunittype($('#tower').val(),"");
		    getUnit($('#tower').val());
		    getfband($('#tower').val());
		   // getFloor(value);
		});
	 $('#floor').change(function(){ 
		    var value = $(this).val();
		    getunittype($('#tower').val(),value);
		});
	 $('#unit').change(function(){ 
		    var value = $(this).val();
		    $(".unitname").val($(this).find("option:selected").val());
		   // getunittype($('#tower').val(),value);
		});
	 
	 $(this).scrollTop(0);
	
	// getClosingManagersList();
});
function generatePaymentLink(){
	$.get("quickInvoiceGenerator", {
		"name" : $('#firstName').val()+" "+$('#lastName').val() ,
		"email": $('#email').val(),
		"mobileNo": $('#mobileNo').val(),
		"amount": $('#amountinLink').val(),
		"enq_name":$(".enquiryId").val(),
		"enq_sfid":$("#enquirysfid").val()
		/*http://10.21.24.46:8084/UserEnqury/quickInvoiceGenerator?
			name=Satheesh%20K&
			email=sathish.kyatham@godrejproperties.com
			&amount=1&
			mobileNo=9987677726
			&enq_name=ENQ%20%201234
			&enq_sfid=12345dfdsaf
		*/
	}, function(data) {
		//alert(data);
		 alert("Link Send o mobile no");
	});
}
function checkStatus(){
	$.get("quickInvoiceStatusCheck", {
		"reference_no" : $('#firstName').val() ,
		"order_no": $('#email').val() 
	}, function(data) {
		//alert(data);
		 //alert("Link Send o mobile no");
	});
}

function getTower(){
	 
	$.get("getTowerMaster", { "project_code" : $('.projectSfid').val() 
	}, function(data) {
	 
		$("#tower").html("");
		var html="";
		html=html+ '<option value=""></option>';
		for(var i=0;i<data.length;i++)
		 html=html+'<option value="'+data[i].tower_code__c+'">'+data[i].tower_name__c+'</option>';
		
		 $("#tower").html(html);
		 if($(".towername").val()!=''){
			 
			 getUnit($(".towername").val());
			 getfband($(".towername").val());
			 
			 $("#tower").val($(".towername").val());
			 getunittype($(".towername").val(),"");
			 
		}
	});
}

function getfband(code){
	 
	$.get("getTowerBand", {
		"project_code" : $('.projectSfid').val(),
		"tower_code":code
	}, function(data) {
		$("#fband").html("");
		var html="";
		html=html+ '<option value=""></option>';
//		alert(data.length);
		for(var i=0;i<data.length;i++)
		 html=html+'<option value="'+data[i].name+'">'+data[i].name+'</option>';
		
		 $("#fband").html(html);
		  
		 if($(".bandname").val()!=''){
			 $("#fband").val($(".bandname").val());
			 
		}
	});
}

function getUnit(code){
	 
	$.get("gethouseunit", {
		"project_code" : $('.projectSfid').val(),
		"tower_code":code,"floor_code":"","unit":""
	}, function(data) {
		$("#unit").html("");
		var html="";
		html=html+ '<option value=""></option>';
//		alert(data.length);
		for(var i=0;i<data.length;i++)
		 html=html+'<option value="'+data[i].sfid+'">'+data[i].propstrength__house_unit_no__c+'</option>';
		
		 $("#unit").html(html);
		 
		  
		 if($(".unitname").val()!=''){
			 $("#unit").val($(".unitname").val());
		 }
		 
	});
}


function getFloor(code){
	 
	$.get("getFloor", {
		"project_code" : $('.projectSfid').val(),
		"tower_code":code
	}, function(data) {
		$("#floor").html("");
		var html="";
		html=html+ '<option value=""></option>';
//		alert(data.length);
		for(var i=0;i<data.length;i++)
		 html=html+'<option value="'+data[i].propstrength__floor_number__c+'">'+data[i].floor_name__c+'</option>';
		
		 $("#floor").html(html);
	});
}


function getunittype(tcode,fcode){
	 
	$.get("getunittype", {
		"project_code" : $('.projectSfid').val(),
		"tower_code":tcode,
		"floor_code":fcode
	}, function(data) {
		$("#radioBtnCol").html("");
		var html="";
		for(var i=0;i<data.length;i++){
			$("#xyz").show();
			if(1===i)
				html=html+  "<label class='btn btn-primary active'>";
			else
				html=html+  "<label class='btn btn-primary'>";
			
			
				html=html+  "<input type='radio' id='desiredUnitType"+i+"' class='desiredUnitType' name='enquiryReport.desiredUnitType' value='"+data[i].propstrength__unit_type__c+"' >"+data[i].propstrength__unit_type__c+"</label>";
	   
		 //html=html+'<option value="'+data[i].propstrength__floor_number__c+'">'+data[i].floor_name__c+'</option>';
		}
	
		$("#radioBtnCol").html(html);


	 $('input.desiredUnitType[value="'+$("#desiredUnitType").val()+'"]').trigger('click');	 
	
	
		 
	});
}
function getDealDone(){
	 
	 $.get("getDealDone", {
		"tokenid" : $('#tokenId').val() 
	}, function(data) {
		// alert("update");
	}); 
}
function onPageLoad(){
	    if($("#isView").val()=='Y'){
            $("#salesTabId").hide();
            $("#basicInfoSaveBtn").hide();
            
            $("#inventoryTab").hide();
            $("#costsheetTab").hide();
            
            $("#basicInfoNxtBtn").hide();
           // $("#inventoryTab").hide();
	    }else{
			$("#basicInfoSaveBtn").hide();
			$("#salesTabId").show();
			$("#basicInfoNxtBtn").show();
			//$("#inventoryTab").show();
	    }
		var projectName=$('#projectName').val();
	    if(projectName!=""){
	    	$("#projectTitle").html(projectName);
	    }
	    var projectSfid=$('.projectSfid').val();
	    if(projectSfid==""){
	    	$('.projectSfid').val("a1l6F000002dTpoQAE");//val("a20O0000002agRG");
	    }
	    
	var hasParam=$('#hasParam').val();
	if(hasParam==="true"){
	    var enquiryType=$("#hiddenEnquiryType").val();
	    $("#isReferredByChannelPartnerRadio"+enquiryType).trigger("click");
	   /* isReferredChanged(enquiryType);*/
	    hideEnquirySourceByEnquiryType(enquiryType,null);
	}
	
    var countryCode=$('#countryCode').val();
    if(countryCode==""){
    	$('#countryCode').val("+91");
    }
    var mobileNo=$('#hiddenMobileNo').val();
    
    customerMobileNo1 = $('#hiddenMobileNo').val();
    
    if(mobileNo!=""){
      
    	$('#hdrCustomerMobileNo').text($('.selected-dial-code').text()+" "+mobileNo);
    	
    	$("#mobileNo").val(mobileNo);
    	
      if($("#viewType").val()==="SALESVIEW" && $("#loginRole").val()==="SALES"){
    	  getEnquiryForSales();
    	  
    	  $('.contactSalesView').attr("readonly","readonly");
    	  $('.contactSalesView').addClass('disableInputs');
      }else{
    	  getExistingInfo();
      }
      
       
      
      
      $("#contactDiv").addClass('disableCol');
    }else{
      $("#contactDiv").removeClass('disableCol');
    }  
}
function getEnquiryForSales(){
	var mobileNo=$("#mobileNo").val();
	var countryCode=$('#countryCode').val();
	var projectSfid=$('.projectSfid').val();
	var token=$('#token').val();
	var resp=fetchData("getEnquiryForSales/"+countryCode+"/"+mobileNo+"/"+projectSfid+"/"+token,null,"GET");
	populateSearchEnquiry(resp,true);
	debugger
	if(token.includes("G")||token.includes("E")||token.includes("MS")){
//		$("#salesTabId").remove();
		$("#salesTabId").show();
		
		$("#paymentDetailsTab").show();
		$("#eoisaveclose").hide();
		
	}
	
}
function getSalesDetails(event){
	event.preventDefault();
	var resp=fetchData('getSalesDropDowns',null,"GET");
	loadReferenceListById("budget",resp.objectMap.budgetList);
	loadReferenceListById("purpose",resp.objectMap.purposeList);
	loadReferenceListById("occupation",resp.objectMap.occupationList);
	loadReferenceListById("requiredPossesionTimeLine",resp.objectMap.possessionTimeLineList);
	loadReferenceListById("currentResidence",resp.objectMap.currentResidenceList);
	/*loadReferenceListById("salutation",resp.objectMap.salutationList);*/
    /*loadReferenceListById("typologicalArea",resp.objectMap.typologicalAreaList);*/
	/*loadReferenceListById("country",resp.objectMap.countryList);*/
}
function saveBaseInfo(event,el){
	event.preventDefault();
	/*$("#mainPageLoad").show();*/
	submitIt("enquiryRequestBasicInfoForm","saveBaseInfo","savebasicInfoResp");
}

function saveAddressInfo(event,el){
	
	/*segregation new and old sales comments*/
	//var newCommentInput = $('#newCommentInput').val()+"\n";
	//var oldCommentInput = $('#enquiryNonEditComment').val();
	//$('#enquiryNonEditComment').val(newCommentInput + oldCommentInput); 
	/* END segregation new and old sales comments*/
	//debugger;
	if($('#userid').val()!="null")
		{
			submitIt("enquiryRequestAddressInfoForm","saveAddressInfo","saveAddressInfoResp");
			getDealDone();
		}
	else
		{
		
		 swal({
             title: "Your Session Has been Expired, please login again",
             text: "",
             icon: "success",
             //type: "error",
             dangerMode: true,
           }).then(function(isConfirm) {
             if (isConfirm) {
            	 var url=$("#contextPath").val();			
     				window.location.href=url+"/"+"saleslogin";
             }
           });
		}
}
function saveAddressInfoEOI(event,el){
	
	submitIt("enquiryRequestAddressInfoForm","saveAddressInfo","saveAddressInfoRespEOI");
	//getDealDone();
}

function submitAddressInfo(event,el){
	submitIt("enquiryRequestAddressInfoForm","saveAddressInfo","submitAddressInfoResp");
}

function submitAddressInfoResp(data){
	 if(data.success){
		var hasParam=$('#hasParam').val();
		if(hasParam==="false"){
			var url=$("#contextPath").val();			
			window.location.href=url+"/"+"salesDeskSearch"+"/"+$(".projectSfid").val()+"/"+$('#projectName').val();
			
		}else{
			/*showMessage(data);*/
			if(data.success){
				switchToNextTab();
			}
		}
	 }else{
		 showMessage(data);
	 }
}
function saveAddressInfoResp(data){
	debugger
	if(data.success){
		var hasParam=$('#hasParam').val();
		if(hasParam==="false"){
			var enq=data.objectMap.EnquiryRequest;
			var url=$("#contextPath").val();
			var projectSfid=enq.project==null?'':enq.project.sfid;
			var enquirySfid=enq.sfid;
			var primaryContactSfid=enq.contact==null?'':enq.contact.sfid;
			var token=$("#token").val();
			var tokenid= $("#tokenId").val();;
			//alert("projectid:-"+$("#projectid").val())
			//getDealDone();
 
			
			window.location.href =url+ "/assignedusers?userId="+$("#userid").val()+"&projectid="+$("#projectid").val()+"&projectname="+$("#projectname").val()  ;
			
			 
		}else{
			/*showMessage(data);*/
			if(data.success){
				switchToNextTab();
			}
		}
	 }else{
		 showMessage(data);
	 }
}


function saveAddressInfoRespEOI(data){
	debugger
	if(data.success){
		var hasParam=$('#hasParam').val();
		if(hasParam==="false"){
			var enq=data.objectMap.EnquiryRequest;
			var url=$("#contextPath").val();
			var projectSfid=enq.project==null?'':enq.project.sfid;
			var enquirySfid=enq.sfid;
			var primaryContactSfid=enq.contact==null?'':enq.contact.sfid;
			var token=$("#token").val();
			var tokenid= $("#tokenId").val();;
			

			$('#paymentDetailsTab').css('display','block');
			$('#paymentDetailsTab a[href="#tab3"]').trigger('click');
 
			
			window.location.href =url+ "/assignedusers?userId="+$("#userid").val()+"&projectid="+$("#projectid").val()+"&projectname="+$("#projectname").val()  ;
			
			 
		}else{
			/*showMessage(data);*/
			if(data.success){
				switchToNextTab();
			}
		}
	 }else{
		 showMessage(data);
	 }
}


function saveAddressInfoRespEOI(data){
	debugger
	if(data.success){
		var hasParam=$('#hasParam').val();
		if(hasParam==="false"){
			var enq=data.objectMap.EnquiryRequest;
			var url=$("#contextPath").val();
			var projectSfid=enq.project==null?'':enq.project.sfid;
			var enquirySfid=enq.sfid;
			var primaryContactSfid=enq.contact==null?'':enq.contact.sfid;
			var token=$("#token").val();
			var tokenid= $("#tokenId").val();;
			

			$('#paymentDetailsTab').css('display','block');
			$('#paymentDetailsTab a[href="#tab3"]').trigger('click');
			
		 	
			 
		}else{
			/*showMessage(data);*/
			if(data.success){
				switchToNextTab();
			}
		}
	 }else{
		 showMessage(data);
	 }
}





function saveRequirementRequest(event,el){
	submitIt("enquiryRequestRequirementInfoForm","saveRequirementInfo","saveRequirementInfoResp");
}

function saveOtherRequest(event,el){
	submitIt("enquiryRequestOtherInfoForm","saveOtherInfo","saveOtherInfoResp");
}

function populateBasicInfo(enq,contact){
		if(!isEmpty(enq)){
			$('.enquiryId').val(enq.enquiryId);
			$('#enquirysfid').val(enq.sfid);
			$('#enquiry_name').val(enq.name);
			
			$('#hdrEnqName').text(enq.name);
			/* If Enquiry sync failed or equiry getting blank then we hide the sales tab buttons */
			
			enqSFDCName = enq.name;
			
			if(enq.contactId!=null)
			{
				$("#btneoi").show();
				$("#salsbtnsaveDiv").show();
				$("#syncIdDivSales").hide();
			}
		else
			{
				$("#btneoi").hide();
				$("#salsbtnsaveDiv").hide();
				$("#syncIdDivSales").show();
			}
			/*if(enq.name!="null" || enq.name!="")
				{
					$("#btneoi").show();
					$("#salsbtnsaveDiv").show();
					$("#syncIdDivSales").hide();
				}
			else
				{
					$("#btneoi").hide();
					$("#salsbtnsaveDiv").hide();
					$("#syncIdDivSales").show();
				}*/
			$('#primarycontactsfid').val(enq.contact.sfid);
			$('#projectsfid').val(enq.project.sfid);
			/*$('.enquirysfid').val(enq.sfid);*/
			/*$('.enquirysfid').removeAttr("disabled");*/
			$('.enquiryId').removeAttr("disabled");
			/*$('isReferredByChannelPartner')*/
			var projectSfid=enq.project==null?"":enq.project.sfid;
			$('.projectSfid').val(projectSfid);
			var channelPartner=enq.channelPartner;
			var brokerContact=enq.brokerContact;
			if(!isEmpty(channelPartner)){
				$('#channelPartnerName').val(channelPartner.name);
				$('#channelPartnerSfid').val(channelPartner.sfid);
				$('#channelPartnerId').val(channelPartner.channelPartnerId);
				loadBrokerContacts();
			}
			if(!isEmpty(brokerContact)){
				$('#brokerContact').val(brokerContact.sfid);
				$('#brokerContactId').val(brokerContact.contactId);
			}
			var cpHS=enq.isReferredByChannelPartnerFlag;
			/*$('label[labelName="isReferredByChannelPartner"]').removeClass("active");*/
			$('#isReferredByChannelPartnerInput').val(enq.isReferredByChannelPartner);
			 
			if(cpHS!=null && cpHS!=""){
				   $('label[labelName="isReferredByChannelPartner"][value="'+cpHS+'"]').trigger("click");
				   hideEnquirySourceByEnquiryType(cpHS,null);
			}else{
				 
					if(!isEmpty(channelPartner) && !isEmpty(brokerContact) && enq.isReferredByChannelPartner==='Partner'){
						 $("#isReferredByChannelPartnerRadioCP").trigger("click");
					}else if(enq.isReferredByChannelPartner==='Direct' && !isEmpty(enq.walkInSource)){
						 $("#isReferredByChannelPartnerRadioD").trigger("click");
					}else if(enq.isReferredByChannelPartner==='Partner' && !isEmpty(enq.otherChannelPartner)){
						 $("#isReferredByChannelPartnerRadioO").trigger("click");
				    }
					hideEnquirySourceByEnquiryType(null,enq);
			}
			/*isReferredChanged(cpHS);*/
			
		//	$('#walkInSource').val(enq.walkInSource);
		 
			
			$('#walkInSource option[value="'+enq.walkInSource+'"]').attr('selected','selected');
		 
			$('#walkInSourceDetail').val(enq.walkInSourceDetail);
			
			//alert(enq.otherChannelPartner);
			$('#otherChannelPartnerName').val(enq.otherChannelPartner);
			/*$('input[name="desiredUnitType"][value="'+enq.desiredUnitType+'"]').trigger('click');*/
			
			$('.enquiryId').val(enq.enquiryId);
			loadEnquiryReport(enq);
			if(!isEmpty(contact=enq.contact)){
				contact=enq.contact;
			}else if(!isEmpty(enq.contactId)){
				contact=enq.contactId;
			}
			if(!isEmpty(channelPartner)){
			$("#channelPartnerSfidCS").val(enq.channelPartner.sfid);
			}
		}
		
		if(!isEmpty(contact)){
			/*$('#salutation').val(contact.salutation);*/
			
			//Added By A
			$('#hdrCustomerName').text(contact.firstName+" "+contact.lastName);
			customerFullname1 = contact.firstName+" "+contact.lastName;
			customerEmail1 = contact.otherEmail;
			$('.customerNameBtn').text('"'+contact.firstName+' '+contact.lastName+'"')
			//END Added By A
			
			$('.contactId').val(contact.contactId);
			$('.contactId').removeAttr("disabled");
			
			$('#firstName').val(contact.firstName);
			$('#lastName').val(contact.lastName);
			
			$('#email').val(contact.otherEmail);
			$('#dateOfBirth').val(getDate(contact.dateOfBirth));
		/*	$('#ageGroup').val(contact.ageGroup);*/
			/*$('#industry').val(contact.industry);*/
			
			$('#addressLine1').val(contact.addressLine1);
			/*$('#addressLine2').val(contact.addressLine2);*/
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
		
		debugger
		if(!isEmpty(enq)){
			populatePaymentDetails(enq);
		}
		
		
		//schemeDropdown ();
}
function loadEnquiryReport(enq){
	
	if(!isEmpty(enq) && !isEmpty(enq.enquiryReport)){
		 
		getEnqDtlFromSFDCAPI();
		
		$("#contributionReceipt_val").val(enq.enquiryReport.contributionReceipt_val);
		$("#loanEligibility_val").val(enq.enquiryReport.loanEligibility_val);
		
		//comment by A
		//$("#enquiryNonEditComment").val(enq.enquiryReport.enquiryNonEditComment);
		
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
		 
	//	alert(enq.enquiryReport.cpComments);
		$('#purpose').val(enq.enquiryReport.purpose);
		$('#budget').val(enq.enquiryReport.budget);
		$('#otherChannelPartnerName').val(enq.enquiryReport.cpComments);	
		if(enq.enquiryReport.dealNegotiation!=null){
			if($("#salesViewType").val()!='N'){
				$("#salsbtnsave").show();
			}else{
				$("#salsbtnsave").hide();
			}
			//if($("#projectid").val()==='a1l6F0000080iilQAA'||$("#projectid").val()==='a1l6F000004QtUZQA0'||$("#projectid").val()==='a1l6F000004Q3l6QAC'||$("#projectid").val()==='a1l6F0000080irTQAQ')
				isEoiAllow=true;	
		}
		
		
		if($("#salesViewType").val()==="SALESVIEWSUBMITTED"){
	    	
			$('.sales_submitted').attr("readonly","readonly");
	    	  $('.sales_submitted').addClass('disableInputs');
	    	  $('#sourceFundingSlider').hide();
	    	  $('#sourceFundingValue').show();
	     }
		if(enq.enquiryReport.sourceOfFunding=='Self Finance'){
			$("#sourceFundingValue").hide();
		}else{
			
			if(enq.enquiryReport.sourceOfFunding!=null && enq.enquiryReport.sourceOfFunding !=''){
				$('#sourceFundingValue').show();
				$('#sourceFundingSlider').hide();
			}else{
				$("#sourceFundingValue").hide();
			}
	    }
		//alert("Trigger:-"+enq.enquiryReport.trigger1);
		$("#carpetAreaRequirement").val(enq.enquiryReport.carpetAreaRequirement);
		$("#followtype").val(enq.enquiryReport.followType);
		$("#trigger1").val(enq.enquiryReport.trigger1);
		$("#barrier1").val(enq.enquiryReport.barrier1);
		$("#trigger2").val(enq.enquiryReport.trigger2);
		$("#barrier2").val(enq.enquiryReport.barrier2);
		//alert(enq.sourcing_Managers__c);
		//alert(enq.verticle__c);
		
		$("#verticalId").val(enq.verticle__c);
		//alert(enq.verticle__c);
		//alert("53435"+enq.sourcing_Managers__c);
//		$("#sourcingManagerId").val(enq.sourcing_Managers__c);
		//var sourcingVal ="gshetty@godrejproperties.com";
		$("#sourcingManagerId").val(enq.sourcing_Managers__c);
		getClosingManagersList(enq.sourcingmanger_email);
		getClosingTeamLeadManagersList(enq.closing_Team_Lead_email);
		getSourcingTeamLeadManagersList(enq.sourcing_Team_Lead_email);
		getInternationalSalesManagersList(enq.internationalSMDto);
		$("#LostReasonID").val(enq.lost_reason_c__c);
		
		//$( "#followDate" ).datepicker({ defaultDate: new Date(enq.enquiryReport.followDate) });
		
		if(enq.enquiryReport.followDate!='' && enq.enquiryReport.followDate!=null){
			/* Changed By Satheesh K - 22-05-2020*/
			//alert(formatDateWithTime(enq.enquiryReport.followDate));
			$("#followdate").val(formatDateWithTime(enq.enquiryReport.followDate));
			/*$("#fdate").hide();
			$("#fdatevalue").show();*/
		}
		
		// Set EOI Tab Enable while alredy data saved
		if(enq.transactionType!=undefined && enq.transactionType!=null && enq.transactionType!=''){
			$('#paymentDetailsTab').css('display','block');
		}
	}else{
		$(".neftdetails").removeAttr('disabled','disabled');
	}
	
	salesSlider();
}


function salesSlider (){
	//alert ("Test 123 NEW 123");
	var slider = new Slider("#ownContro", {
	    ticks: [1000000, 5000000, 10000000, 15000000, 20000000],
	    ticks_labels: ['10L', '50L', '1Cr', '1.5Cr', '2Cr' ],
	    ticks_positions: [0, 25, 50, 75, 100],
	    tooltip: 'always',
	   step: 100000,
	   value: $('#contributionReceipt_val').val(),
	   formatter: function(value) {
			if(value >= 10000000) value = (value/10000000).toFixed(2) + ' Cr';
			else if(value >= 100000) value = (value/100000).toFixed(2) + ' Lac';
			else if(value >= 1000) value = (value/1000).toFixed(2) + ' K';
		   return value;
		}
	});
		
	
	
	var slider = new Slider("#loanAlig", {
	    ticks: [2000000, 10000000, 20000000, 30000000, 40000000, 50000000],
	    ticks_labels: ['20L', '1Cr', '2Cr', '3Cr', '4Cr', '5Cr' ],
	    ticks_positions: [0, 20, 40, 60, 80, 100],
	    //ticks_snap_bounds: 60,
	    tooltip: 'always',
	   step: 100000,
	   value: $("#loanEligibility_val").val(),
	   formatter: function(value) {
		   
		   /* var x=value;
		   x=x.toString();
		   var lastThree = x.substring(x.length-3);
		   var otherNumbers = x.substring(0,x.length-3);
		   if(otherNumbers != '')
		       lastThree = ',' + lastThree;
		   var res = otherNumbers.replace(/\B(?=(\d{2})+(?!\d))/g, ",") + lastThree; */

			if(value >= 10000000) value = (value/10000000).toFixed(2) + ' Cr';
			else if(value >= 100000) value = (value/100000).toFixed(2) + ' Lac';
			else if(value >= 1000) value = (value/1000).toFixed(2) + ' K';
			
			
			//return val;
		   
		   
		   return value;
		}
	    
	});
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
		
		
		$('#officelat').val(contact.contactReport.officelat);
		$('#officelng').val(contact.contactReport.officelng);
		$('#reslat').val(contact.contactReport.reslat);
		$('#reslng').val(contact.contactReport.reslng);
		
		
		$('#officePinCode').val(contact.contactReport.officePincode);
		$('#officeAddress').val(contact.contactReport.officeAddress);
		$('#ageGroup').val(contact.contactReport.ageGroup);
		$('#employmentStatus').val(contact.contactReport.employmentStatus);
		$('#designationCustSales').val(contact.designation);
		
	}
}
function populateAddressInfo(enq,contact){
 
		if(!isEmpty(enq)){
			$('.enquiryId').val(enq.enquiryId);
			/*$('#budget').val(enq.budget);*/
			/*$('#purpose').val(enq.purpose);*/
			/*$('#requiredPossesionTimeLine').val(enq.requiredPossesionTimeLine);*/
			if(!isEmpty(enq.contact)){
				contact=enq.contact;
			}else if(!isEmpty(enq.contactId)){
				contact=enq.contactId;
			}
			
		}
		
		if(!isEmpty(contact)){
			/*$('#currentResidence').val(contact.currentResidenceType);
			$('input[name="currentOwnershipType"]').val(contact.currentOwnershipType);
			$('#occupation').val(contact.occupation);*/
			/*$('#officeLocality').val(contact.officeLocality);*/
			$('#companyName').val(contact.companyName);
			/*$('#designation').val(contact.designation);
			$('#officeCity').val(contact.officeCity);
			$('#officePinCode').val(contact.officePinCode);
			$('#officeAddress').val(contact.companyLocality);*/
			$('.contactId').val(contact.contactId);
			$('.contactId').removeAttr("disabled");
		}
}
function savebasicInfoResp(data){
	debugger
	
	var enq=data.objectMap.EnquiryRequest;
	var contact =enq.contact;
	
	$(".enquiryId").removeAttr('disabled');
	$(".contactId").removeAttr('disabled');
	/*$('.enquirysfid').removeAttr('disabled');*/
	/*$('.enquirysfid').val(enq.sfid);*/
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
			var url=$("#contextPath").val();
			swal({
		        title: "Done",
		        text: "Thank You. Your token no. sent on your mobile number",
		        icon: "success",
		        type: "success",
		        dangerMode: true,
		      }).then(function(isConfirm) {
		        if (isConfirm) {
		        	//window.location.href=url+"/"+"success"+"?enquiryid="+enq.enquiryId+"&mobileno="+contact.mobileNo;
		        } 
		      });			
		}else{
			/*showMessage(data);*/
			if(data.success){
				//comment by A
				//switchToNextTab();
			}
		}
	}else{
		$("#mainPageLoad").hide();
		showMessage(data);
	}
	
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
	debugger
	/*$("#mainPageLoad").show();*/
	
	var mobileNo=$('#mobileNo').val();
	if(mobileNo.length==10){
		
		var countryCode=$('#countryCode').val();
		/*var url=$("#contextPath").val();*/
		var resp=fetchData("getExistingRecords",countryCode+"/"+mobileNo,"GET");
		populateSearchEnquiry(resp,false);
	}
	//$('#mainPageLoad').hide();
	/*populateBasicInfo(resp);
	populateRequirementInfo(resp);
	populateAddressInfo(resp);
	populateOtherInfo(resp);*/
}
function populateSearchEnquiry(resp,isSaleView){
	debugger
	var enquiryList=resp.objectMap.enquiryList;
	var contact=resp.objectMap.contact;
	if(!isEmpty(enquiryList)){
		/*$('#mainPageLoad').hide();*/	
		if(isSaleView){
			var allowPaymentStatus=enquiryList[0].isAllowCCPaymentGateway;
			if(allowPaymentStatus!=undefined && allowPaymentStatus!=null ){
				if (allowPaymentStatus == "Y"){
					$("#paymentReqRecordLi").show();
				} else {
					$("#paymentReqRecordLi").hide();
				}
				//$("#isAllowPaymentStatus").val(allowPaymentStatus);
			}else{
				$("#paymentReqRecordLi").hide();
				//$("#isAllowPaymentStatus").val("");
			}
			
			var enq=enquiryList[0];
			var contact=enq.contact;
			populateBasicInfo(enq,contact);
			populateAddressInfo(enq,contact);
		}else{
		  populateEnquiryList(enquiryList);
		}
	}else if(!isEmpty(contact)){
		$("#enquiryTable tbody").empty();
		$('.enquiryId').val("");
		$('.enquiryReportId').val("");
		$('.enquiryId').removeAttr("disabled");
		$('#channelPartnerName').val("");
		$('#channelPartnerSfid').val("");
		$('#channelPartnerId').val("");
		$('#brokerContact').val("");
		var hasParam=$('#hasParam').val();
		if(hasParam==="true"){
			   var enquiryType=$("#hiddenEnquiryType").val();
			   $("#isReferredByChannelPartnerRadio"+enquiryType).trigger("click");
			   hideEnquirySourceByEnquiryType(enquiryType,null)
			}else{
			   $("#isReferredByChannelPartnerRadioCP").trigger("click");			   
			   hideEnquirySourceByEnquiryType(null,null);
		}
		$('#walkInSource').val("");
	    $('#walkInSourceDetail').val("");
	    $('#otherChannelPartnerName').val("");
	    $("#desiredUnitType0").trigger("click");
		populateBasicInfo(null,contact);
		populateAddressInfo(null,contact);
	}else{			
		
		salesSlider();
			    $('.enquiryId').val("");
			    $('.enquiryReportId').val("");
			    $('.contactReportId').val("");
			    $('.enquiryId').removeAttr("disabled");				
				$('#channelPartnerName').val("");
				$('#channelPartnerSfid').val("");
				$('#channelPartnerId').val("");
				$('#brokerContact').val("");
				var hasParam=$('#hasParam').val();
				if(hasParam==="true"){
					   var enquiryType=$("#hiddenEnquiryType").val();
					   $("#isReferredByChannelPartnerRadio"+enquiryType).trigger("click");
					   hideEnquirySourceByEnquiryType(enquiryType,null);
					}else{
					   $("#isReferredByChannelPartnerRadioCP").trigger("click");
					   hideEnquirySourceByEnquiryType(null,null);
				}
				/*$('#isReferredByChannelPartnerInput').val("Partner");
			    $('label[labelName="isReferredByChannelPartner"][value="CP"]').trigger("click");
			   */ $('#walkInSource').val("");
			    $('#walkInSourceDetail').val("");
			    $('#otherChannelPartnerName').val("");
			    /*$('input[name="desiredUnitType"][value="'+enq.desiredUnitType+'"]').trigger('click');*/
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
			$("#purpose").val("");
		    $("#budget").val("");
		    $("#carpetAreaRequirement").val("");
		    $("#employmentStatus").val("");
		    $('#designationCustSales').val("");
		    $("#companyName").val("");
		    $("#officeAddress").val("");
		    $("#officeCity").val("");
		    $("#officePinCode").val("");
		    
		    $('#officelat').val("");
			$('#officelng').val("");
			$('#reslat').val("");
			$('#reslng').val("");
	}

}
function populateEnquiry(event){
	debugger
	event.preventDefault();
	/*$('#mainPageLoad').show();*/
	$('#multiEnq').modal('hide');
	var selectedEnq=$("input[name='enquiryRowId']:checked").val();
	var enq=enqArray["enquiry"+selectedEnq];
	var contact;
	var channelPartner=enq.channelPartner==null?'':enq.channelPartner;
	var brokerContact=enq.brokerContact==null?'':enq.brokerContact;
	if(!(isEmpty(enq.contact))){
		contact=enq.contact;
	}else{
		contact=enq.contactId;
	}
	
	populateBasicInfo(enq,contact);
	populateAddressInfo(enq,contact);
	/*if(!isEmpty(channelPartner) && !isEmpty(brokerContact) && enq.isReferredByChannelPartner==='Partner'){
		 $("#isReferredByChannelPartnerRadioCP").trigger("click");
	}else if(enq.isReferredByChannelPartner==='Direct' && !isEmpty(enq.walkInSource)){
		 $("#isReferredByChannelPartnerRadioD").trigger("click");
	}else if(enq.isReferredByChannelPartner==='Partner' && !isEmpty(enq.otherChannelPartner)){
		 $("#isReferredByChannelPartnerRadioO").trigger("click");
    }*/
	$("html, body").animate({ scrollTop: 0 }, "slow");
	$(".commonErrorDiv").css('display','none');
	//$('#mainPageLoad').hide();
}

function hideEnquirySourceByEnquiryType(enquiryTypeCode,enq){
	   if(!isEmpty(enquiryTypeCode)){
		if(enquiryTypeCode=='CP'){
			$(".hideDirectType").hide();
			$(".hideChannelPartnerType").show();
			$("#enquirySourceTextDiv").show();
		}else if(enquiryTypeCode=='D'){
			$(".hideChannelPartnerType").hide();
			if($("#isReferredByChannelPartnerInput").val()!="Direct"){
				isReferredChanged("D");
			}
			$(".hideDirectType").hide();/*$(".hideDirectType").show();*/
			$("#enquirySourceTextDiv").hide();
		}else if(enquiryTypeCode=='O'){
			$(".hideDirectType").hide();
			$(".hideChannelPartnerType").show();
			$("#enquirySourceTextDiv").show();
		}
	  }else if(!isEmpty(enq)){
		    var channelPartner=enq.channelPartner;
			var brokerContact=enq.brokerContact;
		    if(!isEmpty(channelPartner) && !isEmpty(brokerContact) && enq.isReferredByChannelPartner==='Partner'){
			    $(".hideDirectType").hide();
				$(".hideChannelPartnerType").show();
				$("#enquirySourceTextDiv").show();
			}else if(enq.isReferredByChannelPartner==='Direct' && !isEmpty(enq.walkInSource)){
				$(".hideChannelPartnerType").hide();
				if($("#isReferredByChannelPartnerInput").val()!="Direct"){
					isReferredChanged("D");
				}
				$(".hideDirectType").hide();/*$(".hideDirectType").show();*/
				$("#enquirySourceTextDiv").hide();
			}else if(enq.isReferredByChannelPartner==='Partner' && !isEmpty(enq.otherChannelPartner)){
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
function populateEnquiryList(enquiryList){
	debugger
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
	/*$('#multiEnq').modal('show');*/
	$('#multiEnq').modal({backdrop: 'static', keyboard: false})  
}

function isReferredChanged(cpHS){
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
		/*$(".hideDirectType").hide();
		$(".hideChannelPartnerType").show();*/
	}else if(cpHS=='D'){
		$("#isReferredByChannelPartnerRadioCP").prop("checked",false);
		$("#isReferredByChannelPartnerRadioO").prop("checked",false);
		
		$(".isReferredByChannelPartnerCP").attr("disabled","disabled");
		$(".isReferredByChannelPartnerO").attr("disabled","disabled");
		/*$(".hideChannelPartnerType").hide();
		$(".hideDirectType").show();*/
	}else if(cpHS=='O'){
		$("#isReferredByChannelPartnerRadioD").prop("checked",false);
		$("#isReferredByChannelPartnerRadioCP").prop("checked",false);
		
		$(".isReferredByChannelPartnerD").attr("disabled","disabled");
		$(".isReferredByChannelPartnerCP").attr("disabled","disabled");
		/*$(".hideDirectType").hide();
		$(".hideChannelPartnerType").show();*/
	}
}
/* END Auto fill address*/
function getChannelPartners(event,el){
	event.preventDefault();
	$("#brokerContact").empty();
	var text=$(el).val();
	if(text.length>=3){		
		fetchAsyncData("getChannelPartnerList",text,"GET","loadChannelPartners");		
	}else if(text.length==0){
		channelPartnerArray=[];
	}
	/*if(text.length>=3){
		
		var resp=fetchData("getChannelPartnerList",text,"GET");
		req=resp;
		var partnerList=resp.objectMap.channelPartnerList;
		channelPartnerArray=partnerList;
		refreshChannelPartnerList();
	*/
}
function loadChannelPartners(resp){
	var partnerList=resp.objectMap.channelPartnerList;
	/*alert(partnerList.length);*/
	channelPartnerArray=partnerList;
	refreshChannelPartnerList();
}
function refreshChannelPartnerList(){
	$("#channelPartnerName").autocomplete({
        source: function (request, response) {
            //data :: JSON list defined
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
        	/*fetchAsyncData("getBrokerContactByCPSfid",el.item.value,"GET","loadBrokerContacts");*/
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
		debugger
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
function generateKYCLink(event,el,isEOI){
	//useremailID
	//userid
	var userid="";
	if($("#userid").val()=='')
		userid=0;
	else
		userid=$("#userid").val();
	var mobile=$('#mobileNo').val();
		   	$.ajax({
		   		url: '/kycform/genSmsKycLink?mobilestr='+mobile+"&projectid="+$("#projectid").val()+"&projectname="+$('#projectName').val()+"&enqid="+$('#enquirysfid').val()+"&emailid="+$('#useremailID').val()+"&isEOI="+isEOI+"&userid="+userid+"&offersfid=",
				/*data: sendingData,*/
			    type: 'POST', 	  
			    success: function(data) 
			    {
			    	//swal('KYC Link Sent...!');
			    	  $("#mainPageLoad").hide();
			    },
			    beforeSend : function() {
		        	$("#mainPageLoad").show();
		        },
			    error: function(e) {
			    	alert(e);
			    	$("#mainPageLoad").hide();
			    }
			});
}
function generateKYCLinkViaOffer(event,el,isEOI,offersfid,tdsPaidBy){
	var userid="";
	var enqId=$('#enquirysfid').val();
	if($("#userid").val()=='')
		userid=0;
	else
		userid=$("#userid").val();
	var mobile=$('#mobileNo').val();
		   	$.ajax({
		   		url: '/kycform/sendKYCLinkAfterOffer?mobilestr='+mobile+"&projectid="+$("#projectid").val()+"&projectname="+$('#projectName').val()+"&enqid="+enqId+"&emailid="+$('#useremailID').val()+"&isEOI="+isEOI+"&userid="+userid+"&offersfid="+offersfid+"&tdspaidby="+tdsPaidBy+"",
				/*data: sendingData,*/
			    type: 'POST', 	  
			    success: function(data) 
			    {
			    	debugger
			    	
			    	  $("#mainPageLoad").hide();
			    },
			    beforeSend : function() {
		        	$("#mainPageLoad").show();
		        },
			    error: function(e) {
//			    	alert(e);
			    	$("#mainPageLoad").hide();
			    }
			});
}

function updateTDSEOIForm(enqId,tds){
	$.ajax({
   		
   		url: '/kycform/updateTDSEOIForm?enqid='+enqId+"&tdsPaidBy="+tds+"",
		/*data: sendingData,*/
	    type: 'POST', 	  
	    success: function(data) 
	    {
	    	  
	    	  $("#mainPageLoad").hide();
	    },
	    beforeSend : function() {
        	$("#mainPageLoad").show();
        },
	    error: function(e) {
	    	alert(e);
	    	$("#mainPageLoad").hide();
	    }
	});
}
function generateEOI(event,el){
	if( $('#followtype').is('[readonly]')){
		$('#paymentDetailsTab').css('display','block');
		$('#paymentDetailsTab a[href="#tab3"]').trigger('click');

	}else{
		saveAddressInfoEOI(event,el);
		getDealDone();
	}
}
/*function populateRequirementInfo(data){
	if(!isEmpty(data)){
		var enq=data.objectMap.enquiry;
		var contact;

		if(!isEmpty(enq)){
			
			contact=enq.contact;
		}else{
			contact=data.objectMap.contact;
		}
		
		if(!isEmpty(contact)){
			$('.contactId').val(contact.contactId);
		}
	}
}

function populateOtherInfo(data){
	if(!isEmpty(data)){
		var enq=data.objectMap.enquiry;
		var contact;
		if(!isEmpty(enq)){
			$('.enquiryId').val(enq.enquiryId);
			contact=enq.contact;
		}else{
			contact=data.objectMap.contact;
		}
		
		if(!isEmpty(contact)){
			
		}
	}
}*/


//$(document).ready(function(){
//	/* DOB date picker */
//	$(".ip-de").AnyPicker({
//		mode: "datetime",
//		//minValue: new Date(),
//		dateTimeFormat: "MMM yyyy",	
//		//maxValue: new Date("2031-01-01"),
//		//maxValue: new Date("2019-01-01"),
//		//maxValue: new Date("2031-01-01"),
//		
//		minValue: new Date(2019, 01),
//		maxValue: new Date(2030, 01)
//	
//			
//	});
//	/* END DOB date picker */
//});





/*Auto fill address*/
var autocomplete = {};
var autocompletesWraps = ['address'/*, 'address2'*/];

/*var address_form = {sublocality_level_1:'long_name', sublocality_level_2:'long_name', street_address:'long_name', sublocality_level_3:'long_name', Street:'long_name', route: 'long_name',locality: 'long_name',country:'long_name',administrative_area_level_1: 'long_name', administrative_area_level_2: 'long_name',  postal_code: 'short_name' };
var address2_form = {sublocality_level_1:'long_name',sublocality_level_2:'long_name', street_address:'long_name', sublocality_level_3:'long_name', Street:'long_name', route: 'long_name',locality: 'long_name',country:'long_name',administrative_area_level_1: 'long_name', administrative_area_level_2: 'long_name',  postal_code: 'short_name' };*/


/*var address_form = 	{route: 'long_name',xyz:'',administrative_area_level_1:'long_name',sublocality_level_1:'long_name', sublocality_level_2:'long_name', locality: 'long_name',country:'long_name',administrative_area_level_1: 'long_name', administrative_area_level_2: 'long_name',  postal_code: 'short_name' };
var address2_form = {route: 'long_name',xyz:'',administrative_area_level_1:'long_name',sublocality_level_1:'long_name',sublocality_level_2:'long_name', locality: 'long_name',country:'long_name',administrative_area_level_1: 'long_name', administrative_area_level_2: 'long_name',  postal_code: 'short_name' };*/


var address_form = 	{xyz:'', street_number: 'long_name', route: 'long_name', sublocality_level_1:'long_name', sublocality_level_2:'long_name', sublocality_level_3:'long_name', locality: 'long_name',country:'long_name',administrative_area_level_1: 'long_name', administrative_area_level_2: 'long_name',  postal_code: 'short_name' };
/*var address2_form = {xyz:'', street_number: 'long_name', route: 'long_name', sublocality_level_1:'long_name', sublocality_level_2:'long_name', sublocality_level_3:'long_name', locality: 'long_name',country:'long_name',administrative_area_level_1: 'long_name', administrative_area_level_2: 'long_name',  postal_code: 'short_name' };*/



function initialize() {
	
	

	$.each(autocompletesWraps, function(index, name) {
	
		if($('#'+name).length == 0) {
			return;
		}
		 
		autocomplete[name] = new google.maps.places.Autocomplete($('#'+name+' .autocomplete')[0], { types: ['geocode','establishment'] });
		
		//var subLocNew = '';
		
		google.maps.event.addListener(autocomplete[name], 'place_changed', function() {
			
			var place = autocomplete[name].getPlace();
			var form = eval(name+'_form');

			for (var component in form) {
				
				//subLocNew + 
				
				/*if (component == 'street_number'){
					alert ("Test ::: " + subLocNew + component);
					
				}*/
				
				
				//console.log ($('#'+name+' .'+component).val(''));
				
				/*if (component == 'xyz'){
					alert ("place.name ::: " + place.name);
				}*/
				
				
				
				
				$('#'+name+' .'+component).val('');
				$('#'+name+' .'+component).attr('disabled', false);
				$('#'+name+' .'+component).addClass('hasVal');
			}
			
			$('#'+name+ ' .autocomplete').val('');		
			
			$('#'+name+ ' .autocomplete').val(place.name);
			
			for (var i = 0; i < place.address_components.length; i++) {
				var addressType = place.address_components[i].types[0];
				if (typeof form[addressType] !== 'undefined') {
					
					if (addressType == "route"){
					//subLocNew + place.address_components[i][form[addressType]];
								
						$('#'+name+ ' .autocomplete').val(', '+place.address_components[i][form[addressType]]);
						
						//$('#comboAdd').append(place.address_components[i][form[addressType]] + ',');
						
					} if (addressType == "sublocality_level_2"){
					//subLocNew + place.address_components[i][form[addressType]];
					
					
						$('#'+name+ ' .autocomplete').val($('#'+name+ ' .autocomplete').val()+', '+place.address_components[i][form[addressType]]);
					
					
					//$('#comboAdd').append(place.address_components[i][form[addressType]] + ',');
					} if (addressType == "sublocality_level_3"){
						
						
						$('#'+name+ ' .autocomplete').val($('#'+name+ ' .autocomplete').val()+', '+place.address_components[i][form[addressType]]);
						
						//$('#comboAdd').append(place.address_components[i][form[addressType]] + ',');
						
					} if (addressType == "sublocality_level_1"){
						$('#'+name+ ' .autocomplete').val($('#'+name+ ' .autocomplete').val()+', '+place.address_components[i][form[addressType]]);
					} 
					
					
					
				
					var val = place.address_components[i][form[addressType]];
					$('#' +name+ '.'+addressType).val(val);
					 
				
					
					var val = place.address_components[i][form[addressType]];
					$('#'+name+' .'+addressType).val(val);
					//console.log (val);
				}
			}
		});
	});
}


function closingManagerList() {
	var url=$("#contextPath").val();
	window.location.href =url+ "/assignedusers?userId="+$("#userid").val()+"&projectid="+$("#projectid").val()+"&projectname="+$("#projectname").val() ;
}



function getEnqDtlFromSFDCAPI () {
	$('#enquiryOldComment').html("");
	$.post(pageContext+"getEnquiryDetails",{"enqSFID":$('#enquirysfid').val()},function(data){                       
	       
    }).done(function(data){
    	if (data != null && data != "") {
    		$('#enquiryOldComment').html(data);
    	} else {
    		$('#enquiryOldComment').html("No record found");
    	}
    }).fail(function(xhr, status, error) {
    	
    });

}

function getClosingManagersList(inputVal)
{
	if(($('#isReferredByChannelPartnerInput').val()==='Direct') && ($('#walkInSource').val()==='Digital' || $('#walkInSource').val()==='Newspaper'
			|| $('#walkInSource').val()==='Hoarding' || $('#walkInSource').val()==='Radio' || $('#walkInSource').val()==='Word of mouth' || $('#walkInSource').val()==='SMS' ))//|| $('#walkInSource').val()==='Exhibition'
		{
		$('#sourcingManagerIdDiv').hide();
		$("#sourcingManagerId").removeClass('requiredField');
		}
	else
		$('#sourcingManagerIdDiv').show();
	//
	       var urlGetUsers = "getUserProjectMapping?projectid="+$('#projectid').val();
	       var j = 0
	       var option="";
	    	$.getJSON(urlGetUsers, function (data) {
	    		option = "<option value=''>Select User</option>";
	    		if(inputVal=="nosourcingcip@godrejproperties.com")
	    			option = option+"<option value='nosourcingcip@godrejproperties.com' selected>No Sourcing Manager</option>";
	    		else
	    			option = option+"<option value='nosourcingcip@godrejproperties.com'>No Sourcing Manager</option>";
	    		$.each(data, function (index, value) {
	    			if(inputVal==value.emailid)
	    				{
	    					option = option+"<option value="+value.emailid+" selected>"+value.user_name+"</option>";
	    				}
	    			else
	    				option = option+"<option value="+value.emailid+">"+value.user_name+"</option>";
	    			j = j+1
	    		});		
	    	}).done(function() {
	    		$("#sourcingManagerId").append(option);
	    	});
}

function getClosingTeamLeadManagersList(inputVal)
{
	   var urlGetUsers = "getUserProjectMappingCT?projectid="+$('#projectid').val();
	   var j = 0
	   var option="";
		$.getJSON(urlGetUsers, function (data) {
			option = "<option value=''>Select User</option>";
			/*if(inputVal=="nosourcingcip@godrejproperties.com")
				option = option+"<option value='nosourcingcip@godrejproperties.com' selected>No Sourcing Manager</option>";
			else
				option = option+"<option value='nosourcingcip@godrejproperties.com'>No Sourcing Manager</option>";*/
			if(inputVal=="noclosingtl@gpl.com")
				option = option+"<option value='noclosingtl@gpl.com' selected>No Closing TL</option>";
			else
				option = option+"<option value='noclosingtl@gpl.com'>No Closing TL</option>";
			$.each(data, function (index, value) {
				if(inputVal==value.emailid)
					{
						option = option+"<option value="+value.emailid+" selected>"+value.user_name+"</option>";
					}
				else
					option = option+"<option value="+value.emailid+">"+value.user_name+"</option>";
				j = j+1
			});		
		}).done(function() {
			$("#closingTeamLeadId").append(option);
		});
}

function getSourcingTeamLeadManagersList(inputVal)
{
	   var urlGetUsers = "getUserProjectMappingST?projectid="+$('#projectid').val();
	   var j = 0
	   var option="";
		$.getJSON(urlGetUsers, function (data) {
			option = "<option value=''>Select User</option>";
			/*if(inputVal=="nosourcingcip@godrejproperties.com")
				option = option+"<option value='nosourcingcip@godrejproperties.com' selected>No Sourcing Manager</option>";
			else
				option = option+"<option value='nosourcingcip@godrejproperties.com'>No Sourcing Manager</option>";*/
			if(inputVal=="nosourcingtl@gpl.com")
				option = option+"<option value='nosourcingtl@gpl.com' selected>No Sourcing TL</option>";
			else
				option = option+"<option value='nosourcingtl@gpl.com'>No Sourcing TL</option>";
			$.each(data, function (index, value) {
				if(inputVal==value.emailid)
					{
						option = option+"<option value="+value.emailid+" selected>"+value.user_name+"</option>";
					}
				else
					option = option+"<option value="+value.emailid+">"+value.user_name+"</option>";
				j = j+1
			});		
		}).done(function() {
			$("#sourcingTeamLeadId").append(option);
		});
}




function getInternationalSalesManagersList(inputVal)
{
	var option="";
	var smNames = ["Ankit Narverkar", "Anupama Jadhav", "Anupama Jadhav","Bhavya Mehra","Chinmay Vaidya","Jessica Ann Lee","Meghna Gupta","Prashant Benjamin","Rohan Vaswani","Zubair Khan","Pratik Yagnik"
		,"Shivam Thakur","Mayank Jain","Samarth Sharma","Deependar Singh","Komit Malik","Parag Jha","Bhavya Dwivedi"];
		//option = option+"<option value='nosourcingtl@gpl.com' selected>No Sourcing TL</option>";
	option = option+"<option value=''>Select Int.Sales Manager</option>";
	for(var i=0;smNames.length-1>i;i++)
		{
		if(smNames[i]==inputVal)
			{
			option = option+"<option value='"+smNames[i]+"' selected>"+smNames[i]+"</option>";
			}
		else
			option = option+"<option value='"+smNames[i]+"'>"+smNames[i]+"</option>";
		}
		$("#internationalSMId").append(option);
	}
