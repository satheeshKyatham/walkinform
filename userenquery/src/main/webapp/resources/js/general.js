$(document).ready(function () {
	var today = new Date();
	var currentDate = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
	$('.cpEngmntFromDateOffer').val(currentDate);
	$('.cpEngmntToDateOffer').val(currentDate);
	$('#visit_date').val(currentDate);
	
	var cpDate = new Date();
	cpDate.setDate(cpDate.getDate() - 7);
    var cpEngmntMinDate = cpDate.getFullYear() + '-' + ('0' + (cpDate.getMonth() + 1)).slice(-2) + '-' + ('0' + cpDate.getDate()).slice(-2);
    $("#visit_date").attr("min", cpEngmntMinDate);
	
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


function getDrupalProjectDtl () {
	//var pageContext = $("#pageContext").val()+"/";
	$('#collateral').html("<div style='text-align:center'><i class='fa fa-spinner fa-spin' style='font-size: 26px;'></i></div>");
	$.post(PAGECONTEXT_GV+"getDrupalProjectDtl",{"projectsfid":PROSFID_GV},function(data){
		var html = '';
		var obj =JSON.parse(data);
		 
		var btnDisBrochure = "";
		var btnDisPdf = "";
		var btnDisVid = "";
		var btnDisFoyr = "";
		
		var hrefDisBrochure = "";
		var hrefDisPdf = "";
		var hrefDisVid = "";
		var hrefDisFoyr = "";
		
		$('#collateral').empty();
		 
		$('#collateral').html("<div style='text-align:center'><i class='fa fa-spinner fa-spin' style='font-size: 26px;'></i></div>");
		
			html +=  "<div class='collateralCol'>";
			
			if (obj.bannerStatus != "STATUS_NOTOK" && obj.banner != null && obj.banner != "null") {
				html += "<img class='full' style='width:100%' src='"+obj.banner+"' alt=' image not found'/>"; 
			}
			
			if (obj.projectStatus != "STATUS_NOTOK") {
				
				if (obj.project_dis != null && obj.project_dis != "null" && obj.project_dis != "") {
					html +=	"<h4>Project Description</h4>" + 
							"<div class='clearfix'></div>" + 	
							"<p>"+obj.project_dis+"</p>"; 			
				}
				
				if (obj.brochure_url == null || obj.brochure_url == "" || obj.brochure_url == "null") {
					btnDisBrochure = "disableCol";
				} else {
					hrefDisBrochure = obj.brochure_url;
				}
				
				if (obj.other_pdf_url == null || obj.other_pdf_url == "" || obj.other_pdf_url == "null") {
					btnDisPdf = "disableCol";
				} else {
					hrefDisPdf = obj.other_pdf_url;
				}
				
				if (obj.proj_vid == null || obj.proj_vid == "" || obj.proj_vid == "null") {
					btnDisVid = "disableCol";
				} else {
					hrefDisVid = obj.proj_vid;
				}
				
				if (obj.foyr_url == null || obj.foyr_url == "" || obj.foyr_url == "null") {
					btnDisFoyr = "disableCol";
				} else {
					hrefDisFoyr = obj.foyr_url;
				}
				
				html +="<div class='row'>" + 
						"<div class='col-md-3 col-xs-6'>" + 
							"<a class='btn btn-primary btnBlack full "+ btnDisBrochure+"' style='width:100%;' href='"+hrefDisBrochure+"' target='_blank' >" + 
								"<i class='fa fa-image'></i> View Brochure" + 
							"</a>" + 
						"</div>	" + 
						"<div class='col-md-3 col-xs-6'>	" + 
							"<a class='btn btn-primary btnBlack full "+ btnDisPdf+"' style='width:100%;' href='"+hrefDisPdf+"' target='_blank' >" + 
								"<i class='fa fa-file'></i> View PDF" + 
							"</a>" + 
						"</div>" + 
						"<div class='col-md-3 col-xs-6'>	" + 
							"<a class='btn btn-primary btnBlack full "+ btnDisVid+"' style='width:100%;' href='"+hrefDisVid+"' target='_blank' >" + 
								"<i class='fa fa-play-circle'></i> View AV" + 
							"</a>" + 
						"</div>" + 
						"<div class='col-md-3 col-xs-6'>	" + 
							"<a class='btn btn-primary btnBlack full "+ btnDisFoyr+"' style='width:100%;' href='"+hrefDisFoyr+"' target='_blank' >" + 
								"<i class='fa fa-street-view'></i> 3D Visualisation" + 
							"</a>" + 
						"</div>	" + 
						"<div class='clearfix'></div>" + 
					"</div>";
			}
			
			html +="<div class='clearfix'></div> </div>" ;
			$('#collateral').empty();	
			$('#collateral').append(html);
		
	}).done(function(obj){ });	
}


function towerCustomList (){
	if ($('#projectid').val() == "a1l6F000003TRcCQAW") {
		var html="";
		
		html=html+'<option value="SO03">Godrej Nature+ Tower-C</option>';
		html=html+'<option value="SO04">Godrej Nature+ Tower-D1</option>';
		html=html+'<option value="SO10">Godrej Nature+ Tower-D2</option>';
		
		return html;
	} else if ($('#projectid').val() == "a1l2s000000PJpLAAW") {
		var html="";
		
		html=html+'<option value="All">All</option>'; 
		
		return html;
	} else if ($('#projectid').val() == "a1l6F000004RvPHQA0") {
		var html="";
		
		html=html+'<option value="J079">VANAANGAN</option>'; 
		
		return html;
	} else {
		return "";
	}
}


function getCPEngmntReportDtl (source) {
	
	$('.cpEngmntSpinner span').html('<i class="fa fa-spinner fa-spin" style="color:#000"></i>'); 
	
	var project = "";
	
	project = $('#cpEngmntMultiselectProject option:selected');
	
	var selectedTower = [];
	$(project).each(function(index, brand){
		selectedTower.push($(this).val());
	});
	
	$("#cpEngmntTable").DataTable().destroy();
	$("#cpEngmntTable tbody").empty();
	
	$.post(PAGECONTEXT_GV+"getCPEngmntReport",{"reportSource":source, "userid":$('#userid').val(), "projectSfid":selectedTower.join(","), "fromDate":$('.cpEngmntFromDateOffer').val(),"toDate":$('.cpEngmntToDateOffer').val(),},function(data){                      
		$("#cpEngmntTable tbody").empty();
		var obj1 =JSON.parse(data);
		var html = '';
		var schemeType = "";
			
		if (obj1 != null) {
			for(var i=0;i<obj1.length;i++){
				
				if (obj1[i].qry_msg != "MAX_LIMIT") {
					
					html += "<tr>" +
						"<td>"+obj1[i].visit_date+"</td>" +
						"<td>"+obj1[i].project_name+"</td>" +
						"<td>"+obj1[i].cp_name+"</td>" +
						"<td>"+obj1[i].meeting_place+"</td>" +
						"<td>"+obj1[i].topic+"</td>" +
						"<td>"+obj1[i].discussed_point+"</td>" +
						"<td>"+obj1[i].user_name+"</td>" +
					" </tr>";
				
				} else {
					swal({
	                	title: "Records exceeding 5000. Please narrow down project selection or date range",
	          			text: "Requested records count is: "+obj1[i].qry_count,
	          			type: "warning",
	                });
					
					$("#swal2-title").css({"font-size": "22px"});
					return false;
				}
			}
			 
			html = html.replace(/undefined/g, " - ");
			html = html.replace(/null/g, " - ");
			$("#cpEngmntTable tbody").empty();
			$("#cpEngmntTable tbody").append(html);
		} else {
			//alert ("Data not found");
		}
	}).done(function(data){
		$('#cpEngmntTable').DataTable({
			dom: 'Bfrtip',
			"buttons": [{ "extend": 'excel', "text":'Export To Excel',"className": 'btn btn-default btn-xs' }],
			"order": []
		});
		$('.cpEngmntSpinner span').html('');
	}).fail(function(xhr, status, error) {
		$('.cpEngmntSpinner span').html(''); 
    });
}