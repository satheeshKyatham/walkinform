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
function insertAuditLogCostsheet(getDetailSource)
{
	var csSource = "";
	var tokenid = "";
	var useremail = "";
	var userid = -1;
	
	if (getDetailSource == "SALES") {
		csSource = "SALES MANAGER";
		if ($("#tokenId").val() != undefined &&  $("#tokenId").val() != "undefined" && $("#tokenId").val() != "" && $("#tokenId").val() != null && $("#tokenId").val() != "null"){
			tokenid = $("#tokenId").val();
		} else {
			tokenid = -1;
		}
		useremail = $("#useremailID").val();
	} else if (getDetailSource == "ADMIN") {
		csSource = "INVENTORY MANAGER";
		tokenid = -1;
		useremail = USEREMAIL_GV;
	}
	
	if ($("#userid").val() != undefined &&  $("#userid").val() != "undefined" && $("#userid").val() != "" && $("#userid").val() != null && $("#userid").val() != "null"){
		userid = $("#userid").val();
	} else {
		userid = -1;
	}
	
	var formData = new FormData();
	formData.append('category', 1);
	formData.append('user_email', useremail);
	formData.append('enquiry_no', "");
	formData.append('enquiry_sfid', $('#enquirysfid').val());//enquiryId
	formData.append('contact_sfid', $("#primarycontactsfid").val());
	formData.append('contact_name', "");
	formData.append('project_sfid', $("#projectId").val());
	formData.append('project_name', $("#projectname").val());//projectname
	formData.append('paymentplan_sfid', $('#ppDropdown').val());
	formData.append('paymentplan_name', "");
	formData.append('tower_sfid', $('#towerSfid').val());//$('#towerTval').text()
	formData.append('tower_name', "");
	formData.append('inventory_sfid', $('#unitSfid').val());//$('#unitTval').text()
	formData.append('inventory_name', "");
	formData.append('scheme_name', "");
	formData.append('scheme_rate', 0);
	formData.append('token_id', parseInt(tokenid));//
	formData.append('token_no', $("#token").val());//token
	//formData.append('created_date', "2019-10-11");
	formData.append('user_machine_details', "123");
	formData.append('user_id', parseInt(userid));//
	
	
	formData.append('source', csSource);
	//formData.append('carpark_type', $('#carParkType').find('option:selected').attr("data-name"));
	//formData.append('carpark_count', $("#carParkCountDD select").val());
	//formData.append('scheme_type', $("#schemeTypeDD").val());
	 
	
	
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