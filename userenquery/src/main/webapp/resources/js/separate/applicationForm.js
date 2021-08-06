$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});

var reraRegistrationNo = '';

function getApplicationList () {
	
	
	$('#applicationsListCol i').show();
	
	 $("#applicationTable tbody").empty();
	//alert ("Test Call 123 456");
	$.get("getApplicationList",{"userid":$('#userid').val(),"projectSfid":$('#projectid').val(), "userVerticals":USER_VERTICALES_GV},function(data){				 
		//alert("Data:-"+data);
		var obj =JSON.stringify(data);
		var obj1 =JSON.parse(obj);
		var applicationBookingId ="";
		var enqId ="";
		
		var appBtn = '';
		
		var html = '';
		
		//alert(obj1.length);
		if(obj1!=null) {
			for(var i=0;i<obj1.length;i++){
				
				appBtn =  "'"+'appBtn'+i+"'";
				
				applicationBookingId = '"'+obj1[i].application_booking_id_18__c+'"';
				enqId = '"'+obj1[i].enquiry18digit__c+'"'
				
				html += "<tr><td>"+obj1[i].createddate+"</td><td>"+obj1[i].propstrength__primary_applicant_name__c+"</td><td>"+obj1[i].booking_source__c+"</td><td>"+obj1[i].propstrength__broker_name__c+"</td><td>"+obj1[i].propstrength__tower__c+"</td><td>"+obj1[i].propstrength__property_name__c+"</td><td>"+obj1[i].propstrength__type__c+"</td><td><button id="+appBtn+"  class='btn btnDefaultBlue btn-default' onclick='getApplicantDetails(this, "+applicationBookingId+" , "+enqId+", "+i+")'><i class='fa fa-print printficon'></i></button></td></tr>";
			}
			
			html = html.replace(/null/g, "");
			
			$("#applicationTable tbody").append(html);
			
			
		} else {
			alert ("Data not found");
		}
	}).done(function(data){
		
		
		$('#applicationTable').dataTable({
			"ordering": false,
			"bDestroy": true
		});
		
		$('#applicationsListCol i').hide();
	});	
}

function getApplicantDetails (e, applicationBookingId18, enqId, rowId) {
	
	$(e).empty();
	$(e).html('<i class="fa fa-spinner fa-spin colorWhite"></i>');
	
	$('#inIndividualCase').empty();
	$('#inComapnyCase').empty();
	$('#applicantPhotos').empty();
	
	var pageContext = $("#pageContext").val()+"/";
	
	$.post(pageContext+"getApplicantData",{"applicationSfid":applicationBookingId18},function(data){
		
		var appType = '';
		var fullName = '';
		var pan = '';
		var email = ''; 
		var aadhar_card_no__c = '';
		var mobile_number__c = '';
		var mobile_number__c = '';
		var propstrength__sharing_ratio__c = '';
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
		
		if(obj!=null){
			var objLength = obj.length;
			var sharingRatio = '0';
			
			for(i = 0; i< obj.length; i++){    
				
				
				if (obj.length == 1) {
					$('#applicantPhotos').removeAttr("style");
					$('#applicantPhotos').css({"padding": "30px 290px 30px 290px"});
				} else if (obj.length == 2) { 
					$('#applicantPhotos').removeAttr("style");
					$('#applicantPhotos').css({"padding": "30px 200px 30px 200px"});
				} else if (obj.length == 3) { 
					$('#applicantPhotos').removeAttr("style");
					$('#applicantPhotos').css({"padding": "30px 110px 30px 110px"});
				} else if (obj.length == 4) { 
					$('#applicantPhotos').removeAttr("style");
					$('#applicantPhotos').css({"padding": "30px 70px 30px 70px"});
				}else if (obj.length == 5) { 
					$('#applicantPhotos').removeAttr("style");
					$('#applicantPhotos').css({"padding": "30px 20px 30px 20px"});
				}
				
				appPhotos += "<td style='height:165px; width:100px; vertical-align: middle; text-align:center;'>"+obj[i].propstrength__type__c+"</td>";
				appPhotoName += "<td style='width:100px; vertical-align: middle; text-align:center;'>"+obj[i].name+"</td>";
				
				
				if (obj[i].propstrength__type__c == "1st Applicant") {
					sharingRatio = '100'
				} else {
					sharingRatio = '0'
				}
				
				
				/*if (applicantCase != 'notIndividual') {
					if (obj[i].salutation == 'Company' || obj[i].salutation == 'HUF' || obj[i].salutation == 'M/s.'){
						
						applicantCase = 'notIndividual';					
						
						$('#inIndividualCase').empty();
						
					} else {
						$('#inComapnyCase').empty();
					}
				}*/
				
			
				if (obj[i].propstrength__type__c == "1st Applicant" && obj[i].salutation == 'Company' || obj[i].salutation == 'HUF' || obj[i].salutation == 'M/s.') {
					//$('#inIndividualCase').empty();
					
					/*$('#niName').text(obj[i].name);
					$('#niDOB').text(obj[i].birthdate);
					$('#niPAN').text(obj[i].propstrength__income_tax_permanent_account_no__c);*/
					
					appTypeCompOrIndv = 'company';
					
					companyHtml += '<table class="table table-bordered" style="margin-bottom:15px;">'
							+ '<tbody>'
								+ '<tr>'
									+ '<th colspan="2"  style="text-align:center;"><b>In case of Company/ LLP/ HUF/ Partnership Firm</b></th>'
								+ '</tr>'
								+ '<tr>'
									+ '<td>Name</td>'
									+ '<td id="niName">'+obj[i].name+'</td>'
								+ '</tr>'
								+ '<tr>'
									+ '<td>Date of Incorporation/ Formation</td>'
									+ '<td id="niDOB">'+obj[i].birthdate+'</td>'
								+ '</tr>'
								+ '<tr>'
									+ '<td>PAN/CIN</td>'
									+ '<td id="niPAN">'+obj[i].propstrength__income_tax_permanent_account_no__c+'</td>'
								+ '</tr>'
								+ '<tr>'
									+ '<td>Registered Office Address</td>'
									+ '<td></td>'
								+ '</tr>'
								+ '<tr>'
									+ '<td>Name of Authorized Representative/ Partner /Karta</td>'
									+ '<td></td>'
								+ '</tr>'
								+ '<tr>'
									+ "<td colspan='2' style='font-size:10px;'>Note: If Applicant/s is company, partnership firm, limited liability partnership, the following incorporation documents are required to be submitted along with this Application Form: (a) Certificate of Incorporation/Registration Certificate for the applicable entity (b) Memorandum of Association (c) Articles of Association (d) Partnership Deed (e) Limited Liability Partnership Agreement (f) Board/Partner's Resolution authorizing this purchase. Please affix the official stamp of the respective Company/LLP/Trust/Partnership firm/HUF/Society as may be applicable.</td>"											
								+ '</tr>'
							+ '</tbody>'
						+ '</table>';
					
					
					
				} else {
					/*appTypeCompOrIndv = 'individual';
					
					$('#inComapnyCase').empty();*/
				}
				
				
				appType += "<td>"+obj[i].propstrength__type__c+"</td>";
				fullName +="<td>"+obj[i].name+"</td>";
				pan +="<td>"+obj[i].propstrength__income_tax_permanent_account_no__c+"</td>";
				email +="<td>"+obj[i].email+"</td>";
				aadhar_card_no__c +="<td>"+obj[i].aadhar_card_no__c+"</td>";
				mobile_number__c +="<td>"+obj[i].mobile_number__c+"</td>";
				propstrength__sharing_ratio__c +="<td>"+sharingRatio+"</td>";
				//propstrength__sharing_ratio__c +="<td>"+obj[i].propstrength__sharing_ratio__c+"</td>";
				passport_no__c +="<td>"+obj[i].passport_no__c+"</td>";
				nationality_a__c +="<td>"+obj[i].nationality_a__c+"</td>";
				propstrength__resident_status__c +="<td>"+obj[i].propstrength__resident_status__c+"</td>";
				permanentAddress +="<td>"+ obj[i].residential_street1__c+", "+ obj[i].residential_street2__c+" "+ obj[i].residential_street3__c+" "+ obj[i].residential_city__c+", "+ obj[i].residential_post_code__c+", "+ obj[i].residential_state__c+", "+ obj[i].residential_country__c +"</td>";
				communicationAddress +="<td>"+obj[i].mailing_street1__c+", "+obj[i].mailing_street2__c+" "+obj[i].mailing_street3__c+" "+obj[i].mailing_city__c+", "+obj[i].mailing_state__c+", "+obj[i].country__c+", "+obj[i].postal_code__c+"</td>";
				birthdate +="<td>"+obj[i].birthdate+"</td>";
			}
			
			$('#inComapnyCase').append(companyHtml);
			
			frontHtml+= "<table class='table table-bordered' > <tbody><tr>"+appPhotos+"</tr> <tr>"+appPhotoName+"</tr></tbody> </table>"
			frontHtml = frontHtml.replace(/undefined/g, "");
			
			$('#applicantPhotos').append(frontHtml);
			
			
			
			
			html +=  "<div style='padding-bottom:10px;'> <b>1. APPLICANT/S DETAILS</b> </div>" +
						"<table class='table table-bordered' style='margin-bottom:15px;' id='applicantDtl'>" +
							"<tbody>" +
								"<tr> <th colspan='"+parseInt(objLength+1)+"' style='text-align:center;'><b>IN CASE OF INDIVIDUAL</b></th></tr>";
			
								html +=	'<tr><td></td>'+appType+'</tr>' 
										+ '<tr><td>Full Name</td>'+fullName+'</tr>'
										+ '<tr><td>Date of Birth</td>'+birthdate+'</tr>'
										+ '<tr><td>PAN</td>'+pan+'</tr>'
										//+ '<tr><td>Aadhar No.</td>'+aadhar_card_no__c+'</tr>'  
										+ '<tr><td>Payment share for TDS</td>'+propstrength__sharing_ratio__c+'</tr>'
										+ '<tr><td>Nationality</td>'+nationality_a__c+'</tr>'
										+ '<tr><td>Residential Status</td>'+propstrength__resident_status__c+'</tr>'
										//+ '<tr><td>Passport No.</td>'+passport_no__c+'</tr>' 
										+ '<tr><td>Permanent Address</td>'+permanentAddress+'</tr>'
										+ '<tr><td>Mobile No.</td>'+mobile_number__c+'</tr>'
										+ '<tr><td>Email</td>'+email+'</tr>'  
										+ '<tr><td>Address for Communication</td>'+communicationAddress+'</tr>'
										+ "<tr> "
											+ '<td colspan="'+parseInt(objLength+1)+'" style="font-size:10px;">' 
												+ "Note: Applicant's passport size photograph and photocopies of PAN Card/OCI/PIO and Voter Card to be mandatorily submitted along with this Application Form. *All compliance in terms of the Foreign Exchange Management Act, 1999 and its amendments shall be the sole responsibility of the Applicant. By providing Applicant's personal information in this Application Form, the Applicant/s hereby consents and authorizes Godrej Properties Limited or/and its affiliates to communicate with the Applicant/s  by email(s), call(s), SMS(es), electronic communication(s) using digital media or via any other mode of communication in relation to any of the information pertaining to the Project."
											+ "</td> " 
										"</tr>" +
										
						"</tbody>" +
						"</table>";
			
			
			html = html.replace(/undefined/g, "");
			
			$("#inIndividualCase").append(html);
			
			
			if (appTypeCompOrIndv == 'company') {
				$('#inIndividualCase').empty();
			}else {
				$('#inComapnyCase').empty();
			}
			
			
			console.log ("html ::: " + "<tr><td></td>"+appType+"</tr> <tr><td>Full Name</td>"+fullName+"</tr>");
		}
	}).done(function(obj){
		if (obj!="null") {
			getEnqAndBookingDtl (enqId, applicationBookingId18, rowId)
		} else {
			alert ("No Record Found");
		}	
	});	
}


function getEnqAndBookingDtl (enqId, applicationBookingId18, rowId) {
	var pageContext = $("#pageContext").val()+"/";
	
	$.post(pageContext+"getEnqAndBookingDtl",{"enqId":enqId, "applicationBookingId":applicationBookingId18},function(data){
		var html = '';
		var obj =JSON.parse(data);
		var jvCityStateCountry = '';
		
		var broker_name = '';
		var broker_mobile = '';
		var walkinSource = '';
		
		$('#modeOfBooking tbody').empty();
		
		if(obj!=null){
			
			if (obj[0].rera_registration_number__c != undefined){
				reraRegistrationNo = obj[0].rera_registration_number__c;
			} else {
				reraRegistrationNo = '';
			}
			
			
			if (obj[0].propstrength__enquiry_type__c != "Partner") {
				
				if (obj[0].walk_in_source__c != undefined){
					walkinSource = obj[0].walk_in_source__c;
				}
				
				$('#modeOfBooking tbody').append('<tr> <td> Direct or Channel Partner : <span>Direct</span> </td> <td>Walk In Source : <span>'+walkinSource+'</span></td></tr>');;
				
				//$('#enqtype').text('Direct');
			} else {
				
				if(obj[0].broker_name != undefined) {
					broker_name = obj[0].broker_name;
				} else if ( obj[0].broker_mobile != undefined) {
					broker_mobile = obj[0].broker_mobile;
				}
				
				$('#modeOfBooking tbody').append('<tr> <td colspan="2"> Direct or Channel Partner : <span>Channel Partner</span> </td> </tr> <tr> <td colspan="2">Name: <span>'+broker_name+'</span></td>  </tr> <tr> <td>RERA Registration No.______________ </td> <td>Valid upto ______________</td></tr>');
				
				/*$('#enqtype').text('Channel Partner');
				$('#broker_name').text(obj[0].broker_name);
				$('#broker_mobile').text(obj[0].broker_mobile);*/
			}
			
			//$('#appProjectName').text(obj[0].marketing_project_name__c);
			$('#appProjectName').val(obj[0].marketing_project_name__c);
			
			$('#SAPCustomerCode').text(obj[0].sap_customer_code__c);
			$('#salesorderNo').text(obj[0].name);
			$('#bookingDate').text(obj[0].propstrength__booking_date__c);
			
			
			if (obj[0].project_phases__c != undefined) {
				$('#projectPhas').html('in ' + obj[0].project_phases__c);
			} else {
				$('#projectPhas').html('');
			}
			
			
			//$('#projectName').text(obj[0].project_name__c);
			
			$('#projectNameLocation').text(obj[0].propstrength__description__c);
			
			jvCityStateCountry = obj[0].jv_city__c + ", "+ obj[0].jv_state__c +", "+ obj[0].jv_country__c
			jvCityStateCountry = jvCityStateCountry.replace(/undefined/g, "");
			
			$("#jvName").text(obj[0].jv_name__c);
			$("#jvStreet").text(obj[0].jv_street__c);
			
			$('#jvCityStateCountry').text(jvCityStateCountry);
			
			
			$('#selfUseInvestment').text(obj[0].is_purchase_for_self_use_or_investment__c);
			
			
			$('#towerName').text(obj[0].propstrength__tower_name__c);
			$('#floorName').text(obj[0].propstrength__floor_name__c);
			
			$('#flatNo').text(obj[0].propstrength__property_name__c);
			$('#carpetArea').text(obj[0].carpet_area_converted__c);
			$('#exclusiveAreas').text(obj[0].appurtenant_area_sq_mt__c);
			$('#totalArea').text(parseFloat(parseFloat($('#carpetArea').text())+parseFloat($('#exclusiveAreas').text())).toFixed(2));
			
			console.log ('propstrength__total_basic_sales_price__c ::: ' + obj[0].propstrength__total_basic_sales_price__c);
			
			
			$('#otherCharges').text(parseFloat(obj[0].propstrength__revised_agreement_amount__c-obj[0].propstrength__total_basic_sales_price__c).toFixed(2));
			
			//$('#carpetAreaCost').text(parseFloat(obj[0].propstrength__revised_agreement_amount__c/parseFloat(parseFloat(obj[0].carpet_area_converted__c)+parseFloat(obj[0].appurtenant_area_sq_mt__c))*obj[0].carpet_area_converted__c).toFixed(2));
			$('#carpetAreaCost').text(parseFloat(obj[0].propstrength__total_basic_sales_price__c/parseFloat(parseFloat(obj[0].carpet_area_converted__c)+parseFloat(obj[0].appurtenant_area_sq_mt__c))*obj[0].carpet_area_converted__c).toFixed(2));
			
			//$('#exclusiveAreasCost').text(parseFloat(obj[0].propstrength__total_basic_sales_price__c/parseFloat(parseFloat(obj[0].carpet_area_converted__c)+parseFloat(obj[0].appurtenant_area_sq_mt__c))*obj[0].appurtenant_area_sq_mt__c).toFixed(2));
			
			$('#exclusiveAreasCost').text(Math.round(obj[0].propstrength__total_basic_sales_price__c/parseFloat(parseFloat(obj[0].carpet_area_converted__c)+parseFloat(obj[0].appurtenant_area_sq_mt__c))*obj[0].appurtenant_area_sq_mt__c));
			
			//$('#exclusiveAreasCost').text(parseFloat(obj[0].propstrength__revised_agreement_amount__c/parseFloat(parseFloat(obj[0].carpet_area_converted__c)+parseFloat(obj[0].appurtenant_area_sq_mt__c))*obj[0].appurtenant_area_sq_mt__c).toFixed(2));
			$('#totalSaleConsideration').text(Math.round(parseFloat(parseFloat($('#carpetAreaCost').text())+parseFloat($('#exclusiveAreasCost').text())+parseFloat($('#otherCharges').text()) ).toFixed(2)));
			
		} else {
			$('#modeOfBooking').remove();
		}
		
	}).done(function(obj){
		
		convertNumberToWords ();
		
		getReceivedPaymentDtl(applicationBookingId18, rowId);
		//printApplicationForm(applicationBookingId18);
	});	
}


function printApplicationForm(applicationBookingId18, rowId) {
	var pageContext = $("#pageContext").val()+"/";
	
	var appFormLogo ='';
	
	if ( $('#projectid').val() == "a1l2s000000PGu3AAG"  || $('#projectid').val() == "a1l2s000000PGu8AAG"  || $('#projectid').val() == "a1l2s000000PGuDAAW" || $('#projectid').val() ==  "a1l2s000000PGuIAAW" || $('#projectid').val() == "a1l2s000000PGuNAAW" || $('#projectid').val() == "a1l2s000000PGuSAAW") {
  	  //appFormLogo = "<img width=\"300\" height=\"40\"  src=\"https://atulbhanushali.com/d4u/gplLogoRoyalWoods.jpg\"/>";
		appFormLogo = "<img width=\"300\" height=\"40\"  src=\"https://storage.googleapis.com/gplimagehost.appspot.com/gplLogoRoyalWoods.jpg\"/>";
    } else {
  	  //appFormLogo = "<img height='50' width='191' src='https://atulbhanushali.com/d4u/gplLogo.jpg' />";
    	appFormLogo = "<img height='50' width='191' src='https://storage.googleapis.com/gplimagehost.appspot.com/gplLogo.jpg' />";
    }
	
	
	$.post(pageContext+"printApplicationForm",{"appFormLogo":appFormLogo, "projectid":$('#projectid').val(), "enqSfid":applicationBookingId18,"appFormData":$('#printApplicationForm').html(),"projectName":$('#appProjectName').val(),"reraRegistrationNo":reraRegistrationNo},function(data){				 
		
	}).done(function(data){
		
		$('#appBtn'+rowId).empty();
		$('#appBtn'+rowId).html('<i class="fa fa-print printficon"></i>');
		
		var win = window.open(pageContext+'applicationForm?name='+applicationBookingId18, '_blank');
		if (win) {
		    //Browser has allowed it to be opened
		    win.focus();
		} else {
		    //Browser has blocked it
		    alert('Please allow popups for this website');
		}
		
	});
}





function getReceivedPaymentDtl (applicationBookingId18, rowId) {
	
	$('#paymentDtltable tbody').find("tr:gt(0)").remove();
	
	$.post("getReceivedPaymentDtl",{"applicationBookingId18":applicationBookingId18},function(data){				 
		
		var html = '';
		var obj =JSON.parse(data);
		
		if(obj!=null){
			for(i = 0; i< obj.length; i++){ 
				
				html += "<tr> " +
							"<td>"+obj[i].propstrength__payment_type__c+"</td> " +
							"<td>"+obj[i].propstrength__bank_name_auto__c+"</td>" +
							"<td>"+obj[i].propstrength__amount__c+"</td>" +
							"<td>"+obj[i].propstrength__crn_no__c+"</td>" +
							"<td>"+obj[i].propstrength__cheque_demand_draft_number__c+"</td>" +
							"<td>"+obj[i].propstrength__ifsc_rtgs_code__c+"</td>" +
						"</tr>";
			}
			
			html = html.replace(/undefined/g, "");
			
			$('#paymentDtltable tbody').append(html);
			
		}
		
	}).done(function(data){
		
		printApplicationForm(applicationBookingId18, rowId);
		
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
    amount = $('#totalSaleConsideration').text();
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
   
    $('#totalScInWord').text(words_string);
   // document.getElementsByTagName('div')[0].innerHTML = words_string;
}