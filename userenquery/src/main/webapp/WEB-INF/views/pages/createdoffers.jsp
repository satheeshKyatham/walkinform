<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Created Offers List</title>
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css' />" type="text/css" />
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
     
<link rel="stylesheet" href="<c:url value='/resources/css/inner.css' />">
<link rel="stylesheet" href="<c:url value='/resources/css/style.css' />">

<link rel="stylesheet" href="<c:url value='/resources/css/font-awesome.css' />">

</head>
<body>

<input id="pageContext" value="${pageContext.request.contextPath}" style="display:none;"/>

<!-- <h4>Created Offers List</h4> -->
<div class="commonLoad" id="mainPageLoad" style="display: none;"></div>
<table class="table table-bordered" id="createdOfferTable">
		<thead>
			<tr>
				<th>Enquiry No</th>
				<th>Contact Name</th>
				<th>Payment Plan</th>
				<th>Offer Name</th>
				<th>Scheme Name</th>
				<th>Scheme Rate</th>
				<th>Balance Amount</th>
				<th>Description</th>
				<th>Final Amount</th>
				<th>Cost Sheet</th>
				<th>Application</th>
			</tr>
		</thead>
		<tbody>
		<%-- <c:forEach items="${createdoffers}" var="lists">
		<tr><td>${lists.scheme_name}</td><td>${lists.car_park_type}</td>
		<td>${lists.paymentplan_sfid}</td>
		<td>${lists.enquiry_sfid}</td>
		<td>${lists.offer_sfid}</td>
		</tr>
	</c:forEach> --%>
	</tbody>
</table>








<!-- Offer Application Form -->
<input id="appProjectNameOffer" style="display:none;">

<div id="printApplicationFormOffer" class="col-md-8" style="border: 1px dotted #ccc; margin: 0 auto; float: none; margin-top: 40px; padding-top: 15px;"> <!-- style="display:none;" -->
	
	
	<table class="table table-bordered"  style="margin-bottom:15px;">
		<tbody>
			<tr>
				<td>Offer Name.: <span id="offerNameOffer"></span></td> 
				<td>Contact ID: <span id="contactidOffer"></span></td> 
				<td>Date: <span id="bookingDateOffer"></span></td> 
			</tr>
		</tbody>
	</table>
	
	<!-- <table class="table table-bordered"  style="margin-bottom:15px;">
		<tbody>
			<tr>
				<td>Sales order No.: <span id="salesorderNo"></span></td> 
				<td>Customer ID: <span id="SAPCustomerCode"></span></td> 
				<td>Date: <span id="bookingDate"></span></td> 
			</tr>
		</tbody>
	</table> -->
	
	<table class="table table-bordered"  style="margin-bottom:15px;">
		<tbody>
			<tr>
				<td>To, </td>  <td></td> 
			</tr>
			<tr>
				<td>
					<div id="jvNameOffer" style="padding-bottom:10px;"></div>
					<div style="padding-bottom:10px;">("Company/Developer"),</div>
					<div id="jvStreetOffer" style="padding-bottom:10px;"></div>
					<div id="jvCityStateCountryOffer"></div>
				</td> 
				<td>
				
				</td>
			</tr>
			
			<tr>
				<td colspan="2" id="applicantPhotosOffer">
					
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					I/We, the Applicant/s mentioned below, request that I/we be allotted a residential flat / unit / apartment / commercial unit in terms of Annexure A in this Application <span id="projectPhasOffer"></span> in "<span id="projectNameLocationOffer"></span>", <!-- project situated at ______________________ ("Project") --> details whereof are as under.
				</td> 
			</tr>
		</tbody>
	</table>	
	<div style="page-break-before: always"></div>
	
	<div id="inIndividualCaseOffer">
		
	</div>
	
	<div id="inComapnyCaseOffer">
		
	</div>
	
	
	
	
	
	<div  style="padding-bottom:10px;"><b>2. MODE OF BOOKING</b></div>
	<table class="table table-bordered" id="modeOfBookingOffer" style="margin-bottom:15px;">		
		<tbody>	
			
		</tbody>
	</table>
	
	<div  style="padding-bottom:10px;"><b>3. PURPOSE OF PURCHASE:</b> <span id="selfUseInvestmentOffer"></span></div>
	
	<div style="page-break-before: always"></div>
	<div style="padding-bottom:10px;"><b>4. DETAILS OF FLAT/UNIT ("Flat"/"Unit") SALE CONSIDERATION AND ESTIMATED OTHER CHARGES:</b></div>
	<table class="table table-bordered"  style="margin-bottom:15px;">
		<tr>
			<th rowspan="11" style="vertical-align: top; width:10px;">1</th>
			<th colspan="4">Flat/Unit Details</th>
		</tr>
  		<tr>
		    <th style="width:10px;">A</th>
		    <td>Details of Flat/Unit</td>
		    <td colspan="2">Flat/Unit No.: <span id="flatNoOffer"></span>, <span id="floorNameOffer"></span>, Tower: <span id="towerNameOffer"></span></td>
  		</tr>
  		<tr>
		    <th>B</th>
		    <td>Location of Building </td>
		    <td colspan="2">As shown in the Plan attached as <strong>Annexure B</strong> hereto.	</td>
  		</tr>
  		<tr>
		    <th rowspan="3">C</th>
		    <td rowspan="3">Area (in square meters only)</td>
		    <td>Carpet Area*</td>
		    <td id="carpetAreaOffer"></td>
  		</tr>
  		<tr>
		    <td>Exclusive Areas**</td>
		    <td id="exclusiveAreasOffer"></td>
  		</tr>
  		<tr>
		    <th>Total Area#</th>
		    <td id="totalAreaOffer"></td>
  		</tr>
   		<tr>
		    <th rowspan="2">D</th>
		    <td rowspan="2">Car Park Space(s):</td>
  		</tr>
  		
  		<tr>
		    <td colspan="2" >As per Annexure B-I</td>
  		</tr>
  		
  		<tr>
		    <th>E</th>
		    <td>Specification(s) of Flat/Unit</td>
		    <td colspan="2">As per <b>Annexure C.</b>
				<div style="padding-top:10px;"><b>[Note:- In case any fitting and fixtures are described of a particular brand then to put a qualification of price range in case such particular brand is not provided/is not available.]</b></div>
			</td>
  		</tr>
   		<tr>
		    <th>F</th>
		    <td>Common Areas</td>
		    <td colspan="2">As per <b>Annexure D.</b></td>
  		</tr>
  		<tr>
		    <th>G</th>
		    <td>Facilities</td>
		    <td colspan="2">As per <b>Annexure E.</b></td>
  		</tr>
  		<tr>
		    <th rowspan="9" style="vertical-align: top;">2</th>
		    <th colspan="4">Sale Consideration</th>
  		</tr>
   		<tr>
		    <th>A</th>
		    <td>Flat/Unit Carpet Area</td>
		    <td colspan="2">Rs.<span id="carpetAreaCostOffer"></span>/-</td>
  		</tr>
  		<tr>
		    <th>B</th>
		    <td>Exclusive Areas</td>
		    <td colspan="2">Rs. <span id="exclusiveAreasCostOffer"></span>/-</td>
  		</tr>
  		<tr>
		    <th>C</th>
		    <td>Covered Car Parking Space(s)</td>
		    <td colspan="2">Not Applicable</td>
  		</tr>
  		<tr>
		    <th>D</th>
		    <td>Proportionate Common Areas Charges including Club house development Charges calculated on the Carpet Area</td>
		    <td colspan="2">Not Applicable</td>
  		</tr>
  		<tr>
		    <th>E</th>
		    <td>Facilities</td>
		    <td colspan="2">Not Applicable</td>
  		</tr>
  		
  		<tr>
		    <th>F</th>
		    <td>Other Charges</td>
		    <td colspan="2" id="otherChargesOffer"></td>
  		</tr>
  		
  		<tr>
		    <th colspan="2" style="text-align:center;">Total (Rounded off)</th>
		    <th colspan="2" style="height:60px;">
		    	Rs. <span id="totalSaleConsiderationOffer"></span>/- 
		    	<div style="line-height: 24px; padding-top:10px;">
		    		<b style="word-break: break-all;">
		    			(Rupees <span id="totalScInWordOffer"></span> Only)
		    		</b>
		    	</div>
		    </th>
  		</tr>
  		<tr>
		    <th>G</th>
		    <td>Payment Schedule</td>
		    <td colspan="2">As per <b>Annexure F.</b></td>
		</tr>
  		<tr>
		    <th>3</th>
		    <th colspan="2">Estimated and Tentative Other Charges</th>
		    <td colspan="2">As per <b>Annexure F.</b></td>
  		</tr>
  		<tr>
  			<td colspan="5">
  				*"Carpet Area" shall mean net usable floor area of the Flat/Unit, excluding the area covered by the external walls, areas under services shafts, exclusive balcony or verandah area appurtenant to the Flat/Unit for exclusive use of the Applicant/s and exclusive open terrace area appurtenant to the Flat/Unit for exclusive use of the Applicant, but includes the area covered by the internal partition walls of the Flat/Unit. **"Exclusive Areas" shall mean exclusive balcony and/or exclusive open terrace and/or exclusive verandah appurtenant to the net usable floor area of the Flat/Unit and meant for exclusive use of the Applicant/s and other areas appurtenant to the Flat/Unit for exclusive use of the Applicant/s. #"Total Area" shall mean the Carpet Area and Exclusive Areas collectively.   
			</td>
  		</tr>
	</table>
	
	<table class="table table-bordered" style="margin-bottom:15px;">		
		<tbody>	
			<tr>
				<th rowspan="4">5.</th>
				<td colspan="2">In addition to the sale consideration, estimated other charges, I/we agree and undertake to pay the following amounts as and when demanded by the Developer, towards:</td>
			</tr>
			
			<tr>
				<td style="width:10px;">a)</td>
				<td>
					All applicable and future taxes, levies, duties, cesses, charges including but not limited to goods and services tax (GST)  and/or TDS, land under construction tax, property tax, External Development Charges (EDC), Infrastructure Development Charges (IDC), lease rent, and/or all other direct/indirect taxes/duties, impositions levied by the Central and/or State Government and/or any local, public or statutory authorities/ bodies ("<strong>Statutory Charges</strong>") in respect of the Flat/Unit and/or the transaction contemplated herein and/or in respect of the sale consideration and/or the other amounts payable by me. The quantum of such taxes, levies, duties, cesses, charges as decided/quantified by the Developer shall be binding on me. 
				</td>
			</tr>
			
			<tr>
				<td>b)</td>
				<td>
					Further, since timely deduction of TDS and payment of the same to the authorities is Applicant's responsibility under Section 194-IA of the Income Tax Act, 1961, the Applicant/s may hereby authorize the Developer to initiate the process of paying TDS amounts to the authorities, by signing TDS Authorization Letter as mentioned in Annexure G, which stipulates the broad terms and conditions of the same. 
				</td>
			</tr>
			
			<tr>
				<td>c)</td>
				<td>
					All costs, charges and expenses including but not limited to stamp duty, registration charges and/or incidental charges in connection with the any of the documents to be executed for the sale of the Flat/Unit including on this Application Form and/or the Allotment Letter and/or the Agreement for Sale (as defined below) as per the provisions of applicable laws, shall be borne and paid by the Applicant/s as and when demanded by the Developer.
					<div style="font-size:10px; padding-top:10px;">
						For the purpose of this Application form, "GST" means and includes any tax imposed on the supply of goods or services or both under GST Law. "GST Law" shall mean and include the Integrated Goods & Service Tax Act, GST (Compensation to the States for Loss of Revenue) Act, Central Goods & Services Tax Act and State Goods & Services Tax Act / UTGST, and all related ancillary legislations, rules, notifications, circulars, statutory orders etc. "Cess" shall mean and include any applicable cess, existing or future on the supply of goods or services or both under GST Law.  
						"Applicable Law" shall mean and include any applicable Central, State or local laws, statutes, ordinances, rules, regulations, codes, bye-laws etc. including amendments/ modification thereto, any government notifications, circulars, office orders, directives, guidelines, policies etc. or any government order or direction, judgment, decree or order of a judicial or a quasi-judicial authority whether in effect on the date of this Application Form or thereafter
					</div> 
				</td>
			</tr>
			
			<tr>
				<th>6.</th>
				<td colspan="2">
					I/We further confirm that I/we am submitting this Application Form after understanding the entire manner and scope of development to be undertaken in the Project, including the details of the Carpet Area, Exclusive Area, Common Areas and Facilities being provided, without relying on any of the publicity materials / advertisements published in any form or any channel by the Developer/DM or any third party in the past. I/We am/are aware and I/we confirm that the advisements / publicity material released in the past does not provide any warranty and may not be providing complete details / disclosures as may be required under the Real Estate (Regulation and Development) Act, 2016 ("RERA") read with the applicable Rules framed thereunder and I/we am/are not relying on the same for my/our decision to purchase the Flat/ Unit. I/We further confirm and undertake to not make any claim against the Developer/DM or seek cancellation of the Application Form / allotment or refund of the monies paid by me/us by reason of anything contained in the publicity material / advertisement published in any form or in any channel, by the Developer or any third party in the past. I/We acknowledge that I/we have not relied upon the interiors depicted / illustrated in marketing collaterals/ the sample flat / mock flat and its colour, texture, the fitting(s) / fixture(s) or any installations depicted therein and understand that the same is shown only as a suggested layout/ furnishing options without any obligation on the part of the Developer/DM to provide the same.
				</td>
			</tr>
			
			<tr>
				<th>7.</th>
				<td colspan="2">
					I/We acknowledge, agree and undertake that I/we shall neither hold the Developer/DM or any of its affiliates liable/ responsible for any representation/ commitment/offer made by any third party to me/us nor make any claims/demands on the Developer/DM or any of its affiliates with respect thereto.
				</td>
			</tr>
			
			<tr>
				<th>8.</th>
				<td colspan="2">
					Save and except the information / disclosure contained herein and on RERA website, I/we confirm and undertake not to make any claim against the Developer/DM or seek cancellation of this Application Form / allotment letter/ Agreement for Sale or refund of the monies paid by me by reason of anything contained in other information / disclosure not forming part of this Application Form / allotment letter/ Agreement for Sale or the RERA website.
				</td>
			</tr>
			
			<tr>
				<th>9.</th>
				<td colspan="2">
					I/We have fully read and understood the Terms and Conditions attached hereto as <strong>Annexure A</strong> which contains broad terms, conditions, representations, covenants, etc. as well as the terms of the Agreement for Sale uploaded on RERA website and do hereby agree, undertake and covenant to abide and be bound by them and also by the area, sale consideration, estimated other charges and payment terms as set out herein. The Terms and Conditions as mentioned in Annexure A forms an integral part of this Application Form and shall always be read together with this Application Form and be construed accordingly.
				</td>
			</tr>
			
			<tr>
				<th>10.</th>
				<td colspan="2">
					I/We have taken the decision to purchase the Flat/Unit in the Project out of my/our own free will after giving careful consideration to the nature and scope of the entire development explained to me/us in person including the disclosures contained herein as well as made available on RERA website and remitted the amounts payable thereof fully conscious of my rights, liabilities and obligations. All the above information provided by me/us is true and nothing has been concealed or suppressed. I/We further undertake to inform the Developer promptly of any changes to the above information and particulars furnished by me/us.
				</td>
			</tr>
			
			<tr>
				<th>11.</th>
				<td colspan="2">
					
					<div>
						I/We hereby confirm that I/we have made the payment towards the booking amount, details whereof are as under: 
					</div>	
					
					<table class="table table-bordered" id="paymentDtltableOffer" style="margin-top:15px;">		
						<tbody>
							<tr>
								<th>Payment Type</th>
								<th>Bank Name</th>
								<th style="width:150px;">Amount</th>
								<th style="width:80px;">Branch</th>
								<th>Transaction ID (Cheque)</th>
								<!-- <th>Transaction ID (Swipe / NEFT)</th> -->
							</tr>
						</tbody>
					</table>
					
					<div style="padding-top:15px;">
						I/We hereby understand that any payment shall be subject to realization and/or actual credit in Developer's bank account. 
					</div>	
				</td>
			</tr>
			
		</tbody>
	</table>
	
</div>
<!-- END Offer Application Form -->





</body>
</html>