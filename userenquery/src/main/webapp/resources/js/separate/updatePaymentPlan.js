$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});
var pageContext = $("#pageContext").val()+"/";	

function addTnCCharge () {
	if($('#regionList').val() != '' && $('#projectDataList').val() != '') {
		 
		$.post(pageContext+"insertTnCForPP",{"tnc_text":$('.Editor-editor').html(),  "project_id":$('#projectDataList').val(), "project_name":$('#projectDataList :selected').text(), "pymt_plan_id":$('#ppDropdown').val(), "pymt_plan_name":$('#ppDropdown :selected').text(), "region_id":$('#regionList').val(), "region_name":$('#regionList :selected').text(),"tower_sfid":$('#towerMst').find('option:selected').attr('name')   },function(data){				 
			
		}).done(function(data){
			swal({
				title: data.insertStatus,
			    text: "",
			    timer: 2000,
			    type: "success",
			});
		});
	}else {
		swal({
			title: "Please select the mandatory fields",
		    text: "",
		    timer: 2000,
		    type: "error",
		});
	}
}

regionList();

function regionList () {
	$('#regionList').find("option:gt(0)").remove();
	
	
	//$('#regionList').empty();	
	var urlRegionList = pageContext+"regionList?project_code=test";
	
	$.getJSON(urlRegionList, function (data) {
		//$('#regionList').append('<option value="">Select</option>');
		$.each(data, function (index, value) {
			$('#regionList').append("<option value='"+value.region__c+"'>"+value.region__c+"</option>");
		});					
	}).done(function() {
	});
}

function projectDataList (){
	$('#projectDataList').find("option:gt(0)").remove();
	//$('#projectDataList').empty();	
	var urlTower = pageContext+"projectDataList?region="+$('#regionList').val();
	$.getJSON(urlTower, function (data) {
		//$('#projectDataList').append('<option value="">Select</option>');
		$.each(data, function (index, value) {
			$('#projectDataList').append("<option value='"+value.sfid+"'>"+value.name+  " / " +value.propstrength__project_code__c+ "</option>");
		});					
	}).done(function() {
		//getPPAgainstProject ();
	});
}


/*-----------------------------------------------*/

function getPPAgainstProject () {
	
	$("#ppDtl").DataTable().destroy();
	$("#ppDtl tbody").empty();
	
	$.post(pageContext+"getPPList",{"projectSfid":$('#projectDataList').val()},function(data){                      
		
		var obj1 =JSON.parse(data);
		var html = '';
			
		var d4uHoldStatus = '';
		var status = '';
		
		var d4uStatus = "";
		var ppStatus = "";
		var ppSFID = "";
		
		if (obj1 != null) {
			for(var i=0;i<obj1.length;i++){
				
				if (obj1[i].d4u_active__c != null && obj1[i].d4u_active__c != undefined && obj1[i].d4u_active__c != "") {
					if (obj1[i].d4u_active__c == 't') {
						d4uStatus = "Yes";
					} else if (obj1[i].d4u_active__c == 'f') {
						d4uStatus = "No";
					} else {
						d4uStatus = "";
					}
				} else {
					d4uStatus = "";
				}
				
			 
				ppSFID = '"'+obj1[i].sfid+'"';
				
				
				html += "<tr>" +
					" <td> <input type='checkbox' name='ppSFID' value="+ppSFID+" /> </td> " +
					" <td>"+obj1[i].name+"</td> " +
					" <td>"+d4uStatus+"</td> " +
				" </tr>";
			}
			 
			html = html.replace(/undefined/g, " - ");
			html = html.replace(/null/g, " - ");
			
			$("#ppDtl tbody").append(html);
		} else {
			alert ("Data not found");
		}
         
	}).done(function(data){
		$('#ppDtl').DataTable({
			"bPaginate": false
			/*dom: 'Bfrtip',
			"buttons": [{ "extend": 'excel', "text":'Export To Excel',"className": 'btn btn-default btn-xs' }],
			"order": []*/
		});
	}).fail(function(xhr, status, error) {
    	 
    });
}


document.getElementById('select-all').onclick = function() {
  var checkboxes = document.querySelectorAll('input[type="checkbox"]');
  for (var checkbox of checkboxes) {
    checkbox.checked = this.checked;
  }
}






function updatePP () {
	var bulkUpdate = [];
	 
    $.each($("input[name='ppSFID']:checked"), function(){ 
        bulkUpdate.push($(this).val());
        
    });

    if(bulkUpdate=='' || bulkUpdate==null){
		swal({
			title: "Please select the Payment Plan",
		    text: "",
		   // timer: 3000,
		    type: "warning",
		});
		return false;
	} else if ($('#bulkSelection').val().trim() == '') {
		swal({
			title: "Please select Bulk Action",
		    text: "",
		   // timer: 3000,
		    type: "warning",
		});
		return false;
	}
   
  	$.ajax({
	    url: pageContext+'updatePP',
	    data: {projectid : $("#projectDataList").val(), 
	    	action : $("#bulkSelection").val(),
	    	ppsfid:bulkUpdate.join(",")  
	    },
	    type: 'POST',
	    success: function(data) { 
	 	    	swal({
					title: "Successfully Submitted",
				    text: "",
				    timer: 3000,
				    type: "success",
				});
	    		 
	 	    	getPPAgainstProject ();
	    		 
	    },
	    error: function(data) { }
	});

}