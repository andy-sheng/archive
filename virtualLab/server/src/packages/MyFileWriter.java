package packages;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MyFileWriter 
{
	File file;
	BufferedWriter bufferedWriter;
	public MyFileWriter(String filePath) throws Exception
	{
		file = new File(filePath);
		bufferedWriter = new BufferedWriter(new FileWriter(file));
	}
	public void writeLines(String[] data) throws Exception
	{
		for(int i = 0; i<=data.length - 1; i++)
		{
			bufferedWriter.write(data[i]);
			bufferedWriter.newLine();
			bufferedWriter.flush();
		}
	}
	public void destroy() throws Exception
	{
		bufferedWriter.close();
	}
}
