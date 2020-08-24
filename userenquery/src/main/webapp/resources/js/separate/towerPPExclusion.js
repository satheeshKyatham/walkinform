
var pageContext = $("#pageContext").val()+"/";	

/*regionList();*/
$(document).ready(function(){
	getTowerPPList();
});
/*function regionList () {
	$('#regionList').empty();	
	var urlRegionList = pageContext+"regionList?project_code=test";
	
	$.getJSON(urlRegionList, function (data) {
		$('#regionList').append('<option value="">Select</option>');
		$.each(data, function (index, value) {
			$('#regionList').append("<option value='"+value.region__c+"'>"+value.region__c+"</option>");
		});					
	}).done(function() {
	});
}*/
projectDataList();
function projectDataList (){
	$('#projectDataList').empty();	
	var urlTower = pageContext+"projectListForSales?projectid="+ $("#gProjectId").val();
	$.getJSON(urlTower, function (data) {
		$('#projectDataList').append('<option value="">Select</option>');
		$.each(data, function (index, value) {
			$("#region_id").val(value.region__c);
			$('#projectDataList').append("<option value='"+value.sfid+"'>"+value.name+  " / " +value.propstrength__project_code__c+ "</option>");
		});					
	}).done(function() {
		
	});
}


function paymentPlanDropdown (){
	
	$('#ppDropdown').empty();
	var urlpayemntPlan = pageContext+"getpaymentPlanWithCIP?projectcode="+$("#projectDataList").val();
	
	$.getJSON(urlpayemntPlan, function (data) {
		$.each(data, function (index, value) {
			$('#ppDropdown').append('<option value='+value.sfid+'>'+value.name+'</option>');
		});					
	}).done(function() {
		towerList();
		$('#towerAgainstBtn').show();
	});
}

function towerList (e, source) {
	$('#towerMst').empty();	
	debugger;
	var projectNameVal = $("#projectDataList").val();
	var urlTower = pageContext+"getTowerMaster?project_code="+projectNameVal;
	$.getJSON(urlTower, function (data) {
		$('#towerMst').append('<option name="0" value="0">Select</option>');
		$.each(data, function (index, value) {
			$('#towerMst').append("<option name='"+value.sfid+"' value='"+value.tower_code__c+"'>"+value.tower_name__c+"</option>");
		});					
	}).done(function() {
		
	});
}


function addTowerPP () {
	debugger
	var tower=$('#towerMst').find('option:selected').attr('name');
	var formData = new FormData();	
	formData.append('project_sfid' ,$('#projectDataList').val());
	formData.append("project_name",$('#projectDataList :selected').text());
	formData.append("payment_plan_sfid",$('#ppDropdown').val());
	formData.append("payment_plan_name",$('#ppDropdown :selected').text());
	formData.append("tower_sfid",tower);
	formData.append("tower_name",$('#towerMst').val());
	var submitURL="saveTowerPPExclusion";
	var object = {};
	formData.forEach(function(value, key){
	    object[key] = value;
	});
	var json = JSON.stringify(object);
	console.log("json",json);
	if(tower==0){
		alert('Please select tower')
	}else{
		$.ajax({
			url : submitURL,
			type : 'POST',
			data : json,
			processData : false, 
			contentType : 'application/json',
			
	        success: function (data) {
	    		
	        	console.log("sus",data);
	        	if(data!=null && data.status =="STATUS_OK"){
	        		swal({
	        			title: "Successfully Submitted",
	        		    text: "",
	        		    timer: 2000,
	        		    type: "success",
	    			});
	        		getTowerPPList();
	        		$('#projectDataList').val("");
	        		$('#projectDataList :selected').text("");
	        		/*$('#regionList').val("");*/
	        		/*$('#regionList :selected').text("");*/
	        		$('#ppDropdown').val("");
	        		$('#ppDropdown :selected').text("");
	        		$('#towerMst :selected').text("");
	        		$('#towerMst').val("");
	        		
	        		
	        	}else{
	        		swal({
	        			title: data.error_msg,
	        		    text: "",
	        		    timer: 2000,
	        		    type: "error",
	    			});
	        	}
			 
	        }
		});
		}
}


function getTowerPPList()
{
	$('#towerPPListId tbody tr.towerPPDataPlotRow').remove();
	
	$.get(pageContext+"getTowerPPExclusionList",function(data){
		
		var html = '';
		var htmlActionBtn = '';
		
		var obj =JSON.parse(data);
		var trans_date = '';
		var trxSuccess = "";
		var trxStatus = "";
		var actionBtn = "";
		
		if(obj!=null){
				for(i = 0; i< obj.length; i++){    
					debugger
					console.log("get",obj[i])
					var projectname=obj[i].project_name==undefined?'':obj[i].project_name;
					var towername=obj[i].tower_name==undefined?'':obj[i].tower_name;
					var pymtplanname=obj[i].payment_plan_name==undefined?'':obj[i].payment_plan_name;
					
					if (obj[i].isactive == "A") {
						htmlActionBtn = "";
						htmlActionBtn +=  '<div style="text-align:center;">'
							+'<button class="btn btn-danger btn-sm rounded-0"  onclick="deleteTowerPP(this)" type="button" data-toggle="tooltip" data-placement="top" title="Delete"><i class="fa fa-trash"></i></button>'
										/*+ '<button class="fa fa-trash cancelPayReqBtn" onclick="cancelPayReq(this)"><i class="fa fa-trash"></i></button>'*/
									+ '</div>';
					} else {
						htmlActionBtn = "";
					}
					
					
					
					html += 	'<tr class="towerPPDataPlotRow '+trxSuccess+'" data-rowid = "'+obj[i].id+'">'	
									+ '<td style="text-align:center;"> ' 
									
											+ '<span class="oldprojectlist">'+projectname+'</span>' 
										+'</td>' 
									+ '<td style="text-align:center;">'
									
											+ '<span class="oldTower">'+towername+'</span>' 
									+ '</td>'
									+ '<td style="text-align:center;">'
									
											+ '<span class="oldPaytPlanId">'+pymtplanname+'</span>' 
									+ '</td>'
									
									+ '<td class="crudRPBtn"> '
										+ htmlActionBtn
									+ '</td>' 
								"</tr>";
				}
				
				html = html.replace(/undefined/g, "");
				
				$("#towerPPListId tbody tr:first-child").after(html);
			
		}
		$('#towerPPListId').DataTable(
				{
					destroy: true,
					language: {
					       // search: "_INPUT_",
					        searchPlaceholder: "Search by name"
					    }
				});
		
	}).done(function(obj){
		if(data!=null){
			if (data.status == "STATUS_NOTOK") {
				alert (data.error_msg);
			}
		}
		
	});
	
}

function deleteTowerPP(e) {
	debugger
	var pageContext = $("#pageContext").val()+"/";
	var towerId = $(e).closest("td").closest("tr.towerPPDataPlotRow").attr("data-rowid"); 
	$.post(pageContext+"deleteTowerPPExclusion",{"Id" : towerId
		},function(data){
		}).done(function(data){
		var obj =JSON.parse(data);
		if(obj!=null){
			 if (obj.status == "STATUS_OK") {
				 swal({
		             title: obj.error_msg,
		              text: "",
		              timer: 3000,
		              type: "success",
		         });
				 getTowerPPList();
			 } else if (obj.status == "STATUS_NOTOK") {
				 swal({
		             title: obj.error_msg,
		              text: "",
		              timer: 3000,
		              type: "warning",
		         });
			 }
		}
	});
		
	}
