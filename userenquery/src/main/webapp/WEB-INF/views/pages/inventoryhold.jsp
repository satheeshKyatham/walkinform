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
<div class="">
				
				<div class="form-group col-md-5">
					<label>Tower</label>
					<!-- <select id="towerMsts" onchange="inventoryUnitTypeMst()" class="form-control"> </select> -->
					<select id="towerMsts" onchange="inventoryUnitTypeMst()" class="form-control"> </select>
				 </div>
				 
				 <div class="form-group col-md-3">
					<label>Typology</label>
					<select id="typoMst" class="form-control">
						<option value='typologyAll'>All</option>
					</select>
				 </div>
				 
				 <div class="form-group col-md-3">
					<label>Unit Status </label>
					<select id="searchadmintype" class="form-control">
						<option value='all'>All</option>
						<option value='f'>Available</option>
						<option value='t'>Hold</option>
					</select>
				 </div>
				<div class="form-group col-md-3">
					<label> &nbsp; </label>
					<button onclick="getInventoryRec()" style="line-height:0; color: #fff; background-color: #0077b9;" class="form-control btn btn-primary">
						Get Details
					</button>
				</div>
				<div class="form-group col-md-3" id="btnholdsave" style="display: none;">
					<label> &nbsp; </label>
					<button  onclick="SaveForHold('temp')" style="line-height:0; color: #fff; background-color: #0077b9;" class="form-control btn btn-primary">
						Temporary Hold 
					</button>
				</div>
				<div class="form-group col-md-3" id="btnholdsave2" style="display: none;">
					<label> &nbsp; </label>
					<button  onclick="SaveForHold('block')" style="line-height:0; color: #fff; background-color: #0077b9;" class="form-control btn btn-primary">
						Unit Block 
					</button>
				</div>
				<div class="form-group col-md-3" id="btnreleasesave" style="display: none;">
					<label> &nbsp; </label>
					<button  onclick="SaveForRelease()" style="line-height:0; color: #fff; background-color: #0077b9;" class="form-control btn btn-primary">
						Release Unit
					</button>
				</div>
				
				<div class="form-group col-md-1">
					<label> &nbsp; </label>
					<button onclick="getInventoryRec()" style="line-height:0; padding:0" class="form-control btn btn-default">
						<i class="glyphicon glyphicon-repeat"></i>
					</button>
				</div>
				<div class="clearfix"></div>
			</div>
<script src="<c:url value='/resources/js/separate/inventoryholded.js?v=16'/>"></script>	