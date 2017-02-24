package packages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

public class UnPackageFiles {

	public UnPackageFiles(String account) throws Exception
	{
		File check = new File(account);
    	if(check.exists())
    		new DeleteFile(account);
		unzip(new File("").getAbsolutePath()+"/"+account+".zip",new File("").getAbsolutePath());
	}
      public static void ZIP(String sourcePath, String zipFileName)
            throws IOException {
        ZipOutputStream zos = new ZipOutputStream(new File(zipFileName));
        // 设置压缩的时候文件名编码为gb2312
        zos.setEncoding("gb2312");
        File file = new File(sourcePath);
        if (file.isDirectory()) {
            // 此处使用/来表示目录,如果使用\\来表示目录的话,有可能导致压缩后的文件目录组织形式在解压缩的时候不能正确识别。
            ZIPDIR(sourcePath, zos, file.getName() + "/");
        } else {
            // 如果直接压缩文件
            ZIPDIR(file.getPath(), zos, new File(file.getParent()).getName() + "/");
            ZIPFile(file.getPath(), zos, new File(file.getParent()).getName() + "/" + file.getName());
        }
        zos.closeEntry();
        zos.close();
    }

    
    public static void ZIPDIR(String sourceDir, ZipOutputStream zos,String tager) throws IOException {
        // System.out.println(tager);
        ZipEntry ze = new ZipEntry(tager);
        zos.putNextEntry(ze);
        // 提取要压缩的文件夹中的所有文件
        File f = new File(sourceDir);
        File[] flist = f.listFiles();
        if (flist != null) {
        // 如果该文件夹下有文件则提取所有的文件进行压缩
            for (File fsub : flist) {
                if (fsub.isDirectory()) {
                    // 如果是目录则进行目录压缩
                    ZIPDIR(fsub.getPath(), zos, tager + fsub.getName() + "/");
                }else{
                    // 如果是文件，则进行文件压缩
                    ZIPFile(fsub.getPath(), zos, tager + fsub.getName());
                }
            }
        }
    }

    
    public static void ZIPFile(String sourceFileName, ZipOutputStream zos,String tager) throws IOException {
         // System.out.println(tager);
         ZipEntry ze = new ZipEntry(tager);
         zos.putNextEntry(ze);
         // 读取要压缩文件并将其添加到压缩文件中
         FileInputStream fis = new FileInputStream(new File(sourceFileName));
         byte[] bf = new byte[2048];
         int location = 0;
         while ((location = fis.read(bf)) != -1) {
             zos.write(bf, 0, location);
         }
         fis.close();
    }
    
    static String getSuffixName(String name) {
    	return name.substring(0, name.lastIndexOf("."));
    }
    static void createDir(String path) {
    	File dir = new File(path);
    	if (dir.exists() == false)
    		dir.mkdir();
     }
    public static void unzip(String zipFilePath, String unzipDirectory)
    		throws Exception {
    	 		File file = new File(zipFilePath);
    	 		ZipFile zipFile = new ZipFile(file);
    	 		File unzipFile = new File(unzipDirectory /*+ "/"+ getSuffixName(file.getName())*/);
    	 		if (unzipFile.exists())
    	 			unzipFile.delete();
    	 			unzipFile.mkdir();
    	 			Enumeration zipEnum = zipFile.getEntries();
    	 			InputStream input = null;
    	 			OutputStream output = null;
    	 			ZipEntry entry = null;
    	 			while (zipEnum.hasMoreElements()) {
    	 				entry = (ZipEntry) zipEnum.nextElement();
    	 				String entryName = new String(entry.getName());
    	 				String names[] = entryName.split("\\/");
    	 				int length = names.length;
    	 				String path = unzipFile.getAbsolutePath();
    	 				for (int v = 0; v < length; v++) {
    	 					if (v < length - 1) { // 最后一个目录之前的目录
    	 						path += "/" + names[v] + "/";
    	 						createDir(path);
    	 					} else { // 最后一个
    	 						if (entryName.endsWith("/")) // 为目录,则创建文件夹
    	 							createDir(unzipFile.getAbsolutePath() + "/" + entryName);
    	 						else { // 为文件,则输出到文件
    	 							input = zipFile.getInputStream(entry);
    	 							output = new FileOutputStream(new File(unzipFile.getAbsolutePath()+ "/" + entryName));
    	 							byte[] buffer = new byte[1024 * 8];
    	 							int readLen = 0;
    	 							while ((readLen = input.read(buffer, 0, 1024 * 8)) != -1)
    	 								output.write(buffer, 0, readLen);
    	 								input.close();
    	 								output.flush();
    	 								output.close();
    	 						}
    	 					}
    	 				}
    	 			}
     	}
}
