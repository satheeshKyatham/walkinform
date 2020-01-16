<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="commonLoad" id="mainPageLoad" style="display: none;"></div>
<table class="table table-bordered" id="createdOfferTable">
		<thead>
			<tr>
				<th>Enquiry No</th>
				<th>Contact Name</th>
				<th>Payment Plan</th>
				<th>Offer Name</th>
				<th>Scheme Name</th>
				<th>Scheme Rate</th>
				<th>Balance Amount</th>
				<th>Description</th>
				<th>Final Amount</th>
				<th>Cost Sheet</th>
				<th>Application</th>
			</tr>
		</thead>
		<tbody>
	</tbody>
</table>
<div id="applicationForm">
	<%@ include file="/WEB-INF/views/pages/createdOfferKyc.jsp" %>
</div>

