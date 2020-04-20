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


function getInventoryReportDtl () {
	
	$("#inventoryReportDtl").DataTable().destroy();
	
	//$('#eoiReportTabSales i').show();
	
	$("#inventoryReportDtl tbody").empty();
	$.get(pageContext+"getInventoryReport",{"projectSfid":$('#projectid').val(), "towerCode":$('#towerMstReport').val()},function(data){				 
		var obj =JSON.stringify(data);
		var obj1 =JSON.parse(obj);
		
		var html = '';
		
		var rowColor = '';
		var hold_reason = '';
		var rate_per_unit = 0;
		var saleable_area = 0;
		 
		if(obj1!=null) {
			for(var i=0;i<obj1.length;i++){
				
				
				if  (obj1[i].hold_reason == 'temp' && obj1[i].eoi_unit_locked == true){
					rowColor = "rowEOIHoldClr";
					hold_reason = 'EOI Hold';
				}
				
				else if (obj1[i].hold_reason == 'temp') {
					rowColor = "rowHoldClr";
					hold_reason = 'Hold';
				} else if (obj1[i].hold_reason == 'block'){
					rowColor = "rowBlockClr";
					hold_reason = 'Block';
				} 
				
				if (obj1[i].propstrength__rate_per_unit_area__c != null && obj1[i].propstrength__rate_per_unit_area__c != undefined && obj1[i].propstrength__rate_per_unit_area__c != '') {
					rate_per_unit = obj1[i].propstrength__rate_per_unit_area__c;
				} else {
					rate_per_unit = 0;
				}
				
				if (obj1[i].saleable_area__c != null && obj1[i].saleable_area__c != undefined && obj1[i].saleable_area__c != '') {
					saleable_area = obj1[i].saleable_area__c;
				} else {
					saleable_area = 0;
				}
				
				
				html += "<tr class='"+rowColor+"'>" +
							
							" <td>"+hold_reason+"</td>" +
							
							"<td>"+obj1[i].enq_name+"</td>" +
							"<td>"+obj1[i].customer_name+"</td>" +
							"<td>"+obj1[i].customer_mobile+"</td>" +
							
							
							" <td>"+obj1[i].tower_name__c+"</td>" +
							" <td>"+obj1[i].floor_number__c+"</td>" +
							" <td>"+obj1[i].propstrength__house_unit_no__c+"</td>" +
							" <td>"+obj1[i].propstrength__unit_type__c+"</td>" +
							
							" <td>"+obj1[i].wing_block__c+"</td>" +
							" <td>"+saleable_area+"</td>" +
							" <td>"+rate_per_unit+"</td>" +
							" <td>"+parseFloat(rate_per_unit*saleable_area).toFixed(2)+"</td>" +
							
							
							" <td>"+obj1[i].admin_name+"</td>" +
							" <td>"+obj1[i].hold_description+"</td>" +
							
							" <td>"+obj1[i].hold_behalf_username+"</td>" +
							" <td style='word-break: break-all;'>"+obj1[i].hold_behalf_email+"</td>" +
							
							
						" </tr>";
			}
			
			html = html.replace(/undefined/g, " - ");
			html = html.replace(/null/g, " - ");
			
			
			$("#inventoryReportDtl tbody").append(html);
			
			
		} else {
			alert ("Data not found");
		}
	}).done(function(data){
		
		//$('#eoiReportTabSales i').hide();
		
		$('#inventoryReportDtl').DataTable( {
			 dom: 'Bfrtip',
			 "buttons": [
				 { "extend": 'excel', "text":'Export To Excel',"className": 'btn btn-default btn-xs' }
		      ],
		      "order": []
		 });
		
	});	
}