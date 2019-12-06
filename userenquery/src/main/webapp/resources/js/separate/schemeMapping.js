$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});
var pageContext = $("#pageContext").val()+"/";	


function projectDataListForSM (){
	$('#projectDataListForSM').empty();	
	var urlTower = pageContext+"projectDataList?region="+$('#regionListForSM').val();
	$.getJSON(urlTower, function (data) {
		$('#projectDataListForSM').append('<option value="-1">Select</option>');
		$.each(data, function (index, value) {
			$('#projectDataListForSM').append("<option value='"+value.sfid+"'>"+value.name+  " / " +value.propstrength__project_code__c+ "</option>");
		});					
	}).done(function() {
	});
}


var schemeSourceArray = {};
var schemeSiteArray = {};
var schemePromotionalArray = {};


function getSchemeSource (){
    
	schemeSourceArray = {};
	
	$('#getSchemeSource').empty();
    
    var optionHtml = '';
    var url = pageContext+"getSchemeSource?projectid="+$("#projectDataListForSM").val()+"&cp_flag_yn=";
    
	$.getJSON(url, function (data) {
		$('#getSchemeSource').append('<option value="-1">Select</option>');
		$.each(data, function (index, value) {
			$('#getSchemeSource').append('<option value='+value.id+'>'+ value.scheme+'</option>');
			
			schemeSourceArray[value.id] = value.scheme;
			
		});   
		//alert ("getSchemeSource ::: " + schemeSourceArray);
	}).done(function() {
		getSchemeSite ();
		
	});
}

function getSchemeSite (){
	$('#getSchemeSite').empty();
	schemeSiteArray = {};
	
    var url = pageContext+"getSchemeSite?projectid="+$("#projectDataListForSM").val()+"&cp_flag_yn=";
    
	$.getJSON(url, function (data) {
		$('#getSchemeSite').append('<option value="-1">Select</option>');
		$.each(data, function (index, value) {
			$('#getSchemeSite').append('<option value='+value.id+'>'+ value.scheme+'</option>');
			
			schemeSiteArray[value.id] = value.scheme;
			
		});                              
	}).done(function() {
		getSchemePromotional ();
	});
}

function getSchemePromotional () {
   $('#getSchemePromotional').empty();
	schemePromotionalArray = {};
	
    var url = pageContext+"getSchemePromotional?projectid="+$("#projectDataListForSM").val()+"&cp_flag_yn=";
    
	$.getJSON(url, function (data) {
		$('#getSchemePromotional').append('<option value="-1">Select</option>');
		$.each(data, function (index, value) {
			$('#getSchemePromotional').append('<option value='+value.id+'>'+ value.scheme+'</option>');
			schemePromotionalArray[value.id] = value.scheme;
		});                              
	}).done(function() {
		mainSchemeDropdown ()
	});
}









function mainSchemeDropdown (){
	$('#headerSchemeDD').empty();
	
	$('#headerSchemeTable').empty();
	
	
    var urlpayemntPlanNew = pageContext+"getHeaderSchemeDtl?projectid="+$("#projectDataListForSM").val();
             
    $.getJSON(urlpayemntPlanNew, function (data) {
    	
    	//alert ("Source ::: " + schemeSourceArray);
    	
    	/*if (data == null) {
    		swal({
    			title: "This is not a valid scheme combination",
    			text: "",
    			type: "error",
    		});
	 
    	} else {*/
    	
    		var option = '';
    		var optionOld = '';
    		var html = '';
    		
    		var schemeSourceHtml = '';
    		var schemeSiteHtml = '';
    		var schemePromotionalHtml = '';
    		
    		
    		
    		var selected = '';
    		
    		
    		
    		/*schemeSourceHtml += "<select class='full form-control'> <option value='-1'>Select</option>" ;
			for (i=0; i<schemeSourceArray.length;i++) {
				schemeSourceHtml += schemeSourceArray[i];
			}
			schemeSourceHtml += "</select>" ;*/
    		
    		
    		html += "<table class='table table-bordered bgWhite'>" +
    				" <tbody>" +
	    				"<tr>" +
	    					"<td>ID</td> " +
	    					"<td>Scheme Rates</td> " +
	    					"<td>Scheme Name</td> " +
	    					
	    					"<td>Source</td> " +
	    					"<td>Site</td> " +
	    					"<td>Promotional</td> " +
	    				"</tr>";
    		
    		$.each(data, function (index, value) {
    			
    			optionOld = "P: " + value.percentage + " , A: " + value.absolute_amount + " , R: " + value.rate + " - " + value.scheme ; 
    			
    			option = "P: " + value.percentage + " , A: " + value.absolute_amount + " , R: " + value.rate;
    			
    			$('#headerSchemeDD').append('<option value='+value.id+'>'+ optionOld +'</option>');
    			
    			
    			
    			schemeSourceHtml = '';
    			schemeSourceHtml += "<select class='full form-control sourceNameDD'> <option value='-1'>Select</option>" ;
        		$.each(schemeSourceArray, function(key, data){
        			if ( value.source_name == data) {
        				selected = "selected";
        			}else {
        				selected = '';
        			}
        			schemeSourceHtml += "<option value='"+key+"'  class='ddid'  "+ selected+">"+data+"</option>";
                });
        		schemeSourceHtml += "</select>" ;
    			
    			
        		schemeSiteHtml = '';
        		schemeSiteHtml += "<select class='full form-control siteNameDD'> <option value='-1'>Select</option>" ;
        		$.each(schemeSiteArray, function(key, data){
        			if ( value.site_name == data) {
        				selected = "selected";
        			}else {
        				selected = '';
        			}
        			schemeSiteHtml += "<option value='"+key+"'  class='ddid'  "+ selected+">"+data+"</option>";
                });
        		schemeSiteHtml += "</select>" ;
        		
        		
        		schemePromotionalHtml = '';
        		schemePromotionalHtml += "<select class='full form-control promotionalNameDD'> <option value='-1'>Select</option>" ;
        		$.each(schemePromotionalArray, function(key, data){
        			if ( value.promotional_name == data) {
        				selected = "selected";
        			}else {
        				selected = '';
        			}
        			schemePromotionalHtml += "<option value='"+key+"'  class='ddid'  "+ selected+">"+data+"</option>";
                });
        		schemePromotionalHtml += "</select>" ;
        		
    			
    			 html += "<tr class='schemeMappingRow'>" +
			                 "<td> <input value='"+value.id+"' class='schemeID' style='display:none' />"+ value.id +" </td>" +
			                 "<td>"+ option +"</td>" +
			                 "<td>"+ value.scheme +"</td>" +
			                 "<td>"+ schemeSourceHtml +"</td>" +
			                 "<td>"+ schemeSiteHtml +"</td>" +
			                 "<td>"+ schemePromotionalHtml +"</td>" +
			                 
				          "</tr>";
    			 
    			
    		});
    		
    		html += "</tbody>" +
    				"</table>";
    		
    		$('#headerSchemeTable').append(html);
    		
    	//}
	 
    }).done(function() { });
}



function addschemeMapping () {
	$.post(pageContext+"insertSchemeMapping",{"region":$('#regionListForSM').val(),"projectName":$('#projectDataListForSM option:selected').text(),
		"projectid":$('#projectDataListForSM').val(), 
		"headerSchemeId": $('#headerSchemeDD').val(),
		"schemeSourceId": $('#getSchemeSource').val(),
		"schemeSiteId": $('#getSchemeSite').val(),
		"schemePromotionalId": $('#getSchemePromotional').val()},function(data){				 
		
	}).done(function(data){
		if (data == "recordExist") {
			swal({
				title: "Scheme Already Exists",
			    text: "",
			    //timer: 2000,
			    type: "error",
			});
		} else if (data == "newRecord") {
			swal({
				title: "Successfully Submitted",
			    text: "",
			    timer: 2000,
			    type: "success",
			});
		}
	});
}




function bulkSubmitSchemeMapping () {
	var arrayData = [];
    $("#headerSchemeTable .schemeMappingRow").each(function () {
       
         
              var schemeData = {};
              schemeData.scheme_id = $(this).find('.schemeID').val();
              schemeData.scheme_source_id = $(this).find('.sourceNameDD').val();
              schemeData.scheme_site_id = $(this).find('.siteNameDD').val();
              schemeData.scheme_promotional_id = $(this).find('.promotionalNameDD').val();
             
              schemeData.isactive = 'A';
              schemeData.createdby = '999';
              schemeData.updatedby = '999';
              
              
              schemeData.region_name = $('#regionListForSM').val();
              schemeData.project_name = $('#projectDataListForSM option:selected').text();
              schemeData.project_id = $('#projectDataListForSM').val();
              
              arrayData.push(schemeData);
             
         
    });
    
    $.post(pageContext+"bulkInsertSchemeMapping",{"mappingJson" : JSON.stringify(arrayData)},function(data){                         
    }).done(function(){
    	swal({
			title: "Successfully Submitted",
		    text: "",
		    //timer: 2000,
		    type: "success",
		});
    });
}
