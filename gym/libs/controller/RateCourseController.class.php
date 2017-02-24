<?php 
	class RateCourseController
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
			$tmp = $db->getCourse();
			$this->smarty->assign('course', $tmp);
			$this->smarty->display('libs/view/header.ctp');
			$this->smarty->display('libs/view/rateCompleted.ctp');
			$this->smarty->display('libs/view/rateCourse.ctp');	
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
			$tmp = $db->getCourse();
			$this->smarty->assign('course', $tmp);
			$this->smarty->display('libs/view/header.ctp');
			$this->smarty->display('libs/view/rateFailed.ctp');
			$this->smarty->display('libs/view/rateCourse.ctp');	
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
			$tmp = $db->getCourse();
			
			$this->smarty->assign('course', $tmp);
			$this->smarty->display('libs/view/header.ctp');
			if(isset($t)){
				$this->smarty->assign('type',$t);
				$this->smarty->assign('message',$m);
				$this->smarty->display('libs/view/operationResult.ctp');
			}
			$this->smarty->display('libs/view/rateCourse.ctp');
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
			if($this->post['course'] != '' && isset($this->post['content']) && isset($this->post['style']) 
				&& isset($this->post['interaction']) && isset($this->post['progress'])){
				$this->display('success', '评价成功！');
				

				require_once 'libs/model/db.class.php';
				$db = new db();
				$db->rateCourse($this->post['course'], $this->post['content'], $this->post['style']
					, $this->post['interaction'], $this->post['progress'], $this->post['comment']);
			}
			else{
				$this->display('danger', '评价失败，请填写完整。');
			}
			
			//if(isset)
		}
	}
 ?>

 
