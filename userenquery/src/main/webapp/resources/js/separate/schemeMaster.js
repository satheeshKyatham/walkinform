$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});
var pageContext = $("#pageContext").val()+"/";	


function projectDataListForMaster (){
	$('#projectDataListForMaster').empty();	
	var urlTower = pageContext+"projectDataList?region="+$('#regionListForMaster').val();
	$.getJSON(urlTower, function (data) {
		$('#projectDataListForMaster').append('<option value="-1">Select</option>');
		$.each(data, function (index, value) {
			$('#projectDataListForMaster').append("<option value='"+value.sfid+"'>"+value.name+  " / " +value.propstrength__project_code__c+ "</option>");
		});					
	}).done(function() {
	});
}







function insertMaster (insetFor) {
	
	var call = '';
	
	if (insetFor == 'source') {
		call = "insertSource";
		sourceInput = $('#sourceInput').val();
	} else if (insetFor == 'site') {
		call = "insertSite";
		sourceInput = $('#siteInput').val();
	} else if (insetFor == 'promotional') {
		call = "insertPromotional";
		sourceInput = $('#promotionalInput').val();
	}
	
	
	$.post(pageContext+call,{"region":$('#regionListForMaster').val(),"projectName":$('#projectDataListForMaster option:selected').text(),
		"projectid":$('#projectDataListForMaster').val(), 
		"inputVal": sourceInput},function(data){				 
		
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