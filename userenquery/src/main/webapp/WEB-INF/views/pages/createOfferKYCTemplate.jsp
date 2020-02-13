<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<input id="pageContext" value="${pageContext.request.contextPath}" style="display:none;"/>

<!-- Offer Application Form -->
<input id="appProjectNameOffer" style="display:none;">

<div id="printApplicationFormOffer" class="col-md-8" style="border: 1px dotted #ccc; margin: 0 auto; float: none; margin-top: 40px; padding-top: 15px;"> <!-- style="display:none;" -->
	
	<div id="templateContent"></div>
	
</div>