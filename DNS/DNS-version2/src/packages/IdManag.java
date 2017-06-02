package packages;

import java.net.InetAddress;
import java.util.Vector;

public class IdManag 
{
	private Vector record;//一共4数据 第一个是oldID 第二个是newid 第二个是PORT 第三个是地址
	private int high;
	private int low;
	public IdManag()
	{
		high = -127;
		low = -127;
		record = new Vector();
	}
	public Vector getOldInfo(byte[] newId)//一共3数据 第一个是ID 第二个是PORT 第三个是地址
	{
		for(int i = 0; i<=record.size()-1; i = i + 4){
			if(newId[0] == ((byte[])record.elementAt(i+1))[0] && newId[1] == ((byte[])record.elementAt(i+1))[1]){
				Vector tmp = new Vector();
				byte[] oldId =new byte[2];
				oldId[0] = ((byte[])record.elementAt(i))[0];
				oldId[1] = ((byte[])record.elementAt(i))[1];
				tmp.add(record.elementAt(i));
				tmp.add(record.elementAt(i+2));
				tmp.add(record.elementAt(i+3));
				record.remove(i);
				record.remove(i);
				record.remove(i);
				record.remove(i);
				return tmp;
			}
				
		}
		return null;
	}
	public byte[] getNewId(byte[] oldId,int port,InetAddress addr)
	{
		byte[] newId= new byte[2];
		newId[0] =(byte)high;
		newId[1] =  (byte)low++;
		if(low == 128){
			high++;
			low = -127;
		}
		if(high == 128)
			high = -127;
		record.add(oldId);
		record.add(newId);
		record.add(port);
		record.add(addr);
		return newId;
	}
}
