<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <html>
  <%
	 response.addHeader("Expires","0");
	 response.addHeader("Pragma","no-cache");
	 response.setHeader("Cache-Control","no-cache,no-store, must-revalidate, pre-check=0, post-check=0, max-age=0, s-maxage=0");
	 response.addHeader("X-Frame-Options", "DENY");
	 %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Users</title>
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css' />" type="text/css" />
     
	<link rel="stylesheet" href="<c:url value='/resources/css/inner.css' />">
	<link rel="stylesheet" href="<c:url value='/resources/css/style.css' />">
<script src="<c:url value='/resources/js/jquery-1.12.4.min.js'/>"></script>
<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>


 <link rel="stylesheet" href="<c:url value='/resources/css/inventory.css' />">

</head> 
<%! String projectname,projectid ,projectrole,userid;%>
<%
projectname = request.getParameter("projectname");
projectid = request.getParameter("projectid");
HttpSession ses=request.getSession(); 
if(ses!=null){
	//  projectname=(String)ses.getAttribute("PRONAME");
	 // projectid=(String)ses.getAttribute("PROID");
	  projectrole=(String)ses.getAttribute("ROLE");
	 userid=(String)ses.getAttribute("USERID");
	
	
}else{
	 // Todo Login page
} 
%> 
<body>
<input type="hidden" id="contextPath" value="${pageContext.request.contextPath}">
<input type="hidden" id="projectname" value="${projectName}">
<input type="hidden" id="projectid" value="${projectSfid}">
<input type="hidden" id="userid" value="${userId}">
  <nav class="navbar topMainBar">
	  <div class="container">
	    <!-- Brand and toggle get grouped for better mobile display -->
	    <div class="navbar-header" style="width:100%;">
      	<a class="navbar-brand" href="#">
			<img class="topLogo" src="<c:url value='/resources/images/gplLogo.jpg' />"/>
		</a>	      
		<ul class="nav navbar-nav navbar-right pull-right">
			 
			    <li><a href="${pageContext.request.contextPath}/saleslogin">Logout</a></li>
			 
		</ul> 
	    </div>
	    
	    
	  </div>
	</nav>
	
	
	<div class="container" style="margin-bottom:30px;">
	
		<div class="titleCol">
				<h4 class="">
					<span id="projectTitle">${projectName}</span>					
				</h4>
				<div class="clearfix"></div>
		</div>
		
		<div>
			<ul class="nav nav-tabs tabNav">
				<li class="active" id="basicInfoTabId">
					<a href="#tab1" data-toggle="tab">Admin Inventory</a>
				</li>
				 <li id="salesTabId" onclick="">
					<a href="#tab2" data-toggle="tab">Hold Inventory</a>
				</li>  
			</ul>
			<div class=""></div>
		</div>
		
		<div class="tab-content formTabCont">
			<div class="tab-pane active" id="tab1">
			  	<div class="col-md-2" style="margin-top:10px; margin-bottom:10px;">
			   
				  <div class="clearfix"></div>
				</div>  
			      
				  	<!-- <div class="col-md-12"> -->
					 <div class="tab-pane" id="inventoryTabCont">
						  <jsp:include page="inventoryholdrel.jsp"></jsp:include>
						</div>
					<div class="clearfix"></div>
			 	<!-- </div> -->
		 	</div>
		 	<div class="tab-pane" id="tab2" >
		 		<%-- <jsp:include page="inventoryhold.jsp"></jsp:include> --%>
				<%@ include file="/WEB-INF/views/pages/inventoryhold.jsp" %>
			</div>
	 	</div>
	</div>
</body>


 <script src="<c:url value='/resources/js/separate/admininventory.js?v=16'/>"></script>	
<!-- </html> -->