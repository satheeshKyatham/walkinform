if (navigator.userAgent.indexOf('Chrome') ==-1)
{
	alert("For Better Experience, Please Use Chrome Browser...");
	window.location = "chromeerror"
}
function startsession( value,mobileno,token,submitedSates,countryCode){
	 
	//update start session
	if(submitedSates=="N")
		{
			getDealStarted(value);
		}
	
	var str = mobileno;
	var code = str.substr(0, 3);
	code=code.replace("+","%2B");
	var mobileNo = str.substr(3, 13);
	
	var url=$("#contextPath").val();
	debugger;
	if(countryCode==undefined || countryCode==null || countryCode=='null')
		{
			countryCode="%2B91";
		}
	else
		{
			countryCode=countryCode.replace("+","%2B");
		}
	debugger;
	var finalMobile="";
	console.log("MobileNo:-"+mobileno);
	
	if(mobileno.includes("+91"))
		{
			var str = mobileno;
			finalMobile = str.slice(3);
		}
	else
		{
			finalMobile=mobileno;
		}
	
	
	//window.location.href=url+"/"+"inventory"+"/"+"48"+"/"+"123123"+"/"+"ABCDEFG"+"/"+"8898821453"+"/"+"E90";
	window.location.href=url+"/"+'salesDetails?tokenid='+value+'&countrycode='+countryCode+'&mobileno='+finalMobile+'&projectSfid='+$('#projectid').val()+'&projectName='+$('#projectname').val()+'&token='+token+'&isView=N&salesViewType='+submitedSates;
	/*window.location.href=url+"/"+'salesDetails?token='+token+'&countrycode='+code+'&mobileno='+mobileNo+'&projectSfid='+$('#projectid').val()+'&projectName='+$('#projectname').val();*/
} 
 
 
function validateDesk() {
	$("#projectdropdownlist").hide();
	var optionText = $("#projectselection option:selected").text();
	  $("#mainPageLoad").show();
	  if($("#userid").val().trim()=='' || $("#password").val().trim()==''){
		  $("#mainPageLoad").hide();
	  	 $(".loginMsg").html("Email ID and Password not empty.");  
	  return false;
	}	
	$.ajax({

	    url: 'userValidateLogin',
	    data: {emailid : $("#userid").val(),
	      	  password : $("#password").val()//,
	      	  //projectId:$("#projectselection option:selected").val()
	    },
	    type: 'POST',
	    success: function(data) { 
	    	
		   debugger;
		  if(data.msg==='Success'){
			  $("#login_div").hide();
			  $("#mainPageLoad").show();
			  $("#projectdropdownlist").show();
			  
			  //call project List
			  $("#loged_userid_main").append('<input id="loged_userid" type="text" value='+ data.user_id+ '>');
			  /*$("#loged_userid_main").append('<input id="loged_role" type="text" value='+ data.role+ '>');*/
			  $("#loged_userid_main").append('<input id="loged_role" type="text" value='+ data.mst_user_rolemaster_id+ '>');
			  //$("#loged_userid_main").append('<input id="loged_projectName" type="text" value='+ data.projectId+ '>');
			 
			  var urlGetUsers = "getProjectListUserWise?userid="+data.user_id;	
		       var j = 0
		    	$.getJSON(urlGetUsers, function (data) {
		    		option = "<select class='inputLabel' onchange='onProjectSelect()' id='projectSelected' style='border-color: #000000 !important;   width: 100%;    min-height: 33px;    margin-bottom: 5px;'><option>Select Type</option>";
		    		$.each(data, function (index, value) {
		    			var name='';
		    			if(value.name==undefined)
		    				name='';
		    			else
		    				name=value.name;
		    			option = option+"<option value="+value.projectId+">"+value.projectName+"</option>";
		    			j = j+1
		    		});		
		    		option=option+"</select>";
		    	}).done(function() {
		    		if (j == 0){
		    			alert ("No record found for this no 233.");
		    		}	
		    		//alert(option);
		    		$("#mainPageLoad").hide();
		    		$("#sip_id").append(option);//
		    		//$("#login_div").hide();
		    		
		    	});
		  }else{
			  $(".loginMsg").html(data.msg);
			  $("#mainPageLoad").hide();
		  }  
	  },
	    error: function(data) {
	    	$("#mainPageLoad").hide();
	    ; }
	});
	//authenticateUser("", "");
} 

function onProjectSelect()
{
	debugger;
	//$("#mainPageLoad").show();
	var optionText = $("#projectselection option:selected").text();
	
	if($('#loged_role').val()=='5')//AM
	  {
		$("#role_page").empty();
		var option = "<select class='inputLabel' onchange='onChangeRole()' id='roleSelected' style='border-color: #000000 !important;   width: 100%;    min-height: 33px;    margin-bottom: 5px;'><option>Select Type</option>";
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
	else if($('#loged_role').val()=='6')//AM
	  {
		$("#role_page").empty();
		var option = "<select class='inputLabel' onchange='onChangeRole()' id='roleSelected' style='border-color: #000000 !important;   width: 100%;    min-height: 33px;    margin-bottom: 5px;'><option>Select Type</option>";
 		option = option+"<option value='AM'>Allocation Manager</option>";
		option = option+"<option value='MIS'>MIS Report</option>";
		option = option+"<option value='CM'>Closing Manager</option></select>";
		
		$("#role_page").append(option);
	  }
	else if($('#loged_role').val()=='7')//AM
	  {
		$("#role_page").empty();
		var option = "<select class='inputLabel' onchange='onChangeRole()' id='roleSelected' style='border-color: #000000 !important;   width: 100%;    min-height: 33px;    margin-bottom: 5px;'><option>Select Type</option>";
		option = option+"<option value='AM'>Allocation Manager</option>";
		option = option+"<option value='CM'>Closing Manager</option></select>";
		$("#role_page").append(option);
 
	  }else if($('#loged_role').val()=='11')//AM
	  {
			$("#role_page").empty();
			var option = "<select class='inputLabel' onchange='onChangeRole()' id='roleSelected' style='border-color: #000000 !important;   width: 100%;    min-height: 33px;    margin-bottom: 5px;'><option>Select Type</option>";
			option = option+"<option value='IM'>Inventory Manager</option>";
			option = option+"<option value='AM'>Allocation Manager</option>";
			option = option+"<option value='CM'>Closing Manager</option>";
			option = option+"<option value='MIS'>MIS Report</option></select>";
				$("#role_page").append(option);
		  }
	else if($('#loged_role').val()=='10')//AM
	  {
		$("#role_page").empty();
		var option = "<select class='inputLabel' onchange='onChangeRole()' id='roleSelected' style='border-color: #000000 !important;   width: 100%;    min-height: 33px;    margin-bottom: 5px;'><option>Select Type</option>";
		option = option+"<option value='IM'>Inventory Manager</option>";
		option = option+"<option value='AM'>Allocation Manager</option>";
		option = option+"<option value='CM'>Closing Manager</option>";
		option = option+"<option value='MIS'>MIS Report</option></select>";
			$("#role_page").append(option);
	  }else if($('#loged_role').val()=='12')//AM
		  {
			var option = "<select class='inputLabel' onchange='onChangeRole()' id='roleSelected' style='border-color: #000000 !important;   width: 100%;    min-height: 33px;    margin-bottom: 5px;'><option>Select Type</option>";
			$("#role_page").empty();
			option = option+"<option value='IM'>Inventory Manager</option>";
			option = option+"<option value='AM'>Allocation Manager</option>";
			option = option+"<option value='CM'>Closing Manager</option>";
			option = option+"<option value='MIS'>MIS Report</option></select>";
			$("#role_page").append(option);
		  }else if($('#loged_role').val()=='13')//AM
		  {
				var option = "<select class='inputLabel' onchange='onChangeRole()' id='roleSelected' style='border-color: #000000 !important;   width: 100%;    min-height: 33px;    margin-bottom: 5px;'><option>Select Type</option>";
				$("#role_page").empty();
				option = option+"<option value='IM'>Inventory Manager</option>";
				option = option+"<option value='AM'>Allocation Manager</option>";
				option = option+"<option value='CM'>Closing Manager</option>";
				option = option+"<option value='MIS'>MIS Report</option>";
				option = option+"<option value='PADMIN'>Admin Panel</option></select>";
				$("#role_page").append(option);
			  }
		  else if($('#loged_role').val()=='14')//KYC Role
		  {
			  //alert("KYC Role");
			  window.location.href = "kycrole?projectid="+$('#projectSelected').val()+"&projectname="+$('#projectSelected option:selected').text();
			 // window.location.href = "assignedusers?userId=" + $('#loged_userid').val()+"&projectid="+$('#projectSelected').val()+"&projectname="+$('#projectSelected option:selected').text() ;
		  }
		  else if($('#loged_role').val()=='15')//KYC Role
		  {
			  window.location.href = "paymentapproval?projectid="+$('#projectSelected').val()+"&projectname="+$('#projectSelected option:selected').text();;
		  }
		  else if($('#loged_role').val()=='16')
		  {
			  $("#role_page").empty();
			  var option = "<select class='inputLabel' onchange='onChangeRole()' id='roleSelected' style='border-color: #000000 !important;   width: 100%;    min-height: 33px;    margin-bottom: 5px;'><option>Select Type</option>";
				option = option+"<option value='IM'>Inventory Manager</option>";
				option = option+"<option value='AM'>Allocation Manager</option>";
				option = option+"<option value='CM'>Closing Manager</option>";
				option = option+"<option value='MIS'>MIS Report</option>";
				//option = option+"<option value='PADMIN'>Admin Panel</option>";
				option = option+"<option value='EOIPA'>EOI Payment Approval</option>";
				option = option+"<option value='KYCA'>KYC Approval</option></select>";
				$("#role_page").append(option);
		  }
		  else if($('#loged_role').val()=='17')
		  {
			  $("#role_page").empty();
			  var option = "<select class='inputLabel' onchange='onChangeRole()' id='roleSelected' style='border-color: #000000 !important;   width: 100%;    min-height: 33px;    margin-bottom: 5px;'><option>Select Type</option>";
				option = option+"<option value='CM'>Closing Manager</option>";
				option = option+"<option value='OFFLINEADM'>OFFLINE EOI</option></select>";
				$("#role_page").append(option);
		  }
	 
		$("#loginMsg").html('');
	}

function onChangeRole()
{
	if($("#roleSelected").val()=='AM')
		window.location.href = "assigntoken?userId=" + $('#loged_userid').val()+"&projectid="+$('#projectSelected').val()+"&projectname="+$('#projectSelected option:selected').text() ;
	else if($("#roleSelected").val()=='MIS')
		window.location.href = "misreportview?projectid="+$('#projectSelected').val()+"&projectname="+$('#projectSelected option:selected').text() ;
	else if($("#roleSelected").val()=='CM')
		window.location.href = "assignedusers?userId=" + $('#loged_userid').val()+"&projectid="+$('#projectSelected').val()+"&projectname="+$('#projectSelected option:selected').text() ;
 	else if($("#roleSelected").val()=='OA')
		window.location.href = "admininventory/"+$('#projectSelected').val()+"/"+$('#projectSelected option:selected').text() +"/"+$('#loged_userid').val() ;
	else if($("#roleSelected").val()=='IM')
		window.location.href = "admininventory/"+$('#projectSelected').val()+"/"+$('#projectSelected option:selected').text()+"/"+ $('#loged_userid').val()+"/";
	else if($("#roleSelected").val()=='PADMIN')
		window.location.href = "usermaster";
	else if($("#roleSelected").val()=='EOIPA')
		window.location.href = "paymentapproval?projectid="+$('#projectSelected').val()+"&projectname="+$('#projectSelected option:selected').text();
	else if($("#roleSelected").val()=='KYCA')
		window.location.href = "kycrole?projectid="+$('#projectSelected').val()+"&projectname="+$('#projectSelected option:selected').text();
	else if($("#roleSelected").val()=='OFFLINEADM'){
		window.location.href = "offlineEOI?projectid="+$('#projectSelected').val()+"&projectname="+$('#projectSelected option:selected').text();
	}

		//window.location.href = "assignedusers?userId=" + $('#loged_userid').val()+"&projectid="+$('#projectSelected').val()+"&projectname="+$('#projectSelected option:selected').text() ;
 //window.location.href = "assignedusers?userId=" + $('#loged_userid').val()+"&projectid="+$('#projectSelected').val()+"&projectname="+$('#projectSelected option:selected').text() ;

 
}

function validateNext() {
	 
	var radioValue = $("input[name='tokentype']:checked").val();
    if(radioValue){
    	$.get("getNextVisitLast", {
    		"counterNo" : $("#userid").val(),
    		"useremail" : $("#useremail").val(),
    		"type" : radioValue
    	}, function(data) {
 
    		if (data != null) {
    			debugger;
    			//alert(data);
    			window.location.href = "salesinfo?token="+data.nv_token_id+"&countrycode=%2B91&mobileno="+data.mobileno;
    			//?token=1&countrycode=%2B91&mobileno=8898821453
    			//window.location.href = "customer_details";
    			//to do -- window.location.href = "userinfo/+91/data.mobile/tokenID";
    		}
    	}, "json");
    } 
	
	 
}
function getDealStarted(tokenid){
	 
	 $.get("getDealStarted", {
		"tokenid" : tokenid 
	}, function(data) {
		// alert("update");
	}); 
}
 