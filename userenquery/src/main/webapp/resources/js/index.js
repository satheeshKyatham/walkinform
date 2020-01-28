$( document ).ready(function() {
     
   /* var selectedInput = null;
	$(function() {
	    $('#mobileno').focus(function() {
	        selectedInput = this;
	    }).blur(function(){
	        selectedInput = null;
	        if($('#mobileno').val().length==10){
	        	_search_data($('#mobileno').val());
	        }
	        else{
	        	alert("Enter valid no");
	        }
	        
	    });
	});*/
});

/*function _search_data(no){
	alert();
	$.post("getUserDeatails",
			{
				"mobileno" :no,
				"projectid" : ""				
			},
			function(data) {				 
		 
		 	if (data != null) {
				$("#email").val(data.email);
				$("#birthdate").val(data.birthdate);
				$("#lastname").val(data.lastname);
				$("#firstname").val(data.firstname);
				}
		 	else{
		 		$("#email").val('');
				$("#birthdate").val('');
				$("#lastname").val('');
				$("#firstname").val('');
		 	}
			 }, "json");
}*/



$("#mobileNo").intlTelInput({
	separateDialCode: true ,
	//autoFormat:false,
	utilsScript: "https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/8.4.6/js/utils.js",
	preferredCountries: ["in"]
});


$("#enMobileNo").intlTelInput({
	separateDialCode: true ,
	//autoFormat:false,
	utilsScript: "https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/8.4.6/js/utils.js",
	preferredCountries: ["in"]
});


$(".input-group-addon").click(function() {
	$('#countryCode').val($('.selected-dial-code').text());
});
