<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<head>
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
		<title> Godrej </title>
		<meta name="description">
		 


	    <link rel="stylesheet" href="<c:url value='/resources/css/style.css' />">
	    <link href="<c:url value='/resources/css/animate.min.css' />" rel="stylesheet">
		<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css' />" type="text/css" />
	    <link rel="stylesheet" href="<c:url value='/resources/css/sweetalert2.min.css' />">
	    <%-- <link rel="stylesheet" type="text/css"  href="<c:url value='/resources/css/intlTelInput.css' />" /> --%>
		<link href="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/8.4.6/css/intlTelInput.css" rel="stylesheet">
		<!-- By a -->
		<link rel="stylesheet" href="<c:url value='/resources/css/inner.css' />" type="text/css" />
		<!--<link rel="stylesheet" href="css/common.css" type="text/css" /> -->
		<script src="<c:url value='/resources/js/jquery-1.12.4.min.js'/>"></script>
		<script src="<c:url value='/resources/js/enquiryRequest/salesSearch.js' />"></script>
		<script src="<c:url value='/resources/js/sweetalert2.min.js' />"></script>
		<script src="<c:url value='/resources/js/springForm.js' />"></script>
	    <script src="<c:url value='/resources/js/commonValidation.js' />"></script>
	    <script src="<c:url value='/resources/js/utility.js' />"></script>
	    <%-- <script src="<c:url value='/resources/js/intlTelInput.js' />"></script> --%>
	    <%-- <script src="<c:url value='/resources/js/index.js' />"></script> --%>
	    	<style>
			.intl-tel-input {
			  display: table-cell;
			}
			.intl-tel-input .selected-flag {
			  z-index: 4;
			}
			.intl-tel-input .country-list {
			  z-index: 5;
			}
			.input-group .intl-tel-input .form-control {
			  border-top-left-radius: 4px;
			  border-top-right-radius: 0;
			  border-bottom-left-radius: 4px;
			  border-bottom-right-radius: 0;
			}
			
			#token {margin-bottom:20px !important;}
		</style>
	    
	    
	    <!-- Add By A -->
	     <link rel="stylesheet" href="<c:url value="/resources/css/login.css" />" type="text/css" />     
	    
	</head>
	<body class="overflow_auto mBgGridnt">
		 
		 
		 <!-- Add By A -->
		  <img class="login_page_bg" src="<c:url value="/resources/images/loginBg.jpg"/>">
		 <div class="clearfix"></div>
		 <div class=" col-md-12">
		  <div class="login_bg col-md-3 col-sm-6">
		  <div class="commonLoad" id="mainPageLoad" style="display:none;"></div>
		  
		   <!-- <div class="module_logo" >
		    <img src="images/logo1.png">
		   </div>  -->   
		   <div class="login_cont">
		   
		   <input type="hidden" id="contextPath" value="${pageContext.request.contextPath}">
		    <form id="salesSearchForm">
		     
		     <%-- <div class="lLoginCol">
		      <img src="<c:url value="/resources/images/logo.png"/>">
		     </div> --%>
		     
		     <h3 class="loginProjTitle" style="text-align: center;">${projectName}</h3>
		     
		     <label class="inputLabel">Token</label>
		     <input type="text" id="token" class="requiredField"/>
			<input  type="hidden" id="projectSfid" value="${projectSfid}" />	
			<input  type="hidden" id="projectName" value="${projectName}" />
		     
		     <label class="inputLabel">Mobile No</label>
		    <!--  <input type="password" name='password' /> -->
		    <input type="hidden" id="countryCode">
			                    <input  type="text" id="mobileNo" class="form-control mobile requiredField" maxlength="10" onclick="setCountryCode(this);">
				    
            <div class="clearfix"></div>
		     
		     <h5 id="invalidEntry"></h5> 
		     <div class="clearfix"></div>
		     
		     <div class="btnCol" style="margin-top: 20px; text-align: center;"> 
		      <!-- <button class="btn btn-primary btnNext" onclick="getDataByMobileNo(this);" > 
		       <span>Search</span> 
		      </button> -->
		      
		      <div class=""> <a class="btn btn-primary btnNext" onclick="getDataByMobileNo(this);"> <span>Search</span> </a><div class="clearfix"></div></div>
		      
		      
		      
						
		      
		      
		      
		      
		      <div class="clearfix"></div>
		     </div>
		     
		     <div class="clearfix"></div>
		    </form>
		    <div class="clearfix"></div>
		   </div> 
		  </div>
		 </div>
		 <!-- END Add By A -->
		 
	<%-- 	 
		 
		 
		<div class="clearfix"></div>
		<div class=" col-md-12">
			<div class="login_bg ">
			    <div class="commonLoad" id="mainPageLoad" style="display:none;"></div>
				<!-- <div class="module_logo" >
					<img src="images/logo1.png">
				</div>	 -->			
				<div class="login_cont">
					<div class="col-md-12">
					  
						<input type="hidden" id="contextPath" value="${pageContext.request.contextPath}">
						<form id="salesSearchForm">
						<div class="lLoginCol">
							<img src="<c:url value='/resources/images/logo.png' />">
						</div>
						
						<h3 class="loginProjTitle">${projectName}</h3>
						<div class="clearfix"></div>
						<label class="inputLabel">Token:</label>
						<div class=" txtCenter col-md-3">
						    <input  type="hidden" id="projectSfid" value="${projectSfid}" />	
						    <input  type="hidden" id="projectName" value="${projectName}" />
							<input  type="text" id="token" class="requiredField"/>
						</div>
						<div class="clearfix"></div>
						<br>
						<div class="clearfix"></div>
						<!-- <label class="inputLabel">Mobile No:</label>
						<div class="txtCenter col-md-3 input-group">	
							<input   type="text" id="mobileNo" class="requiredField" maxlength="10" />
						</div> -->
						 <label class="inputLabel">Mobile No:</label>
						<div class="input-group  col-md-3">
						        <input type="hidden" id="countryCode">
			                    <input  type="text" id="mobileNo" class="form-control mobile requiredField" maxlength="10" onclick="setCountryCode(this);">
			             <span class="input-group-addon"></span>
			            
	                    </div>
						
						<h5 id="invalidEntry"></h5> 
						<div class="clearfix"></div>
						<div class="btnCol"> <a class="btn btn-primary btnNext" onclick="getDataByMobileNo(this);"> <span>Search</span> </a><div class="clearfix"></div></div>
							<div class="clearfix"></div>
						</form>
					</div>
					<div class="clearfix"></div>
				</div>	
			</div>
		</div> --%>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/8.4.7/js/intlTelInput.js"></script>
	<script>
			$(".form-control").intlTelInput({
				separateDialCode: true ,
				utilsScript: "https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/8.4.6/js/utils.js",
				preferredCountries: ["in"],
			});
			/* $(".form").click(function() {
				 						 
			}); */
		</script>
	
	</body>
</html>
