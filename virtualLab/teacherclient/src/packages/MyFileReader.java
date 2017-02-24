package packages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MyFileReader 
{
	File file;
	BufferedReader temp;
	public MyFileReader(String path)
	{
		file = new File(path);
	}
	public MyFileReader(File f)
	{
		file = f;
	}
	public String[] readLines() throws Exception
	{
		int n;
		String[] str;
		String strTemp;
		int i = 0;
		temp = new BufferedReader(new FileReader(file));
		n = temp.read();
		while(n != -1)//¼ÆËãÐÐÊý
		{
			if((char)n == '\n')
				i++;
			n = temp.read();
		}
		temp = new BufferedReader(new FileReader(file));
		strTemp = temp.readLine();
		str = new String[i];
		i = 0;
		while(strTemp != null)
		{
			str[i++] = strTemp;
			strTemp = temp.readLine();
		}
		return str;
	}
	public String readFile() throws IOException
	{
	
		String str = "";
		String strTemp;
		temp = new BufferedReader(new FileReader(file));
		strTemp = temp.readLine();
		while(strTemp != null)
		{
			str = str + strTemp;
			strTemp = temp.readLine();
		}
		return str;
	}
}
