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
			var totalAmt='';
			var propertName ='';
			if(value.totalamount!=null)
				{
					totalAmt=value.totalamount;
				}
			if(value.property_name1__c!=null)
			{
				propertName=value.property_name1__c;
			}
			if(value.kycapproval_status=='Y'){
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
					+"</td><td>"+propertName+"</td><td><a href="
					+value.kyclink
					+"&isrole=Y target='_blank'>Link</a></td>"
					+"</td><td><a class='btn btnDefaultBlue btn-sm btn-default' target=\"_blank\" " +
					"href=\"/walkinform/Costsheet?" +
					"name="+costsheetPath
					+"&amp;from=ofrList\"><i class=\"fa fa-file\"></i></a></td>"
					+appFormHtml
					+"<td><a type='button' data-toggle='modal' data-target='#paymentDetails_KYC' onclick='getKYCPaymentDetails(this,\""+value.offerSfid+"\")'>"+totalAmt+"</a></td>"+//<a href='#' onclick='getKYCPaymentDetails(this)' target='_blank'>Amount</a>
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

function getKYCPaymentDetails(e,offersfid)
{
	//alert("Open:-"+offersfid);
	//paymentDetails_table_KYC
	
	$("#prePaymentDetails_table_KYC tbody").empty();
	$.get("getPrePayemntData",{"offersfid":offersfid},function(data){				 
		var obj =JSON.stringify(data);
		var obj1 =JSON.parse(obj);
		
		var html = '';

		
		//alert(obj1.length);
		if(obj1!=null) {
			for(var i=0;i<obj1.length;i++){
				var chequeNo = '';
				var panTarget='';
				var reciptTarget='';
				if(obj1[i].propstrength__cheque_demand_draft_number__c==0)
				{
					chequeNo=obj1[i].propstrength__ifsc_rtgs_code__c;
					if(chequeNo==null)
						chequeNo=obj1[i].propStrength__Cheque_Demand_Draft_No__c;
				}
			else
				chequeNo=obj1[i].propstrength__cheque_demand_draft_number__c;
	
				var pageContext = $("#pageContext").val()+"/";
				if(obj1[i].pan_attach!="")
					{
					panTarget = pageContext+"file?name="+obj1[i].pan_attach+"&from=EOIbookingReference&eid="+obj1[i].enq_sfid+"&fid="+obj1[i].pan_attach.charAt(0);
					}
				if(obj1[i].cheque_attach!="")
					{
						reciptTarget = pageContext+"file?name="+ encodeURIComponent(obj1[i].cheque_attach)+"&from=EOIbookingReference&eid="+obj1[i].enq_sfid+"&fid="+obj1[i].cheque_attach.charAt(0);
					}
				
				
				html += "<tr>" +
							" <td>"+obj1[i].propstrength__payment_mode__c+"</td>" +
							" <td>"+obj1[i].propstrength__bank_name__c+"</td>" +
							//" <td></td>" +
							" <td>"+chequeNo+"</td>" +//propStrength__Cheque_Demand_Draft_No__c
							" <td>"+obj1[i].propstrength__instrument_date__c+"</td>" +
							" <td>"+obj1[i].propstrength__amount__c+"</td>" +
							" <td><a target='_blank' href="+panTarget+">"+obj1[i].pan_attach+"</a></td>" +
							" <td><a target='_blank' href="+reciptTarget+">"+obj1[i].cheque_attach+"</a></td>" +
							" <td>"+obj1[i].description+"</td>" +
							
						" </tr>";
			}
			
			html = html.replace(/null/g, " - ");
			
			//html = html.replace(/null/g, "");
			
			$("#prePaymentDetails_table_KYC tbody").append(html);
		}
	});
	}