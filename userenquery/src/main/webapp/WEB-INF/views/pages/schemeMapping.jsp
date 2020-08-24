<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
<!-- <div class="form-group col-md-3">
	<label>Region</label>
	<select class="form-control" id="regionListForSM" onchange="projectDataListForSM()"> </select>
</div> -->
<div class="form-group col-md-3">
	<label>Project</label>
	<select class="form-control" id="projectDataListForSM" onchange="getSchemeSource()"> </select>
</div>
  	
<div class="form-group col-md-3 col-sm-6" id="schemeSourceCol">
	<label >Source</label> 
	<select class="full form-control" id="getSchemeSource" >
		<option value="0">Select</option>
	</select>
</div>

<div class="form-group col-md-3 col-sm-6" id="schemeSite">
	<label >Site</label> 
	<select class="full form-control" id="getSchemeSite" >
		<option value="0">Select</option>
	</select>
</div>

<div class="form-group col-md-3 col-sm-6" id="schemePromotional">
	<label >Promotional</label> 
	<select class="full form-control" id="getSchemePromotional" >
		<option value="0">Select</option>
	</select>
</div>  	
  	
<div class="form-group col-md-3 col-sm-6">
	<label >Scheme</label> 
	<select class="full form-control" id="headerSchemeDD"> </select>
</div>  	
<div class="form-group col-md-3">
	<label style="display: block;"> &nbsp; </label>
	<button type="submit" class="btn btn-default btn-primary" onclick="addschemeMapping()">Submit</button>
	<div class="clearfix"></div>
</div>  	
  	
  	
<div class="clearfix"></div>  	
<div id="headerSchemeTable">
	
	<div class="clearfix"></div>
</div>
  	
  	
<hr>
  	
<div class="form-group col-md-3">
	<label style="display: block;"> &nbsp; </label>
	<button type="submit" class="btn btn-default btn-primary" onclick="bulkSubmitSchemeMapping()">Bulk Submit</button>
	<div class="clearfix"></div>
</div>    	
  	

<div class="clearfix"></div>