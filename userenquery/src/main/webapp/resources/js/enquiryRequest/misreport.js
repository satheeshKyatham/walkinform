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

var selectedProject = [];

$(document).ready(function() {
	var today = new Date();
	document.getElementById("txtFromDate").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
	document.getElementById("txtToDate").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
	
	var currentDate = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
	$('#txtFromDateOffer').val(currentDate);
	$('#txtToDateOffer').val(currentDate);
	
	$('#cancelledOfferFromDate').val(currentDate);
	$('#cancelledOfferToDate').val(currentDate);
	
	getAllEnquiryFormReport();
	userprojectmultiselect ();
	  
	/*var urlGetUsers = "getProjectListUserWise?userid="+$('#userid').val();	
  	$.getJSON(urlGetUsers, function (data) {
  		var defaultSelected = "";
  		option = "";
  		$.each(data, function (index, value) {
  			if (value.projectId == $('#projectid').val()) {
  				defaultSelected = "selected";
  			} else {
  				defaultSelected = "";
  			}
  			option = option+"<option value="+value.projectId+" "+defaultSelected+">"+value.projectName+"</option>";
  		});		
  	}).done(function() {
  		$("#multiselectProject").append(option);
  		$('#multiselectProject').multiselect({
			maxHeight: '200',
			allSelectedText: 'All',
			includeSelectAllOption: true
  		});
  	});*/

	
	
});

function getDatewiseReport() {
	
	var project = $('#multiselectProject option:selected');
	selectedProject = [];
	$(project).each(function(index, brand){
		selectedProject.push($(this).val());
	});

	//console.log(selectedProject);
	
	getAllEnquiryFormReport();
	$("#txtFromDate1").val($('#txtFromDate').val());
	$("#txtToDate1").val($('#txtToDate').val());
}

function getAllEnquiryFormReport() {

	$("#downloadCSV").append("<input type='hidden' value='"+$('#txtFromDate').val()+"' name='fromdate' id='txtFromDate1'/>");
	$("#downloadCSV").append("<input type='hidden' value='"+$('#txtToDate').val()+"' name='todate' id='txtToDate1'/>");
	
	$("#downloadCSV").append("<input type='hidden' value='"+USER_VERTICALES_GV+"' name='userVerticals' />");
	
	//alert($('#txtFromDate').val());
	//alert($('#txtToDate').val());
	$("#misReportDetails").dataTable().fnDestroy();
	$("#misReportDetails tbody").empty();
	$("#mainPageLoad").show();//$('#projectid').val() -- $('#txtFromDate').val()
	
	$('#multiProjectid').val(""); 
	 var urlPP = '';
	 if(selectedProject=='' || selectedProject==null){
		 $('#multiProjectid').val($('#projectid').val());
		 urlPP = "misReport?projectid="+$('#projectid').val()+"&userid=&fromdate="+$('#txtFromDate').val()+"&todate="+$('#txtToDate').val()+"&userVerticals="+USER_VERTICALES_GV;
	 } else {
		 $('#multiProjectid').val(selectedProject.join(","));
		 urlPP = "misReport?projectid="+selectedProject.join(",")+"&userid=&fromdate="+$('#txtFromDate').val()+"&todate="+$('#txtToDate').val()+"&userVerticals="+USER_VERTICALES_GV;
	 } 
	 
	//var urlPP = "misReport?projectid="+$('#projectid').val()+"&userid=&fromdate="+$('#txtFromDate').val()+"&todate="+$('#txtToDate').val();
	
	var i = 0
	$.getJSON(urlPP, function (data) {
		$.each(data, function (index, value) {
			var val = "";
			if (value.qry_msg != "MAX_LIMIT") {
				var val = $("<tr><td>"+value.projectname+"</td><td>"+value.enquiryname+"</td><td>"+value.mobilephone+"</td><td>"+value.tokenno+"</td><td>"+value.created+"</td><td>"+value.name+"</td><td>"+value.email+"</td><td>"+value.budget+"</td><td>"+value.carpet_area_requirement+"</td><td>"+value.walk_in_source__c+"</td><td>"+value.user_name+"</td><td>"+value.attended+"</td></tr>");
				$("#misReportDetails tbody").append(val);
				i = i+1
			} else {
				
				swal({
                	title: "Records exceeding 5000. Please narrow down dates or select few projects",
          			text: "Requested records count is: "+value.qry_count,
          			//timer: 8000,
          			type: "warning",
                });
				
				$("#swal2-title").css({"font-size": "22px"});
				//alert ("Can't fetch more than 5000 record (requested record count "+value.qry_count+")");
			}
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

function createdOfferProject(){
	var project = $('#offerProjectFilter option:selected');
	var selectedProjectOffer = [];
	$(project).each(function(index, brand){
		selectedProjectOffer.push($(this).val());
	});
	
	$('#mainPageLoad').show();	
	// $("#createdOfferTable").empty();
	//alert ("Test Call 123 456");
	 
	var contextPath = $("#pageContext").val();
	var csPath = '';
	var projectid = "";
	 
	if(selectedProjectOffer=='' || selectedProjectOffer==null){
		projectid = $('#projectid').val();
	} else {
		projectid = selectedProjectOffer.join(",");
	}  
	 
	//$.get("getEOIReport",{"projectSfid":$('#projectid').val(), "fromDate":$('#txtEOIFromDate').val(), "toDate":$('#txtEOIToDate').val()},function(data){				 
			
	
	$.get("getOfferList",{"userid":"","projectid":projectid, "fromDate":$('#txtFromDateOffer').val(),"toDate":$('#txtToDateOffer').val(), "userVerticals":USER_VERTICALES_GV},function(data){				 
		
	}).done(function(data){
		$("#createdOfferTable").DataTable().destroy();
		
		$("#createdOfferTable tbody").empty();
		
		//alert("Data:-"+data);
		var obj =JSON.stringify(data);
		var obj1 =JSON.parse(obj);
		//alert(obj1.length);
		if(obj1!=null)  {
			for(var i=0;i<obj1.length;i++){
				if (obj1[i].qry_msg != "MAX_LIMIT") {
					var schemename = "";
					if(obj1[i].scheme_name=="Select Scheme") {
						schemename="";
					} else{
						schemename=obj1[i].scheme_name;
					}
					//csPath = contextPath+'/Costsheet?name='+obj1[i].costsheet_path + '&from=ofrList';
					csPath = contextPath+'/Costsheet?name='+encodeURIComponent(obj1[i].costsheet_path) + '&from=ofrList';
					var fileIcon = '';
					if (obj1[i].costsheet_path != null) {
						fileIcon = '<i class="fa fa-file"></i>';
					} else {
						fileIcon = '';
					}
					var verticle__c = '';
					if (obj1[i].verticle__c != null) {
						verticle__c = obj1[i].verticle__c;
					} else {
						verticle__c = '';
					}
					
					var closing_manager_name__c = '';
					if (obj1[i].closing_manager_name__c != null) {
						closing_manager_name__c = obj1[i].closing_manager_name__c;
					} else {
						closing_manager_name__c = '';
					}
					
					var sourcing_manager_name__c = '';
					if (obj1[i].sourcing_manager_name__c != null) {
						sourcing_manager_name__c = obj1[i].sourcing_manager_name__c;
					} else {
						sourcing_manager_name__c = '';
					}
					
					//var val = $("<tr><td>"+obj1[i].projectname+"</td><td>"+obj1[i].createddate+"</td><td>"+obj1[i].enquiryname+"</td>    <td>"+obj1[i].propstrength__property_name__c+"</td><td>"+closing_manager_name__c+"</td><td>"+sourcing_manager_name__c+"</td>        <td>"+verticle__c+"</td><td>"+obj1[i].contactname+"</td><td>"+obj1[i].payment_plan+"</td><td>"+obj1[i].offername+"</td><td>"+schemename+"</td><td>"+obj1[i].scheme_rate+"</td><td>"+obj1[i].amount+"</td><td>"+obj1[i].description+"</td><td>"+obj1[i].cs_final_amount+"</td> <td>  <a target='_blank' href='"+csPath+"'>"+fileIcon+"</a></td><td data-th='Action'> <button type='button' onclick='moreDetails(this,\""+obj1[i].enquiry_sfid+"\","+i+")'  id=\""+obj1[i].enquiryname+"\"  class='btn btnDefaultBlue btn-default btn-xs brdClrBlue moreDetail ' > Details </button></td><td><button class='btn btnDefaultBlue btn-default' onclick='getofferApplicantDetails(this, \""+obj1[i].offer_sfid+"\", \""+obj1[i].enquiry_sfid+"\",  \""+obj1[i].contact_sfid+"\",  \""+obj1[i].offername+"\",  \""+obj1[i].enquiryname+"\", \""+obj1[i].propstrength__property__c+"\", \""+i+"\", \"offer\")'><i class='fa fa-print printficon'></i></button></td></tr>");
					var val = $("<tr><td>"+obj1[i].projectname+"</td><td>"+obj1[i].createddate+"</td><td>"+obj1[i].enquiryname+"</td>    <td>"+obj1[i].propstrength__property_name__c+"</td><td>"+closing_manager_name__c+"</td><td>"+sourcing_manager_name__c+"</td>        <td>"+verticle__c+"</td><td>"+obj1[i].contactname+"</td><td>"+obj1[i].payment_plan+"</td><td>"+obj1[i].offername+"</td><td>"+schemename+"</td><td>"+obj1[i].scheme_rate+"</td><td>"+obj1[i].amount+"</td><td>"+obj1[i].description+"</td><td>"+obj1[i].cs_final_amount+"</td> <td>  <a target='_blank' href='"+csPath+"'>"+fileIcon+"</a></td><td>"+obj1[i].propStrength__Car_Parking_Name__c+"</td><td>"+obj1[i].propStrength__Category_of_Parking__c+"</td><td>"+obj1[i].location_of_Parking__c+"</td><td>"+obj1[i].parking_Area_Sq_Ft__c+"</td><td>"+obj1[i].dimensions__c+"</td><td data-th='Action'> <button type='button' onclick='moreDetails(this,\""+obj1[i].enquiry_sfid+"\","+i+")'  id=\""+obj1[i].enquiryname+"\"  class='btn btnDefaultBlue btn-default btn-xs brdClrBlue moreDetail ' > Details </button></td></tr>");
					$("#createdOfferTable tbody").append(val);
				} else {
					swal({
	                	title: "Records exceeding 5000. Please narrow down dates or select few projects",
	          			text: "Requested records count is: "+obj1[i].qry_count,
	          			//timer: 8000,
	          			type: "warning",
	                });
					
					$("#swal2-title").css({"font-size": "22px"});
					return false;
				}
			}
			
		}
		
		$('#createdOfferTable').DataTable( {
			 dom: 'Bfrtip',
			 "buttons": [
				 { "extend": 'excel', "text":'Export To Excel',"className": 'btn btn-default btn-xs'}
		      ],
		      "order": []
		 });
		
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






function cancelledOffer(){
	var project = $('#cancelledOfferProjectDD option:selected');
	var selectedProjectOffer = [];
	$(project).each(function(index, brand){
		selectedProjectOffer.push($(this).val());
	});
	
	$('.cancelledOfferSpinner span').html('<i class="fa fa-spinner fa-spin" style="color:#000"></i>'); 	
	 
	var projectid = "";
	 
	if(selectedProjectOffer=='' || selectedProjectOffer==null){
		projectid = $('#projectid').val();
	} else {
		projectid = selectedProjectOffer.join(",");
	}  
	 
	$.get("getOfferCancelledList",{"projectid":projectid, "fromDate":$('#cancelledOfferFromDate').val(),"toDate":$('#cancelledOfferToDate').val(), "userVerticals":USER_VERTICALES_GV},function(data){				 
		
	}).done(function(data){
		$("#cancelledOfferTable").DataTable().destroy();
		
		$("#cancelledOfferTable tbody").empty();
		
		var obj =JSON.stringify(data);
		var obj1 =JSON.parse(obj);
		var html = '';
		
		if(obj1!=null)  {
			for(var i=0;i<obj1.length;i++){
				if (obj1[i].qry_msg != "MAX_LIMIT") {
					 
					 
					var verticle__c = '';
					if (obj1[i].verticle__c != null) {
						verticle__c = obj1[i].verticle__c;
					} else {
						verticle__c = '';
					}
					
					var closing_manager_name__c = '';
					if (obj1[i].closing_manager_name__c != null) {
						closing_manager_name__c = obj1[i].closing_manager_name__c;
					} else {
						closing_manager_name__c = '';
					}
					
					var sourcing_manager_name__c = '';
					if (obj1[i].sourcing_manager_name__c != null) {
						sourcing_manager_name__c = obj1[i].sourcing_manager_name__c;
					} else {
						sourcing_manager_name__c = '';
					}
					
					html += "<tr>" +
								"<td>"+obj1[i].projectname+"</td>" +
								"<td>"+obj1[i].createddate+"</td>" +
								"<td>"+obj1[i].enquiryname+"</td>" +
								"<td>"+obj1[i].propstrength__property_name__c+"</td>" +
								"<td>"+closing_manager_name__c+"</td>" +
								"<td>"+sourcing_manager_name__c+"</td>" +
								"<td>"+verticle__c+"</td>" +
								"<td>"+obj1[i].contactname+"</td>" +
								"<td>"+obj1[i].offername+"</td>" +
								"<td>"+obj1[i].propstrength__reason_for_loss__c+"</td>" +
								"<td>"+obj1[i].propstrength__comment__c+"</td>" +
							"</tr>";
	
					html = html.replace(/undefined/g, " - ");
					html = html.replace(/null/g, " - ");
					
					$("#cancelledOfferTable tbody").empty();
					$("#cancelledOfferTable tbody").append(html);
				} else {
					swal({
	                	title: "Records exceeding 5000. Please narrow down dates or select few projects",
	          			text: "Requested records count is: "+obj1[i].qry_count,
	          			type: "warning",
	                });
					
					$("#swal2-title").css({"font-size": "22px"});
					return false;
				}
			}
			
		}
		
		$('#cancelledOfferTable').DataTable( {
			 dom: 'Bfrtip',
			 "buttons": [
				 { "extend": 'excel', "text":'Export To Excel',"className": 'btn btn-default btn-xs' }
		      ],
		      "order": []
		 });
		
		$('.cancelledOfferSpinner span').html(''); 
		
	});	
}


/* For Tab Scroll*/
var hidWidth;
var scrollBarWidths = 40;

var widthOfList = function(){
	var itemsWidth = 0;
	$('.navTabList li').each(function(){
		var itemWidth = $(this).outerWidth();
		itemsWidth+=itemWidth;
	});
	return itemsWidth;
};

var widthOfHidden = function(){
	return (($('.navTabsWrapper').outerWidth())-widthOfList()-getLeftPosi())-scrollBarWidths;
};

var getLeftPosi = function(){
	return $('.navTabList').position().left;
};

var reAdjust = function(){
	if (($('.navTabsWrapper').outerWidth()) < widthOfList()) {
		$('.scroller-right').show();
	}
	else {
		$('.scroller-right').hide();
	}
	if (getLeftPosi()<0) {
		$('.scroller-left').show();
	}
	else {
		$('.navTabItem').animate({left:"-="+getLeftPosi()+"px"},'slow');
		$('.scroller-left').hide();
	}
}

reAdjust();

$(window).on('resize',function(e){  
	reAdjust();
});

$('.scroller-right').click(function() {
	$('.scroller-left').fadeIn('slow');
	$('.scroller-right').fadeOut('slow');
	$('.navTabList').animate({left:"+="+widthOfHidden()+"px"},'slow',function(){});
});

$('.scroller-left').click(function() {
	$('.scroller-right').fadeIn('slow');
	$('.scroller-left').fadeOut('slow');
  	$('.navTabList').animate({left:"-="+getLeftPosi()+"px"},'slow',function(){});
});   
/* END For Tab Scroll*/