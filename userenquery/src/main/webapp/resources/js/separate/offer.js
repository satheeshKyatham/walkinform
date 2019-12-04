

//alert(pageContext);
function getCreatedOfferList () {
	 $('#mainPageLoad').show();	
	 $("#createdOfferTable tbody").empty();
	//alert ("Test Call 123 456");
	 var contextPath = $("#pageContext").val();
	 var csPath = '';
	 
	$.get("getOfferList",{"userid":$('#userid').val(),"projectid":""},function(data){				 
		
	}).done(function(data){
		//alert("Data:-"+data);
		var obj =JSON.stringify(data);
		var obj1 =JSON.parse(obj);
		//alert(obj1.length);
		if(obj1!=null)
			{
				for(var i=0;i<obj1.length;i++){
					var schemename = "";
					if(obj1[i].scheme_name=="Select Scheme")
						{
						schemename="";
						}
					else
						schemename=obj1[i].scheme_name;
					
					csPath = contextPath+'/Costsheet?name='+obj1[i].costsheet_path + '&from=ofrList';
					
					var fileIcon = '';
					
					if (obj1[i].costsheet_path != null) {
						fileIcon = '<i class="fa fa-file"></i>';
					} else {
						fileIcon = '';
					}
					
					var val = $("<tr><td>"+obj1[i].enquiryname+"</td><td>"+obj1[i].contactname+"</td><td>"+obj1[i].payment_plan+"</td><td>"+obj1[i].offername+"</td><td>"+schemename+"</td><td>"+obj1[i].scheme_rate+"</td><td>"+obj1[i].amount+"</td><td>"+obj1[i].description+"</td><td>"+obj1[i].cs_final_amount+"</td> <td>  <a target='_blank' href='"+csPath+"'>"+fileIcon+"</a></td> <td><button class='btn btnDefaultBlue btn-default' onclick='getofferApplicantDetails(this, \""+obj1[i].offer_sfid+"\", \""+obj1[i].enquiry_sfid+"\",  \""+obj1[i].contact_sfid+"\",  \""+obj1[i].offername+"\",  \""+obj1[i].enquiryname+"\", \""+obj1[i].propstrength__property__c+"\", \""+i+"\", \"offer\")'><i class='fa fa-print printficon'></i></button></td></tr>");
					$("#createdOfferTable tbody").append(val);
				}
			}
		
		$('#mainPageLoad').hide();	
		
	});	
}

function createdOfferProject()
{
	alert("Hello");
	}

