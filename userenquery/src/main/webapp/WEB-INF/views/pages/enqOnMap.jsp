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

</head>
<body>
	<input id="pageContext" value="${pageContext.request.contextPath}" style="display:none;"/>
	<!-- <div style="margin-top:20px;">
		<div class="form-group col-md-3">
			<label>Region</label>
			<select class="form-control" id="regionList" onchange="projectDataList()"> </select>
		</div>
		
		<div class="form-group col-md-3">
			<label>Project</label>
			<select class="form-control" id="projectDataList"> </select>
		</div>
	
		<div class="form-group col-md-3">
	   		<label style="display: block;"> &nbsp; </label>
	   		<button type="submit" class="btn btn-default btn-primary" onclick="getEnqDataForMap()">Search</button>
	   		<div class="clearfix"></div>
	   	</div>
		<div class="clearfix"></div>  
	 </div>  	
	
	<div class="clearfix"></div> -->
	<div style="margin-bottom:15px;">
		<img src='https://d4u.godrejproperties.com/walkinform/resources/images/z3.png'/> <b style="margin-right:25px;">Channel Partner</b>
		<img src='https://d4u.godrejproperties.com/walkinform/resources/images/Z1596.png'/> <b>Direct</b>
	</div>
	<div id="mapLoading" style="text-align: center; display:none;">
		<h3> Loading... </h3>
		<h3> Work in progress </h3>
	</div>
	<div id="map-canvas" style="height:700px;"></div>
	<script src="<c:url value='/resources/js/jquery-1.12.4.min.js' />"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
	<script src="<c:url value='/resources/js/separate/enqMap.js?v=18.15'/>"></script>
	<!-- <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB1YQeqmJoubsXBr0BLkIqOtwWyYk2mKJQ&libraries=places" async defer></script> -->
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD6tHZRohy-YmfuSL_TuvYpLt33w4THz1M&libraries=places" async defer></script>
	
	<!-- &callback=getEnqDataForMap -->
</body>
</html>