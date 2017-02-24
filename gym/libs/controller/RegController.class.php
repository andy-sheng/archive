<?php 
	class RegController
	{
		private $smarty;
		private $get;
		private $post;
		function display()
		{
			$this->smarty->display('libs/view/header.ctp');
			$this->smarty->display('libs/view/register.ctp');
			$this->smarty->display('libs/view/tail.ctp');
		}
		function handle($g,$p,$s)
		{
			$this->smarty = $s;
			$this->get = $g;
			$this->post = $p;
			if($_SESSION['type'] != 'admin'){//非管理员跳转回首页
				header('location:index.php');
				exit();
			}
			if(!isset($this->post['email']) && !isset($this->get['ajax'])){//不带表单数据
				$this->display();
			}
			else if(isset($this->get['ajax'])){//不带表单数据
				require_once 'libs/model/db.class.php';
				$db = new db();
				echo $db->checkEmail($this->get['ajax']);
			}
			else{
				require_once 'libs/model/db.class.php';
				$db = new db();
				$transfer = array('会员'=>'customer',
								  '管理员'=>'admin',
								  '教练'=>'coach');
				$this->post['type'] = $transfer[$this->post['type']];
				$db->addUser($this->post);
				$this->smarty->assign('type','success');
				$this->smarty->assign('message','注册成功');
				$this->smarty->display('libs/view/header.ctp');
				$this->smarty->display('libs/view/operationResult.ctp');
				$this->smarty->display('libs/view/register.ctp');
				$this->smarty->display('libs/view/tail.ctp');
			}
		}
	}
 ?>