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
	
	
	<div class="col-md-10" style="margin: 0 auto; float: none;">
		<div class="mainCont " style="padding-top: 10px;">
			<div class="">
				<div class="form-group col-md-5" style="display:none;">
					<label>Parking Tower</label>
					<select class="form-control" id="parkingTowerMst" onclick="parkingLocationList();"> </select>
				 </div>
				 
				 <div class="form-group col-md-5">
					<label>Parking Type</label>
					<select class="form-control" id="parkingTypeCP">
						<option value = ''>Select</option>
						<option value = 'Covered'>Covered</option>
						<option value = 'Stack Parking'>Stack Parking</option>
						<option value = 'Tandem'>Tandem</option>
					</select>
				 </div>
				 
				 <div class="form-group col-md-3 col-sm-6">
					<label>Parking Location</label>
					<select id="parkingLocationMst" class="form-control">
						<option value="All">All</option>
					</select>
				 </div>
				 <div class="form-group col-md-3 col-sm-6">
					<label>Parking Size</label>
					<select id="parkingCategory" class="form-control">
						<option value='All'>All</option>
						<option value='Large'>Large</option>
						<option value='Small'>Small</option>
						<option value='Standard'>Standard</option>
					</select>
				 </div> 
					
				<div class="form-group col-md-3">
					<label>Unit Status </label>
					<select id="searchadminparking" class="form-control">
						<option value='All'>All</option>
						<option value='f'>Action against inventory</option>
						<option value='t'>Held/ Blocked inventory</option>
					</select>
				 </div>	
					
				<div class="form-group col-md-3 col-sm-6">
					<label> &nbsp; </label>
					<button onclick="getParkingRec()" style="line-height:0; color: #fff; background-color: #0077b9;" class="form-control btn btn-primary">
						Get Details
					</button>
				</div>
				
				<div class="form-group col-md-1 col-sm-6">
					<label> &nbsp; </label>
					<button onclick="getParkingRec()" style="line-height:0; padding:0" class="form-control btn btn-default">
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
			<div class="form-group col-md-3" id="btnParkingHOldSave" style="display:none;">
				<label> &nbsp; </label>
				<button onclick="holdBlockParking('parkingHoldBtn')" style="line-height:0; color: #fff; background-color: #0077b9;" class="form-control btn btn-primary">
					Hold against flat/unit 
				</button>
			</div>
			<div class="form-group col-md-3" id="btnParkingHOldSave2" style="display:none;">
				<label> &nbsp; </label>
				<button onclick="holdBlockParking('blockBtn')" style="line-height:0; color: #fff; background-color: #0077b9;" class="form-control btn btn-primary">
					Block 
				</button>
			</div>
			<div class="form-group col-md-3" id="btnParkingreleasesave" style="display: none;">
				<label> &nbsp; </label>
				<button onclick="SaveParkingForRelease()" id="releaseParkingBtnAdmin" style="line-height:0; color: #fff; background-color: #0077b9;" class="form-control btn btn-primary">
					Release Unit
				</button>
			</div>
			<div class="clearfix"></div>

		<div class="inventoryLegendsCol col-md-12">
					<label class="containerCheckbox">Available
					  <input  type="checkbox" >
					  <span class="checkmark ilAvailable"></span>
					</label> 
					<label class="containerCheckbox">Sold Out
					  <input>
					  <span class="checkmark soldOut"></span>
					</label>
					<label class="containerCheckbox">Block
					  <input>
					  <span class="checkmark unitBlockAdmin"></span>
					</label> 
					<label class="containerCheckbox">Hold against flat/unit
					  <input>
					  <span class="checkmark unitEOIBlockAdmin"></span>
					</label> 
			<div class="clearfix"></div>
		</div>
		
		
		<div class="clearfix"></div>
		
		<div class="inventoryBreadcrumb">
			
			<div class="clearfix"></div>
		</div>
		<div class="clearfix"></div>	

		<div class="clearfix"></div>
			
			<div class="floorBand">
				<div class="floorTitle">	
					  <div class="fcData">Type</div>
				</div>	
				<div class="flatTitle">	
					  <div class="fcData">Parking No.</div>
				</div>
				<div class="clearfix"></div>
			</div>
			
			<div id="parkingData" style="position: relative;">
			   <div class="commonLoad" style="display: none;"></div>
			   <!--  <div class="floorBand">
			      <div class="floorCel">
			         <div class="fcData">Podium 1</div>
			      </div>
			      
			      <div style="display: flex; width:100%;">
				      <div class="unitCel" style="cursor: pointer;">
				         <div  class="fcData  unitAvailable">
				         	 <div class="checkbox" style="margin:0 !important">
							    <label>
							      <input type="checkbox"> 0102
							    </label>
							  </div>
				         	<span class=""></span>
				         </div>
				      </div>
				      <div class="unitCel dropdown" style="cursor: pointer;">
				         <div type="button" data-toggle="dropdown" class="fcData dropdown-toggle unitSold">0103<span class=""></span></div>
				      </div>
				      <div class="clearfix"></div>
				   </div>
			      <div class="clearfix"></div>
			   </div>
			   <div class="floorBand">
			      <div class="floorCel">
			         <div class="fcData">Podium 2</div>
			      </div>
			      
			      <div style="display: flex; width:100%;">
					<div class="unitCel dropdown" style="cursor: pointer;">
						<div type="button" data-toggle="dropdown" class="fcData dropdown-toggle unitSold">0201<span class=""></span></div>
					</div>
					<div class="unitCel dropdown" style="cursor: pointer;">
						<div type="button" data-toggle="dropdown" class="fcData dropdown-toggle unitSold">0202<span class=""></span></div>
					</div>
					<div class="unitCel dropdown" style="cursor: pointer;">
						<div type="button" data-toggle="dropdown" class="fcData dropdown-toggle unitSold">0203<span class=""></span></div>
					</div>
					<div class="unitCel dropdown" style="cursor: pointer;">
						<div type="button" data-toggle="dropdown" class="fcData dropdown-toggle unitSold">0204<span class=""></span></div>
					</div>
					<div class="clearfix"></div>
			      </div>
			      <div class="clearfix"></div>
			      <div style="display: flex; width:100%;">
					<div class="unitCel dropdown" style="cursor: pointer;">
						<div type="button" data-toggle="dropdown" class="fcData dropdown-toggle unitSold">0201<span class=""></span></div>
					</div>
					<div class="unitCel dropdown" style="cursor: pointer;">
						<div type="button" data-toggle="dropdown" class="fcData dropdown-toggle unitSold">0202<span class=""></span></div>
					</div>
					<div class="unitCel dropdown" style="cursor: pointer;">
						<div type="button" data-toggle="dropdown" class="fcData dropdown-toggle unitSold">0203<span class=""></span></div>
					</div>
					<div class="unitCel dropdown" style="cursor: pointer;">
						<div type="button" data-toggle="dropdown" class="fcData dropdown-toggle unitSold">0204<span class=""></span></div>
					</div>
					<div class="clearfix"></div>
			      </div>
			      
			      <div class="clearfix"></div>
			   </div>-->
			</div>
			<div style="padding: 10px 0px 10px 0px; text-align: center; position: sticky; bottom: 0;  background-color: #f5f5f5; border-top: 1px solid #ccc;">
				<button onclick="proceedParking('NOPARKING');" style="width: auto; line-height:0; color: #000 !important; background-color: #fff !important; margin-right:20px;" class="form-control btn btn-primary">
					Continue without parking
				</button>
				<button onclick="proceedParking('PARKING');" style="width: auto; line-height:0; color: #fff; background-color: #0077b9;" class="form-control btn btn-primary">
					Continue with <span id="proceedParking"></span>
				</button>
			</div>
			
			
			<div class="clearfix"></div>
		</div>
		<div class="clearfix"></div>
	</div>
	
	
	<!-- Model -->
	<div class="modal fade" id="holdBlockParkingModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="ModalLabelParkingAdmin"> </h4>
	      </div>
	      <div class="modal-body"> 
			<div class="clearfix"></div>
			
			<div id="flatsfidInputField">
				<div class="form-group col-md-7">
				  <div class="form-group">
				    <label>Flat/Unit Name</label>
				    <div class="input-group">
				    	<div class="input-group-addon">Flat/Unit Name -</div>
				      	<input type="text" class="form-control" id="parkingNameInput" placeholder="GHS2T10G03">
				      	<div class="input-group-addon" onclick="flatDtlForAdminHold();">
							<i class="glyphicon glyphicon-search"></i>
						</div>
				    </div>
				  </div>
				</div>
				<div class="clearfix"></div>
				<div class="col-md-12">
					<table class="table table-bordered" id="flatDtlTable">
						<thead>
							<tr>
								<th>Tower</th>
								<th>Floor</th>
								<th>Typology</th>
								<th>Flat/Unit Name</th>
								<th>Flat/Unit No.</th>
							</tr>
						</thead>
						<tbody>
							<tr><td colspan='5' style="text-align:center;">No records found</td></tr>
						</tbody>
					</table>
					<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
			</div>
			
			
			
			<div class="clearfix"></div>
			<!-- <div class="form-group col-md-6" id="userListField">
				<label>User</label>
				<select class='form-control' id='userListInventory'>
		      		<option value="">Select</option>
		      	</select>
			</div> -->
			<div class="clearfix"></div>
			<div class="form-group col-md-12">
				<label>Reason</label>
				<textarea class='form-control' id="holdBlockReasonParking" rows="4" cols="50"></textarea>
			</div>
	        
	        <small id="holdBlockParkingInfo" style="color:#d10000;"></small>
	        <div class="clearfix"></div>
	      </div>
	      <div class="clearfix"></div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-primary btn-default" data-dismiss="modal">Close</button>
	        <!-- <button id="tempModalBtn" onclick="SaveForHold('temp')" 	type="button" style="color: #fff; background-color: #0077b9;" class="btn btn-primary">Hold</button> -->
	        <button id="tempParkingModalBtn" onclick="SaveForHoldParking('eoi_block')" 	type="button" style="color: #fff; background-color: #0077b9;" class="btn btn-primary">Hold</button>
	        <button id="blockParkingModalBtn" onclick="SaveForHoldParking('block')" 	type="button" style="color: #fff; background-color: #0077b9;" class="btn btn-primary">Block</button>
	      </div>
	    </div>
	  </div>
	</div>