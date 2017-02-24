<?php 
	class db
	{
		function connect()
		{
			$host = 'localhost';
			$port = '3306';
			$user = 'root';
			$pwd = '';
			$dbname = 'gym';
			$link = mysql_connect("{$host}:{$port}",$user,$pwd,true);
			if(!$link)
				echo 'failed to connect to the database';
			mysql_select_db($dbname,$link);
			mysql_query("SET NAMES 'utf8'");
		}
		function query($query)
		{
			$this->connect();
			$tmp = array();
			$n = 0;
			$result = mysql_query($query);
			while($row = mysql_fetch_array($result))
					$tmp[$n++] = $row;
			return $tmp;
		}




		function addDeleteCourseByIdReq($rid)
		{
			$query = "insert into course values($rid, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '删除', NULL, NULL)";
			mysql_query($query);
		}

		function addRescheduleCourseNotifyReq($rid, $title, $content)
		{
			$query = "insert into course values($rid, '$title', NULL, NULL, NULL, NULL, NULL, '$content', NULL, '调整', NULL, NULL)";
			mysql_query($query);
		}

		function deleteCourseById($id)
		{
			$this->connect();
			mysql_query("delete from usercourse where courseid = $id");
			$result = mysql_query("delete from course where id = $id and status = '发布'");
			return $result;
		}

		function getInfoByEmail($email)
		{
			$this->connect();
			$tmp = array();
			$result = mysql_query("select * from user where email='$email'");
			while($row = mysql_fetch_array($result)){
				return $row;
			}
		}
		function setInfoByEmail($email,$info)
		{
			$this->connect();
			$tmp = array();
			$query = "update user set name='".$info['name']."'
									,nickname='".$info['nickname']."'
									,pwd='".$info['pwd']."' 
									,paypwd='".$info['paypwd']."' 
									,sex='".$info['sex']."'	
									,telephone='".$info['telephone']."' 
									,address='".$info['address']."' 
					  where email='".$email."'";
			$result = mysql_query($query);	
			return $result;
		}
		function getCourseTimeById($id)
		{
			$this->connect();	
			$tmp = array();
			$n = 0;
			$result = mysql_query("select time 
									from coursetime
									where id='".$id."'");
			while($row = mysql_fetch_array($result))
				$tmp[$n++] = $row[0];
			return $tmp;
		}
		function getCourse()
		{
			$this->connect();
			$tmp = array();
			$result = mysql_query("select *
									from course
									where status = '发布'");
			$transfer = array('Mon'=>'星期一',
							  'Tue'=>'星期二',
							  'Wed'=>'星期三',
							  'Thu'=>'星期四',
							  'Fri'=>'星期五',
							  'Sat'=>'星期六',
							  'Sun'=>'星期日');

			while($row = mysql_fetch_array($result))
			{
				$row['coursetime'] = $transfer[Date('D',$row['starttime'])];
				array_push($tmp, $row);	
			}		
			return $tmp;
		}
		function getCourseById($id)
		{
			$this->connect();
			$tmp = array();
			$result = mysql_query("select *
									from course
									where id = '$id' and status = '发布'");
			while($row = mysql_fetch_array($result))
				array_push($tmp, $row);			
			return $tmp;
		}

		function getCourseByName($name)
		{
			$this->connect();
			$tmp = array();
			$result = mysql_query("select *
									from course
									where locate('$name', name) > 0 and status = '发布'");
			while($row = mysql_fetch_array($result))
				array_push($tmp, $row);			
			return $tmp;
		}
		

		function addCourse($starttime, $name, $amount, $site, $price, $capacity, $description, $coachemail, $time)
		{
			$this->connect();
			$query = 'select max(id) from course where id <= 10000';
			$result = mysql_query($query);
			while($row = mysql_fetch_array($result))
				$id = $row[0]+1;
			$query = "insert into course values('$id', '$name', $starttime, $amount, 60, '$site', $capacity, '$description', $price, '发布', '$coachemail', $time)";
			mysql_query($query);
		}

		function addCourseReq($starttime, $name, $amount, $site, $price, $capacity, $description, $coachemail, $time)
		{
			$this->connect();
			$query = 'select max(id) from course where id <= 10000';
			$result = mysql_query($query);
			while($row = mysql_fetch_array($result))
				$id = $row[0]+1;
			$query = "insert into course values('$id', '$name', $starttime, $amount, 60, '$site', $capacity, '$description', $price, '增加', '$coachemail', $time)";
			mysql_query($query);
		}

		function setCourseInfoById($id, $courseinfo)
		{
			$this->connect();
			$query = "update course set capacity = ".$courseinfo['capacity']." 
									,price = ".$courseinfo['price']."	
									,site = '".$courseinfo['site']."' 
									,description='".$courseinfo['description']."' 
					  where id = $id";
			$result = mysql_query($query);	
			return $result;
		}
		
		

		function addSetCourseInfoByIdReq($newinfo)
		{
			$this->connect();
			$old = $this->getCourseById($newinfo['courseid']);
			$oldinfo = $old[0];
			$rid = $newinfo['courseid'] + 10000;

			mysql_query("delete from course where id = $rid and status <> '发布'");
			
			$query = "insert into course values($rid, '".$oldinfo['name']."', ".$oldinfo['starttime']." , ".$oldinfo['amount']." , 60, '".$newinfo['site']."', ".$newinfo['capacity']." , '".$newinfo['description']."' , ".$newinfo['price']." , '修改', '".$oldinfo['coachemail']."', ".$oldinfo['time'].")";
			mysql_query($query);
		}

		function getCoachCourse($coachemail, $courseid, $coursename)
		{
			$this->connect();
			$tmp = array();
			if($courseid != NULL){
				$result = mysql_query("select *
									from course
									where coachemail = '$coachemail' and id = $courseid and status = '发布'");
			}
			else if($coursename != NULL){
				$result = mysql_query("select *
									from course
									where coachemail = '$coachemail' and locate('$coursename', name) and status = '发布'");
			}
			else{
				$result = mysql_query("select *
									from course
									where coachemail = '$coachemail' and status = '发布'");
			}
			$transfer = array('Mon'=>'星期一',
							  'Tue'=>'星期二',
							  'Wed'=>'星期三',
							  'Thu'=>'星期四',
							  'Fri'=>'星期五',
							  'Sat'=>'星期六',
							  'Sun'=>'星期日');

			while($row = mysql_fetch_array($result))
			{
				$row['coursetime'] = $transfer[Date('D',$row['starttime'])];
				array_push($tmp, $row);	
			}

			return $tmp;
		}


		function getCourseUserById($id)
		{
			$this->connect();	
			$tmp = array();
			$n = 0;
			$result = mysql_query("select user.email, user.name , user.sex, user.telephone, user.address, user.balance
									from usercourse, user
									where usercourse.courseid = $id and usercourse.email = user.email");
			while($result != NULL && $row = mysql_fetch_array($result)){
				$tmp[$n]['email'] = $row[0];
				$tmp[$n]['name'] = $row[1];
				$tmp[$n]['sex'] = $row[2];
				$tmp[$n]['telephone'] = $row[3];
				$tmp[$n]['address'] = $row[4];
				$tmp[$n++]['balance'] = $row[5];
			}
			return $tmp;
		}
		function getCourseUserByName($name)
		{
			$this->connect();	
			$tmp = array();
			$n = 0;
			$result = mysql_query("select user.email, user.name , user.sex, user.telephone, user.address
									from course, usercourse, user
									where locate('$name', course.name) > 0
									and usercourse.courseid = course.id and usercourse.email = user.email
									and course.status = '发布'");
			while($result != NULL && $row = mysql_fetch_array($result)){
				$tmp[$n]['email'] = $row[0];
				$tmp[$n]['name'] = $row[1];
				$tmp[$n]['sex'] = $row[2];
				$tmp[$n]['telephone'] = $row[3];
				$tmp[$n++]['address'] = $row[4];
			}
			return $tmp;
		}
		function getCoach()
		{
			$this->connect();
			$tmp = array();
			$n = 0;
			$result = mysql_query("select email,name,telephone,sex,address,rank from user where type ='coach'");
			while($row = mysql_fetch_array($result))
				array_push($tmp, $row);			
			return $tmp;
		}
		function getCourseByEmail($email)
		{
			$this->connect();
			$tmp = array();
			$n=0;
			$result = mysql_query("select courseid,starttime,lasttime 
									from course,usercourse 
									where usercourse.email='".$email."'and course.id=usercourse.courseid
									 and course.status = '发布'");
			while($row = mysql_fetch_array($result)){
				$tmp[$n] = array();
				$tmp[$n]['id'] = $row[0];
				$tmp[$n]['time'] = $row[1];
				$tmp[$n]['lasttime'] = $row[2];
				$n++;
			}
			return $tmp;
		}
		function coachBook($email,$coachEmail,$comment,$date,$time)
		{
			$this->connect();
			mysql_query("insert into coachbook values('".$email."',
														'".$coachEmail."',
														'".$comment."',
														'".$date."',
														'".$time."')");

			//mysql_query("update coachtime set coachtime.".$time."='ocp' 
			//							  where email='".$coachEmail."' 
			//							  and FROM_UNIXTIME(date, '%Y-%m-%d')=FROM_UNIXTIME(".$date.", '%Y-%m-%d')");
		}
		function getAllCoachBook()
		{
			$this->connect();
			$tmp = array();
			$n = 0;
			$result = mysql_query("select email, addition, FROM_UNIXTIME(date, '%Y-%m-%d'), time
									from coachbook
									order by date, time");
			while($result != NULL && $row = mysql_fetch_array($result)){
				$tmp[$n] = array();
				$tmp[$n]['email'] = $row[0];
				$un = mysql_query("select name
									from user
									where email = '$row[0]'");
				
				if($un != NULL){
					$username = mysql_fetch_array($un);
					$tmp[$n]['username'] = $username[0];
				}
				else
					$tmp[$n]['username'] = "未知";
				$tmp[$n]['addition'] = $row[1];
				$tmp[$n]['date'] = $row[2];
				$tmp[$n]['time'] = $row[3];
				$n++;
			}
			return $tmp;
		}
		function courseBook($email,$courseId)
		{
			$this->connect();
			$result = mysql_query("insert into usercourse values('".$email."',".$courseId.")");
			$course = $this->getCourseInfoById($courseId);
			$user = $this->getUserInfoByEmail($email);
			$this->setBalanceByEmail($email,$user['balance']-$course['price']);
			$this->setCapacityById($courseId,$course['capacity']-1);
			/*
			*时间冲突提示
			*/
		}
		function getRecord($email,$type,$date)
		{
			$this->connect();
			$n = 0;
			$tmp = array();
			if($type != '')
				$query = "select operation,amount,time 
									from record
									where email='".$email."'
									  and time>='".$date."'
									  and operation='".$type."'";
			else
				$query = "select operation,amount,time 
									from record
									where email='".$email."'
									  and time>='".$date."'";
			$result = mysql_query($query);
			while($row = mysql_fetch_array($result)){
				$tmp[$n] = array();
				$tmp[$n]['operation'] = $row[0];
				$tmp[$n]['amount'] = $row[1];
				$tmp[$n]['time'] = $row[2];
				$n++;
			}
			return $tmp;
		}
		function getCoachTime($email,$date)
		{
			$this->connect();
			/*
			$query = "select coachtime.9,coachtime.10,coachtime.11,coachtime.12,coachtime.13,coachtime.14,
							coachtime.15,coachtime.16,coachtime.17,coachtime.18,coachtime.19,coachtime.20
					  from coachtime
					  where email='".$email."' and  FROM_UNIXTIME(date, '%Y-%m-%d')=FROM_UNIXTIME(".$date.", '%Y-%m-%d')";
			$result = mysql_query($query);
			while($row = mysql_fetch_array($result)){
				return $row;
			}
			*/
			$tmp = array('9'=>'free',
				 		'10'=>'free',
					    '11'=>'free',
					    '12'=>'free',
					    '13'=>'free',
					    '14'=>'free',
					    '15'=>'free',
					    '16'=>'free',
					    '17'=>'free',
					    '18'=>'free',
					    '19'=>'free',
					    '20'=>'free');

			/*
			 *如果那一天教练请假
			 */
			$query = "select * from dayoff where email='$email'";
			$result = mysql_query($query);
			while($row = mysql_fetch_array($result)){
				if($row['status'] == '批准' && Date('Y-m-d',$row['time']) == Date('Y-m-d',$date)){
					$tmp['9'] = 'ocp';
					$tmp['10'] = 'ocp';
					$tmp['11'] = 'ocp';
					$tmp['12'] = 'ocp';
					$tmp['13'] = 'ocp';
					$tmp['14'] = 'ocp';
					$tmp['15'] = 'ocp';
					$tmp['16'] = 'ocp';
					$tmp['17'] = 'ocp';
					$tmp['18'] = 'ocp';
					$tmp['19'] = 'ocp';
					$tmp['20'] = 'ocp';
					return $tmp;
				}
			}
	



			/*
			 *删去上课时间
			 */
			$query = "select time 
					  from course 
					  where coachemail = '$email' 
					  and FROM_UNIXTIME(starttime, '%W') = FROM_UNIXTIME('$date', '%W')
					  and FROM_UNIXTIME(starttime, '%Y.%m.%d') <= FROM_UNIXTIME('$date', '%Y.%m.%d') 
					  and FROM_UNIXTIME(starttime+604800*(amount-1), '%Y.%m.%d') >= FROM_UNIXTIME('$date', '%Y.%m.%d'
					  and status = '发布')";//那一天教练的课程

			$result = mysql_query($query);
			while($row = mysql_fetch_array($result))
				$tmp[$row['time']] = 'ocp';

			/*
			 *删去已经预约的时间
			 */
			$query = "select time 
					 from coachbook 
					 where coachemail='$email' 
					   and FROM_UNIXTIME(date, '%Y.%m.%d') = FROM_UNIXTIME($date, '%Y.%m.%d')";
			$result = mysql_query($query);
			while($row = mysql_fetch_array($result))
				$tmp[$row['time']] = 'ocp';	

			/*
			 *根据教练的可预约时间
			 */
			$query = "select * from coachtime where email='$email'";
			$result = mysql_query($query);
			$row = mysql_fetch_array($result);
			if(isset($row['email'])){
				for($n = 9; $n<=20; $n++){
					if($n<$row['starttime'])
						$tmp[$n] = 'ocp';
					if($n>=$row['endtime'])
						$tmp[$n] = 'ocp';
				}
			}
			
			return $tmp; 
		}
		
		function rateCourse($course, $content, $style, $interaction, $progress, $comment)
		{
			$this->connect();
			$id = mysql_fetch_array(mysql_query("select id
								from course
								where name='".$course."' and status = '发布'"));

			mysql_query("insert into ratecourse values('".$id[0]."',
															'".$content."',
															'".$style."',
															'".$interaction."',
															'".$progress."',
															'".$comment."')");
		}
		
		function rateCoach($coachemail, $responsibility, $planning, $listener, $fame, $experience, $progress, $comment)
		{
			$this->connect();
			mysql_query("insert into ratecoach values('$coachemail',
															'$responsibility',
															'$planning',
															'$listener',
															'$fame',
															'$experience',
															'$progress',
															'$comment')");
		}

		
		function getNotify($type)
		{
			$this->connect();
			$query = "select * from notify where type='".$type."' or type='all'";
			$result = mysql_query($query);
			$tmp = array();
			while($row = mysql_fetch_array($result))
				array_push($tmp, $row);
			return $tmp;
		}
		function addNotify($head,$content,$date,$tyep)
		{
			$this->connect();
			$query = 'select max(id) from notify';
			$result = mysql_query($query);
			while($row = mysql_fetch_array($result))
				$id = $row[0]+1;
			$query = "insert into notify values('$id','$head','$content','$date','$tyep')";
			mysql_query($query);
		}
		function getCourseInfoById($id)
		{
			$this->connect();
			$query = "select * from course where id='$id' and status = '发布'";
			$result = mysql_query($query);
			while($row = mysql_fetch_array($result))
				return $row;
		}
		function getUserInfoByEmail($email)
		{
			$this->connect();
			$query = "select balance from user where email='$email'";
			$result = mysql_query($query);
			while($row = mysql_fetch_array($result))
				return $row;
		}
		function setBalanceByEmail($email,$balance)
		{
			$this->connect();
			$query = "update user set balance=$balance where email = '$email'";
			mysql_query($query);
		}
		function setCapacityById($id,$capacity)
		{
			$this->connect();
			$query = "update course set capacity=$capacity where id = '$id'";
			mysql_query($query);
		}
		function getUsercourseByEmail($email)
		{
			$this->connect();
			$query = "select course.id,course.name,course.time,course.starttime,course.amount 
						from usercourse,course 
						where email = '$email' and usercourse.courseid = course.id";
			$result = mysql_query($query);
			$tmp = array();
			while($row = mysql_fetch_array($result))
				array_push($tmp, $row);
			return $tmp;
		}
		function getCoachbookByEmail($email)
		{
			$this->connect();
			$query = "select * from coachbook where email = '$email'";
			$result = mysql_query($query);
			$tmp = array();

			while($row = mysql_fetch_array($result)){
				array_push($tmp, $row);
			}
			return $tmp;
		}
		function getCoachbookByCoachEmail($email)
		{
			$this->connect();
			$query = "select * from coachbook where coachemail = '$email'";
			$result = mysql_query($query);
			$tmp = array();
			while($row = mysql_fetch_array($result)){
				$info = $this->getInfoByEmail($row['email']);
				$row['studentname'] = $info['name'];
				array_push($tmp, $row);
			}
				
			return $tmp;
		}
		function checkEmail($email)
		{
			$this->connect();
			$query = "select email from user where email = '$email'";
			$result = mysql_query($query);
			$row = mysql_fetch_array($result);
			if($row == NULL)
				return 'ok';
			else
				return 'exist';
		}
		function addUser($info)
		{
			$this->connect();
			if($info['type'] == "customer"){
				$maxid = mysql_fetch_array(mysql_query("select max(cardid) from user")) ;
				$id = $maxid[0] + 1;
						$query = "insert into user values('".$info['email']."',
													 '".$info['name']."',
													 '".$info['pwd']."',
													 '".$info['payPwd']."',
													 '".$info['type']."',
													 '".$info['sex']."',
													 '".$info['telephone']."',
													 '".$info['address']."',
													 '".$info['rank']."',
													 '0',
													 '".$info['nickname']."',
													 $id,
													 '正常')";
						mysql_query($query);
					}
				else 
					mysql_query("insert into user values('".$info['email']."',
													 '".$info['name']."',
													 '".$info['pwd']."',
													 '".$info['payPwd']."',
													 '".$info['type']."',
													 '".$info['sex']."',
													 '".$info['telephone']."',
													 '".$info['address']."',
													 '".$info['rank']."',
													 '0',
													 '".$info['nickname']."',
													 NULL,
													 NULL)");
		}
		function charge($email,$balance)
		{
			$this->connect();
			$query = "update user 
				      set balance='$balance'+balance 
				      where email='$email'";
			mysql_query($query);      
		}
		function lock($email)
		{
			$this->connect();
			$query = "update user 
				      set cardstatus='挂失'
				      where email='$email'";
			mysql_query($query);  
		}
		function unlock($email)
		{
			$this->connect();
			$query = "update user 
				      set cardstatus='正常	'
				      where email='$email'";
			mysql_query($query);
		}
		function changeRank($email,$rank)
		{
			$this->connect();
			$query = "update user 
				      set rank='$rank'
				      where email='$email'";
			mysql_query($query);
		}
		function getInstrument()
		{
			$this->connect();
			$query = "select * 
					  from instrument";
			$result = mysql_query($query);
			$tmp = array();
			while ($row = mysql_fetch_array($result))
				array_push($tmp, $row);
			return $tmp;
		}
		function setStatusById($id,$status)
		{
			$this->connect();
			$query = "update instrument 
					  set status = '$status'
					  where id = '$id'";
			mysql_query($query);  
		}
		function deleteInstrument($id)
		{
			$this->connect();
			$query = "delete 
				      from instrument
				      where id='$id'";
			mysql_query($query); 	      
		}
		function addInstrument($name)
		{
			$this->connect();
			$query = "select max(id) from instrument";
			$result = mysql_query($query); 
			$row = mysql_fetch_array($result);	
			$row = $row[0] + 1;		
			$query = "insert into instrument
					  values('$row','$name','在馆')";
			mysql_query($query); 
		}
		function getRateByEmail($email)
		{
			$this->connect();
			$query = "select * 
					  from ratecoach 
					  where email = '$email'";
			$result = mysql_query($query); 
			$tmp = array();
			while($row = mysql_fetch_array($result))
				array_push($tmp, $row);
			return $tmp;
		}
		function setDayoff($email,$time)
		{
			$this->connect();
			$query = "insert into dayoff
					  values('$email','$time','待审核')";
			mysql_query($query); 
		}
		function setCoachtime($email, $startTime, $endTime)
		{
			$this->connect();
			$query = "select * from coachtime where email='$email'";
			$result = mysql_query($query); 
			$row = mysql_fetch_array($result);
			if(isset($row[0]))
				$query = "update coachtime 
						  set starttime='$startTime',endtime='$endTime' 
						   where email = '$email'";
			else
				$query = "insert into coachtime values('$email','$startTime','$endTime')";
			mysql_query($query); 
		}
		function setCoachInfo($email, $data)
		{
			$this->connect();
			$query = "update user
					  set nickname = '".$data['nickname']."',
					  	  name = '".$data['name']."',
					  	  pwd = '".$data['pwd']."',
					  	  rank = '".$data['rank']."',
					  	  sex = '".$data['sex']."',
					  	  telephone = '".$data['telephone']."',
					  	  address = '".$data['address']."'
					  where email = '$email'";
			mysql_query($query); 
		}
		function getCoachStatus()
		{
			$this->connect();
			$query = "select email,name from user where type='coach'";
			$result = mysql_query($query); 
			$coach = array();
			while($row = mysql_fetch_array($result)){
				$coach[$row['email']]['status'] = '正常';
				$coach[$row['email']]['email'] = $row['email'];
				$coach[$row['email']]['name'] = $row['name'];
			}
			$date = time();
			$query = "select email 
					  from dayoff 
					  where FROM_UNIXTIME(time, '%Y.%m.%d') == FROM_UNIXTIME('$date', '%Y.%m.%d') 
					  	and status = '通过'";
			while($row = mysql_fetch_array($result))
				$coach[$row['email']]['status'] = '请假';
			return $coach;			
		}
		function getDayoff()
		{
			$this->connect();
			$query = "select * from dayoff where status='待审核'";
			$tmp = array();
			$coach = array();
			$result = mysql_query($query); 
			while($row = mysql_fetch_array($result)) 
				array_push($tmp, $row);


			$query = "select email,name from user where type='coach'";
			$result = mysql_query($query); 
			while($row = mysql_fetch_array($result)) 
				$coach[$row['email']]['name'] = $row['name']; 
			for($i=0; $i<=count($tmp)-1; $i++){
				$tmp[$i]['name'] = $coach[$tmp[$i]['email']]['name'];
			}
			return $tmp;
		}
		function opDayoff($email,$time,$op)
		{
			$this->connect();
			$query= "update dayoff
					 set status = '$op'
					 where email = '$email'
					 and   time = '$time'";
			mysql_query($query); 
			$coach = $this->getInfoByEmail($email);
			print_r($coach);
			$this->addNotify('教练'.$coach['name'].'请假审核结果', '管理员'.$op.'了'.$coach['name'].'的'.Date('Y年m月d日',$time).'的请假请求',time(),'all');	 
		}
		function getCourseM()
		{
			$this->connect();
			$query = "select * from course where status != '发布'";
			$result= mysql_query($query); 
			$tmp = array();
			while($row = mysql_fetch_array($result)){
				$info = $this->getInfoByEmail($row['coachemail']);
				$row['coachName'] = $info['name'];
				array_push($tmp, $row);
			}
			return $tmp;
		}
		function returnMoney($id)
		{
			$this->connect();
			$query = "select price from course where id='$id'";
			$result= mysql_query($query); 
			$row = mysql_fetch_array($result);
			$price = $row['price'];
			$query = "select email from usercourse where courseid='$id'";
			$result= mysql_query($query); 
			$students = array();
			while($row = mysql_fetch_array($result)){
				array_push($students, $row['email']);
			}
			foreach ($students as $email) {
				$query = "update user set balance = balance+'$price' where email = '$email'";
				mysql_query($query); 
				$query = "delete from usercourse where email='$email' and courseid='$id'";
				mysql_query($query); 
			}
		}
		function opCourse($coach, $id, $op, $status)
		{
			$this->connect();
			if($op == '批准'){
				if($status == '增加'){
					$query = "update course 
							  set status='发布'
							  where id='$id'";
					mysql_query($query);
					$correctId = $id;
				}
				else if($status == '删除'){
					$this->returnMoney($id - 20000);
					mysql_query("delete from course where id='$id'");
					mysql_query("delete from course where id='$id'-20000");
					$correctId = $id - 20000;
				}
				else if($status == '修改'){
					mysql_query("delete from course where id='$id'-10000");
					mysql_query("update course set id='$id'-10000,status='发布' where id='$id'");
					$correctId = $id - 10000;
				}
				else if($status == '调整'){
					$tmp = mysql_fetch_array(mysql_query("select * from course where id='$id'"));
					$this->addNotify($tmp['name'],
						      $tmp['description'],
						      time(),
						      'customer');
					$query = "delete from course where id='$id'";
					mysql_query($query);
					$correctId = $id - 30000;
				} 
			}
			else{
				if($status == '增加'){
					$query = "delete from course where id='$id'";
					mysql_query($query);
					$correctId = $id;
				}
				else if($status == '删除'){
					$query = "update course 
							  set status='发布' 
							  where id='$id'";
					mysql_query($query);
					$correctId = $id - 20000;
				}
				else if($status == '修改'){
					$query = "delete from course where id='$id'";
					mysql_query($query);
					$correctId = $id - 10000;
				}
				else if($status == '调整'){
					$query = "delete from course where id='$id'";
					mysql_query($query);
					$correctId = $id - 30000;
				} 
			}
			$this->addNotify($coach.''.$status.'课程申请的结果',
						     '管理员'.$op.'了'.$coach['name'].'的'.$status.'id号为'.$correctId.'的课程的请求',
						      time(),
						      'admin');
			$this->addNotify($coach.''.$status.'课程申请的结果',
						     '管理员'.$op.'了'.$coach['name'].'的'.$status.'id号为'.$correctId.'的课程的请求',
						      time(),
						      'coach');
				
		}
		function setRecord($email, $op, $amount)
		{
			$this->connect();
			$date = time();
			$query = "insert into record 
					   values('$email','$op','$amount','$date')";
			mysql_query($query);
		}
		function getBCourseByEmail($email)
		{
			$this->connect();
			$query = "select courseid from usercourse where email='$email'";
			$result = mysql_query($query);
			$tmp = array();
			while($row = mysql_fetch_array($result))
				array_push($tmp, $row[0]);
			$result = array();
			$transfer = array('Mon'=>'星期一',
							  'Tue'=>'星期二',
							  'Wed'=>'星期三',
							  'Thu'=>'星期四',
							  'Fri'=>'星期五',
							  'Sat'=>'星期六',
							  'Sun'=>'星期日');
			foreach ($tmp as $id) {
				$course = $this->getCourseInfoById($id);
				$course['coursetime'] = $transfer[Date('D',$course['starttime'])];
				array_push($result, $course);
			}
				
			return $result;
		}
		function getBCoachByEmail($email)
		{
			$this->connect();
			$query = "select * from coachbook where email='$email'";
			$result = mysql_query($query);
			$tmp = array();
			while($row = mysql_fetch_array($result))
				array_push($tmp, $row);
			$result = array();
			foreach ($tmp as $coachBook) {
				$info = $this->getInfoByEmail($coachBook['coachemail']);
				$coachBook['coachname'] = $info['name'];
				array_push($result, $coachBook);
			}
			return $result;
		}
		function subBalance($email, $amount)
		{
			$this->connect();
			$query = "update user set balance = balance - '$amount' where email = '$email'";
			mysql_query($query);
		}
		function getPayPwdByEmail($email)
		{
			$this->connect();
			$query = "select paypwd from user where email='$email'";
			$result = mysql_query($query);
			$row = mysql_fetch_array($result);
			return $row[0];
		}

		function getCourseRate()
		{
			$this->connect();
			$query = "select id from course";
			$result = mysql_query($query);
			$courseRate = array();
			while($row = mysql_fetch_array($result)){
				$query = "select * from ratecourse where id='".$row[0]."'";
				$rates  = mysql_query($query);
				$tmp = array();
				while($rate = mysql_fetch_array($rates))
					array_push($tmp, $rate);
				$courseRate[$row[0]] = $tmp;
			}
			return $courseRate;
		}
	}
?>
