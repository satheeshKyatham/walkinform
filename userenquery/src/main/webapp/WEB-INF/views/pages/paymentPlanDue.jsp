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
<%-- <nav class="navbar topMainBar">
	  <div class="container">
	 <%@ include file="/WEB-INF/views/pages/header.jsp" %>
		 </div>
	</nav> --%>
	<input type="text" id="pageContext" value="${pageContext.request.contextPath}" style="display:none;"/>

	<div class="container">
		<div class="row">
		<input type="hidden" id="region_id" >
			<h2>Payment Plan Due</h2>
		 
		    <div class="row">
				<!-- <div class="form-group col-md-3">
					<label>Region <strong class="mndt">*</strong></label>
					<select class="form-control" id="regionList" onchange="projectDataList()" required="required"> </select>
				</div> -->
				
				<div class="form-group col-md-3">
					<label>Project <strong class="mndt">*</strong></label>
					<select class="form-control" id="projectDataList" onchange="paymentPlanDropdown()" required="required"> </select>
				</div>
		    	<div class="form-group col-md-3">
					<label>Tower <strong class="mndt">*</strong></label>
					<select id="towerMst" required="required" onchange="inventoryUnitTypeMst()" class="form-control"> </select>
				 </div>
				 
		    	<div class="form-group col-md-3" id="ppDropdownDiv" style="display: none;">
					<label>Payment Plan <strong class="mndt">*</strong></label>
					<select class="form-control" id="ppDropdown" onchange="getPymentPlanLineItems()"> </select>
				</div>
		    	
		    	<div class="clearfix"></div>
				
				<div class="form-group col-md-2">
					<label>Dues %</label>
					<input id="due_amount" type="text" disabled="disabled" class="form-control" required="required"/>
				</div>	
				<div class="form-group col-md-2">
					<label>Booking Amount</label>
					<input id="booking_amount_id" type="number" class="form-control" />
				</div>	
				<div class="form-group col-md-2">
					<label>Days</label>
					<input id="days_id" type="number" value="30" class="form-control" />
				</div>
				<div class="form-group col-md-2" style="display: none;">
					<label>String %</label>
					<input id="ppmilestone_json_id" type="text" class="form-control" />
				</div>	
				<div class="form-group col-md-2" style="display: none;">
					<label>ID</label>
					<input id="ppmilestone_pk_id" type="text" class="form-control" />
				</div>		
		    	
		    	<div class="clearfix"></div>
		    	
		    	<!-- <div class="form-group col-md-3">
		    		<label style="display: block;"> &nbsp; </label>
		    		<button id="paymentDueSubmit" style="display:none;" type="submit" class="btn btn-primary" onclick="addPaymentDue()">Submit</button>
		    		<div class="clearfix"></div>
		    	</div> -->
		    	
		    	 <div class="clearfix"></div>
		    </div>		    
		    <div class="commonLoad" id="mainPageLoad" style="display:none;"></div>
	
	<div class="clearfix"></div>
		
	<h4 class="mrgT0">
			Payment Due Details
		</h4>
	<!-- <table class="table table-bordered" id="paymentDueListId">
			<tbody>
				<tr class="subHead">
					<th style="width:150px;">
						Region</br>
					</th>
					<th style="width:200px;">
						Project Name
					</th>
					<th>
						Tower
					</th>
					<th style="width: 140px;">
						Payment Plan Name
					</th>
					<th style="width: 140px;">
						Due
					</th>
					<th style="width: 165px;"></th>
				</tr>
				
				
				
			</tbody>
		</table> -->
		
		<table class="table table-bordered" id="paymentListId">
			<tbody>
				<tr class="subHead">
					<th style="width:300px;">
						Milestone Name</br>
					</th>
					<th style="width:200px;">
						Percentage
					</th>
					<th style="width:200px;">
						Amount
					</th>
					<!-- <th>
						Tower
					</th> -->
					<th>
						Milestone Completed
					</th>
					<!-- <th style="width: 140px;">
						Due
					</th>
					<th style="width: 165px;"></th> -->
				</tr>
				
				
				
			</tbody>
		</table>
	        </div>
		</div>
	<div class="form-group col-md-3">
		    		<label style="display: block;"> &nbsp; </label>
		    		<button id="paymentDueSubmit" type="submit" class="btn btn-primary" onclick="addPaymentDue()">Submit</button>
		    		<div class="clearfix"></div>
		    	</div>
	
	<script src="<c:url value='/resources/js/jquery-1.12.4.min.js' />"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
	<script src="<c:url value='/resources/js/sweetalert.min.js'/>"></script>
	<script src="<c:url value='/resources/js/commonValidation.js?v=18.18' />"></script>
	
	<script src="<c:url value='/resources/js/separate/paymentPlanDues.js?v=18.15'/>"></script>
	
	
	
	<%@ include file="/WEB-INF/views/pages/footer.jsp" %>
	
	
</body>
</html>