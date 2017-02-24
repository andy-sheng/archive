<div class="alert alert-success alert-dismissable" id='error' style='display:none'>
  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
  <strong id='message'></strong>
</div>

<div class='container'>
	<div class='jumbotron' style='max-width:700px;margin:auto'>
		<form role="form" id='form' class='regForm' method="POST" action="index.php?action=sysmanag">
			<div class="form-group" style="display:none">
		 		<input type="text" class="form-control" id="typeInput" name='type' value='all'>
			</div>
			<div class="btn-group"	>
			  	<input type="button" id='type' class="btn btn-primary" value='请选择用户类型'/>
			 	<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
			    	<span class="caret"></span>
			    	<span class="sr-only">Toggle Dropdown</span>
			  	</button>
			  	<ul class="dropdown-menu" role="menu">
			    	<li><a onclick="getType('customer')">会员</a></li>
			    	<li><a onclick="getType('coach')">教练</a></li>
			    	<li><a onclick="getType('admin')">管理员</a></li>
			    	<li><a onclick="getType('all')">所有对象</a></li>
			  	</ul>
			</div>
			<div class="form-group">
       			<label for="exampleInputPassword1">标题</label>
        		<input type="text" class="form-control" id="head" name='head' placeholder="标题">
      		</div>
			<h3>通知内容</h3>
       		<textarea id='content'class="form-control" rows="5" name='content'></textarea>
       		<input type='button' onclick='check()' class="btn btn-primary"	value='提交通知' />
       		<!--验证表单，需要把type改掉，用js提交表单，不让他自动提交-->
		</form>
	</div>
	<!--<div class='jumbotron'></div>-->
</div>
{literal}
<script type="text/javascript">
	function getType(type)
	{
		$('#typeInput').val(type);
		if(type == 'customer')
			$('#type').val('会员');
		else if(type == 'admin')
			$('#type').val('管理员');
		else if(type == 'coach')
			$('#type').val('教练');
		else
			$('#type').val('所有对象');

	}
	/*
	 *验证表单的函数
	 */
	function check()
	{
		if($('#type').val() == '请选择用户类型'){
			$('#error').css('display','block');
			$('#message').html('请填写通知对象');
		}
		else if($('#head').val() == ''){
			$('#error').css('display','block');
			$('#message').html('请填写通知标题');
		}
		else if($('#content').val() == ''){
			$('#error').css('display','block');
			$('#message').html('请填写通知内容');
		}
		else
			$('#form').submit();
	}
</script>
{/literal}