/**
 * Added By Satheesh Kyatham - 16-10-2020
 * Own Sourcing Lead Report Showing on CM login 
 */

$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});

$(document).ready(function() {
	var today = new Date();
	document.getElementById("txtSourcingLeadFromDate").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
	document.getElementById("txtSourcingLeadToDate").value = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
});

/* -------- Sourcing Lead Report ---------- */
function getMySourcingLead () {
	
	$("#mySourcingLeadReportTableSales").DataTable().destroy();
	
	$('#mySourcingLeadReportTabSales i').show();
	
	$("#mySourcingLeadReportTableSales tbody").empty();
	$.get("getSourcingLeadReport",{"emailid":$('#loged_useremail').val(), "projectsfid":$('#projectid').val(), "fromdate":$('#txtSourcingLeadFromDate').val(), "todate":$('#txtSourcingLeadToDate').val()},function(data){				 
		var obj =JSON.stringify(data);
		var obj1 =JSON.parse(obj);
		var html = '';
		var paymentStatus = '';
		var cpName = '';
		var firstName = '';
		var lastName = '';
		var mobile = '';
		var email = '';
		
		if(obj1!=null) {
			for(var i=0;i<obj1.length;i++){
				
				if (obj1[i].channelPartner && obj1[i].channelPartner.name) {
					cpName = obj1[i].channelPartner.name;
				} else {
					cpName = '-';
				}
				
				if (obj1[i].contact != undefined && obj1[i].contact.firstName != undefined) {
					firstName = obj1[i].contact.firstName;
				} else {
					firstName = '';
				}
				
				if (obj1[i].contact != undefined && obj1[i].contact.lastName != undefined) {
					lastName = obj1[i].contact.lastName;
				} else {
					lastName = '';
				}
				
				if (obj1[i].contact != undefined && obj1[i].contact.mobile != undefined) {
					mobile = obj1[i].contact.mobile;
				} else {
					mobile = '';
				}
				
				if (obj1[i].contact != undefined && obj1[i].contact.email != undefined) {
					email = obj1[i].contact.email;
				} else {
					email = '';
				}
				
				html += "<tr>" +
							" <td>"+obj1[i].name+"</td>" +
							" <td>"+firstName+" "+lastName+"</td>" +
							" <td>"+mobile+"</td>" +
							" <td>"+email+"</td>" +
							" <td>"+obj1[i].isReferredByChannelPartner+"</td>" +
							" <td>"+obj1[i].walkInSource+"</td>" +
							" <td>"+obj1[i].closing_manager_name__c+"</td>" +
							" <td>"+obj1[i].dateOfSiteVisit+"</td>" +
							" <td>"+cpName+"</td>" +
						" </tr>";
			}
			
			html = html.replace(/null/g, " - ");
			
			$("#mySourcingLeadReportTableSales tbody").append(html);
			
		} else {
			alert ("Data not found");
		}
	}).done(function(data){
		$('#mySourcingLeadReportTabSales i').hide();
		
		 $('#mySourcingLeadReportTableSales').DataTable( {
			 dom: 'Bfrtip',
			 "buttons": [
				 { "extend": 'excel', "text":'Export To Excel',"className": 'btn btn-default btn-xs' }
		      ],
		      "order": []
		 });
	});	
}