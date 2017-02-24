package packages;

import java.io.IOException;
import java.util.Vector;

import packages.*;

public class AnalyseFile 
{
	final int hehe = 500;
	private int simulationTime;
	private String fileContent;
	private String[] splitFile;
	private String[] signalName;
	private Vector signalLevel;
	public AnalyseFile(String path) throws IOException
	{
		fileContent = new MyFileReader(path).readFile();
		String[] temp = fileContent.split("TRANSITION_LIST");
		temp[temp.length-1] =(temp[temp.length-1].split("DISPLAY_LINE"))[0]; //第一个没有用
		splitFile = new String[temp.length-1];
		for(int i = 0; i<=splitFile.length-1; i++)
			splitFile[i] = temp[i+1];
		
		/*
		 * 获得仿真时间
		 */
		int start = temp[0].indexOf("SIMULATION_TIME");
		int end = temp[0].indexOf(".", start);
		String str = temp[0].substring(start,end);
		temp = str.split(" = ");
		simulationTime = Integer.valueOf(temp[1]);
		/*
		 * 获得信号名字
		 */
		getNames();
		/*
		 * 获得信号电平
		 */
		signalLevel = new Vector();
		for(int i=0; i<=signalName.length-1; i++)
		{
			String node = getNode(splitFile[i]);
			signalLevel.add(dealNode(node));
		}
		int i=0;
	}
	
	
	
	/*
	 * 传入一个node大括号内内容方便递归
	 */
	private int[][] dealNode(String node)
	{
		Vector tempInt = new Vector();
		int[][] subNodeInt;
		String temp;
		int repeat = 0;
		int point;
		for(int i=0; i<=node.length()-1; i++)
		{
			switch(node.charAt(i))
			{
			case 'R'://repeat
				temp = node.substring(i, node.indexOf(";", i));
				repeat = Integer.valueOf((temp.split(" = "))[1]);
				i = temp.length() + i;
				break;
			case 'L'://level
				temp = node.substring(i, node.indexOf(".", i));
				String levelStr = (temp.split(" FOR "))[0];//LEVEL 1
				String timeStr = (temp.split(" FOR "))[1];//5000   按比例缩放
				int[] level = new int[2];
				level[0] = Integer.valueOf(timeStr)/(simulationTime/hehe);
				level[1] = Integer.valueOf(levelStr.substring(levelStr.length()-1,levelStr.length()));
				tempInt.add(level);
				i = temp.length() + i;
				break;
			case 'N'://node
				String subNode = getNode(node.substring(i, node.length()-1));
				subNodeInt = dealNode(subNode);
				for(int n=0; n<=subNodeInt.length-1; n++)
					tempInt.add(subNodeInt[n]);
				i = i + subNode.length();
				break;
			}
			
		}
		int[][] returnInt = new int[tempInt.size()*repeat][2];
		for(int i = 0; i<=tempInt.size()-1; i++)
			for(int j = 0; j<=repeat-1; j++)
				returnInt[i+tempInt.size()*j] = (int[])tempInt.elementAt(i);
		return returnInt;
	}
	
	private String getNode(String res)//获得node大括号内的东西
	{
		boolean flag = false;//flag为true表示已经进入node
		int start = 0;
		int length = 0;
		int k=0;
		for(int i = 0; i<=res.length()-1; i++)
		{
			if(res.charAt(i) == 'N' && flag == false)
				flag = true;
			if(flag)
			{
				length++;
				if(res.charAt(i)=='{')
				{
					k++;
					if(start == 0)
						start = i + 1;
				}
				else if(res.charAt(i)=='}')
					if(--k == 0)
						return res.substring(start, i);
			}
		}
		return "";
	}
	
	
	private void getNames()
	{
		signalName = new String[splitFile.length];
		for(int i=0; i<=signalName.length-1; i++)
			signalName[i] = (splitFile[i].split("\""))[1];
	}
	
	public String[] getSignalNames()
	{
		return signalName;
	}
	
	public Vector getSignalLevel()
	{
		return signalLevel;
	}
	public static void main(String[] args) throws IOException
	{		
	}
}
