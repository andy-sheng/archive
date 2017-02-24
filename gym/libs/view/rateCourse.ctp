<div class='container'>

	
	<div class="jumbotron">

		<h1>评价课程</h1>
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<form class="form-signin" role="form" action="index.php?action=ratecourse" method="POST" id='form'>
          
         
				<ul>
					<li>
						<small>选择需要评价的课程</small>

						<input type="text" name='course' id='course' value="" style="visibility:hidden"/>
						</br>

						<div class="btn-group">
							<input type="button" id='coursebtn' class="btn btn-default" value="　　　　　　"/>
							<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
								<span class="caret"></span>
								<span class="sr-only">Toggle Dropdown</span>
							</button>
							<ul class="dropdown-menu">
								
								{foreach $course as $line}
						    	<li><a id='{$line.name}' onclick="getCourse(this)">{$line.name}</a></li>
						    	{/foreach}
							</ul>
						</div>
					</li>
					
					<li>
						<small>你对课程内容的满意程度</small>
						</br>
						<div class="radio">
							<label>
								<input type="radio" name="content" value="非常满意">
							    <h5>非常满意</h5>
							</label>
							
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="content" value="基本满意">
							    <h5>基本满意</h5>
							</label>
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="content" value="没感觉">
							    <h5>没感觉</h5>
							</label>
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="content" value="不满意">
							    <h5>不满意</h5>
							</label>
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="content" value="非常不满意">
							    <h5>非常不满意</h5>
							</label>
						</div>
					</li>

					<li>
						<small>你对教学方式的满意程度</small>
						</br>
						<div class="radio">
							<label>
								<input type="radio" name="style" value="非常满意">
							    <h5>非常满意</h5>
							</label>
							
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="style" value="基本满意">
							    <h5>基本满意</h5>
							</label>
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="style" value="没感觉">
							    <h5>没感觉</h5>
							</label>
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="style" value="不满意">
							    <h5>不满意</h5>
							</label>
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="style" value="非常不满意">
							    <h5>非常不满意</h5>
							</label>
						</div>
					</li>

					<li>
						<small>你对师生互动的满意程度</small>
						</br>
						<div class="radio">
							<label>
								<input type="radio" name="interaction" value="非常满意">
							    <h5>非常满意</h5>
							</label>
							
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="interaction" value="基本满意">
							    <h5>基本满意</h5>
							</label>
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="interaction" value="没感觉">
							    <h5>没感觉</h5>
							</label>
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="interaction" value="不满意">
							    <h5>不满意</h5>
							</label>
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="interaction" value="非常不满意">
							    <h5>非常不满意</h5>
							</label>
						</div>
					</li>

					<li>
						<small>你个人的进步程度</small>
						</br>
						<div class="radio">
							<label>
								<input type="radio" name="progress" value="飞跃进步">
							    <h5>飞跃进步</h5>
							</label>
							
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="progress" value="微小进步">
							    <h5>微小进步</h5>
							</label>
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="progress" value="原地踏步">
							    <h5>原地踏步</h5>
							</label>
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="progress" value="微小退步">
							    <h5>微小退步</h5>
							</label>
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="progress" value="急剧退步">
							    <h5>急剧退步</h5>
							</label>
						</div>
					</li>

					<li>
						<small>其他想说的话</small>
						<textarea class="form-control" rows="3" name="comment"></textarea>
					</li>

				</ul>
				<button class="btn btn-lg btn-primary btn-block" type="submit">提交</button>
				</form>
			</div>
			<div class="col-md-3"></div>
			</div>

			
		
	</div>
</div>

<script type="text/javascript">
{literal}	
	function getCourse(data)
	{
		var course = $(data).attr('id');
		$('#coursebtn').val(course);
		$('#course').val(course);
	}
{/literal}
</script>
