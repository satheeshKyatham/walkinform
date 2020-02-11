$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});

//Get EOI Details
//getEOIPreferencPrint function call in csPymtDataEoi () / file:storeEOIPaymentDtl.js
function getEOIPreferencPrint () {
	
	$('#EOIMultipleTablePrint tbody').empty();
	$.post(pageContext+"getEOITabPreferencRecord",{"enqSfid":$('#enquirysfid').val()},function(data){
		var html = '';
		var obj =JSON.parse(data);
		var eoiTokenType="";
		if(obj!=null){
			for(i = 0; i< obj.length; i++){
				eoiTokenType = obj[i].tokenTypeName;
				var unitno = '';
				if(obj[i].unit_name=='Select Unit')
					{
						unitno=''
					}
				else
					{
					unitno=obj[i].unit_name;
					}
						
				html += 	'<tr>'
								//+ '<td>'+obj[i].tokenTypeName+'</td>'
								+ '<td>'+obj[i].typology_name+'</td>'
								+ '<td></td>'
								+ '<td>'+obj[i].floor_band+'</td>'
								+ '<td>'+obj[i].tower_name+'</td>'
								+ '<td>'+unitno+'</td>'
								+ '<td>'+obj[i].eoi_carpark_name+'</td>'
								+ '<td>'+obj[i].description+'</td>' 
							"</tr>";
			}
			
			html = html.replace(/undefined/g, "");
			//
			$("#tokenTypeEOIPrint").text(eoiTokenType);
			$("#EOIMultipleTablePrint tbody").append(html);
		}
	}).done(function(obj){
		
		getEOIPaymentPrint();
	
	}).fail(function(xhr, status, error) {
    	swal({
    		title: "111 There was problem in generating PDF at this time. Please try again from EOI report.",
			text: "",
			timer: 8000,
			type: "warning",
        });
    });
}

function getEOIPaymentPrint () {
	
	$('#EOIMultiplePaymentPrint tbody').empty();
	
	$.post(pageContext+"getEOIPaymentRecord",{"enqSfid":$('#enquirysfid').val()},function(data){
		var html = '';
		var obj =JSON.parse(data);
		
		if(obj!=null){
			for(i = 0; i< obj.length; i++){    
				html += 	'<tr>'
								+ '<td></td>' 
								+ '<td>'+obj[i].payment_type+'</td>' 
								+ '<td>'+obj[i].transaction_amount+'</td>'
								+ '<td>'+obj[i].bank_name+'</td>' 
								+ '<td>'+obj[i].branch+'</td>' 
								+ '<td>'+obj[i].transaction_id+'</td>' 
								+ '<td>'+obj[i].description+'</td>'
							"</tr>";
			}
			html = html.replace(/undefined/g, "");
			$("#EOIMultiplePaymentPrint tbody").append(html);
		}
	}).done(function(obj){
		
		tncEOIData ();
		
	}).fail(function(xhr, status, error) {
    	swal({
    		title: "222 There was problem in generating PDF at this time. Please try again from EOI report.",
			text: "",
			timer: 8000,
			type: "warning",
        });
    });	
}
//END Get EOI Details

/*Get TnC Record*/
function tncEOIData () {
	$('#tncDataEOI').empty();
	$.post(pageContext+"getTncEOIData",{"projectid":$('#projectid').val() },function(data){                      
         var obj =JSON.parse(data);
         if (obj != null) {
            for (i = 0; i< obj.length; i++) {
                 $('#tncDataEOI').append(obj[i].tnc_text);
            }
         } 
    }).done(function(data){

    	enqAndProjectDtl();
    	
    }).fail(function(xhr, status, error) {
    	swal({
    		title: "333 There was problem in generating PDF at this time. Please try again from EOI report.",
			text: "",
			timer: 8000,
			type: "warning",
        });
    });	
}
/* END Get TnC Record*/

function enqAndProjectDtl () {
	$('#closingMangrEOIPrint').text('');
	$('#verticalEOIPrint').text('');
	$('#projectNameEOIPrint').text('');
	$('.dateOfEOIPrint').text('');
	$('#enqTypeEOIPrint').text('');
	$('#sourcingMngrEOIPrint').text('');
	$('#channelpartnerNameEOIPrint').text('');
	var contactSFID = '';
	var enqName = '';
	var regionName = '';
	var projectName = '';
	var projectNameWithoutCity = '';
	
	$.post(pageContext+"getEnqAndProjectDtl",{"enqId":$('#enquirysfid').val() },function(data){                      
		 var obj =JSON.parse(data);
         
         if (obj != null) {
			$('#closingMangrEOIPrint').text(obj[0].closing_manager_name__c);
			$('#verticalEOIPrint').text(obj[0].verticle__c);
			$('#projectNameEOIPrint').text(obj[0].marketing_project_name__c);
			$('.dateOfEOIPrint').text(obj[0].date_of_eoi__c);
			/*$('#enqTypeEOIPrint').text(obj[0].propstrength__enquiry_type__c);*/
			$('#enqTypeEOIPrint').text(obj[0].walk_in_source__c);
			$('#sourcingMngrEOIPrint').text(obj[0].sourcing_manager_name__c);
			$('#channelpartnerNameEOIPrint').text(obj[0].broker_name);
			
			contactSFID = obj[0].propstrength__primary_contact__c;
			enqName = obj[0].enq_name;
			regionName = obj[0].region__c;
			projectName = obj[0].marketing_project_name__c;
			projectNameWithoutCity = obj[0].projectNameWithoutCity;
         } 
	}).done(function(data){

		eoiApplicantDetails(contactSFID, enqName, regionName, projectName, projectNameWithoutCity);
          
	}).fail(function(xhr, status, error) {
    	swal({
    		title: "444 There was problem in generating PDF at this time. Please try again from EOI report.",
			text: "",
			timer: 8000,
			type: "warning",
        });
    });
}



// Get Applicant Details
function eoiApplicantDetails (contactSFID, enqName, regionName, projectName, projectNameWithoutCity) {	
	$.post(pageContext+"getKYCApplicantData",{"enqName":enqName, "contactSFID":contactSFID},function(data){
		$('#priAppNameEOIPrint').text('');
		$('#priAppEmailEOIPrint').text('');
		$('#priAppMobileEOIPrint').text('');
		$('#secondAppNameEOIPrint').text('');
		$('#thirdAppNameEOIPrint').text('');
		$('#priAppPANEOIPrint').text('');
		$('#priAppAadharEOIPrint').text('');
		$('#priAppResAddsEOIPrint').text('');
		$('#priAppAreaLocationEOIPrint').text('');
		$('#priAppCityEOIPrint').text('');
		$('#priAppPinCodeEOIPrint').text('');
		
		var resAddrs = '';
		var obj =JSON.parse(data);
		var aadhaarNo = '';
		var passportNo = '';
		
		if(obj!=null){
			for(i = 0; i< obj.length; i++){    
				if (obj[i].propstrength__type__c == "1" || obj[i].propstrength__type__c == null || obj[i].propstrength__type__c == undefined) {
					if (obj[i].address_proof_type == 'Aadhaar') {
						aadhaarNo = obj[i].address_proof_no;
					} else if (obj[i].address_proof_type == 'Passport') {
						passportNo = obj[i].address_proof_no;
					}
					
					resAddrs += obj[i].residential_street1__c+", "+ obj[i].residential_street2__c+" "+ obj[i].residential_street3__c+",  "+ obj[i].residential_state__c+", "+ obj[i].residential_country__c;
					resAddrs = resAddrs.replace(/undefined/g, "");
					
					$('#priAppNameEOIPrint').text(obj[i].name);
					$('#priAppEmailEOIPrint').text(obj[i].email);
					$('#priAppMobileEOIPrint').text(obj[i].mobile_number__c);
					
					$('#priAppPANEOIPrint').text(obj[i].propstrength__income_tax_permanent_account_no__c);
					$('#priAppAadharEOIPrint').text(aadhaarNo);
					$('#priAppResAddsEOIPrint').text(resAddrs);
				
					$('#priAppAreaLocationEOIPrint').text('');
					$('#priAppCityEOIPrint').text(obj[i].residential_city__c);
					$('#priAppPinCodeEOIPrint').text(obj[i].residential_post_code__c);
				} else if (obj[i].propstrength__type__c == "2") {
					$('#secondAppNameEOIPrint').text(obj[i].name);
				} else if (obj[i].propstrength__type__c == "3") {
					$('#thirdAppNameEOIPrint').text(obj[i].name);
				} 
			}
			
		}
	}).done(function(obj){
		
		printEOIForm(regionName, projectName, enqName, projectNameWithoutCity);
		
	}).fail(function(xhr, status, error) {
    	swal({
    		title: "555 There was problem in generating PDF at this time. Please try again from EOI report.",
			text: "",
			timer: 8000,
			type: "warning",
        });
    });	
}
//END Get Applicant Details


function printEOIForm(regionName, projectName, enqName, projectNameWithoutCity) {
	console.log ("regionName ::: " + regionName);
	console.log ("projectName ::: " + projectName);
	
	var pageContext = $("#pageContext").val()+"/";
	
	$.post(pageContext+"printEOIForm",{"EOIFormData":$('#printEOIForm').html(), "regionName":regionName, "projectName":projectName, "enqName":enqName},function(data){				 
		
	}).done(function(data){
		
		 //panTarget = pageContext+"file?name="+obj[i].pan_attach+"&from=EOIbookingReference&eid="+obj[i].enq_sfid+"&fid="+obj[i].pan_attach.charAt(0);
         //reciptTarget = pageContext+"file?name="+obj[i].cheque_attach+"&from=EOIbookingReference&eid="+obj[i].enq_sfid+"&fid="+obj[i].cheque_attach.charAt(0);
         
		
        var eoiFormPath =   'costSheetPDF/'+$('#region__c').val()+'/'+$('#marketingProjectName').val()+'/'+'EOI Details'+'/'+$('#enquiry_name').val()+'/eoi_form_'+$('#enquiry_name').val()+'.pdf';
     	
         
        var win = window.open(pageContext+'file?name='+eoiFormPath+"&from=eoiForm", '_blank');
		if (win) {
		    win.focus();
		    
             swal({
            	 title: "EOI Successfully Generated & KYC Link Sent.",
            	 text: "",
            	 icon: "success",
            	 type: "success",
            	 dangerMode: true,
             }).then(function(isConfirm) {
            	 //Changes by Satheesh - for EOI form
            	 debugger;
            	 if (isConfirm) {
            		 if($('#roleid').val()=='17')
            			 {
            			 	window.location.href =pageContext+ "offlineEOI?userId="+$("#userid").val()+"&projectid="+$("#projectid").val()+"&projectname="+$("#projectname").val();
            			 }
            		 else
            			 window.location.href =pageContext+ "assignedusers?userId="+$("#userid").val()+"&projectid="+$("#projectid").val()+"&projectname="+$("#projectname").val();
            	 }
             });
             
		} else {
		   alert('Please allow popups for this website');
		}
		
	}).fail(function(xhr, status, error) {
    	swal({
    		title: "666 There was problem in generating PDF at this time. Please try again from EOI report.",
			text: "",
			timer: 8000,
			type: "warning",
        });
    });	
}




