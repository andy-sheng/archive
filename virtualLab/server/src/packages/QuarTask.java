package packages;
import java.util.Vector;

import packages.*;


public class QuarTask 
{
	private QuartusModule quartus;
	private String[] expInfo;
	private HostSocket server;
	private String account;
	private DatabaseModule db;
	private String entity;
	private Vector entitys;
	private boolean needBtn;
	private String step;//120 ±àÒë ·ÂÕæ ÏÂÔØ    121 ±àÒë·ÂÕæ   122±àÒëÏÂÔØ
	public void setNeedBt(boolean btn)
	{
		needBtn = btn;
	}
	public boolean getNeedBt()
	{
		return needBtn;
	}
	public void setStep(String s)
	{
		step = s;
	}
	public String getStep()
	{
		return step;
	}
	public void setEntitys(Vector e)
	{
		entitys = new Vector();
		for(int i = 0; i<=e.size()-1; i++)
			entitys.addElement(e.elementAt(i));
	}
	public String getEntitys()
	{
		for(int i = 0; i<=entitys.size()-1; i++){
			System.out.println((String)entitys.elementAt(i));
			
			}return "";
	}
	public void setEntity(String e)
	{
		entity = e;
	}
	public void setDb(DatabaseModule d)
	{
		db = d;
	}
	public void setExpInfo(String[] e)
	{
		expInfo = e;
	}
	public void setAccount(String a)
	{
		account = a;
	}
	public void setQuart(QuartusModule q)
	{
		quartus = q;
	}
	public void setSocket(HostSocket h)
	{
		server = h;
	}
	public String getEntity()
	{
		return entity;
	}
	public DatabaseModule getDb()
	{
		return db;
	}
	public String[]  getExpInfo()
	{
		return expInfo;
	}
	public String getAccount()
	{
		return account;
	}
	public QuartusModule getQuart()
	{
		return quartus;
	}
	public HostSocket getSocket()
	{
		return server;
	}
}

