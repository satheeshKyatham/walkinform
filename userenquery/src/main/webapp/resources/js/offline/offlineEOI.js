$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});
var amIEoi = 0;
var amNumEoi = 4; 


var EOIDtl = 0;
var NumEoiDtl = 4; 

var pageContext = $("#pageContext").val()+"/";

function addMorePtBtnEoi () {
	if(amIEoi < amNumEoi) {
		$('#csPtColEoi tr:last-child').after('<tr class="csPtDataRowEoi"> <td></td> <td><input class="csPtEnqSfidEoi" style="display:none;" value="'+$('#enquirysfid').val()+'"/>  <select onchange="csPtDdEoi(this)" class="full form-control input-sm csPtDropDownEoi requiredField"><option value="">Select</option><option value="Cheque">Cheque</option><option value="NEFT">NEFT/Credit</option><option value="Swipe">Swipe</option><option value="Wire Transfer">Wire Transfer (PayZap, Google Pay)</option> </select></td><td><input class="full form-control input-sm csPtBankNameEoi requiredField" placeholder="Bank Name"/></td><td><input class="full form-control input-sm csPtBranchEoi requiredField" placeholder="Branch Name"/></td><td><input class="full form-control input-sm csPtTransactionIdEoi requiredField" placeholder="Transaction ID" /></td><td><input type="date" class="full form-control input-sm csPtTransactionDateEoi requiredField" placeholder="Transaction Date"/></td><td><input maxlength="10" class="numericWithoutDecimal  numericField full form-control input-sm csPtTransactionAmountEoi requiredField" onkeyup="csPtcalculateGrandTotalEoi()" placeholder="Transaction Amount" name="amount"/></td> <td> <input type="file" class="full form-control input-sm panAttachEoi"  accept="application/pdf,image/*"> </td> <td> <input type="file" class="full form-control input-sm receiptAttachEoi" accept="application/pdf,image/*"/>  <td><textarea class="full form-control input-sm csPtDescriptionEoi" placeholder="Description"></textarea></td><td class="removeCsPtColEoi txtCenter"><i onclick="removeCsPtColEoi(this)" class="fa fa-times-circle redColr cursorPoint"></i></td></tr>');
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

function removeCsPtColEoi (e) {
	$(e).closest("tr").remove();
	amIEoi -= 1;
	
	csPtcalculateGrandTotalEoi ();
}

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
  	   	$(e).closest("tr").find(".csPtTransactionIdEoi").attr("maxlength","15");
  	   	$(e).closest("tr").find(".csPtTransactionIdEoi").after("<small class='csPtReachMexLengthEOI'>ID can be max 15 characters long.</small>");
		
		$(e).closest("tr").find('.csPtBankNameEoi').prop('disabled', false);
		$(e).closest("tr").find('.csPtBranchEoi').prop('disabled', false);
	}else  if ($(e).val() == 'Swipe' || $(e).val() == 'Wire Transfer') {
		$(e).closest("tr").find(".csPtTransactionIdEoi").val("");
 	   	$(e).closest("tr").find(".csPtTransactionIdEoi").attr("maxlength","15");
 	   	$(e).closest("tr").find(".csPtTransactionIdEoi").after("<small class='csPtReachMexLengthEOI'>ID can be max 15 characters long.</small>"); 
		
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

function csPtcalculateGrandTotalEoi() {
	var grandTotal = 0;
    $("#csPtColEoi").find('input[name="amount"]').each(function () {
        grandTotal += +$(this).val();
    });
    $("#csPtGrandtotalEoi").text(grandTotal.toFixed(2));
}

function csPymtDataEoi () {
	
	var pageContext = $("#pageContext").val()+"/";
	
	var i = 0;
	var k = 0;
	
	
	
	$("#csPtColEoi .csPtDataRowEoi").each(function () {
		
		var formData = new FormData();
	 	formData.append('panAttachEoi', $(this).find('.panAttachEoi')[0].files[0]);
	 	formData.append('receiptAttachEoi', $(this).find('.receiptAttachEoi')[0].files[0]);
	 	formData.append('rowCount', i);
	 	
	 	formData.append('enqID', $('#enquirysfid').val());
	 	
		$.ajax({
			url : 'EOIPaymentFileUploadServlet',
			type : 'POST',
			data : formData,
			processData : false,
			contentType : false,
			success : function(data) {
			
			}
		});
		
		i++
	});
	
	var eoiFormPath =   'costSheetPDF/'+$('#region__c').val()+'/'+$('#marketingProjectName').val()+'/'+'EOI Details'+'/'+$('#enquiry_name').val()+'/eoi_form_'+$('#enquiry_name').val()+'.pdf';
    
	var arrayData = [];
	$("#csPtColEoi .csPtDataRowEoi").each(function () {
	    var csPtData = {};
	    csPtData.enq_sfid = $(this).find('.csPtEnqSfidEoi').val();
	    csPtData.payment_type = $(this).find('.csPtDropDownEoi').val();
	    csPtData.bank_name = $(this).find('.csPtBankNameEoi').val();
	    csPtData.branch = $(this).find('.csPtBranchEoi').val();
	    csPtData.transaction_id = $(this).find('.csPtTransactionIdEoi').val();
	    csPtData.transaction_date = $(this).find('.csPtTransactionDateEoi').val();
	    csPtData.transaction_amount = $(this).find('.csPtTransactionAmountEoi').val();
	    csPtData.description = $(this).find('.csPtDescriptionEoi').val();
	    csPtData.total_amount = $('#csPtGrandtotalEoi').text().trim();
	    csPtData.enq_sfid = $('#enquirysfid').val();
	    csPtData.project_sfid = $('#projectId').val();
	    
	    csPtData.project_name = $('#projectname').val();
	    csPtData.user_email = $('#useremailID').val();
	    csPtData.user_name = $('#username').val();
	    
	    csPtData.userid = $('#userid').val();
	    csPtData.eoi_form_path = eoiFormPath;
	    		
	    		//"Noida\Godrej North Estate, Delhi"
	    
	    csPtData.isactive = 'N';
	    
	    // csPtData.gpl_cs_balance_details_id = balance_details_primeryId.id;
	    //csPtData.offerid = balance_details_primeryId.offer_sfid;
	    
	    if($(this).find('.panAttachEoi').val() != "") {
	    	csPtData.pan_attach = k+"PAN_"+$(this).find('.panAttachEoi')[0].files[0].name;
	    }else {
	    	csPtData.pan_attach ="";
	    }
	    
	    if ($(this).find('.receiptAttachEoi').val() != "") {
	    	csPtData.cheque_attach = k+"Receipt_"+$(this).find('.receiptAttachEoi')[0].files[0].name;
	    }else {
	    	csPtData.cheque_attach ="";
	    }
	    
	    arrayData.push(csPtData);
	    k++
	});
	
	$.post(pageContext+"insertEOIPaymentDtl",{"paymentDtlJson" : JSON.stringify(arrayData)},function(data){				 
	}).done(function(data){
		getEOIPreferencPrint();
	});
}


// function call in salesRequest.js in "populateBasicInfo" function end 
function getEOITabPaymentRecord () {
	
	//$('#csPtCol tbody').empty();
	
	$('#csPtColEoi tbody tr.paymentDataPlotRow').remove();
	
	$('#csPtGrandtotalEoi').text("");
	
	$.post(pageContext+"getEOIPaymentRecord",{"enqSfid":$('#enquirysfid').val()},function(data){
		
		var html = '';
		var obj =JSON.parse(data);
		
		
		var trans_date = '';
		
		
		var panTarget = '';
		var reciptTarget = '';
		
		var eoiTransactionTotalAmount = 0;
		
		var status = '';
		var rowStatusColr = '';
		
		if(obj!=null){
			
			for(i = 0; i< obj.length; i++){    
				
				panTarget = pageContext+"file?name="+obj[i].pan_attach+"&from=EOIbookingReference&eid="+obj[i].enq_sfid+"&fid="+obj[i].pan_attach.charAt(0);
				reciptTarget = pageContext+"file?name="+obj[i].cheque_attach+"&from=EOIbookingReference&eid="+obj[i].enq_sfid+"&fid="+obj[i].cheque_attach.charAt(0);
				
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
				
				/*if (obj[i].isactive == 'N') {
					status = 'Not Approved';
					rowStatusColr = 'style="background-color: #ffd4d8;"';
				} else {
					status = 'Approved';
					rowStatusColr = '';
				}*/
				
				
				if (obj[i].isactive == 'N') {
					status = 'Not Approved';
					rowStatusColr = 'style="background-color: #ffd4d8;"';
				} else if (obj[i].isactive == 'O') {
					status = 'Approved <br> & Offer Created';
					rowStatusColr = 'style="background-color: #e7ffd1;"';
				} else if (obj[i].isactive == 'Y'){
					status = 'Approved';
					rowStatusColr = '';
				} else {
					status = 'NA';
					rowStatusColr = '';
				}
				
				
				
				html += 	'<tr class="paymentDataPlotRow" '+rowStatusColr+'>'
								+ '<td style="text-align:center;">'+status+'</td>' 
								+ '<td style="text-align:center;">'+obj[i].payment_type+'</td>' 
								+ '<td style="text-align:center;">'+obj[i].bank_name+'</td>' 
								+ '<td style="text-align:center;">'+obj[i].branch+'</td>' 
								+ '<td style="text-align:center;">'+obj[i].transaction_id+'</td>' 
								+ '<td style="text-align:center;">'+trans_date+'</td>' 
								+ '<td style="text-align:center;">'+obj[i].transaction_amount+'</td>' 
								+ '<td> <a target="_blank" href="'+panTarget+'">'+obj[i].pan_attach+'</a></td>' 
								+ '<td> <a target="_blank" href="'+reciptTarget+'">'+obj[i].cheque_attach+'</a></td>'
								+ '<td style="text-align:center;">'+obj[i].description+'</td>'
								+ '<td></td>'
							"</tr>";
			}
			
			$('#csPtGrandtotalEoi').text(eoiTransactionTotalAmount);
			
			html = html.replace(/undefined/g, "");
		
			$("#csPtColEoi tbody tr:first-child").after(html);
		}
	}).done(function(obj){
		
	});	
}




//$("#paymentDetailsTab").click(function(){
	//getTowerEOI ();
//});

function getTowerEOI (){
	var pageContext = $("#pageContext").val()+"/";
	
	$.get(pageContext+"getTowerMaster", { "project_code" : $('.projectSfid').val() 
	
	}, function(data) {
		
		$('.towerListEOI').find("option:gt(0)").remove();	
		
		var html="";
		
		html=html+'<option value="All">All</option>';
		
		for(var i=0;i<data.length;i++) {
			html=html+'<option value="'+data[i].tower_code__c+'">'+data[i].tower_name__c+'</option>';
		}
		
		$(".towerListEOI").append(html);
	});
}

//$('#tower').val(),""

function getTypologyEOI (e){
	 
	$.get("getunittype", {
		"project_code" : $('.projectSfid').val(),
		"tower_code": $(e).val(),
		"floor_code":""
	}, function(data) {
		
		$(e).closest('.EOIDtlRow').find('.typologyListEOI').find("option:gt(0)").remove();
		
		var html="";
		
		if(data!=null){
			for(var i=0;i<data.length;i++){
				
				html=html+'<option value="'+data[i].propstrength__unit_type__c+'">'+data[i].propstrength__unit_type__c+'</option>';
				
				//html=html+  "<input type='radio' id='desiredUnitType"+i+"' class='desiredUnitType' name='enquiryReport.desiredUnitType' value='"+data[i].propstrength__unit_type__c+"' >"+data[i].propstrength__unit_type__c+"</label>";
			}
		}
		$(e).closest('.EOIDtlRow').find('.typologyListEOI').append(html);
	});
}


function getUnitEOI (e){
	$.get("gethouseunit", {
		"project_code" : $('.projectSfid').val(),
		"tower_code":$(e).val(),"floor_code":"","unit":""
	}, function(data) {
		
		$(e).closest('.EOIDtlRow').find(".unitListEOI").find("option:gt(0)").remove();
		
		var html="";
		
		if(data!=null){
			for(var i=0;i<data.length;i++) {
				html=html+'<option value="'+data[i].sfid+'">'+data[i].propstrength__house_unit_no__c+'</option>';
			}
		}
		
		$(e).closest('.EOIDtlRow').find(".unitListEOI").append(html);
	});
}


function getfbandEOI(e) {
	 
	$.get("getTowerBand", {
		"project_code" : $('.projectSfid').val(),
		"tower_code":$(e).val()
	}, function(data) {

		$(e).closest('.EOIDtlRow').find(".floorListEOI").find("option:gt(0)").remove();
		
		var html="";
		
		if(data!=null){
			for(var i=0;i<data.length;i++){
				html=html+'<option value="'+data[i].name+'">'+data[i].name+'</option>';
			}
		}
		 
		$(e).closest('.EOIDtlRow').find(".floorListEOI").append(html);
		
	});
}

function unitChangeConditionEOI (e){
	if ($(e).val() != 0) {
		$(e).closest('.EOIDtlRow').find('.floorListEOI').prop("disabled", true);
		$(e).closest('.EOIDtlRow').find('.floorListEOI').html("<option value='0'>Select Floor Band</option>");
		
	} else {
		$(e).closest('.EOIDtlRow').find('.floorListEOI').prop("disabled", false);
		
		
		$.get("getTowerBand", {
			"project_code" : $('.projectSfid').val(),
			"tower_code":$(e).closest('.EOIDtlRow').find('.towerListEOI').val()
		}, function(data) {

			$(e).closest('.EOIDtlRow').find(".floorListEOI").find("option:gt(0)").remove();
			
			var html="";
			
			if(data!=null){
				for(var i=0;i<data.length;i++){
					html=html+'<option value="'+data[i].name+'">'+data[i].name+'</option>';
				}
			}
			 
			$(e).closest('.EOIDtlRow').find(".floorListEOI").append(html);
			
		});
		//getfbandEOI(e);
	}
}


function removeEOIRow (e) {
	$(e).closest("tr").remove();
	EOIDtl -= 1;
}


function addMoreEoiRowBtn () {
	
	html = '';
	
	if(EOIDtl < NumEoiDtl) {
		
		
		html += "<tr class='EOIDtlRow'>" +
				
				 "<td>"
					+" <input class='csPtEnqSfidEoi' style='display:none;' /> "
					+" <select class='full form-control input-sm towerListEOI requiredField'  onchange='getTypologyEOI(this); getUnitEOI(this); getfbandEOI(this);'>"
					+"<option value=''>Select Tower</option>"
					+"</select>"
				+"</td>"
				+"<td>"
					+"<select class='full form-control input-sm typologyListEOI'>"
						+"<option value=''>Select Typology</option>"
					+"</select>"
				+"</td>"
				+"<td>"
					+"<select class='full form-control input-sm unitListEOI' onchange='unitChangeConditionEOI(this)'>"
						+"<option value='0'>Select Unit</option>"
					+"</select>"
				+"</td>"
				+"<td>"
					+"<select class='full form-control input-sm floorListEOI'>"
						+"<option value='0'>Select Floor Band</option>"
					+"</select>"
				+"</td>"
				+"<td>"
					+"<textarea class='full form-control input-sm descriptionEOI' placeholder='Description'></textarea>"
				+"</td>"
				
				+ "<td class='txtCenter'><i onclick='removeEOIRow(this)' class='fa fa-times-circle redColr cursorPoint'></i></td> </tr>" ;
				
				$('#EOIMultipleTable tr:last-child').after(html);
				
				
				var k = ".EOIDtlRow:eq("+parseInt(parseInt(EOIDtl)+parseInt(1))+")";
				
				$.get(pageContext+"getTowerMaster", { "project_code" : $('.projectSfid').val() 
					
				}, function(data) {
					
					$(k).find('.towerListEOI').find("option:gt(0)").remove();	
					
					var html="";
					
					html=html+'<option value="All">All</option>';
					
					for(var i=0;i<data.length;i++) {
						html=html+'<option value="'+data[i].tower_code__c+'">'+data[i].tower_name__c+'</option>';
					}
					
					$(k).find(".towerListEOI").append(html);
				});
				
				
				
				
		EOIDtl++;
	}else {
		swal({
			title: "You've reached the limit",
		    text: "",
		    timer: 2000,
		    type: "error",
		});
	}
}

















//Insert EOI Dtl
function insertEOIPreference () {
	swal({
		title: "Please wait, submitting the EOI ...",
		text: "",
		type: "warning",
		allowOutsideClick: false,
		showConfirmButton: false
	});
	
	var pageContext = $("#pageContext").val()+"/";
	
	var i = 0;
	var k = 0;
	
	var d = new Date();

	var month = d.getMonth()+1;
	var day = d.getDate();

	var currentDate = d.getFullYear() + '-' +
	    ((''+month).length<2 ? '0' : '') + month + '-' +
	    ((''+day).length<2 ? '0' : '') + day;

	
	var eoiFormPath =   'costSheetPDF/'+$('#region__c').val()+'/'+$('#marketingProjectName').val()+'/'+'EOI Details'+'/'+$('#enquiry_name').val()+'/eoi_form_'+$('#enquiry_name').val()+'.pdf';
	var arrayData = [];
	$("#EOIMultipleTable .EOIDtlRow").each(function () {
	    var csPtData = {};
	    
	    csPtData.request_id = $('.enquiryId').val(); 
	    csPtData.enq_sfid = $('#enquirysfid').val();
	    
	    csPtData.project_sfid = $('#projectId').val();
	    csPtData.project_name = $('#projectname').val();
	    
	    csPtData.userid = $('#userid').val();
	    csPtData.user_email = $('#useremailID').val();
	    csPtData.user_name =  $('#username').val();
	    
	    
	    $("#yourdropdownid option:selected").text();
	    
	    
	    csPtData.tower_id = $(this).find('.towerListEOI').val();
	    csPtData.tower_name = $(this).find('.towerListEOI option:selected').text();
	    
	    csPtData.typology_id = $(this).find('.typologyListEOI').val();
	    csPtData.typology_name = $(this).find('.typologyListEOI option:selected').text();
	   
	    csPtData.unit_id = $(this).find('.unitListEOI').val();
	    csPtData.unit_name = $(this).find('.unitListEOI option:selected').text();
	    
	    csPtData.floor_band = $(this).find('.floorListEOI').val();
	    
	    csPtData.description = $(this).find('.descriptionEOI').val();
	    
	    //csPtData.eoi_date = "2018-08-08";
	    
	    csPtData.createdby = $('#userid').val();
	    csPtData.updatedby = $('#userid').val();
	    
	    csPtData.isactive = "Y";
	    
	    csPtData.token_no = $('#token').val().substring(1);
	    csPtData.eoi_form_path = eoiFormPath;
	    
	    
	    
	    csPtData.eoi_date_string = currentDate;
	    
	    //-------------------------------------------
	    
	    
	    
	    arrayData.push(csPtData);
	    k++
	});
	
	$.post(pageContext+"insertEOIDtl",{"eoiDataJson" : JSON.stringify(arrayData)},function(data){				 
	}).done(function(data){
		csPymtDataEoi();
		generateKYCLink(event,this,'Y');
	});
}
//END Insert EOI Dtl
















function getEOITabPreferencRecord () {
	
	$('#EOIMultipleTable tbody tr.prefrenceDataPlotRow').remove();
	
	$.post(pageContext+"getEOITabPreferencRecord",{"enqSfid":$('#enquirysfid').val()},function(data){
		
		var html = '';
		var obj =JSON.parse(data);
		
		
		var trans_date = '';
		
		
		var panTarget = '';
		var reciptTarget = '';
		
		var eoiTransactionTotalAmount = 0;
		
		if(obj!=null){
			
			for(i = 0; i< obj.length; i++){    
				
				html += 	'<tr class="prefrenceDataPlotRow">'
								+ '<td style="text-align:center;">'+obj[i].tower_name+'</td>' 
								+ '<td style="text-align:center;">'+obj[i].typology_name+'</td>' 
								+ '<td style="text-align:center;">'+obj[i].unit_name+'</td>' 
								+ '<td style="text-align:center;">'+obj[i].floor_band+'</td>' 
								+ '<td style="text-align:center;">'+obj[i].description+'</td>' 
								+ '<td></td>' 
							"</tr>";
			}
			
			
			html = html.replace(/undefined/g, "");
		
			$("#EOIMultipleTable tbody tr:first-child").after(html);
		}
	}).done(function(obj){
		
	});	
}