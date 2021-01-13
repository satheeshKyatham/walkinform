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
     
<link rel="stylesheet" href="<c:url value='/resources/css/inner.css?v=2.0' />">
<link rel="stylesheet" href="<c:url value='/resources/css/style.css?v=2.0' />">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="<c:url value='/resources/css/sweetalert2.min.css' />"> 
	
<script src="<c:url value='/resources/js/jquery-1.12.4.min.js'/>"></script>
<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
<script type="text/javascript" charset="utf8" src="<c:url value='/resources/js/jquery.dataTables.js'/>"></script>
<script type="text/javascript" charset="utf8" src="<c:url value='/resources/js/payment/paymentapprove.js'/>"></script>
<script type="text/javascript" charset="utf8" src="<c:url value='/resources/js/refundreport/initiateRefundEOI.js?v=${sessionScope.version}'/>"></script>
<script src="<c:url value='/resources/js/sweetalert2.min.js' />"></script>
<script src="<c:url value='/resources/js/utility.js?v=${sessionScope.version}' />"></script> 
</head> 
<%! String projectname,projectid ,projectrole,userid,region_name;%>
<%
projectname = request.getParameter("projectname");
projectid = request.getParameter("projectid");
region_name = request.getParameter("region_name");
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
<input type="hidden" id="region_name" value="<%= region_name %>">
  <nav class="navbar topMainBar">
	  <div class="container">
	  <%--   <div class="navbar-header" style="width:100%;">
      	<a class="navbar-brand" href="#">
			<img class="topLogo" src="<c:url value='/resources/images/gplLogo.jpg' />"/>
		</a>
		<ul class="nav navbar-nav navbar-right pull-right">
			 <li><a href="${pageContext.request.contextPath}/saleslogin">Logout</a></li>
		</ul> 
	    </div> --%>
		<%@ include file="/WEB-INF/views/pages/header.jsp" %>
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
					<li onclick="getPendingPaymentDetails()" class="active" id="Payment_Pending_Id">
						<a href="#tab1" data-toggle="tab">Payment Approval Pending</a>
					</li>
					 <li id="Payment_Approve_Id" onclick="getApprovedPaymentDetails()">
						<a href="#tab2" data-toggle="tab">Payment Approved List</a>
					</li>
					<li id="Payment_Reject_Id" onclick="getRejectedPaymentDetails()">
						<a href="#tab3" data-toggle="tab">Payment Rejected List</a>
					</li>
					<li id="EOI_Refund_Process_Id" onclick="getEOIREFUNDDetails()">
						<a href="#tab4" data-toggle="tab">EOI REFUND List</a>
					</li>    
				</ul>
				<div class=""></div>
			</div>
		
	   		<div class="tab-content formTabCont">
	   		<div class="tab-pane active" id="tab1">
				<div class="commonLoad" id="mainPageLoad" style="display: none;"></div>
				
				<div class="col-md-12" style="overflow: auto;">
					<button onclick="approvedEOIPayment()">Approve Selected</button>
					<button onclick="rejectEOIPayment()">Reject Selected</button>
					<div class="clearfix"></div>
					<table class="table table-bordered" id="Payment_Pending">
						<thead>
							<tr>
							<th style="display:none">ID</th>
								<th></th>
								<th>Created Date</th>
								<th>Enquiry No</th>
								<th>Customer Name</th>
								<th>Mobile No</th>
								<th>Closing Manager</th>
								<th>Payment Type</th>
								<th>Bank Name</th>
								<th>Branch</th>
								<th>Trx ID</th>
								<th>Trx Date</th>
								<th>Trx Amount</th>
								<th>Description</th>
								<th>PAN Attachment</th>
								<th>Cheque/Receipt Attachment</th>
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
					<div class="commonLoad" id="mainPageLoad2" style="display: none;"></div>
					<div class="col-md-12">
						<div class="clearfix"></div>
						<table class="table table-bordered" id="Payment_Approved">
							<thead>
								<tr>
									
									<th>Created Date</th>
									<th>Enquiry No</th>
									<th>Customer Name</th>
									<th>Mobile No</th>
									<th>Closing Manager</th>
									<th>Payment Type</th>
									<th>Bank Name</th>
									<th>Branch</th>
									<th>Trx ID</th>
									<th>Trx Date</th>
									<th>Trx Amount</th>
									<th>Cheque/Receipt Attachment</th>
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
				<div class="commonLoad" id="mainPageLoad3" style="display: none;"></div>
					<div class="col-md-12">
						<div class="clearfix"></div>
						<table class="table table-bordered" id="Payment_Reject">
							<thead>
								<tr>
									<th>Created Date</th>
									<th>Enquiry No</th>
									<th>Customer Name</th>
									<th>Mobile No</th>
									<th>Closing Manager</th>
									<th>Payment Type</th>
									<th>Bank Name</th>
									<th>Branch</th>
									<th>Trx ID</th>
									<th>Trx Date</th>
									<th>Trx Amount</th>
									<th>Cheque/Receipt Attachment</th>
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
				<div class="tab-pane" id="tab4"> 
				<div class="commonLoad" id="mainPageLoad4" style="display: none;"></div>
					<div class="col-md-12" style="overflow: auto;">
						<div class="clearfix"></div>
						
						<select style="display:none" class="selection" id="selectbasic" name="selectbasic" class="form-control">
						    <option value="1">option 1</option>
						    <option value="2">option 2</option>
						    <option value="3">option 3</option>
						  </select>
						<table class="table table-bordered  bgWhite mrgB8" id="EOI_Payment_Refund_List">
							<thead>
								<tr class="subHead">
									<th>Enquiry No.</th>
									<th>EOI Customer Name</th>
									<th>Closing Manager</th>
									<th>Initiate Cancellation/Refund Date</th>
									<th>A/C Holder Name</th>
									<th>Bank Name</th>
									<th>Branch Name </th>
									<th>Account No. </th>
									<th>IFSC Code </th>
									<th>Transaction No. </th>
									<th>Account Type</th>
									<th>Reason for Cancellation </th>
									<th>Description </th>
									<th>Cancelled Cheque Copy</th>
									<th>Refund Total Amt </th>
									<th>NEFT / RTGS UTR No </th>
									<th>Comments </th>
									<th>Status </th>
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
     	 	<!-- Model -->
	<div class="modal fade" id="paymentDetails_EOI_Refund" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document" style="width: 100%;">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="ModalLabelAdmin">EOI Initiated Refund Details</h4>
	      </div>
	      <div class="modal-body">
	      <div class="col-md-12" style="margin-bottom:50px;">
				<table class="table table-bordered" id="paymentDetails_EOI_Refund_table">
						<thead>
							<tr>
								<th style="width:130px;"> Payment Type </th>
								<th style="width:120px;"> Bank Name </th>
								<th> Transaction ID </th>
								<th> Transaction</br>Date </th>
								<th> Transaction Amount </th>
								<th> EOI</br>Date </th>
								<th style="width:200px;"> Receipt/</br>Cheque Attachment </th>
								<th style="width:150px;"> Description </th>
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
	<script src="<c:url value='/resources/js/dataTables.buttons.min.js'/>"></script>
	<script src="<c:url value='/resources/js/jszip.min.js'/>"></script>
	<script src="<c:url value='/resources/js/buttons.html5.min.js'/>"></script>
	
	<%@ include file="/WEB-INF/views/pages/footer.jsp" %>
</body>
</html>