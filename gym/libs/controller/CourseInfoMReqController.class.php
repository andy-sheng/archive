<?php 
	class CourseInfoMReqController
	{
		private $smarty;
		private $post;
		function display($id)
		{
			require_once 'libs/model/db.class.php';
			$db = new db();
			$tmp = $db->getCourseById($id);
			$this->smarty->assign('courseinfo', $tmp[0]);
			$this->smarty->assign('id', $id);
			$this->smarty->assign('type',$_SESSION['type']);
			$this->smarty->display('libs/view/header.ctp');
			$this->smarty->display('libs/view/courseInfoMReq.ctp');	
			$this->smarty->display('libs/view/tail.ctp');
		}
		function writeInfo($post)
		{
			require_once 'libs/model/db.class.php';
			$db = new db();
			

			/*$courseinfo['capacity'] = $post['capacity'];
			$courseinfo['price'] = $post['price'];
			$courseinfo['site'] = $post['site'];
			$courseinfo['description'] = $post['description'];*/
			$db->addSetCourseInfoByIdReq($post);
		}
		function handle($g, $p, $s)
		{
			$this->smarty = $s;
			$this->get = $g;
			$this->post = $p;
			if(isset($this->post['courseid'])){//带了表单数据
				$this->writeInfo($p);
				header('location:index.php?action=coursemanag&operation=success');
				exit();
			}
			else{//不带表单数据
				$this->display($g['id']);
			}
		}
	}
?>