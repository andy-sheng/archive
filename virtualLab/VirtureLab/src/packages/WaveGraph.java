package packages;
import packages.*;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.*;

public class WaveGraph implements MouseWheelListener, MouseListener, MouseMotionListener
{
	private JDialog mainFrame;
	private graph waveGraph;
	private JScrollPane scrollPan;
	private int xMouse;
	private int xBar;
	private int yBar;
	private int yMouse;
	private int mod = 0;//1时显示虚线
	public WaveGraph(String path) throws IOException
	{
		mainFrame = new JDialog();
		mainFrame.setModal(true);
		mainFrame.setLocation(500, 300);
		mainFrame.setSize(500,500);
		mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Container container = mainFrame.getContentPane();
		
		
		/*
		 * 测试
		 */
		AnalyseFile vwfFile = new AnalyseFile(path);
		Vector data = vwfFile.getSignalLevel();
		String name[] = vwfFile.getSignalNames();
		
		
		waveGraph = new graph(name,data);
		waveGraph.setPreferredSize(new Dimension(450,450));
		waveGraph.setVisible(true);
		
		scrollPan = new JScrollPane(waveGraph);
		waveGraph.addMouseWheelListener(this);
		waveGraph.addMouseListener(this);
		scrollPan.setWheelScrollingEnabled(false);
		waveGraph.addMouseMotionListener(this);
		
		container.add(scrollPan);
		int x =(((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().width) - mainFrame.getWidth())/2;
	    int y = (((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().height) - mainFrame.getHeight())/2; 
	    mainFrame.setTitle("波形图");
	    mainFrame.setLocation(x,y);
		mainFrame.setVisible(true);
	}
	
	public class graph extends JPanel
	{
		private int size = 10;
		private Graphics graph;
		private Graphics crosLine;
		private int xMouse = 0;
		private int yMouse = 0;
		private int mod = 0;
		private Vector signalData;
		private String[] signals;
		private int signalAmount;
		public graph(String[] signalNames,Vector signal)
		{
			signalData = signal;
			signals = signalNames;
			signalAmount = signalNames.length;
		}
		public void paintComponent(Graphics g)
		{
			graph = g;
			super.paintComponent(g);
			this.setLayout(null);
			g.setColor(Color.black);
			for(int i = 0; i<=signalAmount-1; i++)
			{
				drawSignal(30*(i+1),signals[i],(int[][])signalData.elementAt(i));
			}
		//	int[][] h={{20,1},{50,0}};
			//drawSignal(30,"hehe",h);
			//g.drawLine(10 , 10, 10, 30);	
			if(mod == 1)
			{
				drawDashed(0,yMouse,this.getWidth(),yMouse);//横线
				drawDashed(xMouse,0,xMouse,this.getHeight());//竖线线
			}
		}
		
		/*
		 * 绘制信号的函数
		 * startPoint:开始绘制波形的基准点，最左下角
		 * nam:信号名字
		 * points:信号的所有转折点 points[][0]存时间  points[][1]存电平 0表示低电平 1表示高电平 2表示未知
		 * 电平1 高 20个像素  一个单位时间宽size个像素
		 */
		private void drawSignal( int startPoint, String nam,int points[][])
		{
			int lastLevel = 2;//上一个电平，2代表起始状态，1代表上一个是1,0代表上一个是0
			int currentPosition = 100;//目前线的位置
			JLabel name = new JLabel(nam);
			name.setToolTipText(nam);
			name.setHorizontalAlignment(JLabel.RIGHT );
			name.setBounds( 0, startPoint-20, 100, 20);
			this.add(name);
			for( int i = 0; i<=points.length-1; i++)
			{
				if(points[i][1] == 0)
				{
					if(lastLevel == 1)
						graph.drawLine(currentPosition, startPoint, currentPosition, startPoint-20);//画竖线
					graph.drawLine(currentPosition, startPoint, size*points[i][0]+currentPosition, startPoint);//画横线
					currentPosition += size*points[i][0]; 
					lastLevel = 0;
				}
				else if(points[i][1] == 1)
				{
					if(lastLevel == 0)
						graph.drawLine(currentPosition, startPoint, currentPosition, startPoint-20);//画竖线
					graph.drawLine(currentPosition, startPoint-20, size*points[i][0]+currentPosition, startPoint-20);//画横线
					currentPosition += size*points[i][0]; 
					lastLevel = 1;
				}
				else
				{
					drawX(currentPosition, startPoint, currentPosition+points[i][0], startPoint);
					currentPosition += size*points[i][0]; 
				}
			}
		}
		
		/*
		 * 设置模式，为1时显示虚线，为0时不显示虚线
		 */
		public void setMod(int m)
		{
			mod = m;
		}
		
		
		/*
		 * 电平为0，画XXX
		 */
		public void drawX(int x1, int y1, int x2, int y2)
		{
			int dis = x2 - x1;
			int x = 0;
			while(dis >= 20)
			{
				graph.drawLine(x1+x*20, y1, x1+x*20+20, y1+20);
				graph.drawLine(x1+x*20, y1+20, x1+x*20+20, y1);
				dis = dis - 20;
				x++;
			}
			graph.drawLine(x1+x*20, y1, x2, y1+20);
			graph.drawLine(x1+x*20, y1+20, x2, y1);
		}
		
		
		/*
		 * 设置鼠标的位置，在鼠标发生移动时用来重绘虚线
		 */
		void setMouseXY(int x,int y)
		{
			xMouse = x;
			yMouse = y;
		}
		
		
		/*
		 * 画虚线的函数
		 */
		public void drawDashed( int x1, int y1, int x2, int y2)
	    {
	        int x = x1,y=y1;
	        int n = 4;  //实线段长度
	        int m = 4;  //虚线段长度
	        
	        int tx = 0,ty = 0;
	        
	        int c = 0;
	        boolean flag = true;  //标记 有没有画完（达到要求的长度）
		        
	        int mark_x = 0;  //标记 要画的是 水平线（值为1）
	        int mark_y = 0;  //标记 要画的是 垂直线（值为1）
	        //要么0，要么 1
	        if(x2-x1 != 0)
	            mark_x = 1;
	        else
	            mark_y = 1;
		        
	        do
	        {
	            tx = (int)((c*(n+m) - m)*mark_x + x1 );    
	            ty = (int)((c*(n+m) - m)*mark_y + y1 );   

	            if(Math.abs(tx-x1) > Math.abs(x2-x1))
	            {
	                tx = x2;
	                flag = false;
	            }
	            if(Math.abs(ty - y1) > Math.abs(y2 - y1))
	            {
	                ty = y2;
	                flag = false;
	            }
	            graph.drawLine(x,y,tx,ty);
	            x = (int)(c*(n+m)*mark_x + x1 );    //更新 实线段 + 虚线段
	            y = (int)(c*(n+m)*mark_y + y1 );
		            
	            if(x > x2 || y > y2) break;
		           c++;
		       }
		       while(flag);
	    }
		
		/*
		 * 放大波形函数
		 * 大小以10为基准，每调用一次加1
		 * 不大于10
		 */
		public void bigger()
		{
			if(size < 10)
				size = size + 1;
			setPreferredSize(new Dimension( 450*size, 400));
			//paintComponent(graph);
			repaint();
		}
		
		/*
		 * 缩小波形函数
		 * 大小以10为基准，每调用一次减1
		 * 不小于1
		 */
		public void smaller()
		{
			if(size > 1)
				size = size - 1;
			setPreferredSize(new Dimension( 450*size, 400));
			//paintComponent(graph);
			repaint();
		}
	}
	
	
	
	public static void main(String[] args) throws InterruptedException, IOException 
	{
	}


	private int bigRec = 0;
	private int smaRec = 0;
	@Override
	public void mouseWheelMoved(MouseWheelEvent event) {
		// TODO Auto-generated method stub
		System.out.println(event.getWheelRotation());
		if(event.getWheelRotation() < 0)
		{
			smaRec = 0;
			bigRec++;
		}
		else
		{
			smaRec++;
			bigRec= 0;
		}
		if(smaRec == 1)
		{
			smaRec = 0;
			waveGraph.smaller();;
			waveGraph.setVisible(false);
			waveGraph.setVisible(true);
		}
		else if(bigRec == 1)
		{
			bigRec = 0;
			waveGraph.bigger();
			waveGraph.setVisible(false);
			waveGraph.setVisible(true);
		}
		
	}



	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		mod = (mod+1)%2;
		waveGraph.setMod(mod);
		waveGraph.repaint();
	}



	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	//	Toolkit tk = Toolkit.getDefaultToolkit(); 
	//	Image img = tk.getImage("1.jpg");
	//	Cursor cu = tk.createCustomCursor(img,new Point(16,16),"stick"); 
		//setCursor(cu);
		Cursor cu = new Cursor(Cursor.CROSSHAIR_CURSOR);  
		waveGraph.setCursor(cu);
	}



	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		Cursor cu = new Cursor(Cursor.DEFAULT_CURSOR);        
		waveGraph.setCursor(cu);
	}



	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		xMouse = arg0.getX();
		xBar = scrollPan.getHorizontalScrollBar().getValue();
		yMouse = arg0.getY(); 
		yBar = scrollPan.getVerticalScrollBar().getValue();
		Cursor cu = new Cursor(Cursor.HAND_CURSOR);        
		waveGraph.setCursor(cu);
	}



	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		Cursor cu = new Cursor(Cursor.CROSSHAIR_CURSOR);  
		waveGraph.setCursor(cu);
	}



	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int xMoved = arg0.getX() - xMouse;
		int yMoved = arg0.getY() - yMouse;
		int xModify = xBar - xMoved;
		int yModify = yBar - yMoved;
		System.out.println("xNow:"+arg0.getX());
		System.out.println("yNow:"+arg0.getY());
		System.out.println("xMouse:"+xMouse);
		System.out.println("yMouse:"+yMouse);
		System.out.println("xBar:"+xBar);
		System.out.println("yBar:"+yBar);
		System.out.println("xModify:"+xModify);
		System.out.println("yModify:"+yModify);
		scrollPan.getHorizontalScrollBar().setValue(xModify);
		scrollPan.getVerticalScrollBar().setValue(yModify);
	}



	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		waveGraph.setMouseXY(arg0.getX(), arg0.getY());
		waveGraph.repaint();
		//waveGraph.repaint(xMouse-1, 0, xMouse+1, 500*mainFrame.getWidth());
		//waveGraph.repaint(0, yMouse-1, mainFrame.getHeight(), yMouse+1);
	}

	
}

