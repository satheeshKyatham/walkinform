<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<!-- saved from url=(0049)http://safety.stepupitsolutions.com:83/Local.aspx -->
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	 response.addHeader("Expires","0");
	 response.addHeader("Pragma","no-cache");
	 response.setHeader("Cache-Control","no-cache,no-store, must-revalidate, pre-check=0, post-check=0, max-age=0, s-maxage=0");
	 response.addHeader("X-Frame-Options", "DENY");
	 %>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv = "refresh" content = "5; url = ${pageContext.request.contextPath}/saleslogin" />
		<title> Godrej </title>
		<meta name="description">
		
		<link rel="shortcut icon" href="<c:url value='/resources/images/favicon.ico' />" type="image/x-icon">
		<link rel="icon" href="<c:url value='/resources/images/favicon.ico' />" type="image/x-icon">
		
		<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />" type="text/css" />
		
		
			
	</head>
	<body >
	
	<div class="jumbotron col-md-4" style="margin: 0 auto; float: none; margin-top: 60px; text-align: center;">
	  <h3>Your session has expired</h3>
	  <p>Please <a style="text-decoration: underline;" href="${pageContext.request.contextPath}/saleslogin">login again</a></p>
	  <div>Redirecting in <h4 id="lblCount"></h4>seconds...</div> 
	  
	  
	  <!-- <button id="btnRedirect">Test Click</button> -->
	</div>
		
		<!--   <div >
		<h2>Your session is expired,Please click <a href="${pageContext.request.contextPath}/saleslogin">here</a> for login again</h2>
		
		</div>-->
		
		<script src="<c:url value='/resources/js/jquery-1.12.4.min.js'/>"></script>	
		<script src="<c:url value='/resources/js/enquiryRequest/sessiontimeout.js'/>"></script>		
		
	</body>
</html>
 
