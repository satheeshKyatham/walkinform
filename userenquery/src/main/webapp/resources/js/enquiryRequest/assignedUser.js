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
$(document).ready(function() {
	 var today = new Date();
	 document.getElementById("txtFromDate").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
	
	loadData();
});
function loadData () {
			  $("#mainPageLoad").show();
			  $("#amsearch").prop("disabled", true);
			  
			  $('#amsearch span').html('<i class="fa fa-spinner fa-spin" style="color:#fff !important"></i>');	
			  
			  
			  getPendingAssignList();
		}
function getPendingAssignList()
		{
	
			$("#dtOrderExample tbody").empty();
			 $("#mainPageLoad").show();
			var urlPP = "getAssignedUserToken?projectid="+$('#projectid').val()+"&user_id="+$('#userid').val()+"&fromdate="+$('#txtFromDate').val();
			var i = 0
			//alert("Alert");
			$.getJSON(urlPP, function (data) {
				$.each(data, function (index, value) {
					debugger;
					//alert("Alert"+value.offerName);
					var val = "<tr><td><label>"+value.nv_token_id+"</label></td><td>"+value.token_no+"</td><td>"+value.name+"</td><td>"+value.mobileno+"</td><td>"+value.priority_no__c+"</td><td>"+value.closing_manager_name__c+"</td><td>"+value.starteddate+"</td><td>"+value.offerName+"</td><td>"+value.isdone+"</td>";
					
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
			}).done(function() {
				$("#amsearch").prop("disabled", false);
				
				$('#amsearch span').html('');
				
				$("#mainPageLoad").hide();
				/*$('#dtOrderExample').DataTable();*/
				if (i == 0){
				}	
				 
			}).error(function() { //alert("error");
			 $("#amsearch").prop("disabled", false);
			 $('#amsearch span').html('');
			});
			
		}