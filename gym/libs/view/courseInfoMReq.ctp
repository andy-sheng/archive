
<div class="container">
  <div class='jumbotron' style='max-width:700px;margin:auto'>
    <h2>课程信息修改申请</h2>
    <form role="form" id='form' class='infoModForm' method="POST" action="index.php?action=courseinfomanagreq">
      <div class="form-group">
        <label for="exampleInputPassword1">课程余量</label>
        <input type="text" class="form-control" id="capacity" name='capacity' placeholder="课程余量" value='{$courseinfo['capacity']}'>
      </div>
      <div class="form-group">
        <label for="exampleInputPassword1">地点</label>
        <input type="text" class="form-control" id="site" name='site' placeholder="地点" value='{$courseinfo['site']}'>
      </div>
      <div class="form-group">
        <label for="exampleInputPassword1">价格</label>
        <input type="text" class="form-control" id="price" name='price' placeholder="价格" value='{$courseinfo['price']}'>
      </div>
      <div class="form-group">
        <label for="exampleInputPassword1">简介</label>
        <textarea type="text" class="form-control" rows="5" id="description" name="description" value='{$courseinfo['description']}'>{$courseinfo['description']}</textarea>
      </div>
      
      <button type="submit" class="btn btn-primary">确定</button>
      <button type="button" onclick="cancel()" class="btn btn-primary" onclick>取消</button>
      <input type="text" name='courseid' id='courseid' value="{$id}" style="visibility:hidden"/>
    </form>
  </div>
</div>

<script type="text/javascript">
{literal}
  function cancel()
  {
    document.all("form").setAttribute("action", "index.php?action=coursemanagreq&operation=cancel");
    $('#form').submit();  
  }
{/literal}
</script>