$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});
var pageContext = $("#pageContext").val()+"/";	


/*regionList();

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
}*/
projectDataList();
function projectDataList (){
	$('#projectDataList').empty();	
	var urlTower = pageContext+"projectListForSales?projectid="+ $("#gProjectId").val();
	$.getJSON(urlTower, function (data) {
		$('#projectDataList').append('<option value="">Select</option>');
		$.each(data, function (index, value) {
			$("#region_id").val(value.region__c);
			$('#projectDataList').append("<option value='"+value.sfid+"'>"+value.name+  " / " +value.propstrength__project_code__c+ "</option>");
		});					
	}).done(function() {
		
	});
}


function paymentPlanDropdown (){
	$('#ppDropdown').empty();
	
	var urlpayemntPlan = pageContext+"getpaymentPlanListData?projectcode="+$("#projectDataList").val();
	
	$.getJSON(urlpayemntPlan, function (data) {
		$.each(data, function (index, value) {
			$('#ppDropdown').append('<option value='+value.sfid+'>'+value.name+'</option>');
		});					
	}).done(function() {
		towerList ();
		$('#bspAgainestBtn').show();
	});
}

function towerList (e, source) {
	$('#towerMst').empty();	
	debugger;
	var projectNameVal = $("#projectDataList").val();
	//var urlTower = pageContext+"getTower?project_code="+projectNameVal;
	
	var urlTower = pageContext+"getTowerMaster?project_code="+projectNameVal;
	
	
	$.getJSON(urlTower, function (data) {
		$('#towerMst').append('<option name="0" value="0">Select</option>');
		$.each(data, function (index, value) {
			$('#towerMst').append("<option name='"+value.sfid+"' value='"+value.tower_code__c+"'>"+value.tower_name__c+"</option>");
		});					
	}).done(function() {
		
	});
}

/*Typology*/
function inventoryUnitTypeMst () {
	$('#typoMst').empty();
	var towerCode = $('#towerMst').val();
	var projectNameVal = $('#projectDataList').val();
	
	var urlProject = pageContext+"getunittype?project_code="+projectNameVal+"&tower_code="+towerCode+"&floor_code="
	
	$('#typoMst').append("<option value='0'>Select</option> ");
	
	$.getJSON(urlProject, function (data) {
		$.each(data, function (index, value) {
			$('#typoMst').append("<option value='"+value.propstrength__unit_type__c+"'>"+value.propstrength__unit_type__c+"</option>");
		});					
	}).done(function() {
		
	});	
}
/*END Typology*/


function addBSPCharge () {
	
	var bspAmountPerVal = 0;
	var bspAmountVal = 0;
	
	if ($('#bsp_amount_per').val().trim() != '') {
		bspAmountPerVal = $('#bsp_amount_per').val();
	} 
	
	if ($('#bsp_amount').val().trim() != '') {
		bspAmountVal = $('#bsp_amount').val();
	}  
	
	$.post(pageContext+"insertBSPForPP",{"bsp_amount_per": bspAmountPerVal, "bsp_amount":bspAmountVal,  "project_id":$('#projectDataList').val(), "project_name":$('#projectDataList :selected').text(), "pymt_plan_id":$('#ppDropdown').val()
		, "pymt_plan_name":$('#ppDropdown :selected').text(), "region_id":$('#region_id').val(), "region_name":$('#region_id').val()
		, "tower_id":$('#towerMst').find('option:selected').attr('name'), "typology_name":$('#typoMst').val(), "inventoryCatDD":$('#inventoryCatDD').val()},function(data){				 
		
	}).done(function(data){
		swal({
			title: data.insertStatus,
		    text: "",
		    timer: 2000,
		    type: "success",
		});
	});
}