$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});
var statusLogin="N";
$(document).ready(function() {
	var id=getProjectId();
	var phone=encryptStr();
	
	getCustomerDetails(phone,id);
});
function getProjectId(){
	var urlString = window.location.href;
	var url = urlString.split("=");/*'3333888379'*/;
	var id=url[2];
	var projectId = id.split("&");
	var proId=projectId[0];
	return proId;
}
function getenqsfId(){
	var urlString = window.location.href;
	var url = urlString.split("=");/*'3333888379'*/;
	var id=url[3];
	var enqId = id.split("&");
	var enqsfidId=enqId[0];
	return enqsfidId;
}
function getCustomerDetails(phone,id){
	var encStr=encryptStr();
	var enqsfid=getenqsfId();
	$.get(
			"getccAvenueList",
			{
				"dncstr" : encStr,
				"projectid" : id,
				"enqsfid":enqsfid
			},function (data){
				console.log(data)
				var myJSON = JSON.stringify(data);
				var data1 = JSON.parse(myJSON);
				if (data != null && data[0].phone_number != undefined) {
					var issubmitted = 'N';
					var j = 1;
					console.log("index" ,data)
					for (var i = 0; i < data.length; i++) {
						
						var kycapproval_status = data[i].kycapproval_status;
						if (data[i].phone_number == undefined) {
							$("#invalidEntry").text(
									"Invalid number or No Entry Found");
						} else {
							$("#invalidEntry").text("");
							if (i == 0) {
								$("#mainTopNav").append("<li class='active'><a href='#eoi"+ j+ "' data-toggle='tab' id='eoitab"	+ j	+ "'>"+ data[i].enquiryid+ "</a></li>")
							} else {
								$("#mainTopNav")
										.append("<li><a href='#eoi"+ j	+ "' data-toggle='tab' id='eoitab"+ j+ "'>"	+ data[i].enquiryid	+ "</a></li>")
							}
						}
						$("#enqsfid").val(data[i].enquirysfid);
						$("#enqid").val(data[i].enquiryid);
						issubmitted = data[i].issubmitted;
						j++;
					}
					getCCAvenuePaymentDetails();
				}
			}
			)
}
function encryptStr() {
	
	var prmstr = window.location.search.substr(1);
	// alert(prmstr);
	var params = {};
	var prmarr = prmstr.split("&");
	for (var i = 0; i < prmarr.length; i++) {
		var tmparr = prmarr[i].split("=");

		params[tmparr[0]] = tmparr[1];
	}
	return params.num;
}
function validatePhNo() {
	var inpuNum = $('#inputNumber').val();
	_search_data(inpuNum, encryptStr);
}
function doit_onKeyPress(event) {
	if (event.keyCode == 13 || event.which == 13) {
		validatePhNo();
	}
}
function _search_data(inpuNum, project_sfid) {
	// alert(encStr);
	debugger
	$("#loginColLoad").show();
	var formData = new FormData();
	var encStr=encryptStr();/*'3333888379'*/;
	var project_sfid=getProjectId();
	var enqsfid=getenqsfId();
	/*formData.append('dncstr', encStr);
	formData.append('user_entr_no', inpuNum);*/
			$.get(
					"validateStr",
					{
						"dncstr" : encStr,
						"user_entr_no":inpuNum,
						"projectid" : project_sfid,
						"enqsfid":enqsfid
					},
					function(data) {
						debugger;
						console.log("hi",data)
						var myJSON = JSON.stringify(data);
						var data1 = JSON.parse(myJSON);
						var en="";
						if (data != null && data[0].phone_number != undefined) {
							var issubmitted = 'N';
							var j = 1;
							console.log("index" ,data)
							for (var i = 0; i < data.length; i++) {
								var kycapproval_status = data[i].kycapproval_status;
								if (data[i].phone_number == undefined) {
									$("#invalidEntry").text(
											"Invalid number or No Entry Found");
								} else {
									$("#invalidEntry").text("");
									if (i == 0) {
										$("#mainTopNav").append("<li class='active'><a href='#eoi"+ j+ "' data-toggle='tab' id='eoitab"	+ j	+ "'>"+ data[i].enquiryid+ "</a></li>")
									} else {
										$("#mainTopNav")
												.append("<li><a href='#eoi"+ j	+ "' data-toggle='tab' id='eoitab"+ j+ "'>"	+ data[i].enquiryid	+ "</a></li>")
									}
								}								
								$("#enqsfid").val(data[i].enquirysfid);
								$("#enqId").val(data[i].enquiryid);
								issubmitted = data[i].issubmitted;
								en=data[i].enquirysfid;
								j++;
							}
							var iseoi = $('#iseoi').val();
							debugger
							if (data != null) {
								window.location.assign("ccAvenue?num="+ encStr+ "&projectid="+ project_sfid+"&enqsfid="+enqsfid);
						} else {
							$("#invalidEntry").text("Invalid number or No Entry Found");
							$("#email").val('');
							$("#birthdate").val('');
							$("#lastname").val('');
							$("#loginColLoad").hide();
						}
							}
					});
}
function getCCAvenuePaymentDetails()
					{
						
					debugger;
					
						$("#dtOrderExample").dataTable().fnDestroy();
						$("#dtOrderExample tbody").empty();
						 $("#mainPageLoad").show();
						 var projectId=getProjectId();
						 var enqId=$('#enqsfid').val();
						var urlPP = "getPaymentReqRecord?projectid="+projectId+"&enqSfid="+enqId;
						var i = 0
						var paymentButton="";
						var transactionId="";
						var transactionStatus="";
						//alert("Alert");
						$.getJSON(urlPP, function (data) {
							$.each(data, function (index, value) {
								debugger;
								console.log("test",value)
								if(value.ispayment_status=='Y'){
									paymentButton="<button class='btn btn-primary btnNext' onclick='requestCCPaymentGateway(this);'>Pay Now</button>";
								}else{
									paymentButton="";
								}
								$('#enquiry_Id').val(value.id);
								var val = "<tr><td><label>"+value.enquiry_name+"</label></td><td>"+value.project_name+"</td><td>"+value.amount+"</td><td>"+transactionId+"</td><td>"+transactionStatus+"</td><td>"+paymentButton+"</td>";
								val=val+"</tr>";
								$("#dtOrderExample tbody").append(val);
								$('#projectTitle').text(value.project_name);
								i = i+1
							});
							$('#dtOrderExample').DataTable({
								destroy: true,
								language: {
									searchPlaceholder: "Search"
								},
								order: [[ 0, "desc" ]]
							});
							
						}).done(function() {
							$("#amsearch").prop("disabled", false);
							
							$('#amsearch span').html('');
							
							$("#mainPageLoad").hide();
							/*$('#dtOrderExample').DataTable();*/
							if (i == 0){
							}	
							 
						}).error(function() { //alert("error");
						 $("#amsearch").prop("disabled", false);
						 $('#amsearch span').html('');
						});
						
					}

function requestCCPaymentGateway(e){
	debugger;
	var primaryId=$("#enquiry_Id").val();
	var formData = new FormData();
	formData.append("id",primaryId)
	$.ajax({
		url : "requestToCCgateway",
		type : 'POST',
		data : formData,
		processData : false,
		contentType : false,
		success : function(data) {
		}
	});
}