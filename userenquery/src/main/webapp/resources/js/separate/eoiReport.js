$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});

$(document).ready(function() {
	var today = new Date();
	document.getElementById("txtEOIFromDate").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
	document.getElementById("txtEOIToDate").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
	document.getElementById("txtpaymentEOIFromDate").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
	document.getElementById("txtpaymentEOIToDate").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
});


function getEOIReport () {
	
	$("#eoiReportTable").DataTable().destroy();
	
	$("#eoiReportCSVInputCol").empty();
	
	$("#eoiReportCSVInputCol").append("<input type='hidden' value='"+$('#txtEOIFromDate').val()+"' name='fromdate' />");
	$("#eoiReportCSVInputCol").append("<input type='hidden' value='"+$('#txtEOIToDate').val()+"' name='todate' />");
	$("#eoiReportCSVInputCol").append("<input type='hidden' value='"+$('#projectid').val()+"' name='projectSfid' />");
	
	$('#eoiReportTab i').show();
	
	$("#eoiReportTable tbody").empty();
	$.get("getEOIReport",{"projectSfid":$('#projectid').val(), "fromDate":$('#txtEOIFromDate').val(), "toDate":$('#txtEOIToDate').val()},function(data){				 
		var obj =JSON.stringify(data);
		var obj1 =JSON.parse(obj);
		
		
		
		var html = '';
		
		var tab = '';
		
		
		//alert(obj1.length);
		if(obj1!=null) {
			for(var i=0;i<obj1.length;i++){
				
				
				//tab = obj1[i].preferences.replace(/|/g, "</div><div>");
				
				//var typology_name3 = obj1[i].typology_name3.replace(/null/g, "-");
				
				
				if (obj1[i].preferences != null && obj1[i].preferences != "" && obj1[i].preferences != undefined && obj1[i].preferences != "undefined") {
					tab = obj1[i].preferences.replace(/\|/g, "</div><div class='prefTab'>");
				}
				
				//tab = "";
				
				
				html += "<tr>" +
							// " <td>"+obj1[i].region__c+"</td>" +
							// " <td>"+obj1[i].project_name+"</td>" +
							" <td>"+obj1[i].enq_name+"</td>" +
							" <td>"+obj1[i].customer_name+"</td>" +
							" <td>"+obj1[i].customer_mobile+"</td>" +
							" <td>"+obj1[i].customer_email+"</td>" +
							" <td>"+obj1[i].walkin_date+"</td>" +
							" <td>"+obj1[i].walk_in_source__c+"</td>" +
							
							
							" <td>"+obj1[i].verticle__c+"</td>" +
							" <td>"+obj1[i].closing_manager_name__c+"</td>" +
							" <td>"+obj1[i].date_of_eoi__c+"</td>" +
							" <td>"+obj1[i].total_payment_done+"</td>" +
							" <td>"+obj1[i].total_payment_approved+"</td>" +
							
							" <td>"+obj1[i].typology_name1+"</td>" +
							" <td>"+obj1[i].floor_band1+"</td>" +
							" <td>"+obj1[i].tower_name1+"</td>" +
							
							" <td>"+obj1[i].typology_name2+"</td>" +
							" <td>"+obj1[i].floor_band2+"</td>" +
							" <td>"+obj1[i].tower_name2+"</td>" +
							
							" <td>"+obj1[i].typology_name3+"</td>" +
							" <td>"+obj1[i].floor_band3+"</td>" +
							" <td>"+obj1[i].tower_name3+"</td>" +
							
						" </tr>";
			}
			
			html = html.replace(/null/g, " - ");
			
			//html = html.replace(/null/g, "");
			
			$("#eoiReportTable tbody").append(html);
			
			
		} else {
			alert ("Data not found");
		}
	}).done(function(data){
		/*$('#applicationTable').dataTable({
			"ordering": false,
			"bDestroy": true
		});*/
		
		$('#eoiReportTab i').hide();
		
		//$('#eoiReportTable').dataTable({
			//"ordering": true,
			//"bDestroy": true
		//});
		
		 $('#eoiReportTable').DataTable( {
			 dom: 'Bfrtip',
			 "buttons": [
				 { "extend": 'excel', "text":'Export To Excel',"className": 'btn btn-default btn-xs' }
		      ],
		      "order": []
		 });
		
		
		
	});	
}

/* -------- Payment EOI Report ---------- */
function getPaymentEOIReport () {
	
	$("#paymentEOIReportTable").DataTable().destroy();
	
	$('#paymentEOIReportTab i').show();
	
	$("#paymentEOIReportTable tbody").empty();
	$.get("getPaymentEOIReport",{"projectSfid":$('#projectid').val(), "fromDate":$('#txtpaymentEOIFromDate').val(), "toDate":$('#txtpaymentEOIToDate').val()},function(data){				 
		var obj =JSON.stringify(data);
		var obj1 =JSON.parse(obj);
		var html = '';
		var paymentStatus = '';
		
		if(obj1!=null) {
			for(var i=0;i<obj1.length;i++){
				
				
				if (obj1[i].payment_status == 'Y' || obj1[i].payment_status == 'O') {
					paymentStatus = 'Approved'
				} else if (obj1[i].payment_status == 'N') {
					paymentStatus = 'Rejected'
				}
				
				
				html += "<tr>" +
							" <td>"+obj1[i].enq_name+"</td>" +
							" <td>"+obj1[i].customer_name+"</td>" +
							" <td>"+obj1[i].customer_mobile+"</td>" +
							" <td>"+obj1[i].customer_email+"</td>" +
							" <td>"+obj1[i].walkin_date+"</td>" +
							" <td>"+obj1[i].walk_in_source__c+"</td>" +
							" <td>"+obj1[i].verticle__c+"</td>" +
							" <td>"+obj1[i].closing_manager_name__c+"</td>" +
							" <td>"+obj1[i].date_of_eoi__c+"</td>" +
							" <td>"+obj1[i].payment_type+"</td>" +
							" <td>"+obj1[i].bank_name+"</td>" +
							" <td>"+obj1[i].branch+"</td>" +
							" <td>"+obj1[i].transaction_id+"</td>" +
							" <td>"+obj1[i].transaction_date+"</td>" +
							" <td>"+obj1[i].transaction_amount+"</td>" +
							" <td>"+obj1[i].description+"</td>" +
							" <td>"+paymentStatus+"</td>" +
							" <td>"+obj1[i].transaction_amount+"</td>" +
						" </tr>";
			}
			
			html = html.replace(/null/g, " - ");
			
			$("#paymentEOIReportTable tbody").append(html);
			
		} else {
			alert ("Data not found");
		}
	}).done(function(data){
		$('#paymentEOIReportTab i').hide();
		
		 $('#paymentEOIReportTable').DataTable( {
			 dom: 'Bfrtip',
			 "buttons": [
				 { "extend": 'excel', "text":'Export To Excel',"className": 'btn btn-default btn-xs' }
		      ],
		      "order": []
		 });
	});	
}