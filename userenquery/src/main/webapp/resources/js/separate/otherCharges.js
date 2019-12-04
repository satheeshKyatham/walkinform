var pageContext = $("#pageContext").val()+"/";	

function addOtherCharge () {
	$.post(pageContext+"insertOtherCharge",{"chargeType":$('#chargeType').val(),  "ratePerUnit":$('#ratePerUnit').val(),    "percentage":$('#percentage').val(),    "region":$('#regionList').val(),    "projectName":$('#projectDataList option:selected').text(),       "project":$('#projectDataList').val(),     "tower":$('#towerDataList').val(),   "towerName":$('#towerDataList option:selected').text(),   "typology":$('#typoDataList').val(),        "chargeName":$('#chargeName').val(), "fixedCharge":$('#chargeRate').val()},function(data){				 
		
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

function towerDataList (e, source) {
	$('#towerDataList').empty();	
	
	var projectNameVal = $("#projectDataList").val();
	var urlTowerData = pageContext+"getTower?project_code="+projectNameVal;
	
	$.getJSON(urlTowerData, function (data) {
		$('#towerDataList').append('<option value="">Select</option>');
		$.each(data, function (index, value) {
			$('#towerDataList').append("<option value='"+value.tower_code__c+"'>"+value.tower_name__c+"</option>");
		});					
	}).done(function() {
	
	});
}

function typoDataList () {
	$('#typoDataList').empty();
	
	var towerCode = $('#towerDataList').val();
	var projectNameVal = $('#projectDataList').val();
	var urlProject = pageContext+"getunittype?project_code="+projectNameVal+"&tower_code="+towerCode+"&floor_code="
	
	$('#typoDataList').append("<option value=''>Select</option> ");
	
	$.getJSON(urlProject, function (data) {
		$.each(data, function (index, value) {
			$('#typoDataList').append("<option value='"+value.propstrength__unit_type__c+"'>"+value.propstrength__unit_type__c+"</option>");
		});					
	}).done(function() {
		
	});	
}

function chargeType (e) {
	if (e.value == 'Fixed'){
		$('#chargeRateCol').show();
		$('#ratePerUnitCol').hide();
		
		$('#chargeRate').val('0');
		$('#ratePerUnit').val('0');
	} else if (e.value == 'Flexible'){
		$('#chargeRateCol').hide();
		$('#ratePerUnitCol').show();

		$('#chargeRate').val('0');
		$('#ratePerUnit').val('0');
	}
}