
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="">
	<h3 class="caption">Add Desk</h3>
	 
	<div class="col-md-3 padL0">
		<label>Select Project</label>
		<select class="form-control" id="main_category" onchange="myFunction(this.value)">
   			<c:forEach items="${lstCategory1}" var="lstCategory1">
		        <option value="${lstCategory1.main_category_id}">  ${lstCategory1.categoryName} </option>
		    </c:forEach>
		</select> 
	</div>
	<div class="col-md-3 padL0">
		<label>Desk Name</label>
		<input class="form-control" type="text" name="categoryName"   id="categoryName" onpaste="restrict(this);" onkeypress="restrict(this);" onkeyup="restrict(this);">
	</div>
	
	<div class="col-md-1 padL0">
		<label>Status</label>
		<input type="checkbox" name="statusAI" id="statusAI">
	</div>
	<div class="col-md-2 padL0">
		<label><br></label>
		<div class="clearfix"></div>
		<button class="btn btn-primary btn-sm" onclick="submitCategory();" id="submit">Submit</button>
		<button class="btn btn-primary btn-sm" onclick="clearCategory();" id="submit">clear</button>
	</div>
	<input type="hidden" name="categoryID"   id="categoryID">
	
	<div class="clearfix"></div>
	
	<p class="bg-info">
		<b>Note: </b>Select the Category name from the above drop down, for refresh the table
	</p>
	
	<table class="table table-striped">
		<thead>
			<tr class="tr tr-success">
				<td>No</td>
				<td>Category Name</td>
				<td>Status AI</td>
			</tr>
		</thead>
		<tbody id="tbody">
			 
		</tbody>
	</table>
</div>

<script type="text/javascript">
function myFunction(val) {
	clearCategory();
	$.post("getCategory2",{"Id":val },
			function(data){
	 	 
			$("#tbody").html('');
		 
		if (data.length == 0) {
			$('#tbody') .append( '<tr><td colspan="11" align="center"> No records found</td></tr>');
		} else {
					 for (var i = 0; i < data.length; i++) {
						$('#tbody').append(
							' <tr><td>'+ (i+1)+'</td><td>'+data[i].sub_categoryName+
							'</td><td>'+data[i].isActive+'</td><td><a class="btn btn-primary btn-sm"	onclick="editCategory(\''+ data[i].sub_category_id +'\',\''
									+ data[i].sub_categoryName+'\',\''+ data[i].isActive  +'\')"  >Edit</a> </td></tr>') ;
					 }
		}
					 
		  		},"json");
}
 
function submitCategory(){
var	selectedCategory=$('#main_category').find(":selected").val();
var status='I';
var subMenuId='0';
if($('#statusAI')[0].checked){
	status="A";
}
if($("#categoryID").val().trim().length!=0)
	subMenuId=$("#categoryID").val()
if(selectedCategory!="Select")
	{
	$.post("saveUpdateCategory2",{"Sub_category_id":subMenuId ,"Main_category_id":selectedCategory,"isActive":status ,"Sub_categoryName": $("#categoryName").val() },
			function(data){
	 	 
			$("#tbody").html('');
		 
		if (data.length == 0) {
			$('#tbody') .append( '<tr><td colspan="11" align="center"> No records found</td></tr>');
		} else {
					 for (var i = 0; i < data.length; i++) {
						 
						 
						$('#tbody').append(
							' <tr><td>'+ (i+1)+'</td><td>'+data[i].sub_categoryName+
							'</td><td>'+data[i].isActive+'</td><td><a class="btn btn-primary btn-sm" onclick="editCategory(\''+ data[i].sub_category_id +'\',\''
									+ data[i].sub_categoryName+'\',\''+ data[i].isActive  +'\')"  >Edit</a> </td></tr>') ;
					 }
		}
					 
		  		},"json");
	}
	
	
}
function editCategory(id,name,status){
	  
	  $("#categoryID").val(id);
	  $("#categoryName").val(name);
	  debugger;
	  if(status==='A')
		  $('#statusAI')[0].checked = true;
	  else
		  $('#statusAI')[0].checked = false;
	 }
function clearCategory(){
	 $("#categoryID").val('');
	  $("#categoryName").val('');
	  $('#statusAI')[0].checked = false;
	//  $("#main_category").val($("#main_category option:first").val());
	
}

var restrict = function(tb) {
	  tb.value = tb.value.replace(/[^a-zA-Z0-9 ]/g, '');
	};
</script>
 