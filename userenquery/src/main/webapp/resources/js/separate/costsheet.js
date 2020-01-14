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

$( document ).ready(function() {
//if ($('#projectId').val() == 'a1l6F000008iEJiQAM') {
if ($('#projectId').val() == 'a1l6F0000047Q1xQAE' || $('#projectId').val() == 'a1l6F000008iEJiQAM') {
       $('#carParkDDCol').hide();
}
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
       //alert("Get CS Data");
		$('#getCSData span').html('<i class="fa fa-spinner fa-spin" style="color:#fff !important"></i>'); 
		insertAuditLogCostsheet();
       $('#updateBtnCol button').prop("disabled", false);
       
       $('#paymentPlanChangeDiv').append('<input id="paymentPlanChangeID">');
       //alert($('#ppDropdown').val());
       $('#paymentPlanChangeID').val($('#ppDropdown').val());
       if ( $('#unitSfid').val() != '') {             
             	
         	 $.post(pageContext+"getInventoryStatus",{"projectsfid":$('#projectsfid').val(),"propid":$('#unitSfid').val()},function(data){                       
         	       
             }).done(function(data){
              	   if (data == "errorNoUnit101") {
	              	   //$('#getCSData span').html('<i class="fa fa-spinner fa-spin" style="color:#fff !important"></i>');          
	              	   loadData ();
              		   swal({
          	   				title: "This unit is no longer available please select another unit.",
          	   				text: "",
          	   				type: "warning",
          	   				allowOutsideClick: true,
          	   				showConfirmButton: true
          	   			});
              	   } else if (data == "errorUnitInactive102") {
              		   //$('#getCSData span').html('<i class="fa fa-spinner fa-spin" style="color:#fff !important"></i>');          
	              	   loadData ();
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
              			   	title: "There was problem in fetching cost sheet data at this time. Please try again by clicking get details.", 

          	   				text: "",
          	   				type: "warning",
          	   				allowOutsideClick: true,
          	   				showConfirmButton: true
          	   			});
              	   } else if (data == "successUnitAvailable102") {
              		  // $('#getCSData span').html('<i class="fa fa-spinner fa-spin" style="color:#fff !important"></i>');          
	              	   loadData ();
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
});





function loadData () {     
	$('#schemeInput').val("");
	
		tncData ();
		getBSPTax ();
       
       if ($('#schemeTypeDD').val() == 'scheme') {
    	   schemeDropdown ();
       } else if ($('#schemeTypeDD').val() == 'noScheme') {
    	   $('#getPln').empty();
    	   $('#getPln').append('<option value="0" data-valuepercentage="0" data-valueabsolute="0" data-valuerate="0">No Scheme</option>');
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
    	   
    	   
    	   $('#getPln').append('<option data-zerogovtcharges="false" data-valuepercentage="'+otherPerVal+'" data-valueabsolute="'+otherAbsVal+'" data-valuerate="'+otherNewPlanVal+'">other</option>');
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
                           
       var url = urlDomin+"getProjectPlan?herokuEnqId="+$('.enquiryId').val()+"&project_code="+projectNameVal+"&unit="+$('#unitSfid').val()+"&towerCode="+$('#towerSfid').val()+"&pymtPlanSfid="+$('#ppDropdown').val()+"&typology="+$('#typoMst').val()  ;
       $.getJSON(url, function (data) {
             
             $.each(data, function (index, value) {
                    
                    
                    if (unitVal == value.unitsfid) {
                           
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
                           
                           
                           $('.a3_bsp').text(parseFloat(value.BasicSalePricepersquarefeet));
                           $('.a3').text(parseFloat(parseFloat(value.BasicSalePricepersquarefeet)+parseFloat(value.bsp_amount)-(otherScheme)).toFixed(2));
                           
                           $('#discountPsqf').val(otherScheme);
                           
                           
                           $('.a4').text(Math.round(value.CarpetArea));
                           $('.a6').text(value.SaleableArea);
                           $('#carpetSqm').text(parseFloat(value.carpet_area_converted__c));
                           $('.carpetSqm').text(parseFloat(value.carpet_area_converted__c));
                           $('#totalSqm').text(parseFloat(parseFloat(value.carpet_area_converted__c) + parseFloat(value.appurtenant_area_sq_mt__c)).toFixed(2));
                           $('.totalSqm').text(parseFloat(parseFloat(value.carpet_area_converted__c) + parseFloat(value.appurtenant_area_sq_mt__c)).toFixed(2));
                           $('#balTerSqm').text(value.appurtenant_area_sq_mt__c);
                           $('.balTerSqm').text(value.appurtenant_area_sq_mt__c);
                           $('#unitTval').text(value.ID);
                           $('.unitTval').text(value.ID);
                           $('#typologyTval').text(value.Typology);
                           $('.typologyTval').text(value.Typology);
                           $('#floorTval').text(value.floorName);
                           $('.floorTval').text(value.floorName);
                           $('#towerTval').text(value.towerName);
                           $('.towerTval').text(value.towerName);
                           
                           
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
             $('.craParkTypeLabel').text('Car Park Type');
             $('.noOfCarParkLabel').text('No. of Car Park');
       }
       
       
       getEOIPaymentRecord ();
}



function getBSPTax () {

       var unitVal = $('#unitSfid').val();
       
       var url = urlDomin+"getProjectPlan?herokuEnqId="+$('.enquiryId').val()+"&project_code="+projectNameVal+"&unit="+$('#unitSfid').val()+"&towerCode="+$('#towerSfid').val()+"&pymtPlanSfid="+$('#ppDropdown').val()+"&typology="+$('#typoMst').val()  ;
       $.getJSON(url, function (data) {
             
             
             $.each(data, function (index, value) {
                    
                    if (unitVal == value.unitsfid) {
                           
                           //NO GST tax on bsp calculation if propstrength__completion_certificate_received__c == Trure OR value.propstrength__category__c == 'Plot'
                           
                           if (value.propstrength__completion_certificate_received__c == true || value.propstrength__category__c == 'Plot') { 
                                 bspTaxRecord ('noGst');
                                 //no gst
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




function paymentPlanOtherCharges (firstRowObj){
       
       
       
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
             
             
             
             for(i = 0; i< obj.length; i++){    
                 if(uniqueId.indexOf(obj[i].id) === -1){
                     uniqueId.push(obj[i].id);
                     uniqueNames.push(obj[i].propstrength__payment_plan_line_item_name__c);
                     uniqePPpercent.push(obj[i].propstrength__amount_percent__c);
                 }        
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
                    var gstFlexible = 0;
                    var gstChargeAmount = 0;
                    var gstFinal = 0;
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
                                        
                                        if (obj[k].propstrength__sta_applicable__c == true) {
                                               //gstFixed = parseFloat ((fixed*2/3)*ocWithBspGst/100);
                                               gstFixed = parseFloat (fixed*ocWithBspGst/100);
                                        } else {
                                               gstFixed = parseFloat (fixed*ocWithBspGst/100);
                                        }
                                        
                                        //gstFixed = parseInt (fixed*obj[k].sum/100);
                                        
                                        gstChargeAmount = gstFixed+gstChargeAmount;
                                        
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
                                               
                                        
                                        
                                        //gstFlexible = parseInt (flexible*obj[k].sum/100);
                                        
                                        if (obj[k].propstrength__sta_applicable__c == true) {
                                               //gstFlexible = parseFloat ((flexible*2/3)*ocWithBspGst/100);
                                               gstFlexible = parseFloat (flexible*ocWithBspGst/100);
                                        } else {
                                               gstFlexible = parseFloat (flexible*ocWithBspGst/100);
                                        }
                                        
                                        gstChargeAmount = gstFlexible+gstChargeAmount;
                                 }
                                 finalCA = chargeAmount;
                                 POCfinalCA = POCchargeAmount;
                                 gstFinal = gstChargeAmount;
                           } else { }
                    }
                    
                    ocPlsBsp =  (($('#flatCostWithDiscount').val())*uniqePPpercent[j])/parseInt(100);
                    
                    gstPymtOcTotal = parseFloat((gstFinal) + ((ocPlsBsp)*($('#bspGSTTax').val())/100)).toFixed(2);
                    
                    
                    //gstPymtOcTotal = parseFloat((gstFinal) + ((ocPlsBsp*2/3)*($('#bspGSTTax').val())/100)).toFixed(2);
                    
                    tokenBaseAmountPP = 0;
                    tokenGSTPP =  0;
                    tokenAmountPP = 0;
                    
                    firstPPText = '';
                    
                    if (j == 0 && firstRowObj != 'null') {
                           tokenBaseAmountPP = parseFloat($('#tokenBaseAmount').text());
                           tokenGSTPP =  parseFloat($('#tokenGST').text());
                           tokenAmountPP = parseFloat($('#tokenAmount').text());
                           firstPPText = ' - Less '+$('#firstPPTextRow').text();
                    }
                    
                    
                    console.log ("PP Name ::: " + uniqueNames[j] +"--------"+ "PP % ::: " + uniqePPpercent[j]);
                    
                    
                    html += "<tr>" +
                                        "<td>"+ uniqueNames[j] + firstPPText+" </td>" +
                                        "<td class='txtRight' style='text-align:right;'>"+ uniqePPpercent[j] +" % </td>" +
                                        "<td class='txtRight' style='text-align:right;'>" + parseFloat(finalCA+ocPlsBsp-tokenBaseAmountPP).toFixed(2) +  "</td>  " +
                                        
                                        //Remove after test
                                        //"<td class='txtRight'>" + parseFloat((ocPlsBsp)*($('#bspGSTTax').val())/100) +  "</td>  " +
                                        //"<td class='txtRight'>" + parseFloat((ocPlsBsp*2/3)*($('#bspGSTTax').val())/100) +  "</td>  " +
                                        //"<td class='txtRight'>" + gstFinal +  "</td>  " +
                                        
                                        
                                        
                                        "<td class='txtRight' style='text-align:right;'>" +parseFloat(gstPymtOcTotal-tokenGSTPP).toFixed(2)+  "</td> " +
                                        "<td class='txtRight' id='payableToTotal"+i+"' style='text-align:right;'> "+parseFloat(finalCA+ocPlsBsp+parseFloat(gstPymtOcTotal)-tokenAmountPP).toFixed(2)+" </td> " +
                                 "</tr>";
                    
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
             
             
             
             
             html+="<tr class='payableStampAndReg'><td colspan='4'>Payable at the time of registration</td><td class='txtRight' style='text-align:right'>"+ parseFloat(parseFloat($('#stamp_duty').text())+parseFloat($('#registrationCharges').text())).toFixed(2)+"</td></tr>";
             
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
             } 
             
             
             
             
             if ( $('#projectId').val() == 'a1l6F0000047Q1xQAE' && $('#stamp_duty').text() == 0 && $('#registrationCharges').text() == 0) {
                    $('.payableStampAndReg').remove();
             }
             
             
             $('#getCSData span').html('');   
       });    
}



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
             paymentPlanOtherCharges(obj);
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
             $('#costsheetError').show();
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
       
       $('.creatOfferBtnCommon span').html('<i class="fa fa-spinner fa-spin" style="color:#fff !important"></i>');
       
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
    	   
    	   if($('#walkInSource').val()==="Referral")
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
       
       
       if ($('#tdsPaidByDD').val() != '') {
             tdsPaidBy = $('#tdsPaidByDD').val();
       }
       
       $.post(pageContext+"updateBSP",{"bspDis":bspVal,"token":$('#token').val(),
              "projectsfid":$('#projectsfid').val(),"enquirysfid":$('#enquirysfid').val(),"primarycontactsfid":$('#primarycontactsfid').val(), "sentToCrmYN":"Y", "timeid":"0"
              ,"propid":$('#unitSfid').val(),"ppid":$('#paymentPlanChangeID').val(),"offerthrough":offerthrough,"brokersfid":brokerAccountSfid
              ,"discount_Value":discount_Value,"balance_amnt":$('#balance_amnt').val(),"balance_amnt_description":$('#balance_amnt_description').val()
              ,"car_park_type":$('#carParkType').find('option:selected').attr("data-name"),"scheme_rate":$('#getPln').find('option:selected').attr("data-valuerate"),"scheme_name":$("#getPln :selected").text(),"userid":$('#userid').val(),"enquiry_name":$('#enquiry_name').val() 
              ,"costsheet_commitment":$('#costsheet_commitment').val(),"prepaymentamt":prepaymentAmount,"bankname":bankname,"trxdate":trxdate,"trxno":trxno,"paymentmode":paymentmode, "tdsPaidBy":tdsPaidBy,"isOthers":isOthers, "costsheet_path": csPath, "cs_final_amount":$('#csFinalAmountInput').val(),"bankGL":$('#towerBankGLCode').val() 
       },function(data){                       
       
       }).done(function(data){
    	   var offerJson = JSON.parse(data);
    	   if (offerJson.offer_successMsg == "errorNoUnit101") {
    		   $('#updateBtnCol button').prop("disabled", false);
    		   $('.creatOfferBtnCommon span').html('');  
    		   
    		   swal({
	   				title: "This unit is no longer available please select another unit.",
	   				text: "",
	   				type: "warning",
	   				allowOutsideClick: true,
	   				showConfirmButton: true
	   			});
    	   } else if (offerJson.offer_successMsg == "errorUnitInactive102") {
    		   $('#updateBtnCol button').prop("disabled", false);
    		   $('.creatOfferBtnCommon span').html('');  
    		   
    		   swal({
	   				title: "Inventory is not activated.",
	   				text: "",
	   				type: "warning",
	   				allowOutsideClick: true,
	   				showConfirmButton: true
	   			});
    	   } else if (offerJson.offer_successMsg == "errorInCode103") {
    		   $('#updateBtnCol button').prop("disabled", false);
    		   $('.creatOfferBtnCommon span').html('');  
    		   
    		   swal({

	   				//title: "There is some technical problem, please try again.",
    			   	title: "There was problem in creating offer at this time. Please try again by clicking create offer.",

	   				text: "",
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
    		          debugger;
    		          if(kycStatus!="Y")
	       			   {
	   	   				generateKYCLinkViaOffer(event,this,'N',offerJson.offer_sfid);
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
	            
	            var url=$("#contextPath").val();
	            
	            getDealDone();
	                
	            $('.creatOfferBtnCommon span').html('');
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
       $('.creatOfferBtnCommon span').html('');
       
    });
       
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
	        "userName":$('#username').val().split(' ')[0]
	        
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
                          	
                           $('#getPln').prepend('<option data-zeroGovtCharges='+value.zero_govt_charges+'  data-valuePercentage='+value.percentage+' data-valueAbsolute='+value.absolute_amount+'  data-valueRate='+value.rate+'   >'+ value.scheme+'</option>');
                           	
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
                           
                    $('#getPln').prepend('<option data-zeroGovtCharges='+value.zero_govt_charges+'  data-valuePercentage='+value.percentage+' data-valueAbsolute='+value.absolute_amount+'  data-valueRate='+value.rate+'     >'+ value.scheme+'</option>');
                    
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
                                 $('#salesConsideration tbody').append('<tr> <th> '+value.propstrength__other_charge_code__c+' </th> <td class="txtRight" id="salesConsiderationAmount'+i+'" style="text-align:right;"> '+amount+' </td>  </tr>');
                                 $('#printSalesConsideration tbody').append('<tr> <th> '+value.propstrength__other_charge_code__c+' </th> <td class="txtRight" id="salesConsiderationAmount'+i+'" style="text-align:right;"> '+amount+' </td>  </tr>');
                                 salesConsiderationV2 = parseFloat(parseFloat($("#salesConsiderationAmount"+i).text()) + parseFloat(salesConsiderationV2)).toFixed(2);
                           } 
                           
                           
                           
                    } else if (value.propstrength__part_of_cop__c == false) {
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
             
             
             
             //otherChargesGSTTotalV2 = parseInt(otherChargesGSTTotalV2)+parseInt(((bspAmount*2/3)*bspTax)/100);
             //otherChargesGSTTotalV2 = parseInt(otherChargesGSTTotalV2)+parseInt(((finalFlatAmount*2/3)*bspTax)/100);
             otherChargesGSTTotalV2 = parseFloat(parseFloat(otherChargesGSTTotalV2)+parseFloat(((finalFlatAmount)*bspTax)/100)).toFixed(2);
             
             
              //$('#flatCostWithDiscount').val(parseFloat(parseFloat(salesConsiderationV2)+parseFloat($('#scOtherChrgAmount0').text())));
             
              $('#flatCostWithDiscount').val(parseFloat(parseFloat($('#scOtherChrgAmount0').text())));
             
             
             //Old
             $('#salesConsideration tbody').append('<tr> <th class="subHead"> Consideration/Price (A) </th> <th class="txtRight salesConsiderationTotalNew" id="salesConsiderationTotal" style="text-align:right;">'+ parseFloat(parseFloat(salesConsiderationV2)+parseFloat($('#scOtherChrgAmount0').text())).toFixed(2) +'</th>    </tr>');
             
             
             
             //-----------------
             
             $('#printSalesConsideration tbody').append('<tr> <th class="subHead"> Consideration/Price (A) </th> <th class="txtRight" id="salesConsiderationTotal" style="text-align:right;">'+ parseFloat(parseFloat(salesConsiderationV2)+parseFloat($('#scOtherChrgAmount0').text())).toFixed(2) +'</th>    </tr>');
             
             $('#tentativeCharges tbody').append('<tr> <th class="subHead"> Total Estimated and Tentative other charges (B) </th> <th class="txtRight" id="tentativeChargesTotal" style="text-align:right;">'+tentativeChargesV2+'</th>    </tr>');
             $('#printTentativeCharges tbody').append('<tr> <th class="subHead"> Total Estimated and Tentative other charges (B) </th> <th class="txtRight" id="tentativeChargesTotal" style="text-align:right;">'+tentativeChargesV2+'</th>    </tr>');
             
             //$('#stamp_duty').text(parseInt((otherChrgAmountTotal*7)/100));
             
             /*$('#stamp_duty').text(parseInt((salesConsiderationV2*7)/100));
             $('.stamp_duty').text(parseInt((salesConsiderationV2*7)/100));*/
             
             //if project 
             
             if ($('#getPln').find('option:selected').attr("data-zerogovtcharges") == 'true'){
                    stampDuty = 0;
                    registrationCharges = 0;
                    
             //Remove below else if after South launch      
             }else if ($('#region__c').val() == "Noida" || $('#region__c').val() == "Gurgaon"  ||  $('#region__c').val() == "Bangalore"){
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
                    
                    $('#totalDicountView').show();
                    
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
                    
                    
                    $('#totalDiscountCol').append(printDiscountHtml);
             } else {
                    $('#totalDicountView').hide();
                    $('#totalDiscountCol').html('');
             }
             
              
             $('#totalDiscount').text(yres);
             
             //paymentPlanOtherCharges();
             
             firstMilstone();
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
                      seconds = 0;
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
       $.post(pageContext+"getTncData",{"ppId":$('#ppDropdown').val(), "projectid":$('#projectid').val() },function(data){                      
       }).done(function(data){
             $('.tncData').empty();
             var obj =JSON.parse(data);
             
             if (obj != null) {
                    $(".tncData").closest('table').next('.tableNoRec').hide();
                    for (i = 0; i< obj.length; i++) {
                           if($('#ppDropdown').val() == obj[i].pymt_plan_id) {
                                 $('.tncData').prepend(obj[i].tnc_text);
                                 return false;
                           }else {
                                 if (obj[i].pymt_plan_id == '') {
                                        $('.tncData').prepend(obj[i].tnc_text);
                                        return false;
                                 }
                           }
                    }
             } else {
                    $(".tncData").closest('table').next('.tableNoRec').show();
             }
       });
}
$('.printCurrentDate').text($.datepicker.formatDate('dd/mm/yy', new Date()));

function printPdfData(generateFrom) {
       var url=$("#contextPath").val();
       
       
       
       
       
       var enqSfid = generateFrom+"-"+$('#enquirysfid').val();
       
       $('#printBtnFloat').empty();
       $('#printBtnFloat').html('<i style="color:#fff !important; " class="fa fa-spinner fa-spin printficon"></i>');
       
       $('#csCommitmentTxt').empty();
       
       if ($('#costsheet_commitment').val().trim() != '') {
             $('#csCommitmentTxt').html("<h5>Sales Comments: </h5><span style='font-size:8px !important;'>"+$('#costsheet_commitment').val()+"</span>");
       } 
             
       $.post(pageContext+"printCSdata",{"unitTval":$('#unitTval').text(), "floorTval":$('#floorTval').text(), "towerName":$('#towerTval').text(), "regionName":$('#region__c').val(), "projectSfid":$('#projectId').val(),"unitSfid":$('#unitSfid').val(),"enqSfid":enqSfid,"csData":$('#getCSDataForPrint').html(), "projectName":$('#marketingProjectName').val(), "currentDate":$.datepicker.formatDate('dd/mm/yy', new Date())},function(data){                           
             
       }).done(function(data){
             
    	   var win = window.open(pageContext+'Costsheet?name='+enqSfid+'-'+$('#unitSfid').val()+'-'+$('#projectId').val() +'&region='+$('#region__c').val() + '&project='+$('#marketingProjectName').val()+'&tower='+$('#towerTval').text()+'&floor=' + $('#floorTval').text() + '&unit=' + $('#unitTval').text() + '&from=costsheet', '_blank');
           
             
             //var win = window.open(pageContext+'Costsheet?name='+enqSfid, '_blank');
             if (win) {
                 //Browser has allowed it to be opened
                 win.focus();
             } else {
                 //Browser has blocked it
                 alert('Please allow popups for this website');
             }
             $('#printBtnFloat').html('<i class="fa fa-print printficon"></i>') 
             
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
var amNum = 4; 

var amNumRowID = 0;

function addMorePtBtn () {
	
	amNumRowID = amI+1;
	
	
	var id= "'"+"csPayRowID"+amNumRowID+"'";
		
	var attachPAN = "'attachPAN'";
	var attachRec = "'attachRec'";
	
	
	
       if(amI < amNum) { 
             $('#csPtCol tr:last-child').after('<tr id="csPayRowID'+amNumRowID+'" class="csPtDataRow csPtFileSize"><td class="txtCenter"><input style="display:none;" class="gpl_cs_payment_details_id" value="-1"><input type="checkbox" class="paymentCScheck" checked></td><td><select onchange="csPtDd(this)" class="full form-control input-sm csPtDropDown requiredField"><option value="">Select</option><option value="Cheque">Cheque</option><option value="NEFT">NEFT/Credit</option><option value="Swipe">Swipe</option><option value="Wire Transfer">Wire Transfer (PayZap, Google Pay)</option></select></td><td><input class="full form-control input-sm csPtBankName requiredField" placeholder="Bank Name"/></td><td><input class="full form-control input-sm csPtBranch requiredField" placeholder="Branch Name"/></td><td><input class="full form-control input-sm csPtTransactionId requiredField" placeholder="Transaction ID" /></td><td><input type="date" class="full form-control input-sm csPtTransactionDate requiredField" placeholder="Transaction Date"/></td><td><input  class="numericField numericWithoutDecimal full form-control input-sm csPtTransactionAmount requiredField" maxlength="10" onkeyup="csPtcalculateGrandTotal()" placeholder="Transaction Amount" name="amount"/></td> <td> <input type="file" class="full form-control input-sm panAttach"/> <input class="panAttachWebcam"/> <a  class="webcamBtn" data-toggle="modal" data-target="#webcamBox" onclick="webcamAttachment(this, '+id+', '+attachPAN+')">or <span>Capture Image</span></a> </td> <td> <input type="file" class="full form-control input-sm receiptAttach"/> <input class="receiptAttachWebcam"/> <a  class="webcamBtn" data-toggle="modal" data-target="#webcamBox" onclick="webcamAttachment(this, '+id+', '+attachRec+')">or <span>Capture Image</span></a> <td><textarea class="full form-control input-sm csPtDescription" placeholder="Description"></textarea></td><td class="removeCsPtCol txtCenter"><i onclick="removeCsPtCol(this)" class="fa fa-times-circle"></i></td></tr>');
             amI++;
       }else {
             swal({
                    title: "You've reached the limit",
                 text: "",
                 timer: 2000,
                 type: "error",
             });
       }
}

function removeCsPtCol (e) {
       $(e).closest("tr").remove();
       amI -= 1;
       
       csPtcalculateGrandTotal ();
}

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
    	   $(e).closest("tr").find(".csPtTransactionId").attr("maxlength","15");
    	   $(e).closest("tr").find(".csPtTransactionId").after("<small class='csPtReachMexLength'>ID can be max 15 characters long.</small>");
           $(e).closest("tr").find('.csPtBankName').prop('disabled', false);
           $(e).closest("tr").find('.csPtBranch').prop('disabled', false);
       }else  if ($(e).val() == 'Swipe' || $(e).val() == 'Wire Transfer') {
    	   $(e).closest("tr").find(".csPtTransactionId").val("");
    	   $(e).closest("tr").find(".csPtTransactionId").attr("maxlength","15");
    	   $(e).closest("tr").find(".csPtTransactionId").after("<small class='csPtReachMexLength'>ID can be max 15 characters long.</small>"); 
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
    $("#csPtCol").find('input[name="amount"]').each(function () {
        grandTotal += +$(this).val();
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
		$('#carParkCountDD').append('<select class="full form-control"><option value="1">1</option> <option value="2">2</option> <option value="3">3</option>  <option value="4">4</option> </select>');
		$('#carParkCount').show();
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
                           
                           panTarget = pageContext+"file?name="+obj[i].pan_attach+"&from=EOIbookingReference&eid="+obj[i].enq_sfid+"&fid="+obj[i].pan_attach.charAt(0);
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
                           
                           eoiTransactionTotalAmount = parseFloat(parseFloat(obj[i].transaction_amount)+parseFloat(eoiTransactionTotalAmount)).toFixed(2);
                           
                           if (obj[i].isactive != 'Y') {
                                 checkBox = 'disabled';
                               disableRow = 'style="background-color: #ffd4d8;"';
                               status = '<br> Not Approved';
                           } else {
                                 checkBox = 'checked';
                                 disableRow = '';
                                 status = '<br> Approved';
                           }
                           
                           
                           html +=      '<tr class="csPtDataRow moveToOffer" '+disableRow+'>'
                                                     + '<td class="txtCenter"><input style="display:none;" class="gpl_cs_payment_details_id" value="'+obj[i].id+'">  <input type="checkbox" class="paymentCScheck" '+checkBox+'> </td>'
                                                     + '<td class="txtCenter"><input class="full form-control input-sm csPtEnqSfid csPtDropDown" value="'+obj[i].payment_type+'" style="display:none;" disabled >'+obj[i].payment_type+'</td>' 
                                                     + '<td class="txtCenter"><input class="full form-control input-sm csPtBankName" value="'+obj[i].bank_name+'" style="display:none; " disabled>'+obj[i].bank_name+'</td>' 
                                                     + '<td class="txtCenter"><input class="full form-control input-sm csPtBranch" value="'+obj[i].branch+'" style="display:none; " disabled>'+obj[i].branch+'</td>' 
                                                     + '<td class="txtCenter"><input class="full form-control input-sm csPtTransactionId" value="'+obj[i].transaction_id+'" style="display:none; " disabled>'+obj[i].transaction_id+'</td>' 
                                                     + '<td class="txtCenter"><input class="full form-control input-sm csPtTransactionDate" value="'+obj[i].transaction_date+'" style="display:none; "  disabled>'+trans_date+'</td>' 
                                                     + '<td class="txtCenter"><input class="full form-control input-sm csPtTransactionAmount"  value="'+obj[i].transaction_amount+'" name="amount" style="display:none; " disabled>'+obj[i].transaction_amount+'</td>' 
                                                     + '<td class="txtCenter"><input style="display:none;" class="full form-control input-sm panAttach" type="file" data-fileName="'+obj[i].pan_attach+'"  name="panAttach"> <a target="_blank" href="'+panTarget+'">'+obj[i].pan_attach+'</a></td>' 
                                                     + '<td class="txtCenter"><input style="display:none;" class="full form-control input-sm receiptAttach" type="file" data-fileName="'+obj[i].cheque_attach+'" name="receiptAttach"> <a target="_blank" href="'+reciptTarget+'">'+obj[i].cheque_attach+'</a></td>'
                                                     + '<td class="txtCenter"><input class="full form-control input-sm csPtDescription" value="'+obj[i].description+'" style="display:none;" disabled>'+obj[i].description+'</td>'
                                                     + '<td > </td>'
                                               "</tr>";
                    }
                    
                    $('#csPtGrandtotal').text(eoiTransactionTotalAmount);
                    
                    html = html.replace(/undefined/g, "");
                    
                    
                    html += '<tr><th colspan="11"  style="text-align:left; font-size: 14px; background-color: #ebebeb;">Payment at the time of booking</th></tr>'
                           
                           +' <tr id="csPayRowID0" class="csPtDataRow csPtFileSize" ><td class="txtCenter">'
                                 +'<input style="display:none;" class="gpl_cs_payment_details_id" value="-1">'
                                 + '<input type="checkbox" class="paymentCScheck" checked>'
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
                           + '<td>'
                                 + '<input class="full form-control input-sm csPtBranch requiredField" placeholder="Branch Name"/>'
                           + '</td>'
                           + '<td>'
                                 + '<input class="full form-control input-sm csPtTransactionId requiredField" placeholder="Transaction ID"/>'
                           + '</td>'
                           + '<td>'
                                 + '<input type="date" class="full form-control input-sm csPtTransactionDate requiredField" placeholder="Transaction Date"/>'
                           + '</td>'
                           + '<td>'
                                 + '<input class="numericField numericWithoutDecimal full form-control input-sm csPtTransactionAmount requiredField" maxlength="10" onkeyup="csPtcalculateGrandTotal()" name="amount" placeholder="Transaction Amount"/>'
                           + '</td>'
             
                           + '<td>'
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
                                        + '<input type="checkbox" class="paymentCScheck" checked>'
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
                                 + '<td>'
                                        + '<input class="full form-control input-sm csPtBranch requiredField" placeholder="Branch Name"/>'
                                 + '</td>'
                                 + '<td>'
                                        + '<input class="full form-control input-sm csPtTransactionId requiredField" placeholder="Transaction ID"/>'
                                 + '</td>'
                                 + '<td>'
                                        + '<input type="date" class="full form-control input-sm csPtTransactionDate requiredField" placeholder="Transaction Date"/>'
                                 + '</td>'
                                 + '<td>'
                                        + '<input class="numericWithoutDecimal numericField full form-control input-sm csPtTransactionAmount requiredField" maxlength="10" onkeyup="csPtcalculateGrandTotal()" name="amount" placeholder="Transaction Amount"/>'
                                 + '</td>'
                    
                                 + '<td class="txtCenter">'
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
