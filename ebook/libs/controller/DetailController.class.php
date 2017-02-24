<?php 
	require_once('libs/controller/function.php');
	class DetailController
	{
		function __construct()
		{

		}
		function addComment()
		{
			$comment = $_POST['comment'];
			$tmp = array();
			$tmp['status'] = 'success';
			$tmp['comment'] = $_POST['comment'];
			if(isset($_SESSION['email']))
				$tmp['email'] = $_SESSION['email'];
			else
				$tmp['email'] = '匿名';
			$db = new db();
			$db->addComment($_GET['bid'], $tmp['email'], $tmp['comment']);
			$tmp['comment'] = htmlentities($tmp['comment']);
			echo json_encode($tmp);
		}
		function show()
		{
			$db = new db();
			$info = $db->searchBookById($_GET['bid']);//判断空的情况
			$comments = $db->getCommentByBid($_GET['bid'],1);
			$info['bookUrl'] = url.'index.php?action=download&bid='.$info['bid'].'.mobi';
			$info['picUrl'] = getImgUrl($info['bid'].'.jpg');
			$smarty = new Smarty();
			if(isset($_SESSION['kindle'])){
				$smarty->assign('hasKindle', 'true');
				$smarty->assign('kindle', $_SESSION['kindle']);
			}
			else{
				$smarty->assign('hasKindle', 'false');
				$smarty->assign('kindle', '');
			}
			$smarty->assign('logined', isLogined());
			$smarty->assign('info', $info);
			$smarty->assign('comments',$comments);
			$smarty->display('libs/view/head.ctp');
			$smarty->display('libs/view/detail.ctp');
			$smarty->display('libs/view/tail.ctp');
		}
		function handle()
		{
			require_once('libs/model/db.class.php');
			if(!isset($_GET['bid']))
				header('location:index.php');
			if(isset($_GET['behav']) ){
				if($_GET['behav'] == 'comment'&& isset($_POST['comment']))
					$this->addComment();
				else if($_GET['behav'] == 'morecomment'&& isset($_GET['pg'])){
					$db = new db();
					$comments = $db->getCommentByBid($_GET['bid'], $_GET['pg']);
					$smarty = new Smarty();
					$smarty->assign('comments',$comments);
					$smarty->display('libs/view/comment.ctp');
				}
			}	
			else{
				$this->show();
			}
			
		}
	}
 ?>