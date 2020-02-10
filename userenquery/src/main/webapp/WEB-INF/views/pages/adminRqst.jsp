<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@page import="java.util.HashMap"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<!-- Declair variables -->
<%!HashMap<String, String> UserData; %>

<% 
	try
	{
		session = request.getSession(false);
		if(session.getAttribute("UserData")!=null) {
			UserData = (HashMap<String, String>) session.getAttribute("UserData");
		}
	}catch(Exception e)
	{	
		e.printStackTrace();
	}
%>



<html lang="en">
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
	
	<input id="pageContext" value="${pageContext.request.contextPath}"/>
	
	<nav class="navbar navbar-default topMenuBar">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <a class="navbar-brand" href="#">
	        <img src="<c:url value="/resources/images/gplLogo.jpg"/>">
	      </a>
	    </div>
	    
	    <div class="topProTitle">
	    	GODREJ AQUA - PRICE SHEET
	    </div>
	    
		
	    <div class="pull-right">	
	    	<a class="" style="margin-top: 13px; display: inline-block; margin-right: 10px;"><%=UserData.get("usrName") %></a> <a href="logout" class="pull-right btn btn-default navbar-btn">Logout</a>
	    </div>
	    
	  </div>
	</nav>
	
	
	<div class="commonLoad" style="display:none;"></div>
	
	
  
	<div class="container ">
	
	
		
		
		
			  
			  
				<div class="col-md-12 col-sm-12">
					<h3 class="animated">Pending Requests</h3>
					<table class="table table-bordered" id="rqstFromAdmin">
						<thead>
							<tr class="">
								<th>ID</th>
								<th>Name</th>
								<th>Mobile</th>
								<!-- <th>Enquiry ID</th> -->
								<!-- <th>Other Discount</th> -->
								<th>Request From</th>
								<th>Request Date</th>
								<th>View</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody> </tbody>
					</table>
					<div class="clearfix"></div>
				</div>
				
				
				
				
				
				
				
				
			<div class="clearfix"></div>
	</div>
	

	
	
    <script src="<c:url value='/resources/js/jquery-1.12.4.min.js' />"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
	<script src="<c:url value='/resources/js/sweetalert.min.js'/>"></script>
	<script src="<c:url value='/resources/js/separate/adminRqst.js?v=18.08'/>"></script>
  </body>
</html>