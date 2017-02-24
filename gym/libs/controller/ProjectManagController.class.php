<?php 
	class ProjectManagController
	{
		private $smarty;
		private $post;
		private $get;
		function display($type, $key)
		{
			$db = new db();
			if($type == NULL)
				$tmp = $db->getCourse();
			else if($type == 'ID')
				$tmp = $db->getCourseById($key);
			else
				$tmp = $db->getCourseByName($key);
				
			$n = 0;
			$transfer = array('Mon'=>'星期一','Tue'=>'星期二','Wed'=>'星期三','Thu'=>'星期四','Fri'=>'星期五','Sat'=>'星期六','Sun'=>'星期日');
			$transfer2 = array('Mon'=>'1','Tue'=>'2','Wed'=>'3','Thu'=>'4','Fri'=>'5','Sat'=>'6','Sun'=>'7');
			
			foreach ($tmp as $course) {

				$coachname = $db->query("select name from user where email = '".$course['coachemail']."'");
				$tmp[$n]['coach'] =  $coachname[0]['name'];
				$tmp[$n]['dispCoursetime'] = $transfer[Date('D',$course['starttime'])];
				$tmp[$n]['coursetime'] = $transfer2[Date('D',$course['starttime'])];
				$tmp[$n]['dispStarttime'] = Date('Y年m月d日',$tmp[$n]['starttime']);
				$tmp[$n]['lastcoursedate'] = Date('m/d/Y',$tmp[$n]['starttime'] + ($tmp[$n]['amount'] - 1) * 7 * 24 * 3600);
				$tmp[$n]['nextcoursedate'] = Date('m/d/Y',$tmp[$n]['starttime'] + (floor((time() - $tmp[$n]['starttime'])/(7 * 24 * 3600)) + 1) * 7 * 24 * 3600);
				
				$n++;
			}
			$this->smarty->display('libs/view/header.ctp');
			$this->smarty->display('libs/view/projmanagheader.ctp');
			for($i = 0; $i < count($tmp); $i++){
				$this->smarty->assign('course', $tmp[$i]);
				if($type != 'name')
					$student = $db->getCourseUserById($tmp[$i]['id']);
				else
					$student = $db->getCourseUserByName($tmp[$i]['name']);
				$this->smarty->assign('student', $student);
				$this->smarty->display('libs/view/projmanag.ctp');
				//$this->smarty->assign()
			}
			//$this->smarty->display('libs/view/projmanag.ctp');
			$this->smarty->display('libs/view/projmanagtail.ctp');
			$this->smarty->display('libs/view/tail.ctp');
		}

		function deleteCourse($pwd, $id)
		{
			$db = new db();
			$email = $_SESSION['email'];
			$query = "select pwd,type,name from user where email = '$email'";
			$result = $db->query($query);
			if($result[0][0] != $pwd){
				return false;
			}
			else{
				
				$course = $db->getCourseById($id);
				$name = $course[0]['name'];
				$price = $course[0]['price'];
				$student = $db->getCourseUserById($id);
				foreach ($student as $stu) {
					$db->setBalanceByEmail($stu['email'], $price + $stu['balance']);
				}		
				$db->addNotify("取消课程$id $name 公告", 
					"课程$id $name 因故取消，全部款项已退还给报名学员，请注意查收。若给你带来不便，深表歉意！",
					time(), "customer");
				$db->deleteCourseById($id);
				return true;
			}
		}


		function addCourse($post)
		{
			$db = new db();
			$datetime = $post['datetime'];
			$year = substr($datetime, 0, 4);
			$month = substr($datetime, strpos($datetime, "年") + 3, 2);
			$day = substr($datetime, strpos($datetime, "月") + 3, 2);
			$time = substr($datetime, strpos($datetime, "周") + 23, strpos($datetime, "点") - 1 - strpos($datetime, "周") - 23);
			$period = substr($datetime, strpos($datetime, "周") + 16, 6);
			if($period == '下午' && $time != 12 || $period == '晚上')
				$time += 12;
			$starttime = strtotime($year.'-'.$month.'-'.$day.' '.$time.':00:00');
			$weekDay = Date('D', $starttime);

			$courseOfCoach = $db->getCoachCourse($post['coach'], NULL, NULL);
			$lastcoursedate = $starttime + ($post['amount'] - 1)* 7 * 24 * 3600 + 3600;
			/*判断是否有团体课程冲突*/
			foreach ($courseOfCoach as $course) {
				$courselasttime = $course['starttime']  + ($course['amount'] - 1)* 7 * 24 * 3600 + 3600;
				if(
					($starttime <= $course['starttime'] && $course['starttime'] <= $lastcoursedate 
						|| $course['starttime'] <= $starttime && $starttime <= $courselasttime) 
					&& ($weekDay == Date('D', $course['starttime']))
					&& (abs($course['time'] - $time) < 1)){
					
					return false;
				}
				
			}


			/*判断是否有私教课程冲突*/
			$courseOfCoach = $db->getCoachbookByEmail($post['coach']);
			foreach ($courseOfCoach as $course) {
				if($starttime <= $course['date'] && $course['date'] <= $lastcoursedate && $weekDay == Date('D', $course['date']) && abs($course['time'] - $time) < 1){
					return false;
				}
			}

			$db->addCourse($starttime, $post['name'], $post['amount'], $post['site'], $post['price'], $post['capacity'], $post['description'], $post['coach'], $time);
			return true;
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

			if(isset($this->post['searchtype'])){
				$this->display($this->post['searchtype'], $this->post['searchkey']);
			}
			else if(isset($this->get['add'])){
				if($g['add'] == 'confirm'){
					$answer = $this->addCourse($p);
					if($answer == true){
						echo $answer;
						$this->smarty->assign('type', "success");
						$this->smarty->assign('message', "添加课程".$p['name']."成功！");						
					}
					else{
						$this->smarty->assign('type', "danger");
						$this->smarty->assign('message', "与教练其他课程发生冲突，添加课程".$p['name']."失败。");	
					}
					$this->smarty->display('libs/view/operationResult.ctp');			
					$this->display(NULL, NULL);
				}
				else{
					$db = new db();
					$coach = $db->getCoach();
					$this->smarty->assign('coach', $coach);
					$this->smarty->display('libs/view/header.ctp');
					$this->smarty->display('libs/view/addcourse.ctp');
					$this->smarty->display('libs/view/tail.ctp');
				}
			}
			else{
				if(isset($this->get['operation'])){
					if($g['operation'] == "success"){
						$this->smarty->assign('type', "success");
						$this->smarty->assign('message', "修改成功！");
						$this->smarty->display('libs/view/operationResult.ctp');
					}
				}
				
				if(isset($this->post['pwd']) && isset($this->post['course'])){
					if($this->deleteCourse($this->post['pwd'], $this->post['course'])){
						$this->smarty->assign('type', "success");
						$this->smarty->assign('message', "删除成功！");
						$this->smarty->display('libs/view/operationResult.ctp');
					}
					else{
						$this->smarty->assign('type', "danger");
						$this->smarty->assign('message', "密码错误，删除失败。");
						$this->smarty->display('libs/view/operationResult.ctp');
					}
				}

				if(isset($this->post['olddatetime'])){
					$db = new db();
					$db->addNotify($this->post['course']."课程调整公告", 
							$this->post['course']."因故".$this->post['olddatetime']."，".$this->post['newdatetime']." ，请报名的学员注意调整时间。若给你带来不便，深表歉意！",
							time(), "customer");
					$this->smarty->assign('type', "success");
					$this->smarty->assign('message', "调整课程成功！");
					$this->smarty->display('libs/view/operationResult.ctp');
				
				}				
				$this->display(NULL, NULL);
			}
			
		}
	}
 ?>
