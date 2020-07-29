<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment List</title>
<link rel="shortcut icon" href="<c:url value='/resources/images/favicon.ico' />" type="image/x-icon">
	<link rel="icon" href="<c:url value='/resources/images/favicon.ico' />" type="image/x-icon">
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css' />" type="text/css" />
     
	<link rel="stylesheet" href="<c:url value='/resources/css/inner.css?v=3.0' />">
	<link rel="stylesheet" href="<c:url value='/resources/css/style.css?v=3.0' />">
	<link rel="stylesheet" href="<c:url value='/resources/css/font-awesome.css' />">
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
</head>
<%-- <%! String projectname,projectid ,projectrole,userid;%>
<%
projectname = request.getParameter("projectname");
projectid = request.getParameter("projectid");
HttpSession ses=request.getSession(); 
if(ses!=null){
	  projectrole=(String)ses.getAttribute("ROLE");
	 userid=(String)ses.getAttribute("USERID");
}else{
}%> --%>
<body>
<input type="hidden" id="contextPath" value="${pageContext.request.contextPath}">
<input type="hidden" id="projectname" value="${projectName}">
<input type="hidden" id="enqsfid" value="${enqsfid}">
<%-- <input type="" id="projectid" value="${projectId}">
<input type="" id="enqId" value="${enqId }">
<input type="" id="userid" value="${userId}">  --%>
  <!-- <input type="text" > -->
 <script type="text/javascript">

 </script>
  <nav class="navbar topMainBar">
	  <div class="container">
	<%--  <%@ include file="/WEB-INF/views/pages/header.jsp" %> --%>
		 </div>
	</nav>
		<div class="titleCol">
			<h4 class="">
				 <span id="projectTitle">${projectTitle}</span> 					
			</h4>
			<div class="clearfix"></div>
		</div>	
		<div class="col-md-12">
	   		<div>
				<ul class="nav nav-tabs tabNav">
					<li class="active" id="basicInfoTabId">
						<a href="#" data-toggle="tab">Payment Details List</a>
					</li>
					
				</ul>
				<div class=""></div>
			</div>
		
	   		<div class="tab-content formTabCont">
				<div class="tab-pane active" id="tab1"> 	
				<h4 id="payment_resp" style="text-align: center;color: #0077b9;"></h4>		      	
			      	<div class="row">
			      		<div class="col-md-12">
							<div class="clearfix"></div>
							<table class="table table-striped table-bordered" id="dtOrderExample" cellspacing="0" width="100%">
								<thead>
									<tr>
										<th id="enquiry_Id">Enquiry</th>
										<th>Project Name</th>
										<th>Amount</th>	
										<th>Transaction Id</th>	
										<th>Transaction Status</th>									
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
							<div class="clearfix"></div>
						</div>
			      		<div class="clearfix"></div>
			      	</div>  	
				</div>
				<div class="clearfix"></div>
			</div>
		  <div class="clearfix"></div>
		</div>  
	  
    	 
     	<div class="clearfix"></div>
	<script src="<c:url value='/resources/js/jquery-1.12.4.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>  
	<script type="text/javascript" charset="utf8" src="<c:url value='/resources/js/jquery.dataTables.js'/>"></script>
	
	<script src="<c:url value='/resources/js/dataTables.buttons.min.js'/>"></script>
	<script src="<c:url value='/resources/js/jszip.min.js'/>"></script>
	<script src="<c:url value='/resources/js/buttons.html5.min.js'/>"></script>
	<script src="<c:url value='/resources/js/payment/ccAvenue.js?v=18.54'/>"></script>
	 <script type="text/javascript">	getCCAvenuePaymentDetails();</script> 
	<%@ include file="/WEB-INF/views/pages/footer.jsp" %>
</body>
</html>