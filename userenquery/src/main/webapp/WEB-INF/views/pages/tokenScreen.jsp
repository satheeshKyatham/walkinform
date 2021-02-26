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
<html>
	<head>
		<title>Godrej</title>
		<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css' />" type="text/css" />
		<style>
			.videoImageCol {}
			.eoiCardCol {}
			.footerTicker {position: fixed; bottom: 0; width: 100%;}
			body {font-size:4vmin;}
			.card {padding-bottom:2vh;} 
			.table>tbody>tr>td, .table>tbody>tr>th, .table>tfoot>tr>td, .table>tfoot>tr>th, .table>thead>tr>td, .table>thead>tr>th {padding-top:5px; padding-bottom:5px;}
			.table-bordered>tbody>tr>td, .table-bordered>tbody>tr>th, .table-bordered>tfoot>tr>td, .table-bordered>tfoot>tr>th, .table-bordered>thead>tr>td, .table-bordered>thead>tr>th {border: 1px solid}
			.tokenCol {color: #fff; text-align: center; background: rgb(10,87,140); background: linear-gradient(0deg, rgba(10,87,140,1) 0%, rgba(25,122,190,1) 100%);}
			
			.tokenTable tbody td:nth-child(2) {border-right: 0; border-left: 0;}
			.tokenTable thead th:nth-child(2) {border-right: 0; border-left: 0;} 
			.tokenTable thead th:nth-child(1) {text-align:center;}
			.tokenTable thead th {background: rgb(10,87,140); background: linear-gradient(0deg, rgba(10,87,140,1) 0%, rgba(25,122,190,1) 100%); color:#fff;}
			.assignedLine {border-right: 1vh solid #5bad03 !important ;} 
			.upcomingLine {border-right: 1vh solid #d6be06 !important ;}  
			
			/* BASE
------------------------------------------------------------ */
 
.eoiCountCard h2 { 
  margin:0 auto;
  padding:0;
  position: relative;
  top: 50%;
  transform: translateY(-50%);
  text-align: center;
}
/* Section and Corners Common
------------------------------------------------------------ */
.eoiCountCard { 
  position:relative; 
  display:inline-block; 
  width:100%; 
  height:100%; 
   
  overflow: hidden;
  /*max-width:960px; margin: 30px auto;*/ 
}
.black { 
  background: #000; 
  /*background: rgba(0,0,0,1.0);*/
  color:#fff; 
  box-shadow:0px 5px 15px 0px rgba(0, 0, 0, 0.4);
  /*transition: background 0.5s ease-in-out;*/
}
/*.black:hover { background: #333;}*/
.corner {
  position: absolute;
  /*background:red;*/
  background:none;
  width:6em;
  height:6em;
  font-size:2px;
  font-size:10px;
  opacity: 1.0;
  transition: opacity 0.3s ease-in-out;
}
.corner:after {
  position: absolute;
  content: '';
  display: block;
  width:0.2em;
  height:0.2em;
}
.topleft {
  top:1em;
  left:1em; 
  /* I do not know why, but it's better with this - */
  -webkit-transform:rotate(360deg); transform:rotate(360deg); 
}
.topright {  
  top:1em; right:1em; -webkit-transform:rotate(90deg); transform:rotate(90deg); 
}
.bottomleft { 
  bottom:1em; left:1em; -webkit-transform:rotate(270deg); transform:rotate(270deg); 
}
.bottomright { 
  bottom:1em; right:1em; -webkit-transform:rotate(180deg); transform:rotate(180deg); 
}


/* Corners
------------------------------------------------------------ */
      
.corner6:after {
  box-shadow:
    0.6em 0em #fff, 0.8em 0em #fff, 1.4em 0em #fff, 1.6em 0em #fff, 2.4em 0em #fff, 2.6em 0em #fff, 
    0.6em 0.2em #fff, 1.0em 0.2em #fff, 1.2em 0.2em #fff, 1.6em 0.2em #fff, 2.2em 0.2em #fff, 2.8em 0.2em #fff, 4.2em 0.2em #fff, 
    0.8em 0.4em #fff, 1.6em 0.4em #fff, 2.4em 0.4em #fff, 2.8em 0.4em #fff, 3.2em 0.4em #fff, 3.4em 0.4em #fff, 3.6em 0.4em #fff, 4.0em 0.4em #fff, 4.4em 0.4em #fff, 4.8em 0.4em #fff, 5.2em 0.4em #fff, 
    0em 0.6em #fff, 0.2em 0.6em #fff, 1.0em 0.6em #fff, 1.6em 0.6em #fff, 2.8em 0.6em #fff, 4.2em 0.6em #fff,
    0em 0.8em #fff, 0.4em 0.8em #fff, 1.6em 0.8em #fff, 2.6em 0.8em #fff,
    0.2em 1.0em #fff, 0.6em 1.0em #fff, 0.8em 1.0em #fff, 1.0em 1.0em #fff, 1.2em 1.0em #fff, 1.6em 1.0em #fff, 2.0em 1.0em #fff, 2.2em 1.0em #fff, 2.4em 1.0em #fff, 
    0.2em 1.2em #fff, 1.6em 1.2em #fff, 
    0em 1.4em #fff, 1.0em 1.4em #fff, 1.8em 1.4em #fff, 
    0em 1.6em #fff, 0.2em 1.6em #fff, 0.4em 1.6em #fff, 0.6em 1.6em #fff, 1.0em 1.6em #fff, 2.0em 1.6em #fff, 
    1.0em 1.8em #fff, 1.4em 1.8em #fff, 2.0em 1.8em #fff, 
    1.0em 2.0em #fff, 1.6em 2.0em #fff, 1.8em 2.0em #fff, 2.0em 2.0em #fff, 
    0.2em 2.2em #fff, 1.0em 2.2em #fff, 2.2em 2.2em #fff, 2.6em 2.2em #fff, 
    0em 2.4em #fff, 0.4em 2.4em #fff, 1.0em 2.4em #fff, 
    0em 2.6em #fff, 0.8em 2.6em #fff, 2.2em 2.6em #fff, 
    0.2em 2.8em #fff, 0.4em 2.8em #fff, 0.6em 2.8em #fff, 
    0.4em 3.2em #fff, 
    0.4em 3.4em #fff, 
    0.4em 3.6em #fff, 
    0.4em 4.0em #fff, 
    0.2em 4.2em #fff, 0.6em 4.2em #fff, 
    0.4em 4.4em #fff, 
    0.4em 4.8em #fff, 
    0.4em 5.2em #fff;
}
			
		</style>
	</head>
	<body>
		<div class="col-md-12" style="height:10vh" > 
			<div class="row" style="height:100%">	
				<div class="col-md-4 text-left" style="height:100%">
					<img src="<c:url value='/resources/images/gplLogo.jpg' />" style="height:100%"/>
				</div>
				<div class="col-md-4 text-center" style="height:100%; text-transform: uppercase;  position: relative;">
					<span style="position: absolute; top: 45%; width: 100%; display: block; line-height: 0;">
						<span style="border-bottom: 0.5vh solid;" id="projectname"></span>
					</span>
				</div>
				<div class="col-md-4 text-right" style="height:100%; padding-top:1vh; padding-bottom:1vh;">
					<img src="<c:url value='/resources/images/logo6.png' />" style="height:100%"/>
				</div>
				<div class="clearfix"></div>
			</div>	
			<div class="clearfix"></div>
		</div>
		<div class="clearfix"></div>
		<div class="col-md-5 leftMainCol" style="height:80vh"> 
			<div class="card mediaCol" style="height:50%;">
				<div class="videoImageCol" style="height:100%; text-align: center; background-color: #000;">
					<!-- <video height="100%" controls autoplay loop>
					  <source src="https://customercare.godrejproperties.com/sites/default/files/2020-05/RW%20WALKTHROUGH%20COMPRESSED%20CUT.mp4" type="video/mp4">
					  Your browser does not support HTML video.
					</video> -->
				</div>
				<div class="clearfix"></div>
			</div>	
			<div class="clearfix"></div>
			
			
			<div class="card eoiMainCol" style="height:50%;">
				<div class="eoiCountCard" style="padding: 1vh; border: 1vh solid;">
					<div class="black" style="height: 100%;">	 
						<div class="corner corner6 topleft"></div>
						<div class="corner corner6 topright"></div>
						<div class="corner corner6 bottomleft"></div>
						<div class="corner corner6 bottomright"></div>
						<div style="position: absolute; top: 20%; width: 100%; font-weight: bolder; text-align:center; font-size: 6vmin;">
							<span id="eoiCount"></span> 
						</div>
						<div style="position: absolute;top: 40%;width: 100%; text-align:center; font-size: 5vmin;">
							<span id="eoiText"></span>
						</div>
					</div>	 
				</div>
			</div>
			
			 
			
			
			<!-- <div class="card" style="height:50%;">
				<div class="eoiCardCol card" style="height:100%; border: 1px solid; text-align: center; position: relative;">
					<div style="position: absolute; top: 30%; width: 100%; font-weight: bolder;">353</div>
					<div style="position: absolute;top: 50%;width: 100%;">Total EOI Received</div>
				</div>
				<div class="clearfix"></div>
			</div>	 -->
			<div class="clearfix"></div>
		</div>
		<div class="col-md-7 rightMainCol" style="height:80vh">
			<div class="row" style="height:100%;">
				<div class="col-md-12" style="height:100%;">       
					<div id="myCarousel" class="carousel slide" data-interval="3000" data-ride="carousel" style="height:100%;">
						<div class="carousel-inner" style="height:100%;">
							<div class="item active" style="height:100%;">
								<table class="table table-bordered tokenTable" id="tokenAssignedTable" style="margin-top:-0.9vh; font-size:3.5vmin; border-collapse: separate; border-spacing: 0 0.9vh; border: 0;">
									<thead>
										<tr>
											<th style="border-top: 1px solid;" colspan="3">Assigned Token</th> 
										</tr>
										<tr>
											<th style="border-top: 1px solid;">Token</th>
											<th style="border-top: 1px solid;">Customer</th>
											<th style="border-top: 1px solid;">Closing manager</th>
										</tr>
									</thead>
									<tbody>
										 
								   </tbody>
								</table>
							</div>
							<div class="item" style="height:100%;">
								<table class="table table-bordered tokenTable"  id="tokenUpcomingTable" style="margin-top:-0.9vh; font-size:3.5vmin; border-collapse: separate; border-spacing: 0 0.9vh; border: 0;">
									<thead>
										<tr>
											<th style="border-top: 1px solid;" colspan="3">Upcoming Token</th> 
										</tr>
										<tr>
											<th style="border-top: 1px solid;">Token</th>
											<th style="border-top: 1px solid;">Customer</th>
											<th style="border-top: 1px solid;">Closing manager</th>
										</tr>
									</thead>
									<tbody>
										 
								   </tbody>
								</table>
							</div>  
						</div>
					</div>
					
						
					
				</div>
				<div class="clearfix"></div> 
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="clearfix"></div>
		<div class="footerTicker" style="background-color: #eaeaea; height:10vh">
			 <div style="float:left; width:10%; height:100%; background-color: #000; color: #fff; ext-align: center; position: relative;">
			 	<span style="position: absolute; top:25%;"> UPDATES: </span> 
			 </div> 
			 <div style="float:left; width: 90%; height:100%; position: relative;">
			 	<marquee style="position: absolute; top:25%;">Book online @ godrejproperties.com or download app "GODREJ PROPERTIES LIMITED"</marquee>
			 </div> 
			<div class="clearfix"></div>
		</div>	
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  		<script src="<c:url value='/resources/js/separate/tokenScreen.js'/>"></script> 
  		
	</body>
</html>