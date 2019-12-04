<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<!-- saved from url=(0049)http://safety.stepupitsolutions.com:83/Local.aspx -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title> Godrej </title>
		<meta name="description">
		
		
		
		<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />" type="text/css" />
		<link rel="stylesheet" href="<c:url value="/resources/css/login.css" />" type="text/css" />					
	</head>
	<body class="overflow_auto">
		<img class="login_page_bg" src="<c:url value="/resources/images/loginBg.jpg"/>">
		<div class="clearfix"></div>
		<div class=" col-md-12">
			<div class="login_bg col-md-3 col-sm-6">
				<!-- <div class="module_logo" >
					<img src="images/logo1.png">
				</div>	 -->			
				<div class="login_cont">
					<form class="" name='login' action="loginform" method='POST'>
						
						<div class="lLoginCol">
							<img src="<c:url value="/resources/images/logo.png"/>">
						</div>
						
						<label class="inputLabel">Username</label>
						<input type="text" name='username'  style="margin-bottom:20px;" autofocus/>
						
						<label class="inputLabel">Password</label>
						<input type="password" name='password' />
						
						<div class="clearfix"></div>
						<div class="btnCol" style="margin-top: 20px; text-align: center;"> 
							<button class="btn btn-primary btnNext" type="submit"> 
								<span>Login</span> 
							</button>
							
							<c:if test="${not empty error}">
								<div class="loginMsg">${error}</div>
							</c:if>
							
							<div class="clearfix"></div>
						</div>
						
						<div class="clearfix"></div>
					</form>
					<div class="clearfix"></div>
				</div>	
			</div>
		</div>
	</body>
</html>