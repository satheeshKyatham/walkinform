$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});

let searchParams = new URLSearchParams(window.location.search)

var htmlGV = "";
htmlGV += 	'<tr> <td class="tokenCol">&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>'
			+'<tr> <td class="tokenCol">&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td> </tr>'
			+'<tr> <td class="tokenCol">&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td> </tr>'
			+'<tr> <td class="tokenCol">&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td> </tr>'
			+'<tr> <td class="tokenCol">&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td> </tr>'
			+'<tr> <td class="tokenCol">&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td> </tr>'
			+'<tr> <td class="tokenCol">&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td> </tr>';

var pageContext = $("#pageContext").val()+'/'; 
$("#myCarousel").on('slide.bs.carousel', function (){
	console.log($('.item.active').index());  
	
	
	
	if ($('.item.active').index() == 0) {
		//alert ("Upcoming");
		//$("#tokenUpcomingTable tbody").append(htmlGV);			
		getPendingAssignList();
	} else if ($('.item.active').index() == 1) {
		//alert ("Assigned");
		//$("#tokenAssignedTable tbody").append(htmlGV);
		getAssignedList ();
	}
	
});


let type = searchParams.get('type');
let project = searchParams.get('project');
let fromdate = searchParams.get('fromdate');
let todate = searchParams.get('todate');
let projectname = searchParams.get('projectname');

var av = "";
var image = "";


if (searchParams.has('eoicount') == true || searchParams.has('eoitext') == true){
	let eoicount = searchParams.get('eoicount');
	let eoitext = searchParams.get('eoitext');	
	
	$('#eoiCount').html(eoicount);
	$('#eoiText').html(eoitext);
} else {
	$('.eoiMainCol').remove();
}

if (searchParams.has('av')){
	av = searchParams.get('av');
	//$("video source").attr("src", encodeURI(searchParams.get('av')));
	
	var video = $('<video />', {
        id: 'video',
        src: encodeURI(searchParams.get('av')),
        type: 'video/mp4',
        controls: true,
        loop: true,
        height: '100%'
    });
    video.appendTo($('.videoImageCol'));
	
} else if (searchParams.has('image')) {
	
	image = searchParams.get('image');
	
	$('.videoImageCol').empty();
	$('.videoImageCol').html("<img height='100%' src='"+image+"'>");
	
} else {
	$('.mediaCol').remove();
}

if (searchParams.has('image') == false && searchParams.has('av') == false && searchParams.has('eoicount') == false && searchParams.has('eoitext') == false) {
	$(".leftMainCol").remove();
	$(".rightMainCol").removeClass("col-md-7");
	$(".rightMainCol").addClass("col-md-12");
}

if (searchParams.has('footer') == false) {
	$('.footerTicker').remove();
}

$('#projectname').html(projectname);

getAssignedList();

function getAssignedList() {
	var urlPPtoken = "gettokenassignentrys?tokenType="+type+"&projectid="+project+"&date="+fromdate+"&todate="+todate;
	var contactName = "";
	var html = '';
	
	var i = 0;
	$.getJSON(urlPPtoken, function (data) {
		$("#tokenAssignedTable tbody").empty();
		$.each(data, function (index, value) { 
			if(value.contact_name==undefined) {
				contactName="Sync in progress, refresh in sometime";
			} else {
				contactName =value.contact_name;
			}
			
			html += 	'<tr>'
							+ '<td class="tokenCol">'+value.type+value.queue+'</td>' 
							+ '<td>'+contactName+'</td>' 
							+ '<td class="assignedLine">'+value.closing_manager_name__c+'</td>' 
						"</tr>";
			i++;
			if (i == 8){
				return false
			}
		});		
		 
		var rem = 8-i;
		
		for (var j = 0; j < rem; j++) {
			html += 	'<tr>'
							+ '<td class="tokenCol">&nbsp;</td>' 
							+ '<td>&nbsp;</td>' 
							+ '<td>&nbsp;</td>' 
						"</tr>";
		}
		
		$("#tokenAssignedTable tbody").append(html);
	}).done(function() {
		 
	});
}


function getPendingAssignList(){	
	var urlPP = "gettokenEntrys?tokenType="+type+"&ProjectId="+project+"&date="+fromdate+"&todate="+todate;
	var contactName = "";
	var html = '';
	
	var i = 0;
	$.getJSON(urlPP, function (data) {
		$("#tokenUpcomingTable tbody").empty();
		$.each(data, function (index, value) {
			if(value.contact_name==undefined) {
				contactName="Sync in progress, refresh in sometime";
			} else {
				contactName =value.contact_name;
			}
			
			html += 	'<tr>'
							+ '<td class="tokenCol">'+value.type+value.queue+'</td>' 
							+ '<td>'+contactName+'</td>' 
							+ '<td class="upcomingLine">'+value.closing_manager_name__c+'</td>' 
						"</tr>";
			i++;
			if (i == 8){
				return false
			}
		});
		
		var rem = 8-i;
		
		for (var j = 0; j < rem; j++) {
			html += 	'<tr>'
							+ '<td class="tokenCol">&nbsp;</td>' 
							+ '<td>&nbsp;</td>' 
							+ '<td>&nbsp;</td>' 
						"</tr>";
		}
		
		$("#tokenUpcomingTable tbody").append(html);
	}).done(function() {});
}