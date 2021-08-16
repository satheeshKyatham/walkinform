<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="">
	<div class="row">
		<div class="col-md-3 form-group">
			<label class="controlLabel required">Project <strong style="color: #f00;">*</strong></label>
           	<select class="form-control" id="projectDataList" onchange="floorRefresh (); towerList ();">
           		<option>Select</option> 
           	</select>
		</div> 
		<div class="col-md-3 form-group">
			<label class="controlLabel required">Tower <strong style="color: #f00;">*</strong></label>
           	<select class="form-control multiselectDD" multiple="multiple" id="towerMst" onchange="floorList()" style="height:34px;">
           		<option>Select</option>
           	</select>	
		</div>
		<div class="col-md-3 form-group">
			<label class="controlLabel required">Floor <strong style="color: #f00;">*</strong></label>
           	<select class="form-control multiselectDD" multiple="multiple" id="floorMst" style="height:34px;">
           		<option>Select</option>
           	</select>	
		</div>
		<div class="col-md-3 form-group">
			<label class="controlLabel required">
				Date <strong style="color: #f00;">*</strong>
			</label>
			<input class="form-control" id="edpDate" type="date"/>
		</div> 
		<div class="clearfix"></div>
	</div> 
	<a class="btn btn-primary" onclick="updateEDP();" style="background-color: #000; color: #fff;">
		<span class="icon text-white-50" id="edpUpdateBtn">
			<i class="fa fa-spinner fa-spin" style="color:#fff !important; display:none;"></i>
		</span>
		<span class="text">Update</span>
	</a> 
</div>

<!-- 
<div class="col-md-12">
	<ul class="nav nav-tabs" id="myTab" role="tablist">
	  <li class="nav-item" role="presentation">
	    <a class="nav-link active" id="profile-tab" data-toggle="tab" href="#EDPTab" role="tab" aria-controls="profile" aria-selected="false">Expected date of possession </a>
	  </li> 
	</ul>
	<div class="tab-content" id="myTabContent">
		<div class="tab-pane fade show active" id="EDPTab" role="tabpanel" aria-labelledby="profile-tab">
	  		<div class="col-md-12"> 
				<div class="card shadow"> 
				  <div class="card-body"> 
				    <div class="">
						<div class="row">
							<div class="col-md-3 form-group">
								<label class="controlLabel required">Project</label>
				             	<select class="form-control" id="projectDataList" onchange="floorRefresh (); towerList ();">
				             		<option>Select</option> 
				             	</select>
							</div> 
							<div class="col-md-3 form-group">
								<label class="controlLabel required">Tower</label>
				             	<select class="form-control multiselectDD" multiple="multiple" id="towerMst" onchange="floorList()">
				             		<option>Select</option>
				             	</select>	
							</div>
							<div class="col-md-3 form-group">
								<label class="controlLabel required">Floor</label>
				             	<select class="form-control multiselectDD" multiple="multiple" id="floorMst">
				             		<option>Select</option>
				             	</select>	
							</div>
							<div class="col-md-3 form-group">
								<label class="controlLabel required">
									Date
								</label>
								<input class="form-control" id="edpDate" type="date"/>
							</div> 
							<div class="clearfix"></div>
				        </div> 
						<a class="btn btn-primary btn-icon-split btn-sm abtn" onclick="updateEDP();">
							<span class="icon text-white-50">
								<i class="fas fa-check" id="edpUpdateBtn"></i>
							</span>
							<span class="text">Update</span>
						</a> 
				    </div>
				  </div>
				</div>
			</div>
		</div>
	</div>
</div> -->