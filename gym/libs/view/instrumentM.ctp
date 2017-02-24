<div class='container'>
	<div class='jumbotron'>
		
		<div class="panel-body">
			<div class="panel panel-primary">
				<div class="panel-heading">器材列表</div>
				<div class="panel-body"> 
					<button type="button" class="btn btn-warning" data-toggle="collapse" data-target="#namePan" >
						<span class="glyphicon glyphicon-plus"></span>
						添加新器材
					</button>
					<button type="button" class="btn btn-danger" data-toggle="collapse" data-target="#idPan" >
						<span class="glyphicon glyphicon-trash"></span>
						删除器材
					</button>
					<div class="panel-collapse collapse" id='namePan'>
						<div class="panel-body">
							<input class="form-control" id='name' placeholder='器械名称'/>	
							<button type="button" class="btn btn-warning" onclick="add()">确认添加</button>
						</div>
					</div>
					<div class="panel-collapse collapse" id='idPan'>
						<div class="panel-body">
							<input class="form-control" id='id' placeholder='器械ID'/>	
							<button type="button" class="btn btn-danger" onclick="delet()">确认删除</button>
						</div>
					</div>
				</div>
				<table class="table table-hover">
					<thead>
						<tr>
							<td>编号</td>
							<td>器材名称</td>
							<td>状态</td>
						</tr>
					</thead>
					<tbody id='table'>
						{foreach $instrument as $line}
						<tr>
							<td>{$line.id}</td>
							<td>{$line.name}</td>
							<td>
								<div class="btn-group">
								 	<input type="button" id='statusbtn{$line.id}' class="btn btn-primary" value="{$line.status}"/>
							    	<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
							     	<span class="caret"></span>
							     	<span class="sr-only">Toggle Dropdown</span>
							   		</button>
							    	<ul class="dropdown-menu">
							      		<li><a id='在馆' onclick="getStatus('在馆',{$line.id})">在馆</a></li>
							      		<li><a id='借出' onclick="getStatus('借出',{$line.id})">借出</a></li>
							      		<li><a id='借入' onclick="getStatus('借入',{$line.id})">借入</a></li>
							      		<li><a id='报修' onclick="getStatus('报修',{$line.id})">报修</a></li>
							    	</ul>
							 	</div>
							</td>
						</tr>
						{/foreach}
					</tbody>
				</table>
			</div>
 		</div>
	</div>
</div>
{literal}
<script type="text/javascript">	
	function getStatus(data,id)
	{
		$.get('index.php?action=instrumentmanag&id='+id+'&status='+data,function(recv,status){
			$('#statusbtn'+id).val(data);
		});
	}
	function add()
	{
		$.get('index.php?action=instrumentmanag&name='+$('#name').val(),function(){
			window.location = '?action=instrumentmanag';
		});
	}
	function delet()
	{
		$.get('index.php?action=instrumentmanag&id='+$('#id').val(),function(){
			window.location = '?action=instrumentmanag';
		});
	}
</script>
{/literal}