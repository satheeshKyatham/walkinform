
$(document).ready(function() {
	var namesfid=getProjectIname();	
});

function getProjectIname(){
	debugger
	var urlString = window.location.href;
	var url = urlString.split("=");
	var id=url[4];
	var namearr =  id.replace(/[&\/\\+,+()$~%.'":*?<>{}0-9]/g," ");    
	console.log("projectma",namearr);
	$(".loginProjTitle").text(namearr);
	return namearr;
}
