<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="libs/view/img/icon.jpg">

    <title>Group C Fitness 健身房</title>
    <!-- Bootstrap core CSS -->
    <link href="libs/view/css/bootstrap.css" rel="stylesheet">
    <script src="libs/jquery.js"></script>

    <script type="text/javascript" src="libs/view/bower_components/moment/min/moment.min.js"></script>
    <script type="text/javascript" src="libs/view/bower_components/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"></script>
    <link rel="stylesheet" href="libs/view/bower_components/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css" />
    <!-- Custom styles for this template -->
    <link href="navbar-fixed-top.css" rel="stylesheet">
     <style type="text/css">
      body {
        padding-top: 70px;
        background-image: url(libs/view/img/running.jpg);
      }
      a {
        cursor: pointer;
      }
      .infoModForm {
        max-width: 500px;
        margin: auto;
      }
      .picroll {
        max-width: 960px;
        margin: auto;
      }
    </style>
    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="../../docs-assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <!-- Fixed navbar -->
    <div class="navbar navbar-default navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="?">Group C Fitness 健身房</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="?">首页</a></li>
            <li class="dropdown">
             <a href='' class='dropdown-toggle' data-toggle='dropdown'>功能表 <b class='caret'></b></a>
             {$dropmenu}
            </li>
            <li><a data-toggle="modal" href="#contact">联系我们</a></li>
            <li><a data-toggle="modal" href="#about">关于</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li><a style='cursor:default'>欢迎您，{$name}</a></li>
            <li class="dropdown">
              <a class='dropdown-toggle' data-toggle='dropdown'>公告<span class="badge">{$notification|@count}</span></a>
              <ul class='dropdown-menu'>
              {foreach $notification as $line}
                <li><a data-toggle="modal" href="#{$line.id}">{$line.head}</a></li><!-- 修改-->
              {/foreach}
              </ul>
            </li>
            <li> <a href="login.php?action=logout"> 退出登录</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>
     {foreach $notification as $line}
      <div class="modal fade" id="{$line.id}" tabindex="-1" role="dialog" aria-labelledby="notify{$line.id}" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
              <h2 class="modal-title" id="notify{$line.id}">{$line.head}</h2>
            </div>
            <div class="modal-body">
            <h4>&nbsp&nbsp{$line.content}</h4>
            <h5 style='text-align: right;margin-bottom: 0px' id="notify{$line.id}">{$line.date|date_format:"%Y年%m月%d日"}</h5>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
             <!-- <button type="button" class="btn btn-primary">Save changes</button>-->
            </div>
          </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
      </div><!-- /.modal -->
      {/foreach}