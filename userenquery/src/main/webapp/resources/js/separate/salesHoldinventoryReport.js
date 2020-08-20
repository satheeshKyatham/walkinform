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
		var rate_per_unit = 0;
		var saleable_area = 0;
		
		if (obj != null) {
			for(var i=0;i<obj.length;i++){
				
				holdMinVar = obj[i].holdMin;
				holdSecVar = obj[i].holdSec;
				
				if (holdMinVar == 'undefined' || holdMinVar == null){
					holdMinVar = 0;
				} else if (holdSecVar == 'undefined' || holdSecVar == null) {
					holdSec = 0;
				}
				
				if (obj[i].propstrength__rate_per_unit_area__c != null && obj[i].propstrength__rate_per_unit_area__c != undefined && obj[i].propstrength__rate_per_unit_area__c != '') {
					rate_per_unit = obj[i].propstrength__rate_per_unit_area__c;
				} else {
					rate_per_unit = 0;
				}
				
				if (obj[i].saleable_area__c != null && obj[i].saleable_area__c != undefined && obj[i].saleable_area__c != '') {
					saleable_area = obj[i].saleable_area__c;
				} else {
					saleable_area = 0;
				}
				
				var unitId =  '"'+obj[i].sfid+'"';
				var customerId = '"'+obj[i].customer_id+'"';
				
				html += "<tr>" +
							" <td>"+obj[i].tower_name__c+"</td>" +
							" <td>"+obj[i].floor_number__c+"</td>" +
							" <td>"+obj[i].propstrength__unit_type__c+"</td>" +
							" <td>"+obj[i].propstrength__house_unit_no__c+"</td>" +
							
							" <td>"+obj[i].wing_block__c+"</td>" +
							" <td>"+saleable_area+"</td>" +
							" <td>"+rate_per_unit+"</td>" +
							" <td>"+parseFloat(rate_per_unit*saleable_area).toFixed(2)+"</td>" +
							
							" <td>"+obj[i].held_by_name+"</td>" +
							" <td>"+obj[i].held_by_email+"</td>" +
							" <td><b>Min:</b>"+holdMinVar +" | <b>Sec:</b>"+holdSecVar+"</td>" +
							
							" <td> <div> <b>Source: </b>"+obj[i].source +" </div> <div> <b>Customer Name: </b>"+obj[i].name +" </div> <div> <b>Customer Mobile: </b>"+obj[i].mobile__c +" </div> </td>" +
							
							" <td><button class='btn btn-primary blue_btn' onclick='releaseCMHold("+unitId+", "+customerId+")'>Release</button></td>" +
							
						" </tr>";
			}
			
			html = html.replace(/undefined/g, " - ");
			html = html.replace(/null/g, " - ");
			
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







function releaseCMHold (unitsfid, customer_id) {
	$.post(pageContext+"releaseFromHold",{"customerId":customer_id, "unitSfid":unitsfid, "projectNameId":$('#projectid').val()},function(data){				 
		
	}).done(function(data){
		swal({
			title: "Successfully Released",
		    text: "",
		    timer: 3000,
		    type: "success",
		}); 
		
		getInventorySalesHoldReportDtl();
	});
}