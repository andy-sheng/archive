package packages;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import packages.*;

public class FileTransferToLowerCase {
	   public String inputPath,outputPath; 
	   public FileTransferToLowerCase(String inputpath,String outputpath){
		   inputPath = inputpath;
		   outputPath = outputpath;
	   }
	   public void transfer() throws IOException {
		//String txtFilePath = "D:\\实验室java程序\\teacher\\experiments\\3.vhd";//原文件
		FileInputStream fis = new FileInputStream(new File(inputPath));
		InputStreamReader isr = new InputStreamReader(fis,"GBK");
		BufferedReader reader = new BufferedReader(isr);
		File file = new File(inputPath);
		file.delete();
		//String otherTxtFilePath = "D:\\实验室java程序\\teacher\\experiments\\3.vhd";//保存小写的文件
		FileOutputStream fos = new FileOutputStream(new File(outputPath));
		OutputStreamWriter osw = new OutputStreamWriter(fos,"GBK");
		BufferedWriter writer = new BufferedWriter(osw);
		
		String line = null;
		while((line = reader.readLine()) != null){
			writer.append(line.toLowerCase()).append("\r\n");
		}
		
		writer.close();
		reader.close();
	}
}