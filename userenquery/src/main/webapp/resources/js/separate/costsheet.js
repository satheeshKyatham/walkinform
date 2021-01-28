$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});
var pageContext = $("#pageContext").val()+'/'; 
var urlDomin = pageContext;
var pdfSrc = pageContext+"Costsheet?name="     
var projectNameVal = $("#projectId").val();
var SALESCOSTSHEET = "SALESCOSTSHEET";

var paymentTrxDaysAllow = 0;
var sysNowDate = "1999-09-09";
var paymentTrxMaxEndDate = "1999-09-09";
var paymentValidCondition = false;


$( document ).ready(function() {
//if ($('#projectId').val() == 'a1l6F000008iEJiQAM') {
if ($('#projectId').val() == 'a1l6F0000047Q1xQAE' || $('#projectId').val() == 'a1l6F000008iEJiQAM') {
       $('#carParkDDCol').hide();
}

csChangesForFaridabad();


});


function schemeType (e) {
       if ($(e).val() == 'other'){
            $('#schemeSourceCol').hide(); 
            $('#schemeSite').hide();
            $('#schemePromotional').hide();
            
            
    	   	$('#newPlan').empty();
             $('#updateCRM').hide();
             $('#otpApprovalCol').show();
             //$('#newPlan').append('<div class="form-group col-md-4"><label >Other </label> <input class="full form-control" id="newPlanVal" type="number"></div>');
             $('#newPlan').append('<div class="form-group col-md-3"><label >Rate per sqf </label> <input class="full form-control" id="newPlanVal" value="0" type="number"></div> <div class="form-group col-md-3"><label >Absolute </label> <input class="full form-control" id="otherAbsVal" value="0" type="number"></div> <div class="form-group col-md-3"><label >Percentage </label> <input class="full form-control" id="otherPerVal" value="0" type="number"></div>');
       } else if ($(e).val() == 'noScheme') {
    	   $('#schemeSourceCol').hide(); 
           $('#schemeSite').hide();
           $('#schemePromotional').hide();
           
           $('#updateCRM').show();
		$('#otpApprovalCol').hide();
           $('#newPlan').empty();
           
       } else if ($(e).val() == 'scheme') {
			$('#schemeSourceCol').show(); 
			$('#schemeSite').show();
			$('#schemePromotional').show();

			$('#updateCRM').show();
			$('#otpApprovalCol').hide();
			$('#newPlan').empty();
       }
}

$("#getCSData").click(function (){
	
	
	
	if ($(this).data('source') == "SALES"){
		//alert("Get CS Data");
		$('#getCSData span').html('<i class="fa fa-spinner fa-spin" style="color:#fff !important"></i>'); 
		insertAuditLogCostsheet("SALES");
       $('#updateBtnCol button').prop("disabled", false);
       
       $('#paymentPlanChangeDiv').append('<input id="paymentPlanChangeID">');
       //alert($('#ppDropdown').val());
       $('#paymentPlanChangeID').val($('#ppDropdown').val());
       if ( $('#unitSfid').val() != '') {             
             	
         	 $.post(pageContext+"getInventoryStatus",{"userid":$('#userid').val(),  "projectsfid":$('#projectsfid').val(),"propid":$('#unitSfid').val()},function(data){                       
         	       
             }).done(function(data){
              	   if (data == "errorNoUserid") {
              		   $('#getCSData span').html(''); 
            		   swal({
            			   	title: "Your session has expired. please login again", 
        	   				text: "",
        	   				type: "warning",
        	   				allowOutsideClick: true,
        	   				showConfirmButton: true
        	   			});
              	   } else if (data == "errorNoProOrUnitid") {
              		 	$('#getCSData span').html(''); 
              		 	swal({
	          			   	title: "Enquiry Sync In-Progress,", 
	      	   				text: "Please Wait OR Refresh the page.",
	      	   				type: "warning",
	      	   				allowOutsideClick: true,
	      	   				showConfirmButton: true
	      	   			});
              	   } else if (data == "errorNoUnit101") {
	              	   //$('#getCSData span').html('<i class="fa fa-spinner fa-spin" style="color:#fff !important"></i>');          
	              	   loadData (SALESCOSTSHEET);
              		   swal({
          	   				title: "This unit is no longer available please select another unit.",
          	   				text: "",
          	   				type: "warning",
          	   				allowOutsideClick: true,
          	   				showConfirmButton: true
          	   			});
              	   } else if (data == "errorUnitInactive102") {
              		   //$('#getCSData span').html('<i class="fa fa-spinner fa-spin" style="color:#fff !important"></i>');          
	              	   loadData (SALESCOSTSHEET);
              		   swal({
          	   				title: "Inventory is not activated.",
          	   				text: "",
          	   				type: "warning",
          	   				allowOutsideClick: true,
          	   				showConfirmButton: true
          	   			});
              	   } else if (data == "errorInCode103") {
              		 $('#getCSData span').html(''); 
              		   swal({

          	   				//title: "There is some technical problem, please try again.",
              			   	title: "There was problem in fetching cost sheet data at this time. Please try again by clicking get details. - 103", 

          	   				text: "",
          	   				type: "warning",
          	   				allowOutsideClick: true,
          	   				showConfirmButton: true
          	   			});
              	   } else if (data == "successUnitAvailable102") {
              		  // $('#getCSData span').html('<i class="fa fa-spinner fa-spin" style="color:#fff !important"></i>');          
	              	   loadData (SALESCOSTSHEET);
             	   }
              }).fail(function(xhr, status, error) {
            	  $('#getCSData span').html(''); 
	              	swal({

	          			//title: "There is some technical problem, please try again.",
	              		title: "There was problem in fetching cost sheet data at this time. Please try again by clicking get details.",

	          			text: "",
	          			timer: 8000,
	          			type: "warning",
	                  });
              });
       } else {
             swal({
                    title: "Please select the inventory",
                 text: "",
                 timer: 3000,
                 type: "warning",
             });
       }
	} else if ($(this).data('source') == "ADMIN") {
		$('#getCSData span').html('<i class="fa fa-spinner fa-spin" style="color:#fff !important"></i>'); 
		insertAuditLogCostsheet("ADMIN");
		loadData ('ADMINCOSTSHEET');
	}
	
       
});





function loadData (csSource) {     
	$('#schemeInput').val("");
	
	if ($('#projectId').val() == 'a1l2s000000PJPmAAO' ) {
		schemeInSalesComment();
	}
	
	
		tncData ();
		getBSPTax ();
       
       if ($('#schemeTypeDD').val() == 'scheme') {
    	   schemeDropdown ();
       } else if ($('#schemeTypeDD').val() == 'noScheme') {
    	   $('#getPln').empty();
    	   $('#getPln').append('<option value="0" data-id="-1" data-zerogovtcharges="false" data-valuepercentage="0" data-valueabsolute="0" data-valuerate="0">No Scheme</option>');
       } else if ($('#schemeTypeDD').val() == 'other') {
    	   $('#getPln').empty();
    	   
    	   // Other scheme field validtion
    	   var otherPerVal = 0;
    	   var otherAbsVal = 0;
    	   var otherNewPlanVal = 0;
    	   
    	   if ($('#otherPerVal').val() == ''){
    		   otherPerVal = 0;
    	   } else {
    		   otherPerVal = $('#otherPerVal').val();
    	   }
    	   
    	   if ($('#otherAbsVal').val() == ''){
    		   otherAbsVal = 0;
    	   } else {
    		   otherAbsVal = $('#otherAbsVal').val();
    	   }
    	   
    	   if ($('#newPlanVal').val() == ''){
    		   otherNewPlanVal = 0;
    	   } else {
    		   otherNewPlanVal = $('#newPlanVal').val();
    	   }
    	   // END Other scheme field validtion
    	   
    	   
    	   $('#getPln').append('<option data-id="-1" data-zerogovtcharges="false" data-valuepercentage="'+otherPerVal+'" data-valueabsolute="'+otherAbsVal+'" data-valuerate="'+otherNewPlanVal+'">other</option>');
       }
       
       $('.printProjectName').text($('#projectname').val());
       $('.csPtEnqSfid').val($('#enquirysfid').val());
       
       getCarParkCharges ();
       
       //$('#carParks').text($("#carParkType").val());
       //$('.carParks').text($("#carParkType").val());
       
       $('#carParks').text($('#carParkType').find('option:selected').attr("data-name"));
       $('.carParks').text($('#carParkType').find('option:selected').attr("data-name"));
       
       $('.noOfCarPark').text($("#carParkCount select").val());
       $('#noOfCarPark').text($("#carParkCount select").val());
       
       $('#viewPDF').addClass('vpdfDis');
       $('#viewPDF .commonLoad').show();
       
       
       // Not in use 20191025
       var plnVal = '';
       
       if ($('#newPlanVal').val()==undefined || $('#newPlanVal').val() == ''){
            //if($('#getPln').val() == 'other') {
            if($('#schemeTypeDD').val() == 'other') {	 
                    plnVal = 0;
             } else {
                    plnVal = $('#getPln').val();
             }
       } else {
             plnVal = $('#newPlanVal').val();
       }
       // END Not in use 20191025
       
       var unitVal = $('#unitSfid').val();
       var wing = "";
       
       /*if ($('#projectId').val() == 'a1l6F000004LVk8QAG' || $('#projectId').val() == 'a1l6F000003TXloQAG') {
      	 $('.facingCSCol').remove();
       } */
       
       var url = urlDomin+"getProjectPlan?herokuEnqId="+$('.enquiryId').val()+"&project_code="+projectNameVal+"&unit="+$('#unitSfid').val()+"&towerCode="+$('#towerSfid').val()+"&pymtPlanSfid="+$('#ppDropdown').val()+"&typology="+$('#typoMst').val()  ;
       $.getJSON(url, function (data) {
             
             $.each(data, function (index, value) {
                    
                    
                    if (unitVal == value.unitsfid) {
                           
                    	sysNowDate = value.nowData;
                    	
                    	if (sysNowDate == undefined || sysNowDate == "" || sysNowDate == null) {
                    		
                    		paymentValidCondition = false;
                    		
                    		//paymentTrxDaysAllow = 0;
                    	} else {
                    		paymentValidCondition = true;
                    		sysNowDate = value.nowData;
                    	}
                    	
                    	
                    	
                    	
                    	//alert ("Days :::" + value.paymentTrxdaysVal + " " + "Now Date	" + sysNowDate);
                    	
                    	
                           /*if (value.propstrength__completion_certificate_received__c == true || value.propstrength__category__c == 'Plot') { 
                                 bspTaxRecord ('noGst');
                                 
                           } else if (value.propstrength__completion_certificate_received__c == false || value.propstrength__category__c != 'Plot') {
                                 
                                 if (value.propstrength__pmay_abatement__c == false){
                                        
                                        if (value.propstrength__gst_status__c == 'Old GST') {
                                               
                                               bspTaxRecord ('oldTaxPercentage');
                                               
                                        } else if (value.propstrength__gst_status__c == 'New GST') {
                                               
                                               bspTaxRecord ('newTaxPercentage');
                                               
                                        }
                                        
                                 } else if (value.propstrength__pmay_abatement__c == true) {
                                        
                                        if (value.propstrength__gst_status__c == 'Old GST') {
                                               
                                               bspTaxRecord ('oldPMAYGST');
                                               
                                        } else if (value.propstrength__gst_status__c == 'New GST') {
                                               
                                               bspTaxRecord ('newPMAYGST');
                                               
                                        }
                                        
                                 }
                           }*/
                           
                           
                           
                           
                           
                           
                           
                           
                           
                           /*var bspTax = '';
                           
                           if (value.propstrength__gst_status__c == 'Old GST') {
                                 bspTax = value.old_tax;
                           } else {
                                 bspTax = value.new_tax;
                           }
                           
                           $('#bspGSTTax').val(bspTax);*/
                           
                           $('.a1').text(value.TypeofCarParking);
                           $('.a2').text(value.Typology);
                           $('#towerBankGLCode').val(value.bank__c)
                           var otherScheme = 0;
                           
                           
                           if ($('#schemeTypeDD').val() == 'other'){
                           //if ($('#getPln').val() == 'other'){
                                // OLD
                        	    //otherScheme = $('#newPlanVal').val();
                                 otherScheme = $('#getPln').find('option:selected').attr("data-valuerate");
                           } else {
                                 
                                 otherScheme = $('#getPln').find('option:selected').attr("data-valuerate");
                           }
                    
                           
                           /*if ($('#projectId').val() == 'a1l6F0000047Q1xQAE' &&  $('#ppDropdown').val() == 'a1i6F000007rApYQAU' ||  $('#projectId').val() == 'a1l6F0000047Q1xQAE' && $('#ppDropdown').val() == 'a1i6F000007rApZQAU') {
                                 $('#addlBspPer').val(4.0);
                           }*/
                    
                           
                           //$('#addlBspPer').val(value.bsp_per);                
                           
                           var bspPerAddition = (value.BasicSalePricepersquarefeet*value.bsp_per/100);
                           
                           $('.a3_bsp').text(parseFloat(value.BasicSalePricepersquarefeet));
                           $('.a3').text(parseFloat(parseFloat(value.BasicSalePricepersquarefeet)+parseFloat(value.bsp_amount)+parseFloat(bspPerAddition)-(otherScheme)).toFixed(2));
                           
                           $('#discountPsqf').val(otherScheme);
                           
                           
                           $('.a4').text(Math.round(value.CarpetArea));
                           $('.a6').text(value.SaleableArea);
                           
                          
                           if ($('#projectId').val() == 'a1l2s000000XmaMAAS') {
                        	   $('#totalSqm').text(parseFloat(value.carpet_area_converted__c));
                               $('.totalSqm').text(parseFloat(value.carpet_area_converted__c));
                               
                               $('#carpetSqm').text(parseFloat(value.length_sqm__c));
                               $('.carpetSqm').text(parseFloat(value.length_sqm__c));
                               
                               $('#balTerSqm').text(value.breadth_sqm__c);
                               $('.balTerSqm').text(value.breadth_sqm__c);
                               
                               $('#plotAreaSqyd').text(value.plot_area_sqyd__c);
                               $('.plotAreaSqyd').text(value.plot_area_sqyd__c);
                               
                           } else {
                        	   $('#totalSqm').text(parseFloat(parseFloat(value.carpet_area_converted__c) + parseFloat(value.appurtenant_area_sq_mt__c)).toFixed(2));
                               $('.totalSqm').text(parseFloat(parseFloat(value.carpet_area_converted__c) + parseFloat(value.appurtenant_area_sq_mt__c)).toFixed(2));
                               
                               $('#carpetSqm').text(parseFloat(value.carpet_area_converted__c));
                               $('.carpetSqm').text(parseFloat(value.carpet_area_converted__c));
                               
                               $('#balTerSqm').text(value.appurtenant_area_sq_mt__c);
                               $('.balTerSqm').text(value.appurtenant_area_sq_mt__c);
                               
                               $('#plotAreaSqyd').text("0");
                               $('.plotAreaSqyd').text("0");
                           }
                           
                          
                           $('#unitTval').text(value.ID);
                           $('.unitTval').text(value.ID);
                           /*Added By Satheesh K - Date : 10-06-2020 - Property Name Added on Cost sheet Page*/
                           $('.unit_property_name').text(value.propstrength__property_name__c);
                           
                           if (value.wing_block__c != undefined) {
                        	   wing = value.wing_block__c;
                           } else {
                        	   wing = "";
                           }
                           
                           $('#wingVal').text(wing);
                           $('.wingVal').text(wing);
                           
                           $('#typologyTval').text(value.Typology);
                           $('.typologyTval').text(value.Typology);
                           $('#floorTval').text(value.floorName);
                           $('.floorTval').text(value.floorName);
                          
                           if ($('#projectId').val() == 'a1l2s000000PGu3AAG' || $('#projectId').val() == 'a1l2s000000PGu8AAG' || $('#projectId').val() == 'a1l2s000000PGuDAAW' || $('#projectId').val() == 'a1l2s000000PGuIAAW' || $('#projectId').val() == 'a1l2s000000PGuNAAW' || $('#projectId').val() == 'a1l2s000000PGuSAAW') {
                        	   $('#towerTval').text("");
                               $('.towerTval').text("");
                           } else {
                        	   $('#towerTval').text(value.towerName);
                               $('.towerTval').text(value.towerName);
                           }
                           
                           csChangesForFaridabad();
                           
                           
                           if (value.property_facing__c != undefined) {
                        	   $('.propFacingType').text(value.property_facing__c);
                               $('#propFacingType').text(value.property_facing__c);
                           } else {
                        	   $('.facingCSCol').remove();
                           }
                           
                           // Not in use - 20191025
                           if (plnVal == ''){
                                 $('.b1').text((value.BasicSalePrice)-(value.GeneratorCharges)-(value.GasBankCharges)-(value.BESCOMWaterSupplyandSewage)-(value.LegalandKhataCharges));
                                 $('#BSPrate').val((value.BasicSalePrice)-(value.GeneratorCharges)-(value.GasBankCharges)-(value.BESCOMWaterSupplyandSewage)-(value.LegalandKhataCharges));
                           } else {
                                 $('.b1').text((value.BasicSalePrice)-(value.GeneratorCharges)-(value.GasBankCharges)-(value.BESCOMWaterSupplyandSewage)-(value.LegalandKhataCharges)-plnVal);
                                 $('#BSPrate').val((value.BasicSalePrice)-(value.GeneratorCharges)-(value.GasBankCharges)-(value.BESCOMWaterSupplyandSewage)-(value.LegalandKhataCharges)-plnVal);
                           }
                           // END Not in use - 20191025
                           
                           $('.b2').text(value.PreferredLocationCharges);
                           $('.b3').text(value.FloorRiseCharges);
                           $('.b4').text(value.GeneratorCharges);
                           $('.b5').text(value.GasBankCharges);
                           $('.b6').text(value.BESCOMWaterSupplyandSewage);
                           $('.b7').text(value.LegalandKhataCharges);
                           $('.b10').text(value.AdvanceMaintenance);
                           $('.b11').text(value.SinkingFundDeposit);
                           $('.ba1').text($('.b1').text()*(value.SaleableArea));
                           
                           if (value.flexibleYN == 'Y') {
                                 $('.ba2').text('');
                                  $('.ba2').text((value.PreferredLocationCharges)*(value.SaleableArea));
                           } else if (value.flexibleYN == 'N'){
                                 $('.b2').text('');                                   
                                 $('.ba2').text('');                                  
                                 $('.b2').text( Math.round((value.APreferredLocationCharges)/(value.SaleableArea)));
                                  $('.ba2').text($('.b2').text()*(value.SaleableArea));
                           }
                           
                           $('.ba3').text((value.FloorRiseCharges)*(value.SaleableArea));
                           $('.ba4').text((value.GeneratorCharges)*(value.SaleableArea));
                           $('.ba5').text((value.GasBankCharges)*(value.SaleableArea));
                           $('.ba6').text((value.BESCOMWaterSupplyandSewage)*(value.SaleableArea));
                           $('.ba7').text((value.LegalandKhataCharges)*(value.SaleableArea));
                           $('.ba8').text(value.ACoveredCarPark);
                           
                           var clubHouseDisc = "50000"
                           
                           if ($('#discount1').val()==undefined || $('#discount1').val() == ''){
                                 $('.ba9').text((value.AClubHouseCharges) - 0);
                           } else if ($('#discount1').val() == '10x'){
                                 $('.ba9').text((value.AClubHouseCharges) - clubHouseDisc);
                           }
                           
                           $('.ba10').text((value.AdvanceMaintenance)*(value.SaleableArea));
                           $('.ba11').text((value.SinkingFundDeposit)*(value.SaleableArea));
                           
                           var BasicSale = $('.ba1').text();
                           var PreferredLocation = $('.ba2').text();
                           var FloorRise = $('.ba3').text();
                           var Generator = $('.ba4').text();
                           var GasBankCharges = $('.ba5').text();
                           var BESCOMWaterSupply = $('.ba6').text();
                           var LegalandKhata = $('.ba7').text();
                           var CarPark = $('.ba8').text();
                           var ClubHouse = $('.ba9').text();
                           var AdvanceMaintenance = $('.ba10').text();
                           var SinkingFund = $('.ba11').text();
                           var totalUnitVal = (parseInt(BasicSale) + parseInt(PreferredLocation) + parseInt(FloorRise) + parseInt(Generator) + parseInt(GasBankCharges) + parseInt(BESCOMWaterSupply) + parseInt(LegalandKhata) + parseInt(CarPark) + parseInt(ClubHouse) + parseInt(AdvanceMaintenance) + parseInt(SinkingFund));
                           
                           $('.ba12Totle').text(totalUnitVal);
                           $('.c1').text(value.SunriseFacing);
                           $('.c2').text(value.GatewayFacing);
                           $('.c3').text(value.EastFacing);
                           $('.c4').text(value.CentralGardenFacing);
                           $('.c5').text(value.CornerApartment);
                           $('.c6').text(value.RearGardenFacing);
                           $('.c7Totle').text(value.TotalPLC);
                           
                    }                                
             });
             
             paymentTrxValidatore();
             
       }).done(function() {
             var costofCarpet = ($('.b1').text())*($('.a4').text());
             $('#costofCarpet').val(costofCarpet); 
             var costCom = parseInt($('.ba1').text()) + parseInt($('.ba4').text()) + parseInt($('.ba5').text()) + parseInt($('.ba6').text()) + parseInt($('.ba7').text()) - parseInt(costofCarpet) - parseInt(costofBalCony);
             $('#costCom').val(costCom); 
             $('#CostofCarpet').text(costofCarpet);
             $('#CostofExcBalcony').text(costofBalCony);
             $('#PropCost').text(costCom);
              $('#BasicPrice1').text(parseInt(costofCarpet)+parseInt(costofBalCony)+parseInt(costCom));
             $('#PreferredLoc1').text($('.ba2').text());
             $('#FloorRise1').text($('.ba3').text());                    
             $('#CarParkDev1').text($('.ba8').text());                   
             $('#ClubHouse1').text($('.ba9').text());
             $('#advance1').text($('.ba10').text());
             $('#sinkingFund1').text($('.ba11').text());
             $('#PreferredLoc2').text(Math.round($('.ba2').text()*2/3));              
              $('#BasicPrice2').text(Math.round((parseInt(costofCarpet)+parseInt(costofBalCony)+parseInt(costCom))*2/3));                                        
             $('#FloorRise2').text(Math.round($('.ba3').text()*2/3));                 
             $('#CarParkDev2').text(Math.round($('.ba8').text()*2/3));                
             $('#ClubHouse2').text($('.ba9').text());
             $('#advance2').text($('.ba10').text());
             $('#sinkingFund2').text('0');
              $('#BasicPrice4').text(Math.round(parseInt($('#BasicPrice2').text())*9/100));                    
              $('#PreferredLoc4').text(Math.round(parseInt($('#PreferredLoc2').text())*9/100));                    
              $('#FloorRise4').text(Math.round(parseInt($('#FloorRise2').text())*9/100));                    
              $('#CarParkDev4').text(Math.round(parseInt($('#CarParkDev2').text())*9/100));
              $('#ClubHouse4').text(Math.round(parseInt($('#ClubHouse2').text())*9/100));
              $('#advance4').text(Math.round(parseInt($('#advance2').text())*9/100));
              $('#sinkingFund4').text(Math.round(parseInt($('#sinkingFund2').text())*0/100));
              $('#BasicPrice6').text(Math.round(parseInt($('#BasicPrice2').text())*9/100));                    
              $('#PreferredLoc6').text(Math.round(parseInt($('#PreferredLoc2').text())*9/100));                    
              $('#FloorRise6').text(Math.round(parseInt($('#FloorRise2').text())*9/100));                    
              $('#CarParkDev6').text(Math.round(parseInt($('#CarParkDev2').text())*9/100));
              $('#ClubHouse6').text(Math.round(parseInt($('#ClubHouse2').text())*9/100));                    
              $('#advance6').text(Math.round(parseInt($('#advance2').text())*9/100));
              $('#sinkingFund6').text(Math.round(parseInt($('#sinkingFund2').text())*0/100));
             $('#BasicPrice7').text(parseInt ($('#BasicPrice1').text()) +  parseInt ($('#BasicPrice4').text()) + parseInt ($('#BasicPrice6').text()));
             $('#PreferredLoc7').text(parseInt ($('#PreferredLoc1').text()) +  parseInt ($('#PreferredLoc4').text()) + parseInt ($('#PreferredLoc6').text()));
             $('#FloorRise7').text(parseInt ($('#FloorRise1').text()) +  parseInt ($('#FloorRise4').text()) + parseInt ($('#FloorRise6').text()));
             $('#CarParkDev7').text(parseInt ($('#CarParkDev1').text()) +  parseInt ($('#CarParkDev4').text()) + parseInt ($('#CarParkDev6').text()));
             $('#ClubHouse7').text(parseInt ($('#ClubHouse1').text()) +  parseInt ($('#ClubHouse4').text()) + parseInt ($('#ClubHouse6').text()));
             $('#advance7').text(parseInt ($('#advance1').text()) +  parseInt ($('#advance4').text()) + parseInt ($('#advance6').text()));
             $('#sinkingFund7').text(parseInt ($('#sinkingFund1').text()) +  parseInt ($('#sinkingFund4').text()) + parseInt ($('#sinkingFund6').text()));
             $('#totalChargeAmt').text(parseInt ($('#BasicPrice1').text()) +  parseInt ($('#PreferredLoc1').text()) + parseInt ($('#FloorRise1').text())  + parseInt ($('#CarParkDev1').text()) + parseInt ($('#ClubHouse1').text()) + parseInt ($('#advance1').text()) + parseInt ($('#sinkingFund1').text()));
             
             var totalTaxableAmt = parseInt ($('#BasicPrice2').text()) +  parseInt ($('#PreferredLoc2').text()) + parseInt ($('#FloorRise2').text())  + parseInt ($('#CarParkDev2').text()) + parseInt ($('#ClubHouse2').text()) + parseInt ($('#advance2').text()) + parseInt ($('#sinkingFund2').text());
             $('#totalTaxableAmt').text(totalTaxableAmt);
             $('#totalTaxableAmtInput').val(totalTaxableAmt- $('#advance2').text());
             $('#totalCGSTAmt').text(parseInt ($('#BasicPrice4').text()) +  parseInt ($('#PreferredLoc4').text()) + parseInt ($('#FloorRise4').text())  + parseInt ($('#CarParkDev4').text()) + parseInt ($('#ClubHouse4').text()) + parseInt ($('#advance4').text()) + parseInt ($('#sinkingFund4').text()));
             $('#totalSGSTAmt').text(parseInt ($('#BasicPrice6').text()) +  parseInt ($('#PreferredLoc6').text()) + parseInt ($('#FloorRise6').text())  + parseInt ($('#CarParkDev6').text()) + parseInt ($('#ClubHouse6').text()) + parseInt ($('#advance6').text()) + parseInt ($('#sinkingFund6').text()));
             $('#TotalFinal').text(parseInt ($('#BasicPrice7').text()) +  parseInt ($('#PreferredLoc7').text()) + parseInt ($('#FloorRise7').text())  + parseInt ($('#CarParkDev7').text()) + parseInt ($('#ClubHouse7').text()) + parseInt ($('#advance7').text()) + parseInt ($('#sinkingFund7').text()));
             
             newOtherCharges2();
             //paymentPlanOtherCharges();
             
                    
       })
       $('.customerName').text($('#firstName').val() +" "+ $('#lastName').val());
       $('.customerContact').text($('#hiddenMobileNo').val());
       
       if ($('#carParkCountDD option').val() == 0) {
             $('.ifCarParkZero').html('');
             $('.craParkTypeLabel').html('');
             $('.noOfCarParkLabel').html('');
       }else {
    	   if ($('#projectId').val() == 'a1l2s00000000X5AAI') {     
	        	$('.craParkTypeLabel').text('Covered Car Park Space');
	        } else if ($('#projectId').val() == 'a1l2s000000XoezAAC'){
	        	$('.craParkTypeLabel').text('Parking Space');
	        } else {
	        	$('.craParkTypeLabel').text('Car Park Type');
	        }
    	   
    	   	if ($('#projectId').val() == 'a1l2s000000XoezAAC'){
    	   		$('.noOfCarParkLabel').text('No. of Parking');
    	   	} else {
    	   		$('.noOfCarParkLabel').text('No. of Car Park');
    	   	}
    	   
	       
       }
       
      if (csSource == "SALESCOSTSHEET") {
    	  getEOIPaymentRecord ();
      }
      
       
       //$(".csPtDataRow .csPtTransactionDate").attr("max", sysNowDate);
      // maxPaymentDate ();
}



function getBSPTax () {

       var unitVal = $('#unitSfid').val();
       
       var url = urlDomin+"getProjectPlan?herokuEnqId="+$('.enquiryId').val()+"&project_code="+projectNameVal+"&unit="+$('#unitSfid').val()+"&towerCode="+$('#towerSfid').val()+"&pymtPlanSfid="+$('#ppDropdown').val()+"&typology="+$('#typoMst').val()  ;
       $.getJSON(url, function (data) {
             
             
             $.each(data, function (index, value) {
                    
                    if (unitVal == value.unitsfid) {
                           
                           //NO GST tax on bsp calculation if propstrength__completion_certificate_received__c == Trure OR value.propstrength__category__c == 'Plot'
                           
                           if (value.propstrength__completion_certificate_received__c == true || value.propstrength__category__c == 'Plot' || value.propstrength__category__c == 'Plots') { 
                                 bspTaxRecord ('noGst');
                                 //no gst
                           } else if (value.propstrength__completion_certificate_received__c == false || value.propstrength__category__c != 'Plot' || value.propstrength__category__c != 'Plots') {
                                 
                                 if (value.propstrength__pmay_abatement__c == false){
                                        
                                        if (value.propstrength__gst_status__c == 'Old GST') {
                                               
                                               bspTaxRecord ('oldTaxPercentage');
                                               
                                        } else if (value.propstrength__gst_status__c == 'New GST') {
                                               
                                               bspTaxRecord ('newTaxPercentage');
                                               
                                        }
                                        
                                 } else if (value.propstrength__pmay_abatement__c == true) {
                                        
                                        if (value.propstrength__gst_status__c == 'Old GST') {
                                               
                                               bspTaxRecord ('oldPMAYGST');
                                               
                                        } else if (value.propstrength__gst_status__c == 'New GST') {
                                               
                                               bspTaxRecord ('newPMAYGST');
                                               
                                        }
                                        
                                 }
                           }
                    }      
             });    
       }).done(function() {
             
       });

}


function bspTaxRecord (taxTag) {
       var urlpayemntPlan = pageContext+"bspTaxRecord?projectcode=test"
       
       var propstrength__tax_percentage__c =          0;
       var propstrength__new_tax_percentage__c =      0;
       var propstrength__pmay_gst__c =                0;
       var propstrength__new_pmay_gst__c =            0;
       
       
       $.getJSON(urlpayemntPlan, function (data) {
             
             $.each(data, function (index, value) {
                    
                    propstrength__tax_percentage__c =              parseFloat(propstrength__tax_percentage__c+value.propstrength__tax_percentage__c);
                    propstrength__new_tax_percentage__c =        parseFloat(propstrength__new_tax_percentage__c+value.propstrength__new_tax_percentage__c);
                    propstrength__pmay_gst__c =                    parseFloat(propstrength__pmay_gst__c+value.propstrength__pmay_gst__c);
                    propstrength__new_pmay_gst__c =        parseFloat(propstrength__new_pmay_gst__c+value.propstrength__new_pmay_gst__c);
             });    
             
             /*propstrength__tax_percentage__c = getTaxPercentage(basicSaleprice, projectSfid, currentTaxRate);
             propstrength__new_tax_percentage__c = getTaxPercentage(basicSaleprice, projectSfid, currentTaxRate);
             propstrength__pmay_gst__c = getTaxPercentage(basicSaleprice, projectSfid, currentTaxRate);
             propstrength__new_pmay_gst__c = getTaxPercentage(basicSaleprice, projectSfid, currentTaxRate);*/
             
             if (taxTag == 'oldTaxPercentage') {
                    
                    $('#bspGSTTax').val(propstrength__tax_percentage__c);
                    console.log ("propstrength__tax_percentage__c ::: " + " | " + "BSP GST ::: " + propstrength__tax_percentage__c);
                    
             } else if (taxTag == 'newTaxPercentage') {
                    
                    $('#bspGSTTax').val(propstrength__new_tax_percentage__c);
                    console.log ("propstrength__new_tax_percentage__c ::: " + " | " + "BSP GST ::: " + propstrength__new_tax_percentage__c);
                    
             } else if (taxTag == 'oldPMAYGST') {
                    
                    $('#bspGSTTax').val(propstrength__pmay_gst__c);
                    console.log ("propstrength__pmay_gst__c ::: " + " | " + "BSP GST ::: " + propstrength__pmay_gst__c);
                    
             } else if (taxTag == 'newPMAYGST') {
                    
                    $('#bspGSTTax').val(propstrength__new_pmay_gst__c);
                    console.log ("propstrength__new_pmay_gst__c ::: " + " | " + "BSP GST ::: " + propstrength__new_pmay_gst__c);
                    
             } else if (taxTag == 'noGst'){
                    
                    $('#bspGSTTax').val('0');
                    console.log ("BSP GST ::: " + "NO GST");
                    
             }

             console.log ("------------------------------------------------------------------------------------------------------------------------");
             
       }).done(function() {
             
       });
}

function getTaxPercentage(basicSaleprice, projectSfid, currentTaxRate, TotalA, reraCarpetSqm){
	try{
		if(basicSaleprice==null || projectSfid == null || currentTaxRate == null){
			return 0;
		}
		
		//Added for Project Godrej Royale Woods and forest grove
		if(projectSfid == 'a1l2s000000PJPmAAO' || projectSfid == 'a1l6F000002X6IOQA0' || projectSfid == 'a1l2s00000003VlAAI' || projectSfid == 'a1l6F0000081xb4QAA' || projectSfid == 'a1l2s000000PGu3AAG' || projectSfid == 'a1l2s000000PGu8AAG' || projectSfid == 'a1l2s000000PGuDAAW' || projectSfid == 'a1l2s000000PGuIAAW' || projectSfid == 'a1l2s000000PGuNAAW' || projectSfid == 'a1l2s000000PGuSAAW'){
			if(TotalA>=4500000 || reraCarpetSqm >= 60){
				return 5;
			}
			return 1;
		}  
		
		//if (projectSfid == 'a1l2s00000000X5AAI' || projectSfid == 'a1l6F000003TXloQAG'){
		if (projectSfid == 'a1l2s00000000X5AAI' || projectSfid == 'a1l6F000003TXloQAG' || projectSfid == 'a1l2s000000XoezAAC'){
			if(TotalA>=4500000 || reraCarpetSqm >= 90){
				return 5;
			}
			return 1;
		}
		
		return currentTaxRate;
		//END Added for Project Godrej Royale Woods, bang
		
		/*if(projectSfid != 'a1l2s00000000X5AAI'){
			return currentTaxRate;
		}
		if(basicSaleprice>4500000){
			return 5;
		}
		return 1;*/
	}catch (e) {
		console.log(e);
		return 0;
	}
}


function paymentPlanOtherCharges (firstRowObj, ppMilestone){
       
       
       
       var payableTotal = 0;
       var gstTotalPP = 0;
       var amountPPTotal = 0;
       
       $.post(pageContext+"getPaymentPlanOtherCharges",{"paymentPlanSfid":$('#ppDropdown').val(), "unitSfid":$('#unitSfid').val(),"projectid":$("#projectid").val()},function(data){

             //$('#paymentPlanOtherCharges tbody').find("tr:gt(0)").remove();       
             //$('#printPPOtherCharges tbody').find("tr:gt(0)").remove();
             
             var html = '';
             var obj =JSON.parse(data);
             var previousObj = ''; 
             var finalData = '';
             var uniqueId = [];
             var uniqueNames = [];
             var uniqePPpercent = [];
             var ocPlsBsp = 0;
             
             var tokenBaseAmountPP = 0;
             var tokenGSTPP = 0;
             var tokenAmountPP = 0;
             var firstPPText = '';
             
             var objMilestone =JSON.parse(ppMilestone);
             
             /* for(i = 0; i< obj.length; i++){    
                 if(uniqueId.indexOf(obj[i].id) === -1){
                     uniqueId.push(obj[i].id);
                     uniqueNames.push(obj[i].propstrength__payment_plan_line_item_name__c);
                     uniqePPpercent.push(obj[i].propstrength__amount_percent__c);
                 }        
              } */
             
             for(i = 0; i< objMilestone.length; i++){    
                 uniqueId.push(objMilestone[i].id);
                 uniqueNames.push(objMilestone[i].nv_payment_plan_line_item_name__c);
                 uniqePPpercent.push(objMilestone[i].nv_amount_percent__c);
              }
             
             
             for(j = 0; j< uniqueId.length; j++){
                    var fixed = 0;
                    var flexible = 0;
                    var chargeAmount = 0;
                    var POCflexible = 0;
                    var POCflexible = 0;
                    var POCchargeAmount = 0;
                    var finalCA = 0;
                    var POCfinalCA = 0;
                    var taxAmount = 0;
                   
                    var gstFixed = 0;
                    var gstFixed1Per = 0;
                    var gstFixed5Per = 0;
                    
                    var gstFlexible = 0;
                    var gstFlexible1Per = 0;
                    var gstFlexible5Per = 0;
                    
                    var gstChargeAmount = 0;
                    var gstChargeAmount1Per = 0;
                    var gstChargeAmount5Per = 0;
                    
                    var gstFinal = 0;
                    var gstFinal1Per = 0;
                    var gstFinal5Per = 0;
                    
                    var gstPymtOcTotal = 0;
                    
                    var ocWithBspGst = 0;
                    
                    for(k = 0; k< obj.length; k++){
                           if(uniqueId[j] == obj[k].id) {
                                 
                                 console.log ("PP line item Name::: " + obj[k].propstrength__payment_plan_line_item_name__c);
                                 
                                 
                                 
                                 if (obj[k].other_charge_type == 'Fixed') {
                                        //console.log("Fixed Val:"+(obj[k].other_charge_percent));
                                        fixed = (parseInt(obj[k].propstrength__fixed_charge__c)*(obj[k].other_charge_percent)/100); 
                                        chargeAmount = fixed+chargeAmount;
                                        
                                        
                                        //if PMAY = TRUE >> (GST status OLD >> if status = old >> Get "old PMAY GST") OR (GST status OLD >> if status = new >> Get "new PMAY GST") 
                                        //if PMAY = FALSE >> (GST status OLD >> if status = old >> Get "old tax Record") OR (GST status NEW >> if status = old >> Get "new tax Record")
                                        
                                        
                                        if (obj[k].propstrength__completion_certificate_received__c == true) {
                                               if (obj[k].propstrength__st_on_completion_certificate__c == true) {
                                                     if (obj[k].propstrength__pmay_abatement__c == true) {
                                                            if (obj[k].propstrength__gst_status__c == 'New GST') {
                                                                   
                                                                   console.log("PP Name ::: "                     +obj[k].propstrength__payment_plan_line_item_name__c);
                                                                   console.log("Other Name ::: "           +obj[k].other_charges_name);
                                                                   
                                                                   ocWithBspGst = obj[k].propstrength__new_pmay_gst__c;
                                                                   
                                                                   console.log("PMAY NEW GST ::: " + obj[k].propstrength__new_pmay_gst__c);
                                                                   
                                                            } else if (obj[k].propstrength__gst_status__c == 'Old GST') {
                                                                   
                                                                   console.log("PP Name ::: "                     +obj[k].propstrength__payment_plan_line_item_name__c);
                                                                   console.log("Other Name ::: "           +obj[k].other_charges_name);
                                                                   
                                                                   ocWithBspGst = obj[k].propstrength__pmay_gst__c;
                                                                   
                                                                   console.log("PMAY OLD GST ::: " + obj[k].propstrength__pmay_gst__c);
                                                                   
                                                            }
                                                     } else if (obj[k].propstrength__pmay_abatement__c == false) {
                                                            if (obj[k].propstrength__gst_status__c == 'New GST') {
                                                                   
                                                                   console.log("PP Name ::: "                     +obj[k].propstrength__payment_plan_line_item_name__c);
                                                                   console.log("Other Name ::: "           +obj[k].other_charges_name);
                                                                   
                                                                   ocWithBspGst = obj[k].propstrength__new_tax_percentage__c;
                                                                   
                                                                   console.log("Tax Record NEW ::: "       + obj[k].propstrength__new_tax_percentage__c);
                                                                   
                                                            } else if (obj[k].propstrength__gst_status__c == 'Old GST') {
                                                                   
                                                                   console.log("PP Name ::: "                     +obj[k].propstrength__payment_plan_line_item_name__c);
                                                                   console.log("Other Name ::: "           +obj[k].other_charges_name);
                                                                   
                                                                   ocWithBspGst = obj[k].propstrength__tax_percentage__c;
                                                                   
                                                                   console.log("Tax Record OLD ::: "       + obj[k].propstrength__tax_percentage__c);
                                                                   
                                                            }
                                                     }
                                               }else {
                                                     ocWithBspGst = 0;
                                                     //No GST tax charges
                                               }
                                        } else {
                                               if (obj[k].propstrength__pmay_abatement__c == true) {
                                                     if (obj[k].propstrength__gst_status__c == 'New GST') {
                                                            
                                                            console.log("PP Name ::: "                 +obj[k].propstrength__payment_plan_line_item_name__c);
                                                            console.log("Other Name ::: "          +obj[k].other_charges_name);
                                                            
                                                            ocWithBspGst = obj[k].propstrength__new_pmay_gst__c;
                                                            
                                                            console.log("PMAY NEW GST ::: "       + obj[k].propstrength__new_pmay_gst__c);
                                                            
                                                     } else if (obj[k].propstrength__gst_status__c == 'Old GST') {
                                                            
                                                            console.log("PP Name ::: "                 +obj[k].propstrength__payment_plan_line_item_name__c);
                                                            console.log("Other Name ::: "          +obj[k].other_charges_name);
                                                            
                                                            ocWithBspGst = obj[k].propstrength__pmay_gst__c;
                                                            
                                                            console.log("PMAY OLD GST ::: "       + obj[k].propstrength__pmay_gst__c);
                                                            
                                                     }
                                               } else if (obj[k].propstrength__pmay_abatement__c == false) {
                                                     if (obj[k].propstrength__gst_status__c == 'New GST') {
                                                            
                                                            console.log("PP Name ::: "                 +obj[k].propstrength__payment_plan_line_item_name__c);
                                                            console.log("Other Name ::: "          +obj[k].other_charges_name);
                                                            
                                                            ocWithBspGst = obj[k].propstrength__new_tax_percentage__c;
                                                            
                                                            console.log("Tax Record NEW ::: "    + obj[k].propstrength__new_tax_percentage__c);
                                                            
                                                     } else if (obj[k].propstrength__gst_status__c == 'Old GST') {
                                                            
                                                            console.log("PP Name ::: "                 +obj[k].propstrength__payment_plan_line_item_name__c);
                                                            console.log("Other Name ::: "          +obj[k].other_charges_name);
                                                            
                                                            ocWithBspGst = obj[k].propstrength__tax_percentage__c;
                                                            
                                                            console.log("Tax Record OLD ::: "    + obj[k].propstrength__tax_percentage__c);
                                                     }
                                               }
                                        }
                                        
                                        
                                        //Added for 45lac condition - 20200610
                                        if (obj[k].propstrength__part_of_cop__c == true) { 
                                        	if (obj[k].other_charges_name != "Club Development Charges_1098" && obj[k].other_charges_name != "Club Development Charges_1085") {
                                        		gstFixed1Per = parseFloat (fixed*1/100);
                                        		gstFixed5Per = parseFloat (fixed*5/100);
                                        	} else {
                                        		gstFixed1Per = parseFloat (fixed*ocWithBspGst/100);
                                        		gstFixed5Per = parseFloat (fixed*ocWithBspGst/100);
                                        	}
                                        } else if (obj[k].propstrength__part_of_cop__c == false) {
                                        		gstFixed1Per = parseFloat (fixed*ocWithBspGst/100);
                                        		gstFixed5Per = parseFloat (fixed*ocWithBspGst/100);
                                        }
                                        //END Added for 45lac condition - 20200610
                                        
                                        
                                        if (obj[k].propstrength__sta_applicable__c == true) {
                                               //gstFixed = parseFloat ((fixed*2/3)*ocWithBspGst/100);
                                               gstFixed = parseFloat (fixed*ocWithBspGst/100);
                                        } else {
                                               gstFixed = parseFloat (fixed*ocWithBspGst/100);
                                        }
                                        
                                        //gstFixed = parseInt (fixed*obj[k].sum/100);
                                        
                                        gstChargeAmount = gstFixed+gstChargeAmount;
                                        gstChargeAmount1Per = gstFixed1Per+gstChargeAmount1Per;
                                        gstChargeAmount5Per = gstFixed5Per+gstChargeAmount5Per;
                                        
                                 } else if (obj[k].other_charge_type == 'Flexible') {
                                        flexible = (parseFloat(obj[k].propstrength__rate_per_unit_area__c)*(parseFloat($('.a6').text()))*(obj[k].other_charge_percent)/100); 
                                        chargeAmount = flexible+chargeAmount;
                                        
                                        //if PMAY = TRUE >> (GST status OLD >> if status = old >> Get "old PMAY GST") OR (GST status OLD >> if status = new >> Get "new PMAY GST") 
                                        //if PMAY = FALSE >> (GST status OLD >> if status = old >> Get "old tax Record") OR (GST status NEW >> if status = old >> Get "new tax Record")
                                        
                                        
                                        if (obj[k].propstrength__completion_certificate_received__c == true) {
                                               if (obj[k].propstrength__st_on_completion_certificate__c == true) { 
                                                     
                                                     if (obj[k].propstrength__pmay_abatement__c == true) {
                                                            if (obj[k].propstrength__gst_status__c == 'New GST') {
                                                                   
                                                                   console.log("PP Name ::: "                     +obj[k].propstrength__payment_plan_line_item_name__c);
                                                                   console.log("Other Name ::: "           +obj[k].other_charges_name);
                                                                   
                                                                   ocWithBspGst = obj[k].propstrength__new_pmay_gst__c;
                                                                   
                                                                   console.log("PMAY NEW GST ::: "        +obj[k].propstrength__new_pmay_gst__c);
                                                                   
                                                            } else if (obj[k].propstrength__gst_status__c == 'Old GST') {
                                                                   
                                                                   console.log("PP Name ::: "                     +obj[k].propstrength__payment_plan_line_item_name__c);
                                                                   console.log("Other Name ::: "           +obj[k].other_charges_name);
                                                                   
                                                                   
                                                                   ocWithBspGst = obj[k].propstrength__pmay_gst__c;
                                                                   
                                                                   console.log("PMAY OLD GST ::: " +obj[k].propstrength__pmay_gst__c);
                                                                   
                                                            }
                                                     } else if (obj[k].propstrength__pmay_abatement__c == false) {
                                                            if (obj[k].propstrength__gst_status__c == 'New GST') {
                                                                   
                                                                   console.log("PP Name ::: "                     +obj[k].propstrength__payment_plan_line_item_name__c);
                                                                   console.log("Other Name ::: "           +obj[k].other_charges_name);
                                                                   
                                                                   ocWithBspGst = obj[k].propstrength__new_tax_percentage__c;
                                                                   
                                                                   console.log("Tax Record NEW ::: "        +obj[k].propstrength__new_tax_percentage__c);
                                                                   
                                                            } else if (obj[k].propstrength__gst_status__c == 'Old GST') {
                                                                   
                                                                   console.log("PP Name ::: "                     +obj[k].propstrength__payment_plan_line_item_name__c);
                                                                   console.log("Other Name ::: "           +obj[k].other_charges_name);
                                                                   
                                                                   ocWithBspGst = obj[k].propstrength__tax_percentage__c;
                                                                   
                                                                   console.log("Tax Record OLD ::: "        +obj[k].propstrength__tax_percentage__c);
                                                                   
                                                            }
                                                     }
                                                     
                                               } else {
                                                     ocWithBspGst = 0;
                                                     //No GST tax charges
                                               }
                                        } else {
                                               if (obj[k].propstrength__pmay_abatement__c == true) {
                                                     if (obj[k].propstrength__gst_status__c == 'New GST') {
                                                            
                                                            console.log("PP Name ::: "                 +obj[k].propstrength__payment_plan_line_item_name__c);
                                                            console.log("Other Name ::: "          +obj[k].other_charges_name);
                                                            
                                                            ocWithBspGst = obj[k].propstrength__new_pmay_gst__c;
                                                            
                                                            console.log("PMAY NEW GST ::: "       +obj[k].propstrength__new_pmay_gst__c);
                                                            
                                                     } else if (obj[k].propstrength__gst_status__c == 'Old GST') {
                                                            
                                                            console.log("PP Name ::: "                 +obj[k].propstrength__payment_plan_line_item_name__c);
                                                            console.log("Other Name ::: "          +obj[k].other_charges_name);
                                                            
                                                            
                                                            ocWithBspGst = obj[k].propstrength__pmay_gst__c;
                                                            
                                                            console.log("PMAY OLD GST ::: "       +obj[k].propstrength__pmay_gst__c);
                                                            
                                                     }
                                               } else if (obj[k].propstrength__pmay_abatement__c == false) {
                                                     if (obj[k].propstrength__gst_status__c == 'New GST') {
                                                            
                                                            console.log("PP Name ::: "                 +obj[k].propstrength__payment_plan_line_item_name__c);
                                                            console.log("Other Name ::: "          +obj[k].other_charges_name);
                                                            
                                                            ocWithBspGst = obj[k].propstrength__new_tax_percentage__c;
                                                            
                                                            console.log("Tax Record NEW ::: "    +obj[k].propstrength__new_tax_percentage__c);
                                                            
                                                     } else if (obj[k].propstrength__gst_status__c == 'Old GST') {
                                                            
                                                            console.log("PP Name ::: "                 +obj[k].propstrength__payment_plan_line_item_name__c);
                                                            console.log("Other Name ::: "          +obj[k].other_charges_name);
                                                            
                                                            ocWithBspGst = obj[k].propstrength__tax_percentage__c;
                                                            
                                                            console.log("Tax Record OLD ::: "    +obj[k].propstrength__tax_percentage__c);
                                                            
                                                     }
                                               }
                                        }
                                            
                                        //Added for 45lac condition - 20200610
                                        if (obj[k].propstrength__part_of_cop__c == true) { 
                                        	if (obj[k].other_charges_name != "Club Development Charges_1098"  && obj[k].other_charges_name != "Club Development Charges_1085") {
                                        		gstFlexible1Per = parseFloat (flexible*1/100);
												gstFlexible5Per = parseFloat (flexible*5/100);
                                        	} else {
                                        		gstFlexible1Per = parseFloat (flexible*ocWithBspGst/100);
												gstFlexible5Per = parseFloat (flexible*ocWithBspGst/100);
                                        	}
                                        } else if (obj[k].propstrength__part_of_cop__c == false) {
                                        		gstFlexible1Per = parseFloat (flexible*ocWithBspGst/100);
                                        		gstFlexible5Per = parseFloat (flexible*ocWithBspGst/100);
                                        }
                                        //END Added for 45lac condition - 20200610
                                        
                                        //gstFlexible = parseInt (flexible*obj[k].sum/100);
                                        
                                        if (obj[k].propstrength__sta_applicable__c == true) {
                                               //gstFlexible = parseFloat ((flexible*2/3)*ocWithBspGst/100);
                                               gstFlexible = parseFloat (flexible*ocWithBspGst/100);
                                        } else {
                                               gstFlexible = parseFloat (flexible*ocWithBspGst/100);
                                        }
                                        
                                        gstChargeAmount = gstFlexible+gstChargeAmount;
                                        gstChargeAmount1Per = gstFlexible1Per+gstChargeAmount1Per;
                                        gstChargeAmount5Per = gstFlexible5Per+gstChargeAmount5Per;
                                 }
                                 finalCA = chargeAmount;
                                 POCfinalCA = POCchargeAmount;
                                 
                                 gstFinal = gstChargeAmount;
                                 gstFinal1Per = gstChargeAmount1Per;
                                 gstFinal5Per = gstChargeAmount5Per;
                                 
                           } else { }
                    }
                    
                    ocPlsBsp =  (($('#flatCostWithDiscount').val())*uniqePPpercent[j])/parseInt(100);
                    
                    
                    
                    //added for 45lac condition
                    if($('#projectid').val() == 'a1l2s000000PJPmAAO' || $('#projectid').val() == 'a1l6F000002X6IOQA0' || $('#projectid').val() == 'a1l2s00000003VlAAI' || $('#projectid').val() == 'a1l6F0000081xb4QAA' || $('#projectid').val() == 'a1l2s000000PGu3AAG' || $('#projectid').val() == 'a1l2s000000PGu8AAG' || $('#projectid').val() == 'a1l2s000000PGuDAAW' || $('#projectid').val() == 'a1l2s000000PGuIAAW' || $('#projectid').val() == 'a1l2s000000PGuNAAW' || $('#projectid').val() == 'a1l2s000000PGuSAAW'){
	       				if($('.salesConsiderationTotalNew').text()>=4500000  || $('#carpetSqm').text() >= 60){
	       					gstPymtOcTotal = parseFloat((gstFinal5Per) + ((ocPlsBsp)*($('#bspGSTTax').val())/100)).toFixed(2);
	       				} else {
	       					gstPymtOcTotal = parseFloat((gstFinal1Per) + ((ocPlsBsp)*($('#bspGSTTax').val())/100)).toFixed(2);
	       				}
                    } else if ($('#projectid').val() == 'a1l6F000003TXloQAG'){
                    	if($('.salesConsiderationTotalNew').text()>=4500000  || $('#carpetSqm').text() >= 90){
	       					gstPymtOcTotal = parseFloat((gstFinal5Per) + ((ocPlsBsp)*($('#bspGSTTax').val())/100)).toFixed(2);
	       				} else {
	       					gstPymtOcTotal = parseFloat((gstFinal1Per) + ((ocPlsBsp)*($('#bspGSTTax').val())/100)).toFixed(2);
	       				}
                    } else {
                    	gstPymtOcTotal = parseFloat((gstFinal) + ((ocPlsBsp)*($('#bspGSTTax').val())/100)).toFixed(2);
                    }
                    //END added for 45lac condition
                    
                    //gstPymtOcTotal = parseFloat((gstFinal) + ((ocPlsBsp)*($('#bspGSTTax').val())/100)).toFixed(2);
                    
                    
                    //gstPymtOcTotal = parseFloat((gstFinal) + ((ocPlsBsp*2/3)*($('#bspGSTTax').val())/100)).toFixed(2);
                    
                    tokenBaseAmountPP = 0;
                    tokenGSTPP =  0;
                    tokenAmountPP = 0;
                    
                    firstPPText = '';
                    
                    /*if (j == 0 && firstRowObj != 'null') {
                           tokenBaseAmountPP = parseFloat($('#tokenBaseAmount').text());
                           tokenGSTPP =  parseFloat($('#tokenGST').text());
                           tokenAmountPP = parseFloat($('#tokenAmount').text());
                           firstPPText = ' - Less '+$('#firstPPTextRow').text();
                    }*/
                    
                    
                    if (j == 1 && firstRowObj != 'null') {
                        tokenBaseAmountPP = parseFloat($('#tokenBaseAmount').text());
                        tokenGSTPP =  parseFloat($('#tokenGST').text());
                        tokenAmountPP = parseFloat($('#tokenAmount').text());
                        firstPPText = ' - Less '+$('#firstPPTextRow').text();
                    } else {
                    	tokenBaseAmountPP = 0;
                        tokenGSTPP =  0;
                        tokenAmountPP = 0;
                        firstPPText = "";
                    }
                    
                    
                    var ppFirstRowFR = '';
                    var firstPPTextRowFR = '';
                    var tokenBaseAmountFR = '';
                    var tokenGSTFR = '';
                    var tokenAmountFR = '';
                    
                    if (j == 0){
                    	ppFirstRowFR = 'ppFirstRow';
                        firstPPTextRowFR = 'firstPPTextRow';
                        tokenBaseAmountFR = 'tokenBaseAmount';
                        tokenGSTFR = 'tokenGST';
                        tokenAmountFR = 'tokenAmount';
                    }
                    
                    console.log ("PP Name ::: " + uniqueNames[j] +"--------"+ "PP % ::: " + uniqePPpercent[j]);
                    
                    
                    
                    if (j == 0 && firstRowObj != "null") {
                    	/*html += "<tr id= '"+ppFirstRowFR+"'>" +
		                        "<td id= '"+firstPPTextRowFR+"'>"+firstPPText+" </td>" +
		                        "<td class='txtRight' style='text-align:right;'>"+ uniqePPpercent[j] +" % </td>" +
		                        "<td id= '"+tokenBaseAmountFR+"' class='txtRight' style='text-align:right;'>" + parseFloat(tokenBaseAmountPP).toFixed(2) +  "</td>  " +
		                        "<td id= '"+tokenGSTFR+"' class='txtRight' style='text-align:right;'>" +parseFloat(tokenGSTPP).toFixed(2)+  "</td> " +
		                        "<td id= '"+tokenAmountFR+"' class='txtRight' id='payableToTotal"+i+"' style='text-align:right;'> "+parseFloat(tokenAmountPP).toFixed(2)+" </td> " +
		                 "</tr>";
		                 */
                    } else {
                    	html += "<tr id= '"+ppFirstRowFR+"'>" +
		                        "<td id= '"+firstPPTextRowFR+"'  >"+ uniqueNames[j] + firstPPText+" </td>" +
		                        "<td class='txtRight' style='text-align:right;'>"+ uniqePPpercent[j] +" % </td>" +
		                        "<td id= '"+tokenBaseAmountFR+"' class='txtRight' style='text-align:right;'>" + parseFloat(finalCA+ocPlsBsp-tokenBaseAmountPP).toFixed(2) +  "</td>  " +
		                        "<td id= '"+tokenGSTFR+"' class='txtRight' style='text-align:right;'>" +parseFloat(gstPymtOcTotal-tokenGSTPP).toFixed(2)+  "</td> " +
		                        "<td id= '"+tokenAmountFR+"' class='txtRight' id='payableToTotal"+i+"' style='text-align:right;'> "+parseFloat(finalCA+ocPlsBsp+parseFloat(gstPymtOcTotal)-tokenAmountPP).toFixed(2)+" </td> " +
		                 "</tr>";
                    }
                    
                    
                    
                    
                    
                    amountPPTotal = parseFloat(finalCA+ocPlsBsp)+parseFloat(amountPPTotal);
                    payableTotal =        parseFloat(finalCA+ocPlsBsp+parseFloat(gstPymtOcTotal))+parseFloat(payableTotal);
                    gstTotalPP = parseFloat(gstPymtOcTotal)+parseFloat(gstTotalPP);
                    
                    
                    finalCA = 0;
                    POCfinalCA = 0;
                    gstFinal = 0;
             }  
             
             $("#paymentPlanOtherCharges tbody").append (html);
             $("#printPPOtherCharges tbody").append (html);
             
       }).done(function(){
             var html = '';
             
             
             if ($('#projectid').val() == 'a1l6F000002X6IOQA0') {
            	 //html+="<tr class='payableStampAndReg'><td colspan='4'>Stamp Duty & Registration Charges (Payable within 21 days from booking)</td><td class='txtRight' style='text-align:right'>"+ parseFloat(parseFloat($('#stamp_duty').text())+parseFloat($('#registrationCharges').text())).toFixed(2)+"</td></tr>";
            	 html+="<tr class='payableStampAndReg'><td colspan='4'>SDR Charges (Payable within 21 days from booking)</td><td class='txtRight' style='text-align:right'>"+ parseFloat(parseFloat($('#stamp_duty').text())+parseFloat($('#registrationCharges').text())).toFixed(2)+"</td></tr>";
             } else {
            	 html+="<tr class='payableStampAndReg'><td colspan='4'>Payable at the time of registration</td><td class='txtRight' style='text-align:right'>"+ parseFloat(parseFloat($('#stamp_duty').text())+parseFloat($('#registrationCharges').text())).toFixed(2)+"</td></tr>";
             }
             
             //html+="<tr><td colspan='4'>Goods & Service Tax (GST)</td><td class='txtRight'>"+$('#registrationCharges').text()+"</td></tr>";
             
             html += "<tr>" +
                    "<th colspan='2' class='txtCenter' style='text-align:center;'>Total</th>" +
                    //"<td> </td>" +
                    //"<td>  </td>  " +
                    "<th>"+parseFloat(amountPPTotal).toFixed(2)+"</th> " +
                    "<th>"+parseFloat(gstTotalPP).toFixed(2)+"</th> " +
                    "<th id='payableToTotal"+i+"' style='text-align:right;'>"+parseFloat(payableTotal+parseFloat($('#stamp_duty').text())+parseFloat($('#registrationCharges').text())).toFixed(2)+"</th> " +
             "</tr>";
             
             
             $('#csFinalAmountInput').val(parseFloat(payableTotal+parseFloat($('#stamp_duty').text())+parseFloat($('#registrationCharges').text())).toFixed(2));
             
             
             $("#paymentPlanOtherCharges tbody").append (html);
             $("#printPPOtherCharges tbody").append (html);
             $("#paymentPlanOtherCharges").next('.tableNoRec').hide();
             $("#printPPOtherCharges").next('.tableNoRec').hide();
            
             if ($('#schemeInput').val() != "schemeNotValid"){
            	 $('#printableArea').show();
                 $('#printBtnFloat').show();
                 
                 if ($("#getCSData").data('source') == "SALES") {
                	 $('#emailBtnFloat').show();
                 } 
                 	
             } else {
            	 $('#printableArea').hide();
                 $('#printBtnFloat').hide();
                 $('#emailBtnFloat').hide();
             }
             
             
             /*if ( $('#projectId').val() == 'a1l6F0000047Q1xQAE' && $('#stamp_duty').text() == 0 && $('#registrationCharges').text() == 0) {
                   $('.payableStampAndReg').remove();
             }*/
             if ($('#stamp_duty').text() == 0 && $('#registrationCharges').text() == 0) {
            	 $('.payableStampAndReg').remove();
             }
             
             
             if ($('#projectId').val() == 'a1l2s00000000pEAAQ' || $('#projectId').val() == 'a1l6F000004LVk8QAG' || $('#region__c').val() == "Noida" || $('#region__c').val() == "Gurgaon"  ||  $('#region__c').val() == "New Delhi" ) {
            	 $('.payableStampAndReg').remove();
             } 
             
             $('#getCSData span').html('');   
       });    
}


//function firstMilstone(ppMilestone) {
function firstMilstone() {
       
       $('#paymentPlanOtherCharges tbody').find("tr:gt(0)").remove();     
       $('#printPPOtherCharges tbody').find("tr:gt(0)").remove();
       
       $.post(pageContext+"getFirstMilstone",{"paymentPlanSfid":$('#ppDropdown').val()},function(data){
             
             var obj =JSON.parse(data);
             var html = '';
             
             var tokenBaseAmount = 0;
             var tokenGST = 0;
             var tokenAmount = 0;
             
             if (obj != null) {
                    for(i = 0; i< obj.length; i++){    
                          
                           tokenAmount = parseFloat(obj[i].propstrength__amount__c).toFixed(2);
                           
                           tokenBaseAmount = parseFloat((tokenAmount*100)/(100+parseFloat($('#bspGSTTax').val()))).toFixed(2);
                           
                           tokenGST = parseFloat(tokenAmount - tokenBaseAmount).toFixed(2);
                           
                           html += '<tr id="ppFirstRow"><td id="firstPPTextRow">'+obj[i].propstrength__payment_plan_line_item_name__c+'</td> <td></td> <td id="tokenBaseAmount" style="text-align:right;">'+tokenBaseAmount+'</td> <td id="tokenGST" style="text-align:right;">'+tokenGST+'</td> <td id="tokenAmount" style="text-align:right;">'+tokenAmount+'</td></tr>'
                           
                           $("#paymentPlanOtherCharges").append(html);
                           $("#printPPOtherCharges").append(html);
                    }
             } 
             
       }).done(function(obj){
    	   paymentPlanMilestone(obj);  
    	   //paymentPlanOtherCharges(obj, ppMilestone);
       });
}


function paymentPlanMilestone(obj) {
    
    $.post(pageContext+"getPaymentPlanMilestone",{"paymentPlanSfid":$('#ppDropdown').val()},function(data){
          
          var ppMilestone =JSON.parse(data);
          var html = '';
          
          if (ppMilestone != null) {
                 for(i = 0; i< ppMilestone.length; i++){    
                	 
                	 ppMilestone[i].nv_payment_plan_line_item_name__c;
                	 
                 }
          } 
          
    }).done(function(ppMilestone){
    	//firstMilstone(ppMilestone);
    	paymentPlanOtherCharges(obj, ppMilestone);
    });
}


function printDiv(divName) {
     var printContents = document.getElementById(divName).innerHTML;
     var originalContents = document.body.innerHTML;
     document.body.innerHTML = printContents;
     window.print();
     document.body.innerHTML = originalContents;
     return (false);
}

// Not in use 20191025
function getUnitDtl () {
       var bspVal = '';
       var scheme_type = '';
       
       if($('#getPln').val() == 'other') {
             bspVal = $("#newPlanVal").val(); 
             scheme_type = $('#getPln').val();
       } else {
             bspVal = $("#getPln").val(); 
             scheme_type = $("#getPln :selected").text();
       }
       
       $.post(pageContext+"getUnitDtl",{"BSPrate":$('#BSPrate').val(), "bspVal":bspVal, "scheme_type":scheme_type, "customerName":$('.customerName').text(),"loginUserName":$('#loginUserName').val(), "loginUserId":$('#loginUserId').val(), "loginUserEmail":$('#loginUserEmail').val(), "contactNo":$('#getContact').val(), "carpetSqm":$('#carpetSqm').text(), "balTerSqm":$('#balTerSqm').text(), "carParks":$('#carParks').text(), "unitTvalGet":$('#unitTval').text(), "towerTvalGet":$('#towerTval').text(), "floorTvalGet":$('#floorTval').text(), "typologyTvalGet":$('#typologyTval').text(), "CostofCarpetGet":$('#CostofCarpet').text(), "CostofExcBalconyGet":$('#CostofExcBalcony').text(), "PropCostGet":$('#PropCost').text()},function(data){                     
       
       }).done(function(response){
             $("#timeid").val(response);
             //console.log ("response 123 456 ::: " + response);
             getExtraCharges (response);             
    });
}
// END Not in use 20191025
function getExtraCharges (timeid) {
       var table = $('#newOtherCharges2').tableToJSON();
       
       $.post(pageContext+"getExtraCharges",{"timeid":timeid,"contactNo":$('#getContact').val(), "caDtl" : JSON.stringify(table)},function(data){                         
       }).done(function(){
             getPaymentSchedule (timeid)
    });
}

function getPaymentSchedule (timeid) {
       var table = $('#paymentSchedule').tableToJSON();
       $.post(pageContext+"getPaymentSchedule",{"timeid":timeid,"contactNo":$('#getContact').val(), "caDtl" : JSON.stringify(table)},function(data){                         
       }).done(function(){
             $('#viewPDF').attr("href", pdfSrc+timeid);
             $('#viewPDF').removeClass('vpdfDis');
             $('#viewPDF .commonLoad').hide();
       });
}

function test123 () { }

function getRqstForAdmin (){
       $.post(pageContext+"adminRqst",{"userId":"A202"},function(data){                       
       }).done(function(data){
             var obj =JSON.parse(data);
             var test = '';
             for (var i = 0; obj.length > i; i++) {
                    test += "<tr> <td>"+obj[i].timeid+"</td> <td></td> <td>"+obj[i].user_contact+"</td> <td></td> <td></td> <td></td> <td>Approve/ Reject</td> </tr>";
             };
             $("#rqstFromAdmin").append (test);
             //console.log (obj);             
    });
}

$('#getRqstForAdmin').click (function () {
       getRqstForAdmin ()
});

$('#updateCRM').click (function () {
       	
		var duplicate =  checkDuplicate("csPtCol");
		
		if (duplicate){
	
			var validate=checkValidationOnSubmit('costsheet');//csPtContainer
			if(validate) {
	             if ($('.paymentCScheck:checked').length > 0) {
	                    
	                    
	                    var sizeValidate = 0;
	                    $("#csPtCol .csPtFileSize").each(function () {
	                           if ($(this).find('.panAttach').get(0).files.length != 0){ 
	                                 if ($(this).find('.panAttach')[0].files[0].size < 5242880) {
	                                 } else {
	                                        sizeValidate++;
	                                 }
	                           }      
	                           
	                           if ($(this).find('.receiptAttach').get(0).files.length != 0){  
	                                 if ($(this).find('.receiptAttach')[0].files[0].size < 5242880 ) {
	                                 } else {
	                                        sizeValidate++;
	                                 }
	                           }
	                    });
	                    
	                    if (sizeValidate < 1) {
	                           updateBSP ($('#timeid').val());
	                    } else {
	                           swal({
	                                 title: "File size must be less than 5 MB",
	                               text: "",
	                               type: "error",
	                           });
	                    }
	             
	             } else {
	                    swal({
	                           title: "Select or Add Payment Details",
	                        text: "",
	                       // timer: 4000,
	                        type: "error",
	                    });
	             }
	       }  else {
	    	   $('#costsheetError').html("<div class='alert alert-danger' style='margin-bottom:0 !important;'>" +
						" Kindly fill the <strong>required</strong> fields. " +
					" </div>");
	             $('#costsheetError').show();
	       }    
       
		} else {
			$('#costsheetError').html("<div class='alert alert-danger' style='margin-bottom:0 !important;'>" +
						" Transaction ID cannot be <strong>duplicate</strong>. " +
					" </div>");
			$('#costsheetError').show();
			
			return;
		}
});

function adminUpdateBSP (e,timeid, disVal){
       $('#updateBtnCol button').prop("disabled", true);
       $.post(pageContext+"updateBSP",{"bspDis":disVal,"token":$('#token').val(),"projectsfid":$('#projectsfid').val(),"enquirysfid":$('#enquirysfid').val(),"primarycontactsfid":$('#primarycontactsfid').val(), "sentToCrmYN":"Y", "timeid":timeid
              ,"propid":$('#unitSfid').val(),"ppid":$('#paymentPlanChangeID').val()},function(data){                           
       }).done(function(data){
             swal({
                    title: "Successfully Submitted",
                 text: "",
                 timer: 2000,
                 type: "success",
             });
    });
}

function updateBSP (timeid) {
       
       $('.creatOfferBtnCommon span.spanLoad').html('<i class="fa fa-spinner fa-spin" style="color:#fff !important"></i>');
       
       var floorName = '';
       
       if ($('#floorTval').text() == ''){
    	   floorName = 'NoFloor';
       } else {
    	   floorName = $('#floorTval').text();
       }
       
       var generateFrom = 'Offered';
       var csPath =    $('#region__c').val() +'/'+  $('#marketingProjectName').val() + '/' + $('#towerTval').text() + '/' + floorName + '/' + $('#unitTval').text() + '/' + generateFrom+"-"+$('#enquirysfid').val()+'-'+$('#unitSfid').val()+'-'+$('#projectId').val();
       
       var bsp_offer = $("#scOtherChrgAmount0").text();
       var saleble_offer = $('.a6').text();
       var original_bsp = $('.a3_bsp').text();
       var discount_Value = parseFloat((parseFloat(original_bsp)*parseFloat(saleble_offer))-(parseFloat(bsp_offer))).toFixed(2);
       var bspVal = "";
       var isOthers=false;
       
       if($('#getPln').val() == 'other') {
    	   bspVal = $('#getPln').find('option:selected').attr("data-valuerate");
           isOthers=true;
       } else {
           bspVal =$('#getPln').find('option:selected').attr("data-valuerate");
       }
       
       var offerthrough="";
       var brokerAccountSfid="";
      
       if ($('#channelPartnerSfidCS').val() === "") {
    	   
    	   if($('#walkInSource').val()==="Referral" || $('#walkInSource').val()==="Existing Customer")
    		   offerthrough = "Referral";
    	   else if($('#walkInSource').val()==="Corporate")
    		   offerthrough = "Corporate";
    	   else if($('#walkInSource').val()==="Godrej Employee")
    		   offerthrough = "Employee";
    	   else
    		   offerthrough = "Direct";
    	   
    	   //$('#walkInSource').val()
    	   brokerAccountSfid ="";
       } else {
    	   offerthrough = "Broker";
    	   brokerAccountSfid=$('#channelPartnerSfidCS').val();
       }
       
       var enqsfid = "";   

       $('#updateBtnCol button').prop("disabled", true);
       
       var row = $(this).closest('csPtCol').find('tr:first');
       var prepaymentAmount ="",bankname="",trxdate="",trxno="",paymentmode="";
       $("#csPtCol .csPtDataRow").each(function () {
             if ($(this).find('.paymentCScheck').is(':checked')) { 
                    prepaymentAmount = $(this).find('.csPtTransactionAmount').val();
                    bankname = $(this).find('.csPtBankName').val();
                    trxdate = $(this).find('.csPtTransactionDate').val();
                    trxno = $(this).find('.csPtTransactionId').val();
                    paymentmode = $(this).find('.csPtDropDown').val();
                    return false;
             }      
       });
       
       var tdsPaidBy = '';
       var bspTaxGST = 0;
       var salesConsiderationTotal = 0;
       var reraCarpetAreaSqm = 0;
       
       if ($('#tdsPaidByDD').val() != '') {
             tdsPaidBy = $('#tdsPaidByDD').val();
       }
       
       if ($('#bspGSTTax').val() != null && $('#bspGSTTax').val().trim() != '') {
    	   bspTaxGST = $('#bspGSTTax').val();
       } 
       
       if ($('#salesConsiderationTotal').text() != null && $('#salesConsiderationTotal').text().trim() != '') {
    	   salesConsiderationTotal = $('#salesConsiderationTotal').text();
       }
       
       if ($('#carpetSqm').text() != null && $('#carpetSqm').text().trim() != '') {
    	   reraCarpetAreaSqm = $('#carpetSqm').text();
       }
       
       
       var price = $("#salesConsiderationTotal").text();
       var priceWithTax = $("#salesConsiderationTotal").text();
       var plannedPayment = $("#tokenBaseAmount").text();
       var plannedPaymentWithTax = $("#tokenAmount").text();
       var tokenTax= $("#tokenGST").text();
       var paymentDetails = getPaymentDetails();
       $.post(pageContext+"updateBSP",{"salesConsiderationTotal":salesConsiderationTotal, "bspTaxGST":bspTaxGST, "bspDis":bspVal,"token":$('#token').val(),
              "projectsfid":$('#projectsfid').val(),"enquirysfid":$('#enquirysfid').val(),"primarycontactsfid":$('#primarycontactsfid').val(), "sentToCrmYN":"Y", "timeid":"0"
              ,"propid":$('#unitSfid').val(),"ppid":$('#paymentPlanChangeID').val(),"offerthrough":offerthrough,"brokersfid":brokerAccountSfid
              ,"discount_Value":discount_Value,"balance_amnt":$('#balance_amnt').val(),"balance_amnt_description":$('#balance_amnt_description').val()
              ,"car_park_type":$('#carParkType').find('option:selected').attr("data-name"),"scheme_rate":$('#getPln').find('option:selected').attr("data-valuerate"),"scheme_name":$("#getPln :selected").text(),"userid":$('#userid').val(),"enquiry_name":$('#enquiry_name').val() 
              ,"costsheet_commitment":$('#costsheet_commitment').val(),"prepaymentamt":prepaymentAmount,"bankname":bankname,"trxdate":trxdate,"trxno":trxno,"paymentmode":paymentmode, "tdsPaidBy":tdsPaidBy,"isOthers":isOthers, "costsheet_path": csPath, "cs_final_amount":$('#csFinalAmountInput').val(),"bankGL":$('#towerBankGLCode').val() 
       ,"paymentDetails": paymentDetails,"price":convertToNumber(price),"priceWithTax":convertToNumber(priceWithTax)
       ,"plannedPayment":convertToNumber(plannedPayment),"plannedPaymentWithTax":convertToNumber(plannedPaymentWithTax)
       ,"tokenTax":convertToNumber(tokenTax), "reraCarpetAreaSqm":reraCarpetAreaSqm},
       function(data){                       
       }).done(function(data){
    	   var offerJson = JSON.parse(data);
    	   if (offerJson.offer_successMsg == "errorNoUnit101") {
    		   $('#updateBtnCol button').prop("disabled", false);
    		   $('.creatOfferBtnCommon span.spanLoad').html('');  
    		   
    		   swal({
	   				title: "This unit is no longer available please select another unit.",
	   				text: "",
	   				type: "warning",
	   				allowOutsideClick: true,
	   				showConfirmButton: true
	   			});
    	   } else if (offerJson.offer_successMsg == "errorUnitInactive102") {
    		   $('#updateBtnCol button').prop("disabled", false);
    		   $('.creatOfferBtnCommon span.spanLoad').html('');  
    		   
    		   swal({
	   				title: "Inventory is not activated.",
	   				text: "",
	   				type: "warning",
	   				allowOutsideClick: true,
	   				showConfirmButton: true
	   			});
    	   } else if (offerJson.offer_successMsg == "errorInCode103") {
    		   $('#updateBtnCol button').prop("disabled", false);
    		   $('.creatOfferBtnCommon span.spanLoad').html('');  
    		   
    		   swal({

	   				//title: "There is some technical problem, please try again.",
    			   	title: "There was problem in creating offer at this time. Please try again by clicking create offer.",

	   				text: ""+offerJson.apiError,
	   				type: "warning",
	   				allowOutsideClick: true,
	   				showConfirmButton: true
	   			});
    	   } else if (offerJson.offer_successMsg == "successOfferCreate101") {
    	   
    		   swal({
	   				title: "Please wait, loading the Cost Sheet ...",
	   				text: "",
	   				type: "success",
	   				allowOutsideClick: false,
	   				showConfirmButton: false
	   			});
	   			//Get for KYC Status
    		   $.post(pageContext+"getKYCStatus",{"enquiryName":$("#enquiry_name").val(),"projectid":$('#projectId').val()},function(data){                       
    		        
    		    }).done(function(data){
    		          var obj =JSON.parse(data);
    		          var kycStatus="";
    		          if(obj!=null)
    		        	  {
    		        	  	kycStatus = obj.issubmitted;
    		        	  }
    		          if(kycStatus!="Y")
	       			   {
	   	   				generateKYCLinkViaOffer(event,this,'N',offerJson.offer_sfid,tdsPaidBy);
	       			   }
    		    });
    		   
    		   
	       	   
	            printPdfData(generateFrom);
	            
	   			/*if (data != '0' || data != '' || data != 'undefined' || data != null || data != 'null') {
	   				console.log("Offer SFID after API Submitted:-"+data)
	   				console.log("Offer SFID after API Offer ID:-"+data.offer_sfid)
	   				var offerJson = JSON.parse(data);
	   				csPymtData (offerJson);
	   			}*/
	             
	            if (offerJson.offer_successMsg == 'successOfferCreate101') {
	   				console.log("Offer SFID after API Submitted:-"+data)
	   				console.log("Offer SFID after API Offer ID:-"+data.offer_sfid)
	   				//var offerJson = JSON.parse(data);
	   				csPymtData (offerJson);
	   				
	   			}
	            /*var enq=$("#enquiry_name").val();
				updateTDSEOIForm(enq,tdsPaidBy);*/
	            var url=$("#contextPath").val();
	            
	            getDealDone();
	                
	            $('.creatOfferBtnCommon span.spanLoad').html('');
    	   }else{
    		   $('#updateBtnCol button').prop("disabled", false);
    		   $('.creatOfferBtnCommon span.spanLoad').html('');  
    		   
    		   swal({

	   				//title: "There is some technical problem, please try again.",
    			   	title: offerJson.offer_successMsg,

	   				text: "",
	   				type: "warning",
	   				allowOutsideClick: true,
	   				showConfirmButton: true
	   			});
    	   }
    	   
    }).fail(function(xhr, status, error) {
      
    	$('#updateBtnCol button').prop("disabled", false);
    	
    	swal({

			//title: "There is some technical problem, please try again.",
    		title: "There was problem in creating offer at this time. Please try again by clicking create offer.",

			text: "",
			timer: 8000,
			type: "warning",
        });
       $('.creatOfferBtnCommon span.spanLoad').html('');
       
    });
       
}


function getPaymentDetails(){
    var arrayData = [];
    $("#csPtCol .csPtDataRow").each(function () {
       
          if ($(this).find('.paymentCScheck').is(':checked')) {
                 var csPtData = {};
              csPtData.enq_sfid = $('#enquirysfid').val();
              csPtData.payment_type = $(this).find('.csPtDropDown').val();
              csPtData.bank_name = $(this).find('.csPtBankName').val();
              csPtData.branch = $(this).find('.csPtBranch').val();
              csPtData.transaction_id = $(this).find('.csPtTransactionId').val();
              csPtData.transaction_amount = $(this).find('.csPtTransactionAmount').val();
              csPtData.description = $(this).find('.csPtDescription').val();
              csPtData.total_amount = $('#csPtGrandtotal').text().trim();
              csPtData.bankGL=$('#towerBankGLCode').val();
              csPtData.userid = $('#userid').val();
              
              csPtData.transaction_date_string = $(this).find('.csPtTransactionDate').val();
              
              csPtData.gpl_cs_eoi_payment_details_id = $(this).find('.gpl_cs_payment_details_id').val();
              
              
              if($(this).find('.panAttach').val() != "" ||  $(this).find('.panAttach').attr('data-fileName') != "" &&  $(this).find('.panAttach').attr('data-fileName') != undefined) {
                 if($(this).find('.panAttach').attr('name') == "panAttach") {
                       csPtData.pan_attach = $(this).find('.panAttach').attr('data-fileName');
                 } else {
                       csPtData.pan_attach = k+"PAN_"+$(this).find('.panAttach')[0].files[0].name;
                 }
              }else {
                 csPtData.pan_attach ="";
              }
              
              if ($(this).find('.receiptAttach').val() != "" || $(this).find('.receiptAttach').attr('data-fileName') != "" && $(this).find('.receiptAttach').attr('data-fileName') != undefined) {
                 if($(this).find('.receiptAttach').attr('name') == "receiptAttach") {
                       csPtData.cheque_attach = $(this).find('.receiptAttach').attr('data-fileName');
                 } else {
                       csPtData.cheque_attach = k+"Receipt_"+$(this).find('.receiptAttach')[0].files[0].name;
                 }
              }else {
                 csPtData.cheque_attach ="";
              }
          
              
              arrayData.push(csPtData);
              k++
          }
    });

    return JSON.stringify(arrayData);
}

function convertToNumber(value){
	var number = Number(value);
	if(number=='NaN'){
		return 0;
	}
	return number;
}


$('#snedApproval').click (function () {
       sendForApproval ();
});

function otpStatus () {
       
       var otpInput = $('#otpInput').val();
       
       if (otpInput.length >= 6) {
             $.post(pageContext+"otpOCValidate",{"OTPValidate": $('#otpInput').val(), "herokuEnqId": $('.enquiryId').val()},function(data){                           
                    
             }).done(function(data){
                    if (data != '') {
                           if($('#otpInput').val() == data) {
                                 $('#otpStatusText').html('<span style="color: #008424;">Valid OTP</span>');
                                 $('#snedApproval').show();
                           }
                    }
           });
       }else {
             $('#otpStatusText').html('<span style="color: #d10000;">Invalid OTP</span>');
             $('#snedApproval').hide();
       }
}

function getCarParkCharges () {
       $.post(pageContext+"getCarParkCharges",{"parkType": $('#carParkType').val(), "projectSFID": $('#projectId').val()},function(data){                           
       }).done(function(data){
             var obj =JSON.parse(data);
             
             if (obj != null) {
                    $("#carParkAmount").val(obj.amount);
             } else {
                    $("#carParkAmount").val(0);
	                if ($('#carParkType').val() != -1){    
                    	swal({
	                    	title: "Car park charge amount not maintained against the selected option, under 'Car Park Type' field",
		          			text: "",
		          			//timer: 8000,
		          			type: "warning",
		                });
	                }	
             }
    });
}

function sendForApproval () {
       $.post(pageContext+"otpOCValidate",{"OTPValidate": $('#otpInput').val(), "herokuEnqId": $('.enquiryId').val()},function(data){                    
              
       }).done(function(data){
             if (data == '0' || data == '' || data == 'undefined' || data == null || data == 'null') {
                   // alert ("Invalid OTP ::: " + data);
             } else {
                  //  alert ("Valid OTP ::: " + data);
                    
                    
                    
                    
                    
                    
                    
                    
                    var validate=checkValidationOnSubmit('costsheet'); //csPtContainer
                    if(validate) {
                           if ($('.paymentCScheck:checked').length > 0) { 
                                 
                                 var sizeValidate = 0;
                                 $("#csPtCol .csPtFileSize").each(function () {
                                        /*if ($(this).find('.panAttach')[0].files[0].size < 5242880 && $(this).find('.receiptAttach')[0].files[0].size < 5242880 ) {
                                        } else {
                                               sizeValidate++;
                                        }*/
                                        
                                        if ($(this).find('.panAttach').get(0).files.length != 0){      
                                               if ($(this).find('.panAttach')[0].files[0].size < 5242880) {
                                               } else {
                                                     sizeValidate++;
                                               }
                                        }      
                                        
                                        if ($(this).find('.receiptAttach').get(0).files.length != 0){  
                                               if ($(this).find('.receiptAttach')[0].files[0].size < 5242880 ) {
                                               } else {
                                                     sizeValidate++;
                                               }
                                        }
                                        
                                        
                                        
                                 });
                                 
                                 if (sizeValidate < 1) {
                                        updateBSP("0");
                                 } else {
                                        swal({
                                               title: "File size must be less than 5 MB",
                                            text: "",
                                            type: "error",
                                        });
                                 }
                           
                           } else {
                                 swal({
                                        title: "Select at least one payment method",
                                     text: "",
                                     // timer: 4000,
                                     type: "error",
                                 });
                           }      
                    }  else {
                           $('#costsheetError').show();
                    }
             }
    });
}

function otpRequestOC(){ 
       $('#otpInput').css("display","inline");
       $('#resendOtpOC').show();
       $('#sendOTPBtn').hide();
             
       /*var newPlanVal = 0;*/
       
       /*if ($('#newPlanVal').val() != ''){
             newPlanVal = $('#newPlanVal').val();
       }*/
       
       $("#offerOPTInfo").empty();

       $.post(pageContext+"sendForApproval", {
	        "actionAR":"Y", 
	        "timeid":$("#timeid").val(),
	        "selable":$('.a6').text(),
	        "otherAmount":$('#finalDiscountValue').val(),
	        "UnitName":$('#unitTval').text(),
	        "towerName":$('#towerTval').text(),
	        "projectsfid":$('#projectSfid').val(),
	        "herokuEnqId":$('.enquiryId').val(),
	        "userName":$('#username').val().split(' ')[0], /*Added By Satheesh K - Date : 10-06-2020 - Property Name Added on Cost sheet Page*/
	        "salesConA":$('.salesConsiderationTotalNew').text(),
	        "projectname":$("#projectname").val(),
	        "propertyname":$(".unit_property_name").text()
	        
	        },function(data){                       
	        
	        }).done(function(data){
	        	if (data != null && data != ""){
	        		var obj =JSON.parse(data);
		            var html = '';
		            for (var i = 0; obj.length > i; i++) {
		                 html += "<div> OTP sent to "+obj[i].user_name+ " - "  +obj[i].mobileNo+"</div>";
		            };
		             
		            $("#offerOPTInfo").append(html);
	        	}
	        });
}

function getRqstForSales (){
       $.post(pageContext+"salesRqst",{"userId":"A202"},function(data){                       
             
       }).done(function(data){
             $("#salesRqst tbody").empty();
             
             var obj =JSON.parse(data);
             var html = '';
             for (var i = 0; obj.length > i; i++) {
                    
                    var actionCol = "";
                    var managerStatusCol = "";
                    var disVal = '';
                    var discount_val = '';
                    
                    var scheme_type = '';
                    
                    if (obj[i].scheme_type == undefined){
                           scheme_type = '';
                    }else {
                           scheme_type = obj[i].scheme_type;
                    }
                    
                    
                    if (obj[i].discount_val == undefined) {
                           discount_val = '';
                           disVal = '0';
                    } else {
                           disVal = obj[i].discount_val;
                           discount_val = obj[i].discount_val;
                    }
                    
                    //alert("obj[i].senttocrm ::: " + obj[i].senttocrm);
                    
                    if (obj[i].admin_status == 'A') {
                           actionCol = "<td><button class='btn btn-sm btn-success' onclick='adminUpdateBSP(this, "+obj[i].timeid+","+disVal+")'>Update BSP in eCRM</button></td>";
                           managerStatusCol = "<td>Approved</td>";
                    }else if (obj[i].admin_status == 'R'){
                           actionCol = "<td></td>";
                           managerStatusCol = "<td>Rejected</td>";
                    }else {
                           actionCol = "<td></td>";
                           managerStatusCol = "<td></td>";
                    }
                    
                    if (obj[i].senttocrm == 'Y'){
                           //alert ("1234564654564 ZZZ");
                           actionCol = "<td></td>";
                    } 
                    
                    
                    html += "<tr> <td class='scheme_type'>"+scheme_type+"</td> <td class='discount_val'>"+discount_val+"</td> <td>"+obj[i].timeid+"</td> <td>"+obj[i].customer_name+"</td> <td>"+obj[i].user_contact+"</td> <td>"+obj[i].createdby+"</td> <td>"+obj[i].created+"</td> <td><a href='http://localhost:8082/UserEnqury/Costsheet?name="+obj[i].timeid+"'>View</a></td> "+managerStatusCol+" "+actionCol+"</tr>";
             };
             $("#salesRqst tbody").append (html);           
    });      
}











$("#testSubmit").click(function (){
              /*$.post(pageContext+"zzExist",{"zzsfid":$('#testSFID').val(),"property_on_hold":"t","crm_user":$('#ZZCRMUser').val()},function(data){                           
                    
             }).done(function(dataCheck){
                    
                    alert ("dataCheck ::: " + dataCheck);
                    
                    //
                    if ( dataCheck == '0' || dataCheck == '' || dataCheck == 'undefined' || dataCheck == null || dataCheck == 'null' ) {
                           $('#testSFID').val();
                                  $.post(pageContext+"/zzholdTesting",{"crm_user":$('#ZZCRMUser').val(), "testSFID":$('#testSFID').val(), "property_on_hold":"t"},function(data){                     
                                 }).done(function(data){
                        });      
                    }else {
                           alert ("sorry this unit no longer available");
                    }
                    console.log (dataCheck);
                    //
           });      */
             
             
             $('#mainLoad').show();
             
             
             var myKeyVals = {
                           "zzsfid" : $('#testSFID').val(),
                           "property_on_hold" : "t",
                           "crm_user" : $('#ZZCRMUser').val()
                    }
             $.ajax({
                    type : 'POST',
                    url : pageContext+"zzExist",
                    data : myKeyVals,
                    dataType : "json",
                    success : function(resultData) {
                           
                    },
                    async:true
             }).done(function(dataCheck){
                    $('#mainLoad').hide();
                    //alert ("dataCheck 333 ::: " + dataCheck);
           });   
             
});




paymentPlanDropdown ();
getSchemeSource(); 
carparkTypeMstList();

function paymentPlanDropdown (){
       $('#ppDropdown').empty();
       
       var urlpayemntPlan = pageContext+"getpaymentPlanListData?projectcode="+$("#projectId").val();
       
       $.getJSON(urlpayemntPlan, function (data) {
             /*$('#ppDropdown').append('<option value="-1">Select Payment Plan</option>');*/
             $.each(data, function (index, value) {
                    $('#ppDropdown').append('<option value='+value.sfid+'>'+value.name+'</option>');
             });                              
       }).done(function() {
             
       });
}

function schemeDropdown (){
       $('#getPln').empty();
       
       if ($('#channelPartnerSfidCS').val() != '') {
             
             //alert ("Not Null ::: " + $('#channelPartnerSfidCS').val());
             
             var urlpayemntPlanNew = pageContext+"getSchemeCharge?projectid="+$("#projectId").val()+"&cp_flag_yn=y&schemeSource="+$("#getSchemeSource").val()+"&schemeSite="+$("#getSchemeSite").val()+"&schemePromotional="+$("#getSchemePromotional").val();
             
             var checkboxScheme = '';
             
             
             $.getJSON(urlpayemntPlanNew, function (data) {
                
            	 if (data == null) {
            		 $('#schemeInput').val("schemeNotValid");
            		 $('#printableArea').hide();
            		 //$('#printableArea').css('display', 'none')
                	 //alert ('Record not found with given scheme inputs');
                	 
					swal({
						title: "This is not a valid scheme combination",
						text: "",
						//timer: 2000,
						type: "error",
					});
                	 
                 } else {
                	 $('#schemeInput').val("schemeValid");
                	 //$('#printableArea').show();
                	 //$('#printableArea').css('display', 'block')
                 }
            	 
            	// $('#getPln').append('<option value="0" data-valuepercentage="0" data-valueabsolute="0" data-valuerate="0">Select Scheme</option>');
                    $.each(data, function (index, value) {
                          	
                           $('#getPln').prepend('<option data-id='+value.id+' data-zeroGovtCharges='+value.zero_govt_charges+'  data-valuePercentage='+value.percentage+' data-valueAbsolute='+value.absolute_amount+'  data-valueRate='+value.rate+'   >'+ value.scheme+'</option>');
                           	
                    });                              
             }).done(function() {
                   // $('#getPln').append('<option value="other" data-valuepercentage="0" data-valueabsolute="0" data-valuerate="0">Others</option> ');
                    //$('#getPln').append('<option value="other" data-valuepercentage="0" data-valueabsolute="0" data-valuerate="0">Others</option> <option value="other"  data-zerogovtcharges="true" data-valuepercentage="0" data-valueabsolute="0" data-valuerate="0">Others with Zero SDR</option>');
             });
             
       } else {
             
             var urlpayemntPlanNew = pageContext+"getSchemeCharge?projectid="+$("#projectId").val()+"&cp_flag_yn=&schemeSource="+$("#getSchemeSource").val()+"&schemeSite="+$("#getSchemeSite").val()+"&schemePromotional="+$("#getSchemePromotional").val();
             
             
             $.getJSON(urlpayemntPlanNew, function (data) {
                
            	 if (data == null) {
            		 $('#schemeInput').val("schemeNotValid");
            		 $('#printableArea').hide();
            		 //$('#printableArea').css('display', 'none')
                	 //alert ('Record not found with given scheme inputs');
                	 
					swal({
						title: "This is not a valid scheme combination",
						text: "",
						//timer: 2000,
						type: "error",
					});
                	 
                 } else {
                	 $('#schemeInput').val("schemeValid");
                	 //$('#printableArea').show();
                	 //$('#printableArea').css('display', 'block')
                 }
            	 
                    var checkboxScheme = '';
                    
                  //$('#getPln').append('<option value="0" data-valuepercentage="0" data-valueabsolute="0" data-valuerate="0">Select Scheme</option>');
                    $.each(data, function (index, value) {
                           
                    $('#getPln').prepend('<option data-id='+value.id+' data-zeroGovtCharges='+value.zero_govt_charges+'  data-valuePercentage='+value.percentage+' data-valueAbsolute='+value.absolute_amount+'  data-valueRate='+value.rate+'     >'+ value.scheme+'</option>');
                    
                    });                              
             }).done(function() {
                    //$('#getPln').prepend('<option value="other" data-valuepercentage="0" data-valueabsolute="0" data-valuerate="0">Others</option>');
                    //$('#getPln').append('<option value="other" data-valuepercentage="0" data-valueabsolute="0" data-valuerate="0">Others</option><option value="other"  data-zerogovtcharges="true" data-valuepercentage="0" data-valueabsolute="0" data-valuerate="0">Others with Zero SDR</option>');
             });
       }
       
       
}
       

/*20190623 - Do not remove below code*/
/*function newOtherChargesData () {
       $('#newOtherCharges1 tbody').find("tr:gt(0)").remove();
       var otherChargeURL = pageContext+"getpropOtherCharges?propSfid="+ $('#unitSfid').val()+"&projectId="+$('#projectid').val();
       var amountPerUnit = 0;
       var amount = 0; 
       var i = 0;
       var amountTotal = 0;
       var otherFinalTotal = 0;
       var bspAmount = parseInt($(".a3").text())*parseInt($(".a6").text());
       
       $('#newOtherCharges1 tbody').append ('<tr><th>Basic Sale Price (BSP)</th> <td>'+$(".a3").text()+'</td> <td>'+bspAmount+'</td> <td>'+$("#bspGSTTax").val()+' %</td></tr>');
       $.getJSON(otherChargeURL, function (data) {
             $.each(data, function (index, value) {
                    i = i+1;
                    if (value.propstrength__type__c == 'Flexible') {
                           amountPerUnit = value.propstrength__rate_per_unit_area__c;
                           amount = value.propstrength__rate_per_unit_area__c*parseInt($('.a6').text());
                    } else if (value.propstrength__type__c == 'Fixed'){
                           amountPerUnit = 0;
                           amount = value.propstrength__fixed_charge__c;
                    }                   
                    $('#newOtherCharges1 tbody').append('<tr> <th>'+value.propstrength__other_charge_code__c+'</th> <td>'+amountPerUnit+'</td> <td id="otherAmount'+i+'">'+amount+'</td> <td>'+value.sum+' %</td> </tr>');
                    otherFinalTotal = parseInt($('#otherAmount'+i).text())+parseInt(otherFinalTotal);
             });                              
       }).done(function() {
             otherFinalTotal = otherFinalTotal+bspAmount;
             $('#newOtherCharges1 tbody').append ('<tr> <th colspan="2">Total Unit Value including other charges</th> <th id="newOC1Total">'+  otherFinalTotal +'</th> <td></td> </tr>')
             $('#newOtherCharges1').next('.tableNoRec').hide();
             newOtherCharges2 ();
       });
}*/
/* END 20190623 - Do not remove below code*/




function newOtherCharges2 () {
       //alert ("Test 4567654 65465 534 465 413 456");
       
       $('#newOtherCharges2 tbody').find("tr:gt(0)").remove();
       
       $('#salesConsideration tbody').find("tr:gt(0)").remove();
       $('#printSalesConsideration tbody').find("tr:gt(0)").remove();
       
       $('#tentativeCharges tbody').find("tr:gt(0)").remove();
       $('#printTentativeCharges tbody').find("tr:gt(0)").remove();
       
       //alert ("Test  1234657894653212 ::: " + $('.unitDD option:selected').attr('name')); 
       var otherChargeURL = pageContext+"getpropOtherCharges?propSfid="+ $('#unitSfid').val()+"&projectId="+$('#projectid').val();
       var amountPerUnit = 0;
       var amount = 0; 
       var i = 0;
       var amountTotal = 0;
       var otherFinalTotal = 0;
       
       
       
       //var bspAmount = parseInt($(".a3").text())*parseInt($(".a6").text())+parseInt($('#carParkAmount').val()*$('#carParkCount select').val());
       var bspAmount = parseFloat(parseFloat($(".a3").text())*parseFloat($(".a6").text())+parseFloat($('#carParkAmount').val()*$('#carParkCount select').val())).toFixed(2);
       
       /*2nd Other Charges*/
       var taxableAmount = 0;
       var gstHalf = 0;
       
       var gstAmount = 0;
       var otherChrgRowtotal = 0;
       
       var otherChrgAmountTotal = 0;
       
       var salesConsiderationV2 = 0;
       var tentativeChargesV2 = 0;
       
       
       var ocTaxableAmountTotal = 0;
       var otherChrgCgstTotal = 0;
       
       var otherChrgSGSTTotal = 0;
       var otherChrgFinalTotal = 0;
       
       var otherChargesGSTTotalV2 = 0;
       var otherChargesGSTTotal1Per = 0;
       var otherChargesGSTTotal5Per = 0;
       
       var bspTax = $("#bspGSTTax").val()
       
       
       var gstHalfConditionLog = ''; 
       
       
       /*$('#newOtherCharges2 tbody').append ('<tr> <th>Flat Cost (BSP)</th>  <td id="otherChrgAmount0">'+bspAmount+'</td>   </tr>');*/
       $('#newOtherCharges2 tbody').append ('<tr> <th>Basic Sale Price (BSP)</th>  <td id="otherChrgAmount0">'+bspAmount+'</td> <td > <span id="otherChrgAmountBSP0">'+bspTax+'</span> %</td> <td id="otherChrgCGST0">'+parseInt((bspAmount*bspTax)/100)+'</td>     <th id="otherChrgRighttotal0" style="text-align:right;"> </th> </tr>');
       
       //$('#salesConsideration tbody').append ('<tr> <th>Flat Cost (BSP)</th>  <td class="txtRight" id="otherChrgAmount0" style="text-align:right;">'+bspAmount+'</td>   </tr>');
       
       //After Discount
       $('#salesConsideration tbody').append ('<tr> <th>Flat/Unit Cost </th>  <td class="txtRight" id="scOtherChrgAmount0" style="text-align:right;">'+bspAmount+'</td>   </tr>');
       $('#printSalesConsideration tbody').append ('<tr> <th>Flat/Unit Cost</th>  <td class="txtRight" id="printScOtherChrgAmount0" style="text-align:right;">'+bspAmount+'</td>   </tr>');
       
       
       
       $.getJSON(otherChargeURL, function (data) {
             $.each(data, function (index, value) {
                    i = i+1;
                    
                    //console.log ("SUM ::: " + value.sum);
                    
                    
                    //console.log ("propstrength__part_of_cop__c ::: " + value.propstrength__part_of_cop__c)
                    
                    if (value.propstrength__type__c == 'Flexible') {
                           amountPerUnit = value.propstrength__rate_per_unit_area__c;
                           
                           amount = parseFloat(value.propstrength__rate_per_unit_area__c*parseFloat($('.a6').text())).toFixed(2);
                    } else if (value.propstrength__type__c == 'Fixed'){
                           amountPerUnit = 0;
                           
                           amount = parseFloat(value.propstrength__fixed_charge__c).toFixed(2);
                    }                   
                    
                    //gstHalf = parseInt(value.sum);
                    
                    
                    if (value.propstrength__completion_certificate_received__c == true) {
                           //New Add
                           if (value.propstrength__st_on_completion_certificate__c == true) { 
                                 
                                 console.log (value.propstrength__other_charge_code__c);
                                 
                                 if (value.propstrength__pmay_abatement__c == true) {
                                        if (value.propstrength__gst_status__c == 'New GST') {
                                            
                                               gstHalf = value.propstrength__new_pmay_gst__c;
                                               gstHalfConditionLog = "propstrength__completion_certificate_received__c: true & propstrength__st_on_completion_certificate__c: true & propstrength__pmay_abatement__c: true & propstrength__gst_status__c:New GST & gstHalf:" + gstHalf; 
                                               /*console.log("PP Name ::: "                     +value.propstrength__payment_plan_line_item_name__c);
                                               console.log("Other Name ::: "              +value.other_charges_name);
                                               console.log("PMAY NEW GST ::: "        +value.propstrength__new_pmay_gst__c);*/
                                               
                                        } else if (value.propstrength__gst_status__c == 'Old GST') {
                                               
                                               gstHalf = value.propstrength__pmay_gst__c;
                                               gstHalfConditionLog = "propstrength__completion_certificate_received__c: true & propstrength__st_on_completion_certificate__c: true & propstrength__pmay_abatement__c: true & propstrength__gst_status__c:Old GST & gstHalf:" + gstHalf;
                                               /*console.log("PP Name ::: "                     +value.propstrength__payment_plan_line_item_name__c);
                                               console.log("Other Name ::: "              +value.other_charges_name);
                                               console.log("PMAY OLD GST ::: "        +value.propstrength__pmay_gst__c);*/
                                               
                                        }
                                 } else if (value.propstrength__pmay_abatement__c == false) {
                                        if (value.propstrength__gst_status__c == 'New GST') {
                                               
                                               gstHalf = value.propstrength__new_tax_percentage__c;
                                               gstHalfConditionLog = "propstrength__completion_certificate_received__c: true & propstrength__st_on_completion_certificate__c: true & propstrength__pmay_abatement__c: false & propstrength__gst_status__c:New GST & gstHalf:" + gstHalf;
                                               /*console.log("PP Name ::: "                     +value.propstrength__payment_plan_line_item_name__c);
                                               console.log("Other Name ::: "              +value.other_charges_name);
                                               console.log("Tax Record NEW ::: "      +value.propstrength__new_tax_percentage__c);*/
                                               
                                        } else if (value.propstrength__gst_status__c == 'Old GST') {
                                               
                                               gstHalf = value.propstrength__tax_percentage__c;
                                               gstHalfConditionLog = "propstrength__completion_certificate_received__c: true & propstrength__st_on_completion_certificate__c: true & propstrength__pmay_abatement__c: false & propstrength__gst_status__c:Old GST & gstHalf:" + gstHalf;
                                               /*console.log("PP Name ::: "                     +value.propstrength__payment_plan_line_item_name__c);
                                               console.log("Other Name ::: "              +value.other_charges_name);
                                               console.log("Tax Record OLD ::: "      +value.propstrength__tax_percentage__c);*/
                                               
                                        }
                                 }
                           } else {
                                 console.log (value.propstrength__other_charge_code__c);
                                 gstHalf = 0;
                                 gstHalfConditionLog = "propstrength__completion_certificate_received__c: true & propstrength__st_on_completion_certificate__c: false & gstHalf:" + gstHalf;
                           }
                    } else {
                           if (value.propstrength__pmay_abatement__c == true) {
                                 if (value.propstrength__gst_status__c == 'New GST') {
                                        
                                        gstHalf = value.propstrength__new_pmay_gst__c;
                                        gstHalfConditionLog = "propstrength__completion_certificate_received__c: false & propstrength__pmay_abatement__c: true & propstrength__gst_status__c: New GST & gstHalf" + gstHalf;
                                 } else if (value.propstrength__gst_status__c == 'Old GST') {
                                        
                                        gstHalf = value.propstrength__pmay_gst__c;
                                        gstHalfConditionLog = "propstrength__completion_certificate_received__c: false & propstrength__pmay_abatement__c: true & propstrength__gst_status__c: Old GST & gstHalf" + gstHalf;
                                 }
                           } else if (value.propstrength__pmay_abatement__c == false) {
                                 if (value.propstrength__gst_status__c == 'New GST') {
                                        
                                        gstHalf = value.propstrength__new_tax_percentage__c;
                                        gstHalfConditionLog = "propstrength__completion_certificate_received__c: false & propstrength__pmay_abatement__c: false & propstrength__gst_status__c: New GST & gstHalf" + gstHalf;
                                 } else if (value.propstrength__gst_status__c == 'Old GST') {
                                        
                                        gstHalf = value.propstrength__tax_percentage__c;
                                        gstHalfConditionLog = "propstrength__completion_certificate_received__c: false & propstrength__pmay_abatement__c: false & propstrength__gst_status__c: Old GST & gstHalf" + gstHalf;
                                 }
                           }
                    }
                    
                    
                    
                    
                    
                    // END New Add
                    

                    /*if (value.sum == '8' || value.sum == '18') {
                           
                           taxableAmount = amount;
                           
                           gstAmount = amount;
                           
                    } else  {
                           taxableAmount = Math.round(amount*2/3);
                           gstAmount = Math.round(taxableAmount*3/2);
                    }*/
                    
                    
                    
                    /*$('#newOtherCharges2 tbody').append('<tr> <th> '+value.propstrength__other_charge_code__c+' </th> <td id="otherChrgAmount'+i+'"> '+amount+' </td>  </tr>');*/
                    
                    otherChrgRowtotal = parseFloat(amount*(gstHalf+100)/100).toFixed(2);
                    
                    $('#newOtherCharges2 tbody').append('<tr> <th> '+value.propstrength__other_charge_code__c+' </th> <td id="otherChrgAmount'+i+'"> '+amount+' </td> <td>'+gstHalf+' %</td> <td id="otherChrgCgst'+i+'">'+amount*gstHalf/100+'</td>  <th id="otherChrgRighttotal'+i+'" >'+otherChrgRowtotal+'</th> </tr>');
                    
                    
                    
                    if (value.propstrength__part_of_cop__c == true) {
                           //otherChrgRowtotal = parseInt(amount*(gstHalf+100)/100);
                    		if (amount > 0) {
								
                    			//Added for 45lac GST condition - 20200609
								if (value.propstrength__other_charge_code__c != "Club Development Charges") {	
	                    			otherChargesGSTTotal1Per = parseFloat(parseFloat((amount)*1/100)+parseFloat(otherChargesGSTTotal1Per)).toFixed(2);
	                    			otherChargesGSTTotal5Per = parseFloat(parseFloat((amount)*5/100)+parseFloat(otherChargesGSTTotal5Per)).toFixed(2);
								} else {
									otherChargesGSTTotal1Per = parseFloat(parseFloat((amount)*gstHalf/100)+parseFloat(otherChargesGSTTotal1Per)).toFixed(2);
									otherChargesGSTTotal5Per = parseFloat(parseFloat((amount)*gstHalf/100)+parseFloat(otherChargesGSTTotal5Per)).toFixed(2);
								}
	                    		// END Added for 45lac condition - 20200609
                           
                                 $('#salesConsideration tbody').append('<tr> <th> '+value.propstrength__other_charge_code__c+' </th> <td class="txtRight" id="salesConsiderationAmount'+i+'" style="text-align:right;"> '+amount+' </td>  </tr>');
                                 $('#printSalesConsideration tbody').append('<tr> <th> '+value.propstrength__other_charge_code__c+' </th> <td class="txtRight" id="salesConsiderationAmount'+i+'" style="text-align:right;"> '+amount+' </td>  </tr>');
                                 salesConsiderationV2 = parseFloat(parseFloat($("#salesConsiderationAmount"+i).text()) + parseFloat(salesConsiderationV2)).toFixed(2);
                           } 
                    } else if (value.propstrength__part_of_cop__c == false) {
	                    	otherChargesGSTTotal1Per = parseFloat(parseFloat((amount)*gstHalf/100)+parseFloat(otherChargesGSTTotal1Per)).toFixed(2);
							otherChargesGSTTotal5Per = parseFloat(parseFloat((amount)*gstHalf/100)+parseFloat(otherChargesGSTTotal5Per)).toFixed(2);
                    	
                    		//otherChrgRowtotal = parseInt(amount*(gstHalf+100)/100);
                    		$('#tentativeCharges tbody').append('<tr> <th> '+value.propstrength__other_charge_code__c+' </th> <td class="txtRight" id="tentativeChargesAmount'+i+'" style="text-align:right;"> '+amount+' </td>  </tr>');
                           $('#printTentativeCharges tbody').append('<tr> <th> '+value.propstrength__other_charge_code__c+' </th> <td class="txtRight" id="tentativeChargesAmount'+i+'" style="text-align:right;"> '+amount+' </td>  </tr>');
                           tentativeChargesV2 = parseFloat(parseFloat($("#tentativeChargesAmount"+i).text()) + parseFloat(tentativeChargesV2)).toFixed(2);
                    }
                    
                    otherFinalTotal = parseFloat($('#otherAmount'+i).text())+parseFloat(otherFinalTotal);
                    otherChrgAmountTotal =        parseFloat($("#otherChrgAmount"+i).text())     + parseFloat(otherChrgAmountTotal);
                    ocTaxableAmountTotal =        parseFloat($("#ocTaxableAmount"+i).text())     + parseFloat(ocTaxableAmountTotal);
                    
                    otherChrgCgstTotal =        parseFloat($("#otherChrgCgst"+i).text())             + parseFloat(otherChrgCgstTotal);
                    
                    otherChrgSGSTTotal =       parseFloat($("#otherChrgSGST"+i).text())             + parseFloat(otherChrgSGSTTotal);
                    otherChrgFinalTotal =        parseFloat($("#otherChrgRighttotal"+i).text())       + parseFloat(otherChrgFinalTotal);
                    
                    console.log ("Other Charge Name :::: GST Status ::: " + gstHalfConditionLog + "..........."  + value.propstrength__other_charge_code__c + " | " + "amount ::: " + amount + " | " + "gstHalf ::: " + gstHalf);
                    
                    
                    if (value.propstrength__sta_applicable__c == true) {
                           //otherChargesGSTTotalV2 = parseInt((amount*2/3)*gstHalf/100)+parseInt(otherChargesGSTTotalV2);
                           otherChargesGSTTotalV2 = parseFloat(parseFloat((amount)*gstHalf/100)+parseFloat(otherChargesGSTTotalV2)).toFixed(2);
                    } else {
                           otherChargesGSTTotalV2 = parseFloat(parseFloat(amount*gstHalf/100)+parseFloat(otherChargesGSTTotalV2)).toFixed(2);
                    }
                    
             });          
       }).done(function() {
             
             var discountVal = 0;
             var carpetAreaAmount = 0;
             var exclusiveAreaAmount = 0;
             
             /*if ($('#projectId').val() == 'a1l6F000001l2yqQAA') {
            	 $('#tentativeCharges tbody').append("<tr> " +
            	 			"<th> Electricity Charges </th> <td class='txtRight' style='text-align:right;'> 0 </td>  </tr>" +
            	 			"<tr> <th> Legal Charges </th> <td class='txtRight' style='text-align:right;'> 0 </td>  </tr>" +
            	 			"<tr> <th> Club Development Charges </th> <td class='txtRight' style='text-align:right;'> 0 </td>  </tr>");
            	 		
            	 
            	 $('#printTentativeCharges tbody').append("<tr> " +
         	 			"<th> Electricity Charges </th> <td class='txtRight' style='text-align:right;'> 0 </td>  </tr>" +
         	 			"<tr> <th> Legal Charges </th> <td class='txtRight' style='text-align:right;'> 0 </td>  </tr>" +
         	 			"<tr> <th> Club Development Charges </th> <td class='txtRight' style='text-align:right;'> 0 </td>  </tr>");
             }*/
             
             /*else if ($('#projectId').val() == 'a1l6F000005hPm5QAE') {
            	 $('#tentativeCharges tbody').append("<tr> " +
            	 			"<th> Electricity Charges </th> <td class='txtRight' style='text-align:right;'> 0 </td>  </tr>" +
            	 			"<tr> <th> Club Development Charges </th> <td class='txtRight' style='text-align:right;'> 0 </td>  </tr>");
            	 
            	 $('#printTentativeCharges tbody').append("<tr> " +
         	 			"<th> Electricity Charges </th> <td class='txtRight' style='text-align:right;'> 0 </td>  </tr>" +
         	 			"<tr> <th> Club Development Charges </th> <td class='txtRight' style='text-align:right;'> 0 </td>  </tr>");
             }*/
             
             
             //alert ("otherChrgCgstTotal ::: " + otherChrgCgstTotal);
             
              $('#otherChrgRighttotal0').text(parseFloat($("#otherChrgAmount0").text())+parseFloat($("#otherChrgCGST0").text()));
             
             otherFinalTotal = otherFinalTotal+bspAmount;
             
             
             otherChrgAmountTotal =        parseFloat(otherChrgAmountTotal)+parseFloat($('#otherChrgAmount0').text());
             
             
             
             //tentativeChargesV2 =     parseInt(tentativeChargesV2);
             
             
             
             
             ocTaxableAmountTotal =        parseFloat(ocTaxableAmountTotal)+parseFloat($('#ocTaxableAmount0').text());
             
             otherChrgCgstTotal =        parseFloat(otherChrgCgstTotal)+parseFloat($('#otherChrgCGST0').text());
             
             //otherChargesGSTTotalV2 = parseInt(otherChargesGSTTotalV2)+parseInt(((bspAmount*2/3)*bspTax)/100);
             //otherChargesGSTTotalV2 = parseInt(otherChargesGSTTotalV2)+parseInt((($('.scOtherChrgAmount0').text()*2/3)*bspTax)/100);
             
             
             otherChrgFinalTotal =        parseFloat(otherChrgFinalTotal)+parseFloat($('#otherChrgRighttotal0').text());
             
             //$('#newOtherCharges2 tbody').append('<tr> <th class="subHead"> Total </th> <th>'+otherChrgAmountTotal+'</th>    </tr>');
             $('#newOtherCharges2 tbody').append('<tr> <th class="subHead"> Total </th> <th>'+otherChrgAmountTotal+'</th>  <th></th>       <th>'+otherChrgCgstTotal+'</th>  <th>'+otherChrgFinalTotal+'</th> </tr>');
             
             
             
             //salesConsiderationV2 =        parseInt(salesConsiderationV2)+parseInt($('#scOtherChrgAmount0').text());
             
             
             var divisor = 0;
             var discountOn = 0;
             var discountAbsolute = 0;
             
             var discountPercentage = 0;
             
             var discountGross = 0;
             
             var discountNet = 0;
             var finalFlatAmount = 0;
             var stampDuty = 0;
             var registrationCharges = 0;
                    
             
             discountAbsolute = $('#getPln').find('option:selected').attr("data-valueabsolute");
             
                    
                    discountOn = parseFloat($('#scOtherChrgAmount0').text())+parseFloat(salesConsiderationV2)-discountAbsolute;
                    discountPercentage = discountOn*$('#getPln').find('option:selected').attr("data-valuepercentage")/100;
                    /*divisor = parseFloat(parseFloat(100)+parseFloat($('#bspGSTTax').val()));
                    discountNet= discountGross/divisor*100*/
                    
                    discountGross = parseFloat(discountAbsolute)+parseFloat(discountPercentage);
                    
                    /*only for south est*/
             
                    var bspIncrease = 0;
                    if ($('#projectId').val() == 'a1l6F0000047Q1xQAE' &&  $('#ppDropdown').val() == 'a1i6F000007rApYQAU' ||  $('#projectId').val() == 'a1l6F0000047Q1xQAE' && $('#ppDropdown').val() == 'a1i6F000007rApZQAU') {
                           bspIncrease = (discountOn - discountPercentage)*.04
                    } else {
                           bspIncrease = 0;
                    }
                    /*END only for south est*/
                    
                    finalFlatAmount = parseFloat(($('#otherChrgAmount0').text())-(discountGross)+parseFloat(bspIncrease)).toFixed(2);
                    
                    
                    $('#scOtherChrgAmount0').text(parseFloat(($('#otherChrgAmount0').text())-(discountGross)+parseFloat(bspIncrease)).toFixed(2));
                    
                    
                    $('#discountPerAndAbsolute').val(discountGross);
                    
                    
                    carpetAreaAmount = parseFloat(finalFlatAmount/$('#totalSqm').text() * $('#carpetSqm').text()).toFixed(2); 
                    exclusiveAreaAmount = parseFloat(finalFlatAmount/$('#totalSqm').text() * $('#balTerSqm').text()).toFixed(2); 
                    
                    $('.carpetAreaAmount').text(carpetAreaAmount);
                    $('.exclusiveAreaAmount').text(exclusiveAreaAmount);
                    
                    
                    $('#printScOtherChrgAmount0').text(parseFloat(($('#otherChrgAmount0').text())-(discountGross)+parseFloat(bspIncrease)).toFixed(2));
                    
                    
       
             
                    //discountGross = $('#getPln').find('option:selected').attr("data-valueabsolute");
                    
                    
                    //finalFlatAmount = $('#scOtherChrgAmount0').text()-(discountGross);
                    
                    //$('#scOtherChrgAmount0').text($('#scOtherChrgAmount0').text()-(discountGross));
             
             
                    
                    //finalFlatAmount = $('#scOtherChrgAmount0').text();
                    
                    //New with Discount 
                    //$('#salesConsideration tbody').append('<tr> <th class="subHead">Discount Total Sales Consideration (A) </th> <th class="txtRight" id="salesConsiderationTotal">'+salesConsiderationV2+'</th>    </tr>');
             
             var TotalA = parseFloat(parseFloat(salesConsiderationV2)+parseFloat($('#scOtherChrgAmount0').text())).toFixed(2);
             
             //otherChargesGSTTotalV2 = parseInt(otherChargesGSTTotalV2)+parseInt(((bspAmount*2/3)*bspTax)/100);
             //otherChargesGSTTotalV2 = parseInt(otherChargesGSTTotalV2)+parseInt(((finalFlatAmount*2/3)*bspTax)/100);
             bspTax = getTaxPercentage(finalFlatAmount, $('#projectid').val(), bspTax, TotalA, $('#carpetSqm').text());
             $('#bspGSTTax').val(bspTax);
             
             var GSTDefault = parseFloat(parseFloat(otherChargesGSTTotalV2)+parseFloat(((finalFlatAmount)*bspTax)/100)).toFixed(2);
             var GST1Per = parseFloat(parseFloat(otherChargesGSTTotal1Per)+parseFloat(((finalFlatAmount)*bspTax)/100)).toFixed(2);
             var GST5Per = parseFloat(parseFloat(otherChargesGSTTotal5Per)+parseFloat(((finalFlatAmount)*bspTax)/100)).toFixed(2);
             
             //added for 45lac condition
             if($('#projectid').val() == 'a1l2s000000PJPmAAO' || $('#projectid').val() == 'a1l6F000002X6IOQA0' || $('#projectid').val() == 'a1l2s00000003VlAAI' || $('#projectid').val() == 'a1l6F0000081xb4QAA' || $('#projectid').val() == 'a1l2s000000PGu3AAG' || $('#projectid').val() == 'a1l2s000000PGu8AAG' || $('#projectid').val() == 'a1l2s000000PGuDAAW' || $('#projectid').val() == 'a1l2s000000PGuIAAW' || $('#projectid').val() == 'a1l2s000000PGuNAAW' || $('#projectid').val() == 'a1l2s000000PGuSAAW'){
				if(TotalA>=4500000 || $('#carpetSqm').text() >= 60){
					otherChargesGSTTotalV2 = GST5Per;
				} else {
					otherChargesGSTTotalV2 = GST1Per;
				}
             } else if ($('#projectid').val() == 'a1l6F000003TXloQAG') {
            	 if(TotalA>=4500000 || $('#carpetSqm').text() >= 90){
 					otherChargesGSTTotalV2 = GST5Per;
 				} else {
 					otherChargesGSTTotalV2 = GST1Per;
 				}
             } else {
            	 otherChargesGSTTotalV2 = GSTDefault;
             }
             //END added for 45lac condition
             
              //$('#flatCostWithDiscount').val(parseFloat(parseFloat(salesConsiderationV2)+parseFloat($('#scOtherChrgAmount0').text())));
             
              $('#flatCostWithDiscount').val(parseFloat(parseFloat($('#scOtherChrgAmount0').text())));
             
             
             //Old
             $('#salesConsideration tbody').append('<tr> <th class="subHead"> Sale Consideration (A) </th> <th class="txtRight salesConsiderationTotalNew" id="salesConsiderationTotal" style="text-align:right;">'+ TotalA +'</th>    </tr>');
             
             
             
             //-----------------
             
             $('#printSalesConsideration tbody').append('<tr> <th class="subHead"> Sale Consideration (A) </th> <th class="txtRight" id="salesConsiderationTotal" style="text-align:right;">'+ TotalA +'</th>    </tr>');
             
             
             
             
             $('#tentativeCharges tbody').append('<tr> <th class="subHead"> Estimated other charges (B) </th> <th class="txtRight" id="tentativeChargesTotal" style="text-align:right;">'+tentativeChargesV2+'</th>    </tr>');
             $('#printTentativeCharges tbody').append('<tr> <th class="subHead"> Estimated other charges (B) </th> <th class="txtRight" id="tentativeChargesTotal" style="text-align:right;">'+tentativeChargesV2+'</th>    </tr>');
             
             //$('#stamp_duty').text(parseInt((otherChrgAmountTotal*7)/100));
             
             /*$('#stamp_duty').text(parseInt((salesConsiderationV2*7)/100));
             $('.stamp_duty').text(parseInt((salesConsiderationV2*7)/100));*/
             
             //if project 
             
             if ($('#getPln').find('option:selected').attr("data-zerogovtcharges") == 'true'){
                    stampDuty = 0;
                    registrationCharges = 0;
                    
             //Remove below else if after South launch      
             }else if ($('#region__c').val() == "Noida" || $('#region__c').val() == "Gurgaon"  ||  $('#region__c').val() == "Bangalore" ||  $('#region__c').val() == "New Delhi"){
                    stampDuty = 0;
                    registrationCharges = 0;
                    
                    $('#stampDutyCol').hide();
                    $('#regChargeCol').hide();
                    
                    $('#stampDutyColPrint').remove();
                    $('#regChargeColPrint').remove();
             }

             //Registration Charges only for Maharashtra
             else if ($('#region__c').val() == "Mumbai" || $('#region__c').val() == "Pune" || $('#region__c').val() == "Nagpur") {
            	 
            	//for maharashtra projects 
                 var onePerOfA = parseFloat($('.salesConsiderationTotalNew').text())*1/100;
                 
                 //if (1% of Agreement value or 30,000 whichever is lower)
                 
                 if (onePerOfA > 30000) {
                        registrationCharges = parseFloat(30000).toFixed(2);
                 } else {
                        registrationCharges = parseFloat(onePerOfA).toFixed(2);
                 }
                 
                 stampDuty = parseFloat(($('.salesConsiderationTotalNew').text()*$('#stampDuty').val())/100).toFixed(2);
                 //registrationCharges = parseFloat(30000).toFixed(2);
            	 
            	 
   
             }
             
             
             else {
            	 registrationCharges = Math.round(parseFloat($('.salesConsiderationTotalNew').text())*1/100);
                 
            	 stampDuty = parseFloat(($('.salesConsiderationTotalNew').text()*$('#stampDuty').val())/100).toFixed(2);
                 //$('#regChargeCol').hide();
                 //$('#regChargeColPrint').remove();      
             }
             
             $('#stamp_duty').text(stampDuty);
             $('.stamp_duty').text(stampDuty);
             
             $('#registrationCharges').text(registrationCharges);
             $('.registrationCharges').text(registrationCharges);
       
       
             //$('#stamp_duty').text((otherChrgAmountTotal*7)/100);
             var total_flat_cost = otherChrgFinalTotal+parseFloat((otherChrgAmountTotal*7)/100)+30000
             
             //$('#total_flat_cost').text(total_flat_cost);
             
             /*$('#stamp_duty').text()
             $('#registrationCharges').text()
             $('#goodsNServiceTax').text()*/
             
             
             $('#newOtherCharges2').next('.tableNoRec').hide();
             
             $('#tentativeCharges').next('.tableNoRec').hide();
             
             $('#salesConsideration').next('.tableNoRec').hide();
             
             //console.log ("otherChargesGSTTotalV2 ::: " + otherChargesGSTTotalV2);
             
             $('#goodsNServiceTax').text(otherChargesGSTTotalV2);
             $('.goodsNServiceTax').text(otherChargesGSTTotalV2);
             

             $('#total_flat_cost').text(parseFloat(  parseFloat($('#stamp_duty').text())+parseFloat($('#registrationCharges').text())+ parseFloat($('#goodsNServiceTax').text())).toFixed(2));
             $('.total_flat_cost').text(parseFloat(  parseFloat($('#stamp_duty').text())+parseFloat($('#registrationCharges').text())+ parseFloat($('#goodsNServiceTax').text())).toFixed(2));
             
             
             var x = parseFloat (parseFloat($('.salesConsiderationTotalNew').text()) + parseFloat($('#tentativeChargesTotal').text())  + parseFloat($('#total_flat_cost').text())   ).toFixed(2) 
             
             $('#totalABCnoCurrency').text(x)
             
             x=x.toString();
             var afterPoint = '';
             if(x.indexOf('.') > 0)
                afterPoint = x.substring(x.indexOf('.'),x.length);
             x = Math.floor(x);
             x=x.toString();
             var lastThree = x.substring(x.length-3);
             var otherNumbers = x.substring(0,x.length-3);
             if(otherNumbers != '')
                 lastThree = ',' + lastThree;
             var res = otherNumbers.replace(/\B(?=(\d{2})+(?!\d))/g, ",") + lastThree + afterPoint;
             
             
             //Discount
             //var bsp_offer = $("#scOtherChrgAmount0").text();
             var saleble_offer = $('.a6').text();
             //var original_bsp = $('.a3_bsp').text();
             
             var discountPerSqf = parseFloat(parseFloat($('#discountPsqf').val())*parseFloat(saleble_offer));
             
             var discount_Value = parseFloat(parseFloat(discountPerSqf)+parseFloat($('#discountPerAndAbsolute').val())).toFixed(2);
             //END Discount
             
             
             $('#totalABC').text(res);
             $('.totalABC').text(res);
             
             

       //     $('.a3_bsp').text(parseFloat(value.BasicSalePricepersquarefeet));
       //       $('.a3').text(parseFloat(parseFloat(value.BasicSalePricepersquarefeet)+parseFloat(value.bsp_amount)-(otherScheme)).toFixed(2));
             
             $('#finalDiscountValue').val(discount_Value);
             
             var y = discount_Value;
             
             y=y.toString();
             var yafterPoint = '';
             if(y.indexOf('.') > 0)
                yafterPoint = y.substring(y.indexOf('.'),y.length);
             y = Math.floor(y);
             y=y.toString();
             var ylastThree = y.substring(y.length-3);
             var yotherNumbers = y.substring(0,y.length-3);
             if(yotherNumbers != '')
                 ylastThree = ',' + ylastThree;
             var yres = yotherNumbers.replace(/\B(?=(\d{2})+(?!\d))/g, ",") + ylastThree + yafterPoint;
             
             
             if (discount_Value != 0) {
                    $('#totalDiscountCol').empty();
                    
                   
                    
                    var printDiscountHtml = '';
                    
                    /*printDiscountHtml +='<table class="table table-bordered  bgWhite" style="width:100%">'
                                                            +'<tbody>'
                                                                   +'<tr>'
                                                                     +'<th class="subHead" style="text-align:left; font-size: 10px; font-weight: normal;">'
                                                                                 +'Total Discount'
                                                                            +'</th>'
                                                                          +'<th class="totalDiscount" width="80" align="right" style="text-align:right !important; font-size: 10px;">'+yres+'</th>'
                                                                     +'</tr>'
                                                            +'</tbody>'
                                                     +'</table>'; */
                    
                    
                    printDiscountHtml +='<div style="font-size:10px !important; border-style: solid;  border-color: #4b4b4b; border-width: 7px; padding: 10px; text-align: center; background-color: #fff; ">'
                                                            +'Total Sales Consideration is calculated after applying discount of Rs.' 
                                                            +'<label style="display: block;">'+yres+'</label>'
                                                     +'</div>'; 
                    
                    //Forest Grove at Godrej Park Greens / Godrej Hillside 2 / Godrej Nurture, Bengaluru / Godrej Seven, Kolkata / Godrej Park Greens, Pune
                   /* if ($('#projectid').val() != "a1l6F000008gL4oQAE" && $('#projectid').val() != "a1l6F000001kj37QAA" &&  $('#projectid').val() != "a1l6F000001l2yqQAA" && $('#projectid').val() != "a1l6F0000080irTQAQ" &&  $('#projectid').val() != "a1l2s00000003lPAAQ"  &&  $('#projectid').val() != "a1l2s00000000X5AAI"  && $('#projectid').val() != "a1l6F000009D6IMQA0" && $('#projectid').val() != "a1l2s00000000pEAAQ" && $('#projectid').val() != "a1l6F000003TXloQAG" && $('#projectid').val() != "a1l6F000005hPm5QAE" ) {
                    	$('#totalDiscountCol').append(printDiscountHtml);
                    	$('#totalDicountView').show();
                    } else {
                    	$('#totalDiscountCol').append('');
                    	$('#totalDicountView').hide();
                    }*/
                    
                    if ($('#projectid').val() == 'a1l2s000000PJPmAAO' || $('#projectid').val() == 'a1l6F000002X6IOQA0' || $('#region__c').val() == "Gurgaon" || $('#region__c').val() == "New Delhi" || $('#region__c').val() == "Noida") {
	                	$('#totalDiscountCol').append(printDiscountHtml);
	                	$('#totalDicountView').show();
	                } else {
	                	$('#totalDiscountCol').append('');
	                	$('#totalDicountView').hide();
	                }
                    
                    
                    //$('#totalDiscountCol').append('');
                	//$('#totalDicountView').hide();
                    
                    
                    
                    
                    
             } else {
                    $('#totalDicountView').hide();
                    $('#totalDiscountCol').html('');
             }
             
              
             $('#totalDiscount').text(yres);
             
            firstMilstone();
            //paymentPlanMilestone();
       });
       
       
}


//HoldReverseTimer();
var interval;
var HoldReverseTimer = function(min, sec){
       
       var countStatus = '';
       
       $('.holdCountdown').empty();
       timer2 = min+":"+sec;
       
       
       interval = setInterval(function(){
               timer = timer2.split(':');
               //by parsing integer, I avoid all extra string processing
                minutes = parseInt(timer[0], 10);
                seconds = parseInt(timer[1], 10);
               --seconds;
               minutes = (seconds < 0) ? --minutes : minutes;
               if (minutes < 0 && seconds < 0) {
                      clearInterval(interval);
                      countStatus = 'countEnd';
               } else {
                      countStatus = 'countInprocess';
               }
                      
               
               seconds = (seconds < 0) ? 59 : seconds;
               seconds = (seconds < 10) ? '0' + seconds : seconds;
               //minutes = (minutes < 10) ?  minutes : minutes;
               
               if (countStatus == 'countEnd'){
                      $('.holdCountdown').html('0:00');
                      $('#inventoryBreadcrumb').empty();
                     // seconds = 0;
               }else {
                      $('.holdCountdown').html(minutes + ':' + seconds);
               }
               timer2 = minutes + ':' + seconds;
       },1000);
};

/*Counter*/
/*function holdCounter (min, sec) {
       $('#hm_timer').countdowntimer({
             //hours : 0,
             minutes : min,
             seconds : sec,
             size : "lg"
       });
}*/



/*$(document).keydown(function (e) {  
    return (e.which || e.keyCode) != 116;  
}); 

window.history.pushState(null, "", window.location.href);        
window.onpopstate = function() {
    window.history.pushState(null, "", window.location.href);
};


window.onbeforeunload = function() {
    return "Are you sure you want to leave?";
}*/
/* END Counter*/




window.onscroll = function() {myFunction()};

var header = document.getElementById("csHoldCountCol");
var sticky = header.offsetTop;

function myFunction() {
  if (window.pageYOffset > sticky) {
    header.classList.add("sticky");
  } else {
    header.classList.remove("sticky");
  }
}


$('#csFormatType').on('change', function() {
       if (this.value == 'v1') {
             $('.csV1').show();
             $('.csV2').hide();
       } else if (this.value == 'v2'){
             $('.csV1').hide();
             $('.csV2').show();
       }
       //alert( this.value );
});


function tncData () {
       $.post(pageContext+"getTncData",{"ppId":$('#ppDropdown').val(), "projectid":$('#projectid').val(), "tower_sfid":$('#towerSfid').val() },function(data){                      
       }).done(function(data){
             $('.tncData').empty();
             var obj =JSON.parse(data);
             
             if (obj != null) {
                    $(".tncData").closest('table').next('.tableNoRec').hide();
                    for (i = 0; i< obj.length; i++) {
                           /*if($('#ppDropdown').val() == obj[i].pymt_plan_id) {
                                 $('.tncData').prepend(obj[i].tnc_text);
                                 //return false;
                           }else {
                                 if (obj[i].pymt_plan_id == '') {
                                        $('.tncData').prepend(obj[i].tnc_text);
                                  //      return false;
                                 }
                           }*/
                    	 $('.tncData').prepend(obj[i].tnc_text);
                    	 return false;
                    }
                    
                    /*if($('#ppDropdown').val() == "a1i2s000000Gn5iAAC" && $('#projectid').val() == "a1l2s00000003BMAAY") {
                    	$('.tncData').append("" +
                    			" <div>* Presently for the Subvention Scheme we have tie up with Axis Bank only .The subvention period is upto 15th March 2022.The said scheme is Subject to Purchaser's loan eligibility. All Loans subject to norms and discretion of Lending authority. </div>" +
                    			" <div>* Pursuant to execution of the TPA between Applicant(s), Bank and Developer, in case the interest rate applicable to the Applicant(s)  increases for any reason whatsoever, the Applicant(s) shall be liable to bear the additional subvention interest cost and not the Developer. GST Not Covered under this Subvention scheme. </div>" +
                    			" <div>* This is a subvention cost sheet </div> ");
                    } */
                    
                    
             } else {
                    $(".tncData").closest('table').next('.tableNoRec').show();
             }
       });
}
$('.printCurrentDate').text($.datepicker.formatDate('dd/mm/yy', new Date()));

function printPdfData(generateFrom) {
       var url=$("#contextPath").val();
       
       var USEREMAIL = '';
       if (generateFrom != "Offered"){
    	   USEREMAIL = USEREMAIL_GV;
       } else {
    	   USEREMAIL = '';
       }
       
       
       var enq18sfid = "";
       
       if ($('#enquirysfid').val() != undefined) {
    	   enq18sfid =  "-"+$('#enquirysfid').val();
       }
       
       var enqSfid = generateFrom+enq18sfid;
       
       if (generateFrom != "Email") {
    	   $('#printBtnFloat').empty();
           $('#printBtnFloat').html('<i style="color:#fff !important; " class="fa fa-spinner fa-spin printficon"></i>');
       } else {
    	   $('#emailBtnFloat').empty();
           $('#emailBtnFloat').html('<i style="color:#fff !important; " class="fa fa-spinner fa-spin printficon"></i>');
           enqSfid = "Draft"+enq18sfid;
       }
       
       
       $('#csCommitmentTxt').empty();
       
       if ($('#costsheet_commitment').val().trim() != '') {
             $('#csCommitmentTxt').html("<h5>Sales Comments: </h5><span style='font-size:8px !important;'>"+$('#costsheet_commitment').val()+"</span>");
       } 
             
       
       
       $.post(pageContext+"printCSdata",{"generateFrom":generateFrom, "USEREMAIL_GV":USEREMAIL,"unitTval":$('#unitTval').text(), "floorTval":$('#floorTval').text(), "towerName":$('#towerTval').text(), "regionName":$('#region__c').val(), "projectSfid":$('#projectId').val(),"unitSfid":$('#unitSfid').val(),"enqSfid":enqSfid,"csData":$('#getCSDataForPrint').html(), "projectName":$('#marketingProjectName').val(), "currentDate":$.datepicker.formatDate('dd/mm/yy', new Date())},function(data){                           
             
       }).done(function(data){
    	   
    	   costsheetLogger (generateFrom);
    	   
    	   //var uriPath = pageContext+'Costsheet?name='+enqSfid+'-'+$('#unitSfid').val()+'-'+$('#projectId').val() +'&region='+$('#region__c').val() + '&project='+$('#marketingProjectName').val()+'&tower='+encodeURIComponent($('#towerTval').text())+'&floor=' + $('#floorTval').text() + '&unit=' + $('#unitTval').text() + '&from=costsheet';
    	   var uriPath = pageContext+'Costsheet?name='+enqSfid+'-'+$('#unitSfid').val()+'-'+$('#projectId').val() +'&region='+$('#region__c').val() + '&project='+encodeURIComponent($('#marketingProjectName').val())+'&tower='+encodeURIComponent($('#towerTval').text())+'&floor=' + $('#floorTval').text() + '&unit=' + $('#unitTval').text() + '&from=costsheet';
    	  
		   
		   if (generateFrom != "Email") {     
			   var win = window.open(uriPath,'_blank');
			   
			   if (win) {
    			   win.focus();
    		   } else {
    			   alert('Please allow popups for this website');
    		   }
    		   
    		   $('#printBtnFloat').html('<i class="fa fa-print printficon"></i>');
		   } else {
			   $.getJSON(uriPath,
			   function(data) {
				 //  doSomethingWith(data); 
			   }); 
		   }
    	  
    	  
	         
	         
	         if (generateFrom == 'Offered') {
	             swal({
	               title: "Offer Created, Cost Sheet Successfully Generated & KYC Link Sent To Customer.",
	               text: "",
	               icon: "success",
	               type: "success",
	               dangerMode: true,
	             }).then(function(isConfirm) {
	               if (isConfirm) {
	                    window.location.href =url+ "/assignedusers?userId="+$("#userid").val()+"&projectid="+$("#projectid").val()+"&projectname="+$("#projectname").val()  ;
	               }
	             });
	         }
	         
	         
	         if (generateFrom == "Email") {
	        	//-----------------  Cost Sheet Email
		         var csImagePath = "D:\\SW\\apache-tomcat-9.0.0.M22\\apache-tomcat-9.0.0.M22\\costSheetPDF\\"+$('#region__c').val()+'\\'+$('#marketingProjectName').val()+'\\'+$('#towerTval').text()+'\\' + $('#floorTval').text() + '\\' + $('#unitTval').text() + '\\' + enqSfid+'-'+$('#unitSfid').val()+'-'+$('#projectId').val()+'.png';
		         
		         var emlContent =  'To: <'+$("#email").val()+'>'+'\n';
		         emlContent += 'Subject: Godrej Properties: Cost sheet for '+$(".unit_property_name").text()+', '+ $(".printProjectName").text() + '\n';
		         emlContent += 'X-Unsent: 1'+'\n';
		         emlContent += 'Content-Type:  multipart/mixed; boundary=--boundary_text_string'+'\n';
		         
		         emlContent += ''+'\n';
		         
		         emlContent += '----boundary_text_string'+'\n';
		         emlContent += 'Content-Type: text/html; charset=UTF-8'+'\n';
		         
		         emlContent += ''+'\n';
		         
		         
		         var emailHTML = '';
		         
		         emailHTML +=  '<html>'
			            	 + '<head>'
				             
			            	 + '<style>'
				             + ' body, html, table {'
				             + '     font-family: Calibri, Arial, sans-serif;'
				             + ' }'
				             + ' .pastdue { color: crimson; }'
				             + ' table {'
				             + ' 	border: 1px solid silver;'
				             + ' 	padding: 6px;'
				             + ' }'
				             + ' thead {'
				             + '     text-align: center;'
				             + '     font-size: 1.2em;'
				             + '     color: navy;'
				             + '     background-color: silver;'
				             + '     font-weight: bold;'
				             + ' }'
				             + ' tbody td {'
				             + ' 	text-align: center;'
				             + ' }'
				             + '</style>'
				             + '</head>'
				             + '<body>'
				             	 
				             + '<div>Dear '+$('#hdrCustomerName').text()+',</div> <br>'
				             + '<div>Greetings from Godrej Properties!</div> <br>'
				             + '<div>We heartily welcome you to the Godrej family.</div> <br><br>'
				             
				             +'<div>Please find attached the cost sheet for '+$(".unit_property_name").text()+', '+$(".printProjectName").text()+'</div> <br><br>'
				             
				             + '<table width=700>'
				             + '	<tr>'
				             + '		<td><img src="'+csImagePath+'"  width="700" alt=""></td>' 
				             + '	</tr>'
				             + '</table>'
				             + '</body>'
				             + '</html>'
				             + '\n'; 
		         
		         
		         if (data != "" && data != null && data != undefined) {
		        	 var emlAttachment = ''+'\n';
			         emlAttachment += '----boundary_text_string'+'\n';
			         emlAttachment += 'Content-Type: application/pdf; name='+enqSfid+'-'+$('#unitSfid').val()+'-'+$('#projectId').val()+'.pdf'+'\n';
			         emlAttachment += 'Content-Transfer-Encoding: base64'+'\n';
			         emlAttachment += 'Content-Disposition: attachment'+'\n';
			         
			         emlAttachment += ''+'\n';
			         
			         //emlAttachment += 'JVBERi0xLjQKJeLjz9MKMiAwIG9iago8PC9Db2xvclNwYWNlL0RldmljZVJHQi9TdWJ0eXBlL0ltYWdlL0hlaWdodCAzMzMvRmlsdGVyL0RDVERlY29kZS9UeXBlL1hPYmplY3QvV2lkdGggMTI3MS9CaXRzUGVyQ29tcG9uZW50IDgvTGVuZ3RoIDUwNTUzPj5zdHJlYW0K/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAIBAQIBAQICAgICAgICAwUDAwMDAwYEBAMFBwYHBwcGBwcICQsJCAgKCAcHCg0KCgsMDAwMBwkODw0MDgsMDAz/2wBDAQICAgMDAwYDAwYMCAcIDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAz/wAARCAFNBPcDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD9/KKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAorP8R+LNL8H2P2nVtRsdMt84El1OsKk+gLEZNeR+N/2+PAPhNWFpPfa3IAf+PWDZGreheTbx7qGrzsdm+CwaviqsYer1+7dkSqRj8TPbKK+L/Gv/AAVOu923R9H0mzGCCbiWS8Yn1GzYB+Oa8s8Uf8FCPHviJXQa3eQRk5C20MVtt+jKu/8AM18nivEbKaWlLmn6Ky/8mt+RwVM2w0PtH6R0V+UGvftI+LPEn/H5rOrXgHQXOoTTAfm1YJ+ImpTSZ3R89eD/AI14lTxSpp+5hm/WVv8A21nK89o9Efr7RX5V/sv3/jTxz+1Z4Z0/wtrOqadMbiK41M20zrbraRMHkMqA7WBUY2twxZV71+qlfccOZ9/auHeI9m4Wdt7/AHOy/I7sDjliYuSjZJ2CiisvxJ430bwdGratqunaYHBK/arlIt+PQMRn8K96c4wjzTdl5nde25qUV53cftYfDy2kZX8UWWVODiORh+BC4NX9G/aN8B68o+z+LdBBPRZrxIGP/AXIP6VxRzTBSfLGtFv/ABL/ADI9rDujtaKjtbqO9t0lhkSWKQbkdGDKw9QR1qSu8sKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiior6+h0yzluLmaK3t4VLySyuESNR1JJ4AobsrsCWmTTpbQtJIyxxxqWZmOAoHUk+lfPfxs/4KG+GvAEU0GgImt3SZU3UjGKzjbnofvScjouAR0avjv41ftl+J/i7cOt5qE09tuyluuYrSP0xEPvEZ4LZb3r4fOOPcvwbdOh+9n/AHdvnLb7rnnYrNKFD4nqfdHxS/bh8EfDlJYra7PiC9jB/d2JBhU443TH5Me67iPSvmT4s/8ABS/xL4kMsGkSQaLbHIC2K+ZMQezTP390CmvlvUtautXfNxM8noucKPoOlQAYFfmWZ8aZrjbrn9nHtDT8d/xS8j5zE5/VnpTVkdX4p+MGt+MNRe6u7uea4k+/NPK08zfV2ya566vpr58zSySn/aYnFQIOKdXyDu5cz3PInXqVNZu4qDJqZRgUyNakpMlBTNQvl0nT3mb+EcD+8ewqWJdzV6J+x7+z+37UX7QVpp1zC0nhfw4Rfaw2DslAPywZ9ZGG3GQdokI6V2ZbgKmNxMMNSWsmb06cpyVOG7PrX/gl9+zYfhX8JX8YatBjxF40Vbhd6jdbWX3okHcF8+YeeQYwQCtfUNIiCNAqgKqjAAHAFLX9OZdgaeCw0MNS2ivv7v5n3+Gw8aFJUobI+fv2w/2sn+E2/QdElSLVPLD3l2VDGzVgCqIDwZGBByRgAjHJyvxB4l+KOp+JNRmuZZ5XmmYs80zmWaQ+rM2cmr/xz8bT+OfHV7fTMS17PJdsM9C7EgfQDgVxor+duJeIMRmWLlJyfIn7q6Jf592fN43GTqVGk9EWn1u7lOWuJT/wKk/tGdh80hP1qADFN0JrvxVq32HRdM1LWrvGfLsrZpmx9FBP6V87CNSo+WF2zznUt8TOi8HfE7xH8PLz7RoWtajpMpILfZrho1kx2ZQcMPYgivoP4P8A/BULV/DcsVp490z+1bTodSsI1iuF68tHxG/b7uzA7GvmrV9B1bwu6jV9F1fSS3T7ZaPDn/voCohtmToGB65Fe1l+fZnlc7UpuP8Ade33PT9Tqo16sP4Urf12P1Y+G3xT8P8Axf8ADUer+G9VtdVsHO0vE3zRN/ddThkb2YA8j1roK/JT4e+N/EvwL8Wx+IPB+oy2N2mPPtid0F4neOROjKfQ8jqpBANfoR+yV+2PoX7UOgPEqrpXiiwTdf6W75IGcebET9+PJGe6kgHqpb9p4Y4zw+apUanuVe3R+n+X5nuYLNFVl7KsuWf4P0/yPY6KKK+1PWCiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAorC+IXxJ0X4WeHn1PXL6KxtVO1d3LzN2VFHLN7D3J4BNfEP7UX/BRHUvFgn0vRDNo+mMCvkxSYu7lf8Apq4+4pH8C+pBLCvnc94nwWVRtWfNN7RW79ey838rnLisZSw8eao7H0x8eP20/C/wbS4tbeRNb1iHIeCGULDbEHB82XkAjn5Rk5GDjOa+GPj5+2f4j+MV6y3V6ZbdWzHbRAx2cB/2Uzlj1+ZiT74rx/XfFN54jnzO/wC7Bysa8Iv4f41RHWvxfO+KMfmjcasuWn/I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H4rqvmfrfRXm37JXx0T9of4FaP4hZo/7S2m01NE48u6jwH47BgVcDssi16TX9O4bEQr0o1qTvGSTXzPpKVWNSCqQ2eoUUUVsaBRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABX5n/8ABW79up4dRuPDmkzg2OlTtbQRgnbe3i8SSsO6RcqB3OTnDDH6YV+CH7evwt8QeF/iCf7RhmEmkq1leQnJa3lDsS59VYEYYcEAHOCK/SvDLLsNiMwlVr7wSsvW+vytb597H5P4t5nisNl1PD0L8tRvma8rWXzvf5drngOpalPrF/LdXUrz3E7F5JHOWcnuayvEt4tlodyx/iQovuTxV2WVYYy7HCqMkntX1b/wSb/4Jq6p+2t8TdP8d+LLBrX4TeGrwSqk6Y/4SW4jP+oQEcwhhiVumMxj5ixT9xzvN8PluEliK7sktF37JH4HkGSYrNcZDDYeN23q+iXVv0P1R/4Ji/C27+DP7Afws0G/Vo72PQ472eNwQ0L3TNdGMg9CvnbSPUGvd6KK/kvE15V6060t5Nv73c/s3C0I0KMKENopJfJWCiiisDcKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiqXiHxFY+EtDutS1K6hsrCyjMs88rbUjUdz/nmnGLk+WO4pSUU5SdkiTV9XtdA0ue9vZ4rW0tUMs00rbUjUckk18Iftoft5yeMRNoegvJbaGeNv3JtR/2pO6x5HCdT1PouB+2j+3HL8VdQbTdHlMei27Zgt93Mx7TTAd/7qdvrXy1dXUl9cPLK7SSSHLMepNfjXGfFVetVnl2HThCOkrpqUvKz1Ufxfpv8lm3EEVH2eFd79VsS6tq9xrd609zIXdvyUegHYVWoor8720R8XKTk7y3DNdT8BPgP4m/aj8fp4e8MW5EEZVtQ1F1P2ewjOfmdh64O1erEHHAJEn7O37PPiD9rT4lr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fiJxW87zRypP9zT92Hn3l8/ySCiiisj4IKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKK+D/+CpX/AAUStfAGi6n4J8O3vABttZvbd8u7nINnER37SHtyvZqN9Ee5w9kGKzjGRweFWr3fRLu/61Y3/gor/wAFR7PwHpN94b8E6njGYL3WLV/3jv3htWB69jKOB/Cf4q/J7x78R7/4har5ty2y3QnybdT8sY/q3qf5dKqeM/Gl7451l7y8f2jjH3YV/uj/ADzWUp+au6lS5dXuf2JwrwlgsjwqoYde91l1b8/606E46V63+xx+xV4x/bc+JS6F4bh+yaXalX1XWZ0P2XTYiepI+9I3OyMHLEH7qhmXV/YW/YW8TftufE9NJ05ZNP8AD9gVk1nWHj3RWER6KOm6V8EImeeScKrEfuT8AvgF4X/Zn+F+n+EfCOnrp+k2C5JOGmu5SBvmmfA3yNgZPsAAFAAirWtpE+Q8QvEank0HgsFaWIa+UF3fn2Xzelr87+yT+x14K/Yz+G6eH/CNgFlmCtqOpzKGvNUlA+/K+OgydqDCrk4GSSW/tj/tUaV+yR8GrzxFeeXc6pPm20jTy3zX1yR8oIzny1+857KMD5ioPbfFP4o6J8GPAOpeJfEN6ljpOlxGWaRjyx/hRB/E7HAVRySQK/EL9vz9tHWf2qPitdaldO1vZxA2+m2CvlNMts8L7yv9527nAGFCheeEXOVj8W4L4XxfE2ZvFYxuVNO85P7T/lv+fZfI8l+MnxQ1P4s+PNT1nV76XUdR1G5e5u7lzzPKxyTxwFHQAcADAGMVydNQ80lzcx2du8srBI0GWY9AK9BRUVZH9dUaMKNNUqaslohLu/i0y2eedxHFGMsxr7C/4JAf8ExJ/wBr3xja/Fb4i6a6fDbRZydF0y4T5PEc6MQWYd7eN1+bjEjDZyFcVyH/AAS7/wCCZeqf8FAvHEXi3xZFdaZ8INCuirAMY5vEc6HmCI9RGDxJIOgyiHeWZP3J8PeHrDwjoNlpel2Vrp2madAltaWltEsUNtEihUREUAKoAAAAwAKwrVbe7E/EfE/xEWGjLKMsl+8ek5L7Pkv73f8Al9draKI1AAAAGAAOlLRRXGfzWFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRVDxN4msPBvh+81XVLqKy0+wiaa4nkPyxqOp9T7Ack4A5oHGLk1GKu2ZHxd+LOj/AAT8B3niDW5/KtLUYSNceZdSH7sUY7s36AEnABI/Gb9vL9sfVvjf491AvcYuJ/3MixvmOwgBO22jP4nceMknuTj0T/gpB+3xefFnxa1vp7yW1pAGj0y0Lc2kR4M8g6ea+On8IwMnGW+Jp5GlcsxLMxySTkk10Uad/eZ/UHhnwEsvo/2hjY/vZbLsu3+f+S1jqKY/NUtQyHLV2H7REjnmW2haRyFRAWYnsBX3H/wQa/Yak+MXxNm+O/iuxz4f8NzPaeE7eeP5bq8Xh7sA8FYckKcEeaxIIaGvkr9l/wDZl1v9ub9pHRvhpoTS29nIwvNf1JE3LplihHmSHtu5VVB4aR0BIBJH9Cvwt+GWifBf4c6L4U8N2MWm6F4fs47Gyto+kcaDAyerMerMeWYkkkkmuevOy5Ufivi7xl9Tw39kYWX7yovet0j29Zbel/I36KKK4z+YAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooA+ff+Chv7WC/s1fCN7fT7pIPEmuxulrJu5sIVH725PoQDhf9o552EV+GHxV+I0/xH8TPcsz/ZYiVt0Y8gZ5Y/7TdT+A7V9Mf8FWv2mZvjB8ZNWaC4L2dxObKywThLGBiq49PMfdIfdmr5Arrw8NOY/sPw04VhlO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Mm+1DcONySSZERPfyggPpXvdFFZNt6s+ExmOxGLqOtipucn1k23+IUUUUjlCiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigD82f+CmX/BGLxB8VfiJqXxI+D15atrOqkS6p4bv5xFHcyAYL20rfIhbgmNyqgliHAISviHTP+Cb/AO0/r/iNtGt/hBqlvdqSrXF1PBDaLjqRM8ojP4Mc9s1/QJRWsa0oqx+mZL4rZ1l2FWEXLUUVZOSd0u2jV/nr5n5lfsTf8G/Fp4e12y8V/HfV7TxXqFuyzW/hnT3b+zYW6gXEhCmbBxmNAqZXlpVJFfpdpmmW2iabb2dnbwWlnaRLDBBDGI44Y1ACoqjhVAAAA4AFT0VEpuTuz47PeIswziv7fH1OZ9Fsl6L+m+rCiiipPECiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooA//ZCmVuZHN0cmVhbQplbmRvYmoKNCAwIG9iago8PC9GaWx0ZXIvRmxhdGVEZWNvZGUvTGVuZ3RoIDc1OTE+PnN0cmVhbQp4nOVd2ZLcNpZ911cgHDEOuV3JIrjTERMxsjarW7JkVXXPi16oTFYV7dxEMiXV39Sn9r1YuFwwl9tZ8zQ9Ey4BIM89uDhYCSC/PPnyRHqx+PbE92Jf5omgfz++fhL4Ik1DL8jE6kmcxjawfHL15A/4vy9Pfr3GZ7IoENeLJ77w8c/lKykkRNw8eSp+uv7TRvtiJpPBYxCOYi8NRmn7342yyIskBl5eo2khI+ASC3wiTCEjyDXL4B/zlbisVre+eLERfyBDqR6S/RPXqyfd2wA4GwYAffCKTDNMyWTmZfga8ksUvQ/15s9y3v6CLDXXy1eBSXu9WdTln+L3Td3eiZdNW7TlhXhRLu8qfG6PBd8Lk7GJ38piId7f3FTz8pAZYLIt67YqG/G2WlVtudhvxZdeHoytGJT3a+AYA99Xy82mvhAfKshgc1esi9sCgi+Lpi3rtXj5fVuXTSN+q27vvhX3F3tNpXnoRenY1L+qv+7qzbISn56+/PTThXi3W30uKjETke/7aX4hrsvlL+LnXM6CYJbIJBdZ4ieuiShPvCSYKpUX4OpfRJBe+vIy8AO5990U/oTjd5+tv4uX+1/IUi8jBfSiLm7aWSF3QeOr/32/l++ePfvjAIj0gs7/EUoRYLZ18VfR3HnVYl2sq/+5VQWy7UrVm29WLiKKOY6xNhq01KD9c121ILa2qJaNee2L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Pj4vWE9iamVjdDw8L2ltZzAgMiAwIFI+Pj4+L1BhcmVudCA1IDAgUi9NZWRpYUJveFswIDAgNTk1IDg0Ml0+PgplbmRvYmoKMSAwIG9iago8PC9TdWJ0eXBlL1R5cGUxL1R5cGUvRm9udC9CYXNlRm9udC9IZWx2ZXRpY2EvRW5jb2RpbmcvV2luQW5zaUVuY29kaW5nPj4KZW5kb2JqCjMgMCBvYmoKPDwvU3VidHlwZS9UeXBlMS9UeXBlL0ZvbnQvQmFzZUZvbnQvSGVsdmV0aWNhLUJvbGQvRW5jb2RpbmcvV2luQW5zaUVuY29kaW5nPj4KZW5kb2JqCjUgMCBvYmoKPDwvS2lkc1s2IDAgUl0vVHlwZS9QYWdlcy9Db3VudCAxPj4KZW5kb2JqCjcgMCBvYmoKPDwvVHlwZS9DYXRhbG9nL1BhZ2VzIDUgMCBSPj4KZW5kb2JqCjggMCBvYmoKPDwvTW9kRGF0ZShEOjIwMjEwMTI3MTUyNjU1KzA1JzMwJykvQ3JlYXRpb25EYXRlKEQ6MjAyMTAxMjcxNTI2NTUrMDUnMzAnKS9Qcm9kdWNlcihpVGV4dK4gNS40LjIgqTIwMDAtMjAxMiAxVDNYVCBCVkJBIFwoQUdQTC12ZXJzaW9uXCkpPj4KZW5kb2JqCnhyZWYKMCA5CjAwMDAwMDAwMDAgNjU1MzUgZiAKMDAwMDA1ODU3MiAwMDAwMCBuIAowMDAwMDAwMDE1IDAwMDAwIG4gCjAwMDAwNTg2NjAgMDAwMDAgbiAKMDAwMDA1MDcyNCAwMDAwMCBuIAowMDAwMDU4NzUzIDAwMDAwIG4gCjAwMDAwNTgzODMgMDAwMDAgbiAKMDAwMDA1ODgwNCAwMDAwMCBuIAowMDAwMDU4ODQ5IDAwMDAwIG4gCnRyYWlsZXIKPDwvSW5mbyA4IDAgUi9JRCBbPGYyZTFmYzZjNTg2NTVlODViYTVkY2RhZmUzYTI2YWE1PjxiYjgyMGEyM2MxMDc2ZThiMTRkNTlmYzVhMzI0NDFkNj5dL1Jvb3QgNyAwIFIvU2l6ZSA5Pj4KJWlUZXh0LTUuNC4yCnN0YXJ0eHJlZgo1OTAwMgolJUVPRgo='+'\n';
			         emlAttachment += data+'\n';
			         
			         emlAttachment += ''+'\n'; 
			         emlAttachment += '----boundary_text_string--'+'\n';
			         emlAttachment += ''+'\n';
		         }
		         
		         
		        
		        
		         
		        var link = document.getElementById('downloadlink');
		     	
		        var textFile = null,
		     	
		     	makeTextFile = function (text) {
		     		var data = new Blob([text], {type: 'text/plain'});
		     		if (textFile !== null) {
		     		  window.URL.revokeObjectURL(textFile);
		     		}
		     		textFile = window.URL.createObjectURL(data);
		     		return textFile;
		     	};
		
		     	var create = document.getElementById('create'),
		     	textbox = document.getElementById('textbox');
		     	
		 		var link = document.getElementById('downloadlink');
		 		link.href = makeTextFile(emlContent + emailHTML + emlAttachment);
		 		link.style.display = 'block';
		 		link.click();
		        
		 		$('#emailBtnFloat').html('<i class="fa fa-envelope printficon" style="line-height: 50px !important; font-size: 22px !important;"></i>');
		        //----------------- END Cost Sheet Email
	         }
	         
	         
         	
             
             
             
       });
       
       //alert ("Test 123 ::: " + $('.getCustomerPD table').html());
       
       //$('.getUnitDetailsPD table').html();
       
       
       
}


/*function printApplicationForm() {
       //var enqSfid = $('#enquirysfid').val();
       
       //$('#printBtnFloat').empty();
       //$('#printBtnFloat').html('<i class="fa fa-spinner fa-spin printficon"></i>');
       
       $.post(pageContext+"printApplicationForm",{"enqSfid":$('#enquirysfid').val(),"appFormData":$('#printApplicationForm').html()},function(data){                           
             
       }).done(function(data){
             
       });
}*/




//Add more payment type - costsheet
var amI = 0;
var amNum = 9; 

var amNumRowID = 0;

function addMorePtBtn () {
	
	amNumRowID = amI+1;
	
	
	var id= "'"+"csPayRowID"+amNumRowID+"'";
		
	var attachPAN = "'attachPAN'";
	var attachRec = "'attachRec'";
	
	
	
       if(amI < amNum) { 
             $('#csPtCol tr:last-child').after('<tr id="csPayRowID'+amNumRowID+'" class="csPtDataRow csPtFileSize"><td class="txtCenter"><input style="display:none;" class="gpl_cs_payment_details_id" value="-1"><input onchange="csPtcalculateGrandTotal()" type="checkbox" class="paymentCScheck" checked></td><td><select onchange="csPtDd(this)" class="full form-control input-sm csPtDropDown requiredField"><option value="">Select</option><option value="Cheque">Cheque</option><option value="NEFT">NEFT/Credit</option><option value="Swipe">Swipe</option><option value="Wire Transfer">Wire Transfer (PayZap, Google Pay)</option></select></td><td><input class="full form-control input-sm csPtBankName requiredField" placeholder="Bank Name"/></td><td style="display:none;"><input class="full form-control input-sm csPtBranch" placeholder="Branch Name"/></td><td><input class="full form-control input-sm csPtTransactionId checkDuplicate requiredField" placeholder="Transaction ID" /></td><td><input onkeydown="return false" type="date" class="full form-control input-sm csPtTransactionDate requiredField" placeholder="Transaction Date"/></td><td><input  class="numericField numericWithDecimal full form-control input-sm csPtTransactionAmount requiredField" maxlength="13" onkeyup="csPtcalculateGrandTotal()" placeholder="Transaction Amount" name="amount"/></td> <td style="display:none;"> <input type="file" class="full form-control input-sm panAttach"/> <input class="panAttachWebcam"/> <a  class="webcamBtn" data-toggle="modal" data-target="#webcamBox" onclick="webcamAttachment(this, '+id+', '+attachPAN+')">or <span>Capture Image</span></a> </td> <td> <input type="file" class="full form-control input-sm receiptAttach"/> <input class="receiptAttachWebcam"/> <a  class="webcamBtn" data-toggle="modal" data-target="#webcamBox" onclick="webcamAttachment(this, '+id+', '+attachRec+')">or <span>Capture Image</span></a> <td><textarea class="full form-control input-sm csPtDescription" placeholder="Description"></textarea></td><td class="removeCsPtCol txtCenter"><i onclick="removeCsPtCol(this)" class="fa fa-times-circle"></i></td></tr>');
             amI++;
       }else {
             swal({
                    title: "You've reached the limit",
                 text: "",
                 timer: 2000,
                 type: "error",
             });
       }
       
       paymentTrxValidatore();
}

function removeCsPtCol (e) {
       $(e).closest("tr").remove();
       amI -= 1;
       
       csPtcalculateGrandTotal ();
}

//function paymentCheck (e) {
	 //if ($(e).is(':checked')) {
		// alert('You have Checked it');
	 //} else {
		// alert('You Un-Checked it');
		// csPtcalculateGrandTotal ();
	 //}
//}

/*$(document).ready(function () {
	var ckbox = $('.paymentCScheck');
	
	$('input').click(function () {
	   
	});
});*/






function csPtDd (e) {
       $(e).closest("tr").find('.csPtReachMexLength').remove();  
       if ($(e).val() == 'Cheque') {
    	   $(e).closest("tr").find(".csPtTransactionId").val("");
    	   $(e).closest("tr").find(".csPtTransactionId").attr("maxlength","10");       
           $(e).closest("tr").find(".csPtTransactionId").after("<small class='csPtReachMexLength'>ID can be max 10 characters long.</small>");              
           $(e).closest("tr").find('.panAttach').prop('disabled', false);
           $(e).closest("tr").find('.csPtBankName').prop('disabled', false);
           $(e).closest("tr").find('.csPtBranch').prop('disabled', false);
       } else  if ($(e).val() == 'NEFT') {
    	   $(e).closest("tr").find(".csPtTransactionId").val("");
    	   $(e).closest("tr").find(".csPtTransactionId").attr("maxlength","30");
    	   $(e).closest("tr").find(".csPtTransactionId").after("<small class='csPtReachMexLength'>ID can be max 30 characters long.</small>");
           $(e).closest("tr").find('.csPtBankName').prop('disabled', false);
           $(e).closest("tr").find('.csPtBranch').prop('disabled', false);
       }else  if ($(e).val() == 'Swipe' || $(e).val() == 'Wire Transfer') {
    	   $(e).closest("tr").find(".csPtTransactionId").val("");
    	   $(e).closest("tr").find(".csPtTransactionId").attr("maxlength","30");
    	   $(e).closest("tr").find(".csPtTransactionId").after("<small class='csPtReachMexLength'>ID can be max 30 characters long.</small>"); 
           $(e).closest("tr").find('.csPtBankName').prop('disabled', false);
           $(e).closest("tr").find('.csPtBranch').prop('disabled', false);
       }
       else {
    	   $(e).closest("tr").find(".csPtTransactionId").val("");
    	   $(e).closest("tr").find(".csPtTransactionId").removeAttr("maxlength");
           $(e).closest("tr").find('.panAttach').val("");
           $(e).closest("tr").find('.csPtBankName').val("");
           $(e).closest("tr").find('.csPtBranch').val("");
       }      
}


$(document).on("keypress keyup blur", ".csPtTransactionId", function (event) {
    if($(this).val().length > 1 && $(this).attr("maxlength") == undefined){
       $(this).closest("tr").find('.csPtReachMexLength').remove();
       $(this).after("<small class='csPtReachMexLength' style='color:#f00 !important;'>Please select the payment type</small>")
    }
});


function csPtcalculateGrandTotal() {
    var grandTotal = 0;
    $("#csPtCol").find('.paymentCScheck:checked').each(function () {
        grandTotal += +$(this).closest('tr').find('input[name="amount"]').val();
    });
    $("#csPtGrandtotal").text(grandTotal.toFixed(2));
}



function csPymtData (balance_details_primeryId) {
       
       var i = 0;
       var k = 0;
       
       $("#csPtCol .csPtDataRow").each(function () {
             //attach file
             var formData = new FormData();
             
             
             formData.append('panAttach', $(this).find('.panAttach')[0].files[0]);
             formData.append('receiptAttach', $(this).find('.receiptAttach')[0].files[0]);
             formData.append('rowCount', i);
             
             formData.append('panAttachWebcam', $(this).find('.panAttachWebcam').val());
             formData.append('receiptAttachWebcam', $(this).find('.receiptAttachWebcam').val());
             
              formData.append('enqID', $('#enquirysfid').val());
             
             $.ajax({
                    url : 'FileUploadServlet',
                    type : 'POST',
                    data : formData,
                    processData : false,
                    contentType : false,
                    success : function(data) {
                    
                    }
             });
             
             i++
             //END attach file 
       });
       
       
       
       
       
       
       
       var arrayData = [];
       $("#csPtCol .csPtDataRow").each(function () {
          
             if ($(this).find('.paymentCScheck').is(':checked')) {
                    var csPtData = {};
                 csPtData.enq_sfid = $('#enquirysfid').val();
                 csPtData.payment_type = $(this).find('.csPtDropDown').val();
                 csPtData.bank_name = $(this).find('.csPtBankName').val();
                 csPtData.branch = $(this).find('.csPtBranch').val();
                 csPtData.transaction_id = $(this).find('.csPtTransactionId').val();
                // csPtData.transaction_date = $(this).find('.csPtTransactionDate').val();
                 csPtData.transaction_amount = $(this).find('.csPtTransactionAmount').val();
                 csPtData.description = $(this).find('.csPtDescription').val();
                 csPtData.total_amount = $('#csPtGrandtotal').text().trim();
                 csPtData.gpl_cs_balance_details_id = balance_details_primeryId.id;
                 //csPtData.offerid = balance_details_primeryId.offer_sfid;
                 csPtData.offer_sfid = balance_details_primeryId.offer_sfid;
                 csPtData.bankGL=$('#towerBankGLCode').val();
                 csPtData.userid = $('#userid').val();
                 
                 csPtData.transaction_date_string = $(this).find('.csPtTransactionDate').val();
                 
                 csPtData.gpl_cs_eoi_payment_details_id = $(this).find('.gpl_cs_payment_details_id').val();
                 
                 
                 if($(this).find('.panAttach').val() != "" ||  $(this).find('.panAttach').attr('data-fileName') != "" &&  $(this).find('.panAttach').attr('data-fileName') != undefined) {
                    if($(this).find('.panAttach').attr('name') == "panAttach") {
                          csPtData.pan_attach = $(this).find('.panAttach').attr('data-fileName');
                    } else {
                          csPtData.pan_attach = k+"PAN_"+$(this).find('.panAttach')[0].files[0].name;
                    }
                 }else {
                    csPtData.pan_attach ="";
                 }
                 
                 if ($(this).find('.receiptAttach').val() != "" || $(this).find('.receiptAttach').attr('data-fileName') != "" && $(this).find('.receiptAttach').attr('data-fileName') != undefined) {
                    if($(this).find('.receiptAttach').attr('name') == "receiptAttach") {
                          csPtData.cheque_attach = $(this).find('.receiptAttach').attr('data-fileName');
                    } else {
                          csPtData.cheque_attach = k+"Receipt_"+$(this).find('.receiptAttach')[0].files[0].name;
                    }
                 }else {
                    csPtData.cheque_attach ="";
                 }
             
                 
                 arrayData.push(csPtData);
                 k++
             }
       });
       
       $.post(pageContext+"getPaymentDtl",{"paymentDtlJson" : JSON.stringify(arrayData)},function(data){                         
       }).done(function(){
             updatePaymentDtl();
       });
}





$("#carParkType").change(function () {
    
	
	
	
	$('#carParkCountDD').empty();
    if ($('#carParkType option:selected').val() != -1) {
	
    	if ($('#projectId').val() == 'a1l2s000000PJPmAAO' || $('#projectId').val() == 'a1l6F000002X6IOQA0') {
    		$('#carParkCountDD').append('<select class="full form-control"><option value="1">1</option> </select>');
    	} else {
    		$('#carParkCountDD').append('<select class="full form-control"><option value="1">1</option> <option value="2">2</option> <option value="3">3</option>  <option value="4">4</option> </select>');
    	}
    	
    	
		
    	
    	
    	if ($('#projectId').val() == 'a1l6F000009D6IMQA0') {
			$('#carParkCount').hide();
		} else {
			$('#carParkCount').show();
		}
    } else {
        $('#carParkCountDD').append('<select class="full form-control"><option value="0">0</option></select>');
        $('#carParkCount').hide();
    }
});

getProjectDtl ();


function getProjectDtl () {
       $.post(pageContext+"getProjectDtl",{"projectId":$('#projectId').val()},function(data){                           
       
       }).done(function(data){
             var obj =JSON.parse(data);
             
             $('#stampDuty').val(obj[0].stamp_duty__c_string);
             $('#marketingProjectName').val(obj[0].marketing_project_name__c);
             
             $('#region__c').val(obj[0].region__c);
             
       });
}












function getEOIPaymentRecord () {
       
       //$('#csPtCol tbody').empty();
       
       $('#csPtCol').find("tr:gt(0)").remove();       
       
       
       $('#csPtGrandtotal').text("");
       
       $.post(pageContext+"getEOIPaymentRecord",{"enqSfid":$('#enquirysfid').val()},function(data){
             
             var html = '';
             var obj =JSON.parse(data);
             
             
             var trans_date = '';
             
             var panTarget = '';
             var reciptTarget = '';
             
            var eoiTransactionTotalAmount = 0;
             
             var checkBox = '';
             var disableRow = '';
             var status = '';
             
             var csPayRowID0 = "'csPayRowID0'";
             
             var attachPAN = "'attachPAN'";
         	 var attachRec = "'attachRec'";
             
             if(obj!=null){
                    
                    for(i = 0; i< obj.length; i++){    
                           if(obj[i].pan_attach!=null)
                        	   panTarget = pageContext+"file?name="+obj[i].pan_attach+"&from=EOIbookingReference&eid="+obj[i].enq_sfid+"&fid="+obj[i].pan_attach.charAt(0);
                          if(obj[i].cheque_attach!=null)
                        	  reciptTarget = pageContext+"file?name="+obj[i].cheque_attach+"&from=EOIbookingReference&eid="+obj[i].enq_sfid+"&fid="+obj[i].cheque_attach.charAt(0);
                           
                           if (obj[i].transaction_date != '') {
                                 var date = new Date(obj[i].transaction_date);

                                 var curr_date = date.getDate();
                                 var curr_month = date.getMonth() + 1; //Months are zero based
                                 var curr_year = date.getFullYear();
                                 
                                 trans_date = curr_date + "-" + curr_month + "-" + curr_year;
                           }else {
                                 trans_date = '';
                           }
                           
                           var checkDuplicate = '';
                           //eoiTransactionTotalAmount = parseFloat(parseFloat(obj[i].transaction_amount)+parseFloat(eoiTransactionTotalAmount)).toFixed(2);
                           
                           if (obj[i].isactive != 'Y') {
                                 checkBox = 'disabled';
                               disableRow = 'style="background-color: #ffd4d8;"';
                               status = '<br> Not Approved';
                               
                               checkDuplicate = '';
                           } else {
                                 checkBox = 'checked';
                                 disableRow = '';
                                 status = '<br> Approved';
                                 
                                 checkDuplicate = 'checkDuplicate';
                                 
                                 eoiTransactionTotalAmount = parseFloat(parseFloat(obj[i].transaction_amount)+parseFloat(eoiTransactionTotalAmount)).toFixed(2);
                           }
                           
                           
                           html +=      '<tr class="csPtDataRow moveToOffer" '+disableRow+'>'
                                                     + '<td class="txtCenter"><input style="display:none;" class="gpl_cs_payment_details_id" value="'+obj[i].id+'">  <input onchange="csPtcalculateGrandTotal()" type="checkbox" class="paymentCScheck" '+checkBox+'> </td>'
                                                     + '<td class="txtCenter"><input class="full form-control input-sm csPtEnqSfid csPtDropDown" value="'+obj[i].payment_type+'" style="display:none;" disabled >'+obj[i].payment_type+'</td>' 
                                                     + '<td class="txtCenter"><input class="full form-control input-sm csPtBankName" value="'+obj[i].bank_name+'" style="display:none; " disabled>'+obj[i].bank_name+'</td>' 
                                                     + '<td class="txtCenter" style="display:none;"><input class="full form-control input-sm csPtBranch" value="'+obj[i].branch+'" style="display:none; " disabled>'+obj[i].branch+'</td>' 
                                                     + '<td class="txtCenter"><input class="full form-control input-sm csPtTransactionId '+checkDuplicate+'" value="'+obj[i].transaction_id+'" style="display:none; " disabled>'+obj[i].transaction_id+'</td>' 
                                                     + '<td class="txtCenter"><input class="full form-control input-sm csPtTransactionDate" value="'+obj[i].transaction_date+'" style="display:none; "  disabled>'+trans_date+'</td>' 
                                                     + '<td class="txtCenter"><input class="full form-control input-sm csPtTransactionAmount"  value="'+obj[i].transaction_amount+'" name="amount" style="display:none; " disabled>'+obj[i].transaction_amount+'</td>' 
                                                     + '<td class="txtCenter" style="display:none;"><input style="display:none;" class="full form-control input-sm panAttach" type="file" data-fileName="'+obj[i].pan_attach+'"  name="panAttach"> <a target="_blank" href="'+panTarget+'">'+obj[i].pan_attach+'</a></td>' 
                                                     + '<td class="txtCenter" style="word-break: break-word;"><input style="display:none;" class="full form-control input-sm receiptAttach" type="file" data-fileName="'+obj[i].cheque_attach+'" name="receiptAttach"> <a target="_blank" href="'+reciptTarget+'">'+obj[i].cheque_attach+'</a></td>'
                                                     + '<td class="txtCenter"><input class="full form-control input-sm csPtDescription" value="'+obj[i].description+'" style="display:none;" disabled>'+obj[i].description+'</td>'
                                                     + '<td > </td>'
                                               "</tr>";
                    }
                    
                    $('#csPtGrandtotal').text(eoiTransactionTotalAmount);
                    
                    html = html.replace(/undefined/g, "");
                    
                    
                    html += '<tr><th colspan="11"  style="text-align:left; font-size: 14px; background-color: #ebebeb;">Payment at the time of booking</th></tr>'
                           
                           +' <tr id="csPayRowID0" class="csPtDataRow csPtFileSize" ><td class="txtCenter">'
                                 +'<input style="display:none;" class="gpl_cs_payment_details_id" value="-1">'
                                 + '<input type="checkbox" class="paymentCScheck" onchange="csPtcalculateGrandTotal()" checked>'
                           + '</td>'
                           + '<td>'
                                 + '<input class="csPtEnqSfid" style="display:none;" />'
                                 + '<select onchange="csPtDd(this)" class="full form-control input-sm csPtDropDown requiredField">'
                                 + '<option value="">Select</option>'
                                 + '<option value="Cheque">Cheque</option>'
                                 + '<option value="NEFT">NEFT/Credit</option>'
                                 + '<option value="Swipe">Swipe</option>'
                                 + '<option value="Wire Transfer">Wire Transfer (PayZap, Google Pay)</option>'
                                 + '</select>'
                           + '</td>'
                           + '<td>'
                                 + '<input class="full form-control input-sm csPtBankName requiredField" placeholder="Bank Name"/>'
                           + '</td>'
                           + '<td style="display:none;">'
                                 + '<input class="full form-control input-sm csPtBranch" placeholder="Branch Name"/>'
                           + '</td>'
                           + '<td>'
                                 + '<input class="full form-control input-sm csPtTransactionId checkDuplicate requiredField" placeholder="Transaction ID"/>'
                           + '</td>'
                           + '<td>'
                                 + '<input onkeydown="return false" type="date" class="full form-control input-sm csPtTransactionDate requiredField" placeholder="Transaction Date"/>'
                           + '</td>'
                           + '<td>'
                                 + '<input class="numericField numericWithDecimal full form-control input-sm csPtTransactionAmount requiredField" maxlength="13" onkeyup="csPtcalculateGrandTotal()" name="amount" placeholder="Transaction Amount"/>'
                           + '</td>'
             
                           + '<td style="display:none;">'
                                 + '<input type="file" class="full form-control input-sm panAttach"/>'
                                 + '<input class="panAttachWebcam"/>'
                                 + '<a  class="webcamBtn" data-toggle="modal" data-target="#webcamBox" onclick="webcamAttachment(this, '+csPayRowID0+', '+attachPAN+')">or <span>Capture Image</span></a>'
                           + '</td>'
                           + '<td>'
                                 + '<input type="file" class="full form-control input-sm receiptAttach"/>'
                                 + '<input class="receiptAttachWebcam"/>'
                                 + '<a  class="webcamBtn" data-toggle="modal" data-target="#webcamBox" onclick="webcamAttachment(this, '+csPayRowID0+', '+attachRec+')">or <span>Capture Image</span></a>'
                           + '</td>'
             
                           + '<td>'
                                 + '<textarea class="full form-control input-sm csPtDescription" placeholder="Description"></textarea>'
                           + '</td>'
                           + '<td class="removeCsPtCol txtCenter"><i onclick="removeCsPtCol(this)" class="fa fa-times-circle"></i></td> </tr>';
                    
                    
                    $("#csPtCol tbody tr:first-child").after(html);
             } else {
                    html += '<tr><th colspan="11" style="text-align:left; font-size: 14px; background-color: #ebebeb;">Payment at the time of booking</th></tr>'
                           
                           +' <tr id="csPayRowID0" class="csPtDataRow csPtFileSize"><td class="txtCenter">'
                                        +'<input style="display:none;" class="gpl_cs_payment_details_id" value="-1">'
                                        + '<input type="checkbox" onchange="csPtcalculateGrandTotal()" class="paymentCScheck" checked>'
                                 + '</td>'
                                 + '<td>'
                                        + '<input class="csPtEnqSfid" style="display:none;" />'
                                        + '<select onchange="csPtDd(this)" class="full form-control input-sm csPtDropDown requiredField">'
                                        + '<option value="">Select</option>'
                                        + '<option value="Cheque">Cheque</option>'
                                        + '<option value="NEFT">NEFT/Credit</option>'
                                        + '<option value="Swipe">Swipe</option>'
                                        + '<option value="Wire Transfer">Wire Transfer (PayZap, Google Pay)</option>'
                                        + '</select>'
                                 + '</td>'
                                 + '<td>'
                                        + '<input class="full form-control input-sm csPtBankName requiredField" placeholder="Bank Name"/>'
                                 + '</td>'
                                 + '<td style="display:none">'
                                        + '<input class="full form-control input-sm csPtBranch" placeholder="Branch Name"/>'
                                 + '</td>'
                                 + '<td>'
                                        + '<input class="full form-control input-sm csPtTransactionId requiredField checkDuplicate" placeholder="Transaction ID"/>'
                                 + '</td>'
                                 + '<td>'
                                        + '<input onkeydown="return false" type="date" class="full form-control input-sm csPtTransactionDate requiredField" placeholder="Transaction Date"/>'
                                 + '</td>'
                                 + '<td>'
                                        + '<input class="numericWithDecimal numericField full form-control input-sm csPtTransactionAmount requiredField" maxlength="13" onkeyup="csPtcalculateGrandTotal()" name="amount" placeholder="Transaction Amount"/>'
                                 + '</td>'
                    
                                 + '<td style="display:none" class="txtCenter">'
                                        + '<input type="file" class="full form-control input-sm panAttach"/>'
                                        + '<input class="panAttachWebcam"/>'
                                        + '<a  class="webcamBtn" data-toggle="modal" data-target="#webcamBox" onclick="webcamAttachment(this, '+csPayRowID0+', '+attachPAN+')">or <span>Capture Image</span></a>'
                                 + '</td>'
                                 + '<td>'
                                        + '<input type="file" class="full form-control input-sm receiptAttach"/>'
                                        + '<input class="receiptAttachWebcam"/>'
                                        + '<a  class="webcamBtn" data-toggle="modal" data-target="#webcamBox" onclick="webcamAttachment(this, '+csPayRowID0+', '+attachRec+')">or <span>Capture Image</span></a>'
                                 + '</td>'
                    
                                 + '<td>'
                                        + '<textarea class="full form-control input-sm csPtDescription" placeholder="Description"></textarea>'
                                 + '</td>'
                                 + '<td> </td>'
                           + '</tr>';
                    $("#csPtCol tbody tr:first-child").after(html);
             }
             
           //  paymentTrxValidatore ();
             
       }).done(function(obj){
    	   
       });    
}





//Update Payment Details from Costsheet
function updatePaymentDtl () {
       var arrayData = [];
       $("#csPtCol .moveToOffer").each(function () {
          
             if ($(this).find('.paymentCScheck').is(':checked')) {
                    var csPtData = {};
                 
                 csPtData.id = $(this).find('.gpl_cs_payment_details_id').val();
                 
                 arrayData.push(csPtData);
             }
       });
       
       $.post(pageContext+"updateForSubmittedOffer",{"paymentDtlJson" : JSON.stringify(arrayData)},function(data){                         
       }).done(function(){
             
       });
}
// END Update Payment Details from Costsheet














//New Drop Down for scheme
function getSchemeSource (){
    $('#getSchemeSource').empty();
    
   /* if ($('#channelPartnerSfidCS').val() != '') {
          var url = pageContext+"getSchemeSource?projectid="+$("#projectId").val()+"&cp_flag_yn=y";
    } else {*/
          var url = pageContext+"getSchemeSource?projectid="+$("#projectId").val()+"&cp_flag_yn=";
    /*}*/
    
    var checkboxScheme = '';
    
	$.getJSON(url, function (data) {
		$('#getSchemeSource').append('<option value="-1">Select</option>');
		$.each(data, function (index, value) {
			$('#getSchemeSource').append('<option value='+value.id+'>'+ value.scheme+'</option>');
		});                              
	}).done(function() {
		//$('#getPln').append('<option value="other" data-valuepercentage="0" data-valueabsolute="0" data-valuerate="0">Others</option> ');
		getSchemeSite ();
	});
}

function getSchemeSite (){
    $('#getSchemeSite').empty();
    

    /*if ($('#channelPartnerSfidCS').val() != '') {

          var url = pageContext+"getSchemeSite?projectid="+$("#projectId").val()+"&cp_flag_yn=y";
    } else {*/
          var url = pageContext+"getSchemeSite?projectid="+$("#projectId").val()+"&cp_flag_yn=";
    /*}*/
    
    var checkboxScheme = '';
    
	$.getJSON(url, function (data) {
		$('#getSchemeSite').append('<option value="-1">Select</option>');
		$.each(data, function (index, value) {
			$('#getSchemeSite').append('<option value='+value.id+'>'+ value.scheme+'</option>');
		});                              
	}).done(function() {
		//$('#getPln').append('<option value="other" data-valuepercentage="0" data-valueabsolute="0" data-valuerate="0">Others</option> ');
		getSchemePromotional ();
	});
}

function getSchemePromotional () {
    $('#getSchemePromotional').empty();
    
    /*if ($('#channelPartnerSfidCS').val() != '') {
          var url = pageContext+"getSchemePromotional?projectid="+$("#projectId").val()+"&cp_flag_yn=y";
    } else {*/
          var url = pageContext+"getSchemePromotional?projectid="+$("#projectId").val()+"&cp_flag_yn=";
    /*}*/
    
    var checkboxScheme = '';
    
	$.getJSON(url, function (data) {
		$('#getSchemePromotional').append('<option value="-1">Select</option>');
		$.each(data, function (index, value) {
			$('#getSchemePromotional').append('<option value='+value.id+'>'+ value.scheme+'</option>');
		});                              
	}).done(function() {
		//$('#getPln').append('<option value="other" data-valuepercentage="0" data-valueabsolute="0" data-valuerate="0">Others</option> ');
	});
}
//END New Drop Down for Scheme


// Carpark Dropdown List
function carparkTypeMstList (){
	$('#carParkType').empty();	
	var urlTower = pageContext+"getCarparkNameCS?projectSFID="+$('#projectId').val();
	$.getJSON(urlTower, function (data) {
		$('#carParkType').append('<option value="-1" data-name="" >Select</option>');
		$.each(data, function (index, value) {
			$('#carParkType').append("<option data-name='"+value.carpark_type+"' value='"+value.id+"'>"+value.carpark_type+ "</option>");
		});					
	}).done(function() {
	});
}
// END Carpark Dropdown List



function paymentTrxValidatore () {
	if (paymentValidCondition) {
		$(".csPtDataRow .csPtTransactionDate").attr("max", sysNowDate);
	}
}


/*This function "viewCostsheet" moved from inventory.js to costsheet .js, because of commonly used for Admin Cost sheet and Sales Cost sheet*/
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
			$('#emailBtnFloat').hide();
			$('#unitSfid').val(sfid);
			$('#towerSfid').val(towerCode);
			$('#costsheetTab a').trigger('click');	
		}
	});
}
/* END This function "viewCostsheet" moved from inventory.js to costsheet .js, because of commonly used for Admin Cost sheet and Sales Cost sheet*/









$(document).on('change', '#csPtCol .paymentCScheck[type="checkbox"]', function(e) {
	if($(this).is(":checked")){
		$(this).closest("tr").find(".csPtTransactionId").addClass("checkDuplicate");
    }
    else if($(this).is(":not(:checked)")){
    	$(this).closest("tr").find(".csPtTransactionId").removeClass("checkDuplicate");
    }
});



function costsheetLogger (cstype) {
	var csSource = "";
	var tokenid = "";
	var useremail = "";
	var schemeName = "";
	
	if ($("#getCSData").data('source') == "SALES") {
		csSource = "SALES MANAGER";
		
		var tokenValidate = basicValidate ("#tokenId", "val");
		if (tokenValidate) {
			tokenid = $("#tokenId").val();
		} else {
			tokenid = -1;
		}
		useremail = $("#useremailID").val();
	} else if ($("#getCSData").data('source') == "ADMIN") {
		csSource = "INVENTORY MANAGER";
		tokenid = -1;
		useremail = USEREMAIL_GV;
	}
	 
	var contactSFIDValidate = basicValidate ("#primarycontactsfid","val");
	var contactSFID = "";
	if (contactSFIDValidate){
		contactSFID = $("#primarycontactsfid").val();
	}
	
	var enqSFIDValidate = basicValidate ("#enquirysfid","val");
	var enqSFID = "";
	if (enqSFIDValidate){
		enqSFID = $("#enquirysfid").val();
	}
	
	var projectSFIDValidate = basicValidate ("#projectId","val");
	var projectSFID = "";
	if (projectSFIDValidate){
		projectSFID = $("#projectId").val();
	}
	var carParkType = "No Car Park"
	if ($('#carParkType').find('option:selected').attr("data-name") != "") {
		carParkType = $('#carParkType').find('option:selected').attr("data-name");
	}
	
	
	var userIDValidate = basicValidate ("#userid","val");
	var useridSFID = -1;
	if (userIDValidate){
		useridSFID = $("#userid").val();
	}
	
	
	
	
	var formData = new FormData();
	formData.append('source', csSource);
	formData.append('costsheet_type', cstype);
	formData.append('token_id', tokenid);
	formData.append('contact_sfid', contactSFID);
	formData.append('enquiry_sfid', enqSFID);
	formData.append('project_sfid', projectSFID);
	formData.append('tower_sfid', $('#towerSfid').val());
	formData.append('inventory_sfid', $('#unitSfid').val());
	formData.append('gpl_cs_scheme_id', $('#getPln').find('option:selected').attr("data-id"));
	formData.append('scheme_type', $("#schemeTypeDD").val());
	formData.append('scheme_name', $("#getPln :selected").text());
	
	formData.append('scheme_rate', $('#getPln').find('option:selected').attr("data-valuerate"));
	formData.append('scheme_absolute', $('#getPln').find('option:selected').attr("data-valueabsolute"));
	formData.append('scheme_percentage', $('#getPln').find('option:selected').attr("data-valuepercentage"));
	formData.append('scheme_zero_govt_charges', $('#getPln').find('option:selected').attr("data-zerogovtcharges"));
	formData.append('carpark_type', carParkType);
	formData.append('carpark_count', $("#carParkCountDD select").val()); 
	formData.append('paymentplan_sfid', $('#ppDropdown').val());
	
	//------------------------------------
	var discountedbspValidate = basicValidate (".a3","text");
	var discounted_bsp = 0;
	if (discountedbspValidate) {
		discounted_bsp = $('.a3').text();
	}
	var ogbspValidate = basicValidate (".a3_bsp","text");
	var og_bsp = 0;
	if (ogbspValidate) {
		og_bsp = $('.a3_bsp').text()
	}
	var carpetareaValidate = basicValidate (".a4","text");
	var carpet_area_sqft = 0;
	if (carpetareaValidate) {
		carpet_area_sqft = $('.a4').text()
	}
	var saleableareaValidate = basicValidate (".a6","text");
	var saleable_area_sqft = 0;
	if (saleableareaValidate) {
		saleable_area_sqft = $('.a6').text()
	}
	var carpetareareraValidate = basicValidate ("#carpetSqm","text");
	var carpet_area_rera_sqmt = 0;
	if (carpetareareraValidate) {
		carpet_area_rera_sqmt = $('#carpetSqm').text()
	}
	var exclusiveareaValidate = basicValidate ("#balTerSqm","text");
	var exclusive_area_sqmt = 0;
	if (exclusiveareaValidate) {
		exclusive_area_sqmt = $('#balTerSqm').text()
	}
	var totalareaValidate = basicValidate ("#totalSqm","text");
	var total_area_sqmt = 0;
	if (totalareaValidate) {
		total_area_sqmt = $('#totalSqm').text()
	}
	var carpetareaamountValidate = basicValidate ("#carpetAreaAmount","text");
	var carpet_area_amount = 0;
	if (carpetareaamountValidate) {
		carpet_area_amount = $('#carpetAreaAmount').text()
	}
	var exclusiveareaamountValidate = basicValidate ("#exclusiveAreaAmount","text");
	var exclusive_area_amount = 0;
	if (exclusiveareaamountValidate) {
		exclusive_area_amount = $('#exclusiveAreaAmount').text()
	}
	var flatunitcostValidate = basicValidate ("#scOtherChrgAmount0","text");
	var flat_unit_cost = 0;
	if (flatunitcostValidate) {
		flat_unit_cost = $('#scOtherChrgAmount0').text()
	}
	var total_aValidate = basicValidate (".salesConsiderationTotalNew","text");
	var total_a = 0;
	if (total_aValidate) {
		total_a = $('.salesConsiderationTotalNew').text()
	}
	var total_bValidate = basicValidate ("#tentativeChargesTotal","text");
	var total_b = 0;
	if (total_bValidate) {
		total_b = $('#tentativeChargesTotal').text()
	}
	var stempdutyamountValidate = basicValidate ("#stamp_duty","text");
	var stemp_duty_amount = 0;
	if (stempdutyamountValidate) {
		stemp_duty_amount = $('#stamp_duty').text()
	}
	var registrationamountValidate = basicValidate ("#registrationCharges","text");
	var registration_amount = 0;
	if (registrationamountValidate) {
		registration_amount = $('#registrationCharges').text()
	}
	var gstamountValidate = basicValidate ("#goodsNServiceTax","text");
	var gst_amount = 0;
	if (gstamountValidate) {
		gst_amount = $('#goodsNServiceTax').text()
	}
	var total_cValidate = basicValidate ("#total_flat_cost","text");
	var total_c = 0;
	if (total_cValidate) {
		total_c = $('#total_flat_cost').text()
	}
	
	var paymentplantotalValidate = basicValidate ("#payableToTotal3","text");
	var paymentplan_total = 0;
	if (paymentplantotalValidate) {
		paymentplan_total = $('#payableToTotal3').text()
	}
	
	var total_abcValidate = basicValidate ("#totalABCnoCurrency","text");
	var total_abc = 0;
	if (total_abcValidate) {
		total_abc = $('#totalABCnoCurrency').text()
	}
	var totaldiscountValidate = basicValidate ("#finalDiscountValue","val");
	var total_discount = 0;
	if (totaldiscountValidate) {
		total_discount = $('#finalDiscountValue').val()
	}
	
	
	
	
	formData.append('discounted_bsp', discounted_bsp);
	formData.append('og_bsp', og_bsp);
	formData.append('carpet_area_sqft', carpet_area_sqft);
	formData.append('saleable_area_sqft', saleable_area_sqft);
	formData.append('property_name', $('.unit_property_name').text());
	formData.append('carpet_area_rera_sqmt', carpet_area_rera_sqmt);
	formData.append('exclusive_area_sqmt', exclusive_area_sqmt);
	formData.append('total_area_sqmt', total_area_sqmt);
	formData.append('carpet_area_amount', carpet_area_amount);
	formData.append('exclusive_area_amount', exclusive_area_amount);
	formData.append('flat_unit_cost', flat_unit_cost);
	formData.append('total_a', total_a);
	formData.append('total_b', total_b);
	formData.append('stemp_duty_amount', stemp_duty_amount);
	formData.append('registration_amount', registration_amount);
	formData.append('gst_amount', gst_amount);
	formData.append('total_c', total_c);
	formData.append('total_abc', total_abc);
	formData.append('total_discount', total_discount);
	formData.append('paymentplan_total', paymentplan_total);
	//------------------------------------
	
	
	formData.append('cs_sales_comments', $('#costsheet_commitment').val());
	formData.append('costsheet_path', "");
	
	//formData.append('createddate', "XYZ");
	formData.append('createdby', parseInt(useridSFID));
	formData.append('createdbyemail', useremail);
	//formData.append('updateddate', "XYZ");
	formData.append('updatedby', parseInt(useridSFID));
	formData.append('isactive', "Y");
	
	//----------------------------------------------------------------------------------
	
	 
	$.ajax({
		url : pageContext+"insertCostsheetLog",
		type: "POST",
		data : JSON.stringify(Object.fromEntries(formData)),
		dataType : 'json',
		contentType: 'application/json'
	}).done(function(response){ //
		 //alert("Success"+response);
	});
}


function basicValidate (id, type) {
	var condition = false;
	
	if (type == "val"){
		if ($(id).val() != undefined &&  $(id).val() != "undefined" && $(id).val() != "" && $(id).val() != null && $(id).val() != "null") {
			condition = true;
		} else {
			condition = false;
		}
	} else if (type == "text") {
		if ($(id).text() != undefined &&  $(id).text() != "undefined" && $(id).text() != "" && $(id).text() != null && $(id).text() != "null" && $(id).text() != "NaN" && $(id).text() != NaN) {
			condition = true;
		} else {
			condition = false;
		}
	} else {
		condition = false;
	}
	
	
	
	return condition;
}


function csChangesForFaridabad () {
	if ($('#projectId').val() == 'a1l2s000000XmaMAAS') {
		$('.towerNameCS').html('');
		$('.towerNameCS').html('Parcel');
		$('.floorNameCS').html('');
		$('.floorNameCS').html('Street Name');
		
		$('.typologyNameCS').html('');
		$('.typologyNameCS').html('-');
		
		$('.typologyTval').html('');
		$('.typologyTval').html('-');
		
		$('#typologyTval').html('');
		$('#typologyTval').html('-');
		
		$('.hideForPlot').remove();
		
		$('.hideForPlot').hide();
		$('.removeForPlot').remove();
		
		$('.carpetSqmlabel').html("");
		$('.carpetSqmlabel').html("Length (in mtr.)");
		
		$('.balTerSqmLabel').html("");
		$('.balTerSqmLabel').html("Breadth (in mtr.)");
		
	} else {
		$('#onlyForPlot').hide();
		$('.onlyForPlot').remove();
	}
}


function schemeInSalesComment (){
	
	$("#costsheet_commitment").val(""); 
	
	var schemes = "";
	
	if ($('#schemeTypeDD').val() == 'scheme') {
		
		$("#costsheet_commitment").css("min-height", "70px");
		
		$("#costsheet_commitment").attr('readonly', true);
  	    
		$("#costsheet_commitment").addClass("disableInputs");
		
		if ($('#getSchemeSource').val() != '-1') {
			schemes += "* Source discount: " + $('#getSchemeSource option:selected').text();
		} else {
			schemes += ""  
		}
		    
		if ($('#getSchemeSite').val() != '-1') {
			schemes += "* Site discount: " + $('#getSchemeSite option:selected').text();
		} else {
			schemes += ""  
		}
		
		if ($('#getSchemePromotional').val() != '-1') {
			schemes += "* Promotional discount: " + $("#getSchemePromotional option:selected").text();
		} else {
			schemes += ""  
		}
  	  
     } else if ($('#schemeTypeDD').val() == 'noScheme') {
    	 $("#costsheet_commitment").css("min-height", "0px");
    	 $("#costsheet_commitment").attr('readonly', false);
    	 $("#costsheet_commitment").removeClass("disableInputs");
    	 schemes = "";
     } else if ($('#schemeTypeDD').val() == 'other') {
    	 $("#costsheet_commitment").css("min-height", "0px");
    	 $("#costsheet_commitment").attr('readonly', false);
    	 $("#costsheet_commitment").removeClass("disableInputs");
    	 schemes = "";
     }
	
	$("#costsheet_commitment").val(schemes);
	
}
