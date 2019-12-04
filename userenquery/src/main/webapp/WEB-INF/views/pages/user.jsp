
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%! String projectname,projectid ,projectrole,userid;%>
<%HttpSession ses=request.getSession(); 
if(ses!=null){
	  projectname=(String)ses.getAttribute("PRONAME");
	  projectid=(String)ses.getAttribute("PROID");
	  projectrole=(String)ses.getAttribute("ROLE");
	 userid=(String)ses.getAttribute("USERID");
	
	
}else{
	 // Todo Login page
}
%> 
<div class="">
<input type="hidden" id="contextPath" value="${pageContext.request.contextPath}">
<input type="hidden" id="projectname" value="<%= projectname %>">
<input type="hidden" id="projectid" value="<%= projectid%>">
<input type="hidden" id="role" value="<%= projectrole %>">
<input type="hidden" id="userid" value="<%= userid %>">
	<h3 class="caption">User Add/Edit</h3>
	 <!-- Project Drop Down Hide for not requied for mapping -->	
	 <%-- <div class="col-md-3 padL0">
		<label>Select Project</label>
		<select class="form-control" id="selected_project" ><!-- onchange="myFunction(this.value)" -->
		 <option value="-1">  Select Project </option>
   			<c:forEach items="${Projects}" var="projectslist">
   			
		        <option value="${projectslist.project_18_digit__c}">  ${projectslist.name} </option>
		    </c:forEach>
		</select> 
	</div> --%>
	<div class="col-md-3 padL0">
		<label>User Name</label>
		<input class="form-control" type="text" name="userName" id="userName" ><!-- onpaste="restrict(this);" onkeypress="restrict(this);" onkeyup="restrict(this);" -->
	</div>
	<div class="col-md-3 padL0">
		<label>Mobile No</label>
		<input class="form-control" type="text" name="mobileNo" id="mobileNo" ><!-- onpaste="restrict(this);" onkeypress="restrict(this);" onkeyup="restrict(this);" -->
	</div>
	
	<div class="col-md-3 padL0">
		<label>Email ID</label>
		<input class="form-control" type="text" name="user_email"   id="user_email">
	</div>
	<div class="clearfix"></div>
	<div class="col-md-3 padL0">
		<label>Role</label>
		<select class="form-control" id="user_role">
		 	<option value="-1">Select Role </option>
		 	<option value="User">User</option>
		 	<option value="AD">Employee</option>
		 	<option value="AM">Allocation Manager</option>
		 	
		</select>
	</div>
	<div class="col-md-3 padL0">
		<label>From Date</label>
		<input class="form-control" type="date" name="" id="txtFromDate">
	</div>
	<div class="col-md-3 padL0">
		<label>To Date</label>
		<input class="form-control" type="date" name="" id="txtToDate">
	</div>
	<div class="clearfix"></div>
	<div class="col-md-3 padL0">
		<label>Type</label>
		<select class="form-control" id="launch_type">
		 	<option value="-1">Select Type </option>
		 	<option value="Sustenance">Sustenance</option>
		 	<option value="Launch">Launch</option>
		</select>
	</div>
	
	<div class="clearfix"></div>
	<div class="col-md-2 padL1">
		<label><br></label>
		<div class="clearfix"></div>
		<button class="btn btn-primary btn-sm" onclick="submitUser();" id="submit">Submit</button>
		<button class="btn btn-primary btn-sm" onclick="clearUser();" id="submit">Clear</button>
	</div>
	<!-- <input type="hidden" name="categoryID"   id="categoryID"> -->
	<div class="commonLoad" id="mainPageLoad" style="display:none;"></div>
	<div class="clearfix"></div>
		
	
	<table class="table table-striped" id="exsitUserListTable">
		<thead>
			<tr class="tr tr-success">
				<td>Sr.No</td>
				<td>Name</td>
				<td>Email</td>
				<td>Role</td>
				<td>Status</td>
				<td>Edit</td>
			</tr>
		</thead>
		<tbody id="tbody">
			 
		</tbody>
	</table>
</div>

<script type="text/javascript">

$(function(){
    var dtToday = new Date();
    
    var month = dtToday.getMonth() + 1;
    var day = dtToday.getDate();
    var year = dtToday.getFullYear();
    if(month < 10)
        month = '0' + month.toString();
    if(day < 10)
        day = '0' + day.toString();
    
    var maxDate = year + '-' + month + '-' + day;
    //alert(maxDate);
    $('#txtToDate').attr('min', maxDate);
    $('#txtFromDate').attr('min', maxDate);
});

	function submitUser()
	{
		$("#mainPageLoad").show();
		var email = $('#user_email').val();
		var userName = $('#userName').val();
		var optionText = $("#selected_project option:selected").text();
		//var projectName = $('#selected_project').val();
		var roleName = $('#user_role').val();
		var launch_type = $('#launch_type').val();
		/* alert($('#txtFromDate').val());
		alert($('#txtToDate').val()); */
		var fromdate = $('#txtFromDate').val();
		var todate = $('#txtToDate').val();
		var mobileno = $('#mobileNo').val();
		debugger;
		 if(email!=null && userName !=null && roleName !=-1 && launch_type !=-1 && fromdate !=null && todate!=null)
			{
			 
			 //&& projectName!=-1 
				$.ajax({

				    url: "insertUserMaster",
				    dataType: 'json',
				    data: {
				    	"emailid" :email,
						"name" : userName,
						"projectId":"",
						"projectname": "",
						"rolename": roleName,
						"fromdate":fromdate,
						"todate":todate,
						"launchtype":launch_type,
						"mobileno":mobileno
				    },
				    type: "POST",
				    cache: false,
				    success: function (data) {
				    	var s = JSON.stringify(data);
				    	s = JSON.parse(s);
				    	alert(s.responseText)
				    	$("#mainPageLoad").hide();
				    },
				    error: function (data) {
				    	var s = JSON.stringify(data);
				    	s = JSON.parse(s);
				       alert(s.responseText);
				       getuserList();
				       clearUser();
				       $("#mainPageLoad").hide();
				    }
				});	
			}
		else
			{
				alert("Enter mandatory values...");
				$("#mainPageLoad").hide();
			} 
	}
	function clearUser()
	{
		$('#user_email').val('');
		$('#userName').val('');
		$('#selected_project').val('-1');
	    $('#user_role').val('-1');
	    $('#txtFromDate').val('');
		$('#txtToDate').val('-1');
	    $('#launch_type').val('-1');
	}
	
	function getuserList()
	{
		$("#mainPageLoad").show();
		$('#exsitUserListTable tbody tr:nth-child(n+2)').remove();
		$("#exsitUserListTable tbody").empty();
		var urlPP = "getUserMaster?projectid="+$("#projectid").val();
		var i = 0
		$.getJSON(urlPP, function (data) {
			$.each(data, function (index, value) {
				//alert(JSON.stringify(value));
				var s = JSON.stringify(value);
				if(s.indexOf("null") != -1)
					{
						s = s.split('null').join('""');
					}
				var data1 = JSON.parse(s);
				
				var checked= "";
				var inputID="updateStatusToInput"+data1.user_id;
				if(data1.isActive=="A")
					{
						checked = "checked";
					}
				else
					{
						
					}
				var inputcheck = "<input type='checkbox' class='updateStatusToInput' name='statusAI' style='display:none' id="+inputID+" "+checked+">"
				//alert("Data1"+data1.user_id);
				$("#exsitUserListTable tbody").append("<tr><td>"+(i+1)+"</td><td>"+data1.user_name+"</td><td>"+data1.emailid+"</td><td>"+data1.role+"</td> <td>"+inputcheck+""+data1.isActive+"</td><td ><input class='editRow1' onclick='onEdit(this)' type='button' value='Edit'/>  <input onclick='onSave(this,"+data1.user_id+")' class='saveRow' style='display:none;' type='button' value='Save'/><input onclick='onCancel(this)' class='cancelRow' style='display:none;' type='button' value='Cancel'/></td> </tr>");
				i=i+1;
			});					
		}).done(function() {
			$("#mainPageLoad").hide();
			$('#exsitUserListTable').DataTable();
			if (i == 0){
				alert ("No record found for this no.");
				$('#exsitUserListTable tbody tr:nth-child(n+2)').remove();
				//$("#exsitUserListTable tbody").empty();				
			}			
		});
		
	}
	
	$(document).ready(function() {
		
		getuserList();
		
	});
	function onEdit(e) {
		$(e).closest('td').closest('tr').find('.updateStatusToInput').show();
		$(e).hide();
		$(e).next('.saveRow').show();
		$(e).next('input').next('.cancelRow').show();
	}
	function onSave(e,id) {
		$("#mainPageLoad").show();
		//alert(id);
		var slectedID="updateStatusToInput"+id;
		//alert($('#'+slectedID+'')[0].checked);
		//alert(id);
		var statusAI="";
		if($('#'+slectedID+'')[0].checked==true)
			{
				statusAI="A";
			}
		else
			{
				statusAI="I"
			}
		$(e).closest('td').closest('tr').find('.updateStatusToInput').hide();
		$(e).hide();
		$(e).prev('input').show();
		$(e).next('input').hide();
		updateProjectStatus(id,statusAI); 
		
		
	}
	function onCancel(e) {
		debugger;
		//alert("Call Cancel");
		$(e).closest('td').closest('tr').find('.updateStatusToInput').hide();
		$(e).hide();
		$(e).prev('input').hide();
		$(e).prev('input').prev('.editRow1').show(); 
	}
	
	function updateProjectStatus(id,status)
	{
		$("#mainPageLoad").show();
		debugger;
		  $.get("updateUserMaster",
				{
					"userid" :id,
					"status":status
				},
				function(data) {	
					alert("Successfully Updated...");
					//onProjectPageLoad();
					getuserList();
					$("#mainPageLoad").hide();
				});   
	}
</script>
 