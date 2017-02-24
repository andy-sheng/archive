<?php 
	require_once('libs/smarty/Smarty.class.php');
	require_once('libs/model/db.class.php');
	class LoginController
	{
		function handle()
		{

			if(!isset($_POST['email'])){
				/*
				if(isset($_COOKIE['email'])){
					$email = $_COOKIE['email'];
					$pwd = $_COOKIE['pwd'];
					$remember = 'true';
				}
				else{
					$email = '';
					$pwd = '';
					$remember = '';
				}

				$smarty = new Smarty();
				$smarty->assign('email', $email);
				$smarty->assign('pwd', $pwd);
				$smarty->assign('remember', $remember);
				$smarty->display('libs/view/login.ctp');*/
				exit();
			}


			$email = $_POST['email'];
			$pwd = $_POST['pwd'];
			if(isset($_POST['remember']))
				$remember = 'checked';
			else
				$remember = '';
			$db = new db();
			if($db->checkPwd($email,$pwd)){
				$_SESSION['email'] = $email;
				$kindle = $db->getKindle($email);
				if($kindle)
					$_SESSION['kindle'] = $kindle;
				if(isset($_POST['remember'])){
					setcookie('email',$email,3600+time());
					setcookie('pwd',$pwd,3600+time());
				}
				else{
					setcookie('email','',time()-3600);
					setcookie('pwd','',time()-3600);
				}	
				echo 'success';
			}
			else{
				echo 'error';
			}
			



		}
	}
 ?>