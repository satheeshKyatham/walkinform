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

$(document).ready(function() {
	var today = new Date();
	var currentDate = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
	$('#csLogFromDateOffer').val(currentDate);
	$('#csLogToDateOffer').val(currentDate);
});

var pageContext = $("#contextPath").val()+"/";	

function getCostsheetLogReportDtl () {
	
	$('#getCSLogBtn span').html('<i class="fa fa-spinner fa-spin" style="color:#fff !important"></i>'); 
	
	var project = $('#csLogReportMultiTower option:selected');
	var selectedTower = [];
	$(project).each(function(index, brand){
		selectedTower.push($(this).val());
	});
	
	
	console.log (selectedTower);
	
	$("#csLogReportTable").DataTable().destroy();
	$("#csLogReportTable tbody").empty();
	
	$.post(pageContext+"getCostSheetLogReport",{"projectSfid":$('#projectid').val(), "towerCode":selectedTower.join(","), "fromDate":$('#csLogFromDateOffer').val(),"toDate":$('#csLogToDateOffer').val(),},function(data){                      
		
		var obj1 =JSON.parse(data);
		var html = '';
		var schemeType = "";
		var parkingSelection = "";
			
		if (obj1 != null) {
			for(var i=0;i<obj1.length;i++){
				
				if (obj1[i].qry_msg != "MAX_LIMIT") {
					
					if (obj1[i].scheme_type == "noScheme") {
						schemeType = "No Scheme";
					} else if (obj1[i].scheme_type == "other") {
						schemeType = "Other";
					} else if (obj1[i].scheme_type == "scheme") {
						schemeType = "Scheme";
					} else {
						schemeType = "";
					}
					
					
					if (obj1[i].parking_selection == "PARKING_SELECTION") {
						parkingSelection = "Continue with parking";
					} else if (obj1[i].parking_selection == "NO_PARKING_SELECTION") {
						parkingSelection = "Continue without parking";
					} else {
						parkingSelection = "";
					}
					
					html += "<tr>" +
						"<td>"+obj1[i].createddate+"</td>" +
						"<td>"+obj1[i].user_name+"</td>" +
						"<td>"+obj1[i].emailid+"</td>" +
						"<td>"+obj1[i].type + obj1[i].queue +"</td>" +
						
						"<td>"+obj1[i].mobile__c+"</td>" +
						"<td>"+obj1[i].customer_name+"</td>" +
						"<td>"+obj1[i].enq_name+"</td>" +
						"<td>"+obj1[i].project_name+"</td>" +
						"<td>"+obj1[i].propstrength__tower_name__c+"</td>" +
						"<td>"+obj1[i].property_name+"</td>" +
						
						
						"<td>"+obj1[i].source+"</td>" +
						"<td>"+obj1[i].costsheet_type+"</td>" +
						"<td>"+schemeType+"</td>" +
						"<td>"+obj1[i].scheme_name+"</td>" +
						"<td>"+obj1[i].scheme_rate+"</td>" +
						"<td>"+obj1[i].scheme_absolute+"</td>" +
						"<td>"+obj1[i].scheme_percentage+"</td>" +
						"<td>"+obj1[i].carpark_type+"</td>" +
						"<td>"+obj1[i].carpark_count+"</td>" +
						
						"<td>"+obj1[i].paymentplan_name+"</td>" +
						
						//------------------------------------
						"<td>"+obj1[i].discounted_bsp+"</td>" +
						"<td>"+obj1[i].og_bsp+"</td>" +
						"<td>"+obj1[i].carpet_area_sqft+"</td>" +
						"<td>"+obj1[i].saleable_area_sqft+"</td>" +
						"<td>"+obj1[i].carpet_area_rera_sqmt+"</td>" +
						"<td>"+obj1[i].exclusive_area_sqmt+"</td>" +
						"<td>"+obj1[i].total_area_sqmt+"</td>" +
						"<td>"+obj1[i].carpet_area_amount+"</td>" +
						"<td>"+obj1[i].exclusive_area_amount+"</td>" +
						//------------------------------------
						"<td>"+obj1[i].flat_unit_cost+"</td>" +
						"<td>"+obj1[i].total_a+"</td>" +
						"<td>"+obj1[i].total_b+"</td>" +
						"<td>"+obj1[i].stemp_duty_amount+"</td>" +
						"<td>"+obj1[i].registration_amount+"</td>" +
						"<td>"+obj1[i].gst_amount+"</td>" +
						"<td>"+obj1[i].total_c+"</td>" +
						"<td>"+obj1[i].total_abc+"</td>" +
						"<td>"+obj1[i].total_discount+"</td>" +
						"<td>"+obj1[i].paymentplan_total+"</td>" +
						"<td>"+obj1[i].cs_sales_comments+"</td>" +
						
						"<td>"+parkingSelection+"</td>" +
						"<td>"+obj1[i].parking_name+"</td>" +
						"<td>"+obj1[i].parking_amount+"</td>" + 
						//------------------------------------
						
					" </tr>";
				
				} else {
					swal({
	                	title: "Records exceeding 5000. Please narrow down project or select few tower",
	          			text: "Requested records count is: "+obj1[i].qry_count,
	          			//timer: 8000,
	          			type: "warning",
	                });
					
					$("#swal2-title").css({"font-size": "22px"});
					return false;
				}
			}
			 
			html = html.replace(/undefined/g, " - ");
			html = html.replace(/null/g, " - ");
			
			$("#csLogReportTable tbody").append(html);
		} else {
			alert ("Data not found");
		}
         
	}).done(function(data){
		$('#csLogReportTable').DataTable({
			dom: 'Bfrtip',
			"buttons": [{ "extend": 'excel', "text":'Export To Excel',"className": 'btn btn-default btn-xs' }],
			"order": []
		});
		$('#getCSLogBtn span').html('');
	}).fail(function(xhr, status, error) {
		$('#getCSLogBtn span').html(''); 
    });
}