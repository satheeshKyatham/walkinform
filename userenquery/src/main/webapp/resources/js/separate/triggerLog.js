$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});
var today = new Date();
	document.getElementById("tlAFromDate").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
	//document.getElementById("tlAToDate").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
	document.getElementById("tlFromDate").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
	//document.getElementById("tlToDate").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
	



function getTriggerLog () {

	
	
	//$('#applicationsListCol i').show();
	
	 $("#triggerLogTable tbody").empty();
	$.get("getTriggerlogList",{"fromDate":$('#tlFromDate').val(),"toDate":"2019-09-09"},function(data){				 
		
	}).done(function(data){
		var obj =JSON.stringify(data);
		var data =JSON.parse(obj);
		
		var html = '';
		
		if(data!=null) {
			for(var i=0;i<data.length;i++){
				
				html += "<tr>" +
							"<td>"+data[i].id+"</td>" +
							"<td>"+data[i].txid+"</td>" +
							"<td>"+data[i].created_at+"</td>" +
							"<td>"+data[i].updated_at+"</td>" +
							"<td>"+data[i].processed_at+"</td>" +
							"<td>"+data[i].processed_tx+"</td>" +
							"<td>"+data[i].state+"</td>" +
							"<td>"+data[i].action+"</td> " +
							"<td>"+data[i].table_name+"</td> " +
							"<td>"+data[i].record_id+"</td> " +
							"<td>"+data[i].sfid+"</td> " +
							"<td>"+data[i].old+"</td> " +
							"<td>"+data[i].values+"</td> " +
							"<td>"+data[i].sf_result+"</td> " +
							"<td>"+data[i].sf_message+"</td> " +
						"</tr>";
			}
			
			html = html.replace(/null/g, "");
			
			$("#triggerLogTable tbody").append(html);
		} else {
			alert ("Data not found");
		}
		
		$('#triggerLogTable thead.tlFilter th').each( function () {
	        var title = $('#triggerLogTable thead th').eq( $(this).index() ).text();
	        $(this).html( '<input type="text" placeholder="Search '+title+'" />' );
	    } );
		
		var table = $('#triggerLogTable').DataTable();
		triggerLogDataCall (table);

	});	
}



function getTriggerLogArchive () {

	//$('#applicationsListCol i').show();
	
	 $("#triggerLogTableArchive tbody").empty();
	$.get("getTriggerlogArchiveList",{"fromDate":$('#tlAFromDate').val(),"toDate":"2019-09-09"},function(data){				 
		
	}).done(function(data){
		var obj =JSON.stringify(data);
		var data =JSON.parse(obj);
		
		var html = '';
		
		if(data!=null) {
			for(var i=0;i<data.length;i++){
				
				html += "<tr>" +
							"<td>"+data[i].id+"</td>" +
							"<td>"+data[i].txid+"</td>" +
							"<td>"+data[i].created_at+"</td>" +
							"<td>"+data[i].updated_at+"</td>" +
							"<td>"+data[i].processed_at+"</td>" +
							"<td>"+data[i].processed_tx+"</td>" +
							"<td>"+data[i].state+"</td>" +
							"<td>"+data[i].action+"</td> " +
							"<td>"+data[i].table_name+"</td> " +
							"<td>"+data[i].record_id+"</td> " +
							"<td>"+data[i].sfid+"</td> " +
							"<td>"+data[i].old+"</td> " +
							"<td>"+data[i].values+"</td> " +
							"<td>"+data[i].sf_result+"</td> " +
							"<td>"+data[i].sf_message+"</td> " +
						"</tr>";
			}
			
			html = html.replace(/null/g, "");
			
			$("#triggerLogTableArchive tbody").append(html);
		} else {
			alert ("Data not found");
		}
		
		//$('#triggerLogTableArchive').DataTable({
			//"ordering": false
		//});
		
		$('#triggerLogTableArchive thead.tlAFilter th').each( function () {
	        var title = $('#triggerLogTableArchive thead th').eq( $(this).index() ).text();
	        $(this).html( '<input type="text" placeholder="Search '+title+'" />' );
	    } );
		
		var table = $('#triggerLogTableArchive').DataTable();
		triggerLogArchiveDataCall (table);
		
		
		//$('#applicationsListCol i').hide();
	});	
}



function triggerLogArchiveDataCall (table) {
	
	$('.tlAFilter').show();
	
	$("#triggerLogTableArchive thead.tlAFilter input").on('keyup change', function () {
		table
	        .column( $(this).parent().index()+':visible' )
	        .search( this.value )
	        .draw();
	} );
} 



function triggerLogDataCall (table) {
	
	$('.tlFilter').show();
	
	$("#triggerLogTable thead.tlFilter input").on('keyup change', function () {
		table
	        .column( $(this).parent().index()+':visible' )
	        .search( this.value )
	        .draw();
	} );
} 
