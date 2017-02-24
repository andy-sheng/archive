

<div class="panel panel-info">
  <div class="panel-heading">
    <h1 class="panel-title">{$course['name']}</h1>
  </div>
  <div class="panel-body">
    <p>课程ID：{$course.id}</p>
    <p>开课日期：{$course.dispStarttime}</p>
    <p>课程时间：{$course.dispCoursetime} {$course.time}点</p>
    <p>课程教练：{$course.coach}</p>
    <p>课程总节数：{$course.amount}</p>
    <p>课程地点：{$course.site}</p>
    <p>课程余量：{$course.capacity}人</p>
    <p>课程价格：{$course.price}元</p>
    <p>课程简介：</p>
    <blockquote>
      <p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp{$course.description}</p>
    </blockquote>
    <p>
      <a href='index.php?action=courseinfomanag&id={$course.id}'><button type="button" class="btn btn-info"><span class="glyphicon glyphicon-wrench">修改信息</span></button></a>

      <a href='#reschedule{$course.id}' data-toggle="modal"><button type="button" class="btn btn-warning"><span class="glyphicon glyphicon-calendar">调课通知</span></button></a>

      <a href='#deletecourse{$course.id}' data-toggle="modal"><button type="button" class="btn btn-danger"><span class="glyphicon glyphicon-remove">删除课程</span></button></a>
    </p>
  </div>
  <hr>
  <div style='margin-left:50px'>
    <h2>学员名单</h2>
    <table class="table table-hover">
      <thead>
        <tr>
          <th>e-mail</th>
          <th>姓名</th>
          <th>性别</th>
          <th>电话</th>
          <th>住址</th>
        </tr>
      </thead>
      <tbody>
        {foreach $student as $line}
        <tr>
          <td><a href='mailto:{$line.email}'>{$line.email}</a></td>
          <td>{$line.name}</td>
          <td>{$line.sex}</td>
          <td>{$line.telephone}</td>
          <td>{$line.address}</td>
        </tr>
        {/foreach}
      </tbody>
    </table>
  </div> 
</div>

<div class="modal fade" id="deletecourse{$course.id}" tabindex="-1" role="dialog" aria-labelledby="deletecourseLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h2 class="modal-title" id="deletecourseLabel">删除课程：{$course.id} {$course['name']}</h2>
      </div> 
      <div class="modal-body"> 
        <form class="form-signin" role="form" action="index.php?action=projmanag" method="POST" id='dform'>
          <div class="alert alert-danger">警告：中途结束本课程会返还会员本课程全额款项。</div>  
          <input type="password" class="form-control" placeholder="请输入本账号管理员密码" name='pwd' id='pwd' required>

          <input type="submit" name="submit" class="btn btn-danger" value='确认删除'/>
          <input type="text" name='course' id='course' value='{$course.id}' style="visibility:hidden"/>
        </form>
        
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>

<div class="modal fade" id="reschedule{$course.id}" tabindex="-1" role="dialog" aria-labelledby="rescheduleLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h2 class="modal-title" id="rescheduleLabel">调整课程公告：{$course.id} {$course['name']}</h2>
      </div> 
      <div class="modal-body"> 
        
        <form class="form-signin" role="form" action="index.php?action=projmanag" method="POST" id='rform'>
          <h4 class="text-success">选择更改的课程日期</h4>
          <div class="row">
              <div class='col-sm-10'>
                  <div class="form-group">
                      <div class='input-group date' id='datetimepicker{$course.id}'>
                          <input type='text' class="form-control" id="olddatetime" name="olddatetime" readonly=""/>
                          <span class="input-group-btn">
                      <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-calendar"></span></button>
                    </span>
                          </span>
                      </div>
                  </div>
              </div>
              <script type="text/javascript" src="libs/view/bower_components/eonasdan-bootstrap-datetimepicker/src/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
              <script type="text/javascript">

                  $(function () {
                    var myDate = new Date();
                    var unavaDay = [];
                    for (var i = 0; i < 7; i++)
                    {
                      if(i != {$course.coursetime})
                        unavaDay.push(i);
                    }
                      $('#datetimepicker{$course.id}').datetimepicker({
                        format: "取消YYYY年MM月DD日的课程",
                        pickTime: false,
                        minDate: "{$course.nextcoursedate}",
                        maxDate: "{$course.lastcoursedate}",
                        daysOfWeekDisabled: unavaDay,
                        language: 'zh-CN'
                      });
                      
                  });
              </script>

          </div>

          <h4 class="text-danger">选择新的课程日期</h4>
          <div class="row">
              <div class='col-sm-10'>
                  <div class="form-group">
                      <div class='input-group date' id='datetimepicker2_{$course.id}'>
                          <input type='text' class="form-control" id="newdatetime" name="newdatetime" readonly=""/>
                          <span class="input-group-btn">
                      <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-calendar"></span></button>
                    </span>
                          </span>
                      </div>
                  </div>
              </div>
              <script type="text/javascript" src="libs/view/bower_components/eonasdan-bootstrap-datetimepicker/src/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
              <script type="text/javascript">

                  $(function () {
                    var myDate = new Date();
                    
                      $('#datetimepicker2_{$course.id}').datetimepicker({
                        format: "调整至YYYY年MM月DD日星期dd A hh:00上课",
                        minDate: myDate,
                        useMinutes: false,
                        useSeconds: false,
                        sideBySide: true,
                        pick12HourFormat: true,
                        language: 'zh-CN'
                      });
                      
                  });
              </script>

          </div>

          <h6 id='message' class="text-danger"></h6>
          <input type='button' onclick='confirm()' class="btn btn-danger"  value='确定' />
          <input type="text" name='course' id='course' value='{$course.name}' style="visibility:hidden"/>
        </form>
        <script>
          function confirm()
          {
            if($('#olddatetime').val() == ''){
              $('#error').css('display','block');
              $('#message').html('请选择更改的课程日期');
            }
            else if($('#newdatetime').val() == ''){
              $('#error').css('display','block');
              $('#message').html('请选择新的课程日期');
            }
            else{
              var hour = $('#newdatetime').val().split(' ')[2].split(':')[0];       
              var period = $('#newdatetime').val().split(' ')[1];
              if(period == '凌晨' || period == '早上' && hour < 9 || period == '晚上' && hour >= 9){
                $('#error').css('display','block');
                $('#message').html('上课时间应在9:00--20:00之间的整点');
              }
              else
                $('#rform').submit();
            }
          }
        </script>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>

