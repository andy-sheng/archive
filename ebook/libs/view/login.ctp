<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../../docs-assets/ico/favicon.png">

    <title>登录</title>

    <!-- Bootstrap core CSS -->
    <link href="libs/view/css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->

    <style type="text/css">
      .form-signin {
        max-width: 350px;
        padding: 15px;
        margin: 0 auto;
      }
      body {
        font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
        font-size: 14px;
        line-height: 1.428571429;
        color: #333333;
        padding-top: 40px;
        padding-bottom: 40px;
      }
      #form {
         background-color: #eee;
         border-radius: 6px;
      }
      #heading {
        text-align: center;
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

  <body style='background-image: url(libs/view/img/background.png)'>

    <div class="container">
      <div>
         <form class="form-signin" role="form" action="index.php?action=login" method="POST" id='form'>
          <h1 id="heading">hehe</h1>
          <h3 class="form-signin-heading">请登录</h3>
          <input type="text" class="form-control" placeholder="邮箱" name='email' id='email' value='{$email}' required autofocus >
          <input type="password" class="form-control" placeholder="密码" name='pwd' id='pwd' value='{$pwd}'required>
          <label class="checkbox" style='margin-left:20px'>
            <input type="checkbox"  name='remember' id='remember' value="{$remember}"> 记住我
          </label>
          <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
         </form>
      </div>
     
    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
  </body>
   <script src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
</html>