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
	 
	<div class="clearfix"></div>
	<div class="col-md-12">
		<div class="row well center-block form-inline">
			<div class="form-group" style=" width: 100%; margin-bottom: 15px;">	
				<label>Project(s) </label>  
				<select id="cpEngmntMultiselectProject" style="width:100%;" class="userMultiselectProject" multiple="multiple"></select>
			</div>
			<div class="clearfix"></div>
			<div class="form-group">
				<label>From Date of Visit</label>
				<input type="date" class="cpEngmntFromDateOffer form-control"></select>
			</div>
			<div class="form-group">
				<label>To Date of Visit</label>
				<input type="date" class="cpEngmntToDateOffer form-control"></select>
			</div>
			<div class="form-group">
				<label> &nbsp; </label>
				<button  onclick="getCPEngmntReportDtl('MISROLE')" style="line-height:0; color: #fff; background-color: #0077b9;" class="form-control btn btn-primary">
					<span></span> Get Details
				</button>
			</div>
			<div class="clearfix"></div>
		</div>
	
		<table class="table table-bordered display" id="cpEngmntTable">
			<thead>
				<tr>
					<th>Date of Visit</th>
					<th>Project</th>
					<th>CP Name</th>
					<th>Place of Meeting</th>
					<th>Topic of Discussion</th>
					<th>Points Discussed over meeting</th>
					<th>Sales RM</th>  
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
		<div class="clearfix"></div>
	</div>
	<div class="clearfix"></div>