
    <div class="col-lg-4">

      <form class="navbar-form navbar-left" role="search" action="index.php?action=projmanag" method="POST">
        <div class="input-group">
          <div class="input-group-btn">
            <input type="button" id="searchtype" class="btn btn-default dropdown-toggle" data-toggle="dropdown" value="查找课程 ▽"></input>
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

    <div class="col-lg-8"></div>
</br>
		</div>
</div>

<script type="text/javascript">
{literal}	
	function setSearchTypeID()
	{
		$('#searchtype').val("课程ID");
		$('#search_type').val("ID");
	}
	function setSearchTypeName()
	{
		$('#searchtype').val("课程名称");
		$('#search_type').val("name");
	}
{/literal}
</script>