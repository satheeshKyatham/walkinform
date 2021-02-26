$.ajaxSetup({
    statusCode: {
        401: function(){
          location.reload();
        }
    }
});

function discountType(e){
	if ($(e).find('option:selected').val() == "0") {
		$('.perDisInput').show();
		$('.flatDisInput').hide();
	} else if ($(e).find('option:selected').val() == "1") {
		$('.perDisInput').hide();
		$('.flatDisInput').show();
	}
};


var pageContext = $("#pageContext").val()+"/";	

function createPromoCode (e, pcStatus) {
	var status = "";
	
	if (pcStatus == "ACTIVE") {
		status = 1;
	} else if (pcStatus == "INACTIVE") {
		status = 0;
	}
	
	var project = $('#pcDisOnTypology option:selected');
	var selectedTower = [];
	$(project).each(function(index, brand){
		selectedTower.push($(this).val());
	});
	
	$.post(pageContext+"createPromoCode",{"project_sfdc_id":$('#projectid').val(), 
		"promo_code":$('#pcPromoCode').val(), 
		"promo_code_discount_type":$('#pcDisType').find('option:selected').val(), 
		"flat_discount_amount":$('#pcFlatDisAmount').val(), 
		"discount_percentage":$('#pcDisPercentage').val(), 
		"expiry_date":$('#pcExpiryDate').val(), 
		"coupon_discount_upto":$('#pcDisUpto').val(), 
		"promocode_max_use_count":$('#pcMaxUseCount').val(), 
		"typology":selectedTower.join(","), 
		"status":status },function(data){
		
		var html = '';
		var obj =JSON.parse(data);
		
		if(obj!=null){
			
			var alertStatus = "";
			var promoID = "";
			
			if (obj.status == "500") {
				alertStatus = "warning";
				promoID = "";
			} else if (obj.status == "200") {
				alertStatus = "success";
				promoID = " Prmocode: " + obj.promo_id;
			}
			
			swal({
				title: obj.msg,
			    text:  promoID,
			    //timer: 8000,
			    type: alertStatus,
			});
		}
	}).done(function(obj){
		fetchPromoCode();
	});	
}


function getTypologyPromoCode (){
	
	var optionTower = ''; 
	
	
	optionTower += "<option value='1 Bed Premium'>1 Bed Premium</option>"
		+ "<option value='1 BHK Luxe'>1 BHK Luxe</option>"
		+ "<option value='3 BHK Luxe'>3 BHK Luxe</option>"
		+ "<option value='Cottage (Test)'>Cottage (Test)</option>"
		+ "<option value='1 Bed Premium'>1 Bed Premium</option>"
		+ "<option value='1 Bed Premium'>1 Bed Premium</option>"
		+ "<option value='1 Bed Luxe'>1 Bed Luxe</option>"
		+ "<option value='1 Bed Luxe'>1 Bed Luxe</option>"
		
		+ "<option value='1 BHK'>1 BHK</option>"
		+ "<option value='1.5 BHK'>1.5 BHK</option>"
		+ "<option value='104-119 Sq.Yard'>104-119 Sq.Yard</option>"
		+ "<option value='120-129 Sq.Yard'>120-129 Sq.Yard</option>"
		+ "<option value='130-139 Sq.Yard'>130-139 Sq.Yard</option>"
		+ "<option value='140-149 Sq.Yard'>140-149 Sq.Yard</option>"
		+ "<option value='150-159 Sq.Yard'>150-159 Sq.Yard</option>"
		+ "<option value='160-169 Sq.Yard'>160-169 Sq.Yard</option>"
		+ "<option value='170-179 Sq.Yard'>170-179 Sq.Yard</option>"
		+ "<option value='2 Bed Premium'>2 Bed Premium</option>"
		+ "<option value='2 Bed Premium'>2 Bed Premium</option>"
		+ "<option value='2 Bed Premium'>2 Bed Premium</option>"
		+ "<option value='2 BHK Premium'>2 BHK Premium</option>"
		+ "<option value='2 BHK C'>2 BHK C</option>"
		+ "<option value='2 BHK Luxe'>2 BHK Luxe</option>"
		
		+ "<option value='2 BHK S'>2 BHK S</option>"
		+ "<option value='A'>A</option>"
		+ "<option value='B'>B</option>"
		+ "<option value='Cottage'>Cottage</option>"
		+ "<option value='Bungalow'>Bungalow</option>"
		+ "<option value='D'>D</option>"
		+ "<option value='Estate'>Estate</option>"
		+ "<option value='Mansion'>Mansion</option>"
		+ "<option value='NA'>NA</option>"
		+ "<option value='Nest'>Nest</option>"
		+ "<option value='Others'>Others</option>"
		+ "<option value='Plot_104-119'>Plot_104-119</option>"
		+ "<option value='Plot_120-129'>Plot_120-129</option>"
		+ "<option value='Plot_130-139'>Plot_130-139</option>"
		
		+ "<option value='Plot_140-149'>Plot_140-149</option>"
		+ "<option value='Plot_150-159'>Plot_150-159</option>"
		+ "<option value='Plot_160-169'>Plot_160-169</option>"
		+ "<option value='Plot_170-179'>Plot_170-179</option>"
		+ "<option value='2 BHK'>2 BHK</option>"
		+ "<option value='2.5 BHK'>2.5 BHK</option>"
		+ "<option value='3 BHK'>3 BHK</option>"
		+ "<option value='3.5 BHK'>3.5 BHK</option>"
		+ "<option value='4 BHK +'>4 BHK +</option>"
		+ "<option value='4 BHK+'>4 BHK+</option>"
		+ "<option value='PLOT'>PLOT</option>"
		+ "<option value='Retail'>Retail</option>"
		+ "<option value='STUDIO'>STUDIO</option>"
		+ "<option value='Studio Apartment'>Studio Apartment</option>"
		+ "<option value='Villa'>Villa</option>"
		
		$(".pcMultiselectTower").html("");
		$('.pcMultiselectTower').multiselect('destroy');
		$(".pcMultiselectTower").append(optionTower);
		$('.pcMultiselectTower').multiselect({
			maxHeight: '200',
			allSelectedText: 'All',
			includeSelectAllOption: true,
			enableFiltering: true,
			enableCaseInsensitiveFiltering: true,
			buttonWidth: '100%'
		});
		
		$('.pcMultiselectTower').multiselect('refresh');	
		
	/*	
	$.get(pageContext+"getunittype", {
		"project_code" : $('#projectid').val(),
		"tower_code": "All",
		"floor_code":""
	}, function(data) {
		var html="";
		if(data!=null){
			
			for(var i=0;i<data.length;i++){
				optionTower = optionTower+"<option value="+data[i].propstrength__unit_type__c+">"+data[i].propstrength__unit_type__c+"</option>";
			}
		}
	}).done(function() {
		$(".pcMultiselectTower").html("");
		$('.pcMultiselectTower').multiselect('destroy');
		$(".pcMultiselectTower").append(optionTower);
		$('.pcMultiselectTower').multiselect({
			maxHeight: '200',
			allSelectedText: 'All',
			includeSelectAllOption: true,
			enableFiltering: true,
			enableCaseInsensitiveFiltering: true,
			buttonWidth: '100%'
		});
		
		$('.pcMultiselectTower').multiselect('refresh');
	}); 	*/
}



function fetchPromoCode () {
	
	$.post(pageContext+"fetchPromoCode",{"project_sfdc_id":$('#projectid').val()},function(data){
		
		$('#fetchPromoCode tbody').empty();
		
		var html = '';
		var obj =JSON.parse(data);
		var buttons = "";
		var functionName = "";
		
		if(obj!=null && obj.length > 0){
			 for(i = 0; i< obj.length; i++){
				
				 if (obj[i].status == "Enabled") {
					 functionName = "updatePromoCode(this, 'INACTIVE')";
					 buttons = '<button class="btn btn-primary blue_btn inactivePCBtn" onclick="'+functionName+'">Inactive</button>';
				 } else if (obj[i].status == "Disabled") {
					 functionName = "updatePromoCode(this, 'ACTIVE')";
					 buttons = '<button class="btn btn-primary blue_btn activePCBtn" onclick="'+functionName+'">Active</button>'; 
				 }
				 
				 html += 	'<tr class="promoCodeRow">'
								+ '<td class="pcName">'+obj[i].PromoCode+'</td>' 
								+ '<td>'+obj[i].Project+'</td>' 
								+ '<td>'+obj[i].field_coupon_discount_upto+'</td>' 
								+ '<td>'+obj[i].field_discount_on_typology+'</td>' 
								+ '<td>'+obj[i].field_discount_percentage+'</td>'
								+ '<td>'+obj[i].field_discount_type+'</td>' 
								+ '<td>'+obj[i].field_end_date+'</td>'
								+ '<td>'+obj[i].field_flat_discount_amount+'</td>'
								+ '<td>'+obj[i].field_promocode_max_use_count+'</td>'
								+ '<td>'+obj[i].field_promocode_use_count+'</td>'
								+ '<td>'+obj[i].status+'</td>'
								+ '<td>'+buttons+'</td>'
							"</tr>";
				  
			 }
		} else {
			html += '<tr><td colspan="12" style="text-align:center;">No records found</td></tr>';
		}
		$("#fetchPromoCode tbody").append(html);
	}).done(function(obj){
		
	});	
}

function updatePromoCode (e, pcStatus) {
	var status = "";
	
	if (pcStatus == "ACTIVE") {
		status = 1;
	} else if (pcStatus == "INACTIVE") {
		status = 0;
	}
	
	$(e).closest('.EOIDtlRow').find(".carparkListEOI").find("option:gt(0)").remove();
	
	$.post(pageContext+"createPromoCode",{"project_sfdc_id":$('#projectid').val(), 
		"promo_code": $(e).closest('.promoCodeRow').find('.pcName').text(), 
		"promo_code_discount_type":"", 
		"flat_discount_amount":"", 
		"discount_percentage":"", 
		"expiry_date":"", 
		"coupon_discount_upto":"", 
		"promocode_max_use_count":"", 
		"typology":"", 
		"status":status },function(data){
		
		 
	}).done(function(obj){
		fetchPromoCode();
	});	
}