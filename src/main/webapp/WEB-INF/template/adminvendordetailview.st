
<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true"></script>
<script>
	var cityCircle;
	var radius = 45
	var mt = 6378100
	var mi = 3963.1676
	radius = (radius / mi ) * mt;
	var centerMap = new google.maps.LatLng($lat$, $lng$);
	var map, marker;
	var mapOptions = {
			zoom : 7,
			center : centerMap,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};
	function initialize() {
		// Create the map.
		map = new google.maps.Map(document.getElementById('map-canvas'),
				mapOptions);
		marker = new google.maps.Marker($openCurlyBraclet$
			      position: centerMap,
			      title: '$company.name$'
			$closingCurlyBraclet$);
			marker.setMap(map);
			
		var populationOptions = {
			strokeColor : '#FF0000',
			strokeOpacity : 0.8,
			strokeWeight : 1,
			fillColor : '#00FF00',
			fillOpacity : 0.35,
			map : map,
			center : centerMap,
			radius : radius
		};
		console.log(populationOptions)
		// Add the circle for this city to the map.
		cityCircle = new google.maps.Circle(populationOptions);
		
	}
	
	function calcRadius()
    {
		radiusVal = document.getElementById("radius").value
        console.log(radiusVal);
        radius = radiusVal;
        radius = (radius / mi ) * mt;
        console.log(radius);
        map = new google.maps.Map(document.getElementById('map-canvas'),
				mapOptions);
			marker.setMap(map);
			
		var populationOptions = {
			strokeColor : '#FF0000',
			strokeOpacity : 0.8,
			strokeWeight : 1,
			fillColor : '#00FF00',
			fillOpacity : 0.35,
			map : map,
			center : centerMap,
			radius : radius
		};
		console.log(populationOptions)
		// Add the circle for this city to the map.
		cityCircle = new google.maps.Circle(populationOptions);
    }
	google.maps.event.addDomListener(window, 'load', initialize);
</script>
<h4>$company.name$</h4>
<p>$company.city$ $company.state$ $company.zipcode$ $company.status$
	$company.consumerRating$</p>
	
<div class="panel panel-default">
	<div class="divide10"></div>
	<p class="bg-success pull-right">
		<span class="glyphicon  glyphicon-open-file"></span>&nbsp; <a
			href="$context$admin/csvreport?type=2">[Export to Excel]</a>&nbsp;
	</p>
	<div class="panel-heading">
		<h4>Clients</h4>
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
		$ clients: { c |
		<tr>
			<td><a href="$context$admin/clientdetailview?id=$c.id$">$c.name$</a></td>
			<td>$c.address$</td>
			<td>$c.city$</td>
			<td>$c.state$</td>
			<td>$c.zipcode$</td>
			<td>$c.consumerRating$</td>
		</tr>
		}$
	</table>
</div>	
<div class="container">
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6 pull-right">
			<form class="form form-inline" role="form">
			<div class="form-group">
			<label for="usr">Radius:</label>
				<select class="form-control" id="radius" onchange="calcRadius();">
				$radius: { c |
					<option value="$c$">$c$</option>
				}$
				</select>
				</div>
			</form>
		</div>
	</div>
</div>
<div class="divide10"></div>
<div class="map_container">
	<div id="map-canvas"></div>
</div>