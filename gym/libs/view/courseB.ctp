
<div class='container'>
	{foreach $course as $line}
		<div class="jumbotron">
	  		<h1>{$line.name}</h1>
	  		<div style='margin-left:50px'>
	  			<p>课程ID：{$line.id}</p>
		  		<p>开课日期：{$line.starttime}</p>
		  		<p>课程时间：{$line.coursetime} {$line.time}点</p>
		  		<p>课程总节数：{$line.amount}</p>
		  		<p>课程地点：{$line.site}</p>
		  		<p>课程余量：{$line.capacity}人</p>
		  		<p>课程价格：{$line.price}元</p>
		  		<p>课程简介：</br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp{$line.description}</p>
					<button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#comment{$line.id}" >
						<span class="glyphicon glyphicon-search"></span>
						查看评价
					</button>
					
			
		  		{if $line.capacity < 1}
		  			<a class="btn btn-default" role="button" style="margin-right:0px" href="" disabled="disabled">
						<span class="glyphicon  glyphicon-ban-circle"></span>
		  				课余量不足
		  			</a>
		  		{else if $line.flag eq 'show'}
		  			<a class="btn btn-primary btn-primary" onclick="setCourseId('{$line.id}')" data-toggle="modal" href="#paypwd" style="margin-right:0px">
		  				<span class="glyphicon glyphicon-shopping-cart" ></span>
		  				预约课程
		  			</a>
		  		{else if $line.flag eq 'booked'}
		  			<a class="btn btn-default" role="button" style="margin-right:0px" href="" disabled="disabled">
		  				<span class="glyphicon glyphicon-certificate"></span>
		  				已预约
		  			</a>
		  		{else if $line.flag eq 'unaffordable'}
		  			<a class="btn btn-default" role="button" style="margin-right:0px" href="" disabled="disabled">
						<span class="glyphicon glyphicon-usd"></span>
		  				余额不足
		  			</a>
		  		{/if}
		  		<div class="panel-collapse collapse" id='comment{$line.id}'>
					<div class="panel-body">
						<table class="table table-hover">
	   						<thead>
	   							<tr>
	   								<th>课程内容</th>
	   								<th>教学方式</th>
	   								<th>师生互动</th>
	   								<th>进步程度</th>
	   								<th>想说的话</th>
	   							</tr>
	   						</thead>
	   						<tbody>
	   							{foreach $rates[$line.id] as $rate}
	   							<tr>
	   								<td><h5>{$rate.content}</h5></td>
	   								<td><h5>{$rate.style}</h5></td>
	   								<td><h5>{$rate.interaction}</h5></td>
	   								<td><h5>{$rate.progress}</h5></td>
	   								<td><h5>{$rate.comment}</h5></td>
	   							</tr>
	   							{/foreach}
	   						</tbody>
	   					</table>
					</div>
				</div>
	  		</div>
		</div>
	{/foreach}
</div>
<div class="modal fade" id="paypwd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h2 class="modal-title" id="myModalLabel">请输入支付密码</h2>
          </div>
          <div class="modal-body">
        		<input type="password" class="form-control" id="payPwd" placeholder="支付密码">
          </div>
          <div class="modal-footer">
          <span id='error' style='color:red;visibility:hidden'>支付密码错误</span>
            <button type="button" class="btn btn-default" onclick='checkPwd()'>确认</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
<script type="text/javascript">
	var payPwd = {$payPwd};
	var id;
{literal}
	function setCourseId(i)
	{
		id = i;
	}
	function checkPwd()
	{
		var tmp = $('#payPwd').val();
		if(tmp == payPwd){
			$('#payPwd').val('');
			window.location = "index.php?action=coursebook&id="+id;
			$('#error').css('visibility','hidden');
		}
		else{
			$('#payPwd').val('');
			$('#error').css('visibility','visible');
		}
	}
{/literal}
</script>