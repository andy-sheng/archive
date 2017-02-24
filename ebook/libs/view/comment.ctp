{foreach $comments as $comment}
	<div class='list-group-item'>
		<h5 class="list-group-item-heading"><small>{$comment.email}</small></h5>
		<p class="list-group-item-text"><small>{$comment.content}</small></p>
	</div>
{/foreach}