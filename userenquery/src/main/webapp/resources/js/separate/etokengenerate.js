/**
 * Added By Satheesh K
 */


$(document).ready(function(){
	$("#enMobileNoEtoken").attr("maxlength","10");
	$(document).on('focus', '.autocomplete-off:input', function(){
	        $( this ).attr( 'autocomplete', 'nope' );
	});

});
function onclickGenerateEToken()
{
	//$('#tab3').show();
}
function searchEToken()
{
	var url=$("#contextPath").val();//$('#enquirysfid').val()
	$('#etokengenratedCol').hide();
	$('.etokenNo').text('');
	$('.ename').text('');
	$('.emobile').text('');
	$('.eenquiry').text('');
	var inputMobile =  $('#enMobileNoEtoken').val();
	var inputCountryCode =  $('.selected-dial-code').text();
	$.post(url+"/android_generateTokenMobileEOIData",{"countryCode":inputCountryCode.substring(3),"mobileNo":inputMobile,"projectname":$('#projectSfid').val(),"createdBy":$('#loged_userid').val()},function(data){                       
	       
    }).done(function(data){
    	if (data != null && data != "") {
    		//alert(data.message);
    		//$('#enquiryOldComment').html(data);
    		$('.eenquiryno').text(data.name);
    		$('.ecustomername').text(data.contact.firstName+data.contact.lastName);
    		$('.ecustomermobile').text(data.contact.mobile);
    		$('.eenquiryrtpe').text(data.isReferredByChannelPartner);
    		$('.ewalkinsource').text(data.walkInSource);
    		$('#etokendispDiv').show();
    		$('#generateEToken_btn_Div_show').show();
    		
    		/*$('.edateofeoi').text(data.walkInSource);*/
    		
    	} else {
    		//$('#enquiryOldComment').html("No record found");
    	}
    }).fail(function(xhr, status, error) {
    	
    });
	}
function getGeneratedEtoken(){
	$('#etokengenratedCol').show();
	var url=$("#contextPath").val();//$('#enquirysfid').val()
	
	var inputMobile =  $('#enMobileNoEtoken').val();
	var inputCountryCode =  $('.selected-dial-code').text();
	$.post(url+"/android_generateTokenMobileEOI",{"countryCode":inputCountryCode.substring(3),"mobileNo":inputMobile,"projectname":$('#projectSfid').val(),"createdBy":$('#loged_userid').val()},function(data){                       
	       
    }).done(function(data){
    	if (data != null && data != "") {
    		
    		//Call assigned to token
    		if(data.type=="E" || data.type=="G")
    			{
		    		$.post(url+"/assignToEToken",{"id":data.nv_token_id,"assinedto":$('#loged_userid').val()},function(data){                       
		    			alert("Offline E Token Generated");
		    	    }).done(function(data){}).fail(function(xhr, status, error) {
		    	    	alert("Offline E Token Error, Please check with D4U Team.");
		    	    });
    			}
    		//$('#enquiryOldComment').html(data);
    		$('.etokenNo').text(data.type+data.queue);
    		$('.ename').text(data.name);
    		$('.emobile').text(data.mobileno);
    		$('.eenquiry').text(data.enqName);
    		$('.epriority').text(data.priorityNo);
    	} else {
    		//$('#enquiryOldComment').html("No record found");
    	}
    }).fail(function(xhr, status, error) {
    	
    });

}