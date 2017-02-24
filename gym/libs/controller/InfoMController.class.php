<?php 
	class InfoMController
	{
		private $smarty;
		private $post;
		function display()
		{
			require_once 'libs/model/db.class.php';
			$db = new db();
			$this->smarty->assign('info',$db->getInfoByEmail($_SESSION['email']));
			$this->smarty->assign('type',$_SESSION['type']);
			$this->smarty->display('libs/view/header.ctp');
			$this->smarty->display('libs/view/infoM.ctp');	
			$this->smarty->display('libs/view/tail.ctp');
		}
		function writeInfo()
		{
			require_once 'libs/model/db.class.php';
			$db = new db();
			if(!isset($this->post['paypwd']))
				$this->post['paypwd']=' ';
			$db->setInfoByEmail($_SESSION['email'],$this->post);
		}
		function handle($p,$s)
		{
			$this->smarty = $s;
			$this->post = $p;
			if(isset($this->post['nickname'])){//带了表单数据
				$this->writeInfo();
				header('location:index.php?action=infomodify');
				exit();
			}
			else{//不带表单数据
				$this->display();
			}
		}
	}
 ?>