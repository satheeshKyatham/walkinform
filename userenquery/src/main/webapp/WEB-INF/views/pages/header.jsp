<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%  
/* Cookie ck=new Cookie("version","18.20");  
response.addCookie(ck);   */
session.setAttribute("version","18.39");  
%>  

<%
	 response.addHeader("Expires","0");
	 response.addHeader("Pragma","no-cache");
	 response.setHeader("Cache-Control","no-cache,no-store, must-revalidate, pre-check=0, post-check=0, max-age=0, s-maxage=0");
	 response.addHeader("X-Frame-Options", "DENY");
	 %>
<head>
<link rel="stylesheet" href="<c:url value='/resources/css/multiselectDD/prettify.min.css' />" type="text/css">
<link rel="stylesheet" href="<c:url value='/resources/css/multiselectDD/bootstrap-multiselect.css' />" type="text/css">

<script type="text/javascript">
	var PAGECONTEXT_GV = '${pageContext.request.contextPath}/';
	
	var USERID_GV = '<%= session.getAttribute("USERID")%>';
	var ROLE_GV = '<%= session.getAttribute("ROLE")%>';
	var PROSFID_GV = '<%= session.getAttribute("PROJECTSFID")%>';
</script>

<script src="<c:url value='/resources/js/salesdesk.js?v=${sessionScope.version}'/>"></script>
</head>
<body>
<input type="hidden" id="loged_role" value="<%= session.getAttribute("ROLE")%>" />
<input type="hidden" id="userNameLoggedIn" value="<%= session.getAttribute("USERNAME")%>" />
<input type="hidden" id="loged_userid" value="<%= session.getAttribute("USERID")%>" />

  <div class="navbar-header" style="width:100%;">
      	<a class="navbar-brand" href="#">
			<img class="topLogo" src="<c:url value='/resources/images/gplLogo.jpg' />"/>
		</a>
		
		
		
		<ul class="nav navbar-nav navbar-right pull-right">
			<li style="padding-top: 15px; padding-right: 10px; color: #0077b9;"><label id="userNameLoggedInShow"></label></li>
			<li class="dropdown">
	          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Change Project/Roles <span class="caret"></span></a>
	          <ul class="dropdown-menu" id="changeProjectRole" style="width: 300px; padding: 10px;">
	            <li id="headerProjectDD">
					 
				</li>
	            <li id="role_page">
	            
				</li> 
	          </ul>
	        </li>
	       
	        <li id="adminOTPLink">
	        	<a href="#" onclick="otpModal()">Get OTP</a>
	        </li>
	        <li><a href="${pageContext.request.contextPath}/saleslogin" onclick="logoutSession()">Logout</a></li>

	    
		</ul> 
	    </div>
	
<!-- Modal -->
<div class="modal fade" id="OTPModal" role="dialog">
	<div class="modal-dialog modal-sm">
		<!-- Modal content-->
		<div class="modal-content">
		  	<div class="modal-header">
		    	<button type="button" class="close" data-dismiss="modal">&times;</button>
		    	<h4 class="modal-title">Get OTP</h4>
		  	</div>
			<div class="modal-body">
				<div class="form-group ">
			    	<label>OTP Request from</label>
			    	<select id="otpRequestorUser" class="form-control"></select>
			  	</div>
			  	<div class="form-group">
			    	<label>Customer Mobile No.</label>
			    	<input type="tel" id="mobile_num" class="form-control">
			  	</div>
			  	<div style="text-align:center;">
			  		<span id="otpNum" style="font-size: 22px;"></span>
			  	</div>
		  	</div>
		  	<div class="modal-footer" style="text-align: center;">
		    	<button type="button" id="getAdminOTP" class="btn btn-default btn-sm" style="background-color: #0077b9; color: #fff;">Get OTP</button>
		    	<button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
		  	</div>
		</div>
	</div>
</div>
	    
</body>
</html>
<script type="text/javascript">
document.getElementById('userNameLoggedInShow').innerHTML = 'Welcome '+'<%= session.getAttribute("USERNAME")%>';
var useridGlobvar = <%= session.getAttribute("USERID")%>

function logoutSession(){ 
	$.get("logout", {
	},
	function(data) {
		console.log("session out" , data);
	});
}

if ('<%=session.getAttribute("ISOTPADMIN")%>' != "Y") {
	document.getElementById('OTPModal').innerHTML = "";
	document.getElementById('adminOTPLink').innerHTML = "";
}
</script>