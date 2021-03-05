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
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1">
		<title> Godrej Properties</title>
		<meta name="description">
		<%@ include file="/WEB-INF/views/pages/home.jsp" %>
		<link rel="shortcut icon" href="<c:url value='/resources/images/favicon.ico' />" type="image/x-icon">
		<link rel="icon" href="<c:url value='/resources/images/favicon.ico' />" type="image/x-icon">
		
		<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />" type="text/css" />
		<link rel="stylesheet" href="<c:url value="/resources/css/login.css?v=${sessionScope.home_version}" />" type="text/css" />		
		<link rel="stylesheet" href="<c:url value="/resources/css/app.css?v=${sessionScope.home_version}" />" type="text/css" />		
		
		<script src="<c:url value='/resources/js/salesdesk.js?v=${sessionScope.home_version}'/>"></script>	
		<script src="<c:url value='/resources/js/loginDropdown.js?v=${sessionScope.home_version}'/>"></script>
		<script src="<c:url value='/resources/js/jquery-1.12.4.min.js'/>"></script>		
		
	</head>
	<body class="overflow_auto">
		<%-- <img class="login_page_bg" src="<c:url value="/resources/images/loginBg.jpg"/>"> --%>
		<img class="login_page_bg" src="<c:url value="/resources/images/bg-login.jpg"/>">
		<div class="clearfix"></div>
		<div class=" col-md-12">
			
			<header class="headerLogin">
				<div class="container">
					<div class="loginHeaderlogo">
						<img src="<c:url value="/resources/images/logo.png"/>" alt="Godrej Properties">
					</div>
				</div>
			</header>
			
			<div class="login_bg col-md-3 col-sm-6">
			
			<div class=""></div>
			
				<!-- <div class="module_logo" >
					<img src="images/logo1.png">
				</div>	 -->			
				<div class="login_cont">
					 
						
						<%-- <div class="lLoginCol">
							<img src="<c:url value="/resources/images/logo.png"/>">
						</div> --%>
						<div class="commonLoadLogin" id="mainPageLoad" style="display: none;"></div>
						<div id="login_div">
							<label class="inputLabel">Email</label>
							<input type="text" name='username' id="userid"  style="margin-bottom:20px;" autofocus/>
							
							<label class="inputLabel">Password</label>
							<input type="password" name='password' id="password" />
							
							<div class="clearfix"></div>
							<div class="btnCol" style="margin-top: 20px; text-align: center;"> 
								<button class="btn btn-primary btnNext" id="loginbtn" onclick="validateDesk();"> 
									<span>LOGIN</span> 
								</button>
								
								 
									<div class="loginMsg" style="color: red;"></div>
								 
								
								<div class="clearfix"></div>
							</div>
						</div>
						<div id="loged_userid_main" style="display:none"></div>
						<div id="projectdropdownlist" style="display:none">
							<label class="inputLabel">Project</label>
							<div id="sip_id">
								<%-- <select  class="inputLabel" id="projectselection" style=" border-color: #000000 !important;   width: 100%;    min-height: 33px;    margin-bottom: 5px;">
									 <c:forEach items = "${Projects}"    var ="projects" varStatus="loop"  >
										<option     value="${projects.project_18_digit__c}">${projects.name}</option >
			    					</c:forEach>
		    					</select> --%>
	    					</div>
	    					<label class="inputLabel">Role</label>
	    					<div id="role_page">
	    					
	    					</div>
    					</div>
						<div class="clearfix"></div>
					 
					<div class="clearfix"></div>
				</div>	
			</div>
			
			<footer class="footer">
				<div class="footer-main">
					<div class="container-fluid">
						<div class="row">
							<div class="col-sm-12">
								<div class="logo">
									<img src="<c:url value="/resources/images/d4u_logo.png"/>" alt="D4U">
								</div>
								<ul class="footer-links">
									<li>Copyright © 2021. Godrej Properties</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</footer>
			
		</div>
	</body>
</html>
 
<script type="text/javascript">
var input = document.getElementById("password");
input.addEventListener("keyup", function(event) {
  if (event.keyCode === 13) {
   event.preventDefault();
   /* if($("#userid").val().trim()=='' || $("#password").val().trim()==''){
		  $("#mainPageLoad").hide();
	  	 $(".loginMsg").html("Email ID and Password not empty.");  
	  return false;
	} */	
   document.getElementById("loginbtn").click();
  }
});
</script>