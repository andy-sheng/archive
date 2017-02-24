<div class='container'>
	<div class='jumbotron' >
		<h1>{$name}</h1>
		<div class="panel panel-primary">
			<div class="panel-heading">个人信息<a href="?action=infomodify" style='color:yellow'>[修改]</a></div>
			<div>
				<table class="table table-hover">
					<thead>
						<tr>
							<td><h4>账号</h4></td>
							<td><h4>姓名</h4></td>
							<td><h4>性别</h4></td>
							<td><h4>等级</td>
							<td><h4>会员卡号</h4></td>
							<td><h4>会员卡状态</h4></td>
							<td><h4>账户余额</h4></td>
							<td><h4>电话</h4></td>
							<td><h4>住址</h4></td>
							<td><h4>昵称/用户名</h4></td>
						</tr>
					</thead>
					<tbody>
					
						<tr>
							<td><h5>{$info.email}</h5></td>
							<td><h5>{$info.name}</h5></td>
							<td><h5>{$info.sex}</h5></td>
							<td><h5>{$info.rank}</h5></td>
							<td><h5>{$info.cardid}</h5></td>
							<td><h5>{$info.cardstatus}</h5></td>
							<td><h5>{$info.balance}元</h5></td>
							<td><h5>{$info.telephone}</h5></td>
							<td><h5>{$info.address}</h5></td>
							<td><h5>{$info.nickname}</h5></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="panel panel-primary">
			<div class="panel-heading">团体课程<a href="?action=coursebook" style='color:yellow'>[更多]</a></div>
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
			<div class="panel-heading">私教预约<a href="?action=coachbook" style='color:yellow'>[更多]</a></div>
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