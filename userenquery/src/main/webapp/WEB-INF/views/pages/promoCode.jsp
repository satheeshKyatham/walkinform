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
		<div id=""> 
			<div class="col-md-12">
				<div class="row">
				 	<div class="form-group col-md-3">
					  <label>Promo Code</label>
					  <input type="text" class="form-control">
					</div>
					
					<div class="form-group col-md-3">
					  <label>Expiry Date</label>
					  <input type="date" class="form-control">
					</div>
					
					<!-- <div class="form-group col-md-3">
					  <label>Project</label>
					  <input type="text" class="form-control">
					</div> -->
					
					<div class="form-group col-md-3">
						<label>Discount Type</label>
					  	<select class="form-control" onchange="discountType(this)">
							<option value = "0">Percentage Discount</option>
							<option value = "1">Flat Discount</option>
					  	</select>
					</div>
			
					<div class="form-group col-md-3 perDisInput">
					  <label>Discount Percentage</label>
					  <input type="text" class="form-control">
					</div>
					
					<div class="form-group col-md-3 perDisInput">
					  <label>Coupon Discount Upto</label>
					  <input type="text" class="form-control">
					</div>
			
					<div class="form-group col-md-3 flatDisInput" style="display:none;">
					  <label>Flat Discount Amount</label>
					  <input type="text" class="form-control">
					</div>
					
					<div class="form-group col-md-3">
					  <label>Discount On Typology</label>
					  <input type="text" class="form-control">
					</div>
					
					<div class="form-group col-md-3">
					  <label>Promocode Use Count</label>
					  <input type="text" class="form-control">
					</div>
					
					<div class="form-group col-md-3">
					  <label>Promocode Max Use Count</label>
					  <input type="text" class="form-control">
					</div>
					
					<button type="submit" class="btn btn-default">Submit</button>
					
				 	<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
			<div class="col-md-12">
				<table class="table table-bordered" id="enqDtlTableEOI">
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
		<div class="clearfix"></div>
	</div>
	<div class="clearfix"></div>
</div>
	
	
	
	
	
	
	
	
	 