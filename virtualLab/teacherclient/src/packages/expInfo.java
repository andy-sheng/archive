package packages;

import java.util.Vector;

public class expInfo 
{
	private Vector shudian;
	private Vector huibian;
	public expInfo() throws Exception
	{
		shudian = new Vector();
		huibian = new Vector();
		int i = 0;
		MyFileReader file = new MyFileReader("expInfo.ini");
		String[] info = file.readLines();
		String[] temp;
		if(info[i++].equals("[Êýµç]"))
		{
			while(true)
			{
				if(info[i].equals("[END]"))
					break;
				temp = info[i].split("=");
				shudian.add(temp);
				i++;
			}
		}
		i++;
		if(info[i++].equals("[»ã±à]"))
		{
			while(true)
			{
				if(info[i].equals("[END]"))
					break;
				huibian.add(info[i]);
				i++;
			}
		}
				
	
	}
	public Vector getShudian()
	{
		return shudian;
	}
	public Vector getHuibian()
	{
		return huibian;
	}
}

