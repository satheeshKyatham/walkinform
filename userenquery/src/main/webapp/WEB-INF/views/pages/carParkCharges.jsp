<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@page import="java.util.HashMap"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	 response.addHeader("Expires","0");
	 response.addHeader("Pragma","no-cache");
	 response.setHeader("Cache-Control","no-cache,no-store, must-revalidate, pre-check=0, post-check=0, max-age=0, s-maxage=0");
	 response.addHeader("X-Frame-Options", "DENY");
	 %>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Godrej</title>
	
	<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css' />">
	<link rel="stylesheet" href="<c:url value='/resources/css/costsheet.css' />">
	<link href="<c:url value='/resources/css/animate.min.css' />" rel="stylesheet">	
	<link rel="stylesheet" href="<c:url value='/resources/css/sweetalert.min.css'/>">   
</head>
<body>

	<input id="pageContext" value="${pageContext.request.contextPath}" style="display:none;"/>

	<div class="container">
		<div class="row">
				<h2 >Car Park</h2>
			    
			    <div class="row">
					
					
					<div class="form-group col-md-3">
						<label>Region</label>
						<select class="form-control" id="regionList" onchange="projectDataList()"> </select>
					</div>
					
					<div class="form-group col-md-3">
						<label>Project</label>
						<select class="form-control" id="projectDataList" onchange="getCarparkTypeList()"> </select>
					</div>
					
					<div class="clearfix"></div>
					
					<hr></hr>
					
					<h4 class="col-md-12">Add Car Park Type</h4>
					
					<div class="form-group col-md-3">
						<label>Car park type</label>
						<input class="form-control" id="carParkTypeName" />
					</div>
			    	
			    	
			    	<div class="form-group col-md-3">
			    		<label style="display: block;"> &nbsp; </label>
			    		<button type="submit" class="btn btn-default btn-primary" onclick="insertMaster()">Submit</button>
			    		<div class="clearfix"></div>
			    	</div>
			    	
			    	<div class="clearfix"></div>
			    </div>
			    
			    <div class="clearfix"></div>
		     </div>
		
		
		
		
		<div class="row">
		    
		    <div class="row">
				
				<h4 class="col-md-12">Add Car Park Charges</h4>
				
				<div class="form-group col-md-3">
					<label>Type</label>
					<select class="form-control" id="parkType">
						<!-- <option value='covered'>Covered Car Park</option>
						<option value='open'>Open Car Park</option> -->
					</select>
				</div>
				
				
				<div class="form-group col-md-3">
					<label>Amount</label>
					<input class="form-control" id="carParkAmount" />
				</div>
		    	
		    
		    	
		    	<div class="form-group col-md-3">
		    		<label style="display: block;"> &nbsp; </label>
		    		<button type="submit" class="btn btn-default btn-primary" onclick="addCarParkCharge()">Submit</button>
		    		<div class="clearfix"></div>
		    	</div>
		    	
		    	 <div class="clearfix"></div>
		    </div>
		    
		    <div class="clearfix"></div>
		    
		   
	        </div>
	        
	        
	        
	        
	        
		</div>
	
	
	<script src="<c:url value='/resources/js/jquery-1.12.4.min.js' />"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
	<script src="<c:url value='/resources/js/sweetalert.min.js'/>"></script>
	
	
	<script src="<c:url value='/resources/js/separate/carParkCharges.js?v=18.10'/>"></script>
	
	
	
	
	
	
</body>
</html>