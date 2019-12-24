/**
 * Added By Satheesh K - For MIS Report
 * 
 */
$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});
$(document).ready(function() {
	debugger;
	var today = new Date();
	document.getElementById("txtFromDateclsoing").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
	document.getElementById("txtToDate").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
	
	//getAllEnquiryFormReport();
	
	
});

function getDatewiseReport()
{
	
	
	getAllEnquiryFormReport();
	
	$("#txtFromDate1").val($('#txtFromDateclsoing').val());
	$("#txtToDate1").val($('#txtToDate').val());
	}

function getAllEnquiryFormReport()
{

	$("#downloadCSV").append("<input type='hidden' value='"+$('#txtFromDateclsoing').val()+"' name='fromdate' id='txtFromDate1'/>");
	$("#downloadCSV").append("<input type='hidden' value='"+$('#txtToDate').val()+"' name='todate' id='txtToDate1'/>");
	//$("#misReportDetails_CM tbody").empty();
	 $("#mainPageLoad").show();//$('#projectid').val() -- $('#txtFromDate').val()//
	var urlPP = "misReport?projectid="+$('#projectid').val()+"&userid="+$('#userid').val()+"&fromdate="+$('#txtFromDateclsoing').val()+"&todate="+$('#txtToDate').val();
	var i = 0
	$("#misReportDetails_CM").dataTable().fnDestroy();
	$("#misReportDetails_CM tbody").empty();
	$.getJSON(urlPP, function (data) {
		$.each(data, function (index, value) {

			var val = $("<tr><td>"+value.projectname+"</td><td>"+value.enquiryname+"</td><td>"+value.mobilephone+"</td><td>"+value.tokenno+"</td><td>"+value.created+"</td><td>"+value.name+"</td><td style='word-break: break-word;'>"+value.email+"</td><td>"+value.budget+"</td><td>"+value.carpet_area_requirement+"</td><td>"+value.walk_in_source__c+"</td><td>"+value.user_name+"</td><td>"+value.attended+"</td></tr>");
			$("#misReportDetails_CM tbody").append(val);
			i = i+1
		});
		
		$('#misReportDetails_CM').DataTable(
				{
					//"bDestroy": false
					destroy: true
				});
	}).done(function() {
		$("#mainPageLoad").hide();
		
	}).error(function() { $("#mainPageLoad").hide(); });

	
	}
function getKYCCMDetails()
{
	$("#KYC_CM_Details").dataTable().fnDestroy();
	$("#KYC_CM_Details tbody").empty();
	 $("#mainPageLoad").show();
	var urlPP = "getKYCData?userid="+$('#userid').val()+"&projectid="+$('#projectid').val();
	var i = 0;
	//var val="";
	$.getJSON(urlPP, function (data) {
		$.each(data, function (index, value) {
			var kycStatus ="";
			if(value.kycapproval_status=="Y")
				kycStatus="KYC Approved";
			else if(value.kycapproval_status=="N")
				kycStatus="KYC Rejected";
			else if(value.issubmitted=="Y")
				kycStatus="KYC submitted";
			else
				kycStatus="KYC link sent to customer";
			var val = $("<tr><td>"+value.enquiryid+"</td><td>"+value.application_name+"</td><td>"+value.phone_number+"</td><td>"+kycStatus+"</td><td><a href="+value.kyclink+"&isrole=Y target='_blank'>Link</a></td></tr>");
			$("#KYC_CM_Details tbody").append(val);
			
			
			i = i+1
		});
		
		$('#KYC_CM_Details').DataTable(
				{
					destroy: true
					//processing:true,
					//serverSide:true
				});
		
	}).done(function() {
		
		$("#mainPageLoad").hide();
	}).error(function() { $("#mainPageLoad").hide(); });

	
	}
function getKYCCMApprovalView()
{
	var url=$("#contextPath").val();
	debugger
	 var url_string = window.location.href; //window.location.href
	   var url = new URL(url_string);
	   var projectid = url.searchParams.get("projectid");
	   var userid = url.searchParams.get("userId");

	var frameElement = document.getElementById("kyc_cm_approval_iframe");
//	frameElement.src = "http://kyc.gplapps.com:8081/kycform/kycApproval?projectid="+projectid+"&userid="+userid;
	frameElement.src = "/kycform/kycApproval?projectid="+projectid+"&userid="+userid;
	}