function submitForm(formObj){
	var formData = formObj.serialize() ;
	var uri =formObj.attr( 'action' );
	var baseUrl=$("#contextPath").val();
	uri=baseUrl+"/"+uri;
	$.ajax({
        type : "POST",
        data : formData, 
        url: uri,
        async: false,
        success: function (data) {
        	
        	try{
        		processResponse(data);
        	}catch(e){
        		console.log(e);
        		commonResponseHandler(data);
        	}
        },
        error: function (e) {
        	alert(e.responseText);
        }
    });
	//return false;
}

function submitIt(formId,action, callback){
	debugger
	$("#mainPageLoad").show();
	$("html, body").animate({ scrollTop: 0 }, "slow");
	$("#"+formId+" .commonErrorDiv").css('display','none');
	var validate=checkValidationOnSubmit(formId);
	if(validate)
		{
			
		
		
			//alert ("SUBMIT 11111111111111111111111111");
			
			var formData = $('#'+formId).serialize();
			var uri =action;
			var baseUrl=$("#contextPath").val();
			uri=baseUrl+"/"+uri;
			setTimeout(function(){
				$.ajax({
			
		        type : "POST",
		        data : formData,
		        url: uri,
		        async: false,
		        success: function (data) {
		        	debugger;
		        	if (formId == 'enquiryRequestCPBasicInfoForm') {
		        		cpPymtDataEoi ();
		        	}
		        	
		        	try{
		       			window[callback](data);
		        	}catch(e){
		        		console.log(e);
		        		commonResponseHandler(data);
		        	}
		        },
		        beforeSend : function() {
		        	$("#mainPageLoad").show();
		        },
		        complete : function() {
		        	$("#mainPageLoad").fadeOut("slow");
		        },
		        error: function (e) {
		        	alert(e.responseText);
		        }
				},10);
		    });
			return false;
		}
	else{
		/*CreateCaptcha();*/		
		$("#mainPageLoad").fadeOut("slow");
		
		$("#"+formId+" .commonErrorDiv").css('display','block');
	}
	return false;
	
}

function submitWithParam(url, paramFieldId, callback){
	
	var val = $("#"+paramFieldId).val();
	url = url+'/'+val;
	var baseUrl=$("#contextPath").val();
	url=baseUrl+"/"+url;
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: url,
        dataType:"json",
        async:false,
        success: function (data) {
        	
        	try{
        		window[callback](data);
        	}catch (e){
        		console.log(e);
        		urlResponseHandler(data);
        	}
        },
        error: function (e) {
        	alert("Exception: " + e.responseText);
        }
    });
	return false;
}
function submitWithTwoParam(url, firstParamFieldId,secondParamFieldId, callback){
	
	var firstValue = $("#"+firstParamFieldId).val();
	var secondValue = $("#"+secondParamFieldId).val();
	url = url+'/'+firstValue+'/'+secondValue;
	var baseUrl=$("#contextPath").val();
	url=baseUrl+"/"+url;
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: url,
        dataType:"json",
        async:false,
        success: function (data) {
        	
        	try{
        		window[callback](data);
        	}catch (e){
        		console.log(e);
        		urlResponseHandler(data);
        	}
        },
        error: function (e) {
        	alert("Exception: " + e.responseText);
        }
    });
	return false;
}

function submitToURL(url, callback){
	var baseUrl=$("#contextPath").val();
	url=baseUrl+"/"+url;
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: url,
        dataType:"json",
        async:false,
        success: function (data) {
        	try{
        		window[callback](data);
        	}catch (e){
        		console.log(e);
        		urlResponseHandler(data);
        	}
        },
        error: function (e) {
        	alert("Exception: " + e.responseText);
        }
    });
	return false;
}


function urlResponseHandler(data){
	if(data != null && data.message !=null){
		alert(data.message);
	}else{
		alert("OK");
	}	
}

function commonResponseHandler(data){
	if(data != null && data.response !== null && data.response.message != null){
		alert(data.response.message);
	}else{
		alert("OK");
	}	
}

function directSubmit(event,form, action){
	event.preventDefault();
	$("#"+form).attr( 'action', action );
	$("#"+form).submit();
}

function submitWithTwoParam(url, firstParamFieldId,secondParamFieldId, callback){
	
	var firstValue = $("#"+firstParamFieldId).val();
	var secondValue = $("#"+secondParamFieldId).val();
	url = url+'/'+firstValue+'/'+secondValue;
	var baseUrl=$("#contextPath").val();
	url=baseUrl+"/"+url;
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: url,
        dataType:"json",
        async:false,
        success: function (data) {
        	
        	try{
        		window[callback](data);
        	}catch (e){
        		console.log(e);
        		urlResponseHandler(data);
        	}
        },
        error: function (e) {
        	alert("Exception: " + e.responseText);
        }
    });
	return false;
}
function submitWithThreeParam(url, name,paramFieldName,paramFieldId,callback){
	
	$("#mainPageLoad").show();
	var value = $("#"+paramFieldId).val();
	url = url+'/'+name+'/'+paramFieldName+'/'+value;
	var baseUrl=$("#contextPath").val();
	url=baseUrl+"/"+url;
	setTimeout(function(){
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: url,
        dataType:"json",
        async:false,
        success: function (data) {
        	
        	try{
        		window[callback](data);
        	}catch (e){
        		console.log(e);
        		urlResponseHandler(data);
        	}
        },
        beforeSend : function() {
        	$("#mainPageLoad").show();
        },
        complete : function() {
        	$("#mainPageLoad").fadeOut("slow");
        },
        error: function (e) {
        	alert("Exception: " + e.responseText);
        }
	},10);
    });
	return false;
}

function submitWithParamByParamClass(url, paramFieldClass, callback){
	
	var firstValue = $("."+paramFieldClass).val();
	url = url+'/'+firstValue;
	var baseUrl=$("#contextPath").val();
	url=baseUrl+"/"+url;
	$.ajax({
        type : "POST",
        contentType : "application/json",
        url: url,
        dataType:"json",
        async:false,
        success: function (data) {
        	
        	try{
        		window[callback](data);
        	}catch (e){
        		console.log(e);
        		urlResponseHandler(data);
        	}
        },
        error: function (e) {
        	alert("Exception: " + e.responseText);
        }
    });
	return false;
}
function submitWithFile(formId,action, callback){
	debugger
	$("#mainPageLoad").show();     
	$("html, body").animate({ scrollTop: 0 }, "slow");
	$("#"+formId+" .commonErrorDiv").css('display','none');
	var validate=checkValidationOnSubmit(formId);
	if(validate)
		{
		
			var formData =new FormData($('#'+formId)[0]);
			var uri =action;
			var baseUrl=$("#contextPath").val();
			uri=baseUrl+"/"+uri;
			setTimeout(function(){
				$.ajax({
			
		        type : "POST",
		        data : formData,
		        url: uri,
		        async: false,
		        processData: false,
		        contentType: false,
		        success: function (data) {
		        	
		        	
		        	try{
		       			//window[callback](data);
		        		if (action == 'savePaymentInfo') {
		        			
		        			var sizeValidate = 0;
		        			
		        			$("#csPtColEoi .csPtDataRowEoi").each(function () {
		        				
		        				//alert("PAN ::: " + $(this).find('.panAttachEoi')[0].files[0].size);
		        				
		        				//alert("Receipt ::: " + $(this).find('.receiptAttachEoi')[0].files[0].size);
		        				
		        				if ($(this).find('.panAttachEoi')[0].files[0].size < 5242880 && $(this).find('.receiptAttachEoi')[0].files[0].size < 5242880 ) {
		        					
		        				} else {
		        					sizeValidate++;
		        				}
		        				
		        			});
		        			
		        			if (sizeValidate < 1) {
		        				csPymtDataEoi ();
				        		insertEOIPreference();
		        			} else {
		        				swal({
		        					title: "File size must be less than 5 MB",
		        				    text: "",
		        				   // timer: 3000,
		        				    type: "error",
		        				});
		        				//alert ("File size must be less than 5 MB");
		        			}
		        			
		        			window[callback](data);
			        	}
		        	}catch(e){
		        		console.log(e);
		        		commonResponseHandler(data);
		        	}
		        	
		        	
		        	
		        	
		        },
		        beforeSend : function() {
		        	$("#mainPageLoad").show();
		        },
		        complete : function() {
		        	$("#mainPageLoad").fadeOut("slow");
		        },
		        error: function (e) {
		        	alert(e.responseText);
		        }
				},10);
		    });
			return false;
		}
	else{
		
		if (action == 'savePaymentInfo') {
			//$('.nav-tabs > .active').prev('li').find('a').trigger('click');
			$("#bankPaymentForm .commonErrorDiv").css('display','block !important');
    	}
		
		/*CreateCaptcha();*/		
		$("#mainPageLoad").fadeOut("slow");
		
		$("#"+formId+" .commonErrorDiv").css('display','block');
	}
	return false;
	
}