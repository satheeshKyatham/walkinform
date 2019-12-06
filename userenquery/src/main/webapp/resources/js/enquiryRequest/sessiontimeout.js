$.ajaxSetup({
    statusCode: {
       401: function(){
          location.reload();
        }
    }
});
$(document).ready(function() {
	debugger
	var seconds = 3;
	$("#lblCount").html(seconds);
	 setInterval(function () {
		 	if(seconds>0){
		 		seconds--;
		 	}
	        $("#lblCount").html(seconds);
	        if (seconds == 0) {
	        	 /*$("#lblCount").hide();*/
	        	seconds=0;
	        	 $("#lblCount").html(seconds);
	            /*$("#dvCountDown").hide();*/
	           /* window.location = "https://www.google.com/";*/
	        }
	    }, 1000);
});



/*$("#btnRedirect").click(function () {
    var seconds = 5;
    $("#dvCountDown").show();
    $("#lblCount").html(seconds);
    setInterval(function () {

        seconds--;
        $("#lblCount").html(seconds);
        if (seconds == 0) {
            $("#dvCountDown").hide();
            window.location = "https://www.google.com/";
        }
    }, 1000);
});*/
