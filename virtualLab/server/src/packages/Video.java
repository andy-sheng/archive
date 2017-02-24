package packages;

import java.io.File;
import java.io.IOException;

import javax.media.DataSink;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Processor;
import javax.media.format.VideoFormat;
import javax.media.protocol.DataSource;
import javax.media.protocol.FileTypeDescriptor;

import jmapps.util.StateHelper;

public class Video {
	public Video(int time,String name,String directory)throws Exception 
	{
		// CaptureDeviceInfo info = (CaptureDeviceInfo)  
        // CaptureDeviceManager.getDeviceList(new VideoFormat(null)).get(0);  
        MediaLocator mediaLocator = new MediaLocator("vfw://0");// 此类描述媒体目录的地址????  
        MediaLocator audioLocator = new MediaLocator("javasound://44100");  
        DataSource[] dataSources = new DataSource[2];  
        dataSources[0] = Manager.createDataSource(mediaLocator);  
        dataSources[1] = Manager.createDataSource(audioLocator);  
        DataSource ds = null;  
        ds = Manager.createMergingDataSource(dataSources);  
        ds = Manager.createCloneableDataSource(ds);  
        Processor p = null;  
        
        try {  
            p = Manager.createProcessor(ds);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        StateHelper sh = new StateHelper(p);  
        sh.configure(5000);  
        VideoFormat vf = new VideoFormat(VideoFormat.CINEPAK);  
        // AudioFormat vf = new AudioFormat(AudioFormat.IMA4);  
        p.getTrackControls()[0].setFormat(vf);  
        p.getTrackControls()[0].setEnabled(true);  
        p.setContentDescriptor(new FileTypeDescriptor(  
                FileTypeDescriptor.QUICKTIME));  
        sh.realize(5000);  
        File file = new File(directory,name);  //创建视频
  
        try { 
            file.createNewFile();  
        } catch (IOException e1) {  
            e1.printStackTrace();  
        }  
  
        DataSink sink = null;  
        try {  
            sink = Manager.createDataSink(p.getDataOutput(), new MediaLocator(  
                    file.toURL()));  
        } catch (Exception e) {  
  
            e.printStackTrace();  
  
        }  
  
        try {  
  
            p.start();  
  
            sink.open();  
  
            sink.start();  
  
        } catch (Exception e) {  
  
            e.printStackTrace();  
  
        }  
  
        try {  
            Thread.sleep(10000*time/7);// 录制时间  (s) 完全不准。。。
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
  
        try {  
            p.close();  
            sink.stop();  
            sink.close();  
        } catch (Exception e) {  
  
            e.printStackTrace();  
  
        }  
	}
}
