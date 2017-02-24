<div class="alert alert-success alert-dismissable" id='error' style='display:none'>
  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
  <strong id='message'></strong>
</div>
<div class='container'>
	<div class='jumbotron'  style='max-width:700px;margin:auto'>
		 <form role="form" id='form' class='regForm' method="POST" action="index.php?action=register">
		 	<div class="form-group" style="display:none">
		 		<input type="text" class="form-control" id="typeInput" name='type'>
			</div>
		 	<div class="btn-group"	>
			  	<input type="button" id='type' class="btn btn-primary" value='请选择用户类型'/>
			 	<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
			    	<span class="caret"></span>
			    	<span class="sr-only">Toggle Dropdown</span>
			  	</button>
			  	<ul class="dropdown-menu" role="menu">
			    	<li><a onclick="getType('会员')">会员</a></li>
			    	<li><a onclick="getType('教练')">教练</a></li>
			    	<li><a onclick="getType('管理员')">管理员</a></li>
			  	</ul>
			</div>
			
		 	<div class="form-group" id='emailDiv'>
       			<label id='emailLabel' for="inputError">邮箱</label>
        		<input type="text" class="form-control" id="email" name='email' placeholder="邮箱">
      		</div>
		 	<div class="form-group">
       			<label for="exampleInputPassword1">用户名</label>
        		<input type="text" class="form-control" id="nickname" name='nickname' placeholder="用户名">
      		</div>
      		<div class="form-group" >
        		<label for="exampleInputPassword1">密码</label>
        		<input type="text" class="form-control" id="pwd" name='pwd' placeholder="密码">
      		</div>
      		<div class="form-group" id='payPwdArea'style='display:none'>
        		<label for="exampleInputPassword1" >支付密码</label>
        		<input type="text" class="form-control" id="payPwd" name='payPwd' placeholder="支付密码" >
      		</div>
      		<div class="form-group">
        		<label for="exampleInputPassword1">等级</label>
        		<input type="text" class="form-control" id="rank" name='rank' placeholder="等级">
      		</div>
      		<div class="form-group">
        		<label for="exampleInputPassword1">姓名</label>
        		<input type="text" class="form-control" id="name" name='name' placeholder="名字">
      		</div>
      		<div class="form-group">
		        <label for="exampleInputPassword1">性别</label>
		        <div class="radio input-sm">
		          	<label>
		            	<input type="radio" name="sex" id="optionsRadios1" value="男" checked>男
		          	</label>
		        </div>
		        <div class="radio input-sm  ">
		          	<label>
		            	<input type="radio" name="sex" id="optionsRadios2" value="女"> 女
		          	</label>
		        </div>
		    </div>
      		<div class="form-group">
        		<label for="exampleInputPassword1">电话</label>
        		<input type="text" class="form-control" id="telephone" name='telephone' placeholder="电话">
      		</div>
      		<div class="form-group">
        		<label for="exampleInputPassword1">地址</label>
        		<input type="text" class="form-control" id='address' name='address' placeholder="地址">
      		</div>	
      		<input type='button' onclick='check()' class="btn btn-primary"	value='确定' />
		 </form>
	</div>
</div>
<script type="text/javascript">
{literal}
	var flag = false;
	function getType(type)
	{
		$('#type').val(type);
		$('#typeInput').val(type);
		if(type == '会员')
			$('#payPwdArea').css('display','block');
		else
			$('#payPwdArea').css('display','none');
	}
	/*
	 *验证表单的函数
	 */
	function check()
	{
		if($('#type').val() == '请选择用户类型'){
			$('#error').css('display','block');
			$('#message').html('请填写用户类型');
		}
		else if($('#email').val() == ''){
			$('#error').css('display','block');
			$('#message').html('请填写用户邮箱');
		}
		else if($('#nickname').val() == ''){
			$('#error').css('display','block');
			$('#message').html('请填写用户名');
		}
		else if($('#pwd').val() == ''){
			$('#error').css('display','block');
			$('#message').html('请填写登录密码');
		}
		else if($('#payPwd').val() == '' && $('#type').val() == '会员'){
			$('#error').css('display','block');
			$('#message').html('请填写支付密码');
		}
		else if($('#telephone').val() == ''){
			$('#error').css('display','block');
			$('#message').html('请填写用户电话');
		}
		else if($('#address').val() == ''){
			$('#error').css('display','block');
			$('#message').html('请填写用户地址');
		}
		else if(flag)
			$('#form').submit();
	}
	$(document).ready(function(){
		$('#email').change(function(){
			$.get('index.php?action=register&ajax='+$('#email').val(),function(data,status){
				if(data == 'exist'){
					$('#emailDiv').removeClass().addClass('form-group has-error');
					$('#emailLabel').html('邮箱（已存在）');
					flag = false;
				}
				else{
					$('#emailDiv').removeClass().addClass('form-group');
					$('#emailLabel').html('邮箱');
					flag = true;
				}
			});
		});
	});
{/literal}
</script>