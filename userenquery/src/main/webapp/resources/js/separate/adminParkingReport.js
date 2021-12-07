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
	$('#ParkingStatus').multiselect({
		maxHeight: '200',
		buttonWidth: '100%'
	});
});

var pageContext = $("#contextPath").val()+"/";	


function getHoldParkingReportDtl () {
	
	$('#getParkingRepUnitBtn span').html('<i class="fa fa-spinner fa-spin" style="color:#fff !important"></i>'); 
	
	$("#parkingReportDtl").DataTable().destroy();
	$("#parkingReportDtl tbody").empty();
	
	$.post(pageContext+"getParkingReport",{"projectSfid":$('#projectid').val(), "towerCode":$('#towerMstReportParking').val()},function(data){                      
		
		var obj1 =JSON.parse(data);
		var html = '';
			
		var d4uHoldStatus = '';
		var rowColor = '';
		
		if (obj1 != null) {
			for(var i=0;i<obj1.length;i++){
				 
					if (obj1[i].hold_reason == 'temp'){
						rowColor = "rowEOIHoldClr";
						d4uHoldStatus= 'Hold against flat/unit';
					} else if (obj1[i].hold_reason == 'block'){
						rowColor = "rowBlockClr";
						d4uHoldStatus= 'Block';
					} else {
						rowColor = "";
						d4uHoldStatus= '';
					}
					
					html += "<tr class='"+rowColor+"'>" +
						"<td>"+obj1[i].created_at+"</td>" +
						"<td>"+d4uHoldStatus+"</td>" +
						"<td>"+obj1[i].admin_emailid+"</td>" +
						"<td>"+obj1[i].propstrength__tower_name__c+"</td>" +
						"<td>"+obj1[i].propstrength__house_unit_no__c+"</td>" +
						"<td>"+obj1[i].propstrength__property_name__c+"</td>" +
						"<td>"+obj1[i].parking_tower+"</td>" +
						"<td>"+obj1[i].propstrength__car_parking_name__c+"</td>" +
						"<td>"+obj1[i].propstrength__parking_type__c+"</td>" +
						"<td>"+obj1[i].propstrength__category_of_parking__c+"</td>" +
						"<td>"+obj1[i].propstrength__parking_size__c+"</td>" +
						"<td>"+obj1[i].location_of_parking__c+"</td>" +
						"<td>"+obj1[i].parking_area_sq_ft__c+"</td>" +
						"<td>"+obj1[i].parking_area_sq_mt__c+"</td>" +  
						"<td>"+obj1[i].dimensions__c+"</td>" +
					" </tr>";
			}
			 
			html = html.replace(/undefined/g, " - ");
			html = html.replace(/null/g, " - ");
			
			$("#parkingReportDtl tbody").append(html);
		} else {
			alert ("Data not found");
		}
         
	}).done(function(data){
		$('#parkingReportDtl').DataTable({
			dom: 'Bfrtip',
			"buttons": [{ "extend": 'excel', "text":'Export To Excel',"className": 'btn btn-default btn-xs' }],
			"order": []
		});
		
		$('#getParkingRepUnitBtn span').html(''); 
	}).fail(function(xhr, status, error) {
		$('#getParkingRepUnitBtn span').html(''); 
    });
}









function getAllParkingReportDtl () {
	
	$('#getAllParkingBtn span').html('<i class="fa fa-spinner fa-spin" style="color:#fff !important"></i>'); 
	
	var project = $('#allParkingReportMultiTower option:selected');
	var selectedTower = [];
	$(project).each(function(index, brand){
		selectedTower.push($(this).val());
	});
	
	console.log (selectedTower);
	
	$("#allParkingReportDtl").DataTable().destroy();
	$("#allParkingReportDtl tbody").empty();
	
	$.post(pageContext+"getAllParkingReport",{"projectSfid":$('#projectid').val(), "towerCode":selectedTower.join(","), "status":$('#ParkingStatus').val()},function(data){                      
		
		var obj1 =JSON.parse(data);
		var html = '';
			
		var d4uHoldStatus = '';
		var status = '';
		
		if (obj1 != null) {
			for(var i=0;i<obj1.length;i++){
				
				if (obj1[i].qry_msg != "MAX_LIMIT") {
					if (obj1[i].hold_reason == 'temp'){
						d4uHoldStatus= 'Hold against flat/unit';
					} else if (obj1[i].hold_reason == 'block'){
						d4uHoldStatus= 'Block';
					} else {
						d4uHoldStatus= '';
					}
					
					
					if (obj1[i].propstrength__is_parking_blocked__c != undefined
							&& obj1[i].allotted_through_offer__c != undefined
							&& obj1[i].propstrength__allotted__c != undefined) {
						if (obj1[i].allotted_through_offer__c == true && obj1[i].propstrength__allotted__c == true) {
							status = 'Sold';
						} else if ((obj1[i].hold_status == true || obj1[i].propstrength__is_parking_blocked__c == true) &&  obj1[i].allotted_through_offer__c == false && obj1[i].propstrength__allotted__c == false) {
							status = 'Hold';
						} else if (obj1[i].allotted_through_offer__c == false && obj1[i].propstrength__allotted__c == false && obj1[i].hold_status == false && obj1[i].propstrength__is_parking_blocked__c == false) {
							status = 'Available';
						} else if (obj1[i].allotted_through_offer__c == true && obj1[i].propstrength__allotted__c == false && obj1[i].hold_status == false && obj1[i].propstrength__is_parking_blocked__c == false) {
							status = 'Offered';
						} else if (obj1[i].propstrength__is_parking_blocked__c == true && obj1[i].allotted_through_offer__c == true && obj1[i].propstrength__allotted__c == false  && obj1[i].hold_status == false ) {
							status = 'Offered with SFDC Hold';
						} else {
							status = '';
						}
						
					} else {
						status = '';
					}
					
					
					html += "<tr>" +
						"<td>"+obj1[i].project_name+"</td>" +
						"<td>"+obj1[i].tower_name+"</td>" +
						"<td>"+obj1[i].name+"</td>" +
						
						"<td>"+obj1[i].propstrength__car_parking_name__c+"</td>" +
						
						"<td>"+obj1[i].propstrength__double_bay_parking__c+"</td>" +
						
						"<td>"+obj1[i].propstrength__parking_size__c+"</td>" +
						"<td>"+obj1[i].propstrength__is_released__c+"</td>" +
						"<td>"+obj1[i].propstrength__is_parking_blocked__c+"</td>" +
						
						"<td>"+obj1[i].propstrength__category_of_parking__c+"</td>" +
						
						"<td>"+obj1[i].location_of_parking__c+"</td>" +
						
						"<td>"+obj1[i].parking_area_sq_ft__c+"</td>" +
						"<td>"+obj1[i].parking_area_sq_mt__c+"</td>" +
						"<td>"+obj1[i].dimensions__c+"</td>" +
						"<td>"+obj1[i].propstrength__approvalstatus__c+"</td>" +  
						 
						"<td>"+obj1[i].allotted_through_offer__c+"</td>" +
						
						"<td>"+obj1[i].propstrength__allotted__c+"</td>" +
						"<td>"+d4uHoldStatus+"</td>" +
						 
						"<td>"+obj1[i].propstrength__active__c+"</td>" +
						"<td>"+status+"</td>" +
						
						
						
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
			
			$("#allParkingReportDtl tbody").append(html);
		} else {
			alert ("Data not found");
		}
         
	}).done(function(data){
		$('#allParkingReportDtl').DataTable({
			dom: 'Bfrtip',
			"buttons": [{ "extend": 'excel', "text":'Export To Excel',"className": 'btn btn-default btn-xs' }],
			"order": []
		});
		
		$('#getAllParkingBtn span').html(''); 
	}).fail(function(xhr, status, error) {
		$('#getAllParkingBtn span').html(''); 
    });
}


function userParkingRepRegionMultiselect (){
	
	$('#allParkingReportMultiTower').hide();
	
	var urlGetUsers = PAGECONTEXT_GV+"getTowerListUserWise?userid="+$('#userid').val()+"&region=null";	
	var uniqueRegion = [];
	var optionTower = ''; 
	
	$.getJSON(urlGetUsers, function (data) {
		$.each(data, function (index, value) {  
            if(uniqueRegion.indexOf(value.region__c) === -1){
                //uniqueId.push(value.projectid);
                uniqueRegion.push(value.region__c);
            }        
         });
		
		for(j = 0; j< uniqueRegion.length; j++){
			
			optionTower = optionTower+"<option  value='"+uniqueRegion[j]+"'>"+uniqueRegion[j];
			optionTower = optionTower+"</option>"
		}
		 
	}).done(function() {
		$("#allParkingReportMultiRegion").append(optionTower);
		$('#allParkingReportMultiRegion').multiselect({
			maxHeight: '200',
			allSelectedText: 'All',
			enableClickableOptGroups: true,
			includeSelectAllOption: true,
			enableFiltering: true,
			enableCaseInsensitiveFiltering: true,
			buttonWidth: '100%',
			onChange: function(option, checked, select) {
				
				var region = $('#allParkingReportMultiRegion').find('option:selected');
				var selectedRegion = [];
				$(region).each(function(index, brand){
					selectedRegion.push($(this).val());
				});
				
				userParkingReptowermultiselect(selectedRegion);
				
				$('#allParkingReportMultiTower').show();
		    }
		});
	});
}

function userParkingReptowermultiselect (selectedRegion){
	if (selectedRegion != "") {
		selectedRegion = selectedRegion.join(",");
	} else {
		selectedRegion = "";
	}
	var urlGetUsers = PAGECONTEXT_GV+"getTowerListUserWise?userid="+$('#userid').val()+"&region="+selectedRegion;
	
	var uniqueId = [];
	var uniqueNames = [];
	
	var defaultSelected = "";
	var optionTower = ''; 
	
	$.getJSON(urlGetUsers, function (data) {
		
		$.each(data, function (index, value) {  
            if(uniqueId.indexOf(value.projectid) === -1){
                uniqueId.push(value.projectid);
                uniqueNames.push(value.projectname);
            }        
         });
		
		for(j = 0; j< uniqueId.length; j++){
			
			optionTower = optionTower+"<optgroup label='"+uniqueNames[j]+"'>"
			
			$.each(data, function (index, value) {
				 if(uniqueId[j] == value.projectid) {
					 optionTower = optionTower+"<option value="+value.tower_sfid+" "+defaultSelected+">"+value.tower_name+"</option>";
				 }
			});
			
			optionTower = optionTower+"</optgroup>"
		} 	
	}).done(function() {
		$("#allParkingReportMultiTower").html("");
		$('#allParkingReportMultiTower').multiselect('destroy');
		$("#allParkingReportMultiTower").append(optionTower);
		$('#allParkingReportMultiTower').multiselect({
			maxHeight: '200',
			allSelectedText: 'All',
			enableClickableOptGroups: true,
			includeSelectAllOption: true,
			enableFiltering: true,
			enableCaseInsensitiveFiltering: true,
			buttonWidth: '100%'
		});
		
		$('#allParkingReportMultiTower').multiselect('refresh');
	});
}