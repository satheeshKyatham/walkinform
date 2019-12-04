<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@page import="java.util.HashMap"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="col-md-10">
	<div class="form-group col-md-4">
		<label>Region</label>
		<select class="form-control" id="regionListForMaster" onchange="projectDataListForMaster()"> </select>
	</div>
	<div class="form-group col-md-4">
		<label>Project</label>
		<select class="form-control" id="projectDataListForMaster"> </select>
	</div>
	<div class="clearfix"></div>
	<hr>
	<div class="clearfix"></div>
	
	<div class="col-md-4">
		<div class="form-group">
			<label>Source</label>
			<input class="form-control" id="sourceInput" />
		</div>
		<div class="clearfix"></div>
		<button type="submit" class="btn btn-default btn-primary" onclick="insertMaster('source')">Submit</button>
	</div>
	
	<div class="col-md-4">
		<div class="form-group">
			<label>Site</label>
			<input class="form-control" id="siteInput" />
		</div>
		<div class="clearfix"></div>
		<button type="submit" class="btn btn-default btn-primary" onclick="insertMaster('site')">Submit</button>
	</div>
	
	<div class="col-md-4">
		<div class="form-group">
			<label>Promotional</label>
			<input class="form-control" id="promotionalInput" />
		</div>
		<div class="clearfix"></div>
		<button type="submit" class="btn btn-default btn-primary" onclick="insertMaster('promotional')">Submit</button>
	</div>
	<div class="clearfix"></div>
</div>