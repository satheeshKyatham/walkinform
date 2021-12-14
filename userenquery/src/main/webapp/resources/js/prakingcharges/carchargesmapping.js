$( document ).ready(function() {
	projectDataList();
});
function projectDataList (){
	debugger
	$('#projectDataList').empty();	
	var urlTower = pageContext+"projectListForSales?userId="+ $("#userid").val();
	$('#projectDataListForSM').append('<option value="-1">Select</option>');
	$.getJSON(urlTower, function (data) {
		$.each(data, function (index, value) {
			//$("#region_id").val(value.region__c);
			$('#projectDataListForSM').append("<option value='"+value.project_id+"'>"+value.project_name+ "</option>");
		});					
	}).done(function() {
	});
}


function getCarParkingCombination()
{
	$('.commonLoad').show();
	var tower=$('#towerChargeMst').find('option:selected').attr('name');
	$('#headerCarParkingTable').empty();
	console.log('tower:-'+tower)
	var urlTower = pageContext+"getCarParkingCombination?towersfid="+ tower;
	
	var html= "";
	html += "<table class='table table-bordered bgWhite'>" +
	" <tbody>" +
		"<tr>" +
			/*"<td></td> " +*/
			"<td style='display:none'>Project SFID</td> " +
			"<td style='display:none'>Tower SFID</td> " +
			"<td>Property Type</td> " +
			"<td>Parking Category</td> " +
			"<td>Property Type SFID</td> " +
			"<td>Property Code</td> " +
			"<td style='width:100px;'>Absolute Amount</td> " +
			"<td>Action</td> " +
		"</tr>";
	
	$.getJSON(urlTower, function (data) {
		var propertList=[];
		var finalrespList = [];
		
		$.each(data, function (index, value) {
			//console.log(value.row_count);
			var propertList1=[];
			propertList1.push(value.property_type_name);
			propertList1.push(value.parking_category);
			propertList.push(propertList1);
		});
		//find for duplicate
		var counts = {};
		propertList.forEach(function(x) { 
			counts[x] = (counts[x] || 0)+1; 
			});
		console.log('Counts:-'+counts);
		var jsonww = JSON.stringify(data);
		var jj = JSON.parse(JSON.stringify(data));
		$.each(data, function (index, value1) {
			$.each(counts, function (index, value) {
				//console.log("Hello"+value+ " Key "+ index);
				var jsonkk="";
				if(index==value1.property_type_name+","+value1.parking_category)
					{
						//console.log("Success:"+index + " ** "+value);
						var inx = index.split(",")[1];
						var valData =index.split(",")[0];
						//console.log("valData:"+valData + " inx: "+inx);
						var item = jj.find(x => x.property_type_name == valData && x.parking_category== inx);
						//console.log("item70:"+item);
						if (item) {
						  item.row_count = value;
						  //console.log("item73:"+item.row_count);
						}
						//console.log("item75:"+item);
						jsonkk = JSON.stringify(item);
						//console.log("jsonkk:"+jsonkk);
						finalrespList.push(item);
					}
			});
			
		});
		 
		/*$.each(finalrespList, function (index, value) {
			$('.submit_carpark').show();
			var dd = "";
			var row_nums=0;
			var nn=false;
			console.log("counts:"+JSON.stringify(counts));*/
			
		$.each(counts, function (indexCount, valueCount) {
				
				var q= 0;
			
				$.each(data, function (index, value) {
					
					
					
					if (indexCount==value.property_type_name+","+value.parking_category) {
						
						$('.submit_carpark').show();
						var dd = "";
						var row_nums=0;
						var nn=false;
						console.log("counts:"+JSON.stringify(counts));
						
						/*if (q == 0){
							dd="<td rowspan='"+valueCount+"' align='right'>"+value.property_type_name+"-"+value.parking_category+"</td>";
							q=1;
						} else {
							dd="";
						}*/
						var amountTXT="";
						var btn_delete="";
						if(value.isactive=='I')//readonly
							{
								amountTXT="<input type='number' name='carpark_amount' class='txt_carpark_amount' style='background-color: #f2f2f2 !important;width:100px;text-align: center;' disabled='disabled' value="+value.absolute_amount+">";//value.absolute_amount
								btn_delete="<button type='button' class='activate_carpark'>Activate</button>";
							}
						else
							{
								amountTXT="<input type='number' name='carpark_amount' class='txt_carpark_amount' style='width:100px;text-align: center;' value="+value.absolute_amount+">";//value.absolute_amount 
								btn_delete="<button type='button' class='delete_carpark'>InActive</button>"
							}
						
						html+="<tr class='carParkMappingRow'>" +
						 
						/*""+dd+""+*/
						"<td class='project_sfid' style='display:none'>"+value.project_sfid+"</td>"+
						"<td class='tower_sfid' style='display:none'>"+value.tower_sfid+"</td>"+
						"<td class='property_type_name'>"+value.property_type_name+"</td>"+
						"<td class='parking_category'>"+value.parking_category+"</td>"+
						"<td class='property_type_sfid' >"+value.property_type_sfid+"</td>"+
						"<td class='property_type_code' >"+value.property_type_code+"</td>"+
						"<td class='amount'>"+amountTXT+"</td>"+
						"<td class='delete'>"+btn_delete+"</td>" +
						"</tr>";
					}
					
					
				 
				
				
				
				
				/*if(indexCount==value.property_type_name+","+value.parking_category)
					{
					row_nums=valueCount;
					nn=true;
					}
				if(nn)
					dd="<td rowspan='"+row_nums+"' align='right'>"+value.property_type_name+"-"+value.parking_category+"</td>";*/
				
			});
		});
			 
			/*if(value.isactuve!='A')
				$('.txt_carpark_amount').attr("disabled","disabled") 
			var amountTXT="<input type='number' name='carpark_amount' class='txt_carpark_amount' value="+value.absolute_amount+">"; 
			var btn_delete="<button type='button' class='delete_carpark'>InActive</button>"
			html+="<tr class='carParkMappingRow'>" +
			 
			""+dd+""+
			"<td class='project_sfid'>"+value.project_sfid+"</td>"+
			"<td class='tower_sfid'>"+value.tower_sfid+"</td>"+
			"<td class='parking_category'>"+value.parking_category+"</td>"+
			"<td class='property_type_name'>"+value.property_type_name+"</td>"+
			"<td class='amount' >"+amountTXT+"</td>"+
			"<td class='property_type_sfid' >"+value.property_type_sfid+"</td>"+
			"<td class='property_type_code' >"+value.property_type_code+"</td>"+
			"<td class='delete'>"+btn_delete+"</td>" +
			"</tr>";
			
			
		});	*/				
	}).done(function() {
		html+="</tbody></table>";
		$('#headerCarParkingTable').append(html);
		$('.commonLoad').hide();
	});
	
	}//
$(document).on('click', '.delete_carpark' , function() {
	 var txt = $(this).parents('tr').find("td:eq(1)").text();
	  console.log(txt);
	  $.ajax({
			url : pageContext+"inActiveParkingCombination?property_type_sfid="+$(this).parents('tr').find("td:eq(4)").text()+"&parking_category="+$(this).parents('tr').find("td:eq(3)").text()+"&isactive=I",
			type : 'GET',
			processData : false, 
			//contentType : 'application/json',
	        success: function (data) {
	        	console.log("sus",data);
	        	swal({
	    			title: "Successfully Submited...!",
	    		    text: "",
	    		    timer: 2000,
	    		    type: "success",
				});
	        	getCarParkingCombination();
			 
	        }
		});
    }) ; 

$(document).on('click', '.activate_carpark' , function() {
	 var txt = $(this).parents('tr').find("td:eq(1)").text();
	  console.log(txt);
	  $.ajax({
			url : pageContext+"inActiveParkingCombination?property_type_sfid="+$(this).parents('tr').find("td:eq(4)").text()+"&parking_category="+$(this).parents('tr').find("td:eq(3)").text()+"&isactive=A",
			type : 'GET',
			processData : false, 
			//contentType : 'application/json',
	        success: function (data) {
	        	console.log("sus",data);
	        	swal({
	    			title: "Successfully Submited...!",
	    		    text: "",
	    		    timer: 2000,
	    		    type: "success",
				});
	        	getCarParkingCombination();
			 
	        }
		});
   }) ; 

$('.delete_carpark').on('click', function(){
	  var txt = $(this).parents('tr').find("td:eq(1)").text();
	 /* $.ajax({
			url : pageContext+"inActiveParkingCombination?property_type_sfid="+property_type_sfid+"&parking_category="+parking_category+"",
			type : 'POST',
			data : {property_type_sfid : property_type_sfid, 
				parking_category :parking_category},
			processData : false, 
			//contentType : 'application/json',
	        success: function (data) {
	        	console.log("sus",data);
	        	swal({
	    			title: "Successfully Submited...!",
	    		    text: "",
	    		    timer: 2000,
	    		    type: "success",
				});
	        	getCarParkingCombination();
			 
	        }
		});*/
	});
function onClickForInactive1(e)//property_type_sfid,parking_category
{
	/*alert($(e.currentTarget).closest("tr").);
	alert($(e.currentTarget).find(".parking_category").html());*/
	var $row = $(this).closest("tr");    // Find the row
    var $text = $row.find(".parking_category"); 
    alert($(this).parents('tr').find("td:eq(2)").text());
    alert(JSON.stringify($text));
	/*$.ajax({
		url : pageContext+"inActiveParkingCombination?property_type_sfid="+property_type_sfid+"&parking_category="+parking_category+"",
		type : 'POST',
		data : {property_type_sfid : property_type_sfid, 
			parking_category :parking_category},
		processData : false, 
		//contentType : 'application/json',
        success: function (data) {
        	console.log("sus",data);
        	swal({
    			title: "Successfully Submited...!",
    		    text: "",
    		    timer: 2000,
    		    type: "success",
			});
        	getCarParkingCombination();
		 
        }
	});*/
	}
function onCarParkingSubmit()
{
	$('.commonLoad').show();
	var your_array = [];
	$('#headerCarParkingTable tbody tr').each(function(row, tr) {
		if($(this).find("td:eq(6) input[type='number']").val()!=''){
			$(this).find('.txt_carpark_amount').css("border-color","");
		your_array.push({
			project_sfid: $(this).find(".project_sfid").html(),
			tower_sfid: $(this).find(".tower_sfid").html(),
			parking_category: $(this).find(".parking_category").html(),
			property_type_name: $(this).find(".property_type_name").html(),
			absolute_amount: $(this).find("td:eq(6) input[type='number']").val(),
			property_type_sfid: $(this).find(".property_type_sfid").html(),
			property_type_code: $(this).find(".property_type_code").html(),
			createdby:$('#userid').val(),
			updatedby:$('#userid').val(),
			isactive:"A"
		    }); 
		}
		else
			{
			 $(this).find('.txt_carpark_amount').css("border-color","red");
			}
	  });
	var theRemovedElement = your_array.shift(); 
	var json = JSON.stringify(your_array);
	console.log("json",json);
	$.ajax({
		url : pageContext+'insertCarParkingCombination',
		type : 'POST',
		data : json,
		processData : false, 
		contentType : 'application/json',
        success: function (data) {
        	swal({
    			title: "Successfully Submited...!",
    		    text: "",
    		    timer: 2000,
    		    type: "success",
			});
        	getCarParkingCombination();
        	/*if(data!=null && data.insertStatus =="Status_OK"){
        		alert('Hecxcc');
        	}else{
        		swal({
        			title: "Something went wrong...!",
        		    text: "",
        		    timer: 2000,
        		    type: "error",
    			});
        	}*/
		 
        }
	});
	/*$('#headerCarParkingTable tr').each(function() {
	    var customerId = $(this).find(".tower_sfid").html();
	    var data1 = $(this).find("td:eq(0) input[type='text']").val();
	   // alert(customerId);
	    alert($(this).find(".amount").html());
	 });*/
	//alert($('#headerCarParkingTable').text());
}

/*function getTowerList(project)
{
	//var project=$('#projectDataListForSM').find('option:selected').attr('value');
	getTowerList(project);
	
	
}*/


function getTowerList() {
	$('#towerChargeMst').empty();	
	var project=$('#projectDataListForSM').find('option:selected').attr('value');
	var urlTower = pageContext+"getTowerMaster?project_code="+project;
	
	
	$.getJSON(urlTower, function (data) {
		$('#towerChargeMst').append('<option name="0" value="0">Select</option>');
		$.each(data, function (index, value) {
			$('#towerChargeMst').append("<option name='"+value.sfid+"' value='"+value.tower_code__c+"'>"+value.tower_name__c+"</option>");
		});					
	}).done(function() {
		//$('#paymentDueSubmit').show();
		
	});
}