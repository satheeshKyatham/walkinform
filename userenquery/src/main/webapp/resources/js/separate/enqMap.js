// https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false
// //code.jquery.com/jquery-1.11.0.min.js
var pageContext = $("#pageContext").val()+"/";	

//getEnqDataForMap();
//regionList();

function regionList () {
	$('#regionList').empty();	
	var urlRegionList = pageContext+"regionList?project_code=test";
	
	$.getJSON(urlRegionList, function (data) {
		$('#regionList').append('<option value="">Select</option>');
		$.each(data, function (index, value) {
			$('#regionList').append("<option value='"+value.region__c+"'>"+value.region__c+"</option>");
		});					
	}).done(function() {
	});
}


function projectDataList (){
	$('#projectDataList').empty();	
	var urlTower = pageContext+"projectDataList?region="+$('#regionList').val();
	$.getJSON(urlTower, function (data) {
		$('#projectDataList').append('<option value="">Select</option>');
		$.each(data, function (index, value) {
			$('#projectDataList').append("<option value='"+value.sfid+"'>"+value.name+  " / " +value.propstrength__project_code__c+ "</option>");
		});					
	}).done(function() {
	});
}




function getEnqDataForMap () {
	 $('#mapLoading').show();	
	debugger;
	//alert ("Test Call 123 456");
	$('#map-canvas').hide();	
	$.post(pageContext+"getEnqDataForMap",{"projectId":$('#projectid').val()},function(data){				 
		
	}).done(function(data){
		var obj =JSON.parse(data);
		
		/*for(var i=0;i<obj.length;i++){
			var unitId =  '"'+obj[i].residencelat+'"';
			var customerId = '"'+obj[i].residencelng+'"';
		}*/
		
		initialize(obj);
		
	});	
}





var map;
// The JSON data
//var json = [{"id":48,"title":"Helgelandskysten","longitude":"12.63376","latitude":"66.02219"},{"id":46,"title":"Tysfjord","longitude":"16.50279","latitude":"68.03515"},{"id":44,"title":"Sledehunds-ekspedisjon","longitude":"7.53744","latitude":"60.08929"},{"id":43,"title":"Amundsens sydpolferd","longitude":"11.38411","latitude":"62.57481"},{"id":39,"title":"Vikingtokt","longitude":"6.96781","latitude":"60.96335"},{"id":6,"title":"Tungtvann- sabotasjen","longitude":"8.49139","latitude":"59.87111"}];

function initialize(json) {
	
	$('#map-canvas').show();
	
  // Giving the map som options
  var mapOptions = {
    zoom: 4,
    center: new google.maps.LatLng(20.5936832,78.962883)
  };
  
  // Creating the map
  map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
  
  
  var infowindow = new google.maps.InfoWindow();
  
  // Looping through all the entries from the JSON data
  for(var i = 0; i < json.length; i++) {
    
    // Current object
    var obj = json[i];
    
	console.log ("obj.residencelat ::: " + obj.residencelat);
	console.log ("obj.residencelng ::: " + obj.residencelng);
    
	//https://d4u.gplapps.com:8085/walkinform/resources/images/gplLogo.jpg
	
	var pinStatus = 'https://d4u.gplapps.com:8085/walkinform/resources/images/z4.png'
	
		
	if (obj.referred_partner_flag__c == "O"){
		pinStatus = 'https://d4u.gplapps.com:8085/walkinform/resources/images/z3.png';
	} else if(obj.referred_partner_flag__c == "D"){
		pinStatus = 'https://d4u.gplapps.com:8085/walkinform/resources/images/Z1596.png';
	} else if (obj.referred_partner_flag__c == "CP"){
		pinStatus = 'https://d4u.gplapps.com:8085/walkinform/resources/images/z3.png';
	}	
		
		
	var image = {
		url: pinStatus,
		// This marker is 20 pixels wide by 32 pixels high.
		size: new google.maps.Size(40, 48),
		// The origin for this image is (0, 0).
		origin: new google.maps.Point(0, 0),
		// The anchor for this image is the base of the flagpole at (0, 32).
		anchor: new google.maps.Point(0, 32)
	};
	
    
    // Adding a new marker for the object
    var marker = new google.maps.Marker({
      position: new google.maps.LatLng(obj.residencelat,obj.residencelng),
      map: map,
      icon: image,
      title: obj.firstname // this works, giving the marker a title with the correct title
    });
    
    
    
    
    	
    
    
    var fullName =   "<ul class='pinInfoWindow'>  <li> <label>Source Type: </label> "+obj.walk_in_source__c+" </li> <li> <label>Mobile No: </label> "+obj.mobileno+" </li> <li>	 <label>Project: </label> "+obj.project_name+" </li> <li>	 <label>Enquiy NO: </label> "+obj.name+" </li> <li> <label>Name: </label> "+obj.firstname+" "+obj.lastname+"</li> </ul>";
    
    // Adding a new info window for the object
    var clicker = addClicker(marker, fullName);
  } // end loop
  
  
  // Adding a new click event listener for the object
  function addClicker(marker, content) {
    google.maps.event.addListener(marker, 'click', function() {
      
      if (infowindow) {infowindow.close();}
      infowindow = new google.maps.InfoWindow({content: content});
      infowindow.open(map, marker);
      
    });
  }
  
  $('#mapLoading').hide();
  
}

// Initialize the map
/*google.maps.event.addDomListener(window, 'load', initialize);*/	