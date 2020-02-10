/**
 * Author - Satheesh Kyatham - 18-12-2019
 */
$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});
getPendingPaymentDetails();
function getPendingPaymentDetails()
{

	 var url_string = window.location.href; //window.location.href
	 var url = new URL(url_string);
	  var projectid = url.searchParams.get("projectid");
	 $("#Payment_Pending").dataTable().fnDestroy();
	 $("#Payment_Pending tbody").empty();
	 $("#mainPageLoad").show();
//	var urlPP = "getKYCData?userid=&projectid="+projectid;
	 var urlPP = "getPendingPaymentData?projectid="+projectid;
	 var i = 0;
	 var panTarget="";
	 var reciptTarget="";
	$.getJSON(urlPP, function (data) {
		$.each(data, function (index, value) {
			//alert("PAN :"+value.pan_attach);
			//alert("Cheque:"+value.cheque_attach);
			panTarget = "file?name="+encodeURIComponent(value.pan_attach)+"&from=EOIbookingReference&eid="+value.enq_sfid+"&fid="+value.pan_attach.charAt(0);
            reciptTarget = "file?name="+encodeURIComponent(value.cheque_attach)+"&from=EOIbookingReference&eid="+value.enq_sfid+"&fid="+value.cheque_attach.charAt(0);//<td>"+value.customerName+"</td><td>"+value.mobileNo+"</td>
			var val = $("<tr class='paymentDataRow'><td style='display:none'><input type='text' value="+value.id+" class='ptPKID'/></td><td><input type='checkbox' class='paymentRowEoicheck'></td><td>"+value.createdDate+"</td><td>"+value.name+"</td><td>"+value.customerName+"</td><td>"+value.mobileNo+"</td><td>"+value.user_name+"</td><td>"+value.payment_type+"</td><td>"+value.bank_name+"</td><td>"+value.branch+"</td><td>"+value.transaction_id+"</td><td>"+value.transaction_date+"</td><td>"+value.transaction_amount+"</td><td>"+value.description+"</td>" +
					"<td class='txtCenter'><input style='display:none;' class='full form-control input-sm panAttach' type='file' data-fileName="+value.pan_attach+"  name='panAttach'> <a target='_blank' href="+panTarget+">"+value.pan_attach+"</a></td>" +
					"<td class='txtCenter'><input style='display:none;' class='full form-control input-sm panAttach' type='file' data-fileName="+value.cheque_attach+"  name='panAttach'> <a target='_blank' href="+reciptTarget+">"+value.cheque_attach+"</a></td></tr>");
			$("#Payment_Pending tbody").append(val);
			i = i+1
		});
		$('#Payment_Pending').DataTable(
				{
					destroy: true,
					dom: 'Bfrtip',
					 "buttons": [
						 { "extend": 'excel', "text":'Export To Excel',"className": 'btn btn-default btn-xs' }
				      ],
				      "order": []
				});
		
	}).done(function() {
		$("#mainPageLoad").hide();
		
	}).error(function() { $("#mainPageLoad").hide(); });
}

function getApprovedPaymentDetails()
{
	 var url_string = window.location.href; //window.location.href
	 var url = new URL(url_string);
	  var projectid = url.searchParams.get("projectid");
	 $("#Payment_Approved").dataTable().fnDestroy();
	 $("#Payment_Approved tbody").empty();
	 $("#mainPageLoad2").show();
	var urlPP = "getApprovedPaymentData?projectid="+projectid;
	// var urlPP = "getApprovedPaymentData";
	 var i = 0;
	$.getJSON(urlPP, function (data) {
		$.each(data, function (index, value) {//<td>"+value.customerName+"</td><td>"+value.mobileNo+"</td>
			var val = $("<tr><td>"+value.createdDate+"</td><td>"+value.name+"</td><td>"+value.customerName+"</td><td>"+value.mobileNo+"</td><td>"+value.user_name+"</td><td>"+value.payment_type+"</td><td>"+value.bank_name+"</td><td>"+value.branch+"</td><td>"+value.transaction_id+"</td><td>"+value.transaction_date+"</td><td>"+value.transaction_amount+"</td></tr>");
			$("#Payment_Approved tbody").append(val);
			i = i+1
		});
		$('#Payment_Approved').DataTable(
				{
					destroy: true,
					dom: 'Bfrtip',
					 "buttons": [
						 { "extend": 'excel', "text":'Export To Excel',"className": 'btn btn-default btn-xs' }
				      ],
				      "order": []
				});
	}).done(function() {
		$("#mainPageLoad2").hide();
		
	}).error(function() { $("#mainPageLoad2").hide(); });

}
function getRejectedPaymentDetails()
{
	 var url_string = window.location.href; //window.location.href
	 var url = new URL(url_string);
	 var projectid = url.searchParams.get("projectid");
	 $("#Payment_Reject").dataTable().fnDestroy();
	 $("#Payment_Reject tbody").empty();
	 $("#mainPageLoad3").show();
	var urlPP = "getRejectedPaymentData?projectid="+projectid;
	// var urlPP = "getRejectedPaymentData";
	 var i = 0;
	$.getJSON(urlPP, function (data) {
		$.each(data, function (index, value) {//<td>"+value.customerName+"</td><td>"+value.mobileNo+"</td>
			var val = $("<tr><td>"+value.createdDate+"</td><td>"+value.name+"</td><td>"+value.customerName+"</td><td>"+value.mobileNo+"</td><td>"+value.user_name+"</td><td>"+value.payment_type+"</td><td>"+value.bank_name+"</td><td>"+value.branch+"</td><td>"+value.transaction_id+"</td><td>"+value.transaction_date+"</td><td>"+value.transaction_amount+"</td></tr>");
			$("#Payment_Reject tbody").append(val);
			i = i+1
		});
		$('#Payment_Reject').DataTable(
				{
					destroy: true,
					dom: 'Bfrtip',
					 "buttons": [
						 { "extend": 'excel', "text":'Export To Excel',"className": 'btn btn-default btn-xs' }
				      ],
				      "order": []
				});
	}).done(function() {
		$("#mainPageLoad3").hide();
		
	}).error(function() { $("#mainPageLoad3").hide(); });
}

function approvedEOIPayment()
{
	$("#mainPageLoad").show();
	var k = 0;
	var arrayData = [];
	$("#Payment_Pending .paymentDataRow").each(function () {
	      if ($(this).find('.paymentRowEoicheck').is(':checked')) {
	             var csPtData = {};
	             csPtData.id=$(this).find(".ptPKID").val();
	             csPtData.isactive="Y";
	             arrayData.push(csPtData);
	             k++
	      }
	});
	
	$.post("updateEOIPayment",{"paymentEOIDtlJson" : JSON.stringify(arrayData)},function(data){                         
	}).done(function(){
	      alert("Payment Succesfully Aproved");
	      getPendingPaymentDetails();
	      $("#mainPageLoad").hide();
	});
}

function rejectEOIPayment()
{
	$("#mainPageLoad").show();
	var k = 0;
	var arrayData = [];
	$("#Payment_Pending .paymentDataRow").each(function () {
	      if ($(this).find('.paymentRowEoicheck').is(':checked')) {
	             var csPtData = {};
	             csPtData.id=$(this).find(".ptPKID").val();
	             csPtData.isactive="R";
	             arrayData.push(csPtData);
	             k++
	      }
	});
	
	$.post("updateEOIPayment",{"paymentEOIDtlJson" : JSON.stringify(arrayData)},function(data){                         
	}).done(function(){
		 alert("Payment Succesfully Rejected");
	      getPendingPaymentDetails();
	      $("#mainPageLoad").hide();
	});
}