<?php 
	session_start();
	if(!isset($_SESSION['type'])){
		header('location:login.php');
		exit();
	}
	require_once 'libs/smarty/Smarty.class.php';
	switch ($_SESSION['type']) {
		case 'customer':
			$functions = "<ul class='dropdown-menu'>
                <li><a href='?action=coursebook'>团体课程预约</a></li>
                <li><a href='?action=coachbook'>私教预约</a></li>
                <li><a href='?action=infomodify'>个人信息修改</a></li>
                <li><a href='?action=lookup'>课程、消费查询</a></li>
                <li class='divider'></li>
                <li><a href='?action=ratecourse'>评价课程</a></li>
                <li><a href='?action=ratecoach'>评价教练</a></li>
              </ul>";
			break;
		case 'admin':
			$functions = "<ul class='dropdown-menu'>
                <li><a href='?action=projmanag'>项目管理</a></li>
                <li><a href='?action=instrumentmanag'>器材管理</a></li>
                <li><a href='?action=customermanag'>会员管理</a></li>
                <li><a href='?action=coachmanag'>教练管理</a></li>
                <li><a href='?action=sysmanag'>系统管理</a></li>
                <li class='divider'></li>
                <li><a href='?action=infomodify'>个人信息修改</a></li>
                <li><a href='?action=register'>用户注册</a></li>
              </ul>";
			break;
		case 'coach':
			$functions = "<ul class='dropdown-menu'>
                <li><a href='?action=coursemanag'>课程管理</a></li>
                <li><a href='?action=infomanag'>个人信息管理</a></li>
                <li><a href='?action=booklookup'>预约查询</a></li>
                <li><a href='?action=infomodify'>个人信息修改</a></li>
              </ul>";
			break;
		default:
			# code...
			break;
	}
	require_once 'libs/model/db.class.php';
	$db = new db();
	$tmp = $db->getNotify($_SESSION['type']);
	$smarty = new Smarty();
	$smarty->assign('notification',$tmp);//通知
	$smarty->assign('name',$_SESSION['name']);
	$smarty->assign('dropmenu',$functions);
	if(!isset($_GET['action'])){//主界面
		$smarty->display('libs/view/header.ctp');
		//$smarty->display('libs/view/picroll.ctp');
		switch ($_SESSION['type']) {
			case 'customer':
				$smarty->assign('coach',$db->getBCoachByEmail($_SESSION['email']));
				$smarty->assign('course',$db->getBCourseByEmail($_SESSION['email']));
				$smarty->assign('info', $db->getInfoByEmail($_SESSION['email']));
				$smarty->display('libs/view/customerindex.ctp');
				break;
			case 'coach':
				$smarty->assign('coach',$db->getCoachbookByCoachEmail($_SESSION['email']));
				$smarty->assign('course',$db->getCoachCourse($_SESSION['email'], NULL, NULL));
				$smarty->assign('info', $db->getInfoByEmail($_SESSION['email']));
				$smarty->display('libs/view/coachindex.ctp');
				break;
			case 'admin':
				$smarty->assign('customer', $db->query("select * from user where type = 'customer'"));
				$smarty->assign('coach', $db->query("select * from user where type = 'coach'"));
				$smarty->assign('course',$db->getCourse());
				$smarty->assign('info', $db->getInfoByEmail($_SESSION['email']));
				$smarty->display('libs/view/adminindex.ctp');
				break;
		}
		$smarty->display('libs/view/tail.ctp');
		exit();
	}
	switch ($_GET['action']) {
		case 'coursebook':
			require_once 'libs/controller/CourseBController.class.php';
			$courseBController = new CourseBController();
			$courseBController->handle($_GET,$smarty);
			break;

		case 'coachbook':
			require_once 'libs/controller/CoachBController.class.php';
			$coachBController = new CoachBController();
			$coachBController->handle($_GET,$smarty);
			break;

		case 'infomodify':
			require_once 'libs/controller/InfoMController.class.php';
			$infoMController = new InfoMController();
			$infoMController->handle($_POST,$smarty);
			break;

		case 'lookup':
			require_once 'libs/controller/LookupController.class.php';
			$LookupController = new LookupController();
			$LookupController->handle($_GET,$smarty);
			break;

		case 'ratecourse':
			require_once 'libs/controller/RateCourseController.class.php';
			$rateController = new RateCourseController();
			$rateController->setSmarty($smarty);
			if(!isset($_POST['course'])){

				$rateController->display(NULL, NULL);
			}
			else {
				$rateController->handle($_POST, $smarty);
			}
			break;

		case 'ratecoach':
			require_once 'libs/controller/RateCoachController.class.php';
			$rateController = new RateCoachController();
			$rateController->setSmarty($smarty);
			if(!isset($_POST['coach'])){

				$rateController->display(NULL, NULL);
			}
			else {
				$rateController->handle($_POST, $smarty);
			}
			break;

		case 'register':
			require_once 'libs/controller/RegController.class.php';
			$regController = new RegController();
			$regController->handle($_GET,$_POST,$smarty);
			break;

		case 'sysmanag':
			require_once 'libs/controller/SysManagController.class.php';
			$sysManagController = new SysManagController();
			$sysManagController->handle($_GET,$_POST,$smarty);
			break;

		case 'booklookup':
			require_once 'libs/controller/CoachBookLookupController.class.php';
			$coachBookLookupController = new CoachBookLookupController();
			$coachBookLookupController->handle($_GET,$smarty);
			break;


		case 'projmanag':
			require_once 'libs/controller/ProjectManagController.class.php';
			$projectManagController = new ProjectManagController();
			$projectManagController->handle($_GET, $_POST, $smarty);
			break;

		case 'courseinfomanag':
			require_once 'libs/controller/CourseInfoMController.class.php';
			$courseInfoMController = new CourseInfoMController();
			$courseInfoMController->handle($_GET, $_POST, $smarty);
			break;

		case 'courseinfomanagreq':
			require_once 'libs/controller/CourseInfoMReqController.class.php';
			$courseInfoMReqController = new CourseInfoMReqController();
			$courseInfoMReqController->handle($_GET, $_POST, $smarty);
			break;

		case 'customermanag':
			require_once 'libs/controller/CustomerMController.class.php';
			$customermanag = new CustomerMController();
			$customermanag->handle($_GET,$smarty);
			break;

		case 'instrumentmanag':
			require_once 'libs/controller/InstrumentMController.class.php';
			$InstrumentM = new InstrumentMController();
			$InstrumentM->handle($_GET, $smarty);
			break;

		case 'infomanag':
			require_once 'libs/controller/InfoManagController.class.php';
			$infoManag = new InfoManagController();
			$infoManag->handle($_GET,$smarty);
			break;


		case 'coursemanag':
			require_once 'libs/controller/CourseMController.class.php';
			$courseMController = new CourseMController();
			$courseMController->handle($_GET, $_POST, $smarty);
			break;


		case 'coachmanag':
			require_once 'libs/controller/CoachManagController.class.php';
			$coachManag = new CoachManagController();
			$coachManag->handle($_GET, $_POST, $smarty);

		default:
			# code...
			break;
	}
 ?>
