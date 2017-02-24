<?php 
	require_once('libs/smarty/Smarty.class.php');
	require_once('libs/controller/function.php');
	require_once('libs/model/db.class.php');
	class SettingController
	{
		function __construct(){}
		function handle()
		{
			if(!isset($_SESSION['email']))
				header('location:index.php');
			if(isset($_GET['hav'])){
				$this->addKindle();
			}
			else{
				$db = new db();
				$smarty = new Smarty();
				$smarty->assign('logined',isLogined());
				$smarty->assign('kindle',$_SESSION['kindle']);
				$smarty->assign('downloads',$db->getDownloads($_SESSION['email']));
				$smarty->display('libs/view/head.ctp');
				$smarty->display('libs/view/setting.ctp');
				$smarty->display('libs/view/tail.ctp');
			}
		}
		function addKindle()
		{
			if(!isset($_GET['kindle']))
				header('location:index.php');
			$db = new db();
			$kindle = $_GET['kindle'];
			$db->addKindle($_SESSION['email'], $kindle);
			array_push($_SESSION['kindle'], $kindle);
			echo 'success';
		}
	}

 ?>