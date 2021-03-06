<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>My Token List</title>
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css' />" type="text/css" />
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">    
<link rel="stylesheet" href="<c:url value='/resources/css/inner.css?v=${sessionScope.version}' />">
<link rel="stylesheet" href="<c:url value='/resources/css/style.css?v=${sessionScope.version}' />">
<link rel="stylesheet" href="<c:url value='/resources/css/sweetalert.min.css' />">
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
		<%-- <div class="navbar-header" style="width:100%;">
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
				<li class="dropdown">
			  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${userName}<span class="caret"></span></a>  
			</li>
			</ul>
		<!-- </div> -->
			
		</div> --%>
		 <%@ include file="/WEB-INF/views/pages/header.jsp" %>
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
				<li class="active">
					<a href="#assignmentList" data-toggle="tab">Assignment</a>
				</li>
				<li id="eoiPriorityTab" onclick="getEOIList()">
					<a href="#setEOIPriority" data-toggle="tab">Set EOI Priority 
						<i class="fa fa-spinner fa-spin" style="display:none;"></i>
					</a>
				</li>
				<li>
					<a href="#tokenScreen" data-toggle="tab" onclick="getTokenScreenConfig()">Token Screen Configuration</a>
				</li>
			</ul>
			<div class=""></div>
		</div>
		<div class="tab-content formTabCont">
			
			<div class="tab-pane active" id="assignmentList"> 
				<!-- Assignment -->
				<div class="well center-block">
					<div class="form-inline">
						<i class="glyphicon glyphicon-filter" style="border-right: 1px solid #333; margin-right: 10px; padding-right: 10px;"></i>
						<div class="form-group">
							<label for="exampleInputName2">Token Type</label> 
							<select class="form-control" id="typeSelected">
								<option value="All" selected>All</option>
								<option value="W">W</option>
								<option value="E">E</option>
								<option value="G">G</option>
								<option value="MS">MS</option>
								<option value="MF">MF</option>
							</select>
						</div>
						<div class="form-group">
							<label for="exampleInputNameDate">From Date</label> 
							<input class="form-control" type="date" name="" id="txtFromDate">
							<label for="exampleInputNameDate">To Date</label> 
							<input class="form-control" type="date" name="" id="txtToDate">
						</div>
						<div class="form-group">
							<input class="form-control" type="button" value="Search" name="Search" id="amsearch" onclick="loadData()"/>
						</div>
					</div>
					<div class="clearfix"></div>
				</div>
				
				<div class="col-md-12">
					<div class="commonLoad" id="assignTouserLoader" style="background-attachment: inherit !important; background-position: center center !important;"></div>
					<div class="clearfix"></div>
					<h4>Assign Token to user.</h4>
					<table class="table table-bordered" id="appDetails">
						<thead>
							<!-- <tbody> -->
							<tr>
								<!-- <th>ID</th> -->
								<th>Token No.</th>
								<th>Enquiry No.</th>
								<th>Name</th>
								<!-- <th>Mobile</th> -->
								<th>Priority No</th>
								<th>Closing Mgr</th>
								<th>Created By</th>
								<th>Partner Type</th>
								 <th>Assign To</th>
								<th>Submit</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
						<!-- </tbody> -->
					</table>
					<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
				<div class="col-md-12" style="margin-top: 20px; border-top: 1px dotted #000; padding-top: 20px;">
					<div class="commonLoad" id="assignedLoader" style="background-attachment: inherit !important; background-position: center center !important;"></div>
					<h4>Assigned Token</h4>
					<div class="clearfix"></div>
					<table class="table table-bordered" id="assinedDetails">
						<thead>
							<tr>
								<th>Token No.</th>
								<th>Enquiry No.</th>
								<th>Name</th>
								<!-- <th>Mobile</th> -->
								<th>Priority No</th>
								<th>Closing Mgr</th>
								<th>Status</th>
								<th>Partner Type</th>
								<th>Assigned To</th>
								<th>Edit</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					<div class="clearfix"></div>
				</div>
			</div>
			<!-- END Assignment -->
			
			<!-- Set EOI Priority -->
			<div class="tab-pane" id="setEOIPriority">
				<%@ include file="/WEB-INF/views/pages/eoiData.jsp" %>
			</div>
			<!-- END Set EOI Priority -->
			
			<div class="tab-pane" id="tokenScreen">
				<%@ include file="/WEB-INF/views/pages/tokenScreenConfig.jsp" %>
			</div>
			
			<div class="clearfix"></div>
		</div>
		
		<div class="clearfix"></div>
	</div>

<script src="<c:url value='/resources/js/jquery-1.12.4.min.js'/>"></script>
<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>  
<script type="text/javascript" charset="utf8" src="<c:url value='/resources/js/jquery.dataTables.js'/>"></script>
<script src="<c:url value='/resources/js/enquiryRequest/assignToken.js?v=${sessionScope.version}'/>"></script>
<script src="<c:url value='/resources/js/salesdesk.js?v=${sessionScope.version}'/>"></script>
<script src="<c:url value='/resources/js/sweetalert.min.js'/>"></script>

<script src="<c:url value='/resources/js/separate/eoiData.js?v=${sessionScope.version}'/>"></script>
<script src="<c:url value='/resources/js/separate/tokenScreenConfig.js?v=${sessionScope.version}'/>"></script>

<%@ include file="/WEB-INF/views/pages/footer.jsp" %>

</body>
</html>  