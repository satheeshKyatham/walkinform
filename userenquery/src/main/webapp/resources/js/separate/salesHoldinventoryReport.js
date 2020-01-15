//alert ($('#contextPath').val()+"/");

//var urlDomin = "http://localhost:8080/UserEnqury/";

//var urlDomin = $('#contextPath').val()+"/";
$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});
var pageContext = $("#contextPath").val()+"/";	


function getInventorySalesHoldReportDtl () {
	$.post(pageContext+"holdSalesExistReport",{"projectSFID":$('#projectid').val(), "towerCode":$('#towerMstSalesHoldReport').val()},function(data){				 
		$("#salesHoldReport tbody").empty();
		
		var holdMinVar = 0;
		var holdSecVar = 0;
		
	}).done(function(data){
		$("#salesHoldReport").DataTable().destroy();
		
		$("#salesHoldReport tbody").empty();
		
		var obj =JSON.parse(data);
		var html = '';
		
		if (obj != null) {
			for(var i=0;i<obj.length;i++){
				
				holdMinVar = obj[i].holdMin;
				holdSecVar = obj[i].holdSec;
				
				if (holdMinVar == 'undefined' || holdMinVar == null){
					holdMinVar = 0;
				} else if (holdSecVar == 'undefined' || holdSecVar == null) {
					holdSec = 0;
				}
				
				html += "<tr>" +
							" <td>"+obj[i].tower_name__c+"</td>" +
							" <td>"+obj[i].floor_number__c+"</td>" +
							" <td>"+obj[i].propstrength__unit_type__c+"</td>" +
							" <td>"+obj[i].propstrength__house_unit_no__c+"</td>" +
							" <td>"+obj[i].held_by_name+"</td>" +
							" <td>"+obj[i].held_by_email+"</td>" +
							" <td><b>Min:</b>"+holdMinVar +" | <b>Sec:</b>"+holdSecVar+"</td>" +
						" </tr>";
			}
			
			$("#salesHoldReport tbody").append(html);
			
			
			$('#salesHoldReport').DataTable( {
				 dom: 'Bfrtip',
				 "buttons": [
					 { "extend": 'excel', "text":'Export To Excel',"className": 'btn btn-default btn-xs' }
			      ],
			      "order": []
			 });
			
		}
	});	
}