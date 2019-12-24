<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	 response.addHeader("Expires","0");
	 response.addHeader("Pragma","no-cache");
	 response.setHeader("Cache-Control","no-cache,no-store, must-revalidate, pre-check=0, post-check=0, max-age=0, s-maxage=0");
	 response.addHeader("X-Frame-Options", "DENY");
	 %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Sales Desk</title>
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css' />" type="text/css" />
    
	<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">

</head>
<%! String projectid;%>
<%HttpSession ses=request.getSession(); 

	projectid=request.getParameter("projectid");

%>
<input type="hidden" id="projectid" value="<%= projectid%>">
<script type="text/javascript">
        window.history.forward();
        function noBack()
        {
            window.history.forward();
        }
</script>
<body onLoad="noBack();" onpageshow="if (event.persisted) noBack();" onUnload="">  
  <input type="hidden" id="contextPath" value="${pageContext.request.contextPath}">
  <!-- <input type="text" > -->
  <div class="col-md-3 padL0">
		<label>Project Name</label>
		<select class="form-control" onchange="loadData()" id="selected_project">
			<option value="-1">Select Project</option>
   			<c:forEach items="${Projects}" var="projects">
		        <option value="${projects.project_18_digit__c}">  ${projects.name} </option>
		    </c:forEach>
		</select> 
	</div>
  <div class="col-md-3 padL0" >
  	<label>Type</label>
  		<select class="form-control" onchange="loadData()" id="typeSelected">
 		  <option>Select Type</option>
		  <c:forEach items = "${tokens}"   var ="tokens" varStatus="loop">
	         <option value="${tokens.type}">${tokens.type}</option>
	      </c:forEach>
       </select>
       
      
	  <div class="clearfix"></div>
	</div>  
	  <!-- vw_usermaster -->
      <!-- <button  onclick ="validateNext();">  
		<span>Next</span>
	  </button> -->
	  	<div class="col-md-12" style="    margin-top: 5%;">
		<!-- <div style="float:right;     margin-top: 10px;">
			<input type="number" placeholder="Mobile no." id="mobileVal"/> 
			<button class="btn btn-primary" onclick="loadData()">Search</button>
		</div> -->
		
		
		<div class="clearfix"></div>
		<h4 style="color: #40a1cd">Assign Token to user.</h4>
		<table class="table table-bordered"  id="appDetails">
		 <thead>
			<!-- <tbody> -->
				<tr>
					<th>ID</th>
					<th>Token No.</th>
					<th>Name</th>
					<th>Mobile</th>									
					<th>Assign To</th>
					<th>Submit</th>
				</tr>
			</thead>
			<!-- </tbody> -->
		</table>
		<div class="clearfix"></div>
	</div>
     <div class="clearfix"></div>
     <div class="col-md-12" style="    margin-top: 5%;">
     <h4 style="color: #40a1cd">Assigned Token </h4>
		<div class="commonLoad" id="mainPageLoad" style="display:none;"></div>
		<div class="clearfix"></div>
		<table class="table table-bordered" id="assinedDetails">
			<thead>
				<tr>
					<th>ID</th>
					<th>Token No.</th>
					<th>Name</th>
					<th>Mobile</th>	
					<th>Assigned To</th>
					<th>Edit</th>						
					
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
		
		
		<!-- Added By A -->
		
		<!-- END ADDED BY A -->
		
		
		<div class="clearfix"></div>
	</div> 
	 
	
<script src="<c:url value='/resources/js/jquery-1.12.4.min.js'/>"></script>
  
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
<script src="<c:url value='/resources/js/salesdesk.js?v=18'/>"></script>
	<script type="text/javascript">
	var option ='';
	var reasignOption ='';
$(document).ready(function() {
	$('#selected_project').change(function(){ 
	    var value = $(this).val();
	    if(value!='-1'){
	    	var urlGetUsers = "getUserProjectMapping?projectid="+$('#selected_project').val();	
	    	$("#mainPageLoad").show();
	       var j = 0
	    	$.getJSON(urlGetUsers, function (data) {
	    		option = "<select class='form-control ' onchange='onUserSelect()' id='userSelected'><option>Select Type</option>";
	    		reasignOption = "<select class='form-control assignToInput' style='display:none' id='userReAssSelected' ><option>Select Type</option>";
	    		$.each(data, function (index, value) {
	    			var name='';
	    			if(value.name==undefined)
	    				name='';
	    			else
	    				name=value.name;
	    			option = option+"<option value="+value.user_id+">"+value.user_name+"</option>";
	    			reasignOption = reasignOption+"<option value="+value.user_id+">"+value.user_name+"</option>";
	    			j = j+1
	    		});		
	    		option=option+"</select>";
	    		reasignOption = reasignOption+"</select>";
	    	}).done(function() {
	    		if (j == 0){
	    			alert ("No record found for this no 233.");
	    		}	
	    		$("#mainPageLoad").hide();
	    	});
	    }
	   	 
	});
	
		
	});
	
	function onUserSelect()
	{
		//alert($('#userSelected').val())
	}
	 
		function loadData () {
			$("#mainPageLoad").show();
			debugger;
				$('#appDetails tbody tr:nth-child(n+2)').remove();
				var urlPP = "gettokenEntrys?tokenType="+$('#typeSelected').val()+"&ProjectId="+$('#selected_project').val();
				var i = 0
				$("#appDetails tbody").empty();
				$.getJSON(urlPP, function (data) {
					$.each(data, function (index, value) {
						var name='';
						if(value.name==undefined)
							name='';
						else
							name=value.contact_name;
						debugger;
						//alert(value.contact_name);
						var userlist ="";
						if(option.indexOf("userSelected") != -1){
							   //alert(option + " found");
							   userlist = option.replace("userSelected","userSelected"+value.nv_token_id);
							   //slectedID="userSelected"+value.nv_token_id;
							}
						var str = value.mobileno;
						var code = str.substr(0, 3);
						code=code.replace("+","%2B");
						var mobileNo = str.substr(3, 13);
						
						var url=$("#contextPath").val();
						var rowurl=url+'/salesDetails?tokenid='+value.nv_token_id+'&countrycode='+code+'&mobileno='+mobileNo+'&projectSfid='+$('#selected_project').val()+'&projectName='+$('#selected_project option:selected').text()+'&token='+(value.type+value.queue)+'&isView=Y&salesViewType=N';
						
						
						$("#appDetails tbody").append('<tr><td><label id="tokenID">'+value.nv_token_id+
								'</label></td><td><a target="_blank"  href='+rowurl+'>'+value.type+value.queue+
								'</a></td><td>'+name+'</td><td>'+value.mobileno+'</td><td>'+userlist
								+'</td><td><input type="button" value="Assign" onclick="asigneddatUpdate('
										+value.nv_token_id+',"'+value.type+value.queue+'",0)/></td></tr>');
						i = i+1
					});					
				}).done(function() {
					$("#mainPageLoad").hide();
					$('#appDetails').DataTable();
					if (i == 0){
						alert ("No record found for this Token Pending Assigned List.");
						//$('#appDetails tbody tr:nth-child(n+2)').remove();
						//$("#appDetails tbody").empty();				
					}	
					
				});
				
				
				$('#assinedDetails tbody tr:nth-child(n+2)').remove();
				var urlPP = "gettokenassignentrys?tokenType="+$('#typeSelected').val();
				$("#mainPageLoad").show();
				var i = 0
				$("#assinedDetails tbody").empty();
				$.getJSON(urlPP, function (data) {
					$.each(data, function (index, value) {
						debugger;
						var userlist ="";
						if(reasignOption.indexOf("userReAssSelected") != -1){
							   userlist = reasignOption.replace("userReAssSelected","userReAssSelected"+value.nv_token_id);
							}
						debugger;
						if(userlist.indexOf(value.window_assign) != -1){
							   userlist = userlist.replace("value="+value.window_assign,"value="+value.window_assign+" selected");
							}
						var str = value.mobileno;
						var code = str.substr(0, 3);
						code=code.replace("+","%2B");
						var mobileNo = str.substr(3, 13);
						
						var url=$("#contextPath").val();
						var rowurl=url+'/salesDetails?tokenid='+value.nv_token_id+'&countrycode='+code+'&mobileno='+mobileNo+'&projectSfid='+$('#selected_project').val()+'&projectName='+$('#selected_project option:selected').text()+'&token='+(value.type+value.queue)+'&isView=Y&salesViewType=N';
						$("#assinedDetails tbody").append("<tr><td><label id='tokenID'>"+value.nv_token_id+"</label></td><td><a target='_blank'  href='"+rowurl+"'>"+value.type+value.queue+"</a></td><td>"+mobileNo+"</td><td><label id='id'>"+value.contact_name+"</label></td><td>"+userlist+""+value.window_assign+"</td><td ><input class='editRow' onclick='onEdit(this)' type='button' value='Edit'/>  <input onclick='onSave(this,"+value.nv_token_id+","+value.type+value.queue+",0)' class='saveRow' style='display:none;' type='button' value='Save'/><input onclick='onCancel(this)' class='cancelRow' style='display:none;' type='button' value='Cancel'/></td></tr>");					
						i = i+1
					});					
				}).done(function() {
					
					$("#mainPageLoad").hide();
					$('#assinedDetails').DataTable();
					if (i == 0){
						alert ("No record found for this ReAssigned no.");
						$('#assinedDetails tbody tr:nth-child(n+2)').remove();
						//$("#appDetails tbody").empty();				
					}	
					
				});
				
		}
		function asigneddatUpdate( tokenid,tokenNo,cust_name)
		{
			var slectedID="userSelected"+tokenid;
			//alert($('#'+slectedID+'').val())
			//debugger;
			$("#mainPageLoad").show();
			var tokenType=$('#typeSelected').val();
			  $.get("updateassignstatus",
					{
						"id" :tokenid,
						"assinedto":$("#"+slectedID+"").val(),
						"tokenType":tokenType,
						"tokenNo":tokenNo,
						"cust_name":cust_name
						/* "assinedto":$("#"+tokenid+"").val() */
					},
					function(data) {	
						alert("Successfully Assigned...");
						loadData();
						$("#mainPageLoad").hide();
					});   
		}
		
		function reAsignedTokenUpdate(tokenid,assignedid,tokenNo,cust_name)
		{
			$("#mainPageLoad").show();
			var tokenType=$('#typeSelected').val();
			debugger;
			  $.get("updateassignstatus",
					{
						"id" :tokenid,
						"assinedto":assignedid,
						"tokenType":tokenType,
						"tokenNo":tokenNo,
						"cust_name":cust_name
						//mobileno,tokenType
					},
					function(data) {	
						alert("Successfully Assigned...");
						loadData();
						$("#mainPageLoad").hide();
					});   
		}
		
		/* ADDED BY A */
		$('.editRow').click(function () {
			//alert ("Test 1234");
			//$(this).closest('td').closest('tr').find('.assignToInput').show();
		});
		
		$('.cancelRow').click(function () {
			//alert ("Test 12345");
			//$(this).closest('td').closest('tr').find('.assignToInput').show();
		});
		
		function onEdit(e) {
			$(e).closest('td').closest('tr').find('.assignToInput').show();
			$(e).hide();
			$(e).next('.saveRow').show();
			$(e).next('input').next('.cancelRow').show();
		}
		function onSave(e,tokenid,tokenNo,cust_name) {
			$("#mainPageLoad").show();
			var slectedID="userReAssSelected"+tokenid;
			//alert($('#'+slectedID+'').val())
			$(e).closest('td').closest('tr').find('.assignToInput').hide();
			$(e).hide();
			$(e).prev('input').show();
			$(e).next('input').hide();
			reAsignedTokenUpdate(tokenid,$('#'+slectedID+'').val(),tokenNo,cust_name);
			$("#mainPageLoad").hide();
			//alert("Call update Query"+$(e).closest('td').closest('tr').find('.userReAssSelected45').text());
		}
		function onCancel(e) {
			debugger;
			//alert("Call Cancel");
			$(e).closest('td').closest('tr').find('.assignToInput').hide();
			$(e).hide();
			$(e).prev('input').hide();
			$(e).prev('input').prev('.editRow').show();
		}
		
		
	</script>
	
 
  </body>
	
</html>  