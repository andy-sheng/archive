package packages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Vector;

public class DnsRelay
{
	private static Vector record;
	public DnsRelay() throws Exception
	{
		readFile();
	}
	public Vector readFile() throws Exception
	{
		record = new Vector();
		String tmp;
		BufferedReader temp = new BufferedReader(new FileReader(new File("dnsrelay.txt")));
		tmp = temp.readLine();
		while(tmp != null)
		{
			if(tmp.length() >= 10){
				String[] str = tmp.split(" ");
				record.add(str);
			}
			tmp = temp.readLine();
		}
		return record;
	}
	public void reflesh()throws Exception
	{
		record.removeAllElements();
		String tmp;
		BufferedReader temp = new BufferedReader(new FileReader(new File("dnsrelay.txt")));
		tmp = temp.readLine();
		while(tmp != null)
		{
			if(tmp.length() >= 10){
				String[] str = tmp.split(" ");
				record.add(str);
			}
			tmp = temp.readLine();
		}
	}
	public String search(String domin)
	{
		for(int i = 0; i<=record.size()-1; i++){
			String[] tmp = (String[])record.elementAt(i);
			if(tmp[1].equals(domin))
				return tmp[0];
		}
		return null;
	}
}
