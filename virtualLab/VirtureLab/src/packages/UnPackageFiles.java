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
        // ����ѹ����ʱ���ļ�������Ϊgb2312
        zos.setEncoding("gb2312");
        File file = new File(sourcePath);
        if (file.isDirectory()) {
            // �˴�ʹ��/����ʾĿ¼,���ʹ��\\����ʾĿ¼�Ļ�,�п��ܵ���ѹ������ļ�Ŀ¼��֯��ʽ�ڽ�ѹ����ʱ������ȷʶ��
            ZIPDIR(sourcePath, zos, file.getName() + "/");
        } else {
            // ���ֱ��ѹ���ļ�
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
        // ��ȡҪѹ�����ļ����е������ļ�
        File f = new File(sourceDir);
        File[] flist = f.listFiles();
        if (flist != null) {
        // ������ļ��������ļ�����ȡ���е��ļ�����ѹ��
            for (File fsub : flist) {
                if (fsub.isDirectory()) {
                    // �����Ŀ¼�����Ŀ¼ѹ��
                    ZIPDIR(fsub.getPath(), zos, tager + fsub.getName() + "/");
                }else{
                    // ������ļ���������ļ�ѹ��
                    ZIPFile(fsub.getPath(), zos, tager + fsub.getName());
                }
            }
        }
    }

    
    public static void ZIPFile(String sourceFileName, ZipOutputStream zos,String tager) throws IOException {
         // System.out.println(tager);
         ZipEntry ze = new ZipEntry(tager);
         zos.putNextEntry(ze);
         // ��ȡҪѹ���ļ���������ӵ�ѹ���ļ���
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
    	 					if (v < length - 1) { // ���һ��Ŀ¼֮ǰ��Ŀ¼
    	 						path += "/" + names[v] + "/";
    	 						createDir(path);
    	 					} else { // ���һ��
    	 						if (entryName.endsWith("/")) // ΪĿ¼,�򴴽��ļ���
    	 							createDir(unzipFile.getAbsolutePath() + "/" + entryName);
    	 						else { // Ϊ�ļ�,��������ļ�
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
