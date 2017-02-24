package packages;

import java.io.File;
import java.io.IOException;

public class DeleteFile 
{
	public DeleteFile(String path) throws IOException
	{
		deleteFile(path);
	}
	public  void deleteFile(String path) throws IOException
	{
		File temp = new File(path);
		if(temp.isDirectory()){
			File[] child = temp.listFiles();
			for(int i=0; i<=child.length-1; i++ )
				deleteFile(child[i].getCanonicalPath());
			
		}
		temp.delete();
	}
}
