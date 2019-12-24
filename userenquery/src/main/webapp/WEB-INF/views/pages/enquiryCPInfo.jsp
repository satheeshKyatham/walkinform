<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <html lang="en">
  <%
	 response.addHeader("Expires","0");
	 response.addHeader("Pragma","no-cache");
	 response.setHeader("Cache-Control","no-cache,no-store, must-revalidate, pre-check=0, post-check=0, max-age=0, s-maxage=0");
	 response.addHeader("X-Frame-Options", "DENY");
	 %>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Godrej</title>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css' />">
	<link rel="stylesheet" href="<c:url value='/resources/css/inner.css' />">
	<link rel="stylesheet" href="<c:url value='/resources/css/style.css' />">
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
	
	
	<link rel="stylesheet" type="text/css"  href="<c:url value='/resources/css/intlTelInput.css' />" />
    
    
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/bootstrap-slider.min.css' />  " />
    
    
  </head>
  <body onload="initialize()" >
   
   
   <nav class="navbar topMainBar">
	  <div class="container">
	    <!-- Brand and toggle get grouped for better mobile display -->
	    <div class="navbar-header">
	     <!--  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button> -->
      	<a class="navbar-brand" href="#">
			<img class="topLogo" src="<c:url value='/resources/images/gplLogo.jpg' />"/>
		</a>
	    </div> 
		
		<!-- <div class="proTopTitle" id="projectTitle">
		  
			GODREJ AQUA
		</div> -->
		
	    <!-- Collect the nav links, forms, and other content for toggling -->
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      
	     
	      <ul class="nav navbar-nav navbar-right">
	        <!-- <li><a href="#"><i class="glyphicon glyphicon-log-out"></i> LOGIN</a></li> -->
	        <!-- <li class="dropdown">
	          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
	          <ul class="dropdown-menu">
	            <li><a href="#">Action</a></li>
	            <li><a href="#">Another action</a></li>
	            <li><a href="#">Something else here</a></li>
	            <li role="separator" class="divider"></li>
	            <li><a href="#">Separated link</a></li>
	          </ul>
	        </li>-->
	      </ul> 
	    </div>
	  </div>
	</nav>
   
	
	
	<div class="container formCol swipeCard posRelative">
			
		<div class="commonLoad" id="mainPageLoad" style="display:none;"></div>	
			
		<div class="titleCol">
			<h4 class="">
				<span id="projectTitle"></span>					
			</h4>
			<div class="clearfix"></div>
		</div>	
		<div class="clearfix"></div>	
				<input type="hidden" id="contextPath" value="${pageContext.request.contextPath}">
				<input type="hidden" id="hasParam" value="${hasParam}">
				<input type="hidden" id="token" value="${token}">
				<input type="hidden" id="recordTypeProspect" value="${recordTypeProspect}">
			    <input type="hidden" id="recordTypeCustomer" value="${recordTypeCustomer}">
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
				<li class="active">
					<!-- <a href="#tab1" data-toggle="tab" >Basic info</a> --> 
				</li>
				<!-- <li>
					<a href="#tab2" data-toggle="tab">Sales</a>
				</li>  -->
				<!-- <li>
					<a href="#tab3" data-toggle="tab">Address</a>
				</li>
				<li>
					<a href="#tab4" data-toggle="tab">Other info</a>
				</li> -->
			</ul>
			<div class=""></div>
		</div>
		
		<div class="tab-content formTabCont">
			
			
			<div class="tab-pane active" id="tab1">
				<form:form modelAttribute="EnquiryRequest" id="enquiryRequestCPBasicInfoForm">
				<input type="hidden" value="${projectName}" id="projectName" name="projectName">
				<input type="hidden" value="${AssignTO}" id="assignTO" name="assignTo">
				<!-- <input type="hidden" class="enquirysfid" disabled="disabled" name="sfid"> -->
				<input type="hidden" class="enquiryId" disabled="disabled" name="enquiryId">
				<input type="hidden" class="contactId" disabled="disabled" name="contact.contactId">
				<!-- <input type="hidden" class="contactId" disabled="disabled" name="contactId.sfid"> -->
				<input type="hidden" name="projectType" id="projectType" value="Residential">
				<input type="hidden" name="enquirySource" id="enquirySource" value="Walk-in">
				<input type="hidden" name="enquiryRating" value="Hot" id="enquiryRating">
				<input type="hidden" value="${projectSfid}" name="project.sfid" id="projectSfid" class="projectSfid"><!--value="a1l6F000004QtUZQA0" a20O0000002agRG-->
				<input type="hidden" value="${countryCode}" name="contact.countryCode" id="countryCode"><!--  +91-->
				<input type="hidden" value="1" name="project.projectId" id="projectId"> 
				<input type="hidden" value="${projectSfid}" name="contact.contactReport.projectId" id="contact_projectId"> 
				<input type="hidden" value="Site Visit Done" name="enquiryStatus" id="enquiryStatus">
				<!-- <input type="hidden" value="a2X6F000000JE2FUAW" name="advertisement" id="advertisement"> --><!--a0xO000000F8TFR a0xO000000F8TFRIA3 --><!-- a2eO0000000Mix6IAC -->
				<input type="hidden" class="enquiryReportId" name="enquiryReport.enquiryReportId">
				<input type="hidden" class="enquiryprojectidId" name="enquiryReport.projectId"  value="${projectSfid}">
				<input type="hidden" class="contactReportId" name="contact.contactReport.contactReportId">
				<div class="row "> <!-- bounceInLeft animated -->
					<div class="col-md-12 commonErrorCol commonErrorDiv" style="display: none">
						<div class="alert alert-danger">
						  	Kindly fill the <strong>required</strong> fields.
						</div>
					</div>
					<div class="col-md-12 commonErrorCol commonMessageDiv" style="display: none">
						<div class="alert alert-danger">
						  	<strong id="commonBoldMessage"></strong>
						</div>
					</div>
					<div class="col-md-3 col-sm-6 col-xs-12">
						<div class="group">				
						  <div class="input-group" id="contactDiv"> 
						      <input type="hidden" id="hiddenMobileNo" value="${mobileNo}">
							  <input type="text" value="${countryCode}" class="autocomplete-off form-control mobile requiredField contactNoDiv" id="mobileNo" name="contact.mobileNo" maxlength="10" onkeyup="getExistingInfo();" tabindex="-1"><!--onchange="getExistingInfo();"-->
							  <span onclick="getExistingInfo();" class="input-group-addon contactNoDiv" data-toggle="modal" ><!--   data-target="#multiEnq"-->
								<i class="glyphicon glyphicon-search contactNoDiv" ></i>
							  </span>
							  <span class="highlight"></span><span class="bar"></span>
								<label>Contact no.<strong class="mndt">*</strong></label>
							</div>
							
							
							<!-- <input type="text" id="mobileNo" onchange="getExistingInfo();" class="mobile requiredField"  name="contact.mobileNo" maxlength="10"/> -->
							<!-- <span class="highlight"></span><span class="bar"></span>
							<label>Contact no.<strong class="mndt">*</strong></label> -->
						</div>
						<div class="clearfix"></div>
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
							<input type="text" id="firstName" class="contactFields autocomplete-off requiredField" required="required" name="contact.firstName"/>
							<span class="highlight"></span><span class="bar"></span>
							<label>First name <strong class="mndt">*</strong></label>
							<!-- <small class="errorMsg">Error message</small> -->
						</div>
						<div class="clearfix"></div>
					</div>
					
					<div class="col-md-3 col-sm-6 col-xs-6">
						<div class="group">
							<input type="text" id="lastName" class="contactFields autocomplete-off requiredField" required="required" name="contact.lastName"/>
							<span class="highlight"></span><span class="bar"></span>
							<label>Last name <strong class="mndt">*</strong></label>
						</div>
						<div class="clearfix"></div>
					</div>
					
					<!-- field Removed -->
					<div class="col-md-3 col-sm-6 col-xs-12">
						<div class="group">
							<input type="text" id="email" class="contactFields autocomplete-off requiredField emailaddress" name="contact.otherEmail" onkeyup="this.value = this.value.toLowerCase();"/>
							<span class="highlight"></span><span class="bar"></span>
							<label>Email ID <strong class="mndt">*</strong></label>
						</div>
						<div class="clearfix"></div>
					</div>
					
					<div class="clearfix"></div>
					
					<div class="col-md-2 radioBtnWrp mrgT0" style="margin-top:-16px !important;">
						  <div class="titleF">Have we met before <strong class="mndt">*</strong>
						  	<div class="clearfix"></div>
						  </div>
							<div class="radioBtnCol" data-toggle="buttons">
							  <label class="btn btn-primary active enquiryFields">
								<input type="radio" id="haveWeMetBeforeY" class="haveWeMetBefore " name="enquiryReport.haveWeMetBefore" checked value="Yes">Yes
							  </label>
							  <label class="btn btn-primary enquiryFields">
								<input type="radio" id="haveWeMetBeforeN" class="haveWeMetBefore " name="enquiryReport.haveWeMetBefore" value="No">No
							  </label>
						</div>
						
						<div class="clearfix"></div>
						</div>
						
						
						
						<div class="col-md-2 col-xs-12">
							<div class="group">
								<select id="ageGroup" name="contact.contactReport.ageGroup" class="contactFields">
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
								<div class="clearfix"></div>
							</div>
							<div class="clearfix"></div>
						</div>
						
						
						
						
						<div class="clearfix"></div>
						
						
						
						
						<div id="address" style="padding-top: 45px; margin-bottom: 40px; border-top: 1px solid #ccc; border-bottom: 1px solid #ccc;">
						
						
						<!-- <div style="display:none;">
							<input id="resLat">
							<input id="resLng">
							<input id="officeLat">
							<input id="officeLng">
						</div> -->
						
						
						<div class="col-md-4 col-sm-12">
							<div class="group">
							  <input class="autocomplete-off autocomplete contactFields requiredField" required="required" type="text" id="addressLine2" name="contact.addressLine2" />
							  <span class="highlight"></span>
							  <span class="bar"></span>
							  <label>Residence Location <strong class="mndt"> *</strong></label>
							</div>
						</div>
						
						<!-- Instracted by Ganesh - comment by A -->
						<!-- <div class="col-md-2 col-sm-12">
							<div class="group">
							  <input type="text" id="addressLine1" name="contact.addressLine1" class="contactFields autocomplete-off"/>
							  <span class="highlight"></span>
							  <span class="bar"></span>
							  <label>Flat/ House No.</label>
							</div>
						</div> -->
						<!-- END Instracted by Ganesh - comment by A -->
						
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
								<input class="autocomplete-off administrative_area_level_1 contactFields requiredField" type="text" id="residentialState" name="contact.residentialState"/>
								<span class="highlight"></span><span class="bar"></span>
								<label>State <strong class="mndt"> *</strong></label>
							</div>
						</div>
						<div class="col-md-2 col-sm-6 col-xs-6">
							<div class="group">
								<input class="autocomplete-off country contactFields requiredField" type="text" id="residentialCountry" name="contact.residentialCountry"/>
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
								<input class="autocomplete-off locality contactFields requiredField" type="text" name="contact.city" id="city"/>
								<span class="highlight"></span><span class="bar"></span>
								<label>City <strong class="mndt"> *</strong></label>
							</div>
						</div>
						
						<div class="col-md-2 col-sm-6 col-xs-6">
							<div class="group">
								<input class="autocomplete-off postal_code contactFields" type="text" name="contact.pinCode" id="pinCode" maxlength="6"/>
								<span class="highlight"></span><span class="bar"></span>
								<label>Pin Code</label>
							</div>	
						</div>
						<div class="clearfix"></div>
					</div>
					
					
					<div style="border-bottom: 1px solid #ccc; margin-bottom: 45px;">
							<div class="col-md-2 col-xs-6">
								<div class="group">
									<select id="employmentStatus" name="contact.contactReport.employmentStatus" class="contactFields">
									        <option value=""></option>
									        <option value="Salaried">Salaried</option>
									        <option value="Business">Business</option>
									        <option value="Professional">Professional</option>
									        <option value="Retired">Retired</option>
									        <option value="Homemaker">Homemaker</option>							       		        					    
									</select>
									<span class="highlight"></span><span class="bar"></span>
									<label class="select-label">Occupation<strong class="mndt"></strong></label>
								</div>
								<div class="clearfix"></div>
							</div>
							<div class="col-md-3 col-xs-6">
								<div class="group">
									<input type="text" id="companyName" name="contact.companyName" class="contactFields autocomplete-off"/>
									<span class="highlight"></span><span class="bar"></span>
									<label>Organisation Name<strong class="mndt"></strong></label>
								</div>
								<div class="clearfix"></div>
					    	</div>
							
							
							<div id="address2">
								<div class="col-md-3 col-xs-12">
									<div class="group">
									  <input class="autocomplete-off autocomplete contactFields" type="text"  id="officeAddress" name="contact.contactReport.officeAddress"/> <!-- autocomplete -->
									  <span class="highlight"></span> <span class="bar"></span>
									  <label>Office Location</label>
									</div>
									<div class="clearfix"></div>
								</div>
								
								<div style="display:none;">
									<input class="contactFields" type="text"  id="officelat" name="contact.contactReport.officelat"/>
									<input class="contactFields" type="text"  id="officelng" name="contact.contactReport.officelng"/>
									<input class="contactFields" type="text"  id="reslat" name="contact.contactReport.reslat"/>
									<input class="contactFields" type="text"  id="reslng" name="contact.contactReport.reslng"/>
								</div>
								
								<div class="col-md-2 col-xs-6">
									<div class="group">
										<input class="autocomplete-off contactFields  locality" type="text" id="officeCity" name="contact.contactReport.officeCity"/> <!-- locality -->
										<span class="highlight"></span><span class="bar"></span>
										<label>City</label>
									</div>
									<div class="clearfix"></div>
								</div> 
								<div class="col-md-2 col-xs-6">
									<div class="group">
										<input class="autocomplete-off contactFields postal_code" type="text" maxlength="6" id="officePinCode" name="contact.contactReport.officePincode"/> <!-- postal_code -->
										<span class="highlight"></span><span class="bar"></span>
										<label>Pin Code</label>
									</div>
									<div class="clearfix"></div>
								</div>
							</div>
							
							<div class="clearfix"></div>
						</div>
					
					
						
						
						
						
						
						
						
						
					
					<div class="clearfix"></div>
					
					<!-- Are you accompanied/ referred by a channel partner?  -->
					<div>
						<div class="col-md-12 radioBtnWrp mrgT0">
							<div class="titleF" id="enquirySourceTextDiv">Are you accompanied/ referred by a channel partner? <strong class="mndt">*</strong></div>
							<input type="hidden" name="isReferredByChannelPartner" id="isReferredByChannelPartnerInput" >
							<input type="hidden" id="hiddenEnquiryType" value="${enquiryType}">
							<div class="radioBtnCol" data-toggle="buttons">
							  <label class="btn btn-primary active hideChannelPartnerType enquiryFields" labelName="isReferredByChannelPartner" value="CP" >
								<input type="radio" id="isReferredByChannelPartnerRadioCP" propValue="Partner" class="hideChannelPartnerType" name="isReferredByChannelPartnerFlag" checked value="CP"> Yes
							  </label>
							  <label class="btn btn-primary hideDirectType enquiryFields" labelName="isReferredByChannelPartner" value="D" >
								<input type="radio" id="isReferredByChannelPartnerRadioD" propValue="Direct" class="hideDirectType" name="isReferredByChannelPartnerFlag" value="D"> No
							  </label>
							  <label class="btn btn-primary hideChannelPartnerType enquiryFields" labelName="isReferredByChannelPartner"  value="O" >
								<input type="radio" id="isReferredByChannelPartnerRadioO" propValue="Partner" class="hideChannelPartnerType" name="isReferredByChannelPartnerFlag" value="O"> Other
							  </label>
							</div>
						</div>
						<div class="clearfix"></div>
						<div id="isReferredByChannelPartnerCP" class="sourceCol animated">
							<div class="col-md-6 col-xs-12">
								<div class="group">
									<input type="text" id="channelPartnerNameSearch" onkeyup="getChannelPartners(event,this);" data-id="channelPartnerName" class= "requiredHidden enquiryFields autocomplete-off isReferredByChannelPartnerCP requiredField"  required="required"  /><!--  -->
									<input type="hidden" id="channelPartnerSfid" name="channelPartner.sfid" class="isReferredByChannelPartnerCP"/>
									<input type="hidden" id="channelPartnerId" name="channelPartner.channelPartnerId" class="isReferredByChannelPartnerCP"/>
									
									<input type="hidden"  id="channelPartnerName" name="channelPartner.name" class="isReferredByChannelPartnerCP"/>
									
									<span class="highlight"></span><span class="bar"></span>
									<label>Enter channel partner name <strong class="mndt">*</strong></label>
								</div>
							</div>
	
	
							<div class="col-md-6 col-xs-12" style="display: none">
								<div class="group">
									<input type="hidden" id="brokerContactId" name="brokerContact.contactId" >
									<select class="isReferredByChannelPartnerCP enquiryFields" id="brokerContact"  name="brokerContact.sfid" onchange="brokerContactChanged(event,this);">
										<!-- <option value="" ></option>
										<option value="1">Option 1</option>
										<option value="2">Option 2</option>
										<option value="3">Option 3</option> -->
									</select>
									<span class="highlight"></span><span class="bar"></span>
									<label class="select-label">Select broker contact <!-- <strong class="mndt">*</strong> --></label>
								</div>
							</div>
							<div class="clearfix"></div>
						</div>
						
						<div id="isReferredByChannelPartnerD" class="sourceCol animated" style="display: none;">
							<div class="col-md-6 col-xs-12">
								<div class="group">
									<select class="isReferredByChannelPartnerD requiredField enquiryFields" disabled="disabled" id="walkInSource" name="walkInSource">
									    <option value=""></option>
										<c:forEach var="communcationMediumList" items="${communcationMediumList}">
	
	                                       <option value="${communcationMediumList.code}">${communcationMediumList.name}</option>
	
	                                     </c:forEach>
									</select>
									<span class="highlight"></span><span class="bar"></span>
									<label class="select-label">How did you come to know about this project? <strong class="mndt">*</strong></label>
								</div>
							</div>
							
							<!-- Instracted BY Ganesh -- comment by A -->
							<!-- <div class="col-md-6 col-xs-12">
								<div class="group">
									<input class="autocomplete-off isReferredByChannelPartnerD requiredField enquiryFields" type="text" disabled="disabled" required="required" id="walkInSourceDetail" name="walkInSourceDetail"/>
									<span class="highlight"></span><span class="bar"></span>
									<label>Details of Newspaper, Website, Hoarding etc. <strong class="mndt">*</strong></label>
								</div>
							</div>	 -->
							<div class="clearfix"></div>
						</div>
						
						<div id="isReferredByChannelPartnerO" class="sourceCol animated" style="display: none;">
							<div class="col-md-6 col-xs-12">
								<div class="group">
									<input class="autocomplete-off isReferredByChannelPartnerO requiredField enquiryFields" type="text" required="required" disabled="disabled" name="cpComment" id="otherChannelPartnerName"/>
									<span class="highlight"></span><span class="bar"></span>
									<label>Enter channel partner name <strong class="mndt">*</strong></label>
								</div>
							</div>	
							<div class="clearfix"></div>
						</div>
						<div class="clearfix"></div>
					</div>
					<!-- END Are you accompanied/ referred by a channel partner? --> 
					
					
					
					
					
					<!-- New filed -->
					
					
					<div class="clearfix"></div>
					
					
					
					
					
					
					
					
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
						
						
						
					<div class="formDivider" >
						<div class="col-md-2 col-sm-6 col-xs-12">
							<div class="group">
								<select  id="purpose" name="enquiryReport.purpose" class="enquiryFields">
								            <option value=""></option>									
	                                        <option value="Personal use">Personal use</option>                                    
	                                        <option value="Investment">Investment</option> 
	                             </select>
								<span class="highlight"></span><span class="bar"></span>
								<label class="select-label">Purchase Purpose<strong class="mndt"></strong></label>
							</div>
							<div class="clearfix"></div>
						</div> 
						
						<div class="clearfix"></div>
						<div class="col-md-12 col-sm-12 col-xs-12 radioBtnWrp padMLR15" radioName="desiredUnitType" id="desiredUnitType">
							<div class="titleF">Requirement <strong class="mndt">*</strong></div>
							<div class="radioBtnCol requiredRadio" data-toggle="buttons">
							     <label class='btn btn-primary'> <!-- enquiryFields -->
							          <input type='radio' id='desiredUnitType0' class="desiredUnitType " name='enquiryReport.desiredUnitType' value='1 BHK'>1 BHK</label>
							     <label class='btn btn-primary '> <!-- enquiryFields -->
							          <input type='radio' id='desiredUnitType1' class="desiredUnitType " name='enquiryReport.desiredUnitType' value='2 BHK'>2 BHK</label>
							     <label class='btn btn-primary '> <!-- enquiryFields -->
							          <input type='radio' id='desiredUnitType2' class="desiredUnitType " name='enquiryReport.desiredUnitType' value='3 BHK'>3 BHK</label>
							     <label class='btn btn-primary '> <!-- enquiryFields -->
							          <input type='radio' id='desiredUnitType2' class="desiredUnitType " name='enquiryReport.desiredUnitType' value='4 BHK'>4 BHK</label>
							     
							     <label class='btn btn-primary '> <!-- enquiryFields -->
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
						<!-- Added By A -->
						
						<div class="col-md-6 col-xs-12" style="padding-left: 25px; padding-right: 25px; min-height: 150px; display:none;">
							<label style="margin-left: -10px; margin-bottom: 30px; font-weight:normal">Carpet Area  </label>
							<input style="width:100%" id="ex14"  data-slider-value="900" type="text" name="enquiryReport.carpetAreaRequirement"/>
							<div class="clearfix"></div>
						</div>	
						<div class="col-md-6 col-xs-12" style="padding-left: 25px; padding-right: 25px; min-height: 150px;">
							<label style="margin-left: -10px; margin-bottom: 30px; font-weight:normal">  Budget  </label>
							<input style="width:100%" id="ex13" type="text" name="enquiryReport.budget" data-slider-value="0" data-slider-step="1" />
							<div class="clearfix"></div>
						</div>
						
						
						
						
						<!-- END Added By A -->
						<div class="clearfix"></div>
						
						<input id="budget"  style="display:none;"/>
						<input id="carpetAreaRequirement" style="display:none;"/>
						
						<!-- Don't remove, Below Code replace with slider -testing is pending -->
						<!-- <div class="col-md-3 col-sm-6 col-xs-12">
							<div class="group">
								<select  id="budget" name="enquiryReport.budget" class="requiredField enquiryFields" >
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
								<label class="select-label"> Budget  <strong class="mndt">*</strong></label>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="col-md-3 col-xs-12">
							<div class="group">
								<select id="carpetAreaRequirement" name="enquiryReport.carpetAreaRequirement" class="requiredField enquiryFields">
								        <option value=""></option>
								        <option value="Below 500 sq.ft.">Below 500 sq.ft.</option>
								        <option value="Between 501-600 sq. ft.">Between 501-600 sq. ft.</option>
								        <option value="Between 601-700 sq. ft.">Between 601-700 sq. ft.</option>
								        <option value="Between 701-800 sq. ft">Between 701-800 sq. ft</option>
								        <option value="More than 801">More than 801</option>
								</select>
								<span class="highlight"></span><span class="bar"></span>
								<label class="select-label">Carpet Area <strong class="mndt">*</strong></label>
							</div>
							<div class="clearfix"></div>
						</div> -->
						<!-- END Don't remove, Below Code replace with slider -testing is pending -->
						
						
					</div>
						
					
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
					
					
					
					
					<div ><!-- id="address2" -->
					 
						
						
						
						
						
						
					<!-- Test -->	
					<!-- <div id="address2">
						
						<div class="col-md-4 col-sm-12">
							<div class="group">
							  <input class="autocomplete" type="text" required="required" id="officeAddress" name="contact.companyLocality"/>
							  <span class="highlight"></span>
							  <span class="bar"></span>
							  <label>Office Address</label>
							</div>
						</div>
						
						
						<div class="col-md-2 col-sm-6 col-xs-6">
							<div class="group">
								<input class="locality" type="text" required="required" id="officeCity" name="contact.officeCity"/>
								<span class="highlight"></span><span class="bar"></span>
								<label>City</label>
							</div>
						</div>
						
						<div class="col-md-2 col-sm-6 col-xs-6">
							<div class="group">
								<input class="postal_code" type="text" maxlength="6" id="officePinCode" name="contact.officePinCode"/>
								<span class="highlight"></span><span class="bar"></span>
								<label>Pin Code</label>
							</div>
						</div>
						<div class="clearfix"></div>
					</div> -->
					<!-- END Test -->	
						
						
						
						
						
						
						
						
						 
						
						 
					</div>
					<div class="clearfix"></div>
				</div>
				
				<!-- New EOI Payment Dtl -->
			<div class="col-md-12" style="margin-bottom:50px;">
				<table class="table table-bordered  bgWhite" id="csPtColEoi">
					<tbody>
						<tr class="subHead">
							<th style="width:130px;">
								Payment Type
							</th>
							<th style="width:100px;">
								Bank Name
							</th>
							<th style="width:100px;">
								Branch
							</th>
							<th>
								Transaction ID
							</th>
							<th style="width:100px;">
								Transaction</br>Date
							</th>
							<th>
								Transaction Amount
							</th>
							
							<th style="width:250px;">
								PAN Attachment 
							</th>
							<th style="width:250px;">
								Receipt/</br>Cheque Attachment
							</th>
							
							<th style="width:200px;">
								Description
							</th>
							<th style="width:32px;"></th>
						</tr>
						
						<tr class="csPtDataRowEoi">
							<td>
								<input class="csPtEnqSfidEoi" style="display:none;" />
								<select onchange="csPtDdEoi(this)" class="full form-control input-sm csPtDropDownEoi requiredField">
									<option value="">Select</option>
									<option value="Cheque">Cheque</option>
									<option value="NEFT">NEFT/Credit</option>
									<option value="Swipe">Swipe</option>
									<!--<option value="Online">Online</option>-->
								</select>
							</td>
							<td>
								<input class="full form-control input-sm csPtBankNameEoi requiredField" placeholder="Bank Name"/>
							</td>
							<td>
								<input class="full form-control input-sm csPtBranchEoi requiredField" placeholder="Branch Name"/>
							</td>
							<td>
								<input class="full form-control input-sm csPtTransactionIdEoi requiredField" placeholder="Transaction ID"/>
							</td>
							<td>
								<input type="date" class="full form-control input-sm csPtTransactionDateEoi requiredField" placeholder="Transaction Date"/>
							</td>
							<td>
								<input class="numericField full form-control input-sm csPtTransactionAmountEoi requiredField" onkeyup="csPtcalculateGrandTotalEoi()" name="amount" placeholder="Transaction Amount"/>
							</td>
							
							<td>
								<input type="file" class="full form-control input-sm panAttachEoi requiredField"/>
							</td>
							<td>
								<input type="file" class="numericField full form-control input-sm receiptAttachEoi requiredField"/>
							</td>
							
							<td>
								<textarea class="full form-control input-sm csPtDescriptionEoi" placeholder="Description"></textarea>
							</td>
							<td> </td>
						</tr>
						
					</tbody>
				</table>
				 <!-- <button type='button' onclick="csPymtDataEoi ('99999')">Test Btn</button> -->
				
				<div class="clearfix"></div>
			</div>
				
				<div class="txtCenter">
					<a class="btn btn-primary btnNext" onclick="saveCPBaseInfo(event,this);">
						<span>Submit</span>
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
			
			
			<%-- <div class="tab-pane" id="tab2">
				<form:form modelAttribute="EnquiryRequest" id="enquiryRequestAddressInfoForm" >
				<input type="hidden" class="enquiryId" disabled="disabled" name="enquiryId">
				<!-- <input type="hidden" class="enquirysfid" disabled="disabled" name="sfid"> -->
				<input type="hidden" class="contactId" disabled="disabled" name="contact.contactId">
				<input type="hidden" class="enquiryReportId" name="enquiryReport.enquiryReportId">
				<div class="row "> <!-- bounceInRight animated -->
				
	<!-- Add new field -->
					<div class="col-md-2">
						<div class="group">
							<select class="requiredField" id="requiredPossesionTimeLine" name="requiredPossesionTimeLine">
								<option value=""></option>
									<c:forEach var="possessionTimeLineList" items="${possessionTimeLineList}">

                                       <option value="${possessionTimeLineList.code}">${possessionTimeLineList.name}</option>

                                     </c:forEach>
							</select>
							<span class="highlight"></span><span class="bar"></span>
							<label class="select-label">Possession Timeline<strong class="mndt">*</strong></label>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="col-md-2">
						<div class="group">
							<select class="requiredField" id="budget" name="budget">
								<option value=""></option>
									<c:forEach var="budgetList" items="${budgetList}">
                                        <option value="${budgetList.code}">${budgetList.name}</option>
                                    </c:forEach>
							</select>
							<span class="highlight"></span><span class="bar"></span>
							<label class="select-label">Budget <strong class="mndt">*</strong></label>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="col-md-2">
						<div class="group">
							<select class="requiredField" id="purpose" name="purpose">
								<option value=""></option>
									<c:forEach var="purposeList" items="${purposeList}">
                                        <option value="${purposeList.code}">${purposeList.name}</option>
                                    </c:forEach>
							</select>
							<span class="highlight"></span><span class="bar"></span>
							<label class="select-label">Purpose of purchase <strong class="mndt">*</strong></label>
						</div>
					</div>
					<div class="col-md-2">
						<div class="group">
							<select class="requiredField" id="currentResidence" name="contact.currentResidenceType">
								<option value=""></option>
									<c:forEach var="currentResidenceList" items="${currentResidenceList}">
                                        <option value="${currentResidenceList.code}">${currentResidenceList.name}</option>
                                    </c:forEach>
							</select>
							<span class="highlight"></span><span class="bar"></span>
							<label class="select-label">Current residence<strong class="mndt">*</strong></label>
						</div>
					</div>
					
					<div class="clearfix"></div>
					<div class="col-md-2">
						<div class="group">
							<select class="requiredField" id="occupation" name="contact.occupation">
								<option value=""></option>
									<c:forEach var="occupationList" items="${occupationList}">
                                        <option value="${occupationList.code}">${occupationList.name}</option>
                                    </c:forEach>
							</select>
							<span class="highlight"></span><span class="bar"></span>
							<label class="select-label">Occupation  <strong class="mndt">*</strong></label>
						</div>
					</div>
					
					<div class="col-md-2">
						<div class="group">
							<!-- <select class="" id="designation" name="contact.designation">
								<option></option>
							</select>
							<span class="highlight"></span><span class="bar"></span>
							<label class="select-label">Designation <strong class="mndt">*</strong></label> -->
							
							<input type="text" id="designation" class="" name="contact.designation"/>
							<span class="highlight"></span><span class="bar"></span>
							<label>Designation</label>
						</div>
					</div>
					
					<div id="address2">
						
						<div class="col-md-4 col-sm-12">
							<div class="group">
							  <input class="autocomplete" type="text" required="required" id="officeAddress" name="contact.companyLocality"/>
							  <span class="highlight"></span>
							  <span class="bar"></span>
							  <label>Office Address</label>
							</div>
						</div>
						
						
						<div class="col-md-2 col-sm-6 col-xs-6">
							<div class="group">
								<input class="locality" type="text" required="required" id="officeCity" name="contact.officeCity"/>
								<span class="highlight"></span><span class="bar"></span>
								<label>City</label>
							</div>
						</div>
						
						<div class="col-md-2 col-sm-6 col-xs-6">
							<div class="group">
								<input class="postal_code" type="text" maxlength="6" id="officePinCode" name="contact.officePinCode"/>
								<span class="highlight"></span><span class="bar"></span>
								<label>Pin Code</label>
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
					
					<!-- New fields -->
					<div class="" style="border-top: 1px solid #ddd; padding-top: 25px;">
						 
						
						 <div class="col-md-6 radioBtnWrp mrgT0">
						  <div class="titleF">Customer Gender<strong class="mndt"></strong></div>
							<div class="radioBtnCol" data-toggle="buttons">
							  <label class="btn btn-primary active">
								<input type="radio" id="genderMale" class="customerGender" name="enquiryReport.gender" checked value="Male">Male
							  </label>
							  <label class="btn btn-primary">
								<input type="radio" id="genderFemale" class="customerGender" name="enquiryReport.gender" value="Female">Female
							  </label>
							  <label class="btn btn-primary">
								<input type="radio" id="genderOther"  class="customerGender" name="enquiryReport.gender" value="Other">Other
							  </label>
							</div>
						</div>
						 <div class="col-md-4 radioBtnWrp mrgT0">
						  <div class="titleF">Customer Classification<strong class="mndt"></strong></div>
							<div class="radioBtnCol" data-toggle="buttons">
							  <label class="btn btn-primary active">
								<input type="radio" id="classificationHot" class="customerClassification" name="enquiryReport.customerClassification" checked value="Hot">Hot
							  </label>
							  <label class="btn btn-primary">
								<input type="radio" id="classificationWarm" class="customerClassification" name="enquiryReport.customerClassification" value="Warm">Warm
							  </label>
							  <label class="btn btn-primary">
								<input type="radio" id="classificationCold"  class="customerClassification" name="enquiryReport.customerClassification" value="Cold">Cold
							  </label>
							</div>
						</div>
						
						<div class="col-md-2 ethnicity" style="margin-top: 13px;">
							<div class="group" style="min-height: 50px;">
								<select  id="ethnicity" name="enquiryReport.ethnicity">
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
						
						<div class="col-md-6 radioBtnWrp mrgT0">
						  <div class="titleF">Vastu Preference<strong class="mndt"></strong></div>
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
						</div>
						<div class="col-md-6 radioBtnWrp mrgT0">
						  <div class="titleF">Preferred Unit Availability<strong class="mndt"></strong></div>
							<div class="radioBtnCol" data-toggle="buttons">
							  <label class="btn btn-primary active">
								<input type="radio" id="unitAvailabilityY"  class="unitAvailability"name="enquiryReport.unitAvailability" checked value="Yes">Yes
							  </label>
							  <label class="btn btn-primary">
								<input type="radio" id="unitAvailabilityN" class="unitAvailability"name="enquiryReport.unitAvailability" value="No">No
							  </label>
							</div>
						</div>
						<div class="col-md-6 radioBtnWrp mrgT0">
						  <div class="titleF">Accompanied By<strong class="mndt"></strong></div>
							<div class="radioBtnCol" data-toggle="buttons">
							  <label class="btn btn-primary active">
								<input type="radio" id="accompaniedByS" class="accompaniedBy" name="enquiryReport.accompaniedBy" checked value="Self">Self
							  </label>
							  <label class="btn btn-primary">
								<input type="radio" id="accompaniedByFy" class="accompaniedBy"name="enquiryReport.accompaniedBy" value="Family">Family
							  </label>
							  <label class="btn btn-primary">
								<input type="radio" id="accompaniedByFd" class="accompaniedBy" name="enquiryReport.accompaniedBy" value="Friends">Friends
							  </label>
							</div>
						</div>
						<div class="col-md-6 radioBtnWrp mrgT0">
						  <div class="titleF">Deal Negotiation<strong class="mndt"></strong></div>
							<div class="radioBtnCol" data-toggle="buttons">
							  <label class="btn btn-primary active">
								<input type="radio" id="dealNegotiationS" class="dealNegotiation"  name="enquiryReport.dealNegotiation" checked value="Skipped">Skipped
							  </label>
							  <label class="btn btn-primary">
								<input type="radio" id="dealNegotiationUW" class="dealNegotiation"name="enquiryReport.dealNegotiation" value="Underway">Underway
							  </label>
							 
							</div>
						</div>
						<div class="col-md-12 radioBtnWrp mrgT0">
						  <div class="titleF">Preferred construction status / Possession Timeline<strong class="mndt"></strong></div>
							<div class="radioBtnCol" data-toggle="buttons">
							  <label class="btn btn-primary active">
								<input type="radio" id="constructionStatusUC" class="constructionStatus" name="enquiryReport.constructionStatus" checked value="Under Construction">Under Construction
							  </label>
							  <label class="btn btn-primary">
								<input type="radio" id="constructionStatusNC" class="constructionStatus" name="enquiryReport.constructionStatus" value="Nearing Completion">Nearing Completion
							  </label>
							  <label class="btn btn-primary">
								<input type="radio" id="constructionStatusRP" class="constructionStatus" name="enquiryReport.constructionStatus" value="Ready Possession">Ready Possession
							  </label>
							 
							</div>
						</div>
						<div class="col-md-12 radioBtnWrp mrgT0">
						  <div class="titleF">Timeframe to Book<strong class="mndt"></strong></div>
							<div class="radioBtnCol" data-toggle="buttons">
							  <label class="btn btn-primary active">
								<input type="radio" id="timeFrameToBook0" class="timeframeToBook" name="enquiryReport.timeframeToBook" checked value="Less than 7days">Less than 7days
							  </label>
							  <label class="btn btn-primary">
								<input type="radio" id="timeFrameToBook1" class="timeframeToBook" name="enquiryReport.timeframeToBook" value="Between 8-15 days">Between 8-15 days
							  </label>
							  <label class="btn btn-primary">
								<input type="radio" id="timeFrameToBook2" class="timeframeToBook" name="enquiryReport.timeframeToBook" value="Between 16-30 days">Between 16-30 days
							  </label>
							 <label class="btn btn-primary">
								<input type="radio" id="timeFrameToBookM3" class="timeframeToBook" name="enquiryReport.timeframeToBook" value="Between 16-30 days">More than 30 days
							  </label>
							</div>
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
						<a class="btn btn-primary btnPrevious">
							<span>Previous</span>
						</a> 
						<a class="btn btn-primary btnNext" onclick="saveAddressInfo(event,this);">
							<span>Save & Continue</span>
						</a>					
						<div class="clearfix"></div>
					</div>
					<div class="clearfix"></div>
				</div>
					</form:form>
			</div>
 --%>						
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
        <span id="notSelectError" style="color: red ;display: none; float:left">Please select enquiry..</span>
        <!-- <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> -->
        <button type="button" class="btn btn-primary blue_btn" data-dismiss="modal" onclick="populateEnquiry(event,this);">Save changes</button>
        <input type="hidden" value="15">
      </div>
    </div>
  </div>
</div>
	
	
	
	
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    
    
    <script src="<c:url value='/resources/js/jquery-1.12.4.min.js' />"></script>
    <script src="<c:url value='/resources/js/bootstrap.min.js' />"  ></script>
	<script src="<c:url value='/resources/js/anypicker.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery.touchSwipe.min.js' />"></script>
	<link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel = "stylesheet">
    <script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    
    <script src="<c:url value='/resources/js/bootstrap-slider.min.js' />"></script>
    
	<script src="<c:url value='/resources/js/springForm.js?v=18' />"></script>
	<script src="<c:url value='/resources/js/commonValidation.js?v=18' />"></script>
	<script src="<c:url value='/resources/js/utility.js?v=18' />"></script>
	<script src="<c:url value='/resources/js/enquiryRequest/enquiryCPRequest.js?v=18' />" /></script>
	<%-- <script src="<c:url value='/resources/js/enquiryRequest/salesRequest.js?v=18' />"></script> --%>
	
    <script src="<c:url value='/resources/js/intlTelInput.js' />"></script>
    <script src="<c:url value='/resources/js/sweetalert2.min.js' />"></script>
  	<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/8.4.7/js/intlTelInput.js"></script> -->
	
	<script src="<c:url value='/resources/js/index.js?v=18' />"></script>
	<script src="<c:url value='/resources/js/separate/storeEOIPaymentDtlCP.js?v=18' />"></script>
	
	
	<script>
	
		var rcpNO = $("#isReferredByChannelPartnerD").removeClass("shake");
		var rcpYes = $("#isReferredByChannelPartnerCP").removeClass("shake");
		
		/* $('.btnNext').click(function(){
		  $('.nav-tabs > .active').next('li').find('a').trigger('click');
		  
		 
		}); */

		 $('.btnPrevious').click(function(){
		  $('.nav-tabs > .active').prev('li').find('a').trigger('click');
		});
		
		
		if ($(window).width() > 960) {
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
		}
		//created by Hassan Rana
		//hassanrana300@gmal.com
		
		$("label[labelName$='isReferredByChannelPartner']").click(function() {			
			debugger;
			var cpHS = $(this).attr('value');		
			isReferredChanged(cpHS);			
			//$("#cp" + cpHS).addClass("shake");						
		});
	</script>
	
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCGiIJnQmPDp3qoLF0j6xt8msWAD-7FC2I&libraries=places&callback=initAutocomplete" async defer></script>
	
	<!-- <script src="https://rawgit.com/seiyria/bootstrap-slider/master/dist/bootstrap-slider.min.js"></script> -->
		
	
	
	
	<script src="<c:url value='/resources/js/separate/rangeSlider.js' />"></script>
	<!-- 
	<script>
		//enqSlider();
		</script> -->		
  </body>
</html>
