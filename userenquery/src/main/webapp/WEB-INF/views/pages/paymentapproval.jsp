<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<link rel="shortcut icon" href="<c:url value='/resources/images/favicon.ico' />" type="image/x-icon">
<link rel="icon" href="<c:url value='/resources/images/favicon.ico' />" type="image/x-icon">
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css' />" type="text/css" />
     
<link rel="stylesheet" href="<c:url value='/resources/css/inner.css' />">
<link rel="stylesheet" href="<c:url value='/resources/css/style.css' />">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
	
<script src="<c:url value='/resources/js/jquery-1.12.4.min.js'/>"></script>
<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
<script type="text/javascript" charset="utf8" src="<c:url value='/resources/js/jquery.dataTables.js'/>"></script>
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
<input type="hidden" id="projectname" value="<%=projectname %>">
<input type="hidden" id="projectid" value="<%=projectid%>">
<input type="hidden" id="role" value="<%= projectrole %>">
<input type="hidden" id="userid" value="<%= userid %>">
  
  <nav class="navbar topMainBar">
	  <div class="container">
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
		<div class="titleCol">
			<h4 class="">
				<span id="projectTitle"><%= projectname %></span>					
			</h4>
			<div class="clearfix"></div>
		</div>	
		
	
	  	<div class="col-md-12">
	   		<div>
				<ul class="nav nav-tabs tabNav">
					<li onclick="" class="active" id="Payment_ApprReject_Id">
						<a href="#tab1" data-toggle="tab">Payment Approve/Reject</a>
					</li>
					 <li id="Payment_Approve_Id" onclick="">
						<a href="#tab2" data-toggle="tab">Payment Approved List</a>
					</li>
					<li id="Payment_Reject_Id" onclick="">
						<a href="#tab3" data-toggle="tab">Payment Rejected List</a>
					</li>    
				</ul>
				<div class=""></div>
			</div>
		
	   		<div class="tab-content formTabCont">
	   		<div class="tab-pane active" id="tab1">
				<div class="commonLoad" id="mainPageLoad" style="display: none;"></div>
				
				<div class="col-md-12">
					
					<div class="clearfix"></div>
					<table class="table table-bordered" id="Payment_ApprReject">
						<thead>
							<tr>
								<th>Enquiry No</th>
								<th>Payment Type</th>
								<th>Bank Name</th>
								<th>Branch</th>
								<th>Trx ID</th>
								<th>Trx Date</th>
								<th>Trx Amount</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
				</div>
	   		
				<div class="tab-pane" id="tab2">
					<div class="commonLoad" id="mainPageLoad" style="display: none;"></div>
					<div class="col-md-12">
						<div class="clearfix"></div>
						<table class="table table-bordered" id="Payment_Approval">
							<thead>
								<tr>
									<th>Enquiry No</th>
									<th>Payment Type</th>
									<th>Bank Name</th>
									<th>Branch</th>
									<th>Trx ID</th>
									<th>Trx Date</th>
									<th>Trx Amount</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
						<div class="clearfix"></div>
					</div>
				<div class="clearfix"></div> 
				</div>
				<div class="clearfix"></div>
				<div class="tab-pane" id="tab3"> 
				<div class="commonLoad" id="mainPageLoad" style="display: none;"></div>
					<div class="col-md-12">
						<div class="clearfix"></div>
						<table class="table table-bordered" id="Payment_Reject">
							<thead>
								<tr>
									<th>Enquiry No</th>
									<th>Payment Type</th>
									<th>Bank Name</th>
									<th>Branch</th>
									<th>Trx ID</th>
									<th>Trx Date</th>
									<th>Trx Amount</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
						<div class="clearfix"></div>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
			</div>
		  <div class="clearfix"></div>
		</div>  
     	<div class="clearfix"></div>
	
</body>
</html>