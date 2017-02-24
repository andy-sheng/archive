package packages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

public class PackageFiles {

	public PackageFiles(String account) throws IOException
	{
		File check = new File(account);
    	if(check.exists())
    		new DeleteFile(account);
		ZIP("students\\"+account,"students\\"+account+".zip");
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
    
    
    @SuppressWarnings("unchecked")
    public static void UnZIP(String sourceFileName, String desDir) throws IOException {
        // ����ѹ���ļ�����
        ZipFile zf = new ZipFile(new File(sourceFileName),"GB2312");
        // ��ȡѹ���ļ��е��ļ�ö��
        Enumeration en = zf.getEntries();
        int length = 0;
        byte[] b = new byte[1024];
        // ��ȡѹ���ļ����е�����ѹ��ʵ������
        while (en.hasMoreElements()) {
            //ZipEntry ze=en.nextElement();
        	ZipEntry ze=(ZipEntry) en.nextElement();
            // System.out.println("ѹ���ļ����е����ݣ�"+ze.getName());
            // System.out.println("�Ƿ����ļ��У�"+ze.isDirectory());
            // ������ѹ������ļ�ʵ������
            File f = new File(desDir + ze.getName());
            // System.out.println("��ѹ������ݣ�"+f.getPath());
            // System.out.println("�Ƿ����ļ��У�"+f.isDirectory());
            // �����ǰѹ���ļ��е�ʵ���������ļ��о��ڽ�ѹ������ļ����д������ļ���
            if (f.isDirectory()) {
                f.mkdirs();
            } else {
                // �����ǰ��ѹ���ļ��ĸ����ļ���û�д����Ļ����򴴽��ø����ļ���
                if (!f.getParentFile().exists()) {
                    f.getParentFile().mkdirs();
                }
                // ����ǰ�ļ�������д���ѹ����ļ����С�
                FileOutputStream  outputStream = new FileOutputStream(f);
                InputStream inputStream = zf.getInputStream(ze);
                while ((length = inputStream.read(b)) > 0){
                    outputStream.write(b, 0, length);
                }
                inputStream.close();
                outputStream.close();
            }
        }
        zf.close();
    }
}

