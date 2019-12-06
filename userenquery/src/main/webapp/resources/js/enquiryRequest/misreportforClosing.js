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
	//alert($('#txtFromDate').val());
	//alert($('#txtToDate').val());
	$("#misReportDetails tbody").empty();
	 $("#mainPageLoad").show();//$('#projectid').val() -- $('#txtFromDate').val()//
	var urlPP = "misReport?projectid="+$('#projectid').val()+"&userid="+$('#userid').val()+"&fromdate="+$('#txtFromDateclsoing').val()+"&todate="+$('#txtToDate').val();
	var i = 0
	debugger;
	$.getJSON(urlPP, function (data) {
		$.each(data, function (index, value) {

			var val = $("<tr><tr><td>"+value.projectname+"</td><td>"+value.enquiryname+"</td><td>"+value.mobilephone+"</td><td>"+value.tokenno+"</td><td>"+value.created+"</td><td>"+value.name+"</td><td style='word-break: break-word;'>"+value.email+"</td><td>"+value.budget+"</td><td>"+value.carpet_area_requirement+"</td><td>"+value.walk_in_source__c+"</td><td>"+value.user_name+"</td><td>"+value.attended+"</td></tr></tr>");
			$("#misReportDetails tbody").append(val);
			i = i+1
		});
	}).done(function() {
		$("#mainPageLoad").hide();
		$('#misReportDetails').DataTable();
		if (i == 0){
		}	
		
	}).error(function() { $("#mainPageLoad").hide();alert("error"); });

	
	}
function getKYCCMDetails()
{
	$("#KYC_CM_Details tbody").empty();
	 $("#mainPageLoad").show();
	var urlPP = "getKYCData?userid="+$('#userid').val()+"&projectid="+$('#projectid').val();
	var i = 0;
	$.getJSON(urlPP, function (data) {
		$.each(data, function (index, value) {
			var kycStatus ="";
			if(value.kycapproval_status=="Y")
					kycStatus="Yes";
			else if(value.kycapproval_status=="N")
				kycStatus="No";
			var val = $("<tr><tr><td>"+value.enquiryid+"</td><td>"+value.application_name+"</td><td>"+value.phone_number+"</td><td>"+kycStatus+"</td><td><a href="+value.kyclink+"&isrole=Y target='_blank'>Link</a></td></tr></tr>");
			$("#KYC_CM_Details tbody").append(val);
			i = i+1
		});
	}).done(function() {
		$("#mainPageLoad").hide();
		$('#KYC_CM_Details').DataTable();
		
	}).error(function() { $("#mainPageLoad").hide();alert("error"); });

	
	}
function getKYCCMApprovalView()
{
	 var url_string = window.location.href; //window.location.href
	   var url = new URL(url_string);
	   var projectid = url.searchParams.get("projectid");

	var frameElement = document.getElementById("kyc_cm_approval_iframe");
	frameElement.src = "https://d4u.gplapps.com:8085/kycform/kycApproval?projectid=" +projectid;
	}