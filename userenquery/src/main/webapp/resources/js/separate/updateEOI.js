$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});

var enqSFIDforEOI = '';

function enqDtlForAdminEOI () {
	var enqName = "ENQ - " + $('#enqNameInputEOI').val().trim();
	
	$.post(pageContext+"getEnqForAdminInventoryHold",{"enqName":enqName, "projectSFID":$('#projectid').val()},function(data){                      
		$("#enqDtlTableEOI tbody").empty();
		var obj =JSON.parse(data);
		 var html = '';
         if (obj != null) {
        	 for(var i=0;i<obj.length;i++){
        		 html += "<tr>" +
        		 	" <td>"+obj[i].enq_name+"</td>" +
					" <td>"+obj[i].mobile__c+"</td>" +
					" <td>"+obj[i].name+"</td>" +
				" </tr>";
        		 enqSFIDforEOI = obj[i].enq_sfid;
        	 }
        	 $("#enqDtlTableEOI tbody").append(html);
         } else {
        	 enqSFIDforEOI = '';
        	 $("#enqDtlTableEOI tbody").append("<tr><td colspan='3'>No records found</td></tr>");
         }
	}).done(function(data){
		if (enqSFIDforEOI != ''){
			getEOITabPaymentRecord (); getEOITabPreferencRecord();
		}
	}).fail(function(xhr, status, error) {
		alert (error);
    });
}

//---------------------------------------------------------------//

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

// function call in salesRequest.js in "populateBasicInfo" function end 
function getEOITabPaymentRecord () {
	$('#csPtColEoi tbody tr.paymentDataPlotRow').remove();
	$('#csPtGrandtotalEoi').text("");
	$.post(pageContext+"getEOIPaymentRecord",{"enqSfid":enqSFIDforEOI},function(data){
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
		
		$(".towerListEOI").append(html);
	});
}

/* Added By Satheesh K - 30-01-2020*/
function getTokenTypeEOI (){
	$('.tokenTypeEOI').find("option:gt(0)").remove();
	var html="";
	if($("#projectsfid").val()=='a1l6F000009D6IMQA0')
		{
			html='<option value="F">GOLD</option><option value="F">PLATINUM</option><option value="T">EXPRESS</option>';
		}
	else if($("#projectsfid").val()=='a1l6F000004LVk8QAG' || $("#projectsfid").val()=='a1l6F000008DnniQAC' || $("#projectsfid").val()=='a1l2s00000000pEAAQ' || $("#projectsfid").val()=='a1l6F000008iZJMQA2' || $("#projectsfid").val()=='a1l2s00000003BMAAY' || $("#projectsfid").val()=='a1l2s00000003VlAAI')
		{
			html='<option value="F">REFUNDABLE</option><option value="T">NON-REFUNDABLE</option>';
		}
	else if($("#projectsfid").val()=='a1l2s00000003lPAAQ')
	{
		html='<option value="F">PLATINUM</option><option value="T">EXPRESS</option>';
	}
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

function getTypologyEOI (e){
	 
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
				
				//html=html+  "<input type='radio' id='desiredUnitType"+i+"' class='desiredUnitType' name='enquiryReport.desiredUnitType' value='"+data[i].propstrength__unit_type__c+"' >"+data[i].propstrength__unit_type__c+"</label>";
			}
		}
		$(e).closest('.EOIDtlRow').find('.typologyListEOI').append(html);
	});
}

function getUnitEOI (e) {
	$(e).closest('.EOIDtlRow').find(".unitListEOI").find("option:gt(0)").remove();
	
	var typologyListEOI = 0;
	
	if ($(e).closest('.EOIDtlRow').find(".typologyListEOI").val().trim() != "") {
		typologyListEOI = $(e).closest('.EOIDtlRow').find(".typologyListEOI").val();
	}
	
	$.get("getInventoryRecords",{"project_code" : $('.projectSfid').val(),  "tower_code":$(e).closest('.EOIDtlRow').find(".towerListEOI").val(),  "unitType":typologyListEOI},function(data){				 
		var obj =JSON.stringify(data);
		var obj1 =JSON.parse(obj);
		var html = '';
		
		if(obj1!=null) {
			for(var i=0;i<obj1.length;i++){
				if (obj1[i].eoi_unit_locked != true && obj1[i].hold_status != true) {
					html += '<option value="'+obj1[i].sfid+'">'+obj1[i].propstrength__house_unit_no__c+'</option>';
				}
				
			}
			
			$(e).closest('.EOIDtlRow').find(".unitListEOI").append(html);
			
			
		} else {
			//alert ("Data not found");
		}
	}).done(function(data){
		
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

function getEOITabPreferencRecord () {
	$('#EOIMultipleTable tbody tr.prefrenceDataPlotRow').remove();
	$.post(pageContext+"getEOITabPreferencRecord",{"enqSfid":enqSFIDforEOI},function(data){
		
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