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
<title>Admin Inventory Page</title>
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css' />" type="text/css" />
     <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
     <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/fixedHeader.dataTables.min.css' />">
     <link rel="stylesheet" href="<c:url value='/resources/css/sweetalert2.min.css' />">  
	<link rel="stylesheet" href="<c:url value='/resources/css/inner.css?v=${sessionScope.version}' />">
	<link rel="stylesheet" href="<c:url value='/resources/css/style.css?v=${sessionScope.version}' />">
	
	
<script src="<c:url value='/resources/js/jquery-1.12.4.min.js'/>"></script>
<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>


 <link rel="stylesheet" href="<c:url value='/resources/css/inventory.css?v=${sessionScope.version}' />">
 
 <!-- Costsheet -->
    <link rel="stylesheet" href="<c:url value='/resources/css/costsheet.css?v=${sessionScope.version}' />">
	<link rel="stylesheet" href="<c:url value='/resources/css/font-awesome.css' />">
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
<!-- Added for Costsheet -->
<input type="hidden" id="projectId" value="${projectSfid}">
<!-- END Added for Costsheet -->
<input type="hidden" id="userid" value="${userId}">
<input type="hidden" id="roleid" value="${projectrole}">



  <nav class="navbar topMainBar">
	  <div class="container">
	  <%@ include file="/WEB-INF/views/pages/header.jsp" %>
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
				<li class="active" id="basicInfoTabId" >
					<a href="#tab1" data-toggle="tab">Admin Inventory</a>
				</li>
				<li id="costsheetTab">
					<a href="#costsheetTabCont" data-toggle="tab">Costsheet</a>
				</li>
				<li>
					<a href="#inventoryReport" data-toggle="tab">Hold/ Block Inventory Report</a>
				</li>
				
				<li>
					<a href="#allInventoryReport" data-toggle="tab">Inventory Report</a>
				</li>
				
				<li>
					<a href="#inventorySalesHoldReport" data-toggle="tab">Closing Manager Hold Inventory Report</a>
				</li>
				<li>
					<a href="#costsheetLogReport" onclick="usertowermultiselect('');" data-toggle="tab">Cost Sheet Log</a>
				</li>
				<li>
					<a href="#updateEOITab" data-toggle="tab">EOI</a>
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
		 	<div class="tab-pane" id="costsheetTabCont">
				<jsp:include page="costsheetSalesinfo2.jsp"></jsp:include>
			</div>
		 	<div class="tab-pane" id="tab2" >
		 		<%-- <jsp:include page="inventoryhold.jsp"></jsp:include> --%>
				<%@ include file="/WEB-INF/views/pages/inventoryhold.jsp" %>
			</div>
			
			<div class="tab-pane" id="inventoryReport" >
				<%@ include file="/WEB-INF/views/pages/inventoryReport.jsp" %>
			</div>
			
			<div class="tab-pane" id="allInventoryReport" >
				<%@ include file="/WEB-INF/views/pages/allInventoryReport.jsp" %>
			</div>
			
			<div class="tab-pane" id="inventorySalesHoldReport" >
				<%@ include file="/WEB-INF/views/pages/inventorySalesHoldReport.jsp" %>
			</div>
			
			<div class="tab-pane" id="costsheetLogReport" >
				<%@ include file="/WEB-INF/views/pages/costsheetLogReport.jsp" %>
			</div>
			
			<div class="tab-pane" id="updateEOITab" >
				<%@ include file="/WEB-INF/views/pages/updateEOI.jsp" %>
			</div>
	 	</div>
	</div>
	
	<script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>  
	
	<script src="<c:url value='/resources/js/commonValidation.js?v=18.19' />"></script>
	<script type="text/javascript" charset="utf8" src="<c:url value='/resources/js/jquery.dataTables.js'/>"></script>
	<script src="<c:url value='/resources/js/dataTables.buttons.min.js'/>"></script>
	<script src = "<c:url value='/resources/js/dataTables.fixedHeader.min.js'/> "></script>
		
	<script src="<c:url value='/resources/js/jszip.min.js'/>"></script>
	<script src="<c:url value='/resources/js/buttons.html5.min.js'/>"></script>
	
	<script src="<c:url value='/resources/js/sweetalert2.min.js' />"></script>
	<script src="<c:url value='/resources/js/separate/admininventory.js?v=${sessionScope.version}'/>"></script>
	<script src="<c:url value='/resources/js/separate/admininventoryReport.js?v=${sessionScope.version}'/>"></script>
	<script src="<c:url value='/resources/js/separate/costsheetLogReport.js?v=${sessionScope.version}'/>"></script>
	<script src="<c:url value='/resources/js/separate/salesHoldinventoryReport.js?v=${sessionScope.version}'/>"></script>
	<script src="<c:url value='/resources/js/separate/costsheet.js?v=${sessionScope.version}'/>"></script>
	<script src="<c:url value='/resources/js/separate/updateEOI.js?v=${sessionScope.version}'/>"></script>	
	<script src="<c:url value='/resources/js/separate/eoiFormPrint.js?v=${sessionScope.version}'/>"></script>

	<script src="<c:url value='/resources/js/enquiryRequest/common.js?v=${sessionScope.version}'/>"></script>
	
	

	<%@ include file="/WEB-INF/views/pages/footer.jsp" %>
</body>

</html>