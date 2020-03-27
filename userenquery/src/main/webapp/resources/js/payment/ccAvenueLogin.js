
$(document).ready(function() {
	var namesfid=getProjectId();
	/*getProjectNameById(namesfid);*/
	
});

function getProjectId(){
	debugger
	var urlString = window.location.href;
	var url = urlString.split("=");
	var id=url[4];
	var namearr =  id.replace(/[&\/\\+,+()$~%.'":*?<>{}0-9]/g," ");    /*(/\+/g, " ");*/
	console.log("projectma",namearr);
	$(".loginProjTitle").text(namearr);
	return namearr;
}
