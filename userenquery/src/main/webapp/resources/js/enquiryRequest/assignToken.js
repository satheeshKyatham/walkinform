$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});

var option1 ='';
	var reasignOption ='';
$(document).ready(function() {
	
	 var today = new Date();
	 document.getElementById("txtFromDate").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
	 document.getElementById("txtToDate").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
	
	    var value = $(this).val();
	    if(value!='-1'){
	    	var urlGetUsers = "getUserProjectMapping?projectid="+$('#projectid').val();
	    	
	    	//alert ("PROJECT ID" + $('#projectid').val());
	    	
	    	//$("#mainPageLoad").show();
	       var j = 0
	    	$.getJSON(urlGetUsers, function (data) {
	    		option1 = "<select class='form-control ' onchange='onUserSelect()' id='userSelected'><option>Select Type</option>";
	    		reasignOption = "<select class='form-control assignToInput' style='display:none' id='userReAssSelected' ><option>Select Type</option>";
	    		$.each(data, function (index, value) {
	    			var name='';
	    			if(value.name==undefined)
	    				name='';
	    			else
	    				name=value.name;
	    			option1 = option1+"<option value="+value.user_id+">"+value.user_name+"</option>";
	    			reasignOption = reasignOption+"<option value="+value.user_id+">"+value.user_name+"</option>";
	    			j = j+1
	    		});		
	    		
	    		//alert ('BEFORE Test APPEND');
	    		
	    		option1=option1+"</select>";
	    		reasignOption = reasignOption+"</select>";
	    		
	    		//alert ('Test APPEND');
	    		
	    	}).done(function() {
	    		
	    		//alert ('Test DONE');
	    		
	    		if (j == 0){
	    			//alert ("No record found for this no 233.");
	    		}	
	    		//$("#mainPageLoad").hide();
	    		
	    		loadData();
	    	});
	    }
	});
	
	function onUserSelect()
	{
		//alert($('#userSelected').val())
	}
	 
		function loadData () {
			  $("#mainPageLoad").show();
			  $("#amsearch").prop("disabled", true);
			  getPendingAssignList();
			  getAssignedList();	
		}
		function getPendingAssignList()
		{	
			
			//alert ("Test 132465 654 465 65478 46 5465 46");
			$("#appDetails").dataTable().fnDestroy();
			$("#appDetails tbody").empty();
			$("#assignTouserLoader").show();
			
			var urlPP = "gettokenEntrys?tokenType="+$('#typeSelected').val()+"&ProjectId="+$('#projectid').val()+"&date="+$('#txtFromDate').val()+"&todate="+$('#txtToDate').val();
			var i = 0
			
			$.getJSON(urlPP, function (data) {
				$.each(data, function (index, value) {
					
					var name='';
					  
					if(value.contact_name==undefined)
						name='';
					else
						
						name=value.contact_name;
					 if(name.includes("'"))
					  {
						 name = name.replace("'","");
					  }
					
					var userlist ="";
					if(option1.indexOf("userSelected") != -1){
						   //alert(option + " found");
						   userlist = option1.replace("userSelected","userSelected"+value.nv_token_id);
						   //slectedID="userSelected"+value.nv_token_id;
						}
					var str = value.mobileno;
					var code = str.substr(0, 3);
					code=code.replace("+","%2B");
					var mobileNo = str.substr(3, 13);
					var countryCode = value.countrycode;
					if(countryCode==undefined)
						{
							countryCode="%2B91";
						}
					else
						{
						countryCode=countryCode.replace("+","%2B");
						}
					var finalMobile="";
					if(value.mobileno.includes("+91"))
						{
							var str = value.mobileno;
							finalMobile = str.slice(3);
						}
					else
						{
							finalMobile=value.mobileno
						}
					console.log("Country Code:-"+value.countrycode);
					var url=$("#contextPath").val();
					var rowurl=url+'/salesDetails?tokenid='+value.nv_token_id+'&countrycode='+countryCode+'&mobileno='+finalMobile+'&projectSfid='+$('#projectid').val()+'&projectName='+$('#projectname').val()+'&token='+(value.type+value.queue)+'&isView=Y&salesViewType=N&roleid=0';
 
					var prorityNo="";
					if(value.priority_no__c==undefined)
					{
						prorityNo="";
					}
				else
					prorityNo = value.priority_no__c;
					var contactName =value.contact_name;
				if(value.contact_name==undefined)
					{
						contactName="Sync in progress, refresh in sometime";
					}
					
					/*var val = $("<tr><td><a target='_blank'  href='"+rowurl+"'>"+value.type+value.queue+"</a></td><td><label>"+contactName+"</label></td><td>"+value.mobileno+"</td><td>"+prorityNo+"</td><td>"+value.closing_manager_name__c+"</td><td>"+value.createdBy+"</td><td>"+value.partnertype+"</td><td>"+userlist+"</td><td><input type='button' value='Assign' onclick='asigneddatUpdate("+value.nv_token_id+",\""+value.type+value.queue+"\",\""+name+"\",\""+mobileNo+"\")'/></td></tr>");*/
				var val = $("<tr><td><a target='_blank'  href='"+rowurl+"'>"+value.type+value.queue+"</a></td><td>"+value.enq_name+"</td><td><label>"+contactName+"</label></td><td>"+prorityNo+"</td><td>"+value.closing_manager_name__c+"</td><td>"+value.createdBy+"</td><td>"+value.partnertype+"</td><td>"+userlist+"</td><td><input type='button' value='Assign' onclick='asigneddatUpdate("+value.nv_token_id+",\""+value.type+value.queue+"\",\""+name+"\",\""+mobileNo+"\")'/></td></tr>");
				//	var val = $("<tr><td><label id='tokenID'>"+value.nv_token_id+"</label></td><td><a target='_blank'  href='"+rowurl+"'>"+value.type+value.queue+"</a></td><td>"+name+"</td><td>"+value.mobileno+"</td><td>"+userlist+"</td><td><input type='button' value='Assign' onclick='asigneddatUpdate("+value.nv_token_id+","+value.type+value.queue+","+name+")'/></td></tr>");

				//	var val = $("<tr><td><label id='tokenID'>"+value.nv_token_id+"</label></td><td><a target='_blank'  href='"+rowurl+"'>"+value.type+value.queue+"</a></td><td>"+name+"</td><td>"+value.mobileno+"</td><td>"+userlist+"</td><td><input type='button' value='Assign' onclick='asigneddatUpdate("+value.nv_token_id+","+value.type+value.queue+","+name+")'/></td></tr>");

					$("#appDetails tbody").append(val);
					i = i+1
				});
				$('#appDetails').DataTable(
						{
							destroy: true,
							language: {
						       // search: "_INPUT_",
						        searchPlaceholder: "Search by name"
						    }
							//processing:true,
							//serverSide:true
						});
			}).done(function() {
				
				//$('#appDetails').DataTable();
				$("#assignTouserLoader").hide();
				
				
				if (i == 0){
					//alert ("No record found for this Token Pending Assigned List.");
					//$('#appDetails tbody tr:nth-child(n+2)').remove();
					//$("#appDetails tbody").empty();				
				}	
				
			}).error(function() {  });
		}
		function getAssignedList()
		{
			$("#assinedDetails").dataTable().fnDestroy();
			 $("#assignedLoader").show();
			 $("#assinedDetails tbody").empty();
			var urlPPtoken = "gettokenassignentrys?tokenType="+$('#typeSelected').val()+"&projectid="+$('#projectid').val()+"&date="+$('#txtFromDate').val()+"&todate="+$('#txtToDate').val();
			//$("#mainPageLoad").show();
			var i = 0
			
			$.getJSON(urlPPtoken, function (data) {
				$.each(data, function (index, value) {
					var userlist ="";
					if(reasignOption.indexOf("userReAssSelected") != -1){
						   userlist = reasignOption.replace("userReAssSelected","userReAssSelected"+value.nv_token_id);
						}
					if(userlist.indexOf(value.window_assign) != -1){
						   userlist = userlist.replace("value="+value.window_assign,"value="+value.window_assign+" selected");
						}
					var str = value.mobileno;
					var code = str.substr(0, 3);
					code=code.replace("+","%2B");
					var mobileNo = str.substr(3, 13);
					var countryCode = value.countrycode;
					if(countryCode==undefined)
						{
							countryCode="%2B91";
						}
					else
						{
							countryCode=countryCode.replace("+","%2B");
						}
					var finalMobile="";
					if(value.mobileno.includes("+91"))
						{
							var str = value.mobileno;
							finalMobile = str.slice(3);
						}
					else
						{
							finalMobile=value.mobileno
						}
					console.log("Country Code147:-"+value.countrycode);
					
					//alert(userlist);
					var url=$("#contextPath").val();
					var rowurl=url+'/salesDetails?tokenid='+value.nv_token_id+'&countrycode='+countryCode+'&mobileno='+finalMobile+'&projectSfid='+$('#projectid').val()+'&projectName='+$('#projectname').val()+'&token='+(value.type+value.queue)+'&isView=Y&salesViewType=N&roleid=0';

					var editButtonShow ="";
					var prorityNo ="";
					if(value.isdone=="Session Started")//|| value.isdone=="Started" --Attended
						{
							editButtonShow="<td></td>";
						}
					else
						editButtonShow ="<td><input class='editRow' onclick='onEdit(this,"+value.nv_token_id+")' type='button' value='Edit'/>  <input onclick='onSave(this,"+value.nv_token_id+",\""+value.type+value.queue+"\",\""+value.contact_name+"\",\""+mobileNo+"\")' class='saveRow' style='display:none;' type='button' value='Save'/><input onclick='onCancel(this)' class='cancelRow' style='display:none;' type='button' value='Cancel'/></td>";
					
					if(value.priority_no__c==undefined)
						{
							prorityNo="";
						}
					else
						prorityNo = value.priority_no__c;
						
					
					/*$("#assinedDetails tbody").append("<tr><td><a target='_blank'  href='"+rowurl+"'>"+value.type+value.queue+"</a></td><td><label id='id'>"+value.contact_name+"</label></td><td>"+mobileNo+"</td><td>"+prorityNo+"</td><td>"+value.closing_manager_name__c+"</td><td>"+value.isdone+"</td><td>"+value.partnertype+"</td><td>"+userlist+""+value.user_name+"</td>"+editButtonShow+"</tr>");*/
					$("#assinedDetails tbody").append("<tr><td><a target='_blank'  href='"+rowurl+"'>"+value.type+value.queue+"</a></td><td>"+value.enq_name+"</td><td><label id='id'>"+value.contact_name+"</label></td><td>"+prorityNo+"</td><td>"+value.closing_manager_name__c+"</td><td>"+value.isdone+"</td><td>"+value.partnertype+"</td><td>"+userlist+""+value.user_name+"</td>"+editButtonShow+"</tr>");
					
				//	$("#assinedDetails tbody").append("<tr><td><a target='_blank'  href='"+rowurl+"'>"+value.type+value.queue+"</a></td><td>"+mobileNo+"</td><td>"+userlist+""+value.user_name+"</td><td ><input class='editRow' onclick='onEdit(this)' type='button' value='Edit'/>  <input onclick='onSave(this,"+value.nv_token_id+","+value.type+value.queue+","+value.contact_name+")' class='saveRow' style='display:none;' type='button' value='Save'/><input onclick='onCancel(this)' class='cancelRow' style='display:none;' type='button' value='Cancel'/></td></tr>");					

				//	$("#assinedDetails tbody").append("<tr><td><a target='_blank'  href='"+rowurl+"'>"+value.type+value.queue+"</a></td><td>"+mobileNo+"</td><td>"+userlist+""+value.user_name+"</td><td ><input class='editRow' onclick='onEdit(this)' type='button' value='Edit'/>  <input onclick='onSave(this,"+value.nv_token_id+","+value.type+value.queue+","+value.contact_name+")' class='saveRow' style='display:none;' type='button' value='Save'/><input onclick='onCancel(this)' class='cancelRow' style='display:none;' type='button' value='Cancel'/></td></tr>");					

					i = i+1
				});		
				$('#assinedDetails').DataTable(
						{
							destroy: true,
							language: {
							       // search: "_INPUT_",
							        searchPlaceholder: "Search by name"
							    }
						});
			}).done(function() {
				
				//$('#assinedDetails').DataTable();
				$("#assignedLoader").hide();
				
				if (i == 0){
					//alert ("No record found for this ReAssigned no.");
					$('#assinedDetails tbody tr:nth-child(n+2)').remove();
					//$("#appDetails tbody").empty();				
				}
				$("#amsearch").prop("disabled", false);
			});
			
		}
		
		function asigneddatUpdate( tokenid,tokenNo,cust_name,cust_mobileNo)
		{
			var slectedID="userSelected"+tokenid;
			var tokenType=$('#typeSelected').val();
			//alert($("#"+slectedID+" option:selected").text())
			//debugger;
			//$("#mainPageLoad").show();
			  $.get("updateassignstatus",
					{
						"id" :tokenid,
						"assinedto":$("#"+slectedID+"").val(),
						"tokenType":tokenType,
						"tokenNo":tokenNo,
						"cust_name":cust_name,
						"cust_mobileNo":cust_mobileNo,
						"salesPerson":$("#"+slectedID+" option:selected").text()
					},
					function(data) {	
						alert("Successfully Assigned...");
						loadData();
						//$("#mainPageLoad").hide();
					});   
		}
		
		function reAsignedTokenUpdate(tokenid,assignedid,tokenNo,cust_name,cust_mobileNo)
		{
			var slectedID="userReAssSelected"+tokenid;
			
			//$("#mainPageLoad").show();
			var tokenType=$('#typeSelected').val();
			debugger;
			  $.get("updateassignstatus",
					{
						"id" :tokenid,
						"assinedto":assignedid,
						"tokenType":tokenType,
						"tokenNo":tokenNo,
						"cust_name":cust_name,
						"cust_mobileNo":cust_mobileNo,
						"salesPerson":$("#"+slectedID+" option:selected").text()
					},
					function(data) {	
						alert("Successfully Assigned...");
						loadData();
						//$("#mainPageLoad").hide();
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
		
		function onEdit(e,tokenid) {
			//
			
			$.get("getTokenDetails",
					{
						"tokenid" :tokenid
					},
					function(data) {	
						var dd = JSON.parse(data);
						if(dd.isdone=="S")//dd.isdone=="Y" || 
							{
							swal({
								title: "Assigned user already started the session on this token",
							    text: "",
							    timer: 3000,
							    type: "error",
							});
							}
						else
							{
								$(e).closest('td').closest('tr').find('.assignToInput').show();
								$(e).hide();
								$(e).next('.saveRow').show();
								$(e).next('input').next('.cancelRow').show();
							}
					});
			
			
		}
		function onSave(e,tokenid,tokenNo,cust_name,mobileno) {
			$("#assignedLoader").show();
			var slectedID="userReAssSelected"+tokenid;
			//alert($('#'+slectedID+'').val())
			$(e).closest('td').closest('tr').find('.assignToInput').hide();
			$(e).hide();
			$(e).prev('input').show();
			$(e).next('input').hide();
			reAsignedTokenUpdate(tokenid,$('#'+slectedID+'').val(),tokenNo,cust_name,mobileno);
			$("#assignedLoader").hide();
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
		
		
		
		/*$('#example').dataTable({
		    columnDefs: [
		       {type: 'non-empty-string', targets: 0} // define 'name' column as non-empty-string type
		    ]
		});*/