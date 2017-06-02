package packages;

import java.util.Vector;
import java.util.concurrent.Semaphore;

public class DnsCache
{
	private Vector dnsCache;
	Semaphore lock;
	public DnsCache()
	{
		lock = new Semaphore(1);
	}
	public String searchCache(String domin)
	{
		for(int i = 0; i<=dnsCache.size()-1; i++)
		{
			String[] tmp = (String[])dnsCache.elementAt(i);
			if(domin.equals(tmp[0])){//Уќжа
				dnsCache.remove(i);//RU
				dnsCache.insertElementAt(tmp, 0);
				return tmp[1];
			}
		}
		return null;
	}
	public void insert(String[] record)
	{
		try {
			lock.acquire();
			if(dnsCache.size() == 1000)
				dnsCache.remove(999);
			dnsCache.insertElementAt(record, 0);
			lock.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
