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
			<label for="exampleInputNameDate">From Date</label> 
			<input class="form-control" type="date" name="" id="txtEOIFromDate">
		</div>
		<div class="form-group">
			<label for="exampleInputNameDate">To Date</label> 
			<input class="form-control" type="date" name="" id="txtEOIToDate">
		</div>
		<div class="form-group">
			<label for="exampleInputNameDate">Search</label> 
			<input class="form-control" type="button" value="Search" name="Search" onclick="getEOIReport()"/>
		</div>
	</div>
	<div class="clearfix"></div>
</div>


<div class="commonLoad" id="mainPageLoad" style="display: none;"></div>

<form:form action="downloadEOICSV" method="post">
	<div id="eoiReportCSVInputCol"> </div>

	<fieldset style="width: 400px;">
		<input type="submit" value="Downlaod In Excel">
	</fieldset>
</form:form>

<table class="table table-bordered" id="eoiReportTable">
	<thead>
		<tr>
			<th>Enq SFID</th>
			<th>Preferences</th>
			<th>Payment Type</th>
			<th>Bank Name</th>
			<th>Branch</th>
			<th>Transaction ID</th>
			<th>Transaction Date</th>	
			<th>Transaction Amount</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>