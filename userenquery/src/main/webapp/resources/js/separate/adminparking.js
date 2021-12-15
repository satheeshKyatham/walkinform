$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});

if (PARKING_MODULE_GV == "Y"){
	parkingLocationList();
} 

var pageContext = $("#pageContext").val()+"/";	
 
var enqSFIDforHoldUnit = '';

//Fixed parking type   
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
	
	
	$('#searchadminparking').change(function(){ 
	 	if ( $('#parkingTypeCP option:selected').val() != '') {
	 		var value = $(this).val();
			if(value=='all'){
				$('#btnParkingHOldSave').hide(); 
				$('#btnParkingHOldSave2').hide();
				$("#btnParkingreleasesave").hide();
				getParkingRec();
			}else if(value=='t'){
				$('#btnParkingHOldSave').hide(); 
				$('#btnParkingHOldSave2').hide();
				$("#btnParkingreleasesave").show();
				getParkingRec();
			}else if(value=='f'){
				$('#btnParkingHOldSave').show();
				$('#btnParkingHOldSave2').show();
				$("#btnParkingreleasesave").hide();
				getParkingRec();
			}
		   // getFloor(value);
		} else {
			swal({
				title: "Please select the Parking Type",
			    text: "",
			    timer: 3000,
			    type: "warning",
			});
		}
	});
});
// END - Fixed parking type

$(document).on('change','input[name=optionsParking]',function(){
	var parkingName = $( 'input[name=optionsParking]:checked' ).val();
	
	$('#proceedParking').html(parkingName);
});

function getParkingRec () {
	if ( $('#parkingTypeCP option:selected').val() != '') {	
		parkingLoad ();
	} else {
		swal({
			title: "Please select the Parking Type",
		    text: "",
		    timer: 3000,
		    type: "warning",
		});
	}
}

function parkingLoad (){
	// Comment for Admin
	/*if ($('#propertyTypeSfidCS').val().trim() == "" ) {
		alert ("Please select the inventory");
		return;
	}*/
	
	$('#towerRec').val($('#towerMst option:selected').text());
	$('#towerRecCode').val($('#towerMst option:selected').val());
	
	$('#parkingLoader').show();
	
	var unitStatus = "";
	
	var unitHTML = "";
	
	var holdVal = '';
	
	var unitAvailable = '';
	 
	var soldVal = '';
	
	$.post(pageContext+"getAdminParkingDetails",{
		"propertyTypeSfid":$('#propertyTypeSfidCS').val(), 
		"projectId":$('#projectId').val(), 
		"towerMst":"", 
		//"typoMst":"", 
		//"holdMst":"", 
		//"soldMst":"", 
		//"facing":"", 
		//"unitAvailable":"", 
		"unitCategory":$("#parkingCategory").val(),
		"parkingLocation":$("#parkingLocationMst").val(),
		"searchadminparking":$("#searchadminparking").val(),
		"parkingTypeCP":$("#parkingTypeCP").val()},function(data){				 
		
	}).done(function(data){
		
		$('#proceedParking').html("");
		
		$("#parkingData").empty();
		
		var obj =JSON.parse(data);
		var html = '';
		
		var unitcheckbox = "";
		
		var value = $("#searchadminparking").val();
		
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
				var parkingSfid = '"'+obj1[j].sfid+'"';
				
				if(value!='All'){
					unitcheckbox = '<input type="checkbox" value='+parkingSfid+' name="parkingUnit" >';
				}
				
				
				/*New Admin*/
				if (obj1[j].propstrength__is_parking_blocked__c == true || obj1[j].allotted_through_offer__c == true) {
					unitHTML = "";
					unitHTML = "<div class='unitCel' style='cursor: pointer;'> <div type='button'class='fcData unitSold'> " +obj1[j].propstrength__car_parking_name__c+" <div> </div> &nbsp; </div>  </div>"
				} else if (obj1[j].propstrength__allotted__c == true) {
					if(obj1[j].hold_reason==='block'){
						unitHTML = "";
						if (obj1[j].absolute_amount == '-1') {
							unitHTML = "<div class='unitCel' style='cursor: pointer;'> "+unitcheckbox+" <div type='button'class='fcData unitBlock'>  " +obj1[j].propstrength__car_parking_name__c+" <div>INR: not found</div> </div>   "+dropdown+" </div>"
						} else {
							unitHTML = "<div class='unitCel' style='cursor: pointer;'> "+unitcheckbox+" <div type='button'class='fcData unitBlock radio'> <label> <input type='radio' name='optionsParking' value='"+obj1[j].propstrength__car_parking_name__c+"'  data-parkingSFID = '"+obj1[j].sfid+"' data-parkingAmount = '"+obj1[j].absolute_amount+"' data-parkingCategory  = '"+obj1[j].propstrength__category_of_parking__c+"' > " +obj1[j].propstrength__car_parking_name__c+" <div>INR: "+obj1[j].absolute_amount+"</div></label></div>   "+dropdown+" </div>"
						}
					} else if(obj1[j].hold_reason==='temp'){
						
						//if (obj1[j].flatsfid == $('#unitSfid').val()) {
							unitHTML = "";
							if (obj1[j].absolute_amount == '-1') {
								unitHTML = "<div class='unitCel' style='cursor: pointer;'> "+unitcheckbox+" <div type='button'class='fcData unitEOIBlockAdmin'>  " +obj1[j].propstrength__car_parking_name__c+" <div>INR: not found</div> </div>   "+dropdown+" </div>"
							} else {
								unitHTML = "<div class='unitCel' style='cursor: pointer;'> "+unitcheckbox+" <div type='button'class='fcData unitEOIBlockAdmin radio'> <label> <input type='radio' name='optionsParking' value='"+obj1[j].propstrength__car_parking_name__c+"'  data-parkingSFID = '"+obj1[j].sfid+"' data-parkingAmount = '"+obj1[j].absolute_amount+"' data-parkingCategory  = '"+obj1[j].propstrength__category_of_parking__c+"' > " +obj1[j].propstrength__car_parking_name__c+" <div>INR: "+obj1[j].absolute_amount+"</div></label></div>   "+dropdown+" </div>"
							} 
						/*} else {
							unitHTML = "";
							if (obj1[j].absolute_amount == '-1') {
								unitHTML = "<div class='unitCel' style='cursor: pointer;'> "+unitcheckbox+" <div type='button'class='fcData unitSold'>  " +obj1[j].propstrength__car_parking_name__c+" <div>INR: not found</div> </div>   "+dropdown+" </div>"
							} else {
								unitHTML = "<div class='unitCel' style='cursor: pointer;'> "+unitcheckbox+" <div type='button'class='fcData unitSold radio'> <label> <input type='radio' name='optionsParking' value='"+obj1[j].propstrength__car_parking_name__c+"'  data-parkingSFID = '"+obj1[j].sfid+"' data-parkingAmount = '"+obj1[j].absolute_amount+"' data-parkingCategory  = '"+obj1[j].propstrength__category_of_parking__c+"' > " +obj1[j].propstrength__car_parking_name__c+" <div>INR: "+obj1[j].absolute_amount+"</div></label></div>   "+dropdown+" </div>"
							} 	
						}*/
					} else {
						unitHTML = "";
						unitHTML = "<div class='unitCel' style='cursor: pointer;'> <div type='button'class='fcData unitSold'> " +obj1[j].propstrength__car_parking_name__c+" <div> </div> &nbsp; </div>  </div>"
					}
				}  else if (obj1[j].flagForHold == 'Hold'){
					unitHTML = "";
					if (obj1[j].absolute_amount == '-1') {
						unitHTML = "<div class='unitCel' style='cursor: pointer;'> <div type='button'class='fcData intervalHold radio'> <label> <input type='radio' name='optionsParking' value='"+obj1[j].propstrength__car_parking_name__c+"'  data-parkingSFID = '"+obj1[j].sfid+"' data-parkingAmount = '"+obj1[j].absolute_amount+"' data-parkingCategory  = '"+obj1[j].propstrength__category_of_parking__c+"' >" +obj1[j].propstrength__car_parking_name__c+" <div>INR: "+obj1[j].absolute_amount+"</div> </label> </div>   "+dropdown+" </div>"
					} else {
						unitHTML = "<div class='unitCel' style='cursor: pointer;'> <div type='button'class='fcData intervalHold'> " +obj1[j].propstrength__car_parking_name__c+" <div>INR: "+obj1[j].absolute_amount+"</div> </div>   "+dropdown+" </div>"
					} 
				} else if (obj1[j].flagForHold == undefined || obj1[j].flagForHold == 'Release') {
					unitHTML = "";
					if (obj1[j].absolute_amount == '-1') {
						unitHTML = "<div class='unitCel' style='cursor: pointer;'> "+unitcheckbox+" <div type='button'class='fcData unitAvailable'>  " +obj1[j].propstrength__car_parking_name__c+" <div>INR: not found</div> </div>   "+dropdown+" </div>"
					} else {
						unitHTML = "<div class='unitCel' style='cursor: pointer;'> "+unitcheckbox+" <div type='button'class='fcData unitAvailable radio'> <label> <input type='radio' name='optionsParking' value='"+obj1[j].propstrength__car_parking_name__c+"'  data-parkingSFID = '"+obj1[j].sfid+"' data-parkingAmount = '"+obj1[j].absolute_amount+"' data-parkingCategory  = '"+obj1[j].propstrength__category_of_parking__c+"' > " +obj1[j].propstrength__car_parking_name__c+" <div>INR: "+obj1[j].absolute_amount+"</div></label></div>   "+dropdown+" </div>"
					}
				} else {
					unitHTML = "";
					if (obj1[j].absolute_amount == '-1') {
						unitHTML = "<div class='unitCel' style='cursor: pointer;'> "+unitcheckbox+" <div type='button'class='fcData unitAvailable'>  " +obj1[j].propstrength__car_parking_name__c+" <div>INR: not found</div> </div>   "+dropdown+" </div>"
					} else {
						unitHTML = "<div class='unitCel' style='cursor: pointer;'> "+unitcheckbox+" <div type='button'class='fcData unitAvailable radio'> <label> <input type='radio' name='optionsParking' value='"+obj1[j].propstrength__car_parking_name__c+"'  data-parkingSFID = '"+obj1[j].sfid+"' data-parkingAmount = '"+obj1[j].absolute_amount+"' data-parkingCategory  = '"+obj1[j].propstrength__category_of_parking__c+"' > " +obj1[j].propstrength__car_parking_name__c+" <div>INR: "+obj1[j].absolute_amount+"</div></label></div>   "+dropdown+" </div>"
					}
				}
				/* END New Admin*/
				
				
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
   


var GV_ParkingSource = "";

function proceedParking (source) {
	
	var parkingName = "";
	var parkingSFID = "";
	var parkingAmount = "";
	var parkingCategory = "";
	
	if (source == "NOPARKING") {
		$('#parkingSFIDCS').val("NO_PARKING_SELECTION");
		$('#parkingAmountCS').val("0");
		$('#parkingCatCS').val("");
		$('#parkingNameCS').val("");
		
		GV_ParkingSource = "NOPARKING";
	} else if (source == "PARKING" && ! $("input[name=optionsParking]").is(':checked')) {
		swal({
			title: "Please select the parking",
		    text: "", 
		    type: "warning", 
		});
		return;
	} else if (source == "PARKING") {
		parkingName = $( 'input[name=optionsParking]:checked' ).val();
		parkingSFID = $( 'input[name=optionsParking]:checked' ).attr("data-parkingSFID");
		parkingAmount = $( 'input[name=optionsParking]:checked' ).attr("data-parkingAmount");
		parkingCategory = $( 'input[name=optionsParking]:checked' ).attr("data-parkingCategory");
		
		$('#parkingSFIDCS').val(parkingSFID);
		$('#parkingAmountCS').val(parkingAmount);
		$('#parkingCatCS').val(parkingCategory);
		$('#parkingNameCS').val(parkingName);
		
		GV_ParkingSource = "PARKING";
	}
	//parkingReleaseFromHold ("PARKING_SELECTION");
	
	$('#printableArea').hide();
	$('#costsheetTab a').trigger('click');
}

function SaveForHoldParking(msg) {
	$("#holdBlockParkingInfo").text("");
	
	var favorite = [];
	$.each($("input[name='parkingUnit']:checked"), function(){ 
		favorite.push($(this).val());
	});
	
	if(favorite=='' || favorite==null){
		$("#holdBlockParkingInfo").text("Select parking");
		return false;
	} else {
		$("#holdBlockParkingInfo").text("");
	}
	
	var flatSFIDval = '';
	 	
	
	if (msg == 'eoi_block') {
		if(enqSFIDforHoldUnit != '' && enqSFIDforHoldUnit != undefined){
			flatSFIDval = enqSFIDforHoldUnit;
			
			$("#holdBlockParkingInfo").text("");
		} else {
			$("#holdBlockParkingInfo").text("Select flat/unit Name");
			return false;
		} 
	} else if (msg == 'block') {
		if($("#holdBlockReasonParking").val().trim() != ''){
			$("#holdBlockParkingInfo").text("");
		} else {
			$("#holdBlockParkingInfo").text("Please enter reason for block");
			return false;
		}
	}
	
	//$("#tempModalBtn").attr("disabled", true);
	$("#blockParkingModalBtn").attr("disabled", true);
	
	$('#parkingLoader').show();
	
  	$.ajax({
	    url: pageContext+'saveAdminParking',
	    data: {
			projectid : $("#projectid").val(),
			userId : $("#userid").val(),
			unitsfid:favorite.join(","),
			holdmsg:msg,
			reasonInput : $("#holdBlockReasonParking").val(),
			flatsfid:flatSFIDval,
	    },
	    type: 'POST',
	    success: function(data) { 
		    		
			    	if (data == 'duplicateRecords') {
			    		swal({
		                	title: "Unit is already block",
		          			text: "",
		          			type: "warning",
		                });
			    		
			    		parkingLoad ();
			    		$('#parkingLoader').hide();
			    	} else if (data == 'success')  {
			    		$('#holdBlockParkingModal').modal('hide');
			    		//$("#tempModalBtn").attr("disabled", false);
			    		$("#blockParkingModalBtn").attr("disabled", false);
				    	swal({
							title: "Successfully Submitted",
						    text: "",
						    timer: 3000,
						    type: "success",
						});
			    		parkingLoad ();
			    		$('#parkingLoader').hide();
			    	}
	    },
	    error: function(data) {
	    	$('#holdBlockParkingModal').modal('hide');
	    	swal({
				title: "There was problem in submitting record at this time. Please try again",
			    text: "",
			    type: "warning",
			});
	    	
	    	//$("#tempModalBtn").attr("disabled", false);
    		$("#blockParkingModalBtn").attr("disabled", false);
	    }
	});
}

function holdBlockParking (source) {
	var favorite = [];
	$.each($("input[name='parkingUnit']:checked"), function(){ 
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
	
	$('#holdBlockParkingModal').modal('show');
	
	$('#parkingNameInput').val('');
	$('#holdBlockReasonParking').val('');
	enqSFIDforHoldUnit = '';
	
	$('#flatDtlTable tbody').empty();
	$('#flatDtlTable tbody').append("<tr><td colspan='5' style='text-align:center;'>No records found</td></tr>");
	
	if (source == "blockBtn") {
		$('#flatsfidInputField').hide();
		
		//$('#tempModalBtn').hide();
		$('#tempParkingModalBtn').hide();
		$('#blockParkingModalBtn').show();
		$('#ModalLabelParkingAdmin').text('Reason for Block');
	} else if (source == "parkingHoldBtn") {
		$('#ModalLabelParkingAdmin').text('Reason for Hold against flat/unit');
		//$('#userListField').hide();
		$('#flatsfidInputField').show();

		$('#blockParkingModalBtn').hide();
		//$('#tempModalBtn').hide();
		$('#tempParkingModalBtn').show();
	}
}


function flatDtlForAdminHold () {
	
	var flatName = $('#parkingNameInput').val().trim();
	
	$.post(pageContext+"getFlatForAdminParkingHold",{"flatName":flatName, "projectSFID":$('#projectid').val()},function(data){                      
		$("#flatDtlTable tbody").empty();
		
		var obj =JSON.parse(data);
		var html = '';
		enqSFIDforHoldUnit = '';
			
		if (obj != null) {
        	 for(var i=0;i<obj.length;i++){
        		 html += "<tr>" +
        		 	" <td>"+obj[i].tower_name__c+"</td>" +
					" <td>"+obj[i].floor_name__c+"</td>" +
					" <td>"+obj[i].propstrength__unit_type__c+"</td>" +
					" <td>"+obj[i].propstrength__property_name__c+"</td>" +
					" <td>"+obj[i].propstrength__house_unit_no__c+"</td>" +
				" </tr>";
        		 enqSFIDforHoldUnit = obj[i].sfid; 
        	 }
        	 
        	 $("#flatDtlTable tbody").append(html);
         } else {
        	 $("#flatDtlTable tbody").append("<tr><td colspan='5'>No records found</td></tr>");
         }
         
	}).done(function(data){
          
	}).fail(function(xhr, status, error) {
    	 
    });
}


function SaveParkingForRelease(){
	 var favorite = [];
	 var unitNameArray = [];
	 
    $.each($("input[name='parkingUnit']:checked"), function(){ 
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
    
   $("#releaseParkingBtnAdmin").attr("disabled", true);
    
   $('#parkingLoader').show();
  	$.ajax({
	    url: pageContext+'updateAdminParking',
	    data: {projectid : $("#projectid").val(),
	    	userId : $("#userid").val(),
	    	unitsfid:favorite.join(","),
	    	unitNames:unitNameArray.join(",")
	    },
	    type: 'POST',
	    success: function(data) { 
	 	    	swal({
					title: "Successfully Submitted",
				    text: "",
				    timer: 3000,
				    type: "success",
				});
	    		$('#parkingLoader').hide();
	    		parkingLoad ();
	    		$("#releaseParkingBtnAdmin").attr("disabled", false);
	    },
	    error: function(data) {
	    	$("#releaseParkingBtnAdmin").attr("disabled", false);
	    }
	});
}

function parkingLocationList () {
	var projectsfid = $('#projectId').val();
	var urlTower = pageContext+"getParkingLocation?projectsfid="+projectsfid;
	
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