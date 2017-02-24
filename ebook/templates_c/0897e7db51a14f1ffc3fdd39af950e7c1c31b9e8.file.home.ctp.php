<?php /* Smarty version Smarty-3.1.18, created on 2014-09-20 01:49:43
         compiled from "libs\view\home.ctp" */ ?>
<?php /*%%SmartyHeaderCode:16231541c6cb7f15375-47939362%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_valid = $_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '0897e7db51a14f1ffc3fdd39af950e7c1c31b9e8' => 
    array (
      0 => 'libs\\view\\home.ctp',
      1 => 1411145860,
      2 => 'file',
    ),
  ),
  'nocache_hash' => '16231541c6cb7f15375-47939362',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
  'version' => 'Smarty-3.1.18',
  'unifunc' => 'content_541c6cb8020c00_03597754',
),false); /*/%%SmartyHeaderCode%%*/?>
<?php if ($_valid && !is_callable('content_541c6cb8020c00_03597754')) {function content_541c6cb8020c00_03597754($_smarty_tpl) {?><div>
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
</div><?php }} ?>
