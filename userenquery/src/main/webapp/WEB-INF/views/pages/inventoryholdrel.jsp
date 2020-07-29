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
				 
				 <div class="form-group col-md-3">
					<label>Typology</label>
					<select id="typoMst" class="form-control">
						<option value='typologyAll'>All</option>
					</select>
				 </div>
				 
				 <div class="form-group col-md-3 col-sm-6">
					<label>Category</label>
					<select id="unitCategory" class="form-control">
						<option value='All'>All</option>
						<option value='A'>A</option>
						<option value='B'>B</option>
						<option value='C'>C</option>
					</select>
				 </div>
				 
				 <div class="form-group col-md-3">
					<label>Unit Status </label>
					<select id="searchadmintype" class="form-control">
						<option value='all'>All</option>
						<option value='f'>Action against inventory</option>
						<option value='t'>Held/ Blocked inventory</option>
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
				
					
				<div class="form-group col-md-3">
					<label> &nbsp; </label>
					<button onclick="getInventoryRec()" style="line-height:0; color: #fff; background-color: #0077b9;" class="form-control btn btn-primary">
						Get Details
					</button>
				</div>
				<div class="form-group col-md-1">
					<label> &nbsp; </label>
					<button onclick="getInventoryRec()" style="line-height:0; padding:0" class="form-control btn btn-default">
						<i class="glyphicon glyphicon-repeat"></i>
					</button>
				</div>
				
				<div class="clearfix"></div>
				<hr> 
				<div class="clearfix"></div>
				
				<!-- <div class="form-group col-md-3" id="btnholdsave" style="display: none;">
					<label> &nbsp; </label>
					<button  onclick="holdBlockResion('tempBtn')" placement="right"  data-container="body" data-toggle="popover" data-content="Selected units are viewing as sold out on sales manager inventory screen"  style="line-height:0; color: #fff; background-color: #0077b9;" class="form-control btn btn-primary">
						Hold 
					</button>  
				</div> -->
				
				<div class="form-group col-md-3" id="btnEOIHOldSave" style="display:none;" >
					<label> &nbsp; </label>
					<button onclick="holdBlockResion('eoiHoldBtn')" style="line-height:0; color: #fff; background-color: #0077b9;" class="form-control btn btn-primary">
						EOI Hold 
					</button>
				</div>
				
				
				<div class="form-group col-md-3" id="btnholdsave2" style="display: none;">
					<label> &nbsp; </label>
					<button  onclick="holdBlockResion('blockBtn')" style="line-height:0; color: #fff; background-color: #0077b9;" class="form-control btn btn-primary">
						Block 
					</button> <!-- onclick="SaveForHold('block')" -->
				</div>
				<div class="form-group col-md-3" id="btnreleasesave" style="display: none;">
					<label> &nbsp; </label>
					<button  onclick="SaveForRelease()" id="releaseUnitBtnAdmin" style="line-height:0; color: #fff; background-color: #0077b9;" class="form-control btn btn-primary">
						Release Unit
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
			<ul>
				<li><span class="ilAvailable"></span> Available</li>
				<!-- <li><span class="holdforDiscussion"></span> Hold for Discussion </li> -->
				<li><span class="soldOut"></span> Sold Out</li>
				<!-- <li><span class="holdforDiscussion"></span> Hold </li> -->
				<li><span class="unitBlockAdmin"></span> Block</li>
				<li><span class="unitEOIBlockAdmin"></span> EOI Hold</li>
				
				
				<!-- <li><span class="allotedThroughOffer"></span> Alloted through offer</li>
				<li><span class="holdForReallocation"></span> Hold for Reallocation</li> -->
			</ul>
			<div class="clearfix"></div>
		</div>
		
		
		<div style="display:none;">
			<input id="towerRec" />
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
	
	
	
	<!-- Model -->
	<div class="modal fade" id="holdBlockRsionModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="ModalLabelAdmin"> </h4>
	      </div>
	      <div class="modal-body">
	      	<!-- <div class="form-group col-md-6" id="enqsfidInputField">
				<label>ENQ SFID</label>
		      	<input class='form-control' id="enqsfidInput"> 
			</div> -->
			
			<div class="clearfix"></div>
			
			
			<div id="enqsfidInputField">
				<div class="form-group col-md-6">
				  <div class="form-group">
				    <label>ENQ Name</label>
				    <div class="input-group">
				    	<div class="input-group-addon">ENQ -</div>
				      	<input type="text" class="form-control" id="enqNameInput">
				      	<div class="input-group-addon" onclick="enqDtlForAdminHold();">
							<i class="glyphicon glyphicon-search"></i>
						</div>
				    </div>
				  </div>
				</div>
				<div class="clearfix"></div>
				<div class="col-md-12">
					<table class="table table-bordered" id="enqDtlTable">
						<thead>
							<tr>
								<th>ENQ Name</th>
								<th>Mobile</th>
								<th>Name</th>
							</tr>
						</thead>
						<tbody>
							<tr><td colspan='3' style="text-align:center;">No records found</td></tr>
						</tbody>
					</table>
					<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
			</div>
			
			
			
			<div class="clearfix"></div>
			<div class="form-group col-md-6" id="userListField">
				<label>User</label>
				<select class='form-control' id='userListInventory'>
		      		<option value="">Select</option>
		      	</select>
			</div>
			<div class="clearfix"></div>
			<div class="form-group col-md-12">
				<label>Reason</label>
				<textarea class='form-control' id="holdBlockReasonInput" rows="4" cols="50"></textarea>
			</div>
	        
	        <small id="holdBlockInputInfo" style="color:#d10000;"></small>
	        <div class="clearfix"></div>
	      </div>
	      <div class="clearfix"></div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-primary btn-default" data-dismiss="modal">Close</button>
	        <button id="tempModalBtn" onclick="SaveForHold('temp')" 	type="button" style="color: #fff; background-color: #0077b9;" class="btn btn-primary">Hold</button>
	        <button id="tempEOIModalBtn" onclick="SaveForHold('eoi_block')" 	type="button" style="color: #fff; background-color: #0077b9;" class="btn btn-primary">EOI Hold</button>
	        <button id="blockModalBtn" onclick="SaveForHold('block')" 	type="button" style="color: #fff; background-color: #0077b9;" class="btn btn-primary">Block</button>
	      </div>
	    </div>
	  </div>
	</div>
	