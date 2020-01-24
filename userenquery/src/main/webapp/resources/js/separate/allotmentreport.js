$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});

$(document).ready(function() {
	var today = new Date();
	document.getElementById("txtAllotFromDate").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
	document.getElementById("txtAllotToDate").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
});


function getAllotmentDashboardReport () {
	
	$("#allotmentReportTable").DataTable().destroy();
	
	$("#eoiReportCSVInputCol").empty();
	
	$("#eoiReportCSVInputCol").append("<input type='hidden' value='"+$('#txtAllotFromDate').val()+"' name='fromdate' />");
	$("#eoiReportCSVInputCol").append("<input type='hidden' value='"+$('#txtAllotToDate').val()+"' name='todate' />");
	$("#eoiReportCSVInputCol").append("<input type='hidden' value='"+$('#projectid').val()+"' name='projectSfid' />");
	
	$('#allotmentReportID i').show();
	
	$("#allotmentReportTable tbody").empty();
	$.get("getAllotmentDayReport",{"projectSfid":$('#projectid').val(), "fromDate":$('#txtAllotFromDate').val(), "toDate":$('#txtAllotToDate').val()},function(data){				 
		var obj =JSON.stringify(data);
		var obj1 =JSON.parse(obj);
		
		
		
		var html = '';
		
		var tab = '';
		var eoiTarget = '';
		var icon = '';
		
		//alert(obj1.length);
		if(obj1!=null) {
			for(var i=0;i<obj1.length;i++){
				if (obj1[i].preferences != null && obj1[i].preferences != "" && obj1[i].preferences != undefined && obj1[i].preferences != "undefined") {
					tab = obj1[i].preferences.replace(/\|/g, "</div><div class='prefTab'>");
				}
				var offerAmount =0; 
				if(obj1[i].offeramount!=null)
					offerAmount=obj1[i].offeramount;
				
				html += "<tr>" +
							" <td>"+obj1[i].priority_no__c+"</td>" +
							" <td>"+obj1[i].enqname+"</td>" +
							" <td>"+obj1[i].custname+"</td>" +
							" <td>"+obj1[i].mobile__c+"</td>" +
							" <td>"+obj1[i].email+"</td>" +
							" <td>"+obj1[i].walk_in_source__c+"</td>" +
							" <td>"+obj1[i].sourcing_manager_name__c+"</td>" +
							
							" <td>"+obj1[i].verticle__c+"</td>" +
							" <td>"+obj1[i].brokername+"</td>" +
							" <td>"+obj1[i].closing_manager_name__c+"</td>" +
							" <td>"+obj1[i].propstrength__request_source__c+"</td>" +
							" <td>"+obj1[i].propstrength__enquiry_type__c+"</td>" +
							" <td>"+obj1[i].offername+"</td>" +
							" <td>"+obj1[i].propstrength__super_area__c+"</td>" +
							" <td>"+obj1[i].propstrength__carpet_area__c+"</td>" +
							" <td>"+obj1[i].propstrength__unit_type__c+"</td>" +
							" <td>"+obj1[i].propstrength__total_basic_sale_price__c+"</td>" +
							" <td>"+obj1[i].propstrength__total_sales_consideration__c+"</td>" +
							" <td>"+obj1[i].user_name+"</td>" +
							" <td>"+obj1[i].offeramount+"</td>" +
							" <td>"+obj1[i].propStrength__Property_Name__c+"</td>" +
							" <td>"+obj1[i].propStrength__House_Unit_No__c+"</td>" +
							" <td>"+obj1[i].actula_5_per+"</td>" +
							" <td>"+obj1[i].diffamount+"</td>" +
						" </tr>";
			}
			
			html = html.replace(/null/g, " - ");
			
			//html = html.replace(/null/g, "");
			
			$("#allotmentReportTable tbody").append(html);
			
			
		} else {
			//alert ("Data not found");
		}
	}).done(function(data){
		/*$('#applicationTable').dataTable({
			"ordering": false,
			"bDestroy": true
		});*/
		
		$('#allotmentReportID i').hide();
		
		//$('#eoiReportTable').dataTable({
			//"ordering": true,
			//"bDestroy": true
		//});
		
		 $('#allotmentReportTable').DataTable( {
			 dom: 'Bfrtip',
			 "buttons": [
				 { "extend": 'excel', "text":'Export To Excel',"className": 'btn btn-default btn-xs' }
		      ],
		      "order": []
		 });
		
		
		
	});	
	
	$.get("getAllotmentDayMISReport",{"projectSfid":$('#projectid').val()},function(data){
	}).done(function(data){
		$("#allotmentmisReport tbody").empty();
		var html = "<tr>" +
					" <td>"+data.totalArealSold+" Sq. Ft.</td>" +
					" <td>"+parseFloat(data.totalBSP).toFixed(2)+" Cr.</td>" +
					" <td>"+data.totalEtoken+"</td>" +
					" <td>"+data.unitcount+"</td>" +
					" <td>"+data.kycApprovedCount+"</td>" +
					" <td>"+data.bookingcount+"</td>" +
					
					" </tr>";
	
	$("#allotmentmisReport tbody").append(html);
		});
}