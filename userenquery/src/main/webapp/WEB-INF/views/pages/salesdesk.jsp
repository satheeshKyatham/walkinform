<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	 response.addHeader("Expires","0");
	 response.addHeader("Pragma","no-cache");
	 response.setHeader("Cache-Control","no-cache,no-store, must-revalidate, pre-check=0, post-check=0, max-age=0, s-maxage=0");
	 response.addHeader("X-Frame-Options", "DENY");
	 %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

 <%! HttpSession session ; %>
   
 
 <% 
 session = request.getSession(); 
	
 	if(session.getAttribute("USERID")==null)
	{
		response.sendRedirect("index");
	}	
 %>
<title>Sales Desk</title>
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css' />" type="text/css" />
 <script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>

<script src="<c:url value='/resources/js/salesdesk.js?v=20.2'/>"></script>
<script src="<c:url value='/resources/js/jquery-1.12.4.min.js'/>"></script>
</head>
<body>
  
  <!-- <input type="text" > -->
  <div class="col-md-8" style="margin-top:10px; margin-bottom:10px;">
  <input type="hidden" id="userid" value="<%= session.getAttribute("USERID")%>" />
  <input type="hidden" id="proid" value="<%= session.getAttribute("PROID")%> "/>
   <input type="hidden" id="role" value="<%= session.getAttribute("ROLE")%> "/>
  <input type="text" id="useremail" value="<%= session.getAttribute("USEREMAIL")%>" />
   <input type="text" id="proame" value="<%= session.getAttribute("PRONAME")%>" />
  
  <table>
    <!-- here should go some titles... -->
    <tr>
        <th>No</th>
        <th>Type</th>
        <th>Name</th>
        <th>Count</th>
    </tr>
    <c:forEach items = "${tokens}"    var ="tokens" varStatus="loop"  >
    <tr>
        <td>
           ${loop.index+1}
        </td>
        <td>
            <input type="radio"  name="tokentype"  value="${tokens.type}">${tokens.type}</input>
        </td>
        <td>
             <label> ${tokens.typeName}</label>
        </td>
        <td>
           <label> ${tokens.typeCount}</label>
        </td>
    </tr>
    </c:forEach>
</table> 
	  
	  
	  
	  
      <button  onclick ="validateNext();">  
		<span>Next</span>
	  </button>  
	  	 
		<div class="clearfix"></div>
	</div> 
	
	
 
	
</body>
	
</html>