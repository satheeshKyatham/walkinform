<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="">
	<div class="row">
		<div class="col-md-3 form-group">
			<label class="controlLabel required">Project <strong style="color: #f00;">*</strong> </label>
           	<select class="form-control" id="projectDataListEDR" onchange="floorRefreshEDR (); customerRefreshEDR (); towerListEDR ();">
           		<option>Select</option> 
           	</select>
		</div> 
		<div class="col-md-3 form-group">
			<label class="controlLabel required">Tower <strong style="color: #f00;">*</strong></label>
           	<select class="form-control" id="towerMstEDR" onchange="customerRefreshEDR (); floorListEDR();">
           		<option>Select</option>
           	</select>	
		</div>
		<div class="col-md-3 form-group">
			<label class="controlLabel required">Floor <strong style="color: #f00;">*</strong></label>
           	<select class="form-control multiselectDD" style="height:34px;" multiple="multiple" id="floorMstEDR" onchange="bookingList ()">
           		<option>Select</option>
           	</select>	
		</div>
		<div class="clearfix"></div>
		<div class="col-md-6 form-group">
			<label class="controlLabel required">Customer <strong style="color: #f00;">*</strong></label>
           	<select class="form-control multiselectDD" multiple="multiple" id="bookingList" style="height:34px;">
           		<option>Select</option>
           	</select>	
		</div>
		<div class="col-md-3 form-group">
			<label class="controlLabel required">
				Date <strong style="color: #f00;">*</strong> 
			</label>
			<input class="form-control" id="edrDate" type="date"/>
		</div> 
		<div class="clearfix"></div>
	</div> 
	<a class="btn btn-primary" onclick="updateEDR();" style="background-color: #000; color: #fff;"> 
		<span class="icon text-white-50" id="edrUpdateBtn">
			<i class="fa fa-spinner fa-spin" style="color:#fff !important; display:none;"></i> 
		</span>
		<span class="text">Update</span>
	</a> 
</div>