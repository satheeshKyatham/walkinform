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
	
	<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css' />" type="text/css" />
     <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
     <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/fixedHeader.dataTables.min.css' />">
	
	<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css' />">
	<link rel="stylesheet" href="<c:url value='/resources/css/costsheet.css' />">
	<link href="<c:url value='/resources/css/animate.min.css' />" rel="stylesheet">	
	<link rel="stylesheet" href="<c:url value='/resources/css/sweetalert.min.css'/>">   
	
	<link rel="stylesheet" href="<c:url value='/resources/css/editor.css' />">
	<link rel="stylesheet" href="<c:url value='/resources/css/font-awesome.css' />">
</head>
<body>

	<input id="pageContext" value="${pageContext.request.contextPath}" style="display:none;"/>

	<div class="container">
		<div class="row">
			<h2>Update Payment Plan</h2>
		    
		    <div class="row">
				<div class="form-group col-md-3">
					<label>Region</label>
					<select class="form-control" id="regionList" onchange="projectDataList()">
						<option value="">Select</option>
					</select>
				</div>
				
				<div class="form-group col-md-3">
					<label>Project</label>
					<select class="form-control" id="projectDataList" onchange="getPPAgainstProject()">
						<option value="">Select</option>
					</select>
				</div>

		    	<div class="clearfix"></div>
		    	
		    	
		    	<div class="form-group col-md-3">
					<label>Bulk  Action</label>
					<select class="form-control" id="bulkSelection">
						<option value="">Select</option>
						<option value="true">D4U Active</option>
						<option value="false">D4U Inactive</option>
					</select>
				</div>
		    	
		    	<div class="form-group col-md-3">
		    		<label style="display: block;"> &nbsp; </label>
		    		<button type="submit" class="btn btn-default btn-primary" onclick="updatePP()">Update</button>
		    		<div class="clearfix"></div>
		    	</div>
		    	
		    	 <div class="clearfix"></div>
		    </div>
		    
		    <div>
		    	<table class="table table-bordered display" id="ppDtl">
					<thead>
						<tr>
							<th>
								<input id="select-all" type="checkbox">
							</th>
							<th>Payment Plan Name</th>
							<th>D4U Active</th> 
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
		    </div>
		    
		    <div class="clearfix"></div>
		    
		   
	        </div>
		</div>
	
	
	<script src="<c:url value='/resources/js/jquery-1.12.4.min.js' />"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
	<script src="<c:url value='/resources/js/sweetalert.min.js'/>"></script>
	 
	 <script type="text/javascript" charset="utf8" src="<c:url value='/resources/js/jquery.dataTables.js'/>"></script>
	<script src="<c:url value='/resources/js/dataTables.buttons.min.js'/>"></script>
	<script src = "<c:url value='/resources/js/dataTables.fixedHeader.min.js'/> "></script>
	
	<script src="<c:url value='/resources/js/separate/updatePaymentPlan.js?v=18.18'/>"></script>
	
	
</body>
</html>