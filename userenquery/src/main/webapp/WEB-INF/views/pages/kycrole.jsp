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
<title>Users</title>

<link rel="shortcut icon" href="<c:url value='/resources/images/favicon.ico' />" type="image/x-icon">
<link rel="icon" href="<c:url value='/resources/images/favicon.ico' />" type="image/x-icon">
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css' />" type="text/css" />
     
	<link rel="stylesheet" href="<c:url value='/resources/css/inner.css' />">
	<link rel="stylesheet" href="<c:url value='/resources/css/style.css' />">
	
	<link rel="stylesheet" href="<c:url value='/resources/css/font-awesome.css' />">
	
	
	
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
	
<script src="<c:url value='/resources/js/jquery-1.12.4.min.js'/>"></script>
<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
<script type="text/javascript" charset="utf8" src="<c:url value='/resources/js/jquery.dataTables.js'/>"></script>
<script type="text/javascript" charset="utf8" src="<c:url value='/resources/js/separate/kycdetails.js'/>"></script>
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
<input id="pageContext" value="${pageContext.request.contextPath}" style="display:none;"/>
<input type="hidden" id="projectname" value="<%=projectname %>">
<input type="hidden" id="projectid" value="<%=projectid%>">
<input type="hidden" id="role" value="<%= projectrole %>">
<input type="hidden" id="userid" value="<%= userid %>">
  <!-- <input type="text" > -->
    <script type="text/javascript">
  var offerTemplate = '${offerTemplate}';
  </script>
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
					<li onclick="getCMKYCDetails()" class="active" id="KYC_Admin_Details_Id">
						<a href="#tab1" data-toggle="tab">KYC</a>
					</li>
					 <li id="salesTabId" onclick="getKYCApprovalView()">
						<a href="#tab2" data-toggle="tab">KYC Approve/Reject</a>
					</li>  
				</ul>
				<div class=""></div>
			</div>
		
	   		<div class="tab-content formTabCont">
	   		<div class="tab-pane active" id="tab1">
				<div class="commonLoad" id="mainPageLoad" style="display: none;"></div>
				
				<div class="col-md-12">
					
					<div class="clearfix"></div>
					<table class="table table-bordered" id="KYC_Admin_Details">
						<thead>
							<tr>
								<th>Enquiry No</th>
								<th>Customer Name</th>
								<th>Mobile No</th>
								<th>KYC Status</th>
								<th>Offer Number</th>
								<th>Unit Number</th>
								<th>View/Fill KYC</th>
								<th>Cost sheet</th>
								<th>Application Form</th>
								<th>Total Amount</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
				<div id= "applicationFormKyc">
					<%-- <%@ include file="/WEB-INF/views/pages/createdOfferKyc.jsp" %> --%>
					<%@ include file="/WEB-INF/views/pages/createOfferKYCTemplate.jsp" %>
				</div>
				<div class="clearfix"></div>
				</div>
				
	  	 		
				<div class="tab-pane" id="tab2"> 
				  	<iframe id="kyc_approval_iframe" width="100%" height="400px"></iframe>
				</div>
				
				
				<div class="clearfix"></div>
			</div>
		  <div class="clearfix"></div>
		</div>  
	  
    	 
     	<div class="clearfix"></div>
     	
     	<!-- Model -->
	<div class="modal fade" id="paymentDetails_KYC" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document" style="width: 100%;">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="ModalLabelAdmin">PrePayment Details</h4>
	      </div>
	      <div class="modal-body">
	      <div class="col-md-12" style="margin-bottom:50px;">
				<table class="table table-bordered" id="prePaymentDetails_table_KYC">
						<thead>
							<tr>
								<th>Payment Type </th>
								<th>Bank Name</th>
								<!-- <th>Branch</th> -->
								<th>Transaction ID</th>
								<th>Transaction</br>Date</th>
								<th>Transaction Amount</th>
								<th>PAN Attachment</th>
								<th>Receipt/</br>Cheque Attachment</th>
								<th>Description</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				<div class="clearfix"></div>
			</div>
	      <div class="clearfix"></div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-primary btn-default" data-dismiss="modal">Close</button>
	      </div>
	    </div>
	  </div>
	</div>
	</div>
	<script src="<c:url value='/resources/js/salesdesk.js?v=18.09'/>"></script>
	<script src="<c:url value='/resources/js/separate/offer.js?v=18.09'/>"></script>
	
	<script src="<c:url value='/resources/js/separate/applicationForm.js?v=18.09'/>"></script>
	<script src="<c:url value='/resources/js/separate/offerApplicationForm.js?v=18.09'/>"></script>
	<script src="<c:url value='/resources/js/demo/offerTemplate.js?v=18.09'/>"></script>	
</body>
</html>

	
<!-- </html> -->