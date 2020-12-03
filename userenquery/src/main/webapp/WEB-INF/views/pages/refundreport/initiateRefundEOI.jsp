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
			
			<div class="">
				
				<div class="form-group col-md-3">
					<label>A/C Holder Name</label>
					<input type="text"/>
				 </div>
				 <div class="form-group col-md-3">
					<label>Bank Name</label>
					<input type="text"/>
				 </div>
				 <div class="form-group col-md-3">
					<label>Branch Name</label>
					<input type="text"/>
				 </div>
				 <div class="form-group col-md-3">
					<label>Account No</label>
					<input type="text"/>
				 </div>
				 <div class="form-group col-md-3">
					<label>IFSC Code</label>
					<input type="text"/>
				 </div>
				 <div class="form-group col-md-3">
					<label>Account Type</label>
					<select id="" class="form-control">
						<option value='NRE'>NRE</option>
						<option value='NRO'>NRO</option>
						<option value='Saving'>Saving</option>
						<option value='Current'>Current</option>
					</select>
				 </div>
				 <div class="form-group col-md-3">
					<label>Upload Cancelled Check</label>
					<input type="file"/>
				 </div>
				 <div class="form-group col-md-3">
					<label>Reason for Cancellation/Refund</label>
					<select id="" class="form-control">
						<option value='Customer Request'>Customer Request</option>
					</select>
				 </div>
				 
				 <div class="form-group col-md-3">
					<label>Description </label>
					<input type="text"/><strong>*</strong>
				 </div>
			
				<div class="clearfix"></div>
			</div>
			<div class="form-group col-md-3">
				<label> &nbsp; </label>
				<button onclick="initiateRefundRequest()" style="line-height:0; color: #fff; background-color: #0077b9;" class="form-control btn btn-primary mrgT0">
					Initiate Cancellation/Refund
				</button>
				
				<h4 class="mrgT0">
					Refund Initiated Log
				</h4>
				
				<table class="table table-bordered  bgWhite mrgB8" id="csPtColRefundIntiatedEoi">
					<thead>
						<tr class="subHead">
							<th>Bank Name</th>
							<th style="width:130px;"> Branch Name </th>
							<th style="width:120px;"> Account No. </th>
							<th> IFSC Code </th>
							<th> Transaction No. </th>
							<th> Cancelled Cheque</th>
							<th style="width:200px;"> Reason for Cancellation </th>
							<th style="width:150px;"> Description </th>
						</tr>
					</thead>
					<tbody>
						
					</tbody>
				</table>
			</div>
			<div class="clearfix"></div>
		</div>
		
		<div class="clearfix"></div>
	</div>
