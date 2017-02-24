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
	require_once('libs/sdk/JingdongStorageService.php');
	$storage = new JingdongStorageService("07395fe30c1d4db1b18efb8f3112630a", "e87b653c3d7949fda9f41259132d0a06cDGuQXYE");

	$other_headers = array();     //可传入如Range等其他该请求可用的request header
	$bucket_name = 'ebook';
	$local_file = 'tmp/467.mobi';
	$key = '467.mobi';
	$storage->get_object($bucket_name,$key, $local_file,$other_headers);
	print_r($other_headers);
	echo '123';
	$f = fopen('tmp/467.mobi','r');
	echo fread($f,10 );
	$bucketslist = $storage->list_buckets();
foreach($bucketslist as $jss_bucket) {
  print_r("Bucket:" . $jss_bucket->get_name() . "\n");
  print_r("CTime: " . $jss_bucket->get_ctime()  . "\n\n");  

}
 ?>