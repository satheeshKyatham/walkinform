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
<title>MIS Report</title>
 
 


<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css' />" type="text/css" />
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">

<link rel="stylesheet" href="<c:url value='/resources/css/font-awesome.css' />" type="text/css" />


     
<link rel="stylesheet" href="<c:url value='/resources/css/inner.css?v=2.0' />">
<link rel="stylesheet" href="<c:url value='/resources/css/style.css' />">
<link rel="stylesheet" href="<c:url value='/resources/css/enqMap.css' />">
<%-- 
<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>

<script src="<c:url value='/resources/js/salesdesk.js'/>"></script>
<script src="<c:url value='/resources/js/jquery-1.12.4.min.js'/>"></script> --%>

</head>


<%! String projectid,projectname,projectnameReq;%>
<%HttpSession ses=request.getSession(); 
projectname=(String)ses.getAttribute("PRONAME");
projectnameReq =request.getParameter("projectname");
projectid=request.getParameter("projectid");
%>
<input type="hidden" id="projectid" value="<%= projectid%>">
<script type="text/javascript">
        window.history.forward();
        function noBack()
        {
            window.history.forward();
        }
</script>
<body onLoad="noBack();" onpageshow="if (event.persisted) noBack();" onUnload="">  
  <input type="hidden" id="contextPath" value="${pageContext.request.contextPath}">
  <input type="hidden" id="projectname" value="<%= projectnameReq %>">
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
			<a class="navbar-brand" href="#"> <img class="topLogo"
				src="<c:url value='/resources/images/gplLogo.jpg' />" />
			</a>
			
			<!-- Collect the nav links, forms, and other content for toggling -->
		<!-- <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1"> -->

			<ul class="nav navbar-nav navbar-right pull-right">
				<!-- <ul class="dropdown-menu"> -->
				<li><a href="${pageContext.request.contextPath}/saleslogin">Logout</a></li>
				<!-- </ul> -->
				<!-- <li><a href="#"><i class="glyphicon glyphicon-log-out"></i> LOGIN</a></li> -->
				<%-- <li class="dropdown">
			  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${userName}<span class="caret"></span></a>
			  
			</li> --%>
			</ul>
		<!-- </div> -->
			
		</div>



		
	</div>
	</nav>


	<div class="container" style="margin-bottom:30px;">
		
		
		
		
		<div class="titleCol">
			<h4 class="">
				<span id="projectTitle"><%=projectnameReq%></span>
			</h4>
			<div class="clearfix"></div>
		</div>
		
		<div>
			<ul class="nav nav-tabs tabNav">
				<li class="active" id="basicInfoTabId">
					<a href="#tab1" data-toggle="tab">MIS Report</a>
				</li>
				<li id="salesTabId" onclick="getEnqDataForMap()">
					<a href="#tab2" data-toggle="tab">Walk-in's Map</a>
				</li> 
				<li id="salesTabId" onclick="createdOfferProject()">
					<a href="#tab3" data-toggle="tab">Created Offer List</a>
				</li> 
				<li onclick="getApplicationList()" id="applicationsListCol">
					<a href="#applications" data-toggle="tab">Applications <i class="fa fa-spinner fa-spin" style="display:none;"></i> </a>
				</li>
				<li onclick="getEOIReport()" id="eoiReportTab">
					<a href="#eoiReport" data-toggle="tab">EOI Report <i class="fa fa-spinner fa-spin" style="display:none;"></i> </a>
				</li>
				<li onclick="getPaymentEOIReport()" id="paymentEOIReportTab">
					<a href="#paymentEOIReport" data-toggle="tab">Payment Report <i class="fa fa-spinner fa-spin" style="display:none;"></i> </a>
				</li>
				<li id="allotmentReportID" onclick="getAllotmentDashboardReport()">
					<a href="#allotmentReportTab" data-toggle="tab">Allotment Day Report<i class="fa fa-spinner fa-spin" style="display:none;"></i></a>
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
							<label for="exampleInputNameDate">From Date</label> 
							<input class="form-control" type="date" name="" id="txtFromDate">
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
					<!-- <h4>Assign Token to user.</h4> -->
					<form:form action="downloadCSV" method="post" id="downloadCSV">
						<input type="hidden" id="projectid" value="<%=projectid%>" name="projectid"/>
						<input type="hidden" id="userid" value="" name="userid"/>
						<%-- <input type="hidden" value="<%=projectid%>" name="fromdate"/>
						<input type="hidden" value="<%=projectid%>" name="todate"/> --%>
						<fieldset style="width: 400px;">
							<h3>Download Enquiry Form Submitted Data</h3>
							<input id="submitId" type="submit" value="Downlaod In Excel">
						</fieldset>
					</form:form>
					<table class="table table-bordered" id="misReportDetails">
						<thead>
							<!-- <tbody> -->
							<tr>
								<th>Project Name</th>
								<th>Enquiry No</th>
								<th>Mobile No</th>
								<th>Token No</th>
								<th>Created Date</th>
								<th>Customer Name</th>
								<th>Email</th>
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
			<div class="tab-pane" id="tab2">
			<%@ include file="/WEB-INF/views/pages/enqOnMap.jsp" %>
			</div>
			<div class="tab-pane" id="tab3" >
			<%-- <jsp:include page="createofferPayment.jsp"></jsp:include> --%>
					<%@ include file="/WEB-INF/views/pages/createofferPayment.jsp" %>
				</div>
				
			<div class="tab-pane" id="applications">
				<%@ include file="/WEB-INF/views/pages/applications.jsp" %>
			</div>	
				
			<div class="tab-pane" id="eoiReport">
				<%@ include file="/WEB-INF/views/pages/eoiReport.jsp" %>
			</div>		
				
			<div class="tab-pane" id="paymentEOIReport">
				<%@ include file="/WEB-INF/views/pages/paymentEOIReport.jsp" %>
			</div>	
			<%-- <div class="tab-pane" id="tab7" >
					<%@ include file="/WEB-INF/views/pages/createofferPayment.jsp" %>
				</div> --%>	
			<div class="tab-pane" id="allotmentReportTab">
				<%@ include file="/WEB-INF/views/pages/allotmentReport.jsp" %>
			</div>	
				
			</div>
	</div>

<script src="<c:url value='/resources/js/jquery-1.12.4.min.js'/>"></script>
<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>  

<script src="<c:url value='/resources/js/separate/applicationForm.js?v=18.15'/>"></script>

<script src="<c:url value='/resources/js/separate/offerApplicationForm.js?v=18.16'/>"></script>


<script type="text/javascript" charset="utf8" src="<c:url value='/resources/js/jquery.dataTables.js'/>"></script>

<script src="<c:url value='/resources/js/dataTables.buttons.min.js'/>"></script>
<script src="<c:url value='/resources/js/jszip.min.js'/>"></script>
<script src="<c:url value='/resources/js/buttons.html5.min.js'/>"></script>

<script src="<c:url value='/resources/js/enquiryRequest/misreport.js?v=18.15'/>"></script>
<script src="<c:url value='/resources/js/separate/eoiReport.js?v=18.15'/>"></script>
<script src="<c:url value='/resources/js/separate/allotmentreport.js?v=18.15'/>"></script>
<%-- <script src="<c:url value='/resources/js/separate/offer.js?v=18.10'/>"></script> --%>

</body>
</html>  