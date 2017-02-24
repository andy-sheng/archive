<div class='container'>
	<div class='jumbotron' style='max-width:700px;margin:auto'>
		<h2>注册</h2>
		<form role='form' method='post' action='index.php?action=reg' id='regform'>
			<div id='emaildiv' class="form-group">
  			<label for="邮箱">邮箱:<small id='emailcheck'></small></label>
  			<input type="email" class="form-control" id="email" placeholder="邮箱" name='email' required autofocus>
			</div>
     <div id='name-div' class="form-group">
        <label for="用户名">用户名:<small id='name-check'></small></label>
        <input type="text" class="form-control" id="name" placeholder="邮箱" name='email' required>
      </div>
			<div id='pwd1div' class="form-group">
  			<label for="密码">密码:<small id='pwdcheck'></small></label>
  			<input type="password" class="form-control" id="pwd" placeholder="密码" name='pwd' required>
			</div>
			<div id='pwd2div' class="form-group">
  			<label for="确认密码">确认密码:</label>
  			<input type="password" class="form-control" id="pwd2" placeholder="确认密码" required> 
  		</div>
		</form>
		<button id='submitbtn'class="btn btn-default" >提交</button>
	</div>
</div>