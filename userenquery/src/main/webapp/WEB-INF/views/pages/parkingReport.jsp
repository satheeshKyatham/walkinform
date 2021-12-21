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
				<div class="form-group col-md-3">
					<label>Tower</label>
					<select id="towerMstReportParking" class="form-control">
						<option value="All">All</option>
					</select>
				</div>
				<div class="form-group col-md-2">
					<label> &nbsp; </label>
					<button onclick="getHoldParkingReportDtl()" style="line-height:0; color: #fff; background-color: #0077b9;" class="form-control btn btn-primary">
						Get Details <span id="getParkingRepUnitBtn"></span>
					</button>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
			<div class="inventoryLegendsCol col-md-12">
				<ul>
					<li><span class="unitBlockAdmin"></span> Block</li>
					<li><span class="unitEOIBlockAdmin"></span> Hold against flat/unit</li>
				</ul>
				<div class="clearfix"></div>
			</div>
		
			<div class="clearfix"></div>
			
			<div class="col-md-12">
				<table class="table table-bordered display" id="parkingReportDtl">
					<thead>
						<tr>
							<th>Hold/ Block Date</th>
							<th>Hold/ Block</th>
							<th>Admin</th>
							<th>Flat Tower</th>
							<th>Flat No.</th>
							<th>Flat Name</th>
							<th>Parking Tower</th>
							<th>Parking Name</th>
							<th>Parking Type</th>
							<th>Category Of Parking</th>
							<th>Parking Size</th>
							<th>Location of Parking</th>
							<th>Parking Area (Sq. Ft.)</th>
							<th>Parking Area (Sq. Mt.)</th>
							<th>Parking Dimensions</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="clearfix"></div>
	</div>