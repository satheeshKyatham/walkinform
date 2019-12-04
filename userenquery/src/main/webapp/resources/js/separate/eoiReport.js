$(document).ready(function() {
	var today = new Date();
	document.getElementById("txtEOIFromDate").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
	document.getElementById("txtEOIToDate").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
});


function getEOIReport () {
	
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
				
				tab = obj1[i].preferences.replace(/\|/g, "</div><div class='prefTab'>");
				
				html += "<tr>" +
							" <td>"+obj1[i].enq_sfid+"</td>" +
							" <td><div class='prefTab'>"+tab+"</div></td>" +
							" <td>"+obj1[i].payment_type+"</td>" +
							" <td>"+obj1[i].bank_name+"</td>" +
							" <td>"+obj1[i].branch+"</td>" +
							" <td>"+obj1[i].transaction_id+"</td>" +
							" <td>"+obj1[i].transaction_date+"</td>" +
							" <td>"+obj1[i].transaction_amount+"</td>" +
						" </tr>";
			}
			
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
	});	
}