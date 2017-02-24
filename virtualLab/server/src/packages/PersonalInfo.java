package packages;

public class PersonalInfo
{
	private String name;
	private String clas;
	private String picture;
	private String password;
	private String number;
	private String[] experiments;
	public String getNumber()
	{
		return number;
	}
	public String getName()
	{
		return name;
	}
	public String getClas()
	{
		return clas;
	}
	public String getPicture()
	{
		return picture;
	}
	public String[] getExperiments()
	{
		return experiments;
	}
	public String getPassword()
	{
		return password;
	}
	public void changeName(String n)
	{
		name = n;
	}
	public void changeClas(String c)
	{
		clas = c;
	}
	public void changePicture(String p)
	{
		picture = p;
	}
	public void changePassword(String p)
	{
		password = p;
	}
	public void changeNumber(String n)
	{
		number = n;
	}
}
