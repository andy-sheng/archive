<?php 
	require_once('libs/controller/function.php');
	require_once('libs/model/db.class.php');
	require_once('config.php');
	class SearchController
	{
		function __construct()
		{

		}
		function handle()
		{
			if(!isset($_GET['method']) || !isset($_GET['query']))
				header('location:'.url);
			if($_GET['method'] == 'name'){
				$db = new db();
				$total = $db->countBookByName($_GET['query']);
				$totalPage = ceil($total/pagesize);
				$curPage = 1;
				if(isset($_GET['pg']) && $_GET['pg'] <= $totalPage)
					$curPage = $_GET['pg'];
				$result = $db->searchBookByName($_GET['query'],$curPage);
			}
			else if($_GET['method'] == 'author'){

			}
			else if($_GET['method'] == 'ISBN'){

			}
			else
				header('location:'.url);
			for($i = 0; $i <= count($result)-1; $i++)
				$result[$i]['picSrc'] = getImgUrl($result[$i]['bid'].'.jpg');

			$pageLink = array();
			$tmp = array();
			$tmp['flag'] = 'start';
			$tmp['url'] = url.'search/name/'.$_GET['query'].'.html/pg=1';
			array_push($pageLink, $tmp);
			$start = 1;
			if($totalPage <= 10){	
				$start = 1;
			}
			else{
				$start = $curPage-3;
				if($start <= 0)
					$start = 1;
				if($start + 9 > $totalPage)
					$start = $totalPage - 9;
			}
			$i = 1;
			for(; $start <= $totalPage && $i < 10; $start++,$i++){
				$tmp = array();
				if($start == $curPage)
					$tmp['flag'] = 'active';
				else
					$tmp['flag'] = '';
				$tmp['url'] = url.'search/name/'.$_GET['query'].".html/pg=$start";
				$tmp['pg'] = $start;
				array_push($pageLink, $tmp);
			}
			if($start < $totalPage){
				$tmp = array();
				$tmp['flag'] = 'dot';
				array_push($pageLink, $tmp);
			}
			$tmp = array();
			$tmp['flag'] = 'end';
			$tmp['url'] = url.'search/name/'.$_GET['query'].".html/pg=$totalPage";
			array_push($pageLink, $tmp);


			$smarty = new Smarty();
			$smarty->assign('pageLink', $pageLink);
			$smarty->assign('logined', isLogined());
			$smarty->assign('result',$result);
			$smarty->display('libs/view/head.ctp');
			$smarty->display('libs/view/search.ctp');
			$smarty->display('libs/view/tail.ctp');
			//print_r($_GET);
		}
	}

 ?>