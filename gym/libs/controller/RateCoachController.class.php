<?php 
	class RateCoachController
	{
		private $smarty;
		private $post;
		function setSmarty($s)
		{
			$this->smarty = $s;
		}
		/*function ratecompleted()
		{
			if($_SESSION['type'] != 'customer'){
				header('location:index.php');
				exit();
			}
			require_once 'libs/model/db.class.php';
			$db = new db();
			$tmp = $db->getCoach();
			$this->smarty->assign('coach', $tmp);
			$this->smarty->display('libs/view/header.ctp');
			$this->smarty->display('libs/view/rateCompleted.ctp');
			$this->smarty->display('libs/view/rateCoach.ctp');	
			$this->smarty->display('libs/view/tail.ctp');
		}

		function ratefailed()
		{
			if($_SESSION['type'] != 'customer'){
				header('location:index.php');
				exit();
			}
			require_once 'libs/model/db.class.php';
			$db = new db();
			$tmp = $db->getCoach();
			$this->smarty->assign('coach', $tmp);
			$this->smarty->display('libs/view/header.ctp');
			$this->smarty->display('libs/view/rateFailed.ctp');
			$this->smarty->display('libs/view/rateCoach.ctp');	
			$this->smarty->display('libs/view/tail.ctp');
		}*/

		function display($t, $m)
		{
			if($_SESSION['type'] != 'customer'){
				header('location:index.php');
				exit();
			}
			require_once 'libs/model/db.class.php';
			$db = new db();
			$tmp = $db->getCoach();
			$this->smarty->assign('coach', $tmp);
			$this->smarty->display('libs/view/header.ctp');
			if(isset($t)){
				$this->smarty->assign('type',$t);
				$this->smarty->assign('message',$m);
				$this->smarty->display('libs/view/operationResult.ctp');
			}
			$this->smarty->display('libs/view/rateCoach.ctp');	
			$this->smarty->display('libs/view/tail.ctp');
		}
		function handle($p, $s)
		{
			if($_SESSION['type'] != 'customer'){
				header('location:index.php');
				exit();
			}
			$this->smarty = $s;
			$this->post = $p;
			if($this->post['coach'] != '' && isset($this->post['responsibility']) && isset($this->post['planning']) 
				&& isset($this->post['listener']) && isset($this->post['fame']) && isset($this->post['experience']) && isset($this->post['progress'])){
				$this->display('success', '评价成功！');
				

				require_once 'libs/model/db.class.php';
				$db = new db();
				$db->rateCoach($this->post['coach'], $this->post['responsibility'], $this->post['planning'], 
					$this->post['listener'], $this->post['fame'], $this->post['experience'], $this->post['progress'], 
					$this->post['comment']);
			}
			else{
				$this->display('danger', '评价失败，请填写完整。');
			}
			
			//if(isset)
		}
	}
 ?>
