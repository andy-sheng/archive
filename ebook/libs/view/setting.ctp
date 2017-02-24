<div class='jumbotron'  style='background-image: url(tree_bark.png)'>
	<div class='row'>
		<div class='col-md-6'>
			<div class='panel panel-success'>
				<div class='panel-heading'>
					<h4>我的Kindle</h4>
					
				</div>
				<div class='panel-body'>
					<table class="table table-hover">
						<thead>
							<tr>
								<th>#</th>
								<th>Kindle</th>
							</tr>
						</thead>
						<tbody id='kindle-form'>
						
						{foreach $kindle as $line }
							<tr>
								<td>
									*
								</td>
								<td>
									{$line}
								</td>
							</tr>
						
						{/foreach}
						</tbody>
						
					
					</table>
					<a data-toggle="collapse" data-parent="#accordion" href="#new-kindle-div">
						<button type='button' class='btn btn-success' style>
							<span class='glyphicon glyphicon-plus'></span>
							添加Kindle
						</button>
					</a>
					<div class="collapse" style="padding-top:10px" id="new-kindle-div">
						<form role="form"  >
						  	<div class="form-group">
						    	<input type="email" class="form-control" id="new-kindle" placeholder="输入Kindle的推送地址" name='newkindle'>
						 	</div>
						 	
						</form>
						<button  class="btn btn-success" id='new-kindle-submit'>
							<span class='glyphicon glyphicon-ok'></span>
							确定
						</button>
					</div>
				</div>

			</div>
		</div>
		<div class='col-md-6'>
			<div class="panel panel-warning">
			    <div class="panel-heading">
			      	<h4 class="panel-title">
			      		我的上传
			        	<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style='float: right'>
			          		<span class='glyphicon glyphicon-collapse-up' id='myupload-collapse' onclick="collapse('myupload-collapse')"></span>
			        	</a>
			      	</h4>

			      	
			    </div>
			    <div id="collapseOne" class="panel-collapse collapse in">
			      	<div class="panel-body">
			       
			      	</div>
			    </div>
			</div>
		</div>
	</div><!--row-->
	<div class='row'>
		<div class='col-md-6'>
			<div class='panel panel-danger'>
				<div class="panel-heading">
				      	<h4 class="panel-title">
				      		我的下载
				        	<a data-toggle="collapse" data-parent="#accordion" href="#collapse-download" style='float: right'>
				          		<span class='glyphicon glyphicon-collapse-up' id='mydownload-collapse' onclick="collapse('mydownload-collapse')"></span>
				        	</a>
				      	</h4>

				      	
				</div>
			    <div id="collapse-download" class="panel-collapse collapse in">
			      	<div class="panel-body">
			       		<table class="table table-hover">
						<thead>
							<tr>
								<th>#</th>
								<th>书籍</th>
								<th>方式</th>
								<th>时间</th>
								
								<th>状态</th>
							</tr>
						</thead>
						<tbody id='download-form'>
						
						{foreach $downloads as $download }
							<tr>
								<td>
									*
								</td>
								<td>
									{$download.bid}
								</td>
								<td>
									{$download.type}
								</td>
								<td>
									{$download.date|date_format:'%Y-%m-%d %H:%M:%S'}
								</td>
								
								<td>
									{$download.status}
								</td>
							</tr>
						
						{/foreach}
						</tbody>
			      	</div>
			    </div>
			</div>
		</div>
	</div><!--row-->
	
</div>