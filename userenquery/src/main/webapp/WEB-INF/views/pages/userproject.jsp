
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="">
	<h3 class="caption">Daily Wise User Desk Assign</h3>
	 
	<div class="col-md-3 padL0">
		<label>Project Name</label>
		<select class="form-control" id="selected_project">
			<option value="-1">  Select Project</option>
   			<c:forEach items="${Projects}" var="projects">
		        <option value="${projects.project_18_digit__c}">  ${projects.name} </option>
		    </c:forEach>
		</select> 
	</div>
	<div class="col-md-3 padL0">
		<label>User Name</label>
		<select class="form-control" id="selected_user" >
			<%-- <option value="-1">  Select User</option>
   			<c:forEach items="${userlist}" var="userlist">
		        <option value="${userlist.user_id}">  ${userlist.user_name} </option>
		    </c:forEach> --%>
		</select> 
	</div>
	
	<div class="col-md-3 padL0">
		<label>Desk No</label>
		<input class="form-control" type="text" name="desk_no"   id="desk_no" >
	</div>
	<div class="clearfix"></div>
	<div class="col-md-2 padL0">
		<label><br></label>
		<div class="clearfix"></div>
		<button class="btn btn-primary btn-sm" onclick="submitUserMapping();" id="submit">Submit</button>
		<button class="btn btn-primary btn-sm" onclick="clearUserMapping();" id="submit">Clear</button>
	</div>
	<div class="clearfix"></div>
	<div class="commonLoad" id="mainPageLoad" style="display:none;"></div>
	<table class="table table-striped" id="existsdailyuploadedList">
		<thead>
			<tr class="tr tr-success">
				<td>Project Name</td>
				<td>User Name</td>
				<td>Desk No</td>
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
		var desk_no = $('#desk_no').val();
		var optionTextProject = $("#selected_project option:selected").text();
		var projectName = $('#selected_project').val();
		var optionTextUser = $("#selected_user option:selected").text();
		var userName = $('#selected_user').val();
		debugger;
		if(desk_no!=null && optionTextProject !=-1 && optionTextUser!=-1)
			{
			$("#mainPageLoad").show();
				$.ajax({
				    url: "dailyuserupload",
				    dataType: 'json',
				    data: {
				    	"user_id" :userName,
						"project_id" : projectName,
						"desk_no":desk_no
				    },
				    type: "GET",
				    cache: false,
				    success: function (data) {
				    	var s = JSON.stringify(data);
				    	s = JSON.parse(s);
				    	alert(s);
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
	
	function getdailyuploaduserList()
	{
		var urlPP = "getdailyuseruploadlist?projectid="+ $('#selected_project').val();
		/* $('#existsdailyuploadedList tbody tr:nth-child(n+2)').remove(); */
		$("#mainPageLoad").show();
		var i = 0
		$("#existsdailyuploadedList tbody").empty();
		
		
		
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
				if(data1.isactive=="A")
					{
						checked = "checked";
					}
				else
					{
						
					}
				var inputcheck = "<input type='checkbox' class='updateStatusToInput' name='statusAI' style='display:none' id="+inputID+" "+checked+">"
				//alert("Data1"+data1.user_id);
				$("#existsdailyuploadedList tbody").append("<tr><td>"+data1.project_name+"</td><td>"+data1.user_name+"</td><td>"+data1.desk_code+"</td> <td>"+inputcheck+""+data1.isactive+"</td><td ><input class='editRow1' onclick='onEdit(this)' type='button' value='Edit'/>  <input onclick='onSave(this,"+data1.user_id+")' class='saveRow' style='display:none;' type='button' value='Save'/><input onclick='onCancel(this)' class='cancelRow' style='display:none;' type='button' value='Cancel'/></td> </tr>");
				i=i+1;
			});					
		}).done(function() {
			$("#mainPageLoad").hide();
			$('#existsdailyuploadedList').DataTable();
			if (i == 0){
				alert ("No record found for this no.");
				$('#existsdailyuploadedList tbody tr:nth-child(n+2)').remove();
				//$("#exsitUserListTable tbody").empty();				
			}	
			
		});
		
	}
	$(document).ready(function() {
		
		//getdailyuploaduserList();
		$('#selected_project').change(function(){ 
		    var value = $(this).val();
		    getdailyuploaduserList();
		    addProjectWiseUserList();
		    //user List get project wise
		});
		
		});
	
	/* $(document).ready(function() {
		
		getdailyuploaduserList();
		
	}); */
	function addProjectWiseUserList()
	{
		debugger;
		var urlPP = "getUserProjectMapping?projectid="+ $('#selected_project').val();
		var j = 0
		var option="";
		$("#selected_user").empty();
		$.getJSON(urlPP, function (data) {
			option="<option value='-1'>Select User</option>"
			$.each(data, function (index, value) {
				option = option+"<option value="+value.user_id+">"+value.user_name+"</option>";
				j = j+1
				
			});		
		}).done(function() {
			$("#selected_user").append(option);
			//alert(option);
			
			if (j == 0){
				alert ("No record found for this no 233.");
			}	
			/* $("#mainPageLoad").hide(); */
		});
		
	}
	
	function onEdit(e) {
		$(e).closest('td').closest('tr').find('.updateStatusToInput').show();
		$(e).hide();
		$(e).next('.saveRow').show();
		$(e).next('input').next('.cancelRow').show();
	}
	function onSave(e,id) {
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
		updateDailyUserStatus(id,statusAI); 
		
	}
	function onCancel(e) {
		debugger;
		//alert("Call Cancel");
		$(e).closest('td').closest('tr').find('.updateStatusToInput').hide();
		$(e).hide();
		$(e).prev('input').hide();
		$(e).prev('input').prev('.editRow1').show(); 
	}
	function updateDailyUserStatus(id,status)
	{
		debugger;
		$("#mainPageLoad").show();
		  $.get("updatedailyuseruploadlist",
				{
					"userid" :id,
					"status":status
				},
				function(data) {	
					alert("Successfully Updated...");
					//onProjectPageLoad();
					getdailyuploaduserList();
					$("#mainPageLoad").hide();
				});   
	}
	//updatedailyuseruploadlist
	
</script>
 