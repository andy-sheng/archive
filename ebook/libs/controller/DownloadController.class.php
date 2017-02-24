<?php 
	require_once('libs/controller/function.php');
	require_once('config.php');
	require_once('libs/model/db.class.php');
	class DownloadController
	{
		var $bid;
		function __construct()
		{
			$this->bid = $_GET['bid'];
		}
		function handle()
		{
			//$storage = new JingdongStorageService(accesskey, secrectkey);

			/*
			$fileName = uniqid().'.mobi';
			$filePath = 'tmp/' + $fileName;
			$fp = fopen($filePath, 'wb+');

			
			if($fp){
	        	$storage->get_object(bookbucket,$this->bid,$fp, false);
	        	
			}
			else
				echo 'error';
			*/

			$filePath = './jae/'.$this->bid;
			if(!file_exists($filePath))
				download($this->bid);
			
			$fp = fopen($filePath, 'r');
			
			Header("Content-type: application/octet-stream"); 
			Header("Accept-Ranges: bytes"); 
			Header("Accept-Length: ".filesize($filePath)); 
			Header("Content-Disposition: attachment; filename=" . $this->bid); 
			echo fread($fp,filesize($filePath)); 
			
	        fclose($fp);
	   		
	   		
	   		if(isset($_SESSION['email'])){
	   			$email = $_SESSION['email'];
				$db = new db();
				$db->addDownload($email, $this->bid, '下载', '成功');
	   			
	   		}
	   		
		}
	}
 ?>