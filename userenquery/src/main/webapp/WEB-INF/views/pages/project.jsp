
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="">
	<div class="clearfix"></div>
	<div class="commonLoad" id="mainPageLoad" style="display:none;"></div>	
	<h3 class="caption">Project Master</h3>
	<table class="table table-striped" id="exsitProjectListTable">
		<thead>
			<tr class="tr tr-success">
				<td>ID</td>
				<td>Project Name</td>
				<td>Project ID</td>
				<td>Region</td>
				<td>Status</td>
				<td>Edit</td>
			</tr>
		</thead>
		<tbody id="tbody">
			 
		</tbody>
	</table>
	
</div>

<script type="text/javascript">
	function onProjectPageLoad()
	{
		debugger;
		$("#mainPageLoad").show();
		
		var urlPP = "getprojects?regionid=Mumbai";
		
		var i = 0
		$("#exsitProjectListTable tbody").empty();
		$.getJSON(urlPP, function (data) {
			$.each(data, function (index, value) {
				
				/* var userlist ="";
				if(reasignOption.indexOf("userReAssSelected") != -1){
					   userlist = reasignOption.replace("userReAssSelected","userReAssSelected"+value.nv_token_id);
					}
				debugger;
				if(userlist.indexOf(value.window_assign) != -1){
					   userlist = userlist.replace("value="+value.window_assign,"value="+value.window_assign+" selected");
					} */
					var inputID="updateStatusToInput"+value.id;
					var checked= "";
					if(value.isActive=="A")
						{
							checked = "checked";
						}
					else
						{
							
						}
					var inputcheck = "<input type='checkbox' class='updateStatusToInput' name='statusAI' style='display:none' id="+inputID+" "+checked+">"
					
					/* var inputText =  "<input type='text' class='updateStatusToInput' style='display:none' value="+value.isActive+" id="+inputID+">"; */
				$("#exsitProjectListTable tbody").append("<tr><td>"+value.id+"</td><td>"+value.name+"</td><td>"+value.project_18_digit__c+"</td><td>"+value.regionname+"</td><td>"+inputcheck+""+value.isActive+"</td><td ><input class='editRow1' onclick='onEdit(this)' type='button' value='Edit'/>  <input onclick='onSave(this,"+value.id+")' class='saveRow' style='display:none;' type='button' value='Save'/><input onclick='onCancel(this)' class='cancelRow' style='display:none;' type='button' value='Cancel'/></td></tr>");					
				i = i+1
			});					
		}).done(function() {
			$("#mainPageLoad").hide();
			$('#exsitProjectListTable').DataTable();
			if (i == 0){
				alert ("No record found for this ReAssigned no.");
				$('#exsitProjectListTable tbody tr:nth-child(n+2)').remove();
				//$("#appDetails tbody").empty();				
			}	
			
		});
	}
	
$(document).ready(function() {
		
	onProjectPageLoad();
		
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
	/* $(e).prev('input').hide();
	$(e).prev('input').prev('.editRow1').show(); */
	/* updateProjectStatus(id,statusAI); */
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
	  $.get("updateproject",
			{
				"id" :id,
				"status":status
			},
			function(data) {	
				alert("Successfully Updated...");
				onProjectPageLoad();
				$("#mainPageLoad").hide();
			});   
}
</script>
 