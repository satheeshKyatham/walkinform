//alert ($('#contextPath').val()+"/");

//var urlDomin = "http://localhost:8080/UserEnqury/";

//var urlDomin = $('#contextPath').val()+"/";
$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});
var pageContext = $("#contextPath").val()+"/";	

var enqSFIDforHoldUnit = '';

$(document).ready(function(){
	$('#csPtContainer').remove();
	$('#updateBtnCol').remove();
	$('#csHoldCountCol').hide();
	$('#csDescriptionCol').remove();
	$('#tdsPaidByDDCol').remove();
	
	$('#getCSData').data('source','ADMIN');
	
	$('[data-toggle="popover"]').popover({
    	trigger : 'hover'
    });
    
	 $('#searchadmintype').change(function(){ 
		 	if ( $('#towerMst option:selected').val() != '') {
		 		var value = $(this).val();
				if(value=='all'){
					 $('.checkboxhide').hide();  
					 $('#btnEOIHOldSave').hide();
					$("#btnholdsave").hide();
					$("#btnholdsave2").hide();
					$("#btnreleasesave").hide();
				}else if(value=='t'){
					$('.checkboxhide').show();
					$("#btnholdsave").hide();
					$('#btnEOIHOldSave').hide();
					$("#btnholdsave2").hide();
					$("#btnEOIHOldSave").hide();
					$("#btnreleasesave").show();
					inventoryLoad ();
				}else if(value=='f'){
					$('.checkboxhide').show();
					$('#btnEOIHOldSave').show();
					$("#btnholdsave").show();
					$("#btnholdsave2").show();
					$("#btnreleasesave").hide();
					inventoryLoad ()
				}
			   // getFloor(value);
			} else {
				swal({
					title: "Please select the Tower",
				    text: "",
				    timer: 3000,
				    type: "warning",
				});
			}
		});
});



/*function inventoryLoad () {
	alert ("Test Click ::: " + $('#projectid').val());
	
	
	var projectid = $('#projectid').val();
	var inventoryURL = pageContext+"/getInventoryDetails?projectid="+projectid;
		
	alert ("inventoryURL ::: " + inventoryURL);
	
		$.getJSON(inventoryURL, function (data) {
			$.each(data, function (index, value) {
				
				
				
				
				console.log(value.name);
				//$('#ppDropdown').append('<option value='+value.sfid+'>'+value.name+'</option>');
			});					
		}).done(function() {
	});
}*/






function holdBlockResion (source) {
	//$('#enqsfidInput').val('');
	
	var favorite = [];
	$.each($("input[name='unit']:checked"), function(){ 
		favorite.push($(this).val());
	});
	
	if(favorite=='' || favorite==null){
		swal({
			title: "Please select the Unit",
		    text: "",
		   // timer: 3000,
		    type: "warning",
		});
		return false;
	} 
	
	
	$('#holdBlockRsionModal').modal('show');
	
	$('#enqNameInput').val('');
	$('#holdBlockReasonInput').val('');
	enqSFIDforHoldUnit = '';
	
	$('#enqDtlTable tbody').empty();
	$('#enqDtlTable tbody').append("<tr><td colspan='3' style='text-align:center;'>No records found</td></tr>");
	
	if (source == "tempBtn") {
		$('#enqsfidInputField').hide();
		$('#userListField').show();
		$('#blockModalBtn').hide();
		$('#tempEOIModalBtn').hide();
		$('#tempModalBtn').show();
		$('#ModalLabelAdmin').text('Reason for Hold');
		
		
	} else if (source == "blockBtn") {
		$('#enqsfidInputField').hide();
		
		$('#tempModalBtn').hide();
		$('#tempEOIModalBtn').hide();
		$('#blockModalBtn').show();
		$('#ModalLabelAdmin').text('Reason for Block');
	} else if (source == "eoiHoldBtn") {
		$('#ModalLabelAdmin').text('Reason for EOI Hold');
		$('#userListField').hide();
		$('#enqsfidInputField').show();

		$('#blockModalBtn').hide();
		$('#tempModalBtn').hide();
		$('#tempEOIModalBtn').show();
		
		
	}
}






function getInventoryRec () {
	if ( $('#towerMst option:selected').val() != '') {
		inventoryLoad ();
	} else {
		swal({
			title: "Please select the Tower",
		    text: "",
		    timer: 3000,
		    type: "warning",
		});
	}
}


function SaveForRelease(){
	 var favorite = [];
	 var unitNameArray = [];
	 
     $.each($("input[name='unit']:checked"), function(){ 
         favorite.push($(this).val());
         unitNameArray.push($(this).next().text());
     });

     if(favorite=='' || favorite==null){
 		swal({
 			title: "Please select the Unit",
 		    text: "",
 		   // timer: 3000,
 		    type: "warning",
 		});
 		return false;
 	}
     
    $("#releaseUnitBtnAdmin").attr("disabled", true);
     
    $('#inventoryLoader').show();
   	$.ajax({
 	    url: pageContext+'updateAdminUnit',
 	    data: {projectid : $("#projectid").val(),
 	    	userId : $("#userid").val(),
 	    	unitsfid:favorite.join(","),
 	    	unitNames:unitNameArray.join(",")
 	      	  //projectId:$("#projectselection option:selected").val()
 	    },
 	    type: 'POST',
 	    success: function(data) { 
	 	    	swal({
					title: "Successfully Submitted",
				    text: "",
				    timer: 3000,
				    type: "success",
				});
 	    		$('#inventoryLoader').hide();
 	    		inventoryLoad ();
 	    		$("#releaseUnitBtnAdmin").attr("disabled", false);
 	    },
 	    error: function(data) {
 	    	$("#releaseUnitBtnAdmin").attr("disabled", false);
 	    }
 	});
}

function SaveForHold(msg) {
	$("#holdBlockInputInfo").text("");
	
	var favorite = [];
	$.each($("input[name='unit']:checked"), function(){ 
		favorite.push($(this).val());
	});
	
	if(favorite=='' || favorite==null){
		$("#holdBlockInputInfo").text("Select Unit");
		return false;
	} else {
		$("#holdBlockInputInfo").text("");
	}
	
	var holdBlockBehalfOfNameVal = '';
	var holdBlockBehalfOfIDVal = '';
	var enqSFIDval = '';
	
	if (msg == 'eoi_block') {
		///msg = 'temp';
		
		//holdBlockBehalfOfNameVal = $('#userListInventory option:selected').text();
		//holdBlockBehalfOfIDVal = $("#userListInventory").val();
		
		holdBlockBehalfOfNameVal = '';
		holdBlockBehalfOfIDVal = $("#userid").val();
		
		
		if(enqSFIDforHoldUnit != '' && enqSFIDforHoldUnit != undefined){
			enqSFIDval = enqSFIDforHoldUnit;
			
			$("#holdBlockInputInfo").text("");
		} else {
			$("#holdBlockInputInfo").text("Select ENQ Name");
			return false;
		}
		
		/*if($("#enqsfidInput").val().trim() != ''){
			enqSFIDval = $("#enqsfidInput").val();
			
			$("#holdBlockInputInfo").text("");
		} else {
			$("#holdBlockInputInfo").text("Select ENQ Name");
			return false;
		}*/
		
	} else if (msg == 'temp' || msg == 'block') {
		
		/*if($("#userListInventory").val().trim()==''){
			$("#holdBlockInputInfo").text("Select user");
			return false;
		} else {
			$("#holdBlockInputInfo").text("");
		}*/
		
		if($("#userListInventory").val().trim() != ''){
			holdBlockBehalfOfNameVal = $('#userListInventory option:selected').text();
			holdBlockBehalfOfIDVal = $("#userListInventory").val();
			
			$("#holdBlockInputInfo").text("");
		} else {
			$("#holdBlockInputInfo").text("Select user");
			return false;
		}
	}
	
	
	
	
	$("#tempModalBtn").attr("disabled", true);
	$("#blockModalBtn").attr("disabled", true);
	
	$('#inventoryLoader').show();
	
  	$.ajax({
	    url: pageContext+'saveAdminUnit',
	    data: {
			projectid : $("#projectid").val(),
			userId : $("#userid").val(),
			unitsfid:favorite.join(","),
			holdmsg:msg,
			reasonInput : $("#holdBlockReasonInput").val(),
			holdBlockBehalfOfName : holdBlockBehalfOfNameVal,
			holdBlockBehalfOfID : holdBlockBehalfOfIDVal,
			enqSFID : enqSFIDval,
	    },
	    type: 'POST',
	    success: function(data) { 
		    		
			    	if (data == 'duplicateRecords') {
			    		swal({
		                	title: "Unit is already block",
		          			text: "",
		          			//timer: 8000,
		          			type: "warning",
		                });
			    		
			    		inventoryLoad ();
			    		$('#inventoryLoader').hide();
			    	} else if (data == 'success')  {
			    		$('#holdBlockRsionModal').modal('hide');
			    		$("#tempModalBtn").attr("disabled", false);
			    		$("#blockModalBtn").attr("disabled", false);
				    	swal({
							title: "Successfully Submitted",
						    text: "",
						    timer: 3000,
						    type: "success",
						});
			    		inventoryLoad ();
			    		$('#inventoryLoader').hide();
			    	}
	    	
	    	
	    			
	    },
	    error: function(data) {
	    	$('#holdBlockRsionModal').modal('hide');
	    	swal({
				title: "There was problem in submitting record at this time. Please try again",
			    text: "",
			    //timer: 3000,
			    type: "warning",
			});
	    	
	    	$("#tempModalBtn").attr("disabled", false);
    		$("#blockModalBtn").attr("disabled", false);
	    }
	});
}

function inventoryLoad (){
	//alert ("Test 4654567685456");
	
	
	$('#towerRec').val($('#towerMst option:selected').text());
	
	$('#inventoryLoader').show();
	
	var unitStatus = "";
	
	var holdVal = '';
	if($('#holdMst').is(":checked")){
		holdVal = $('#holdMst').val();
    }
    else {
    	holdVal = "";
    }
	
	
	var soldVal = '';
	if($('#soldMst').is(":checked")){
		soldVal = $('#soldMst').val();
    }
    else {
    	soldVal = "";
    }
	
	
	
 
	$.post(pageContext+"getInventoryDetailsAdmin",{"projectId":$('#projectid').val(), 
		"towerMst":$('#towerMst').val(), "typoMst":$("#typoMst").val(), 
		"holdMst":holdVal, "soldMst":soldVal
		, "unitAvailable":$("#searchadmintype").val(), "facing":"facing"	
	},function(data){				 
		
		
	}).done(function(data){
		
		$("#inventoryData").empty();
		
		
		
		var obj =JSON.parse(data);
		var html = '';
		var wing = "";
		
		html += "<div class='commonLoad' id='inventoryLoader'></div>";
		
		
		debugger;
		for(var i=0;i<obj.length;i++){
			var obj1 =obj[i];
			html += "<div class='floorBand'>";
			html += "<div class='floorCel'> <div class='fcData'>"+obj1[0].floor_number__c+"</div></div>";
			
			for(var j=0;j<obj1.length;j++){
				
				
				 
				
				var unitSfid= '"'+obj1[j].sfid+'"';
				
				var holdByUserId = '"'+obj1[j].customer_id+'"';
				
				
				var dropdown = "";
				var caret = "";
				var unitcheckbox="";
				
				
				var houseNo = '"'+obj1[j].propstrength__house_unit_no__c+'"';
				var floorNo = '"'+obj1[0].floor_number__c+'"';
				var towerCode = '"'+obj1[0].propstrength__tower__c+'"';
				
				if (obj1[j].propstrength__property_on_hold_for_reallocation__c == 't' || obj1[j].PropStrength__Property_Alloted_Through_Offer__c == 't' ) {
					/*dropdown = "";
					caret = "";
					
					unitStatus = "";
					unitStatus = "unitHold";*/
					//dropdown = "";
					//caret = "";
					unitStatus = "";
					if(obj1[j].hold_reason==='block')
						unitStatus="unitBlock";
					else if (obj1[j].hold_reason==='temp') {
						unitStatus="unitTemp";
					}
					else
						unitStatus = "unitSold";
				} else if (obj1[j].propstrength__allotted__c == 't'){
					//dropdown = "";
					//caret = "";
					unitStatus = "";
					if(obj1[j].hold_reason==='block'){
						unitStatus="unitBlock";
					}
					else if(obj1[j].hold_reason==='temp'){
						if (obj1[j].eoi_unit_locked == true) {
							unitStatus="unitEOIBlockAdmin";
						}else {
							unitStatus="unitTempAdmin";
						}
					}
					else
						unitStatus = "unitSold";
					var value = $("#searchadmintype").val();
					if(value=='t' || value=='f'){
						 unitcheckbox='<input type="checkbox" value='+unitSfid+' name="unit" > '; 
						}
				} 
				
				/*else if (obj1[j].holdstatusyn == 'Y'){
					dropdown = "<ul class='dropdown-menu'> <li><a onclick='holdInterval(this, "+unitSfid+")'>Hold</a></li> </ul>";
					caret = "caret";
					unitStatus = "";
					unitStatus = "intervalHold";
				}*/
				
				else if (obj1[j].flagForHold == 'Hold'){
					/*dropdown = "<ul class='dropdown-menu'> <li><a onclick='holdInterval(this, "+unitSfid+")'>Hold</a></li> </ul>";
					caret = "caret";*/
					
				//	dropdown = "<ul class='dropdown-menu'> <li><a onclick='releaseFromHold(this, "+unitSfid+",  "+holdByUserId+")'>Release from hold</a></li> </ul>";
					//caret = "caret";
					
					unitStatus = "";
					unitStatus = "intervalHold   HOLD123";
					
				} else if (obj1[j].flagForHold == 'Release' || obj1[j].flagForHold == undefined) {
					
					 var value = $("#searchadmintype").val();
					if(value=='t' || value=='f'){
						 unitcheckbox='<input type="checkbox" value='+unitSfid+' name="unit" > '; 
						} 
					unitStatus = "unitAvailable";
					
					//dropdown = "<ul class='dropdown-menu'> <li><a onclick='holdInterval(this, "+unitSfid+", "+houseNo+", "+floorNo+")'>Hold</a></li>  <li><a onclick='viewCostsheet(this, "+unitSfid+", "+houseNo+", "+floorNo+")'>View Costsheet</a></li>  </ul>";
					//caret = "caret";
				}
				
				else {
					 var value = $("#searchadmintype").val();
					 if(value=='t' || value=='f'){
							unitcheckbox='<input type="checkbox" value='+unitSfid+' name="unit" > '; 
						} 
					unitStatus = "unitAvailable";
					//dropdown = "<ul class='dropdown-menu'> <li><a onclick='holdInterval(this, "+unitSfid+", "+houseNo+", "+floorNo+")'>Hold</a></li> <li><a onclick='viewCostsheet(this, "+unitSfid+", "+houseNo+", "+floorNo+")'>View Costsheet</a></li> </ul>";
					//caret = "caret";
				}
				
				dropdown = "<ul class='dropdown-menu'> " +
							"	<li><a onclick='viewCostsheet(this, "+unitSfid+", "+houseNo+", "+floorNo+", "+towerCode+")'>View Costsheet</a></li> " +
						" </ul>";
				caret = "caret";
				
				//dropdown = "";
				//html += "<div class='unitCel dropdown'> "+unitcheckbox+" <div type='button' data-toggle='dropdown' class='fcData dropdown-toggle "+ unitStatus +"'>"+obj1[j].propstrength__house_unit_no__c+"<span class='"+caret+"'></span></div>   "+dropdown+" </div>";
				
				if (obj1[j].wing_block__c != undefined) {
					wing = obj1[j].wing_block__c + " - ";
				} else {
					wing = "";
				}
				
				html += "<div class='unitCel dropdown'> "+unitcheckbox+" <div type='button' data-toggle='dropdown' class='fcData dropdown-toggle "+ unitStatus +"'>" +wing+obj1[j].propstrength__house_unit_no__c+"<span class='"+caret+"'></span></div>   "+dropdown+" </div>";
				//html += "<div class='unitCel dropdown'> "+unitcheckbox+" <div type='button' data-toggle='dropdown' class='fcData dropdown-toggle "+ unitStatus +"'>" +wing+obj1[j].propstrength__house_unit_no__c+"</div> </div>";
				
			}
			
			html += "<div class='clearfix'></div></div>";
		}
		
		
		
		/*for (var i = 0;flors > i; i++) {
			
			
			
			html += "<div class='floorBand'>";
			
				
			html += "<div class='unitCel'> <div class='fcData'>"+obj[i].name+"</div></div>";
			
			html += "<div class='clearfix'></div></div>";
		};*/
		
		
		
		$("#inventoryData").append (html);	
		
		$("#inventoryLoader").hide();
    });	
}



function holdInterval (e, sfid, unitNo, floor) {
	
	
	//alert ("holdByUserId ::: " + holdByUserid);
	
	/*$("#towerMst").val();
	$("#typoMst").val();
	$("#typoMst").val();*/
	
	
	$('#inventoryBreadcrumb').empty();
	
	$('#inventoryBreadcrumb').append("<nav aria-label='breadcrumb'><ol class='breadcrumb'>  <li class='breadcrumb-item'> "+ $('#towerRec').val()+" </li> <li class='breadcrumb-item'>"+floor+"</li>  <li class='breadcrumb-item'> "+unitNo+" </li></ol></nav>");
	
	
	
	
	$('#inventoryLoader').show();
	
	$.post(pageContext+"holdInventoryRqst",{"customerId":$('#hiddenMobileNo').val(), "unitSfid":sfid, "projectNameId":$('#projectid').val(), "towerCode": $('#towerMst').val()},function(data){				 
		
	}).done(function(data){
		
		/*if (data = 'moreThanOne') {
			alert ("You can not Hold more than One unit");
		}*/
		
		//alert ("sfid ::: " + data);
		
		if (data == 'inserted'){
			//alert ("Success");
			//inventoryLoad ();
			
			
			//$('.btnNext').click(function(){
				 
				$('#unitSfid').val(sfid);
			
					$('#costsheetTab a').trigger('click');
				  holdCounter ();
				//}); 
			
			
			
			//window.open(pageContext+'costsheet?hold=yes', '_blank');
			
			inventoryLoad();
			
			//$('#inventoryLoader').hide();
			
			//window.location.href="http://localhost:8080/UserEnqury/costsheet?hold=yes";
			
		} else {
			$('#inventoryLoader').hide();
			alert (data);
		}
	});	
}





function viewCostsheet (e, sfid, unitNo, floor) {
	$('#unitSfid').val(sfid);
	$('#costsheetTab a').trigger('click');	
}






function releaseFromHold (e, sfid, holdedBy) {
	//alert ("releaseFromHold");
	
	if (holdedBy == $('#hiddenMobileNo').val()) {
		$.post(pageContext+"releaseFromHold",{"customerId":$('#hiddenMobileNo').val(), "unitSfid":sfid, "projectNameId":$('#projectid').val()},function(data){				 
			
		}).done(function(data){
			swal({
				title: "Successfully Released",
			    text: "",
			    timer: 3000,
			    type: "success",
			});
			inventoryLoad();
		});
	} else {
		
		alert ("This Unit Not Hold By You");
	}
	
		
}


towerList ();

function towerList (e, source) {
	$('#towerMst').empty();	
	//alert($("#projectid").val())
	var projectNameVal = $("#projectid").val();
	var urlTower = pageContext+"getTower?project_code="+projectNameVal;
	
	
	$.getJSON(urlTower, function (data) {
		
		$('#towerMst').append('<option value="">Select</option>');
		$.each(data, function (index, value) {
			$('#towerMst').append("<option value='"+value.tower_code__c+"'>"+value.tower_name__c+"</option>");
			$('#towerMstReport').append("<option value='"+value.tower_code__c+"'>"+value.tower_name__c+"</option>");
			$('#towerMstSalesHoldReport').append("<option value='"+value.tower_code__c+"'>"+value.tower_name__c+"</option>");
		});					
	}).done(function() {
			
		// $('#tdd .commonLoad').hide();
		
	});
	
	//unitTypeMst ();
	
}

/*Typology*/
function inventoryUnitTypeMst () {
	$('#typoMst').empty();
	 
	
	
	
	var towerCode = $('#towerMst').val();
	var projectNameVal = $('#projectid').val();
	

	
	
	var urlProject = pageContext+"getunittype?project_code="+projectNameVal+"&tower_code="+towerCode+"&floor_code="
	
	$('#typoMst').append("<option value='typologyAll'>All</option> ");
	
	
	$.getJSON(urlProject, function (data) {
		$.each(data, function (index, value) {
			
			
			$('#typoMst').append("<option value='"+value.propstrength__unit_type__c+"'>"+value.propstrength__unit_type__c+"</option>");
		});					
	}).done(function() {
		
		
		
	});	
}
/*END Typology*/
projectWiseUserList();

function projectWiseUserList () {
	var urlGetUsers = pageContext+"getUserProjectMapping?projectid="+$('#projectid').val();
   
	$.getJSON(urlGetUsers, function (data) {
		
		$.each(data, function (index, value) {
			var name='';
			if(value.name==undefined)
				name='';
			else
				name=value.name;
			$('#userListInventory').append("<option value="+value.user_id+">"+value.user_name+"</option>");  
		});		
	}).done(function() {
		 
	});
}





function enqDtlForAdminHold () {
	//$("#enqDtlTable tbody").empty();
	
	var enqName = "ENQ - " + $('#enqNameInput').val().trim();
	
	
	$.post(pageContext+"getEnqForAdminInventoryHold",{"enqName":enqName, "projectSFID":$('#projectid').val()},function(data){                      
		$("#enqDtlTable tbody").empty();
		
		var obj =JSON.parse(data);
		 var html = '';
		 enqSFIDforHoldUnit = '';
			
         if (obj != null) {
        	 for(var i=0;i<obj.length;i++){
        		
        		 
        		 html += "<tr>" +
        		 	" <td>"+obj[i].enq_name+"</td>" +
					" <td>"+obj[i].mobile__c+"</td>" +
					" <td>"+obj[i].name+"</td>" +
				" </tr>";
        		 enqSFIDforHoldUnit = obj[i].enq_sfid;
        		 /*obj[i].propstrength__primary_contact__c;
     			obj[i].enq_name;
     			obj[i].region__c;
     			obj[i].marketing_project_name__c;
     			obj[i].projectNameWithoutCity;*/
        	 }
        	 
        	 $("#enqDtlTable tbody").append(html);
         } else {
        	 $("#enqDtlTable tbody").append("<tr><td colspan='3'>No records found</td></tr>");
         }
         
	}).done(function(data){
          
	}).fail(function(xhr, status, error) {
    	 
    });
}