package packages;

import java.io.*;

public class Change 
{
	private SystemInfo systemInfo;
	private String oldStr1 = "signal count: integer range 0 to 0;";
	private String oldStr2 = "		if count=0 then";
	private String newStr1;
	private String newStr2;
	private String changetime;
	private String path;
	public Change(String time,String p) throws Exception
	{
		path = p;
		changetime = time;
		newStr1 = "signal count: integer range 0 to " + changetime + ";";
		newStr2 = "		if count="+ changetime + " then";
		systemInfo = new SystemInfo();
	}
	
	public String changeSep()
	{
		addTxtByStr(oldStr1, newStr1, path);
		addTxtByStr(oldStr2, newStr2, path);
		return "102";
	}
	
	public static void addTxtByStr(String oldStr, String newStr, String path)
	{
		String temp = "";
		try
		{
			File fr = new File(path);
			FileInputStream fis = new FileInputStream(fr);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			StringBuffer buf = new StringBuffer();

			while((temp = br.readLine()) != null && !temp.equals(oldStr))
			{
				buf = buf.append(temp);
				buf = buf.append(System.getProperty("line.separator"));
			}
			
			buf = buf.append(newStr);
			
			
			while((temp=br.readLine()) != null)
			{
				buf = buf.append(System.getProperty("line.separator"));
				buf = buf.append(temp);
			}

			br.close();
			FileOutputStream fos = new FileOutputStream(fr);
			PrintWriter pw = new PrintWriter(fos);
			pw.write(buf.toString().toCharArray());
			pw.flush();
			pw.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
