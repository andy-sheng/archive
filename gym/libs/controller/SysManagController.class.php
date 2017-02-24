<?php 
	class SysManagController
	{
		private $smarty;
		private $post;
		private $get;
		function display()
		{
			$this->smarty->display('libs/view/header.ctp');
			$this->smarty->display('libs/view/sysmanag.ctp');
			$this->smarty->display('libs/view/tail.ctp');
		}
		function handle($g,$p,$s)
		{
			$this->smarty = $s;
			$this->post = $p;
			$this->get = $g;
			if($_SESSION['type'] != 'admin'){//非管理员跳转回首页
				header('location:index.php');
				exit();
			}
			if(!isset($this->post['content'])){//不带post请求
				$this->display();
			}
			else{
				$head =  $this->post['head'];
				$type = $this->post['type'];//通知的对象在这里     分为 会员 管理员 教练
				$content = $this->post['content'];//通知内容在这里
				/*
				 *下面需要将通知内容写进数据库 并通知成功
				 */
				$db = new db();
				$db->addNotify($head,$content,Time(),$type);
				$this->smarty->assign('message','提交成功');
				$this->smarty->display('libs/view/header.ctp');
				$this->smarty->display('libs/view/operationSuccess.ctp');
				$this->smarty->display('libs/view/sysmanag.ctp');
				$this->smarty->display('libs/view/tail.ctp');
			}
		}
	}
 ?>
