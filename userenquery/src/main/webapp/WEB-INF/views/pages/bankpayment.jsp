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
    <link  rel="stylesheet" type="text/css" href="<c:url value='/resources/css/bootstrap-datetimepicker.min.css' />"/>
  
  </head>
  <body>
   
   
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
	        
	      </ul> 
	    </div>
	  </div>
	</nav>
	<div class="commonLoad" id="mainPageLoad" style="display:none;"></div>
                <input type="hidden" id="contextPath" value="${pageContext.request.contextPath}">
				<input type="hidden" id="hasParam" value="${hasParam}">
				<input type="hidden" id="token" value="${token}">
				<input type="hidden" id="recordTypeProspect" value="${recordTypeProspect}">
			    <input type="hidden" id="recordTypeCustomer" value="${recordTypeCustomer}">
	<jsp:include page="commonBankPayment.jsp"></jsp:include>
	
	<%-- <div class="container formCol swipeCard posRelative">
			
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
				<form:form  id="bankPaymentForm" modelAttribute="EnquiryRequest">
					
				<input type="hidden" class="enquiryId" name="enquiryId" value="2269368">
				<input type="hidden" class="enquiryReportId" name="enquiryReport.enquiryReportId">
				
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
					  <div class="col-md-2 col-xs-6">
						<div class="group">
							<select id="paymentopt"  class="" name="transactionType">
							        <option value=""></option>
							        <option value="Bank">Bank</option>
							        <option value="NEFT">NEFT/Swap</option>
							        <option value="Online">Online</option>
							</select>
							<span class="highlight"></span><span class="bar"></span>
							<label class="select-label">Payment type <strong class="mndt"></strong></label>
						</div>
					</div>
					<div class="clearfix"></div>
					<div id="bankdetails" style="display: none"> 
					<div class="col-md-3 col-sm-6 col-xs-12">
						<div class="group">
							<input type="text" id="eoiBankName" name="eoiBankName" class="autocomplete-off bankdetails requiredField" required="required" />
							<span class="highlight"></span><span class="bar"></span>
							<label>Bank name <strong class="mndt">*</strong></label>
							<!-- <small class="errorMsg">Error message</small> -->
						</div>
					</div>
					
					<div class="col-md-3 col-sm-6 col-xs-12">
						<div class="group">
							<input type="text" id="branch" name="branch" class="autocomplete-off bankdetails requiredField" required="required" />
							<span class="highlight"></span><span class="bar"></span>
							<label>Branch<strong class="mndt">*</strong></label>
						</div>
					</div>
					
				 
					<div class="col-md-3 col-sm-6 col-xs-12">
						<div class="group">
							<input type="text" id="chequeno" name="micRChequeNoNEFTRTGS"  class="autocomplete-off  bankdetails requiredField" />
							<span class="highlight"></span><span class="bar"></span>
							<label>Cheque No<strong class="mndt">*</strong></label>
						</div>
					</div>
					
					<div class="col-md-3 col-sm-6 col-xs-12">
						<div class="group">
							<input type="text" id="chequedate" name="" class="ip-de autocomplete-off  bankdetails " /><!-- transactionDate -->
							<span class="highlight"></span><span class="bar"></span>
							<label>Cheque Date<strong class="mndt">*</strong></label>
						</div>
					</div>
					<div class="col-md-3 col-sm-6 col-xs-12">
						<div class="group">
							<input type="text" id="amount" name="transactionAmount" class="autocomplete-off   bankdetails requiredField" />
							<span class="highlight"></span><span class="bar"></span>
							<label>Amount<strong class="mndt">*</strong></label>
						</div>
					</div>
					
					<!-- <div class="col-md-3 col-sm-6 col-xs-12">
						<div class="group">
							<input type="text" id="panno" class="autocomplete-off requiredField  " />
							<span class="highlight"></span><span class="bar"></span>
							<label>Pan No<strong class="mndt">*</strong></label>
						</div>
					</div> -->
					<div class="col-md-3 col-sm-6 col-xs-12">
						<div class="group">
							<input type="file" id="file" class="autocomplete-off  bankdetails " />
							<span class="highlight"></span><span class="bar"></span>
							<label>Cheque Image<strong class="mndt">*</strong></label>
						</div>
					</div>
					
					<div class="col-md-3 col-sm-6 col-xs-12">
						<div class="group">
							<input type="file" id="file" class="autocomplete-off  bankdetails " />
							<span class="highlight"></span><span class="bar"></span>
							<label>Upload Pan Image<strong class="mndt">*</strong></label>
						</div>
					</div>
					<div class="clearfix"></div>
					<div class="txtCenter">
						<a class="btn btn-primary btnNext" onclick="savePaymentInfo(event,this);">
							<span>Submit</span>
						</a>
						<div class="clearfix"></div>
				</div>
				</div>
					
					<div class="clearfix"></div>
				<div id="neftdetails" style="display: none"> 
				
					<div class="col-md-3 col-sm-6 col-xs-6">
						<div class="group">
							<input type="text" id="transId" name="transactionID" class="autocomplete-off neftdetails requiredField" required="required" />
							<span class="highlight"></span><span class="bar"></span>
							<label>Transaction ID<strong class="mndt">*</strong></label>
						</div>
					</div>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<div class="group">
							<input id="transDate" name="" class="autocomplete-off neftdetails " required="required" />
							<span class="highlight"></span><span class="bar"></span>
							<label>Transaction Date<strong class="mndt">*</strong></label>
						</div>
					</div>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<div class="group">
							<input type="text" id="transamt" name="transactionAmount" class="autocomplete-off neftdetails requiredField" required="required" />
							<span class="highlight"></span><span class="bar"></span>
							<label>Transaction Amount<strong class="mndt">*</strong></label>
						</div>
					</div>
				<div class="col-md-3 col-sm-6 col-xs-12">
				
						<div class="group">
							<input type="file" id="file" class="autocomplete-off  neftdetails " />
							<span class="highlight"></span><span class="bar"></span>
							<label>NEFT/Swap Image<strong class="mndt">*</strong></label>
						</div>
					</div>
					<div class="clearfix"></div>
					<div class="txtCenter">
						<a class="btn btn-primary btnNext" onclick="savePaymentInfo(event,this);">
							<span>Submit</span>
						</a>
						<div class="clearfix"></div>
				</div>
				</div>	
				<div class="clearfix"></div>
				
				
				
				<div id="onlinedetails" style="display: none"> 
				
					<div class="col-md-3 col-sm-6 col-xs-12">
				
						<div class="group">
							<a class="btn btn-primary btnNext" >
							<span>Pay Now</span>
						</a>
						</div>
					</div>
					<div class="col-md-3 col-sm-6 col-xs-12">
				
						<div class="group">
							<a class="btn btn-primary btnNext" >
							<span>Generate Link For Payment</span>
						</a>
						</div>
					</div>
						<div class="clearfix"></div>
				</div>
				</div>
					 
				
			  
				</form:form>
			</div>
			
			
		 				
		</div>
	<div class="clearfix"></div>
	</div>  --%>
	
	
	
	
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    
    
    <script src="<c:url value='/resources/js/jquery-1.12.4.min.js' />"></script>
    <script src="<c:url value='/resources/js/bootstrap.min.js' />"  ></script>
	<script src="<c:url value='/resources/js/anypicker.js?v=15'/>"></script>
	<script src="<c:url value='/resources/js/jquery.touchSwipe.min.js' />"></script>
	<link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel = "stylesheet">
    <script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <script src="<c:url value='/resources/js/bankdetails.js?v=15' />"></script>
	<script src="<c:url value='/resources/js/springForm.js?v=15' />"></script>
	<script src="<c:url value='/resources/js/commonValidation.js?v=15' />"></script>
	<script src="<c:url value='/resources/js/utility.js?v=15' />"></script> 
	<%-- <script src="<c:url value='/resources/js/enquiryRequest/salesRequest.js' />"></script> --%>
	
    <script src="<c:url value='/resources/js/intlTelInput.js?v=15' />"></script>
    <script src="<c:url value='/resources/js/sweetalert2.min.js' />"></script>
    <!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/8.4.7/js/intlTelInput.js"></script> -->
	<script src="<c:url value='/resources/js/moment.js' />"></script>
    <script src="<c:url value='/resources/js/bootstrap-datetimepicker.min.js' />"></script>  
	 <script>
	 $(function () {                
	     $('.datetimepicker').datetimepicker({
	         format: 'DD-MM-YYYY'
	   });
	 });
	
	
	
	<script>
	
		
		
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
		
	
	</script>
	
 
		
		
	
		
		
  </body>
</html>
 
 
  