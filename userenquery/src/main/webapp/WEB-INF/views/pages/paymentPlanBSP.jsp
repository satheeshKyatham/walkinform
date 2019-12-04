<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@page import="java.util.HashMap"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
			<h2>Add BSP against Payment Plan</h2>
		    
		    <div class="row">
				<div class="form-group col-md-3">
					<label>Region</label>
					<select class="form-control" id="regionList" onchange="projectDataList()"> </select>
				</div>
				
				<div class="form-group col-md-3">
					<label>Project</label>
					<select class="form-control" id="projectDataList" onchange="paymentPlanDropdown()"> </select>
				</div>
		    	<div class="form-group col-md-3">
					<label>Tower</label>
					<select id="towerMst" onchange="inventoryUnitTypeMst()" class="form-control"> </select>
				 </div>
				 
				 <div class="form-group col-md-3">
					<label>Typology</label>
					<select id="typoMst" class="form-control">
						<!-- <option value='typologyAll'>All</option> -->
					</select>
				 </div>
		    	<div class="form-group col-md-3">
					<label>Payment Plan</label>
					<select class="form-control" id="ppDropdown"> </select>
				</div>
		    	
		    	<div class="clearfix"></div>
		    	
		    	<div class="form-group col-md-2">
					<label>Addl BSP psft</label>
					<input id="bsp_amount" type="number" class="form-control" />
				</div>
				
				<div class="form-group col-md-2">
					<label>Addl BSP %</label>
					<input id="bsp_amount_per" type="number" class="form-control" />
				</div>
				
				
				<!--<div class="form-group col-md-7">
					<label>T & C</label>
					<textarea class="form-control" rows="12"> </textarea>
				</div>-->
				
		    	
		    	<div class="clearfix"></div>
		    	
		    	<div class="form-group col-md-3">
		    		<label style="display: block;"> &nbsp; </label>
		    		<button type="submit" class="btn btn-default btn-primary" onclick="addBSPCharge()">Submit</button>
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
	
	
	<script src="<c:url value='/resources/js/separate/paymentPlanBSP.js?v=15'/>"></script>
	
	
	
	
	
	
</body>
</html>