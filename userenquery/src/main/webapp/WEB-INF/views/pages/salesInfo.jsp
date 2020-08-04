<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <html lang="en">
 
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Godrej</title>
    
    <link rel="shortcut icon" href="<c:url value='/resources/images/favicon.ico' />" type="image/x-icon">
	<link rel="icon" href="<c:url value='/resources/images/favicon.ico' />" type="image/x-icon">
    
    <!-- Bootstrap -->
    <link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css' />">
	<link rel="stylesheet" href="<c:url value='/resources/css/inner.css?v=${sessionScope.version}' />">
	<link rel="stylesheet" href="<c:url value='/resources/css/style.css?v=${sessionScope.version}' />">
	<link href="<c:url value='/resources/css/animate.min.css' />" rel="stylesheet">
	<link rel="stylesheet" href="<c:url value='/resources/css/sweetalert2.min.css' />">  
 
	
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/anypicker-font.css' />" />
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/anypicker.css' />" />

	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/anypicker-ios.css' />" />
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/anypicker-android.css' />" />
	<link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel = "stylesheet">
	
	<link rel="stylesheet" type="text/css"  href="<c:url value='/resources/css/intlTelInput.css' />" />
   
   <link  rel="stylesheet" type="text/css" href="<c:url value='/resources/css/bootstrap-datetimepicker.min.css' />"/>
   
   <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/bootstrap-slider.min.css' />  " />
    
    <!-- Inventory -->
    <link rel="stylesheet" href="<c:url value='/resources/css/inventory.css?v=${sessionScope.version}' />">
    <!-- END Inventory -->
    
    <!-- Costsheet -->
    <link rel="stylesheet" href="<c:url value='/resources/css/costsheet.css?v=${sessionScope.version}' />">
	<%-- <link href="<c:url value='/resources/css/animate.min.css' />" rel="stylesheet"> --%>
	<link rel="stylesheet" href="<c:url value='/resources/css/sweetalert.min.css'/>">
	<%-- <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/jquery.countdownTimer.css'/> " /> --%>
    <!-- END Costsheet -->
    
    <link rel="stylesheet" href="<c:url value='/resources/css/font-awesome.css' />">
    
  </head>
  
  <%! String projectname,projectid ,projectrole,userid,email,userName,roleid;%>
<%HttpSession ses=request.getSession(); 

projectid =request.getParameter("projectSfid");
projectname =request.getParameter("projectName");
if(request.getParameterMap().containsKey("roleid"))
	roleid = request.getParameter("roleid");
if(ses!=null){
	  //projectname=(String)ses.getAttribute("PRONAME");
	 // projectid=(String)ses.getAttribute("PROID");
	  projectrole=(String)ses.getAttribute("ROLE");
	 userid=(String)ses.getAttribute("USERID");
	 email=(String)ses.getAttribute("USEREMAIL");
	 userName=(String)ses.getAttribute("USERNAME");
}else{
	 // Todo Login page
}
%> 
<body onload="initialize()" >
<!-- Inventory -->   
<div style="display:none;"> 
	<input type="text" id="projectname" value="<%= projectname %>">
	<input type="text" id="projectId" value="<%= projectid%>">
	<input type="text" id="useremailID" value="<%= email%>">
	<input id="pageContext" value="${pageContext.request.contextPath}"/>
	<input id="token" value="${token}"/>
	<input id="tokentype" value="${tokentype}"/>
	<input id="projectsfid" value="${projectsfid}"/>
	<input id="primarycontactsfid" value="${primarycontactsfid} "/>
	<input id="enquiry_name" value="${name} "/>
	<input id="enquirysfid" value="${enquirysfid}"/>
	<%-- <input id="enquirysfid123" value="${sfid}"/> --%>
	<input id="customerId" value="123">
	<input id="channelPartnerSfidCS">
	<input  id="userid" value="<%= userid %>">
	<input  id="roleid" value="<%= roleid %>">
	
</div>
<!-- END Inventory -->   
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
      	<a class="navbar-brand" href="#" style="margin-left: 0px;">
			<img class="topLogo" src="<c:url value='/resources/images/gplLogo.jpg' />"/>
		</a>
		<!--  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1"> -->
	    <ul class="nav navbar-nav navbar-right pull-right">
				<!-- <li><a href="#"><i class="glyphicon glyphicon-log-out"></i> LOGIN</a></li> -->
			 <li><a href="${pageContext.request.contextPath}/saleslogin">Logout</a></li>
				<li class="dropdown">
				  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${userName}<span class="caret"></span></a> 
				  <ul class="dropdown-menu">
				    <li><a href="${pageContext.request.contextPath}/saleslogin">Logout</a></li>
				  </ul>
				</li>
			</ul> 
	    <!-- </div> -->
		</div> --%>
		<!-- <div class="proTopTitle" id="projectTitle">
		</div> -->
		<!-- Collect the nav links, forms, and other content for toggling -->
		<%@ include file="/WEB-INF/views/pages/header.jsp" %>
	  </div>
	</nav>
   
	<div class="container">
		<div class="enquiryDtlCol">
		<input type="hidden" id="isAllowPaymentStatus" value=""/>
			<div class="col-md-3">
				<div>
					<strong>Project</strong>
				</div>
				<div id="projectTitle"> </div>
				<div class="clearfix"></div>
			</div>
			
			<div class="col-md-3">
				<div>
					<strong>Enquiry</strong>
				</div>
				<div id="hdrEnqName"></div>
				<div class="clearfix"></div>
			</div>
			
			<div class="col-md-3">
				<div>
					<strong>Customer Name</strong>
				</div>
				<div id="hdrCustomerName"></div>
				<div class="clearfix"></div>
			</div>
			
			<div class="col-md-3">
				<div>
					<strong>Customer Mobile No.</strong>
				</div>
				<div id="hdrCustomerMobileNo"></div>
				<div class="clearfix"></div>
			</div>
			
			<div class="clearfix"></div>
		</div>
		<div class="clearfix"></div>
	</div>
   	<div class="container formCol swipeCard posRelative">
			
		<div class="commonLoad" id="mainPageLoad" style="display:none;"></div>	
			
		<!-- <div class="titleCol">
			<h4 class="">
				<span id="projectTitle"></span>					
			</h4>
			<div class="clearfix"></div>
		</div>	 -->
		<div class="clearfix"></div>	
				<input type="hidden" id="contextPath" value="${pageContext.request.contextPath}">
				<input type="hidden" id="hasParam" value="${hasParam}">
				<input type="hidden" id="token" value="${token}">
				<input type="hidden" id="tokenId" value="${tokenId}">
				<input type="hidden" id="viewType" value="${viewType}">
				<input type="hidden" id="salesViewType" value="${salesViewType}">
				 
				<input type="hidden" id="loginRole" value="${loginRole}">
     	        <input type="hidden" id="isView" value="${isView}">
				<input type="hidden" id="userid" value="<%= userid %>">
				<input type="hidden" id="username" value="<%= userName %>">
				
				
				<input type="hidden" id="projectid" value="<%= projectid %>">
				<input type="hidden" id="projectname" value="<%= projectname %>">
 
 
		<div>
			<%-- <div class="logoCol">
				<img class="topLogo" src="<c:url value='/resources/images/gplLogo.jpg' />"/>
				<div class="clearfix"></div>
			</div>
			
			<div class="titleCol">
				<h4 class="txtCenter">
					<span style="margin-left:3px;">GODREJ AQUA ENQUIRY FORM</span>					
				</h4>
				<div class="clearfix"></div>
			</div> --%>
			
			
			
			<div class="clearfix"></div>
			<ul class="nav nav-tabs tabNav">
				<li class="active" id="basicInfoTabId">
					<a href="#tab1" data-toggle="tab">Basic info</a>
				</li>
				 <li id="salesTabId"><!--   onclick="getSalesDetails(event);"-->
					<a href="#tab2" data-toggle="tab">Sales</a>
				</li>  
				 <li id="paymentDetailsTab" style="display: none;" onclick="getEOITabPaymentRecord (); getEOITabPreferencRecord();">
					<a href="#tab3" data-toggle="tab">EOI Details</a>
				</li>
				
				<li id="inventoryTab" onclick="holdExistData()">
					<a href="#inventoryTabCont" data-toggle="tab">Inventory</a>
				</li>
				
				
				<li id="costsheetTab" onclick="holdExistData();">
				<!-- <li id="costsheetTab" onclick="holdExistData(); schemeDropdown (); getSchemeSource();"> -->
					<a href="#costsheetTabCont" data-toggle="tab">Costsheet</a>
				</li>
				
				<li onclick="getPaymentReqRecord()" id="paymentReqRecordLi" style="display:none;">
					<a href="#onlinePaymentTab" data-toggle="tab">Generate payment links</a>
				</li>
				
				
				
				
				<!-- <li>
					<a href="#tab4" data-toggle="tab">Other info</a>
				</li>  -->
			</ul>
			<div class=""></div>
		</div>
		
		<div class="tab-content formTabCont">
			
			
			<div class="tab-pane active" id="tab1">
				<form:form modelAttribute="EnquiryRequest" id="enquiryRequestBasicInfoForm">
				<input type="hidden" value="${projectName}" id="projectName">
				<input type="hidden" value="${token}" id="token">
				<input type="hidden" class="enquirysfid" disabled="disabled" name="sfid">
				<input type="hidden" class="enquiryId" disabled="disabled" name="enquiryId">
				<input type="hidden" class="contactId" disabled="disabled" name="contact.contactId">
				<!-- <input type="hidden" class="contactId" disabled="disabled" name="contactId.sfid"> -->
				<input type="hidden" name="projectType" id="projectType" value="Residential">
				<input type="hidden" name="enquirySource" id="enquirySource" value="Walk-in">
				<input type="hidden" name="enquiryRating" value="Hot" id="enquiryRating">
				<input type="hidden" value="${projectSfid}"name="project.sfid" id="projectSfid" class="projectSfid"><!--value="a1l6F000004QtUZQA0" a20O0000002agRG-->
				<input type="hidden" value="${countryCode}" name="contact.countryCode" id="countryCode"><!--  +91-->
				<input type="hidden" value="1" name="project.projectId" id="projectId">
				<input type="hidden" value="Site Visit Done" name="enquiryStatus" id="enquiryStatus">
				<!-- <input type="hidden" value="a2X6F000000JE2FUAW" name="advertisement" id="advertisement"> --><!--a0xO000000F8TFR a0xO000000F8TFRIA3 --><!-- a2eO0000000Mix6IAC -->
				<input type="hidden" class="enquiryReportId" name="enquiryReport.enquiryReportId">
				<input type="hidden" class="contactReportId" name="contact.contactReport.contactReportId">
				<%-- <input type="hidden" id="closingmanagers" name="closingmanagers" value="${closingmanagers}"> --%>
				
				<div class="row "> <!-- bounceInLeft animated -->
					<div class="col-md-12 commonErrorCol commonErrorDiv" style="display: none">
						<div class="alert alert-danger">
						  	Kindly fill the <strong>required</strong> fields.
						</div>
					</div>
					<div class="col-md-12 commonErrorCol commonMessageDiv" style="display: none">
						<div class="alert alert-danger">
						  	<strong></strong>
						</div>
					</div>
					<div class="col-md-3 col-sm-6 col-xs-12">
						<div class="group">				
						  <div class="input-group" id="contactDiv" >
						      <input type="hidden" id="hiddenMobileNo" value="${mobileNo}">
							  <input type="text" value="${countryCode}" class="autocomplete-off form-control mobile requiredField contactNoDiv" id="mobileNo" name="contact.mobileNo" maxlength="10" onkeyup="getExistingInfo();"><!--onchange="getExistingInfo();"-->
							  <span onclick="getExistingInfo();" class="input-group-addon contactNoDiv contactSalesView" data-toggle="modal" ><!--   data-target="#multiEnq"-->
								<i class="glyphicon glyphicon-search contactNoDiv" ></i>
							  </span>
							  <span class="highlight"></span><span class="bar"></span>
								<label>Contact no.<strong class="mndt">*</strong></label>
							</div>
							
							
							<!-- <input type="text" id="mobileNo" onchange="getExistingInfo();" class="mobile requiredField"  name="contact.mobileNo" maxlength="10"/> -->
							<!-- <span class="highlight"></span><span class="bar"></span>
							<label>Contact no.<strong class="mndt">*</strong></label> -->
						</div>
					</div>
					<!-- field Removed -->
					<!--<div class="col-md-3 col-xs-6">
						<div class="select">
							<select id="region" class="select-text requiredField" required >
								<option value="1">Mumbai</option>
							</select>
							<span class="select-highlight"></span>
							<span class="select-bar"></span>
							<label class="select-label" style="top: -14px;">Region <strong class="mndt">*</strong></label>
						</div>
					</div>
					<div class="col-md-3 col-xs-6">
						<div class="select">
							<select id="project" class="select-text requiredField" required name="project.projectId">
								<option value="a1l6F000004QtUZQA0" select>The Trees</option>
							</select>
							<span class="select-highlight"></span>
							<span class="select-bar"></span>
							<label class="select-label" style="top: -14px;">Project Name <strong class="mndt">*</strong></label>
						</div>
					</div>
					
					<div class="clearfix"></div> -->
					<!-- field Removed -->
					<!-- No need Title -->
					<!--<div class="col-md-1 col-xs-6">
						<div class="group">
							<select id="salutation" class="requiredField"  name="contact.salutation">
								<option value="" ></option>
								<option value="1">Mr.</option>
								<option value="2">Mrs.</option>
								<option value="3">Miss.</option>
							</select>
							<span class="highlight"></span><span class="bar"></span>
							<label class="select-label">Title <strong class="mndt">*</strong></label>
						</div>
					</div>-->
					
					<div class="col-md-3 col-sm-6 col-xs-6">
						<div class="group">
							<input type="text" id="firstName" class="autocomplete-off requiredField contactSalesView" required="required" name="contact.firstName"/>
							<span class="highlight"></span><span class="bar"></span>
							<label>First name <strong class="mndt">*</strong></label>
							<!-- <small class="errorMsg">Error message</small> -->
						</div>
					</div>
					
					<div class="col-md-3 col-sm-6 col-xs-6">
						<div class="group">
							<input type="text" id="lastName" class="autocomplete-off requiredField contactSalesView" required="required" name="contact.lastName"/>
							<span class="highlight"></span><span class="bar"></span>
							<label>Last name <strong class="mndt">*</strong></label>
						</div>
					</div>
					
					<!-- field Removed -->
					<div class="col-md-3 col-sm-6 col-xs-12">
						<div class="group">
							<input type="text" id="email" class="autocomplete-off requiredField emailaddress contactSalesView" name="contact.otherEmail"/>
							<span class="highlight"></span><span class="bar"></span>
							<label>Email <strong class="mndt">*</strong></label>
						</div>
					</div>
						
					<div class="clearfix"></div>
					
					<div class="col-md-2 radioBtnWrp mrgT0" style="margin-top:-16px !important;">
						  <div class="titleF">Have you visited site before<strong class="mndt">*</strong></div>
							<div class="radioBtnCol" data-toggle="buttons">
							  <label class="btn btn-primary active contactSalesView">
								<input type="radio" id="haveWeMetBeforeY" class="haveWeMetBefore" name="enquiryReport.haveWeMetBefore" checked value="Yes">Yes
							  </label>
							  <label class="btn btn-primary contactSalesView">
								<input type="radio" id="haveWeMetBeforeN" class="haveWeMetBefore" name="enquiryReport.haveWeMetBefore" value="No">No
							  </label>
						</div>
					</div>
					
				
					
					<!-- New filed -->
					<div class="col-md-2 col-xs-6">
						<div class="group">
							<select id="ageGroup" name="contact.contactReport.ageGroup" class="contactSalesView">
							        <option value=""></option>
							        <option value="Less than 20">Less than 20</option>
							        <option value="Between 21-25">Between 21-25</option>
							        <option value="Between 26 -35">Between 26 -35</option>
							        <option value="Between 36 - 45">Between 36 - 45</option>
							        <option value="Between 46 -60">Between 46 -60</option>
							        <option value="Greater than 60">Greater than 60</option>
							     <%--  <option value="< 30">&lt; 30</option>                                
                                    <option value="31-35">31-35</option>                                 
                                    <option value="36-40">36-40</option>                                 
                                    <option value="41-45">41-45</option>                                 
                                    <option value="46-50">46-50</option>                                 
                                    <option value="> 51">&gt; 51</option> --%>
								<%-- <c:forEach var="ageGroup" items="${ageGroupList}">
                                    <option value="${ageGroup.code}">${ageGroup.name}</option>
                                 </c:forEach> --%>
							</select>
							<span class="highlight"></span><span class="bar"></span>
							<label class="select-label">Age Group <strong class="mndt"></strong></label>
						</div>
					</div>
					
					<div class="clearfix"></div>
					
					<div id="address"  style="padding-top: 45px; margin-bottom: 40px; border-top: 1px solid #ccc; border-bottom: 1px solid #ccc;">
						
						
						
						<div class="col-md-4 col-sm-12">
							<div class="group">
							  <input class="autocomplete-off autocomplete contactSalesView" type="text" required="required" id="addressLine2" name="contact.addressLine2" />
							  <span class="highlight"></span>
							  <span class="bar"></span>
							  <label>Residence Location  <strong class="mndt">*</strong></label>
							</div>
						</div>
						
						<!-- instrected by Ganesh - commented by A -->
						<!-- <div class="col-md-2 col-sm-12">
							<div class="group">
							  <input type="text" id="addressLine1" name="contact.addressLine1" class="autocomplete-off contactSalesView"/>
							  <span class="highlight"></span>
							  <span class="bar"></span>
							  <label>Flat/ House No.</label>
							</div>
						</div> -->
						
						<!-- No need -->
						<!-- <div class="col-md-4 col-sm-12">
							<div class="group">
							  <input type="text" required="required" id="addressLine2" name="contact.addressLine2"/>
							  <span class="highlight"></span>
							  <span class="bar"></span>
							  <label>Address Line 2</label>
							</div>
						</div>
						
						<div class="col-md-4 col-sm-12">
							<div class="group">
							  <input type="text" required="required" id="addressLine3" name="contact.addressLine3"/>
							  <span class="highlight"></span>
							  <span class="bar"></span>
							  <label>Address Line 3</label>
							</div>
						</div> -->
						<!-- END No need -->
						
						
						<!-- Add new field -->
						<div class="col-md-2 col-sm-6 col-xs-6">
							<div class="group">
								<input class="autocomplete-off administrative_area_level_1 contactSalesView" type="text" id="residentialState" name="contact.residentialState"/>
								<span class="highlight"></span><span class="bar"></span>
								<label>State <strong class="mndt">*</strong></label>
							</div>
						</div>
						<div class="col-md-2 col-sm-6 col-xs-6">
							<div class="group">
								<input class="autocomplete-off country contactSalesView" type="text" id="residentialCountry" name="contact.residentialCountry"/>
								<span class="highlight"></span><span class="bar"></span>
								<label>Country<strong class="mndt"> *</strong></label>
							</div>
						</div>
						<!-- END Add new field -->
						
						
						<!-- No need -->
						<!-- <div class="col-md-3 col-sm-6 col-xs-6">
							<div class="group">
								<input type="text" required="required" name="contact.locality" id="locality"/>
								<span class="highlight"></span><span class="bar"></span>
								<label>Locality</label>
							</div>
						</div> -->
						<!-- END No need -->
						
						<div class="col-md-2 col-sm-6 col-xs-6">
							<div class="group">
								<input class="autocomplete-off locality contactSalesView" type="text" required="required" name="contact.city" id="city"/>
								<span class="highlight"></span><span class="bar"></span>
								<label>City  <strong class="mndt">*</strong></label>
							</div>
						</div>
						
						<div class="col-md-2 col-sm-6 col-xs-6">
							<div class="group">
								<input class="autocomplete-off postal_code contactSalesView numericField"  type="text" required="required" name="contact.pinCode" id="pinCode" maxlength="6"/>
								<span class="highlight"></span><span class="bar"></span>
								<label>Pin Code</label>
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
					
					
					
					
					
					
					<div style="border-bottom: 1px solid #ccc; margin-bottom: 45px;">
						<div class="col-md-2 col-xs-6">
							<div class="group">
								<select id="employmentStatus" name="contact.contactReport.employmentStatus" class="contactSalesView">
								        <option value=""></option>
								        <option value="Salaried">Salaried</option>
								        <option value="Business">Business</option>
								        <option value="Professional">Professional</option>
								        <option value="Retired">Retired</option>
								        <option value="Between 46 -60">Homemaker</option>							       		        					    
								</select>
								<span class="highlight"></span><span class="bar"></span>
								<label class="select-label">Employment Status<strong class="mndt"></strong></label>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="col-md-2 col-xs-6">
								<div class="group">
									<select id="designationCustSales" name="contact.designation" class="contactSalesView">
									        <option value=""></option>
									        <option value="Owner / Proprietor">Owner / Proprietor</option>
									        <option value="CEO">CEO</option>
									        <option value="AVP & Above">AVP & Above</option>
									        <option value="Middle Management">Middle Management</option>
									        <option value="Executive">Executive</option>
									        <option value="Manager">Manager</option>
									        <option value="Assistant Manager">Assistant Manager</option>
									        <option value="Software Engineer">Software Engineer</option>
									        <option value="Analyst">Analyst</option>
									        <option value="Associate">Associate</option>
									        <option value="Consultant">Consultant</option>
									        <option value="General Manager">General Manager</option>
									        <option value="Vice President">Vice President</option>
									        <option value="Ass. Vice President">Ass. Vice President</option>
									        <option value="Chairman">Chairman</option>
									        <option value="Vice Chairman">Vice Chairman</option>
									        <option value="CMO">CMO</option>
									       	<option value="COO">COO</option>
									       	<option value="CTO">CTO</option>
									       	<option value="CXO">CXO</option>
									        <option value="Director & Above">Director & Above</option>
									       	<option value="Executive Director">Executive Director</option>
									       	<option value="Managing Director">Managing Director</option>
									       	<option value="Officer">Officer</option>
									        <option value="Product Head">Product Head</option>
									       	<option value="Department Head">Department Head</option>
									       	<option value="Professor">Professor</option>
									       	<option value="Team Lead">Team Lead</option>
									        <option value="Doctor">Doctor</option>							       		        					    
									</select>
									<span class="highlight"></span><span class="bar"></span>
									<label class="select-label">Designation<strong class="mndt"></strong></label>
								</div>
								<div class="clearfix"></div>
							</div>
						<div class="col-md-3 col-xs-6">
							<div class="group">
								<input type="text" id="companyName" name="contact.companyName" class="autocomplete-off contactSalesView"/>
								<span class="highlight"></span><span class="bar"></span>
								<label>Organisation Name<strong class="mndt"></strong></label>
							</div>
							<div class="clearfix"></div>
				    	</div>
					
						<!-- <div > --> <!-- id="address2" -->
					 	<div class="col-md-3 col-xs-6">
							<div class="group">
							  <input class="autocomplete-off autocomplete contactSalesView" type="text"  id="officeAddress" name="contact.contactReport.officeAddress"/>
							  <span class="highlight"></span>
							  <span class="bar"></span>
							  <label>Office Location</label>
							</div>
							<div class="clearfix"></div>
						</div> 
						
						
						 <div class="col-md-2 col-xs-6">
							<div class="group">
								<input class="autocomplete-off locality contactSalesView" type="text" id="officeCity" name="contact.contactReport.officeCity"/>
								<span class="highlight"></span><span class="bar"></span>
								<label>City</label>
							</div>
							<div class="clearfix"></div>
						</div> 
						
						
						<div style="display:none;"> <!-- style="display:none;" -->
							<input class="contactFields" type="text"  id="officelat" name="contact.contactReport.officelat"/>
							<input class="contactFields" type="text"  id="officelng" name="contact.contactReport.officelng"/>
							<input class="contactFields" type="text"  id="reslat" name="contact.contactReport.reslat"/>
							<input class="contactFields" type="text"  id="reslng" name="contact.contactReport.reslng"/>
						</div>
						
						 <div class="col-md-2 col-xs-6">
							<div class="group">
								<input class="autocomplete-off postal_code contactSalesView numericField" type="text" maxlength="6" id="officePinCode" name="contact.contactReport.officePincode"/>
								<span class="highlight"></span><span class="bar"></span>
								<label>Pin Code</label>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="clearfix"></div>
					</div>
					
					
					
					<div class="formDivider">
						<div class="col-md-3 col-sm-6 col-xs-12">
							<div class="group">
								<select  id="purpose" name="enquiryReport.purpose" class="contactSalesView">
								            <option value=""></option>									
	                                        <option value="Personal use">Personal use</option>                                    
	                                        <option value="Investment">Investment</option> 
	                             </select>
								<span class="highlight"></span><span class="bar"></span>
								<label class="select-label">Purchase Purpose<strong class="mndt"></strong></label>
							</div>
						</div> 
						
						<div class="clearfix"></div>
						
						<!-- Added By A -->
						<!-- <div class="col-md-6 col-xs-12" style="padding-left: 25px; padding-right: 25px; min-height: 150px;">
							<label style="margin-left: -10px; margin-bottom: 30px; font-weight:normal">Carpet Area  </label>
							<input style="width:100%" id="carpetSales"  data-slider-value="900" type="text" name="enquiryReport.carpetAreaRequirement"/>
							<div class="clearfix"></div>
						</div>	
						<div class="col-md-6 col-xs-12" style="padding-left: 25px; padding-right: 25px; min-height: 150px;">
							<label style="margin-left: -10px; margin-bottom: 30px; font-weight:normal">  Budget  </label>
							<input style="width:100%" id="budgetSales" type="text" name="enquiryReport.budget" data-slider-value="0" data-slider-step="1" />
							<div class="clearfix"></div>
						</div> -->
						<!-- END Added By A -->
						
						<!-- Jugad - Added by A -->
						
						<div class="col-md-6 col-xs-6">
							<div class="group">
								<input type="text" id="budget" name="enquiryReport.budget" class="requiredField contactSalesView">
								<span class="highlight"></span><span class="bar"></span>
								<label>Budget</label>
							</div>
							<div class="clearfix"></div>
						</div>
						
						
						<div class="col-md-6 col-xs-6" style="display:none;">
							<div class="group">
								<input type="text" id="carpetAreaRequirement" name="enquiryReport.carpetAreaRequirement" class="requiredField contactSalesView">
								<span class="highlight"></span><span class="bar"></span>
								<label>Carpet Area</label>
							</div>
							<div class="clearfix"></div>
						</div>
						
						
						
						<!-- END Jugad - Added by A -->
						
						<!--  <div class="col-md-3 col-sm-6 col-xs-12">
							<div class="group">
								<select  id="budget" name="enquiryReport.budget" class="requiredField contactSalesView">
								<option value=""></option>	
								<option value="Below INR 35 lacs">Below INR 35 lacs</option>	
								<option value="Between INR 35-50 lacs">Between INR 35-50 lacs</option>	
								<option value="Between INR 50-75 lacs">Between INR 50-75 lacs</option>	
								<option value="Between INR 75lacs-1 Cr">Between INR 75lacs-1 Cr</option>	
								<option value="Between INR 1 Cr-1.5 Cr">Between INR 1 Cr-1.5 Cr</option>	
								<option value="Between INR 1.5 Cr- 2 Cr">Between INR 1.5 Cr- 2 Cr</option>	
								<option value="Between INR 2 Cr-2.5 Cr">Between INR 2 Cr-2.5 Cr</option>	
								<option value="Between INR 2.5 Cr-3 Cr">Between INR 2.5 Cr-3 Cr</option>		
	                            <option value="More than INR 3 Cr">More than INR 3 Cr</option>
								  
								</select>
								<span class="highlight"></span><span class="bar"></span>
								<label class="select-label">Customers Budget Requirement <strong class="mndt">*</strong></label>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="col-md-3 col-xs-12">
							<div class="group">
								<select id="carpetAreaRequirement" name="enquiryReport.carpetAreaRequirement" class="requiredField contactSalesView">
								        <option value=""></option>
								        <option value="Below 500 sq.ft.">Below 500 sq.ft.</option>
								        <option value="Between 501-600 sq. ft.">Between 501-600 sq. ft.</option>
								        <option value="Between 601-700 sq. ft.">Between 601-700 sq. ft.</option>
								        <option value="Between 701-800 sq. ft">Between 701-800 sq. ft</option>
								        <option value="More than 801">More than 801</option>
								</select>
								<span class="highlight"></span><span class="bar"></span>
								<label class="select-label">Carpet Area requirement<strong class="mndt">*</strong></label>
							</div>
						</div>
						 -->
						
						<div class="col-md-12 col-sm-12 col-xs-12 radioBtnWrp  padMLR15" radioName="desiredUnitType" id="desiredUnitType">
							<div class="titleF">Requirement <strong class="mndt">*</strong></div>
							<div class="radioBtnCol" data-toggle="buttons">
							     <label class='btn btn-primary active contactSalesView'>
							          <input type='radio' id='desiredUnitType0' class="desiredUnitType " name='enquiryReport.desiredUnitType' value='1 BHK' checked>1 BHK</label>
							     <label class='btn btn-primary contactSalesView'>
							          <input type='radio' id='desiredUnitType1' class="desiredUnitType " name='enquiryReport.desiredUnitType' value='2 BHK'>2 BHK</label>
							     <label class='btn btn-primary contactSalesView'>
							          <input type='radio' id='desiredUnitType2' class="desiredUnitType " name='enquiryReport.desiredUnitType' value='3 BHK'>3 BHK</label>
							       <label class='btn btn-primary contactSalesView'>
							          <input type='radio' id='desiredUnitType2' class="desiredUnitType " name='enquiryReport.desiredUnitType' value='4 BHK'>4 BHK</label>
							        
							     <label class='btn btn-primary contactSalesView'>
							          <input type='radio' id='desiredUnitType3' class="desiredUnitType " name='enquiryReport.desiredUnitType' value='Others'>Others</label>
							          
							     <%-- <c:forEach var="requirement" items="${requirementList}" varStatus="loop">
							       <c:choose>
							        <c:when test="${loop.index==0}">
							          <label class='btn btn-primary active'>
							          <input type='radio' id='desiredUnitType${loop.index}' name='desiredUnitType' value='${requirement.code}' checked>${requirement.name}</label>
                                     </c:when>
                                    <c:otherwise>
                                    <label class='btn btn-primary'>
							          <input type='radio' id='desiredUnitType${loop.index}' name='desiredUnitType' value='${requirement.code}' checked>${requirement.name}</label>
                                     </c:otherwise>
                                    </c:choose>
                                 </c:forEach> --%>							 
							</div>
							<!-- <label class="select-label fnt14 padMLR15">Typology Requirement<strong class="mndt">*</strong></label> -->
							<div class="claerfix"></div>
						</div>
						<div class="clearfix"></div>
					</div>
					
					<div>
						<%-- <div class="col-md-3 col-xs-6 mrgR25">
						
							<div class="group">
								<select id="industry" name="contact.industry" class="requiredField">
								 <option value=""></option>
								<c:forEach var="indusrty" items="${indusrtyList}">
                                    <option value="${indusrty.code}">${indusrty.name}</option>
                                 </c:forEach>
								</select>
								<input type="text">
								<span class="highlight"></span><span class="bar"></span>
								<label class="select-label">Industry<strong class="mndt">*</strong></label>
							</div>
						</div> --%>
						
						
						
						<!-- END New filed -->
					
						<div class="clearfix"></div>
					</div>
					
					
					
					<!-- No need in front ( Region and Project Name ) -->
					<!-- <div class="col-md-3 col-xs-6">
						<div class="group">
							<select id="region" class="requiredField"  >
								<option value="1">Mumbai</option>
							</select>
							<span class="highlight"></span><span class="bar"></span>
							<label class="select-label">Region <strong class="mndt">*</strong></label>
						</div>
					</div>
					<div class="col-md-3 col-xs-6">
						<div class="group">
							<select id="project" class="requiredField"  name="project.projectId">
								<option value="1">The Trees</option>
							</select>
							<span class="highlight"></span><span class="bar"></span>
							<label class="select-label">Project Name <strong class="mndt">*</strong></label>
						</div>
					</div>-->
					<!-- END No need in front ( Region and Project Name ) -->
					
					
					<!-- <div class="col-md-2 col-sm-6 col-xs-6">
						<div class="group">
							<input type="text" id="dateOfBirth" required="required" class="ip-de requiredField" name="contact.dateOfBirth" readonly/>
							<span class="highlight"></span><span class="bar"></span>
							<label>DOB <strong class="mndt">*</strong></label>
						</div>
					</div> -->
					
					
					
					
					
					<div class="clearfix"></div>
					
					<div class="col-md-12 radioBtnWrp mrgT0">
						<div class="titleF" id="enquirySourceTextDiv">Are you accompanied/ referred by a channel partner? <strong class="mndt">*</strong></div>
						<input type="hidden" name="isReferredByChannelPartner" id="isReferredByChannelPartnerInput" >
						<input type="hidden" id="hiddenEnquiryType" value="${enquiryType}">
						<div class="radioBtnCol" data-toggle="buttons">
						  <label class="btn btn-primary active hideChannelPartnerType contactSalesView" labelName="isReferredByChannelPartner" value="CP" >
							<input type="radio" id="isReferredByChannelPartnerRadioCP" propValue="Partner" class="hideChannelPartnerType" name="isReferredByChannelPartnerFlag" checked value="CP"> Yes
						  </label>
						  <label class="btn btn-primary hideDirectType contactSalesView" labelName="isReferredByChannelPartner" value="D" >
							<input type="radio" id="isReferredByChannelPartnerRadioD" propValue="Direct" class="hideDirectType" name="isReferredByChannelPartnerFlag" value="D"> No
						  </label>
						  <label class="btn btn-primary hideChannelPartnerType contactSalesView" labelName="isReferredByChannelPartner"  value="O" >
							<input type="radio" id="isReferredByChannelPartnerRadioO" propValue="Partner" class="hideChannelPartnerType" name="isReferredByChannelPartnerFlag" value="O"> Other
						  </label>
						</div>
					</div>
					<div class="clearfix"></div>
					<div id="isReferredByChannelPartnerCP" class="sourceCol animated">
						<div class="col-md-6 col-xs-12">
							<div class="group">
								<input type="text"  onkeyup="getChannelPartners(event,this);" class= "contactSalesView autocomplete-off isReferredByChannelPartnerCP requiredField" id="channelPartnerName" required="required"  name="channelPartner.name"/><!--  -->
								<input type="hidden" id="channelPartnerSfid" name="channelPartner.sfid" class="isReferredByChannelPartnerCP"/>
								<input type="hidden" id="channelPartnerId" name="channelPartner.channelPartnerId" class="isReferredByChannelPartnerCP"/>
								<span class="highlight"></span><span class="bar"></span>
								<label>Enter channel partner name <strong class="mndt">*</strong></label>
							</div>
						</div>

						<div class="col-md-6 col-xs-12" style="display: none ">
							<div class="group">
								<input type="hidden" id="brokerContactId" name="brokerContact.contactId" >
								<select class="isReferredByChannelPartnerCP requiredField contactSalesView" id="brokerContact"  name="brokerContact.sfid" onchange="brokerContactChanged(event,this);">
									<!-- <option value="" ></option>
									<option value="1">Option 1</option>
									<option value="2">Option 2</option>
									<option value="3">Option 3</option> -->
								</select>
								<span class="highlight"></span><span class="bar"></span>
								<label class="select-label">Select broker contact <strong class="mndt">*</strong></label>
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
					
					<div id="isReferredByChannelPartnerD" class="sourceCol animated" style="display: none;">
						<div class="col-md-6 col-xs-12">
							<div class="group">
								<select class="isReferredByChannelPartnerD requiredField contactSalesView" disabled="disabled" id="walkInSource" name="walkInSource">
								    <option value=""></option>
									<c:forEach var="communcationMediumList" items="${communcationMediumList}">

                                       <option value="${communcationMediumList.code}">${communcationMediumList.name}</option>

                                     </c:forEach>
								</select>
								<span class="highlight"></span><span class="bar"></span>
								<label class="select-label">How did you come to know about this project? <strong class="mndt">*</strong></label>
							</div>
						</div>
						<div class="col-md-6 col-xs-12">
							<div class="group">
								<input class="autocomplete-off isReferredByChannelPartnerD requiredField contactSalesView" type="text" disabled="disabled" required="required" id="walkInSourceDetail" name="walkInSourceDetail"/>
								<span class="highlight"></span><span class="bar"></span>
								<label>Details of Newspaper, Website, Hoarding etc. <strong class="mndt">*</strong></label>
							</div>
						</div>	
						<div class="clearfix"></div>
					</div>
					
					<div id="isReferredByChannelPartnerO" class="sourceCol animated" style="display: none;">
						<div class="col-md-6 col-xs-12">
							<div class="group">
								<input class="autocomplete-off isReferredByChannelPartnerO requiredField contactSalesView" type="text" required="required" disabled="disabled" name="cpComment" id="otherChannelPartnerName"/>
								<span class="highlight"></span><span class="bar"></span>
								<label>Enter channel partner name <strong class="mndt">*</strong></label>
							</div>
						</div>	
						<div class="clearfix"></div>
					</div>
					
						
						<div class="clearfix"></div>
						
					<!-- </div> -->
					 <div class="clearfix"></div>
				</div>
				
				<div class="txtCenter">
					<a class="btn btn-primary" onclick="saveBaseInfo(event,this);" id="basicInfoSaveBtn">
						<span>Submit</span>
					</a>
					<a class="btn btn-primary btnNext" id="basicInfoNxtBtn">
						<span>Next</span>
					</a>
					<div class="clearfix"></div>
				</div>
				<!-- <div class="btnWrp">
					<div class="btnCol">
						<a class="btn btn-primary btnPrevious">
							<span>Previous</span>
						</a>
						<a class="btn btn-primary btnNext" onclick="saveBaseInfo(event,this);">
							<span>Save & Continue</span>
						</a>
						<div class="clearfix"></div>
					</div>
					<div class="clearfix"></div>
				</div> -->
				</form:form>
			</div>
			
			
			<div class="tab-pane" id="tab2">
				<form:form modelAttribute="EnquiryRequest" id="enquiryRequestAddressInfoForm" >
				
				
			
				<input type="hidden" class="enquiryId" disabled="disabled" name="enquiryId">
				<!-- <input type="hidden" class="enquirysfid" disabled="disabled" name="sfid"> -->
				<input type="hidden" class="contactId" disabled="disabled" name="contact.contactId">
				<input type="hidden" class="enquiryReportId" name="enquiryReport.enquiryReportId">
				<input type="hidden" class="contactReportId" name="contact.contactReport.contactReportId">
				<input type="hidden" class="tokenno" name="contact.contactReport.tokenno" value="${tokenId}">
				<input type="hidden" class="tokenno" name="enquiryReport.tokenno" value="${tokenId}">
				<input type="hidden" class="userid" name="contact.contactReport.userid" value="<%= userid %>">
				<input type="hidden" class="userid" name="enquiryReport.userid" value="<%= userid %>">
				
				<%-- <input type="hidden" id="closingmanagers" name="closingmanagers" value="${closingmanagers}"> --%>
				<input type="hidden" id="assignTo" name="assignTo" value="${assignTo}">
				<div class="row "> <!-- bounceInRight animated -->
				<div class="col-md-12 commonErrorCol commonErrorDiv" style="display: none">
						<div class="alert alert-danger">
						  	Kindly fill the <strong>required</strong> fields.
						</div>
			    </div>
	<!-- Add new field -->
	                   
	                

		                   <!-- <div class="col-md-3 col-sm-6 col-xs-12" >
							<div class="group">
							
								<select class="sales_submitted" id="followtype" name="enquiryReport.followType">
									<option value=""></option>
									<option value="Call">Call</option>
									<option value="Home Visit">Home Visit</option>
									<option value="Revisit">Revisit</option>
		
								</select> <span class="highlight"></span><span class="bar"></span> <label
									class="select-label">Follow Type </label>
							</div>
							<div class="clearfix"></div>
						</div>
							
							
						<div class="col-md-3 col-sm-6 col-xs-12">
							<div class="form-group" id="fdate" style="margin-top: -15px; z-index: 0;">
								<label style="font-weight: normal;">Follow Date</label>
								<div class='input-group date datetimepickerfollow'>
									<input style="z-index: 0; border-radius: 0;" type='text' name="enquiryReport.followDate"
										class="form-control autocomplete-off sales_submitted "
										 /> <span style="border-radius: 0;" class="input-group-addon"> <span
										class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
							<div class="form-group" id="fdatevalue" style="display: none; margin-top: -15px; z-index: 0;">
								<label style="font-weight: normal;">Follow Date</label>
								<div class='input-group'>
									<input style="z-index: 0; border-radius: 0;" type='text' 
										class="form-control autocomplete-off sales_submitted "
										id="followdate" /> <span style="border-radius: 0;" class="input-group-addon"> <span
										class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div> 
						</div>-->
					 
	                   <!-- Now comming from backend - instract by ganesh 20190523 -->
	                   <div class="col-md-3 col-sm-6 col-xs-12" style="display:none;">
							<div class="group">
								<!-- requiredField --><input class="autocomplete-off" type="text" id="allocatedSalesManager" name="enquiryReport.allocatedSalesManager"/>
								<span class="highlight"></span><span class="bar"></span>
								<label>Allocated Sales Manager <!-- <strong class="mndt">*</strong> --></label>
							</div>
							<div class="clearfix"></div>
						</div>
						<!-- END Now comming from backend - instract by ganesh 20190523 -->
						
					<%-- <div class="col-md-2">
						<div class="group">
							<select class="requiredField" id="requiredPossesionTimeLine" name="requiredPossesionTimeLine">
								<option value=""></option>
								<option value="< 6 months">> 6 months</option>
                                <option value="6 months to 1 year">6 months to 1 year</option>
                                <option value="1 year to 3 years">1 year to 3 years</option>
                                <option value="> 3 years">> 3 years</option>								
									<c:forEach var="possessionTimeLineList" items="${possessionTimeLineList}">

                                       <option value="${possessionTimeLineList.code}">${possessionTimeLineList.name}</option>

                                     </c:forEach>
							</select>
							<span class="highlight"></span><span class="bar"></span>
							<label class="select-label">Possession Timeline<strong class="mndt">*</strong></label>
						</div>
						<div class="clearfix"></div>
					</div> --%>
					
					<%-- <div class="col-md-2">
						<div class="group">
							<select class="requiredField" id="purpose" name="purpose">
							            <option value=""></option>									
                                        <option value="Self Use">Self Use</option>                                    
                                        <option value="Investment">Investment</option>                                    
                                        <option value="Weekend / Second home">Weekend / Second home</option>                                    
                                        <option value="Others">Others</option>
								<option value=""></option>
									<c:forEach var="purposeList" items="${purposeList}">
                                        <option value="${purposeList.code}">${purposeList.name}</option>
                                    </c:forEach>
							</select>
							<span class="highlight"></span><span class="bar"></span>
							<label class="select-label">Purpose of purchase <strong class="mndt">*</strong></label>
						</div>
					</div> --%>
					<div class="col-md-3 col-sm-6 col-xs-12">
						<div class="group">
							<select class="requiredField sales_submitted" id="currentResidence" name="contact.contactReport.currentResidenceType">
							            <option value=""></option>									
                                        <option value="1 BHK">1 BHK</option>                                    
                                        <option value="2 BHK">2 BHK</option>                                    
                                        <!-- <option value="2.5 BHK">2.5 BHK</option>     -->                                
                                        <option value="3 BHK">3 BHK</option>                                    
                                        <option value="4 BHK">4 BHK</option>
                                        <option value="Others">Others</option>
								<%-- <option value=""></option>
									<c:forEach var="currentResidenceList" items="${currentResidenceList}">
                                        <option value="${currentResidenceList.code}">${currentResidenceList.name}</option>
                                    </c:forEach> --%>
							</select>
							<span class="highlight"></span><span class="bar"></span>
							<label class="select-label">Current Residence Configuration <strong class="mndt">*</strong></label>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="col-md-3 col-sm-6 col-xs-12">
						<div class="group">
							<select class="sales_submitted" id="currentResidenceOwnership" name="contact.contactReport.currentResidenceOwnership">
							            <option value=""></option>
							            <option value="Rented">Rented</option>
							            <option value="Self Owned">Self Owned</option>
							            <option value="Family Owned">Family Owned</option>					
                            </select>
							<span class="highlight"></span><span class="bar"></span>
							<label class="select-label">Ownership of Current Residence<strong class="mndt"></strong></label>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="col-md-3 col-sm-6 col-xs-12 ethnicity">
							<div class="group" style="min-height: 50px;">
								<select class="sales_submitted" id="ethnicity" name="contact.contactReport.ethnicity">
								    <option value=""></option>
									<option value="Marwari">Marwari</option>
								    <option value="Gujarati">Gujarati</option>
								    <option value="Maharashtrian">Maharashtrian</option>
								    <option value="Christian">Christian</option>
								    <option value="Parsi">Parsi</option>
								    <option value="Sindhi">Sindhi</option>
								    <option value="Bengali">Bengali</option>
								    <option value="Jain">Jain</option>
								    <option value="Muslim">Muslim</option>
								    <option value="North Indian">North Indian</option>
								    <option value="South Indian">South Indian</option>
								    <option value="Others">Others</option>
								</select>
								<span class="highlight"></span><span class="bar"></span>
								<label class="select-label">Ethnicity<strong class="mndt"></strong></label>
							</div>
						</div>
						<div class="clearfix"></div>
						<div class="col-md-3 col-sm-6 col-xs-12 radioBtnWrp mrgT0 " style="margin-top: -20px !important;">
						  <div class="titleF">Source of Funding <strong class="mndt">*</strong></div>
							<div class="radioBtnCol requiredRadio" data-toggle="buttons">
							  <label class="btn btn-primary sales_submitted" id="sourceOfFundingLabel">
								<input type="radio" id="sourceOfFundingSF" class="sourceOfFunding sales_submitted" name="enquiryReport.sourceOfFunding" value="Self Finance" disable="disable">Self Finance
							  </label>
							  <label class="btn btn-primary sales_submitted"  id="sourceOfFundingLoanLabel">
								<input type="radio" id="sourceOfFundingL" class="sourceOfFunding" name="enquiryReport.sourceOfFunding" value="Loan" disable="disable">Loan
							  </label>
							</div>
						</div>
						
						<div class="clearfix"></div>
						
						<!-- Instract By Ganesh -- Add new field for loan -->
						<div id="sourceFundingValue" style="display:none;">
						<div class="col-md-6 col-xs-12" style="padding-left: 25px; padding-right: 25px; min-height: 150px;">
						 <label style="margin-left: -10px; margin-bottom: 30px; font-weight:normal">Own contribution receipt</label>
						 <input readonly class="sales_submitted" type="text" id="contributionReceipt_val" name="enquiryReport.contributionReceipt_val">
						 </div>
						 <div class="col-md-6 col-xs-12" style="padding-left: 25px; padding-right: 25px; min-height: 150px;">
						 <label style="margin-left: -10px; margin-bottom: 30px; font-weight:normal">  Loan eligibility  </label>
						 <input readonly class="sales_submitted" type="text" id="loanEligibility_val" name="enquiryReport.loanEligibility_val">
						 </div>
						 </div>
						<div id="sourceFundingSlider" style="display:none;">	
							<div class="col-md-6 col-xs-12" style="padding-left: 25px; padding-right: 25px; min-height: 150px;">
								<label style="margin-left: -10px; margin-bottom: 30px; font-weight:normal">Own contribution receipt</label>
								<input style="width:100%" id="ownContro" type="text" name="enquiryReport.contributionReceipt"/>
								<div class="clearfix"></div>
							</div>	
							<div class="col-md-6 col-xs-12" style="padding-left: 25px; padding-right: 25px; min-height: 150px;">
								<label style="margin-left: -10px; margin-bottom: 30px; font-weight:normal">  Loan eligibility  </label>
								<input style="width:100%" id="loanAlig" type="text" data-slider-value="0" data-slider-step="1" name="enquiryReport.loanEligibility"/>
								<div class="clearfix"></div>
							</div>
							<div class="clearfix"></div>	
						</div>	
						<!-- END Instract By Ganesh  -- Add new field for loan -->
						
					
					<!-- Instect By Ganesh - Comment by A -->
					<!-- <div class="col-md-3 col-xs-12">
						<div class="group">
							<select  id="occupation" name="contact.contactReport.occupation">
							            <option value=""></option>
							            <option value="Agriculture">Agriculture</option>
							            <option value="Automobile">Automobile</option>
							            <option value="Aviation">Aviation</option>
							            <option value="Banking and Financial Sectors">Banking and Financial Sectors</option>
							            <option value="E-Commerce">E-Commerce</option>
							            <option value="Government">Government</option>
							            <option value="Healthcare and Pharma">Healthcare and Pharma</option>
							            <option value="Hospitality">Hospitality</option>
							            <option value="Infrastructure">Infrastructure</option>
							            <option value="IT">IT</option>
							            <option value="Manufacturing">Manufacturing</option>
							            <option value="Media and Entertainment">Media and Entertainment</option>
							            <option value="Metals and Mining">Metals and Mining</option>
							            <option value="Oil and Gas">Oil and Gas</option>
							            <option value="Professional Service">Professional Service</option>	
							            <option value="Railways">Railways</option>	
							            <option value="Travel and Tourism">Travel and Tourism</option>	
							            <option value="Telecom">Telecom</option>	
							            <option value="Textile">Textile</option>	
							            <option value="Others">Others</option>
							            
							</select>
							<span class="highlight"></span><span class="bar"></span>
							<label class="select-label">Employment Sector<strong class="mndt"></strong></label>
						</div>
						<div class="clearfix"></div>
					</div> -->
					
					<!-- <div class="col-md-2">
						<div class="group">
							<select class="" id="designation" name="contact.designation">
								<option></option>
							</select>
							<span class="highlight"></span><span class="bar"></span>
							<label class="select-label">Designation <strong class="mndt">*</strong></label>
							
							<input type="text" id="designation" class="autocomplete-off" name="contact.designation"/>
							<span class="highlight"></span><span class="bar"></span>
							<label>Designation</label>
						</div>
					</div> -->
					
					<!-- <div id="address2"> -->
						
						<!-- <div class="col-md-4 col-sm-12">
							<div class="group">
							  <input class="autocomplete-off autocomplete" type="text" required="required" id="officeAddress" name="contact.companyLocality"/>
							  <span class="highlight"></span>
							  <span class="bar"></span>
							  <label>Office Address</label>
							</div>
						</div> -->
						
						
						<!-- <div class="col-md-2 col-sm-6 col-xs-6">
							<div class="group">
								<input class="autocomplete-off locality" type="text" required="required" id="officeCity" name="contact.officeCity"/>
								<span class="highlight"></span><span class="bar"></span>
								<label>City</label>
							</div>
						</div> -->
						
						<!-- <div class="col-md-2 col-sm-6 col-xs-6">
							<div class="group">
								<input class="autocomplete-off postal_code" type="text" maxlength="6" id="officePinCode" name="contact.officePinCode"/>
								<span class="highlight"></span><span class="bar"></span>
								<label>Pin Code</label>
							</div>
						</div> -->
						<div class="clearfix"></div>
						
				<!-- 	</div> -->
					
					<!-- New fields -->
					<div class="" style="border-top: 1px solid #ddd; padding-top: 25px;">
						 
						<!-- Instract BY Ganesh - comment by A -->
						 <!-- <div class="col-md-6 radioBtnWrp mrgT0">
						  <div class="titleF">Customer Gender<strong class="mndt"></strong></div>
							<div class="radioBtnCol" data-toggle="buttons">
							  <label class="btn btn-primary active">
								<input type="radio" id="genderMale" class="customerGender" name="contact.contactReport.gender" checked value="Male">Male
							  </label>
							  <label class="btn btn-primary">
								<input type="radio" id="genderFemale" class="customerGender" name="contact.contactReport.gender" value="Female">Female
							  </label>
							  <label class="btn btn-primary">
								<input type="radio" id="genderOther"  class="customerGender" name="contact.contactReport.gender" value="Other">Other
							  </label>
							</div>
						</div> -->
						 <div class="col-md-6 radioBtnWrp mrgT0">
						  	<div class="titleF">Customer Classification <strong class="mndt">*</strong></div>
							<div class="radioBtnCol requiredRadio" data-toggle="buttons">
							  <label class="btn btn-primary NoLostReasonSelected">
								<input type="radio" id="classificationHot" class="customerClassification" name="contact.contactReport.customerClassification"  value="Hot">Hot
							  </label>
							  <label class="btn btn-primary NoLostReasonSelected" >
								<input type="radio" id="classificationWarm" class="customerClassification" name="contact.contactReport.customerClassification" value="Warm">Warm
							  </label>
							  <label class="btn btn-primary NoLostReasonSelected">
								<input type="radio" id="classificationCold"  class="customerClassification" name="contact.contactReport.customerClassification" value="Cold">Cold
							  </label>
							  
							  <!-- Instract BY Ganesh - Added new radio tab -->
							  <label class="btn btn-primary lostReasonSelect">
								<input type="radio" class="customerClassification" name="contact.contactReport.customerClassification" value="Lost">Lost
							  </label>
							</div>
						</div>
						<!-- <input type="text" name="lost_reason_c__c"> -->
						<div id="LostReasonDivId" class="col-md-3" style="display:none;margin-top: 13px;">
							<div class="group"><!-- requiredField sales_submitted requiredField-->
								<select class="" id="LostReasonID" name="lost_reason_c__c">
						            <option value=""></option>
									<option value="Not Interested">Not Interested</option>
								    <option value="Budget">Budget</option>
								    <option value="Time constraint">Time constraint</option>
								    <option value="Lost to competitor">Lost to competitor</option>
								    <option value="Size of Apartments">Size of Apartments</option>
								    <option value="Design/Vastu/View/Floor">Design/Vastu/View/Floor</option>
								    <option value="Specification/Amenities">Specification/Amenities</option>
								    <option value="No Plans">No Plans</option>
								    <option value="Location">Location</option>
								    <option value="Not Contactable">Not Contactable</option>
								    <option value="Duplicate Channel">Duplicate Channel</option>
								    <option value="Inventory not available">Inventory not available</option>
								    <option value="Foreign National">Foreign National</option>
								    <option value="Not Enquired">Not Enquired</option>
								    <option value="Test Enquiry">Test Enquiry</option>
								    <option value="Frequent Disconnect">Frequent Disconnect</option>
								    <option value="Ready Possession">Ready Possession</option>
								</select>
								<span class="highlight"></span><span class="bar"></span>
								<label class="select-label">Lost Reason<strong class="mndt">*</strong></label>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="col-md-3 col-sm-6 col-xs-12" id="followupTypeID" style="display:none;" >
							<div class="group">
							
								<select class="" id="followtype" name="enquiryReport.followType">
									<option value=""></option>
									<option value="Call">Call</option>
									<option value="Home Visit">Home Visit</option>
									<option value="Revisit">Revisit</option>
		
								</select> <span class="highlight"></span><span class="bar"></span> <label
									class="select-label">Follow-up Type <strong class="mndt">*</strong></label>
							</div>
							<div class="clearfix"></div>
						</div>
							
							
						<div class="col-md-3 col-sm-6 col-xs-12" id="followupDateID" ><!-- id="followupDateID" style="display:none;" -->
							<div class="form-group" id="fdate" style="margin-top: -15px; z-index: 0;">
								<label style="font-weight: normal;">Follow-up Date & Time<strong class="mndt">*</strong></label>
								<div class='input-group date datetimepickerfollow'>
									<input style="z-index: 0; border-radius: 0;" type='text' name="enquiryReport.followDate"
										class="form-control autocomplete-off "
										 id="followdate"/> <!-- sales_submitted --> <span style="border-radius: 0;" class="input-group-addon"> <span
										class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
							<!-- <div class="form-group" id="fdatevalue" style="display: none; margin-top: -15px; z-index: 0;">
								<label style="font-weight: normal;">Follow Date & Time</label>
								<div class='input-group datetimepickerfollowSpan'>
									<input style="z-index: 0; border-radius: 0;" type='text' name="enquiryReport.followDate"
										class="form-control autocomplete-off "
										id="followdate" /> <span style="border-radius: 0;" class="input-group-addon"> <span
										class="glyphicon glyphicon-calendar" onclick="onCalendarClick();"></span>
									</span>
								</div>
							</div>  -->
						</div>
						
						
						<div class="clearfix"></div>
						
						<!-- Instract BY Ganesh - comment by A -->
						<!-- <div class="col-md-6 radioBtnWrp mrgT0">
						  <div class="titleF">Vastu Preference<strong class="mndt">*</strong></div>
							<div class="radioBtnCol" data-toggle="buttons">
							  <label class="btn btn-primary active">
								<input type="radio" id="vastuPreferenceC" class="vastuPreference" name="enquiryReport.vastuPreference" checked value="Compliant">Compliant
							  </label>
							  <label class="btn btn-primary">
								<input type="radio" id="vastuPreferenceNC" class="vastuPreference" name="enquiryReport.vastuPreference" value="Non Compliant">Non Compliant
							  </label>
							  <label class="btn btn-primary">
								<input type="radio" id="vastuPreferenceNA"  class="vastuPreference" name="enquiryReport.vastuPreference" value="Not Applicable">Not Applicable
							  </label>
							</div>
						</div> -->
						
						
						
						<div class="col-md-6 radioBtnWrp mrgT0">
						  <div class="titleF">Preferred Unit Availability <strong class="mndt">*</strong></div>
							<div class="radioBtnCol requiredRadio" data-toggle="buttons">
							  <label class="btn btn-primary sales_submitted">
								<input type="radio" id="unitAvailabilityY"  class="unitAvailability" name="enquiryReport.unitAvailability"  value="Yes">Yes
							  </label>
							  <label class="btn btn-primary sales_submitted">
								<input type="radio" id="unitAvailabilityN" class="unitAvailability" name="enquiryReport.unitAvailability" value="No">No
							  </label>
							</div>
						</div>
						<div class="col-md-6 radioBtnWrp mrgT0">
						  <div class="titleF">Accompanied By <strong class="mndt">*</strong></div>
							<div class="radioBtnCol requiredRadio" data-toggle="buttons">
							  <label class="btn btn-primary sales_submitted">
								<input type="radio" id="accompaniedByS" class="accompaniedBy" name="enquiryReport.accompaniedBy"  value="Self">Self
							  </label>
							  <label class="btn btn-primary sales_submitted">
								<input type="radio" id="accompaniedByFy" class="accompaniedBy"name="enquiryReport.accompaniedBy" value="Family">Family
							  </label>
							  <label class="btn btn-primary sales_submitted">
								<input type="radio" id="accompaniedByFd" class="accompaniedBy" name="enquiryReport.accompaniedBy" value="Friends">Friends
							  </label>
							</div>
						</div>
						<div class="col-md-6 radioBtnWrp mrgT0">
						  <div class="titleF">Deal Negotiation <strong class="mndt">*</strong></div>
							<div class="radioBtnCol requiredRadio " data-toggle="buttons">
							  <label class="btn btn-primary sales_submitted">
								<input type="radio" id="dealNegotiationS" class="dealNegotiation"  name="enquiryReport.dealNegotiation" value="Skipped">Skipped
							  </label>
							  <label class="btn btn-primary sales_submitted">
								<input type="radio" id="dealNegotiationUW" class="dealNegotiation" name="enquiryReport.dealNegotiation" value="Underway">Underway
							  </label>
							 
							</div>
						</div>
						<div class="col-md-12 radioBtnWrp mrgT0">
						  <div class="titleF">Preferred construction status / Possession Timeline <strong class="mndt">*</strong></div>
							<div class="radioBtnCol requiredRadio" data-toggle="buttons">
							  <label class="btn btn-primary sales_submitted">
								<input type="radio" id="constructionStatusUC" class="constructionStatus sales_submitted" name="enquiryReport.constructionStatus" value="Under Construction">Under Construction
							  </label>
							  <label class="btn btn-primary sales_submitted">
								<input type="radio" id="constructionStatusNC" class="constructionStatus sales_submitted" name="enquiryReport.constructionStatus" value="Nearing Completion">Nearing Completion
							  </label>
							  <label class="btn btn-primary sales_submitted">
								<input type="radio" id="constructionStatusRP" class="constructionStatus sales_submitted" name="enquiryReport.constructionStatus" value="Ready Possession">Ready Possession
							  </label>
							 
							</div>
						</div>
						<div class="col-md-12 radioBtnWrp mrgT0">
						  <div class="titleF">Timeframe to Book <strong class="mndt">*</strong></div>
							<div class="radioBtnCol requiredRadio" data-toggle="buttons">
							  <label class="btn btn-primary sales_submitted">
								<input type="radio" id="timeFrameToBook0" class="timeframeToBook sales_submitted" name="enquiryReport.timeframeToBook" value="Less than 7days">Less than 7days
							  </label>
							  <label class="btn btn-primary sales_submitted">
								<input type="radio" id="timeFrameToBook1" class="timeframeToBook sales_submitted" name="enquiryReport.timeframeToBook" value="Between 8-15 days">Between 8-15 days
							  </label>
							  <label class="btn btn-primary sales_submitted">
								<input type="radio" id="timeFrameToBook2" class="timeframeToBook sales_submitted" name="enquiryReport.timeframeToBook" value="Between 16-30 days">Between 16-30 days
							  </label>
							 <label class="btn btn-primary sales_submitted">
								<input type="radio" id="timeFrameToBookM3" class="timeframeToBook sales_submitted" name="enquiryReport.timeframeToBook" value="Between 16-30 days">More than 30 days
							  </label>
							</div>
						</div>
						
						
						<!-- Triggers and barriers adding on sales page -  Change By Satheesh Kyatham- 07-10-2019   -->
						
						<div class="col-md-3 col-sm-6 col-xs-12">
							<div class="group">
								<select class="requiredField sales_submitted" id="trigger1" name="enquiryReport.trigger1">
						            <option value=""></option>
									<option value="Location">Location</option>
								    <option value="MLP">MLP</option>
								    <option value="Amenities">Amenities</option>
								    <option value="Unit layout">Unit layout</option>
								    <option value="Spacious rooms">Spacious rooms</option>
								    <option value="Views">Views</option>
								    <option value="Sample flat">Sample flat</option>
								    <option value="Brand">Brand</option>
								    <option value="Offer">Offer</option>
								    <option value="Payment Plan">Payment Plan</option>
								    <option value="Referral/ Positive Word of Mouth">Referral/ Positive Word of Mouth</option>
								    <option value="Construction Timeline">Construction Timeline</option>
								    <option value="Ready Liquidity available">Ready Liquidity available</option>
								    <option value="Very less dense development">Very less dense development</option>
								    <option value="Luxury specifications inside flat/building">Luxury specifications inside flat/building</option>
								    <option value="USP concept">USP concept</option>
								</select>
								<span class="highlight"></span><span class="bar"></span>
								<label class="select-label">Trigger 1 for purchase<strong class="mndt">*</strong></label>
							</div>
							<div class="clearfix"></div>
						</div>
						
						<div class="col-md-3 col-sm-6 col-xs-12">
							<div class="group">
								<select class="sales_submitted" id="trigger2" name="enquiryReport.trigger2">
						            <option value=""></option>
									<option value="Location">Location</option>
								    <option value="MLP">MLP</option>
								    <option value="Amenities">Amenities</option>
								    <option value="Unit layout">Unit layout</option>
								    <option value="Spacious rooms">Spacious rooms</option>
								    <option value="Views">Views</option>
								    <option value="Sample flat">Sample flat</option>
								    <option value="Brand">Brand</option>
								    <option value="Offer">Offer</option>
								    <option value="Payment Plan">Payment Plan</option>
								    <option value="Referral/ Positive Word of Mouth">Referral/ Positive Word of Mouth</option>
								    <option value="Construction Timeline">Construction Timeline</option>
								    <option value="Ready Liquidity available">Ready Liquidity available</option>
								    <option value="Very less dense development">Very less dense development</option>
								    <option value="Luxury specifications inside flat/building">Luxury specifications inside flat/building</option>
								    <option value="USP concept">USP concept</option>
								</select>
								<span class="highlight"></span><span class="bar"></span>
								<label class="select-label">Trigger 2 for purchase</label>
							</div>
							<div class="clearfix"></div>
						</div>
						
						<div class="col-md-3 col-sm-6 col-xs-12">
							<div class="group">
								<select class="requiredField sales_submitted" id="barrier1" name="enquiryReport.barrier1">
						            <option value=""></option>
									<option value="OCR issue">OCR issue</option>
								    <option value="Loan eligibility">Loan eligibility</option>
								    <option value="Budget constraint">Budget constraint</option>
								    <option value="Price vs Value">Price vs Value</option>
								    <option value="Location">Location</option>
								    <option value="Possession timeline">Possession timeline</option>
								    <option value="Unit Layout">Unit Layout</option>
								    <option value="MLP">MLP</option>
								    <option value="USP">USP</option>
								    <option value="Sale of property">Sale of property</option>
								    <option value="Vastu">Vastu</option>
								    <option value="Payment Plan">Payment Plan</option>
								    <option value="Discuss with family">Discuss with family</option>
								    <option value="Need time to arrange finance">Need time to arrange finance</option>
								    <option value="Undesirable inventory">Undesirable inventory</option>
								    <option value="Lack of parking spaces">Lack of parking spaces</option>
								    <option value="Evaluate competition">Evaluate competition</option>
								    <option value="Site lacks proper access">Site lacks proper access</option>
								</select>
								<span class="highlight"></span><span class="bar"></span>
								<label class="select-label">Barrier 1 for purchase<strong class="mndt">*</strong></label>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="col-md-3 col-sm-6 col-xs-12">
							<div class="group">
								<select class="sales_submitted" id="barrier2" name="enquiryReport.barrier2">
						            <option value=""></option>
									<option value="OCR issue">OCR issue</option>
								    <option value="Loan eligibility">Loan eligibility</option>
								    <option value="Budget constraint">Budget constraint</option>
								    <option value="Price vs Value">Price vs Value</option>
								    <option value="Location">Location</option>
								    <option value="Possession timeline">Possession timeline</option>
								    <option value="Unit Layout">Unit Layout</option>
								    <option value="MLP">MLP</option>
								    <option value="USP">USP</option>
								    <option value="Sale of property">Sale of property</option>
								    <option value="Vastu">Vastu</option>
								    <option value="Payment Plan">Payment Plan</option>
								    <option value="Discuss with family">Discuss with family</option>
								    <option value="Need time to arrange finance">Need time to arrange finance</option>
								    <option value="Undesirable inventory">Undesirable inventory</option>
								    <option value="Lack of parking spaces">Lack of parking spaces</option>
								    <option value="Evaluate competition">Evaluate competition</option>
								    <option value="Site lacks proper access">Site lacks proper access</option>
								</select>
								<span class="highlight"></span><span class="bar"></span>
								<label class="select-label">Barrier 2 for purchase</label>
							</div>
							<div class="clearfix"></div>
						</div>
						
						<!-- END of Barriers & Triggers -->
						<div class="col-md-3 col-sm-6 col-xs-12" id="closingManagerIdDiv">
							<div class="group">
							<!-- <div id="sourcingManagerId"></div> -->
							<select class="requiredField autocomplete-off" id="closingManagerId" name="closingmanagers">
							</select>
								<span class="highlight"></span><span class="bar"></span>
								<label class="select-label">Closing Manager<strong class="mndt">*</strong></label>
							</div>
							<div class="clearfix"></div>
						</div>
						
						<div class="col-md-3 col-sm-6 col-xs-12" id="sourcingManagerIdDiv">
							<div class="group">
							<!-- <div id="sourcingManagerId"></div> -->
							<select class="requiredField autocomplete-off" id="sourcingManagerId" name="closingManagerDto">
							</select>
								<span class="highlight"></span><span class="bar"></span>
								<label class="select-label">Sourcing Manager<strong class="mndt">*</strong></label>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="col-md-3 col-sm-6 col-xs-12" id="closingTeamLeadIdDiv">
							<div class="group">
							<div id="sourcingManagerId"></div>
							<select class="requiredField autocomplete-off" id="closingTeamLeadId" name="closingTeamLeadDto">
							</select>
								<span class="highlight"></span><span class="bar"></span>
								<label class="select-label">Closing Team lead<strong class="mndt">*</strong></label>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="col-md-3 col-sm-6 col-xs-12" id="sourcingTeamLeadIdDiv">
							<div class="group">
							<div id="sourcingManagerId"></div>
							<select class="autocomplete-off" id="sourcingTeamLeadId" name="sourcingTeamLeadDto">
							</select>
								<span class="highlight"></span><span class="bar"></span>
								<label class="select-label">Sourcing Team Lead</label>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="col-md-3 col-sm-6 col-xs-12" id="">
							<div class="group">
							<div id=""></div>
							<select class="autocomplete-off" id="internationalSMId" name="internationalSMDto">
							</select>
								<span class="highlight"></span><span class="bar"></span>
								<label class="select-label">International Sales Manager</label>
							</div>
							<div class="clearfix"></div>
						</div>
						
						<div class="col-md-3 col-sm-6 col-xs-12">
							<div class="group">
								<input class="disableInputs" type="text" id="enquirytypeID" disabled="disabled">
								<span class="highlight"></span><span class="bar"></span>
								<label>Enquiry Type</label>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="col-md-3 col-sm-6 col-xs-12">
							<div class="group">
								<input class="disableInputs" type="text" id="enquirysourceID" disabled="disabled">
								<span class="highlight"></span><span class="bar"></span>
								<label>Enquiry Source</label>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="col-md-3 col-sm-6 col-xs-12">
							<div class="group">
								<input class="disableInputs" type="text" id="walkinsourceID" disabled="disabled">
								<span class="highlight"></span><span class="bar"></span>
								<label>Walk-in Source</label>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="col-md-3 col-sm-6 col-xs-12">
							<div class="group">
								<select class="sales_submitted disableInputs" id="verticalId" name="verticle__c">
						            <option value=""></option>
									<option value="International">International</option>
									<option value="Cross">Cross</option>
									<option value="Corporate">Corporate</option>
									<option value="Loyalty/ Referral">Loyalty/ Referral</option>
									<option value="Employee">Employee</option>
									<option value="Region CP">Region CP</option>
									<option value="Region Direct">Region Direct</option>
									<option value="International Team">International Team</option>
									<option value="International Type">International Type</option>
								</select>
								<span class="highlight"></span><span class="bar"></span>
								<label class="select-label">Vertical</label>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="col-md-12 col-xs-12">
							<div class="group">
								<pre class="" id="enquiryOldComment" style="border-radius: 0; margin-bottom: 40px;"></pre>
								<span class="highlight"></span><span class="bar"></span>
								<label>Old Comments</label>
							</div>
							<div class="clearfix"></div>
						</div>
						
						
						<div class="col-md-12 col-xs-12"  id="enquiryCommentsDiv">
							<div class="group">
							<textarea class="autocomplete-off requiredField" id="enquiryNonEditComment" name="enquiryReport.enquiryNonEditComment"></textarea> <!-- class="sales_submitted" -->
								<span class="highlight"></span><span class="bar"></span>
								<label>Add Comments<strong class="mndt">*</strong></label>
							</div>
							<div class="clearfix"></div>
						</div>
						<!-- <div class="clearfix"></div> -->
						
						<div class="clearfix"></div>
					</div>
					
					
					<!-- END Add new field -->
					<div class="clearfix"></div>
					
				</div>
				<div class="clearfix"></div>
				<div class="tab-pane">
				  
				</div>
				
				<div class="btnWrp">
					<div class="btnCol">
						<!-- <a class="btn btn-primary btnPrevious">
							<span>Previous</span>
						</a> --> 
						<!-- <a class="btn btn-primary btnNext" onclick="submitAddressInfo(event,this);">
							<span>Submit</span>
						</a> -->
							
						<%-- <c:if test="${isView=='Y'}">
   <a class="btn btn-primary blue_btn  mrgR15" id="salsbtnsave" onclick="saveAddressInfo(event,this);">
							<span>Save & Clossse</span>
						</a>
</c:if> --%>
						<span id="salsbtnsaveDiv">
							<%if(request.getParameter("salesViewType").toString().equals("Y")){%>
							<a class="btn btn-primary  blue_btn mrgR15" id="salsbtnsave" onclick="saveAddressInfo(event,this);"> <!-- class="disableInputs" -->
								<span>Save & Close</span>
							</a>
							<%}else{%>
							 <a class="btn btn-primary blue_btn  mrgR15" id="salsbtnsave" onclick="saveAddressInfo(event,this);">
								<span>Save & Close</span>
							</a>
						<%} %>
						</span>
						<a class="btn btn-primary blue_btn  mrgR15" id="btneoi" style="display:none;" onclick="generateEOI(event,this);">
							<span>Save & Generate EOI</span>
						</a>
						<div class="mrgR15" id="syncIdDivSales" style="color:red;font-weight: bold;font-size: 18px;">
						  Enquiry Sync In-Progress, Please Wait OR Refresh the page.
						</div>				
						<div class="clearfix"></div>
					</div>
					<div class="clearfix"></div>
				</div>
					</form:form>
			</div>
			<div class="clearfix"></div>
			
			<div  class="tab-pane" id="tab3">
			  <jsp:include page="commonBankPayment.jsp"></jsp:include>
			</div>	
			
			<div class="tab-pane" id="inventoryTabCont">
			  <jsp:include page="inventory.jsp"></jsp:include>
			</div>	
			
			
			<div class="tab-pane" id="costsheetTabCont">
			  <%-- <jsp:include page="costsheetSalesinfo.jsp"></jsp:include> --%>
			  <jsp:include page="costsheetSalesinfo2.jsp"></jsp:include>
			</div>	
				
				
			<div class="tab-pane" id="onlinePaymentTab">
			  <jsp:include page="generatePayment.jsp"></jsp:include>
			</div>	
				
		</div>
	<div class="clearfix"></div>
	</div>
<!-- <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
  Launch demo modal
</button> -->

<!-- Modal -->
<div class="modal fade" id="multiEnq" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <!-- <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> -->
        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
      </div>
      <div class="modal-body">
        	<table class="table table-bordered" id="enquiryTable">
		      <thead>
		        <tr>
		          <th>#</th>
		          <th>Enquiry Date</th>
		          <th>Enquiry Id</th>
		          <th>Type</th>
		          <th>Status</th>
		          <th>Source</th>
		          <th>Owner</th>
		        </tr>
		      </thead>
		      <tbody>
		      </tbody>
		    </table>
      </div>
      <div class="modal-footer">
        <!-- <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> -->
        <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="populateEnquiry(event,this);">Save changes</button>
        <input type="hidden" value="15">
      </div>
    </div>
  </div>
</div>
	
<!-- Do not remove and Take in code merge -->	
<!-- <a class="printFloat" style="bottom: 171px;" onclick="generateKYCLink(event,this,'Y');" data-toggle="tooltip" title="Send KYC">
	<i class="fa fa-user" style="line-height: 50px !important; font-size: 22px !important;"></i>
</a> -->	
<a class="printFloat" style="bottom: 40px;" onclick="closingManagerList()" data-toggle="tooltip" title="Assigned List">
	<i class="fa fa-home" style="line-height: 50px !important; font-size: 22px !important;"></i>
</a>
	
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    
    <script src="<c:url value='/resources/js/jquery-1.12.4.min.js' />"></script>
    <script src="<c:url value='/resources/js/bootstrap.min.js' />"  ></script>
	<script src="<c:url value='/resources/js/anypicker.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery.touchSwipe.min.js' />"></script>
  
    <script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>  
    
    <!-- For Webcam attachments -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/webcamjs/1.0.25/webcam.min.js"></script>

	<script src="<c:url value='/resources/js/springForm.js?v=${sessionScope.version}' />"></script>
	<script src="<c:url value='/resources/js/commonValidation.js?v=18.19' />"></script>
	<script src="<c:url value='/resources/js/utility.js?v=18.19' />"></script>
	<%-- <script src="<c:url value='/resources/js/enquiryRequest/enquiryRequest.js' />"></script> --%>
	<script src="<c:url value='/resources/js/enquiryRequest/salesRequest.js?v=${sessionScope.version}' />"></script>	
    <script src="<c:url value='/resources/js/intlTelInput.js' />"></script>
   
    <script src="<c:url value='/resources/js/sweetalert2.min.js' />"></script>
   
   
  	<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/8.4.7/js/intlTelInput.js"></script> -->
	<script src="<c:url value='/resources/js/bankdetails.js?v=${sessionScope.version}' />"></script>
	<script src="<c:url value='/resources/js/index.js?v=18.18' />"></script>
	
	<script>
	
		var rcpNO = $("#isReferredByChannelPartnerD").removeClass("shake");
		var rcpYes = $("#isReferredByChannelPartnerCP").removeClass("shake");
		
		$('.btnNext').click(function(){
		  $('.nav-tabs > .active').next('li').find('a').trigger('click');
		  
		 
		}); 

		 $('.btnPrevious').click(function(){
		  $('.nav-tabs > .active').prev('li').find('a').trigger('click');
		});
		
		/* Comment by A */
		/* if ($(window).width() > 960) {
		    $(".swipeCard").swipe( {
				swipeLeft:function(event, direction, distance, duration, fingerCount) {
				  $(".nav-tabs li.active").next('li').find('a').tab('show');
				},
				swipeRight:function(event, direction, distance, duration, fingerCount) {
				  $(".nav-tabs li.active").prev('li').find('a').tab('show');
				},
				excludedElements: "button, input, select, textarea, .noSwipe"
			});		  
		} else {			
			$(".swipeCard").swipe( {
				swipeLeft:function(event, direction, distance, duration, fingerCount) {
				  $(".nav-tabs li.active").next('li').find('a').tab('show');
				},
				swipeRight:function(event, direction, distance, duration, fingerCount) {
				  $(".nav-tabs li.active").prev('li').find('a').tab('show');
				},				
			});
		} */
		//created by Hassan Rana
		//hassanrana300@gmal.com
		
		$("label[labelName$='isReferredByChannelPartner']").click(function() {			
			debugger;
			var cpHS = $(this).attr('value');		
			isReferredChanged(cpHS);			
			//$("#cp" + cpHS).addClass("shake");						
		});
	</script>
	
	<!-- <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB1YQeqmJoubsXBr0BLkIqOtwWyYk2mKJQ&libraries=places&callback=initAutocomplete" async defer></script> -->
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAMtXEgz-EmdSjN3vqjcG0KpVP72vMKtyI&libraries=places&callback=initAutocomplete" async defer></script>
	
		
		
 <script src="<c:url value='/resources/js/moment.js' />"></script>
 <script src="<c:url value='/resources/js/bootstrap-datetimepicker.min.js' />"></script> 
 
 <script src="<c:url value='/resources/js/separate/inventory.js?v=${sessionScope.version}'/>"></script>
 
<!-- Costsheet --> 
<script src="<c:url value='/resources/js/jquery.tabletojson.min.js'/>"></script>
<%-- <script type="text/javascript" src="<c:url value='/resources/js/jquery.countdownTimer.js'/>"></script> --%>
<script src="<c:url value='/resources/js/enquiryRequest/common.js?v=18.18'/>"></script>
<script src="<c:url value='/resources/js/separate/costsheet.js?v=${sessionScope.version}'/>"></script>
<script src="<c:url value='/resources/js/separate/webcamAttachmentCS.js?v=${sessionScope.version}'/>"></script>


<script src="<c:url value='/resources/js/separate/storeEOIPaymentDtl.js?v=${sessionScope.version}'/>"></script>
<script src="<c:url value='/resources/js/separate/eoiFormPrint.js?v=${sessionScope.version}'/>"></script>

<script src="<c:url value='/resources/js/separate/generatePayment.js?v=${sessionScope.version}'/>"></script>

<%-- <script src="<c:url value='/resources/js/separate/storeEOIPaymentDtl.js?v=15'/>"></script> --%>
<!-- END Costsheet --> 
 
 <script>
 function formatDate(date) {
	    var d = new Date(date),
	        month = '' + (d.getMonth() + 1),
	        day = '' + d.getDate(),
	        year = d.getFullYear();

	    if (month.length < 2) month = '0' + month;
	    if (day.length < 2) day = '0' + day;

	    return [day, month, year].join('-');
	}
 /*Start :  Added By Satheesh K - 22-05-2020 - For Followup Date and Time adding  */
 function formatDateWithTime(date) {
	    var d = new Date(date),
	        month = '' + (d.getMonth() + 1),
	        day = '' + d.getDate(),
	        year = d.getFullYear(),
	    	hours = addZero(d.getHours()),
       		minutes = addZero(d.getMinutes());

	    if (month.length < 2) month = '0' + month;
	    if (day.length < 2) day = '0' + day;
		var finalTimeStamp = [day, month, year].join('-');
	    return finalTimeStamp+" "+hours+":"+minutes;
	}
 function addZero(i) {
	  if (i < 10) {
	    i = "0" + i;
	  }
	  return i;
	}
 /*END :*/
var dateToday = new Date();
$(function () {                
     $('.datetimepickerfollow').datetimepicker({
         format: 'DD-MM-YYYY HH:mm',
        	 //inline: true,
             sideBySide: true
        // minDate: dateToday 
         
   });
 });
 
/* function onCalendarClick()
{
	$('.datetimepickerfollowSpan').datetimepicker({
        format: 'DD-MM-YYYY HH:mm'//,
       // minDate: dateToday 
        
  });
	} */
$(function () {                
    $('.datetimepicker').datetimepicker({
        format: 'DD-MM-YYYY',
        minDate: dateToday
        
  });
});
 </script>	
		
		
	<script src="<c:url value='/resources/js/bootstrap-slider.min.js' />"></script>
	<%-- <script src="<c:url value='/resources/js/separate/rangeSlider.js' />"></script>		 --%>
		
	<script>
		
		
		
		
		$('#sourceOfFundingLoanLabel').click ( function (){
			$('#sourceFundingSlider').show();
		});
		
		$('#sourceOfFundingLabel').click ( function (){
			$('#sourceFundingSlider').hide();
		});
		$('.lostReasonSelect').click ( function (){
			//alert("On select");
			$('#LostReasonDivId').show();
			$('#followupTypeID').hide();
			$('#followupDateID').hide();
			$('#LostReasonID').addClass('requiredField');
			$('#followtype').removeClass('requiredField');
			$('#followdate').removeClass('requiredField');
		});
		$('.NoLostReasonSelected').click ( function (){
			//alert("On select");
			$('#LostReasonDivId').hide();
			$('#followupTypeID').show();
			$('#followupDateID').show();
			$('#LostReasonID').removeClass('requiredField');
			$('#followtype').addClass('requiredField');
			$('#followdate').addClass('requiredField');
		});
		
	</script>	
		
				
		
	<script>
	// Without JQuery
	var slider = new Slider("#budgetSales", {
	    ticks: [2000000, 10000000, 20000000, 30000000, 40000000, 50000000],
	    ticks_labels: ['20L', '1Cr', '2Cr', '3Cr', '4Cr', '5Cr' ],
	    ticks_positions: [0, 20, 40, 60, 80, 100],
	    //ticks_snap_bounds: 60,
	    tooltip: 'always',
	   step: 100000,
	   value: 10000000,
	   formatter: function(value) {
		   
		   //alert ("Value ::: " + value);
		   
		   
		   /* var x=value;
		   x=x.toString();
		   var lastThree = x.substring(x.length-3);
		   var otherNumbers = x.substring(0,x.length-3);
		   if(otherNumbers != '')
		       lastThree = ',' + lastThree;
		   var res = otherNumbers.replace(/\B(?=(\d{2})+(?!\d))/g, ",") + lastThree; */

			if(value >= 10000000) value = (value/10000000).toFixed(2) + ' Cr';
			else if(value >= 100000) value = (value/100000).toFixed(2) + ' Lac';
			else if(value >= 1000) value = (value/1000).toFixed(2) + ' K';
			
			
			//return val;
		   
		   
		   return value;
		}
	    
	});
		
		
	var slider = new Slider("#carpetSales", {
	    ticks: [200, 500, 1000, 1500, 2000, 2500],
	    ticks_labels: ['200', '500', '1000', '1500', '2000', '2500' ],
	    ticks_positions: [0, 20, 40, 60, 80, 100],
	    //ticks_snap_bounds: 60,
	    tooltip: 'always',
	   step: 50,
	   value: 700,
		formatter: function(value) {
		   
		   
		   
		   return value + ' sqft';
		}
	    
	});

	</script>	
		
		
	<%@ include file="/WEB-INF/views/pages/footer.jsp" %>
		
  </body>
</html>