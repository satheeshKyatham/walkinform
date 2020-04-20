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
	<div class="clearfix"></div>
	
	<div class="col-md-12" style="margin: 0 auto; float: none;">
		
		<div class="mainCont " style="padding-top: 10px;">
			
			
				 
				<div id=""> <!-- enqsfidInputField -->
					<div class="form-group col-md-3">
					  <div class="form-group">
					    <label>ENQ Name</label>
					    <div class="input-group">
					    	<div class="input-group-addon">ENQ -</div>
					      	<input type="text" class="form-control" id="enqNameInputEOI">
					      	<div class="input-group-addon" onclick="enqDtlForAdminEOI();">
								<i class="glyphicon glyphicon-search"></i>
							</div>
					    </div>
					  </div>
					</div>
					<div class="clearfix"></div>
					<div class="col-md-12">
						<table class="table table-bordered" id="enqDtlTableEOI">
							<thead>
								<tr>
									<th>ENQ Name</th>
									<th>Mobile</th>
									<th>Name</th>
								</tr>
							</thead>
							<tbody>
								<tr><td colspan='3' style="text-align:center;">No records found</td></tr>
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
						<tbody>
							<tr class="subHead">
								<th>
									Tower
								</th>
								<th>
									Typology 
								</th>
								<th>
									Unit
								</th>
								<th>
									Floor Band
								</th>
								<th>
									Car Park Type
								</th>
								<th>
									Description
								</th>
								<th style="width:32px;"></th>
							</tr>
							<!-- 
							<tr class="EOIDtlRow">  
								<td>
									<input class="csPtEnqSfidEoi" style="display:none;" />
									<select class="full form-control input-sm towerListEOI requiredField"  onchange="getTypologyEOI(this); getUnitEOI(this); getfbandEOI(this); getCarparkEOIMst(this);">
										<option value="">Select Tower</option>
									</select>
								</td>
								<td>
									<select class="full form-control input-sm typologyListEOI requiredField" onchange="getUnitEOI(this)">
										<option value="">Select Typology</option>
									</select>
								</td>
								<td>
									<select class="full form-control input-sm unitListEOI" onchange="unitChangeConditionEOI(this)">
										<option value="0">Select Unit</option>
									</select>
								</td>
								<td>
									<select class="full form-control input-sm floorListEOI">
										<option value="">Select Floor Band</option>
									</select>
								</td>
								<td>
									<select class="full form-control input-sm carparkListEOI">
										<option data-carparkname="" value="-1">Select Car Park</option>
									</select>
								</td>
								<td style="width:150px;">
									<textarea class="full form-control input-sm descriptionEOI" placeholder="Description"></textarea>
								</td>
								<td> </td>
							</tr>
							-->
						</tbody>
					</table>
				</div>
				
				
				<div class="col-md-12" style="margin-bottom:50px;">
					<h4 class="mrgT0">
						Payment Details
					</h4>
					
					<table class="table table-bordered  bgWhite mrgB8" id="csPtColEoi">
						<tbody>
							<tr class="subHead">
								<th>Status</th>
								<th style="width:130px;">
									Payment Type
								</th>
								<th style="width:120px;">
									Bank Name
								</th>
								<th style="width:100px; display:none;">
									Branch
								</th>
								<th>
									Transaction ID
								</th>
								<th>
									Transaction</br>Date
								</th>
								<th>
									Transaction Amount
								</th>
								
								<th style="width:250px; display:none;">
									PAN Attachment 
								</th>
								<th style="width:200px;">
									Receipt/</br>Cheque Attachment
								</th>
								
								<th style="width:150px;">
									Description
								</th>
								<th style="width:32px;"></th>
							</tr>
							
							<!-- 
							<tr class="csPtDataRowEoi">
								<td></td>
								<td>
									<input class="csPtEnqSfidEoi" style="display:none;" />
									<select onchange="csPtDdEoi(this)" class="full form-control input-sm csPtDropDownEoi requiredField">
										<option value="">Select</option>
										<option value="Cheque">Cheque</option>
										<option value="NEFT">NEFT/Credit</option>
										<option value="Swipe">Swipe</option>
										<option value="Wire Transfer">Wire Transfer (PayZap, Google Pay)</option>
									</select>
								</td>
								<td>
									<input class="full form-control input-sm csPtBankNameEoi requiredField" placeholder="Bank Name"/>
								</td>
								<td style="display:none;">
									<input class="full form-control input-sm csPtBranchEoi" placeholder="Branch Name"/>
								</td> 
								<td>
									<input class="full form-control input-sm csPtTransactionIdEoi requiredField" placeholder="Transaction ID"/>
								</td>
								<td>
									<input type="date" class="form-control input-sm csPtTransactionDateEoi requiredField" placeholder="Transaction Date"/>
								</td>
								<td>
									<input maxlength="10" class="numericWithoutDecimal numericField full form-control input-sm csPtTransactionAmountEoi requiredField" onkeyup="csPtcalculateGrandTotalEoi()" name="amount" placeholder="Transaction Amount"/>
								</td>
								
								<td style="display:none;">
									<input type="file" class="full form-control input-sm panAttachEoi" accept="application/pdf,image/*"/>
								</td>
								<td>
									<input type="file" class="numericField full form-control input-sm receiptAttachEoi requiredField" accept="application/pdf,image/*"/>
								</td>
								
								<td>
									<textarea class="full form-control input-sm csPtDescriptionEoi" placeholder="Description"></textarea>
								</td>
								<td> </td>
							</tr>
							-->
						</tbody>
					</table>
					
					<div class="clearfix"></div>
				</div>
			
			<div class="clearfix"></div>
		</div>
		
		<div class="clearfix"></div>
	</div>