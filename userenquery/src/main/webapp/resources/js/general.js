$(document).ready(function () {
	$('#changeProjectRole').click(function(e) {
	    e.stopPropagation();
	});
	
	var urlGetUsers = PAGECONTEXT_GV+"getProjectListUserWise?userid="+USERID_GV;	
	 
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
			
			option = option+"<option value="+value.projectId+" "+defaultSelected+">"+value.projectName+"</option>";
			
		});		
		option=option+"</select>";
	}).done(function() {
		$("#headerProjectDD").append("");
		$("#headerProjectDD").append(option);
	});
});


function userprojectmultiselect (){
	var urlGetUsers = "getProjectListUserWise?userid="+$('#userid').val();	
	$.getJSON(urlGetUsers, function (data) {
		var defaultSelected = "";
		option = '';
		$.each(data, function (index, value) {
			if (value.projectId == $('#projectid').val()) {
				defaultSelected = "selected";
			} else {
				defaultSelected = "";
			}
			option = option+"<option value="+value.projectId+" "+defaultSelected+">"+value.projectName+"</option>";
		});		
	}).done(function() {
		$(".userMultiselectProject").append(option);
		$('.userMultiselectProject').multiselect({
			maxHeight: '200',
			allSelectedText: 'All',
			includeSelectAllOption: true
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