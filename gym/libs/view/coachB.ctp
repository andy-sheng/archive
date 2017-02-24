
<div class='container'>
	{foreach $coach as $line}
		<div class="jumbotron">
      <h1>{$line.name}</h1>
      <div style='margin-left:50px'> 
        <p>性别：{$line.sex}</p>
        <p>邮箱：{$line.email}</p>
        <p>电话：{$line.telephone}</p>
        <p>住址：{$line.address}</p>
        <p>等级：{$line.rank}</p>
        <p>单次课金额：100元</p>
        {if $balance >= 100}
        <a class="btn btn-primary btn-primary" id='{$line.email}'name="coachbook" role="button" style="margin-right:0px" onclick="setId('{$line.email}')">
          <span class="glyphicon glyphicon-shopping-cart"></span>
          预约教练
        </a>
        {else}
         <a class="btn btn-default" role="button" style="margin-right:0px" href="#coachbook" data-toggle="modal" disable='true'>
          <span class="glyphicon glyphicon-shopping-cart"></span>
          余额不足
        </a>
        {/if}
      </div> 
		</div>
	{/foreach}
</div>
<!-- Modal联系我们 -->
<div class="modal fade" id="coachbook" tabindex="-1" role="dialog" aria-labelledby="coachbookLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h2 class="modal-title" id="coachbookLabel"></h2>
      </div> 
      <div class="modal-body">     
        预约日期：    
        <div class="btn-group">
          <input id='date'  type="button" class="btn btn-primary" value='选择日期'/>
          <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
            <span class="caret"></span>
            <span class="sr-only">Toggle Dropdown</span>
          </button>
          <ul class="dropdown-menu" role="menu">
            {foreach $date as $line}
            <li><a onclick="getDate('{$line|date_format:'%Y年%m月%d日'}',{$line})">{$line|date_format:'%Y年%m月%d日'}</a></li>
            {/foreach}
          </ul>
        </div>
        <font style='margin-left:50px'>预约时间：</font>
        <div class="btn-group">
          <input id='time' type="button" class="btn btn-primary" value="选择时间"/>
          <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
            <span class="caret"></span>
            <span class="sr-only">Toggle Dropdown</span>
          </button>
          <ul id='timenemu' class="dropdown-menu" role="menu">
           <!-- <li><a onclick="getTime(9)">9点-10点</a></li>
            <li><a onclick="getTime(10)">10点-11点</a></li>
            <li><a onclick="getTime(11)">11点-12点</a></li>
            <li><a onclick="getTime(12)">12点-13点</a></li>
            <li><a onclick="getTime(13)">13点-14点</a></li>
            <li><a onclick="getTime(14)">14点-15点</a></li>
            <li><a onclick="getTime(15)">15点-16点</a></li>
            <li><a onclick="getTime(16)">16点-17点</a></li>
            <li><a onclick="getTime(17)">17点-18点</a></li>
            <li><a onclick="getTime(18)">18点-19点</a></li>
            <li><a onclick="getTime(19)">19点-20点</a></li>
            <li><a onclick="getTime(20)">20点-21点</a></li>-->
          </ul>
        </div>
        <h3>对私教的要求</h3>
        <textarea id='addition'class="form-control" rows="3" name='comment'></textarea>
      </div>
      <div class="modal-footer">
        <input type="submit" name="submit" class="btn btn-primary" value='确定预约'/>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
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
{literal}
  var coachEmail;
  $("[name='coachbook']").click(function(){
    var email = $(this).attr('id');
    coachEmail = email;
    $("[name='submit']").attr('href','index.php?action=coachbook&email='+email+"&addition=");
  });
  $("[name='submit']").click(function(){
    var addition = $('#addition').val();
    var date = $('#date').attr('name');
    var time = $('#time').attr('name');
    document.location.href=$(this).attr('href')+addition+'&date='+date+'&time='+time;
  });
  function getDate(data,timeStamp)
  {
    $('#date').val(data);
    $('#date').attr('name',timeStamp);//时间戳
    $.get("index.php?action=coachbook&email="+coachEmail+"&ajax=gettime&date="+timeStamp,function(data,status){
      var time =  eval('('+data+')');
      var html = '';
      for(var i=9; i<=20; i++)
        if(time[i] == 'free')
          html = html + "<li><a onclick='getTime("+i+")'>"+i+"点-"+(i+1)+"点</a></li>";
      $('#timenemu').html(html);

    });
  }
  function getTime(data)
  {
    $('#time').val(data+'点-'+(data+1)+'点');
    $('#time').attr('name',data);
  }
{/literal}
</script>
<script type="text/javascript">
  var payPwd = {$payPwd};
  var id ;
{literal}
  function setId(i)
  { 
    id = i;
    $('#paypwd').modal('show');
  }
  function checkPwd()
  {
    var tmp = $('#payPwd').val();
    if(tmp == payPwd){
      $('#payPwd').val('');
      $('#error').css('visibility','hidden');
      $('#paypwd').modal('hide');
      $('#coachbook').modal('show');
    }
    else{
      $('#payPwd').val('');
      $('#error').css('visibility','visible');
    }
  }
{/literal}
</script>