$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});

var parent = "";
var projectid = "";
var projectname = "";
var htmlGV = "";
var tokenType = "";

/*htmlGV += 	'<tr> <td class="tokenCol">&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>'
	+'<tr> <td class="tokenCol">&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td> </tr>'
	+'<tr> <td class="tokenCol">&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td> </tr>'
	+'<tr> <td class="tokenCol">&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td> </tr>'
	+'<tr> <td class="tokenCol">&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td> </tr>'
	+'<tr> <td class="tokenCol">&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td> </tr>'
	+'<tr> <td class="tokenCol">&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td> </tr>';

$("#tokenAssignedTable tbody").append(htmlGV);
$("#tokenUpcomingTable tbody").append(htmlGV);
*/
$(function () {
    
	var eoicount = "";
	var eoitext = "";
	var avUrl = "";
	var imageUrl = "";
	var footerUpdate = "";
	var footerUpdateLabel = "";
	
	
	if (window.opener != null && !window.opener.closed) {
        parent = $(window.opener.document).contents();
        
        eoicount = parent.find("#keyCont1").val().trim();
        eoitext = parent.find("#keyCont2").val().trim();
        avUrl = parent.find("#avUrl").val().trim();
        imageUrl = parent.find("#imageUrl").val().trim();
        footerUpdate = parent.find("#footerUpdate").val().trim();
        footerUpdateLabel = parent.find("#footerUpdateLabel").val().trim();
        projectid = parent.find("#projectid").val();
        projectname = parent.find("#projectname").val();
        tokenType = parent.find("#tokenType").val();
        
        // EOI Box
        if (eoicount.length > 0 || eoitext.length > 0){
        	$('#eoiCount').html(eoicount);
        	$('#eoiText').html(eoitext);
        } else {
        	$('.eoiMainCol').remove();
        }
        // END EOI Box
        
        // Hide left panel based on condition
        if (imageUrl.length == 0 && avUrl.length == 0 && eoicount.length == 0  && eoitext.length == 0) {
        	$(".leftMainCol").remove();
        	$(".rightMainCol").removeClass("col-md-7");
        	$(".rightMainCol").addClass("col-md-12");
        }
        // END Hide left panel based on condition
        
        // Media
        if (avUrl.length > 0){
        	var video = $('<video />', {
                id: 'video',
                src: avUrl,
                type: 'video/mp4',
                controls: true,
                loop: true,
                height: '100%'
            });
            video.appendTo($('.videoImageCol'));
        } else if (imageUrl.length > 0) {
        	$('.videoImageCol').empty();
        	$('.videoImageCol').html("<img width='100%' style='padding: 1vh;' src='"+imageUrl+"'>");
        	
        } else {
        	$('.mediaCol').remove();
        }
        //END Media
        
        // Footer
        if (footerUpdate.length > 0) {
        	$("#footerMarquee").html(footerUpdate);
        	$("#footerMarqueelabel").html(footerUpdateLabel);
        } else {
        	$('.footerTicker').remove();
        }
        // END Footer
        
        $('#projectname').html(projectname);
    }
	
	getAssignedList();
});

var pageContext = $("#pageContext").val()+'/'; 
$("#myCarousel").on('slide.bs.carousel', function (){
	console.log($('.item.active').index());  
	
	if ($('.item.active').index() == 0) {
		//alert ("Upcoming");
		getPendingAssignList();
	} else if ($('.item.active').index() == 1) {
		//alert ("Assigned");
		getAssignedList ();
	}
});

function getAssignedList() {
	var urlPPtoken = "getAssignedTokenScreen?tokenType="+tokenType+"&projectid="+projectid;
	var contactName = "";
	var html = '';
	
	var i = 0;
	$.getJSON(urlPPtoken, function (data) {
		
		$.each(data, function (index, value) { 
			if(value.contact_name==undefined) {
				contactName="Sync in progress, refresh in sometime";
			} else {
				contactName =value.contact_name;
			}
			
			html += 	'<tr>'
							+ '<td class="tokenCol">'+value.type+value.queue+'</td>' 
							+ '<td>'+contactName+'</td>' 
							+ '<td class="assignedLine">'+value.user_name+'</td>' 
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
		$("#tokenAssignedTable tbody").empty();
		$("#tokenAssignedTable tbody").append(html);
	}).done(function() {
		 
	});
}


function getPendingAssignList(){	
	var urlPP = "getUpcomingTokenScreen?tokenType="+tokenType+"&ProjectId="+projectid;
	var contactName = "";
	var htmlNext = '';
	var htmlLater = '';
	
	var i = 0;
	
	var x = 0;
	var y = 0;
	
	$.getJSON(urlPP, function (data) {
		
		$.each(data, function (index, value) {
			if(value.contact_name==undefined) {
				contactName="Sync in progress, refresh in sometime";
			} else {
				contactName =value.contact_name;
			}
			// for next
			if (i < 8) {
				htmlNext += 	'<tr>'
					+ '<td class="tokenCol">'+value.type+value.queue+'</td>' 
					+ '<td class="upcomingLine" style="border-right: 1px solid;">'+contactName+'</td>' 
				"</tr>";
				
				x++;
			} 
			// END for next
			
			//for Later
			if (i  >= 8) {
				htmlLater += 	'<tr>'
					+ '<td class="tokenCol">'+value.type+value.queue+'</td>' 
					+ '<td class="upcomingLine" style="border-right: 1px solid;">'+contactName+'</td>' 
				"</tr>";
				
				y++;
			} 
			//END Later
			
			if (i == 15){
				return false
			}
			
			i++;
		});
		
		var next = 8-x;
		var later = 8-y;
		
		for (var j = 0; j < next; j++) {
			htmlNext += 	'<tr>'
							+ '<td class="tokenCol">&nbsp;</td>' 
							+ '<td style="border-right: 1px solid;">&nbsp;</td>' 
						"</tr>";
		}
		
		for (var k = 0; k < later; k++) {
			htmlLater += 	'<tr>'
							+ '<td class="tokenCol">&nbsp;</td>' 
							+ '<td style="border-right: 1px solid;">&nbsp;</td>' 
						"</tr>";
		}
		
		$("#tokenUpcomingNextTable tbody").empty();
		$("#tokenUpcomingLaterTable tbody").empty();
		$("#tokenUpcomingNextTable tbody").append(htmlNext);
		$("#tokenUpcomingLaterTable tbody").append(htmlLater);
		
	}).done(function() {});
}


//let searchParams = new URLSearchParams(window.location.search)

//let type = searchParams.get('type');
//let project = searchParams.get('project');
//let fromdate = searchParams.get('fromdate');
//let todate = searchParams.get('todate');
//let projectname = searchParams.get('projectname');

//var av = "";
//var image = "";


/*if (searchParams.has('eoicount') == true || searchParams.has('eoitext') == true){
	let eoicount = searchParams.get('eoicount');
	let eoitext = searchParams.get('eoitext');	
	
	$('#eoiCount').html(eoicount);
	$('#eoiText').html(eoitext);
} else {
	$('.eoiMainCol').remove();
}*/

//$('#eoiCount').html(eoicount);

/*if (searchParams.has('av')){
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
}*/

/*if (searchParams.has('image') == false && searchParams.has('av') == false && searchParams.has('eoicount') == false && searchParams.has('eoitext') == false) {
	$(".leftMainCol").remove();
	$(".rightMainCol").removeClass("col-md-7");
	$(".rightMainCol").addClass("col-md-12");
}*/