package packages;

public class DnsBagAnaly
{
	private byte[] dnsData;
	public DnsBagAnaly(byte[] dnsBag)
	{
		dnsData = new byte[dnsBag.length];
		System.arraycopy(dnsBag, 0, dnsData, 0, dnsBag.length);
	}
	public String getQuestions()
	{
		int flag = (int)dnsData[12];//问题的第一个
		int n = 13;
		String dominName = "";
		while(flag!=0){
			byte[] tmp=new byte[flag];
			for(int i =0; i<=tmp.length-1; i++)//提取这一段
				tmp[i] = dnsData[n+i];
			if(dominName == "")
				dominName = new String(tmp);
			else
				dominName = dominName + "." + new String(tmp);
			n  = n + flag;
			flag = (int)dnsData[n++];
		}
		return dominName;
	}
	public byte[] getId()
	{
		byte[] id = new byte[2];
		id[0] = dnsData[0];
		id[1] = dnsData[1];
		return id;
	}
	public int getSize()
	{
		int flag = (int)dnsData[12];//问题的第一个
		int n = 13;
		while(flag!=0){
			n  = n + flag;
			flag = (int)dnsData[n++];
		}
		return n+4;
	}
	public int getType()
	{
		int flag = (int)dnsData[12];//问题的第一个
		int n = 13;
		while(flag!=0){
			n  = n + flag;
			flag = (int)dnsData[n++];
		}
		return dnsData[++n];
	}
	public void setId(int id)
	{
		String ID = Integer.toHexString(id);
		if(ID.length()==2)
			ID = "00"+ID;
		else if(ID.length()==3)
			ID = "0" + ID;
		else if(ID.length()==1)
			ID = "000" + ID;
		dnsData[0] = (byte)(charToByte(ID.charAt(0)) << 4 | charToByte(ID.charAt(1)));
		dnsData[1] = (byte)(charToByte(ID.charAt(2)) << 4 | charToByte(ID.charAt(3)));
	}
	public void setId(byte[] id)
	{
		dnsData[0] = id[0];
		dnsData[1] = id[1];
	}
	public void setResponse(String ip)
	{
		byte[] response = new byte[16];
		String ipTmp;
		String[] tmp = ip.split("\\.");
		response[0] = (byte)(charToByte('c') << 4 | charToByte('0'));//name
		response[1] = 0x0c;
		response[2] = 0x00;//type
		response[3] = 0x01;
		response[4] = 0x00;//class
		response[5] = 0x01;
		response[6] = 0x00;//ttl
		response[7] = 0x00;
		response[8] = 0x00;
		response[9] = 0x30;
		response[10] = 0x00;
		response[11] = 0x04;
		ipTmp = Integer.toHexString(Integer.valueOf(tmp[0]));
		if(ipTmp.length() == 1)
			ipTmp = '0'+ipTmp;
		response[12] = (byte)(charToByte(ipTmp.charAt(0)) << 4 | charToByte(ipTmp.charAt(1)));
		ipTmp = Integer.toHexString(Integer.valueOf(tmp[1]));
		if(ipTmp.length() == 1)
			ipTmp = '0'+ipTmp;
		response[13] = (byte)(charToByte(ipTmp.charAt(0)) << 4 | charToByte(ipTmp.charAt(1)));
		ipTmp = Integer.toHexString(Integer.valueOf(tmp[2]));
		if(ipTmp.length() == 1)
			ipTmp = '0'+ipTmp;
		response[14] = (byte)(charToByte(ipTmp.charAt(0)) << 4 | charToByte(ipTmp.charAt(1)));
		ipTmp = Integer.toHexString(Integer.valueOf(tmp[3]));
		if(ipTmp.length() == 1)
			ipTmp = '0'+ipTmp;
		response[15] = (byte)(charToByte(ipTmp.charAt(0)) << 4 | charToByte(ipTmp.charAt(1)));
		appendAnswer(response);
		
	}
	public void appendAnswer(byte[] responseData)
	{
		int flag = (int)dnsData[12];//问题的第一个
		int n = 13;
		while(flag!=0){
			n  = n + flag;
			flag = (int)dnsData[n++];
		}
		byte[] tmp = new byte[n+16+4];
		System.arraycopy(dnsData, 0, tmp, 0, n+4);
		System.arraycopy(responseData, 0, tmp, n+4, 16);
		tmp[2] = (byte)0x81;
		tmp[3] = (byte)0x80;
		tmp[7] = (byte)0x01;
		dnsData = new byte[tmp.length];
		System.arraycopy(tmp, 0, dnsData, 0, tmp.length);
	}
	public byte[] getDnsBag()
	{
		return dnsData;
	}
	private byte charToByte(char c)
	{  
		return (byte) "0123456789abcdef".indexOf(c);  
	}
}
