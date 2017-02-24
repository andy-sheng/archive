<?php 
	class CoachManagController
	{
		private $smarty;
		private $get;
		private $post;
		function handle($g, $p, $s)
		{
			$this->get = $g;
			$this->post = $p;
			$this->smarty = $s;




			//不是管理员重定向
			if($_SESSION['type'] != 'admin'){
				header('location:index.php');
				exit();
			}

			//处理请求
			require_once 'libs/model/db.class.php';
			$db = new db();
			if(isset($this->get['id'])){
				$db->opCourse($_SESSION['name'], $this->get['id'], $this->get['op'], $this->get['status']);
			}
			else if(isset($this->get['op'])){
				$db->opDayoff($this->get['email'], $this->get['time'], $this->get['op']);
			}
			else if(isset($this->get['email']) && !isset($this->get['write'])){
				$info = $db->getInfoByEmail($this->get['email']);
				echo json_encode($info);
			}
			else if (isset($this->get['write'])) {
				$db->setCoachInfo($this->get['email'], $this->post);
			}
			else{
				$coach = $db->getCoach();
				$tmp = array();
				foreach ($coach as $line) {
					$rates['rate'] = $db->getRateByEmail($line['email']);
					$rates['name'] = $line['name'];
					$rates['email'] = $line['email'];
					array_push($tmp, $rates);
				}
				$this->smarty->assign('status', $db->getCoachStatus());
				$this->smarty->assign('dayoff', $db->getDayoff());
				$this->smarty->assign('course', $db->getCourseM());
				$this->smarty->assign('data',$tmp);
				$this->smarty->display('libs/view/header.ctp');
				$this->smarty->display('libs/view/coachManag.ctp');
				$this->smarty->display('libs/view/tail.ctp');
			}

			
		}
	}
	
 ?>