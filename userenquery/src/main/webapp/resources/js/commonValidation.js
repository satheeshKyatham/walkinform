var validationFlag=false;
var arr_error=[
	"Enter Six Digit Pincode",
	"Enter Only number",
	"Password and Confirmed Password do not match",
	"Invalid Email",
	"The password you have entered does not match the guidelines mentioned above. Please re-enter.",
	"This Field is required",
	"Enter Correct Mobile No.",
	"Invalid Number",
	"Pan Card should be eg.ABCDF1234H",
	"From date should be less than To date",
	"To date should be greater than From date",
	"Invalid Decimal value",
	"Enter only alphabetes",
	"Enter only alpha-numeric value",
	"Special character not allowed",
	"Enter value more than Zero",
	"Enter correct Telephone no.",
	"Enter correct Year value",
	"Enter correct Tractor Sr No",
	"Enter correct Registration No",
	"Credit Limit below 10k only",
	"Enter alpha numeric with single hyphen",
	"Select One",
	"IFSC Code should be eg. 11 digit alphanumeric",
	"Enter proper year",
	"File is Mandatory",
	"Only / or - special characters can be used",
	"GSTIN No should be eg.12PANNO1234R1WE1",
	"Date is Mandatory",
	"Cannot be Zero",
	"Enter only alphabetes or space",
	"Is not a valid channel partner"
 ];

$(document).ready(function(){
	
	checkKeyPressEvents();
	
	$(".dropDown,.requiredField,.requiredDate,.requireddot,.emailaddress,.ifscCode,.panNumber,.gstIn,.onlyNumber,.mobile,.requiredalphabetics,.requiredalphabeticsWithSpace,.requiredalphanumericWithSpaceAndDot,.multiplemobile,.pincode,.telephone,.expYear,.alphaNumeric,.alphaNumericWithSpace,.alphaNumericWithSpaceAndSpecialCharacter,.moreThanZero,.requiredFile,.requiredDate").change(function(){
		//debugger
		if($(this).val()!="" || $(this).attr('disabled')=='disabled'){
			$(this).removeClass('errorinput');
	   	}else{
	   		$(this).addClass('errorinput');
	   	}
   });
  $(".requiredField").on('keyup', function (){
		$(this).removeAttr('style');
		/*$(this).removeAttr('title');*/
   });
    	
	  $(".emailaddress").on('keyup', function (){
		  //debugger;
		  $("#errorParagraph").remove();
		  var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
		  if(!emailReg.test($(this).val())){
			  $(this).parent().append("<p id='errorParagraph' style='color:red;margin-bottom: 20px;'>Invalid email address</p>");
		  }		  
	  });
});
function checkValidationOnSubmit(id){
	debugger;
	validationFlag=true;
	var valArray =$('#'+id).find('input, textarea, select, .requiredRadio');

	//alert ("Test Length ::: " + $('#'+id).find('input[type=radio]').length);
	
	$.each(valArray,function(index,obj){
		
		if($(obj).hasClass("requiredRadio")){
			if ($(this).find('input[type=radio]:checked').length == 0) {
				
				
				$(this).addClass('errorRadio');
				
				$(this).attr('style', 'border-left: 0 !important');
				
				setValidationMsg(5,this);
				validationFlag=false;
				//$(this).hide();
				
				//alert ("Condition TRUE");
	        }
		}
		
		
		
		if($(obj).attr("disabled")!="disabled"){
			
			// Decimal point number
			
			
			/*if($('input[type=radio][class=muuttuminen]:checked').length == 0)
			{
				alert("Please select atleast one");
				return false;
			}*/
			
			
			if($(obj).hasClass("requiredField")){
				if (isEmpty($(this).val()) || $(this).val()=="select"){
					console.log('mandatory field-->>'+$(this).attr('id')+'-->'+$(this).attr('name'));
					$(this).addClass('errorinput');
			    	setValidationMsg(5,this);
					validationFlag=false;
				}else{
					$(this).removeClass('errorinput');
					removeValidationMsg(this,validationFlag);
				}
			}
			if($(obj).hasClass("requiredHidden")){
				var hiddenField=$(this).data('id');
				if($("#"+hiddenField).val()!=""){
					removeValidationMsg(this,validationFlag);
			    }else{
			    	$(this).addClass('errorinput');
			    	setValidationForFile(31,this);
			    	validationFlag=false;
			    }
			}
			if($(obj).hasClass("requireddot")){
				var nostr =/^[0-9]*\.?[0-9]*$/;
				if(checkPattern($(this).val(),nostr)){
					var arr=$(this).val().split('');
					if(arr[arr.length-1]=='.'){
						if(!$(this).hasClass('requiredField')){
							$(this).val('0');
						}
			    		$(this).addClass('errorinput');
			    	    setValidationMsg(11,this);
			    	    validationFlag=false;
			    	}else{
			    		$(this).removeClass('errorinput');
			    		removeValidationMsg(this,validationFlag,validationFlag);
			    	}
			    }else{
			    	if(!$(this).hasClass('requiredField'))
			    	     $(this).val('0');
			    	$(this).addClass('errorinput');
			    	     setValidationMsg(11,this);
			    	     validationFlag=false;
			    }  
			} 
		// Email Address validation
			if($(obj).hasClass("emailaddress")){	
				//debugger
				var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
				if($(this).val()!=""){
			    	if(emailReg.test($(this).val())){
			    		$(this).removeClass('errorinput');	
			    		removeValidationMsg(this,validationFlag,validationFlag);
			    	}else{
			    		$(this).addClass('errorinput');
			    		setValidationMsg(5,this);
			    		validationFlag=false;
			    	} 
				}else{
					$(this).addClass('errorinput');
		    		setValidationMsg(3,this);
		    		validationFlag=false;
				}
			}
		// IFSC validation
			if($(obj).hasClass("ifscCode")){
				var ifscCode = /^([A-Z]{4}[A-Z0-9]{7})+$/;
		    	if(ifscCode.test($(this).val())){
		    		$(this).removeClass('errorinput');
		    			removeValidationMsg(this,validationFlag,validationFlag);
		    	}else{
		    		$(this).addClass('errorinput');
		    		setValidationMsg(23,this);
		    		validationFlag=false;
		    	}   
			}
			if($(obj).hasClass("panNumber")){
				var panno = /^([a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1})+$/;
		    	if(panno.test($(this).val())){
		    		$(this).removeClass('errorinput');
		    			removeValidationMsg(this,validationFlag,validationFlag);
		    	}else{
		    		$(this).addClass('errorinput');
		    		setValidationMsg(8,this);
		    		validationFlag=false;
		    	}    
			}
			if($(obj).hasClass("gstIn")){
				var gstin = /^([0-9]{2}[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}[0-9]{1}[a-zA-Z]{1}[a-zA-Z0-9]{1})+$/;
				if(gstin.test($(this).val())){
					$(this).removeClass('errorinput');
					removeValidationMsg(this,validationFlag,validationFlag);
				}else{
					$(this).addClass('errorinput');
					setValidationMsg(27,this);
					validationFlag=false;
				}
			}
		// Validate Number
			if($(obj).hasClass("onlyNumber")){
				if($(this).val().match('^[0-9]+$')){
					$(this).removeClass('errorinput');
			    		 removeValidationMsg(this,validationFlag);
			    }else{
			    	$(this).addClass('errorinput');
	    		 	setValidationMsg(1,this);
	    		 	validationFlag=false;
				  }
			  }
			    	
		  }
		 // Mobile Number Validation
		if($(obj).hasClass("mobile")){
			debugger;
			 if($(this).val()==null || $(this).val()==""){
				 $(this).addClass('errorinput');
				 setValidationMsg(6,this);
				 validationFlag=false;
			 }else{
				 var mobileNo = /^([0-9]{10})+$/;
				 /*if($(this).val().match('^[0-9]+$')){*/
				 if(mobileNo.test($(this).val())){
					 if ($(this).val()<999999999 || $(this).val().length<10){
				 		$(this).addClass('errorinput');
						setValidationMsg(6,this);
						validationFlag=false;
					 }else{
			    		$(this).removeClass('errorinput');
						removeValidationMsg(this,validationFlag);
					 }
				 }else{
					 //changes by Satheesh - for more than or less than mobile number
					 /*$(this).addClass('errorinput');
					 setValidationMsg(6,this);
					 validationFlag=false;*/
				 }
			   }
		  }
		// Alphabetics Validation
		if($(obj).hasClass("requiredalphabetics")){
			var val=$(this).val();
			var alphaExp = /^[a-zA-Z ]+$/;
			if(val.charAt(0)==' '){  
				   $(this).val("");
				   $(this).addClass('errorinput');
				   setValidationMsg(12,this);
				   validationFlag=false;
			}else if($(this).val().match(alphaExp)){
				$(this).removeClass('errorinput');
				removeValidationMsg(this,validationFlag);
			}else{
				$(this).addClass('errorinput');
				  setValidationMsg(12,this);
				  validationFlag=false;
			}
		}
		 
		if($(obj).hasClass("requiredalphabeticsWithSpace")){
			var val=$(this).val();
			var alphaExp = /^[a-zA-Z ]+$/;
			if(val.charAt(0)!=' ' && val.match(alphaExp)){
				$(this).removeClass('errorinput');
				removeValidationMsg(this,validationFlag);
			}else{
				$(this).addClass('errorinput');
				  setValidationMsg(12,this);
				  validationFlag=false;
			}
		}
		if($(obj).hasClass("requiredalphanumericWithSpaceAndDot")){
			
			var val=$(this).val();
			var alphaExp = /^[a-zA-Z0-9\.\s]+$/;
			if(val.charAt(0)==' '){
				$(this).addClass('errorinput');
				   setValidationMsg(13,this);
				   validationFlag=false;
			}else if($(this).val().match(alphaExp)){
				$(this).removeClass('errorinput');
				removeValidationMsg(this,validationFlag);
			}else{
				$(this).addClass('errorinput');
				  setValidationMsg(13,this);
				  validationFlag=false;
			}
		}
		// Multiple Mobile Number Validation
		if($(obj).hasClass("multiplemobile")){
				var val=$(this).val();
				var alphaExp = /^(\d{10},)*\d{10}$/; 
				if(val.charAt(0)==' '){  
					$(this).addClass('errorinput');
					setValidationMsg(6,this);
					validationFlag=false;
				}else if($(this).val().match(alphaExp)){
					$(this).removeClass('errorinput');
					removeValidationMsg(this,validationFlag);
				}else{
					$(this).addClass('errorinput');
					setValidationMsg(6,this);
					validationFlag=false;
				}
			}
		
		//Pin Code validation 
		if($(obj).hasClass("pincode")){ 
			   var pin_val=$(this).val();
			   if($(this).val().match('^[0-9]+$')){
				   if(pin_val.length<6 || pin_val.length>6){
					   $(this).addClass('errorinput');
					   setValidationMsg(0,this);
					   validationFlag=false;
				   }else{
					   $(this).removeClass('errorinput');
					   removeValidationMsg(this,validationFlag);
				   }
			   }else{
				   $(this).addClass('errorinput');
				   setValidationMsg(1,this);
				   validationFlag=false;
			   }
		}
		
		// Telephone Number Validation
		if($(obj).hasClass("telephone")){ 
			   var val=$(this).val();
			   if($(this).val().match('^[0-9]+$')){
				   if(val.length<8 || val.length>15){
					   $(this).addClass('errorinput');
					   setValidationMsg(16,this);
					   validationFlag=false;
				   }else{
					   $(this).removeClass('errorinput');
					   removeValidationMsg(this,validationFlag);
				   }
			   }else{
				   $(this).addClass('errorinput');
				   setValidationMsg(1,this);
				   validationFlag=false;
			   }
		  }
		
		// Experience year  Validation
		if($(obj).hasClass("expYear")){
			  var val=$(this).val();
			  if($(this).val().match('^[0-9]+$')){
				   if(val.length<1 || val.length>2){
					   $(this).addClass('errorinput');
					   setValidationMsg(24,this);
					   validationFlag=false;
				   }else{
					   $(this).removeClass('errorinput');
					   removeValidationMsg(this,validationFlag);
				   }
			   }else{
				   $(this).addClass('errorinput');
				   setValidationMsg(1,this);
				   validationFlag=false;
			   }
		  }
		
		//From Date validation 
		if($(obj).hasClass("fromDate")){
			   var toDate=$('.toDate').val();
			   var fromDate=$(this).val();
			   var nfrdt=fromDate.substring(3,5)+'/'+fromDate.substring(0,2)+'/'+fromDate.substring(6);
			   var ntodt=toDate.substring(3,5)+'/'+toDate.substring(0,2)+'/'+toDate.substring(6);
			   var frmdt=new Date(nfrdt);
			   var todt=new Date(ntodt);
			   var frdd=frmdt.getDate();
			   var frmm=frmdt.getMonth()+1;
			   var fryy=frmdt.getFullYear();
			   if(frdd<10){
				   frdd='0'+frdd;
			   }
			   if(frmm<10){
				   frmm='0'+frmm;
			   }
			   frmdt=fryy+'-'+frmm+'-'+frdd;
			   
			   todd=todt.getDate();
			   tomm=todt.getMonth()+1;
			   toyy=todt.getFullYear();
			   if(todd<10){
				   todd='0'+todd;
			   }
			   if(tomm<10){
				   tomm='0'+tomm;
			   }
			   todt=toyy+'-'+tomm+'-'+todd;
			   
			   if(frmdt>todt){
				   $(this).addClass('errorinput');
				   setValidationMsg(9,this);
				   validationFlag=false;
			   }else{
				   $(this).removeClass('errorinput');
				   removeValidationMsg(this,validationFlag);
			   }
	    }
		 
		//To Date validation 
		if($(obj).hasClass("toDate")){
			   var fromDate=$('.fromDate').val();
			   var toDate=$(this).val();
			   if(fromDate==null || fromDate=="" || fromDate.length==0){
				  $(this).removeClass('errorinput');
				  removeValidationMsg(this,validationFlag);
			   }else{
				   nfrdt=fromDate.substring(3,5)+'/'+fromDate.substring(0,2)+'/'+fromDate.substring(6);
				   ntodt=toDate.substring(3,5)+'/'+toDate.substring(0,2)+'/'+toDate.substring(6);
				   var frmdt=new Date(nfrdt);
				   var todt=new Date(ntodt);
				   var frdd=frmdt.getDate();
				   var frmm=frmdt.getMonth()+1;
				   var fryy=frmdt.getFullYear();
				   if(frdd<10){
					   frdd='0'+frdd;
				   }
				   if(frmm<10){
					   frmm='0'+frmm;
				   }
				   frmdt=fryy+'-'+frmm+'-'+frdd;
				   
				   todd=todt.getDate();
				   tomm=todt.getMonth()+1;
				   toyy=todt.getFullYear();
				   if(todd<10){
					   todd='0'+todd;
				   }
				   if(tomm<10){
					   tomm='0'+tomm;
				   }
				   todt=toyy+'-'+tomm+'-'+todd;
				   
				   if(frmdt>todt){
					   $(this).addClass('errorinput');
					   setValidationMsg(10,this);
					   validationFlag=false;
				   }else{
					   $(this).removeClass('errorinput');
					   removeValidationMsg(this,validationFlag);
				   }  
		      }
		  }
		
		if($(obj).hasClass("dropDown")){
			  
			  if($(this).val()!=""){
				  $(this).removeClass('errorinput');
		    		 removeValidationMsg(this,validationFlag);
			  }else{
		    	$(this).addClass('errorinput');
		    	setValidationMsg(22,this);
		    	validationFlag=false;
			  }
		  }
		// Alpha Numeric Validation
		if($(obj).hasClass("alphaNumeric")){
			if($(this).val().match(/^[a-zA-Z0-9]+$/)){
	    		 $(this).removeClass('errorinput');
	    		 removeValidationMsg(this,validationFlag);
	    	 }else{
	    		 $(this).addClass('errorinput');
	    		 	setValidationMsg(13,this);
	    		 	validationFlag=false;
	    	}
		}
		
		// Alpha Numeric with Space Validation
		if($(obj).hasClass("alphaNumericWithSpace")){
			if($(this).val().match(/^[a-zA-Z0-9 ]+$/)){
	    		 $(this).removeClass('errorinput');
	    		 removeValidationMsg(this,validationFlag);
	    	 }else{
	    		 $(this).addClass('errorinput');
	    		 setValidationMsg(13,this);
	    		 validationFlag=false;
	    	}
		  }
				
				// Alpha Numeric with Space and special characters Validation
			
		if($(obj).hasClass("alphaNumericWithSpaceAndSpecialCharacter")){
			if($(this).val().match(/^[a-zA-Z0-9\/\-\s]+$/)){
				$(this).removeClass('errorinput');
	    		removeValidationMsg(this,validationFlag);
	    	}else{
	    		$(this).addClass('errorinput');
	    		setValidationMsg(26,this);
	    		validationFlag=false;
	    	}
		}
				
		 // Number greater than zero
		if($(obj).hasClass("moreThanZero")){
			  if(Number($(this).val())>0){
				  $(this).removeClass('errorinput');
				  removeValidationMsg(this,validationFlag);
			  }else{
				  $(this).addClass('errorinput');
				  setValidationMsg(15,this);
			  }
		}
		
		 /* For File Mandatory */
		if($(obj).hasClass("requiredFile")){
			var hiddenField=$(this).data('id');
			if($("#"+hiddenField).val()!=""){
				removeValidationMsg(this,validationFlag);
		    }else{
		    	$(this).addClass('errorinput');
		    	setValidationForFile(25,this);
		    	validationFlag=false;
		    }
		}
		 /* For Date Mandatory*/
		if($(obj).hasClass("requiredDate")){
			if($(this).val()!=""){
				$(this).removeClass("errorinput");
		    	removeValidationMsg(this,validationFlag);
		    }else{
		    		$(this).addClass('errorinput');
		    		setValidationForFile(28,this);
		    		validationFlag=false;
		    }
		 }
});
return validationFlag;
}

function setValidationForFile(msgNo,field)
{
	
	var msg=arr_error[msgNo];
	$(field).val(''); 
	$(field).attr('placeholder',msg);
	$(field).attr('title',msg);
	$(field).addClass("errorinput");
	tabAlert();
	
}
function setValidationMsg(msgNo,field){
	var msg=arr_error[msgNo];
	var title=$(field).attr('title');
	$(field).attr('title',title+msg+';');
	$(field).addClass("errorinput");
	console.log($(field).attr('name')+'  '+msg);
	tabAlert();
} 

function removeValidationMsg(field,validationMsg){
	if(validationMsg){
		$(field).removeClass('plchBorder');
		//Start code to remove hightlight error
		var result = $(".tab-pane.active").find(".plchBorder");
		//console.log("removeValidationMsg: "+result.length);
		if(result.length == 0)
			$('li.active > a').removeClass('tab-error-cls');
		else
			$('li.active > a').addClass('tab-error-cls');
	}
	//End code to remove hightlight error
}

function highlightTabs(el){
	
	var $tabpane = el.closest('.tab-pane');
	if($tabpane.length > 0){ /* check if it is a tab-pane*/
	    var $tabpaneid = $tabpane[0].id // finding closest tab-pane id //** added by swapnil **//
	    var $tabpanelink = $("a[href='#"+$tabpaneid+"']").first() // finding link for the id  //  added by swapnil //
	}
	
	 if($tabpane.length > 0){ /* check if it is a tab-pane*/	        
		 $tabpanelink.addClass('tab-error-cls');        
    }
	 
}
function tabAlert(){

	if($('.tab-pane').hasClass('active')){
		var result = $(".tab-pane.active").find(".plchBorder");
		//console.log("setValidationMsg: "+result.length);
		if(result.length > 0)
			$('li.active > a').addClass('tab-error-cls');
		else
			$('li.active > a').removeClass('tab-error-cls');		
	}
	
	$.each($('.tab-pane'), function(key, value) {
		var result = $(value).find(".plchBorder");
		if (result.length > 0) {
			highlightTabs($(value))
		} 
	});
	
};

function checkKeyPressEvents(){
	
	// Restrict to enter letters in Number field
	$('.mobile,.multiplemobile,.onlyNumber,.pincode,.telephone,.year,.numericField').on('keypress', function(e) {
        var k = (e.which) ? e.which : e.keyCode;
        var ok = k >= 48 && k <= 57 || k==127 || k==8 || k==9; // 0-9
     
        if (!ok){
            e.preventDefault();
        }
	});
	$('.onlyDate').on('keypress', function(e) {
		//debugger
        var k = (e.which) ? e.which : e.keyCode;
        if(!$(this).val().match(/^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/)){
        	e.preventDefault();
        }
	});
	
	// Restrict to enter letters in Number field
	$('.decimalNumber').on('keypress', function(e) {
        var k = (e.which) ? e.which : e.keyCode;
        var ok = k >= 48 && k <= 57 || k==127 || k==8 || k==46 || k==9; // 0-9
     
        if (!ok){
            e.preventDefault();
        }
	});
	
	// Restrict to enter number in Alphabet field
	$('.requiredalphabetics').on('keypress', function(e) {
        var k = (e.which) ? e.which : e.keyCode;
        var ok = k >= 65 && k <= 90 || // A-Z
            k >= 97 && k <= 122 || k==127 || k==8 || k==9; // a-z
     
        if (!ok){
            e.preventDefault();
        }
	}); 
	
	// Restrict to enter number in Alphabet and space field
	$('.requiredalphabeticsWithSpace').on('keypress', function(e) {
        var k = (e.which) ? e.which : e.keyCode;
        var ok = k >= 65 && k <= 90 || // A-Z
            k >= 97 && k <= 122 || k==127 || k==8 || k==32 || k==9; // a-z
     
        if (!ok){
            e.preventDefault();
        }
	}); 

	// keypress event for alphanumeric
	$('.alphaNumeric').on('keypress', function(e) {
        //var k = e.which;
        var k = (e.which) ? e.which : e.keyCode;
        var ok = k >= 48 && k <= 57 || k==127 || k==8 || // 0-9
		        k >= 65 && k <= 90 || // A-Z
	            k >= 97 && k <= 122 || k==127 || k==9; // a-z
     
        if (!ok){
            e.preventDefault();
        }
  });
 
	// keypress event for alphanumeric with space
 $('.alphaNumericWithSpace').on('keypress', function(e) {
        //var k = e.which;
	 	var k = (e.which) ? e.which : e.keyCode;
        var ok = k >= 48 && k <= 57 || k==127 || k==8 || // 0-9
		        k >= 65 && k <= 90 || // A-Z
	            k >= 97 && k <= 122 || k==127 || k==9 || k==32; // a-z
     
        if (!ok){
            e.preventDefault();
        }
  });
} 
function isZero(ele){
	var k = Number($(ele).val()) ;
 
    if (k==0 || k=='0' ){
	   	 var msg=arr_error[29];
	   	 Alert.warn(msg);
	   	 $(ele).val('');
	   	 $(ele).addClass('errorinput');
    }else{
		 $(ele).removeClass('errorinput');
    }
}

function isZero(ele){
	var k = Number($(ele).val()) ;
 
    if (k==0 || k=='0' ){
	   	 return true;
    }else{
		return false;
    }
}

function isZeroValue(value){
	var k = Number(value) ;
 
    if (k==0 || k=='0' ){
	   	 return true;
    }else{
		return false;
    }
}

//Numeric (Number) values only allowed (Without Decimal Point)
$(document).on("keypress keyup blur", ".numericWithoutDecimal", function (event) {    
	$(this).val($(this).val().replace(/[^\d].+/, ""));
	if ((event.which < 48 || event.which > 57)) {
		event.preventDefault();
	}
});

//Numeric (Number) values only allowed (with Decimal Point)
$(document).on("keypress keyup blur", ".numericWithDecimal", function (event) {
	$(this).val($(this).val().replace(/[^0-9\.]/g,''));
    if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
        event.preventDefault();
    }
});