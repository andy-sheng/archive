<table class="table table-hover">
	<tbody>
	    <tr><td><span >用户名:</span></td><td>{$customer.nickname}</td></tr>
	   	<tr><td>Email:</td><td>{$customer.email}</td></tr>
	    <tr><td>名字:</td><td>{$customer.name}</td></tr>
	    <tr><td>性别:</td><td>{$customer.sex}</td></tr>
	    <tr><td>电话:</td><td>{$customer.telephone}</td></tr>
	    <tr><td>地址:</td><td>{$customer.address}</td></tr>
	    <tr><td>等级:</td><td id='showrank'>{$customer.rank}</td></tr>
	    <tr><td>账户余额:</td><td id='showbalance'>{$customer.balance}</td></tr>
	    <tr><td>会员卡号:</td><td>{$customer.cardid}</td></tr>
	    <tr><td>会员卡状态:</td><td id='status'>{$customer.cardstatus}</td></tr>
	  </tbody>
 </table>