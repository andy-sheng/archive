<div class='container'>

	
	<div class="jumbotron">

		<h1>评价教练</h1>
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<form class="form-signin" role="form" action="index.php?action=ratecoach" method="POST" id='form'>
          
         
				<ul>
					<li>
						<small>选择需要评价的教练</small>

						<input type="text" name='coach' id='coach' value="" style="visibility:hidden"/>
						</br>

						<div class="btn-group">
							<input type="button" id='coachbtn' class="btn btn-default" value="　　　　　　"/>
							<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
								<span class="caret"></span>
								<span class="sr-only">Toggle Dropdown</span>
							</button>
							<ul class="dropdown-menu">
								
								{foreach $coach as $line}
						    	<li><a id='{$line.email}' onclick="getCoach('{$line.email}', '{$line.name}')">{$line.name}</a></li>
						    	{/foreach}
							</ul>
						</div>
					</li>
					
					<li>
						<small>你认为教练的责任感如何？</small>
						</br>
						<div class="radio">
							<label>
								<input type="radio" name="responsibility" value="尽心尽责">
							    <h5>尽心尽责</h5>
							</label>
							
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="responsibility" value="认真负责">
							    <h5>认真负责</h5>
							</label>
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="responsibility" value="基本负责">
							    <h5>基本负责</h5>
							</label>
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="responsibility" value="不太负责">
							    <h5>不太负责</h5>
							</label>
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="responsibility" value="非常不负责">
							    <h5>非常不负责</h5>
							</label>
						</div>
					</li>

					<li>
						<small>你认为教练的健身规划能力如何？</small>
						</br>
						<div class="radio">
							<label>
								<input type="radio" name="planning" value="非常满意">
							    <h5>非常满意</h5>
							</label>
							
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="planning" value="基本满意">
							    <h5>基本满意</h5>
							</label>
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="planning" value="没感觉">
							    <h5>没感觉</h5>
							</label>
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="planning" value="不满意">
							    <h5>不满意</h5>
							</label>
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="planning" value="非常不满意">
							    <h5>非常不满意</h5>
							</label>
						</div>
					</li>

					<li>
						<small>你认为教练的倾听能力如何？</small>
						</br>
						<div class="radio">
							<label>
								<input type="radio" name="listener" value="善于并且主动倾听意见和建议">
							    <h5>善于并且主动倾听意见和建议</h5>
							</label>
							
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="listener" value="善于倾听意见和建议">
							    <h5>善于倾听意见和建议</h5>
							</label>
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="listener" value="不排斥他人的意见和建议">
							    <h5>不排斥他人的意见和建议</h5>
							</label>
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="listener" value="忽视他人的意见和建议">
							    <h5>忽视他人的意见和建议</h5>
							</label>
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="listener" value="对他人的意见和建议不满">
							    <h5>对他人的意见和建议不满</h5>
							</label>
						</div>
					</li>

					<li>
						<small>你认为教练的名气如何？</small>
						</br>
						<div class="radio">
							<label>
								<input type="radio" name="fame" value="德高望重">
							    <h5>德高望重</h5>
							</label>
							
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="fame" value="口碑良好">
							    <h5>口碑良好</h5>
							</label>
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="fame" value="尚未听说">
							    <h5>尚未听说</h5>
							</label>
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="fame" value="口碑略差">
							    <h5>口碑略差</h5>
							</label>
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="fame" value="负分滚粗">
							    <h5>负分滚粗</h5>
							</label>
						</div>
					</li>

					<li>
						<small>你认为教练的经验如何？</small>
						</br>
						<div class="radio">
							<label>
								<input type="radio" name="experience" value="经验丰富">
							    <h5>经验丰富</h5>
							</label>
							
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="experience" value="经验较多">
							    <h5>经验较多</h5>
							</label>
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="experience" value="经验一般">
							    <h5>经验一般</h5>
							</label>
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="experience" value="经验较少">
							    <h5>经验较少</h5>
							</label>
						</div>
						<div class="radio">
							<label>
								<input type="radio" name="experience" value="根本没经验">
							    <h5>根本没经验</h5>
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
	function getCoach(email, name)
	{
		document.all("coachbtn").setAttribute("value", name);
		document.all("coach").setAttribute("value", email);
	}
{/literal}
</script>
