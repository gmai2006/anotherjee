<div id="header-top" class="hidden-xs">
	<div class="container">
		<div class="top-bar">
			<div class="pull-left sample-1right">
				<a><i class="fa fa-"></i> Any questions? Call us: <span
					class="colored-text">1-855-767-7768</span> </a>
			</div>
			<div class="pull-right">
				<ul class="list-inline top-social">
					$if (loginmenu)$
					<li class="$login$"><a href="$context$login.xhtml"><span
							class="glyphicon  glyphicon-user"></span>&nbsp;<strong>Sign
								in</strong></a></li> $else$
					<li class="$accountinfo$"><a
						href="$context$account/account-info.xhtml"><strong>$userId$</strong></a></li>
					<li><a href="$context$logout"><span
							class="glyphicon  glyphicon-off"></span>&nbsp;<strong>Sign
								out</strong></a></li> $endif$
					
				</ul>
			</div>
		</div>
	</div>
</div>