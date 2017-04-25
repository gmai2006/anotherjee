<div class="panel panel-default">
$if(message)$
	<div class="alert $cssId$" role="alert">$message$</div>
	$endif$
	<div class="panel-heading" >
		<h4>
			Send Email to Customers
		</h4>
	</div>
	<div class="divide10"></div>
	<div class="panel-body" >
	<form role="form" action="$context$admin/sms" method="post">
		<div class="row">
			<div class="col-md-8">
				<div class="form-group">
					<label for="InputName">Select a list of customers</label>
					<div class="input-group">
						<select class="form-control" name="type" >
							<option value="">Select a list of customers</option>
							<option value="1">Clients</option>
							<option value="2">Vendors</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="InputEmail">Subject</label>
					<div class="input-group">
						<input type="text" class="form-control" id="InputEmail"
							name="subject" placeholder="Enter Subject" required> <span
							class="input-group-addon"><i
							class="glyphicon glyphicon-ok form-control-feedback"></i></span>
					</div>
				</div>
				<div class="form-group">
					<label for="InputMessage">Message</label>
					<div class="input-group">
						<textarea name="message" id="message" class="form-control"
							rows="7" required></textarea>
						<span class="input-group-addon"><i
							class="glyphicon glyphicon-ok form-control-feedback"></i></span>
					</div>
				</div>
				
				<input type="submit" name="submit" id="submit" value="Send"
					class="btn btn-info pull-right">
			</div>
			<!-- end of col 8 -->

			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>
							<a name="usa">Tips</a>
						</h4>
					</div>
					<div class="panel-body text_3 color_0">
						<address>
							Select a list of customers<br>
							Write an message</br>
							Click [Send] button</br>
						</address>
					</div>
				</div>
			</div>
		</div>
	</form>
	</div>
</div>