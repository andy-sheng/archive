<?php 
	class LookupController
	{
		private $get;
		private $smarty;
		function setSmarty($s)
		{
			$this->smarty = $s;
		}
		function handle($g,$s)
		{
			$this->get = $g;
			$this->smarty = $s;
			if($_SESSION['type'] != 'customer'){
				header('location:index.php');
				exit();
			}
			if($_SESSION['type'] == 'customer' && !isset($this->get['type'])){//没有带type参数
				echo "asdfasdfasdf";
				$smarty->display('libs/view/header.ctp');
				$smarty->display('libs/view/lookup.ctp');
				$smarty->display('libs/view/tail.ctp');
			}
			else if(isset($this->get['type'])){//
				require_once 'libs/model/db.class.php';
				$db = new db();
				$tmp = array();
				$tmp[0] =array('operation'=>'123','amount'=>'123123','time'=>'asdf');
				$this->smarty->assign('record',$tmp);
				$this->smarty->display('libs/view/lookupResult.ctp')；
			}
		}
	}
 ?>