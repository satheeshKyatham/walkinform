$(document).ready(function () {
	$('#changeProjectRole').click(function(e) {
	    e.stopPropagation();
	});
	
	var urlGetUsers = PAGECONTEXT_GV+"getProjectListUserWise?userid="+USERID_GV;	
	var option = ''; 
	
	$.getJSON(urlGetUsers, function (data) {
		var sourceType = '"HEADER_COMMON"';
		var defaultSelected = "";
		option = "<select class='inputLabel' onchange='onProjectSelect("+sourceType+")' id='projectSelected' style='text-transform: capitalize; background-color: #f6f6f6;  width: 100%;    min-height: 33px;    margin-bottom: 5px;'> <option>Select Project</option>";
		$.each(data, function (index, value) {
			
			/*if(value.projectId == PROID_GV){
				defaultSelected = "selected";
			} else {
				defaultSelected = "";
			}*/
			
			option = option+"<option value="+value.projectId+" name="+value.region__c+" "+defaultSelected+">"+value.projectName+"</option>";
			
		});		
		option=option+"</select>";
	}).done(function() {
		$("#headerProjectDD").append("");
		$("#headerProjectDD").append(option);
	});
	
	if (FOYRAPI_GV != "null"){
		$('#foyrTab').show();
		$('#foyrFram').html('<iframe src="'+FOYRAPI_GV+'"  style="width:100%; height:500px;"></iframe>');
	} else {
		$('#foyrTab').hide();
	}
	
});

var optionDtl = '';

function userprojectmultiselect (){
	var urlGetUsers = "getProjectListUserWise?userid="+$('#userid').val();	
	$.getJSON(urlGetUsers, function (data) {
		var defaultSelected = "";
		
		$.each(data, function (index, value) {
			if (value.projectId == $('#projectid').val()) {
				defaultSelected = "selected";
			} else {
				defaultSelected = "";
			}
			optionDtl = optionDtl+"<option value="+value.projectId+" name="+value.region__c+" "+defaultSelected+">"+value.projectName+"</option>";
		});		
	}).done(function() {
		$(".userMultiselectProject").append(optionDtl);
		$('.userMultiselectProject').multiselect({
			maxHeight: '200',
			allSelectedText: 'All',
			includeSelectAllOption: true,
			enableFiltering: true,
			enableCaseInsensitiveFiltering: true
		});
	});
}


function projectUserList () {
	var urlGetUsers = PAGECONTEXT_GV+"getUserProjectMapping?projectid="+PROSFID_GV;
	$('#otpRequestorUser').empty();
	$.getJSON(urlGetUsers, function (data) {
		$('#otpRequestorUser').append("<option value=''>Select</option>"); 
		$.each(data, function (index, value) {
			$('#otpRequestorUser').append("<option value="+value.user_id+">"+value.user_name+"</option>");  
		});		
	}).done(function() {
		$('#otpRequestorUser').multiselect({
			maxHeight: '200',
			buttonWidth: '100%',
			allSelectedText: 'All',
			includeSelectAllOption: true,
			enableFiltering: true,
			enableCaseInsensitiveFiltering: true
		});
	});
}

function otpModal(){
	$('#OTPModal').modal('show');
	projectUserList();
}

$("#getAdminOTP").click(function() {
	getAdminOTPData();
});
/*function getAdminOTPData() {
	$("#getAdminOTP").prop('disabled', true);
	$.get(PAGECONTEXT_GV+"getAdminOTP", {"mobileno" :$('#mobile_num').val(), "projectsfid": PROSFID_GV, "loggedinuserid": USERID_GV, "otprequesterid": $('#otpRequestorUser').val()},
	function(data) {	
		$("#otpNum").text("");
		if(data.status=="STATUS_OK") {
			$("#otpNum").text(data.otp);
			$("#getAdminOTP").prop('disabled', false);
		} else {
			$("#otpNum").text(data.error_msg);
			$("#getAdminOTP").prop('disabled', false);
		}
	});
}*/

function getAdminOTPData() {
	$("#getAdminOTP").prop('disabled', true);
	$.ajax({
	    url: PAGECONTEXT_GV+'getAdminOTP',
	    data: {mobileno : $('#mobile_num').val(),
	    		projectsfid : PROSFID_GV,
	    		loggedinuserid : USERID_GV,
	    		otprequesterid : $('#otpRequestorUser').val()
	    },
	    type: 'POST',
	    success: function(data) { 
	    	$("#otpNum").text("");
	    	if(data.status=="STATUS_OK") {
				$("#otpNum").text(data.otp);
				$("#getAdminOTP").prop('disabled', false);
			} else {
				$("#otpNum").text(data.error_msg);
				$("#getAdminOTP").prop('disabled', false);
			} 
	    },
	    error: function(data) {
	    	alert ("Error");
	    }
	});
}

function checkDuplicate(id){			
	//Create array of input values
	var allValueArray =  $('#'+id).find('.checkDuplicate').map(function() {
		if ($(this).val() != '')

		return $(this).val()
	}).get();

	//Create array of duplicates if there are any
	var duplicateValueArray = allValueArray.filter(function(element, pos) {
		if(allValueArray.indexOf(element) != pos){
			return true;
		} else{
			return false;
		}
	});

	//check length of duplicate array, if any duplicate element found it is stored in duplicateValueArray after filter out.
	if (duplicateValueArray.length != 0){
		return false;
	} else{
		return true;
	}
}


function usertowermultiselect (selectedRegion){
	
	if (selectedRegion != "") {
		selectedRegion = selectedRegion.join(",");
	} else {
		selectedRegion = "";
	}
	
	//var urlGetUsers = PAGECONTEXT_GV+"getTowerListUserWise?userid="+$('#userid').val()+"&region="+selectedRegion.join(",");
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
		
		//alert (uniqueId.length);
		
		
		/*var defaultSelected = "";
		option = '';
		$.each(data, function (index, value) {
			option = option+"<option value="+value.tower_sfid+" "+defaultSelected+">"+value.tower_name+"</option>";
		});	*/	
	}).done(function() {
		$(".userMultiselectTower").html("");
		$('.userMultiselectTower').multiselect('destroy');
		$(".userMultiselectTower").append(optionTower);
		$('.userMultiselectTower').multiselect({
			maxHeight: '200',
			allSelectedText: 'All',
			enableClickableOptGroups: true,
			includeSelectAllOption: true,
			enableFiltering: true,
			enableCaseInsensitiveFiltering: true,
			buttonWidth: '100%'
		});
		
		$('.userMultiselectTower').multiselect('refresh');
	});
}

 

function userRegionMultiselect (regionSelector){
	
	$('#allReportMultiTower').hide();
	
	var urlGetUsers = PAGECONTEXT_GV+"getTowerListUserWise?userid="+$('#userid').val()+"&region=null";	
	
	//var uniqueId = [];
	var uniqueRegion = [];
	
	//var defaultSelected = "";
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
		$(".userMultiselectRegion").append(optionTower);
		$('.userMultiselectRegion').multiselect({
			maxHeight: '200',
			allSelectedText: 'All',
			enableClickableOptGroups: true,
			includeSelectAllOption: true,
			enableFiltering: true,
			enableCaseInsensitiveFiltering: true,
			buttonWidth: '100%',
			onChange: function(option, checked, select) {
				
				var region = $('#'+regionSelector).find('option:selected');
				var selectedRegion = [];
				$(region).each(function(index, brand){
					selectedRegion.push($(this).val());
				});
				
				usertowermultiselect(selectedRegion);
				
				$('#allReportMultiTower').show();
		    }
		});
	});
}