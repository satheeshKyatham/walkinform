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
	var urlPP = "misReport?projectid="+$('#projectid').val()+"&userid="+$('#userid').val()+"&fromdate="+$('#txtFromDateclsoing').val()+"&todate="+$('#txtToDate').val()+"&userVerticals=";
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
			var offerSfid = value.offerSfid==null?'':value.offerSfid;
			var offerName = value.offerName==null?'':value.offerName;
			var enquiryid = value.enquiryid==null?'':value.enquiryid;
			var costsheetPath = value.costsheetPath==null?'':value.costsheetPath;
			var totalAmt=value.totalamount==null?'':value.totalamount;
			var propertName =value.property_name1__c==null?'':value.property_name1__c;
			
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
			var val = $("<tr data-offerSfid='" +value.offerSfid+ " '>" +
					"<td>"+value.enquiryid+"</td><td>"
					+value.application_name
					+"</td><td>"
					+value.phone_number
					+"</td><td>"
					+kycStatus
					+"</td>"
					+"<td>"+offerName
					+"</td><td>"+propertName+"</td><td><a href="
					+value.kyclink
					+"&isrole=Y target='_blank'>Link</a></td>"
					+"</td><td><a target=\"_blank\" " +
					"href=\"/walkinform/Costsheet?" +
					"name="+costsheetPath
					+"&amp;from=ofrList\"><i class=\"fa fa-file\"></i></a></td>"
					+appFormHtml
					+"<td><a type='button' data-toggle='modal' data-target='#paymentDetails_KYC_CM' onclick='getKYCPaymentDetailsCM(this,\""+value.offerSfid+"\")'>"+totalAmt+"</a></td>"+
						+"</tr>"
					);
			/*
			 * <button class="btn btnDefaultBlue btn-default" 
			 * onclick="getofferApplicantDetails(this, &quot;a1X2s0000004GTcEAM&quot;, &quot;a1u2s0000000PcsAAE&quot;,  &quot;0032s000001axeGAAQ&quot;,  &quot;OFFER-2019-11-0020597&quot;,  &quot;ENQ - 04918431&quot;, &quot;a1s6F00000CHzLDQA1&quot;, &quot;0&quot;, &quot;offer&quot;)">
			 * <i class="fa fa-print printficon"></i></button>*/
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
function getKYCPaymentDetailsCM(e,offersfid)
{
	//alert("Open:-"+offersfid);
	//paymentDetails_table_KYC
	
	$("#prePaymentDetails_table_KYC_CM tbody").empty();
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
			
			$("#prePaymentDetails_table_KYC_CM tbody").append(html);
		}
	});
	}