package packages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CopyFile
{
	public CopyFile() 
	{
	}
	public void copyFile(String from,String to)throws IOException
	{
		File temp = new File(from);
		FileInputStream data = new FileInputStream(from);
		byte[] fileData = new byte[data.available()];
		data.read(fileData);
		OutputStream b = new FileOutputStream(new File(to+"//"+temp.getName()));
		b.write(fileData); 
		b.close();
		data.close();
	}
	public void copyFiles(String from,String to) throws IOException
	{
		File[] files = new File(from).listFiles();
		for(int i = 0; i<=files.length - 1; i++)
			copyFile(files[i].getAbsolutePath(),to);
	}
}