<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
		<title> Godrej </title>
		<meta name="description">
<link rel="shortcut icon" href="<c:url value='/resources/images/favicon.ico' />" type="image/x-icon">
	<link rel="icon" href="<c:url value='/resources/images/favicon.ico' />" type="image/x-icon">
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css' />" type="text/css" />
     
	<link rel="stylesheet" href="<c:url value='/resources/css/inner.css?v=2.0' />">
	<link rel="stylesheet" href="<c:url value='/resources/css/style.css?v=2.0' />">
	<link rel="stylesheet" href="<c:url value='/resources/css/font-awesome.css' />">
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">

<%-- <link rel="shortcut icon" href="<c:url value='/resources/images/favicon.ico' />" type="image/x-icon">
	<link rel="icon" href="<c:url value='/resources/images/favicon.ico' />" type="image/x-icon">
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css' />" type="text/css" />
     
	<link rel="stylesheet" href="<c:url value='/resources/css/inner.css?v=2.0' />">
	<link rel="stylesheet" href="<c:url value='/resources/css/style.css?v=2.0' />">
	<link rel="stylesheet" href="<c:url value='/resources/css/font-awesome.css' />">
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css"> --%>
				
	</head>
	<body class="overflow_auto mBgGridnt" style="background:#0077b9">
		<div class="clearfix"></div>
		<div class=" col-md-12">
			<div class="login_bg " style="position: relative;">			
				<div class="login_cont">
					<div class=""><!-- #0077b9 -->
						<div class="commonLoad" id="loginColLoad" style="border-radius: 8px !important; display: none;"></div>
						
						<div class="lLoginCol">
							<img src="<c:url value='/resources/images/logo.png' />">
						</div>
						
						<h3 class="loginProjTitle">"${projectName }"</h3>
						<div class="clearfix"></div>
						<label class="inputLabel">Enter your registered mobile no.</label>
						<div class="txtCenter">	
							<input placeholder="Mobile no." type="number" id="inputNumber" onkeypress="doit_onKeyPress(event)"/>
						</div>
						<input type="text" id="iseoi" style="display: none" value="<%=request.getParameter("iseoi") %>">
						<input type="text" id="projectid" style="display: none" value="<%=request.getParameter("projectid") %>">
						<h5 id="invalidEntry"></h5>
						<div class="clearfix"></div>
						<div class="btnColLogin"> <a class="btn btn-primary btnNext" onclick="validatePhNo(this)" > <span>Submit</span> </a><div class="clearfix"></div></div>
							<div class="clearfix"></div>
					</div>
					<div class="clearfix"></div>
				</div>	
			</div>
		</div>
		<script src="<c:url value='/resources/js/jquery-1.12.4.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>  
	<script type="text/javascript" charset="utf8" src="<c:url value='/resources/js/jquery.dataTables.js'/>"></script>
	
	<script src="<c:url value='/resources/js/dataTables.buttons.min.js'/>"></script>
	<script src="<c:url value='/resources/js/jszip.min.js'/>"></script>
	<script src="<c:url value='/resources/js/buttons.html5.min.js'/>"></script>
	<script src="<c:url value='/resources/js/payment/ccAvenue.js?v=18.15'/>"></script>
	<script src="<c:url value='/resources/js/payment/ccAvenueLogin.js?v=18.15'/>"></script>	
	</body>
</html>
