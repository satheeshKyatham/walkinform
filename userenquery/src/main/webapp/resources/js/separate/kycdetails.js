/* Author : Satheesh Kyatham*
 * 
 */
$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});
getCMKYCDetails();
function getCMKYCDetails()
{
	 var url_string = window.location.href; //window.location.href
	   var url = new URL(url_string);
	   var projectid = url.searchParams.get("projectid");
	$("#KYC_Admin_Details tbody").empty();
	 $("#mainPageLoad").show();
	var urlPP = "getKYCData?userid=&projectid="+projectid;
	var i = 0;
	$.getJSON(urlPP, function (data) {
		$.each(data, function (index, value) {
			var kycStatus ="";
			if(value.kycapproval_status=="Y")
					kycStatus="Yes";
			else if(value.kycapproval_status=="N")
				kycStatus="No";
			
			var val = $("<tr><td>"+value.enquiryid+"</td><td>"+value.application_name+"</td><td>"+value.phone_number+"</td><td>"+kycStatus+"</td><td><a href="+value.kyclink+"&isrole=Y target='_blank'>Link</a></td></tr>");
			$("#KYC_Admin_Details tbody").append(val);
			i = i+1
		});
		$('#KYC_Admin_Details').DataTable(
				{
					destroy: true
					//processing:true,
					//serverSide:true
				});
	}).done(function() {
		$("#mainPageLoad").hide();
		
	}).error(function() { $("#mainPageLoad").hide();alert("error"); });

	
	
	}

function getKYCApprovalView()
{
	debugger;
	 var url_string = window.location.href; //window.location.href
	   var url = new URL(url_string);
	   var projectid = url.searchParams.get("projectid");
	var frameElement = document.getElementById("kyc_approval_iframe");
//	frameElement.src = "http://kyc.gplapps.com:8081/kycform/kycApproval?projectid="+projectid+"&userid=0";
	frameElement.src = "/kycform/kycApproval?projectid="+projectid+"&userid=0";
	}