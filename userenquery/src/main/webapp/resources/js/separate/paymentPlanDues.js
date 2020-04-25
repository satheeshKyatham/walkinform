
var pageContext = $("#pageContext").val()+"/";	


regionList();
/*$(document).ready(function(){

	getPymentPlanDueData();
});*/
function regionList () {
	$('#regionList').empty();	
	var urlRegionList = pageContext+"regionList?project_code=test";
	
	$.getJSON(urlRegionList, function (data) {
		$('#regionList').append('<option value="">Select</option>');
		$.each(data, function (index, value) {
			$('#regionList').append("<option value='"+value.region__c+"'>"+value.region__c+"</option>");
			$('#regionListid').append("<option value='"+value.region__c+"'>"+value.region__c+"</option>");
			
		});					
	}).done(function() {
	});
}

function projectDataList(){
	$('#projectDataList').empty();	
	var urlTower = pageContext+"projectDataList?region="+$('#regionList').val();
	$.getJSON(urlTower, function (data) {
		$('#projectDataList').append('<option value="">Select</option>');
		$.each(data, function (index, value) {
			$('#projectDataList').append("<option value='"+value.sfid+"'>"+value.name+  " / " +value.propstrength__project_code__c+ "</option>");
		});					
	}).done(function() {
		
	});
}

function projectDataForEdit(){
	$('#projectDataListId').empty();	
	var urlTower = pageContext+"projectDataList?region="+$('#regionListid').val();
	$.getJSON(urlTower, function (data) {
		$('#projectDataListId').append('<option value="">Select</option>');
		$.each(data, function (index, value) {
			$('#projectDataListId').append("<option value='"+value.sfid+"'>"+value.name+  " / " +value.propstrength__project_code__c+ "</option>");
		});					
	}).done(function() {
		
	});
}


function paymentPlanDropdown (){
	$('#ppDropdown').empty();
	
	var urlpayemntPlan = pageContext+"getpaymentPlanListData?projectcode="+$("#projectDataList").val();
	
	$.getJSON(urlpayemntPlan, function (data) {
		$('#ppDropdown').append('<option value="">Select</option>');
		$.each(data, function (index, value) {
			$('#ppDropdown').append('<option value='+value.sfid+'>'+value.name+'</option>');
		});					
	}).done(function() {
		towerList ();
		
	});
}



function paymentPlanDropdownForEdit (){
	$('#paytPlanId').empty();
	
	var urlpayemntPlan = pageContext+"getpaymentPlanListData?projectcode="+$("#projectDataListId").val();
	
	$.getJSON(urlpayemntPlan, function (data) {
		$.each(data, function (index, value) {
			$('#paytPlanId').append('<option value='+value.sfid+'>'+value.name+'</option>');
		});					
	}).done(function() {
		towerListForEdit ();
		
	});
}

function towerListForEdit (e, source) {
	$('#towername_id').empty();	
	debugger;
	var projectNameVal = $("#projectDataListId").val();
	//var urlTower = pageContext+"getTower?project_code="+projectNameVal;
	
	var urlTower = pageContext+"getTowerMaster?project_code="+projectNameVal;
	
	
	$.getJSON(urlTower, function (data) {
		$('#towername_id').append('<option name="0" value="0">Select</option>');
		$.each(data, function (index, value) {
			$('#towername_id').append("<option name='"+value.sfid+"' value='"+value.tower_code__c+"'>"+value.tower_name__c+"</option>");
		});					
	}).done(function() {
		
	});
}
function towerList (e, source) {
	$('#towerMst').empty();	
	debugger;
	var projectNameVal = $("#projectDataList").val();
	//var urlTower = pageContext+"getTower?project_code="+projectNameVal;
	
	var urlTower = pageContext+"getTowerMaster?project_code="+projectNameVal;
	
	
	$.getJSON(urlTower, function (data) {
		$('#towerMst').append('<option name="0" value="0">Select</option>');
		$.each(data, function (index, value) {
			$('#towerMst').append("<option name='"+value.sfid+"' value='"+value.tower_code__c+"'>"+value.tower_name__c+"</option>");
		});					
	}).done(function() {
		$('#paymentDueSubmit').show();
		
	});
}

function inventoryUnitTypeMst()
{
	$('#ppDropdownDiv').show();	
}

function addPaymentDue () {
	debugger
	var tower=$('#towerMst').find('option:selected').attr('name');
	var dueAmountVal=$('#due_amount').val();
	if(dueAmountVal != undefined && dueAmountVal != "" && dueAmountVal != null){
		dueAmountVal=$('#due_amount').val();
	}else {
		dueAmountVal = 0;
	} 
	
	var formData = new FormData();	
	formData.append('dues_amount' , dueAmountVal);
	formData.append('project_id' ,$('#projectDataList').val());
	formData.append("project_name",$('#projectDataList :selected').text());
	formData.append("pymt_plan_id",$('#ppDropdown').val());
	formData.append("pymt_plan_name",$('#ppDropdown :selected').text());
	formData.append("region_id",$('#regionList').val());
	formData.append("region_name",$('#regionList :selected').text());
	formData.append("towerid",tower);
	formData.append("tower_name",$('#towerMst').val());
	formData.append("payplan_milestones",$('#ppmilestone_json_id').val());
	formData.append("bookingamount",$('#booking_amount_id').val());
	formData.append("days",$('#days_id').val());
	if($('#ppmilestone_pk_id').val()!=null)
		formData.append("id",$('#ppmilestone_pk_id').val());
	var submitURL="savePaymentPlanWithDue";
	var object = {};
	formData.forEach(function(value, key){
	    object[key] = value;
	});
	var json = JSON.stringify(object);
	console.log("json",json);
	if(tower==0){
		alert('Please select tower')
	}else{
		
		$.ajax({
			url : submitURL,
			type : 'POST',
			data : json,
			processData : false, 
			contentType : 'application/json',
			
	        success: function (data) {
	        	console.log("sus",data);
	        	if(data!=null && data.insertStatus =="Status_OK"){
	        		swal({
	        			title: "Successfully Submitted",
	        		    text: "Thank you for your details.",
	        		    timer: 2000,
	        		    type: "success",
	    			});
	        		//getPymentPlanDueData();
	        		$('#projectDataList').val("");
	        		$('#projectDataList :selected').text("");
	        		$('#due_amount').val("");
	        		$('#regionList').val("");
	        		$('#regionList :selected').text("");
	        		$('#ppDropdown').val("");
	        		$('#ppDropdown :selected').text("");
	        		$('#regionList').val("");
	        		$('#regionList :selected').text("");
	        		$('#towerMst :selected').text("");
	        		$('#towerMst').val("");
	        		
	        		$('#due_amount').val("");
	        		$('#booking_amount_id').val("");
	        		$('#days_id').val("30");
	        		$('#paymentListId tbody tr.paymentPlanListRow').remove();
	        		
	        	}else{
	        		swal({
	        			title: "Something went wrong...!",
	        		    text: "",
	        		    timer: 2000,
	        		    type: "error",
	    			});
	        	}
			 
	        }
		});
		}
}


function getPymentPlanDueData()
{
	
	$('#paymentDueListId tbody tr.paymentDataPlotRow').remove();
	
	$.get(pageContext+"getPymentPlanDueList",function(data){
		
		var html = '';
		var htmlActionBtn = '';
		
		var obj =JSON.parse(data);
		var trans_date = '';
		var trxSuccess = "";
		var trxStatus = "";
		var actionBtn = "";
		
		if(obj!=null){
				$("#PaymentLinkForSales").empty();
				$("#PaymentLinkForSales").append (obj[0].request_url);
				for(i = 0; i< obj.length; i++){    
					debugger
					console.log("get",obj[i])
					var regionname=obj[i].region_name==undefined?'':obj[i].region_name;
					var projectname=obj[i].project_name==undefined?'':obj[i].project_name;
					var towername=obj[i].tower_name==undefined?'':obj[i].tower_name;
					var pymtplanname=obj[i].pymt_plan_name==undefined?'':obj[i].pymt_plan_name;
					var dueamount=obj[i].dues_amount==undefined?'':obj[i].dues_amount;
					
					if (obj[i].isactive == "A") {
						htmlActionBtn = "";
						htmlActionBtn +=  '<div>'
										+ '<button class="btn btn-primary blue_btn editPayReqBtn" onclick="editPaymentDue(this)">Edit</button>'
										+ '<button class="btn btn-primary blue_btn updatePaymentBtn" style="display:none" onclick="updatePaymentDue(this)">Update</button>'
										+ '<button class="btn btn-primary blue_btn cancelPayReqBtn" style="display:none" onclick="cancelPayReq(this)">Cancel</button>'
									+ '</div>';
					} else {
						htmlActionBtn = "";
					}
					
					
					
					html += 	'<tr class="paymentDataPlotRow '+trxSuccess+'" data-rowid = "'+obj[i].id+'">'
									+ '<td style="text-align:center;">'
										+'<span class="oldregionName">'+regionname+'</span>'
										/*+ '<input class="exisitingPayReqdate" style="display:none;" value="'+trans_date+'">'*/ 
										+'<select style="display:none;" class="form-control exisitingRegionName" id="regionListid" onchange="projectDataForEdit()"><option name="'+regionname+'" value="'+obj[i].region_id+'">'+regionname+'</option</select>'
									+'</td>'	
									+ '<td style="text-align:center;"> ' 
									+'<select style="display:none;" class="form-control existingproject" id="projectDataListId" onchange="paymentPlanDropdownForEdit()"><option name="'+projectname+'" value="'+obj[i].project_id+'">'+projectname+'</option></select>'
											+ '<span class="oldprojectlist">'+projectname+'</span>' 
										+'</td>' 
									+ '<td style="text-align:center;">'
									+'<select style="display:none;" class="form-control existingtower" id="towername_id" onchange="inventoryUnitTypeMst()"><option name="'+towername+'" value="'+obj[i].towerid+'">'+towername+'</option></select>'
											+ '<span class="oldTower">'+towername+'</span>' 
									+ '</td>'
									+ '<td style="text-align:center;">'
									+'<select  style="display:none;" class="form-control existingPaytPlan" id="paytPlanId"><option name="'+pymtplanname+'" value="'+obj[i].pymt_plan_id+'">'+pymtplanname+'</option></select>'
											+ '<span class="oldPaytPlanId">'+pymtplanname+'</span>' 
									+ '</td>'
									+ '<td style="text-align:center;">'	
									 +'<input  style="display:none;" class="existDueAmount full form-control input-sm"  id="existingDueId" value="'+dueamount+'"></input>'
											+ '<span class="oldDueAount">'+dueamount+'</span>' 
									+ '</td>'
									+ '<td class="crudRPBtn"> '
										+ htmlActionBtn
									+ '</td>' 
								"</tr>";
				}
				
				html = html.replace(/undefined/g, "");
				
				$("#paymentDueListId tbody tr:first-child").after(html);
			
		}
		
	}).done(function(obj){
		var data =JSON.parse(obj);
		$('#paymentDueListId').dataTable({
			destroy: true,
			language: {
				searchPlaceholder: "Search"
			},
			order: [[ 0, "desc" ]]
		});
		if(data!=null){
			if (data.status == "STATUS_NOTOK") {
				alert (data.error_msg);
			}
		}
		
	});
	
}

function editPaymentDue (e) {
	//alert ($(e).closest("td").closest("tr.paymentDataPlotRow").attr("data-rowid"));
	
	$(e).closest("td").closest("tr.paymentDataPlotRow").addClass("eidtPayRow");
	$("p:first").addClass("intro");
	
	var existingDes = $(e).closest("td").closest("tr").find(".existingDes").text();
	
	$(e).hide();
	$(e).closest("td").find(".deletePayReqBtn").hide();
	$(e).closest("td").find(".updatePaymentBtn").show();
	$(e).closest("td").find(".cancelPayReqBtn").show();
	$(e).closest("td").closest("tr").find(".submittedAmount").show();
	$(e).closest("td").closest("tr").find(".addedAmount").hide();
	$(e).closest("td").closest("tr").find(".editDesPR").val(existingDes);
	$(e).closest("td").closest("tr").find(".existingDes").hide();
	$(e).closest("td").closest("tr").find(".editDesPR").show();
	$(e).closest("td").closest("tr").find(".exisitingRegionName").show();
	$(e).closest("td").closest("tr").find(".existingproject").show();
	$(e).closest("td").closest("tr").find(".oldprojectlist").hide();
	$(e).closest("td").closest("tr").find(".oldTower").hide();
	$(e).closest("td").closest("tr").find(".oldregionName").hide();
	$(e).closest("td").closest("tr").find(".oldPaytPlanId").hide();
	$(e).closest("td").closest("tr").find(".oldDueAmount").hide();
	$(e).closest("td").closest("tr").find(".existingtower").show();
	$(e).closest("td").closest("tr").find(".existingPaytPlan").show();
	$(e).closest("td").closest("tr").find(".existDueAmount").show();
	
	
	
	
	
	
	
	
	regionList();
	
	
} 

function cancelPayReq (e) {
	var existingAmount = $(e).closest("td").closest("tr").find(".oldReqAmount").text();
	
	$(e).closest("td").closest("tr.paymentDataPlotRow").removeClass("eidtPayRow");
	
	$(e).hide();
	$(e).closest("td").find(".deletePayReqBtn").show();
	$(e).closest("td").find(".updatePaymentBtn").hide();
	$(e).closest("td").find(".editPayReqBtn").show();
	$(e).closest("td").closest("tr").find(".submittedAmount").val("");
	$(e).closest("td").closest("tr").find(".submittedAmount").val(existingAmount);
	$(e).closest("td").closest("tr").find(".submittedAmount").hide();
	$(e).closest("td").closest("tr").find(".oldReqAmount").show();
	$(e).closest("td").closest("tr").find(".existingDes").show();
	$(e).closest("td").closest("tr").find(".editDesPR").hide();
	$(e).closest("td").closest("tr").find(".exisitingRegionName").hide();
	$(e).closest("td").closest("tr").find(".existingproject").hide();
	$(e).closest("td").closest("tr").find(".oldprojectlist").show();
	$(e).closest("td").closest("tr").find(".oldTower").show();
	$(e).closest("td").closest("tr").find(".oldregionName").show();
	$(e).closest("td").closest("tr").find(".oldPaytPlanId").show();
	$(e).closest("td").closest("tr").find(".oldDueAmount").show();
	$(e).closest("td").closest("tr").find(".existingtower").hide();
	$(e).closest("td").closest("tr").find(".existingPaytPlan").hide();
	$(e).closest("td").closest("tr").find(".existDueAmount").hide();
}


function updatePaymentDue (e) {
debugger
	var pageContext = $("#pageContext").val()+"/";
	var i = 0;
	    var csPtData = {};
	    
	   /* csPtData.id = $(e).closest("td").closest("tr.paymentDataPlotRow").attr("data-rowid");
	    csPtData.amount = $(this).find('.submittedAmount').val();
	    csPtData.transaction_date_string = $(this).find('.exisitingPayReqdate').val();
	    csPtData.description = $(this).find('.editDesPR').val();  
	   
	    arrayData.push(csPtData);
	    k++*/
	    var id=$(e).closest("td").closest("tr.paymentDataPlotRow").attr("data-rowid");
	    var tower=$('#towername_id').find('option:selected').attr('name');
		var dueAmountVal=$('#existingDueId').val();
		if(dueAmountVal != undefined && dueAmountVal != "" && dueAmountVal != null){
			dueAmountVal=$('#existingDueId').val();
		}else {
			dueAmountVal = 0;
		} 
	    var formData = new FormData();	
	    formData.append('id' , id);
		formData.append('dues_amount' , dueAmountVal);
		formData.append('project_id' ,$('#projectDataListId').val());
		formData.append("project_name",$('#projectDataListId :selected').text());
		formData.append("pymt_plan_id",$('#paytPlanId').val());
		formData.append("pymt_plan_name",$('#paytPlanId :selected').text());
		formData.append("region_id",$('#regionListid').val());
		formData.append("region_name",$('#regionListid :selected').text());
		formData.append("towerid",$('#towername_id').val());
		formData.append("tower_name",tower);
	
	var object = {};
	formData.forEach(function(value, key){
	    object[key] = value;
	});
	var json = JSON.stringify(object);
	if(tower==0){
		alert('Please select tower')
	}else{
	$.ajax({
		url : 'updatePaymentPlanWithDue',
		type : 'POST',
		data : json,
		processData : false, 
		contentType : 'application/json',
		
        success: function (data) {
        	console.log("sus",data);
        	if(data!=null && data.insertStatus =="Status_OK"){
        		swal({
        			title: "Successfully Submitted",
        		    text: "Thank you for your details.",
        		    timer: 2000,
        		    type: "success",
    			});
        		//getPymentPlanDueData();
        	}else{
        		swal({
        			title: "Something went wrong...!",
        		    text: "",
        		    timer: 2000,
        		    type: "error",
    			});
        	}
		 
        }
	});
	}
	
}

function getPymentPlanLineItems()
{
	//alert($('#projectDataList').val());
	$('#paymentListId tbody tr.paymentPlanListRow').remove();
	//getpaymentplanlist
	$.get(pageContext+"getpaymentplanlist_due?project_sfid="+$('#projectDataList').val()+"&tower_sfid="+$('#towerMst').find('option:selected').attr('name')+"&payment_plan_sfid="+$('#ppDropdown').val()+"",function(data){
		
		var html = '';
		var htmlActionBtn = '';
		
		//var obj =JSON.parse(data);
		var trans_date = '';
		var trxSuccess = "";
		var trxStatus = "";
		var actionBtn = "";
		
		if(data!=null){
			$("#ppmilestone_pk_id").val(data.id);
			$("#due_amount").val(data.dues_amount);
			$("#booking_amount_id").val(data.bookingamount);
				//$("#paymentListIdHead").empty();
				//alert(data.paymentPlanList.length)
				//$("#PaymentLinkForSales").append (obj[0].request_url);
				for(i = 0; i< data.paymentPlanList.length; i++){    
					console.log("get",data.paymentPlanList[i])
					var percentage=data.paymentPlanList[i].Per==null?'':data.paymentPlanList[i].Per;
					var amountplan=data.paymentPlanList[i].Amount==null?'':data.paymentPlanList[i].Amount;
					/*var towername=obj[i].tower_name==undefined?'':obj[i].tower_name;
					var pymtplanname=obj[i].pymt_plan_name==undefined?'':obj[i].pymt_plan_name;
					var dueamount=obj[i].dues_amount==undefined?'':obj[i].dues_amount;*/
					
					var ischecked="";
					if(data.paymentPlanList[i].iscompleted=="Y")
						ischecked="checked";
					html += 	'<tr class="paymentPlanListRow" data-rowid = '+data.paymentPlanList[i].id+'><td class="sfid_p" style="display: none;">'+data.paymentPlanList[i].sfid+'</td><td class="id_p" style="display: none;">'+data.paymentPlanList[i].id+'</td><td class="milestone_p">'+data.paymentPlanList[i].Milestone+'</td><td class="ptPKID">'+percentage+'</td><td class="amount_p">'+amountplan+'</td><td class="order_p" style="display: none;">'+data.paymentPlanList[i].order+'</td><td><input type="checkbox" '+ischecked+' class="paymentPlanRowcheck" onchange="milestoneChecked()"></td></tr>' ;
								
				}
				
				html = html.replace(/undefined/g, "");
				
				$("#paymentListId tbody tr:first-child").after(html);
			
		}
		
	}).done(function(obj){
		$('#paymentListId').dataTable({
			destroy: true,
			language: {
				searchPlaceholder: "Search"
			},
			order: [[ 0, "desc" ]]
		});
		if(data!=null){
			if (obj.status == "STATUS_NOTOK") {
				alert (obj.error_msg);
			}
		}
		
	});
	
}
function milestoneChecked()
{
var k = 0;
var arrayData = [];
var sumPer=0;

$("#paymentListId .paymentPlanListRow").each(function () {
	if ($(this).find('.paymentPlanRowcheck').is(':checked')) {
		$(this).prevAll().find('input:checkbox').prop('checked', true);
	}
	//alert($(this).find(".milestone_p").text());
	// var csPtData = {};
     /* if ($(this).find('.paymentPlanRowcheck').is(':checked')) {
             sumPer=Number(sumPer)+Number($(this).find(".ptPKID").text());
             csPtData.id=$(this).find(".id_p").text();
             csPtData.isactive="Y";
             csPtData.iscompleted="Y";
             csPtData.per=$(this).find(".ptPKID").text();
             csPtData.totalper=sumPer;
             csPtData.name=$(this).find(".milestone_p").text();
             csPtData.sfid=$(this).find(".sfid_p").text();
             csPtData.amount=$(this).find(".amount_p").text();
             csPtData.order=$(this).find(".order_p").text();
             arrayData.push(csPtData);
             
             k++
             return false;
      }*/
     /* else 
    	  {
    	  	sumPer=Number(sumPer)+Number($(this).find(".ptPKID").text());
    	  	csPtData.id=$(this).find(".id_p").text();
            csPtData.isactive="Y";
            csPtData.iscompleted="Y";
            csPtData.per=$(this).find(".ptPKID").text();
            csPtData.totalper=sumPer;
            csPtData.name=$(this).find(".milestone_p").text();
            csPtData.sfid=$(this).find(".sfid_p").text();
            csPtData.amount=$(this).find(".amount_p").text();
            csPtData.order=$(this).find(".order_p").text();
            arrayData.push(csPtData);
    	  }*/
     
});

$("#paymentListId .paymentPlanListRow").each(function () {
	//alert("saf");
	var csPtData = {};
     if ($(this).find('.paymentPlanRowcheck').is(':checked')) {
            sumPer=Number(sumPer)+Number($(this).find(".ptPKID").text());
            csPtData.id=$(this).find(".id_p").text();
            csPtData.isactive="Y";
            csPtData.iscompleted="Y";
            csPtData.per=$(this).find(".ptPKID").text();
            csPtData.totalper=sumPer;
            csPtData.name=$(this).find(".milestone_p").text();
            csPtData.sfid=$(this).find(".sfid_p").text();
            csPtData.amount=$(this).find(".amount_p").text();
            csPtData.order=$(this).find(".order_p").text();
            arrayData.push(csPtData);
            
            k++
           // return false;
     }
     else 
   	  {
   	  	//sumPer=Number(sumPer)+Number($(this).find(".ptPKID").text());
   	  	csPtData.id=$(this).find(".id_p").text();
           csPtData.isactive="Y";
           csPtData.iscompleted="N";
           csPtData.per=$(this).find(".ptPKID").text();
//           csPtData.totalper=sumPer;
           csPtData.totalper=0;
           csPtData.name=$(this).find(".milestone_p").text();
           csPtData.sfid=$(this).find(".sfid_p").text();
           csPtData.amount=$(this).find(".amount_p").text();
           csPtData.order=$(this).find(".order_p").text();
           arrayData.push(csPtData);
   	  }
});
$('#due_amount').val(sumPer);
//alert(sumPer);
//alert(arrayData);
$('#ppmilestone_json_id').val(JSON.stringify(arrayData));
}