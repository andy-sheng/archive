package packages;


import java.awt.BorderLayout;     
import java.awt.Color;     
import java.awt.Component;     
import java.awt.Dimension;     
import java.awt.Graphics;     
import java.awt.Graphics2D;     
import java.awt.Image;     
import java.awt.Panel;     
import java.awt.event.ActionEvent;     
import java.awt.event.ActionListener;     
import java.awt.event.WindowAdapter;     
import java.awt.event.WindowEvent;     
import java.awt.image.BufferedImage;     
import java.io.File;
import java.io.FileNotFoundException;     
import java.io.FileOutputStream;     
import java.io.IOException;     
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.media.Buffer;     
import javax.media.CannotRealizeException;     
import javax.media.CaptureDeviceInfo;     
import javax.media.CaptureDeviceManager;     
import javax.media.DataSink;
import javax.media.Manager;     
import javax.media.MediaLocator;     
import javax.media.NoPlayerException;     
import javax.media.Player;     
import javax.media.Processor;
import javax.media.control.FrameGrabbingControl;     
import javax.media.format.VideoFormat;     
import javax.media.protocol.DataSource;
import javax.media.protocol.FileTypeDescriptor;
import javax.media.util.BufferToImage;     
import javax.swing.JButton;     
import javax.swing.JFrame;     
import javax.swing.JPanel;     

import jmapps.util.StateHelper;

import com.sun.image.codec.jpeg.ImageFormatException;     
import com.sun.image.codec.jpeg.JPEGCodec;     
import com.sun.image.codec.jpeg.JPEGEncodeParam;     
import com.sun.image.codec.jpeg.JPEGImageEncoder;  


public class Camera 
{
	private CaptureDeviceInfo captureDeviceInfo=null;   //捕获硬件设备信息对象  
	private MediaLocator mediaLocator=null;     //媒体定位器
	private static Player player=null;     //播放对象
	private Buffer buffer=null;       //缓冲区
	private VideoFormat videoFormat=null;     //视频数据格式
	private BufferToImage bufferToImage=null;   //缓存影像  
    private Image image=null; 
	public Camera(String path , int n)
	{
		 String str="vfw:Microsoft WDM Image Capture (Win32):0";
	       captureDeviceInfo=CaptureDeviceManager.getDevice(str);
	       mediaLocator=new MediaLocator("vfw://0");     
	     //  imagePanel=new ImagePanel();     //设置面板
	       // capture=new JButton("拍照");     //设置按钮名称
	       // capture.addActionListener(this);     //增加按钮事件
	       try 
	       {     
	           player=Manager.createRealizedPlayer(mediaLocator);  //将本地视频绑定到播放对象中，数据源绑定.   
	           player.start();     //启动，一般分为6个状态
	       } 
	       catch (NoPlayerException e)
	       {     
	           // TODO Auto-generated catch block     
	           e.printStackTrace();     
	       }
	       catch (CannotRealizeException e) {     
	           // TODO Auto-generated catch block     
	           e.printStackTrace();     
	       }
	       catch (IOException e)
	       {     
	           // TODO Auto-generated catch block     
	           e.printStackTrace();     
	       }    
	       try { Thread.sleep ( 10000 ) ; 
	       } catch (InterruptedException ie){}
	       for( int i = 1;  i <= n; i++)
	       {
	    	   File temp = new File(path);
	    	   if(temp.exists() == false)
	    		   temp.mkdirs();
	    	   shoot(path + "\\"+i + ".jpg");
	    	   try
	    	   { 
	    		   Thread.sleep ( 1000 ) ; 
		       } 
	    	   catch (InterruptedException ie){}
	       }
	       player.close();
	}
	public void shoot(String path)
   {
	   FrameGrabbingControl fgc=(FrameGrabbingControl)player.getControl("javax.media.control.FrameGrabbingControl");     
      buffer=fgc.grabFrame();     
      bufferToImage=new BufferToImage((VideoFormat)buffer.getFormat());     
      image=bufferToImage.createImage(buffer);  
      
      
      
      BufferedImage bi=new BufferedImage(200, 250,BufferedImage.TYPE_INT_RGB);     
      Graphics2D g2 = bi.createGraphics();     
      g2.drawImage(image, null, null);     
      FileOutputStream fos=null;     //文件输出流对象
      try
      {     
          fos=new FileOutputStream(path);     //获得路径
               
      } catch (FileNotFoundException e) {     
          // TODO Auto-generated catch block     
          e.printStackTrace();     
      }     
      JPEGImageEncoder je=JPEGCodec.createJPEGEncoder(fos);   //创建一个指向  fos的JPEGImageEncoder对象
      JPEGEncodeParam jp=je.getDefaultJPEGEncodeParam(bi);     //@@@
      jp.setQuality(0.5f, false);      //创建替代当前已建量化表的新量化表
      je.setJPEGEncodeParam(jp);      //设置JPEGImageEncoder对象编码操作
      try 
      {     
          je.encode(bi);      // � BufferedImage 作为 JPEG 数据流编码。
          fos.close();     
      }
      catch (ImageFormatException e) 
      {     
          // TODO Auto-generated catch block     
          e.printStackTrace();     
      }
      catch (IOException e) 
      {     
          // TODO Auto-generated catch block     
          e.printStackTrace();     
      }        
   }
	
	   public static void main(String[] args)
	   {
	   }
}
