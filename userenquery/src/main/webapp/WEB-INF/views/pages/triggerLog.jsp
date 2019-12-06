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
	<link rel="stylesheet" href="<c:url value='/resources/css/sweetalert.min.css'/>">    
	<link rel="stylesheet" href="<c:url value='/resources/css/jquery.dataTables.css'/>">    
	
</head>
<body>

	<input id="pageContext" value="${pageContext.request.contextPath}" style="display:none;"/>

	<div class="container">
		<div class="row">
		
		    <div class="row">
				
				<div style="margin-top:20px;">
				  <!-- Nav tabs -->
				  <ul class="nav nav-tabs" role="tablist">
				    <li role="presentation" class="active"><a href="#tiggerLogTabCont" aria-controls="home" role="tab" data-toggle="tab">Trigger Log</a></li>
				    <li role="presentation"><a href="#tiggerLogArchiveTabCont" aria-controls="profile" role="tab" data-toggle="tab">Trigger Log Archive</a></li>
				  </ul>
				  <!-- Tab panes -->
				  <div class="tab-content">
				    <div role="tabpanel" class="tab-pane active" id="tiggerLogTabCont">
						<div>
							<h3>Trigger Log</h3>
							<div class="form-group col-md-3">
								<label>Date </label>
								<input class="form-control" type="date" id="tlFromDate">
							</div>
							<div class="form-group col-md-3">
								<label>&nbsp;</label>
								<button onclick="getTriggerLog()">Search</button>
							</div>
							
							
							<!-- <div class="form-group col-md-3">
								<label>To Date </label>
								<input class="form-control" type="date" id="tlToDate">
							</div> -->
							<!-- <h4>Created Offers List</h4> -->
							<div class="commonLoad" id="mainPageLoad" style="display: none;"></div>
							<table class="table table-bordered" id="triggerLogTable">
								<thead>
									<tr>
										<th>id</th>
										<th>txid</th>
										<th>created_at</th>
										<th>updated_at</th>
										<th>processed_at</th>
										<th>processed_tx</th>
										<th>state</th>	
										<th>action</th>
										<th>table_name</th>
										<th>record_id</th>
										<th>sfid</th>
										<th>old</th>
										<th>values</th>
										<th>sf_result</th>
										<th>sf_message</th>
									</tr>
								</thead>
								<thead class="tlAFilter" style="display:none;">
									<tr>
										<th>id</th>
										<th>txid</th>
										<th>created_at</th>
										<th>updated_at</th>
										<th>processed_at</th>
										<th>processed_tx</th>
										<th>state</th>	
										<th>action</th>
										<th>table_name</th>
										<th>record_id</th>
										<th>sfid</th>
										<th>old</th>
										<th>values</th>
										<th>sf_result</th>
										<th>sf_message</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
							
					    	 <div class="clearfix"></div>
					    	 </div>
					</div>
				    <div role="tabpanel" class="tab-pane" id="tiggerLogArchiveTabCont">
						 
		    	<div>
		    	<h3>Trigger Log Archive</h3>
		    	 <div class="form-group col-md-3">
					<label>Date </label>
					<input class="form-control" type="date" id="tlAFromDate">
				</div>
				<div class="form-group col-md-3">
					<label> &nbsp; </label>
					<button onclick="getTriggerLogArchive()">Search</button>
				</div>
				
				 
				<!-- <div class="form-group col-md-3">
					<label>To Date </label>
					<input class="form-control" type="date" id="tlAToDate">
				</div> -->
		    	
		    	
		    	<div class="clearfix"></div>
		    	 
		    	 <table class="table table-bordered" id="triggerLogTableArchive">
					<thead>
						<tr>
							<th>id</th>
							<th>txid</th>
							<th>created_at</th>
							<th>updated_at</th>
							<th>processed_at</th>
							<th>processed_tx</th>
							<th>state</th>	
							<th>action</th>
							<th>table_name</th>
							<th>record_id</th>
							<th>sfid</th>
							<th>old</th>
							<th>values</th>
							<th>sf_result</th>
							<th>sf_message</th>
						</tr>
					</thead>
					<thead class="tlAFilter" style="display:none;">
						<tr>
							<th>id</th>
							<th>txid</th>
							<th>created_at</th>
							<th>updated_at</th>
							<th>processed_at</th>
							<th>processed_tx</th>
							<th>state</th>	
							<th>action</th>
							<th>table_name</th>
							<th>record_id</th>
							<th>sfid</th>
							<th>old</th>
							<th>values</th>
							<th>sf_result</th>
							<th>sf_message</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
		    	
		    	 <div class="clearfix"></div>
		    	 </div>
					</div>
				  </div>
				</div>
				
				
				
				 
		    	 
		    </div>
		    
		    <div class="clearfix"></div>
		    
	        </div>
		</div>
	
	<script src="<c:url value='/resources/js/jquery-1.12.4.min.js' />"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
	<script src="<c:url value='/resources/js/sweetalert.min.js'/>"></script>
	
	<script type="text/javascript" charset="utf8" src="<c:url value='/resources/js/jquery.dataTables.js'/>"></script>
	
	<script src="<c:url value='/resources/js/separate/triggerLog.js'/>"></script>
	
</body>
</html>