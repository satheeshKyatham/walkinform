$(document).ready(function() {
	/*var today = new Date();
	var currentDate = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
	$('.cpEngmntFromDateOffer').val(currentDate);
	$('.cpEngmntToDateOffer').val(currentDate);
	$('#visit_date').val(currentDate);*/
	userprojectmultiselect ();
});

function cpEngProjectDD () {
	var urlGetUsers = PAGECONTEXT_GV+"getProjectListUserWise?userid="+USERID_GV;	
	var option = ''; 
	
	$.getJSON(urlGetUsers, function (data) {
		 
		var defaultSelected = "";
		option = "<option value=''>Select Project</option>";
		$.each(data, function (index, value) {
			
			if (value.projectId == $('#projectid').val()) {
				defaultSelected = "selected";
			} else {
				defaultSelected = "";
			}
			
			/*if(value.projectId == PROID_GV){
				defaultSelected = "selected";
			} else {
				defaultSelected = "";
			}*/
			
			option = option+"<option value="+value.projectId+" name="+value.region__c+" "+defaultSelected+">"+value.projectName+"</option>";
			
		});		
		//option=option+"</select>";
	}).done(function() {
		$("#cpEngProjectDD").append("");
		$("#cpEngProjectDD").append(option);
	});  
}

function insertCPEngmnt () {
	
	var validate=checkValidationOnSubmit("cpEngmntForm");
	if(validate) {
		var formData = new FormData();
		formData.append('visit_date', $('#visit_date').val().replace(/-/gi, "/"));
		formData.append('project_sfid', $('#cpEngProjectDD').val());
		formData.append('cp_name', $('#cp_name').val());
		formData.append('meeting_place', $('#meeting_place').val());
		formData.append('topic', $('#topic').val());
		formData.append('discussed_point', $('#discussed_point').val());
		formData.append('createdby', $('#userid').val());
		formData.append('updatedby', $('#userid').val());
		 
		$.ajax({
			url : PAGECONTEXT_GV+"insertCPEngmnt",
			type: "POST",
			data : JSON.stringify(Object.fromEntries(formData)),
			dataType : 'json',
			contentType: 'application/json'
		}).done(function(response){
			if (response.status == "STATUS_OK"){
				swal({
		        	title: "Successfully submitted",
		  			//text: "Requested records count is: "+obj1[i].qry_count,
		  			type: "success",
		        });
			} else {
				swal({
		        	title: "Unable to Submit a Record.",
		  			//text: "Requested records count is: "+obj1[i].qry_count,
		  			type: "warning",
		        });
			}
		});
	}
}

