<?php 
	require_once('libs/controller/function.php');
	require_once('libs/model/db.class.php');
	require_once('libs/smarty/Smarty.class.php');
	class UploadController
	{
		function __construct()
		{

		}
		function handle()
		{
			if(isset($_GET['behav'])){
				if($_GET['behav'] == 'uploadimg'){
					$name = uniqid().'.jpg';
					move_uploaded_file($_FILES["pic"]["tmp_name"], './jae/'.$name);
					upoadTmpFile('./jae/'.$name);
					$tmp = array();
					$tmp['url'] = getTmpFileUrl($name);
					$tmp['id'] = $name;
					echo json_encode($tmp);
					exit();
				}
			}
			$smarty = new Smarty();
			$smarty->assign('logined', isLogined());
			$smarty->display('libs/view/head.ctp');
			if(isset($_POST['bookname'])){
				$name = explode('.', $_FILES["file"]["name"]);
				$name = $name[count($name) -1 ];
				if($name == 'mobi'){
					$id = $this->doUpload();
					header("location:index.php?action=detail&bid=$id");
					//$smarty->assign('type','success');
					//$smarty->assign('message','上传成功');
				}
				else{
					$smarty->assign('type','error');
					$smarty->assign('message','上传失败，文件类型错误');
				}
				//$smarty->display('libs/view/alert.ctp');

			}
			$smarty->display('libs/view/upload.ctp');
			$smarty->display('libs/view/tail.ctp');
		}
		function doUpload()
		{
			$db = new db();
			$name = $_POST['bookname'];
			if($_POST['author'] != '')
				$author = $_POST['author'];
			else
				$author = '未知';
			if($_POST['desc'] != '')
				$desc = $_POST['desc'];
			else
				$desc = '无';
			if($_POST['authordesc'] != '')
				$authorDesc = $_POST['authordesc'];
			else
				$authorDesc = '无';
			if($_POST['tag'] != '')
				$tag = $_POST['tag'];
			else
				$tag = '其他';

			if($_POST['isbn'] != '')
				$isbn = $_POST['isbn'];
			else
				$isbn = 0;
			if(isset($_SESSION['email']) && $_SESSION['email'] != '')
				$uploader = $_SESSION['email'];
			else
				$uploader = '匿名';
			$id = $db->addBook($name, $author, $tag, $isbn, $desc, $authorDesc, $uploader);
			move_uploaded_file($_FILES["file"]["tmp_name"], './jae/'.$id.'.mobi');
			uploadEbook('./jae/'.$id.'.mobi');


			if($_POST['pic-id'] != '')
				rename('./jae/'.$_POST['pic-id'], './jae/'.$id.'.jpg');
			else
				createCover($name, $author, $id);
			uploadImg('./jae/'.$id.'.jpg');
			unlink('./jae/'.$id.'.mobi');
			return $id;
		}
	}
 ?>