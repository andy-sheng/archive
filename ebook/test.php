<?php 
	require('libs/controller/function.php');
	$name = $_GET['name'];
	$data = getHtml("https://api.douban.com/v2/book/search?q=$name&start=0&count=1");
	$json = json_decode($data);
	if(isset($json->{'books'}[0])){
		$book = $json->{'books'}[0];
		$result = array();
		$result['name'] = $book->{'title'};
		$result['author'] = $book->{'author'};
		$result['author_intro'] = $book->{'author_intro'};
		$result['isbn'] = $book->{'isbn13'};
		$result['pic-id'] = downloadImg($book->{'image'});
		$result['img'] = getTmpFileUrl($result['pic-id']);
		$result['publisher'] = $book->{'publisher'};
		$result['summary'] = $book->{'summary'};
		echo json_encode($result);
	}
	else
		echo 'error';
 ?>
