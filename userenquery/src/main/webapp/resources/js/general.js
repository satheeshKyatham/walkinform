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