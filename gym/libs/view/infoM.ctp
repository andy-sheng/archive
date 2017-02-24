
<div class="container">
  <div class='jumbotron' style='max-width:700px;margin:auto'>
    <h2>个人信息修改</h2>
    <form role="form" class='infoModForm' method="POST" action="index.php?action=infomodify">
      <div class="form-group">
        <label for="exampleInputPassword1">用户名</label>
        <input type="text" class="form-control" id="nickname" name='nickname' placeholder="用户名" value='{$info['nickname']}'>
      </div>
      <div class="form-group">
        <label for="exampleInputPassword1">密码</label>
        <input type="password" class="form-control" id="pwd" name='pwd' placeholder="密码" value='{$info['pwd']}'>
      </div>
      <div class="form-group">
        <label for="exampleInputPassword1">等级</label>
        <input type="text" class="form-control" id="rank" name='rank' placeholder="等级" value='{$info['rank']}' disabled="true">
      </div>
      {if $type eq 'customer'}
      <div class="form-group">
        <label for="exampleInputPassword1">支付密码</label>
        <input type="password" class="form-control" id="ppwd" name='paypwd' placeholder="支付密码" value='{$info['paypwd']}' >
      </div>
      {/if}
      
      <div class="form-group">
        <label for="exampleInputPassword1">姓名</label>
        <input type="text" class="form-control" id="name" name='name' placeholder="名字" value='{$info['name']}'>
      </div>
      <div class="form-group">
        <label for="exampleInputPassword1">性别</label>
      {if $info['sex'] eq '男'}
        <div class="radio input-sm">
          <label>
            <input type="radio" name="sex" id="optionsRadios1" value="男" checked>男
          </label>
        </div>
        <div class="radio input-sm  ">
          <label>
            <input type="radio" name="sex" id="optionsRadios2" value="女"> 女
          </label>
        </div>
        {else if}
          <div class="radio input-sm">
            <label>
              <input type="radio" name="sex" id="optionsRadios1" value="男" >男
            </label>
          </div>
          <div class="radio input-sm  ">
            <label>
              <input type="radio" name="sex" id="optionsRadios2" value="女" checked> 女
            </label>
          </div>
        {/if}
      </div>
      <div class="form-group">
        <label for="exampleInputPassword1">电话</label>
        <input type="tel" class="form-control" id="telephone" name='telephone' placeholder="电话" value='{$info['telephone']}'>
      </div>
      <div class="form-group">
        <label for="exampleInputPassword1">家庭住址</label>
        <input type="text" class="form-control" id="address" name='address' placeholder="家庭住址" value='{$info['address']}'>
      </div>
      <button type="submit" class="btn btn-primary">确定</button>
    </form>
  </div>
</div>
