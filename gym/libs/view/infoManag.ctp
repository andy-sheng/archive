<div class='container'>
	<div class='jumbotron'>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<button type="button" class="btn btn-danger" data-toggle="collapse" data-target="#offPan" >
					<span class="glyphicon glyphicon-plane"></span>
					申请请假
				</button>
				<button type="button" class="btn btn-warning" data-toggle="collapse" data-target="#timePan" >
					<span class="glyphicon glyphicon-wrench"></span>
					设置可约时间
				</button>
				<div class="panel-collapse collapse" id='offPan'>
					<div class="panel-body">
						<div class="btn-group">
							<input type='button' id='dayoff' class="btn btn-warning dropdown-toggle" value='选择日期 '/>
						  	<button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown">
						    	<span class="caret"></span>
						  	</button>
						  	<ul class="dropdown-menu" role="menu">
						    	<li>
						    		<a onclick="setDay('{($smarty.now+86400)}',this)">
						    			{($smarty.now+86400)|date_format:'%Y年%m月%d日'}
						    		</a>
						    	</li>
						    	<li>
						    		<a onclick="setDay('{($smarty.now+86400*2)}',this)">
						    			{($smarty.now+86400*2)|date_format:'%Y年%m月%d日'}
						    		</a>
						    	</li>
						    	<li>
						    		<a onclick="setDay('{($smarty.now+86400*3)}',this)">
						    			{($smarty.now+86400*3)|date_format:'%Y年%m月%d日'}
						    		</a>
						    	</li>
						    	<li>
						    		<a onclick="setDay('{($smarty.now+86400*4)}',this)">
						    			{($smarty.now+86400*4)|date_format:'%Y年%m月%d日'}
						    		</a>
						    	</li>
						    	<li>
						    		<a onclick="setDay('{($smarty.now+86400*5)}',this)">
						    			{($smarty.now+86400*5)|date_format:'%Y年%m月%d日'}
						    		</a>
						    	</li>
						    	<li>
						    		<a onclick="setDay('{($smarty.now+86400*6)}',this)">
						    			{($smarty.now+86400*6)|date_format:'%Y年%m月%d日'}
						    		</a>
						    	</li>
						    	<li>
						    		<a onclick="setDay('{($smarty.now+86400*7)}',this)">
						    			{($smarty.now+86400*7)|date_format:'%Y年%m月%d日'}
						    		</a>
						    	</li>
						  	</ul>
						</div>
						<button type="button" class="btn btn-warning" onclick="sendDayOff()">确认申请</button>
					</div>
				</div>
				<div class="panel-collapse collapse" id='timePan'>
					<div class="panel-body">
						<div class="btn-group">
							<input type='button' id='startTime' class="btn btn-warning dropdown-toggle" value='选择起始时间 '/>
						  	<button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown">
						    	<span class="caret"></span>
						  	</button>
						  	<ul class="dropdown-menu" role="menu">
						    	<li><a onclick="setStartTime('9')">9点</a></li>
						    	<li><a onclick="setStartTime('10')">10点</a></li>
						    	<li><a onclick="setStartTime('11')">11点</a></li>
						    	<li><a onclick="setStartTime('12')">12点</a></li>
						    	<li><a onclick="setStartTime('13')">13点</a></li>
						    	<li><a onclick="setStartTime('14')">14点</a></li>
						    	<li><a onclick="setStartTime('15')">15点</a></li>
						    	<li><a onclick="setStartTime('16')">16点</a></li>
						    	<li><a onclick="setStartTime('17')">17点</a></li>
						    	<li><a onclick="setStartTime('18')">18点</a></li>
						    	<li><a onclick="setStartTime('19')">19点</a></li>
						  	</ul>
						</div>
						<div class="btn-group">
							<input type='button' id='endTime' class="btn btn-warning dropdown-toggle" value='选择结束时间 '/>
						  	<button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown">
						    	<span class="caret"></span>
						  	</button>
						  	<ul id='endTimeMenu'class="dropdown-menu" role="menu">
						    	
						    	
						  	</ul>
						</div>
						<button type="button" class="btn btn-warning" onclick="sendTime()">确认设置</button>
					</div>
				</div>
			</div>
			<div class="panel-body"> 
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
   					<tbody>
   					{foreach $rate as $line}
   						<tr>
   							<td><h5>{$line.responsibility}</h5></td>
   							<td><h5>{$line.planning}</h5></td>
   							<td><h5>{$line.listener}</h5></td>
   							<td><h5>{$line.fame}</h5></td>
   							<td><h5>{$line.experience}</h5></td>
   							<td><h5>{$line.progress}</h5></td>
   							<td><h5>{$line.comment}</h5></td>
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
	var dayoff;
	var startTime;
	var endTime;
	function setDay(data,source)
	{
		dayoff = data;
		$('#dayoff').val($(source).html());
	}
	function setStartTime(time)
	{
		startTime = time;
		$('#startTime').val(time+'点');
		var html = "";
		for(var i=++time; i<21; i++){
			html = html + "<li><a onclick=\"setEndTime('"+i+"')\">"+i+"点</a></li>";
		}
		$('#endTimeMenu').html(html);
	}
	function setEndTime(time)
	{
		endTime = time;
		$('#endTime').val(time+'点');
	}
	function sendDayOff()
	{
		$.get('index.php?action=infomanag&dayoff='+dayoff,function(data,status){

		});
		$('#offPan').collapse('hide');
	}
	function sendTime()
	{
		$.get('index.php?action=infomanag&startTime='+startTime+'&endTime='+endTime,function(data,status){

		});
		$('#timePan').collapse('hide');
	}
</script>
{/literal}