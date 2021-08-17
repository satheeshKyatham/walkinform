$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});

var today = new Date();
var currentDate = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
$('#edpDate').val(currentDate); 

var pageContext = $("#pageContext").val()+"/";	
 
//projectDataList ();  

function projectDataList (){
	if ($('#userid').val() != "null") {
		$('#projectDataList').empty();	
		$('#milestoneId').empty();	
		$('#towerMst').empty();
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
			$('#projectDataList').append('<option value="">Select</option>' + optionTower);
		});
	}  
}

function towerList (e, source) {
	$('#towerMst').empty();	
	var projectNameVal = $("#projectDataList").val();
	
	var urlTower = pageContext+"getTowerMaster?project_code="+projectNameVal;
	 
	var optionTower = ''; 
	
	$.getJSON(urlTower, function (data) {
			$.each(data, function (index, value) {
					optionTower = optionTower+"<option name='"+value.tower_name__c+"' value='"+value.sfid+"'>"+value.tower_name__c+"</option>";
			}); 			
	}).done(function() {
		$("#towerMst").html("");
		$('#towerMst').multiselect('destroy');
		$("#towerMst").append(optionTower);
		$('#towerMst').multiselect({
			maxHeight: '200',
			allSelectedText: 'All',
			enableClickableOptGroups: true,
			includeSelectAllOption: true,
			enableFiltering: true,
			enableCaseInsensitiveFiltering: true,
			buttonWidth: '100%'
		});
		
		$('#towerMst').multiselect('refresh');
	});
}






function floorList (){
	
	var tower = $('#towerMst').find('option:selected');
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
		$("#floorMst").html("");
		$('#floorMst').multiselect('destroy');
		$("#floorMst").append(optionFloor);
		$('#floorMst').multiselect({
			maxHeight: '200',
			allSelectedText: 'All',
			enableClickableOptGroups: true,
			includeSelectAllOption: true,
			enableFiltering: true,
			enableCaseInsensitiveFiltering: true,
			buttonWidth: '100%'
		});
		
		$('#floorMst').multiselect('refresh');
	});
}



function updateEDP () {
	
	document.getElementById('updateEDPBtn').style.pointerEvents = 'none'; 
	
	//BTNLOADING.loading("edpUpdateBtn", "show");
	
	$("#edpUpdateBtn i").show();
	
	var project = $('#floorMst option:selected');
	var selectedFloor = [];
	$(project).each(function(index, brand){
		selectedFloor.push($(this).val());
	});
	
	var towerMulti = $('#towerMst option:selected');
	var selectedTower = [];
	$(towerMulti).each(function(index, brand){
		selectedTower.push($(this).val());
	});
	
	if ($("#projectDataList option:selected").val() == undefined || $("#projectDataList option:selected").val() == '' || $("#towerMst option:selected").val() == undefined ||  selectedFloor=='' || selectedFloor==null || $('#edpDate').val() == "") {
		$("#edpUpdateBtn i").hide();
		
		//BTNLOADING.loading("edpUpdateBtn", "hide");
		swal({
			//title: "Please enter all mandatory fields.",
			text: "Please enter all mandatory fields.",
			type: "warning",
			//allowOutsideClick: false,
			//showConfirmButton: false
		});
		document.getElementById('updateEDPBtn').style.pointerEvents = 'auto'; 
    	return false;
	}
	
  	$.ajax({
	    url: pageContext+'updateEDP',
	    data: {projectsfid : $("#projectDataList option:selected").val(), 
	    	floors:selectedFloor.join(","),
	    	tower:selectedTower.join(","),
	    	edpDate:$("#edpDate").val()
	    },
	    type: 'POST',
	    success: function(data) { 
	    	var obj =JSON.parse(data);
			
			if (obj!=null && obj.status =="STATUS_OK") {
				
				swal({
					//title: "Please enter all mandatory fields.",
					text: obj.error_msg,
					type: "success",
					//allowOutsideClick: false,
					//showConfirmButton: false
				});
				
				/*Toast.fire({
        			icon: 'success',
        			title: obj.error_msg
        		})*/
				 
				//BTNLOADING.loading("edpUpdateBtn", "hide"); 
				$("#edpUpdateBtn i").hide();
				
				projectDataList (); 
				towerRefresh (); 
				floorRefresh (); 
				edpDateRefresh ();
				
			} else {
				swal({
					//title: "Please enter all mandatory fields.",
					text: obj.error_msg,
					type: "warning",
					//allowOutsideClick: false,
					//showConfirmButton: false
				});
				
				/*Toast.fire({
        			icon: 'warning',
        			title: obj.error_msg
        		})*/
				 
				//BTNLOADING.loading("edpUpdateBtn", "hide");
				$("#edpUpdateBtn i").hide();
			}
			document.getElementById('updateEDPBtn').style.pointerEvents = 'auto';
	    },
	    error: function(data) {
	    	//BTNLOADING.loading("edpUpdateBtn", "hide");
	    	$("#edpUpdateBtn i").hide();
	    	document.getElementById('updateEDPBtn').style.pointerEvents = 'auto';
	    }
	});
}


function floorRefresh () {
	$("#floorMst").html("");
	$('#floorMst').multiselect('destroy');
	$("#floorMst").append("");
	$('#floorMst').multiselect({
		maxHeight: '200',
		allSelectedText: 'All',
		enableClickableOptGroups: true,
		includeSelectAllOption: true,
		enableFiltering: true,
		enableCaseInsensitiveFiltering: true,
		buttonWidth: '100%'
	});
	
	$('#floorMst').multiselect('refresh');
}


function towerRefresh () {
	$("#towerMst").html("");
	$('#towerMst').multiselect('destroy');
	$("#towerMst").append("");
	$('#towerMst').multiselect({
		maxHeight: '200',
		allSelectedText: 'All',
		enableClickableOptGroups: true,
		includeSelectAllOption: true,
		enableFiltering: true,
		enableCaseInsensitiveFiltering: true,
		buttonWidth: '100%'
	});
	
	$('#towerMst').multiselect('refresh');
}

function edpDateRefresh () {
	$("#edpDate").val("");
}