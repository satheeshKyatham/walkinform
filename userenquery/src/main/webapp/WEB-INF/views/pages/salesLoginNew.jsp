<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
		<title> Godrej </title>
		<meta name="description">
		 


		<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css' />" type="text/css" />
	 
        <link rel="stylesheet" href="<c:url value='/resources/css/sweetalert2.min.css' />">
		<!-- By a -->
		<link rel="stylesheet" href="<c:url value='/resources/css/style.css' />">
		<link rel="stylesheet" href="<c:url value='/resources/css/inner.css?v=2.0' />" type="text/css" />
		<!--<link rel="stylesheet" href="css/common.css" type="text/css" /> -->
		<script src="<c:url value='/resources/js/jquery-1.12.4.min.js'/>"></script>
		<script src="<c:url value='/resources/js/enquiryRequest/salesLoginNew.js' />"></script>
		<script src="<c:url value='/resources/js/sweetalert2.min.js' />"></script>
		<script src="<c:url value='/resources/js/sweetalert2.min.js' />"></script>
		<script src="<c:url value='/resources/js/springForm.js' />"></script>
	    <script src="<c:url value='/resources/js/commonValidation.js' />"></script>
	    <script src="<c:url value='/resources/js/utility.js' />"></script>
	    
	    <!-- Add By A -->
	     <link rel="stylesheet" href="<c:url value="/resources/css/login.css" />" type="text/css" />     
	    <style type="text/css">
	     #userid {margin-bottom:20px !important;}
	    </style>
	</head>
	<body class="overflow_auto mBgGridnt">
		 
		 
		 <!-- Add By A -->
		  <img class="login_page_bg" src="<c:url value="/resources/images/loginBg.jpg"/>">
		 <div class="clearfix"></div>
		 <div class=" col-md-12">
		  <div class="login_bg col-md-3 col-sm-6">
		   <!-- <div class="module_logo" >
		    <img src="images/logo1.png">
		   </div>  -->   
		   <div class="login_cont">
		   <input type="hidden" id="contextPath" value="${pageContext.request.contextPath}">
		   <div class="commonLoad" id="mainPageLoad" style="display:none;"></div>
		    <form id="salesLoginForm">
		     
		     <div class="lLoginCol">
		      <img src="<c:url value="/resources/images/logo.png"/>">
		     </div>
		     
		     <label class="inputLabel">Username</label>
		     <input  type="text" id="userid" class="requiredField" style="margin-bottom:20px;"/>
		     <label class="inputLabel">Password</label>
		      <input   type="password" id="password" class="requiredField"/>
		     
		     <div class="clearfix"></div>
		     <div class="btnCol"> <a class="btn btn-primary btnNext" onclick="validateLogin(this);"> <span>LOGIN</span> </a><div class="clearfix"></div></div>		     
		     <div class="clearfix"></div>
		    </form>
		    <div class="clearfix"></div>
		   </div> 
		  </div>
		 </div>
		 <!-- END Add By A -->
		 
		 
		<%-- <div class="clearfix"></div>
		<div class=" col-md-12">
			<div class="login_bg ">
				<!-- <div class="module_logo" >
					<img src="images/logo1.png">
				</div>	 -->			
				<div class="login_cont">
					<div class="col-md-12">
						<input type="hidden" id="contextPath" value="${pageContext.request.contextPath}">
						<div class="lLoginCol">
							<img src="<c:url value='/resources/images/logo.png' />">
						</div>
						 <div class="commonLoad" id="mainPageLoad" style="display:none;"></div>
						<form id="salesLoginForm">
						<!-- <h3 class="loginProjTitle">Godrej Aqua</h3> -->
						<div class="clearfix"></div>
						<label class="inputLabel">User ID</label>
						<div class="txtCenter col-md-3">	
							<input  type="text" id="userid" class="requiredField"/>
						</div>
						<div class="clearfix"></div>
						<br>
						<div class="clearfix"></div>
						<label class="inputLabel">Password</label>
						<div class="txtCenter col-md-3">	
							<input   type="password" id="password" class="requiredField"/>
						</div>
						<h5 id="invalidEntry"></h5>
						<div class="clearfix"></div>
						<div class="btnCol"> <a class="btn btn-primary btnNext" onclick="validateLogin(this);"> <span>Submit</span> </a><div class="clearfix"></div></div>
							<div class="clearfix"></div>
					    </form>
					</div>
					<div class="clearfix"></div>
				</div>	
			</div>
		</div> --%>
	</body>
</html>
