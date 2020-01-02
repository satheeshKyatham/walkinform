<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@page import="java.util.HashMap"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<!-- Declair variables -->
<%-- <%!HashMap<String, String> UserData; %> --%>

<%-- <% 
	try
	{
		session = request.getSession(false);
		if(session.getAttribute("UserData")!=null) {
			UserData = (HashMap<String, String>) session.getAttribute("UserData");
		}
	}catch(Exception e)
	{	
		e.printStackTrace();
	}
%> --%>



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
    <title>Godrej</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css' />">
	<link rel="stylesheet" href="<c:url value='/resources/css/costsheet.css' />">
	<link href="<c:url value='/resources/css/animate.min.css' />" rel="stylesheet">
	
	<link rel="stylesheet" href="<c:url value='/resources/css/sweetalert.min.css'/>">
	 
    
     <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/jquery.countdownTimer.css'/> " />
	
	
	
	
	
  </head>
  <body>
	
<%-- 	<input id="loginUserName" value="<%=UserData.get("usrName") %>" style="display:none;">
	<input id="loginUserId" value="<%=UserData.get("userID") %>" style="display:none;">
	<input id="loginUserEmail" value="<%=UserData.get("EmailID") %>" style="display:none;">
 --%>	
	<div style="display:none;">
	
		<input id="paymentPlanId" value="a1l6F000004QtUZQA0">
		
		<input id="BSPrate" style="">
		
		<input id="timeid"  style="">
		
		
		<%-- <input id="token" value="${token}">
		<input id="projectsfid" value="${projectsfid}" />
		<input id="enquirysfid" value="${enquirysfid}" />
		<input id="primarycontactsfid" value="${primarycontactsfid}" />
		 --%>
		<!-- <input id="token" value="7">
		<input id="projectsfid" value="a20O0000002XKut" />
		<input id="enquirysfid" value="a29O000000AqL4M" />
		<input id="primarycontactsfid" value="003O000000whPJ3" /> 003O000001Dhw9r
		 -->
		<input id="pageContext" value="${pageContext.request.contextPath}"/>
	
	</div>
	
	<nav class="navbar navbar-default topMenuBar">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <a class="navbar-brand" href="#">
	        <img src="<c:url value="/resources/images/gplLogo.jpg"/>">
	      </a>
	    </div>
	    
	    <div class="topProTitle">
	    	<!-- GODREJ AQUA -  -->PRICE SHEET 
	    </div>
	    
		
	    
	    <div class="pull-right">	
	    	<a class="" style="margin-top: 13px; display: inline-block; margin-right: 10px;"><%-- <%=UserData.get("usrName") %> --%></a> <a href="logout" class="pull-right btn btn-default navbar-btn">Logout</a>
	    </div>
	    
	  </div>
	</nav>
	
	
	<div class="commonLoad" id="mainLoad" style="display:none;"></div>
	
	
	<input id="costofCarpet" style="display:none;" />
	<input id="costofBalCony" style="display:none;" />
	<input id="costCom" style="display:none;" />	
	<input id="totalTaxableAmtInput" style="display:none;" />
	
	
	<!-- <input id="timeId">
  
  	<input id="timeIdNew" placeholder="timeIdNew"> -->
  	
  
  <!-- <div class="money">2055000</div>
  <button onclick="test123()">Currency</button> -->
  
  
  
<div>
	<!-- Nav tabs -->
	<ul class="nav nav-tabs" role="tablist">
	  <li role="presentation" class="active"><a href="#costsheet" aria-controls="costsheet" role="tab" data-toggle="tab">Costsheet</a></li>
	  <li onclick="getRqstForSales()" role="presentation"><a href="#listSales" aria-controls="listSales" role="tab" data-toggle="tab">Requests</a></li>
	</ul>
	
	<!-- Tab panes -->
	<div class="tab-content">
		<div role="tabpanel" class="tab-pane active" id="costsheet">
			<div class="container inventoryWrp" style="margin-top:20px;">
		
	<!-- <div>
		<ul class="nav nav-tabs" role="tablist">
			<li role="presentation" class="active"><a href="#tab1" aria-controls="home" role="tab" data-toggle="tab">Tab 1</a></li>
			<li role="presentation"><a href="#tab2" aria-controls="profile" role="tab" data-toggle="tab">Tab 2</a></li>
		</ul>
		<div class="tab-content">
			<div role="tabpanel" class="tab-pane active" id="tab1">1</div>
			<div role="tabpanel" class="tab-pane" id="tab2">2</div>
		</div>
	</div> -->
				
		<!-- <input type="number" id="getPln"/> -->
		
		<div>
			  <div class="col-md-8 col-sm-8">
		
			
			<!-- <h3 class="flipInX animated">GODREJ AQUA - PRICE SHEET</h3> -->      
			<table class="table table-bordered brd0">
				<tbody>
					<tr>
						<th class="txtLeft brd0" style="padding:0 0 20px 0 !important;">
							<a class="btn btn-primary vpdfDis" target="_blank" id="viewPDF" >
								<i class="glyphicon glyphicon-print"></i> PRINT PRICE SHEET
								<div class="commonLoad" style="display:none;"></div>
							</a>
							<a class="btn btn-primary" onclick="location.reload()">
								Reset
							</a>
						</th>
						<td class="posRelative fltrDD txtRight brd0">
							
						</td>
						<!-- <td class="brd0" colspan="2" rowspan="5">
						</td>	 -->
						<td class="brd0"> </td>
						<td class="brd0"> </td>							
					</tr>
					
					<tr>
						<th class="txtRight">Mobile No.</th>
						<td class="posRelative fltrDD">
							<!-- <div class="commonLoad"></div> -->
							<input class="full" id="getContact">
						</td>
						<!-- <td class="brd0" colspan="2" rowspan="5">
						</td>	 -->
						<td class="brd0"> </td>
						<td class="brd0"> </td>							
					</tr>
					
					
					
					<tr>
						<th class="txtRight">Name</th>
						<td class="">
							<div id="customerName"></div>
						</td>	
						<td class="brd0"> </td>
						<td class="brd0"> </td>					
					</tr>
					
					<tr>
						<th class="txtRight">Email ID</th>
						<td class="">
							<div id="customerEmail"></div>
						</td>	
						<td class="brd0"> </td>
						<td class="brd0"> </td>					
					</tr>
					
					<tr>
						<th class="txtRight">Enquiry ID</th>
						<td class="posRelative"> </td>
						<td class="brd0"> </td>
						<td class="brd0"> </td>	
						<!-- <td class="brd0" colspan="2" rowspan="5">
						</td>	 -->
						<!-- <td class="brd0 btnCOl" colspan="2" rowspan="5">
							<button class="btn btn-primary" onclick="loadData()">Search</button>
						</td> -->						
					</tr>
					
					<tr>
						<th class="txtRight">Tower <strong class="mndtStar">*</strong></th>
						<td class="posRelative fltrDD" id="tdd">
							
							
							
							<form class="form-inline">
							  <div class="form-group">
							   
							    <div class="input-group">
							     
							      <input type="text" class="form-control" id="ZZtestProjectIdRemoveafterUAT" value="a1l6F000004QtUZQA0" placeholder="a1l6F000004QtUZQA0">
							      <a class="input-group-addon" onclick="towerList ()" style="cursor: pointer;">Click</a>
							    </div>
							  </div>
							</form>
							
							
							<!-- <div class="commonLoad"></div> -->
							<select class="full" id="towerDD" onchange="towerChange()">
							  <option value="">Select</option>
							</select>
							
							<!-- Below commented code do not remove -- for synchronize request for same unit -->
							<!-- <select id="testSFID">
								<option value="a1s6F000005e1z9QAA">a1s6F000005e1z9QAA</option>
								<option value="a1s6F000005e1zAQAQ">a1s6F000005e1zAQAQ</option>
								<option value="a1s6F000005e1yXQAQ">a1s6F000005e1yXQAQ</option>
							</select>
							
							<select id="ZZCRMUser">
								<option value="A">A</option>
								<option value="B">B</option>
								<option value="C">C</option>
								<option value="D">D</option>
							</select> -->
							
							
						</td>
						<td class="brd0 btnCOl" colspan="2" rowspan="5">
							<button class="btn btn-primary" id="getCSData" >Get Details</button> <!-- onclick="loadData()" -->
							
							<button id="zzzzTestCharges">Dynamic Other Charges</button>
							
							<!-- Below commented code do not remove -- for synchronize request for same unit -->
							<!-- <button id="testSubmit">Test Submit</button> -->
						</td>
						<!-- <td class="brd0 btnCOl" colspan="2" rowspan="5">
							<button class="btn btn-primary" onclick="loadData()">Search</button>
						</td> -->						
					</tr>
					
					
					
					
					<tr>
						<th class="txtRight">Floor <strong class="mndtStar">*</strong></th>
						<td class="posRelative fltrDD" id="fdd">
							<div class="commonLoad" style="display:none;"></div>
							<select class="full" id="floorList" onchange="unitTypeMst()">
							  <option value="">Select</option>
							</select>
						</td>
						<!-- <td class="brd0" colspan="2">
							
						</td> -->
					</tr>
					<tr>
						<th class="txtRight">Flat Type <strong class="mndtStar">*</strong></th>
						<td class="posRelative ">
							<select class="full" onchange="flatTypeNew()" id="flatType">
							  <option value="">Select</option>
							  <!-- <option value="2 BHK">2 BHK</option>
							  <option value="3 BHK">3 BHK</option> -->
							</select>
						</td>
						<!-- <td class="brd0" colspan="2">
							
						</td> -->
					</tr>
					<tr>
						<th class="txtRight">Unit Number <strong class="mndtStar">*</strong></th>
						<td class="posRelative fltrDD" id="udd">
							<div class="commonLoad" style="display:none;"></div>
							<select class="unitDD">
							  <option value="">Select</option>
							  <!-- <option value="A201">A201</option>
							  <option value="A202">A202</option>
							  <option value="A203">A203</option>
							  <option value="A204">A204</option> -->
							</select>							
						</td>
						<!-- <td class="brd0 btnCOl " colspan="2">
							<button class="btn btn-primary" onclick="loadData()">Search</button>
						</td> -->
					</tr>
					
					<tr>
						<th class="txtRight">10X</th>
						<td class="">
							<select class="full" id="discount1">
								<option value="">Select</option>
								<option value="10x">10X</option>
							</select>
						</td>
						<!-- <td class="brd0" colspan="2"></td> -->
					</tr>
					
					<tr>
						<th class="txtRight">Scheme</th>
						<td class="">
							<select id="getPln" onchange="schemeType(this)">
								<option value="">Select</option>
								<option value="200">Loyalty€“ Rs. 200/sqft</option>
								<option value="200">Defence Rs. 200/sqft</option>
								<option value="100">Corporate Rs. 100/sqft</option>
								<option value="100">Referral Rs. 100/sqft</option>
								<option value="100">Bulk(2 units) Rs. 100/Sqft</option>
								<option value="150">Bulk(3-5 units) - Rs. 150/Sqft</option>
								<option value="300">Bulk(5+) - Rs. 300/Sqft</option>
								<option value="200">Airlines Rs. 200/Sqft</option>
								<option value="200">GILAC Rs. 200/Sqft</option>
								<option value="50">Others - Rs. 50/Sqft</option>
								<option value="other">Others</option>
							</select>
							
							<div id="newPlan"></div>
							
						</td>
						<!-- <td class="brd0" colspan="2"></td> -->
					</tr>
					
					
					<tr id="ZZGetPPDD">
						<th class="txtRight">Payment Plan</th>
						<td class="">
							<select id="ppDropdown" class="full">
								<option value="">Select</option>
							</select>
						</td>
					</tr>
					
					
					<tr>
						<th>Type of Car Parking</th>
						<td class="a1 animated"></td>
						<td class="brd0"></td>
						<td class="brd0"></td>
					</tr>
					<tr>
						<th>Typology</th>
						<td class="a2 animated"></td>
						<td class="brd0"></td>
						<td class="brd0"></td>
					</tr>	
					<tr>
						<th>Basic Sale Price per square feet (INR)</th>
						<td class="a3 money animated"></td>
						<td class="brd0"></td>
						<td class="brd0"></td>
					</tr>
					<tr>
						<th>Carpet Area (in square feet) </th>
						<td class="a4 animated"></td>
						<td class="brd0"></td>
						<td class="brd0"></td>
					</tr>
					<tr>
						<th>Exclusive Balcony Area/Terrace Area (in square feet)</th>
						<td class="a5 animated"></td>
						<td class="brd0"></td>
						<td class="brd0"></td>
					</tr>
					<tr>
						<th>Saleable Area (in square feet)</th>
						<td class="a6 animated"></td>
						<td class="brd0"></td>
						<td class="brd0"></td>
					</tr>
					<tr class="subHead">
						<th>Parameter</th>
						<th>Amount per Sq.ft. of Saleable Area (INR)</th>
						<th>Amount in INR</th>
						<th>GST %</th>
					</tr>
					<tr>
						<th>Basic Sale Price (BSP)</th>
						<td class="b1 money animated"></td>
						<td class="ba1 money animated"></td>
						<td>12%</td>
					</tr>
					<tr>
						<th>Preferred Location Charges (PLC)</th>
						<td class="b2 money animated"></td>
						<td class="ba2 money animated"></td>
						<td>12%</td>
					</tr>
					<tr>
						<th>Floor Rise Charges (FRC)</th>
						<td class="b3 money animated"></td>
						<td class="ba3 money animated"></td>
						<td>12%</td>
					</tr>	
					<tr>
						<th>Generator Charges</th>
						<td class="b4 money animated"></td>
						<td class="ba4 money animated"></td>
						<td>18%</td>
					</tr>
					<tr>
						<th>Gas Bank Charges</th>
						<td class="b5 money animated"></td>
						<td class="ba5 money animated"></td>
						<td>18%</td>
					</tr>
					<tr>
						<th>BESCOM/Water Supply and Sewage Treatment Charges</th>
						<td class="b6 money animated"></td>
						<td class="ba6 money animated"></td>
						<td>18%</td>
					</tr>
					<tr>
						<th>Legal and Khata Charges</th>
						<td class="b7 money animated"></td>
						<td class="ba7 money animated"></td>
						<td>18%</td>
					</tr>
					<tr>
						<th>Car Park</th>
						<td></td>
						<td class="ba8 money animated"></td>
						<td>12%</td>
					</tr>
					<tr>
						<th>Club House Charges </th>
						<td></td>
						<td class="ba9 money animated"></td>
						<td>18%</td>
					</tr>
					
					<!-- Other Charges (OC) - Payable on notice to possession -->
					<tr>
						<th class="subHead" colspan="2">Other Charges (OC) - Payable on notice to possession</th>	
						<td></td>
						<td></td>						
					</tr>
					<tr>
						<th>Advance Maintenance for 1 year (OC- Payable on notice to Possession)</th>
						<td class="b10 money animated"></td>
						<td class="ba10 money animated"></td>
						<td>18%</td>
					</tr>
					<tr>
						<th>Sinking Fund Deposit (One time - OC- Payable on notice to Possession)</th>
						<td class="b11 money animated"></td>
						<td class="ba11 money animated"></td>
						<td>0%</td>
					</tr>
					<tr>
						<th class="subHead" colspan="2">Total Unit Value including other charges</th>
						<th class="ba12Totle money animated"></th>
						<td></td>
					</tr>
				</tbody>
			</table>
			<div class="clearfix"></div>
		</div>
		
		
		
		
		
		<div class="col-md-4">
			Property Release in
			<span id="hm_timer" style="border: 0; background-color: inherit; color: #333;"></span> <span>min.</span>
			<div class="clearfix"></div>
		</div>
		
		
		
		
		<!-- Dynamic Other Charges 20190521 -->
		<div class="col-md-12 col-sm-12">
			<h3 class="">Dynamic Other charges</h3>
			<table class="table table-bordered" id="newOtherCharges1">
				<tbody>
					<tr class="subHead" >
						<th>
							Parameter
						</th>
						<th>
							Amount per Sq.ft. of Saleable Area (INR)
						</th>
						<th>
							Amount in INR
						</th>
						<th>
							GST %
						</th>				
					</tr>
				</tbody>
			</table>
			<div class="clearfix"></div>
		</div>
		<!-- END Dynamic Other Charges 20190521 -->
		
		
		
		
		
		
		<!-- Dynamic Other Charges part 2 20190522 -->
		<div class="col-md-12 col-sm-12">
			<h3 class="">P2 Dynamic Other charges</h3>
			<table class="table table-bordered" id="newOtherCharges2">
				<tbody>
					<tr class="subHead" >
						<th>
							Parameter
						</th>
						<th>
							Charge Amount (INR)
						</th>
						<th>
							Taxable Amount (INR)
						</th>
						<th>
							CGST Rate
						</th>	
						<th>
							CGST Amount (INR)
						</th>
						<th>
							SGST Rate
						</th>
						<th>
							SGST Amount (INR)
						</th>	
						<th>
							Total (INR)
						</th>				
					</tr>
					
					
					
					
					
					
				</tbody>
			</table>
			<div class="clearfix"></div>
		</div>
		<!-- END Dynamic Other Charges part 2 20190522 -->
		
		
		
		
		
		
		
		
		
		
		
		
		<!-- <div class="col-md-4 col-sm-4">
			<h3 class="flipInX animated">PLC</h3>      
			<table class="table table-bordered">
				<tbody>
					<tr class="subHead">
						<th>PLC Type</th>
						<th>Charge</th>
					</tr>
					<tr>
						<th>Sunrise Facing</th>
						<td class="c1 animated"></td>
					</tr>
					<tr>
						<th>Gateway Facing</th>
						<td class="c2 animated"></td>
					</tr>
					<tr>
						<th>East Facing</th>
						<td class="c3 animated"></td>
					</tr>
					<tr>
						<th>Central Garden Facing</th>
						<td class="c4 animated"></td>
					</tr>
					<tr>
						<th>Corner Apartment</th>
						<td class="c5 animated"></td>
					</tr>
					<tr>
						<th>Rear Garden Facing</th>
						<td class="c6 animated"></td>
					</tr>
					<tr>
						<th>Total PLC</th>
						<td class="c7Totle animated"></td>
					</tr>
				</tbody>
			</table>
			<div class="clearfix"></div>
		</div> -->
	
		<div class="clearfix"></div>
	  
	  
		<!-- PAYMENT SCHEDULE -->
		<div class="col-md-8 col-sm-12">
			<h3 class="flipInX animated paySchTitle">Payment Schedule</h3>      
			<table class="table table-bordered" id="paymentMilestone">
				<tbody>
					
				</tbody>
			</table>
			<div class="clearfix"></div>
		</div>
		<div class="clearfix"></div>
	</div>

	<div class="clearfix"></div>	
		
		
		
		
	  
	  <!-- ---------------------- 2nd TAB START ------------------------------------------------------------ -->
	
		<div id="printableArea" style="    border-top: 1px dotted #000;     padding-top: 20px;">
			
			<h4 class="printTitle" style="display:none;">
				GODREJ AQUA: PRICE AND PAYMENT SCHEDULE (AS PER ANNEXURE E)
			</h4>
			
			<div class="clearfix"></div>
			  <div class="col-md-4 col-sm-4">
					<table class="table table-bordered">
						<tbody>
							<tr>
								<th>Unit Number</th>
								<th class="animated" id="unitTval"></th>
							</tr>
							<tr>
								<th>Tower</th>
								<td class="animated" id="towerTval"></td>
							</tr>
							<tr>
								<th>Floor</th>
								<th id="floorTval"></th>
							</tr>
							<tr>
								<th>Typology</th>
								<td id="typologyTval" class="animated"></td>
							</tr>
						</tbody>
					</table>
					<div class="clearfix"></div>
				</div>
				
				<div class="col-md-4 col-sm-4">
					<table class="table table-bordered">
						<tbody>
							<tr>
								<th>Carpet Area  (Sq.m)</th>
								<td id="carpetSqm" class="animated"></td>
							</tr>
							<tr>
								<th>Exclusive Balcony Area/Terrace Area(Sq.m)</th>
								<td id="balTerSqm" class="animated"></td>
							</tr>
							<tr>
								<th>No of Car parks</th>
								<td id="carParks"  class="animated">1</td>
							</tr>
						</tbody>
					</table>
					<div class="clearfix"></div>
				</div>
			  
				<div class="col-md-4 col-sm-4">
					<table class="table table-bordered">
						<tbody>
							<tr>
								<th>Cost of Carpet Area (A.) (In INR)</th>
								<td class="animated" id="CostofCarpet"></td>
							</tr>
							<tr>
								<th>Cost of Exclusive Balcony Area/Terrace Area (B.) (In INR)</th>
								<td class="animated" id="CostofExcBalcony"></td>
							</tr>
							<tr>
								<th>Proportionate cost of Common Areas (C.) (In INR)</th>
								<td id="PropCost"></td>
							</tr>
						</tbody>
					</table>
					<div class="clearfix"></div>
				</div>
			  
			  
			  
			  
			  
				<!-- ----------------- Charges & Amount --------------------- -->
				<div class="col-md-12 col-sm-12">
					<h3 class="flipInX animated paySchTitle">Charges & Amount</h3>
					<table class="table table-bordered" id="extraCharges">
						<tbody>
							<tr class="subHead">
								<!-- <th>Seq</th> -->
								<th>Description</th>
								<th>Charge Amount (INR)</th>
								<th>Taxable Amount (INR)</th>
								<th>CGST Rate</th>
								<th>CGST Amount (INR)</th>
								<th>SGST Rate</th>
								<th>SGST Amount (INR)</th>
								<th>Total (INR)</th>
							</tr>
							<tr>
								<!-- <td>1</td> -->
								<th class="animated">
									Basic Price (Part A+ Part B+ Part C)
								</th>
								<td class="animated money" id="BasicPrice1"></td>
								<td class="animated money" id="BasicPrice2"></td>
								<td class="animated" id="BasicPrice3">9%</td>
								<td class="animated money" id="BasicPrice4"></td>
								<td class="animated" id="BasicPrice5">9%</td>
								<td class="animated money" id="BasicPrice6"></td>
								<th class="animated money" id="BasicPrice7"></th>
							</tr>
							<tr>
								<!-- <td>2</td> -->
								<th class="animated">
									Preferred Location Charges (PLC)
								</th>
								<td class="animated money" id="PreferredLoc1"></td>
								<td class="animated money" id="PreferredLoc2"></td>
								<td class="animated" id="PreferredLoc3">9%</td>
								<td class="animated money" id="PreferredLoc4"></td>
								<td class="animated" id="PreferredLoc5">9%</td>
								<td class="animated money" id="PreferredLoc6"></td>
								<th class="animated money" id="PreferredLoc7"></th>
							</tr>
							<tr>
								<!-- <td>3</td> -->
								<th class="animated">
									Floor Rise Charges (FRC)
								</th>
								<td class="animated money" id="FloorRise1"></td>
								<td class="animated money" id="FloorRise2"></td>
								<td class="animated" id="FloorRise3">9%</td>
								<td class="animated money" id="FloorRise4"></td>
								<td class="animated" id="FloorRise5">9%</td>
								<td class="animated money" id="FloorRise6"></td>
								<th class="animated money" id="FloorRise7"></th>
							</tr>
							<tr>
								<!-- <td>4</td> -->
								<th class="animated">
									Car Park Development Charges
								</th>
								<td class="animated money" id="CarParkDev1"></td>
								<td class="animated money" id="CarParkDev2"></td>
								<td class="animated" id="CarParkDev3">9%</td>
								<td class="animated money" id="CarParkDev4"></td>
								<td class="animated" id="CarParkDev5">9%</td>
								<td class="animated money" id="CarParkDev6"></td>
								<th class="animated money" id="CarParkDev7"></th>
							</tr>
							<tr>
								<!-- <td>5</td> -->
								<th class="animated">
									Club House Charges
								</th>
								<td class="animated" id="ClubHouse1"></td>
								<td class="animated" id="ClubHouse2"></td>
								<td class="animated" id="ClubHouse3">9%</td>
								<td class="animated" id="ClubHouse4"></td>
								<td class="animated" id="ClubHouse5">9%</td>
								<td class="animated" id="ClubHouse6"></td>
								<th class="animated" id="ClubHouse7"></th>
							</tr>
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							<!-- <tr class="subHead">
								<th colspan="8">Other Charges (OC)</th>
							</tr> -->
							<tr>
								<!-- <td>6</td> -->
								<th class="animated">
									Advance Maintenance for 1 year (OC- Payable on notice to Possession)
								</th>
								<td class="animated money" id="advance1"></td>
								<td class="animated money" id="advance2"></td>
								<td class="animated" id="advance3">9%</td>
								<td class="animated money" id="advance4"></td>
								<td class="animated" id="advance5">9%</td>
								<td class="animated money" id="advance6"></td>
								<th class="animated money" id="advance7"></th>
							</tr>
							<tr>
								<!-- <td>7</td> -->
								<th class="animated">
									Sinking Fund Deposit (One time - OC- Payable on notice to Possession)
								</th>
								<td class="animated money" id="sinkingFund1"></td>
								<td class="animated money" id="sinkingFund2"></td>
								<td class="animated" id="sinkingFund3">0</td>
								<td class="animated money" id="sinkingFund4"></td>
								<td class="animated" id="sinkingFund5">0</td>
								<td class="animated money" id="sinkingFund6"></td>
								<th class="animated money" id="sinkingFund7"></th>
							</tr>
							<tr>
								<!-- <td>8</td> -->
								<th class="animated subHead">
									Total
								</th>
								<th class="animated money" id="totalChargeAmt"></th>
								<th class="animated money" id="totalTaxableAmt"></th>
								<th class="animated"></th>
								<th class="animated money" id="totalCGSTAmt"></th>
								<th class="animated"></th>
								<th class="animated money" id="totalSGSTAmt"></th>
								<th class="animated money" id="TotalFinal"></th>
							</tr>
							
						
							
						</tbody>
					</table>
					<div class="clearfix"></div>
				</div>
				
				
				
				<!-- ------- Payment Schedule ---------- -->
				
				<div class="col-md-12 col-sm-12">
					<h3 class="flipInX animated">Payment Schedule</h3>
					<table class="table table-bordered" id="paymentSchedule">
						<tbody>
							<tr class="subHead" >
								<th>
									Milestones
								</th>
								<th>
									Charge Amount (INR)
								</th>
								<th>
									Taxable Amount (INR)
								</th>
								<th>
									CGST Rate
								</th>
								<th>
									CGST Amount (INR)
								</th>
								<th>
									SGST Rate
								</th>
								<th>
									SGST Amount (INR)
								</th>
								<th>
									Total (INR)
								</th>
								<th>
									TDS @ 1% (INR)
								</th>
								<th>
									Payable to 
								</th>					
							</tr>
						</tbody>
					</table>
					<div class="clearfix"></div>
				</div>
			<div class="clearfix"></div>
		</div>
		
		<!-- PRICE AND PAYMENT SCHEDULE (AS PER ANNEXURE E) -->
	
	</div>
	
	
	<div class="bottomBar" id="updateBtnCol">
		<button class="btn btn-primary" id="updateCRM" disabled="disabled">Create Offer</button>
		<button class="btn btn-primary" id="snedApproval" disabled="disabled" style="display:none;">Send for Approval</button>
	</div> 
		</div>
		<div role="tabpanel" class="tab-pane" id="listSales">
			<div class="col-md-12 col-sm-12">
				<h3 class="animated">Pending Requests</h3>
				<table class="table table-bordered" id="salesRqst">
					<thead>
						<tr class="">
							<th>Scheme Type</th>
							<th>Discount</th>
							<th>ID</th>
							<th>Name</th>
							<th>Mobile</th>
							<!-- <th>Enquiry ID</th> -->
							<!-- <th>Other Discount</th> -->
							<th>Request From</th>
							<th>Request Date</th>
							<th>View</th>
							<th>Manager Status</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody> </tbody>
				</table>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
</div>
  
  
  
  
  
  
  
  
  
  
	
	
	
    <script src="<c:url value='/resources/js/jquery-1.12.4.min.js' />"></script>
	
	<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script> -->
    
    <%-- <script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script> --%>
	
	
	<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
	<script src="<c:url value='/resources/js/sweetalert.min.js'/>"></script>
	
	
	
	
	<script src="<c:url value='/resources/js/jquery.tabletojson.min.js'/>"></script>
	
	<script src="<c:url value='/resources/js/currencyFormatter.min.js'/>" type="text/javascript"></script>
	
	<script type="text/javascript" src="<c:url value='/resources/js/jquery.countdownTimer.js?v=20'/>"></script>
	
	<script src="<c:url value='/resources/js/separate/costsheet.js?v=20'/>"></script>
	
	
	
	
	
  </body>
</html>