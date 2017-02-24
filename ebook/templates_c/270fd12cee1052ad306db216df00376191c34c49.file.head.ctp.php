<?php /* Smarty version Smarty-3.1.18, created on 2014-09-22 00:52:26
         compiled from "libs\view\head.ctp" */ ?>
<?php /*%%SmartyHeaderCode:14118541c6cb7b89865-88347504%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_valid = $_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '270fd12cee1052ad306db216df00376191c34c49' => 
    array (
      0 => 'libs\\view\\head.ctp',
      1 => 1411227338,
      2 => 'file',
    ),
  ),
  'nocache_hash' => '14118541c6cb7b89865-88347504',
  'function' => 
  array (
  ),
  'version' => 'Smarty-3.1.18',
  'unifunc' => 'content_541c6cb7d72716_82538285',
  'variables' => 
  array (
    'logined' => 0,
  ),
  'has_nocache_code' => false,
),false); /*/%%SmartyHeaderCode%%*/?>
<?php if ($_valid && !is_callable('content_541c6cb7d72716_82538285')) {function content_541c6cb7d72716_82538285($_smarty_tpl) {?><!DOCTYPE html>
<html>
<head>
    <meta name="baidu-site-verification" content="LfiqymI8xF" />
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Kindle电子书下载，推送平台</title>

    <!-- Bootstrap -->
    <link href="http://myebook.jd-app.com/css/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="http://myebook.jd-app.com/css//my.css">
    <a href='http://myebook.jd-app.com/sitemap.xml' style='visbility:none'></a>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
<body style='background-image: url(http://myebook.jd-app.com/img/background.png)'>
	
	
		    <!-- Fixed navbar -->
    <div class="navbar navbar-default navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="http://myebook.jd-app.com/index.html">Myebook</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="http://myebook.jd-app.com/index.html">首页</a></li>
            <li><a data-toggle="modal" href="#about">关于</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
              <li>
                <a href='http://myebook.jd-app.com/upload.html'>
                <span class='glyphicon glyphicon-cloud-upload'></span>
                上传
                </a>
              </li>
            <?php if ($_smarty_tpl->tpl_vars['logined']->value['flag']=='true') {?>
              <li>
                <a href='http://myebook.jd-app.com/setting.html'>
                  <span class='glyphicon glyphicon-cog'></span>
                  设置
                </a>
              </li>
              <li> 
                <a href="http://myebook.jd-app.com/logout.html"> 
                  <span class='glyphicon glyphicon-log-out'>
                  </span>
                  注销
                </a>
              </li>
            <?php } else { ?>
              <li>
                <a a data-toggle="modal" href="#login"><span class='glyphicon glyphicon-log-in'></span>
                  登录
                </a>
              </li>
              <li> 
                <a href="http://myebook.jd-app.com/reg.html">
                <span class='glyphicon glyphicon-pencil'></span>
                注册
                </a>
              </li>
            <?php }?>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>
    <div class="container" style='padding-top:100px'><?php }} ?>
