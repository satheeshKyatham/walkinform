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
	document.getElementById("txtFromDate").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
	document.getElementById("txtToDate").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
	
	getAllEnquiryFormReport();
	
	
});

function getDatewiseReport()
{
	
	
	getAllEnquiryFormReport();
	$("#txtFromDate1").val($('#txtFromDate').val());
	$("#txtToDate1").val($('#txtToDate').val());
	}

function getAllEnquiryFormReport()
{

	$("#downloadCSV").append("<input type='hidden' value='"+$('#txtFromDate').val()+"' name='fromdate' id='txtFromDate1'/>");
	$("#downloadCSV").append("<input type='hidden' value='"+$('#txtToDate').val()+"' name='todate' id='txtToDate1'/>");
	//alert($('#txtFromDate').val());
	//alert($('#txtToDate').val());
	$("#misReportDetails").dataTable().fnDestroy();
	$("#misReportDetails tbody").empty();
	 $("#mainPageLoad").show();//$('#projectid').val() -- $('#txtFromDate').val()
	var urlPP = "misReport?projectid="+$('#projectid').val()+"&userid=&fromdate="+$('#txtFromDate').val()+"&todate="+$('#txtToDate').val();
	var i = 0
	$.getJSON(urlPP, function (data) {
		$.each(data, function (index, value) {

			var val = $("<tr><td>"+value.projectname+"</td><td>"+value.enquiryname+"</td><td>"+value.mobilephone+"</td><td>"+value.tokenno+"</td><td>"+value.created+"</td><td>"+value.name+"</td><td>"+value.email+"</td><td>"+value.budget+"</td><td>"+value.carpet_area_requirement+"</td><td>"+value.walk_in_source__c+"</td><td>"+value.user_name+"</td><td>"+value.attended+"</td></tr>");
			$("#misReportDetails tbody").append(val);
			i = i+1
		});
		$('#misReportDetails').DataTable(
				{
					destroy: true
					//processing:true,
					//serverSide:true
				});
	}).done(function() {
		$("#mainPageLoad").hide();
		//$('#misReportDetails').DataTable();	
		
	}).error(function() { $("#mainPageLoad").hide(); });

	
	}

function createdOfferProject()
{

	 $('#mainPageLoad').show();	
	 $("#createdOfferTable").empty();
	//alert ("Test Call 123 456");
	 
	 var contextPath = $("#pageContext").val();
	 var csPath = '';
	 
	$.get("getOfferList",{"userid":"","projectid":$('#projectid').val()},function(data){				 
		
	}).done(function(data){
		//alert("Data:-"+data);
		var obj =JSON.stringify(data);
		var obj1 =JSON.parse(obj);
		//alert(obj1.length);
		if(obj1!=null)
			{
				for(var i=0;i<obj1.length;i++){
					var schemename = "";
					if(obj1[i].scheme_name=="Select Scheme")
						{
						schemename="";
						}
					else
						schemename=obj1[i].scheme_name;
					
					csPath = contextPath+'/Costsheet?name='+obj1[i].costsheet_path + '&from=ofrList';
					
					
					var fileIcon = '';
					
					if (obj1[i].costsheet_path != null) {
						fileIcon = '<i class="fa fa-file"></i>';
					} else {
						fileIcon = '';
					}
					
					
					var val = $("<tr><td>"+obj1[i].enquiryname+"</td><td>"+obj1[i].contactname+"</td><td>"+obj1[i].payment_plan+"</td><td>"+obj1[i].offername+"</td><td>"+schemename+"</td><td>"+obj1[i].scheme_rate+"</td><td>"+obj1[i].amount+"</td><td>"+obj1[i].description+"</td><td>"+obj1[i].cs_final_amount+"</td> <td>  <a target='_blank' href='"+csPath+"'>"+fileIcon+"</a></td><td data-th='Action'> <button type='button' onclick='moreDetails(this,\""+obj1[i].enquiry_sfid+"\","+i+")'  id=\""+obj1[i].enquiryname+"\"  class='btn btnDefaultBlue btn-default btn-xs brdClrBlue moreDetail ' > Details </button></td><td><button class='btn btnDefaultBlue btn-default' onclick='getofferApplicantDetails(this, \""+obj1[i].offer_sfid+"\", \""+obj1[i].enquiry_sfid+"\",  \""+obj1[i].contact_sfid+"\",  \""+obj1[i].offername+"\",  \""+obj1[i].enquiryname+"\", \""+obj1[i].propstrength__property__c+"\", \""+i+"\", \"offer\")'><i class='fa fa-print printficon'></i></button></td></tr>");
					$("#createdOfferTable").append(val);
					
				}
			}
		
		$('#mainPageLoad').hide();	
		
	});	

	}


/*add more data fileds after table row*/
var itbodyid="";
function moreDetails (e,enq,i) {
	//alert("Hello");
	debugger;
	itbodyid=i;
	
	var enqid= $("#enq"+i).val();
	var appli_type= $("#atype"+i).val();
	
	
	

	var data;
	var kycdatahtml="";
	
	if ($(e).data("clicked")) {
		$("#approvaltable"+i).empty();
		$(e).closest("tr").next(".rowMoreData").remove();
		//$(e).closest("tr").remove();
		$("#i_td"+i).remove();
	} else {
		
		
		$.get("getPrepayment",
				{
					"enquirysfid" :enq
				},
				function(data) {
					
					var resultData =data;
					var priapprove="Y";
					
					if(resultData !=null){
						
						kycdatahtml+="<td colspan='12'> <ul>";
						for (var i = 0; i < resultData.length; i++) {
							kycdatahtml+="<li id='i_td0' class='form-inline'>" +
											 "<div class='form-group col-md-3'><label>Payment Type:</label>"+resultData[i].payment_type+"</div>" +
											 "<div class='form-group col-md-3'><label>Bank Name: </label>"+resultData[i].bank_name+"</div>" +
										     "<div class='form-group col-md-3'><label>Banch: </label>"+resultData[i].branch+"</div>" +
										     "<div class='form-group col-md-3'><label>Transaction ID: </label>"+resultData[i].transaction_id+"</div>" +
										     "<div class='form-group col-md-3'><label>Transaction Date: </label>"+resultData[i].transaction_date+"</div>" +
										     "<div class='form-group col-md-3'><label>Transaction Amount: </label>"+resultData[i].transaction_amount+"</div>" +
										     "<div class='form-group col-md-3'><label>Total Amount: </label>"+resultData[i].total_amount+"</div>" ;
							kycdatahtml+="<div class='clearfix'></div></li>";
						   
						}
						kycdatahtml+="</ul> </td>";
					
					//alert("Final"+kycdatahtml);
					//$("#priapprove"+itbodyid).val(priapprove);
					$("#approvaltable"+itbodyid).empty();
					$("#approvaltable"+itbodyid).append(kycdatahtml);
				//	kyccheck();
					}else{
						
						//kycdatahtml +="<tr> <td data-th=''>NO Record Found </td></tr>";
						$("#approvaltable"+itbodyid).empty();
						$("#approvaltable"+itbodyid).append("<tr><td colspan='8' style='text-align:center;' ><b>NO Record Found</b></td></tr>")
						
					}
					
				
				});
		
		
		
		
		var newTable =	'<tr id="approvaltable'+i+'" class="offerPaymentCol"></tr>';
		
		
		$(e).closest("tr").after(newTable);
	}
	$(e).data("clicked", !$(e).data("clicked"));
}
/* END add more data fileds after table row*/
