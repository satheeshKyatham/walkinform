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
					<label>Region </label>
					<select class="" id="allParkingReportMultiRegion"  multiple="multiple"></select>
				</div>
				
				
				<div class="form-group col-md-4">
					<label>Project(s) </label>
					<select class="" id="allParkingReportMultiTower" multiple="multiple"></select>
				</div>
				
				<div class="form-group col-md-3">
					<label>Status </label>
					<select id="ParkingStatus">
						<option value="ALL" selected>All</option>
					    <option value="AVAILABLE">Available + Hold</option>
					    <option value="SOLD">Sold</option>
					    <option value="OFFERED">Offered </option>
					    <option value="OFFEREDSFDCHOLD">Offered with SFDC Hold</option>
					</select>
				</div>
				 
				<div class="form-group col-md-2">
					<label> &nbsp; </label>
					<button id="getAllParkingBtn" onclick="getAllParkingReportDtl()" style="line-height:0; color: #fff; background-color: #0077b9;" class="form-control btn btn-primary">
						<span></span> Get Details
					</button>
				</div>
				
				<div class="clearfix"></div>
			</div>
		
			<div class="clearfix"></div>
			
			<div class="col-md-12">
				
				 <ul style="display: inline-block; padding: 12px 12px 12px 12px; list-style: none; border: 1px dotted;  margin: 5px 0 25px 0;">
					<li style="background-color: #ccc; padding: 2px; text-align: center;"><b>Status</b></li>
					<li style="border-top: 1px dotted;"><b style="width: 90px; display: inline-block;">Hold :</b> D4U Hold against flat/unit <small>or</small> D4U Block <small>or</small> SFDC Parking On Hold</li>
					<li style="border-top: 1px dotted;"><b style="width: 90px; display: inline-block;">Sold :</b> Parking Allotted <small>and</small> Allotted Through Offer </li>
					<li style="border-top: 1px dotted;"><b style="width: 90px; display: inline-block;">Available:</b> Parking Not Allotted <small>and</small> Not Allotted Through Offer  </li>
					<li style="border-top: 1px dotted;"><b style="width: 90px; display: inline-block;">Offered :</b> Parking Not Allotted <small>and</small> Only Allotted Through Offer</li>
					<li style="border-top: 1px dotted;"><b style="width: 90px; display: inline-block;">Offered with SFDC Hold :</b> Parking Not Allotted <small>and</small> Only Allotted Through Offer with SFDC Hold</li>  
				</ul>
			  
				<table class="table table-bordered display" id="allParkingReportDtl">
					<thead>
						<tr>
							<th>Project</th>
							<th>Tower</th>
							<th>Parking ID</th>
							<th>Parking Name</th>
							<th>Double Bay Parking</th>
							<th>Parking Size</th>
							<th>Is Released</th>
							<th>Is Parking Blocked</th>
							<th>Category Of Parking</th>
							<th>Location of Parking</th>
							<th>Parking Area (Sq. Ft.)</th>
							<th>Parking Area (Sq. Mt.)</th>
							<th>Dimensions</th>
							<th>Approval Status</th>
							<th>Allotted Through Offer</th>
							<th>Allotted</th>
							<th>D4U Hold</th>
							<th>Active</th>
							<th>Status</th>
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