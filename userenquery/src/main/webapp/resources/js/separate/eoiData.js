var pageContext = $("#contextPath").val()+"/";

function getEOIList () {
	
	$('#eoiPriorityTab i').show();
	
	$("#eoiPriorityTable tbody").empty();
	
	$.get("getEOIList",{"projectSfid":$('#projectid').val()},function(data){				 
		
	}).done(function(data){
		
		
		var jsonString =JSON.stringify(data);
		var obj =JSON.parse(jsonString);
		
		var html = '';
		
		if(obj!=null) {
			for(var i=0;i<obj.length;i++){
				
				html += "<tr class='eoiDataRow'><td class='enqPrimaryId'>"+obj[i].id+"</td><td class='eoiEnqSfid'>"+obj[i].sfid+"</td><td><input class='eoiPriority form-control input-sm' placeholder='Priority' /></td></tr>";
			}
			
			html = html.replace(/null/g, "");
			
			$("#eoiPriorityTable tbody").append(html);
		} else {
			alert ("Data not found");
		}
		
		$('#eoiPriorityTable').DataTable({
			"ordering": false
		});
		
		$('#eoiPriorityTab i').hide();
	
	});
}



function updateEOIPriority () {
	
	var arrayData = [];
	$("#eoiPriorityTable .eoiDataRow").each(function () {
	    var eoiData = {};
	    //eoiData.id = $(this).find('.enqPrimaryId').text();
	    eoiData.enquiryId = $(this).find('.enqPrimaryId').text();
	    eoiData.priority_no__c = $(this).find('.eoiPriority').val();
	    
	    arrayData.push(eoiData);
	});
	
	console.log ("eoiData ::: " + JSON.stringify(arrayData));

	$.post(pageContext+"updateEOIPriority",{"eoiDtl" : JSON.stringify(arrayData)},function(data){				 
	}).done(function(){
		
    });
}