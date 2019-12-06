
//webcam attachment
function webcamAttachment(e, webAttachmentRowID, source) {
	$('#snapshotBtnCol').html("");
	$('#webAttachmentRowID').val("");
	$('#webAttachmentRowID').val(webAttachmentRowID);
	
	Webcam.set({
		width: 520,
		height: 400,
		image_format: 'jpeg',
		jpeg_quality: 120
	});

	Webcam.attach('#my_camera');
	
	if (source == "attachPAN") {
		$('#snapshotBtnCol').html("<button class='btn btnDefaultBlue btn-default' onClick='panTakeSnapshot()'>Take Snapshot</button>");
	} else if (source == "attachRec") {
		$('#snapshotBtnCol').html("<button class='btn btnDefaultBlue btn-default' onClick='recTakeSnapshot()'>Take Snapshot</button>");
	}
	
}


function panTakeSnapshot() {
	//shutter.play();
	
	var sound = document.getElementById("audio");
    sound.play()
	
	Webcam.snap( function(data_uri) {
		var id = "#"+$('#webAttachmentRowID').val() + " ";
		
		$(id + ".panAttachWebcam").val(data_uri);
		document.getElementById('results').innerHTML = '<img src="'+data_uri+'"/>';
	});
	
	//$('#webcamBox').modal('hide');
	
	//Webcam.reset();
}

function recTakeSnapshot() {
	//shutter.play();
	 
	var sound = document.getElementById("audio");
    sound.play()
	
	Webcam.snap( function(data_uri) {
		var id = "#"+$('#webAttachmentRowID').val() + " ";
		
		$(id + ".receiptAttachWebcam").val(data_uri);
		document.getElementById('results').innerHTML = '<img src="'+data_uri+'"/>';
	});
	
	//$('#webcamBox').modal('hide');
	
	//Webcam.reset();
}


function closeWebcamModel() {
	$('#webcamBox').modal('hide');
	Webcam.reset();
}
// END webcam attachment