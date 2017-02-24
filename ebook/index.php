<?php 
@ini_set('display_errors', '1');
	session_start();
	require_once('libs/sdk/JingdongStorageService.php');
	require_once('libs/smarty/Smarty.class.php');
	require_once('libs/model/db.class.php');
	require_once('libs/controller/function.php');
	require_once('config.php');
	if(!isset($_GET['action'])){
		$smarty = new Smarty();
		$smarty->assign('logined', isLogined());
		$smarty->display('libs/view/head.ctp');
		$smarty->display('libs/view/home.ctp');
		$smarty->display('libs/view/tail.ctp');
		exit();
	}
	switch ($_GET['action']) {
		case 'login':
			require_once('libs/controller/LoginController.class.php');
			$controller = new LoginController();
			$controller->handle();
			break;
		case 'logout':
			unset($_SESSION['email']);
			header('location:index.php');
			break;
		case 'upload':
			require_once('libs/controller/UploadController.class.php');
			$controller = new UploadController();
			$controller->handle();
			break;
		case 'setting':
			require_once('libs/controller/SettingController.class.php');
			$controller = new SettingController();
			$controller->handle();
			break;
		case 'reg':
			require_once('libs/controller/RegController.class.php');
			$controller = new RegController();
			$controller->handle();
			break;
		case 'search':
			require_once('libs/controller/SearchController.class.php');
			$controller = new SearchController();
			$controller->handle();
			break;
		case 'detail':
			require_once('libs/controller/DetailController.class.php');
			$controller = new DetailController();
			$controller->handle();
			break;
		case 'push':
      		session_write_close();
			require_once('libs/controller/PushController.class.php');
			$controller = new PushController();
			$controller->doPush();
			break;
		case 'download':
			require_once('libs/controller/DownloadController.class.php');
			$controller = new DownloadController($_GET['id']);
			$controller->handle();
			break;
		case 'test':
		print_r($_SESSION['kindle']);
		break;
		default:
			$smarty = new Smarty();
			$smarty->assign('logined', isLogined());
			$smarty->display('libs/view/head.ctp');
			$smarty->display('libs/view/home.ctp');
			$smarty->display('libs/view/tail.ctp');
			break;
	}
 ?>