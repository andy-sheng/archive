
<div class='container'>

	
		<div class="jumbotron">
       <h1>预约查询</h1>

      <div style='margin-left:50px'> 
      
        <table class="table table-hover">
          <thead>
            <tr>
              <th>预约者</th>
              <th>邮箱</th>
              <th>日期</th>
              <th>时间</th>
              <th>留言</th>
              <th><a href="index.php?action=booklookup"><span class="glyphicon glyphicon-refresh"></span></a></th>
            </tr>
          </thead>
          <tbody>
          {foreach $coachbook as $line}
          <tr>
            <td>{$line.username}</td>
            <td><a href="mailto:{$line.email}">{$line.email}</a></td>
            <td>{$line.date}</td>
            <td>{$line.time}:00 -- {$line.time + 1}:00</td>
            <td>
                <p>{$line.addition}</p>
            </td>
          </tr>
          {/foreach}
          </tbody>
        </table>
      </div> 
		</div>
</div>

