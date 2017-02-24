
<div class='container'>
  <div class="jumbotron">

    <h1>课程管理</h1>
    
    <a href='index.php?action=coursemanag&add=request'><button type="button" class="btn btn-success"><span class="glyphicon glyphicon-plus">申请添加课程</span></button></a>
    <a href='index.php?action=coursemanag'><button type="button" class="btn btn-info"><span class="glyphicon glyphicon-th-list">现教课程</span></button></a>

    <div class="panel-group" id="accordion">
    
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
            <span class="glyphicon glyphicon-search">查询所教的课程</span>
          </a>
        </h4>
      </div>
      
      <div id="collapseOne" class="panel-collapse collapse">
        <div class="panel-body">
          
          <form class="navbar-form navbar-left" role="search" action="index.php?action=coursemanag" method="POST">
            <div class="input-group">
              <div class="input-group-btn">
                <input type="button" id="searchtype" class="btn btn-default dropdown-toggle" data-toggle="dropdown" value="查找方式 ▽"></input>
                <ul class="dropdown-menu" role="menu">
                  <li><a onclick="setSearchTypeID()">课程ID</a></li>
                  <li><a onclick="setSearchTypeName()">课程名称</a></li>
                </ul>
              </div><!-- /btn-group -->
              <input type="text" name="searchkey" class="form-control">
              <span class="input-group-btn">
                <button class="btn btn-default" type="submit">Go!</button>
              </span>
            </div>
            <input type="text" name='searchtype' id='search_type' value="" style="visibility:hidden"/>
          </form>
        
        </div>
      </div>
    
    </div>
    
  </div>