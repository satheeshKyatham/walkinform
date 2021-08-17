$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});

var today = new Date();
var currentDate = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
$('#edrDate').val(currentDate); 

var pageContext = $("#pageContext").val()+"/";	
 
//projectDataListEDR ();  

function projectDataListEDR (){
	if ($('#userid').val() != "null") {
		$('#projectDataListEDR').empty();	
		$('#milestoneId').empty();	
		$('#towerMstEDR').empty();
		var urlTower = pageContext+"getProjectListUserWise?userid="+$('#userid').val();
		var uniqueId = [];
		var optionTower = ''; 
		
		$.getJSON(urlTower, function (data) {
			$.each(data, function (index, value) {  
				if(uniqueId.indexOf(value.region__c) === -1){
				    uniqueId.push(value.region__c); 
				}        
			});
			for(j = 0; j< uniqueId.length; j++){
				optionTower = optionTower+"<optgroup label='"+uniqueId[j]+"'>"
				$.each(data, function (index, value) {
					 if(uniqueId[j] == value.region__c) {
						 optionTower = optionTower+"<option data-region="+value.region__c+" value="+value.projectId+">"+value.projectName+"</option>";
					 }
				});
				optionTower = optionTower+"</optgroup>"
			}
		}).done(function() {
			$('#projectDataListEDR').append('<option value="">Select</option>' + optionTower);
		});
	}  
}

function towerListEDR (e, source) {
	
	//$("#floorMst").html("");
	//$("#floorMst").html("<option value=''>Select</option>");
	//$('#floorMst').multiselect('destroy');
	//$('#floorMst').multiselect('refresh');
	
	
	
	
	
	
	$('#towerMstEDR').empty();	
	var projectNameVal = $("#projectDataListEDR").val();
	
	var urlTower = pageContext+"getTowerMaster?project_code="+projectNameVal;
	 
	var optionTower = ''; 
	
	optionTower = "<option value=''>Select</option>";
	
	$.getJSON(urlTower, function (data) {
			$.each(data, function (index, value) {
					optionTower = optionTower+"<option name='"+value.tower_name__c+"' value='"+value.sfid+"'>"+value.tower_name__c+"</option>";
			}); 			
	}).done(function() {
		$("#towerMstEDR").html("");
		 
		$("#towerMstEDR").append(optionTower);
	});
}

function floorListEDR (){
	
	var tower = $('#towerMstEDR').find('option:selected');
	var selectedTower = [];
	$(tower).each(function(index, brand){
		selectedTower.push($(this).val());
	});
	
	
	if (selectedTower != "") {
		selectedTower = selectedTower.join(",");
	} else {
		selectedTower = "";
		return;
	}
	
	var urlGetUsers = pageContext+"getFloorMaster?towersfid="+selectedTower;
	
	var uniqueId = [];
	var uniqueNames = [];
	 
	var optionFloor = ''; 
	
	$.getJSON(urlGetUsers, function (data) {
		
		$.each(data, function (index, value) {  
            if(uniqueId.indexOf(value.propstrength__tower__c) === -1){
                uniqueId.push(value.propstrength__tower__c);
                uniqueNames.push(value.propstrength__tower_name__c);
            }        
         });
		
		for(j = 0; j< uniqueId.length; j++){
			
			optionFloor = optionFloor+"<optgroup label='"+uniqueNames[j]+"'>"
			
			$.each(data, function (index, value) {
				 if(uniqueId[j] == value.propstrength__tower__c) {
					 optionFloor = optionFloor+"<option value="+value.sfid+"  >"+value.propstrength__floor_name__c+"</option>";
				 }
			});
			
			optionFloor = optionFloor+"</optgroup>"
		} 
	}).done(function() {
		$("#floorMstEDR").html("");
		$('#floorMstEDR').multiselect('destroy');
		$("#floorMstEDR").append(optionFloor);
		$('#floorMstEDR').multiselect({
			maxHeight: '200',
			allSelectedText: 'All',
			enableClickableOptGroups: true,
			includeSelectAllOption: true,
			enableFiltering: true,
			enableCaseInsensitiveFiltering: true,
			buttonWidth: '100%'
		});
		
		$('#floorMstEDR').multiselect('refresh');
	});
}





function bookingList (){
	var tower = $('#floorMstEDR').find('option:selected');
	var selectedTower = [];
	$(tower).each(function(index, brand){
		selectedTower.push($(this).val());
	});
	
	
	if (selectedTower != "") {
		selectedTower = selectedTower.join(",");
	} else {
		selectedTower = "";
		return;
	}
	
	var urlGetUsers = pageContext+"getFloorWiseBooking?floorsfid="+selectedTower+"&projectsfid="+$("#projectDataListEDR option:selected").val();
	
	var uniqueId = [];
	var uniqueNames = [];
	 
	var optionFloor = ''; 
	
	$.getJSON(urlGetUsers, function (data) {
		
		$.each(data, function (index, value) {  
            if(uniqueId.indexOf(value.propstrength__floor__c) === -1){
                uniqueId.push(value.propstrength__floor__c);
                uniqueNames.push(value.floor_name__c);
            }        
         });
		
		for(j = 0; j< uniqueId.length; j++){
			
			optionFloor = optionFloor+"<optgroup label='"+uniqueNames[j]+"'>"
			
			$.each(data, function (index, value) {
				 if(uniqueId[j] == value.propstrength__floor__c) {
					 optionFloor = optionFloor+"<option value="+value.sfid+"  >"+value.propstrength__primary_applicant_name__c + " / " +value.sap_customer_code__c+"</option>";
				 }
			});
			
			optionFloor = optionFloor+"</optgroup>"
		} 
	}).done(function() {
		$("#bookingList").html("");
		$('#bookingList').multiselect('destroy');
		$("#bookingList").append(optionFloor);
		$('#bookingList').multiselect({
			maxHeight: '200',
			allSelectedText: 'All',
			enableClickableOptGroups: true,
			includeSelectAllOption: true,
			enableFiltering: true,
			enableCaseInsensitiveFiltering: true,
			buttonWidth: '100%'
		});
		
		$('#bookingList').multiselect('refresh');
	});
}




function updateEDR () {
	document.getElementById('updateEDRBtn').style.pointerEvents = 'none'; 
	//BTNLOADING.loading("edrUpdateBtn", "show");
	$("#edrUpdateBtn i").show();
	
	var project = $('#bookingList option:selected');
	var selectedBooking = [];
	$(project).each(function(index, brand){
		selectedBooking.push($(this).val());
	});
	
	var towerMulti = $('#towerMstEDR option:selected');
	var selectedTower = [];
	$(towerMulti).each(function(index, brand){
		selectedTower.push($(this).val());
	});
	
	var floorMulti = $('#floorMstEDR option:selected');
	var selectedFloor = [];
	$(floorMulti).each(function(index, brand){
		selectedFloor.push($(this).val());
	});
	
	if ($("#projectDataListEDR option:selected").val() == undefined || $("#projectDataListEDR option:selected").val() == '' || $("#towerMstEDR option:selected").val() == undefined || $("#floorMstEDR option:selected").val() == undefined ||  selectedBooking=='' || selectedBooking==null || $('#edrDate').val() == "") {
		//BTNLOADING.loading("edrUpdateBtn", "hide");
		$("#edrUpdateBtn i").hide();
		
		swal({
			text: "Please enter all mandatory fields.",
			type: "warning" 
		});
		
    	/*Toast.fire({
			icon: 'warning',
			title: 'Please enter all mandatory fields.'
		})*/
		document.getElementById('updateEDRBtn').style.pointerEvents = 'auto';
    	return false;
	}
	
  	$.ajax({
	    url: pageContext+'updateEDR',
	    data: {projectsfid : $("#projectDataListEDR option:selected").val(), 
	    	bookingsfid:selectedBooking.join(","),
	    	edrDate:$("#edrDate").val(),
	    	selectedTower:selectedTower.join(","),
	    	selectedFloor:selectedFloor.join(",")
	    },
	    type: 'POST',
	    success: function(data) { 
	    	var obj =JSON.parse(data);
			
			if (obj!=null && obj.status =="STATUS_OK") {
				swal({
					text: obj.error_msg,
					type: "success" 
				});
				
				/*Toast.fire({
        			icon: 'success',
        			title: obj.error_msg
        		})*/
        		$("#edrUpdateBtn i").hide();
				//BTNLOADING.loading("edrUpdateBtn", "hide"); 
        		
        		projectDataListEDR (); 
        		floorRefreshEDR (); 
        		customerRefreshEDR (); 
        		edrDateRefresh ();
        		
			} else {
				swal({
					text: obj.error_msg,
					type: "warning" 
				});
				/*Toast.fire({
        			icon: 'warning',
        			title: obj.error_msg
        		})*/
        		$("#edrUpdateBtn i").hide();
				//BTNLOADING.loading("edrUpdateBtn", "hide");
			}
			document.getElementById('updateEDRBtn').style.pointerEvents = 'auto';
	    },
	    error: function(data) {
	    	$("#edrUpdateBtn i").hide();
	    	//BTNLOADING.loading("edrUpdateBtn", "hide");
	    	document.getElementById('updateEDRBtn').style.pointerEvents = 'auto';
	    }
	});
}


function floorRefreshEDR () {
	$("#floorMstEDR").html("");
	$('#floorMstEDR').multiselect('destroy');
	$("#floorMstEDR").append("");
	$('#floorMstEDR').multiselect({
		maxHeight: '200',
		allSelectedText: 'All',
		enableClickableOptGroups: true,
		includeSelectAllOption: true,
		enableFiltering: true,
		enableCaseInsensitiveFiltering: true,
		buttonWidth: '100%'
	});
	
	$('#floorMstEDR').multiselect('refresh');
}

function customerRefreshEDR () {
	$("#bookingList").html("");
	$('#bookingList').multiselect('destroy');
	$("#bookingList").append("");
	$('#bookingList').multiselect({
		maxHeight: '200',
		allSelectedText: 'All',
		enableClickableOptGroups: true,
		includeSelectAllOption: true,
		enableFiltering: true,
		enableCaseInsensitiveFiltering: true,
		buttonWidth: '100%'
	});
	
	$('#bookingList').multiselect('refresh');
}

function edrDateRefresh () {
	$("#edrDate").val("");
}