<?php 
	$f = fopen('1.txt','w');
	for( $i=1; $i<=1600; $i++){
		echo htmlentities('<url>').'<br>';
		echo '&nbsp'.htmlentities('<loc>http://myebook.jd-app.com/book/'.$i.'.html</loc>').'<br>';
		echo '&nbsp'.htmlentities('<changefreq>daily</changefreq>').'<br>';

		echo htmlentities('</url>');
	}
	


 ?>