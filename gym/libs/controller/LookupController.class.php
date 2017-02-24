<?php 
	class LookupController
	{
		private $get;
		private $smarty;
		function setSmarty($s)
		{
			$this->smarty = $s;
		}
		function handle($g,$s)
		{
			$this->get = $g;
			$this->smarty = $s;
			if($_SESSION['type'] != 'customer'){
				header('location:index.php');
				exit();
			}
			if($_SESSION['type'] == 'customer' && !isset($this->get['type'])){//没有带type参数
				require_once 'libs/model/db.class.php';
				$db = new db();
				$this->smarty->assign('coach',$db->getBCoachByEmail($_SESSION['email']));
				$this->smarty->assign('course',$db->getBCourseByEmail($_SESSION['email']));
				$this->smarty->display('libs/view/header.ctp');
				$this->smarty->display('libs/view/lookup.ctp');
				$this->smarty->display('libs/view/tail.ctp');
			}
			else if(isset($this->get['type'])){//
				require_once 'libs/model/db.class.php';
				$type = $this->get['type'];
				$date = $this->get['date'];
				$curTime = time();
				$transferD = array('一周内'=>$curTime-604800,'两周内'=>$curTime-1209600,'一个月内'=>$curTime-2592000,'两个月内'=>$curTime-5184000,'一年内'=>$curTime-31536000,'两年内'=>$curTime-63072000,'所有'=>0,'查询日期'=>0);
				$transferT = array('查询种类'=>'','两者'=>'','充值记录'=>'充值','消费记录'=>'消费');
				$db = new db();
				$tmp = $db->getRecord($_SESSION['email'],$transferT[$type],$transferD[$date]);
			//	echo Date('Y年m月d日',$tmp[0]['time']);
				$this->smarty->assign('record',$tmp);
				$this->smarty->display('libs/view/lookupResult.ctp');
			}
		}
	}
 ?>