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
		      position: new google.maps.LatLng($client.lat$, $client.lng$),
		      title: '$client.name$',
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
		<h4>$client.name$</h4>
	</div>
	<div class="panel-body">
		<p>$client.city$ $client.state$ $client.zipcode$ $client.status$</p>
	</div>
</div>
<div class="map_container">
	<div id="map-canvas"></div>
</div>
<div class="panel panel-default">
	<div class="panel-heading">
		<h4>Photo Upload</h4>
	</div>
	<div class="panel-body">
		<form id="uploadform" action="$context$vendor/upload"
			enctype="multipart/form-data" method="POST"
			onsubmit="postForm(this);">
			<input type="hidden" name="id" value="$client.id$" /> <input
				name="userfile" id="input-id" type="file" class="file"
				data-preview-file-type="text">
		</form>
	</div>

	<div class="panel-body">
		<table class="table table-condensed ">
			<thead>
				<tr>
					<th >File Name</th>
					<th >Last modified</th>
					<th >File size</th>
					<th  class="pull-right">Action</th>
				</tr>
			</thead>

			<tbody>
				$files:{ file |
				<tr>
					<td>$file.name$</td>
					<td>$file.date$</td>
					<td>$file.size$</td>
					<td class="pull-right"><a
						href="$context$vendor/clientdetailview?cmd=delete&id=$client.id$&fileName=$file.name$"><span
							class="glyphicon glyphicon-trash"></span>&nbsp;[Delete]</a>
				</tr>
				}$
			</tbody>
		</table>
	</div>
</div>
