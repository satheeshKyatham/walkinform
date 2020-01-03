<input id="pageContext" value="${pageContext.request.contextPath}" style="display:none;"/>

<!-- <h4>Created Offers List</h4> -->
<div class="commonLoad" id="mainPageLoad" style="display: none;"></div>
<table class="table table-bordered" id="applicationTable">
	<thead>
		<tr>
			<th>Created Date</th>
			<th>Primary applicant</th>
			<th>Source</th>
			<th>Broker Name</th>
			<th>Tower</th>
			<th>Property Name</th>
			<th>Flat Type</th>	
			<th>App. Form</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>


<input id="appProjectName" style="display:none;">

<div id="printApplicationForm" class="col-md-8" style="border: 1px dotted #ccc; margin: 0 auto; float: none; margin-top: 40px; padding-top: 15px;"> <!-- style="display:none;" -->
	
	
	<%-- <table class="" style="margin-top:0px; margin-bottom:15px; text-align:center; font-size:16px;">
		<tbody>
			<tr>
				<th style="text-transform: uppercase !important; height:100px;  color:#666666;   vertical-align: top; font-size:20px !important; text-align:left; border: 0; border-color: #ffffff; border-left-width: 0px; border-left-style: solid;  border-top-width: 0px; border-top-style: solid; border-collapse: collapse; border-spacing: 1px; ">
					<img style="width: 210px; height: 55px;" src="<c:url value='/resources/images/gplLogo.jpg' />"/>
				</th>
				<th id="appProjectName" style="text-transform: uppercase !important; height:100px;  color:#666666;   vertical-align: top; font-size:20px !important; text-align:right; border: 0; border-color: #ffffff; border-left-width: 0px; border-left-style: solid;  border-top-width: 0px; border-top-style: solid; border-collapse: collapse; border-spacing: 1px; ">
					
				</th>
			</tr>
			
			<tr>
				<th colspan="2" style="border: 0; border-color: #ffffff; border-left-width: 0px; border-left-style: solid;  border-top-width: 0px; border-top-style: solid; border-collapse: collapse; border-spacing: 1px; ">
					APPLICATION FORM
				</th>
			</tr>
		</tbody>
	</table> --%>
	
	
	
	
	
	
	
	
	
	
	<table class="table table-bordered"  style="margin-bottom:15px;">
		<tbody>
			<tr>
				<td>Sales order No.: <span id="salesorderNo"></span></td> 
				<td>Customer ID: <span id="SAPCustomerCode"></span></td> 
				<td>Date: <span id="bookingDate"></span></td> 
			</tr>
		</tbody>
	</table>
	
		
	<table class="table table-bordered"  style="margin-bottom:15px;">
		<tbody>
			<tr>
				<td>To, </td>  <td></td> 
			</tr>
			<tr>
				<td>
					<div id="jvName" style="padding-bottom:10px;"></div>
					<div style="padding-bottom:10px;">("Company/Developer"),</div>
					<div id="jvStreet" style="padding-bottom:10px;"></div>
					<div id="jvCityStateCountry"></div>
				</td> 
				<td>
				
				</td>
			</tr>
			
			<tr>
				<td colspan="2" id="applicantPhotos">
					
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					We, the Applicant/s mentioned below, request that we be allotted a residential flat / unit / apartment / commercial unit in terms of Annexure A in this Application <span id="projectPhas"></span> in "<span id="projectNameLocation"></span>", <!-- project situated at ______________________ ("Project") --> details whereof are as under.
				</td> 
			</tr>
		</tbody>
	</table>	
	<div style="page-break-before: always"></div>
	<div id="inIndividualCase">
		<!-- <div style="padding-bottom:10px;"> <b>1. APPLICANT/S DETAILS</b> </div>
		<table class="table table-bordered" style="margin-bottom:15px;" id="applicantDtl">
			<tbody>
				
			</tbody>
		</table> -->	
	</div>
	
	<div id="inComapnyCase">
		<!-- <table class="table table-bordered" style="margin-bottom:15px;">
			<tbody>
				<tr>
					<th colspan="2"  style="text-align:center;"><b>In case of Company/ LLP/ HUF/ Partnership Firm</b></th>
				</tr>
				<tr>
					<td>Name</td>
					<td id="niName"></td>
				</tr>
				<tr>
					<td>Date of Incorporation/ Formation</td>
					<td id="niDOB"></td>
				</tr>
				<tr>
					<td>PAN/CIN</td>
					<td id="niPAN"></td>
				</tr>
				<tr>
					<td>Registered Office Address</td>
					<td></td>
				</tr>
				<tr>
					<td>Name of Authorized Representative/ Partner /Karta</td>
					<td></td>
				</tr>
				<tr>
					<td colspan="2" style="font-size:10px;">
						Note: If Applicant/s is company, partnership firm, limited liability partnership, the following incorporation documents are required to be submitted along with this Application Form: (a) Certificate of Incorporation/Registration Certificate for the applicable entity (b) Memorandum of Association (c) Articles of Association (d) Partnership Deed (e) Limited Liability Partnership Agreement (f) Board/Partner's Resolution authorizing this purchase. Please affix the official stamp of the respective Company/LLP/Trust/Partnership firm/HUF/Society as may be applicable.
					</td>
				</tr>
			</tbody>
		</table> -->
	</div>
	
	
	
	
	
	<div  style="padding-bottom:10px;"><b>2. MODE OF BOOKING</b></div>
	<table class="table table-bordered" id="modeOfBooking" style="margin-bottom:15px;">		
		<tbody>	
			
		</tbody>
	</table>
	
	<!-- <div  style="padding-bottom:10px;"><b>3. FINANCE FROM BANK / FINANCIAL INSTITUTION:</b></div>
	<table class="table table-bordered"  style="margin-bottom:15px;">		
		<tbody>	
			<tr>
				<td>Yes / No.</td>
				<td>If yes, Preferred Financial Institution: ______________________________________,</td>
			</tr>
		</tbody>
	</table>	 -->

	<!-- <div  style="padding-bottom:10px;"><b>4. MODE OF PAYMENT:</b></div>
	<table class="table table-bordered"  style="margin-bottom:15px;">		
		<tbody>	
			<tr>
				<td>Cheque</td>
				<td>Draft / P.O.</td>
				<td>RTGS/NEFT</td>
			</tr>
		</tbody>
	</table> -->

	<!-- <div  style="padding-bottom:10px;"><b>5. I HEARD ABOUT YOU FROM? (Please tick the applicable):</b></div>
	<table class="table table-bordered"  style="margin-bottom:15px;">		
		<tbody>	
			<tr>
				<td>Newspaper</td>
				<td>Hoarding</td>
				<td>Television Advertisement</td>
				<td>Internet Advertisement</td>
			</tr>
			<tr>
				<td>Internet Portals</td>
				<td>Corporate offer (please specify)</td>
				<td>Emailer</td>
				<td>Broker/Channel Partner (please specify)</td>
			</tr>
			<tr>
				<td>Referred by Bank / HFI (please specify)</td>
				<td>Godrej Properties Website / GPL Facebook page / GPL iPad application</td>
				<td colspan="2">Other: _________________</td>
			</tr>
			<tr>
				<td colspan="4">Details of Newspaper, Website, Hoarding etc. _________________________,</td>
			</tr>
		</tbody>
	</table> -->
	
	<div  style="padding-bottom:10px;"><b>3. PURPOSE OF PURCHASE:</b> <span id="selfUseInvestment"></span></div>
	
	<div  style="padding-bottom:10px;"><b>4. FINANCE FROM BANK/FINANCIAL INSTITUTION:</b> <span>YES / NO</span></div>
	<div  style="padding-bottom:12px; padding-left:10px;"> IF <b>YES</b>, PREFERRED FINANCIAL INSTITUTION: _______________________________ </div>
	
	<!-- <table class="table table-bordered"  style="margin-bottom:15px;">		
		<tbody>	
			<tr>
				<td>Investment</td>
				<td>Self-Use</td>
			</tr>
		</tbody>
	</table> -->
	<div style="page-break-before: always"></div>
	<div style="padding-bottom:10px;"><b>5. DETAILS OF FLAT/UNIT ("Flat"/"Unit") SALE CONSIDERATION AND ESTIMATED OTHER CHARGES:</b></div>
	<table class="table table-bordered"  style="margin-bottom:15px;">
		<tr>
			<th rowspan="11" style="vertical-align: top; width:10px;">1</th>
			<th colspan="4">Flat/Unit Details</th>
		</tr>
  		<tr>
		    <th style="width:10px;">A</th>
		    <td>Details of Flat/Unit</td>
		    <td colspan="2">Flat/Unit No.: <span id="flatNo"></span>, <span id="floorName"></span>, Tower: <span id="towerName"></span></td>
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
		    <td id="carpetArea"></td>
  		</tr>
  		<tr>
		    <td>Exclusive Areas**</td>
		    <td id="exclusiveAreas"></td>
  		</tr>
  		<tr>
		    <th>Total Area#</th>
		    <td id="totalArea"></td>
  		</tr>
   		<tr>
		    <th rowspan="2">D</th>
		    <td rowspan="2">Car Park Space(s):</td>
		    <!-- <td style="height:25px;"></td>
		    <td style="height:25px;"></td> -->
  		</tr>
  		<!-- <tr>
		    <td>Independent</td>
		    <td>Dependent</td>
  		</tr> -->
  		<tr>
		    <td colspan="2" >As per Annexure B-I</td>
  		</tr>
  		
  		
  		<!-- <tr>
   			<td colspan="2">[Please mention the number of covered car park(s). Mention '0' where not applicable.]</td>
  		</tr> -->
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
		    <td colspan="2">Rs.<span id="carpetAreaCost"></span>/-</td>
  		</tr>
  		<tr>
		    <th>B</th>
		    <td>Exclusive Areas</td>
		    <td colspan="2">Rs. <span id="exclusiveAreasCost"></span>/-</td>
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
		    <td colspan="2" id="otherCharges"></td>
  		</tr>
  		
  		<tr>
		    <th colspan="2" style="text-align:center;">Total (Rounded off)</th>
		    <th colspan="2" style="height:60px;">
		    	Rs. <span id="totalSaleConsideration"></span>/- 
		    	<div style="line-height: 24px; padding-top:10px;">
		    		<b style="word-break: break-all;">
		    			(Rupees <span id="totalScInWord"></span> Only)
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
				<th rowspan="4">6.</th>
				<td colspan="2">In addition to the sale consideration, estimated other charges, we agree and undertake to pay the following amounts as and when demanded by the Developer, towards:</td>
			</tr>
			
			<tr>
				<td style="width:10px;">a)</td>
				<td>
					All applicable and future taxes, levies, duties, cesses, charges including but not limited to goods and services tax (GST)  and/or TDS, land under construction tax as demanded by the authorities, property tax, External Development Charges (EDC), Infrastructure Development Charges (IDC), lease rent, and/or all other direct/indirect taxes/duties, impositions levied by the Central and/or State Government and/or any local, public or statutory authorities/ bodies ("<strong>Statutory Charges/ Other Charges</strong>") in respect of the Flat/Unit and/or the transaction contemplated herein and/or in respect of the sale consideration and/or the other amounts payable by me. The quantum of such taxes, levies, duties, cesses, charges as decided/quantified by the Developer shall be binding on me. 
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
				<th>7.</th>
				<td colspan="2">
					I/We further confirm that I/we am submitting this Application Form after understanding the entire manner and scope of development to be undertaken in the Project, including the details of the Carpet Area, Exclusive Area, Common Areas and Facilities being provided, without relying on any of the publicity materials / advertisements published in any form or any channel by the Developer/DM or any third party in the past. I/We am/are aware and I/we confirm that the advisements / publicity material released in the past does not provide any warranty and may not be providing complete details / disclosures as may be required under the Real Estate (Regulation and Development) Act, 2016 ("RERA") read with the applicable Rules framed thereunder and I/we am/are not relying on the same for my/our decision to purchase the Flat/ Unit. I/We further confirm and undertake to not make any claim against the Developer/DM or seek cancellation of the Application Form / allotment or refund of the monies paid by me/us by reason of anything contained in the publicity material / advertisement published in any form or in any channel, by the Developer or any third party in the past. I/We acknowledge that I/we have not relied upon the interiors depicted / illustrated in marketing collaterals/ the sample flat / mock flat and its colour, texture, the fitting(s) / fixture(s) or any installations depicted therein and understand that the same is shown only as a suggested layout/ furnishing options without any obligation on the part of the Developer/DM to provide the same.
				</td>
			</tr>
			
			<tr>
				<th>8.</th>
				<td colspan="2">
					I/We acknowledge, agree and undertake that I/we shall neither hold the Developer/DM or any of its affiliates liable/ responsible for any representation/ commitment/offer made by any third party to me/us nor make any claims/demands on the Developer/DM or any of its affiliates with respect thereto.
				</td>
			</tr>
			
			<tr>
				<th>9.</th>
				<td colspan="2">
					Save and except the information / disclosure contained herein and on RERA website, I/we confirm and undertake not to make any claim against the Developer/DM or seek cancellation of this Application Form / allotment letter/ Agreement for Sale or refund of the monies paid by me by reason of anything contained in other information / disclosure not forming part of this Application Form / allotment letter/ Agreement for Sale or the RERA website.
				</td>
			</tr>
			
			<tr>
				<th>10.</th>
				<td colspan="2">
					I/We have fully read and understood the Terms and Conditions attached hereto as <strong>Annexure A</strong> which contains broad terms, conditions, representations, covenants, etc. as well as the terms of the Agreement for Sale uploaded on RERA website and do hereby agree, undertake and covenant to abide and be bound by them and also by the area, sale consideration, estimated other charges and payment terms as set out herein. The Terms and Conditions as mentioned in Annexure A forms an integral part of this Application Form and shall always be read together with this Application Form and be construed accordingly.
				</td>
			</tr>
			
			<tr>
				<th>11.</th>
				<td colspan="2">
					I/We have taken the decision to purchase the Flat/Unit in the Project out of my/our own free will after giving careful consideration to the nature and scope of the entire development explained to me/us in person including the disclosures contained herein as well as made available on RERA website and remitted the amounts payable thereof fully conscious of my rights, liabilities and obligations. All the above information provided by me/us is true and nothing has been concealed or suppressed. I/We further undertake to inform the Developer promptly of any changes to the above information and particulars furnished by me/us.
				</td>
			</tr>
			
			<tr>
				<th>12.</th>
				<td colspan="2">
					<!-- <div style="line-height: 40px;">
						I/We hereby enclose (i) a Cheque/Demand Draft No.___________________ dated ______________   in favour of "___________________________________________________________" drawn on________________________ Bank, _______________________ Branch ________________________ OR (ii) acknowledgement receipt of NEFT/RTGS/Debit Card/Credit Card bearing transaction reference no. _______________ dated ____________ for an amount of Rs. _____________________ /- (Rupees ____________________________________________ only) as and by way of booking amount payable by me/us.
					</div> -->
					
					<div>
						I/We hereby confirm that I/we have made the payment towards the booking amount, details whereof are as under: 
					</div>	
					
					<table class="table table-bordered" id="paymentDtltable" style="margin-top:15px;">		
						<tbody>
							<tr>
								<th>Payment Type</th>
								<th>Bank Name</th>
								<th style="width:150px;">Amount</th>
								<th style="width:80px;">Branch</th>
								<th>Transaction ID (Cheque)</th>
								<th>Transaction ID (Swipe / NEFT)</th>
								<!-- <th>Transaction Date</th>
								<th>Transaction Amount</th>	 -->
							</tr>
						</tbody>
					</table>
					
					<div style="padding-top:15px;">
						I/We hereby understand that any payment shall be subject to realization and/or actual credit in Developer's bank account. 
					</div>	
					
					<!-- <table class="table table-bordered" style="margin-top:15px;">		
						<tbody>	
							<tr>
								<td style="height:80px; vertical-align: bottom;">
									<div>_________________</div>
									<div style="padding-top:10px;">(First/Sole Applicant/s)</div>
								</td>
								<td style="height:80px; vertical-align: bottom;">
									<div>_________________</div>
									<div style="padding-top:10px;">(Second Applicant/s)</div>
								</td>
								<td style="height:80px; vertical-align: bottom;">
									<div>_________________</div>
									<div style="padding-top:10px;">(Third Applicant/s)</div>
								</td>
							</tr>
						</tbody>
					</table> -->		
				
				</td>
			</tr>
			
		</tbody>
	</table>
	
	<!-- <div style="page-break-before: always"></div>
	
	<table class="" style="margin-bottom:15px; text-align:center; font-size:16px;">
		<tbody>
			<tr>
				<td style="border: 0; border-color: #ffffff; border-left-width: 0px; border-left-style: solid;  border-top-width: 0px; border-top-style: solid; border-collapse: collapse; border-spacing: 1px; ">
					<b style="font-size:16px;">ANNEXURE A</b>
				</td>
			</tr>
			<tr>
				<td style="padding-top:0 !important; font-size:12px; border: 0; border-color: #ffffff; border-left-width: 0px; border-left-style: solid;  border-top-width: 0px; border-top-style: solid; border-collapse: collapse; border-spacing: 1px; ">
					TERMS & CONDITIONS
				</td>
			</tr>
		</tbody>
	</table>
	
	<table class="table table-bordered" style="margin-bottom:15px;">		
		<tbody>	
			<tr>
				<td colspan="3">The Applicant/s agrees, acknowledges, confirms and covenants that:</td>
			</tr>
			<tr>
				<th style="width:10px;">(a)</th>
				<td colspan="2">The Applicant/s is/are aware that:</td>
			</tr>
			<tr>
				<td></td>
				<td style="width:10px;">1)</td>
				<td>The Developer/DM and _____(JVP name)____ have executed a Development Agreement/Development Management Agreement dated ____________ for the purpose of development of the [freehold/ leasehold] land bearing plot no. ________ situated at ____________ ("Project Land"). The entire Project Land will be developed by the Developer in phase wise/segment wise manner at the discretion of the Developer/DM in the manner the Developer/DM may deem fit.</td>
			</tr>
			<tr>
				<td></td>
				<td>2)</td>
				<td>The Developer/DM currently proposes to develop a portion of the Project Land known as Phase _____ of the ___________ Project which is a mixed use development comprising of [residential and commercial zones] (hereinafter referred to as “Current Development”) and this Application Form is for the allotment of the Flat situated in the Current Development.</td>
			</tr>
			<tr>
				<td></td>
				<td>3)</td>
				<td>The plans, specifications, images and other details herein may undergo change in accordance with applicable laws, directions/orders of the statutory authorities. The process of approvals has been initiated and some of the approvals may be received over a period of time.</td>
			</tr>
			<tr>
				<td></td>
				<td>4)</td>
				<td>Post development of the entire Project Land, it is envisaged by the Developer, that the Developer may  depending on the nature, scope and use of entire development, form a co-operative society / condominium / limited company or combination of them for the respective phases in the Project and at its discretion and form an apex organization (being either a co-operative society / condominium / limited company or combination of them for the entire development or separate apex association / apex body / apex bodies (being either a co-operative society / condominium / limited company or combination of them) for each of residential and commercial zones, if any, as the Developer may deem fit. As the Project Land is being developed phase-wise/segment-wise, the Developer may in its discretion form a single co-operative society / condominium / limited company for all the phases to be developed on the Project Land including the Current Development.</td>
			</tr>
			<tr>
				<th>(b)</th>
				<td colspan="2">The Applicant/s has fully understood the development scheme as envisaged by the Developer. The Applicant/s is aware that the title of the Project Land is clear and marketable.</td>
			</tr>
			<tr>
				<th>(c)</th>
				<td colspan="2">The Applicant/s hereby agrees and undertakes to pay all the amounts due and payable to the Developer in accordance with the Payment Schedule on or before the respective due dates. Further, in the event the Applicant/s offers to make advance payments to the Developer simultaneously with the amounts due on completion of any of the payment milestones towards the Flat, at the express request of the Applicant/s, the Developer may offer a rebate to the Applicant/s as the Developer may deem fit and proper. It is hereby clarified that the foregoing rebate is further subject to the Applicant/s complying with all its obligations under this Application Form including timely payment of installments. Save as foregoing, the quantum of rebate shall not be subject to any change/withdrawal. Subject to timely payment of installments, the Applicant/s understands that in the event the Applicant/s wishes to make any advance payments, the Applicant/s can make the same only after the Applicant/s has registered the Agreement for Sale within the timelines stipulated by the Developer. The Applicant/s further understands and agrees that the Developer shall have the right to accept or reject such advance payment on such terms and conditions as the Developer may deem fit and proper.</td>
			</tr>
			<tr>
				<th>(d)</th>
				<td colspan="2">For the purpose of this Application Form, the term earnest money shall mean 20% (twenty percent) of the total sale consideration including but not limited to Application Money (as defined below) (“Earnest Money”). The Applicant/s hereby agrees, confirms and undertakes to come forward and register the Agreement for Sale of the Flat on or before the payment of 10% (ten percent) of sale consideration to the Developer or as stipulated by the Developer, failing which the Developer shall without prejudice to any other rights be entitled at its sole discretion to (i) charge Interest to the Applicant/s and/or (ii) cancel this Application Form / allotment letter and forfeit the Non-Refundable Amounts as defined herein below.</td>
			</tr>
			<tr>
				<th>(e)</th>
				<td colspan="2">Notwithstanding the fact that the Developer may have issued an acknowledgement by way of a receipt for the money tendered with this Application Form (“Application Money”), the Applicant/s has clearly understood that this Application Form is only a request of the Applicant/s for allotment of the Flat and does not constitute a final/provisional allotment or an agreement 
				</td>
			</tr>
			<tr>
				<th>(f)</th>
				<td colspan="2">
				The Developer, at its absolute discretion, shall be entitled to reject this Application Form without assigning any reason whatsoever. In the event of rejection of this Application Form, the Application Money tendered by the Applicant/s shall be refunded by the Developer without any liability towards interest/damages. Further, in the event the Developer decides to allot the Flat in favor of the Applicant/s, the Developer will send the intimation thereof to the Applicant to make payments as per the Payment Schedule towards further consideration. Upon receipt of the same, the Developer shall proceed with allotment of the Flat and registration of the Agreement for Sale. 
				</td>
			</tr>
			<tr>
				<th>(g)</th>
				<td colspan="2">
				The Applicant/s further agree and understand that the allotment of the Flat is further subject to the Applicant/s paying the requisite stamp duty and registration charges and registering the Agreement for Sale within the timelines stipulated by the Developer, failing which, the Developer is entitled to charge Interest as mutually agreed under the terms of this Application Form. The Applicant/s further agrees and understands that in the event the Applicant/s fail to register the Agreement for Sale within the stipulated timelines, the same shall not be treated as a deemed cancellation of allotment and the Applicant/s agrees to be bound by the terms of this Application Form and waive off any right to the contrary that the Applicant/s may have under any applicable law.
				</td>
			</tr>
			<tr>
				<th>(h)</th>
				<td colspan="2">
				Without prejudice to the Developer’s right to charge Interest, in the event the Applicant/s fails to (i) pay the requisite stamp duty and registration charges within the stipulated timelines and / or (ii) come forward for registration of the Agreement for Sale within the stipulated timelines, the Developer may, at its sole discretion reserves its right to cancel this Application Form/revoke the allotment of the Flat and in event the Developer exercises its right to cancel/ revoke, then the Non-Refundable Amounts as defined herein below shall stand forfeited and the Applicant/s shall not raise any claims/dispute and waive off any rights/claims to the contrary that the Applicant/s may have under any applicable law.
				</td>
			</tr>
			<tr>
				<th>(i)</th>
				<td colspan="2">
				The Applicant/s is not vested with any right, interest or entitlement in or over the Flat, until a formal Agreement for Sale (“Agreement for Sale”) is executed and registered between the Developer/DM and the Applicant/s under the applicable laws within the timelines stipulated by the Developer. 
				The term “allot” or “allotment” or “Allotment Letter” wherever included in the Application Form shall always mean “provisional allotment” until the Agreement for Sale is executed and registered by the Developer/DM and the Applicant.  
				</td>
			</tr>
			<tr>
				<th>(j)</th>
				<td colspan="2">
				The Applicant/s understands that the Applicant’s eligibility to avail subvention plan, if offered, for payments, shall be decided by the bank/financial institution in their sole discretion and in accordance with their policies, terms and conditions. 
				</td>
			</tr>
			<tr>
				<th>(k)</th>
				<td colspan="2">
				All outstanding amounts payable by any party under this transaction to other shall carry such applicable interest at the rate of (i) 2% (two percent) above the then existing SBI MCLR (State Bank of India – Marginal Cost of Lending Rate) per annum or (ii) such other rate of interest higher/ lower than 2% as may be prescribed under the Real Estate (Regulation and Development) Act, 2016 and Rules made thereunder (“Interest”) from the date they fall due till the date of receipt/realization of payment by the other party. Any overdue payments so received will be first adjusted against Interest then towards statutory dues and subsequently towards outstanding principal amounts.
				</td>
			</tr>
			<tr>
				<th>(l)</th>
				<td colspan="2">
				In the event if the Applicant/s fails or neglects to (i) make the payment of the sale consideration and all  other amounts due including but not limited to estimated other charges due from the Applicant/s as mentioned in this Application Form and/or Allotment Letter and/or Agreement for Sale on due dates (ii) comply with the obligations as set out herein/ Allotment Letter/ Agreement for Sale including timely registration of Agreement for Sale, at any point of time, the Developer shall be entitled, without prejudice to other rights and remedies available to the Developer, to cancel/terminate this transaction and forfeit (a) Earnest Money including but not limited to the Application Money, from the amounts paid till such date and (b) Interest on any overdue payments and (c) brokerage paid to channel partners/brokers, if any, and (d) administrative charges as determined by the Developer (e) all taxes paid by the Developer to the Authorities and (f) amount of stamp duty and registration charges to be paid on deed of cancellation of the Agreement for Sale, if  Agreement for Sale is registered and (g) any other applicable taxes and (h) subvention cost (if the Applicant/s has opted for subvention plan) which the Developer may incur either by way of adjustment made by the bank in installments or paid directly by the Developer to the bank, (collectively referred to as the “Non-Refundable Amount”). Balance amounts, if any, without any liabilities towards costs/damages/interest etc. shall be refunded without interest upon registration of the deed of cancellation, if applicable. For the sake of clarity, the interest and/or taxes paid on the sale consideration shall not be refunded upon such cancellation / termination. Upon such cancellation, the Applicant/s shall not have any right, title and/or interest in the Flat and/or car park space and/or the Project and/or the Project Land and the Applicant/s waives his right to claim and/or dispute against the Developer in any manner whatsoever. 
				</td>
			</tr>
			<tr>
				<th>(m)</th>
				<td colspan="2">
				The Applicant/s acknowledges and agrees that such forfeiture and the refund of the balance amount, if any, to the Applicant/s shall be deemed to be full and final settlement of the claim and the Developer shall be entitled to sell the Flat to any third party of the Developer choice without any recourse to the Applicant. 
				</td>
			</tr>
			<tr>
				<th>(n)</th>
				<td colspan="2">
				The Applicant/s further agrees that in the event this Application Form is withdrawn/cancelled by the Applicant/s for reasons not attributable to Developer’s default, then the Developer shall be entitled to forfeit the Non-Refundable Amounts. 
				</td>
			</tr>
			<tr>
				<th>(o)</th>
				<td colspan="2">
				Except for the Covered Car Parking Space allotted by the Developer in accordance to this Application Form, the Applicant/s agrees and confirms that all parking spaces including open parking spaces will be dealt with in accordance with the applicable laws as well as bye-laws and constitutional documents of the society / association. The Applicant/s hereby declares and confirms that except for the Covered Car Parking Space allotted by the Developer, the Applicant/s does not require any parking space including open car parking space and accordingly the Applicant/s waives his claim, right, title, interest whatsoever on the areas of parking space in the Project. The Applicant/s further agrees and undertakes that it shall have no concerns towards the identification and allotment/allocation of parking space done by Developer / association / apex body, at any time and shall not challenge the same anytime in future. The Applicant/s agrees and acknowledges that Developer/the association/apex body shall deal with the parking space in the manner association / apex body deems fit, subject to the terms of bye-laws and constitutional documents of the association / apex body / the applicable laws.  The Developer acknowledges and accepts the aforementioned waiver and accordingly has given effect to the same while calculating the Sale Consideration.The Applicant/s agrees and acknowledges that the Covered Car Parking Space in the Project cannot be transferred / leased / sold or dealt otherwise independently of the Flat. All clauses of this Application Form and the Agreement for Sale pertaining to allotment, possession, cancellation etc. shall also apply mutatis mutandis to the Covered Car Parking Space.
				</td>
			</tr>
			<tr>
				<th>(p)</th>
				<td colspan="2">
				The Applicant/s further agrees and acknowledges that if in the event of any variation in the Carpet Area of the Flat, the sale consideration payable for the Carpet Area shall be recalculated upon confirmation by the Developer and in such event only recourse shall be a prorate adjustment in the last installment payable by the Applicant/s towards the Sale Consideration. It is hereby clarified in case of variations/ additions required due to architectural and structural reason duly recommended and verified by Architect or Engineer, the Developer shall intimate the Applicant/s in writing and the Applicant/s hereby gives its consent for such variation or addition.
				</td>
			</tr>
			<tr>
				<th>(q)</th>
				<td colspan="2">
				The Applicant/s agrees and understands that the Other Charges as mentioned in Annexure F are only estimated amounts and are payable by the Applicants/s over and above the total sale consideration. The Applicant/s agrees and undertakes to pay all charges towards electricity, water and sewerage connection, maintenance charges, etc. for upkeep and maintenance of various common services and facilities and limited common area (if any), as may be called upon by the Developer. 
				</td>
			</tr>
			<tr>
				<th>(r)</th>
				<td colspan="2">
				The Developer shall offer possession of the Flat to the Applicant/s on or before ___day of ____, 20___ (“Delivery Date”) and shall deliver the Common Areas and Facilities such as ___________________ on or before ______. The Delivery Date shall stand reasonably extended on account of (i) any force majeure events and/or (ii) reasons beyond the control of the Developer and/or its agents and/or (iii) due to non-compliance on the part of the Applicant/s including on account of any default on the part of the Applicant/s. In case the Developer is unable to offer possession on or before the Delivery Date for any reasons other than those set out in the foregoing, then on demand in writing by the Applicant/s, the Developer shall refund the amounts received from the Applicant/s along with prescribed Interest in accordance to the applicable laws.
				</td>
			</tr>
			<tr>
				<th>(s)</th>
				<td colspan="2">
				In the event the Applicant/s fails to take possession of the Flat within the stipulated timelines, then the Applicant/s shall be liable to pay to the Developer Rs.______/- (Rupees _______Only) per month per square meter on the Total Area of the Flat and applicable maintenance charges for the upkeep and maintenance of the Flat. 
				</td>
			</tr>
			<tr>
				<th>(t)</th>
				<td colspan="2">
				Due to any operation of law / statutory order/otherwise, if a portion of the Project or the entire Project is discontinued/ modified resulting in cancellation of allotment, then the Applicant/s affected by such discontinuation/ modification will have no right of compensation from the Developer in any manner including any loss of profit. The Developer will, however, refund all the money received from the Applicant/s without any liability towards any interest/costs/damages, subject to deduction of applicable taxes. 
				</td>
			</tr>
			<tr>
				<th>(u)</th>
				<td colspan="2">
				The Applicant/s is aware that for the purposes of maintenance and management of the Project, the Developer/DM would be appointing a facility management company, at its sole discretion without any reference to the Applicant/s and other occupants of the Project on such terms and conditions as the Developer/DM may deem fit and the Applicant/s agrees and consents to the same. The Applicant/s acknowledges that the Developer/DM may also retain some portion / units / flats in the Project which may be subject to different terms of use as may be permissible under law, and the Applicant/s shall not raise any objections with respect to the same.
				</td>
			</tr>
			<tr>
				<th>(v)</th>
				<td colspan="2">
				The Applicant/s shall not be entitled to transfer/assign his interest in the Flat in favor of any third party unless (i) ______ (____percent) of the sale consideration has already been paid; and (ii) a term of ______ (____) years (i.e. ______ months) has elapsed from the date of issuance of this Allotment Letter, whichever is later,; and (iii) the Applicant/s has obtained prior written consent of the Developer. The Developer reserves the right to allow such transfer at its sole discretion on payment of transfer charges of Rs. _____/- (Rupees________ only) per square meter plus taxes as applicable on the Total Area. On such transfer recorded / endorsed by the Developer, the Applicant/s along with third party transferee shall furnish requisite undertakings and indemnities, as may be required by the Developer, to abide by all the terms and conditions of this Application Form /Agreement for Sale. The Applicant/s shall solely be liable and responsible for all legal and other consequences that may arise due to acceptance of application for such transfer/ assignment. 
				</td>
			</tr>
			<tr>
				<th>(w)</th>
				<td colspan="2">
				The name of the individual towers and/or the respective phases in the Project may be amended at the sole discretion of the Developer/DM and the Applicant/s shall not be entitled to raise any objection/hindrance on the same.
				</td>
			</tr>
			<tr>
				<th>(x)</th>
				<td colspan="2">
				In the case of joint application for the Flat, unless a duly executed instruction by all such joint Applicant/s is provided to the Developer at the time of termination, all payments/ refund to be made by the Developer to the Applicant/s under the terms of the transaction documents, upon termination, shall be made to the first mentioned Applicant, which payment/refund shall be construed to be a valid discharge of all liabilities towards all such joint Applicants.
				</td>
			</tr>
			<tr>
				<th>(y)</th>
				<td colspan="2">
				All terms & conditions, rights and obligations of the parties as contained hereunder shall be subject to the provisions of Real Estate (Regulation and Development) Act, 2016 (“Act”) and the Rules and Regulations made thereunder (“Rules and Regulations”) and the exercise of such rights and obligations shall be subject to the provisions of the Act and the Rules and Regulations made thereunder. Any change so prescribed by the Act and the Rules and Regulations shall be deemed to be automatically included in this Applications Form and similarly any such provision which is inconsistent or contradictory to the Act and the Rules and Regulations shall not have any effect.
				</td>
			</tr>
			<tr>
				<th>(z)</th>
				<td colspan="2">
				In case the Parties are unable to settle their disputes within 15 (fifteen) days of intimation of dispute by either Party, the Parties shall in the first instance, if permitted under law, have the right to settle the dispute through arbitration in accordance to the procedure laid down under the applicable laws. Costs of arbitration shall be shared equally by the Parties. The award of the Arbitrator shall be final and binding on the Parties to the reference. The arbitration proceedings shall be held in Mumbai and conducted in English only. This transaction will be subject to the exclusive jurisdiction of Courts at ______only.
				</td>
			</tr>
			<tr>
				<th>(aa)</th>
				<td colspan="2">
					Unless the context otherwise requires, reference to one gender includes a reference to the other, words importing the singular include the plural and vice versa, which means the use of singular expressions shall also include plural expressions and masculine includes the feminine gender wherever the context of this Application form so demands.
				</td>
			</tr>
			<tr>
				<td colspan="3">
					The contents of this Application Form, including the terms and conditions therein and price and payment plan have been explained to me and I hereby solemnly agree to be bound by them.
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<table class="table table-bordered" style="margin-bottom:0 !important;">		
						<tbody>	
							<tr>
								<th colspan="3">Signature(s)</th>
							</tr>	
							<tr>
								<td style="height:80px; vertical-align: bottom;">
									<div>_________________</div>
									<div style="padding-top:10px;">(First/Sole Applicant/s)</div>
								</td>
								<td style="height:80px; vertical-align: bottom;">
									<div>_________________</div>
									<div style="padding-top:10px;">(Second Applicant/s)</div>
								</td>
								<td style="height:80px; vertical-align: bottom;">
									<div>_________________</div>
									<div style="padding-top:10px;">(Third Applicant/s)</div>
								</td>
							</tr>
						</tbody>
					</table>	
				</td>
			</tr>
			
			<tr>
				<th colspan="3" style="height:50px; text-align:center; border-top-color: #ffffff;">
					ANNEXURE B - (Plan)
				</th>
			</tr>
			<tr>
				<th colspan="3" style="height:50px; text-align:center; border-top-color: #ffffff;">	
					ANNEXURE C - (Specification(s) of the Flat)
					
				</th>
			</tr>
			<tr>
				<th colspan="3" style="height:50px; text-align:center; border-top-color: #ffffff;">	
					ANNEXURE D - (Common Areas)
				</th>
			</tr>
			<tr>
				<th colspan="3" style="height:50px; text-align:center; border-top-color: #ffffff;">	
					ANNEXURE E - (Facilities)
				</th>
			</tr>
			<tr>
				<th colspan="3" style="height:50px; text-align:center; border-top-color: #ffffff;">	
					ANNEXURE F - (Payment Schedule & Estimated Other Charges)
				</th>
			</tr>
			<tr>
				<th colspan="3" style="height:50px; text-align:center; border-top-color: #ffffff;">	
					ANNEXURE G - (TDS Authorization Letter)
				</th>
			</tr>
		</tbody>
	</table>-->
</div>