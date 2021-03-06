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
			
			<div class="">
				<div class="form-group col-md-4">
					<label>Project(s) </label>
					<select class="userMultiselectTower" id="csLogReportMultiTower" multiple="multiple"></select>
				</div>
				<div class="form-group col-md-3">
					<label>From Date </label>
					<input type="date" id="csLogFromDateOffer"></select>
				</div>
				<div class="form-group col-md-3">
					<label>To Date </label>
					<input type="date" id="csLogToDateOffer"></select>
				</div>
				<div class="form-group col-md-2">
					<label> &nbsp; </label>
					<button id="getCSLogBtn" onclick="getCostsheetLogReportDtl()" style="line-height:0; color: #fff; background-color: #0077b9;" class="form-control btn btn-primary">
						<span></span> Get Details
					</button>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
			<div class="col-md-12">
				 <table class="table table-bordered display" id="csLogReportTable">
					<thead>
						<tr>
							<th>Timestamp</th>
							<th>User Name</th>
							<th>User Email ID</th>
							<th>Token</th>
							<th>Customer Mobile</th>
							<th>Customer Name</th>
							<th>ENQ No.</th>
							<th>Project Name</th>
							<th>Tower Name</th>
							<th>Property Name</th>
							<th>Source</th>
							<th>CS Type</th>
							<th>Scheme Type</th>
							<th>Scheme Name</th>
							<th>Discount RPS</th>
							<th>Discount Absolute</th>
							<th>Discount Percentage</th>
							<th>Car Park Type</th>
							<th>Car Park Count</th>
							<th>Payment Plan Name</th>
							<!--   -->
							<th>CS BSP</th>
							<th>OG BSP</th>
							<th>Carpet Area (sq.ft)</th>
							<th>Saleable Area (sq.ft)</th>
							<th>Carpet Area Rera (sq.mt)</th>
							<th>Exclusive Area (sq.mt)</th>
							<th>Total Area (sq.mt)</th>
							<th>Carpet Area Amount</th>
							<th>Exclusive Area Amount</th>
							<!--   -->
							<th>Flat Unit Cost</th>
							<th>Total A</th>
							<th>Total B</th>
							<th>Stemp Duty</th>
							<th>Registration</th>
							<th>GST</th>
							<th>Total C</th>
							<th>Total ABC</th>
							<th>Total Discount</th>
							<th>Payment Plan Total</th>
							<th>CS Sales Comments</th>
							<!--   -->
							
							<th>Parking Selection</th>
							<th>Parking Name</th>
							<th>Parking Amount</th>
							
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