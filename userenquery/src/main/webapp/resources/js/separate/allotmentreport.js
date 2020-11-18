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
	$("#covered1bhkid").empty();
	$("#covered2bhkid").empty();
	$("#covered3bhkid").empty();
	$("#blockInvId").empty();
	$("#holdInvId").empty();
	
	
	$.get("getAllotmentDayReport",{"projectSfid":$('#projectid').val(), "fromDate":$('#txtAllotFromDate').val(), "toDate":$('#txtAllotToDate').val(), "userVerticals":USER_VERTICALES_GV },function(data){				 
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
							" <td>"+obj1[i].user_name+"</td>" +
							" <td>"+obj1[i].offer_date+"</td>" +
							" <td>"+obj1[i].propstrength__super_area__c+"</td>" +
							" <td>"+obj1[i].propstrength__carpet_area__c+"</td>" +
							" <td>"+obj1[i].propstrength__unit_type__c+"</td>" +
							" <td>"+obj1[i].car_park_type+"</td>" +
							" <td>"+obj1[i].scheme_name+"</td>" +
							" <td>"+obj1[i].propstrength__total_basic_sale_price__c+"</td>" +
							" <td>"+obj1[i].propstrength__total_sales_consideration__c+"</td>" +
							/*" <td>"+obj1[i].user_name+"</td>" +*/
							
							" <td>"+obj1[i].booking_name+"</td>" +
							" <td>"+obj1[i].booking_status+"</td>" +
							" <td>"+obj1[i].kyc_approvedby+"</td>" +
							
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
	if($("#projectid").val()=="a1l6F000009D6IMQA0") {
		$("#coveredcarpark_id").show();
		var coverd1bhk=data.coverd1bhk;
		$("#covered1bhkid").append(coverd1bhk.toString()+"/69");
		$("#covered2bhkid").append(data.coverd2bhk+"/96");
		$("#covered3bhkid").append(data.coverd3bhk+"/147");
	} else if ($("#projectid").val()=="a1l2s000000XoezAAC") {
		
		$("#coveredcarpark_id").empty();
		$("#coveredcarpark_id").show();
		
		var carparkHTML = "<div class='col-lg-3 col-xs-6'>" +
								"<div class='small-box bg-aqua'>"+
									"<div class='inner'>"+
										"<h3>"+data.coverd2bhk+"/268</h3>"+
										"<p>Sold Car Park Covered (2 bhk)</p>"+
									"</div>"+
									"<div class='icon'>"+
										"<i class='fa fa-car'></i>"+
									"</div>"+
								"</div>"+
								"<div class='clearfix'></div>"+
							"</div>"+
							
							"<div class='col-lg-3 col-xs-6'>" +
								"<div class='small-box bg-aqua'>"+
									"<div class='inner'>"+
										"<h3>"+data.coverd3bhk+"/180</h3>"+
										"<p>Sold Car Park Covered (3 bhk)</p>"+
									"</div>"+
									"<div class='icon'>"+
										"<i class='fa fa-car'></i>"+
									"</div>"+
								"</div>"+
								"<div class='clearfix'></div>"+
							"</div>"+
								
							"<div class='col-lg-3 col-xs-6'>" +
								"<div class='small-box bg-aqua'>"+
									"<div class='inner'>"+
										"<h3>"+data.stack1bhk+"/94</h3>"+
										"<p>Sold Car Park Stack (1 bhk)</p>"+
									"</div>"+
									"<div class='icon'>"+
										"<i class='fa fa-car'></i>"+
									"</div>"+
								"</div>"+
								"<div class='clearfix'></div>"+
							"</div>"+	
							
							"<div class='col-lg-3 col-xs-6'>" +
								"<div class='small-box bg-aqua'>"+
									"<div class='inner'>"+
										"<h3>"+data.stack2bhk+"/76</h3>"+
										"<p>Sold Car Park Stack (2 bhk)</p>"+
									"</div>"+
									"<div class='icon'>"+
										"<i class='fa fa-car'></i>"+
									"</div>"+
								"</div>"+
								"<div class='clearfix'></div>"+
							"</div>";
		
		$("#coveredcarpark_id").append(carparkHTML);
		
	} else {
		$("#coveredcarpark_id").hide();
	}
	
	
	$("#blockInvId").append(data.blockedInventoryCount);
	$("#holdInvId").append(data.holdInventoryCount);
	
		});
}