<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	 response.addHeader("Expires","0");
	 response.addHeader("Pragma","no-cache");
	 response.setHeader("Cache-Control","no-cache,no-store, must-revalidate, pre-check=0, post-check=0, max-age=0, s-maxage=0");
	 response.addHeader("X-Frame-Options", "DENY");
	 %>
<input id="pageContext" value="${pageContext.request.contextPath}" style="display:none;"/>

<!-- <h4>Created Offers List</h4> -->

<div class="well center-block">
	<div class="form-inline">
		
		<i class="glyphicon glyphicon-filter" style="border-right: 1px solid #333; margin-right: 10px; padding-right: 10px;"></i>
		
		<div class="form-group">
			<label>From Date</label> 
			<input class="form-control" type="date" name="" id="txtSourcingLeadFromDate">
		</div>
		<div class="form-group">
			<label>To Date</label> 
			<input class="form-control" type="date" name="" id="txtSourcingLeadToDate">
		</div>
		<div class="form-group">
			<button style="padding-top:6px; padding-bottom:6px;" class="btn blue_btn btnCommon" type="button" value="Search" name="Search" id="amsearch" onclick="getMySourcingLead()">
				<span>
				</span> Search
			</button>
		</div>
		
	</div>
	<div class="clearfix"></div>
</div>

<div class="wrapperEOIRepo1">
	<div class="divEOI1"></div>
</div>

<div class="wrapperEOIRepo2">
	
	<div class="divEOI2" style="overflow: auto;">
		<table class="table table-bordered" id="mySourcingLeadReportTableSales">
			<thead>
				<tr>
					<th>ENQ Name</th>
					<th>Customer Name</th>
					<th>Customer Mobile</th>
					<th>Customer Email</th>
					<th>Enquiry Type</th>
					<th>Walk-In Source</th>
					<th>Closing Manager</th>
					<th>Date Of Site Visit</th>
					<th>CP Name</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
	
	
	<div class="clearfix"></div>
</div>