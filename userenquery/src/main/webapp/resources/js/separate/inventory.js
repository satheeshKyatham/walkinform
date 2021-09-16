$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});

var pageContext = $("#pageContext").val()+"/";	

$(document).ready(function(){
    $('[data-toggle="popover"]').popover();   
});

setInterval(function(){
	if ($('#inventoryTab').hasClass('active')) {
		if ($('#towerMst').val().trim() != ""){
			inventoryLoad("autoRefresh");
		}
	}
},30000);

function getInventoryRec () {
	if ( $('#towerMst option:selected').val() != '') {
		inventoryLoad ("getRec");
	} else {
		swal({
			title: "Please select the Tower",
		    text: "",
		    timer: 3000,
		    type: "error",
		});
	}
}

function holdExistData () {
	$.post(pageContext+"holdExistData",{"projectNameId":$('#projectId').val(), "customerId":$('#hiddenMobileNo').val()},function(data){				 
		
		var holdMinVar = 0;
		var holdSecVar = 0;
		
	}).done(function(data){
		var obj =JSON.parse(data);
		
		if (obj.length > 0) {
			for(var i=0;i<obj.length;i++){
				
				holdMinVar = obj[i].holdMin;
				holdSecVar = obj[i].holdSec;
				
				if (holdMinVar == 'undefined' || holdMinVar == null){
					holdMinVar = 0;
				} else if (holdSecVar == 'undefined' || holdSecVar == null) {
					holdSec = 0;
				} else {
					$('#holdCountZero').hide();
				}
				
				//holdCounter(holdMinVar, holdSecVar);
				
				clearInterval(interval);
				
				//HoldReverseTimer(0, 15);
				
				
				
				HoldReverseTimer(holdMinVar, holdSecVar);
				
				var unitId =  '"'+obj[i].unitSfid+'"';
				var customerId = '"'+obj[i].customer_id+'"';
				
				$('#inventoryBreadcrumb').empty();
				
				$('#inventoryBreadcrumb').append('<div style="min-height: 40px; text-align: right; background-color: #26262a; color: #fff; padding-right: 15px; "> Property Release in <span class="holdCountdown"></span> min. </div>');
				$('#inventoryBreadcrumb').append("<nav aria-label='breadcrumb'><ol class='breadcrumb'> <li class='breadcrumb-item'><a>Held by You</a></li> <li class='breadcrumb-item'> Tower: "+obj[i].tower_name+" </li> <li class='breadcrumb-item'>Floor: "+obj[i].floor_no+"</li>  <li class='breadcrumb-item'>Unit No: "+obj[i].unit_no+" </li>  <li class='breadcrumb-item pull-right' onclick='releaseFromHold(this, "+unitId+", "+customerId+")'><a>Release Unit for other</a></li> </ol></nav>");
			}
		} else {
			$('#holdCountZero').hide();
		} 
		
		
	});	
}

function inventoryLoad (callFrom){
	
	$('#towerRec').val($('#towerMst option:selected').text());
	$('#towerRecCode').val($('#towerMst option:selected').val());
	
	//if (callFrom != "autoRefresh") {
		$('#inventoryLoader').show();
	//}
	
	var unitStatus = "";
	var holdVal = '';
	
	var unitAvailable = '';
	
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

	if ($("#ilAvailableCheck").is(":checked")) { 
		unitAvailable = 'f';
		//alert("Check box in Checked"); 
	} else {
		unitAvailable = '';
		//alert("Check box is Unchecked"); 
	} 
	
	
	$.post(pageContext+"getInventoryDetails",{"projectId":$('#projectId').val(), "towerMst":$('#towerMst').val(), "typoMst":$("#typoMst").val(), "holdMst":holdVal, "soldMst":soldVal, "facing":$("#facing").val(), "unitAvailable":unitAvailable, "unitCategory":$("#unitCategory").val() },function(data){				 
		
	}).done(function(data){
		$("#inventoryData").empty();
		
		var obj =JSON.parse(data);
		var html = '';
		var min = '';
		var sec = '';
		var wing = "";
		
		html += "<div class='commonLoad' id='inventoryLoader' ></div>";
		
		for(var i=0;i<obj.length;i++){
			var obj1 =obj[i];
			html += "<div class='floorBand'>";
			html += "<div class='floorCel'> <div class='fcData'>"+obj1[0].floor_number__c+"</div></div>";
			
			for(var j=0;j<obj1.length;j++){
				
				var unitcheckbox = '';
				var unitSfid= '"'+obj1[j].sfid+'"';
				var holdByUserId = '"'+obj1[j].customer_id+'"';
				var dropdown = "";
				var caret = "";
				var houseNo = '"'+obj1[j].propstrength__house_unit_no__c+'"';
				var floorNo = '"'+obj1[0].floor_number__c+'"';
				
				var towerCode = '"'+obj1[0].propstrength__tower__c+'"';
				
				if (obj1[j].propstrength__property_on_hold_for_reallocation__c == 't' ) {
					dropdown = "";
					caret = "";
					unitStatus = "";
					unitStatus = "unitHold";
				} else if (obj1[j].propstrength__allotted__c == 't' || obj1[j].PropStrength__Property_Alloted_Through_Offer__c == 't'){
					dropdown = "";
					caret = "";
					unitStatus = "";
					if(obj1[j].hold_reason==='block' && obj1[j].propstrength__property_on_hold_for_reallocation__c != 't' && obj1[j].sfdc_propstrength__allotted__c != true  && obj1[j].PropStrength__Property_Alloted_Through_Offer__c != 't')
						unitStatus="unitBlock";
					else if (obj1[j].hold_reason==='temp' && obj1[j].propstrength__property_on_hold_for_reallocation__c != 't' && obj1[j].sfdc_propstrength__allotted__c != true  && obj1[j].PropStrength__Property_Alloted_Through_Offer__c != 't') {
						if (obj1[j].eoi_unit_locked == true && obj1[j].enq_sfid == $('#enquirysfid').val()) {
							unitStatus="unitEOIBlockAdmin";
							dropdown = "<ul class='dropdown-menu'>  <li><a onclick='viewCostsheet(this, "+unitSfid+", "+houseNo+", "+floorNo+", "+towerCode+")'>View Costsheet</a></li> </ul>";
							caret = "caret";
						
						//add condition not equal to tru for alloted, offer through alloted and hold for reallocation
						} 
						
						/*else if (obj1[j].hold_reason==='temp' && obj1[j].hold_behalf_userid == $('#userid').val()) {
							unitStatus="unitTempAdmin";
							dropdown = "<ul class='dropdown-menu'>  <li><a onclick='viewCostsheet(this, "+unitSfid+", "+houseNo+", "+floorNo+", "+towerCode+")'>View Costsheet</a></li> </ul>";
							caret = "caret";
						}*/
						
						else {
							unitStatus="unitTemp";
						}
					}
					
					else
						unitStatus = "unitSold";
					
					
					
					var value = $("#searchadmintype").val();
					if(value=='t' || value=='f'){
						 unitcheckbox='<input type="checkbox" value='+unitSfid+' name="unit" > '; 
						}
				} 
				else if (obj1[j].flagForHold == 'Hold'){
					dropdown = "<ul class='dropdown-menu'> <li><a onclick='releaseFromHold(this, "+unitSfid+",  "+holdByUserId+")'>Release from hold</a></li> </ul>";
					caret = "caret";
					
					unitStatus = "";
					unitStatus = "intervalHold   HOLD123";
				} else if (obj1[j].flagForHold == 'Release' || obj1[j].flagForHold == undefined) {
					//Add code for property Type Check - Added By Satheesh 
					
					 var value = $("#searchadmintype").val();
					if(value=='t' || value=='f'){
						 unitcheckbox='<input type="checkbox" value='+unitSfid+' name="unit" > '; 
						} 
					unitStatus = "unitAvailable Available123";
					dropdown = "<ul class='dropdown-menu'> <li><a onclick='holdInterval(this, "+unitSfid+", "+houseNo+", "+floorNo+", "+towerCode+")'>Hold</a></li>  <li><a onclick='viewCostsheet(this, "+unitSfid+", "+houseNo+", "+floorNo+", "+towerCode+")'>View Costsheet</a></li>  </ul>";
					caret = "caret";
				}
				else {
					//Add code for property Type Check - Added By Satheesh
					
					 var value = $("#searchadmintype").val();
					 if(value=='t' || value=='f'){
							unitcheckbox='<input type="checkbox" value='+unitSfid+' name="unit" > '; 
						} 
					unitStatus = "unitAvailable";
					dropdown = "<ul class='dropdown-menu'> <li><a onclick='holdInterval(this, "+unitSfid+", "+houseNo+", "+floorNo+", "+towerCode+")'>Hold</a></li> <li><a onclick='viewCostsheet(this, "+unitSfid+", "+houseNo+", "+floorNo+", "+towerCode+")'>View Costsheet</a></li> </ul>";
					caret = "caret";
				}
				
				if (obj1[j].wing_block__c != undefined) {
					wing = obj1[j].wing_block__c + " - ";
				} else {
					wing = "";
				}
				
				html += "<div class='unitCel dropdown' style='cursor: pointer;'> <div type='button' data-toggle='dropdown' class='fcData dropdown-toggle "+ unitStatus +"'>" +wing +obj1[j].propstrength__house_unit_no__c+"<span class='"+caret+"'></span></div>   "+dropdown+" </div>";
			}
			html += "<div class='clearfix'></div></div>";
		}
		
		$("#inventoryData").append (html);	
		
		$('#inventoryLoader').hide();
		
    });	
}

function holdInterval (e, sfid, unitNo, floor, towerCode) {
	
	
	
	var urlProject = pageContext+"getPropertyTypeStatus?propertyid="+sfid
	var propertyTypeChargeStatus="";
	$.getJSON(urlProject, function (data) {
		propertyTypeChargeStatus=data;		
	}).done(function() {
		
		if(propertyTypeChargeStatus=="Y")
		{
			swal({
				title: "Property Type Charges",
			    text: "Validation Message - Please contact ECRM Administrator to move the property type charges from Property Type to Property.",
			    timer: 2000,
			    type: "error",
			});
		}
	else
		{
			$('#printableArea').hide();
			$('#printBtnFloat').hide();
			
			$('#inventoryLoader').show();
			
			$.post(pageContext+"holdInventoryRqst",{"customerId":$('#hiddenMobileNo').val(), "unitSfid":sfid, "projectNameId":$('#projectId').val(), "towerCode": $('#towerRecCode').val(),  "towerName": $('#towerRec').val(), "unitNo": unitNo, "floorNo": floor,"userid": $('#userid').val(), "contactsfid": $('#primarycontactsfid').val() },function(data){				 
				
			}).done(function(data){
				
				if (data == 'inserted'){
					//alert ("Success");
					//inventoryLoad ();
					
					
					//$('.btnNext').click(function(){
						 
						$('#unitSfid').val(sfid);
						$('#towerSfid').val(towerCode);
						
						$('.holdCountdown').show();
						$('#holdCountZero').hide();
					
							$('#costsheetTab a').trigger('click');
						  //holdCounter ();
						//}); 
					inventoryLoad("holdUnit");
				} else {
					$('#inventoryLoader').hide();
					alert (data);
				}
				
				
				/*setInterval(function(){
					alert ("Test 4765413 54654 65");
					inventoryLoad("refreshAfter5Min");
				},300000);*/ //5 min
			});	
		}
	});
	
	
}

/*
function viewCostsheet (e, sfid, unitNo, floor, towerCode) {
	var urlProject = pageContext+"getPropertyTypeStatus?propertyid="+sfid
	var propertyTypeChargeStatus="";
	$.getJSON(urlProject, function (data) {
		propertyTypeChargeStatus=data;		
	}).done(function() {
		
		if(propertyTypeChargeStatus=="Y")
		{
			swal({
				title: "Property Type Charges",
			    text: "Validation Message - Please contact ECRM Administrator to move the property type charges from Property Type to Property.",
			    timer: 2000,
			    type: "error",
			});
		}
	else
		{	
			$('#unitSfid').val('');
			
			$('#printableArea').hide();
			$('#printBtnFloat').hide();
			
			$('#unitSfid').val(sfid);
			$('#towerSfid').val(towerCode);
			$('#costsheetTab a').trigger('click');	
		}
	});
	
	
}*/

function releaseFromHold (e, sfid, holdedBy) {
	
	$('#inventoryBreadcrumb').empty();
	
	$('.holdCountdown').empty();
	
	
	if (holdedBy == $('#hiddenMobileNo').val()) {
		$.post(pageContext+"releaseFromHold",{"customerId":$('#hiddenMobileNo').val(), "unitSfid":sfid, "projectNameId":$('#projectId').val()},function(data){				 
			
		}).done(function(data){
			swal({
				title: "Successfully Released",
			    text: "",
			    timer: 3000,
			    type: "success",
			});
			
			/*var min = null;
			var sec = null;
			
			HoldReverseTimer(min, sec);*/
			
			$('.holdCountdown').hide();
			$('#holdCountZero').show();
			
			inventoryLoad("releaseUnit");
			//HoldReverseTimer(0, 0);
			

		});
	} else {
		
		alert ("This Unit Not Held by You");
	}
}

towerList ();
function towerList (e, source) {
	$('#towerMst').empty();	
	var projectNameVal = $("#projectId").val();
	//var urlTower = pageContext+"getTower?project_code="+projectNameVal;
	
	var urlTower = pageContext+"getTowerMaster?project_code="+projectNameVal;
	
	
	$.getJSON(urlTower, function (data) {
		$('#towerMst').append('<option value="">Select</option>');
		$.each(data, function (index, value) {
			$('#towerMst').append("<option value='"+value.tower_code__c+"'>"+value.tower_name__c+"</option>");
		});					
	}).done(function() {
		
	});
}

/*Typology*/
function inventoryUnitTypeMst () {
	$('#typoMst').empty();
	var towerCode = $('#towerMst').val();
	var projectNameVal = $('#projectId').val();
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
/* Property Type Charges Status Check START*/
function getPropertyTypeChargeStatus(propertyID)
{
	var urlProject = pageContext+"getPropertyTypeStatus?propertyid="+propertyID
	
	$.getJSON(urlProject, function (data) {
		alert("Response:"+data)
		/*$.each(data, function (index, value) {
			alert()
		});	*/				
	}).done(function() {
		
	});	
	
	}
/* Property Type Charges Status Check END*/
