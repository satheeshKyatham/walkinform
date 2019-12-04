
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="">
	<h3 class="caption">User Assign Project</h3>
	 
	<div class="col-md-3 padL0">
		<label>Project Name</label>
		<select class="form-control" id="selected_project">
			<option value="-1">Select Project</option>
   			<c:forEach items="${Projects}" var="projects">
		        <option value="${projects.project_18_digit__c}">  ${projects.name} </option>
		    </c:forEach>
		</select> 
	</div>
	<div class="col-md-3 padL0">
		<label>User Name</label>
		<select class="form-control" id="selected_user" >
			<option value="-1">Select User</option>
   			<c:forEach items="${userlist}" var="userlist">
		        <option value="${userlist.user_id}">  ${userlist.user_name} </option>
		    </c:forEach>
		</select> 
	</div>
	<div class="commonLoad" id="mainPageLoad" style="display:none;"></div>
<div class="clearfix"></div>
	<div class="col-md-2 padL0">
		<label><br></label>
		<div class="clearfix"></div>
		<button class="btn btn-primary btn-sm" onclick="submitUserMapping();" id="submit">Submit</button>
		<button class="btn btn-primary btn-sm" onclick="clearUserMapping();" id="submit">Clear</button>
	</div>
	<div class="clearfix"></div>
	<table class="table table-striped" id="exsitUserListTable">
		<thead>
			<tr class="tr tr-success">
				<td>ID</td>
				<td>User Name</td>
				<td>Project Name</td>
				<!-- <td>Project ID</td> -->
				<td>Status</td>
				<td>Edit</td>
			</tr>
		</thead>
		<tbody id="tbody">
			 
		</tbody>
	</table>
</div>

<script type="text/javascript">

	
	function submitUserMapping()
	{
		$("#mainPageLoad").show();
		//alert($('#selected_project').val());
		var optionTextProject = $("#selected_project option:selected").text();
		var projectName = $('#selected_project').val();
		var optionTextUser = $("#selected_user option:selected").text();
		var userName = $('#selected_user').val();
		debugger;
		if(projectName !=-1 && userName!=-1)
			{
				$.ajax({
				    url: "projectassignUpdate",
				    dataType: 'json',
				    data: {
				    	"user_id" :userName,
						"project_id" : projectName,
						"project_name":optionTextProject
				    },
				    type: "GET",
				    cache: false,
				    success: function (data) {
				    	var s = JSON.stringify(data);
				    	s = JSON.parse(s);
				    	//alert(s);
				    	 clearUserMapping();
				    	 $("#mainPageLoad").hide();
				    	//alert(s.responseText)
				    },
				    error: function (data) {
				    	var s = JSON.stringify(data);
				    	s = JSON.parse(s);
				    	//alert("Error:-"+s)
				       alert(s);
				      // getuserList();
				       $("#mainPageLoad").hide();
				    }
				});	
			}
		else
			{
				alert("Enter mandatory values...");
			} 
	}
	function clearUserMapping()
	{
		$('#desk_no').val('');
		$('#selected_project').val('-1');
		$('#selected_user').val('-1');
	}
	
	function onUserMappingPageLoad()
	{
		var urlPP = "usermappinglist?projectid="+ $('#selected_project').val();
		$("#mainPageLoad").show();
		var i = 0
		$("#exsitUserListTable tbody").empty();
		$.getJSON(urlPP, function (data) {
			$.each(data, function (index, value) {
				debugger;
				/* var userlist ="";
				if(reasignOption.indexOf("userReAssSelected") != -1){
					   userlist = reasignOption.replace("userReAssSelected","userReAssSelected"+value.nv_token_id);
					}
				debugger;
				if(userlist.indexOf(value.window_assign) != -1){
					   userlist = userlist.replace("value="+value.window_assign,"value="+value.window_assign+" selected");
					} */
					var inputID="updateStatusToInput"+value.gpl_user_project_mapping_id;
					//alert(inputID);
					var checked= "";
					if(value.isactive=="A")
						{
							checked = "checked";
						}
					else
						{
							
						}
					var inputcheck = "<input type='checkbox' class='updateStatusToInput' name='statusAI' style='display:none' id="+inputID+" "+checked+">"
					
					/* var inputText =  "<input type='text' class='updateStatusToInput' style='display:none' value="+value.isActive+" id="+inputID+">"; */
				$("#exsitUserListTable tbody").append("<tr><td>"+value.gpl_user_project_mapping_id+"</td><td>"+value.user_name+"</td><td>"+value.project_name+"</td><td>"+inputcheck+""+value.isactive+"</td><td ><input class='editRow1' onclick='onEdit(this)' type='button' value='Edit'/>  <input onclick='onSave(this,"+value.gpl_user_project_mapping_id+")' class='saveRow' style='display:none;' type='button' value='Save'/><input onclick='onCancel(this)' class='cancelRow' style='display:none;' type='button' value='Cancel'/></td></tr>");					
				i = i+1
			});					
		}).done(function() {
			$("#mainPageLoad").hide();
			$('#exsitUserListTable').DataTable();
			if (i == 0){
				alert ("No record found for this ReAssigned no.");
				$('#exsitUserListTable tbody tr:nth-child(n+2)').remove();
				//$("#appDetails tbody").empty();	
				
			}			
		});
	}
	
$(document).ready(function() {
		
	
	$('#selected_project').change(function(){ 
	    var value = $(this).val();
	    if(value!='-1')
	   	 onUserMappingPageLoad();
	});
	
	});
	
	
function onEdit(e) {
	$(e).closest('td').closest('tr').find('.updateStatusToInput').show();
	$(e).hide();
	$(e).next('.saveRow').show();
	$(e).next('input').next('.cancelRow').show();
}
function onSave(e,id) {
	//updateproject
	//$('#statusAI')[0].checked = true
	
	//updateStatusToInput
	var slectedID="updateStatusToInput"+id;
	//alert($('#'+slectedID+'')[0].checked);
	//alert(id);
	//alert(slectedID);
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

function updateProjectStatus(id,status)//projectMappingUpdateStatus
{
	$("#mainPageLoad").show();
	debugger;
	  $.get("projectMappingUpdateStatus",
			{
				"id" :id,
				"status":status
			},
			function(data) {	
				alert("Successfully Updated...");
				onUserMappingPageLoad();
				$("#mainPageLoad").hide();
			});   
}
</script>
 