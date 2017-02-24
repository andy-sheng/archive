
	<div class='jumbotron' style='max-width:700px;margin:auto'>
		<div class='row'> 
			<div class='col-md-4'>
				<form role='form' method='post' action='index.php?action=upload&behav=uploadimg' id='pic-form' enctype='multipart/form-data'>
					<div class="form-group">
				    	<label for="exampleInputFile">封面:</label>
				    	<input type="file" id="pic" name='pic'>
				  	</div>
				</form>
			</div>
			<div class='col-md-4'>
				<div>
			  		<img id='pic-img' src=''>
			  	</div>
			</div>
		</div>
		
		<form role='form' method='post' action='index.php?action=upload' id='uploadform' enctype='multipart/form-data' style='padding-top:30px' >
				<div class='row'>
			  		<div id='namediv' class="form-group col-md-8">
		    			<label for="书名">书名:<small id='namecheck' style='color:red'></small></label>
		    			<input type="text" class='form-control' id="bookname" placeholder="书名" name='bookname' style='' required autofocus>
	  				</div>
	  				<div class='col-md-2'>
	  					<label style='visibility:hidden'>aa</label>
	  					<button class='btn btn-primary' type='button' id='auto-fill' data-loading-text="填写中..." autocomplete="off">
	  						<span class='glyphicon glyphicon-flash'></span>
	  						自动填写
	  					</button>
	  				</div>
			  	</div>
	  			<div class="form-group">
			    	<label for="exampleInputFile" >选择书籍:<small id='filecheck' style='color:red'></small></label>
			    	<input type="file" id="file" name='file'>
			  	</div>
			  	<div class='form-group'>
			  		<label for="exampleInputFile" >选择标签:<small id='tagcheck' style='color:red'></small></label>
			  		<div class='row'>
			  			<div class='col-md-8 col-md-offset-2'>
			  				<label class="radio-inline">
					  			<input type="radio" name="tag" id="keji" value="科技"> 科技
							</label>
			  				<label class="radio-inline">
					  			<input type="radio" name="tag" id="renwensheke" value="人文社科"> 人文社科
							</label>
							<label class="radio-inline">
						  		<input type="radio" name="tag" id="lishi" value="历史"> 历史
							</label>
							<label class="radio-inline">
							  <input type="radio" name="tag" id="xiaoshuo" value="小说"> 小说
							</label>
							<label class="radio-inline">
							  <input type="radio" name="tag" id="zhuanji" value="传记"> 传记
							</label>
						</div>
				  	</div>
			  		<div class='row'>
			  			<div class='col-md-8 col-md-offset-2'>
				  			<label class="radio-inline">
							  <input type="radio" name="tag" id="wenxue" value="文学"> 文学 	
							</label>
							<label class="radio-inline">
							  <input type="radio" name="tag" id="jingji" value="经济管理"> 经济管理 	
							</label>
							<label class="radio-inline">
							  <input type="radio" name="tag" id="shenghuo" value="生活"> 生活 	
							</label>
							<label class="radio-inline">
							  <input type="radio" name="tag" id="zazhi" value="杂志"> 杂志 	
							</label>
							<label class="radio-inline">
							  <input type="radio" name="tag" id="manhua" value="漫画"> 漫画 	
							</label>
				  		</div>
				  	</div>
					<div class='row'>
			  			<div class='col-md-8 col-md-offset-2'>
			  				<label class="radio-inline">
						  		<input type="radio" name="tag" id="lizhi" value="励志"> 励志
							</label>
				  			<label class="radio-inline">
							  <input type="radio" name="tag" id="qita" value="其他"> 其他 	
							</label>

				  		</div>
				  	</div>
					
			  	</div>
			  	<input type="text"  style='visibility:hidden' id="pic-id"  name='pic-id'>
	  			<div id='authordiv' class="form-group">
	    			<label for="作者">作者:</label>
	    			<input type="text" class="form-control" id="author" placeholder="作者" name='author'>
	  			</div>
	  			<div id='isbndiv' class="form-group">
	    			<label for="ISBN">ISBN:</label>
	    			<input type="text" class="form-control" id="isbn" placeholder="ISBN" name='isbn'>
	  			</div>
	  			<div id='descdiv' class="form-group">
	    			<label for="描述">作者描述:</label>
	    			<textarea class="form-control" rows="3" id="authordesc" placeholder="作者描述" name='authordesc'></textarea>
	  			</div>
	  			<div id='descdiv' class="form-group">
	    			<label for="描述">内容描述:</label>
	    			<textarea class="form-control" rows="5" id="desc" placeholder="内容描述" name='desc'></textarea>
	  			</div>
			</form>
			<button id='book-submit-btn'class="btn btn-success" >
				<span class='glyphicon glyphicon-cloud-upload'></span>
				提交
			</button>
	</div>