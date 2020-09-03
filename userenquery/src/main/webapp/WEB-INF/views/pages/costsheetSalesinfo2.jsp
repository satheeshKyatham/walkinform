<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@page import="java.util.HashMap"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	 response.addHeader("Expires","0");
	 response.addHeader("Pragma","no-cache");
	 response.setHeader("Cache-Control","no-cache,no-store, must-revalidate, pre-check=0, post-check=0, max-age=0, s-maxage=0");
	 response.addHeader("X-Frame-Options", "DENY");
	 %>



	<div style="display:none;">
	
	
		<input id="stampDuty" >
		<input id="marketingProjectName">
		
		<input id="region__c">
		
		<div id="paymentPlanChangeDiv"></div>
		<input id="BSPrate" style="">
		
		<input id="timeid"  style="">
		
		
		<%-- <input id="token" value="${token}">
		<input id="projectsfid" value="${projectsfid}" />
		<input id="enquirysfid" value="${enquirysfid}" />
		<input id="primarycontactsfid" value="${primarycontactsfid}" /> --%>
		
		<!-- commented - 20190801 -->
		<!--<input id="paymentPlanId" value="a1l6F000004QtUZQA0">
		<input id="token" value="7">
		<input id="projectsfid" value="a20O0000002XKut" />
		<input id="enquirysfid" value="a29O000000AqL4M" />
		<input id="primarycontactsfid" value="003O000000whPJ3" />  -->
		<!-- END commented - 20190801 -->
		
		<input id="pageContext" value="${pageContext.request.contextPath}"/>
	
	
		<input id="costofCarpet"  />
		<input id="costofBalCony"  />
		<input id="costCom"/>	
		<input id="totalTaxableAmtInput"  />
		
		<input id="csFinalAmountInput" />
		
		<input id="carParkAmount" />
		
		<input id="schemeInput" />
		<input id="finalDiscountValue" />
	</div>
	
	
	<input id="addlBspPer" style="display:none;">
	
	<div class="commonLoad" id="mainLoad" style="display:none;"></div>
	
	
	
  <!-- <input id="bspAginestPP"> -->
  <input id="discountPsqf" style="display:none;">
  
  
  <input id="discountPerAndAbsolute" style="display:none;">
  
  
<div>

	<!-- <a class="printFloat vpdfDis" target="_blank" id="viewPDF">
		<i class="glyphicon glyphicon-print printficon"></i>
	</a> -->
	
	
	<!-- Do not remove below code -->
	<!-- <a class="printFloat" onclick="printDiv('printableAreaNew')" value="print a div!">
		<i class="glyphicon glyphicon-print printficon"></i>
	</a> -->
	
	
	
	
	<a class="printFloat" id="printBtnFloat" style="bottom: 106px !important; display:none;" onclick="printPdfData('Draft')" data-toggle="tooltip" title="Print Cost Sheet">
		<i class="fa fa-print printficon" style="line-height: 50px !important; font-size: 22px !important;"></i>
	</a>
	
	
	<!-- Costsheet -->
	<div id="costsheet">
	
		<input id="flatCostWithDiscount" style="display:none;"/>
		<input id="towerBankGLCode" style="display:none;"/>
		
			<div class="inventoryWrp" style="margin-bottom:0 !important;">
				<div class="row">
					<div class="col-md-8">
						
						<div class="clearfix"></div>
						
						<div class="col-md-12" style="margin-bottom: 10px; padding-left: 0;">
							<label style="color: #a94442;">(*) indicates mandatory fields</label>
							<div class="clearfix"></div>
						</div>
						
						<div class="clearfix"></div>
						
						<div class="filterCSCol">
							<input type="text" id="unitSfid" style="display:none;"/> 
							<input type="text" id="towerSfid" style="display:none;"/> 
							<input type="text" id="bspGSTTax" style="display:none;"/> 
							
							<div class="schemeFilterCol">
								<div class="form-group col-md-3 col-sm-6">
									<label >Scheme Type</label> 
									<select class="full form-control" id="schemeTypeDD" onchange="schemeType(this)">
										<option value="scheme">Scheme</option>
										<option value="other" data-valuepercentage="0" data-valueabsolute="0" data-valuerate="0">Others</option>
										<option value="noScheme">No Scheme</option>
									</select>
								</div>
							
								<div class="form-group col-md-3 col-sm-6" style="display:none;">
									<label >Scheme Type</label> 
									<select class="full form-control" id="getPln" onchange="schemeType(this)">
										<option value="0" data-valuepercentage="0" data-valueabsolute="0" data-valuerate="0">Select</option>
										<!-- <option value="other" data-valuepercentage="0" data-valueabsolute="0" data-valuerate="0">Others</option> -->
										<!-- <option value="0">Select</option> -->
										<!-- <option value="200">Loyalty€“ Rs. 200/sqft</option>
										<option value="200">Defence Rs. 200/sqft</option>
										<option value="100">Corporate Rs. 100/sqft</option>
										<option value="100">Referral Rs. 100/sqft</option>
										<option value="100">Bulk(2 units) Rs. 100/Sqft</option>
										<option value="150">Bulk(3-5 units) - Rs. 150/Sqft</option>
										<option value="300">Bulk(5+) - Rs. 300/Sqft</option>
										<option value="200">Airlines Rs. 200/Sqft</option>
										<option value="200">GILAC Rs. 200/Sqft</option>
										<option value="50">Others - Rs. 50/Sqft</option>
										<option value="other">Others</option> -->
									</select>
								</div>
							
								<!-- New scheme -->
								<div class="form-group col-md-3 col-sm-6" id="schemeSourceCol">
									<label >Source</label> 
									<select class="full form-control" id="getSchemeSource" >
										<option value="0">Select</option>
									</select>
								</div>
							
								<div class="form-group col-md-3 col-sm-6" id="schemeSite">
									<label >Site</label> 
									<select class="full form-control" id="getSchemeSite" >
										<option value="0">Select</option>
									</select>
								</div>
							
								<div class="form-group col-md-3 col-sm-6" id="schemePromotional">
									<label >Promotional</label> 
									<select class="full form-control" id="getSchemePromotional" >
										<option value="0">Select</option>
									</select>
								</div>
								<!-- END New scheme -->
								<div id="newPlan"></div>
								
								<div class="clearfix"></div>
							</div>
							
							<div class="clearfix"></div>
							<div class="row">
								<div class="form-group col-md-4 col-sm-6" id="carParkDDCol">
									<label >Car Park Type </label> 
									<select class="full form-control" id="carParkType">
										<option value="">Select</option>
										<option value="covered">Covered Car Park</option>
										<!-- Do not remove -->
										<!-- <option value="open">Open Car Park</option>
										<option value="Covered/Stacked">Covered/Stacked</option> -->
									</select>
								</div>
							
								<div class="form-group col-md-4 col-sm-6" id="carParkCount" style="display:none;">
									<label>No. of Car Park </label> 
									<div id="carParkCountDD">
										<select class="full form-control">
											<option value="0">0</option>
										</select>
									</div>
									<!-- <select class="full form-control">
										<option value="0">0</option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
									</select> -->
								</div>
								<div class="form-group col-md-4 col-sm-6">
									<label >Payment Plan <strong class="mndt">*</strong> </label> 
									<select class="full form-control" id="ppDropdown">
										<option value="">Select</option>
									</select>
								</div>
								<!-- <div class="form-group col-md-4 col-sm-6">
									<label >Costsheet Format Type </label> 
									<select class="full form-control" id="csFormatType">
										<option value="v2">Default</option>
										<option value="v1">Version 1</option>
										<option value="v2">Version 2</option>
									</select>
								</div> -->
								<div class="col-md-4 col-sm-12 col-xs-12">
									<!-- <button class="btn btn-primary" onclick="location.reload()"><i class="glyphicon glyphicon-repeat"></i></button> -->
									<div style="height: 20px; margin-bottom: 4px;">&nbsp;</div>
									<button class="btn btn-primary btnCommon" data-source="SALES" id="getCSData">
										<span></span> Get Details
									</button> 
									
									<!-- <div id="getCSForAdminBtnCol">
										<button class="btn btn-primary btnCommon" id="getCSForAdminBtn" onclick="loadData ('ADMINCOSTSHEET')">
											<span></span> Admin - Get Details
										</button> 
									</div> -->
									
									
									
									
									<!-- <button class="btn btn-primary btnCommon" id="zzzzTestCharges">Dynamic Other Charges</button> -->
								</div>
								<div class="clearfix"></div>
							</div>
							<div class="clearfix"></div>
						</div>
						
						
						
						
						<!-- 
						<div class="col-md-12">
							<h4 class="">Other charges</h4>
							<table class="table table-bordered mrgB0" id="newOtherCharges1">
								<tbody>
									<tr class="subHead" >
										<th>
											Parameter
										</th>
										<th>
											Amount per Sq.ft. of Saleable Area (INR)
										</th>
										<th>
											Amount in INR
										</th>
										<th>
											GST %
										</th>				
									</tr>
								</tbody>
							</table>
							<div class="tableNoRec">
								No record found
							</div>
							<div class="clearfix"></div>
						</div>
						 -->
						
						
						
						<div class="clearfix"></div>
					</div>
					 
					
					<div class="col-md-4"  id="csHoldCountCol">
						<div style="border: 1px solid #ccc; box-shadow: 4px 3px 4px #999; padding: 8px; background-color: #fff; border-radius: 10px;">
							
							<div class="counterCol">
								<div class="counterTxt1">Property Release in</div>
								<span class="holdCountdown">00:00</span> 
								<span id="holdCountZero" style="font-size: 28px !important;">00:00</span> 
								<span>min.</span>
								<div class="clearfix"></div>
							</div>
							
							<ul class="basicDetailsCS" style="display: none">
								<!-- <li>
									<label>Type of Car Parking</label> <span>: </span><span class="a1"></span>
								</li> -->
								<li>
									<label>Typology</label> <span>: </span><span class="a2"></span>
								</li>
								<li>
									<label>Basic Sale Price per square feet <small>(INR)</small> </label> <span>: </span><span class="a3"></span>
								</li>
								<li>
									<label>Original BSP <small>(INR)</small> </label> <span>: </span><span class="a3_bsp"></span>
								</li>
								<li>
									<label>Carpet Area <small>(in square feet)</small></label> <span>: </span><span class="a4"></span>
								</li>
								<!-- <li>
									<label>Exclusive Balcony Area/Terrace Area <small>(in square feet)</small></label> <span>: </span><span class="a5"></span>
								</li> -->
								<li>
									<label>Saleable Area <small>(in square feet)</small></label> <span>: </span><span class="a6"></span>
								</li>
								<li><!-- Added By Satheesh K - Date : 10-06-2020 - Property Name Added on Cost sheet Page -->
									<label>Unit No.</label> <span>: </span><span class="unit_property_name"></span>
								</li>
							</ul>
							
							<div class="clearfix"></div>
						</div>	
						<div class="clearfix"></div>
					</div>
					<div class="clearfix"></div>
				</div>
				
		
		
		
			
			<div>
				
			
			<div class="clearfix"></div>
			
			<!-- 20190623 - Do not remove below code -->
			<!-- 
			<div class="col-md-12">
				<h4 class="flipInX animated paySchTitle">Payment Schedule</h4>      
				<table class="table table-bordered mrgB0" id="paymentMilestone">
					<tbody>
						<tr class="subHead"><th>Payment Milestone</th> <th>Amount in INR</th> <th>%</th></tr>
					</tbody>
				</table>
				<div class="tableNoRec">
					No record found
				</div>
				<div class="clearfix"></div>
			</div>
			 -->
			<!-- END 20190623 - Do not remove below code -->
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		
			<div class="clearfix"></div>
		  
		  
			
			<div class="clearfix"></div>
		</div>
	
		<div class="clearfix"></div>	
			
			
			
			
		  
		  <!-- ---------------------- 2nd TAB START ------------------------------------------------------------ -->
			
			<div>
				
				<div class="clearfix"></div>
			</div>
			
			<div id="printableArea" style="display:none; border-top: 1px dotted #000; padding-top: 20px;  background-color: #effaff;">
				
				
				
				<div id="printableAreaNew">
					<!-- <h4 class="printTitle" style="display:none;">
					PRICE AND PAYMENT SCHEDULE
					</h4> -->
			
				  
				  <div class="printHeader" style="display:none;">
				  	
				  	<table>
					  <tr>
					    <th rowspan="3">
							<img width="191" height="50" src="<c:url value='/resources/images/gplLogo.jpg' />"/>
						</th>
					  </tr>
					  <tr>
					    <td>
							Project: <b class="printProjectName"> </b>
						</td>
					    <td class="txtCenter">
							Date: <span class="printCurrentDate"></span>
						</td>
					  </tr>
					  <tr>
					    <td colspan="2">
							Head Office: <b>Godrej Properties Limited</b>
		  					<br>
		  					Godrej One, 5th Floor, Pirojshanagar, Eastern Express Highway,
		  					<br>
		  					Vikhroli (E), Mumbai - 400079, Tel: +91-22-6169 8606
						</td>
					  </tr>
					</table>
				  	
				  	
				  		<hr>
				  	
				  	
				  	<%-- <table >
				  		<tbody>
				  			<tr>
				  				<td colspan="2">
				  					<img width="191" height="50" src="<c:url value='/resources/images/gplLogo.jpg' />"/>
				  				</td>
				  				<td>
				  					Project Address: <b class="printProjectName"> </b>
				  				</td>
				  				<td style="text-align: right !important;">
				  					Date: <span class="printCurrentDate"></span>
				  				</td>
				  			</tr>
				  			
				  			<tr>
				  				<td>
				  					
				  				</td>
				  				<td>
				  					Head Office: <b>Godrej Properties Limited</b>
				  					<br>
				  					Godrej One, 5th Floor, Pirojshanagar, Eastern Express Highway,
				  					<br>
				  					Vikhroli (E), Mumbai - 400079, Tel: +91-22-6169 8606
				  				</td>
				  				<td>
				  					
				  				</td>
				  			</tr>
				  			
				  			
				  			
				  			
				  		</tbody>
				  	</table> --%>
				  	
				  	<div class="clearfix"></div>
				  </div>
				  
				  
				  
				  
				  
				  
				  
					<!-- For Print -->
					<div id="getCSDataForPrint" style="display:none; margin-top:80px; border-top:1px solid #000000;">
						
						<!-- <table style="width:100%;">
							<tr>
								<th rowspan="3">
									<img width="191" height="50" src="https://www.hindustantimes.com/rf/image_size_640x362/HT/p2/2016/03/09/Pictures/godrej_f737e254-e5b8-11e5-93d4-5ee5efee2841.jpg"/>
								</th>
							 </tr>
							 <tr>
								<td>
									Project: <b class="printProjectName"> </b>
								</td>
							   	<td class="txtCenter">
									Date: <span class="printCurrentDate"></span>
								</td>
							 </tr>
							 <tr>
							    <td colspan="2">
									Head Office 123: <b>Godrej Properties Limited</b>
									
									Godrej One, 5th Floor, Pirojshanagar, Eastern Express Highway,
									
									Vikhroli (E), Mumbai - 400079, Tel: +91-22-6169 8606
								</td>
							 </tr>
						</table> -->
						
						<!-- <div>
							<div style="height:50px; float:left; width:200px;">
								<img width="191" height="50" src="https://www.hindustantimes.com/rf/image_size_640x362/HT/p2/2016/03/09/Pictures/godrej_f737e254-e5b8-11e5-93d4-5ee5efee2841.jpg"/>
							</div>
							<div style="float:left;">
								<div>
									<div style="float:left; width:350px;">Project: <b class="printProjectName"> </b></div>
									<div style="float:right">Date: <span class="printCurrentDate"></span></div>
								</div>
								<div>
									<div>Head Office 123: <strong>Godrej Properties Limited</strong> Godrej One, 5th Floor, Pirojshanagar, Eastern Express Highway, Vikhroli (E), Mumbai - 400079, Tel: +91-22-6169 8606</div>	
								</div>
							</div>
						</div> -->
						
						
						<!-- <div>
							<div>
								<img style="float: left;" width="191" height="50" src="https://www.hindustantimes.com/rf/image_size_640x362/HT/p2/2016/03/09/Pictures/godrej_f737e254-e5b8-11e5-93d4-5ee5efee2841.jpg"  /> 

								<div style="margin-left: 250px;">
									<div>
										<div style="float:left; width:350px;">Project: <b class="printProjectName"> </b></div>
										<div style="float:right">Date: <span class="printCurrentDate"> </span></div>
									</div>
									<div style="clear:both; float:none;"></div>
									<div style=" width: 380px; ">
										<div style="margin-top: -20px;">Head Office 123: <strong>Godrej Properties Limited</strong> Godrej One, 5th Floor, Pirojshanagar, Eastern Express Highway, Vikhroli (E), Mumbai - 400079, Tel: +91-22-6169 8606</div>	
									</div>
								</div>
							</div>
							
						</div> -->
						
						
						<!-- <div class="divTable">
							<div class="divTableBody">
								<div class="divTableRow">
									<div class="divTableHead">
										<img width="191" height="50" src="https://www.hindustantimes.com/rf/image_size_640x362/HT/p2/2016/03/09/Pictures/godrej_f737e254-e5b8-11e5-93d4-5ee5efee2841.jpg"/>
									</div>
								</div>
								<div class="divTableRow">
									<div class="divTableCell">Project: <b class="printProjectName"> </b></div>
									<div class="divTableCell">Date: <span class="printCurrentDate"></span></div>
								</div>
								<div class="divTableRow">
									<div class="divTableCell">Head Office 123: <strong>Godrej Properties Limited</strong> Godrej One, 5th Floor, Pirojshanagar, Eastern Express Highway, Vikhroli (E), Mumbai - 400079, Tel: +91-22-6169 8606</div>
								</div>
							</div>
						</div> -->
						
						
						
						
						<div id="printColLeft" style="width:460px; float:left; padding-right:10px;">
							<!-- <h5 class="">Customer Details</h5>
						  	<div class="">
								<table class="table table-bordered bgWhite" style="width:100%; font-size:8px !important;">
									<tbody>
										<tr>
											<th class="subHead" style="text-align:left">Customer Name</th> 
											<td class="customerName"> </td> 
										</tr>
										<tr >
											<th class="subHead" style="text-align:left">Contact No.</th> 
											<td class="customerContact"> </td> 
										</tr>
									</tbody>
								</table>
								
								<div class="clearfix"></div>
							</div>	-->
							<div class="printBasicDetails" id="unitDtls">
								<h5 class="">Unit Details</h5>
								<div class="">
									<table class="table table-bordered bgWhite" style="width:100%; font-size:8px !important;">
										<tbody>
											<tr>
												<th class="subHead">Tower</th>
												<td><span class="towerTval"></span><span class="wingVal"></span></td>
												<th class="subHead">Carpet Area Rera (in Sq.mtr.)</th>
												<td class="carpetSqm"></td>
											</tr>
											<tr>
												<th class="subHead">Unit Number</th>
												<td>
													<span class="unitTval"></span>
												</td>
												<th class="subHead">Exclusive Area (in Sq.mtr.)</th>
												<td class="balTerSqm"></td>
											</tr>
											<tr>
												<th class="subHead">Floor</th>
												<td class="floorTval"></td>
												<th class="subHead">Total Area (in Sq.mtr.)</th>
												<td class="totalSqm"></td>
											</tr>
											<tr>
												<th class="subHead">Typology</th>
												<td   class="typologyTval"></td>
												<th class="subHead noOfCarParkLabel">No. of Car Park</th>
												<td  class="noOfCarPark ifCarParkZero"></td>
											</tr>
											<tr>
												<th class="subHead">Carpet Area Amount</th>
												<td  class="carpetAreaAmount" style="text-align:right;"></td>
												
												
												<th class="subHead craParkTypeLabel">Car park Type</th>
												<td  class="carParks ifCarParkZero">-</td>
												<!-- <th class="subHead"></th>
												<td></td> -->
												<!-- <th class="subHead">View Facing</th>
												<td class=""> </td> -->
											</tr>
											
											<tr>
												<th class="subHead">Exclusive Area Amount</th>
												<td class="exclusiveAreaAmount" style="text-align:right"></td>
												<th class="subHead"></th>
												<td></td>
											</tr>
											
											<tr class="facingCSCol">
												<th class="subHead">Facing</th>
												<td id="propFacingType" style="text-align:left;" ></td>
												<th class="subHead"></th>
												<td></td>
											</tr>
											
										</tbody>
									</table>
								</div>
								<div class="clearfix"></div>
						 	</div>
							<div>
								<h5 class="flipInX paySchTitle">Payment Plan</h5>      
								<table class="table table-bordered bgWhite" id="printPPOtherCharges" style="width:100%; font-size:8px !important;">
									<tbody>
										<tr class="subHead">
											<th style="width:400px;">Milestone</th> 
											<th>%</th>  
											<th>Amount (INR)</th>  
											<th>GST (INR)</th> 
											<th>Total</th>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="clearfix"></div>
						</div>
						<div id="printColRight" style="width:275px; float:left; padding-left:10px;">
							<h5 class="">Sale Consideration</h5>
							<table class="table table-bordered  bgWhite" id="printSalesConsideration" style="width:100%; font-size:8px !important;">
								<tbody>
									<tr class="subHead" >
										<th>
											Description
										</th>
										<th width="80">
											Amount (INR)
										</th>			
									</tr>
								</tbody>
							</table>
							<h5 class="">Estimated Other Charges</h5>
							<table class="table table-bordered  bgWhite" id="printTentativeCharges" style="width:100%; font-size:8px !important;">
								<tbody>
									<tr class="subHead" >
										<th>
											Description
										</th>
										<th width="80">
											Amount (INR)
										</th>			
									</tr>
								</tbody>
							</table>
							<h5 class="paySchTitle">Government Levies</h5>      
							<table class="table table-bordered bgWhite" style="width:100%; font-size:8px !important;">
								<tbody>
									<tr class="subHead" >
										<th>
											Description
										</th>
										<th width="80">
											Amount (INR)
										</th>			
									</tr>
									
								
									
									<tr id="stampDutyColPrint"><th>Stamp Duty</th><td class="txtRight stamp_duty" width="80" style="text-align:right;"></td></tr>
									<tr id="regChargeColPrint"><th>Registration Charges</th><td  class="txtRight registrationCharges" style="text-align:right;">0</td></tr>
									<tr><th>Goods and Service Tax (GST) </th><td class="txtRight goodsNServiceTax" style="text-align:right;"></td></tr>
									<tr><th  class="subHead">Total Govt. Levies incl. GST (C)</th><th class="txtRight total_flat_cost" style="text-align:right;"></th></tr>
								</tbody>
							</table>
							<table class="table table-bordered  bgWhite" style="width:100%">
								<tbody>
									<tr>
										<th class="subHead" style="text-align:left; font-size: 10px; font-weight: normal;">
											Total Sale Price including Government Levies (A+B+C)
										</th>
										<th class="totalABC" width="80" align="right" style="text-align:right !important; font-size: 10px;"> 
											
										</th>
									</tr>
								</tbody>
							</table>
							
							<div id="totalDiscountCol">
								<!-- <table class="table table-bordered  bgWhite" style="width:100%">
									<tbody>
										<tr>
											<th class="subHead" style="text-align:left; font-size: 10px; font-weight: normal;">
												Total Discount
											</th>
											<th class="totalDiscount" width="80" align="right" style="text-align:right !important; font-size: 10px;">
												
											</th>
										</tr>
									</tbody>
								</table> -->	
							</div>
							
							
							
						</div>	
						
						
						<!-- <div>
							<table class="table table-bordered  bgWhite" style="margin-top:10px; width:100%">
								<tbody>
									<tr>
										<th class="subHead" style="text-align:left; font-size: 10px; font-weight: normal;">
											Total Consideration including Government Levies (A+B+C)
										</th>
										<th width="80" style="text-align:center; font-size: 10px; font-weight: normal;"> 
											<div  class="txtCenter totalABC"></div>
										</th>
									</tr>
								</tbody>
							</table>
						</div> -->
						<div class="printFooter">
							
						 	<table class="table table-bordered  bgWhite" style="width:100%;">
								<tbody>
									<tr>
										<td colspan="3" class="subHead">
											<h5>Terms & Conditions</h5>
										</td>
									</tr>
									<tr>
										<td colspan="3" class="tncData">									
											
										</td>
									</tr>
									<tr>
										<td style="font-size: 8px !important; padding-top:10px; padding-bottom:10px;">
											<div class="printSignature">
												<span class="psTxt">Primary Applicant:</span>
												<span class="psSignBlock"></span> 
												<div class="clearfix"></div>
											</div>
										</td>
										<td style="font-size: 8px !important; padding-top:10px; padding-bottom:10px;">
											<div class="printSignature">
												<span class="psTxt">2nd Applicant:</span>
												<span class="psSignBlock"></span> 
												<div class="clearfix"></div>
											</div>
										</td>
										<td style="font-size: 8px !important; padding-top:10px; padding-bottom:10px;">
											<div class="printSignature">
												<span class="psTxt">3rd Applicant:</span>
												<span class="psSignBlock"></span> 
												<div class="clearfix"></div>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						 	<div class="clearfix"></div>
						 </div>
						 
						 <div>
						 	<div> 
						 		<div id="csCommitmentTxt"></div>
						 	</div>
						 </div>
						 
						 
						<div class="clearfix"></div>
					</div>
				  	<!-- END For Print -->
				  
				  
				  
				  
				  
				  
				  
				  
				  
				  
				  
				  
				  
				  
				  
				 
				  
				  <!-- Left Table -->
				  <div class="col-md-6 printLeftTbl">
				  	<!-- Customer Details -->
				  	<!-- <div id="customerDtl">
				  		<h4 class="">Customer Details</h4>
					  	<div class="">	
					  		<table class="table table-bordered bgWhite">
							<tbody>
								<tr>
									<th class="subHead">Customer Name</th>
									<td class="customerName"></td>
							</tr>
							<tr>
								<th class="subHead">Contact No.</th>
								<td class="customerContact"></td>
							</tr>
								</tbody>
							</table>
							<div class="clearfix"></div>
						</div>	
				  		<div class="clearfix"></div>
				  	</div> -->
				  	<!-- END Customer Details -->
				  	
					<div class="printBasicDetails" id="unitDtls">
					<h4 class="">Unit Details</h4>
					
					<div class="">
						<table class="table table-bordered bgWhite">
						<tbody>
							<tr>
								<th class="subHead">Tower</th>
								<td class="animated"><span id="towerTval"></span><span id="wingVal"></span></td>
								
								<th class="subHead">Carpet Area Rera (in Sq.mtr.)</th>
								<td id="carpetSqm" class="animated"></td>
								<!-- <th>Cost of Carpet Area (A.) (In INR)</th>
							<td class="animated" id="CostofCarpet"></td> -->
						</tr>
						<tr>
							<th class="subHead">Unit Number</th>
							<td class="animated">
								<span id="unitTval"></span>
							</td>
							<th class="subHead">Exclusive Area (in Sq.mtr.)</th>
							<td id="balTerSqm" class="animated"></td>
							<!-- <th>Cost of Exclusive Balcony Area/Terrace Area (B.) (In INR)</th>
							<td class="animated" id="CostofExcBalcony"></td> -->
						</tr>
						<tr>
							<th class="subHead">Floor</th>
							<td id="floorTval"></td>
							<th class="subHead">Total Area (in Sq.mtr.)</th>
							<td id="totalSqm"></td>
						</tr>
						<tr>
							<th class="subHead">Typology</th>
							<td id="typologyTval" class="animated"></td>
							<th class="subHead noOfCarParkLabel">No. of Car Park</th>
							<td id="noOfCarPark"  class="ifCarParkZero"></td>
						</tr>
						
						
						<tr>
							<th class="subHead">Carpet Area Amount</th>
							<td class="carpetAreaAmount" id="carpetAreaAmount" style="text-align:right;">
							
							
							<th class="subHead craParkTypeLabel">Car Park Type</th>
							<td id="carParks"  class="ifCarParkZero">-</td>
							<!-- <th class="subHead">View Facing</th>
							<td id="" class="animated"> </td> -->
							<!-- <th class="subHead"></th>
							<td ></td> -->
						</tr>
						
						<tr>
							<th class="subHead">Exclusive Area Amount</th>
							<td class="exclusiveAreaAmount" id="exclusiveAreaAmount" style="text-align:right;" ></td>
							<th class="subHead"></th>
							<td></td>
						</tr>
						
						<tr class="facingCSCol">
							<th class="subHead">Facing</th>
							<td class="propFacingType" style="text-align:left;" ></td>
							<th class="subHead"></th>
							<td></td>
						</tr>
						
						
							</tbody>
						</table>
						<div class="clearfix"></div>
					</div>
					
						
						<div class="clearfix"></div>
					 </div>
				  	
				  	<div class="scrollYauto">
					<!-- PAYMENT SCHEDULE -->
					<h4 class="flipInX paySchTitle">Payment Plan</h4>      
					<table class="table table-bordered bgWhite" id="paymentPlanOtherCharges">
						<tbody>
							<tr class="subHead" id="ppOtherChargesHeader">
								<th>Milestone</th> 
								<th width="50"> % </th>
								<th>Amount (INR)</th>  
								
								<!-- <th>BSP GST (INR)</th>
								<th>OC GST (INR)</th> -->
								
								<th>GST (INR)</th> 
								
								
								
								<th>Total</th>
							</tr>
						</tbody>
					</table>
					<div class="tableNoRec">
						No record found
					</div>
					<div class="clearfix"></div>
				</div>	
				  	<div class="clearfix"></div>
				  </div>
				  <!-- END Left Table -->
				  
		<!-- Right Table -->
		<div class="col-md-6 printRightTbl">
				  
				  	
			<!-- Dynamic Other Charges part 2 20190522 -->
			<div class="scrollYauto csV2">
				<h4 class="">Sale Consideration</h4>
				<table class="table table-bordered  bgWhite" id="salesConsideration">
					<tbody>
						<tr class="subHead" >
							<th>
								Description
							</th>
							<th width="80">
								Amount (INR)
							</th>			
						</tr>
					</tbody>
				</table>
				<div class="tableNoRec">
					No record found
				</div>
				<div class="clearfix"></div>
			</div>
			<!-- END Dynamic Other Charges part 2 20190522 -->
				 
				 
			<!-- Other Charges No COP -->	 
			<div class="scrollYauto csV2">
				<h4 class="">Estimated Other Charges</h4>
				<table class="table table-bordered  bgWhite" id="tentativeCharges">
					<tbody>
						<tr class="subHead" >
							<th>
								Description
							</th>
							<th width="80">
								Amount (INR)
							</th>			
						</tr>
					</tbody>
				</table>
				<div class="tableNoRec">
					No record found
				</div>
				<div class="clearfix"></div>
			</div>	 
			<!-- END Other Charges No COP -->	 
				 
				 
			
			
			<!-- Other Charges No COP -->	 
			<div class="scrollYauto csV1" style="display:none;">
				<h4 class="">Sales Consideration</h4>
				<table class="table table-bordered  bgWhite" id="newOtherCharges2">
					<tbody>
						<tr class="subHead">
							<th>
								Parameter
							</th>
							<th>
								Charge Amount (INR)
							</th>
							<th>
								GST Rate
							</th>	
							<th>
								GST Amount (INR)
							</th>
							<th>
								Total (INR)
							</th>				
						</tr>
					</tbody>
				</table>
				<div class="tableNoRec">
					No record found
				</div>
				<div class="clearfix"></div>
			</div>
			<!-- END Other Charges No COP -->	 
		
				 
				 
			<div class="scrollYauto" id="govt_charges">
				<h4 class="flipInX animated paySchTitle">Government Levies</h4>      
				<table class="table table-bordered bgWhite" id="otherGovtCharges">
					<tbody>
						<tr class="subHead" >
							<th>
								Description
							</th>
							<th width="80">
								Amount (INR)
							</th>			
						</tr>
						<tr id="stampDutyCol"><th>Stamp Duty</th><td class="txtRight" id="stamp_duty" width="80"></td></tr>
						<tr id="regChargeCol"><th>Registration Charges</th><td  class="txtRight" id="registrationCharges">0</td><tr>
						
						<tr><th>Goods & Service Tax (GST)</th><td class="txtRight" id="goodsNServiceTax"></td><tr>
						
						<tr><th  class="subHead">Total Govt. Levies incl. GST (C)</th><th class="txtRight" id="total_flat_cost"></th><tr>
					</tbody>
				</table>
				<div class="clearfix"></div>
			</div>	 
				
			<div>
			 	<table class="table table-bordered  bgWhite">
					<tbody>
						<tr>
							<th class="subHead">
								<h5 class="txtLeft">Total Sale Price including Government Levies (A+B+C)</h5>
							</th>
							<th style="width: 135px; vertical-align: middle;" >
								<h5 id="totalABC" style="font-weight:700" class="txtRight"></h5>
								<h5 id="totalABCnoCurrency" style="font-weight:700; display:none;" class="txtRight"></h5>
							</th>
						</tr>
						
					</tbody>
				</table>
			 	<div class="clearfix"></div>
			 </div> 	
				 
				 
			<div id="totalDicountView">
				<div style=" border: dotted #4b4b4b;  padding: 10px; text-align: center; background-color: #fff; ">
					Total Sales Consideration is calculated after applying discount of Rs. 
					<label style="display: block;" id="totalDiscount"> </label>
					<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
			</div>	 
				 
				 
			<!-- <div>	
				<table class="table table-bordered  bgWhite">
					<tbody>
						<tr>
							<th class="subHead">
								<h5 class="txtLeft">Total Discount</h5>
							</th>
							<th style="width: 135px;">
								<h5 class="txtRight" id="totalDiscount" style="font-weight:700"></h5>
							</th>
						</tr>	
					</tbody>
				</table>		
			</div> -->	  
				
				  	<div class="clearfix"></div>
				  </div>	
				  <!-- END Right Table -->
				  
				  
				 
				  
				  
				<div class="clearfix"></div>  
				  
				  
				 
				 
				 <div class="col-md-12 printFooter">
				 	<table class="table table-bordered  bgWhite">
						<tbody>
							<tr>
								<td class="subHead">
									<h4>Terms & Conditions</h4>
								</td>
							</tr>
							<tr>
								<td class="tncData">									
									<div class="printSignature" style="display:none;">
										<span class="psTxt">Signature:</span>
										<span class="psSignBlock"></span> 
										<div class="clearfix"></div>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
					<div class="tableNoRec" style="display:none; margin-top: -20px; margin-bottom: 20px;">
						No record found
					</div>
				 	<div class="clearfix"></div>
				 </div>
				 	
					<div class="clearfix"></div>
				</div>
				
				
			
				<!-- Button trigger modal -->
				<!-- <div class="col-md-12">	
					<a type="button" class="" data-toggle="modal" data-target="#tnc">
					   Terms and Condition 
					</a>
					<div class="clearfix"></div>
				</div>
				<div class="modal fade" id="tnc" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				  <div class="modal-dialog" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				        <h4 class="modal-title" id="myModalLabel">Terms and Condition</h4>
				      </div>
				      <div class="modal-body tncData">
				       
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				      </div>
				    </div>
				  </div>
				</div> -->
				
				<div class="clearfix"></div>
					
					<div class="col-md-12" id="csPtContainer">
						<p onclick="addMorePtBtn()">
							<i class="fa fa-plus-circle"></i> Add More
						</p>
						<table class="table table-bordered  bgWhite" id="csPtCol">
							<tbody>
								<tr class="subHead">
									<th>
										
									</th>
									<th style="width:130px;">
										Payment Type <strong class="mndt">*</strong>
									</th>
									<th style="width:120px;">
										Bank Name <strong class="mndt">*</strong>
									</th>
									<!-- <th style="width:100px;">
										Branch <strong class="mndt">*</strong>
									</th> -->
									<th style="width:150px;">
										Transaction ID <strong class="mndt">*</strong>
									</th>
									<th style="width:70px;">
										Transaction</br>Date <strong class="mndt">*</strong>
									</th>
									<th>
										Transaction Amount <strong class="mndt">*</strong>
									</th>
									
									<!-- <th style="width:250px;">
										PAN Attachment
									</th> -->
									<th style="width:200px;">
										Receipt/</br>Cheque Attachment
									</th>
									
									<th style="width:150px;">
										Description
									</th>
									<th style="width:32px;"></th>
								</tr>
								
								<!-- <tr class="csPtDataRow">
									<td class="txtCenter">
										<input type="checkbox" class="paymentCScheck" checked>
									</td>
									<td>
										<input class="csPtEnqSfid" style="display:none;" />
										<select onchange="csPtDd(this)" class="full form-control input-sm csPtDropDown requiredField">
											<option value="">Select</option>
											<option value="Cheque">Cheque</option>
											<option value="NEFT">NEFT/Credit</option>
											<option value="Swipe">Swipe</option>
										</select>
									</td>
									<td>
										<input class="full form-control input-sm csPtBankName requiredField" placeholder="Bank Name"/>
									</td>
									<td>
										<input class="full form-control input-sm csPtBranch requiredField" placeholder="Branch Name"/>
									</td>
									<td>
										<input class="full form-control input-sm csPtTransactionId requiredField" placeholder="Transaction ID"/>
									</td>
									<td>
										<input type="date" class="full form-control input-sm csPtTransactionDate requiredField" placeholder="Transaction Date"/>
									</td>
									<td>
										<input class="numericField full form-control input-sm csPtTransactionAmount requiredField" onkeyup="csPtcalculateGrandTotal()" name="amount" placeholder="Transaction Amount"/>
									</td>
									
									<td>
										<input type="file" class="full form-control input-sm panAttach requiredField"/>
									</td>
									<td>
										<input type="file" class="full form-control input-sm receiptAttach requiredField"/>
									</td>
									
									<td>
										<textarea class="full form-control input-sm csPtDescription" placeholder="Description"></textarea>
									</td>
									<td> </td>
								</tr> -->
								
							</tbody>
						</table>
						
						<!-- <div>
							<span>Total transaction amount: </span><span id="csPtGrandtotal"></span>
						</div> -->
						
						<!--  <button onclick="csPymtData ('123')">Test Btn</button> -->
						
						<div class="clearfix"></div>
					</div>
					<div class="clearfix"></div>
				
					
					<!-- by A - Webcam attachment -->
					<!--  <div class="col-md-6 col-xs-12">
				       <div id="my_camera"></div><br/>
				          <input type=button value="Take Snapshot" class="btn btn-primary pull-right" onClick="take_snapshot()">
				          <input type="hidden" name="image" class="image-tag">
				     </div>
				     <div class="col-md-6 col-xs-12">
				       <div id="results">
				        
				       </div>
				     </div> -->
					<!-- END by A - Webcam attachment -->
				
				
				
				
					<div class="clearfix"></div>
					<div class="row" style="margin-top:20px;">
						<div class="col-md-12 col-sm-12">
							<div class="col-md-9">
								<h4>Sales Comments: </h4>
								<textarea class="form-control" id="costsheet_commitment" rows="1" style="background-color:#fff;"></textarea>
								<div class="clearfix"></div>
							</div>
							
							<div class="col-md-3" style="display:none;">	
								<h4>Balance Amount : </h4>
								<input type="number" id="balance_amnt" class="form-control">
								<div class="clearfix"></div>
							</div>
							<div class="col-md-9" id="csDescriptionCol">
								<h4>Description : </h4>
								<textarea class="form-control" id="balance_amnt_description" rows="" style="background-color:#fff;"></textarea>
								<div class="clearfix"></div>
							</div>
							<div class="clearfix"></div>
							
							
							<!-- Do not remove -->
							<div class="form-group col-md-3 col-sm-6" id="tdsPaidByDDCol">
								<h4 >TDS paid by <strong class="mndt">*</strong> </h4> 
								<select class="full form-control requiredField" id="tdsPaidByDD">
									<option value="">Select</option>
									<option value="Authorization to Company">Authorization to Company</option>
									<option value="Self">Self</option>
								</select>
							</div>
							
							<div class="clearfix"></div>
						</div>
						<div class="clearfix"></div>
					</div>
					
					
					<!-- <button onclick="csPymtData ('123456')">Test</button> -->
					 
					
					<div class="clearfix"></div>
					
					<div class="col-md-12" id="costsheetError" style="display: none; margin-top: 20px;" >
						<div class="alert alert-danger" style="margin-bottom:0 !important;">
						  	Kindly fill the <strong>required</strong> fields.
						</div>
			    	</div>
					
					<div class="clearfix"></div>
					
					<div class="bottomBar" id="updateBtnCol" style="margin-top: 30px; position: inherit; box-shadow: none;">
						<div style="margin-bottom:10px;">
							<span>Total transaction amount: </span><strong id="csPtGrandtotal"></strong>
						</div>
						
						<button class="btn btn-primary btnCommon creatOfferBtnCommon" id="updateCRM" ><span class="spanLoad"></span> Create Offer for : <span class="customerNameBtn"></span></button>
						<!-- <a id="btnkyclink" class="btn btn-primary btnCommon" onclick="generateKYCLink(event,this,'N');"> <span>Generate
							KYC Link</span>
						</a> -->
						<div id="otpApprovalCol" style="display:none; margin-top:20px;">
							<div style="margin-bottom:20px;">
								<input type="" placeholder="OTP"  onkeyup="otpStatus()"  id="otpInput" class="form-control input-sm" style="width: 100px; display: initial; display:none;" /> 
								<button onclick="otpRequestOC()" id="sendOTPBtn" class="btn btn-primary btnCommon input-sm" style="margin-top:20px; padding: 0px 10px;">Get Approval OTP for Request</button> 
								<button onclick="otpRequestOC()" class="btn btn-primary btnCommon input-sm" id="resendOtpOC" style="display:none; padding: 0px 10px;">Resend OTP</button>
								<div class="clearfix"></div>
								<div id="otpStatusText"></div>
								<div class="clearfix"></div>
								
								<div id="offerOPTInfo"></div>
								<div class="clearfix"></div>
							</div>
							<div class="clearfix"></div>
							<button class="btn btn-primary btnCommon creatOfferBtnCommon" id="snedApproval"  style="display:none;"><span class="spanLoad"></span> Create Offer for : <span class="customerNameBtn"></span></button>
							<div class="clearfix"></div>
						</div>
						<div class="clearfix"></div>
					</div> 
					
				<div class="clearfix"></div>
				
			</div>
		
		</div>
		
	</div>
	<!-- END costsheet -->
	

	<!-- Nav tabs -->
	<!-- <ul class="nav nav-tabs" role="tablist">
	  <li role="presentation" class="active"><a href="#costsheet" aria-controls="costsheet" role="tab" data-toggle="tab">Costsheet</a></li>
	 <li onclick="getRqstForSales()" role="presentation"><a href="#listSales" aria-controls="listSales" role="tab" data-toggle="tab">Requests</a></li>
	</ul> -->
	
	<!-- Tab panes -->
	<!-- <div class="tab-content costsheetTabCont">
		<div role="tabpanel" class="tab-pane active" id="costsheet">
			
		</div>
		<div role="tabpanel" class="tab-pane" id="listSales">
			<div class="col-md-12 col-sm-12">
				<h3 class="animated">Pending Requests</h3>
				<table class="table table-bordered" id="salesRqst">
					<thead>
						<tr class="">
							<th>Scheme Type</th>
							<th>Discount</th>
							<th>ID</th>
							<th>Name</th>
							<th>Mobile</th>
							<th>Enquiry ID</th>
							<th>Other Discount</th>
							<th>Request From</th>
							<th>Request Date</th>
							<th>View</th>
							<th>Manager Status</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody> </tbody>
				</table>
				<div class="clearfix"></div>
			</div>
		</div>
		<div class="clearfix"></div>
	</div> -->
	
	
	
</div>
 


<audio id="audio" src="<c:url value='/resources/css/camera-shutter-click-01.wav' />" autostart="false" ></audio>
<!-- Webcam Modal -->
<div class="modal fade" id="webcamBox" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog" role="document" style="width:1200px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" onclick="closeWebcamModel()" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Webcam</h4>
      </div>
      <div class="modal-body">
        <div class="col-md-6 col-xs-12">
	       <div id="my_camera"></div><br/>
	       		<input id="webAttachmentRowID" style="display:none;"/>
	          
	          <div id="snapshotBtnCol" class="pull-left">
	          		
	          </div>
	          <div class="pull-left" style="margin-left:20px;">
	       		<button type="button" class="btn btnDefaultBlue btn-default" onclick="closeWebcamModel()">Close</button>
	       		</div>
	          <div class="clearfix"></div>
	          
	          <input type="hidden" name="image" class="image-tag">
	          <div class="clearfix"></div>
	     </div>
	     <div class="col-md-6 col-xs-12">
	       <div id="results">
	         <!-- Your captured image will appear here... -->
	       </div>
	       <div class="clearfix"></div>
	     </div>
	     <div class="clearfix"></div>
      </div>
      <!-- <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div> -->
    </div>
  </div>
</div>
<!-- END Webcam Modal -->