var offer = '';
offer = offer+
'<table class="table table-bordered"  style="margin-bottom:15px;">'
+'<tbody>'
+'<tr>'
+'<td>Offer Name.: <span id="offerNameOffer"></span></td>' 
+'<td>Contact ID: <span id="contactidOffer"></span></td>'
+'<td>Date: <span id="bookingDateOffer"></span></td>' 
+'</tr>'
+'</tbody>'
+'</table>'
+'<table class="table table-bordered"  style="margin-bottom:15px;">'
+'<tbody>'
+'<tr>'
+'<td>To, </td>  <td></td>' 
+'</tr>'
+'<tr>'
+'<td>'
+'<div id="jvNameOffer" style="padding-bottom:10px;"></div>'
+'<div style="padding-bottom:10px;">("Company/Developer"),</div>'
+'<div id="jvStreetOffer" style="padding-bottom:10px;"></div>'
+'<div id="jvCityStateCountryOffer"></div>'
+'</td>' 
+'<td>'
+'</td>'
+'</tr>'
+'<tr>'
+'<td colspan="2" id="applicantPhotosOffer">'
+'</td>'
+'</tr>'
+'<tr>'
+'<td colspan="2">'
+'We, the Applicant/s mentioned below, request that we be allotted a residential flat / unit / apartment / commercial unit in terms of Annexure A in this Application <span id="projectPhasOffer"></span> in "<span id="projectNameLocationOffer"></span>", <!-- project situated at ______________________ ("Project") --> details whereof are as under.'
+'</td> '
+'</tr>'
+'</tbody>'
+'</table>'
;

$(document).ready(function(){
	$("#templateContent").html(offerTemplate);
});

function getOfferTemplate(){
	return offer;
}