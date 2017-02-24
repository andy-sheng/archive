<!DOCTYPE html>
<html>
<head>
	<meta charset='utf-8'>
	<title></title>
</head>
<body>

</body>
</html>
<?php 

	require_once('class.phpmailer.php'); //导入PHPMAILER类
	$mail = new PHPMailer(); //创建实例
	$mail->CharSet='utf-8'; //设置字符集
	$mail->SetLanguage('ch','language/');  //设置语言类型和语言文件所在目录          
	//$mail->IsSMTP(); //使用SMTP方式发送
	$mail->Mailer   = "SMTP";

	$mail->SMTPAuth = true; //设置服务器是否需要SMTP身份验证  
	$mail->Host = "smtp.qq.com"; // 您的邮局域名
	$mail->Port = '587'; // 端口
	$mail->SMTPSecure = "ssl"; //加密方式
	$mail->From = '2805551741@qq.com'; //发件人EMAIL地址
	$mail->FromName = '2805551741@qq.com'; //发件人在SMTP主机中的用户名  
	$mail->Username = '2805551741@qq.com'; //发件人的姓名  
	$mail->Password = '123abc'; //发件人在SMTP主机中的密码  
	$mail->Subject = '测试邮件的标题'; //邮件主题  
	$mail->AltBody = 'text/html'; //设置在邮件正文不支持HTML时的备用显示
	$mail->Body = '测试邮件的内容';//邮件内容做成
	$mail->IsHTML(true);  //是否是HTML邮件
	$mail->AddAddress('andysheng@live.com','andy'); //收件人的地址和姓名  
	$mail->AddReplyTo('andysheng@live.com','andy'); //收件人回复时回复给的地址和姓名
	$mail->AddAttachment('1.epub','1.txt');//附件的路径和附件名称
	$mail->SMTPDebug=true;
	if(!$mail -> Send()) //发送邮件  
		var_dump($mail -> ErrorInfo);  //查看发送的错误信息

 ?>
