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
			<div class="col-md-12" style="margin-bottom:15px;">
				<div class="row">
				 	<div class="form-group col-md-3">
					  <label>Promo Code</label>
					  <input id="pcPromoCode" type="text" class="form-control">
					</div>
					
					<div class="form-group col-md-3">
					  <label>Expiry Date</label>
					  <input id="pcExpiryDate" type="date" class="form-control">
					</div>
					
					<!-- <div class="form-group col-md-3">
					  <label>Project</label>
					  <input type="text" class="form-control">
					</div> -->
					
					<div class="form-group col-md-3">
						<label>Discount Type</label>
					  	<select id="pcDisType" class="form-control" onchange="discountType(this)">
							<option value = "0">Percentage Discount</option>
							<option value = "1">Flat Discount</option>
					  	</select>
					</div>
			
					<div class="form-group col-md-3 perDisInput">
					  <label>Discount Percentage</label>
					  <input id="pcDisPercentage" type="number" class="form-control">
					</div>
					
					<div class="form-group col-md-3 perDisInput">
					  <label>Coupon Discount Upto</label>
					  <input id="pcDisUpto" type="number"  class="form-control">
					</div>
			
					<div class="form-group col-md-3 flatDisInput" style="display:none;">
					  <label>Flat Discount Amount</label>
					  <input id="pcFlatDisAmount" type="number" class="form-control">
					</div>
					
					<div class="form-group col-md-3">
					  <label>Discount On Typology</label>
					  <select id="pcDisOnTypology" type="text" class="form-control pcMultiselectTower" multiple="multiple"></select>
					</div>
					
					<div class="form-group col-md-3">
					  <label>Promocode Max Use Count</label>
					  <input id="pcMaxUseCount" type="number"  class="form-control">
					</div>
					<div class="clearfix"></div>
					<div class="col-md-12">
						<button type="submit" onclick="createPromoCode(this, 'ACTIVE');" style="color: #fff; background-color: #0077b9;" class="btn btn-primary btn-sm">Submit</button>
					</div>
					
				 	<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
			<div class="col-md-12">
				<table class="table table-bordered" id="fetchPromoCode">
					<thead>
						<tr>
							<th>PromoCode</th>
							<th>Project</th>
							<th>Coupon discount upto</th>
							<th>Discount on typology</th>
							<th>Discount percentage</th>
							
							<th>Discount Type</th>
							<th>End Date</th>
							<th>Flat discount amount</th>
							<th>Promocode max use count</th>
							<th>Promocode use count</th>
							<th>Status</th>
							<th></th>
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
	<div class="clearfix"></div>
</div>