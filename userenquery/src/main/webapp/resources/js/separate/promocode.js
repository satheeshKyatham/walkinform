$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});

function discountType(e){
	if ($(e).find('option:selected').val() == "0") {
		$('.perDisInput').show();
		$('.flatDisInput').hide();
	} else if ($(e).find('option:selected').val() == "1") {
		$('.perDisInput').hide();
		$('.flatDisInput').show();
	}
};