function validateLogin(el) {
	
	debugger;
	$("#mainPageLoad").show();
	var validate=checkValidationOnSubmit('salesLoginForm');
	if(validate){
	    var sendingData = {
			emailid:$("#userid").val(),
			password:$("#password").val()
		}

		$.ajax({
			url: 'userValidate',
			data: sendingData,
		    type: 'POST', 	  
		    success: function(data) { 
		     
		       loginResp(data);
		    },
		    beforeSend : function() {
	        	$("#mainPageLoad").show();
	        },
	      /*  complete : function() {
	        	$("#mainPageLoad").fadeOut("slow");
	        },*/
		    error: function(e) {
		    	debugger;
		    	alert(e);
		    }
		});
	}else{
		$("#mainPageLoad").fadeOut("slow");
	}
}
function loginResp(data){
	debugger;
	console.log(data);
	if(data.isActive==="A"){
		 var url=$("#contextPath").val();
		/* window.location.href=url+"/"+"salesDeskSearch"+"/"+data.projectId+"/"+data.projectName;*/	
	     var form = $('<form modelAttribute="userSession" action="salesDeskSearch" method="POST" >' +
	       '<input type="text" name="projectId" value="' + data.projectId + '" />' +
	       '<input type="text" name="projectName" value="' + data.projectName + '" />' +
	       '<input type="text" name="userName" value="' + data.emailid+ '" />' +
	       '<input type="text" name="isActive" value="' + data.isActive + '" />' +
	       '<input type="text" name="role" value="' + data.role + '" />' +
	       '<input type="text" name="userId" value="' + data.user_id + '" />' +
	       '</form>');
	     $('body').append(form);
	     form.submit();
	}else{
		$("#mainPageLoad").fadeOut("slow");
		swal('Invalid Username/Password');
		 
	}
}