<?php 
	class CoachBController
	{
		private $get;
		private $smarty;
		function setSmarty($s)
		{
			$this->smarty = $s;
		}
		function display()
		{
			$db = new db();
			$tmp = $db->getCoach();
			$curTime = time();
			$dateTmp = array($curTime+86400,$curTime+86400*2,$curTime+86400*3,$curTime+86400*4,$curTime+86400*5,$curTime+86400*6,$curTime+86400*7,$curTime+86400*8);
			$info = $db->getInfoByEmail($_SESSION['email']);
			$this->smarty->assign('balance',$info['balance']);
			$this->smarty->assign('date',$dateTmp);
			$this->smarty->assign('coach',$tmp);
			$this->smarty->assign('payPwd',$db->getPayPwdByEmail($_SESSION['email']));
			$this->smarty->display('libs/view/header.ctp');
			$this->smarty->display('libs/view/coachB.ctp');	
			$this->smarty->display('libs/view/tail.ctp');
		}
		function sendCoachTime($email,$date)
		{
			$db = new db();
			$tmp = $db->getCoachTime($email,$date);
			echo json_encode($tmp);
		}
		function handle($g,$s)
		{
			$this->get = $g;
			$this->smarty = $s;
			if($_SESSION['type'] != 'customer'){
				header('location:index.php');
				exit();
			}
			require_once 'libs/model/db.class.php';
			$db = new db();
			if($_SESSION['type'] == 'customer' && !isset($this->get['email'])){//没有带email参数
				$this->display();
			}
			else if(isset($this->get['ajax'])){
				$this->sendCoachTime($this->get['email'],$this->get['date']);
			}
			else if(isset($this->get['email'])){//
				//预定教练逻辑
				$addition = $this->get['addition'];
				$email = $this->get['email'];
				$date = $this->get['date'];
				$time = $this->get['time'];
				$db->coachBook($_SESSION['email'], $email,$addition,$date,$time);
				$db->setRecord($_SESSION['email'], '消费', '100');
				$db->subBalance($_SESSION['email'], '100');
				header('location:index.php?action=coachbook');
			}
		}
	}
 ?>