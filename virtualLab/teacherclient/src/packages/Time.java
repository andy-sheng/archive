package packages;

import java.util.Calendar;

public class Time 
{
	public String getTime()
	{
		Calendar a = Calendar.getInstance();
		String time;
        int year = a.get(Calendar.YEAR);
        int month = a.get(Calendar.MONTH) + 1;
        int day = a.get(Calendar.DAY_OF_MONTH);
        int hour = a.get(Calendar.HOUR_OF_DAY);
        return year+"/"+month+"/"+day+"/"+hour;
	}
	public static void main(String args[])
	{
	}
}
