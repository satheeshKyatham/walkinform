<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	 response.addHeader("Expires","0");
	 response.addHeader("Pragma","no-cache");
	 response.setHeader("Cache-Control","no-cache,no-store, must-revalidate, pre-check=0, post-check=0, max-age=0, s-maxage=0");
	 response.addHeader("X-Frame-Options", "DENY");
	 %>
<input id="pageContext" value="${pageContext.request.contextPath}" style="display:none;"/>

<div>
	<ul class="nav nav-tabs" role="tablist">
		<li role="presentation" class="active" id="journeyTab">
			<a href="#journey" role="tab" data-toggle="tab" onclick="getAllotmentDashboardReport();">Journey So Far <i class="fa fa-spinner fa-spin" style="display:none;"></i></a>
		</li>
		<li role="presentation" id="soldCarparkTab">
			<a href="#soldCarpark" role="tab" data-toggle="tab" onclick="carparkCountList();">Sold Car Park <i class="fa fa-spinner fa-spin" style="display:none;"></i></a>
		</li>
		<li role="presentation" id="facingSoldUnitTab">
			<a href="#facingSoldUnit" role="tab" data-toggle="tab" onclick="facingDashboard(); getUnitFacingCount();">Facing Wise Offer Created <i class="fa fa-spinner fa-spin" style="display:none;"></i></a>
		</li>
		<li role="presentation" id="towerSoldUnitTab">
			<a href="#towerSoldUnit" role="tab" data-toggle="tab" onclick="towerDashboard();">Tower Wise Offer Created <i class="fa fa-spinner fa-spin" style="display:none;"></i></a>
		</li>
	</ul>

	<div class="tab-content" style="padding: 15px; border: 1px solid #ccc; border-top: 0;">
		<div role="tabpanel" class="tab-pane active" id="journey">
			<h4 style="margin-top:0">Allotment Journey So Far</h4>
			<table class="table table-bordered" id="allotmentmisReport" style="font-size: 16px;">
				<thead>
					<tr>
						<th>Total Saleable Area Sold : </th>
						<th>Total BSP (Subject to payment realization) : </th>
						<th>Allotment Walk-ins(E Token) </th>
						<th>Total Units sold  : </th>
						<th>KYC Approved  : </th>
						<th>Total Application/Booking </th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			<div class="clearfix"></div>            
			<!-- info box -->
			<div id="reportInfocontainer">
				<div class="col-md-4" style="float:left; padding-right: 0;">
					<div class="col-lg-6 col-xs-6">
						<div class="small-box bg-aqua" style="background-color: #929eaa !important;">
							<div class="inner">
						    	<h3 id="holdInvId"></h3>
						        <p>Hold Inventory</p>
							</div>
							<div class="icon">
						    	<i class="fa fa-lock"></i>
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
				    <div class="col-lg-6 col-xs-6">
						<div class="small-box bg-aqua" style="background-color: #00e0ec !important;">
							<div class="inner">
                          		<h3 id="blockInvId"></h3>
                          		<p>Block Inventory</p>
				            </div>
				            <div class="icon">
				            	<i class="fa fa-ban"></i>
				            </div>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
			    
			    <div class="col-md-8" style="float:left; padding-left: 0; display:none !important;" id="coveredcarpark_id">
	             	<div class="col-lg-3 col-xs-6">
	               		<div class="small-box bg-aqua">
	                    	<div class="inner">
								<h3 id="covered1bhkid"></h3>
	                            <p>Sold Car Park Covered (1 bhk)</p>
	                   		</div>
	                   		<div class="icon">
	                        	<i class="fa fa-car"></i>
	                   		</div>
	                    </div>
	                    <div class="clearfix"></div>
	             	</div>
					<div class="col-lg-3 col-xs-6">
						<div class="small-box bg-aqua">
							<div class="inner">
								<h3 id="covered2bhkid"></h3>
								<p>Sold Car Park Covered (2 bhk)</p>
							</div>
							<div class="icon">
								<i class="fa fa-car"></i>
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="col-lg-3 col-xs-6">
						<div class="small-box bg-aqua">
							<div class="inner">
								<h3 id="covered3bhkid"></h3>
								<p>Sold Car Park Covered (3 bhk)</p>
							</div>
							<div class="icon">
								<i class="fa fa-car"></i>
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
			    <div class="clearfix"></div>
			</div>
			<!-- END info box -->
			
			<div class="commonLoad" id="mainPageLoad" style="display: none;"></div>
			<%-- <form:form action="downloadEOICSV" method="post">
				<div id="eoiReportCSVInputCol"> </div>
			
				<fieldset style="width: 400px;">
					<input type="submit" value="Downlaod In Excel">
				</fieldset>
			</form:form> --%>
			<div class="well center-block">
				<div class="form-inline">
					<i class="glyphicon glyphicon-filter" style="border-right: 1px solid #333; margin-right: 10px; padding-right: 10px;"></i>
					<div class="form-group">
						<label for="exampleInputNameDate">From Date</label> 
						<input class="form-control" type="date" name="" id="txtAllotFromDate">
					</div>
					<div class="form-group">
						<label for="exampleInputNameDate">To Date</label> 
						<input class="form-control" type="date" name="" id="txtAllotToDate">
					</div>
					<div class="form-group">
						<label for="exampleInputNameDate">Search</label> 
						<input class="form-control" type="button" value="Search" name="Search" onclick="getAllotmentDashboardReport()"/>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
			
			<div class="wrapperEOIRepo1">
				<div class="divEOI1"></div>
			</div>
			<div class="wrapperEOIRepo2">
				<div> </div>
				<div class="divEOI2" style="overflow: auto;">
					<table class="table table-bordered" id="allotmentReportTable">
						<thead>
							<tr>
								<th>Priority No.</th>
								<th>ENQ Name</th>
								<th>Customer Name</th>
								<th>Customer Mobile</th>
								<th>Customer Email</th>
								<th>Walk-in Source</th>
								<th>Sourcing Manager Name</th>
								<th>Vertical</th>
								<th>CP</th>
								<th>Closing Manager</th>
								<th>Request Source</th>
								<th>Enquiry Type</th>
								<th>Offer No.</th>
								<th>Offer Created By</th>
								<th>Offer Creation Date</th>
								<th>Super Area</th>
								<th>Carpet Area</th>
								<th>Unit Type</th>
								<th>Car Park Type</th>
								<th>Scheme Name</th>
								<th>Total Basic Price</th>
								<th>Sales Consideration</th>
								<!-- <th>Created By</th> -->
								<th>Booking Name</th>
								<th>Booking Status</th>
								<th>KYC Approved By</th>
								<th>Booking Amount</th>
								<th>Property Name</th>
								<th>Unit No</th>
								<th>5% Amount</th>
								<th>Balance Amt. w.r.t 5%</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div> 
			</div>
		</div>
		<div role="tabpanel" class="tab-pane" id="soldCarpark">
			<div id="carparkCountTbl">
				<div class="clearfix"></div>
			</div>
		</div>
		<div role="tabpanel" class="tab-pane" id="facingSoldUnit">
			<div id="unitFacingType">
				<div class="clearfix"></div>
			</div>
			
			<div id="unitFacingCount">
				<div class="clearfix"></div>
			</div>
			
		</div>
		<div role="tabpanel" class="tab-pane" id="towerSoldUnit">
			<div id="towerDashboard">
				<div class="clearfix"></div>
			</div>  
		</div>
	</div>
</div>