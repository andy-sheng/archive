<div class='container'>
	<div class='jumbotron' style='max-width:700px;margin:auto;min-height:400px'>
		<div class="btn-group">
		 	<input type="button" id='typebtn' class="btn btn-primary" value="查询种类"/>
	    	<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
	     	<span class="caret"></span>
	     	<span class="sr-only">Toggle Dropdown</span>
	   		</button>
	    	<ul class="dropdown-menu">
	      		<li><a id='消费记录' onclick="getType(this)">消费记录</a></li>
	      		<li><a id='充值记录' onclick="getType(this)">充值记录</a></li>
	      		<li><a id='两者' onclick="getType(this)">两者</a></li>
	    	</ul>
	 	</div>
	 	<div class="btn-group">
		 	<input type="button" id='datebtn' class="btn btn-primary" value='查询日期'/>
	    	<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
	     	<span class="caret"></span>
	     	<span class="sr-only">Toggle Dropdown</span>
	   		</button>
	    	<ul class="dropdown-menu">
	      		<li><a id='一周内' onclick="getDate(this)">一周内</a></li>
	      		<li><a id='两周内' onclick="getDate(this)">两周内</a></li>
	      		<li><a id='一个月内' onclick="getDate(this)">一个月内</a></li>
	      		<li><a id='两个月内' onclick="getDate(this)">两个月内</a></li>
	      		<li><a id='一年内' onclick="getDate(this)">一年内</a></li>
	      		<li><a id='所有' onclick="getDate(this)">所有</a></li>
	    	</ul>
	 	</div>
	 	<div class="btn-group" style='padding-left:200px'>
	 		<button type="button" class="btn btn-success" id='lookup' onclick="lookup()">
	 			<span class="glyphicon glyphicon-search"></span>
	 			查询
	 		</button>
	 	</div>
	 	<div class="panel panel-primary">
	 		<div class='panel-heading'>
	 			<h5>查询结果</h5>
	 		</div>
  			<div class="panel-body">
  				<table class="table table-hover">
  					<thead>
  						<tr>
  							<td><h5>操作类型</h5></td>
  							<td><h5>金额(元)</h5></td>
  							<td><h5>时间</h5></td>
  						</tr>
  					</thead>
  					<tbody id='table'>
  					</tbody>
				</table>
 			</div>
		</div>
		<div class="panel panel-primary">
			<div class="panel-heading">课程信息</div>
			<div>
				<table class="table table-hover">
					<thead>
						<tr>
							<td><h4>课程编号</h4></td>
							<td><h4>课程名称</h4></td>
							<td><h4>课程开始时间</td>
							<td><h4>上课地点</h4></td>
							<td><h4>上课时间</h4></td>
						</tr>
					</thead>
					<tbody>
					{foreach $course as $line}
						<tr>
							<td><h5>{$line.id}</h5></td>
							<td><h5>{$line.name}</h5></td>
							<td><h5>{$line.starttime|date_format:"%Y年%m月%d日"}</h5></td>
							<td><h5>{$line.site}</h5></td>
							<td><h5>{$line.coursetime} {$line.time}点</h5></td>
						</tr>
					{/foreach}
					</tbody>
				</table>
			</div>
		</div>
		<div class="panel panel-primary">
			<div class="panel-heading">私教信息</div>
			<div>
				<table class="table table-hover">
					<thead>
						<tr>
							<td><h4>私教帐号</h4></td>
							<td><h4>私教名字</h4></td>
							<td><h4>预约时间</h4></td>
							<td><h4>额外要求</h4></td>
						</tr>
					</thead>
					<tbody>
					{foreach $coach as $line}
						<tr>
							<td><h5>{$line.coachemail}</h5></td>
							<td><h5>{$line.coachname}</h5></td>
							<td><h5>{$line.date|date_format:"%Y年%m月%d日"}{$line.time}点</h5></td>
							<td><h5>{$line.addition}</h5></td>
						</tr>
					{/foreach}
					</tbody>
				</table>
			</div>
		</div>
	</div>

</div>

<script type="text/javascript">
	function lookup()
	{
		$.get("index.php?action=lookup&type="+$('#typebtn').val()+"&date="+$('#datebtn').val(),function(data,status){
    		$('#table').html(data);
  		});
	}
	function getDate(data)
	{
		var date = $(data).attr('id');
		$('#datebtn').val(date);
	}
	function getType(data)
	{
		var type = $(data).attr('id');
		$('#typebtn').val(type);
	}
</script>