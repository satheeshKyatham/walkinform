
var statusLogin="N";
$(document).ready(function() {
	var id=getProjectId();
	var phone=encryptStr();
	
});
function getProjectId(){
	debugger
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
	var encStr=encryptStr();
	var project_sfid=getProjectId();
	var enqsfid=getenqsfId();
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
						debugger
						if (data=="Invalid input or No Entry Found") {
							$("#invalidEntry").text("Invalid number or No Entry Found");
							$("#email").val('');
							$("#birthdate").val('');
							$("#lastname").val('');
							$("#loginColLoad").hide();
								
						} else {
							window.location.assign("ccAvenue?num="+ encStr+ "&projectid="+ project_sfid+"&enqsfid="+enqsfid);
						}
							
					});
}
function getCCAvenuePaymentDetails()
					{
						
					
					
						$("#dtOrderExample").dataTable().fnDestroy();
						$("#dtOrderExample tbody").empty();
						 $("#mainPageLoad").show();
						 var projectId=getProjectId();
						 var enqId=getenqsfId();
						var urlPP = "getPaymentReqRecord?projectid="+projectId+"&enqSfid="+enqId;
						var i = 0
						var paymentButton="";
						var transactionId="";
						var transactionStatus="";
						//alert("Alert");
						 var url_string = window.location.href; //window.location.href
						 var url = new URL(url_string);
						 var respid = url.searchParams.get("respid");
						 var amt = url.searchParams.get("amt");
						 
						 var msgShow = "";
						
						
						$.getJSON(urlPP, function (data) {
							
							/*<thead>
							<tr>
								<th id="enquiry_Id">Enquiry</th>
								<th>Project Name</th>
								<th>Amount</th>	
								<th>Transaction Id</th>	
								<th>Transaction Status</th>									
								<th>Action</th>
							</tr>
						</thead>*/
							if(data[0].requestsource=="CP APP")
							{
								var headData="<th>Typology</th><th>Floor Band</th><th>Broker Name</th><th>Transaction Id</th><th>Transaction Status</th><th>Action</th>"
								$("#dtOrderExample thead tr").append(headData);
							}
							else
								$("#dtOrderExample thead tr").append("<th>Transaction Id</th><th>Transaction Status</th><th>Action</th>");
							if(respid=="1")//Success
							{
								msgShow="Dear "+data[0].customer_name+", payment has been initiated for "+amt+""
							}
							else if(respid=="2")//Failed
								{
									msgShow="There was error in processing your payment at this time"
								}
							else if(respid=="3")//Cancelled
								{
									msgShow="Dear "+data[0].customer_name+", payment has been cancelled"
								}
							else if(respid=="4")//Cancelled
							{
								msgShow="Oops! somthing went wrong, please check with IT team"
							}
							$('#payment_resp').text(msgShow);
							$.each(data, function (index, value) {
								console.log("test",value)
								if(value.ispayment_status=='N'){
									paymentButton="<button class='btn btn-primary btnNext' onclick='requestCCPaymentGateway("+value.id+");'>Pay Now</button>";
								}else{
									paymentButton="";
								}
								if(value.bank_ref_no!=undefined && value.bank_ref_no!="" && value.bank_ref_no!=null){
									var transactionId=value.bank_ref_no.trim();
									if(transactionId=="null" ){
										transactionId="";
									}	
								}else{
									transactionId="";
								}
								if(value.payment_status!=undefined && value.payment_status!="" && value.payment_status!=null){
								transactionStatus=value.payment_status.trim();
								if(transactionStatus=="null"){
									transactionStatus="";
								}
								}else{
									transactionStatus="";
								}
								/*var transactionStatus=value==null?'':value.payment_status==null?'':value.payment_status;*/
								$('#enquiry_Id').val(value.id);
								
								var brokerData="";
								if(data[0].requestsource=="CP APP")
									{
										brokerData="<td>"+value.cp_typology+"</td><td>"+value.cp_floor_band+"</td><td>"+value.cp_broker_name+"</td><td>"+transactionId+"</td><td>"+transactionStatus+"</td>";
									}
								else
									brokerData="<td>"+transactionId+"</td><td>"+transactionStatus+"</td>";
								var val = "<tr><td><label>"+value.enquiry_name+"</label></td><td>"+value.project_name+"</td><td>"+value.amount+"</td>"+brokerData+"<td>"+paymentButton+"</td>";
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
	formData.append("id",e)
	$.ajax({
		url : "requestToCCgateway",
		type : 'POST',
		data : formData,
		processData : false,
		contentType : false,
		success : function(data) {
//			 window.location.href = "ccavRequestHandler?ccencrqst="+data;
			//alert(data);
			//alert("ccavRequestHandler?"+data);
			window.location.href = "ccavRequestHandler?"+data;
			
		}
	});
}
