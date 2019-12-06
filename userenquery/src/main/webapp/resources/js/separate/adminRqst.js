$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});
var pageContext = $("#pageContext").val();

getRqstForAdmin ();

function getRqstForAdmin (){
	//alert ("Test 123456789");
	$.post(pageContext+"/adminRqst",{"userId":"A202"},function(data){				 
		
	}).done(function(data){
		$("#rqstFromAdmin tbody").empty();
		
		var obj =JSON.parse(data);
		var html = '';
		for (var i = 0; obj.length > i; i++) {
			//html += "<tr> <td>"+obj[i].timeid+"</td> <td>"+obj[i].customer_name+"</td> <td>"+obj[i].user_contact+"</td> <td>"+obj[i].createdby+"</td> <td>"+obj[i].created+"</td> <td><a href='http://localhost:8082/UserEnqury/MyPdf?name="+obj[i].timeid+"'>View</a></td> <td><button class='btn btn-sm btn-danger' onclick='adminReject("+obj[i].timeid+")'>Reject</button> <button class='btn btn-sm btn-success' onclick='adminApprove("+obj[i].timeid+")'>Approve</button></td> </tr>";
			html += "<tr> <td>"+obj[i].timeid+"</td> <td>"+obj[i].customer_name+"</td> <td>"+obj[i].user_contact+"</td> <td>"+obj[i].createdby+"</td> <td>"+obj[i].created+"</td> <td><a href='http://kyc.gplapps.com:8085/zcs/MyPdf?name="+obj[i].timeid+"'>View</a></td> <td><button class='btn btn-sm btn-danger' onclick='adminReject("+obj[i].timeid+")'>Reject</button> <button class='btn btn-sm btn-success' onclick='adminApprove("+obj[i].timeid+")'>Approve</button></td> </tr>";
		};
		$("#rqstFromAdmin tbody").append (html);		
    });	
}






function adminReject (timeId) {
	//alert ("timeId ::: " + timeId);
	$.post(pageContext+"/adminAction",{"timeid":timeId, "actionAR":"R"},function(data){				 
		
	}).done(function(data){
		swal({
			title: "Request Rejected",
		    text: "",
		    timer: 2000,
		    type: "error",
		});
		getRqstForAdmin ();
    });	
}

function adminApprove (timeId) {
	//alert ("timeId ::: " + timeId);
	$.post(pageContext+"/adminAction",{"timeid":timeId, "actionAR":"A"},function(data){				 
		
	}).done(function(data){
		swal({
			title: "Request Approved",
		    text: "",
		    timer: 2000,
		    type: "success",
		});
		getRqstForAdmin ();	
    });
}