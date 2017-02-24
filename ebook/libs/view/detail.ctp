<div class='jumbotron detail-box' style='background-image:url(http://myebook.jd-app.com/img/paper.png)'>
	<div class='row'>
		<div class='col-md-3'>
			<img src='{$info.picUrl}' class='detail-pic' alt='{$info.name}'>
		</div>
		<div class='col-md-9' >
			<h3>《{$info.name}》</h3>
			<h3 id='bid' style='display:none'>{$info.bid}</h3>
			<h5><small>{$info.author}&nbsp 著</small></h5>
			<h5><small>标签：{$info.tag}</small></h5>
			<h5><small>ISBN：{$info.isbn}</small></h5>
			<h5><small>上传者:{$info.uploader}</small></h5>
			<h5><small>上传日期：{$info.date|date_format:'%Y-%m-%d'}</small></h5>
			<h5><small>下载次数：{$info.count}</small> </h5>
		</div>
	</div>
	<div class='row' style='margin-top: 30px;margin-bottom: 30px;'>
		<div class='col-md-2 col-md-offset-1'>
			<a id='bookLink' name='{$info.bid}' href='{$info.bookUrl}'>
				<button class='btn btn-success'>
				<span class="glyphicon glyphicon-download-alt"></span>
				下载&nbsp&nbsp&nbsp&nbsp
				</button>
			</a>
		</div>
		<div class='col-md-9'>
			{if $hasKindle == 'true'}
			或 推送到
			<div class="btn-group">

				<button type="button" class="btn btn-default dropdown-toggle" id='email-btn' name='{$kindle.0}' data-toggle="dropdown">
	    				{$kindle.0}	 <span class="caret"></span>
		  		</button><ul class="dropdown-menu" role="menu">
				{foreach $kindle as $line}
		    			<li><a href="javascript:void(0)" onclick="dropMenu('{$line}','email-btn')">{$line}</a></li>			
	  			{/foreach}
	  				</ul>
	  				<button class='btn btn-success' id='doPush' onclick='doPush()' data-loading-text="推送中..." autocomplete="off">
		  				<span class='glyphicon glyphicon-share'></span>
		  				推送
	  				</button>
			</div>
			{/if}
		</div>

	</div>
	<div style='padding-top:5px'>
		<!--<div class="panel-group" id="accordion">-->
		  	<div class="panel panel-info">
		    	<div class="panel-heading">
		      		<h4 class="panel-title">
		        		
		          		作者简介:
		        		
		        		<a data-toggle="collapse" data-parent="#accordion" href="#authorDesc" style='float: right'>
			          		<span class='glyphicon glyphicon-collapse-up' id='author-desc-collapse' onclick="collapse('author-desc-collapse')"></span>
			        	</a>
		      		</h4>
		    	</div>
		    	<div id="authorDesc" class="panel-collapse collapse in">
		      		<div class="panel-body">
		        		{$info.authordesc}
		      		</div>
		    	</div>
		  	</div>
		  	<div class="panel panel-success">
		    	<div class="panel-heading">
		      		<h4 class="panel-title">
		        		
		 					内容简介:
		       
		        		<a data-toggle="collapse" data-parent="#accordion" href="#bookDesc" style='float: right'>
			          		<span class='glyphicon glyphicon-collapse-up' id='book-desc-collapse' onclick="collapse('book-desc-collapse')"></span>
			        	</a>
		      		</h4>
		    	</div>
		    	<div id="bookDesc" class="panel-collapse collapse in">
		      		<div class="panel-body">
		        		{$info.bookdesc}
		      		</div>
		    	</div>
		  	</div>
		  	<div class="panel panel-warning">
		    	<div class="panel-heading">
		      		<h4 class="panel-title">
		        	
		 					评论:
		        		
		        		<a data-toggle="collapse" data-parent="#accordion" href="#comment" style='float: right'>
			          		<span class='glyphicon glyphicon-collapse-down' id='comment-collapse' onclick="collapse('comment-collapse')"></span>
			        	</a>
		      		</h4>
		    	</div>
		    	<div id="comment" class="panel-collapse collapse">
		      		<div class="panel-body">
		        		<div class='list-group' id='comment-list'>
		        		{foreach $comments as $comment}
		        			<div class='list-group-item'>
		        				<h5 class="list-group-item-heading"><small>{$comment.email}</small></h5>
    							<p class="list-group-item-text"><small>{$comment.content}</small></p>
		        			</div>
		        		{/foreach}
		        		</div>
		        		<div style='height:50px'>
		        			<a data-toggle="collapse" data-parent="#accordion" href="#comment-area">
		        				<button class='btn btn-primary' style='float:right'>添加评论</button>
		        			</a>
		        		</div>
		        		
		        		<div class='collapse' id='comment-area' >
	        				<textarea id='comment-content'class="form-control" rows="10" placeholder='输入评论内容'></textarea>
	        				<div style='height:50px;margin-top:20px'>
	        					<button class='btn btn-primary' style='float:right' id='comment-submit'>
	        						提交
	        					</button>
		        			</div>
		        		</div>
		        		<div class=''>
		        			<button class='btn btn-default form-control' id='more-comment' value='2'>更多</button>
		        		</div>
		        		
		      		</div>
		      		
		    	</div>
		  	</div>
		<!--</div>-->
	</div>
</div>