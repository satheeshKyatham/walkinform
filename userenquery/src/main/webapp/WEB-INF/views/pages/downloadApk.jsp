<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
 
<title>Download APK</title>

<style>
	.downloadSvg {
  cursor: pointer;
  height: 100px;
  width: 100px;
  overflow: visible;
}
.downloadSvg:hover .arrow {
  animation: bounce 1s both ease-out 1;
}

@keyframes bounce {
  0% {
    transform: translateY(0);
  }
  25% {
    transform: translateY(-3px);
  }
  50% {
    transform: translateY(0);
  }
  75% {
    transform: translateY(-3px);
  }
  100% {
    transform: translateY(0);
  }
}
/* For demo styling only */
.downloadAppCol {
  background-color: #96034a;
  font-family: "Lato", sans-serif;
  margin: 120px 40px 40px 40px;
  /* text-transform: uppercase; */
  color: #fff;
  font-size: 14px;
  text-align: center;
}


.downloadSvgLabel {
  display: block;
  text-align: center;
  font-size: 24px;
  margin-bottom: 100px;
}
</style>

</head>
<body class="downloadAppCol">

<div class="icon">
  	<a href="downloadapk" style="color:#fff; text-decoration:none; display: block; "> 	
  		<div class="downloadSvgLabel">Download  <div style="font-size:30px !important; background-color: #6fa624; padding: 2px 10px; margin: 15px 0; display: block;"> DIGITAL WALK-IN FORM </div> android mobile APP</div>
		<svg class="downloadSvg" xmlns="http://www.w3.org/2000/svg" width="25%" height="25%" viewBox="0 0 14 17">
			<path class="arrow" fill="#fff" fill-rule="evenodd" d="M14 6h-4V0H4v6H0l7 7 7-7z"/>
			<path class="line" fill="#fff" fill-rule="evenodd" d="M0 15v2h14v-2H0z" />
		</svg>
	</a>	
</div>

</body>
</html>