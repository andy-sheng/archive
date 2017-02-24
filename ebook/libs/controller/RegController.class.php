<?php 
require_once('libs/model/db.class.php');
require_once('libs/smarty/Smarty.class.php');
class RegController
{
	
	function __construct()
	{
	}
	function handle()
	{
		$db = new db();
		if(isset($_GET['action2']) == 'checkemail'){
			if(isset($_GET['email']))
				echo $db->checkEmail($_GET['email']);
			else
				header('location:index.php');
			exit();
		}






		if(isset($_SESSION['email']))
			header('location:index.php');
		if(!isset($_POST['email']) || !isset($_POST['pwd'])){
			$smarty = new Smarty();
			$smarty->assign('logined',isLogined());
			$smarty->display('libs/view/head.ctp');
			$smarty->display('libs/view/reg.ctp');
			$smarty->display('libs/view/tail.ctp');
			exit();
		}
		$email = $_POST['email'];
		$pwd = $_POST['pwd'];

		
		if($db->addUsr($email, $pwd)){
			$_SESSION['email']  = $email;
			$_SESSION['pwd'] = $pwdl;
		}
		header('location:index.php');
		

	}
}
 ?>