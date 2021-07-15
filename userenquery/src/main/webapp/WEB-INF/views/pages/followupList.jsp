<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
 
<div class="well center-block">
	<div class="form-inline">
		
		<i class="glyphicon glyphicon-filter" style="border-right: 1px solid #333; margin-right: 10px; padding-right: 10px;"></i>
		<div class="form-group">
			<label>From Date</label> 
			<input class="form-control" type="date" name="" id="txtFromDateFollowUp">
		</div>
		<div class="form-group">
			<label>To Date</label> 
			<input class="form-control" type="date" name="" id="txtToDateFollowUp">
		</div>
		<div class="form-group">
			<button style="padding-top:6px; padding-bottom:6px;" class="btn blue_btn btnCommon" type="button"  onclick="getTodayFollowup()">
				<span>
					
				</span> Search
			</button>
		</div>
	</div>
	<div class="clearfix"></div>
</div> 
    	
<div class="row">
	<div class="col-md-12">
		<div class="clearfix"></div>
		
		<div class="rattingCBCol" style="display:none;"> 
			<!-- <label class="rattingCol rcbAll">All
				<input type="checkbox" name="rattingCB" value="All" onchange="selectedratting ()" checked="checked">
				<span class="checkmarkFU"></span>
			</label> -->
			<label class="rattingCol rcbHot">Hot
				<input type="checkbox" name="rattingCB" value="Hot" onchange="selectedratting ()">
				<span class="checkmarkFU"></span>
			</label>
			<label class="rattingCol rcbCold">Cold
				<input type="checkbox" name="rattingCB" value="Cold" onchange="selectedratting ()">
				<span class="checkmarkFU"></span>
			</label>
			<label class="rattingCol rcbWarm">Warm
				<input type="checkbox" name="rattingCB" value="Warm" onchange="selectedratting ()">
				<span class="checkmarkFU"></span>
			</label>
			<label class="rattingCol rcbLost">Lost
				<input type="checkbox" name="rattingCB" value="Lost" onchange="selectedratting ()">
				<span class="checkmarkFU"></span>
			</label>
			<!-- <label class="rattingCol rcbLost">Lost
				<input type="checkbox" name="rattingCB" value="Lost" onchange="selectedratting ()">
				<span class="checkmarkFU"></span>
			</label> -->
			<div class=""></div>
		</div>		
		<table class="table table-striped table-bordered" id="followUpList" cellspacing="0" width="100%">
			<thead>
				<tr>
				<th>ID</th>
				<th>Token No.</th>
				<th>Enq.</th>
				<th>Name</th>
				<th>Mobile</th>									
				 								
				<th>Closing Mgr</th>
				
				<th>Follow-up Date & Time</th>
				<th>Follow-up Type</th>
				<th id="rattingColumn">Rating</th>
				<th>Status</th>
				<th>Action</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
		<div class="clearfix"></div>
	</div>
	<div class="clearfix"></div>
</div>