<div class='container'>
	<div class='jumbotron'>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<button type="button" class="btn btn-danger" data-toggle="collapse" data-target="#coachPan" >
					<span class="glyphicon glyphicon-search"></span>
					查看教练状态
				</button>
				<button type="button" class="btn btn-warning" data-toggle="collapse" data-target="#reqPan" >
					<span class="glyphicon glyphicon-search"></span>
					查看教练请求
				</button>
				<button type="button" class="btn btn-danger" data-toggle="collapse" data-target="#infoPan" >
					<span class="glyphicon glyphicon-wrench"></span>
					修改教练信息
				</button>
				<a href="index.php?action=coachmanag"><button type="button" class="btn btn-warning"><span class="glyphicon glyphicon-refresh"></span></button></a>
			</div>
			<div class='panel-body'></div>	
				<div class="panel-collapse collapse" id='coachPan'>
					<table class="table table-hover">
						<thead>
	   						<tr>
	   							<th>帐号</th>
	   							<th>名字</th>
	   							<th>状态</th>
	   						</tr>
	   					</thead>
	   					<tbody>
	   					{foreach $status as $line}
	   					
	   						<tr>
	   							<td>{$line.email}</td>
	   							<td>{$line.name}</td>
	   							<td>{$line.status}</td>
	   						</tr>
	   					{/foreach}
	   					</table>
					</table>
				</div>
				<div class="panel-collapse collapse" id='infoPan'>
					<div class="panel-body">
						<input class="form-control" id='emailBox' placeholder='教练帐号'/>	
						<button type="button" class="btn btn-warning" onclick="fetchInfo()">确认</button>
					</div>
				</div>
				<div class="panel-collapse collapse" id='reqPan'>
					<div class='panel panel-primary'>
						<div class='panel-heading'>
							请假请求
						</div>
						<div class='panel-body'>
							<table class="table table-hover">
								<thead>
									<tr>
										<td>帐号</td>
										<td>名字</td>
										<td>请假日期</td>
										<td>操作</td>
									</tr>
								</thead>
								<tbody>
									{assign var="i" value="1000"}
									{foreach $dayoff as $line}
										<td id='email{$i}'>{$line.email}</td>
										<td>{$line.name}</td>
										<td id='time{$i}' name='{$line.time}'>{$line.time|date_format:"%Y年%m月%d日"}</td>
										<td>
											<div class="btn-group">
									 			<input type="button" id='{$i}' class="btn btn-danger" value="请选择操作"/>
								    			<button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown">
								     			<span class="caret"></span>
								     			<span class="sr-only">Toggle Dropdown</span>
								   				</button>
										    	<ul class="dropdown-menu">
										      		<li><a id='拒绝' onclick="getOp('拒绝', '{$i}')">拒绝</a></li>
										      		<li><a id='批准' onclick="getOp('批准', '{$i}')">批准</a></li>
										    	</ul>
							 				</div>
										</td>
										{assign var="i" value=$i+1}
									{/foreach}
								</tbody>
							</table>
						</div>
						<div class='panel-heading'>
							课程操作请求
						</div>
						<div class='panel-body'>
							<table class='table table-hover'>
								<thead>
									<tr>
										<th><h4>ID</h4></th>
										<th><h4>课程名称</h4></th>
										<th><h4>开始时间</h4></th>
										<th><h4>课程数量</h4></th>
										<th><h4>课程地点</h4></th>
										<th><h4>课余量</h4></th>
										<th><h4>课程价格</h4></th>
										<!--<th><h4>课程简介</h4></th>-->
										<th><h4>课程时间</h4></th>
										<th><h4>请求种类</h4></th>
										<th><h4>操作</h4></th>
									</tr>
								</thead>
								<tbody>
									{assign var="i" value="2000"}
									{foreach $course as $line}
									<tr>
										<td ><h5 id='id{$i}'>{$line.id}</h5></td>
										<td><h5>{$line.name}</h5></td>
										<td><h5>{$line.starttime|date_format:"%Y年%m月%d日"}</h5></td>
										<td><h5>{$line.amount}</h5></td>
										<td><h5>{$line.site}</h5></td>
										<td><h5>{$line.capacity}</h5></td>
										<td><h5>{$line.price}</h5></td>
										<!--<td><h6>{$line.description}</h6></td>-->
										<td><h5>{$line.time}点</h5></td>
										<td><h5 id='status{$i}'>{$line.status}</h5></td>
										<td><h5>
											<div class="btn-group">
									 			<input type="button" id='{$i}' class="btn btn-danger" value="请选择操作"/>
								    			<button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown">
								     			<span class="caret"></span>
								     			<span class="sr-only">Toggle Dropdown</span>
								   				</button>
										    	<ul class="dropdown-menu">
										      		<li><a id='拒绝' onclick="getOp2('拒绝', '{$i}')">拒绝</a></li>
										      		<li><a id='批准' onclick="getOp2('批准', '{$i}')">批准</a></li>
										    	</ul>
							 				</div></h5> 	
										</td>
									</tr>
									<tr>
										<td><h4>简介：</h4></td>
										<td  colspan=9><h6>{$line.description}</h6></td>
									</tr>
									{assign var="i" value=$i+1}
									{/foreach}
								</tbody>
							</table>
						</div>
					</div>
				</div>
				{foreach $data as $rates}
				<div class='panel panel-warning'>
					<div class="panel-heading">{$rates.name}</div>
					<table class="table table-hover">
						<thead>
	   						<tr>
	   							<th>责任感</th>
	   							<th>规划能力</th>
	   							<th>倾听能力</th>
	   							<th>名气</th>
	   							<th>经验</th>
	   							<th>学员进步程度</th>
	   							<th>评论</th>
	   						</tr>
	   					</thead>
						{foreach $rates.rate as $line}
						<tbody>
							<tr>
	   							<td><h5>{$line.responsibility}</h5></td>
	   							<td><h5>{$line.planning}</h5></td>
	   							<td><h5>{$line.listener}</h5></td>
	   							<td><h5>{$line.fame}</h5></td>
	   							<td><h5>{$line.experience}</h5></td>
	   							<td><h5>{$line.progress}</h5></td>
	   							<td><h5>{$line.comment}</h5></td>
   							</tr>
						</tbody>
						{/foreach}
					</table>	
					{/foreach}
				</div>
		</div>
	</div>
</div>
<div class="modal fade" id="infoM" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  	<div class="modal-dialog">
    	<div class="modal-content">
      		<div class="modal-header">
        		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
       			<h4 class="modal-title" id="myModalLabel">信息修改</h4>
      		</div>
	      	<div class="modal-body">
	        	<form id='infoForm'role="form" class='infoModForm' method="POST" action="">
				    	<div class="form-group">
				        	<label for="exampleInputPassword1">用户名</label>
				        	<input type="text" class="form-control" id="nickname" name='nickname' placeholder="用户名" value=''>
				      	</div>
				      	<div class="form-group">
				        	<label for="exampleInputPassword1">等级</label>
				        	<input type="text" class="form-control" id="rank" name='rank' placeholder="等级" value=''>
				      	</div>
				      	<div class="form-group">
				        	<label for="exampleInputPassword1">姓名</label>
				        	<input type="text" class="form-control" id="name" name='name' placeholder="名字" value=''>
				      	</div>
				      	<div class="form-group" style='display:none'> 
				        	<input type="text" class="form-control" id="pwd" name='pwd' value=''>
				      	</div>
				      	<div class="form-group" style='display:none'> 
				        	<input type="text" class="form-control" id="payPwd" name='paypwd' value=' '>
				      	</div>
				      	<div class="form-group">
				        	<label for="exampleInputPassword1">性别</label>
				        	<div class="radio input-sm">
				          		<label>
				            		<input type="radio" name="sex" id="male" value="男">男
				          		</label>
				       		</div>
				        	<div class="radio input-sm  ">
				          		<label>
				            		<input type="radio" name="sex" id="female" value="女"> 女
				          		</label>
				        	</div>
				     	</div>
				      	<div class="form-group">
				        	<label for="exampleInputPassword1">电话</label>
				        	<input type="tel" class="form-control" id="telephone" name='telephone' placeholder="电话" value=''>
				     	</div>
				     	<div class="form-group">
				        	<label for="exampleInputPassword1">家庭住址</label>
				        	<input type="text" class="form-control" id="address" name='address' placeholder="家庭住址" value=''>
				     	</div>
				    </form>
	      	</div>
	      	<div class="modal-footer">
	        	<button type="button" class="btn btn-primary" onclick="save()">保存</button>
	      	</div>
    	</div><!-- /.modal-content -->
  	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->
{literal}
<script type="text/javascript">
	function getOp(op, btn)
	{
		var time = $('#time'+btn).attr('name');
		var email = $('#email'+btn).html();
		$('#'+btn).val(op);
		$.get("index.php?action=coachmanag&email="+email+"&time="+time+"&op="+op,function(data, status){

		});
	}
	function getOp2(op, btn)
	{
		$('#'+btn).val(op);
		$.get("index.php?action=coachmanag&id="+$('#id'+btn).html()+"&op="+op+"&status="+$('#status'+btn).html(),function(data, status){

		});
	}
	function fetchInfo()
	{
		$.get('index.php?action=coachmanag&email='+$('#emailBox').val(),function(data,status){
			var data = eval('('+data+')');
			$('#nickname').val(data['nickname']);
			$('#rank').val(data['rank']);
			$('#name').val(data['name']);
			$('#pwd').val(data['pwd']);
			if(data['sex'] == '男')
				$('#male').attr('checked','');
			else
				$('#female').attr('checked','');
			$('#telephone').val(data['telephone']);
			$('#address').val(data['address']);
			$('#infoM').modal('show');
		});
		
	}
	function save()
	{
		 $.ajax({
           type: "POST",
           url: 'index.php?action=coachmanag&write=true&email='+$('#emailBox').val(),
           timeout: 20000,
           data: $('#infoForm').serialize()
           });
		$('#infoM').modal('hide');
	}
</script>
{/literal}