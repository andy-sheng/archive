{foreach $record as $line}
	<tr>
		<td><h5>{$line.operation}</h5></td>
		<td><h5>{$line.amount}</h5></td>
		<td><h5>{$line.time|date_format:"%Y年%m月%d日 %H时%M分%S秒"}</h5></td>
	</tr>
{/foreach}