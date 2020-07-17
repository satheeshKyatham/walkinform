<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@page import="java.util.HashMap"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<input id="pageContext" value="${pageContext.request.contextPath}" style="display:none;"/>
	
	<div style="margin-bottom:15px;">
		<img src='https://d4u.gplapps.com:8085/walkinform/resources/images/z3.png'/> <b style="margin-right:25px;">Channel Partner</b>
		<img src='https://d4u.gplapps.com:8085/walkinform/resources/images/Z1596.png'/> <b>Direct</b>
	</div>
	
	<div id="bookingMapLoading" style="text-align: center; display:none;">
		<h3> Loading... </h3>
		<h3> Work in progress </h3>
	</div>
	<div id="booking-map-canvas" style="height:700px;"></div>
	<%-- <script src="<c:url value='/resources/js/jquery-1.12.4.min.js' />"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
	<script src="<c:url value='/resources/js/separate/enqMap.js?v=18.15'/>"></script> --%>
	<!-- <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDM5bKqtFEgJ0D1jXEHx-nsZUPyBLNW7B4&libraries=places" async defer></script> -->