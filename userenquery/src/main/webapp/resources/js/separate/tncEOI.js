$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});
var pageContext = $("#pageContext").val()+"/";	

function addTnCChargeEOI () {
	if($('#regionListEOI').val() != '' && $('#projectDataListEOI').val() != '') {
		
		$.post(pageContext+"insertTnCForEOI",{"tnc_text":$('.Editor-editor').html(),  "project_id":$('#projectDataListEOI').val(), "project_name":$('#projectDataListEOI :selected').text(),  "region_id":$('#regionListEOI').val(), "region_name":$('#regionListEOI :selected').text()},function(data){				 
			
		}).done(function(data){
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

regionListEOI();

function regionListEOI () {
	$('#regionListEOI').find("option:gt(0)").remove();
	var urlRegionList = pageContext+"regionList?project_code=test";
	
	$.getJSON(urlRegionList, function (data) {
		$.each(data, function (index, value) {
			$('#regionListEOI').append("<option value='"+value.region__c+"'>"+value.region__c+"</option>");
		});					
	}).done(function() {
	});
}

function projectDataListEOI (){
	$('#projectDataListEOI').find("option:gt(0)").remove();
	var urlTower = pageContext+"projectDataList?region="+$('#regionListEOI').val();
	$.getJSON(urlTower, function (data) {
		$.each(data, function (index, value) {
			$('#projectDataListEOI').append("<option value='"+value.sfid+"'>"+value.name+  " / " +value.propstrength__project_code__c+ "</option>");
		});					
	}).done(function() {
	});
}


$(document).ready(function() {
	$("#tncEOI_text").Editor();
});