<?php 
	class LogoutController
	{
		function __constuct()
		{

		}
		function handle()
		{
			unset($_SESSION['email']);
			header('location:index.php');
		}
	}
 ?>