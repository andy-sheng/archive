package packages;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;
import packages.*;
public class Judgement {
	private static Vector signalLevel0;//ԭ��vector
	private static Vector signalLevel1;//���ɵ�vector
	private static String[] signalName0;//ԭ�е�String[]
	private static String[] signalName1;//���ɵ�String[]
	private static boolean S,V,ans;//x �ж�String[]��� y�ж�Vector�Ľ�� ans�ܵĽ��
	
	public  Judgement(String a,String b) throws IOException
	{	
		AnalyseFile waveA = new AnalyseFile(a);
		AnalyseFile waveB = new AnalyseFile(b);
		signalLevel0 = waveA.getSignalLevel();
		signalLevel1 = waveB.getSignalLevel();
		
	}
	public static boolean compareString()
	{	
		boolean x = true;
		if (signalName0 != null && signalName1 != null) {
			if (signalName0.length == signalName1.length) 
			{
				int i;
				for(i=0;i<signalName0.length;i++)
				{
					if(!signalName0[i].equals(signalName1[i]))
					{
						x=false;
						break;
					}
				}
			}
			else
				System.out.println("String[]���Ȳ��ȡ�");
		}
		else 
			System.out.println("���ڿ�String[]");
		return x;
		}
	
	
	public static boolean compareVector()
	{
		if((signalLevel0.capacity()!=0)&&(signalLevel1.capacity()!=0))
		{
		if(signalLevel0.capacity()==signalLevel1.capacity())
		{
			
			int lenth=signalLevel0.size();
			for(int i=0;i<lenth;i++)
			{
				int[][] temp1=(int[][])signalLevel0.elementAt(i);
				int[][] temp2=(int[][])signalLevel1.elementAt(i);
				if(temp1.length!=temp2.length)
				{
					return false;
				}
				else
				{
					for(int j=0;j<temp1.length;j++)
					{
						if(temp1[j].length!=temp2[j].length)
							return false;
						else
						{
							if(!Arrays.equals(temp1[j],temp2[j]))
								return false;
						}
							
					}
				}
		
			}
		}
		else
			{
			System.out.println("Vector���Ȳ���");
			return false;
			}
		}
		else
			{
			System.out.println("���ڿ�Vector");
			return false;
			}
		return true;

	}

	
		
}
