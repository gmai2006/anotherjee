<!-- Page Content -->
<div class="jumbotron">
	<h2 class="custom-header">
		Weather Forecast By Zipcode
	</h2>
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8 custom-center">
			<form action="$context$index.xhtml"
				method="POST">
				<div class="input-group">
					<input type="text" name="search" class="form-control"
						placeholder="98204"><span class="input-group-btn"><button class="btn btn-default" type="submit">Enter Zipcode</button></span>
				</div>
			</form>
		</div>
	</div>
</div>
<div class="divide10"></div>
<div class="container">
	<iframe id="forecast_embed" type="text/html" frameborder="0"
				height="345" width="100%"
				src="http://forecast.io/embed/#lat=$lat$&lon=$long$&name=$name$">
			</iframe>
</div>
<!--  end containner -->

