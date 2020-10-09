//alert ($('#contextPath').val()+"/");

//var urlDomin = "http://localhost:8080/UserEnqury/";

//var urlDomin = $('#contextPath').val()+"/";
$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});

$(document).ready(function () {
	$('#UnitStatus').multiselect({
		maxHeight: '200',
		buttonWidth: '100%'
	});
});

var pageContext = $("#contextPath").val()+"/";	

/*var visible = true;
var tableContainer = '';
var table = '';*/

function getInventoryReportDtl () {
	
	$("#inventoryReportDtl").DataTable().destroy();
	
	//$('#eoiReportTabSales i').show();
	
	$("#inventoryReportDtl tbody").empty();
	$.get(pageContext+"getInventoryReport",{"projectSfid":$('#projectid').val(), "towerCode":$('#towerMstReport').val()},function(data){				 
		var obj =JSON.stringify(data);
		var obj1 =JSON.parse(obj);
		
		var html = '';
		
		var rowColor = '';
		var hold_reasonVar = '';
		var rate_per_unit = 0;
		var saleable_area = 0;
		 
		if(obj1!=null) {
			
			var uniqueId = [];
			
			var enq_name = [];
			var customer_name = [];
			var customer_mobile = [];
			var tower_name__c = [];
			var floor_number__c = [];
			var propstrength__house_unit_no__c = [];
			var propstrength__unit_type__c = [];
			var wing_block__c = [];
			var admin_name = [];
			var hold_description = [];
			var hold_behalf_username = [];
			var hold_behalf_email = [];
			
			var hold_reason = [];
			var eoi_unit_locked = [];
			var propstrength__rate_per_unit_area__c = [];
			var saleable_area__c = [];
			var verticle__c = [];
			
			for(var i = 0; i< obj1.length; i++){    
                if(uniqueId.indexOf(obj1[i].id) === -1){
                    uniqueId.push(obj1[i].id);
                    enq_name.push(obj1[i].enq_name); 
                    customer_name.push(obj1[i].customer_name); 
                    customer_mobile.push(obj1[i].customer_mobile); 
					tower_name__c.push(obj1[i].tower_name__c); 
                    floor_number__c.push(obj1[i].floor_number__c); 
                    propstrength__house_unit_no__c.push(obj1[i].propstrength__house_unit_no__c); 
                    propstrength__unit_type__c.push(obj1[i].propstrength__unit_type__c); 
                    wing_block__c.push(obj1[i].wing_block__c); 
                    admin_name.push(obj1[i].admin_name); 
                    hold_description.push(obj1[i].hold_description); 
                    hold_behalf_username.push(obj1[i].hold_behalf_username); 
                    hold_behalf_email.push(obj1[i].hold_behalf_email); 
                    hold_reason.push(obj1[i].hold_reason);
                    eoi_unit_locked.push(obj1[i].eoi_unit_locked);
                    propstrength__rate_per_unit_area__c.push(obj1[i].propstrength__rate_per_unit_area__c);
                    saleable_area__c.push(obj1[i].saleable_area__c);
                    verticle__c.push(obj1[i].verticle__c);
                }        
            }
			
			 
			for(var j = 0; j< uniqueId.length; j++){
				
				var amount = 0;
				var ATotal = 0;
				var BTotal = 0;
				
				for(var k = 0; k< obj1.length; k++){
					if(uniqueId[j] == obj1[k].id) {
						if (obj1[k].propstrength__type__c == 'Flexible') {
							amount = (obj1[k].othercharge__rate_per_unit_area__c)*(obj1[k].saleable_area__c)
						} else if (obj1[k].propstrength__type__c == 'Fixed'){
							amount = obj1[k].propstrength__fixed_charge__c
						}   

						if(obj1[k].propstrength__part_of_cop__c == true) {
							ATotal = amount+ATotal;
						} else {
							BTotal = amount+BTotal;
						}
					}
				}
				
				if  (hold_reason[j] == 'temp' && eoi_unit_locked[j] == true){
					rowColor = "rowEOIHoldClr";
					hold_reasonVar = 'EOI Hold';
				}
				
				else if (hold_reason[j] == 'temp') {
					rowColor = "rowHoldClr";
					hold_reasonVar = 'Hold';
				} else if (hold_reason[j] == 'block'){
					rowColor = "rowBlockClr";
					hold_reasonVar = 'Block';
				} 
				
				if (propstrength__rate_per_unit_area__c[j] != null && propstrength__rate_per_unit_area__c[j] != undefined && propstrength__rate_per_unit_area__c[j] != '') {
					rate_per_unit = propstrength__rate_per_unit_area__c[j];
				} else {
					rate_per_unit = 0;
				}
				
				if (saleable_area__c[j] != null && saleable_area__c[j] != undefined && saleable_area__c[j] != '') {
					saleable_area = saleable_area__c[j];
				} else {
					saleable_area = 0;
				}
				
				html += "<tr class='"+rowColor+"'>" +
							" <td>"+hold_reasonVar+"</td>" +
							"<td>"+enq_name[j]+"</td>" +
							"<td>"+verticle__c[j]+"</td>" +
							"<td>"+customer_name[j]+"</td>" +
							"<td>"+customer_mobile[j]+"</td>" +
							" <td>"+tower_name__c[j]+"</td>" +
							" <td>"+floor_number__c[j]+"</td>" +
							" <td>"+propstrength__house_unit_no__c[j]+"</td>" +
							" <td>"+propstrength__unit_type__c[j]+"</td>" +
							" <td>"+wing_block__c[j]+"</td>" +
							" <td>"+saleable_area+"</td>" +
							" <td>"+rate_per_unit+"</td>" +
							" <td>"+parseFloat((rate_per_unit*saleable_area)+(ATotal)).toFixed(2)+"</td>" +
							
							" <td>"+parseFloat(BTotal).toFixed(2)+"</td>" +
							
							" <td>"+admin_name[j]+"</td>" +
							" <td>"+hold_description[j]+"</td>" +
							" <td>"+hold_behalf_username[j]+"</td>" +
							" <td style='word-break: break-all;'>"+hold_behalf_email[j]+"</td>" +
						" </tr>";
			}
			
			html = html.replace(/undefined/g, " - ");
			html = html.replace(/null/g, " - ");
			
			$("#inventoryReportDtl tbody").append(html);
			
		} else {
			alert ("Data not found");
		}
	}).done(function(data){
		
		//$('#eoiReportTabSales i').hide();
		
		$('#inventoryReportDtl').DataTable( {
			 dom: 'Bfrtip',
			 //fixedHeader: true,
			 "buttons": [
				 { "extend": 'excel', "text":'Export To Excel',"className": 'btn btn-default btn-xs' }
		      ],
		      "order": []
		 });
		
		//visible = true;
		//tableContainer = $(table.table().container());
	});	
}






function getAllInventoryReportDtl () {
	
	$('#getAllUnitBtn span').html('<i class="fa fa-spinner fa-spin" style="color:#fff !important"></i>'); 
	
	var project = $('#allReportMultiTower option:selected');
	var selectedTower = [];
	$(project).each(function(index, brand){
		selectedTower.push($(this).val());
	});
	
	console.log (selectedTower);
	
	$("#allInventoryReportDtl").DataTable().destroy();
	$("#allInventoryReportDtl tbody").empty();
	
	$.post(pageContext+"getAllInventoryReport",{"projectSfid":$('#projectid').val(), "towerCode":selectedTower.join(","), "status":$('#UnitStatus').val()},function(data){                      
		
		var obj1 =JSON.parse(data);
		var html = '';
			
		var d4uHoldStatus = '';
		var status = '';
		
		if (obj1 != null) {
			for(var i=0;i<obj1.length;i++){
				
				if (obj1[i].qry_msg != "MAX_LIMIT") {
					if (obj1[i].hold_reason == 'temp'){
						d4uHoldStatus= 'EOI Hold';
					} else if (obj1[i].hold_reason == 'block'){
						d4uHoldStatus= 'Block';
					} else {
						d4uHoldStatus= '';
					}
					
					
					if (obj1[i].propstrength__property_on_hold_for_reallocation__c != undefined
							&& obj1[i].propstrength__property_alloted_through_offer__c != undefined
							&& obj1[i].propstrength__allotted__c != undefined) {
						
						/*if (obj1[i].hold_status == true || obj1[i].propstrength__property_on_hold_for_reallocation__c == true){
							status = 'Hold';
						} else if (obj1[i].propstrength__property_alloted_through_offer__c == true && obj1[i].propstrength__allotted__c == true) {
							status = 'Sold';
						} else if (obj1[i].propstrength__property_alloted_through_offer__c == false && obj1[i].propstrength__allotted__c == false) {
							status = 'Available';
						} else if (obj1[i].propstrength__property_alloted_through_offer__c == true && obj1[i].propstrength__allotted__c == false) {
							status = 'Offered';
						} else if (obj1[i].propstrength__property_on_hold_for_reallocation__c == true && obj1[i].propstrength__property_alloted_through_offer__c == true && obj1[i].propstrength__allotted__c == false) {
							status = 'Offered with SFDC Hold';
						}*/
						
						if (obj1[i].propstrength__property_alloted_through_offer__c == true && obj1[i].propstrength__allotted__c == true) {
							status = 'Sold';
						} else if ((obj1[i].hold_status == true || obj1[i].propstrength__property_on_hold_for_reallocation__c == true) &&  obj1[i].propstrength__property_alloted_through_offer__c == false && obj1[i].propstrength__allotted__c == false) {
							status = 'Hold';
						} else if (obj1[i].propstrength__property_alloted_through_offer__c == false && obj1[i].propstrength__allotted__c == false && obj1[i].hold_status == false && obj1[i].propstrength__property_on_hold_for_reallocation__c == false) {
							status = 'Available';
						} else if (obj1[i].propstrength__property_alloted_through_offer__c == true && obj1[i].propstrength__allotted__c == false && obj1[i].hold_status == false && obj1[i].propstrength__property_on_hold_for_reallocation__c == false) {
							status = 'Offered';
						} else if (obj1[i].propstrength__property_on_hold_for_reallocation__c == true && obj1[i].propstrength__property_alloted_through_offer__c == true && obj1[i].propstrength__allotted__c == false  && obj1[i].hold_status == false ) {
							status = 'Offered with SFDC Hold';
						} else {
							status = '';
						}
						
					} else {
						status = '';
					}
					
					
					html += "<tr>" +
						"<td>"+obj1[i].project_name__c+"</td>" +
						"<td>"+obj1[i].tower_name__c+"</td>" +
						"<td>"+obj1[i].wing_block__c+"</td>" +
						
						"<td>"+obj1[i].property_facing__c+"</td>" +
						
						"<td>"+obj1[i].propstrength__unit_type__c+"</td>" +
						
						"<td>"+obj1[i].propstrength__house_unit_no__c+"</td>" +
						"<td>"+obj1[i].inventory_category__c+"</td>" +
						"<td>"+obj1[i].propstrength__property_name__c+"</td>" +
						
						"<td>"+obj1[i].cop+"</td>" +
						
						"<td>"+obj1[i].propstrength__rate_per_unit_area__c+"</td>" +
						
						"<td>"+obj1[i].rera_net_carpet_sqft+"</td>" +
						"<td>"+obj1[i].rera_exclusive_sqft+"</td>" +
						"<td>"+obj1[i].saleable_area__c+"</td>" +
						"<td>"+obj1[i].super_area+"</td>" +  
						"<td>"+status+"</td>" +
						"<td>"+obj1[i].propstrength__allotted__c+"</td>" +
						
						"<td>"+obj1[i].propstrength__property_alloted_through_offer__c+"</td>" +
						
						
						"<td>"+obj1[i].propstrength__property_on_hold_for_reallocation__c+"</td>" +
						"<td>"+d4uHoldStatus+"</td>" +
						"<td>"+obj1[i].enq_name+"</td>" +
						
						"<td>"+obj1[i].verticle__c+"</td>" +
						
						"<td>"+obj1[i].propstrength__active__c+"</td>" +
						
						
						
					" </tr>";
				
				} else {
					swal({
	                	title: "Records exceeding 15000. Please narrow down project or select few tower",
	          			text: "Requested records count is: "+obj1[i].qry_count,
	          			//timer: 8000,
	          			type: "warning",
	                });
					
					$("#swal2-title").css({"font-size": "22px"});
					return false;
				}
			}
			 
			html = html.replace(/undefined/g, " - ");
			html = html.replace(/null/g, " - ");
			
			$("#allInventoryReportDtl tbody").append(html);
		} else {
			alert ("Data not found");
		}
         
	}).done(function(data){
		$('#allInventoryReportDtl').DataTable({
			dom: 'Bfrtip',
			"buttons": [{ "extend": 'excel', "text":'Export To Excel',"className": 'btn btn-default btn-xs' }],
			"order": []
		});
		
		$('#getAllUnitBtn span').html(''); 
	}).fail(function(xhr, status, error) {
		$('#getAllUnitBtn span').html(''); 
    });
}





/*
function getAllInventoryReportDtl () {
	
	$("#allInventoryReportDtl").DataTable().destroy();
	
	$("#allInventoryReportDtl tbody").empty();
	
	$.get(pageContext+"getAllInventoryReport",{"projectSfid":$('#projectid').val(), "towerCode":$('#allUnitTowerMstReport').val()},function(data){				 
		var obj =JSON.stringify(data);
		var obj1 =JSON.parse(obj);
		
		var html = '';
		
		if(obj1!=null) {
			
			for(var i = 0; i< obj1.length; i++){    
                
				html += "<tr>" +
							"<td>"+obj1[i].project_name__c+"</td>" +
							"<td>"+obj1[i].tower_name__c+"</td>" +
							"<td>"+obj1[i].propstrength__house_unit_no__c+"</td>" +
							"<td>"+obj1[i].wing_block__c+"</td>" +
							"<td>"+obj1[i].inventory_category__c+"</td>" +
							"<td>"+obj1[i].propstrength__property_name__c+"</td>" +
							"<td>"+obj1[i].propstrength__rate_per_unit_area__c+"</td>" +
							"<td>"+obj1[i].propstrength__property_alloted_through_offer__c+"</td>" +
							"<td>"+obj1[i].propstrength__active__c+"</td>" +
							"<td>"+obj1[i].propstrength__property_on_hold_for_reallocation__c+"</td>" +
							"<td>"+obj1[i].hold_reason+"</td>" +
							"<td>"+obj1[i].enq_name+"</td>" +
							"<td>"+obj1[i].rera_net_carpet_sqft+"</td>" +
							"<td>"+obj1[i].rera_exclusive_sqft+"</td>" +
							"<td>"+obj1[i].saleable_area__c+"</td>" +
							"<td>"+obj1[i].super_area+"</td>" +  
						" </tr>";
			}
			
			html = html.replace(/undefined/g, " - ");
			html = html.replace(/null/g, " - ");
			
			$("#allInventoryReportDtl tbody").append(html);
			
		} else {
			alert ("Data not found");
		}
	}).done(function(data){
		$('#allInventoryReportDtl').DataTable( {
			 dom: 'Bfrtip',
			 "buttons": [
				 { "extend": 'excel', "text":'Export To Excel',"className": 'btn btn-default btn-xs' }
		      ],
		      "order": []
		 });
	});	
}*/