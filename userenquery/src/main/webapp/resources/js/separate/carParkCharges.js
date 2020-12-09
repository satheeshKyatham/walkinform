
$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});
var pageContext = $("#pageContext").val()+"/";	

function addCarParkCharge () {
	$.post(pageContext+"insertCarparkCharges",{ "project_id":$('#projectDataList').val(), "project_name":$('#projectDataList :selected').text(), "region_id":$('#region_id').val(), "region_name":$('#region_id').val(),"parkTypeName":$('#parkType :selected').text(),  "parkType":$('#parkType').val(),   "carParkAmount":$('#carParkAmount').val() },function(data){				 
		
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
		$('#regionList').append('<option value="">Select</option>');
		$.each(data, function (index, value) {
			$('#regionList').append("<option value='"+value.region__c+"'>"+value.region__c+"</option>");
		});					
	}).done(function() {
	});
}*/
projectDataList();
function projectDataList (){
	debugger
	$('#projectDataList').empty();	
	var urlTower = pageContext+"projectListForSales?userId="+ $("#logedInuserid").val();
	$.getJSON(urlTower, function (data) {
		$('#projectDataList').append('<option value="">Select</option>');
		$.each(data, function (index, value) {
			$("#region_id").val(value.region__c);
			$('#projectDataList').append("<option value='"+value.project_id+"'>"+value.project_name+ "</option>");
		});					
	}).done(function() {
	});
}


function getCarparkTypeList (){
	$('#parkType').empty();	
	var urlTower = pageContext+"getCarparkTypeMst?projectSFID="+$('#projectDataList').val();
	$.getJSON(urlTower, function (data) {
		$.each(data, function (index, value) {
			$('#parkType').append("<option value='"+value.id+"'>"+value.carpark_type+ "</option>");
		});					
	}).done(function() {
	});
}


function insertMaster () {
	var call = 'insertCarparkType';
	$.post(pageContext+call,{"region":$('#region_id').val(),
		"projectName":$('#projectDataList option:selected').text(),
		"projectid":$('#projectDataList').val(), 
		"carparkName": $('#carParkTypeName').val(),
		"carparkAlias": $('#carparkAlias').val(),
		"totalCarpark": $('#totalCarpark').val()},function(data){				 
		
	}).done(function(data){
		
		if (data == 'inserted') {
			swal({
				title: "Successfully Submitted",
			    text: "",
			    timer: 2000,
			    type: "success",
			});
		}
	});
}
