<?php 
    require_once('libs/controller/function.php');
	class PushController
	{

		var $fileName;
		var $e;
		function __construct()
		{
			$this->fileName = $_GET['fileName'];
			$this->e = $_GET['email'];
		}
		function doPush()
		{
			$db = new db();
			$did = -1;
			if(isset($_SESSION['email']))
				$did = $db->addDownload($_SESSION['email'], $this->fileName, '推送', '准备推送');
			$info = 'error';
			download($this->fileName);
			try{
				$info = pushToEmail($this->e, $this->fileName);
			}
			catch(Exception $e){
				if($did != -1)
					$db->updateDownloadStatus($did, '失败');
	   			echo 'error';exit();
			}
			if($info == 'success'){
				if($did != -1)
					$db->updateDownloadStatus($did, '成功');		
				echo 'success';
			}
			else{
				if($did != -1)
					$db->updateDownloadStatus($did, '失败');
	   			echo 'error';
			}
			
		}
	}

 ?>