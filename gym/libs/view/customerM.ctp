<div class="alert alert-danger alert-dismissable" id='error' style='display:none'>
  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
  <strong id='message'>请输入Email</strong>
</div>
<div class="alert alert-success alert-dismissable" id='success' style='display:none'>
  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
  <strong id='successm'></strong>
</div>
<div class='container'>
	<div class='jumbotron' style='max-width:700px;margin:auto;min-height:400px'>
		<form role="form" method="POST" action="index.php?action=infomodify" style='margin:auto'>
			<div class="btn-group" style=''>
				<input type="text" class="form-control" id="email" placeholder="会员邮箱" >
		 	</div>
		 		<input type='button' onclick='lookup()' class="btn btn-primary"	value='确定' />
		 		<input type='button' onclick='lockFunc()'style='visibility:hidden' class="btn btn-danger" id='lock' value='挂失' />
		 		<input type='button' onclick='unlockFunc()'style='visibility:hidden'  class="btn btn-danger"id='unlock'	value='解挂' />
		 		<input type='button' style='visibility:hidden'class="btn btn-primary btn-warning"  id='changeRank' role="button"  href="#rankbox" data-toggle="modal"	value='修改等级'/>
		 		<input type='button' style='visibility:hidden'class="btn btn-primary btn-primary"  id='charge' role="button"  href="#inputbox" data-toggle="modal" value='充值'/>
		 </form>
		 <div id='result'>
		 	
		 </div>
	</div>
</div>
<div class="modal fade" id="inputbox" tabindex="-1" role="dialog" aria-labelledby="input" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
          <h2 class="modal-title" >请输入充值金额</h2>
        </div>
        <div class="modal-body">
      		<div class="btn-group" style=''>
				<input type="text" class="form-control" id="balance" placeholder="请输入金额" >
		 	</div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal" onclick="charge()">确定</button>
         <!-- <button type="button" class="btn btn-primary">Save changes</button>-->
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->
 <div class="modal fade" id="rankbox" tabindex="-1" role="dialog" aria-labelledby="inputrank" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
          <h2 class="modal-title" >请输入等级</h2>
        </div>
        <div class="modal-body">
      		<div class="btn-group" style=''>
				<input type="text" class="form-control" id="newrank" placeholder="请输入等级" >
		 	</div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal" onclick="changeRankFunc()">确定</button>
         <!-- <button type="button" class="btn btn-primary">Save changes</button>-->
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->
<script type="text/javascript">
	$('#email').change(function(){
		$('#success').css('display','none');
	});
	function lookup()
	{
		if($('#email').val() == '')
			$('#error').css('display','block');
		else
			$('#error').css('display','none');
		$.get("index.php?action=customermanag&ajax="+$('#email').val(),function(data,status){
			if(data == 'null'){
				$('#charge').css('visibility','hidden');
				$('#lock').css('visibility','hidden');
    			$('#unlock').css('visibility','hidden');
    			$('#changeRank').css('visibility','hidden');
	    		$('#result').html('无此用户');
    		}
    		else{
    			$('#charge').css('visibility','visible');
    			$('#lock').css('visibility','visible');
    			$('#unlock').css('visibility','visible');
    			$('#changeRank').css('visibility','visible');
	    		$('#result').html(data);
    		}
  		});

	}
	function charge()
	{
		$.get("index.php?action=customermanag&ajax="+$('#email').val()+"&balance="+$('#balance').val(),function(data,status){
			if(data=='success'){
				$('#successm').html('充值成功');
				$('#success').css('display','block');
				$('#showbalance').html($('#showbalance').html()*1+$('#balance').val()*1);
			}	
			$('#balance').val('');
		});
	}
	function lockFunc()
	{
		$.get("index.php?action=customermanag&ajax="+$('#email').val()+"&lock=true",function(data,status){
			$('#successm').html('挂失成功');
			$('#success').css('display','block');
			$('#status').html('挂失');
		});
	}
	function unlockFunc()
	{
		$.get("index.php?action=customermanag&ajax="+$('#email').val()+"&unlock=true",function(data,status){
			$('#successm').html('解挂成功');
			$('#success').css('display','block');
			$('#status').html('正常');
		});
	}
	function changeRankFunc()
	{
		$.get("index.php?action=customermanag&ajax="+$('#email').val()+"&changerank="+$('#newrank').val(),function(data,status){
			$('#successm').html('修改成功');
			$('#success').css('display','block');
			$('#showrank').html($('#newrank').val());
			$('#newrank').val('');
		});
	}
</script>