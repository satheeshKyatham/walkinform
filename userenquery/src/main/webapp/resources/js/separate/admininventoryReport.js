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
	
	$("#inventoryReport").DataTable().destroy();
	
	//$('#eoiReportTabSales i').show();
	
	$("#inventoryReport tbody").empty();
	$.get(pageContext+"getInventoryReport",{"projectSfid":$('#projectid').val(), "towerCode":$('#towerMstReport').val()},function(data){				 
		var obj =JSON.stringify(data);
		var obj1 =JSON.parse(obj);
		
		var html = '';
		
		var rowColor = '';
		var hold_reason = '';
		 
		if(obj1!=null) {
			for(var i=0;i<obj1.length;i++){
				
				
				if (obj1[i].hold_reason == 'temp') {
					rowColor = "rowHoldClr";
					hold_reason = 'Hold';
				} else if (obj1[i].hold_reason == 'block'){
					rowColor = "rowBlockClr";
					hold_reason = 'Block';
				}
				
				
				
				
				html += "<tr class='"+rowColor+"'>" +
							
							" <td>"+hold_reason+"</td>" +
							" <td>"+obj1[i].tower_name__c+"</td>" +
							" <td>"+obj1[i].floor_number__c+"</td>" +
							" <td>"+obj1[i].propstrength__house_unit_no__c+"</td>" +
							
							
							" <td>"+obj1[i].propstrength__unit_type__c+"</td>" +
							" <td>"+obj1[i].admin_name+"</td>" +
							" <td>"+obj1[i].hold_description+"</td>" +
							
							" <td>"+obj1[i].hold_behalf_username+"</td>" +
							" <td></td>" +
							
							
						" </tr>";
			}
			
			html = html.replace(/null/g, " - ");
			
			
			$("#inventoryReport tbody").append(html);
			
			
		} else {
			alert ("Data not found");
		}
	}).done(function(data){
		
		//$('#eoiReportTabSales i').hide();
		
		$('#inventoryReport').DataTable( {
			 dom: 'Bfrtip',
			 "buttons": [
				 { "extend": 'excel', "text":'Export To Excel',"className": 'btn btn-default btn-xs' }
		      ],
		      "order": []
		 });
		
	});	
}