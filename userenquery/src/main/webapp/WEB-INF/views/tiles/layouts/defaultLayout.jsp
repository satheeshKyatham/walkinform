<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>
 
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><tiles:getAsString name="title" /></title>
	<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css' />" type="text/css" />
	<link href="<c:url value='/resources/css/bootstrap.css' />"  rel="stylesheet"></link>
	
	<link rel="stylesheet" href="<c:url value='/resources/css/inner.css?v=${sessionScope.version}' />">
	<link rel="stylesheet" href="<c:url value='/resources/css/style.css?v=${sessionScope.version}' />">
	<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
	<%-- <script src="<c:url value='/resources/js/jquery.min.js' />"></script> --%>
	
	
	<script src="<c:url value='/resources/js/jquery-1.12.4.min.js'/>"></script>
	
	
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
  
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
	
	
</head>
 
<body>
		 <%--  <header id="header">
			<tiles:insertAttribute name="header" />
		</header>   --%>
		<nav class="navbar topMainBar">
		<div class="container">
	<%@ include file="/WEB-INF/views/pages/header.jsp" %>
		 </div>
		 </nav>
	
		<section id="sidemenu">
			<tiles:insertAttribute name="menu" />
		</section>
			
		<section id="site-content">
			<tiles:insertAttribute name="body" />
		</section>
		
		  <%-- <footer id="footer">
			<tiles:insertAttribute name="footer" />
		</footer>  --%>
</body>
</html>