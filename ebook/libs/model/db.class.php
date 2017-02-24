<?php  
	require_once('config.php');
	class db
	{
		function connect()
		{
			/*
			$host = '127.11.204.2';
			$port = '3306';
			$user = 'adminUhSyTY2';
			$pwd = 'TpbhRLG_gvkG';
			$dbname = 'test2';
			$link = mysql_connect("{$host}:{$port}",$user,$pwd,true);
			if(!$link)
				echo 'failed to connect to the database';
			mysql_select_db($dbname,$link);
			*/
			/*
			$mysql_host = "mysql6.000webhost.com";
		    $mysql_database = "a4476103_test";
		    $mysql_user = "a4476103_db";
		    $mysql_password = "201314sheng";
		    $con = mysql_connect($mysql_host, $mysql_user, $mysql_password);
		    mysql_select_db("a4476103_test", $con);
*/
	
		    $mysql_host = "localhost";
		    $mysql_database = "ebook";
		    $mysql_user = "root";
		    $mysql_password = "";
		    //$con = mysql_connect($mysql_host, $mysql_user, $mysql_password);
		   //mysql_select_db("ebook", $con);



			$con = mysql_connect(dbhost, dbusr, dbpwd);
		mysql_select_db(dbname, $con);
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
		function searchBookByName($name, $page)
		{
			$start = ($page - 1)*pagesize;
			$num = pagesize;
			$this->connect();
			$query = "select * from book where name like'"."%$name%"."' limit $start,$num";
			$result = mysql_query($query);
			$tmp = array();
			while($row = mysql_fetch_array($result))
				array_push($tmp, $row);
			return $tmp;
		}
		function countBookByName($name)
		{
			$this->connect();
			$query = "select count(bid) from book where name like'"."%$name%"."'";
			$result = mysql_query($query);
			$row = mysql_fetch_array($result);
			return $row['count(bid)'];
		}
		function searchBookById($id)
		{
			$this->connect();
			$id = addslashes($id);
			$query = "select * from book where bid='$id'";
			$result = mysql_query($query);
			while($row = mysql_fetch_array($result))
				return $row;
		}
		function checkPwd($email, $pwd)
		{
			$this->connect();
			$email = addslashes($email);
			$query = "select pwd from usr where email='$email'";
			$result = mysql_query($query);
			while($row = mysql_fetch_array($result)){
				if($row['pwd'] == $pwd)
					return true;
			}
			return false;
		}
		function getKindle($email)
		{
			$this->connect();

			$email = addslashes($email);

			$query = "select kindle from usr inner join kindle on usr.email=kindle.email where usr.email='$email'";
			$result = mysql_query($query);
			$tmp = array();
			while($kindle = mysql_fetch_array($result))
				array_push($tmp, stripslashes($kindle['kindle']));
			return $tmp;
		}
		function addUsr($email, $pwd)
		{
			$this->connect();
			$query = "select * from usr where email = '$email'";
			$result = mysql_query($query);
			while($row = mysql_fetch_array($result))
				return false;
			$query = "insert into usr value('$email', '$pwd')";
			mysql_query($query);
			return true;
		}
		function checkEmail($email)
		{
			$this->connect();
			$email = addslashes($email);
			$query = "select email from usr where email = '$email'";
			$result = mysql_query($query);
			while($row = mysql_fetch_array($result))
				return 'exit';
			return 'notexit';
		}
		function addBook($bookName, $author, $tag, $isbn, $bookDesc, $authorDesc, $uploader)
		{
			$bid = 0;
			$date = time();
/*

			$bookName = addslashes(htmlentities($bookName));
			$author = addslashes(htmlentities($author));
			$isbn = addslashes(htmlentities($isbn));
			$bookDesc = addslashes(htmlentities($bookDesc));
			$authorDesc = addslashes(htmlentities($bookDesc));
			$uploader = addslashes(htmlentities($uploader));
			$tag = addslashes(htmlentities($tag));
*/

			$this->connect();
			$query = "select max(bid) from book";
			$result = mysql_query($query);
			while($row = mysql_fetch_array($result))
				$bid =  $row['max(bid)'];
			$bid = $bid + 1;
			$query = "insert into book value('$bid',
											'$bookName',
											'$tag',
											'$author',
											'$authorDesc',
											'$bookDesc', 
											'$date', 
											'$isbn', 
											0,
											'$uploader')";
			$result = mysql_query($query);
			return $bid;
		}
		function addKindle($email, $kindle)
		{
			$kid = '0';

			$kindle = addslashes($kindle);

			$this->connect();
			$query = 'select max(kid) from kindle';
			$result = mysql_query($query);
			while($row = mysql_fetch_array($result))
				$kid =  $row['max(kid)'];
			$kid = $kid + 1;
			$query = "insert into kindle value('$kid', '$email', '$kindle')";
			$result = mysql_query($query);
		}
		function getCommentByBid($bid, $pg)
		{
			$start = ($pg-1)*commentpgsize;
			$num = commentpgsize;
			$this->connect();
			$query = "select cid,email,content from comment where bid = '$bid' order by cid limit $start,$num";
			$result = mysql_query($query);
			$tmp = array();
			while($row = mysql_fetch_array($result)){
				$row['content'] = stripslashes($row['content']);
				array_push($tmp, $row);
			}
			
			return $tmp;
		}
		function addComment($bid, $email, $comment)
		{
			$cid = 1;

			$comment = addslashes(htmlentities($comment));

			$this->connect();
			$query = "select max(cid) from comment where bid = '$bid'";
			$result = mysql_query($query);
			if($row = mysql_fetch_array($result))
				$cid = $row['max(cid)'] + 1;
			$query = "insert into comment value('$bid', '$cid', '$email', '$comment')";
			mysql_query($query);

		}
		function addDownload($email, $bid, $type, $status)
		{
			$did = 1;
			$date = time();
			$this->connect();
			$query = "select max(did) from download where email = '$email'";
			$result = mysql_query($query);
			if($row = mysql_fetch_array($result))
				$did = $row['max(did)'] + 1;
			$query = "insert into download value('$did', '$email', '$bid', '$type', '$status', '$date')";
			mysql_query($query);



			$query = "update book set count = count + 1 where bid = '$bid'";
			mysql_query($query);

			return $did;
		}		
		function updateDownloadStatus($did,$status)
		{
			$query = "update download set status='$status' where did = '$did'";
			mysql_query($query);
		}
		function getDownloads($email)
		{
			$tmp = array();
			$this->connect();
			$query = "select * from download where email = '$email'";
			$result = mysql_query($query);
			while($row = mysql_fetch_array($result)){
				array_push($tmp, $row);
			}
			return $tmp;
		}
	}
?>