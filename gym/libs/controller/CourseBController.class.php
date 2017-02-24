<?php 
	class CourseBController
	{
		private $smarty;
		private $get;
		function display($t,$m)
		{
			require_once 'libs/model/db.class.php';
			$db = new db();
			$tmp = $db->getCourse();//获取课程信息
			//print_r($tmp);exit();
			$userInfo = $db->getUserInfoByEmail($_SESSION['email']);
			$n = 0;
			$transfer = array('Mon'=>'星期一','Tue'=>'星期二','Wed'=>'星期三','Thu'=>'星期四','Fri'=>'星期五','Sat'=>'星期六','Sun'=>'星期日');
			foreach ($tmp as $course) {
				$tmp[$n]['coursetime'] = $transfer[Date('D',$course['starttime'])];
				$tmp[$n]['starttime'] = Date('Y年m月d日',$tmp[$n]['starttime']);
				$n++;
			}
			$selected = $db->getCourseByEmail($_SESSION['email']);
			$n = 0;
			foreach ($tmp as $line) {
				$flag = 'show';
				if($line['price'] > $userInfo['balance'])
					$flag = 'unaffordable';
				foreach ($selected as $s) {
					if($line['id'] == $s['id']){
						$flag = 'booked';
						break;
					}
				}
				$tmp[$n]['flag']= $flag;
				$n++;
			}

			$this->smarty->assign('rates',$db->getCourseRate());//print_r($db->getCourseRate());exit();
			$this->smarty->assign('payPwd',$db->getPayPwdByEmail($_SESSION['email']));
			$this->smarty->assign('course',$tmp);
			$this->smarty->display('libs/view/header.ctp');
			if(isset($t)){
				$this->smarty->assign('type',$t);
				$this->smarty->assign('message',$m);
				$this->smarty->display('libs/view/operationResult.ctp');
			}
			$this->smarty->display('libs/view/courseB.ctp');	
			$this->smarty->display('libs/view/tail.ctp');
		}
		function isConflict($id)
		{
			$db = new db();
			$courseInfo = $db->getCourseInfoById($this->get['id']);
			$usercourses = $db->getUsercourseByEmail($_SESSION['email']);
			foreach ($usercourses as $course) {
				if(($courseInfo['time'] == $course['time']) && (Date('D',$courseInfo['starttime']) == Date('D',$course['starttime']))){//同一天同一节
					$start1 = Date('Y.m.d',$courseInfo['time']);
					$end1 = Date('Y.m.d',$courseInfo['time'] + ($courseInfo['amount']-1)*3600*24*7);
					$start2 = Date('Y.m.d',$course['time']);
					$end2 = Date('Y.m.d',$course['time'] + ($course['amount']-1)*3600*24*7);
					if(($start1>=$start2 && $start1<=$end2) || ($end1 >= $start2 && $end1 <= $end2))//判断时间是否有重叠
						return $course;
				}
			}
			$coachs = $db->getCoachbookByEmail($_SESSION['email']);
			foreach ($coachs as $coach) {
				if(Date('D',$courseInfo['starttime']) == Date('D',$coach['date']) && $courseInfo['time'] == $coach['time']){//通一个星期几
					$start = Date('Y.m.d',$courseInfo['starttime']);
					$end = Date('Y.m.d',$courseInfo['starttime'] + ($courseInfo['amount']-1)*3600*24*7);
					$coachTime = Date('Y.m.d',$coach['date']);
					if( $start <= $coachTime && $end >= $coachTime)
						return $coach;
				}
			}
			return null;
		}
		function handle($g,$s)
		{
			if($_SESSION['type'] != 'customer'){
				header('location:index.php');
				exit();
			}
			require_once 'libs/model/db.class.php';
			$db = new db();
			$this->get = $g;
			$this->smarty = $s;
			if($_SESSION['type'] == 'customer' && !isset($this->get['id'])){//没有带id参数
				$this->display('','');
			}
			else if(isset($this->get['id'])){//
				//预定课程逻辑
				$reslut = $this->isConflict($this->get['id']);
				if(isset($reslut['starttime']))
					$this->display('warning',"课程预约失败，与ID号为".$reslut['id']."的课程".$reslut['name']."冲突!");
				else{
					$db->courseBook($_SESSION['email'],$this->get['id']);
					if(isset($reslut['date']))
						$this->display('warning','课程预约成功，但是和'.Date('Y年m月d日',$reslut['date'])."的教练预约冲突，请自行解决！");
					else
						$this->display('success','课程预约成功，请按时上课');
					$tmp = $db->getCourseInfoById($this->get['id']);
					$db->setRecord($_SESSION['email'], '消费', $tmp['price']);
				}
			}
		}
	}
 ?>