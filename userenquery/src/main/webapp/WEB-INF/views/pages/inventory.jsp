<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@page import="java.util.HashMap"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<%
	 response.addHeader("Expires","0");
	 response.addHeader("Pragma","no-cache");
	 response.setHeader("Cache-Control","no-cache,no-store, must-revalidate, pre-check=0, post-check=0, max-age=0, s-maxage=0");
	 response.addHeader("X-Frame-Options", "DENY");
	 %>
	<div class="clearfix"></div>
	
	
	<div class="col-md-11" style="margin: 0 auto; float: none;">
	
	
	<%-- <input type="text" id="projectname" value="<%= projectname %>">
	
	<input type="text" id="projectId" value="<%= projectid%>">
	
	<input id="pageContext" value="${pageContext.request.contextPath}"/>
	<input id="token" value="${token}"/>
	<input id="tokentype" value="${tokentype}"/>
	<input id="projectsfid" value="${projectsfid}"/>
	<input id="primarycontactsfid" value="${primarycontactsfid} "/>
	<input id="enquirysfid" value="${enquirysfid}"/>
	<input id="customerId" value="123">
	
	<input  id="userid" value="<%= userid %>"> --%>
	
		
		<%-- <div class="topBarCol">
			<h2 class="pull-left topTitle">Inventory Details</h2>
			<div class="projectLogoCol pull-right">
				<img src="<c:url value="/resources/images/gplLogo.jpg"/>">
			</div>
			<div class="clearfix"></div>
		</div> --%>
		
		
		<div class="mainCont " style="padding-top: 10px;">
			
			<div class="">
				
				<div class="form-group col-md-5">
					<label>Tower</label>
					<select id="towerMst" onchange="inventoryUnitTypeMst()" class="form-control"> </select>
				 </div>
				 
				 <div class="form-group col-md-3 col-sm-6">
					<label>Typology</label>
					<select id="typoMst" class="form-control">
						<option value='typologyAll'>All</option>
					</select>
				 </div>
				 <div class="form-group col-md-3 col-sm-6">
					<label>Facing</label>
					<select id="facing" class="form-control">
						<option value='0'>Select Facing</option>
						<option value='Garden'>Garden</option>
						<option value='Cityscape'>Cityscape</option>
						<option value='East'>East</option>
						<option value='North'>North</option>
						<option value='West'>West</option>
					</select>
				 </div>
				 
				 
				 
				 <!-- <div class="form-group col-md-3">
					<label>Hold</label>
					<select id="holdMst" class="form-control">
						<option value="t">Hold</option>
						<option value="f">Not Hold</option>
					</select>
				 </div> -->
				 
				<!-- <div class="form-group col-md-2">
					<label> &nbsp; </label>
					<div class="checkbox">	
						<label>
							<input type="checkbox" id="holdMst" value="t"> On Hold
						</label>
					 </div>
				</div>
				<div class="form-group col-md-2">
					<label> &nbsp; </label>
					<div class="checkbox">	
						<label>
							<input type="checkbox" id="soldMst" value="f" > Available
						</label>
					 </div>
				</div> -->
				
				
				
				
				<!-- <div class="form-group col-md-3">
					<label>Sold</label>
					<select id="soldMst" class="form-control">
						<option value="t">Sold</option>
						<option value="f">Not Sold</option>
					</select>
				 </div> -->
				
					
				<div class="form-group col-md-3 col-sm-6">
					<label> &nbsp; </label>
					<button onclick="getInventoryRec()" style="line-height:0; color: #fff; background-color: #0077b9;" class="form-control btn btn-primary">
						Get Details
					</button>
				</div>
				
				<div class="form-group col-md-1 col-sm-6">
					<label> &nbsp; </label>
					<button onclick="getInventoryRec()" style="line-height:0; padding:0" class="form-control btn btn-default">
						<i class="glyphicon glyphicon-repeat"></i>
					</button>
				</div>
				
				
				
				
				<!-- 
				<select id="typoMst">
					<option value="2 BHK">2 BHK</option>
					<option value="3 BHK">3 BHK</option>
				</select>
				
				<select id="holdMst">
					<option value="t">Hold</option>
					<option value="f">Not Hold</option>
				</select>
				
				<select id="soldMst">
					<option value="t">Sold</option>
					<option value="f">Not Sold</option>
				</select> -->
				
				
				
				<div class="clearfix"></div>
			</div>
			
			<div class="clearfix"></div>

		<div class="inventoryLegendsCol col-md-12">
			<!--  <ul>
				<li><span class="ilAvailable"></span> Available</li>
				<li><span class="holdforDiscussion"></span> Hold for Discussion </li>
				<li><span class="soldOut"></span> Sold Out</li>
			</ul> -->
			
			
			
					
					<label class="containerCheckbox">Available
					  <input onclick="getInventoryRec()" type="checkbox" id="ilAvailableCheck">
					  <span class="checkmark ilAvailable"></span>
					</label>
					
					<label class="containerCheckbox">Hold for Discussion
					  <input>
					  <span class="checkmark holdforDiscussion" style="background-color: #ff953f !important;"></span>
					</label>
					
					<label class="containerCheckbox">Sold Out
					  <input>
					  <span class="checkmark soldOut"></span>
					</label>
					
			
			
			
			
			
			
			<!-- <ul>
				<li>
					
					<label class="containerCheckbox">One
					  <input type="checkbox" checked="checked">
					  <span class="checkmark"></span>
					</label>
					
				</li>
				<li><span class="ilAvailable"></span> Available</li>
				<li><span class="holdforDiscussion"></span> Hold for Discussion </li>
				<li><span class="soldOut"></span> Sold Out</li>
			</ul> -->
			
			
			<div class="clearfix"></div>
		</div>
		
		
		<div style="display:none;">
			<input id="towerRec" />
			<input id="towerRecCode" />
			<input id="typologyRec" />
			<div class="clearfix"></div>
		</div>
		
		<div class="clearfix"></div>
		
		<div id="inventoryBreadcrumb">
			
			<div class="clearfix"></div>
		</div>
		<div class="clearfix"></div>	
			    
			 
		

		<div class="clearfix"></div>
			
			<div class="floorBand">
				<div class="floorTitle">	
					  <div class="fcData">Floor</div>
				</div>	
				<div class="flatTitle">	
					  <div class="fcData">Flat No.</div>
				</div>
				<div class="clearfix"></div>
			</div>
			
			
			
			
			<div id="inventoryData"  style ="position: relative;" >
				
				
				<!-- <div class="floorBand">
					<div class="floorCel">	
						 <div class="fcData">1</div>
					</div>	
					<div class="unitCel" data-container="body" data-toggle="popover" data-placement="top" data-content="Price: 6050000">	
						  <div class="fcData">A101</div>
					</div>
					<div class="unitCel" data-container="body" data-toggle="popover" data-placement="top" data-content="Price: 6050000">	
						<div class="fcData">A101</div>
					</div>
					<div class="unitCel" data-container="body" data-toggle="popover" data-placement="top" data-content="Price: 6050000">	
						 <div class="fcData">A101</div>
					</div>
					<div class="unitCel" data-container="body" data-toggle="popover" data-placement="top" data-content="Price: 6050000">	
						<div class="fcData">A101</div>
					</div>
					<div class="clearfix"></div>
				</div>
			
				<div class="floorBand">
					<div class="floorCel">	
						 <div class="fcData">1</div>
					</div>	
					
					<div class="unitCel">	
						  <div class="fcData">A101</div>
					</div>
					<div class="unitCel">	
						<div class="fcData">A101</div>
					</div>
					
					<div class="clearfix"></div>
				</div> -->
				
				
				
				
				
				
				<div class="clearfix"></div>
			</div>
			
			
			
			
			<div class="clearfix"></div>
		</div>
		
		<div class="clearfix"></div>
	</div>
	
	