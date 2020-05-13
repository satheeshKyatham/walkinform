function onProjectSelect(source) {
	var optionText = $("#projectselection option:selected").text();
	
	var source = '"'+source+'"';
	
	if($('#loged_role').val()=='5'){
		$("#role_page").empty();
		var option = "<select class='inputLabel' onchange='onChangeRole()' id='roleSelected' style='border-color: #000000 !important;   width: 100%;    min-height: 33px;    margin-bottom: 5px;'><option>Select Role</option>";
 		option = option+"<option value='AM'>Allocation Manager</option>";
		option = option+"<option value='MIS'>MIS Report</option></select>";
		
		$("#role_page").append(option);
	}	
	else if($('#loged_role').val()=='4')
		{
			window.location.href = "assigntoken?userId=" + $('#loged_userid').val()+"&projectid="+$('#projectSelected').val()+"&projectname="+$('#projectSelected option:selected').text() ;
		}
	else if($('#loged_role').val()=='3' || $('#loged_role').val()=='1' )
	  {
		 window.location.href = "assignedusers?userId=" + $('#loged_userid').val()+"&projectid="+$('#projectSelected').val()+"&projectname="+$('#projectSelected option:selected').text() ;
	  }
	else if($('#loged_role').val()=='2')
	  {
		window.location.href = "misreportview?projectid="+$('#projectSelected').val()+"&projectname="+$('#projectSelected option:selected').text() ;
	  }	
	else if($('#loged_role').val()=='6') 
	  {
		$("#role_page").empty();
		var option = "<select class='inputLabel' onchange='onChangeRole()' id='roleSelected' style='border-color: #000000 !important;   width: 100%;    min-height: 33px;    margin-bottom: 5px;'><option>Select Role</option>";
 		option = option+"<option value='AM'>Allocation Manager</option>";
		option = option+"<option value='MIS'>MIS Report</option>";
		option = option+"<option value='CM'>Closing Manager</option></select>";
		
		$("#role_page").append(option);
	  }
	else if($('#loged_role').val()=='7') 
	  {
		$("#role_page").empty();
		var option = "<select class='inputLabel' onchange='onChangeRole()' id='roleSelected' style='border-color: #000000 !important;   width: 100%;    min-height: 33px;    margin-bottom: 5px;'><option>Select Role</option>";
		option = option+"<option value='AM'>Allocation Manager</option>";
		option = option+"<option value='CM'>Closing Manager</option></select>";
		$("#role_page").append(option);
 
	  }else if($('#loged_role').val()=='11') 
	  {
			$("#role_page").empty();
			var option = "<select class='inputLabel' onchange='onChangeRole()' id='roleSelected' style='border-color: #000000 !important;   width: 100%;    min-height: 33px;    margin-bottom: 5px;'><option>Select Role</option>";
			option = option+"<option value='IM'>Inventory Manager</option>";
			option = option+"<option value='AM'>Allocation Manager</option>";
			option = option+"<option value='CM'>Closing Manager</option>";
			option = option+"<option value='MIS'>MIS Report</option></select>";
				$("#role_page").append(option);
		  }
	else if($('#loged_role').val()=='10') 
	  {
		$("#role_page").empty();
		var option = "<select class='inputLabel' onchange='onChangeRole()' id='roleSelected' style='border-color: #000000 !important;   width: 100%;    min-height: 33px;    margin-bottom: 5px;'><option>Select Role</option>";
		option = option+"<option value='IM'>Inventory Manager</option>";
		option = option+"<option value='AM'>Allocation Manager</option>";
		option = option+"<option value='CM'>Closing Manager</option>";
		option = option+"<option value='MIS'>MIS Report</option></select>";
			$("#role_page").append(option);
	  }else if($('#loged_role').val()=='12') 
		  {
			var option = "<select class='inputLabel' onchange='onChangeRole()' id='roleSelected' style='border-color: #000000 !important;   width: 100%;    min-height: 33px;    margin-bottom: 5px;'><option>Select Role</option>";
			$("#role_page").empty();
			option = option+"<option value='IM'>Inventory Manager</option>";
			option = option+"<option value='AM'>Allocation Manager</option>";
			option = option+"<option value='CM'>Closing Manager</option>";
			option = option+"<option value='MIS'>MIS Report</option></select>";
			$("#role_page").append(option);
		  }else if($('#loged_role').val()=='13') 
		  {
				var option = "<select class='inputLabel' onchange='onChangeRole()' id='roleSelected' style='border-color: #000000 !important;   width: 100%;    min-height: 33px;    margin-bottom: 5px;'><option>Select Role</option>";
				$("#role_page").empty();
				option = option+"<option value='IM'>Inventory Manager</option>";
				option = option+"<option value='AM'>Allocation Manager</option>";
				option = option+"<option value='CM'>Closing Manager</option>";
				option = option+"<option value='MIS'>MIS Report</option>";
				option = option+"<option value='PADMIN'>Admin Panel</option></select>";
				$("#role_page").append(option);
			  }
		  else if($('#loged_role').val()=='14') 
		  {
			  window.location.href = "kycrole?projectid="+$('#projectSelected').val()+"&projectname="+$('#projectSelected option:selected').text();
		  }
		  else if($('#loged_role').val()=='15') 
		  {
			  window.location.href = "paymentapproval?projectid="+$('#projectSelected').val()+"&projectname="+$('#projectSelected option:selected').text();;
		  }
		  else if($('#loged_role').val()=='16')
		  {
			  $("#role_page").empty();
			  var option = "<select class='inputLabel' onchange='onChangeRole("+source+")' id='roleSelected' style='border-color: #000000 !important;   width: 100%;    min-height: 33px;    margin-bottom: 5px;'><option>Select Role</option>";
				option = option+"<option value='IM'>Inventory Manager</option>";
				option = option+"<option value='AM'>Allocation Manager</option>";
				option = option+"<option value='CM'>Closing Manager</option>";
				option = option+"<option value='MIS'>MIS Report</option>";
				option = option+"<option value='EOIPA'>EOI Payment Approval</option>";
				option = option+"<option value='KYCA'>KYC Approval</option></select>";
				$("#role_page").append(option);
		  }
		  else if($('#loged_role').val()=='17')
		  {
			  $("#role_page").empty();
			  var option = "<select class='inputLabel' onchange='onChangeRole()' id='roleSelected' style='border-color: #000000 !important;   width: 100%;    min-height: 33px;    margin-bottom: 5px;'><option>Select Role</option>";
				option = option+"<option value='CM'>Closing Manager</option>";
				option = option+"<option value='OFFLINEADM'>OFFLINE EOI</option></select>";
				$("#role_page").append(option);
		  }
	 
		$("#loginMsg").html('');
	}

function onChangeRole(source) {
	if($("#roleSelected").val()=='AM') {
		if (source != 'HEADER_COMMON') {
			window.location.href = "assigntoken?userId=" + $('#loged_userid').val()+"&projectid="+$('#projectSelected').val()+"&projectname="+$('#projectSelected option:selected').text() ;
		} else {
			var path = "assigntoken?userId=" + $('#loged_userid').val()+"&projectid="+$('#projectSelected').val()+"&projectname="+$('#projectSelected option:selected').text();
			pageRedirect (path);
		}
	} else if($("#roleSelected").val()=='MIS') {
		if (source != 'HEADER_COMMON') {
			window.location.href = "misreportview?projectid="+$('#projectSelected').val()+"&projectname="+$('#projectSelected option:selected').text() ;
		} else {
			var path = "misreportview?projectid="+$('#projectSelected').val()+"&projectname="+$('#projectSelected option:selected').text() ;
			pageRedirect (path);
		}
	} else if($("#roleSelected").val()=='CM'){
		if (source != 'HEADER_COMMON') {
			window.location.href = "assignedusers?userId=" + $('#loged_userid').val()+"&projectid="+$('#projectSelected').val()+"&projectname="+$('#projectSelected option:selected').text() ;
		} else {
			var path = "assignedusers?userId=" + $('#loged_userid').val()+"&projectid="+$('#projectSelected').val()+"&projectname="+$('#projectSelected option:selected').text() ;
			pageRedirect (path);
		}
	} else if($("#roleSelected").val()=='OA'){
		if (source != 'HEADER_COMMON') {
			window.location.href = "admininventory/"+$('#projectSelected').val()+"/"+$('#projectSelected option:selected').text() +"/"+$('#loged_userid').val() ;
		} else {
			var path = "admininventory/"+$('#projectSelected').val()+"/"+$('#projectSelected option:selected').text() +"/"+$('#loged_userid').val() ;
			pageRedirect (path);
		}
	} else if($("#roleSelected").val()=='IM'){
		if (source != 'HEADER_COMMON') {
			window.location.href = "admininventory/"+$('#projectSelected').val()+"/"+$('#projectSelected option:selected').text()+"/"+ $('#loged_userid').val()+"/";
		} else {
			var path = "admininventory/"+$('#projectSelected').val()+"/"+$('#projectSelected option:selected').text()+"/"+ $('#loged_userid').val()+"/";
			pageRedirect (path);
		}
	} else if($("#roleSelected").val()=='PADMIN'){
		if (source != 'HEADER_COMMON') {
			window.location.href = "usermaster";
		} else {
			var path = "usermaster";
			pageRedirect (path);
		}
	} else if($("#roleSelected").val()=='EOIPA'){
		if (source != 'HEADER_COMMON') {
			window.location.href = "paymentapproval?projectid="+$('#projectSelected').val()+"&projectname="+$('#projectSelected option:selected').text();
		} else {
			var path = "paymentapproval?projectid="+$('#projectSelected').val()+"&projectname="+$('#projectSelected option:selected').text();
			pageRedirect (path);
		}
	} else if($("#roleSelected").val()=='KYCA') {
		if (source != 'HEADER_COMMON') {
			window.location.href = "kycrole?projectid="+$('#projectSelected').val()+"&projectname="+$('#projectSelected option:selected').text();
		} else {
			var path = "kycrole?projectid="+$('#projectSelected').val()+"&projectname="+$('#projectSelected option:selected').text();
			pageRedirect (path);
		}
	} else if($("#roleSelected").val()=='OFFLINEADM'){
		if (source != 'HEADER_COMMON') {
			window.location.href = "offlineEOI?projectid="+$('#projectSelected').val()+"&projectname="+$('#projectSelected option:selected').text();
		} else {
			var path = "offlineEOI?projectid="+$('#projectSelected').val()+"&projectname="+$('#projectSelected option:selected').text();
			pageRedirect (path);
		}
	}
}


function pageRedirect (path) {
	var win = window.open(PAGECONTEXT_GV+path, '_blank');
	if (win) {
	    win.focus();
	} else {
	    alert('Please allow popups for this website');
	}
}