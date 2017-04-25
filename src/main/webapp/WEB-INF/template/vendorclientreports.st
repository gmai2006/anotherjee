<div class="panel panel-default">
	
	<div class="divide10"></div>
	<p class="bg-success pull-right"><span
	class="glyphicon  glyphicon-open-file"></span>
	&nbsp;<a href="$context$vendor/csvreport?type=1">[Export to Excel]</a>&nbsp;</p>
	<div class="panel-heading" >
		<h4>
			Reports
		</h4>
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
	$ companies: { c |
	<tr>
		<td><a href="$context$vendor/clientdetailview?id=$c.id$">$c.name$</a></td>
		<td>$c.address$</td>
		<td>$c.city$</td>
		<td>$c.state$</td>
		<td>$c.zipcode$</td>
		<td>$c.consumerRating$</td>
	</tr>
	}$
</table>
</div>