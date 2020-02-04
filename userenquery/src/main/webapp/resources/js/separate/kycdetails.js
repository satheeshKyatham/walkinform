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
			/*if(value.phone_number=="4447775522")
				{
					console.log("value.kycapproval_status:"+value.kycapproval_status);
				}*/
			var kycStatus ="";
			if(value.kycapproval_status=="Y")
					kycStatus="KYC Approved";
			else if(value.kycapproval_status=="N")
				kycStatus="KYC Rejected";
			else if(value.issubmitted=="Y")
				kycStatus="KYC submitted";
			else
				kycStatus="KYC link sent to customer";
			var offerSfid = value.offerSfid==null?'':value.offerSfid;
			var offerName = value.offerName==null?'':value.offerName;
			var enquiryid = value.enquiryid==null?'':value.enquiryid;
			var costsheetPath = value.costsheetPath==null?'':value.costsheetPath;
			var appFormHtml="<td>";
			if(value.kycapproval_status==='Y'){
				appFormHtml = appFormHtml+ 
				 "<button isrole=Y class='btn btnDefaultBlue btn-default' " +
				 "onclick='getofferApplicantDetails(this,\""+
				  offerSfid+"\",\""+value.enquirySfid+"\",\""+value.contactSfid
				  +"\",\""+offerName+"\",\""+enquiryid+"\",\"\",\"\",\"\")'>" +
				  "<i class='fa fa-print printficon'></button>";	
				}
			appFormHtml = appFormHtml+"</td>";
			var val = $("<tr><td>"+value.enquiryid
					+"</td><td>"+value.application_name
					+"</td><td>"+value.phone_number
					+"</td><td>"+kycStatus
					+"</td>"
					+"<td>"+offerName
					+"</td><td><a href="
					+value.kyclink
					+"&isrole=Y target='_blank'>Link</a></td>"
					+"</td><td><a class='btn btnDefaultBlue btn-sm btn-default' target=\"_blank\" " +
					"href=\"/walkinform/Costsheet?" +
					"name="+costsheetPath
					+"&amp;from=ofrList\"><i class=\"fa fa-file\"></i></a></td>"
					+appFormHtml
					+"<td><a href='' target='_blank'>Amount</a></td>"+
					
					+"</tr>");
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
		
	}).error(function() { $("#mainPageLoad").hide(); });

	
	
	}

function getKYCApprovalView()
{
	 var url_string = window.location.href; //window.location.href
	   var url = new URL(url_string);
	   var projectid = url.searchParams.get("projectid");
	   
	var frameElement = document.getElementById("kyc_approval_iframe");
//	frameElement.src = "http://kyc.gplapps.com:8081/kycform/kycApproval?projectid="+projectid+"&userid=0";
	frameElement.src = "/kycform/kycApproval?projectid="+projectid+"&userid=0&adminid="+$('#userid').val();
	}