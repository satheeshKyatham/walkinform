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
			<input class="form-control" type="date" name="" id="txtEOIFromSalesDate">
		</div>
		<div class="form-group">
			<label for="exampleInputNameDate">To Date</label> 
			<input class="form-control" type="date" name="" id="txtEOIToSalesDate">
		</div>
		<div class="form-group">
			<input class="form-control" type="button" value="Search" name="Search" onclick="getEOIReportSales()"/>
		</div>
	</div>
	<div class="clearfix"></div>
</div>


<div class="commonLoad" id="mainPageLoad" style="display: none;"></div>



<div class="wrapperEOIRepo1">
	<div class="divEOI1"></div>
</div>

<div class="wrapperEOIRepo2">
	
	<div class="divEOI2" style="overflow: auto;">
		<table class="table table-bordered" id="eoiReportTableSales">
			<thead>
				<tr>
					<!-- <th>Region</th>
					<th>Project Name</th> -->
					<th>ENQ_Name</th>
					<th>PDF</th>
					<th>Customer Name</th>
					<th>Customer Mobile</th>
					<th>Customer Email</th>
					<th>Walkin Date</th>
					<th>Walk-in Source</th>
					<th>Sourcing Manager Name</th>
					
					<th>Vertical</th>
					<th>CP</th>
					<th>Closing Manager</th>
					<th>Date Of EOI</th>
					<th>EOI Token Type</th>
					<th>Total Payment Done</th>
					<th>Total Payment Approved</th>
					
					<th>Typology Name1</th>
					<th>Floor_Band1</th>
					<th>Tower Name1</th>
					<th>Unit1</th>
					<th>Car Praking1</th>
					<th>Description1</th>
					
					<th>Typology Name2</th>
					<th>Floor Band2</th>
					<th>Tower Name2</th>
					<th>Unit2</th>
					<th>Car Praking2</th>
					<th>Description2</th>
					
					<th>Typology Name3</th>
					<th>Floor Band3</th>
					<th>Tower Name3</th>
					<th>Unit3</th>
					<th>Car Praking3</th>
					<th>Description3</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
	
	
	<div class="clearfix"></div>
</div>