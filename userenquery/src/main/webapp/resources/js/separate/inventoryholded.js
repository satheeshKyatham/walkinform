var pageContext = $("#contextPath").val()+"/";
towerLists();
function towerLists()
{
//alert("HFDSFFFKSFVF");
$('#towerMsts').empty();	
//alert($("#projectid").val())
var projectNameVal = $("#projectid").val();
var urlTower = pageContext+"getTower?project_code="+projectNameVal;


$.getJSON(urlTower, function (data) {
	
	$('#towerMsts').append('<option value="">Select</option>');
	$.each(data, function (index, value) {
		
		$('#towerMsts').append("<option value='"+value.tower_code__c+"'>"+value.tower_name__c+"</option>");
	});					
}).done(function() {
		
	// $('#tdd .commonLoad').hide();
	
});

}


/*Typology*/
function inventoryUnitTypeMst () {
	$('#typoMst').empty();
	 
	
	
	
	var towerCode = $('#towerMsts').val();
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


function getInventoryRec () {
	if ( $('#towerMsts option:selected').val() != '') {
		holdInventoryLoad ();
	} else {
		swal({
			title: "Please select the Tower",
		    text: "",
		    timer: 3000,
		    type: "error",
		});
	}
}
function holdInventoryLoad (){
	$('#towerRec').val($('#towerMsts option:selected').text());
	
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
				
				if (obj1[j].propstrength__property_on_hold_for_reallocation__c == 't') {
					dropdown = "";
					caret = "";
					
					unitStatus = "";
					unitStatus = "unitHold";
				} else if (obj1[j].propstrength__allotted__c == 't'){
					dropdown = "";
					caret = "";
					unitStatus = "";
					if(obj1[j].hold_reason==='block')
						unitStatus="unitBlock";
					else if(obj1[j].hold_reason==='temp')
						unitStatus="unitTempAdmin";
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
					caret = "caret";
					
					unitStatus = "";
					unitStatus = "intervalHold   HOLD123";
					
				} else if (obj1[j].flagForHold == 'Release' || obj1[j].flagForHold == undefined) {
					
					 var value = $("#searchadmintype").val();
					if(value=='t' || value=='f'){
						 unitcheckbox='<input type="checkbox" value='+unitSfid+' name="unit" > '; 
						} 
					unitStatus = "unitAvailable";
					
					//dropdown = "<ul class='dropdown-menu'> <li><a onclick='holdInterval(this, "+unitSfid+", "+houseNo+", "+floorNo+")'>Hold</a></li>  <li><a onclick='viewCostsheet(this, "+unitSfid+", "+houseNo+", "+floorNo+")'>View Costsheet</a></li>  </ul>";
					caret = "caret";
				}
				
				else {
					 var value = $("#searchadmintype").val();
					 if(value=='t' || value=='f'){
							unitcheckbox='<input type="checkbox" value='+unitSfid+' name="unit" > '; 
						} 
					unitStatus = "unitAvailable";
					//dropdown = "<ul class='dropdown-menu'> <li><a onclick='holdInterval(this, "+unitSfid+", "+houseNo+", "+floorNo+")'>Hold</a></li> <li><a onclick='viewCostsheet(this, "+unitSfid+", "+houseNo+", "+floorNo+")'>View Costsheet</a></li> </ul>";
					caret = "caret";
				}
				
				dropdown = "";
				//html += "<div class='unitCel dropdown'> "+unitcheckbox+" <div type='button' data-toggle='dropdown' class='fcData dropdown-toggle "+ unitStatus +"'>"+obj1[j].propstrength__house_unit_no__c+"<span class='"+caret+"'></span></div>   "+dropdown+" </div>";
				html += "<div class='unitCel dropdown'> "+unitcheckbox+" <div type='button' data-toggle='dropdown' class='fcData dropdown-toggle "+ unitStatus +"'>"+obj1[j].propstrength__house_unit_no__c+"</div> </div>";
				
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