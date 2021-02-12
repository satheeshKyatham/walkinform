$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});
var amIEoi = 0;
var amNumEoi = 9; 


var EOIDtl = 0;
var NumEoiDtl = 4; 



function addMorePtBtnEoi () {
	if(amIEoi < amNumEoi) {
		$('#csPtColEoi tr:last-child').after('<tr class="csPtDataRowEoi"> <td></td> <td><input class="csPtEnqSfidEoi" style="display:none;" value="'+$('#enquirysfid').val()+'"/>  <select onchange="csPtDdEoi(this)" class="full form-control input-sm csPtDropDownEoi requiredField"><option value="">Select</option><option value="Cheque">Cheque</option><option value="NEFT">NEFT/Credit</option><option value="Swipe">Swipe</option><option value="Wire Transfer">Wire Transfer (PayZap, Google Pay)</option> </select></td><td><input class="full form-control input-sm csPtBankNameEoi requiredField" placeholder="Bank Name"/></td><td style="display:none;"><input class="full form-control input-sm csPtBranchEoi" placeholder="Branch Name"/></td><td><input class="full form-control input-sm csPtTransactionIdEoi requiredField checkDuplicate" placeholder="Transaction ID" /></td><td><input type="date" class="form-control input-sm csPtTransactionDateEoi requiredField" placeholder="Transaction Date"/></td><td><input maxlength="13" class="numericWithDecimal  numericField full form-control input-sm csPtTransactionAmountEoi requiredField" onkeyup="csPtcalculateGrandTotalEoi()" placeholder="Transaction Amount" name="amount"/></td> <td style="display:none;"> <input type="file" class="full form-control input-sm panAttachEoi"  accept="application/pdf,image/*"> </td> <td> <input type="file" class="full form-control input-sm receiptAttachEoi" accept="application/pdf,image/*"/>  <td><textarea class="full form-control input-sm csPtDescriptionEoi" placeholder="Description"></textarea></td><td class="removeCsPtColEoi txtCenter"><i onclick="removeCsPtColEoi(this)" class="fa fa-times-circle redColr cursorPoint"></i></td></tr>');
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
	    
	    csPtData.refund_trx_no = 0;
	    
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
		//holdUnitForEOI('eoi_block');
		getEOIPreferencPrint("SALESTAB");
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
			
			$("#eoiPaymentFirstRow").html('<i onclick="removeCsPtColEoi(this)" class="fa fa-times-circle redColr cursorPoint"></i>');
			
			for(i = 0; i< obj.length; i++){    
				
				if (obj[i].pan_attach != undefined){
					panTarget = pageContext+"file?name="+obj[i].pan_attach+"&from=EOIbookingReference&eid="+obj[i].enq_sfid+"&fid="+obj[i].pan_attach.charAt(0);
				} else {
					panTarget = "";
				}
				
				if (obj[i].cheque_attach != undefined){
					reciptTarget = pageContext+"file?name="+obj[i].cheque_attach+"&from=EOIbookingReference&eid="+obj[i].enq_sfid+"&fid="+obj[i].cheque_attach.substring(0, obj[i].cheque_attach.indexOf("Receipt_"));
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
								+ '<td style="text-align:center; font-size:11px;">'+status+'</td>' 
								+ '<td style="text-align:center;">'+obj[i].payment_type+'</td>' 
								+ '<td style="text-align:center;">'+obj[i].bank_name+'</td>' 
								/*+ '<td style="text-align:center;">'+obj[i].branch+'</td>' */
								+ '<td style="text-align:center;">'+obj[i].transaction_id+'</td>' 
								+ '<td style="text-align:center;">'+trans_date+'</td>' 
								+ '<td style="text-align:center;">'+obj[i].transaction_amount+'</td>' 
								+ '<td style="display:none;"> <a target="_blank" href="'+panTarget+'">'+obj[i].pan_attach+'</a></td>' 
								+ '<td style="word-break: break-word;"> <a target="_blank" href="'+reciptTarget+'">'+obj[i].cheque_attach+'</a></td>'
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




$("#paymentDetailsTab").click(function(){
	getTowerEOI ();
	getTokenTypeEOI();
});

function getTowerEOI (){
	$.get("getTowerMaster", { "project_code" : $('.projectSfid').val() 
	
	}, function(data) {
		
		$('.towerListEOI').find("option:gt(0)").remove();	
		
		var html="";
		
		html=html+'<option value="All">All</option>';
		
		for(var i=0;i<data.length;i++) {
			html=html+'<option value="'+data[i].tower_code__c+'">'+data[i].tower_name__c+'</option>';
		}
		
		var customTower = towerCustomList ();
		if (customTower != "") {
			html = towerCustomList ();
		}
		
		$(".towerListEOI").append(html);
	});
}

/* Added By Satheesh K - 30-01-2020*/
function getTokenTypeEOI (){
	/*$.get("getTowerMaster", { "project_code" : $('.projectSfid').val() 
	
	}, function(data) {
		
		$('.towerListEOI').find("option:gt(0)").remove();	
		
		var html="";
		
		html=html+'<option value="All">All11</option>';
		
		for(var i=0;i<data.length;i++) {
			html=html+'<option value="'+data[i].tower_code__c+'">'+data[i].tower_name__c+'</option>';
		}
		
		$(".towerListEOI").append(html);
	});*/
	$('.tokenTypeEOI').find("option:gt(0)").remove();
	var html="";
	if($("#projectsfid").val()=='a1l6F000009D6IMQA0')
		{
			html='<option value="F">GOLD</option><option value="F">PLATINUM</option><option value="T">EXPRESS</option>';
		}
	else if($("#projectsfid").val()=='a1l2s000000PJpLAAW' || $("#projectsfid").val()=='a1l2s000000PJPmAAO' || $("#projectsfid").val()=='a1l6F000004RvPHQA0' || $("#projectsfid").val()=='a1l6F000004LVk8QAG' || $("#projectsfid").val()=='a1l6F000008DnniQAC' || $("#projectsfid").val()=='a1l2s00000000pEAAQ' || $("#projectsfid").val()=='a1l6F000008iZJMQA2' || $("#projectsfid").val()=='a1l2s00000003BMAAY' || $("#projectsfid").val()=='a1l2s00000003VlAAI')
		{
			html='<option value="F">REFUNDABLE</option><option value="T">NON-REFUNDABLE</option>';
		}
	else if($("#projectsfid").val()=='a1l2s000000XmaMAAS')
	{
		html='<option value="F">NON-REFUNDABLE</option>';
	}
	else if($("#projectsfid").val()=='a1l2s00000003lPAAQ' || $("#projectsfid").val()=='a1l2s000000PK3IAAW')
	{
		html='<option value="F">PLATINUM</option><option value="T">EXPRESS</option>';
	} else if ($("#projectsfid").val()=='a1l6F000002X6IOQA0') {
		html='<option value="F">REFUNDABLE</option>';
	} else if($("#projectsfid").val()=='a1l2s000000XoezAAC') {
		html='<option value="F">PLATINUM</option><option value="T">EXPRESS</option>';
	} else if($("#projectsfid").val()=='a1l2s000000PJMJAA4') {
		html='<option value="F">GOLD</option><option value="T">PLATINUM</option><option value="T">EXPRESS</option>';
	} else if($("#projectsfid").val()=='a1l2s00000002YZAAY') {
		html='<option value="F">PLATINUM</option>';
	} else if ($("#projectsfid").val()=='a1l6F000003TRcCQAW') {
		html='<option value="F">SERENITY</option>';
	}
	
	
	
	//a1l2s00000003BMAAY
	$(".tokenTypeEOI").append(html);
}
/* Added By Satheesh K - 30-01-2020*/

function tokenTypeChangeEOI(e)
{
	debugger;
	//$(e).find("option:selected").text();
	var val = $(e).val();
	if(val=='F')
		{
		$('.unitListEOI option[value=0]').prop('selected', true);
		$('.floorListEOI').prop("disabled", false);
		
		//$('.unitListEOI').find("option:selected").text('Select Unit');
		$('.unitListEOI').prop("disabled", true);
		}
	else
		{
		$('.unitListEOI').prop("disabled", false);
		}
	
}
//$('#tower').val(),""

function getTypologyEOI (e){
	 
	if ($('.projectSfid').val() == "a1l2s00000002YZAAY") {
		var html = "";
		
		$(e).closest('.EOIDtlRow').find('.typologyListEOI').find("option:gt(0)").remove();
		$(e).closest('.EOIDtlRow').find('.unitListEOI').find("option:gt(0)").remove();
		
		
		if ($(e).closest('.EOIDtlRow').find('.towerListEOI  option:selected').val() == "SC01") {
			html = "<option value='2 BHK'>2 BHK</option>";
		} else if ($(e).closest('.EOIDtlRow').find('.towerListEOI  option:selected').val() == "SC02") {
			html = "<option value='3 BHK'>3 BHK</option>";
		} else if ($(e).closest('.EOIDtlRow').find('.towerListEOI  option:selected').val() == "SC03") {
			html = "<option value='3 BHK'>3 BHK</option>";
		}
		
		
		$(e).closest('.EOIDtlRow').find('.typologyListEOI').append(html);
	} else if ($('.projectSfid').val() == "a1l6F000003TRcCQAW" && $(e).closest('.EOIDtlRow').find('.towerListEOI  option:selected').val() != "SO03") {
		var html = "";
		
		$(e).closest('.EOIDtlRow').find('.typologyListEOI').find("option:gt(0)").remove();
		$(e).closest('.EOIDtlRow').find('.unitListEOI').find("option:gt(0)").remove();
		
		
		if ($(e).closest('.EOIDtlRow').find('.towerListEOI  option:selected').val() == "SO04") {
			html = html+"<option value='2BHK (Type-A)'>2BHK (Type-A)</option>";
			html = html+"<option value='2BHK (Type-C)'>2BHK (Type-C)</option>";
			html = html+"<option value='3BHK'>3BHK</option>";
		} else if ($(e).closest('.EOIDtlRow').find('.towerListEOI  option:selected').val() == "SO10") {
			html = html+"<option value='2BHK (Type-A)'>2BHK (Type-A)</option>";
			html = html+"<option value='2BHK (Type-C)'>2BHK (Type-C)</option>";
			html = html+"<option value='3BHK'>3BHK</option>";
		} 
		
		$(e).closest('.EOIDtlRow').find('.typologyListEOI').append(html);
	}else {
		$.get("getunittype", {
			"project_code" : $('.projectSfid').val(),
			"tower_code": $(e).val(),
			"floor_code":""
		}, function(data) {
			
			$(e).closest('.EOIDtlRow').find('.typologyListEOI').find("option:gt(0)").remove();
			$(e).closest('.EOIDtlRow').find('.unitListEOI').find("option:gt(0)").remove();
			
			var html="";
			
			if(data!=null){
				for(var i=0;i<data.length;i++){
					
					html=html+'<option value="'+data[i].propstrength__unit_type__c+'">'+data[i].propstrength__unit_type__c+'</option>';
				}
			}
			$(e).closest('.EOIDtlRow').find('.typologyListEOI').append(html);
		});
	}
	
	
}
/*
function getUnitEOI (e) {
	 
	$.post(pageContext+"getInventoryRecords",{"project_code" : $('.projectSfid').val(),"tower_code":$(e).val(),"floor_code":"","unit":""},function(data){				 
		
	}).done(function(data){
		alert (data);
		
		var obj =JSON.parse(data);
		var html="";
		
		alert ("obj ::: " + obj)
		$(e).closest('.EOIDtlRow').find(".unitListEOI").find("option:gt(0)").remove();
		if(obj!=null){
			for(var i=0;i<obj.length;i++){
				html=html+'<option value="'+obj[i].sfid+'">'+obj[i].propstrength__house_unit_no__c+'</option>';
			}
		}
		$(e).closest('.EOIDtlRow').find(".unitListEOI").append(html);
	});	
}*/



function getUnitEOI (e) {
	$(e).closest('.EOIDtlRow').find(".unitListEOI").find("option:gt(0)").remove();
	
	$(e).closest('.EOIDtlRow').find('.smapleUnit').html("");
	
	var typologyListEOI = 0;
	
	if ($(e).closest('.EOIDtlRow').find(".typologyListEOI").val().trim() != "") {
		typologyListEOI = $(e).closest('.EOIDtlRow').find(".typologyListEOI").val();
	}
	
	
	
	$.get("getInventoryRecords",{"project_code" : $('.projectSfid').val(),  "tower_code":$(e).closest('.EOIDtlRow').find(".towerListEOI").val(),  "unitType":typologyListEOI},function(data){				 
		var obj =JSON.stringify(data);
		var obj1 =JSON.parse(obj);
		var html = '';
		var wing = "";
		
		if(obj1!=null) {
			for(var i=0;i<obj1.length;i++){
				if (obj1[i].eoi_unit_locked != true && obj1[i].hold_status != true) {
					if (obj1[i].wing_block__c != undefined) {
						wing = obj1[i].wing_block__c + " - ";
                    } else {
                 	   	wing = "";
                    }
					html += '<option value="'+obj1[i].sfid+'">'+ wing + obj1[i].propstrength__house_unit_no__c+'</option>';
				}
				
			}
			
			$(e).closest('.EOIDtlRow').find(".unitListEOI").append(html);
			
			
		} else {
			//alert ("Data not found");
		}
	}).done(function(data){
		
	});	
}








/*
function getUnitEOI (e){
	$.get("getInventoryRecords", {
	
		"project_code" : $('.projectSfid').val(),
		"tower_code":$(e).val(),"floor_code":"","unit":""
	}, function(data) {
		var obj =JSON.parse(data);
		$(e).closest('.EOIDtlRow').find(".unitListEOI").find("option:gt(0)").remove();
		
		var html="";
		
		if(obj!=null){
			for(var i=0;i<obj.length;i++) {
					
					html=html+'<option value="'+obj[i].sfid+'">'+obj[i].propstrength__house_unit_no__c+'</option>';
			}
		}
		
		$(e).closest('.EOIDtlRow').find(".unitListEOI").append(html);
	});
}*/


function getfbandEOI(e) {
	
	//Changing for Plot Faridabad Project
	//Added By Satheesh K - 25-09-2020
	if($('.projectSfid').val()=="a1l2s000000XmaMAAS")
		{
		if($.trim($(e).closest('.EOIDtlRow').find(".typologyListEOI").val())!='')
			{
				var concertArray = [
					{towername:"Godrej Retreat Vista", range:"Plot_104-119", streetname:"Amaryllis Street"},
					{towername:"Godrej Retreat Vista", range:"Plot_150-159", streetname:"Amaryllis Street"},
					{towername:"Godrej Retreat Orchard", range:"Plot_160-169", streetname:"Berry Garden Street 1"},
					{towername:"Godrej Retreat Orchard", range:"Plot_160-169", streetname:"Berry Garden Street 2"},
					{towername:"Godrej Retreat Orchard", range:"Plot_140-149", streetname:"Canna Street"},
					{towername:"Godrej Retreat Orchard", range:"Plot_120-129", streetname:"Copperpod Street"},
					{towername:"Godrej Retreat Vibe", range:"Plot_104-119", streetname:"Duranta Street"},
					{towername:"Godrej Retreat Vibe", range:"Plot_170-179", streetname:"Ficus Street"},
					{towername:"Godrej Retreat Aroma", range:"Plot_104-119", streetname:"Fragrance Park Street 1"},
					{towername:"Godrej Retreat Aroma", range:"Plot_104-119", streetname:"Fragrance Park Street 2"},
					{towername:"Godrej Retreat Aroma", range:"Plot_130-139", streetname:"Fragrance Park Street 2"},
					{towername:"Godrej Retreat Orchard", range:"Plot_130-139", streetname:"Galphimia Street"},
					{towername:"Godrej Retreat Orchard", range:"Plot_140-149", streetname:"Galphimia Street"},
					{towername:"Godrej Retreat Vista", range:"Plot_104-119", streetname:"Hibiscus Street"},
					{towername:"Godrej Retreat Vista", range:"Plot_170-179", streetname:"Hibiscus Street"},
					{towername:"Godrej Retreat Vista", range:"Plot_120-129", streetname:"Hues Park Street"},
					{towername:"Godrej Retreat Symphony", range:"Plot_140-149", streetname:"Iris Street"},
					{towername:"Godrej Retreat Symphony", range:"Plot_170-179", streetname:"Iris Street"},
					{towername:"Godrej Retreat Aroma", range:"Plot_120-129", streetname:"Jacaranda Street"},
					{towername:"Godrej Retreat Aroma", range:"Plot_140-149", streetname:"Jacaranda Street"},
					{towername:"Godrej Retreat Aroma", range:"Plot_150-159", streetname:"Jacaranda Street"},
					{towername:"Godrej Retreat Orchard", range:"Plot_160-169", streetname:"Lantana Street"},
					{towername:"Godrej Retreat Symphony", range:"Plot_104-119", streetname:"Lily Street"},
					{towername:"Godrej Retreat Symphony", range:"Plot_150-159", streetname:"Lily Street"},
					{towername:"Godrej Retreat Symphony", range:"Plot_170-179", streetname:"Lily Street"},
					{towername:"Godrej Retreat Aroma", range:"Plot_130-139", streetname:"Orchid Street"},
					{towername:"Godrej Retreat Aroma", range:"Plot_140-149", streetname:"Orchid Street"},
					{towername:"Godrej Retreat Aroma", range:"Plot_170-179", streetname:"Orchid Street"},
					{towername:"Godrej Retreat Vibe", range:"Plot_120-129", streetname:"Pilkhan Street"},
					{towername:"Godrej Retreat Vibe", range:"Plot_130-139", streetname:"Pilkhan Street"},
					{towername:"Godrej Retreat Aroma", range:"Plot_150-159", streetname:"Plumbago Street"},
					{towername:"Godrej Retreat Aroma", range:"Plot_160-169", streetname:"Plumbago Street"},
					{towername:"Godrej Retreat Aroma", range:"Plot_170-179", streetname:"Plumbago Street"},
					{towername:"Godrej Retreat Symphony", range:"Plot_170-179", streetname:"Ripple Park Street 1"},
					{towername:"Godrej Retreat Vista", range:"Plot_104-119", streetname:"Semal Street"},
					{towername:"Godrej Retreat Vibe", range:"Plot_140-149", streetname:"Sheesham Street"},
					{towername:"Godrej Retreat Symphony", range:"Plot_104-119", streetname:"Vinca Street"},
					{towername:"Godrej Plots_Parcel C", range:"Plot_104-119", streetname:"Amaryllis Street Test"},
					{towername:"Godrej Plots_Parcel C", range:"Plot_104-119", streetname:"Amaryllis Street 2"}

				];
				var html="";
				for(var i = 0; i < concertArray.length; i++) 
				{
					var fullname = concertArray[i].range+concertArray[i].towername;
					var inputfullname = $(e).closest('.EOIDtlRow').find(".typologyListEOI").val()+$(".towerListEOI  option:selected").text();
					if(fullname==inputfullname)
					{
					    $(e).closest('.EOIDtlRow').find(".floorListEOI").find("option:gt(0)").remove();
						var valName = concertArray[i].towername+"-"+concertArray[i].range+"-"+concertArray[i].streetname;
						html=html+'<option value="'+valName+'">'+concertArray[i].streetname+'</option>';
						 
						
					 }
				
				}
				$(e).closest('.EOIDtlRow').find(".floorListEOI").append(html);
			}
		}
	else
		{
			$.get("getTowerBand", {
				"project_code" : $('.projectSfid').val(),
				//"tower_code":$(e).val()
				"tower_code":$(e).closest('.EOIDtlRow').find(".towerListEOI").val()
				
			
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
	
}



function unitChangeConditionEOI (e){
	if ($(e).val() != 0) {
		$(e).closest('.EOIDtlRow').find('.floorListEOI').prop("disabled", true);
		//$(e).closest('.EOIDtlRow').find('.floorListEOI').html("<option value='0'>Select Floor Band</option>");
		$(e).closest('.EOIDtlRow').find('.floorListEOI').html("<option value=''>Select Floor Band</option>");
		
		$(e).closest('.EOIDtlRow').find('.smapleUnit').html("");
		
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
					+" <select class='full form-control input-sm towerListEOI requiredField'  onchange='getTypologyEOI(this); getUnitEOI(this); getfbandEOI(this); getCarparkEOIMst(this);'>"
					+"<option value=''>Select Tower</option>"
					+"</select>"
				+"</td>"
				+"<td>"
					+"<select class='full form-control input-sm typologyListEOI requiredField' onchange='getUnitEOI(this); getfbandEOI(this);'>"
						+"<option value=''>Select Typology</option>"
					+"</select>"
				+"</td>"
				+"<td>"
					+"<select class='full form-control input-sm unitListEOI' onchange='unitChangeConditionEOI(this)'>"
						+"<option value='0'>Select Unit</option>"
					+"</select>" +
					" <div class='text-center'><span class='smapleUnit'></span></div> "
				+"</td>"
				+"<td>"
					+"<select class='full form-control input-sm floorListEOI requiredField' onchange='getSampleUnit(this);'>"
						/*+"<option value='0'>Select Floor Band</option>"*/
						+"<option value=''>Select Floor Band</option>"
					+"</select>"
				+"</td>"
				+"<td>"
					+"<select class='full form-control input-sm carparkListEOI'>"
						+"<option data-carparkname='' value='-1'>Select Car Park</option>"
					+"</select>"
				+"</td>"
				+"<td>"
					+"<textarea class='full form-control input-sm descriptionEOI' placeholder='Description'></textarea>"
				+"</td>"
				
				+ "<td class='txtCenter'><i onclick='removeEOIRow(this)' class='fa fa-times-circle redColr cursorPoint'></i></td> </tr>" ;
				
				$('#EOIMultipleTable tr:last-child').after(html);
				
				
				var k = ".EOIDtlRow:eq("+parseInt(parseInt(EOIDtl)+parseInt(1))+")";
				
				$.get("getTowerMaster", { "project_code" : $('.projectSfid').val() 
					
				}, function(data) {
					
					$(k).find('.towerListEOI').find("option:gt(0)").remove();	
					
					var html="";
					
					html=html+'<option value="All">All</option>';
					
					for(var i=0;i<data.length;i++) {
						html=html+'<option value="'+data[i].tower_code__c+'">'+data[i].tower_name__c+'</option>';
					}
					
					var customTower = towerCustomList ();
					if (customTower != "") {
						html = towerCustomList ();
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
	    
	    //csPtData.token_no = $('#token').val().substring(1);
	    csPtData.token_no = $('#token').val().replace(/[^0-9]/gi, '');
	    
	    csPtData.eoi_form_path = eoiFormPath;
	    
	    
	    csPtData.eoi_carpark_name =  $(this).find('.carparkListEOI option:selected').attr("data-carparkname");
	    csPtData.eoi_carpark_mst_id =  $(this).find('.carparkListEOI option:selected').val();
	   
	    csPtData.sample_unit_name = $(this).find('.smapleUnit').text();
	    
	    csPtData.eoi_date_string = currentDate;
	    
	    //-------------------------------------------
	    /* Added By Satheesh K - 30-01-2020*/
		var val = $('.tokenTypeEOI').val();
		if(val=='F')
			csPtData.tokenTypeId = "1";
		else
			csPtData.tokenTypeId = "2";
	    csPtData.tokenTypeName = $('.tokenTypeEOI').find("option:selected").text();
	    
	    
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
			
			$("#preferenceFirstRow").html('<i onclick="removeEOIRow(this)" class="fa fa-times-circle redColr cursorPoint"></i>');
			
			for(i = 0; i< obj.length; i++){    
				
				html += 	'<tr class="prefrenceDataPlotRow">'
								+ '<td style="text-align:center;">'+obj[i].tower_name+'</td>' 
								+ '<td style="text-align:center;">'+obj[i].typology_name+'</td>' 
								+ '<td style="text-align:center;">'+obj[i].unit_name+'<div>'+obj[i].sample_unit_name+'</div></td>' 
								+ '<td style="text-align:center;">'+obj[i].floor_band+'</td>' 
								+ '<td style="text-align:center;">'+obj[i].eoi_carpark_name+'</td>'
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



function holdUnitForEOI(msg) {
	var favorite = [];
	//var unitNameArray = [];
	
	$.each($("#EOIMultipleTable .unitListEOI"), function(){ 
		if ($(this).val() != 0) {
			favorite.push($(this).val());
			//unitNameArray.push($(this).find('option:selected').text());
		}	
	});
	
	if(favorite != '' && favorite != null){
		/*swal({
			title: "EOI submitting without any unit preference",
		    text: "",
		    //timer: 3000,
		    type: "warning",
		});*/
		//return false;
		
		$.ajax({
		    url: pageContext+'saveAdminUnit',
		    data: {
				projectid : $("#projectid").val(),
				userId : $("#userid").val(),
				unitsfid:favorite.join(","),
				holdmsg:msg,
				reasonInput : "EOI Block",
				holdBlockBehalfOfName : $('#username').val(),
				holdBlockBehalfOfID : $("#userid").val(),
				enqSFID : $('#enquirysfid').val(),
				//unitNames:unitNameArray.join(","),
		    },
		    type: 'POST',
		    success: function(data) { 
			    	if (data == 'duplicateRecords') {
			    		swal({
	                    	title: "Unit is already block",
		          			text: "",
		          			//timer: 8000,
		          			type: "warning",
		                });
			    	} else if (data == 'success')  {
			    		insertEOIPreference ();
			    	}
		    },
		    error: function(data) {
		    	swal({
					title: "There was problem in holding EOI unit at this time. Please try again",
				    text: "",
				    //timer: 3000,
				    type: "warning",
				});
		    }
		});
	} else {
		insertEOIPreference ();
	}
	
}



function getCarparkEOIMst(e) {
	 
	$.get("getCarparkEOIMst", {
		"project_sfid" : $('.projectSfid').val()
	}, function(data) {

		$(e).closest('.EOIDtlRow').find(".carparkListEOI").find("option:gt(0)").remove();
		
		var html="";
		
		if(data!=null){
			for(var i=0;i<data.length;i++){
				html=html+'<option data-carparkname="'+data[i].name+'"  value="'+data[i].id+'">'+data[i].name+'</option>';
			}
		}
		 
		$(e).closest('.EOIDtlRow').find(".carparkListEOI").append(html);
		
	});
}













function getSampleUnit (e) {
	
	var towerCode = $(e).closest('.EOIDtlRow').find(".towerListEOI option:selected").val();
	var typology = $(e).closest('.EOIDtlRow').find(".typologyListEOI option:selected").val();
	var floorBand = $(e).closest('.EOIDtlRow').find(".floorListEOI option:selected").val();
	 
	
	$.post(pageContext+"getSampleUnit",{"projectSFID":$('#projectId').val(), "towerCode":towerCode, "typology":typology, "floorBand":floorBand },function(data){
		
		var obj =JSON.parse(data);
		
		var smapleUnit = "";
		
		if(obj!=null){
			smapleUnit = obj.sample_unit_name;
		} else {
			smapleUnit = "";
		}
		
		$(e).closest('.EOIDtlRow').find(".smapleUnit").text(smapleUnit); 
		
	}).done(function(obj){
		/*if (obj!="null") {
			alert ("Record Found");
		} else {
			alert ("No Record Found");
		}*/	
	});	
}