<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
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
    <!-- Bootstrap -->
    <link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css' />">
	<link rel="stylesheet" href="<c:url value='/resources/css/inner.css?v=3.0' />">
	<link rel="stylesheet" href="<c:url value='/resources/css/style.css?v=3.0' />">
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
	    <%-- <div class="navbar-header">
      	<a class="navbar-brand" href="#">
			<img class="topLogo" src="<c:url value='/resources/images/gplLogo.jpg' />"/>
		</a>
	    </div>


		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">


			<ul class="nav navbar-nav navbar-right">
				<li><a href="${pageContext.request.contextPath}/saleslogin">Logout</a></li>
			</ul>
		</div> --%>
		 <%@ include file="/WEB-INF/views/pages/header.jsp" %>
	</div>
	</nav>
   
	
	<div class="commonLoad" id="mainPageLoad" style="display:none;"></div>
	<div class="container formCol swipeCard posRelative">
			
			
			
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
			    <input type="hidden" id="etokentaballow" value="${etokenTab}">
		
		<%-- <div class="filterColBg">
			<div class="filterCol">

			<div class="col-md-3 col-sm-6 col-xs-12">
				<div class="group">
					<div class="input-group" id="contactDiv">
						<input type="hidden" id="hiddenMobileNo" value="${mobileNo}">
						<input type="text" value="${countryCode}"
							class="autocomplete-off form-control mobile requiredField contactNoDiv"
							id="enMobileNo" name="enMobileNo" tabindex="-1"><!-- maxlength="10" -->
						 <span class="highlight"></span><span class="bar"></span> <label>Contact
							no.<strong class="mndt">*</strong>
						</label>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
			
				<div class="form-group col-md-2" id="otpInputColDiv">
					<div class="group" style="min-height: auto; margin-bottom: 0;">
						<div class="input-group" id="otpInputDiv">
							<button class="btn blue_btn square_btn otpInput_btn_Div" onclick="generateOTP(1);">
								<span>Access Code</span>
							</button>
						</div>
						
					</div>
				</div>
				<div class="form-group col-md-2" id="generateOTPColDiv">
					<div class="group" style="min-height: auto; margin-bottom: 0;">
						<div class="input-group" id="generateOTPDiv">
							<span class="validBox">	
								<input name="otp1" class="otpInput form-control" autocomplete="off" placeholder="Access Code."  onkeyup="otpvalidate(this)">
							</span>
						<label class="errorOTP"></label>
						</div>
					</div>
				</div>
				
				<!-- <div class="form-group col-md-2">
					<div class="group" style="min-height: auto; margin-bottom: 0;">
						<div class="input-group" id="generateOTPDiv">
							<button class="btn blue_btn" id="getGenerateOTP">Generate OTP</button>
						</div>
					</div>
				</div> -->
				<div class="form-group col-md-2" id="getEnquiry_search_btn" style="display: none">
					<div class="group" style="min-height: auto; margin-bottom: 0;">
						<div class="input-group" id="contactInputDiv">
							<button class="btn blue_btn getEnquiry_btn" id="getEnquiry">Search</button>
						</div>
					</div>
				</div>
				<div class="form-group col-md-2" id="reset_btn_ColDiv" style="display: none;">
					<div class="group" style="min-height: auto; margin-bottom: 0;">
						<div class="input-group" id="otpInputResetDiv">
							<button class="btn blue_btn square_btn" onclick="resetOfflineEnq(this);">
								<span>Reset</span>
							</button>
						</div>
						
					</div>
				</div>


				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
		</div> --%>
		
		<div class="clearfix"></div>
		<!-- <div id="tab_offline_enq"> style="display: none" -->
		<div>
		
			<div class="clearfix"></div>
			<ul class="nav nav-tabs tabNav">

				<li id="" class="active"><!-- tab1_offline_enq -->
					<a href="#tab1" data-toggle="tab">Enquiry</a>
				</li>
				
				<li id="" onclick="getTowerEOI()" style="display:none;">
					<a href="#tab2" data-toggle="tab">EOI</a>
				</li>
				<li class="" id="etokentabli" onclick="onclickGenerateEToken()" style="display:none;">
					<a href="#tab3" data-toggle="tab">Generate E-Token</a>
				</li>
			</ul>
			<div class=""></div>
		</div>
		
		
		
		<div class="tab-content formTabCont">
			
			
			<div class="tab-pane active" id="tab1">
			
			<div class="filterColBg">
			<div class="filterCol">

			<div class="col-md-3 col-sm-6 col-xs-12">
				<div class="group">
					<div class="input-group" id="contactDiv">
						<input type="hidden" id="hiddenMobileNo" value="${mobileNo}">
						<input type="text" value="${countryCode}"
							class="autocomplete-off form-control mobile requiredField contactNoDiv"
							id="enMobileNo" name="enMobileNo" tabindex="-1"><!-- maxlength="10" -->
						 <span class="highlight"></span><span class="bar"></span> <label>Contact
							no.<strong class="mndt">*</strong>
						</label>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
			
			
				<div class="form-group col-md-2" id="otpInputColDiv">
					<div class="group" style="min-height: auto; margin-bottom: 0;">
						<div class="input-group" id="otpInputDiv">
							<button class="btn blue_btn square_btn otpInput_btn_Div" onclick="generateOTP(1);">
								<span>Access Code</span>
							</button>
						</div>
						
					</div>
				</div>
				<div class="form-group col-md-2" id="generateOTPColDiv">
					<div class="group" style="min-height: auto; margin-bottom: 0;">
						<div class="input-group" id="generateOTPDiv">
							<span class="validBox">	
								<input name="otp1" class="otpInput form-control" autocomplete="off" placeholder="Access Code."  onkeyup="otpvalidate(this)">
							</span>
						<label class="errorOTP"></label>
						</div>
					</div>
				</div>
				
				<!-- <div class="form-group col-md-2">
					<div class="group" style="min-height: auto; margin-bottom: 0;">
						<div class="input-group" id="generateOTPDiv">
							<button class="btn blue_btn" id="getGenerateOTP">Generate OTP</button>
						</div>
					</div>
				</div> -->
				<div class="form-group col-md-2" id="getEnquiry_search_btn" style="display: none">
					<div class="group" style="min-height: auto; margin-bottom: 0;">
						<div class="input-group" id="contactInputDiv">
							<button class="btn blue_btn getEnquiry_btn" id="getEnquiry">Search</button>
						</div>
					</div>
				</div>
				<div class="form-group col-md-2" id="reset_btn_ColDiv" style="display: none;">
					<div class="group" style="min-height: auto; margin-bottom: 0;">
						<div class="input-group" id="otpInputResetDiv">
							<button class="btn blue_btn square_btn" onclick="resetOfflineEnq(this);">
								<span>Reset</span>
							</button>
						</div>
						
					</div>
				</div>


				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
		</div>
		<div id="tab_offline_enq" style="display: none">
				<form:form modelAttribute="EnquiryRequest" id="enquiryRequestBasicInfoForm">

				<input type="hidden" value="${projectName}" id="projectName" name="projectName">
				<input type="hidden" value="${AssignTO}" id="assignTO" name="assignTo">
				<input type="hidden" class="enquiryId" disabled="disabled" name="enquiryId">
				<input type="hidden" class="contactId" disabled="disabled" name="contact.contactId">
				<input type="hidden" name="projectType" id="projectType" value="Residential">
				<input type="hidden" name="enquirySource" id="enquirySource" value="Walk-in">
				<input type="hidden" name="enquiryRating" value="Hot" id="enquiryRating">
				<input type="hidden" value="${projectSfid}" name="project.sfid" id="projectSfid" class="projectSfid">
				<input type="hidden" value="${userId}" name="logginUserId" id="logginUserId" class="logginUserId">
				<input type="hidden" value="${roleId}" name="logginRoleId" id="logginRoleId" class="logginRoleId">
				<input type="hidden" value="${countryCode}" name="contact.countryCode" id="countryCode" ><!--  +91-->
				<input type="hidden" value="1" name="project.projectId" id="projectId">
				<input type="hidden" value="" name="tokenId" id="tokenId"/>
				<input type="hidden" value="" name="tokenNo" id="tokenNo"/> 
				<input type="hidden" value="${projectSfid}" name="contact.contactReport.projectId" id="contact_projectId"> 
				<input type="hidden" value="Virtual Meeting Done" name="enquiryStatus" id="enquiryStatus">
				<input type="hidden" class="enquiryReportId" name="enquiryReport.enquiryReportId">
				<input type="hidden" class="enquiryprojectidId" name="enquiryReport.projectId"  value="${projectSfid}">
				<input type="hidden" class="contactReportId" name="contact.contactReport.contactReportId">
				<input type="hidden" class="is_revisit" name="enquiryReport.is_revisit">
				<div class="row " style="padding-top: 10px;"> <!-- bounceInLeft animated -->
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
			<div class="col-md-3 col-sm-6 col-xs-12" id="hdrEnqNameoffline_div">
				<div class="group">
					<div class="input-group" id="">
						<input type="text"
							class="autocomplete-off form-control "
							id="hdrEnqNameoffline" disabled="disabled">
						 <span class="highlight"></span><span class="bar"></span> <label>Enquiry
							no.
						</label>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
			
			<div class="col-md-3 col-sm-6 col-xs-12">
				<div class="group">
					<div class="input-group" id="contactDiv">
						<input type="text" value="${countryCode}"
							class="autocomplete-off form-control mobile requiredField readonly contactNoDiv"
							id="inputMobileNo" name="contact.mobileNo" tabindex="-1" readonly>
						 <span class="highlight"></span><span class="bar"></span> <label>Contact
							no.<strong class="mndt">*</strong>
						</label>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<div class="group">
							<input type="text" id="firstName" class="contactFields autocomplete-off requiredField" required="required" name="contact.firstName"/>
							<span class="highlight"></span><span class="bar"></span>
							<label>First name <strong class="mndt">*</strong></label>
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
					
									<div class="form-group col-md-2">
					<div class="group" style="min-height: auto; margin-bottom: 0;">
						<div class="input-group" id="contactInputDiv">
							<select id="visitType" name="enquiryReport.visitType" class="form-control"
								style="background-color: #fff;">
								<c:forEach var="visitType" items="${visitTypes}">
									<option value="${visitType.code}">${visitType.name}</option>
								</c:forEach>
							</select> <span class="highlight"></span><span class="bar"></span> 
							<label>Visit Type</label>
						</div>
					</div>
				</div>
					
					
					<div class="col-md-2 radioBtnWrp mrgT0" style="margin-top:-16px !important;">
						  <div class="titleF">Have you visited site before <strong class="mndt">*</strong>
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
								</select>
								<span class="highlight"></span><span class="bar"></span>
								
								<label class="select-label">Age Group <strong class="mndt"></strong></label>
								<div class="clearfix"></div>
							</div>
							<div class="clearfix"></div>
						</div>
						
						
						
						
						<div class="clearfix"></div>
						
						
						
						
						<div id="address" style="padding-top: 45px; margin-bottom: 40px; border-top: 1px solid #ccc; border-bottom: 1px solid #ccc;">
						
												
						<div class="col-md-4 col-sm-12">
							<div class="group">
							  <input class="autocomplete-off autocomplete contactFields requiredField" required="required" type="text" id="addressLine2" name="contact.addressLine2" />
							  <span class="highlight"></span>
							  <span class="bar"></span>
							  <label>Residence Location <strong class="mndt"> *</strong></label>
							</div>
						</div>
						
						
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
								<div class="group posRelative">
									<div class="commonLoad" id="channelPartnerLoader" style="display:none;"></div>
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
									</select>
									<span class="highlight"></span><span class="bar"></span>
									<label class="select-label">Select broker contact </label>
								</div>
							</div>
							<div class="clearfix"></div>
						</div>
						
						<div id="isReferredByChannelPartnerD" class="sourceCol animated" style="display: none;">
							<div class="col-md-6 col-xs-12">
								<div class="group">
									<select class="isReferredByChannelPartnerD requiredField enquiryFields" disabled="disabled" id="walkInSource" name="walkInSource" onchange="onSelectWalkinSrcReferral(event,this);">
									    <option value=""></option>
										<c:forEach var="communcationMediumList" items="${communcationMediumList}">
	
	                                       <option value="${communcationMediumList.code}">${communcationMediumList.name}</option>
	
	                                     </c:forEach>
									</select>
									<span class="highlight"></span><span class="bar"></span>
									<label class="select-label">How did you come to know about this project? <strong class="mndt">*</strong></label>
								</div>
							</div>
							<!--  Referred by added on Enquiry page, on select of walk-in source as referral -  
     						* Change By Satheesh Kyatham- 25-12-2019
     						* Request From - Prakash Idnani -->
     						<!-- Start -->
							<div class="referred_by_name ccol-md-3 col-xs-6">
								<div class="group">
									<input type="text" class="autocomplete-off" id="referredbyId" name="enquiryReport.referredby"/>
									<span class="highlight"></span><span class="bar"></span>
									<label>Referred by</label>
								</div>
							</div>
							<!-- END -->
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
							<div class="group" id="lastvisitdate_div" style="display:none"><!--  -->
									<input type="text" class="autocomplete-off disableInputs" id="lastvisitdate" /><!-- name="enquiryReport.lastvisitdate" -->
									<span class="highlight"></span><span class="bar"></span>
									<label>Last Visit Date</label>
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
		 
							</div>

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
												
						
					</div>
						
						<!-- END New filed -->
					
						<div class="clearfix"></div>
					</div>
					
					<div class="clearfix"></div>
					
					<div > 
					</div>
					<div class="clearfix"></div>
				</div>
				
				<div class="txtCenter">
					<a class="btn btn-primary btnNext" onclick="saveBaseInfo(event,this);">
						<span>Submit</span>
					</a>
					
					<a class="btn btn-primary btnNext" onclick="openClosingMDashboard(event,this);">
						<span>Open Dashboard</span>
					</a>
					<div class="clearfix"></div>
				</div>
				</form:form>
			</div>
			
					
		</div>
		<div class="tab-pane" id="tab2" style="display: none">
			<%@ include file="/WEB-INF/views/pages/offlineeoi/eoiForm.jsp" %>
		</div>
		<div class="tab-pane" id="tab3">
			<%@ include file="/WEB-INF/views/pages/etokengenerate.jsp" %>
		</div>	
	</div>
	<div class="clearfix"></div>
	</div>

<!-- Modal -->
<div class="modal fade" id="multiEnq" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
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
    
	<script src="<c:url value='/resources/js/springForm.js?v=18.80' />"></script>
	<script src="<c:url value='/resources/js/commonValidation.js?v=18.80' />"></script>
	<script src="<c:url value='/resources/js/utility.js?v=18.26' />"></script>
	<script src="<c:url value='/resources/js/offline/enquiry.js?v=18.80' />"></script>	
    <script src="<c:url value='/resources/js/intlTelInputCustom.js?v=18.26' />"></script>
    <script src="<c:url value='/resources/js/sweetalert2.min.js' />"></script>
	
	<script src="<c:url value='/resources/js/index.js?v=18.80' />"></script>
	
	<script src="<c:url value='/resources/js/offline/offlineEOI.js?v=18.80'/>"></script>
	<script src="<c:url value='/resources/js/separate/etokengenerate.js' />"></script>

	
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
	
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD6tHZRohy-YmfuSL_TuvYpLt33w4THz1M&libraries=places&callback=initAutocomplete" async defer></script>		
	<script src="<c:url value='/resources/js/separate/rangeSlider.js' />"></script>
	
	<%@ include file="/WEB-INF/views/pages/footer.jsp" %>
  </body>
</html>
