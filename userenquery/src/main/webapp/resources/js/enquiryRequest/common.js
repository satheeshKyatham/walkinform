/**
 * 
 */
//Common Method for AuditLog
/*let formData = new FormData();
formData.append('key1', 'value1');
formData.append('key2', 'value2');*/
$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});
function insertAuditLogCostsheet()
{
	debugger;
	var formData = new FormData();
	formData.append('category', 1);
	formData.append('user_email', $("#useremailID").val());
	formData.append('enquiry_no', "");
	formData.append('enquiry_sfid', "");//enquiryId
	formData.append('contact_sfid', "");
	formData.append('contact_name', "");
	formData.append('project_sfid', $("#projectId").val());
	formData.append('project_name', $("#projectname").val());//projectname
	formData.append('paymentplan_sfid', $('#ppDropdown').val());
	formData.append('paymentplan_name', "");
	formData.append('tower_sfid', "");//$('#towerTval').text()
	formData.append('tower_name', "");
	formData.append('inventory_sfid', "SDFSASSSS4422SSaa");//$('#unitTval').text()
	formData.append('inventory_name', "");
	formData.append('scheme_name', "");
	formData.append('scheme_rate', 0);
	formData.append('token_id', parseInt($("#tokenId").val()));//
	formData.append('token_no', $("#token").val());//token
	formData.append('created_date', "2019-10-11");
	formData.append('user_machine_details', "123");
	formData.append('user_id', parseInt($("#userid").val()));//
	
	
	$.ajax({
		url : pageContext+"insertAuditLog",
		type: "POST",
		data : JSON.stringify(Object.fromEntries(formData)),
		dataType : 'json',
		contentType: 'application/json'
	}).done(function(response){ //
		 //alert("Success"+response);
	});
	
}