var pageContext = $("#pageContext").val()+"/";	

function addCarParkCharge () {
	$.post(pageContext+"insertCarparkCharges",{ "project_id":$('#projectDataList').val(), "project_name":$('#projectDataList :selected').text(), "region_id":$('#regionList').val(), "region_name":$('#regionList :selected').text(),       "parkTypeName":$('#parkType :selected').text(),  "parkType":$('#parkType').val(),   "carParkAmount":$('#carParkAmount').val() },function(data){				 
		
	}).done(function(data){
		swal({
			title: "Successfully Submitted",
		    text: "",
		    timer: 2000,
		    type: "success",
		});
	});
}

regionList();

function regionList () {
	$('#regionList').empty();	
	var urlRegionList = pageContext+"regionList?project_code=test";
	
	$.getJSON(urlRegionList, function (data) {
		$('#regionList').append('<option value="">Select</option>');
		$.each(data, function (index, value) {
			$('#regionList').append("<option value='"+value.region__c+"'>"+value.region__c+"</option>");
		});					
	}).done(function() {
	});
}

function projectDataList (){
	$('#projectDataList').empty();	
	var urlTower = pageContext+"projectDataList?region="+$('#regionList').val();
	$.getJSON(urlTower, function (data) {
		$('#projectDataList').append('<option value="">Select</option>');
		$.each(data, function (index, value) {
			$('#projectDataList').append("<option value='"+value.sfid+"'>"+value.name+  " / " +value.propstrength__project_code__c+ "</option>");
		});					
	}).done(function() {
	});
}



