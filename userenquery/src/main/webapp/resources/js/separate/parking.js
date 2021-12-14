$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});

var pageContext = $("#pageContext").val()+"/";	
  
setInterval(function(){
	if ($('#parkingTab').hasClass('active')) {
		if ($('#parkingTowerMst').val().trim() != ""){
			parkingLoad();
		}
	}
},30000);

function getParkingRec () {
	if ( $('#parkingTowerMst option:selected').val() != '') {
		parkingLoad ();
	} else {
		/*swal({
			title: "Please select the Tower",
		    text: "",
		    timer: 3000,
		    type: "error",
		});*/
	}
}



function parkingLoad (){
	
	if ($('#propertyTypeSfidCS').val().trim() == "" ) {
		swal({
			title: "Please select the inventory",
		    text: "",
		    //timer: 3000,
		    type: "warning",
		    //allowOutsideClick: false,
		    //confirmButtonText: 'Yes it\'\s okay!',
		    //cancelButtonText: "No, cancel it!",
		    //closeOnConfirm: false,
		    //closeOnCancel: false
		});
		return;
	}
	
	$('#towerRec').val($('#towerMst option:selected').text());
	$('#towerRecCode').val($('#towerMst option:selected').val());
	
	$('#parkingLoader').show();
	
	var unitStatus = "";
	
	var unitHTML = "";
	
	var holdVal = '';
	
	var unitAvailable = '';
	 
	var soldVal = '';
	 
	
	$.post(pageContext+"getParkingDetails",{
		"propertyTypeSfid":$('#propertyTypeSfidCS').val(), 
		"projectId":$('#projectId').val(), 
		"towerMst":$('#parkingTowerMst').val(), 
		//"typoMst":$("#typoMst").val(), 
		//"holdMst":holdVal, 
		//"soldMst":soldVal, 
		//"facing":$("#facing").val(), 
		//"unitAvailable":unitAvailable, 
		"unitCategory":$("#parkingCategory").val(),
		"parkingLocation":$("#parkingLocationMst").val()},function(data){				 
		
	}).done(function(data){
		
		$('#proceedParking').html("");
		
		$("#parkingData").empty();
		
		var obj =JSON.parse(data);
		var html = '';
		 
		
		html += "<div class='commonLoad' id='parkingLoader' ></div>";
		
		for(var i=0;i<obj.length;i++){
			var obj1 =obj[i];
			html += "<div class='floorBand floorBandCol'>";
			html += "<div class='floorCel'> <div class='fcData'>"+obj1[0].propstrength__category_of_parking__c+"</div></div> <div class='parkingBoxCol' style='width: 100%; margin-left: 120px;'>";
			
			var x = 1;
			var y = 0;
			
			for(var j=0;j<obj1.length;j++){
				
				//var unitcheckbox = '';
				//var unitSfid= '"'+obj1[j].car_parking_18_digit__c+'"';
				//var holdByUserId = '"'+obj1[j].customer_id+'"';
				var dropdown = "";
				var caret = "";
				
				//For Parking
				// Start New 
				if (obj1[j].propstrength__is_parking_blocked__c == true ) {
					unitHTML = "";
					if (obj1[j].absolute_amount == '-1') {
						unitHTML = "<div class='unitCel' style='cursor: pointer;'> <div type='button'class='fcData unitHold'>  " +obj1[j].propstrength__car_parking_name__c+" <div>INR: not found</div> </div>   "+dropdown+" </div>"
					} else {
						unitHTML = "<div class='unitCel' style='cursor: pointer;'> <div type='button'class='fcData unitHold'>  " +obj1[j].propstrength__car_parking_name__c+" <div>INR: "+obj1[j].absolute_amount+"</div> </div>   "+dropdown+" </div>"
					}
				} else if (obj1[j].propstrength__allotted__c == true || obj1[j].allotted_through_offer__c == true) {
					if(obj1[j].hold_reason==='block' && obj1[j].propstrength__is_parking_blocked__c != true && obj1[j].sfdc_propstrength__allotted__c != true && obj1[j].allotted_through_offer__c != true) {
						unitHTML = "";
						if (obj1[j].absolute_amount == '-1') {
							unitHTML = "<div class='unitCel' style='cursor: pointer;'> <div type='button'class='fcData unitBlock'>  " +obj1[j].propstrength__car_parking_name__c+" <div>INR: not found</div> </div>   "+dropdown+" </div>"
						} else {
							unitHTML = "<div class='unitCel' style='cursor: pointer;'> <div type='button'class='fcData unitBlock'>  " +obj1[j].propstrength__car_parking_name__c+" <div>INR: "+obj1[j].absolute_amount+"</div> </div>   "+dropdown+" </div>"
						}
					} else if (obj1[j].hold_reason==='temp' && obj1[j].propstrength__is_parking_blocked__c != true && obj1[j].sfdc_propstrength__allotted__c != true && obj1[j].allotted_through_offer__c != true) {
						if (obj1[j].hold_status == true && obj1[j].flatsfid == $('#unitSfid').val()) {
							if (obj1[j].flagForHold == 'Hold') {
							//if (obj1[j].flagForHold == 'Hold' && obj1[j].hold_user_id == USERID_GV) {
								unitHTML = "";
								if (obj1[j].absolute_amount == '-1') {
									unitHTML = "<div class='unitCel' style='cursor: pointer;'> <div type='button'class='fcData intervalHold'>  " +obj1[j].propstrength__car_parking_name__c+" <div>INR: not found</div> </div>   "+dropdown+" </div>"
								} else {
									unitHTML = "<div class='unitCel' style='cursor: pointer;'> <div type='button'class='fcData intervalHold radio'> <label> <input type='radio' name='optionsParking' value='"+obj1[j].propstrength__car_parking_name__c+"'  data-parkingSFID = '"+obj1[j].sfid+"' data-parkingAmount = '"+obj1[j].absolute_amount+"' data-parkingCategory  = '"+obj1[j].propstrength__category_of_parking__c+"' >" +obj1[j].propstrength__car_parking_name__c+" <div>INR: "+obj1[j].absolute_amount+"</div> </label> </div>   "+dropdown+" </div>"
								}  
							} else {
								unitHTML = "";
								if (obj1[j].absolute_amount == '-1') {
									unitHTML = "<div class='unitCel' style='cursor: pointer;'> <div type='button'class='fcData unitEOIBlockAdmin'>  " +obj1[j].propstrength__car_parking_name__c+" <div>INR: not found</div> </div>   "+dropdown+" </div>"
								} else {
									unitHTML = "<div class='unitCel' style='cursor: pointer;'> <div type='button'class='fcData unitEOIBlockAdmin radio'> <label> <input type='radio' name='optionsParking' value='"+obj1[j].propstrength__car_parking_name__c+"'  data-parkingSFID = '"+obj1[j].sfid+"' data-parkingAmount = '"+obj1[j].absolute_amount+"' data-parkingCategory  = '"+obj1[j].propstrength__category_of_parking__c+"' > " +obj1[j].propstrength__car_parking_name__c+" <div>INR: "+obj1[j].absolute_amount+"</div></label></div>   "+dropdown+" </div>"
								}
							}
						} else {
							unitHTML = "";
							unitHTML = "<div class='unitCel' style='cursor: pointer;'> <div type='button'class='fcData unitSold'> " +obj1[j].propstrength__car_parking_name__c+" <div> </div> &nbsp; </div>   "+dropdown+" </div>"
						}
					}  else {
						unitHTML = "";
						unitHTML = "<div class='unitCel' style='cursor: pointer;'> <div type='button'class='fcData unitSold'> " +obj1[j].propstrength__car_parking_name__c+" <div> </div> &nbsp; </div>   "+dropdown+" </div>"
					}
				} else if (obj1[j].flagForHold == 'Hold'){
					unitHTML = "";
					if (obj1[j].absolute_amount == '-1') {
						unitHTML = "<div class='unitCel' style='cursor: pointer;'> <div type='button'class='fcData intervalHold'>  " +obj1[j].propstrength__car_parking_name__c+" <div>INR: not found</div> </div>   "+dropdown+" </div>"
					} else if (obj1[j].hold_user_id == USERID_GV) {
						unitHTML = "<div class='unitCel' style='cursor: pointer;'> <div type='button'class='fcData intervalHold radio'> <label> <input type='radio' name='optionsParking' value='"+obj1[j].propstrength__car_parking_name__c+"'  data-parkingSFID = '"+obj1[j].sfid+"' data-parkingAmount = '"+obj1[j].absolute_amount+"' data-parkingCategory  = '"+obj1[j].propstrength__category_of_parking__c+"' >" +obj1[j].propstrength__car_parking_name__c+" <div>INR: "+obj1[j].absolute_amount+"</div> </label> </div>   "+dropdown+" </div>"
					} else {
						unitHTML = "<div class='unitCel' style='cursor: pointer;'> <div type='button'class='fcData intervalHold'> " +obj1[j].propstrength__car_parking_name__c+" <div>INR: "+obj1[j].absolute_amount+"</div> </div>   "+dropdown+" </div>"
					} 
					
					$('#proceedParking').html(obj1[j].propstrength__car_parking_name__c);
				} else if (obj1[j].flagForHold == undefined || obj1[j].flagForHold == 'Release') {
					unitHTML = "";
					if (obj1[j].absolute_amount == '-1') {
						unitHTML = "<div class='unitCel' style='cursor: pointer;'> <div type='button'class='fcData unitAvailable'>  " +obj1[j].propstrength__car_parking_name__c+" <div>INR: not found</div> </div>   "+dropdown+" </div>"
					} else {
						unitHTML = "<div class='unitCel' style='cursor: pointer;'> <div type='button'class='fcData unitAvailable radio'> <label> <input type='radio' name='optionsParking' value='"+obj1[j].propstrength__car_parking_name__c+"'  data-parkingSFID = '"+obj1[j].sfid+"' data-parkingAmount = '"+obj1[j].absolute_amount+"' data-parkingCategory  = '"+obj1[j].propstrength__category_of_parking__c+"' > " +obj1[j].propstrength__car_parking_name__c+" <div>INR: "+obj1[j].absolute_amount+"</div></label></div>   "+dropdown+" </div>"
					}
				} 
				// END New 
				
				
				/*if (obj1[j].propstrength__allotted__c == true) {
					unitHTML = "";
					unitHTML = "<div class='unitCel' style='cursor: pointer;'> <div type='button'class='fcData unitSold'> " +obj1[j].propstrength__car_parking_name__c+" <div> </div> &nbsp; </div>   "+dropdown+" </div>"
				}  else if (obj1[j].propstrength__allotted__c == false && obj1[j].propstrength__is_parking_blocked__c == false && obj1[j].propstrength__active__c == true) {
					if (obj1[j].flagForHold == 'Hold'){
						unitHTML = "";
						if (obj1[j].absolute_amount == '-1') {
							unitHTML = "<div class='unitCel' style='cursor: pointer;'> <div type='button'class='fcData intervalHold'>  " +obj1[j].propstrength__car_parking_name__c+" <div>INR - not found</div> </div>   "+dropdown+" </div>"
						} else if (obj1[j].hold_user_id == USERID_GV) {
							unitHTML = "<div class='unitCel' style='cursor: pointer;'> <div type='button'class='fcData intervalHold radio'> <label> <input type='radio' name='optionsParking' value='"+obj1[j].propstrength__car_parking_name__c+"'  data-parkingSFID = '"+obj1[j].sfid+"' data-parkingAmount = '"+obj1[j].absolute_amount+"' data-parkingCategory  = '"+obj1[j].propstrength__category_of_parking__c+"' >" +obj1[j].propstrength__car_parking_name__c+" <div>INR - "+obj1[j].absolute_amount+"</div> </label> </div>   "+dropdown+" </div>"
						} else {
							unitHTML = "<div class='unitCel' style='cursor: pointer;'> <div type='button'class='fcData intervalHold'> " +obj1[j].propstrength__car_parking_name__c+" <div>INR - "+obj1[j].absolute_amount+"</div> </div>   "+dropdown+" </div>"
						} 
						
						$('#proceedParking').html(obj1[j].propstrength__car_parking_name__c);
					} else if (obj1[j].flagForHold == undefined || obj1[j].flagForHold == 'Release') {
						unitHTML = "";
						if (obj1[j].absolute_amount == '-1') {
							unitHTML = "<div class='unitCel' style='cursor: pointer;'> <div type='button'class='fcData unitAvailable'>  " +obj1[j].propstrength__car_parking_name__c+" <div>INR - not found</div> </div>   "+dropdown+" </div>"
						} else {
							unitHTML = "<div class='unitCel' style='cursor: pointer;'> <div type='button'class='fcData unitAvailable radio'> <label> <input type='radio' name='optionsParking' value='"+obj1[j].propstrength__car_parking_name__c+"'  data-parkingSFID = '"+obj1[j].sfid+"' data-parkingAmount = '"+obj1[j].absolute_amount+"' data-parkingCategory  = '"+obj1[j].propstrength__category_of_parking__c+"' > " +obj1[j].propstrength__car_parking_name__c+" <div>INR - "+obj1[j].absolute_amount+"</div></label></div>   "+dropdown+" </div>"
						}
					}
					
				} */
				// END For Parking
				
				if (x > 0) {
					html += "<div class='test' style='width: 100%; display: flex; margin-bottom: 10px;'>";
					x=0;
				}
				
				html += unitHTML;
				
				if (y == 4 || obj1.length-1 == j) {
					html += "</div>";
					x=1;
					y=-1;
				}
				
				//x++;
				y++;
			}
			html += "<div class='clearfix'></div></div></div>";
		}
		
		$("#parkingData").append (html);	
		
		$('#parkingLoader').hide();
		
    });	
}
   
// Fixed parking type   
function fixTitle() {
	$('.floorBandCol').each(function () {
		var $this = $(this);
		var offset = $this.offset().top - 80;
		var scrollTop = $(window).scrollTop();

		if (scrollTop > offset) {
			$this.addClass('fixed');
			$this.find('.floorCel').addClass('container');
		} else {
			$this.removeClass('fixed');
			$this.find('.floorCel').removeClass('container');
		}
	});
}

$(document).ready(function () {
	$(window).scroll(fixTitle);
});
// END - Fixed parking type

/*function parkingTowerList () {
	$('#parkingTowerMst').empty();	
	var projectNameVal = $("#projectId").val();
	
	var urlTower = pageContext+"getTowerMaster?project_code="+projectNameVal;
	
	
	$.getJSON(urlTower, function (data) {
		$('#parkingTowerMst').append('<option value="">Select</option>');
		$.each(data, function (index, value) {
			$('#parkingTowerMst').append("<option value='"+value.sfid+"'>"+value.tower_name__c+"</option>");
		});					
	}).done(function() {
		
	});
}*/



$(document).on('change','input[name=optionsParking]',function(){
	var parkingName = $( 'input[name=optionsParking]:checked' ).val();
	/*var parkingSFID = $( 'input[name=optionsParking]:checked' ).attr("data-parkingSFID");
	var parkingAmount = $( 'input[name=optionsParking]:checked' ).attr("data-parkingAmount");
	var parkingCategory = $( 'input[name=optionsParking]:checked' ).attr("data-parkingCategory");
	
	$('#parkingSFIDCS').val(parkingSFID);
	$('#parkingAmountCS').val(parkingAmount);
	$('#parkingCatCS').val(parkingCategory);
	$('#parkingNameCS').val(parkingName);*/
	
	
	$('#proceedParking').html(parkingName);
});

//var GV_ParkingSource = "";

function proceedParking (source) {
	if (source == "NOPARKING") {
		/*$('#parkingSFIDCS').val('');
		$('#parkingAmountCS').val('0');
		$('#parkingCatCS').val('-');
		$('#parkingNameCS').val('-');*/
		parkingReleaseFromHold ("NO_PARKING_SELECTION");
	} else if (source == "PARKING" && ! $("input[name=optionsParking]").is(':checked')) {
		swal({
			title: "Please select the parking",
		    text: "", 
		    type: "warning", 
		});
		return;
	} else if (source == "PARKING") {
		parkingReleaseFromHold ("PARKING_SELECTION");
	} 
}

//Parking
function parkingReleaseFromHold (source) {
	$.post(pageContext+"parkingReleaseFromHold",{"projectsfid":$('#projectId').val(), "userid":USERID_GV, "flatsfid":""},function(data){				 
		
	}).done(function(data){
		if (source == "PARKING_SELECTION" || source == "NO_PARKING_SELECTION") {
			selectParking(source);
		} else if (source == "RELEASE_PARKING") {
			parkingLoad();
		}
	});
}
// END parking

function selectParking (source) {
	
	var parkingName = "";
	var parkingSFID = "";
	var parkingAmount = "";
	var parkingCategory = "";
	
	if (source == "PARKING_SELECTION") {
		parkingName = $( 'input[name=optionsParking]:checked' ).val();
		parkingSFID = $( 'input[name=optionsParking]:checked' ).attr("data-parkingSFID");
		parkingAmount = $( 'input[name=optionsParking]:checked' ).attr("data-parkingAmount");
		parkingCategory = $( 'input[name=optionsParking]:checked' ).attr("data-parkingCategory");
		
		$('#parkingSFIDCS').val(parkingSFID);
		$('#parkingAmountCS').val(parkingAmount);
		$('#parkingCatCS').val(parkingCategory);
		$('#parkingNameCS').val(parkingName);
	} else {
		$('#parkingSFIDCS').val("NO_PARKING_SELECTION");
		$('#parkingAmountCS').val("0");
		$('#parkingCatCS').val("");
		$('#parkingNameCS').val("");
		
		$('#printableArea').hide(); 
		$('#costsheetTab a').trigger('click');
		
		return;
	}
	
	$.post(pageContext+"parkingSelection",{"propid":$('#unitSfid').val(), 
		"parkingsfid":$('#parkingSFIDCS').val(),
		"projectsfid":$('#projectId').val(),
		"userid":$('#userid').val()},function(data){                       
	       
	}).done(function(data){
		//parkingLoad();
		var obj =JSON.parse(data);
		
		if (obj!=null && obj.status =="STATUS_OK" && obj.record_found =="NO") {
			//if (source == "NO_PARKING_SELECTION") {
				//$('#printableArea').hide(); 
				//$('#costsheetTab a').trigger('click');
			//} else {
			swal({
				title: "",
			    text: " Parking will not get HOLD as Inventory is not under HOLD.",
			    //timer: 3000,
			    type: "warning",
			    allowOutsideClick: false,
			    showCancelButton: true,
			    confirmButtonText: 'Yes - Proceed with selection',
			    cancelButtonText: "No - Cancel selection",
			    closeOnConfirm: false,
			    closeOnCancel: false
			}).then(function(isConfirm) {
				if (isConfirm.value) {
					$('#printableArea').hide(); $('#costsheetTab a').trigger('click');
				}  
			});
			//}
		} else if (obj!=null && obj.status =="STATUS_OK" && obj.record_found =="YES") {
			$('#printableArea').hide(); $('#costsheetTab a').trigger('click');
			parkingLoad();
		} else if (obj!=null && obj.status =="STATUS_NOTOK") {
			swal({
				title: "Sorry this parking is Held by someone else, Please try again after some time.",
			    text: "",
			    type: "warning", 
			});
		}
		
	}).fail(function(xhr, status, error) {
		swal({
			title: "Sorry this parking is Held by someone else, Please Try again after some time.",
		    text: "",
		    type: "warning", 
		});
		parkingLoad();
	});
}


function parkingLocationList () {
	var towersfid = $("#parkingTowerMst").val();
	var urlTower = pageContext+"getParkingLocation?towersfid="+towersfid;
	
	var html = "";
	
	$.getJSON(urlTower, function (data) {
		$('#parkingLocationMst').empty();
		
		html += '<option value="All">All</option>';
		 
		$.each(data, function (index, value) {
			html += "<option value='"+value.location_of_parking__c+"'>"+value.location_of_parking__c+"</option>";
		});					
	}).done(function() {
		$('#parkingLocationMst').append(html);
	}); 
}