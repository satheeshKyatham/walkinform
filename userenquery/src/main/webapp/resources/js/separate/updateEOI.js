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
function getEOITabPreferencRecord () {
	$('#EOIMultipleTable tbody tr.prefrenceDataPlotRow').remove();
	$.post(pageContext+"getEOITabPreferencRecord",{"enqSfid":enqSFIDforEOI},function(data){
		var html = '';
		var obj =JSON.parse(data);
		var trans_date = '';
		var panTarget = '';
		var reciptTarget = '';
		var eoiTransactionTotalAmount = 0;
		var htmlActionBtn = "";
		var oldunitidVar = "";
		var oldunitnameVar = "";
		var floor_band = "";
		
		if(obj!=null){
			for(i = 0; i< obj.length; i++){    
					
				if (obj[i].unit_id != "0" && obj[i].unit_id != "") {
					oldunitidVar = obj[i].unit_id;
				} else {
					oldunitidVar = "";
				}
				
				if (obj[i].unit_name != "Select Unit" && obj[i].unit_name != "" && obj[i].unit_name != "0") {
					oldunitnameVar = obj[i].unit_name;
				} else{
					oldunitnameVar = "";
				}
				
				if (obj[i].floor_band != "0" && obj[i].floor_band != "") {
					floor_band = obj[i].floor_band;
				} else {
					floor_band = "";
				}
				
				
				
					htmlActionBtn = "";
					htmlActionBtn +=  '<div>'
									+ '<button class="btn btn-primary blue_btn editPayReqBtn" onclick="editPaymentRequest(this)">Edit</button>'
									+ '<button class="btn btn-primary blue_btn updatePaymentBtn" style="display:none; margin-bottom:10px !important;" onclick="updateEOIPreference(this)">Update</button>'
									+ '<button class="btn btn-primary blue_btn cancelPayReqBtn" style="display:none" onclick="cancelPayReq(this)">Cancel</button>'
								+ '</div>';
				html += 	'<tr class="prefrenceDataPlotRow" data-rowid = "'+obj[i].id+'">'
								+ '<td class="towerColtd"><span class="eoieditOldRec">'+obj[i].tower_name+'</span></td>' 
								+ '<td class="typologyColtd"><span class="eoieditOldRec">'+obj[i].typology_name+'</span></td>' 
								+ '<td class="unitColtd">'
										+ '<span class="oldUnitname eoieditOldRec">'+oldunitnameVar+'</span>'  
										+ '<input style="display:none;" class="oldUnitsfid" value="'+oldunitidVar+'">'
								+ '</td>' 
								+ '<td class="floorbandColtd"><span class="eoieditOldRec">'+floor_band+'</span></td>' 
								+ '<td class="eoicarparkColtd"><span class="eoieditOldRec">'+obj[i].eoi_carpark_name+'</span></td>'
								+ '<td class="eoiUnitDesColtd"><span class="eoieditOldRec">'+obj[i].description+'</span></td>' 
								+ '<td class="crudRPBtn"><span class="eoieditOldRec">'+htmlActionBtn+'</span></td>' 
							"</tr>";
			}
			html = html.replace(/undefined/g, "");
			$("#EOIMultipleTable tbody tr:first-child").after(html);
		}
	}).done(function(obj){
		
	});	
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

/*$("#paymentDetailsTab").click(function(){
	getTowerEOI ();
	getTokenTypeEOI();
});*/

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

function tokenTypeChangeEOI(e) {
	//$(e).find("option:selected").text();
	var val = $(e).val();
	if(val=='F') {
		$('.unitListEOI option[value=0]').prop('selected', true);
		$('.floorListEOI').prop("disabled", false);
		//$('.unitListEOI').find("option:selected").text('Select Unit');
		$('.unitListEOI').prop("disabled", true);
	} else {
		$('.unitListEOI').prop("disabled", false);
	}
}

function getTypologyEOI (e){
	$.get(pageContext+"getunittype", {
		"project_code" : $('#projectid').val(),
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
	if ($(e).closest('.EOIDtlRow').find(".typologyListEOI").val() != "") {
		typologyListEOI = $(e).closest('.EOIDtlRow').find(".typologyListEOI").val();
	}
	$.get(pageContext+"getInventoryRecords",{"project_code" : $('#projectid').val(),  "tower_code":$(e).closest('.EOIDtlRow').find(".towerListEOI").val(),  "unitType":typologyListEOI},function(data){				 
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
	$.get(pageContext+"getTowerBand", {
		"project_code" : $('#projectid').val(),
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
	}
}

function getCarparkEOIMst(e) {
	$.get(pageContext+"getCarparkEOIMst", {
		"project_sfid" : $('#projectid').val()
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

// --------------------- Update EOI Preference ----------------------
function updateEOIPreference (e) {
	
	var newUnitsfidVal = "";
	
	if ($(e).closest("td").closest("tr").find('.unitListEOI').val() != "0") {
		newUnitsfidVal = $(e).closest("td").closest("tr").find('.unitListEOI').val();
	}
	
	var unitsfidOldArray = [];
	var unitNameOldArray = [];
	var newUnitsfidArray = [];
	
	var unitsfidOldVal = $(e).closest("td").closest("tr").find('.oldUnitsfid').val();
	var unitnameOldVal = $(e).closest("td").closest("tr").find('.oldUnitname').val();
	//var newUnitsfidVal = $(e).closest("td").closest("tr").find('.unitListEOI').val();
	
    //$.each($("input[name='unit']:checked"), function(){ 
    unitsfidOldArray.push(unitsfidOldVal);
    unitNameOldArray.push(unitnameOldVal);
    newUnitsfidArray.push(newUnitsfidVal);
    //});

    /*if(unitsfidOldArray=='' || unitsfidOldArray==null){
		swal({
			title: "Please select the Unit",
		    text: "",
		    type: "warning",
		});
		return false;
	}*/
	
	var arrayData = [];
	//$("#EOIMultipleTable .EOIDtlRow").each(function () {
    var csPtData = {};
    
    
    //$(e).closest("td").closest("tr").find(".towerColtd").append(towerHtml);
    
    csPtData.rowid =  $(e).closest("td").closest("tr").attr("data-rowid");
    
    csPtData.tower_id =  $(e).closest("td").closest("tr").find('.towerListEOI').val();
    csPtData.tower_name =  $(e).closest("td").closest("tr").find('.towerListEOI option:selected').text();
    csPtData.typology_id =  $(e).closest("td").closest("tr").find('.typologyListEOI').val();
    csPtData.typology_name =  $(e).closest("td").closest("tr").find('.typologyListEOI option:selected').text();
    csPtData.unit_id =  $(e).closest("td").closest("tr").find('.unitListEOI').val();
    csPtData.unit_name =  $(e).closest("td").closest("tr").find('.unitListEOI option:selected').text();
    csPtData.floor_band =  $(e).closest("td").closest("tr").find('.floorListEOI').val();
    csPtData.eoi_carpark_name =   $(e).closest("td").closest("tr").find('.carparkListEOI option:selected').attr("data-carparkname");
    csPtData.eoi_carpark_mst_id =   $(e).closest("td").closest("tr").find('.carparkListEOI option:selected').val();
    csPtData.description =  $(e).closest("td").closest("tr").find('.descriptionEOI').val();
    
    arrayData.push(csPtData);
	//});
	
	$.post(pageContext+"updateEOIPreference",{"preferenceJson" : JSON.stringify(arrayData),
		"userid" : $('#userid').val(),
		"username" : $('#userNameLoggedIn').val(),
		"enq_sfid" : enqSFIDforEOI,
		"project_sfid" : $('#projectid').val(),
		"unitsfidOldArray" : unitsfidOldArray.join(","),
		"newUnitsfidArray" : newUnitsfidArray.join(",")
	},function(data){
		
	}).done(function(data){
		var obj =JSON.parse(data);
		if(obj!=null){
			 if (obj.status == "STATUS_OK") {
				 swal({
		             title: obj.error_msg,
		              text: "",
		              timer: 3000,
		              type: "success",
		         });
				 getEOITabPreferencRecord ();
			 } else if (obj.status == "STATUS_NOTOK") {
				 swal({
		             title: obj.error_msg,
		              text: "",
		              timer: 3000,
		              type: "warning",
		         });
			 }
		}
	});
}
//------------------ Update EOI Preference -----------------


/*
function holdUnitForEOI(msg) {
	var favorite = [];
	
	$.each($("#EOIMultipleTable .unitListEOI"), function(){ 
		if ($(this).val() != 0) {
			favorite.push($(this).val());
		}	
	});
	
	if(favorite != '' && favorite != null){
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
		    },
		    type: 'POST',
		    success: function(data) { 
			    	if (data == 'duplicateRecords') {
			    		swal({
	                    	title: "Unit is already block",
		          			text: "",
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
				    type: "warning",
				});
		    }
		});
	} else {
		insertEOIPreference ();
	}
}*/


// ------------------------------------ New -----------------------------------------

function editPaymentRequest (e) {
	$(e).hide();
	$(e).closest("td").find(".updatePaymentBtn").show();
	$(e).closest("td").find(".cancelPayReqBtn").show();
	
	$(e).closest("td").closest("tr").addClass("EOIDtlRow");
	
	var towerHtml = '';
	var typologyHtml = '';
	var unitHtml = '';
	var floorbandHtml = '';
	var carparkHtml = '';
	var descriptionHtml = '';
	
	towerHtml += " <select class='full form-control input-sm towerListEOI requiredField'  onchange='getTypologyEOI(this); getUnitEOI(this); getfbandEOI(this); getCarparkEOIMst(this);'>"
					+"<option value=''>Select Tower</option>"
				+"</select>"
	
	typologyHtml += "<select class='full form-control input-sm typologyListEOI requiredField' onchange='getUnitEOI(this)'>"
					+"<option value=''>Select Typology</option>"
				+"</select>"
		
	unitHtml += "<select class='full form-control input-sm unitListEOI' onchange='unitChangeConditionEOI(this)'>"
					+"<option value='0'>Select Unit</option>"
				+"</select>"
			
	floorbandHtml += "<select class='full form-control input-sm floorListEOI'>"
					+"<option value='0'>Select Floor Band</option>"
				+"</select>"
		
	carparkHtml += "<select class='full form-control input-sm carparkListEOI'>"
					+"<option data-carparkname='' value='-1'>Select Car Park</option>"
				+"</select>"
	
	descriptionHtml += "<textarea class='full form-control input-sm descriptionEOI' placeholder='Description'></textarea>"	
	
	
	$(e).closest("td").closest("tr").find(".towerColtd").append(towerHtml);
	$(e).closest("td").closest("tr").find(".typologyColtd").append(typologyHtml);
	$(e).closest("td").closest("tr").find(".unitColtd").append(unitHtml);
	$(e).closest("td").closest("tr").find(".floorbandColtd").append(floorbandHtml);
	$(e).closest("td").closest("tr").find(".eoicarparkColtd").append(carparkHtml);
	$(e).closest("td").closest("tr").find(".eoiUnitDesColtd").append(descriptionHtml);
	
	$.get(pageContext+"getTowerMaster", { "project_code" : $('#projectid').val() 
		
	}, function(data) {
		
		$(e).closest("td").closest("tr").find('.towerListEOI').find("option:gt(0)").remove();
		
		//$(k).find('.towerListEOI').find("option:gt(0)").remove();	
		
		var html="";
		
		html=html+'<option value="All">All</option>';
		
		for(var i=0;i<data.length;i++) {
			html=html+'<option value="'+data[i].tower_code__c+'">'+data[i].tower_name__c+'</option>';
		}
		
		$(e).closest("td").closest("tr").find(".towerListEOI").append(html);
	});
} 

function cancelPayReq (e) {
	$(e).hide();
	$(e).closest("td").find(".updatePaymentBtn").hide();
	$(e).closest("td").find(".editPayReqBtn").show();
	
	$(e).closest("td").closest("tr").removeClass("EOIDtlRow");
	
	$(e).closest("td").closest("tr").find(".towerColtd .towerListEOI").remove();
	$(e).closest("td").closest("tr").find(".typologyColtd .typologyListEOI").remove();
	$(e).closest("td").closest("tr").find(".unitColtd .unitListEOI").remove();
	$(e).closest("td").closest("tr").find(".floorbandColtd .floorListEOI").remove();
	$(e).closest("td").closest("tr").find(".eoicarparkColtd .carparkListEOI").remove();
	$(e).closest("td").closest("tr").find(".eoiUnitDesColtd .descriptionEOI").remove();
}