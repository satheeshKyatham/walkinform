//function getofferApplicantDetails (e, enqName) {
$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});

var reraRegistrationNo = '';
var towerReraRegistrationNo = '';
var reraLabel = "";
var towerName1 = "";

if ($('#projectid').val() == "a1l6F000003TXloQAG"){
	reraLabel = "WBHIRA";
} else {
	reraLabel = "RERA";
}



function getofferApplicantDetails (e, offerSFID, enqSFID, contactSFID, offerName, enqName, propSFID, rowId, sourceCall) {	
	
	
	//$('#offerNameOffer').text(offerName);
	//$('#contactidOffer').text(contactSFID);
	
	
	//enqName = 'ENQ - 04833138';
	
	//this, \""+obj1[i].offer_sfid+"\", \""+obj1[i].enquiry_sfid+"\",  \""+obj1[i].contact_sfid+"\",  \""+obj1[i].offername+"\",  \""+obj1[i].enquiryname+"\", \""+obj1[i].propstrength__property__c+"\", \""+i+"\", \"offer\"
	
	$('#inIndividualCaseOffer').empty();
	$('#inComapnyCaseOffer').empty();
	$('#applicantPhotosOffer').empty();
	
	
	var pageContext = $("#pageContext").val()+"/";
	
	$.post(pageContext+"getKYCApplicantData",{"enqName":enqName, "contactSFID":contactSFID},function(data){
		
		var appType = '';
		var fullName = '';
		var pan = '';
		var email = ''; 
		var aadhar_card_no__c = '';
		var mobile_number__c = '';
		var mobile_number__c = '';
		var propstrength__sharing_ratio__c = '';
		var tdsSharingPer = '';
		var relationshipDtl = '';
		var passport_no__c = '';
		var nationality_a__c = '';
		var propstrength__resident_status__c = '';
		var permanentAddress = '';
		var communicationAddress = '';
		var birthdate = '';
		var html = '';
		var obj =JSON.parse(data);
		
		var j = 0;
		var appNumber = '';
		var frontHtml = '';
		var appPhotos = '';
		var appPhotoName = '';
		
		var companyHtml = '';
		
		var appTypeCompOrIndv = '';
		
		//var applicantCase = '';
		
		var dataStatus = '';
		var aadhaarNo = '';
		var passportNo = '';
		
		var applicantNote = '';
		var tdsStatus = '';
		var tdsSharing = '';
		var sonOf = '';
		var passportNoHtml = '';
		
		if(obj!=null){
			var objLength = obj.length;
			var sharingRatio = '0';
			
			var annexureName = '';
			
			if ($("#projectid").val() == "a1l2s00000000pEAAQ"  || $("#projectid").val() == "a1l2s00000003lPAAQ" || $("#projectid").val() == "a1l2s000000XmaMAAS" ) {
				annexureName = "Annexure F";
			} else if ($("#projectid").val() == "a1l6F000002X6IOQA0" || $("#projectid").val() == "a1l2s00000003VlAAI" || $("#projectid").val() == "a1l2s000000PGu3AAG" || $("#projectid").val() == "a1l2s000000PGu8AAG" || $("#projectid").val() == "a1l2s000000PGuDAAW" || $("#projectid").val() == "a1l2s000000PGuIAAW" || $("#projectid").val() == "a1l2s000000PGuNAAW" || $("#projectid").val() == "a1l2s000000PGuSAAW") {
				annexureName = "Annexure E";
			} else {
				annexureName = "Annexure G";
			}
			
			
			for(i = 0; i< obj.length; i++){    
				
				
				if (obj.length == 1) {
					$('#applicantPhotosOffer').removeAttr("style");
					$('#applicantPhotosOffer').css({"padding": "30px 290px 30px 290px"});
				} else if (obj.length == 2) { 
					$('#applicantPhotosOffer').removeAttr("style");
					$('#applicantPhotosOffer').css({"padding": "30px 200px 30px 200px"});
				} else if (obj.length == 3) { 
					$('#applicantPhotosOffer').removeAttr("style");
					$('#applicantPhotosOffer').css({"padding": "30px 110px 30px 110px"});
				} else if (obj.length == 4) { 
					$('#applicantPhotosOffer').removeAttr("style");
					$('#applicantPhotosOffer').css({"padding": "30px 70px 30px 70px"});
				}else if (obj.length == 5) { 
					$('#applicantPhotosOffer').removeAttr("style");
					$('#applicantPhotosOffer').css({"padding": "30px 20px 30px 20px"});
				}
				
				appPhotos += "<td style='height:165px; width:100px; vertical-align: middle; text-align:center;'>"+obj[i].propstrength__type__c+"</td>";
				appPhotoName += "<td style='width:100px; vertical-align: middle; text-align:center;'>"+obj[i].name+"</td>";
				
				
				if (obj[i].propstrength__type__c == "1") {
					sharingRatio = '100'
				} else {
					sharingRatio = '0'
				}
				
				
				/*if (applicantCase != 'notIndividual') {
					if (obj[i].salutation == 'Company' || obj[i].salutation == 'HUF' || obj[i].salutation == 'M/s.'){
						
						applicantCase = 'notIndividual';					
						
						$('#inIndividualCaseOffer').empty();
						
					} else {
						$('#inComapnyCaseOffer').empty();
					}
				}*/
				
			
				if (obj[i].propstrength__type__c == "1" && obj[i].salutation == 'Company' || obj[i].salutation == 'HUF' || obj[i].salutation == 'M/s.') {
					$('#inIndividualCaseOffer').empty();
					
					var officeAddress = obj[i].residential_street1__c+", "+ obj[i].residential_street2__c+","+obj[i].pstreetaddress2+","+obj[i].pstreetaddress3+","+ obj[i].residential_street3__c+" "+ obj[i].residential_city__c+","+ obj[i].residential_post_code__c+","+ obj[i].residential_state__c+","+ obj[i].residential_country__c;
					
					officeAddress = officeAddress.replace(/undefined/g, "");
					
					
					/*$('#niNameOffer').text(obj[i].name);
					$('#niDOBOffer').text(obj[i].birthdate);
					$('#niPANOffer').text(obj[i].propstrength__income_tax_permanent_account_no__c);*/
					
					appTypeCompOrIndv = 'company';
					
					
					//if ($("#projectid").val() == "a1l2s000000XmaMAAS") {
						companyHtml += '<div  style="padding-bottom:10px;"><b>1. In case of Company/Partnership Firm/LLP/Trust/HUF</b></div>';
						
						companyHtml += '<table class="table table-bordered" style="margin-bottom:15px;">'
								+ '<tbody>'
									+ '<tr>'
										+ '<td>Name</td>'
										+ '<td id="niNameOffer">'+obj[i].name+'</td>'
									+ '</tr>'
									+ '<tr>'
										+ '<td>Date of Incorporation/Formation</td>'
										+ '<td id="niDOBOffer">'+obj[i].birthdate+'</td>'
									+ '</tr>'
									+ '<tr>'
										+ '<td>PAN/CIN/LLPIN</td>'
										+ '<td id="niPANOffer">'+obj[i].propstrength__income_tax_permanent_account_no__c+'</td>'
									+ '</tr>'
									+ '<tr>'
										+ '<td>Registered Office Address</td>'
										+ '<td>'+officeAddress+'</td>'
									+ '</tr>'
									+ '<tr>'
										+ '<td>Name of Authorized Representative/ Partner /Karta /Trustee</td>'
										+ '<td>'+obj[i].relationship__c+' '+obj[i].relationship_name__c+'</td>'
									+ '</tr>'
									+ '<tr>'
										+ "<td colspan='2' style='font-size:10px;'>Note: If Applicant/s is Company, Partnership Firm, Limited Liability Partnership (LLP), Trust, Hindu Undivided Family (HUF), the following incorporation documents are required to be submitted along with this Application Form: (a) Certificate of Incorporation/Registration Certificate for the applicable entity (b) Memorandum of Association (c) Articles of Association (d) Partnership Deed (e) Limited Liability Partnership Agreement (f) Trust Deed (g) HUF PAN (h) Board/Partner/Trust's Resolution authorizing this purchase along with the name of the authorized representative/Partner. Please affix the official stamp/signature of the respective Company/Partnership Firm/LLP/Trust/HUF as may be applicable.</td>"											
									+ '</tr>'
								+ '</tbody>'
							+ '</table>';
					/*} else {
						companyHtml += '<div  style="padding-bottom:10px;"><b>1. In case of Company/LLP/HUF/Partnership Firm</b></div>';
						
						companyHtml += '<table class="table table-bordered" style="margin-bottom:15px;">'
								+ '<tbody>'
									+ '<tr>'
										+ '<td>Name</td>'
										+ '<td id="niNameOffer">'+obj[i].name+'</td>'
									+ '</tr>'
									+ '<tr>'
										+ '<td>Date of Incorporation/Formation</td>'
										+ '<td id="niDOBOffer">'+obj[i].birthdate+'</td>'
									+ '</tr>'
									+ '<tr>'
										+ '<td>PAN/CIN</td>'
										+ '<td id="niPANOffer">'+obj[i].propstrength__income_tax_permanent_account_no__c+'</td>'
									+ '</tr>'
									+ '<tr>'
										+ '<td>Registered Office Address</td>'
										+ '<td>'+officeAddress+'</td>'
									+ '</tr>'
									+ '<tr>'
										+ '<td>Name of Authorized Representative/ Partner /Karta</td>'
										+ '<td>'+obj[i].relationship__c+' '+obj[i].relationship_name__c+'</td>'
									+ '</tr>'
									+ '<tr>'
										+ "<td colspan='2' style='font-size:10px;'>Note: If Applicant/s is company, partnership firm, limited liability partnership, the following incorporation documents are required to be submitted along with this Application Form: (a) Certificate of Incorporation/Registration Certificate for the applicable entity (b) Memorandum of Association (c) Articles of Association (d) Partnership Deed (e) Limited Liability Partnership Agreement (f) Board/Partner's Resolution authorizing this purchase. Please affix the official stamp of the respective Company/LLP/Trust/Partnership firm/HUF/Society as may be applicable.</td>"											
									+ '</tr>'
								+ '</tbody>'
							+ '</table>';
					}*/
					
					
					
				
						
					
					
					
				} else {
					appTypeCompOrIndv = 'individual';
					
					$('#inComapnyCaseOffer').empty();
				}
				
				
				if (obj[i].address_proof_type == 'Aadhaar') {
					aadhaarNo = obj[i].address_proof_no;
				} else if (obj[i].address_proof_type == 'Passport') {
					passportNo = obj[i].address_proof_no;
				}
				var lastName = obj[i].lastName==null?'':obj[i].lastName;
				appType += "<td>Applicant "+obj[i].propstrength__type__c+"</td>";
				fullName +="<td>"+obj[i].name + ' ' + lastName + "</td>";
				pan +="<td>"+obj[i].propstrength__income_tax_permanent_account_no__c+"</td>";
				email +="<td>"+obj[i].email+"</td>";
				//aadhar_card_no__c +="<td>"+obj[i].aadhar_card_no__c+"</td>";
				aadhar_card_no__c +="<td>"+aadhaarNo+"</td>";
				mobile_number__c +="<td>"+obj[i].mobile_number__c+"</td>";
/*				propstrength__sharing_ratio__c +="<td>"+sharingRatio+"</td>";*/
				propstrength__sharing_ratio__c +="<td> As per <b> "+annexureName+" </b> </td>";
				tdsSharingPer +="<td></td>";
				relationshipDtl +="<td>"+obj[i].relationship__c+" "+obj[i].relationship_name__c+"</td>";
				//passport_no__c +="<td>"+obj[i].passport_no__c+"</td>";
				passport_no__c +="<td>"+passportNo+"</td>";
				nationality_a__c +="<td>"+obj[i].nationality_a__c+"</td>";
				propstrength__resident_status__c +="<td>"+obj[i].propstrength__resident_status__c+"</td>";
				permanentAddress +="<td>"+ obj[i].residential_street1__c+", "+ obj[i].residential_street2__c+","+obj[i].pstreetaddress2+","+obj[i].pstreetaddress3+","+ obj[i].residential_street3__c+" "+ obj[i].residential_city__c+", "+ obj[i].residential_post_code__c+", "+ obj[i].residential_state__c+", "+ obj[i].residential_country__c +"</td>";
				communicationAddress +="<td>"+obj[i].mailing_street1__c+", "+obj[i].mailing_street2__c+","+obj[i].cstreetaddress2+","+obj[i].cstreetaddress3+","+obj[i].mailing_street3__c+","+obj[i].mailing_city__c+", "+obj[i].mailing_state__c+", "+obj[i].country__c+", "+obj[i].postal_code__c+"</td>";
				birthdate +="<td>"+obj[i].birthdate+"</td>";
			}
			
			$('#inComapnyCaseOffer').append(companyHtml);
			
			frontHtml+= "<table class='table table-bordered' > <tbody><tr>"+appPhotos+"</tr> <tr>"+appPhotoName+"</tr></tbody> </table>"
			frontHtml = frontHtml.replace(/undefined/g, "");
			
			$('#applicantPhotosOffer').append(frontHtml);
			
			
			
			
			/*if ($("#projectid").val() == "a1l2s000000XmaMAAS") {
				applicantNote += "<tr> "
						+ '<td colspan="'+parseInt(objLength+1)+'" style="font-size:10px;">' 
							+ "Note: Applicant's passport size photograph and photocopies of PAN Card/OCI/PIO and Voter Card to be mandatorily submitted along with this Application Form. All compliance in terms of the Foreign Exchange Management Act, 1999 and its amendments shall be the sole responsibility of the Applicant/s. By providing Applicant's personal information in this Application Form, the Applicant/s hereby consents and authorizes Godrej Properties Limited or/and its affiliates to communicate with the Applicant/s by email(s), call(s), SMS(es),WhatsApp, electronic communication(s) using digital media or via any other mode of communication in relation to any of the information pertaining to the Project.   Applicant understands that Share in the property shall not be determined by the Payment Share of TDS and the same shall be as per the Applicable Laws."
						+ "</td> " 
					"</tr>" ;
				tdsStatus = "";
				tdsSharing += '<tr><td>Payment share for TDS</td>'+tdsSharingPer+'</tr>';
				
				sonOf = "";
				passportNoHtml = "";
				
			} else {*/
				applicantNote += "<tr> "
						+ '<td colspan="'+parseInt(objLength+1)+'" style="font-size:10px;">' 
							+ "Note: Applicant's passport size photograph and photocopies of PAN Card/OCI/PIO and Voter Card to be mandatorily submitted along with this Application Form. *All compliance in terms of the Foreign Exchange Management Act, 1999 and its amendments shall be the sole responsibility of the Applicant. By providing Applicant's personal information in this Application Form, the Applicant/s hereby consents and authorizes Godrej Properties Limited or/and its affiliates to communicate with the Applicant/s  by email(s), call(s), SMS(es), WhatsApp, electronic communication(s) using digital media or via any other mode of communication in relation to any of the information pertaining to the Project."
						+ "</td> " 
					"</tr>" ;
				tdsStatus = "";
				tdsSharing += '<tr><td>Payment share for TDS</td>'+tdsSharingPer+'</tr>';
				
				sonOf += '<tr><td>S/o/W/o/D/o</td>'+relationshipDtl +'</tr>';
				passportNoHtml += '<tr><td>Passport No.(In case of *NRI/ Foreign Customers) </td>'+passport_no__c+'</tr>'; 
			//}
			
			
			html +=  "<div style='padding-bottom:10px;'> <b>1. APPLICANT/S DETAILS</b> </div>" +
						"<table class='table table-bordered' style='margin-bottom:15px;' id='applicantDtlOffer'>" +
							"<tbody>" +
								"<tr> <th colspan='"+parseInt(objLength+1)+"' style='text-align:center;'><b>IN CASE OF INDIVIDUAL</b></th></tr>";
								
								
			
			
								html +=	'<tr><td></td>'+appType+'</tr>' 
										+ '<tr><td>Full Name</td>'+fullName+'</tr>'
										+ '<tr><td>Date of Birth</td>'+birthdate+'</tr>'
										
										//+ '<tr><td>TDS Sharing Ratio</td>'+tdsSharingPer+'</tr>'
										+ sonOf
										//+ '<tr><td>S/o/W/o/D/o</td>'+relationshipDtl +'</tr>'
										+ '<tr><td>PAN</td>'+pan+'</tr>'
										+ tdsStatus
										//+ '<tr><td>TDS Status</td>'+propstrength__sharing_ratio__c+'</tr>'
										+ '<tr><td>Nationality</td>'+nationality_a__c+'</tr>'
										+ '<tr><td>Residential Status</td>'+propstrength__resident_status__c+'</tr>'
										+ passportNoHtml
										//+ '<tr><td>Passport No.(In case of *NRI/ Foreign Customers) </td>'+passport_no__c+'</tr>' 
										+ '<tr><td>Permanent Address</td>'+permanentAddress+'</tr>'
										+ '<tr><td>Mobile No.</td>'+mobile_number__c+'</tr>'
										+ '<tr><td>Email Id</td>'+email+'</tr>'  
										+ '<tr><td>Address for Communication</td>'+communicationAddress+'</tr>' 
										+ tdsSharing
										+ applicantNote
										/*+ "<tr> "
											+ '<td colspan="'+parseInt(objLength+1)+'" style="font-size:10px;">' 
												+ "Note: Applicant's passport size photograph and photocopies of PAN Card/OCI/PIO and Voter Card to be mandatorily submitted along with this Application Form. *All compliance in terms of the Foreign Exchange Management Act, 1999 and its amendments shall be the sole responsibility of the Applicant. By providing Applicant's personal information in this Application Form, the Applicant/s hereby consents and authorizes Godrej Properties Limited or/and its affiliates to communicate with the Applicant/s  by email(s), call(s), SMS(es), electronic communication(s) using digital media or via any other mode of communication in relation to any of the information pertaining to the Project."
											+ "</td> " 
										"</tr>" +*/
										
										
										
										
										
										
						"</tbody>" +
						"</table>";
			
			
			html = html.replace(/undefined/g, "");
			
			$("#inIndividualCaseOffer").append(html);
			
			
			if (appTypeCompOrIndv == 'company') {
				$('#inIndividualCaseOffer').empty();
			}else {
				$('#inComapnyCaseOffer').empty();
			}
			
			
			console.log ("html ::: " + "<tr><td></td>"+appType+"</tr> <tr><td>Full Name</td>"+fullName+"</tr>");
		}
	}).done(function(obj){
		if (obj!="null") {
			getEnqAndOfferDtl (enqSFID, offerSFID,  rowId)
		} else {
			alert ("No Record Found");
		}
	});	
}




function getEnqAndOfferDtl (enqSFID, offerSFID, rowId) {
	var pageContext = $("#pageContext").val()+"/";
	
	$.post(pageContext+"getEnqAndOfferDtl",{"enqId":enqSFID, "offerSFID":offerSFID},function(data){
		var html = '';
		var obj =JSON.parse(data);
		var jvCityStateCountry = '';
		
		var broker_name = '';
		var broker_mobile = '';
		var walkinSource = '';
		var wing = ''; 
		var cmNameHtml = '';
		
		var brokerDtlHtml = '';
		var closingMngrName = "";
		
		$('#modeOfBookingOffer tbody').empty();
		
		if(obj.length > 0){
		//if(obj!=null){
			
			if (obj[0].rera_registration_number__c != undefined){
				reraRegistrationNo = obj[0].rera_registration_number__c;
			} else {
				reraRegistrationNo = '';
			}
			
			if (obj[0].tower_rera_no != undefined){
				towerReraRegistrationNo = obj[0].tower_rera_no;
			} else {
				towerReraRegistrationNo = '';
			}
			
			if (obj[0].propstrength__tower_name__c != undefined){
				towerName1 = obj[0].propstrength__tower_name__c;
			} else {
				towerName1 = '';
			}
			
			if (obj[0].propstrength__enquiry_type__c != "Partner") {
				
				if (obj[0].walk_in_source__c != undefined){
					walkinSource = obj[0].walk_in_source__c;
				}
				
				closingMngrName = "";
				
				if (obj[0].closing_manager_name__c != "No Closing Manager" && obj[0].closing_manager_name__c != "null" && obj[0].closing_manager_name__c != "" && obj[0].closing_manager_name__c != undefined && obj[0].closing_manager_name__c != 'undefined') {
					closingMngrName = obj[0].closing_manager_name__c;
				} else {
					closingMngrName = "";
				}
				
				
				//if ($("#projectid").val() == "a1l2s000000XmaMAAS") {
					cmNameHtml +=  "<tr> " +
										'<td colspan="2"> Name of the Developer\'s sale\'s representative: '+closingMngrName+' </td> ' +
									"</tr>";
				/*} else {
					cmNameHtml = "";
				}*/
				
				
				html +=  "<tr> " +
							"<td> Direct or Channel Partner : <span>Direct</span> </td> " +
							'<td>Walk In Source : <span>'+walkinSource+'</span></td>' +
						"</tr>" + 
						cmNameHtml;
				
				$('#modeOfBookingOffer tbody').append(html);
				//$('#modeOfBookingOffer tbody').append('<tr> <td> Direct or Channel Partner : <span>Direct</span> </td> <td>Walk In Source : <span>'+walkinSource+'</span></td></tr>');
				
			} else {
				
				if(obj[0].broker_name != undefined) {
					if (obj[0].broker_name != 'CP - GPL') {
						broker_name = obj[0].broker_name;
					} else {
						broker_name = "";
					}
						
						
				} else if ( obj[0].broker_mobile != undefined) {
					broker_mobile = obj[0].broker_mobile;
				}
				
				html = "";
				cmNameHtml = "";
				closingMngrName = "";
				
				if (obj[0].closing_manager_name__c != "No Closing Manager" && obj[0].closing_manager_name__c != "null" && obj[0].closing_manager_name__c != "" && obj[0].closing_manager_name__c != undefined && obj[0].closing_manager_name__c != 'undefined') {
					closingMngrName = obj[0].closing_manager_name__c;
				} else {
					closingMngrName = "";
				}
				
				
				//if ($("#projectid").val() == "a1l2s000000XmaMAAS") {
					brokerDtlHtml += '<tr>  <td colspan="2" style="height:100px; vertical-align: top;">Name, contact number, stamp and signature of Channel Partner (if applicable): '+broker_name+'</td>  </tr> ' ;
					cmNameHtml +=  "<tr>" +
										'<td colspan="2"> Name of the Developer\'s sale\'s representative: '+closingMngrName+' </td> ' +
									"</tr>";
				/*} else {
					brokerDtlHtml += '<tr>  <td colspan="2">Name: <span>'+broker_name+'</span></td>  </tr> ' ;
					cmNameHtml = "";
				}*/
				
				
				html += " <tr> " +
								'<td colspan="2"> Direct or Channel Partner : <span>Channel Partner</span> </td> ' +
						"</tr> " +
						cmNameHtml +
						brokerDtlHtml +
						//'<tr>  <td colspan="2">Name: <span>'+broker_name+'</span></td>  </tr> ' +
						"<tr> " +
							'<td>'+reraLabel+' Registration No.______________ </td> ' +
							"<td>Valid upto ______________</td>" +
						"</tr> " ;
							 
				$('#modeOfBookingOffer tbody').append(html);
				
				
				//$('#modeOfBookingOffer tbody').append('<tr> <td colspan="2"> Direct or Channel Partner : <span>Channel Partner</span> </td> </tr> <tr> <td colspan="2">Name: <span>'+broker_name+'</span></td>  </tr> <tr> <td>'+reraLabel+' Registration No.______________ </td> <td>Valid upto ______________</td></tr>');
				
			}
			
			$('#appProjectNameOffer').val(obj[0].marketing_project_name__c);
			
			/*$('#SAPCustomerCode').text(obj[0].sap_customer_code__c);
			$('#salesorderNo').text(obj[0].name);
			$('#bookingDate').text(obj[0].propstrength__booking_date__c);*/
			
			$('#offerNameOffer').text(obj[0].name);
			$('#contactidOffer').text(obj[0].propstrength__primary_contact__c);
			$('#bookingDateOffer').text(obj[0].propstrength__booking_date__c);
			
			
			if (obj[0].project_phases__c != undefined) {
				$('#projectPhasOffer').html('in ' + obj[0].project_phases__c);
				$('.towerPhasNameOffer').html(obj[0].project_phases__c);
			} else {
				$('#projectPhasOffer').html('');
				$('.towerPhasNameOffer').html('');
			}
			
			
			//$('#projectName').text(obj[0].project_name__c);
			
			
			if ($("#projectid").val() == "a1l2s000000XmaMAAS") {
				$('#projectNameLocationOffer').text(towerName1);
				$('#towerNameOffer').text(towerName1);
				
				if (obj[0].tower_sfid == "a2F2s000000csVgEAI") {
					//Godrej Retreat Symphony: 
					$('#plotArea').text('9.31875'); 
				} else if (obj[0].tower_sfid == "a2F2s000000csVlEAI") {
					//Godrej Retreat Vista: 
					$('#plotArea').text('6.7');
				} else if (obj[0].tower_sfid == "a2F2s000000csVqEAI") {
					//Godrej Retreat Aroma: 
					$('#plotArea').text('9.706');
				} else if (obj[0].tower_sfid == "a2F2s000000csVvEAI") {
					//Godrej Retreat Vibe: 
					$('#plotArea').text('7.806');
				} else if (obj[0].tower_sfid == "a2F2s000000csW0EAI") {
					//Godrej Retreat Orchard: 
					$('#plotArea').text('10.3375');
				}
				
			} else {
				$('#projectNameLocationOffer').text(obj[0].propstrength__description__c);
				$('#towerNameOffer').text(obj[0].propstrength__tower_name__c+wing);
			}
				
				
			if ($("#projectid").val() == "a1l2s00000000pEAAQ") {
				jvCityStateCountry = obj[0].jv_city__c 	
			} else {
				jvCityStateCountry = obj[0].jv_city__c + ", "+ obj[0].jv_state__c +", "+ obj[0].jv_country__c
			}
			
			jvCityStateCountry = jvCityStateCountry.replace(/undefined/g, "");
			
			$("#jvNameOffer").text(obj[0].jv_name__c);
			$("#jvStreetOffer").text(obj[0].jv_street__c);
			
			$('#jvCityStateCountryOffer').text(jvCityStateCountry);
			
			
			$('#selfUseInvestmentOffer').text(obj[0].is_purchase_for_self_use_or_investment__c);
			
			
			if (obj[0].wing_block__c != undefined) {
         	   wing = obj[0].wing_block__c;
            } else {
         	   wing = "";
            }
			
			
			$('#floorNameOffer').text(obj[0].propstrength__floor_name__c);
			
			$('#flatNoOffer').text(obj[0].propstrength__property_name__c);
			//$('#carpetAreaOffer').text(obj[0].carpet_area_converted__c);
			
			if ($('#projectid').val() == "a1l6F000003TXloQAG") {
				$('#carpetAreaOffer').text(obj[0].open_balc_sq_ft__c);
				$('#exclusiveAreasOffer').text(obj[0].appurtenant_area_sq_ft__c);
			} else if ($('#projectid').val() == "a1l2s000000XmaMAAS") {
				
				var length_sqm__c = 0;
				var breadth_sqm__c = 0;
				var totalCarpetArea = 0;
				
				if (obj[0].open_balc_sq_mt__c != undefined) {
					totalCarpetArea = obj[0].open_balc_sq_mt__c;
				} 
				
				if (obj[0].length_sqm__c != undefined) {
					length_sqm__c = obj[0].length_sqm__c;
				} 
				
				if (obj[0].breadth_sqm__c != undefined) {
					breadth_sqm__c = obj[0].breadth_sqm__c;
				} else {
					breadth_sqm__c= parseFloat(totalCarpetArea/length_sqm__c).toFixed(2);
				}
				
				$('#carpetAreaOffer').text(breadth_sqm__c);
				$('#exclusiveAreasOffer').text(length_sqm__c);
			} else {
				$('#carpetAreaOffer').text(obj[0].open_balc_sq_mt__c);
				$('#exclusiveAreasOffer').text(obj[0].appurtenant_area_sq_mt__c);
			}
			
			if ($('#projectid').val() == "a1l2s000000XmaMAAS") {
				$('#totalAreaOffer').text(obj[0].open_balc_sq_mt__c);
			} else if ($('#projectid').val() == "a1l2s000000PJMJAA4") {
				$('#totalAreaOffer').text(parseFloat(parseFloat(obj[0].open_balc_sq_mt__c)+parseFloat(obj[0].appurtenant_area_sq_mt__c)).toFixed(2));
			} else {
				$('#totalAreaOffer').text(parseFloat(parseFloat($('#carpetAreaOffer').text())+parseFloat($('#exclusiveAreasOffer').text())).toFixed(2));
			}
			
			
			
			
			
			console.log ('propstrength__total_basic_sales_price__c ::: ' + obj[0].propstrength__total_basic_sales_price__c);
			
			
			//$('#otherChargesOffer').text(parseFloat(obj[0].propstrength__revised_agreement_amount__c-obj[0].propstrength__total_basic_sales_price__c).toFixed(2));
			if ($("#projectid").val() != "a1l2s00000000pEAAQ") {
				$('#otherChargesOffer').text(0);
			}
			else
				{
					$('#otherChargesOffer').text(parseFloat(obj[0].propstrength__total_other_charges__c).toFixed(2));
				}
			
			
			
			
			//var netMinTotalOther = obj[0].propstrength__net_revenue__c-obj[0].propstrength__total_other_charges__c;
			
			var netMinTotalOther = obj[0].propstrength__total_sales_consideration__c-obj[0].propstrength__total_other_charges__c;
			
			var carpetAreaCostOffer = parseFloat(netMinTotalOther/parseFloat(parseFloat(obj[0].open_balc_sq_mt__c)+parseFloat(obj[0].appurtenant_area_sq_mt__c))*obj[0].open_balc_sq_mt__c).toFixed(2);
			var exclusiveAreasCostOffer = parseFloat(netMinTotalOther/parseFloat(parseFloat(obj[0].open_balc_sq_mt__c)+parseFloat(obj[0].appurtenant_area_sq_mt__c))*obj[0].appurtenant_area_sq_mt__c).toFixed(2);
			
			if ($("#projectid").val() == "a1l2s000000PJMJAA4") {
				$('#carpetAreaCostOffer').text(parseFloat(parseFloat(carpetAreaCostOffer)+parseFloat(exclusiveAreasCostOffer)).toFixed(2));
			} else {
				$('#carpetAreaCostOffer').text(parseFloat(netMinTotalOther/parseFloat(parseFloat(obj[0].open_balc_sq_mt__c)+parseFloat(obj[0].appurtenant_area_sq_mt__c))*obj[0].open_balc_sq_mt__c).toFixed(2));
			}
			
			//$('#exclusiveAreasCostOffer').text(parseFloat(netMinTotalOther/parseFloat(parseFloat(obj[0].carpet_area_converted__c)+parseFloat(obj[0].appurtenant_area_sq_mt__c))*obj[0].appurtenant_area_sq_mt__c).toFixed(2));
			$('#exclusiveAreasCostOffer').text(parseFloat(netMinTotalOther/parseFloat(parseFloat(obj[0].open_balc_sq_mt__c)+parseFloat(obj[0].appurtenant_area_sq_mt__c))*obj[0].appurtenant_area_sq_mt__c).toFixed(2));
			
			
			
			if ($("#projectid").val() != "a1l2s00000000pEAAQ") {
				$('#totalSaleConsiderationOffer').text(Math.round(parseFloat(parseFloat($('#carpetAreaCostOffer').text())+parseFloat($('#exclusiveAreasCostOffer').text()) ).toFixed(2)));
			}
			else
				{
				$('#totalSaleConsiderationOffer').text(Math.round(parseFloat(parseFloat($('#carpetAreaCostOffer').text())+parseFloat($('#exclusiveAreasCostOffer').text())+parseFloat(obj[0].propstrength__total_other_charges__c) ).toFixed(2)));
				}
			var totalSaleConsideration = parseFloat($('#carpetAreaCostOffer').text())+parseFloat($('#exclusiveAreasCostOffer').text())+parseFloat(obj[0].propstrength__total_other_charges__c);
			
			if ($("#projectid").val() == "a1l2s00000000pEAAQ") {
				otherCharges (obj[0].mappedCharges, obj[0].saleable_area__c, totalSaleConsideration);
			}
			
			// Godrej Meridien 2 AND Godrej Exquisite, Mumbai (Thane)
			if ($("#projectid").val() == "a1l2s000000PJMJAA4" || $("#projectid").val() == "a1l6F000004LVk8QAG" || $("#projectid").val() == "a1l2s00000003BMAAY" || $("#projectid").val() == "a1l2s00000003VlAAI" || $("#projectid").val() == "a1l2s000000XmaMAAS" || $("#projectid").val() == "a1l2s000000PGu3AAG" || $("#projectid").val() == "a1l2s000000PGu8AAG" || $("#projectid").val() == "a1l2s000000PGuDAAW" || $("#projectid").val() == "a1l2s000000PGuIAAW" || $("#projectid").val() == "a1l2s000000PGuNAAW" || $("#projectid").val() == "a1l2s000000PGuSAAW") {
				
				var totalCarpetNExclusiveArea = 0;
				
				if ($("#projectid").val() == "a1l2s000000XmaMAAS" || $("#projectid").val() == "a1l2s000000PJMJAA4") {
					totalCarpetNExclusiveArea = parseFloat($('#carpetAreaCostOffer').text());
				} else {
					totalCarpetNExclusiveArea = parseFloat($('#carpetAreaCostOffer').text())+parseFloat($('#exclusiveAreasCostOffer').text());
				}
				
				otherChargesUnit (obj[0].mappedCharges, obj[0].saleable_area__c, totalCarpetNExclusiveArea);
			}
			
			//Godrej Exquisite, Mumbai (Thane)
			//if ($("#projectid").val() == "a1l2s00000003BMAAY") {
			//	var totalCarpetNExclusiveArea = parseFloat($('#carpetAreaCostOffer').text())+parseFloat($('#exclusiveAreasCostOffer').text());
			//	otherChargesUnit (obj[0].mappedCharges, obj[0].saleable_area__c, totalCarpetNExclusiveArea);
			//}
			
			
			
			
		} else {
			$('#modeOfBookingOffer').remove();
		}
		
	}).done(function(obj){		
	
		if ($("#projectid").val() != "a1l2s00000000pEAAQ") {
			convertNumberToWords ();
		}
			
		if(offerSFID ==null || offerSFID==''){
			getOfferReceivedPaymentDtlWithEnquiry (enqSFID, rowId)
		}else{
			getOfferReceivedPaymentDtl(offerSFID, rowId);
		}
	});	
}




function getOfferReceivedPaymentDtl (offerSFID, rowId) {
	
	$('#paymentDtltableOffer tbody').find("tr:gt(0)").remove();
	
	//offerSFID = "a1X2s0000004GU1EAM";
	
	$.post("getOfferReceivedPaymentDtl",{"offerSFID":offerSFID},function(data){				 
		
		var html = '';
		var obj =JSON.parse(data);
		var txsNo = '';
		var branchHtml = '';
		
		if(obj!=null){
			for(i = 0; i< obj.length; i++){ 
				
				
				if ($("#projectid").val() == "a1l2s000000XmaMAAS") {
					 branchHtml += "<td> </td>" ; 
				} else {
					branchHtml = "";
				}
				
				
				if (obj[i].propstrength__payment_mode__c == 'Cheque') {
					txsNo = obj[i].propstrength__cheque_demand_draft_no__c;
				} else if (obj[i].propstrength__payment_mode__c == 'neft' || obj[i].propstrength__payment_mode__c == 'Swipe' || obj[i].propstrength__payment_mode__c == 'Wire Transfer' ) {
					txsNo = obj[i].propstrength__ifsc_rtgs_code__c;
				}
				
				
				/*html += "<tr> " +
							"<td>"+obj[i].propstrength__payment_type__c+"</td> " +
							"<td>"+obj[i].propstrength__bank_name_auto__c+"</td>" +
							"<td>"+obj[i].propstrength__amount__c+"</td>" +
							"<td>"+obj[i].propstrength__crn_no__c+"</td>" +
							"<td>"+obj[i].propstrength__cheque_demand_draft_number__c+"</td>" +
						"</tr>";*/
				
				html += "<tr> " +
				"<td>"+obj[i].propstrength__payment_mode__c+"</td> " +
				"<td>"+obj[i].propstrength__bank_name__c+"</td>" +
				"<td>"+obj[i].propstrength__amount__c+"</td>" +
				
				branchHtml 
				
				//"<td></td>" +
				+ "<td>"+txsNo+"</td>" +
			"</tr>";
				
				
			}
			
			html = html.replace(/undefined/g, "");
			
			$('#paymentDtltableOffer tbody').append(html);
			
		}
	}).done(function(data){
		printApplicationOfferForm(offerSFID, rowId);
	});	
}

function getOfferReceivedPaymentDtlWithEnquiry (enquirySFID, rowId) {
	
	$('#paymentDtltableOffer tbody').find("tr:gt(0)").remove();
	
	//offerSFID = "a1X2s0000004GU1EAM";
	
	$.post("getOfferReceivedPaymentDtl",{"offerSFID":enquirySFID},function(data){				 
		
		var html = '';
		var obj =JSON.parse(data);
		var txsNo = '';
		
		if(obj!=null){
			for(i = 0; i< obj.length; i++){ 
				
				
				if (obj[i].propstrength__payment_mode__c == 'Cheque') {
					txsNo = obj[i].propstrength__cheque_demand_draft_no__c;
				} else if (obj[i].propstrength__payment_mode__c == 'neft' || obj[i].propstrength__payment_mode__c == 'Swipe' || obj[i].propstrength__payment_mode__c == 'Wire Transfer' ) {
					txsNo = obj[i].propstrength__ifsc_rtgs_code__c;
				}
				
				
				/*html += "<tr> " +
							"<td>"+obj[i].propstrength__payment_type__c+"</td> " +
							"<td>"+obj[i].propstrength__bank_name_auto__c+"</td>" +
							"<td>"+obj[i].propstrength__amount__c+"</td>" +
							"<td>"+obj[i].propstrength__crn_no__c+"</td>" +
							"<td>"+obj[i].propstrength__cheque_demand_draft_number__c+"</td>" +
						"</tr>";*/
				
				html += "<tr> " +
				"<td>"+obj[i].propstrength__payment_mode__c+"</td> " +
				"<td>"+obj[i].propstrength__bank_name__c+"</td>" +
				"<td>"+obj[i].propstrength__amount__c+"</td>" +
				//"<td></td>" +
				"<td>"+txsNo+"</td>" +
			"</tr>";
				
				
			}
			
			html = html.replace(/undefined/g, "");
			
			$('#paymentDtltableOffer tbody').append(html);
			
		}
	}).done(function(data){
		printApplicationOfferForm(enquirySFID, rowId);
	});	
}



function convertNumberToWords() {
    var words = new Array();
    words[0] = '';
    words[1] = 'One';
    words[2] = 'Two';
    words[3] = 'Three';
    words[4] = 'Four';
    words[5] = 'Five';
    words[6] = 'Six';
    words[7] = 'Seven';
    words[8] = 'Eight';
    words[9] = 'Nine';
    words[10] = 'Ten';
    words[11] = 'Eleven';
    words[12] = 'Twelve';
    words[13] = 'Thirteen';
    words[14] = 'Fourteen';
    words[15] = 'Fifteen';
    words[16] = 'Sixteen';
    words[17] = 'Seventeen';
    words[18] = 'Eighteen';
    words[19] = 'Nineteen';
    words[20] = 'Twenty';
    words[30] = 'Thirty';
    words[40] = 'Forty';
    words[50] = 'Fifty';
    words[60] = 'Sixty';
    words[70] = 'Seventy';
    words[80] = 'Eighty';
    words[90] = 'Ninety';
    amount = $('#totalSaleConsiderationOffer').text();
    var atemp = amount.split(".");
    var number = atemp[0].split(",").join("");
    var n_length = number.length;
    var words_string = "";
    if (n_length <= 9) {
        var n_array = new Array(0, 0, 0, 0, 0, 0, 0, 0, 0);
        var received_n_array = new Array();
        for (var i = 0; i < n_length; i++) {
            received_n_array[i] = number.substr(i, 1);
        }
        for (var i = 9 - n_length, j = 0; i < 9; i++, j++) {
            n_array[i] = received_n_array[j];
        }
        for (var i = 0, j = 1; i < 9; i++, j++) {
            if (i == 0 || i == 2 || i == 4 || i == 7) {
                if (n_array[i] == 1) {
                    n_array[j] = 10 + parseInt(n_array[j]);
                    n_array[i] = 0;
                }
            }
        }
        value = "";
        for (var i = 0; i < 9; i++) {
            if (i == 0 || i == 2 || i == 4 || i == 7) {
                value = n_array[i] * 10;
            } else {
                value = n_array[i];
            }
            if (value != 0) {
                words_string += words[value] + " ";
            }
            if ((i == 1 && value != 0) || (i == 0 && value != 0 && n_array[i + 1] == 0)) {
                words_string += "Crores ";
            }
            if ((i == 3 && value != 0) || (i == 2 && value != 0 && n_array[i + 1] == 0)) {
                words_string += "Lakhs ";
            }
            if ((i == 5 && value != 0) || (i == 4 && value != 0 && n_array[i + 1] == 0)) {
                words_string += "Thousand ";
            }
            if (i == 6 && value != 0 && (n_array[i + 1] != 0 && n_array[i + 2] != 0)) {
                words_string += "Hundred and ";
            } else if (i == 6 && value != 0) {
                words_string += "Hundred ";
            }
        }
        words_string = words_string.split("  ").join(" ");
    }
   
    $('#totalScInWordOffer').text(words_string);
}



function printApplicationOfferForm(offerSFID, rowId) {
	var pageContext = $("#pageContext").val()+"/";
	
	var projectName1 = "";
	
	if ($('#projectid').val() == "a1l6F000003TXloQAG" || $('#projectid').val() == "a1l2s000000XmaMAAS" || $('#projectid').val() == "a1l6F000002X6IOQA0"){
		reraRegistrationNo = towerReraRegistrationNo;
	}
	
	if ($('#projectid').val() == "a1l2s000000XmaMAAS"){
		projectName1 = towerName1;
	} else {
		projectName1 = $('#appProjectNameOffer').val();
	}
	
	$.post(pageContext+"printApplicationForm",{"projectid":$('#projectid').val(), "enqSfid":offerSFID,"appFormData":$('#printApplicationFormOffer').html(),"projectName":projectName1,"reraRegistrationNo":reraRegistrationNo},function(data){				 
		
	}).done(function(data){
		
		//$('#appBtn'+rowId).empty();
		//$('#appBtn'+rowId).html('<i class="fa fa-print printficon"></i>');
		
		var win = window.open(pageContext+'applicationForm?name='+offerSFID, '_blank');
		if (win) {
		    win.focus();
		} else {
		    alert('Please allow popups for this website');
		}
		
	});
}


function otherCharges(otherCharges, saleable_area__c, offerOtherCharges) {
	
	if(otherCharges!=null){

		var Sinking_Fund = otherCharges.Sinking_Fund;
		var INFRA_CHARGES = otherCharges.INFRA_CHARGES;
		var PLC = otherCharges.PLC;
		var CAR_PARKING_CHARGES = otherCharges.CAR_PARKING_CHARGES;
		var Club_Development_Charges = otherCharges.Club_Development_Charges;
		var Advance_Maintenance_Charges = otherCharges.Advance_Maintenance_Charges;
		var Floor_Rise = otherCharges.Floor_Rise;
		
		
		processCnICharges (Club_Development_Charges, INFRA_CHARGES, saleable_area__c);
		
		processCarParkCharges (CAR_PARKING_CHARGES);
		
		processPLCnFRCCharges (PLC, Floor_Rise, saleable_area__c);
		
		processAdvMainNSinkingCharges (Advance_Maintenance_Charges, Sinking_Fund, saleable_area__c, offerOtherCharges);
		
		//processSinkingFund (Sinking_Fund);
		
	}
}

function otherChargesUnit(otherCharges, saleable_area__c, totalSaleConsideration) {
	if(otherCharges != null){
		//Godrej Exquisite, Mumbai (Thane)
		if ($("#projectid").val() == "a1l2s00000003BMAAY") {
			var Common_Area_Charges = otherCharges.Common_Area_Charges;
			processOtherChargesExqMum (Common_Area_Charges, saleable_area__c, totalSaleConsideration);
		} else if ($("#projectid").val() == "a1l6F000004LVk8QAG") {
			// Godrej Meridien 2
			var Other_Charges = otherCharges.Other_Charges;
			processTotalOtherCharges (Other_Charges, saleable_area__c, totalSaleConsideration);
		} else if ($("#projectid").val() == "a1l2s00000003VlAAI" || $("#projectid").val() == "a1l2s000000PGu3AAG" || $("#projectid").val() == "a1l2s000000PGu8AAG" || $("#projectid").val() == "a1l2s000000PGuDAAW" || $("#projectid").val() == "a1l2s000000PGuIAAW" || $("#projectid").val() == "a1l2s000000PGuNAAW" || $("#projectid").val() == "a1l2s000000PGuSAAW"){
			var clubDevCharges = otherCharges.Club_Development_Charges;
			
			var frc = otherCharges.Floor_Rise;
			var plc = otherCharges.PLC;
			var infra_charges = otherCharges.INFRA_CHARGES;
			
			var carParkCharges = otherCharges.CAR_PARKING_CHARGES;
			
			var clubDevChargesAmount =  clubDevChargesRWblr (clubDevCharges);
			var frcPlcInfraAmount = frcPlcInfraRWBlr (frc, plc, infra_charges, saleable_area__c);
			var carParkChargesAmount = carParkChargesRWBlr (carParkCharges, saleable_area__c);
			
			
			//processCarParkCharges (CAR_PARKING_CHARGES);
			
			
			$("#totalSaleConsiderationOffer").text("");
			$("#totalSaleConsiderationOffer").text(parseInt(parseInt(clubDevChargesAmount)+parseInt(frcPlcInfraAmount)+parseInt(carParkChargesAmount)+parseInt(totalSaleConsideration)).toFixed(2));
			
			convertNumberToWords ();
			
		} else if ($("#projectid").val() == "a1l2s000000XmaMAAS") {
			var otherCharge = otherCharge_RetreatGurg (otherCharges.Other_Charges, saleable_area__c);
			
			$("#totalSaleConsiderationOffer").text("");
			$("#totalSaleConsiderationOffer").text(parseInt(parseInt(otherCharge)+parseInt(totalSaleConsideration)).toFixed(2));
			
			convertNumberToWords ();
		} else if ($("#projectid").val() == "a1l2s000000PJMJAA4") {
			var otherCharge = otherCharge_WoodlandBlr (otherCharges.Infrastructure_Development_charges, saleable_area__c);
			
			$("#totalSaleConsiderationOffer").text("");
			$("#totalSaleConsiderationOffer").text(parseInt(parseInt(otherCharge)+parseInt(totalSaleConsideration)).toFixed(2));
			
			convertNumberToWords ();
		}
		
		
	}
}

function processSinkingFund (Sinking_Fund) {
	if(Sinking_Fund == null ){
		return;
	}
	
	Sinking_Fund.id
}

function processCnICharges (Club_Development_Charges, INFRA_CHARGES, saleable_area__c) {
	if(Club_Development_Charges == null || INFRA_CHARGES == null){
		return;
	}
	
	if (INFRA_CHARGES.propstrength__rate_per_unit_area__c == null || Club_Development_Charges.propstrength__fixed_charge__c == null || saleable_area__c == null) {
		return;
	} else {
		$("#cniCharges").text("");
		$("#cniCharges").text("Rs. " + parseInt(parseInt(INFRA_CHARGES.propstrength__rate_per_unit_area__c*saleable_area__c)+parseInt(Club_Development_Charges.propstrength__fixed_charge__c)).toFixed(2) + "/-");
	}
}


function processTotalOtherCharges (Other_Charges, saleable_area__c, totalSaleConsideration) {
	if(Other_Charges == null){
		return;
	}
	
	if (Other_Charges.propstrength__rate_per_unit_area__c == null || saleable_area__c == null) {
		return;
	} else {
		$("#commonAreaChargesTotal").text("");
		$("#commonAreaChargesTotal").text("Rs. " + parseInt(Other_Charges.propstrength__rate_per_unit_area__c*saleable_area__c).toFixed(2) + "/-");
		
		$("#totalSaleConsiderationOffer").text("");
		$("#totalSaleConsiderationOffer").text(parseInt(parseInt(Other_Charges.propstrength__rate_per_unit_area__c*saleable_area__c)+parseInt(totalSaleConsideration)).toFixed(2));
		
		convertNumberToWords ();
	}
}

//Total Other Charges for Godrej Exquisite, Mumbai
function processOtherChargesExqMum (Common_Area_Charges, saleable_area__c, totalSaleConsideration) {
	if(Common_Area_Charges == null){
		return;
	}
	
	if (Common_Area_Charges.propstrength__rate_per_unit_area__c == null || saleable_area__c == null) {
		return;
	} else {
		$("#commonAreaChargesTotal").text("");
		$("#commonAreaChargesTotal").text("Rs. " + parseInt(Common_Area_Charges.propstrength__rate_per_unit_area__c*saleable_area__c).toFixed(2) + "/-");
		
		$("#totalSaleConsiderationOffer").text("");
		$("#totalSaleConsiderationOffer").text(parseInt(parseInt(Common_Area_Charges.propstrength__rate_per_unit_area__c*saleable_area__c)+parseInt(totalSaleConsideration)).toFixed(2));
		
		convertNumberToWords ();
	}
}
// END Total Other Charges for Godrej Exquisite, Mumbai


function processCarParkCharges (CAR_PARKING_CHARGES) {
	if (CAR_PARKING_CHARGES.propstrength__fixed_charge__c == null ){
		return
	} else {
		if (CAR_PARKING_CHARGES.propstrength__fixed_charge__c > 0) {
			$("#carParkCharges").text("");
			$("#carParkCharges").text("Rs. " + CAR_PARKING_CHARGES.propstrength__fixed_charge__c + "/-");
		} else {
			$("#carParkCharges").text("");
			$("#carParkCharges").text("NA");
		}	
	}
	
	if (CAR_PARKING_CHARGES.propstrength__fixed_charge__c > 0) {
		$("#carParkSpaceCount").text("");
		$("#carParkSpaceCount").text("1");
	} else {
		$("#carParkSpaceCount").text("0");
	}
}


function processPLCnFRCCharges (PLC, Floor_Rise, saleable_area__c) {
	
	if(PLC == null || Floor_Rise == null){
		return;
	}
	
	if (PLC.propstrength__rate_per_unit_area__c == null || Floor_Rise.propstrength__rate_per_unit_area__c == null || saleable_area__c == null) {
		return;
	} else {
		$("#facilitiesCharges").text("");
		$("#facilitiesCharges").text("Rs. " + parseInt(parseInt(PLC.propstrength__rate_per_unit_area__c*saleable_area__c)+parseInt(Floor_Rise.propstrength__rate_per_unit_area__c*saleable_area__c)).toFixed(2) + "/-");
	}
}


function processAdvMainNSinkingCharges (Advance_Maintenance_Charges, Sinking_Fund, saleable_area__c, otherCharges) {
	if(Advance_Maintenance_Charges == null || Sinking_Fund == null){
		return;
	}
	
	if (Advance_Maintenance_Charges.propstrength__rate_per_unit_area__c == null || Sinking_Fund.propstrength__rate_per_unit_area__c == null || saleable_area__c == null || otherCharges == null) {
		return;
	} else {
		
		var advMainAmount = Advance_Maintenance_Charges.propstrength__rate_per_unit_area__c*saleable_area__c;
		var sinkingAmount  = Sinking_Fund.propstrength__rate_per_unit_area__c*saleable_area__c;
		
		
		$("#totalSaleConsiderationOffer").text("");
		$("#totalSaleConsiderationOffer").text(parseInt(parseInt(otherCharges)-parseInt(parseInt(advMainAmount)+parseInt(sinkingAmount))).toFixed(2));
		
		convertNumberToWords ();
	}
}


function clubDevChargesRWblr (clubDevCharges) {
	if(clubDevCharges == null){
		return 0;
	}
	
	if (clubDevCharges.propstrength__fixed_charge__c == null) {
		return 0;
	} else {
		$("#cniCharges").text("");
		$("#cniCharges").text("Rs. " + parseFloat(parseFloat(clubDevCharges.propstrength__fixed_charge__c)).toFixed(2) + "/-");
	}
	
	return parseFloat(parseFloat(clubDevCharges.propstrength__fixed_charge__c)).toFixed(2);
}


function frcPlcInfraRWBlr (frc, plc, infra_charges, saleable_area__c){
	if(frc == null || plc == null || infra_charges == null){
		return 0;
	}
	
	if (frc.propstrength__rate_per_unit_area__c == null || plc.propstrength__rate_per_unit_area__c == null || infra_charges.propstrength__rate_per_unit_area__c == null || saleable_area__c == null) {
		return 0;
	} else {
		$("#facilitiesCharges").text("");
		$("#facilitiesCharges").text("Rs. " + parseFloat(parseFloat(frc.propstrength__rate_per_unit_area__c*saleable_area__c)+parseFloat(plc.propstrength__rate_per_unit_area__c*saleable_area__c)+parseFloat(infra_charges.propstrength__rate_per_unit_area__c*saleable_area__c)).toFixed(2) + "/-");
	}
	return parseFloat(parseFloat(frc.propstrength__rate_per_unit_area__c*saleable_area__c)+parseFloat(plc.propstrength__rate_per_unit_area__c*saleable_area__c)+parseFloat(infra_charges.propstrength__rate_per_unit_area__c*saleable_area__c)).toFixed(2);
}

function carParkChargesRWBlr (carParkCharges, saleable_area__c){
	
	if (carParkCharges != null && carParkCharges.propstrength__fixed_charge__c != null && carParkCharges.propstrength__fixed_charge__c > 0) {
		$("#carParkCharges").text("");
		$("#carParkCharges").text("Rs. " + parseFloat(parseFloat(carParkCharges.propstrength__fixed_charge__c)).toFixed(2) + "/-");
		
		$("#carParkSpaceCount").text("");
		$("#carParkSpaceCount").text("1");
	} else {
		$("#carParkCharges").text("");
		$("#carParkCharges").text("NA");
		
		$("#carParkSpaceCount").text("");
		$("#carParkSpaceCount").text("0");
		
		return 0;
	}
	
	return parseFloat(parseFloat(carParkCharges.propstrength__fixed_charge__c)).toFixed(2);
}


function otherCharge_RetreatGurg (other_charges, saleable_area__c){
	if(other_charges == null){
		return 0;
	}
	
	if (other_charges.propstrength__rate_per_unit_area__c == null || saleable_area__c == null) {
		return 0;
	} else {
		$("#otherChrg_RetreatGurg").text("");
		$("#otherChrg_RetreatGurg").text(parseFloat(other_charges.propstrength__rate_per_unit_area__c*saleable_area__c).toFixed(2));
	}
	return parseFloat(other_charges.propstrength__rate_per_unit_area__c*saleable_area__c).toFixed(2);
}

function otherCharge_WoodlandBlr (other_charges, saleable_area__c){
	if(other_charges == null){
		return 0;
	}
	
	if (other_charges.propstrength__rate_per_unit_area__c == null || saleable_area__c == null) {
		return 0;
	} else {
		$("#otherChrg_WoodlandBlr").text("");
		$("#otherChrg_WoodlandBlr").text(parseFloat(other_charges.propstrength__rate_per_unit_area__c*saleable_area__c).toFixed(2));
	}
	return parseFloat(other_charges.propstrength__rate_per_unit_area__c*saleable_area__c).toFixed(2);
}