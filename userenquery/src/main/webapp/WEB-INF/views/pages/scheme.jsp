<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@page import="java.util.HashMap"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
	<title>Godrej</title>
	
	<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css' />">
	<link rel="stylesheet" href="<c:url value='/resources/css/costsheet.css' />">
	<link href="<c:url value='/resources/css/animate.min.css' />" rel="stylesheet">	
	<link rel="stylesheet" href="<c:url value='/resources/css/sweetalert.min.css'/>">    
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/jquery.countdownTimer.css'/> " />
</head>
<body>

	<input id="pageContext" value="${pageContext.request.contextPath}" style="display:none;"/>

	<div class="container">
		<div class="row">
			<h2>Scheme Charges</h2>
		    
		    
		    
		    
		    
		    
		    <!-- New changes --> 
		    <div class="titleCol">
				<h4 class="">
					<span id="projectTitle">${projectName}</span>					
				</h4>
				<div class="clearfix"></div>
			</div>
			
			<div>
				<ul class="nav nav-tabs tabNav">
					<li class="active" id="">
						<a href="#SchemeMasterTab" data-toggle="tab">Scheme Master</a>
					</li>
					<li id="">
						<a href="#headerSchemeTab" data-toggle="tab">Header Scheme</a>
					</li>
					 <li id="">
						<a href="#schemeMapping" data-toggle="tab">Header Scheme Mapping</a>
					</li>  
				</ul>
				<div class=""></div>
			</div>
			
			<div class="tab-content formTabCont" style="border: 1px solid #ccc; border-top: 0;">
				<div class="tab-pane active" id="SchemeMasterTab">
					<jsp:include page="schemeMaster.jsp"></jsp:include>		
			 	</div>
			 	
				<div class="tab-pane" id="headerSchemeTab">
			 		
						<div class="form-group col-md-3">
							<label>Region</label>
							<select class="form-control" id="regionList" onchange="projectDataList()"> </select>
						</div>
						<div class="form-group col-md-3">
							<label>Project</label>
							<select class="form-control" id="projectDataList" onchange="getHeaderSchemeSource(), getHeaderSchemePromotional (), getHeaderSchemeSite ()"> </select>
						</div>
						<div class="form-group col-md-3">
							<label>Scheme Name</label>
							<input id="scheme_name" type="text" class="form-control" />
						</div>
						<div class="clearfix"></div>
						
						
						<!-- New changes -->
						<div class="form-group col-md-3 col-sm-6" id="schemeSourceCol">
							<label >Source</label> 
							<select class="full form-control" id="getHeaderSchemeSource" >
								<option value="0">Select</option>
							</select>
						</div>
						
						<div class="form-group col-md-3 col-sm-6" id="schemeSite">
							<label >Site</label> 
							<select class="full form-control" id="getHeaderSchemeSite" >
								<option value="0">Select</option>
							</select>
						</div>
						
						<div class="form-group col-md-3 col-sm-6" id="schemePromotional">
							<label >Promotional</label> 
							<select class="full form-control" id="getHeaderSchemePromotional" >
								<option value="0">Select</option>
							</select>
						</div>  	
						<!-- END New changes -->
						
						
						<div class="clearfix"></div>
						<div class="form-group col-md-3">
							<label>Rate</label>
							<input id="scheme_rate" type="number" class="form-control" value="0"/>
						</div>
						<div class="form-group col-md-3">
							<label>Percentage</label>
							<input id="percentage" type="number" class="form-control" value="0"/>
						</div>
						<div class="form-group col-md-3">
							<label>Absolute Amount</label>
							<input id="absolute_amount" type="number" class="form-control" value="0"/>
						</div>
				    	<div class="clearfix"></div>
				    	
				    	<hr>
						
						<div class="checkbox">
						  <label><input id="zeroGovtCharges" value="true" type="checkbox" value="" >Zero Govt. Charges</label>
						</div>
				    	
				    	<div class="form-group col-md-3">
				    		<label style="display: block;"> &nbsp; </label>
				    		<button type="submit" class="btn btn-default btn-primary" onclick="addschemeCharge()">Submit</button>
				    		<div class="clearfix"></div>
				    	</div>
				    	<div class="clearfix"></div>
				   
			 	</div>
			 	
			 	<div class="tab-pane" id="schemeMapping" >
					<jsp:include page="schemeMapping.jsp"></jsp:include>
				</div>
				<div class="clearfix"></div>
		 	</div>
		    <!-- END New Changes -->
		    
		    
		    
		    
		    
		    <div class="clearfix"></div>
		    
		   
		    
		    
		    
		    <!-- <div class="">
	            <div class="row">
	                <label></label>
	                    <div class="form-group multiple-form-group input-group col-md-12">
	                        <div class="col-md-4">
	                            <input placeholder="Charge Name" class="form-control">
	                            <div class="clearfix"></div>
	                        </div>    
	                        <div class="col-md-4">
	                            <input placeholder="Rate" type="number" name="contacts['value'][]" class="form-control">
	                            <div class="clearfix"></div>
	                        </div>
	                        <div class="col-md-4">
	                            <span class="input-group-btn">
	                                <button type="button" class="btn btn-success btn-add">+</button>
	                                <div class="clearfix"></div>
	                            </span>
	                        </div>  
	                        <div class="clearfix"></div>  
	                    </div>
	                </div>
	            </div> -->
	            
	            
	        </div>
		</div>
	
	
	<script src="<c:url value='/resources/js/jquery-1.12.4.min.js' />"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
	<script src="<c:url value='/resources/js/sweetalert.min.js'/>"></script>
	
	
	<script src="<c:url value='/resources/js/separate/scheme.js?v=18.05'/>"></script>
	
	<script src="<c:url value='/resources/js/separate/schemeMapping.js?v=18.05'/>"></script>
	<script src="<c:url value='/resources/js/separate/schemeMaster.js?v=18.05'/>"></script>
	
	
	
	<!-- <script type="text/javascript">
		(function ($) {
		    $(function () {
	
		        var addFormGroup = function (event) {
		            event.preventDefault();
	
		            var $formGroup = $(this).closest('.form-group');
		            var $multipleFormGroup = $formGroup.closest('.multiple-form-group');
		            var $formGroupClone = $formGroup.clone();
	
		            $(this)
		                .toggleClass('btn-success btn-add btn-danger btn-remove')
		                .html('-');
	
		            $formGroupClone.find('input').val('');
		            $formGroupClone.find('.concept').text('Phone');
		            $formGroupClone.insertAfter($formGroup);
	
		            var $lastFormGroupLast = $multipleFormGroup.find('.form-group:last');
		            if ($multipleFormGroup.data('max') <= countFormGroup($multipleFormGroup)) {
		                $lastFormGroupLast.find('.btn-add').attr('disabled', true);
		            }
		        };
	
		        var removeFormGroup = function (event) {
		            event.preventDefault();
	
		            var $formGroup = $(this).closest('.form-group');
		            var $multipleFormGroup = $formGroup.closest('.multiple-form-group');
	
		            var $lastFormGroupLast = $multipleFormGroup.find('.form-group:last');
		            if ($multipleFormGroup.data('max') >= countFormGroup($multipleFormGroup)) {
		                $lastFormGroupLast.find('.btn-add').attr('disabled', false);
		            }
	
		            $formGroup.remove();
		        };
	
		        var selectFormGroup = function (event) {
		            event.preventDefault();
	
		            var $selectGroup = $(this).closest('.input-group-select');
		            var param = $(this).attr("href").replace("#","");
		            var concept = $(this).text();
	
		            $selectGroup.find('.concept').text(concept);
		            $selectGroup.find('.input-group-select-val').val(param);
	
		        }
	
		        var countFormGroup = function ($form) {
		            return $form.find('.form-group').length;
		        };
	
		        $(document).on('click', '.btn-add', addFormGroup);
		        $(document).on('click', '.btn-remove', removeFormGroup);
		        $(document).on('click', '.dropdown-menu a', selectFormGroup);
	
		    });
		})(jQuery);
	</script> -->
	
	
</body>
</html>