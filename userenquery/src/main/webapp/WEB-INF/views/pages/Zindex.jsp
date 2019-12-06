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
	
	<link href="<c:url value='/resources/css/animate.min.css' />" rel="stylesheet">
 
	
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
   
	
	<div class="container formCol swipeCard">
				
		<div>
			<div class="logoCol">
				<img class="topLogo" src="<c:url value='/resources/images/gplLogo.jpg' />"/>
				<div class="clearfix"></div>
			</div>
			
			<div class="titleCol">
				<h4 class="txtCenter">
					<i class="glyphicon glyphicon-list-alt"></i> 
					<span style="margin-left:3px;">ENQUIRY FORM</span>
				</h4>
				<div class="clearfix"></div>
			</div>
			
			
			
			<div class="clearfix"></div>
			<ul class="nav nav-tabs">
				<li class="active">
					<a href="#tab1" data-toggle="tab">Basic info</a>
				</li>
				<li>
					<a href="#tab2" data-toggle="tab">Requirement</a>
				</li>
				<li>
					<a href="#tab3" data-toggle="tab">Address</a>
				</li>
				<li>
					<a href="#tab4" data-toggle="tab">Other info</a>
				</li>
			</ul>
			<div class=""></div>
		</div>
		
		<div class="tab-content">
			
			<form:form method="POST" modelAttribute="contact">
			<div class="tab-pane active" id="tab1">
				<div class="row "> <!-- bounceInLeft animated -->
					 
					
					<div class="col-md-2 col-sm-6 col-xs-12">
						<div class="group">
							<form:input type="text" required="required" id="mobileno" path="mobilephone"/>
							<span class="highlight"></span><span class="bar"></span>
							<label>Contact no.</label>
						</div>
					</div>
					
					<div class="col-md-3 col-xs-6">
						<div class="select">
							<form:select class="select-text"  path="company_name__c" >
								<option value="">Mumbai</option>
							</form:select>
							<span class="select-highlight"></span>
							<span class="select-bar"></span>
							<label class="select-label" style="top: -14px;">Region <strong class="mndt">*</strong></label>
						</div>
					</div>
					<div class="col-md-3 col-xs-6">
						<div class="select">
							<select class="select-text" required>
								<option value="" select>The Trees</option>
							</select>
							<span class="select-highlight"></span>
							<span class="select-bar"></span>
							<label class="select-label" style="top: -14px;">Project Name <strong class="mndt">*</strong></label>
						</div>
					</div>
					
					<div class="clearfix"></div>
					<div class="col-md-1 col-xs-6">
						<div class="select">
							<form:select class="select-text" required="required" path="salutation" id="salutation">
								<option value="0" >Select</option>
								<option value="Mr">Mr.</option>
								<option value="2">Mrs.</option>
								<option value="3">Miss.</option>
							</form:select>
							<span class="select-highlight"></span>
							<span class="select-bar"></span>
							<label class="select-label">Title <strong class="mndt">*</strong></label>
						</div>
					</div>
					<div class="col-md-2 col-sm-6 col-xs-6">
						<div class="group">
							<form:input type="text" required="required" path="firstname" id="firstname"/>
							<span class="highlight"></span><span class="bar"></span>
							<label>First name <strong class="mndt">*</strong></label>
							<!-- <small class="errorMsg">Error message</small> -->
						</div>
					</div>
					
					<div class="col-md-2 col-sm-6 col-xs-6">
						<div class="group">
							<form:input type="text" required="required" path="lastname" id="lastname"/>
							<span class="highlight"></span><span class="bar"></span>
							<label>Last name <strong class="mndt">*</strong></label>
						</div>
					</div>
					
					<div class="col-md-2 col-sm-6 col-xs-6">
						<div class="group">
							<form:input type="text" required="required" path="birthdate" id="birthdate"/>
							<span class="highlight"></span><span class="bar"></span>
							<label>DOB <strong class="mndt">*</strong></label>
						</div>
					</div>
					
					<div class="col-md-3 col-sm-6 col-xs-12">
						<div class="group">
							<form:input type="text" required="required" path="email" id="email"/>
							<span class="highlight"></span><span class="bar"></span>
							<label>Email <strong class="mndt">*</strong></label>
						</div>
					</div>
					
					
					
					<div class="clearfix"></div>
					
					<div class="col-md-12 radioBtnWrp mrgT0">
						<div class="titleF">Are you accompanied/ referred by a channel partner? <strong class="mndt">*</strong></div>
						<div class="radioBtnCol" data-toggle="buttons">
						  <label class="btn btn-primary active" name="cp" value="Yes">
							<input type="radio" name="cp" value="cpYes" checked> Yes
						  </label>
						  <label class="btn btn-primary" name="cp" value="No">
							<input type="radio" > No
						  </label>
						  <label class="btn btn-primary" name="cp" value="Other">
							<input type="radio" > Other
						  </label>
						</div>
					</div>
					<div class="clearfix"></div>
					<div id="cpYes" class="sourceCol animated">
						<div class="col-md-6 col-xs-12">
							<div class="group">
								<input type="text" required="required"/>
								<span class="highlight"></span><span class="bar"></span>
								<label>Enter channel partner name <strong class="mndt">*</strong></label>
							</div>
						</div>

						<div class="col-md-6 col-xs-12">
							<div class="select">
								<select class="select-text" required>
									<option value="" ></option>
									<option value="1">Option 1</option>
									<option value="2">Option 2</option>
									<option value="3">Option 3</option>
								</select>
								<span class="select-highlight"></span>
								<span class="select-bar"></span>
								<label class="select-label">Select broker contact <strong class="mndt">*</strong></label>
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
					
					<div id="cpNo" class="sourceCol animated" style="display: none;">
						<div class="col-md-6 col-xs-12">
							<div class="select">
								<select class="select-text" required>
									<option value="" ></option>
									<option value="1">Option 1</option>
									<option value="2">Option 2</option>
									<option value="3">Option 3</option>
								</select>
								<span class="select-highlight"></span>
								<span class="select-bar"></span>
								<label class="select-label">How did you come to know about this project? <strong class="mndt">*</strong></label>
							</div>
						</div>
						<div class="col-md-6 col-xs-12">
							<div class="group">
								<input type="text" required="required"/>
								<span class="highlight"></span><span class="bar"></span>
								<label>Details of Newspaper, Website, Hoarding etc. <strong class="mndt">*</strong></label>
							</div>
						</div>	
						<div class="clearfix"></div>
					</div>
					
					<div id="cpOther" class="sourceCol animated" style="display: none;">
						<div class="col-md-6 col-xs-12">
							<div class="group">
								<input type="text" required="required"/>
								<span class="highlight"></span><span class="bar"></span>
								<label>Enter channel partner name <strong class="mndt">*</strong></label>
							</div>
						</div>	
						<div class="clearfix"></div>
					</div>
					
					
					
					
					
					
					<div class="clearfix"></div>
				</div>
				
				<div class="btnWrp">
					<div class="btnCol">
						<a class="btn btn-primary btnPrevious">
							<span>Previous</span>
						</a>
						<a class="btn btn-primary btnNext">
							<span>Save & Continue</span>
						</a>
						<div class="clearfix"></div>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
			 </form:form>
			
			<div class="tab-pane" id="tab2">
				<div class="row "> <!-- bounceInRight animated -->
					<div class="col-md-12 radioBtnWrp mrgT0">
						<div class="titleF">Requirement <strong class="mndt">*</strong></div>
						<div class="radioBtnCol" data-toggle="buttons">
						  <label class="btn btn-primary active">
							<input type="radio" name="options" id="option1" checked> 1 BHK
						  </label>
						  <label class="btn btn-primary">
							<input type="radio" name="options" id="option2"> 2 BHK
						  </label>
						  <label class="btn btn-primary">
							<input type="radio" name="options" id="option3"> 3 BHK
						  </label>
						  <label class="btn btn-primary">
							<input type="radio" name="options" id="option3"> 4 BHK
						  </label>
						  <label class="btn btn-primary">
							<input type="radio" name="options" id="option3"> 5 BHK
						  </label>
						</div>
					</div>
					
					<div class="col-md-3">
						<div class="select">
							<select class="select-text" required>
								<option value="" ></option>
								<option value="1">Option 1</option>
								<option value="2">Option 2</option>
								<option value="3">Option 3</option>
							</select>
							<span class="select-highlight"></span>
							<span class="select-bar"></span>
							<label class="select-label">Budget <strong class="mndt">*</strong></label>
						</div>
					</div>
					
					<div class="col-md-3">
						<div class="select">
							<select class="select-text" required>
								<option value="" ></option>
								<option value="1">Option 1</option>
								<option value="2">Option 2</option>
								<option value="3">Option 3</option>
							</select>
							<span class="select-highlight"></span>
							<span class="select-bar"></span>
							<label class="select-label">Required possession timeline <strong class="mndt">*</strong></label>
						</div>
					</div>	
					
					<div class="col-md-3">
						<div class="select">
							<select class="select-text" required>
								<option value="" ></option>
								<option value="1">Option 1</option>
								<option value="2">Option 2</option>
								<option value="3">Option 3</option>
							</select>
							<span class="select-highlight"></span>
							<span class="select-bar"></span>
							<label class="select-label">Purpose</label>
						</div>
					</div>	
					
					<div class="clearfix"></div>
				</div>
				<div class="btnWrp">
					<div class="btnCol">
						<a class="btn btn-primary btnPrevious">
							<span>Previous</span>
						</a>
						<a class="btn btn-primary btnNext">
							<span>Save & Continue</span>
						</a>					
						<div class="clearfix"></div>
					</div>
					<div class="clearfix"></div>
				</div>
					
			</div>
			
			
			<div class="tab-pane" id="tab3">
				<div class="row "> <!-- bounceInRight animated -->
					<div class="col-md-4 col-sm-12">
						<div class="group">
						  <textarea type="textarea" rows="2" required="required"></textarea>
						  <span class="highlight"></span>
						  <span class="bar"></span>
						  <label>Address Line 1</label>
						</div>
					</div>
					
					<div class="col-md-4 col-sm-12">
						<div class="group">
						  <textarea type="textarea" rows="2" required="required"></textarea>
						  <span class="highlight"></span>
						  <span class="bar"></span>
						  <label>Address Line 2</label>
						</div>
					</div>
					
					<div class="col-md-4 col-sm-12">
						<div class="group">
						  <textarea type="textarea" rows="2" required="required"></textarea>
						  <span class="highlight"></span>
						  <span class="bar"></span>
						  <label>Address Line 3</label>
						</div>
					</div>
					
					<div class="clearfix"></div>
					<div class="col-md-3 col-sm-6 col-xs-6">
						<div class="group">
							<input type="text" required="required"/>
							<span class="highlight"></span><span class="bar"></span>
							<label>Locality</label>
						</div>
					</div>
					
					<div class="col-md-3 col-sm-6 col-xs-6">
						<div class="group">
							<input type="text" required="required"/>
							<span class="highlight"></span><span class="bar"></span>
							<label>City</label>
						</div>
					</div>
					
					<div class="col-md-2 col-sm-6 col-xs-6">
						<div class="group">
							<input type="text" required="required"/>
							<span class="highlight"></span><span class="bar"></span>
							<label>Pin Code</label>
						</div>
					</div>
					
					<div class="col-md-6 col-xs-12 radioBtnWrp">
						<div class="titleF">Current Residence</div>
						<div class="radioBtnCol" data-toggle="buttons">
						  <label class="btn btn-primary active">
							<input type="radio" name="options" id="option1" checked> 1 BHK
						  </label>
						  <label class="btn btn-primary">
							<input type="radio" name="options" id="option2"> 2 BHK
						  </label>
						  <label class="btn btn-primary">
							<input type="radio" name="options" id="option3"> 3 BHK
						  </label>
						  <label class="btn btn-primary">
							<input type="radio" name="options" id="option3"> 4 BHK
						  </label>
						  <label class="btn btn-primary">
							<input type="radio" name="options" id="option3"> 5 BHK
						  </label>
						</div>
					</div>
					
					
					<div class="col-md-6 col-xs-12 radioBtnWrp">
						<div class="titleF">Ownership</div>
						<div class="radioBtnCol" data-toggle="buttons">
						  <label class="btn btn-primary active">
							<input type="radio" name="options" id="option1" checked> Self-owned
						  </label>
						  <label class="btn btn-primary">
							<input type="radio" name="options" id="option2"> Rented
						  </label>
						  <label class="btn btn-primary">
							<input type="radio" name="options" id="option3"> Family own
						  </label>
						  <label class="btn btn-primary">
							<input type="radio" name="options" id="option3"> Other
						  </label>
						</div>
					</div>
					
					
					
					
					<div class="clearfix"></div>
				</div>
				
				<div class="btnWrp">
					<div class="btnCol">
						<a class="btn btn-primary btnPrevious">
							<span>Previous</span>
						</a>
						<a class="btn btn-primary btnNext">
							<span>Save & Continue</span>
						</a>					
						<div class="clearfix"></div>
					</div>
					<div class="clearfix"></div>
				</div>			
			</div>
			
			
			
			
			<div class="tab-pane" id="tab4">
				<div class="row "> <!-- bounceInRight animated -->
					<div class="col-md-3 col-xs-6">
						<div class="select">
							<select class="select-text" required>
								<option value="" ></option>
								<option value="1">Option 1</option>
								<option value="2">Option 2</option>
								<option value="3">Option 3</option>
							</select>
							<span class="select-highlight"></span>
							<span class="select-bar"></span>
							<label class="select-label">Occupation</label>
						</div>
					</div>
					
					<div class="col-md-3 col-xs-6">
						<div class="group">
							<input type="text" required="required"/>
							<span class="highlight"></span><span class="bar"></span>
							<label>Company</label>
						</div>
					</div>
					
					<div class="col-md-3 col-xs-6">
						<div class="group">
							<input type="text" required="required"/>
							<span class="highlight"></span><span class="bar"></span>
							<label>Designation</label>
						</div>
					</div>
					
					<div class="col-md-3 col-xs-6">
						<div class="group">
							<input type="text" required="required"/>
							<span class="highlight"></span><span class="bar"></span>
							<label>Office Locality</label>
						</div>
					</div>
					
					<div class="col-md-3 col-xs-6">
						<div class="group">
							<input type="text" required="required"/>
							<span class="highlight"></span><span class="bar"></span>
							<label>Office City</label>
						</div>
					</div>
					
					<div class="col-md-3 col-xs-6">
						<div class="group">
							<input type="text" required="required"/>
							<span class="highlight"></span><span class="bar"></span>
							<label>Office Pin Code</label>
						</div>
					</div>
					<div class="clearfix"></div>
					
					
					
					
					<div class="clearfix"></div>
				</div>
				
				<div class="btnWrp">
					<div class="btnCol">
						<a class="btn btn-primary btnPrevious">
							<span>Previous</span>
						</a>
						<a class="btn btn-primary btnNext btnSubmit">
							<span>SUBMIT</span>
						</a>	

						<div class="clearfix"></div>
					</div>
					<div class="clearfix"></div>
				</div>				
			</div>
			
		</div>
	
	
	
		<!-- <div> -->
			<!-- <h3>ENQUIRY FORM</h3> -->
				
			
			
			
			
			<!-- <hr class="sep"/> -->
			
			
			
			<!-- <div class="clearfix"></div> -->
			
			
			<!-- <div class="row">
				
				
				
				<div class="clearfix"></div>
				
				
				
				
				
				<div class="clearfix"></div>
			</div> -->
				
			
			
			
			
			<!-- <div class="group">
			  <input type="password" required="required"/><span class="highlight"></span><span class="bar"></span>
			  <label>Password</label>
			</div>
			<div class="group">
			  <input type="number" required="required"/><span class="highlight"></span><span class="bar"></span>
			  <label>Number</label>
			</div>
			<div class="group">
			  <textarea type="textarea" rows="5" required="required"></textarea><span class="highlight"></span><span class="bar"></span>
			  <label>Message</label>
			</div>
			<div class="select">
				<select class="select-text" required>
					<option value="" ></option>
					<option value="1">Option 1</option>
					<option value="2">Option 2</option>
					<option value="3">Option 3</option>
				</select>
				<span class="select-highlight"></span>
				<span class="select-bar"></span>
				<label class="select-label">Select</label>
			</div> -->
			
			<!-- <div class="btn-box">
			  <button class="btn btn-submit" type="submit">submit</button>
			  <button class="btn btn-cancel" type="button">cancel</button>
			</div> -->
		<!-- </div> -->
		<div class="clearfix"></div>
	</div>
	
	
	
	
	
	
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<c:url value='/resources/js/jquery-1.12.4.min.js' />"></script>
    <script src="<c:url value='/resources/js/bootstrap.min.js' />"  ></script>
	<script src="<c:url value='/resources/js/jquery.touchSwipe.min.js' />"></script>
	<script src="<c:url value='/resources/js/index.js' />"></script>
	
	<script>
	
		var rcpNO = $("#cpNo").removeClass("shake");
		var rcpYes = $("#cpYes").removeClass("shake");
		
		$('.btnNext').click(function(){
		  $('.nav-tabs > .active').next('li').find('a').trigger('click');
		  
		 
		});

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
		
		$("label[name$='cp']").click(function() {			
			var cpHS = $(this).attr('value');		
			$("div.sourceCol").hide();
			$("#cp" + cpHS).show();
			
			//$("#cp" + cpHS).addClass("shake");						
		});
	</script>
  </body>
</html>