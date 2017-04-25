<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true"></script>
<script>
	
	var centerMap = new google.maps.LatLng($company.lat$, $company.lng$);
	function initialize() {
		// Create the map.
		var mapOptions = {
			zoom : 10,
			center : centerMap,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};

		var map = new google.maps.Map(document.getElementById('map-canvas'),
				mapOptions);
		
		var marker = new google.maps.Marker($openCurlyBraclet$
			      position: centerMap,
			      title: '$company.name$'
			$closingCurlyBraclet$);
			marker.setMap(map);
			
		var image = '$context$images/mapicons/marker1.png';
		var marker = new google.maps.Marker($openCurlyBraclet$
		      position: new google.maps.LatLng($vendor.lat$, $vendor.lng$),
		      title: '$vendor.name$',
		      icon: image,
		      labelContent: "A",
	    	  labelAnchor: new google.maps.Point(3, 30),
	          labelClass: "labels", // the CSS class for the label
	        	labelStyle: $openCurlyBraclet$opacity: 0.75$closingCurlyBraclet$
		$closingCurlyBraclet$);
		marker.setMap(map);
	}

	google.maps.event.addDomListener(window, 'load', initialize);
</script>
$if(message)$
	<div class="alert $cssId$" role="alert">$message$</div>
$endif$
<div class="panel panel-default">
	<div class="panel-heading">
		<h4>$vendor.name$</h4>
	</div>
	<div class="panel-body">
		<p>$vendor.city$ $vendor.state$ $vendor.zipcode$
			$vendor.status$ </p>
	</div>
</div>

<div class="map_container">
	<div id="map-canvas"></div>
</div>

<div class="panel panel-default">
	<div class="panel-heading">
		<h4>Vendor Works</h4>
	</div>
	<div class="panel-body">
	$files:{ file |
		<img src="$context$$file.name$"
							class="img-responsive">
							<div class="divide10"></div>
	}$
	</div>
</div>