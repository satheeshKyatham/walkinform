
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>D4U New Site Redirection</title>
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/login.css?v=${sessionScope.home_version}" />" type="text/css" />
<script src="<c:url value='/resources/js/jquery-1.12.4.min.js'/>"></script>			
<%-- <link rel="stylesheet" href="<c:url value="/resources/css/app.css?v=${sessionScope.home_version}" />" type="text/css" /> --%>	
</head>
<body>
<div class="clearfix"></div>
		<div class=" col-md-12">
			<header class="headerLogin">
				<div class="container">
					<div class="loginHeaderlogo">
						<img src="<c:url value="/resources/images/logo.png"/>" alt="Godrej Properties">
					</div>
				</div>
			</header>
<!-- <input type="button" id="btnRedirect" value="Redirect" />  --> 
<br />  
<br />  
<%-- D4U application is now migrated to Amazon Cloud. Please bookmark new URL (https://d4u.godrejpropeties.com) You will be redirected to new URL in <%> seconds. This URL will no longer work after June 30, 2021 --%>
<div id="dvCountDown" style="display: none;text-align: center;font-size: 30px;font-weight: bold;font-size: larger;" > 
<p>D4U application is now migrated to Amazon Cloud. Please bookmark new URL <a href="https://d4u.godrejproperties.com">(https://d4u.godrejproperties.com)</a>. You will be redirected to new URL in <span id="lblCount"></span>&nbsp;seconds.</p>
<a>https://d4u.gplapps.com:8085/walkinform/saleslogin</a> will no longer work after June 30, 2021. 
<br/>
For any queries , send email to d4u support email id.
<br/>
Thanks, D4U team. 
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
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>  
<script type="text/javascript">  
/* $(function () {  
    $("#btnRedirect").click(function () {   */
    	
    	$(document).ready(function() {
        var seconds = 10;  
        $("#dvCountDown").show();  
        $("#lblCount").html(seconds);  
        setInterval(function () {  
            seconds--;  
            $("#lblCount").html(seconds);  
            if (seconds == 0) {  
                $("#dvCountDown").hide();  
                window.location = "https://d4u.godrejproperties.com/";  
            }  
        }, 1000);  
    	});
/*     });  
});  */ 
</script>  


</body>
</html>