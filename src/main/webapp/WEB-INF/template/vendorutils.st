<div class="panel panel-default">
	<div class="panel-heading">
		<h4>File Upload</h4>
	</div>
	$if(cssId)$
	<div class="alert $cssId$" role="alert">$message$</div>
	$endif$
	<div class="panel-body">
		<form id="uploadform" action="$context$upload.asp"
			enctype="multipart/form-data" method="POST"
			onsubmit="postForm(this);">
			<input name="userfile" id="input-id" type="file" class="file"
				data-preview-file-type="text">
		</form>
	</div>

	<table class="table table-condensed">
		<thead>
			<tr>
				$headers:{ header |
				<th scope="col"><a
					href="$context$vendor/file-manager.xhtml?sort=$header$">$header$</a></th>
				}$ $actions:{ action |
				<th scope="col">$action$</th> }$
			</tr>
		</thead>

		<tbody>
			$if(subdir)$
			<td><a href="#"
				onClick="callUrl('$context$vendor/file-manager.xhtml?dir=$parentdir$')"><img
					src="$context$/images/16px/folder.png" alt="" width="16"
					height="16" /> ..<img src="$context$images/arrow_turn_left.png"
					alt="" width="16" height="16" /></a></td>
			<td><br /></td>
			<td><br /></td>
			<td></td>
			</tr>
			$endif$ $files:{ file | $if(file.dir)$
			<tr>
				<td><a href="#"
					onClick="callUrl('$context$vendor/file-manager.xhtml?dir=$dir$/$file.name$')"><img
						src="$context$images/16px/$file.imageName$" alt="page" width="16"
						height="16" /> $file.name$</a></td>
				<td>$file.date$</td>
				<td>$file.fileSize$</td>
				<td><a href="#" onclick="showPrompt('$file.name$','rn')">[Rename]</a></td>
			</tr>
			$else$
			<tr>
				<td><a href="$context$download.asp?fileName=$file.name$"
					target="_blank"><img
						src="$context$images/16px/$file.imageName$" alt="" width="16"
						height="16" /> $file.name$</a></td>
				<td>$file.date$</td>
				<td>$file.size$</td>
				<td id="test"><a href="#"
					onclick="showPrompt('$file.name$','rn')">[Rename]</a> <a href="#"
					onclick="showPrompt('$file.name$','cp')">[Copy]</a> <a href="#"
					onclick="showDialog('Are you sure you want to delete ', '$file.name$', '$context$vendor/file-manager.xhtml?cmd=delete&fileName=$file.name$');"><span
						class="glyphicon glyphicon-trash"></span>&nbsp;[Delete]</a>
			</tr>
			$endif$ }$
		</tbody>
	</table>
</div>