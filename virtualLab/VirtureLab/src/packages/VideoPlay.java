package packages;
import java.io.File;

public class VideoPlay 
{
	static String path;
	public VideoPlay(String filePath) throws Exception
	{
	   path = filePath;
	   File batFile = new File("temp.bat");
	   batFile.deleteOnExit();
	   batFile = new File("temp.bat");
	   MyFileWriter wrtBat = new MyFileWriter("temp.bat");
	   String[] temp = new File(filePath).getAbsolutePath().split("\\\\");
	   filePath = temp[0];
	   for(int i = 1; i<=temp.length-1; i++)
		   filePath = filePath +"\\\\" + temp[i];
	   wrtBat.writeLine("\""+filePath+"\"");
	   wrtBat.destroy();
	   batFile = new File("temp.bat");
	   String path = batFile.getAbsolutePath();System.out.println(path);
	   temp = path.split("\\\\");
	   for(int i = 0; i<=temp.length-1; i++)
		   System.out.println(temp[i]);
	   path = temp[0];
	   for(int i = 1; i<=temp.length-1; i++)
		   path = path +"\\\\" + temp[i];
	   Process process = Runtime.getRuntime().exec(path);
	   	process.waitFor();
	}
	public static void main(String[] arg) throws Exception
	{
		
		
	}
}
