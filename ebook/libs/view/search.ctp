<div class='' style='padding-bottom:80px'>
	<div class="search-result-box">
	{if $result|@count neq 0}
		{foreach $result as $line}
			<div class='panel row' style='padding-top:20px;padding-bottom:20px;background-image: url(http://myebook.jd-app.com/img/tree_bark.png)'>
				<div class='col-md-2'>
					<a href='http://myebook.jd-app.com/book/{$line.bid}.html'>
						<img src='{$line.picSrc}' class='search-result-pic' alt='{$line.name}'>
					</a>		
				</div>
				<div class='col-md-8'>
					<a href='http://myebook.jd-app.com/book/{$line.bid}.html'><h4>{$line.name}</h4></a>
					<h5><small>{$line.author}&nbsp著</small></h5>
					<h5><small>标签：{$line.tag}</small></h5>
				</div>
			</div>
			<!--<div style='padding-top:5px;border-bottom:1px solid #2A2A2C'></div>-->
		{/foreach}	
		</div>
		<div style='float:right'>
			<ul class="pagination">
				{foreach $pageLink as $link}
					{if $link.flag == 'start'}
			   			<li><a href="{$link.url}">&laquo;首页</a></li>
			   		{else if $link.flag == 'end'}
			   			<li><a href="{$link.url}">&raquo;尾页</a></li>
			   		{else if $link.flag == 'dot'}
			   			<li><span>.......</span></li>
			   		{else}
			   			<li class='{$link.flag}'><a href="{$link.url}">{$link.pg}</a></li>
			   		{/if}
		   		{/foreach}	
			</ul>
		</div>
	{else}
		<div class='panel row'  style='padding-top:20px;padding-bottom:20px;background-image: url(http://myebook.jd-app.com/img/tree_bark.png)'>
				<div class='col-md-6'>
					没有结果
				</div>
		</div>
	{/if}
</div>
