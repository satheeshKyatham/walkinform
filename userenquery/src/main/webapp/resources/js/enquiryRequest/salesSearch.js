$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});

$(document).ready(function(){
	$("#mainPageLoad").fadeOut("slow"); 
});

function getDataByMobileNo(el){
	debugger
	$("#mainPageLoad").show();
	var validate=checkValidationOnSubmit('salesSearchForm');
	if(validate){
		setTimeout(function(){
			if($("#token").val()=="" || $('#countryCode').val()=="" || $('#mobileNo').val()=="" || $('#projectSfid').val()==""){
			   swal('Invalid Input Parameter');
			}else{
				validateMobileAndToken();
			}
		 },10);
		}else{
		   $("#mainPageLoad").fadeOut("slow");
	    }
}
function validateMobileAndToken(){
	var code=$('#countryCode').val();
	code=code.replace("+","%2B");
	var baseUrl=$("#contextPath").val();
	$.ajax({
		url: baseUrl+"/"+'validateMobileAndToken'+"/"+code+$('#mobileNo').val()+"/"+$("#token").val()+"/"+$('#projectSfid').val(),
		type: 'GET', 	  
	    success: function(data) { 
	    	debugger;
	    	if(data.success){    
		    	var url=$("#contextPath").val();
				window.location.href=url+"/"+'salesinfo?token='+$("#token").val()+'&countrycode='+code+'&mobileno='+$('#mobileNo').val()+'&projectSfid='+$('#projectSfid').val()+'&projectName='+$('#projectName').val();
				
	    	}else{
	    		 $("#mainPageLoad").fadeOut("slow"); 
            	 swal('Enquiry Not Found');
             }
	    },
	    beforeSend : function() {
        	$("#mainPageLoad").show();
        },
        error: function(e) {
	    	debugger;
	    	alert(e);
	    }
	});
}
function setCountryCode(el){
	$('#countryCode').val($('.selected-dial-code').text());
}