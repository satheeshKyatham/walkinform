$(document).ready(function() {
	/*var today = new Date();
	var currentDate = today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);
	$('.cpEngmntFromDateOffer').val(currentDate);
	$('.cpEngmntToDateOffer').val(currentDate);
	$('#visit_date').val(currentDate);*/
	userprojectmultiselect ();
});

function cpEngProjectDD () {
	var urlGetUsers = PAGECONTEXT_GV+"getProjectListUserWise?userid="+USERID_GV;	
	var option = ''; 
	
	$.getJSON(urlGetUsers, function (data) {
		 
		var defaultSelected = "";
		option = "<option value=''>Select Project</option>";
		$.each(data, function (index, value) {
			
			if (value.projectId == $('#projectid').val()) {
				defaultSelected = "selected";
			} else {
				defaultSelected = "";
			}
			
			/*if(value.projectId == PROID_GV){
				defaultSelected = "selected";
			} else {
				defaultSelected = "";
			}*/
			
			option = option+"<option value="+value.projectId+" name="+value.region__c+" "+defaultSelected+">"+value.projectName+"</option>";
			
		});		
		//option=option+"</select>";
	}).done(function() {
		$("#cpEngProjectDD").append("");
		$("#cpEngProjectDD").append(option);
	});  
}

function insertCPEngmnt () {
	
	$("#insertCPRec").attr("disabled", true);
	
	var validate=checkValidationOnSubmit("cpEngmntForm");
	if(validate) {
		var formData = new FormData();
		formData.append('visit_date', $('#visit_date').val().replace(/-/gi, "/"));
		formData.append('project_sfid', $('#cpEngProjectDD').val());
		formData.append('cp_name', $('#cp_name').val());
		formData.append('meeting_place', $('#meeting_place').val());
		formData.append('topic', $('#topic').val());
		formData.append('discussed_point', $('#discussed_point').val());
		formData.append('createdby', $('#userid').val());
		formData.append('updatedby', $('#userid').val());
		
		formData.append('cpid', $("#cpName").attr('data-cpid'));
		formData.append('cpsfid', $("#cpName").attr('data-cpsfid')); 
		
		$.ajax({
			url : PAGECONTEXT_GV+"insertCPEngmnt",
			type: "POST",
			data : JSON.stringify(Object.fromEntries(formData)),
			dataType : 'json',
			contentType: 'application/json'
		}).done(function(response){
			
			if (response.status == "STATUS_OK"){
				
				$('#cp_name').val("");
				
				$("#cpName").attr('data-cpid', '');
				$("#cpName").attr('data-cpname', '');
				$("#cpName").attr('data-cpsfid', '');
				
				$('#meeting_place').prop('selectedIndex',0);
				$('#topic').prop('selectedIndex',0);
				$('#discussed_point').val("");
				
				swal({
		        	title: "Successfully submitted",
		  			//text: "Requested records count is: "+obj1[i].qry_count,
		  			type: "success",
		        });
				
				getCPEngmntReportDtl('CLOSINGROLE');
			} else {
				swal({
		        	title: "Unable to Submit a Record.",
		  			//text: "Requested records count is: "+obj1[i].qry_count,
		  			type: "warning",
		        });
			}
			
			$("#insertCPRec").attr("disabled", false);
			
		});
	} else {
		$("#insertCPRec").attr("disabled", false);
	}
}

/*Auto Search CP name*/
fetchChannelPartners();

var channelPartnerArray=[];
var channelPartners = [];
 

function fetchChannelPartners(){
	var url=$("#contextPath").val();
	$.get(url+"/getChannelPartners",function(data){				 
	}).done(function(data){
		channelPartners=data.objectMap.channelPartnerList;
	});
}

var cpName = "";
var cpid = "";
var cpsfid = "";

function getChannelPartners(event,el){
	
	event.preventDefault();
	var text=$(el).val();
	
	if (text.trim() != cpName.trim()) {
		$("#cpName").attr('data-cpid', '');
		$("#cpName").attr('data-cpname', '');
		$("#cpName").attr('data-cpsfid', '');
	} else {
		$("#cpName").attr('data-cpid', cpid);
		$("#cpName").attr('data-cpname', cpName);
		$("#cpName").attr('data-cpsfid', cpsfid);
	}
	
	if(channelPartners.length>0){
		var partnerList = getMatchingTen(text);
    	filterChannelPartners(partnerList);
    } else{
    	if(text.length>=3){		
    		fetchAsyncData("getChannelPartnerList",text,"GET","loadChannelPartners");		
    	}else if(text.length==0){
    		channelPartnerArray=[];
    	}
    }
}

function filterChannelPartners(partnerList){
	channelPartnerArray=partnerList;
	refreshChannelPartnerList();
	//$("#channelPartnerLoader").hide();
	//$("#cp_name").attr('readonly', false);
}

function refreshChannelPartnerList(){
	
	$("#cp_name").autocomplete({
        source: function (request, response) {
            response($.map(channelPartnerArray, function (value, key) {
                 return {
                     label: value.name,
                     value: value.sfid,
                     attr: value.channelPartnerId
                 }
             }));
        },
        select: function (event, el) {  
        	event.preventDefault();
        	$("#cp_name").val(el.item.label);
        	
        	cpName= el.item.label;
        	cpid= el.item.attr;
        	cpsfid= el.item.value;
        	
        	$("#cpName").attr('data-cpid', el.item.attr);
        	$("#cpName").attr('data-cpname', el.item.label);
        	$("#cpName").attr('data-cpsfid', el.item.value);
        	
        	//$("#channelPartnerName").val(el.item.label);
        	//$("#channelPartnerSfid").val(el.item.value);
        	//$("#channelPartnerId").val(el.item.attr);
        	//loadBrokerContacts();
            return false;
        }
    });

}

 

function getMatchingTen(word){
	if(word ==null){
		return;
	}
	var counter =0;
	var result = channelPartners.filter(function (partner){
		var matcher = word.replace(/ /g,"");
		matcher = matcher.toLowerCase();
		var partnerName = partner.name== null? "":partner.name.replace(/ /g,"").toLowerCase();
		var wordLength = matcher.length;
		var name = partnerName.substring(0,wordLength)
		var matchedName = name==matcher;
		if(matchedName){
			counter++;
		}
		return matchedName && counter <11;
	});
	var includedValues =[];
	if(result ==null || result.length<10){
		includedValues = channelPartners.filter(function (partner){
			var matcher = word.replace(/ /g,"");
			matcher = matcher.toLowerCase();
			var partnerName = partner.name== null? "":partner.name.replace(/ /g,"").toLowerCase();
			var matchedName =partnerName.includes(matcher);
			if(matchedName && !result.includes(partner)){
				counter++;
			}
			return matchedName && counter <11 && !result.includes(partner);
		});
	}
	if(result ==null){
		result=[];
	}
	if(includedValues !=null && includedValues.length>0){
		result =  result.concat(includedValues);
	}
	 
	return result;
}
/* END Auto Search CP name*/