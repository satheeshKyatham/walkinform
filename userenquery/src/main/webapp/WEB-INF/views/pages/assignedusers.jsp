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

<link rel="shortcut icon" href="<c:url value='/resources/images/favicon.ico' />" type="image/x-icon">
	<link rel="icon" href="<c:url value='/resources/images/favicon.ico' />" type="image/x-icon">
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css' />" type="text/css" />
     
	<link rel="stylesheet" href="<c:url value='/resources/css/inner.css' />">
	<link rel="stylesheet" href="<c:url value='/resources/css/style.css' />">
	
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
	
<%-- <script src="<c:url value='/resources/js/jquery-1.12.4.min.js'/>"></script>
<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script> --%>

<%-- <script src="<c:url value='/resources/js/enquiryRequest/kycdetails.js'/>"></script> --%>
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
  <!-- <input type="text" > -->
  
  <nav class="navbar topMainBar">
	  <div class="container">
	    <!-- Brand and toggle get grouped for better mobile display -->
	    <div class="navbar-header" style="width:100%;">
	      <!-- <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button> -->
      	<a class="navbar-brand" href="#">
			<img class="topLogo" src="<c:url value='/resources/images/gplLogo.jpg' />"/>
		</a>
		
		<!-- Collect the nav links, forms, and other content for toggling -->
	   <!--  <div class="collapse navbar-collapse " id="bs-example-navbar-collapse-1"> -->
	      
		<ul class="nav navbar-nav navbar-right pull-right">
			 
			    <li><a href="${pageContext.request.contextPath}/saleslogin">Logout</a></li>
			 
		</ul> 
	   <!--  </div> -->
		
		
		
	    </div>
		
		 
		
	    
	  </div>
	</nav>
	
	<!-- <div class="container" style="margin-bottom:30px;"> -->
		<div class="titleCol">
			<h4 class="">
				<span id="projectTitle"><%= projectname %></span>					
			</h4>
			<div class="clearfix"></div>
		</div>	
		
	
	  	<div class="col-md-12">
	   		<div>
				<ul class="nav nav-tabs tabNav">
					<li class="active" id="basicInfoTabId">
						<a href="#tab1" data-toggle="tab">Assigned Token List</a>
					</li>
					 <li id="salesTabId" onclick="getCreatedOfferList()">
						<a href="#tab2" data-toggle="tab">Created Offer List</a>
					</li>  
					<!-- <li onclick="getApplicationList()" id="applicationsListCol">
						<a href="#applications" data-toggle="tab">Applications <i class="fa fa-spinner fa-spin" style="display:none;"></i> <span class="alphaCol">Beta</span></a>
					</li> -->
					<li onclick="getAllEnquiryFormReport()" id="basicInfoTabId">
						<a href="#tab4" data-toggle="tab">MIS Report</a>
					</li>
					<li onclick="getKYCCMDetails()" id="KYC_CM_Details_Tab_Id">
						<a href="#tab5" data-toggle="tab">KYC</a>
					</li>
					<li onclick="getKYCCMApprovalView()" id="KYC_CM_Approval_Tab_Id">
						<a href="#tab6" data-toggle="tab">KYC Approve/Reject</a>
					</li>
				</ul>
				<div class=""></div>
			</div>
		
	   		<div class="tab-content formTabCont">
				<div class="tab-pane active" id="tab1"> 
			      <div class="well center-block">
						<div class="form-inline">
							
							<i class="glyphicon glyphicon-filter" style="border-right: 1px solid #333; margin-right: 10px; padding-right: 10px;"></i>
							<div class="form-group">
								<label for="exampleInputNameDate">Date</label> 
								<input class="form-control" type="date" name="" id="txtFromDate">
							</div>
							<div class="form-group">
								<!-- <input class="form-control" type="button" value="Search" name="Search" id="amsearch" onclick="loadData()"/> -->
								
								<button style="padding-top:6px; padding-bottom:6px;" class="btn blue_btn btnCommon" type="button" value="Search" name="Search" id="amsearch" onclick="loadData()">
									<span>
										
									</span> Search
								</button>
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
			      
				  	<div class="col-md-12">
					 
						<div class="clearfix"></div>
						<table class="table table-striped"   id="dtOrderExample" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>ID</th>
									<th>Token No.</th>
									<th>Name</th>
									<th>Mobile</th>									
									<th>Priority No</th>									
									<th>Closing Mgr</th>									
									<th>Date</th>
									<th>Offer</th>
									<th>Status</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
						<div class="clearfix"></div>
					</div>
				</div>
				<div class="tab-pane" id="tab2">
					<%@ include file="/WEB-INF/views/pages/createdoffers.jsp" %>
				</div>
				
				<div class="tab-pane" id="applications">
					<%@ include file="/WEB-INF/views/pages/applications.jsp" %>
				</div>
				<div class="tab-pane" id="tab4">
					<div class="well center-block">
					<div class="form-inline">
						
						<i class="glyphicon glyphicon-filter" style="border-right: 1px solid #333; margin-right: 10px; padding-right: 10px;"></i>
						
						<div class="form-group">
							<label for="exampleInputNameDate">From Date</label> 
							<input class="form-control" type="date" name="" id="txtFromDateclsoing">
						</div>
						<div class="form-group">
							<label for="exampleInputNameDate">To Date</label> 
							<input class="form-control" type="date" name="" id="txtToDate">
						</div>
						<div class="form-group">
							<label for="exampleInputNameDate">Search</label> 
							<input class="form-control" type="button" value="Search" name="Search" id="amsearch" onclick="getDatewiseReport()"/>
						</div>
					</div>
					<div class="clearfix"></div>
				</div>
				
				<div class="commonLoad" id="mainPageLoad" style="display: none;"></div>
				
				<div class="col-md-12">
					
					<div class="clearfix"></div>
					<form:form action="downloadCSV" method="post" id="downloadCSV">
						<input type="hidden" value="<%=projectid%>" name="projectid"/>
						<input type="hidden" value="<%=userid%>" name="userid"/>
						<fieldset style="width: 400px;">
							<h3>Download Enquiry Form Submitted Data</h3>
							<input id="submitId" type="submit" value="Download In Excel">
						</fieldset>
					</form:form>
					<table class="table table-bordered" id="misReportDetails_CM">
						<thead>
							<!-- <tbody> -->
							<tr>
								<th>Project Name123</th>
								<th>Enquiry No</th>
								<th>Mobile No</th>
								<th>Token No</th>
								<th>Created Date</th>
								<th>Customer Name</th>
								<th style="word-break: break-word; width:200px !important">Email</th>
								<th>Budget</th>
								<th>Carpet Area</th>
								<th>Walk-in Source</th>
								<th>Assigned To</th>
								<th>Attended</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
						<!-- </tbody> -->
					</table>
					<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
				</div>
				
				
				<div class="tab-pane" id="tab5">
					<div class="commonLoad" id="mainPageLoad" style="display: none;"></div>
					<div class="col-md-12">
						<div class="clearfix"></div>
						<table class="table table-bordered" id="KYC_CM_Details">
							<thead>
								<tr>
									<th>Enquiry No</th>
									<th>Customer Name</th>
									<th>Mobile No</th>
									<th>KYC Status</th>
									<th>View/Fill KYC</th>
									
									<!-- <th style="word-break: break-word; width:200px !important">Email</th> -->
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
				<div class="tab-pane" id="tab6">
					<div class="commonLoad" id="mainPageLoad" style="display: none;"></div>
					<iframe id="kyc_cm_approval_iframe" width="100%" height="400px"></iframe>
				<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
			</div>
		  <div class="clearfix"></div>
		</div>  
	  
    	 
     	<div class="clearfix"></div>
	<!-- </div> -->
	
	<!-- <script type="text/javascript">
	$(document).ready(function () {
		$('#dtOrderExample').DataTable({
		"order": [[ 3, "desc" ]]
		});
		$('.dataTables_length').addClass('bs-select');
		});
	</script> -->
	<script src="<c:url value='/resources/js/jquery-1.12.4.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>  
	<script type="text/javascript" charset="utf8" src="<c:url value='/resources/js/jquery.dataTables.js'/>"></script>
	<script src="<c:url value='/resources/js/salesdesk.js?v=18'/>"></script>
	<script src="<c:url value='/resources/js/enquiryRequest/assignedUser.js?v=18'/>"></script>
	<script src="<c:url value='/resources/js/separate/offer.js?v=18'/>"></script>
	<script src="<c:url value='/resources/js/enquiryRequest/misreportforClosing.js?v=18'/>"></script>
	
	<script src="<c:url value='/resources/js/separate/applicationForm.js?v=18'/>"></script>
	<script src="<c:url value='/resources/js/separate/offerApplicationForm.js?v=18'/>"></script>
	
	
	
</body>
</html>

	
<!-- </html> -->