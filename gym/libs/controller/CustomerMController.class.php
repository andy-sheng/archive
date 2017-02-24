<?php 

	class CustomerMController{
		private $get;
		private $smarty;
		function display()
		{
			$this->smarty->display('libs/view/header.ctp');
			$this->smarty->display('libs/view/customerM.ctp');
			$this->smarty->display('libs/view/tail.ctp');
		}
		function handle($g,$s)
		{
			$this->get = $g;
			$this->smarty = $s;
			if($_SESSION['type'] != 'admin'){
				header('location:index.php');
				exit();
			}
			if(isset($this->get['ajax'])){
				$db = new db();
				if(isset($this->get['ajax']) && isset($this->get['balance'])){
					$db->charge($this->get['ajax'], $this->get['balance']);
					$db->setRecord($this->get['ajax'], '充值', $this->get['balance']);
					echo 'success';
				}
				else if(isset($this->get['ajax']) && isset($this->get['lock'])){
					$db->lock($this->get['ajax']);
					echo 'success';
				}
				else if(isset($this->get['ajax']) && isset($this->get['unlock'])){
					$db->unlock($this->get['ajax']);
					echo 'success';
				}
				else if(isset($this->get['ajax']) && isset($this->get['changerank'])){
					$db->changeRank($this->get['ajax'], $this->get['changerank']);
					echo 'success';
				}
				else{
					$result = $db->getInfoByEmail($this->get['ajax']);
					if($result){
						$this->smarty->assign('customer', $result);
						$this->smarty->display('libs/view/customerInfo.ctp');
					}
					else{
						echo 'null';
					}
				}
			}
			else
				$this->display();
		}
	}
 ?>