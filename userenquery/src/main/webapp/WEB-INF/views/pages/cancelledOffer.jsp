
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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

	<div class="well center-block">
		<div class="form-inline">
			<div>
				<div class="form-group">	
					<label>Project(s) </label>  
					<select class="userMultiselectProject" id="cancelledOfferProjectDD" multiple="multiple"></select>
				</div>
				<div class="clearfix"></div>
				<br>
				<div class="form-group">
					<label>From Date</label> 
					<input class="form-control" type="date" id="cancelledOfferFromDate">
				</div>
				<div class="form-group">
					<label>To Date</label> 
					<input class="form-control" type="date" id="cancelledOfferToDate">
				</div>
				<div class="form-group">
					<input class="form-control" type="button" value="Search" name="Search" id="amsearch" onclick="cancelledOffer()"/>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>


	<!-- filter bar -->


	<!-- <div class="col-md-12"> -->
		
		
		<!-- <div ><lable id="msg"> </lable></div> -->
		
			<table class="table table-bordered" id="cancelledOfferTable"> <!-- rwd-table -->
				<thead>  
				  <tr>
				  	<th>Project</th>
				  	<th>Date</th>
					<th>Enquiry No</th>
					<th>Unit</th>
					<th>Closing Manager</th>
					<th>Sourcing Manager</th>
					<th>Verticle</th>
					<th>Contact Name</th>
					<th>Offer Name</th>
					<th>Reason For Loss</th>
					<th>Comment</th>
					<!-- <th>Application</th> -->				
				  </tr>
				 </thead> 
			  <tbody></tbody> <!-- id="createdOfferTable" -->
			  
			</table>
		<!-- </div> -->
		<!-- <h1>RWD List to Table</h1> -->
			 
