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
<input type="hidden" id="eoi_trx_amount">
	
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
							      	<input type="text" class="form-control" id="enqNameInputRefund">
							      	<div class="input-group-addon" onclick="enqDtlForAdminRefund();">
										<i class="glyphicon glyphicon-search"></i>
									</div>
							    </div>
							    <div class="clearfix"></div>
							  </div>
					  	</div>
					  	<div class="clearfix"></div>
					</div>
					<div class="clearfix"></div>
					<div class="col-md-12">
						<table class="table table-bordered" id="enqDtlTableRefundEOI">
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
				<div class="col-md-12" style="margin-bottom:50px;">
					<h4 class="mrgT0">
						Payment Details
					</h4>
					
					<table class="table table-bordered  bgWhite mrgB8" id="csPtColRefundEoi">
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
							</tr>
						</thead>
						<tbody>
							
						</tbody>
					</table>
					
					<div class="clearfix"></div>
				</div>
			
			<div id="refund_div_id">
				
				<div class="form-group col-md-3">
					<label>A/C Holder Name<strong class="mndt">*</strong></label>
					<input type="text" id="ref_acholder_name" class="full form-control input-sm requiredField"/>
				 </div>
				 <div class="form-group col-md-3">
					<label>Bank Name<strong class="mndt">*</strong></label>
					<input type="text" id="ref_bank_name" class="full form-control input-sm requiredField"/>
				 </div>
				 <div class="form-group col-md-3">
					<label>Branch Name<strong class="mndt">*</strong></label>
					<input type="text" id="ref_branch_name" class="full form-control input-sm requiredField"/>
				 </div>
				 <div class="form-group col-md-3">
					<label>Account No<strong class="mndt">*</strong></label>
					<input type="text"id="ref_ac_no" class="full form-control input-sm requiredField"/>
				 </div>
				 <div class="form-group col-md-3">
					<label>IFSC Code<strong class="mndt">*</strong></label>
					<input type="text" id="ref_ifsc_code" class="full form-control input-sm requiredField"/>
				 </div>
				 <div class="form-group col-md-3">
					<label>Account Type<strong class="mndt">*</strong></label>
					<select id="ref_ac_type" class="full form-control input-sm requiredField" >
						<option value='NRE'>NRE</option>
						<option value='NRO'>NRO</option>
						<option value='Saving'>Saving</option>
						<option value='Current'>Current</option>
					</select>
				 </div>
				 <div class="form-group col-md-3">
					<label>Upload Cancelled Check<strong class="mndt">*</strong></label>
					<input type="file" id="ref_cncelled_check" class="full form-control input-sm requiredField" accept="application/pdf,image/*"/>
				 </div>
				 <div class="form-group col-md-3">
					<label>Reason for Cancellation/Refund<strong class="mndt">*</strong></label>
					<select id="ref_reason_cancellation" class="full form-control input-sm requiredField" class="form-control">
						<option value='Customer Request'>Customer Request</option>
					</select>
				 </div>
				 
				 <div class="form-group col-md-3">
					<label>Description<strong class="mndt">*</strong> </label>
					<textarea id="ref_description" class="full form-control input-sm requiredField"></textarea>
				 </div>
			
				<div class="clearfix"></div>
			</div>
			<div class="form-group col-md-3">
				<label> &nbsp; </label>
				<button id="initiateRefundRequest_id" onclick="initiateRefundRequest()" style="line-height:0; color: #fff; background-color: #0077b9;" class="form-control btn btn-primary mrgT0">
					Initiate Cancellation/Refund
				</button>
				<!-- <div class="col-md-12">
						<table class="table table-bordered" id="enqDtlTableRefundEOI">
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
					</div> -->
					<div class="clearfix"></div>
				
			</div>
			<div class="clearfix"></div>
			<h4 class="mrgT0 col-md-12">
					Refund Initiated Log
				</h4>
				<div class="clearfix"></div>
				<div class="col-md-12">
				
				
				<table class="table table-bordered  bgWhite mrgB8" id="csPtColRefundIntiatedEoi">
					<thead>
						<tr class="subHead">
							<th>Enquiry No.</th>
							<th>A/C Holder Name</th>
							<th>Bank Name</th>
							<th style="width:130px;"> Branch Name </th>
							<th style="width:120px;"> Account No. </th>
							<th> IFSC Code </th>
							<th> D4U's Transaction No. </th>
							<th> Account Type</th>
							<th> Reason for Cancellation </th>
							<th style="width:250px;"> Description </th>
							<th> Refund Total Amt </th>
							<th> Refund Status </th>
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
