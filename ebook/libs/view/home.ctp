<div>
  <div class="row" >
  <div class="col-lg-8 col-md-offset-2">
    <div class="input-group">
      <div class="input-group-btn">
        <button type="button" id='search-type'class="btn btn-default dropdown-toggle" data-toggle="dropdown">书名 <span class="caret"></span></button>
        <ul class="dropdown-menu" role="menu">
          <li><a href="javascript:void(0)" onclick="dropMenu('书名', 'search-type')">书名</a></li>
          <li><a href="javascript:void(0)" onclick="dropMenu('作者', 'search-type')">作者</a></li>
          <li><a href="javascript:void(0)" onclick="dropMenu('ISBN', 'search-type')">ISBN</a></li>
        </ul>
      </div><!-- /btn-group -->
      <input type="text" class="form-control" id='query' required autofocus>
      <span class="input-group-btn">
        <button class="btn btn-default" type="button" id='search' onclick='search()'>
        <span class="glyphicon  glyphicon-search"></span>

        搜索
        </button>
      </span>
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
</div><!-- /.row -->
</div>