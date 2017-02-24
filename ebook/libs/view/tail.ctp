      	</div>
      	

    <div class="modal fade" id="about" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h2 class="modal-title" id="myModalLabel2">关于</h2>
          </div>
          <div class="modal-body">
           <h3>hehe</h3>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
           <!-- <button type="button" class="btn btn-primary">Save changes</button>-->
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    {if $logined.flag eq 'false'}
    <div class="modal fade" id="login" tabindex="-1" role="dialog" aria-labelledby="loginL" aria-hidden="true">
      <div class="modal-dialog modal-sm">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h3 class="modal-title" id="loginL">登录</h3>
          </div>
          <div class="modal-body">

           <form class="form-signin" role="form" action="http://myebook.jd-app.com/index.php?action=login" method="POST" id='login-form'>
            <input type="text" class="form-control" placeholder="邮箱" name='email' id='email' value='{$logined.cookie.email}' required autofocus >
            <input type="password" class="form-control" placeholder="密码" name='pwd' id='pwd' value='{$logined.cookie.pwd}'required>
            <label class="checkbox" style='margin-left:20px'>
              <input type="checkbox"  name='remember' id='remember' checked="{$logined.cookie.remember}"> 记住我
            </label>
            <button class="btn btn-lg btn-primary btn-block" type="button" id='login-btn'>登录</button>
           </form>
          </div>
    

        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal --> 
{/if}
      	<script src="http://myebook.jd-app.com/css/jquery.min.js"></script>
        <script src="http://myebook.jd-app.com/css/jquery.form.js"></script>
        
    	<!-- Include all compiled plugins (below), or include individual files as needed -->
    	<script src="http://myebook.jd-app.com/css/js/bootstrap.min.js"></script>
    	{literal}
    	<script type="text/javascript">
      function collapse(id)
      {
        var target = $('#'+id);
        if(target.attr('class') == 'glyphicon glyphicon-collapse-up')
          target.attr('class','glyphicon glyphicon-collapse-down');
        else
          target.attr('class','glyphicon glyphicon-collapse-up');
      }
      $(document).ready(function(){
        $('#login-btn').click(function() {
          $('#login-form').ajaxSubmit(function(data){
            if(data == 'success')
              //alert('1');
              location.reload();
          });
        });
        $('#email').blur(function(){
            checkEmail();
        });
        $('#pwd').blur(function(){
          checkPwd();
        });
        $('#pwd2').blur(function(){
          checkPwd();
        });
        $('#submitbtn').click(function(){
          submitt();
        });
        $('#pic').change(function(){
           $('#pic-form').ajaxSubmit(function(data) {
            var json = eval('(' + data + ')');
            $('#pic-id').val(json['id']);
             $('#pic-img').attr('src',json['url']);
           });
        });
        $('#file').change(function(){
          fileCheck();
        });
        $('#book-submit-btn').click(function(){
          $('#namecheck').html('');
          $('#filecheck').html('');
          if($('#bookname').val() != '' && $('#file').val() != '')
            $('#uploadform').submit();
          if($('#bookname').val() == '')
            $('#namecheck').html('请输入书名');
          if($('#file').val() == '')
            $('#filecheck').html('请选择书籍');
        });
        $('#new-kindle-submit').click(function(){
          var email = $('#new-kindle').val(); 
          if(isEmail(email) == true){
            $.get('http://myebook.jd-app.com/index.php?action=setting&hav=newkindle&kindle=' + email,function(data,status){
              if(data == 'success'){
                $('#kindle-form').html($('#kindle-form').html() + '<tr><td>*</td><td>' + email + '</td></tr>');
                $('#new-kindle').val('');
                $('#new-kindle-div').collapse('hide');
              }
              else{
                $('#new-kindle').val('');
                $('#new-kindle').attr('placeholder','出错了');
              }
            });
          }
          else{
            $('#new-kindle').val('');
            $('#new-kindle').attr('placeholder','请输入有效地址');
          }
        });
        $('#auto-fill').on('click', function () {
          autoFill();
        });
        $('#comment-submit').click(function(){
          var content = $('#comment-content').val();
          var id = $('#bid').html();
          if(content != ''){
            $.post('http://myebook.jd-app.com/index.php?action=detail&behav=comment&bid='+id,{comment:content,bid:id},function(data,status){
              var json = eval('('+data+')');
              var html = "<div class='list-group-item'><h5 class='list-group-item-heading'><small>"+json['email'] +"</small></h5><p class='list-group-item-text'><small>"+json['comment']+"</small></p></div>";
              $('#comment-list').html($('#comment-list').html()+html);
              $('#comment-area').collapse('hide');
              $('#comment-content').val('');
            });
          }
        });
        $('#more-comment').click(function(){
          var pg = $('#more-comment').val();
          var id = $('#bid').html();
          $.get('http://myebook.jd-app.com/index.php?action=detail&bid='+id+'&behav=morecomment&pg=' + pg,function(data,status){
            $('#comment-list').html($('#comment-list').html() + data);
            $('#more-comment').val(++pg);
          });
        });
      });
      function autoFill()
      {
        $('#namecheck').html('');
        var name = $('#bookname').val();
        if(name != ''){
          $('#auto-fill').button('loading');
          $.get('http://myebook.jd-app.com/test.php?name='+name,function(data,status){
            if(data == 'error')
              alert('填写失败');
            else{
              var json = eval('('+data+')');
              $('#bookname').val(json['name']);
              $('#author').val(json['author']);
              $('#isbn').val(json['isbn']);
              $('#authordesc').val(json['author_intro']);
              $('#desc').val(json['summary']);
              $('#pic-id').val(json['pic-id']);
              $('#pic-img').attr('src',json['img']);
              
            }
            $('#auto-fill').button('reset');
          });
        }
        else{
           $('#namecheck').html('请输入书名');
        }

      }
      function fileCheck()
      {
        var path = $('#file').val();
        var tmp = path.split('.');
        format = tmp[tmp.length - 1];
        if(format == 'mobi')
          return true;
        else{
          $('#file').val('');
          return false;
        }
      }
      function submitt()
      {
        if( checkEmail() && checkPwd())
          $('#regform').submit();
      }
      var flag = false;
      function isEmail(email)
      {
        var myreg =/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
        if(email != '' && myreg.test(email))
          return true;
        else
          return false;
      }
      function checkEmail()
      {
        var email = $('#email').val();
        var myreg =/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
        if(email != '' && myreg.test(email)){
            $.get('http://myebook.jd-app.com/index.php?action=reg&action2=checkemail&email='+email,function(data,status){
              if(data == 'notexit'){
                $('#emailcheck').html('该邮箱可以注册');
                $('#emaildiv').attr('class','form-group has-success');
                flag =  true;
              }
              else{
                $('#emailcheck').html('该邮箱已被注册');
                $('#emaildiv').attr('class','form-group has-error');
              }
            });
        }
        else{
          $('#emailcheck').html('请正确填写邮箱地址');
          $('#emaildiv').attr('class','form-group has-error');
        }
        return flag;
      }
      function checkPwd()
      {
        var pwd1 = $('#pwd').val();
        var pwd2 = $('#pwd2').val();
        if(pwd1 != '' && pwd2 != ''){
          if(pwd1 == pwd2){
            $('#pwdcheck').html('');
            $('#pwd1div').attr('class','form-group has-success');
            $('#pwd2div').attr('class','form-group has-success');
            return true;
          }
          else{
            $('#pwdcheck').html('密码不一致');
            $('#pwd1div').attr('class','form-group has-error');
            $('#pwd2div').attr('class','form-group has-error');
            
          }
        }
        return false;
      }
		  function search()
		  {
        if($('#query').val() != '')
		      window.location = 'http://myebook.jd-app.com/search/name/' + $('#query').val()+'.html';
		  }
		  $("body").keydown(function() {
             if (event.keyCode == "13") {//keyCode=13是回车键
                 $('#search').click();
             }
         });
      function doPush()
      {
        $('#doPush').button('loading');
        var email = $('#email-btn').attr('name');
        var bid = $('#bookLink').attr('name');
        $.get('http://myebook.jd-app.com/index.php?action=push&email=' + email + '&fileName=' + bid + '.mobi',function(data,status){
          $('#doPush').button('reset');
      });
      }
      function dropMenu(text, id)
      {
        $('#'+id).html(text + "<span class='caret'></span>");
        $('#'+id).attr('name',text);
      }
      var last = $('#home');
      function navBar(obj)
      {
        last.attr('class','');
        obj.attr('class','active');
      }
		</script>
    <script>
      (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
      (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
      m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
      })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

      ga('create', 'UA-54707499-1', 'auto');
      ga('send', 'pageview');



    </script>
		{/literal}
	</body>
</html>