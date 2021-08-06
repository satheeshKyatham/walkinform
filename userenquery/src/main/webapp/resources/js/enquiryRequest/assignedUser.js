/**
 * Added By Satheesh K - 11th June 2019
 */
$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});
var today ='';
var atFromDate= "";
var atToDate= "";
$(document).ready(function() {
	//setting region name on page load
	var urlPP = "getSelectedProjectMaster?projectid="+$('#projectid').val();
	$.getJSON(urlPP, function (data) {
		$("#region_name").val(data.regionname);
	}).done(function() {
	}).error(function() { alert("error");
	});
	
	 today = new Date();
	 
	 document.getElementById("txtFromDateFollowUp").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
	 document.getElementById("txtToDateFollowUp").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
	 
	 if (sessionStorage.getItem('dateCount') < 1) {
		 document.getElementById("txtFromDateAssigned").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
		 document.getElementById("txtToDateAssigned").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
	 } else {
		$("#txtFromDateAssigned").val(sessionStorage.getItem('fromDate'));
		$("#txtToDateAssigned").val(sessionStorage.getItem('toDate'));
	 }
	loadData();
});
function loadData () {
			  $("#mainPageLoad").show();
			  $("#amsearch").prop("disabled", true);
			  
			  $('#amsearch span').html('<i class="fa fa-spinner fa-spin" style="color:#fff !important"></i>');	
			  
			  
			  getPendingAssignList('DEFAULT');
			  //closingDashboard();
		}
function getPendingAssignList(sourecVal) {
			
			$(".tokenRadio input[name=atData][value='assignedToken']").prop("checked",false);
			$(".tokenRadio input[name=atData][value='totalPendingLead']").prop("checked",false);
			if (sourecVal == "DEFAULT") {
				$(".tokenRadio input[name=atData][value='assignedToken']").prop("checked",true);
				$('#atFilterCol').show();
			} else if (sourecVal == "TOTALPENDING") {
				$(".tokenRadio input[name=atData][value='totalPendingLead']").prop("checked",true);
				$('#atFilterCol').hide();
			} else {
				$(".tokenRadio input[name=atData][value='assignedToken']").prop("checked",true);
				$('#atFilterCol').show();
			} 
	
			/* From & To date from javaScript sessionStorage */
			
			sessionStorage.setItem('fromDate', $("#txtFromDateAssigned").val());
			sessionStorage.setItem('toDate', $("#txtToDateAssigned").val());
			sessionStorage.setItem('dateCount', 1);
			
			atFromDate = sessionStorage.getItem('fromDate');
			atToDate = sessionStorage.getItem('toDate');
			
			console.log ("fromDate:: " + sessionStorage.getItem('fromDate'));
			console.log ("toDate:: " + sessionStorage.getItem('toDate'));
			console.log ("dateCount:: " + sessionStorage.getItem('dateCount'));
			
			/* END From & To date from javaScript sessionStorage */
	
			$("#dtOrderExample").dataTable().fnDestroy();
			$("#dtOrderExample tbody").empty();
			 $("#mainPageLoad").show();
			var urlPP = "getAssignedUserToken?source="+sourecVal+"&projectid="+$('#projectid').val()+"&user_id="+$('#userid').val()+"&fromdate="+atFromDate+"&todate="+atToDate;
			var i = 0
			var followdate = "";
			var followtype = "";
			
			//alert("Alert");
			$.getJSON(urlPP, function (data) {
				$.each(data, function (index, value) {
					debugger;
					//alert("Alert"+value.offerName);
					
					if (value.followdate != null) {
						followdate = value.followdate;
					} else {
						followdate = "";
					}
					
					if (value.followtype != null) {
						followtype = value.followtype;
					} else {
						followtype = "";
					}
					
					var val = "<tr><td><label>"+value.nv_token_id+"</label></td><td>"+value.token_no+"</td><td>"+value.name+"</td><td><a href='tel:"+value.mobileno+"'>"+value.mobileno+"</a></td><td>"+value.priority_no__c+"</td><td>"+value.closing_manager_name__c+"</td> <td>"+followdate+"</td> <td>"+followtype+"</td> <td>"+value.starteddate+"</td><td>"+value.offerName+"</td><td>"+value.isdone+"</td>";
					
					if(value.isdone=='Attended')
						{
						 	val=val+"<td><input class='assignedListBtn btn blue_btn btnCommon' type='button' value='View Details' onclick=startsession('"+value.nv_token_id+"','"+value.mobileno+"','"+value.token_no+"','Y','"+value.countrycode+"')></td>";//
						}
					else if(value.isdone=='Started')
						{
							val=val+"<td><input class='assignedListBtn btn blue_btn btnCommon' type='button' value='Start Session' onclick=startsession('"+value.nv_token_id+"','"+value.mobileno+"','"+value.token_no+"','N','"+value.countrycode+"')></td>";
						}
					else
						val=val+"<td><input class='assignedListBtn btn blue_btn btnCommon' type='button' value='Start Session' onclick=startsession('"+value.nv_token_id+"','"+value.mobileno+"','"+value.token_no+"','N','"+value.countrycode+"')></td>";
					val=val+"</tr>";
					
					$("#dtOrderExample tbody").append(val);
					i = i+1
				});
				
				$('#dtOrderExample').DataTable({
					destroy: true,
					language: {
						searchPlaceholder: "Search"
					},
					order: [[ 0, "desc" ]]
				});
				
			}).done(function() {
				$("#amsearch").prop("disabled", false);
				
				$('#amsearch span').html('');
				
				$("#mainPageLoad").hide();
				/*$('#dtOrderExample').DataTable();*/
				if (i == 0){ }
				closingDashboard ();
				//sessionStorage.setItem('fromDate', $("#txtFromDateAssigned").val());
				//sessionStorage.setItem('toDate', $("#txtToDateAssigned").val());
			}).error(function() { //alert("error");
			 $("#amsearch").prop("disabled", false);
			 $('#amsearch span').html('');
			});
			
		}


function getTodayFollowup() {
	$('.rattingCBCol').hide();
	$("#followUpList").dataTable().fnDestroy();
	$("#followUpList tbody").empty();
	 $("#mainPageLoad").show();
	var urlPP = "getTodayFollowUp?source=followup&projectid="+$('#projectid').val()+"&user_id="+$('#userid').val()+"&fromdate="+$('#txtFromDateFollowUp').val()+"&todate="+$('#txtToDateFollowUp').val();
	var i = 0
	var followdate = "";
	var followtype = "";
	
	//alert("Alert");
	$.getJSON(urlPP, function (data) {
		$.each(data, function (index, value) {
			debugger;
			//alert("Alert"+value.offerName);
			
			if (value.followdate != null) {
				followdate = value.followdate;
			} else {
				followdate = "";
			}
			
			if (value.followtype != null) {
				followtype = value.followtype;
			} else {
				followtype = "";
			}
			
			var fuRatting = "";
			if (value.rating__c != null && value.rating__c != undefined) {
				if (value.rating__c == "Hot") {
					fuRatting = "fuRattingHot";
				} else if (value.rating__c == "Cold") {
					fuRatting = "fuRattingCold";
				} else if (value.rating__c == "Warm") {
					fuRatting = "fuRattingWarm";
				} else if (value.rating__c == "Lost") {
					fuRatting = "fuRattingLost";
				}
			} else {
				fuRatting = "";
			}
			
			
			var val = "<tr class='"+fuRatting+"'><td><label>"+value.nv_token_id+"</label></td><td>"+value.token_no+"</td><td>"+value.enqname+"</td><td>"+value.name+"</td><td><a href='tel:"+value.mobileno+"'>"+value.mobileno+"</a></td> <td>"+value.closing_manager_name__c+"</td> <td>"+followdate+"</td> <td>"+followtype+"</td> <td>"+value.rating__c+"</td> <td>"+value.isdone+"</td>";
			
			if(value.isdone=='Attended')
				{
				 	val=val+"<td ><input class='assignedListBtn btn blue_btn btnCommon' type='button' value='View Details' onclick=startsession('"+value.nv_token_id+"','"+value.mobileno+"','"+value.token_no+"','Y','"+value.countrycode+"')></td>";//
				}
			else if(value.isdone=='Started')
				{
					val=val+"<td ><input class='assignedListBtn btn blue_btn btnCommon' type='button' value='Start Session' onclick=startsession('"+value.nv_token_id+"','"+value.mobileno+"','"+value.token_no+"','N','"+value.countrycode+"')></td>";
				}
			else
				val=val+"<td ><input class='assignedListBtn btn blue_btn btnCommon' type='button' value='Start Session' onclick=startsession('"+value.nv_token_id+"','"+value.mobileno+"','"+value.token_no+"','N','"+value.countrycode+"')></td>";
			val=val+"</tr>";
			
			$("#followUpList tbody").append(val);
			i = i+1
		});
		
		$('#followUpList').DataTable({
			destroy: true,
			language: {
				searchPlaceholder: "Search"
			},
			order: [[ 0, "desc" ]]
		});
		
	}).done(function() {
		$("#amsearch").prop("disabled", false);
		
		$('#amsearch span').html('');
		
		$("#mainPageLoad").hide();
		/*$('#followUpList').DataTable();*/
		if (i == 0){ }
		
		$('.rattingCBCol').show();
		closingDashboard ();
		//sessionStorage.setItem('fromDate', $("#txtFromDateAssigned").val());
		//sessionStorage.setItem('toDate', $("#txtToDateAssigned").val());
	}).error(function() { //alert("error");
	 $("#amsearch").prop("disabled", false);
	 $('#amsearch span').html('');
	});
}
 
function closingDashboard () {
	
	$.post(PAGECONTEXT_GV+"closingDashboard",{"projectSFID":$('#projectid').val(), "userid":$('#userid').val() },function(data){
		$('.todayAssLead').html("");
		$('.totalPendingLead').html("");
		$('.todayFollowupCount').html("");
		//$('.totalPendingKYCApproval').html("");
		//$('.totalCreatedOffer').html("");
		//$('.totalBookingDone').html("");
	}).done(function(data){
		var obj =JSON.parse(data);
		
		if(obj!=null){
			$('.todayAssLead').html(obj.todayAssigned);
			$('.totalPendingLead').html(obj.totalPendingLead);
			$('.todayFollowupCount').html(obj.todayFollowup);
			//$('.totalPendingKYCApproval').html(obj.totalPendingKYCApproval);
			//$('.totalCreatedOffer').html(obj.totalCreatedOffer);
			//$('.totalBookingDone').html(obj.totalBookingDone);
		} else {
			//smapleUnit = "";
		}
	});	
}

function dashboardBox (source) {
	if (source == "TODAYSASSIGNED") {
		document.getElementById("txtFromDateAssigned").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
		document.getElementById("txtToDateAssigned").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
		
		getPendingAssignList('DEFAULT');
		
		$('.dbAtl a').trigger('click');
	} else if (source == "TODAYSFOLLOWUP") {
		document.getElementById("txtFromDateFollowUp").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
		document.getElementById("txtToDateFollowUp").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
		
		$('.dbTfu a').trigger('click');
	} else if (source == "TOTALPENDINGLEAD")  {
		
		getPendingAssignList('TOTALPENDING');
		
		$('.dbAtl a').trigger('click');
	}
}

$('.tokenRadio input[type=radio][name=atData]').change(function() {
    if (this.value == 'assignedToken') {
    	getPendingAssignList('DEFAULT');
    }
    else if (this.value == 'totalPendingLead') {
    	getPendingAssignList('TOTALPENDING');
    }
});

function selectedratting () {
	var ratting = "";
	var i = 0;
	$.each($("input[name='rattingCB']:checked"), function(){ 
		if ($(this).val() != "All") {
			if (i == 0) {
				ratting += $(this).val();
			} else {
				ratting += "|"+$(this).val();
			}
			i++;
		}  
		//ratting.push($(this).val());
	});
	
	$('#followUpList').DataTable().columns('#rattingColumn').search( ratting, true, false ).draw();
}