function insertScreenConfig() {
	var formData = new FormData();
	formData.append('projectsfid', $("#projectid").val().trim());
	formData.append('token_type', $("#tokenType").val().trim());
	formData.append('key_cont1', $("#keyCont1").val().trim());
	formData.append('key_cont2', $("#keyCont2").val().trim());
	formData.append('video_url', $("#avUrl").val().trim());
	formData.append('image_url', $("#imageUrl").val().trim());
	formData.append('ticker_label', $("#footerUpdateLabel").val().trim());
	formData.append('ticker_text', $("#footerUpdate").val().trim());
	formData.append('createdby', USERID_GV);
	formData.append('updatedby', USERID_GV);
	 
	$.ajax({
		url : pageContext+"insertTokenScreenConfig",
		type: "POST",
		data : JSON.stringify(Object.fromEntries(formData)),
		dataType : 'json',
		contentType: 'application/json'
	}).done(function(response){
		swal({
        	title: "Successfully inserted",
  			text: "",
  			timer: 8000,
  			type: "success",
        });
		getTokenScreenConfig();
	});
}

function getTokenScreenConfig () {
    $.post(pageContext+"getScreenConfig",{"projectid":$('#projectid').val()},function(data){                      
    }).done(function(data){
          $('.tncData').empty();
          var obj =JSON.parse(data);
          
          if (obj.status == "NO_RECORD") {
				$("#tcInsertBtn").show();
				$("#tcUpdateBtn").hide();
				$("#generateScreenBtn").hide();
          } else if (obj.status == "ERROR") {
				$("#tcInsertBtn").hide();
				$("#tcUpdateBtn").hide();
				$("#generateScreenBtn").hide();
          } else {
				$("#tcInsertBtn").hide();
				$("#tcUpdateBtn").show();
				$("#generateScreenBtn").show();
	        	  
				//$('#tokenType [value='+obj.token_type.trim()+']').attr('selected', 'selected');
				
				/*$('#tokenType option')
			     .removeAttr('selected')
			     .filter('[value='+obj.token_type.trim()+']')
			         .attr('selected', true)*/
			         
			    var tokenTypeHtml = '';
				var selectedAll = '';
				var selectedW = '';
				var selectedE = '';
				var selectedG = '';
				var selectedMS = '';
				var selectedMF = '';
				
				if (obj.token_type.trim() == "All") {
					selectedAll = "selected";
				}else if (obj.token_type.trim() == "W") {
					selectedW = "selected";
				}else if (obj.token_type.trim() == "E") {
					selectedE = "selected";
				}else if (obj.token_type.trim() == "G") {
					selectedG = "selected";
				}else if (obj.token_type.trim() == "MS") {
					selectedMS = "selected";
				}else if (obj.token_type.trim() == "MF") {
					selectedMF = "selected";
				} 
				
				tokenTypeHtml += 	'<option value="All" '+selectedAll+'>All</option>'
									+ '<option value="W" '+selectedW+'>W</option>' 
									+ '<option value="E" '+selectedE+'>E</option>' 
									+ '<option value="G" '+selectedG+'>G</option>' 
									+ '<option value="MS" '+selectedMS+'>MS</option>' 
									+ '<option value="MF" '+selectedMF+'>MF</option>';    
			         
			     
				$("#tokenType").empty();
				$("#tokenType").append(tokenTypeHtml);
				
				$("#keyCont1").val(obj.key_cont1.trim());
				$("#keyCont2").val(obj.key_cont2.trim());
				$("#avUrl").val(obj.video_url.trim());
				$("#imageUrl").val(obj.image_url.trim());
				$("#footerUpdateLabel").val(obj.ticker_label.trim());
				$("#footerUpdate").val(obj.ticker_text.trim());
				$("#recordid").val(obj.id);
				
				$('#tokenTypeVal').val(obj.token_type.trim());
				
				$("#generateScreenBtn").attr("href", PAGECONTEXT_GV+"tokenScreen?tokentype="+$('#tokenTypeVal').val()+"&projectid="+$('#projectid').val()+"&projectname="+$('#projectname').val())
          }
    });
}

function updateScreenConfig() {
	var formData = new FormData();
	formData.append('projectsfid', $("#projectid").val().trim());
	formData.append('token_type', $("#tokenType").val().trim());
	formData.append('key_cont1', $("#keyCont1").val().trim());
	formData.append('key_cont2', $("#keyCont2").val().trim());
	formData.append('video_url', $("#avUrl").val().trim());
	formData.append('image_url', $("#imageUrl").val().trim());
	formData.append('ticker_label', $("#footerUpdateLabel").val().trim());
	formData.append('ticker_text', $("#footerUpdate").val().trim());
	formData.append('updatedby', USERID_GV);
	formData.append('id', $("#recordid").val().trim());
	
	$.ajax({
		url : pageContext+"updateTokenScreenConfig",
		type: "POST",
		data : JSON.stringify(Object.fromEntries(formData)),
		dataType : 'json',
		contentType: 'application/json'
	}).done(function(response){
		if (response){
			swal({
            	title: "Successfully updated",
      			text: "",
      			timer: 8000,
      			type: "success",
            });
			getTokenScreenConfig();
		} else {
			swal({
            	title: "Something went wrong",
      			text: "",
      			timer: 8000,
      			type: "warning",
            });
		}
	});
}