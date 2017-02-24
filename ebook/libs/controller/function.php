<?php 
	require_once('libs/mail/class.phpmailer.php'); //导入PHPMAILER类
	require_once('libs/sdk/JingdongStorageService.php');
	require_once('config.php');
	function pushToEmail($email, $fileName)
	{	
		$mail = new PHPMailer(); //创建实例
		//$mail->SMTPDebug=true;
		$mail->CharSet='utf-8'; //设置字符集
		$mail->SetLanguage('en','libs/mail/language/');  //设置语言类型和语言文件所在目录          
		//$mail->IsSMTP(); //使用SMTP方式发送
		$mail->IsSMTP();
		$mail->SMTPAuth = true; //设置服务器是否需要SMTP身份验证  
		//$mail->Host = "smtp.gmail.com"; // 您的邮局域名
		$mail->Host = smtp;
		//$mail->Port = '465'; // 端口
		//$mail->SMTPSecure = "ssl"; //加密方式
		$mail->From = email; //发件人EMAIL地址
		$mail->FromName = email; //发件人在SMTP主机中的用户名  
		$mail->Username = email; //发件人的姓名  
		$mail->Password = emailpwd; //发件人在SMTP主机中的密码  
		$mail->Subject = 'kindle推送'; //邮件主题  
		$mail->Body = 'kindle';//邮件内容做成
		 $mail->IsHTML(true);
		$mail->AddAddress($email,'kindle user'); //收件人的地址和姓名  
		$mail->AddAttachment("./jae/$fileName",'1.mobi');//附件的路径和附件名称
		if(!$mail -> Send()){ //发送邮件 
			//var_dump($mail -> ErrorInfo);  //查看发送的错误信息
			return 'error';
		}
		else{
			return 'success';
		}
			
	}
	function getHtml($url)
	{
		$ch = curl_init();
		$user_agent = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; .NET CLR 1.1.4322)";
		curl_setopt ( $ch, CURLOPT_USERAGENT, $user_agent );
		curl_setopt($ch, CURLOPT_URL, $url);
		curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
		curl_setopt($ch, CURLOPT_HEADER, 0);
		curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, FALSE);
		curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, FALSE);
		$output = curl_exec($ch);
		curl_close($ch);
		return $output;
	}
	function downloadImg($url)
	{
		$id = uniqid().'.jpg';
		$data = getHtml($url);
		$local = fopen('./jae/'.$id, 'wb');
		fwrite($local, $data);
		upoadTmpFile('./jae/'.$id);
		return $id;
	}
	function download($fileName)
	{
		$storage = new JingdongStorageService(accesskey, secrectkey);
		$other_headers = array();     //可传入如Range等其他该请求可用的request header
		$bucket_name = 'ebook';
		$local_file = "./jae/$fileName";
		$key = $fileName;
		$storage->get_object($bucket_name,$key, $local_file,$other_headers);
	}
	function upload($path, $bucket)
	{
		$storage = new JingdongStorageService(accesskey, secrectkey);
		$other_headers = array();     //可传入如Range等其他该请求可用的request header'
		$key = explode('/', $path);
		$key = $key[count($key)-1];
		$storage->put_object($bucket,$key, $path);
	}
	function isLogined()
	{
		$tmp = array();
		if(isset($_SESSION['email']))
			$tmp['flag'] = 'true';
		else
			$tmp['flag'] = 'false';
		$tmp['cookie'] = cookie();
		return $tmp;
	}
	function uploadImg($path)
	{
		upload($path, imgbucket);
	}
	function uploadEbook($path)
	{
		upload($path, bookbucket);
	}
	function upoadTmpFile($path)
	{
		upload($path, tmpbucket);
	}
	function getFileUrl($fileName, $bucket)
	{
		$storage = new JingdongStorageService(accesskey, secrectkey);
		try {
			$expire = 10*60; //十分钟后失效
			$url = $storage->get_object_resource($bucket,$fileName,$expire);
			return $url;
		} catch (Exception $e) {
			return '';
		}
	}
	function getTmpFileUrl($fileName)
	{
		return getFileUrl($fileName, tmpbucket);
	}
	function getImgUrl($imgName)
	{
		return getFileUrl($imgName, imgbucket);
	}
	function cookie()
	{
		$cookie = array();
		if(isset($_COOKIE['email'])){
			$email = $_COOKIE['email'];
			$pwd = $_COOKIE['pwd'];
			$remember = 'true';
		}
		else{
			$email = '';
			$pwd = '';
			$remember = '';
		}
		$cookie['email'] = $email;
		$cookie['pwd'] = $pwd;
		$cookie['remember'] = $remember;
		return $cookie;
	}
	function createCover($title, $author, $id)
	{
		$back = imagecreatefromjpeg('./img/cover.jpg');
		$color = imagecolorallocate($back, 0, 0, 0);


		$len = strlen($title);
		$size = 30;
		$y = 375;
		$x = 130;
		if($len > 12)
			$x = $x - 40*($len - 12)/3;
		if($x < 0){
			$x = 0;
			$size = ceil($size/($len/21));
		}
		imagettftext($back, $size, 0, $x, $y, $color, './img/1.ttf', $title);

		$author = '作者：'.$author;
		$len = strlen($author);
		$size = 20;
		$y = 450;
		$x = 90;
		if($len > 15)
			$x = $x - 40*($len - 15)/3;
		if($x < 0){
			$x = 0;
			$size = ceil($size/($len/24));
		}
		imagettftext($back, $size, 0, $x, $y, $color, 'img/1.ttf', $author);




		imagejpeg($back, './jae/'.$id.'.jpg');
		imagedestroy($back);
	}
 ?>