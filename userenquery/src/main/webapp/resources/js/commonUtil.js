var existingClass="";
BTNLOADING = {
	loading: function(id,condition) {
		if (condition == "show") {
			existingClass = $('#'+id).attr('class');
			$('#'+id).attr('class', '');
		    $('#'+id).attr('class', 'fa fa-spinner fa-spin');
		} else if (condition == "hide") {
			$('#'+id).attr('class', '');
		    $('#'+id).attr('class', existingClass);
		}
	}
}