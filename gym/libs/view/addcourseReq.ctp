
<div class='container'>


	<div class='jumbotron'  style='max-width:700px;margin:auto'>
		<h1>申请添加课程</h1>
		<form role="form" id='form' class='regForm' method="POST" action="">
		 	<div class="form-group" style="display:none">
		 		<input type="text" class="form-control" id="typeInput" name='type'>
			</div>

		 	
		 	<div class="form-group" id='nameDiv'>
       			<label id='exampleInputPassword1' for="inputError">开课时间</label>
	        	<div class="row">
			        <div class='col-sm-7'>
			            <div class="form-group">
			                <div class='input-group date' id='datetimepicker'>
			                    <input type='text' class="form-control" id="datetime" name="datetime" readonly=""/>
			                    <span class="input-group-btn">
						        	<button class="btn btn-default" type="button"><span class="glyphicon glyphicon-calendar"></span></button>
						        </span>
			                    </span>
			                </div>
			            </div>
			        </div>
			        <script type="text/javascript" src="libs/view/bower_components/eonasdan-bootstrap-datetimepicker/src/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
			        <script type="text/javascript">
			            $(function () {
			            	var myDate = new Date();
			                $('#datetimepicker').datetimepicker({
			                	format: "YYYY年MM月DD日 每周：星期dd A h 点",
			                	minDate: myDate,
			                	useMinutes: false,
	    						useSeconds: false,
			                    sideBySide: true,
			                    pick12HourFormat: true,
			                    language: 'zh-CN'
			                });
			                
			            });
			        </script>

			    </div>
      		</div>	
	 		

		 	<div class="form-group">
       			<label id='exampleInputPassword1' for="inputError">课程名称</label>
        		<input type="text" class="form-control" id="name" name='name' placeholder="名称">
      		</div>

      		<div class="form-group">
       			<label id='exampleInputPassword1' for="inputError">教练</label>
       			<input type="text" class="form-control" id="coach" name='coach' value="{$coach}" readonly/>
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

		 	<div class="form-group">
       			<label for="exampleInputPassword1">课程总节数</label>
        		<input type="text" class="form-control" id="amount" name='amount' placeholder="总节数">
      		</div>
      		<div class="form-group" >
        		<label for="exampleInputPassword1">课程地点</label>
        		<input type="text" class="form-control" id="site" name='site' placeholder="地点">
      		</div>
      		<div class="form-group">
        		<label for="exampleInputPassword1" >课程余量</label>
        		<input type="text" class="form-control" id="capacity" name='capacity' placeholder="余量" >
      		</div>
      		<div class="form-group">
        		<label for="exampleInputPassword1">课程价格</label>
        		<input type="text" class="form-control" id="price" name='price' placeholder="价格">
      		</div>
      		<div class="form-group">
        		<label for="exampleInputPassword1">课程简介</label>
        		<textarea type="text" class="form-control" rows="5" id="description" name="description" ></textarea>
      		</div>

      		<h6 id='message' class="text-danger"></h6>
      		<input type='button' onclick='confirm()' class="btn btn-primary"	value='确定' />
      		<input type='button' onclick='cancel()' class="btn btn-primary"	value='取消' />

		 </form>
	</div>
</div>


<script type="text/javascript">
{literal}
	

	/*
	 *验证表单的函数
	 */
	function confirm()
	{
		if($('#datetime').val() == ''){
			$('#error').css('display','block');
			$('#message').html('请填写开课日期');
		}
		else{
			var hour = $('#datetime').val().split(' ')[3].split(':')[0];
			var period = $('#datetime').val().split(' ')[2];
			if(period == '凌晨' || period == '早上' && hour < 9 || period == '晚上' && hour >= 9){
				$('#error').css('display','block');
				$('#message').html('开课时间应在9:00--20:00之间的整点');
			}
			else if($('#name').val() == ''){
				$('#error').css('display','block');
				$('#message').html('请填写课程名称');
			}
			else if($('#amount').val() == ''){
				$('#error').css('display','block');
				$('#message').html('请填写课程总节数');
			}
			else if(isNaN($('#amount').val())){
				$('#error').css('display','block');
				$('#message').html('课程总节数应为数字');
			}
			else if($('#site').val() == ''){
				$('#error').css('display','block');
				$('#message').html('请填写课程地点');
			}
			else if($('#capacity').val() == ''){
				$('#error').css('display','block');
				$('#message').html('请填写课程余量');
			}
			else if(isNaN($('#capacity').val())){
				$('#error').css('display','block');
				$('#message').html('课余量应为数字');
			}
			else if($('#price').val() == ''){
				$('#error').css('display','block');
				$('#message').html('请填写课程价格');
			}
			else if(isNaN($('#price').val())){
				$('#error').css('display','block');
				$('#message').html('课程价格应为数字');
			}
			else if($('#description').val() == ''){
				$('#error').css('display','block');
				$('#message').html('请填写课程简介');
			}
			else {
				document.all("form").setAttribute("action", "index.php?action=coursemanag&add=confirm");
				$('#form').submit();
			}
		}
		
	}

	function cancel()
	{
		document.all("form").setAttribute("action", "index.php?action=coursemanag");
		$('#form').submit();	
	}
	
{/literal}
</script>