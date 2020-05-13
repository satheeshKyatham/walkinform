<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<input type="hidden" id="enquirysfid">	
<input type="hidden" id="enquiry_name">
	
	<div class="clearfix"></div>
	
	<div class="col-md-12" style="margin: 0 auto; float: none;">
		
		<div class="mainCont " style="padding-top: 10px;">
			
			
				 
				<div id=""> <!-- enqsfidInputField -->
					<div class="form-group col-md-12">
						<div class="row">
							  <div class="form-group col-md-3">
							    <label>ENQ Name</label>
							    <div class="input-group">
							    	<div class="input-group-addon">ENQ -</div>
							      	<input type="text" class="form-control" id="enqNameInputEOI">
							      	<div class="input-group-addon" onclick="enqDtlForAdminEOI();">
										<i class="glyphicon glyphicon-search"></i>
									</div>
							    </div>
							    <div class="clearfix"></div>
							  </div>
							  <div class="col-md-9" style="padding-top: 22px;">
							  	 <div class="form-group" id="cancelEOICol" style="float: right; margin-left:20px; display:none;">
								  	<button class="btn btn-primary blue_btn" onclick="deleteEOI();" style="background-color: #ce1818 !important; color: #fff; ">
								  		Cancel EOI
								  		<!-- <i class="fa fa-spinner fa-spin" style="display: none; color: #fff !important;"></i> -->
								  	</button>
								  </div>
								  
							  	 <div class="form-group" id="generateEOICol" style="display:none; float: right;">
								  	<button onclick="generateEOIForm();" class="btn btn-primary blue_btn">
								  		Generate EOI Form
								  		<i class="fa fa-spinner fa-spin" style="display: none; color: #fff !important;"></i>
								  	</button>
								  </div>
								  <div class="clearfix"></div>
							  </div>
							 
							  <div class="clearfix"></div>
					  	</div>
					  	<div class="clearfix"></div>
					</div>
					<div class="clearfix"></div>
					<div class="col-md-12">
						<table class="table table-bordered" id="enqDtlTableEOI">
							<thead>
								<tr>
									<th>ENQ ID</th>
									<th>ENQ Name</th>
									<th>Mobile</th>
									<th>Name</th>
								</tr>
							</thead>
							<tbody>
								<tr><td colspan='4' style="text-align:center;">No records found</td></tr>
							</tbody>
						</table>
						<div class="clearfix"></div>
					</div>
					<div class="clearfix"></div>
				</div>
				
				
				<div class="col-md-12" style="margin-bottom:20px;">
					<h4 class="mrgT0">
						Preference Details
					</h4>
					
					<table class="table table-bordered  bgWhite mrgB8" id="EOIMultipleTable">
							<thead>
								<tr class="subHead">
									<th> Tower </th>
									<th> Typology </th>
									<th> Unit </th>
									<th> Floor Band </th>
									<th> Car Park Type </th>
									<th> Description </th>
									<th style="width:32px;"></th>
								</tr>
							</thead>
							<tbody>
								
							</tbody>
					</table>
				</div>
				
				
				<div class="col-md-12" style="margin-bottom:50px;">
					<h4 class="mrgT0">
						Payment Details
					</h4>
					
					<table class="table table-bordered  bgWhite mrgB8" id="csPtColEoi">
						<thead>
							<tr class="subHead">
								<th>Status</th>
								<th style="width:130px;"> Payment Type </th>
								<th style="width:120px;"> Bank Name </th>
								<th> Transaction ID </th>
								<th> Transaction</br>Date </th>
								<th> Transaction Amount </th>
								<th style="width:200px;"> Receipt/</br>Cheque Attachment </th>
								<th style="width:150px;"> Description </th>
								<th style="width:32px;"></th>
							</tr>
						</thead>
						<tbody>
							
						</tbody>
					</table>
					
					<div class="clearfix"></div>
				</div>
			
			<div class="clearfix"></div>
		</div>
		
		<div class="clearfix"></div>
	</div>
	
	
	
	
	
	
	
	
	
	
<!-- EOI Print Form -->

<div id="printEOIForm" class="col-md-8" style="display:none; border: 1px dotted #ccc; margin: 0 auto; float: none; margin-top: 40px; padding-top: 15px;">
		<!-- <div style="padding-bottom:10px;"><b style="font-size:18px; text-align:center;">EOI Form: <span id="projectNameEOIPrint"></span></b></div> -->
		
		<table class="table table-bordered" style="margin-bottom:20px; border-color: #ffffff; padding:0px;">
		    <tbody style="border-color: #ffffff; padding:0px;">
		    	<tr style="border-color: #ffffff; padding:0px;">
		    		<td style="border-color: #ffffff; padding:0px;"><b style="font-size:18px; text-align:left;">EOI Form: <span id="projectNameEOIPrint"></span></b></td>
		    		<td class="dateOfEOIPrint" style="text-align:right; border-color: #ffffff; padding:0px;"></td>
		    	</tr>
		    </tbody>
		 </table>
		
		
		<div style="padding-bottom:10px;"><b>Applicant Details</b></div>
		<table id="" style="margin-bottom:20px;" class="table table-bordered">
		    <tbody>
				<tr>
					<td>Name of Primary Applicant</td> <td id="priAppNameEOIPrint"></td>
				</tr>
				<tr>
					<td>Email Address of Primary Applicant</td> <td id="priAppEmailEOIPrint"></td>
				</tr>
				<tr>
					<td>Mobile No. of Primary Applicant</td> <td id="priAppMobileEOIPrint"></td>
				</tr>
				<tr>
					<td>Name of Second Applicant</td> <td id="secondAppNameEOIPrint"></td>
				</tr>
				<tr>
					<td>Name of Third Applicant</td> <td id="thirdAppNameEOIPrint"></td>
				</tr>
				
				
				<tr>
					<td>PAN Card No.</td> <td id="priAppPANEOIPrint"></td>
				</tr>
				<tr>
					<td>Aadhar Card No.</td> <td id="priAppAadharEOIPrint"></td>
				</tr>
				<tr>
					<td>Residence Address</td> <td id="priAppResAddsEOIPrint"></td>
				</tr>
				
				
				<tr>
					<td>Area / Location</td> <td id="priAppAreaLocationEOIPrint"></td>
				</tr>
				<tr>
					<td>City</td> <td id="priAppCityEOIPrint"></td>
				</tr>
				<tr>
					<td>PIN Code.</td> <td id="priAppPinCodeEOIPrint"></td>
				</tr>
		    </tbody>
		  </table>
		
		<table style="margin-bottom:20px;" class="table table-bordered">
			<tr>
				<td colspan='2'><b>EOI Token Type:</b> <span id="tokenTypeEOIPrint"></span></td>
			</tr>
		</table>
		<div  style="padding-bottom:10px;"><b>Preference(s)</b></div>
		<table id="EOIMultipleTablePrint" style="margin-bottom:20px;" class="table table-bordered">
		    <thead>
				<tr>
					<th>Typology</th>
					<th>Ticket Size</th>
					<th>Floor Band</th>
					<th>Tower</th>
					<th>Unit No.</th>
					<th>Car Park Type</th>
					<th>Description</th>
		      	</tr>
		    </thead>
		    <tbody>
		      
		    </tbody>
		 </table>
	
	
		<div style="padding-bottom:10px;"><b>Payment Details</b></div>
		<table id="EOIMultiplePaymentPrint" style="margin-bottom:20px;" class="table table-bordered">
			<thead>
				<tr>
					<th>Name of Payer</th>
					<th>Mode of Payment</th>
					<th>Amount (in Rs.)</th>
					<th>Bank Name</th>
					<th>Branch Name</th>
					<th>Cheque No./ Txn ID</th>
					<th>Description</th>
		      	</tr>
			</thead>
		    <tbody>
		      
		    </tbody>
		</table>
		
		<table style="margin-bottom:20px;" class="table table-bordered">
			<tr>
				<td colspan='2'><b>Closing Manager Name:</b> <span id="closingMangrEOIPrint"></span></td>
			</tr>
			<tr>
				<td style="height:40px; width:60%;"><b>Signature:</b></td>
				<td><b>Date:</b> <span class="dateOfEOIPrint"></span></td>
			</tr>
		</table>
		
		
		<table id="" style="margin-bottom:20px;" class="table table-bordered">
		    <tbody>
				<tr>
					<td><b>Source:</b> <span id="enqTypeEOIPrint"></span></td>
					<td><b>Vertical:</b> <span id="verticalEOIPrint"></span></td>
				</tr>		
				<tr>	
					<!-- <th colspan="2">RERA ID (Channel Partner):</th> -->
					<td colspan="2"><b>Channel Partner :</b>  <span id="channelpartnerNameEOIPrint"></span></td>
		      	</tr>
		      	<tr>	
					<td colspan="2"><b>Sourcing Manager Name:</b>  <span id="sourcingMngrEOIPrint"></span></td>
		      	</tr>		      
		    </tbody>
		</table>
		<div style="page-break-before: always"></div>
		<table class="table table-bordered">
			<tbody>
				<tr>
					<th>
						Terms and Conditions
					</th>
				</tr>
				<tr>
					<td id="tncDataEOI">									
					</td>
				</tr>
			</tbody>
		</table>
</div>
<!-- END EOI Print Form -->