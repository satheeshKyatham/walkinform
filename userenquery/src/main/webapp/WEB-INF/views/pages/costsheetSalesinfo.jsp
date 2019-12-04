<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@page import="java.util.HashMap"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



	<div style="display:none;">
	
		<input id="paymentPlanId" value="a1l6F000004QtUZQA0">
		<div id="paymentPlanChangeDiv"></div>
		<input id="BSPrate" style="">
		
		<input id="timeid"  style="">
		
		
		<%-- <input id="token" value="${token}">
		<input id="projectsfid" value="${projectsfid}" />
		<input id="enquirysfid" value="${enquirysfid}" />
		<input id="primarycontactsfid" value="${primarycontactsfid}" /> --%>
		
		<input id="token" value="7">
		<input id="projectsfid" value="a20O0000002XKut" />
		<input id="enquirysfid" value="a29O000000AqL4M" />
		<input id="primarycontactsfid" value="003O000000whPJ3" /> <!-- 003O000001Dhw9r -->
		
		<input id="pageContext" value="${pageContext.request.contextPath}"/>
	
	
		<input id="costofCarpet"  />
		<input id="costofBalCony"  />
		<input id="costCom"/>	
		<input id="totalTaxableAmtInput"  />
		
		
		<input id="carParkAmount" />
	</div>
	
	
	
	
	<div class="commonLoad" id="mainLoad" style="display:none;"></div>
	
	
	
  
  
  
<div>

	<!-- <a class="printFloat vpdfDis" target="_blank" id="viewPDF">
		<i class="glyphicon glyphicon-print printficon"></i>
	</a> -->
	
	
	<a class="printFloat" onclick="printDiv('printableArea')" value="print a div!">
		<i class="glyphicon glyphicon-print printficon"></i>
	</a>
	
	
	<!-- Costsheet -->
	<div id="costsheet">
			<div class="inventoryWrp" style="margin-bottom:0 !important;">
				<div class="row">
					<div class="col-md-8">
						<div class="filterCSCol">
							<input type="text" id="unitSfid" style="display:none;"/> 
							<input type="text" id="towerSfid" style="display:none;"/> 
							<input type="text" id="bspGSTTax" style="display:none;"/> 
							
							<div class="form-group col-md-4 col-sm-6">
								<label >Scheme </label> 
								<select class="full form-control" id="getPln" onchange="schemeType(this)">
									<option value="0">Select</option>
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
							
							<div id="newPlan"></div>
							
							<div class="form-group col-md-4 col-sm-6">
								<label >Car Park Type </label> 
								<select class="full form-control" id="carParkType">
									<option value="">Select</option>
									<option value="covered">Covered Car Park</option>
									<option value="open">Open Car Park</option>
									<option value="Covered/Stacked">Covered/Stacked</option>
								</select>
							</div>
							
							
							<div class="form-group col-md-4">
								<label >Payment Plan </label> 
								<select class="full form-control" id="ppDropdown">
									<option value="">Select</option>
								</select>
							</div>
							<div class="col-md-12">
								<!-- <button class="btn btn-primary" onclick="location.reload()"><i class="glyphicon glyphicon-repeat"></i></button> -->
								<button class="btn btn-primary btnCommon" id="getCSData" style="font-size: 11px;">Get Details</button> 
								<!-- <button class="btn btn-primary btnCommon" id="zzzzTestCharges">Dynamic Other Charges</button> -->
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
				
				<h4 class="printTitle" style="display:none;">
					PRICE AND PAYMENT SCHEDULE
				</h4>
				
				<div class="clearfix"></div>
				  <!-- <div class="noPrintBasicDetails">
				  <div class="col-md-4 col-sm-4">
						<table class="table table-bordered bgWhite">
							<tbody>
								<tr>
									<th>Unit Number</th>
									<td class="animated" id="unitTval"></td>
								</tr>
								<tr>
									<th>Tower</th>
									<td class="animated" id="towerTval"></td>
								</tr>
								<tr>
									<th>Floor</th>
									<td id="floorTval"></td>
								</tr>
								<tr>
									<th>Typology</th>
									<td id="typologyTval" class="animated"></td>
								</tr>
							</tbody>
						</table>
						<div class="clearfix"></div>
					</div>
					
					<div class="col-md-4 col-sm-4">
						<table class="table table-bordered bgWhite">
							<tbody>
								<tr>
									<th>Carpet Area  (Sq.m)</th>
									<td id="carpetSqm" class="animated"></td>
								</tr>
								<tr>
									<th>Exclusive Balcony Area/Terrace Area(Sq.m)</th>
									<td id="balTerSqm" class="animated"></td>
								</tr>
								<tr>
									<th>No of Car parks</th>
									<td id="carParks"  class="animated">1</td>
								</tr>
							</tbody>
						</table>
						<div class="clearfix"></div>
					</div>
				  
					<div class="col-md-4 col-sm-4">
						<table class="table table-bordered bgWhite">
							<tbody>
								<tr>
									<th>Cost of Carpet Area (A.) (In INR)</th>
									<td class="animated" id="CostofCarpet"></td>
								</tr>
								<tr>
									<th>Cost of Exclusive Balcony Area/Terrace Area (B.) (In INR)</th>
									<td class="animated" id="CostofExcBalcony"></td>
								</tr>
								<tr>
									<th>Proportionate cost of Common Areas (C.) (In INR)</th>
									<td id="PropCost"></td>
								</tr>
							</tbody>
						</table>
						<div class="clearfix"></div>
					</div>
				  </div>
				   -->
				  
				  <div class="printBasicDetails col-md-12 col-sm-12">
				  
						<table class="table table-bordered bgWhite">
							<tbody>
								<tr>
									<th>Unit Number</th>
									<td class="animated" id="unitTval"></td>
									<th>Carpet Area  (Sq.m)</th>
									<td id="carpetSqm" class="animated"></td>
									<!-- <th>Cost of Carpet Area (A.) (In INR)</th>
									<td class="animated" id="CostofCarpet"></td> -->
								</tr>
								<tr>
									<th>Tower</th>
									<td class="animated" id="towerTval"></td>
									<th>Exclusive Balcony Area/Terrace Area(Sq.m)</th>
									<td id="balTerSqm" class="animated"></td>
									<!-- <th>Cost of Exclusive Balcony Area/Terrace Area (B.) (In INR)</th>
									<td class="animated" id="CostofExcBalcony"></td> -->
								</tr>
								<tr>
									<th>Floor</th>
									<td id="floorTval"></td>
									<th>Car park Type</th>
									<td id="carParks"  class="animated"></td>
									<!-- <th>Proportionate cost of Common Areas (C.) (In INR)</th>
									<td id="PropCost"></td> -->
								</tr>
								<tr>
									<th>Typology</th>
									<td id="typologyTval" class="animated"></td>
									<th></th>
									<td></td>
									<!-- <th></th>
									<td></td> -->
								</tr>
							</tbody>
						</table>
						
					<div class="clearfix"></div>
				  </div>
				  
				  
				  
				  
				  
					<!-- Dynamic Other Charges part 2 20190522 -->
			<div class="col-md-12 col-sm-12 scrollYauto">
				<h4 class="">Sales Consideration</h4>
				<table class="table table-bordered mrgB0 bgWhite" id="newOtherCharges2">
					<tbody>
						<tr class="subHead" >
							<th>
								Parameter
							</th>
							<th>
								Charge Amount (INR)
							</th>
							<!-- <th>
								Taxable Amount (INR)
							</th> -->
							<th>
								GST Rate
							</th>	
							<th>
								GST Amount (INR)
							</th>
							<!-- <th>
								SGST Rate
							</th>
							<th>
								SGST Amount (INR)
							</th> -->	
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
			<!-- END Dynamic Other Charges part 2 20190522 -->
					
			
			<div class="col-md-12 scrollYauto">
				<!-- PAYMENT SCHEDULE -->
				<h4 class="flipInX animated paySchTitle">Payment Schedule</h4>      
				<table class="table table-bordered mrgB0 bgWhite" id="paymentPlanOtherCharges">
					<tbody>
						<tr class="subHead">
							<th>Milestone</th> 
							<th>% Amount</th> 
							<th>Charge Amount (INR)</th>  
							<!-- <th>Taxable Amount (INR)</th> --> 
							<!-- <th>CGST Rate</th> -->
							<th>GST Amount (INR)</th> 
							<!-- <th>SGST Rate</th> --> 
							<!-- <th>SGST Amount (INR)</th>
							<th>Total (INR)</th>
							<th>TDS @ 1% (INR)</th> -->
							<th>Total</th>
						</tr>
					</tbody>
				</table>
				<div class="tableNoRec">
					No record found
				</div>
				<div class="clearfix"></div>
			</div>		
			
			<div class="col-md-12 scrollYauto" id="govt_charges">
				<h4 class="flipInX animated paySchTitle">Other Govt. Charges</h4>      
				<table class="table table-bordered mrgB0 bgWhite" id="otherGovtCharges">
					<tbody>
						<tr><th>Stamp Duty</th><th id="stamp_duty"></th></tr>
						<tr><th>Registration Charges</th><th>30000</th><tr>
						<tr><th  class="subHead">Total Flat Cost</th><th id="total_flat_cost"></th><tr>
						
					</tbody>
					<!-- <tbody>
						<tr><td></td><td>30000</td></tr>
					</tbody> -->
				</table>
				<div class="clearfix"></div>
			</div>
			
					
					
					<!-- ------- Payment Schedule ---------- -->
					
					<!-- <div class="col-md-12 col-sm-12 mrgT20 scrollYauto">
						<h4 class="flipInX animated">Payment Schedule</h4>
						<table class="table table-bordered mrgB0 bgWhite" id="paymentSchedule">
							<tbody>
								<tr class="subHead" >
									<th>
										Milestones
									</th>
									<th>
										Charge Amount (INR)
									</th>
									<th>
										Taxable Amount (INR)
									</th>
									<th>
										CGST Rate
									</th>
									<th>
										CGST Amount (INR)
									</th>
									<th>
										SGST Rate
									</th>
									<th>
										SGST Amount (INR)
									</th>
									<th>
										Total (INR)
									</th>
									<th>
										TDS @ 1% (INR)
									</th>
									<th>
										Payable to 
									</th>					
								</tr>
							</tbody>
						</table>
						<div class="tableNoRec">
							No record found
						</div>
						
						<div class="clearfix"></div>
					</div> -->
					<div class="clearfix"></div>
					<div class="row" style="margin-top:20px;">
						<div class="col-md-12 col-sm-12">
							<div class="col-md-3">	
								<h4>Balance Amount : </h4>
								<input type="number" id="balance_amnt" class="form-control">
								<div class="clearfix"></div>
							</div>
							<div class="col-md-9">
								<h4>Description : </h4>
								<textarea class="form-control" id="balance_amnt_description" rows="" style="background-color:#fff;"></textarea>
								<div class="clearfix"></div>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="clearfix"></div>
					</div>
					
					<div class="clearfix"></div>
					
					<div class="bottomBar" id="updateBtnCol" style="margin-top: 30px; position: inherit; box-shadow: none;">
						<button class="btn btn-primary btnCommon" id="updateCRM" >Create Offer</button>
						
						<div id="otpApprovalCol" style="display:none;">
							<div style="margin-bottom:20px;">
								<input type="" placeholder="OTP"  onkeyup="otpStatus()"  id="otpInput" class="form-control input-sm" style="width: 100px; display: initial; display:none;" /> 
								<button onclick="otpRequestOC()" id="sendOTPBtn" class="btn btn-primary btnCommon input-sm" style="padding: 0px 10px;">Get Approval OTP for Request</button> 
								<button onclick="otpRequestOC()" class="btn btn-primary btnCommon input-sm" id="resendOtpOC" style="display:none; padding: 0px 10px;">Resend OTP</button>
								<div class="clearfix"></div>
								<div id="otpStatusText"></div>
								<div class="clearfix"></div>
							</div>
							<div class="clearfix"></div>
							<button class="btn btn-primary btnCommon" id="snedApproval"  style="display:none;">Create Offer</button>
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
 
	