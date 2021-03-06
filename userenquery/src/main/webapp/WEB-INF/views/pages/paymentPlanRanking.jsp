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
	
	<link rel="stylesheet" href="<c:url value='/resources/css/font-awesome.css' />">
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css"> 
</head>
<body>

	<input id="pageContext" value="${pageContext.request.contextPath}" style="display:none;"/>

	<div class="container">
		<div class="row">
		<input type="hidden" id="region_id" >
			<h2>Payment Plan Ranking</h2>
		    
		    <div class="row">
			<!-- 	<div class="form-group col-md-3">
					<label>Region</label>
					<select class="form-control" id="regionList" onchange="projectDataList()"> </select>
				</div> -->
				
				<div class="form-group col-md-3">
					<label>Project</label>
					<select class="form-control" id="projectDataList" onchange="getPaymentPlanData()"> </select>
				</div>
		    	
		   <!--   <div class="form-group col-md-3">
					<label>Payment Plan</label>
					<select class="form-control" id="ppDropdown"> </select>
				</div> -->
						    	
		    	<div class="clearfix"></div>
		    	
		    	<!-- <div class="form-group col-md-3">
		    		<label style="display: block;"> &nbsp; </label>
		    		<button id="towerAgainstBtn" style="display:none;" type="submit" class="btn btn-default btn-primary" onclick="addPaymentPlanRank()">Submit</button>
		    		<div class="clearfix"></div>
		    	</div> -->
		    	
		    	 <div class="clearfix"></div>
		    </div>
		    
		    <div class="clearfix"></div>
		    <div class="commonLoad" id="mainPageLoad" style="display:none;"></div>
		   <h4 class="mrgT0">
			Payment Due Details
		</h4>
	<table class="table table-bordered" id="paymentRankListId">
			<tbody>
				<tr class="subHead">
					<th style="width:150px;">
						Payment Plan
					</th>
					<th style="width: 140px;">
						Ranking
					</th>
					<!-- <th style="width: 165px;">
						Action
					</th> -->
				</tr>
				
				
				
			</tbody>
		</table>
		<div class="form-group col-md-3">
	<label style="display: block;"> &nbsp; </label>
	<button type="submit" class="btn btn-default btn-primary" onclick="bulkSubmitPaymentRanking()">Bulk Submit</button>
	<button type="submit" class="btn btn-default btn-primary editRanking" style="display:none" id="editRankingId" onclick="editPaymentRanking()">Edit</button>
	<button class="btn btn-danger cancelRanking" style="display:none" onclick="cancelPaymentRanking(this)">Cancel</button>
	<div class="clearfix"></div>
</div> 
	        </div>
		</div>
	
	
	<script src="<c:url value='/resources/js/jquery-1.12.4.min.js' />"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
	<script src="<c:url value='/resources/js/sweetalert.min.js'/>"></script>
	<script src="<c:url value='/resources/js/separate/paymentPlanRanking.js?v=18.15'/>"></script>
	<script type="text/javascript" charset="utf8" src="<c:url value='/resources/js/jquery.dataTables.js'/>"></script>
	<script src="<c:url value='/resources/js/dataTables.buttons.min.js'/>"></script>
	
	
	
	
	
	
</body>
</html>