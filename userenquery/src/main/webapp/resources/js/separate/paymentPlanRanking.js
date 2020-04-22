
var pageContext = $("#pageContext").val()+"/";	

regionList();
var totalPP='';
$(document).ready(function(){
	
});
function regionList () {
	$('#regionList').empty();	
	var urlRegionList = pageContext+"regionList?project_code=test";
	
	$.getJSON(urlRegionList, function (data) {
		$('#regionList').append('<option value="">Select</option>');
		$.each(data, function (index, value) {
			$('#regionList').append("<option value='"+value.region__c+"'>"+value.region__c+"</option>");
		});					
	}).done(function() {
	});
}

function projectDataList (){
	$('#projectDataList').empty();	
	var urlTower = pageContext+"projectDataList?region="+$('#regionList').val();
	$.getJSON(urlTower, function (data) {
		$('#projectDataList').append('<option value="">Select</option>');
		$.each(data, function (index, value) {
			$('#projectDataList').append("<option value='"+value.sfid+"'>"+value.name+  " / " +value.propstrength__project_code__c+ "</option>");
		});					
	}).done(function() {
		
	});
}


function paymentPlanDropdown (){
	
	$('#ppDropdown').empty();
	$('#paymentRankListId tbody tr.paymentRankDataPlotRow').remove();
	/*$('#ppDropdown tbody tr.towerPPDataPlotRow').remove();*/
	var html = '';
	var rankNumber='';
	var htmlActionBtn = '';
	var urlpayemntPlan = pageContext+"getpaymentPlanWithCIP?projectcode="+$("#projectDataList").val();
	
	$.getJSON(urlpayemntPlan, function (data) {
		var no="";
		var arr=[]
		$.each(data, function (index, value) {
			$('#ppDropdown').append('<option value='+value.sfid+'>'+value.name+'</option>');
		});	
		/*console.log("arr",paymentRank)*/
		totalPP=data.length
		debugger
			console.log("ui")
			for(i = 0; i< data.length; i++){    
				debugger
				console.log("get",data)
				var planName=data[i].name==undefined?'':data[i].name;
				var sfid=data[i].sfid==undefined?'':data[i].sfid;
				for(j=0;j<totalPP;j++){
					var d=j+1;
					/*rankNumber += '<option name='+d+' value='+d+'>'+d+'</option>';*/ 
					rankNumber+='<option value='+d+'  class="rankClass">'+d+'</option>';
				}
				html += 	'<tr class="paymentRankDataPlotRow data-rowid = "'+data[i].sfid+'">'
				
								+ '<td style="text-align:center;">'
								
								+ '<input style="display:none;" type="hidden" class="oldPaymentPlan" id="oldPaymentPlanId"  value="'+sfid+'"></input>'
								+ '<input style="display:none;" type="hidden" class="oldPaymentPlan2" id="oldPaymentPlanId2"  value="'+planName+'"></input>'
								+ '<span class="existPaymentPlan">'+planName+'</span>' 
								+ '</td>'
								+ '<td style="text-align:center;">'
								+ '<select class="form-control rankNo'+i+'" id="rank"> '
								+ rankNumber	 
								+ '</select></td>'
								+ '<td class="crudRPBtn"> '
								+ htmlActionBtn
								+ '</td>' 
							"</tr>";
				rankNumber="";
			}
			
			html = html.replace(/undefined/g, "");
			
			$("#paymentRankListId tbody tr:first-child").after(html);	
		/*});	*/				
	}).done(function() {
		/*$('#towerAgainstBtn').show();*/
	});
}

function bulkSubmitPaymentRanking () {
	var checkDupli=validateRank();
	if(checkDupli){
		alert("Payment plan has duplicate rank");
	}else{
		var arrayData = [];
	    $("#paymentRankListId .paymentRankDataPlotRow").each(function () {
	    		  var rankData = {};
	    			
	              /*rankData.payment_plan_sfid = $('.existPaymentPlan').val();*/
	              rankData.payment_plan_sfid= $(this).find('.oldPaymentPlan').val();
	              rankData.payment_plan_name = $('#oldPaymentPlanId2').val();
	              rankData.sequence = $(this).find('#rank').val();
	              rankData.isactive = 'A';
	              rankData.createdby = '999';
	              rankData.project_name = $('#projectDataList option:selected').text();
	              rankData.project_sfid = $('#projectDataList').val();
	              arrayData.push(rankData);    
	    });
	    
	    $.post(pageContext+"bulkInsertPaymentRanking",{"rankingJson" : JSON.stringify(arrayData)},function(data){                         
	    }).done(function(data){
	    	debugger
	    	console.log("data",data)
	    	if(data!="" && data=="STATUS_NOTOK"){
	    		swal({
					title: "Already Submitted",
				    text: "",
				    //timer: 2000,
				    type: "error",
				});
	    	}else{
	    		swal({
					title: "Successfully Submitted",
				    text: "",
				    //timer: 2000,
				    type: "success",
				});
	    	}
	    	
	    });	
	}
	
}

	function validateRank(e){
		debugger
	     var arr = new Array();
	     for(i=0;i<totalPP;i++){
	    	 arr.push($('.rankNo'+i).val());
	     }
	  	console.log("array",arr);
	      // create a Set with array elements
	      const s = new Set(arr);
	      // compare the size of array and Set
	      if(arr.length !== s.size){
	         return true;
	      }
	      
	      return false
	   
	
}
function addPaymentPlanRank () {
	debugger
	var tower=$('#towerMst').find('option:selected').attr('name');
	var formData = new FormData();	
	formData.append('project_sfid' ,$('#projectDataList').val());
	formData.append("project_name",$('#projectDataList :selected').text());
	formData.append("payment_plan_sfid",$('#ppDropdown').val());
	formData.append("payment_plan_name",$('#ppDropdown :selected').text());
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
	        		$('#regionList').val("");
	        		$('#regionList :selected').text("");
	        		$('#ppDropdown').val("");
	        		$('#ppDropdown :selected').text("");
	        		$('#towerMst :selected').text("");
	        		$('#towerMst').val("");
	        		
	        		
	        	}else{
	        		swal({
	        			title: "Something went wrong...!",
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
	/*$("#paymentRankListId").dataTable().fnDestroy();*/
	$('#paymentRankListId tbody tr.paymentRankDataPlotRow').remove();
	
	$.get(pageContext+"getTowerPPExclusionList",function(data){
		
		var html = '';
		var htmlActionBtn = '';
		
		var obj =JSON.parse(data);
		var trans_date = '';
		var trxSuccess = "";
		var trxStatus = "";
		var actionBtn = "";
		
		/*if(obj!=null){
				for(i = 0; i< obj.length; i++){    
					debugger
					console.log("get",obj[i])
					var pymtplanname=obj[i].payment_plan_name==undefined?'':obj[i].payment_plan_name;
					
					if (obj[i].isactive == "A") {
						htmlActionBtn = "";
						htmlActionBtn +=  '<div style="text-align:center;">'
							+'<button class="btn btn-danger btn-sm rounded-0"  onclick="deleteTowerPP(this)" type="button" data-toggle="tooltip" data-placement="top" title="Delete"><i class="fa fa-trash"></i></button>'
										+ '<button class="fa fa-trash cancelPayReqBtn" onclick="cancelPayReq(this)"><i class="fa fa-trash"></i></button>'
									+ '</div>';
					} else {
						htmlActionBtn = "";
					}
					
					
					
					html += 	'<tr class="paymentRankDataPlotRow '+trxSuccess+'" data-rowid = "'+obj[i].id+'">'	
									+ '<td style="text-align:center;">'
									
											+ '<span class="oldTower" id="ppDropdown">'+towername+'</span>' 
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
				
				$("#paymentRankListId tbody tr:first-child").after(html);
			
		}*/
		$('#paymentRankListId').DataTable(
				{
					destroy: true,
					language: {
					       // search: "_INPUT_",
					        searchPlaceholder: "Search by name"
					    }
				});
		
	}).done(function(obj){
		//var data =JSON.parse(obj);
		
		if(data!=null){
			if (data.status == "STATUS_NOTOK") {
				alert (data.error_msg);
			}
		}
		
	});
	
}

