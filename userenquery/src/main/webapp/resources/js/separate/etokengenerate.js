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
	
	var inputMobile =  $('#enMobileNoEtoken').val();
	var inputCountryCode =  $('.selected-dial-code').text();
	$.post(url+"/android_generateTokenMobileEOIData",{"countryCode":inputCountryCode.substring(3),"mobileNo":inputMobile,"projectname":$('#projectSfid').val(),"createdBy":$('#loged_userid').val()},function(data){                       
	       
    }).done(function(data){
    	if (data != null && data != "") {
    		alert(data.message);
    		//$('#enquiryOldComment').html(data);
    		/*$('.etokenNo').text(data.type+data.queue);
    		$('.ename').text(data.name);
    		$('.emobile').text(data.mobileno);
    		$('.eenquiry').text(data.enqName);
    		$('.epriority').text();*/
    	} else {
    		//$('#enquiryOldComment').html("No record found");
    	}
    }).fail(function(xhr, status, error) {
    	
    });
	}
function getGeneratedEtoken(){
	
	var url=$("#contextPath").val();//$('#enquirysfid').val()
	
	var inputMobile =  $('#enMobileNoEtoken').val();
	var inputCountryCode =  $('.selected-dial-code').text();
	$.post(url+"/android_generateTokenMobileEOI",{"countryCode":inputCountryCode.substring(3),"mobileNo":inputMobile,"projectname":$('#projectSfid').val(),"createdBy":$('#loged_userid').val()},function(data){                       
	       
    }).done(function(data){
    	if (data != null && data != "") {
    		//$('#enquiryOldComment').html(data);
    		$('.etokenNo').text(data.type+data.queue);
    		$('.ename').text(data.name);
    		$('.emobile').text(data.mobileno);
    		$('.eenquiry').text(data.enqName);
    		$('.epriority').text();
    	} else {
    		//$('#enquiryOldComment').html("No record found");
    	}
    }).fail(function(xhr, status, error) {
    	
    });

}