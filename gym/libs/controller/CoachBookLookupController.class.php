<?php 
	class CoachBookLookupController
	{
		private $get;
		private $smarty;
		function setSmarty($s)
		{
			$this->smarty = $s;
		}
		function displayAll()
		{
			$db = new db();
			$tmp = $db->getAllCoachBook();
			$this->smarty->assign('coachbook',$tmp);
			$this->smarty->display('libs/view/header.ctp');
			$this->smarty->display('libs/view/coachBookLookup.ctp');	
			$this->smarty->display('libs/view/tail.ctp');
		}
		
		function handle($g,$s)
		{
			$this->get = $g;
			$this->smarty = $s;
			if($_SESSION['type'] != 'coach'){
				header('location:index.php');
				exit();
			}
			require_once 'libs/model/db.class.php';
			$this->displayAll();
		}
	}
 ?>