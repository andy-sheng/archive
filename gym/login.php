<?php 
	if(isset($_GET['action']) && $_GET['action']=='logout' && isset($_SESSION['type']))
		session_destroy();
	require_once 'libs/model/db.class.php';
	$db = new db();
	if(isset($_POST['email']) && isset($_POST['pwd'])){
		$email = $_POST['email'];
		$pwd = $_POST['pwd'];
		$query = "select pwd,type,name from user where email = '".$email."'";
		$result = $db->query($query);
		if($result[0][0] != $pwd){
			header('location:login.php');
			exit();
		}
		if(isset($_POST['remember'])){
			setcookie('email',$email,3600+time());
			setcookie('pwd',$pwd,3600+time());
		}
		else{
			setcookie('email','',time()-3600);
			setcookie('pwd','',time()-3600);
		}
		switch ($result[0][1]) {
			case 'customer':
				session_start();
				$_SESSION['type'] = 'customer';
				$_SESSION['email'] = $email;
				$_SESSION['name'] = $result[0][2];
				echo $result[0][2];
				header('location:index.php');
				break;
			case 'admin':
				session_start();
				$_SESSION['type'] = 'admin';
				$_SESSION['email'] = $email;
				$_SESSION['name'] = $result[0][2];
				header('location:index.php');
				break;
			case 'coach':
				session_start();
				$_SESSION['type'] = 'coach';
				$_SESSION['email'] = $email;
				$_SESSION['name'] = $result[0][2];
				header('location:index.php');
				break;
			default:
				header('location:index.php');
				break;
		}
		
	}
	else{
		require_once 'libs/smarty/Smarty.class.php';
		$smarty = new Smarty();
	//	$smarty->assign('email','');
	//	$smarty->assign('pwd','');
		if(isset($_COOKIE['email'])){
			$smarty->assign('email',$_COOKIE['email']);
			$smarty->assign('pwd',$_COOKIE['pwd']);
		}
		$smarty->display('libs/view/login.ctp');
	}

 ?>