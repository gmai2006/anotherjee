<div class="panel panel-default">
	<div class="panel-heading">
		<h4>Customer Batch Update</h4>
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
</div>