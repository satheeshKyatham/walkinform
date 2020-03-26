<%
/*
   This is the sample Checkout Page JSP script. It can be directly used for integration with CCAvenue if your application is developed in JSP. You need to simply change the variables to match your variables as well as insert routines (if any) for handling a successful or unsuccessful transaction.
*/
%>
<%@ page import = "java.io.*,java.util.*,com.ccavenue.security.*" %>
<html>
<head>
	<title>Sub-merchant checkout page</title>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>
	<%
	 /* String accessCode= "AVMB75FA72BU32BMUB";		//Put in the Access Code in quotes provided by CCAVENUES.
	 String workingKey = "2E669830C50D062CA89B2ED44260814C";    //Put in the 32 Bit Working Key provided by CCAVENUES.  */
	 
	 String accessCode= "AVAP91HC24BY51PAYB";		//Put in the Access Code in quotes provided by CCAVENUES.
	 String workingKey = "8F5A15D1CFDB862691F13C0676D87091";    //Put in the 32 Bit Working Key provided by CCAVENUES. 
	 Enumeration enumeration=request.getParameterNames();
	 String ccaRequest="", pname="", pvalue="";
	 while(enumeration.hasMoreElements()) {
	      pname = ""+enumeration.nextElement();
	      pvalue = request.getParameter(pname);
	      ccaRequest = ccaRequest + pname + "=" + pvalue + "&";
	      System.out.println(ccaRequest); 
	      System.out.println(request.getParameter(pname));
	 }
	 System.out.println(ccaRequest);
	 AesCryptUtil aesUtil=new AesCryptUtil(workingKey);
	 System.out.println(aesUtil);
	 String encRequest = aesUtil.encrypt(ccaRequest);
	 System.out.println(encRequest);
	 System.out.println("Description:"+aesUtil.decrypt(encRequest));
	%>
	
	<form id="nonseamless" method="post" name="redirect" action="https://secure.ccavenue.com/transaction.do?command=initiateTransaction"/> 
		<input type="hidden" id="encRequest" name="encRequest" value="<%= encRequest %>">
		<input type="hidden" name="access_code" id="access_code" value="<%= accessCode %>">
		<script language='javascript'>document.redirect.submit();</script>
	</form>
	
 </body> 
</html>
