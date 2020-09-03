$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});
var pageContext = $("#pageContext").val()+"/";	

function addschemeCharge () {
	var zeroGovtCharges = false;
	
	if($('#zeroGovtCharges').is(":checked")) {
		zeroGovtCharges = true;
    }  else {
    	zeroGovtCharges = false;
    }
	
	$.post(pageContext+"insertSchemeCharge",{"sourceName":$('#getHeaderSchemeSource option:selected').text(),  "siteName":$('#getHeaderSchemeSite option:selected').text(),      "promotionalName":$('#getHeaderSchemePromotional option:selected').text(),        "percentage":$('#percentage').val(), "absolute_amount":$('#absolute_amount').val(),  "region":$('#region_id').val(),"projectName":$('#projectDataList option:selected').text()
		,"projectid":$('#projectDataList').val(),"scheme_name":$('#scheme_name').val(),"scheme_rate":$('#scheme_rate').val() ,"zeroGovtCharges":zeroGovtCharges},function(data){				 
		
	}).done(function(data){
		swal({
			title: "Successfully Submitted",
		    text: "",
		    timer: 2000,
		    type: "success",
		});
	});
}

/*regionList();

function regionList () {
	$('#regionList').empty();	
	var urlRegionList = pageContext+"regionList?project_code=test";
	
	$.getJSON(urlRegionList, function (data) {
		$('#regionList').append('<option value="-1">Select</option>');
		$('#regionListForSM').append('<option value="-1">Select</option>');
		$('#regionListForMaster').append('<option value="-1">Select</option>');
		$.each(data, function (index, value) {
			$('#regionList').append("<option value='"+value.region__c+"'>"+value.region__c+"</option>");
			$('#regionListForSM').append("<option value='"+value.region__c+"'>"+value.region__c+"</option>");
			$('#regionListForMaster').append("<option value='"+value.region__c+"'>"+value.region__c+"</option>");
		});					
	}).done(function() {
	});
}*/

projectDataList();
function projectDataList (){
	debugger
	$('#projectDataList').empty();	
	var urlTower = pageContext+"projectListForSales?userId="+ $("#logedInuserid").val();
	/*$('#projectDataList').append('<option value="-1">Select</option>');*/
	$('#projectDataListForSM').append('<option value="-1">Select</option>');
	$('#projectDataListForMaster').append('<option value="-1">Select</option>');
	$.getJSON(urlTower, function (data) {
		$('#projectDataList').append('<option value="-1">Select</option>');
		$.each(data, function (index, value) {
			$("#region_id").val(value.region__c);
			$('#projectDataList').append("<option value='"+value.project_id+"'>"+value.project_name+ "</option>");
			$('#projectDataListForSM').append("<option value='"+value.project_id+"'>"+value.project_name+ "</option>");
			$('#projectDataListForMaster').append("<option value='"+value.project_id+"'>"+value.project_name+ "</option>");
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
/*
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
}*/

/*function chargeType (e) {
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
}*/

/*$('#zeroGovtCharges').val($(this).is(':checked'));

$('#zeroGovtCharges').change(function() {
    if($(this).is(":checked")) {
       $('#scheme_name').val('Zero Govt. Charges');
    }       
});*/






function getHeaderSchemeSource (){
    $('#getHeaderSchemeSource').empty();
    
    var url = pageContext+"getSchemeSource?projectid="+$("#projectDataList").val()+"&cp_flag_yn=";
    
	$.getJSON(url, function (data) {
		$('#getHeaderSchemeSource').append('<option value="-1">Select</option>');
		$.each(data, function (index, value) {
			$('#getHeaderSchemeSource').append('<option value='+value.id+'>'+ value.scheme+'</option>');
		});                              
	}).done(function() {
		//getSchemeSite ();
	});
}

function getHeaderSchemeSite (){
    $('#getHeaderSchemeSite').empty();
    
    var url = pageContext+"getSchemeSite?projectid="+$("#projectDataList").val()+"&cp_flag_yn=";
    
	$.getJSON(url, function (data) {
		$('#getHeaderSchemeSite').append('<option value="-1">Select</option>');
		$.each(data, function (index, value) {
			$('#getHeaderSchemeSite').append('<option value='+value.id+'>'+ value.scheme+'</option>');
		});                              
	}).done(function() {
		//getSchemePromotional ();
	});
}

function getHeaderSchemePromotional () {
    $('#getHeaderSchemePromotional').empty();
    
    var url = pageContext+"getSchemePromotional?projectid="+$("#projectDataList").val()+"&cp_flag_yn=";
    
	$.getJSON(url, function (data) {
		$('#getHeaderSchemePromotional').append('<option value="-1">Select</option>');
		$.each(data, function (index, value) {
			$('#getHeaderSchemePromotional').append('<option value='+value.id+'>'+ value.scheme+'</option>');
		});                              
	}).done(function() {
	});
}
