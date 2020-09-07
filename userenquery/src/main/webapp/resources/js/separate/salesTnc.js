$.ajaxSetup({
	statusCode : {
		401 : function() {
			location.reload();
		}
	}
});
$(document).ready(function() {
	$("#tnc_text").Editor();
});
var pageContext = $("#pageContext").val() + "/";
projectDataList();
function addSalesTnc() {
	debugger
	
	if ($('#projectDataListTnc').val() != '') {
		$("#addSalesTncId").attr("disabled","disabled");
		$.post(pageContext+"insertTnCForPP",{"tnc_text" : $('.Editor-editor').html(),"project_id" : $('#projectDataListTnc').val(),"project_name" : $('#projectDataListTnc :selected').text(),"pymt_plan_id" : $('#paymentPlanDropdownTnc').val(),"pymt_plan_name" : $('#paymentPlanDropdownTnc :selected').text(),"region_id" :$('#region_id').val(),"region_name" : $('#region_id').val(),"tower_sfid" : $('#towerMstTnc').find('option:selected').attr('name'),"tower_name" : $('#towerMstTnc :selected').text()}, function(data) {
				}).done(function(data) {
			$("#addSalesTncId").removeAttr("disabled","disabled");
			if (data.insertStatus != "NOT_OK") {
				projectDataList();
				$('#tncTableId tbody tr.tncDataPlotRow').remove();
				$(".Editor-editor").html("");
				$('#towerMstTnc').empty();
				$('#towerMstSearch').empty();
				$('#paymentPlanDropdownTnc').find("option:gt(0)").remove();
				$('#paymentPlanDropdownSearch').find("option:gt(0)").remove();
				swal({
					title : data.insertStatus,
					text : "",
					timer : 2000,
					type : "success",
				});
				
			} else {
				swal({
					title : "Already Exist",
					text : "",
					timer : 100000,
					type : "error",
				});
			}

		});
	} else {
		swal({
			title : "Please select the mandatory fields",
			text : "",
			timer : 2000,
			type : "error",
		});
	}
}


function projectDataList() {
	debugger
	$('#projectDataListTnc').find("option:gt(0)").remove();
	$('#projectDataListSearch').find("option:gt(0)").remove();
	$('#paymentPlanDropdownTnc').find("option:gt(0)").remove();
	$('#towerMstTnc').empty();
	var urlTower = pageContext + "projectListForSales?userId="+ $("#logedInuserid").val();
	$.getJSON(
			urlTower,
			function(data) {
				console.log("test",data)
				$.each(data, function(index, value) {
					$("#region_id").val(value.region__c);
					$('#projectDataListTnc').append("<option value='" + value.project_id + "'>" + value.project_name+"</option>");
					$('#projectDataListSearch').append("<option value='" + value.project_id + "'>" + value.project_name+ "</option>");
				});
			}).done(function() {

	});
}

function paymentPlanDropdownForTnc() {
	$('#paymentPlanDropdownTnc').find("option:gt(0)").remove();
	var urlpayemntPlan = pageContext + "getpaymentPlanListData?projectcode="+ $("#projectDataListTnc").val();
	$.getJSON(
			urlpayemntPlan,
			function(data) {
				$.each(data, function(index, value) {
					$('#paymentPlanDropdownTnc').append('<option value=' + value.sfid + '>' + value.name+ '</option>');
				});
			}).done(function() {
		towerList();
	});
}



function towerList() {
	$('#towerMstTnc').empty();
	var projectNameVal = $("#projectDataListTnc").val();
	var urlTower = pageContext + "getTowerMaster?project_code="
			+ projectNameVal;
	$.getJSON(
			urlTower,
			function(data) {
				$('#towerMstTnc').append('<option name="" value="">Select</option>');
				$.each(data, function(index, value) {
					$('#towerMstTnc').append(
							"<option name='" + value.sfid + "' value='"
									+ value.tower_code__c + "'>"
									+ value.tower_name__c + "</option>");
				});
			}).done(function() {

	});
}

function paymentAndTowerDropdownForTncSearch(){
		$('#paymentPlanDropdownSearch').find("option:gt(0)").remove();
		var urlpayemntPlan = pageContext + "getpaymentPlanListData?projectcode="+ $("#projectDataListSearch").val();
		$.getJSON(
				urlpayemntPlan,
				function(data) {
					$.each(data, function(index, value) {
						$('#paymentPlanDropdownSearch').append('<option value=' + value.sfid + '>' + value.name+ '</option>');
					});
				}).done(function() {
			towerListSearch();
		});
}

function towerListSearch(){
	$('#towerMstSearch').empty();
	var projectNameVal = $("#projectDataListSearch").val();
	$("#amsearchId").attr("disabled","disabled");
	var urlTower = pageContext + "getTowerMaster?project_code="+ projectNameVal;
	$.getJSON(
			urlTower,
			function(data) {
				$("#amsearchId").removeAttr("disabled","disabled");
				$('#towerMstSearch').append('<option name="" value="">Select</option>');
				$.each(data, function(index, value) {
					$('#towerMstSearch').append("<option name='" + value.tower_code__c + "' value='"+ value.sfid + "'>"+ value.tower_name__c + "</option>");
				});
			}).done(function() {
	});
}

function tncSearch() {
	debugger;
	$('#tncTableId tbody tr.tncDataPlotRow').remove();
	var proId = $("#projectDataListSearch").val();
	var towerId = $("#towerMstSearch").val();
	var pymtPlanId = $("#paymentPlanDropdownSearch").val();
	$.post(pageContext + "getSearchTncData",
					{
						"proId" : proId,
						"towerId" : towerId,
						"pymtPlanId" : pymtPlanId
					},
					function(obj) {
						debugger
						console.log("tncdata", obj)
						var html = '';
						var trxSuccess = "";
						var htmlActionBtn = '';
						/*var obj = JSON.parse(data);*/
						if (obj != null) {
							for (var i = 0; i < obj.length; i++) {
								console.log("searchre", obj[i])
								var projectname = obj[i].project_name == undefined ? '': obj[i].project_name;
								var towername = obj[i].tower_name == undefined ? '': obj[i].tower_name;
								var pymtplanname = obj[i].pymt_plan_name == undefined ? '': obj[i].pymt_plan_name;
								var tnc = obj[i].tnc_text == undefined ? '': obj[i].tnc_text;
								if(towername=="Select"){
									towername="";
								}
								if(pymtplanname=="Select"){
									pymtplanname="";
								}
								
								if (obj[i].isactive == "A") {
									htmlActionBtn = "";
									htmlActionBtn += '<div style="text-align:center;">'
											+ '<button class="btn btn-danger btn-sm rounded-0"  onclick="deleteSearchTnc(this)" '
											+ 'type="button" data-toggle="tooltip" data-placement="top" title="Delete"><i class="fa fa-trash"></i></button></div>';
								} else {
									htmlActionBtn = "";
								}
								html += '<tr class="tncDataPlotRow '
										+ trxSuccess + '" data-rowid = "'
										+ obj[i].id + '">'
										+ '<td style="text-align:center;"> '
										+ '<span class="oldProjectList">'
										+ projectname + '</span>' + '</td>'
										+ '<td style="text-align:center;">'
										+ '<span class="oldTowerId">'
										+ towername + '</span>' + '</td>'
										+ '<td style="text-align:center;">'
										+ '<span class="oldPaytPlanId">'
										+ pymtplanname + '</span>' + '</td>'
										+ '<td style="text-align:center;">'
										+ '<span class="oldTnC">' + tnc
										+ '</span>' + '</td>'
										+ '<td class="crudRPBtn"> '
										+ htmlActionBtn + '</td>'
								"</tr>";
							}
							html = html.replace(/undefined/g, "");
							$("#tncTableId tbody tr:first-child").after(html);
						}
						$('#tncTableId').DataTable({
							destroy : true,
							language : {
								searchPlaceholder : "Search by name"
							}
						});
					});
}

function deleteSearchTnc(e) {
	debugger;
	var pageContext = $("#pageContext").val() + "/";
	var tncId = $(e).closest("td").closest("tr.tncDataPlotRow").attr("data-rowid");
	$.post(pageContext + "deleteTnc", {
		"Id" : tncId
	}, function(data) {
		debugger
	}).done(function(data) {
		var obj = JSON.parse(data);
		if (obj != null) {
			if (obj.status == "STATUS_OK") {
				swal({
					title : obj.error_msg,
					text : "",
					timer : 3000,
					type : "success",
				});
				tncSearch();
			} else if (obj.status == "STATUS_NOTOK") {
				swal({
					title : obj.error_msg,
					text : "",
					timer : 3000,
					type : "warning",
				});
			}
		}
	});
}
