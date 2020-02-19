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
<label style="font-size: 22px">Allotment Journey So Far</label>
<table class="table table-bordered" id="allotmentmisReport" style="font-size: 16px;">
			<thead>
				<tr>
					<th>Total Saleable Area Sold : </th>
					<th>Total BSP (Subject to payment realization) : </th>
					<th>Allotment Walk-ins(E Token) </th>
					<th>Total Units sold  : </th>
					<th>KYC Approved  : </th>
					<th>Total Application/Booking </th>
					
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
<div class="well center-block">

	<div class="form-inline">
		
		<i class="glyphicon glyphicon-filter" style="border-right: 1px solid #333; margin-right: 10px; padding-right: 10px;"></i>
		
		<div class="form-group">
			<label for="exampleInputNameDate">From Date</label> 
			<input class="form-control" type="date" name="" id="txtAllotFromDate">
		</div>
		<div class="form-group">
			<label for="exampleInputNameDate">To Date</label> 
			<input class="form-control" type="date" name="" id="txtAllotToDate">
		</div>
		<div class="form-group">
			<label for="exampleInputNameDate">Search</label> 
			<input class="form-control" type="button" value="Search" name="Search" onclick="getAllotmentDashboardReport()"/>
		</div>
	</div>
	<div class="clearfix"></div>
</div>


<div class="commonLoad" id="mainPageLoad" style="display: none;"></div>

<%-- <form:form action="downloadEOICSV" method="post">
	<div id="eoiReportCSVInputCol"> </div>

	<fieldset style="width: 400px;">
		<input type="submit" value="Downlaod In Excel">
	</fieldset>
</form:form> --%>

<div class="wrapperEOIRepo1">
	<div class="divEOI1"></div>
</div>

<div class="wrapperEOIRepo2">
	
	<div>
	
	
		
		
	</div>
	
	<div class="divEOI2" style="overflow: auto;">
		<table class="table table-bordered" id="allotmentReportTable">
			<thead>
				<tr>
				
					<th>Priority No.</th>
					<th>ENQ Name</th>
					<th>Customer Name</th>
					<th>Customer Mobile</th>
					<th>Customer Email</th>
					<th>Walk-in Source</th>
					<th>Sourcing Manager Name</th>
					<th>Vertical</th>
					<th>CP</th>
					<th>Closing Manager</th>
					<th>Request Source</th>
					<th>Enquiry Type</th>
					<th>Offer No.</th>
					<th>Offer Created By</th>
					<th>Super Area</th>
					<th>Carpet Area</th>
					<th>Unit Type</th>
					<th>Car Park Type</th>
					<th>Scheme Name</th>
					<th>Total Basic Price</th>
					<th>Sales Consideration</th>
					<th>Created By</th>
					<th>Booking Amount</th>
					
					<th>Property Name</th>
					<th>Unit No</th>
					<th>5% Amount</th>
					<th>Balance Amt. w.r.t 5%</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
	
	
	<div class="clearfix"></div>
</div>