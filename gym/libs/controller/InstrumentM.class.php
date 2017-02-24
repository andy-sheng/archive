<?php 
	class InstrumentM
	{
		private $smarty;
		private $get;
		function handle($g, $s)
		{
			$this->get = $g;
			$this->smarty = $s;

			//不是管理员重定向
			if($_SESSION['type'] != 'admin'){
				header('location:index.php');
				exit();
			}



			//处理请求
			require_once 'libs/model/db.class.php';
			$db = new db();
			if(isset($this->get['id']) && isset($this->get['status'])){//修改status
				$db->setStatusById($this->get['id'], $this->get['status']);
			}
			else if(isset($this->get['id']) && !isset($this->get['status'])){//删除
				$db->deleteInstrument($this->get['id']);
			}
			else if(isset($this->get['name'])){
				$db->addInstrument($this->get['name']);	
			}
			else{
				$this->smarty->assign('instrument',$db->getInstrument());
				$this->smarty->display('libs/view/header.ctp');
				$this->smarty->display('libs/view/instrumentM.ctp');
				$this->smarty->display('libs/view/tail.ctp');
			}
		}
	}
 ?>