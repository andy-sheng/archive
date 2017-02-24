<?php 
	class InfoManagController
	{
		private $smarty;
		private $get;
		function handle($g, $s)
		{
			$this->smarty = $s;
			$this->get = $g;

			//不是教练重定向
			if($_SESSION['type'] != 'coach'){
				header('location:index.php');
				exit();
			}

			//处理请求
			require_once 'libs/model/db.class.php';
			$db = new db();
			if(isset($this->get['dayoff'])){
				$db->setDayoff($_SESSION['email'],$this->get['dayoff']);
			}
			else if(isset($this->get['startTime'])){
				$db->setCoachtime($_SESSION['email'],$this->get['startTime'],$this->get['endTime']);
			}
			else{
				$this->smarty->assign('rate',$db->getRateByEmail($_SESSION['email']));
				$this->smarty->display('libs/view/header.ctp');
				$this->smarty->display('libs/view/infoManag.ctp');
				$this->smarty->display('libs/view/tail.ctp');
			}
			
		}
	}
 ?>