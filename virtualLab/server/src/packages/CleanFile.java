package packages;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import packages.*;

public class CleanFile 
{
	private static String path;
	public CleanFile(String account, String name,String entity) throws IOException 
	{
		path = "students\\"+account + "\\experiments\\";
		CopyFile copy = new CopyFile();
		File[] files = new File(path + name).listFiles();
		for(int i = 0; i<=files.length-1; i++){
			if(files[i].getName().equals("video") || files[i].getName().endsWith(".vhd") || files[i].getName().equals("sim.vwf"))
				continue;
			System.out.println(files[i].getAbsolutePath());
			new DeleteFile(files[i].getAbsolutePath());
		}
	}
	public CleanFile(String name) throws IOException 
	{
		path = "experiments\\" + name;
		CopyFile copy = new CopyFile();
		File[] files = new File(path).listFiles();
		for(int i = 0; i<=files.length-1; i++){
			if(files[i].getName().endsWith(".txt") || files[i].getName().endsWith(".vhd") || files[i].getName().equals("standard.vwf"))
				continue;
			System.out.println(files[i].getAbsolutePath());
			new DeleteFile(files[i].getAbsolutePath());
		}
	}
}
