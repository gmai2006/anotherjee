<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true"></script>
<script>
	
	var centerMap = new google.maps.LatLng($lat$, $lng$);
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
			
		var counter = 1
		$vendors: { c |
			var image = '$context$images/mapicons/marker$i++$.png';
			var marker = new google.maps.Marker($openCurlyBraclet$
			      position: new google.maps.LatLng($c.lat$, $c.lng$),
			      title: '$c.name$',
			      icon: image,
			      labelContent: "A",
		    	  labelAnchor: new google.maps.Point(3, 30),
		          labelClass: "labels", // the CSS class for the label
		        	labelStyle: $openCurlyBraclet$opacity: 0.75$closingCurlyBraclet$
			$closingCurlyBraclet$);
			marker.setMap(map);
		}$

	}

	google.maps.event.addDomListener(window, 'load', initialize);
</script>
$if(message)$
	<div class="alert $cssId$" role="alert">$message$</div>
$endif$
<div class="panel panel-default">
	<div class="panel-heading">
		<h4>$company.name$</h4>
	</div>
	<div class="panel-body">
		<p>$company.city$ $company.state$ $company.zipcode$
			$company.status$ $company.consumerRating$</p>
	</div>
</div>

<div class="panel panel-default">
	<div class="divide10"></div>
	<p class="bg-success pull-right">
		<span class="glyphicon  glyphicon-open-file"></span>&nbsp; <a
			href="$context$admin/csvreport?type=2">[Export to Excel]</a>&nbsp;
	</p>
	<div class="panel-heading">
		<h4>Vendors</h4>
	</div>
	<div class="divide10"></div>

	<table class="table table-condensed table-bordered">
		<thead>
			<tr>
				<th>Name</th>
				<th>Address</th>
				<th>City</th>
				<th>State</th>
				<th>Zipcode</th>
			</tr>
		</thead>
		$ assignedVendors: { c |
		<tr>
			<td><a href="$context$admin/vendordetailview?id=$c.id$">$c.name$</a></td>
			<td>$c.address$</td>
			<td>$c.city$</td>
			<td>$c.state$</td>
			<td>$c.zipcode$</td>
			<td>$c.consumerRating$</td>
		</tr>
		}$
	</table>
</div>	

<div class="panel panel-default">
	<div class="panel-heading">
		<h4>Nearest Vendors</h4>
	</div>
	<table class="table table-condensed table-bordered">
		<thead>
			<tr>
				<th>Nearest</th>
				<th>Name</th>
				<th>Address</th>
				<th>City</th>
				<th>State</th>
				<th>Zipcode</th>
				<th>Action</th>
			</tr>
		</thead>
		$ vendors: { c |
		<tr>
			<td><img src="$context$images/mapicons/marker$i++$.png"></td>
			<td><a href="$context$admin/vendordetailview?id=$c.id$">$c.name$</a></td>
			<td>$c.address$</td>
			<td>$c.city$</td>
			<td>$c.state$</td>
			<td>$c.zipcode$</td>
			<td><a href="$context$admin/clientdetailview?id=$company.id$&vendor=$c.id$">[Assign]</a></td>
		</tr>
		}$
	</table>
</div>
<div class="map_container">
	<div id="map-canvas"></div>
</div>