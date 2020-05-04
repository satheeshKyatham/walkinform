<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%! String projectname,projectid ,projectrole,userid;%>
<%HttpSession ses=request.getSession(); 
if(ses!=null){
	  projectname=(String)ses.getAttribute("PRONAME");
	  projectid=(String)ses.getAttribute("PROID");
	  projectrole=(String)ses.getAttribute("ROLE");
	 userid=(String)ses.getAttribute("USERID");
	
}else{
	 // Todo Login page
}
%> 

<nav>
	<%--  <a href="${pageContext.request.contextPath}/"><img style="width: 100%;  height: 150px;"class="logo" src="${pageContext.request.contextPath}/resources/images/logo.png"></a> --%>
	 <div class="menuLogo">
		<img style="width: 100%;  height: 70px;"class="logo" src="${pageContext.request.contextPath}/resources/images/logo.png">
		<span>Queue</span>
	</div>
	
	<ul id="menu">
	
	
	<li><a href="${pageContext.request.contextPath}/project">Project Master</a></li>
	<li><a href="${pageContext.request.contextPath}/usermaster">User Master</a></li>
	<li><a href="${pageContext.request.contextPath}/projectassign?regionid=Mumbai">User / Project Mapping</a></li>
	<li><a href="${pageContext.request.contextPath}/userprojectmap?regionid=Mumbai">Upload Launch Entry</a></li>
	<li><a href="${pageContext.request.contextPath}/assigndesk?id=3&projectid=a1l6F000005QQvmQAG">Assign Desk</a></li>
	<li><a href="${pageContext.request.contextPath}/paymentPlanBSP">Payment Plan BSP Addition</a></li>
	<li><a href="${pageContext.request.contextPath}/paymentPlanDue">Payment Plan Due</a></li>
	<li><a href="${pageContext.request.contextPath}/towerPPExclusion">Tower PP Exclusion</a></li>
	<li><a href="${pageContext.request.contextPath}/tnc">Cost Sheet T&C</a></li>
	<li><a href="${pageContext.request.contextPath}/tncEOI">EOI T&C</a></li>
	<li><a href="${pageContext.request.contextPath}/paymentPlanRanking">Payment Plan Ranking</a></li>
	<li><a href="${pageContext.request.contextPath}/schemeCharges">Scheme Charges</a></li>
	<li><a href="${pageContext.request.contextPath}/carParkCharges">Car Park Changes</a></li>
	<li><a href="${pageContext.request.contextPath}/otherCharges">Other Charges</a></li>
	<li><a href="${pageContext.request.contextPath}/admininventory/a1l6F0000080iilQAA/Godrej%20Lake%20Garden/80/">Inventory</a></li>
	<li><a href="${pageContext.request.contextPath}/createdoffers?projectid=a1l6F0000080irTQAQ">Created Offers List</a></li>
	<li><a href="${pageContext.request.contextPath}/triggerLog">Trigger Log</a></li>
	
	<li><a href="${pageContext.request.contextPath}/saleslogin">Logout</a></li>
	</ul> 
</nav>