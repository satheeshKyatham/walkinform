$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});

$(document).ready(function() {
	var today = new Date();
	document.getElementById("txtAllotFromDate").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
	document.getElementById("txtAllotToDate").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
	
	if ($('#projectid').val() != "a1l2s000000XoezAAC") {
		//$("#facingSoldUnitTab").hide();
		//$("#towerSoldUnitTab").hide();
	}

});


function getAllotmentDashboardReport () {
	
	$("#allotmentReportTable").DataTable().destroy();
	
	$("#eoiReportCSVInputCol").empty();
	
	$("#eoiReportCSVInputCol").append("<input type='hidden' value='"+$('#txtAllotFromDate').val()+"' name='fromdate' />");
	$("#eoiReportCSVInputCol").append("<input type='hidden' value='"+$('#txtAllotToDate').val()+"' name='todate' />");
	$("#eoiReportCSVInputCol").append("<input type='hidden' value='"+$('#projectid').val()+"' name='projectSfid' />");
	
	$('#allotmentReportID i').show();
	$('#journeyTab i').show();
	
	$("#allotmentReportTable tbody").empty();
	$("#covered1bhkid").empty();
	$("#covered2bhkid").empty();
	$("#covered3bhkid").empty();
	$("#blockInvId").empty();
	$("#holdInvId").empty();
	
	
	$.get("getAllotmentDayReport",{"projectSfid":$('#projectid').val(), "fromDate":$('#txtAllotFromDate').val(), "toDate":$('#txtAllotToDate').val(), "userVerticals":USER_VERTICALES_GV },function(data){				 
		var obj =JSON.stringify(data);
		var obj1 =JSON.parse(obj);
		
		
		
		var html = '';
		
		var tab = '';
		var eoiTarget = '';
		var icon = '';
		
		//alert(obj1.length);
		if(obj1!=null) {
			for(var i=0;i<obj1.length;i++){
				if (obj1[i].preferences != null && obj1[i].preferences != "" && obj1[i].preferences != undefined && obj1[i].preferences != "undefined") {
					tab = obj1[i].preferences.replace(/\|/g, "</div><div class='prefTab'>");
				}
				var offerAmount =0; 
				if(obj1[i].offeramount!=null)
					offerAmount=obj1[i].offeramount;
				
				html += "<tr>" +
							" <td>"+obj1[i].priority_no__c+"</td>" +
							" <td>"+obj1[i].enqname+"</td>" +
							" <td>"+obj1[i].custname+"</td>" +
							" <td>"+obj1[i].mobile__c+"</td>" +
							" <td>"+obj1[i].email+"</td>" +
							" <td>"+obj1[i].walk_in_source__c+"</td>" +
							" <td>"+obj1[i].sourcing_manager_name__c+"</td>" +
							
							" <td>"+obj1[i].verticle__c+"</td>" +
							" <td>"+obj1[i].brokername+"</td>" +
							" <td>"+obj1[i].closing_manager_name__c+"</td>" +
							" <td>"+obj1[i].propstrength__request_source__c+"</td>" +
							" <td>"+obj1[i].propstrength__enquiry_type__c+"</td>" +
							" <td>"+obj1[i].offername+"</td>" +
							" <td>"+obj1[i].user_name+"</td>" +
							" <td>"+obj1[i].offer_date+"</td>" +
							" <td>"+obj1[i].propstrength__super_area__c+"</td>" +
							" <td>"+obj1[i].propstrength__carpet_area__c+"</td>" +
							" <td>"+obj1[i].propstrength__unit_type__c+"</td>" +
							" <td>"+obj1[i].car_park_type+"</td>" +
							" <td>"+obj1[i].scheme_name+"</td>" +
							" <td>"+obj1[i].propstrength__total_basic_sale_price__c+"</td>" +
							" <td>"+obj1[i].propstrength__total_sales_consideration__c+"</td>" +
							/*" <td>"+obj1[i].user_name+"</td>" +*/
							
							" <td>"+obj1[i].booking_name+"</td>" +
							" <td>"+obj1[i].booking_status+"</td>" +
							" <td>"+obj1[i].kyc_approvedby+"</td>" +
							
							" <td>"+obj1[i].offeramount+"</td>" +
							" <td>"+obj1[i].propStrength__Property_Name__c+"</td>" +
							" <td>"+obj1[i].propStrength__House_Unit_No__c+"</td>" +
							" <td>"+obj1[i].actula_5_per+"</td>" +
							" <td>"+obj1[i].diffamount+"</td>" +
						" </tr>";
			}
			
			html = html.replace(/null/g, " - ");
			
			//html = html.replace(/null/g, "");
			
			$("#allotmentReportTable tbody").append(html);
			
			
		} else {
			//alert ("Data not found");
		}
	}).done(function(data){
		/*$('#applicationTable').dataTable({
			"ordering": false,
			"bDestroy": true
		});*/
		
		$('#allotmentReportID i').hide();
		$('#journeyTab i').hide();
		
		//$('#eoiReportTable').dataTable({
			//"ordering": true,
			//"bDestroy": true
		//});
		
		 $('#allotmentReportTable').DataTable( {
			 dom: 'Bfrtip',
			 "buttons": [
				 { "extend": 'excel', "text":'Export To Excel',"className": 'btn btn-default btn-xs' }
		      ],
		      "order": []
		 });
		
		
		
	});	
	
	$.get("getAllotmentDayMISReport",{"projectSfid":$('#projectid').val()},function(data){
	}).done(function(data){
		$("#allotmentmisReport tbody").empty();
		var html = "<tr>" +
					" <td>"+data.totalArealSold+" Sq. Ft.</td>" +
					" <td>"+parseFloat(data.totalBSP).toFixed(2)+" Cr.</td>" +
					" <td>"+data.totalEtoken+"</td>" +
					" <td>"+data.unitcount+"</td>" +
					" <td>"+data.kycApprovedCount+"</td>" +
					" <td>"+data.bookingcount+"</td>" +
					
					" </tr>";
	
	$("#allotmentmisReport tbody").append(html);
	if($("#projectid").val()=="a1l6F000009D6IMQA0") {
		$("#coveredcarpark_id").show();
		var coverd1bhk=data.coverd1bhk;
		$("#covered1bhkid").append(coverd1bhk.toString()+"/69");
		$("#covered2bhkid").append(data.coverd2bhk+"/96");
		$("#covered3bhkid").append(data.coverd3bhk+"/147");
	} 
	
	$("#blockInvId").append(data.blockedInventoryCount);
	$("#holdInvId").append(data.holdInventoryCount);
	
		});
}

function carparkCountList (){
	$('#soldCarparkTab i').show();
	$.get("getCarparkCount",{"projectSFID":$('#projectid').val() },function(data){				 
		//var obj =JSON.stringify(data);
		
		var countHTML = "";
		var carparkTypeName = "";
		
		if(data!=null) {
			$("#carparkCountTbl").html("");
			
			var totalSold = 0;
			var totalCarpark = 0;
			
			countHTML += "<div class='clearfix'></div> <h4>Sold Car Park</h4> <table class='table table-bordered table-striped'>" 
							+ "<thead>" 
								+ "<tr>"
									+ "<th>Car park type</th>"
									+ "<th>Car park <small><b>(*Indicative - Its considering offers created through D4U only)</b><small></th>" 
								+ "</tr>"
							+ "</thead>"
							+ "<tbody>";
				
								for(var i=0;i<data.length;i++){
									
									if (data[i].carpark_alias != "null" && data[i].carpark_alias != undefined && data[i].carpark_alias != null){
										carparkTypeName = data[i].carpark_alias;
									} else {
										carparkTypeName = data[i].carpark_type;
									}
									
									countHTML += "<tr>" 
													+ "<td>"+carparkTypeName+"</td>" 
													+ "<td>"+data[i].sold_carpark+"/"+data[i].total_carpark+"</td>"  
												+ "</tr>"; 
									
									totalSold = parseInt(data[i].sold_carpark)+parseInt(totalSold);
									totalCarpark = parseInt(data[i].total_carpark)+parseInt(totalCarpark); 
									
								}
			
				countHTML += "<tr><th>Grand Total</th><th>"+totalSold+"/"+totalCarpark+"</th></tr>"; 
								
				countHTML += "</tbody>" 
						+ "</table>";		
				
				$("#carparkCountTbl").html(countHTML);
				
		} else {
			$('#soldCarparkTab i').hide();
			$("#carparkCountTbl").html("");
		}
	}).done(function(data){
		$('#soldCarparkTab i').hide();
	});	
}

			
function  towerDashboard (){
	$('#towerSoldUnitTab i').show();
	$.get("getTowerdashboard",{"projectSfid":$('#projectid').val()},function(data){
	}).done(function(data){
			
		$("#towerDashboard").html("");
		
		var t1 = parseInt(data.t1BHK1)+parseInt(data.t1BHK2)+parseInt(data.t1BHK3);
		var t2 = parseInt(data.t2BHK1)+parseInt(data.t2BHK2)+parseInt(data.t2BHK3);
		var t3 = parseInt(data.t3BHK1)+parseInt(data.t3BHK2)+parseInt(data.t3BHK3);
		var t4 = parseInt(data.t4BHK1)+parseInt(data.t4BHK2)+parseInt(data.t4BHK3);
		
		var bhk1 = parseInt(data.t1BHK1)+parseInt(data.t2BHK1)+parseInt(data.t3BHK1)+parseInt(data.t4BHK1);
		var bhk2 = parseInt(data.t1BHK2)+parseInt(data.t2BHK2)+parseInt(data.t3BHK2)+parseInt(data.t4BHK2);
		var bhk3 = parseInt(data.t1BHK3)+parseInt(data.t2BHK3)+parseInt(data.t3BHK3)+parseInt(data.t4BHK3);
			
		var grandTotal = parseInt(bhk1)+parseInt(bhk2)+parseInt(bhk3);
		
		var unitFacingTypeHTML = "<div class='clearfix'></div> <h4>Tower Wise Offer Created</h4>" +
		"<table class='table table-bordered table-striped' >" +
			"<thead>" +
				"<tr>" +
					"<th>Type</th>" +
					"<th>T1</th>" +
					"<th>T2</th>" +
					"<th>T3</th>" +
					"<th>T4</th>" +
					"<th>Grand Total</th>" +
				"</tr>" +
			"</thead>" +
			"<tbody>" +
				"<tr>" + 
					"<td>1 BHK</td>" + 
					"<td>"+data.t1BHK1+"/46</td>" + 
					"<td>"+data.t2BHK1+"/46</td>" +
					"<td>"+data.t3BHK1+"/48</td>" +
					"<td>"+data.t4BHK1+"/48</td>" +
					"<td>"+bhk1+"/188</td>" +
				"</tr>" + 
				
				"<tr>" + 
					"<td>2 BHK</td>" + 
					"<td>"+data.t1BHK2+"/84</td>" + 
					"<td>"+data.t2BHK2+"/84</td>" +
					"<td>"+data.t3BHK2+"/88</td>" +
					"<td>"+data.t4BHK2+"/88</td>" +
					"<td>"+bhk2+"/344</td>" +
				"</tr>" + 
				
				"<tr>" + 
					"<td>3 BHK</td>" + 
					"<td>"+data.t1BHK3+"/44</td>" + 
					"<td>"+data.t2BHK3+"/44</td>" +
					"<td>"+data.t3BHK3+"/46</td>" +
					"<td>"+data.t4BHK3+"/46</td>" +
					"<td>"+bhk3+"/180</td>" +
				"</tr>" + 
				
				"<tr>" + 
					"<th>Grand Total</th>" + 
					"<th>"+t1+"/174</th>" + 
					"<th>"+t2+"/174</th>" +
					"<th>"+t3+"/182</th>" +
					"<th>"+t4+"/182</th>" +
					"<th>"+grandTotal+"/712</th>" +
				"</tr>" + 
				
			"</tbody>" +
		"</table>";		
				
		$("#towerDashboard").html(unitFacingTypeHTML);
		$('#towerSoldUnitTab i').hide();
	});
}

function  facingDashboard (){
	$('#facingSoldUnitTab i').show();
	$.get("getFacingdashboard",{"projectSfid":$('#projectid').val()},function(data){
	}).done(function(data){
		
		$("#unitFacingType").html("");
		
		var bhk1CityGard = parseInt(data.city1bhk)+parseInt(data.garden1bhk);
		var bhk2CityGard = parseInt(data.city2bhk)+parseInt(data.garden2bhk);
		var bhk3CityGard = parseInt(data.city3bhk)+parseInt(data.garden3bhk);
		var totalCityGard = parseInt(bhk1CityGard)+parseInt(bhk2CityGard)+parseInt(bhk3CityGard);
		
		
		var cityTotal = parseInt(data.city1bhk)+parseInt(data.city2bhk)+parseInt(data.city3bhk);
		var gardenTotal = parseInt(data.garden1bhk)+parseInt(data.garden2bhk)+parseInt(data.garden3bhk);
		
		
		
		
		var unitFacingTypeHTML = "<div class='clearfix'></div> <h4>Facing Wise Offer Created</h4>" +
		"<table class='table table-bordered table-striped' id='unitFacingType' >" +
			"<thead>" +
				"<tr>" +
					"<th>Type</th>" +
					"<th>City Facing</th>" +
					"<th>Garden Facing</th>" +
					"<th>Grand Total</th>" +
				"</tr>" +
			"</thead>" +
			"<tbody>" +
				"<tr>" + 
					"<td>1 BHK</td>" + 
					"<td>"+data.city1bhk+"/24</td>" + 
					"<td>"+data.garden1bhk+"/164</td>" +
					"<td>"+bhk1CityGard+"/188</td>" +
				"</tr>" +
				
				"<tr>" + 
					"<td>2 BHK</td>" + 
					"<td>"+data.city2bhk+"/262</td>" + 
					"<td>"+data.garden2bhk+"/82</td>" +
					"<td>"+bhk2CityGard+"/344</td>" +
				"</tr>" +
				
				"<tr>" + 
					"<td>3 BHK</td>" + 
					"<td>"+data.city3bhk+"/98</td>" + 
					"<td>"+data.garden3bhk+"/82</td>" +
					"<td>"+bhk3CityGard+"/180</td>" +
				"</tr>" +
				
				"<tr>" + 
					"<th>Grand Total</th>" + 
					"<th>"+cityTotal+"/384</th>" + 
					"<th>"+gardenTotal+"/328</th>" +
					"<th>"+totalCityGard+"/712</th>" +
				"</tr>" +
				
			"</tbody>" +
		"</table>";		
				
		//$("#coveredcarpark_id").append(carparkHTML);
		$("#unitFacingType").html(unitFacingTypeHTML);
		$('#facingSoldUnitTab i').hide();
	});
}



function getUnitFacingCount (){
	 
	$.get("getUnitFacingCount",{"projectSFID":$('#projectid').val() },function(data){				 
		
		var countHTML = "";
		var carparkTypeName = "";
		
		if(data!=null) {
			$("#unitFacingCount").html("");
			
			var unitType = [];
            var unitFacing = [];
			
			for(i = 0; i< data.length; i++){    
				if(unitType.indexOf(data[i].propstrength__unit_type__c) === -1){
					unitType.push(data[i].propstrength__unit_type__c);
                }
            }
			
			for(h = 0; h< data.length; h++){    
				if(unitFacing.indexOf(data[h].property_facing__c) === -1){
					unitFacing.push(data[h].property_facing__c);
                } 
            }
			
			countHTML += "<div class='clearfix'></div> <h4>Facing Wise Offer Created</h4> <table class='table table-bordered table-striped'>" ;
							/*+ "<thead>" 
								+ "<tr>"
									+ "<th>Car park type</th>"
									+ "<th>Car park <small><b>(*Indicative - Its considering offers created through D4U only)</b><small></th>" 
								+ "</tr>"
							+ "</thead>"*/
							 
								
								countHTML += "<thead> <tr> <th>Type</th>"; 
								for(h = 0; h< unitFacing.length; h++){
									countHTML += "<th>"+unitFacing[h]+"</th>";
								}
								
								countHTML += "<th>Grand Total</th>";
								
								countHTML += "</tr></thead><tbody>";
			
								for(j = 0; j< unitType.length; j++){
									countHTML += "<tr>";
										countHTML += "<th>"+unitType[j]+"</th>";
										var x = 0;
										var soldUnit = 0;
										var totalUnit = 0;
										
										for (k = 0; k < data.length; k++) {
											
											if (unitType[j] == data[k].propstrength__unit_type__c) {
												
												for (p = 0; p< unitFacing.length; p++){
													if (unitFacing[p] == data[k].property_facing__c){
														countHTML += "<td>"+data[k].sold+"/"+data[k].total+"</td>";
													} else {
														countHTML += "<td></td>";
													}
												}
												
												
												//x++
												 
												soldUnit = parseInt(data[k].sold)+parseInt(soldUnit);
												totalUnit = parseInt(data[k].total)+parseInt(totalUnit);
											} 
											
										}
										
										//var remTD = unitFacing.length - x;
										
										/*for (y = 0; y < remTD; y++){
											countHTML += "<td>-</td>";
										}*/
										
										countHTML += "<td>"+soldUnit+"/"+totalUnit+"</td>";
									
									countHTML += "</tr>";
								}
								
								countHTML += "<tr><th>Grand Total</th>";
								
								var grandTotal = 0;
								var grandSold = 0;
								
								for(m = 0; m< unitFacing.length; m++){
									
										var soldUnit1 = 0;
										var totalUnit1 = 0;
										
										
										
									
										for (n = 0; n < data.length; n++) {
											if (unitFacing[m] == data[n].property_facing__c) {
												
												soldUnit1 = parseInt(data[n].sold)+parseInt(soldUnit1);
												totalUnit1 = parseInt(data[n].total)+parseInt(totalUnit1);
												
											}
										}
										
										grandTotal = parseInt(grandTotal)+parseInt(totalUnit1);
										grandSold = parseInt(grandSold)+parseInt(soldUnit1);
										
										countHTML += "<th>"+soldUnit1+"/"+totalUnit1+"</th>";
								}
								countHTML += "<th>"+grandSold+"/"+grandTotal+"</th></tr>";	
								
				//countHTML += "<tr><th>Grand Total</th><th>"+totalSold+"/"+totalCarpark+"</th></tr>"; 
								
				countHTML += "</tbody>" 
						+ "</table>";		
				
				$("#unitFacingCount").html(countHTML);
				
		} else {
			 
			$("#unitFacingCount").html("");
		}
	}).done(function(data){

	});	
}