function fetchData(addr,id,reqMethod){
	debugger
	$("#mainPageLoad").show();
		var url;
	    var baseUrl=$("#contextPath").val();
		var resp;
		if(id==null){
			url=addr;
		}else{
			url=addr+"/"+id;
		}
		jQuery.ajax({
			type : reqMethod,
			contentType : "application/json",
			url : baseUrl+"/"+url,
			dataType:"json",
	        async:false,
	        success : function(data) {
	        	resp= data;
			},
			beforeSend : function() {
		        	$("#mainPageLoad").show();
		    },
		    complete : function() {
		        	$("#mainPageLoad").fadeOut("slow");
		    },
			error : function(ex) {
				$("#mainPageLoad").fadeOut("slow");
				console.log(ex);
				return null;
			}
		});
		return resp;
	}

	
function fetchAsyncData(addr,id,reqMethod,callback){
	debugger
	    /*$("#mainPageLoad").show();*/
		var url;
	    var baseUrl=$("#contextPath").val();
		
		if(id==null){
			url=addr;
		}else{
			url=addr+"/"+id;
		}
	/*setTimeout(function(){*/
		jQuery.ajax({
			type : reqMethod,
			contentType : "application/json",
			url : baseUrl+"/"+url,
			dataType:"json",
	        async:true,
	        success : function(data) {
	        	try{
	        		window[callback](data);
	        	}catch (e){
	        		console.log(e);
	        		urlResponseHandler(data);
	        	}
			},
			error : function(ex) {
				console.log(ex);
				return null;
			}
		});
	}

function fetchSyncData(addr,id,reqMethod,callback){
	    /*$("#mainPageLoad").show();*/
		var url;
	    var baseUrl=$("#contextPath").val();
		
		if(id==null){
			url=addr;
		}else{
			url=addr+"/"+id;
		}
	/*setTimeout(function(){*/
		jQuery.ajax({
			type : reqMethod,
			contentType : "application/json",
			url : baseUrl+"/"+url,
			dataType:"json",
	        async:false,
	        success : function(data) {
	        	try{
	        		window[callback](data);
	        	}catch (e){
	        		console.log(e);
	        		urlResponseHandler(data);
	        	}
			},
			error : function(ex) {
				console.log(ex);
				return null;
			}
		});
	}

function filterMatches(words, input) {
    return words.filter(function (word) {
    	var regExp = new RegExp(input.toLowerCase());
         return regExp.test(word.name.toLowerCase());
    });
}

function isEmpty(value){
	if(typeof(value)=='object' || typeof(value)=='string'){
		return $.isEmptyObject(value);
	}else{
		return "undefined"==typeof(value) || value=='' || value==null ;
	}
}

function loadReferenceList(cls,list){
	$("."+cls).empty();
	$("."+cls).append("<option value=''></option>" );
	$.each(list,function(index,object){
		$("."+cls).append("<option value='"+object.code+"'>"+ object.name+" </option>" );
	});
}

function loadReferenceListById(id,list){
	$("#"+id).empty();
	$("#"+id).append("<option value=''></option>" );
	$.each(list,function(index,object){
		$("#"+id).append("<option value='"+object.code+"'>"+ object.name+" </option>" );
	});
}

function getDate(value){
	if("undefined"==typeof(value) || value=='' || value==null ){
		return formatDate();
	}else{
		return formatDate(value);
	}
}

function getDateTime(value){
	if("undefined"==typeof(value) || value=='' || value==null ){
		return formatDateTime();
	}else{
		return formatDateTime(value);
	}
}

function formatDate(longDate){
	var dt;
	if(null!=longDate && typeof(longDate)!="undefined" && ""!=longDate){
		dt=new Date(longDate);
	}else{
		dt=new Date();
	}
   var dd=dt.getDate();
   var MM=dt.getMonth()+1;
   var yyyy=dt.getFullYear();
   /*var HH= dt.getHours();
   var mm= dt.getMinutes();
   var ss= dt.getSeconds();*/
   
   return getDoubleDigit(dd)+'-'+getDoubleDigit(MM)+'-'+getDoubleDigit(yyyy)

}

function formatDateTime(longDate){
	var dt;
	if(typeof(longDate)!="undefined" && null!=longDate && ""!=longDate){
		dt=new Date(longDate);
	}else{
		dt=new Date();
	}
	var dd=dt.getDate();
	var MM=dt.getMonth()+1;
	var yyyy=dt.getFullYear();
	var HH= dt.getHours();
	var mm= dt.getMinutes();
	/*var ss= dt.getSeconds();*/
	
	return getDoubleDigit(dd)+'-'+getDoubleDigit(MM)+'-'+getDoubleDigit(yyyy)+' '+getDoubleDigit(HH)+':'+getDoubleDigit(mm);
}

function getDoubleDigit(value){
	var nmr=Number(value);
	var intPart=Math.floor(nmr/10);
	if(intPart==0){
		return value="0"+value;
	}else{
		return value;
	}
}

function loadReferenceListToRadio(cls,list){
	var radioName=$("."+cls).attr('radioName');
	var radioDiv=$("."+cls).find('.radioBtnCol');
	radioDiv.html('');
	$.each(list,function(index,object){
		if(index==0){
			radioDiv.append("<label class='btn btn-primary active'>"+
			"<input type='radio' id='' name='"+radioName+"' value='"+object.code+"' checked>"+object.name+
			"</label>");
		}else{
			radioDiv.append("<label class='btn btn-primary'>"+
			"<input type='radio' id='' name='"+radioName+"' value='"+object.code+"' >"+object.name+
			"</label>");
		}
		
	});
}

function loadReferenceListToRadioById(id,list){
	var radioName=$('#'+id).attr('radioName');
	var radioDiv=$('#'+id).find('.radioBtnCol');
	radioDiv.html('');
	$.each(list,function(index,object){
		if(index==0){
			radioDiv.append("<label class='btn btn-primary active'>"+
			"<input type='radio' id='' name='"+radioName+"' value='"+object.code+"' checked>"+object.name+
			"</label>");
		}else{
			radioDiv.append("<label class='btn btn-primary'>"+
			"<input type='radio' id='' name='"+radioName+"' value='"+object.code+"' >"+object.name+
			"</label>");
		}
		
	});
}