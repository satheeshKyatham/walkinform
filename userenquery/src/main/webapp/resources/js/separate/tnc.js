$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});
var pageContext = $("#pageContext").val()+"/";	

function addTnCCharge () {
	if($('#regionList').val() != '' && $('#projectDataList').val() != '') {
		
		/*var tncAgainest = "";
		
		if ($('#ppDropdown').val() != '') {
			tncAgainest = "insertTnCForPP";
		}else {
			tncAgainest = "insertTnCForProject";
		}*/
		
		//	alert ("HTML ::: " + $('.Editor-editor').html() );
		$.post(pageContext+"insertTnCForPP",{"tnc_text":$('.Editor-editor').html(),  "project_id":$('#projectDataList').val(), "project_name":$('#projectDataList :selected').text(), "pymt_plan_id":$('#ppDropdown').val(), "pymt_plan_name":$('#ppDropdown :selected').text(), "region_id":$('#regionList').val(), "region_name":$('#regionList :selected').text()},function(data){				 
			
		}).done(function(data){
			//alert(data.insertStatus);
			swal({
				title: data.insertStatus,
			    text: "",
			    timer: 2000,
			    type: "success",
			});
		});
	}else {
		swal({
			title: "Please select the mandatory fields",
		    text: "",
		    timer: 2000,
		    type: "error",
		});
	}
}

regionList();

function regionList () {
	$('#regionList').find("option:gt(0)").remove();
	
	
	//$('#regionList').empty();	
	var urlRegionList = pageContext+"regionList?project_code=test";
	
	$.getJSON(urlRegionList, function (data) {
		//$('#regionList').append('<option value="">Select</option>');
		$.each(data, function (index, value) {
			$('#regionList').append("<option value='"+value.region__c+"'>"+value.region__c+"</option>");
		});					
	}).done(function() {
	});
}

function projectDataList (){
	$('#projectDataList').find("option:gt(0)").remove();
	//$('#projectDataList').empty();	
	var urlTower = pageContext+"projectDataList?region="+$('#regionList').val();
	$.getJSON(urlTower, function (data) {
		//$('#projectDataList').append('<option value="">Select</option>');
		$.each(data, function (index, value) {
			$('#projectDataList').append("<option value='"+value.sfid+"'>"+value.name+  " / " +value.propstrength__project_code__c+ "</option>");
		});					
	}).done(function() {
	});
}


function paymentPlanDropdown (){
	//$('#ppDropdown').empty();
	$('#ppDropdown').find("option:gt(0)").remove();
	var urlpayemntPlan = pageContext+"getpaymentPlanListData?projectcode="+$("#projectDataList").val();
	
	$.getJSON(urlpayemntPlan, function (data) {
		$.each(data, function (index, value) {
			$('#ppDropdown').append('<option value='+value.sfid+'>'+value.name+'</option>');
		});					
	}).done(function() {
		
	});
}


$(document).ready(function() {
	$("#tnc_text").Editor();
});