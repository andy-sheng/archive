package packages;

public class SystemInfo 
{
	private String quartusPath;
	public SystemInfo() throws Exception
	{
		MyFileReader file = new MyFileReader("systemInfo.ini");
		String[] info = file.readLines();
		for(int i = 0; i<=info.length - 1; i++)
		{
			String[] temp = info[i].split("=");
			if(temp[0].equals("[QuartusII Path]"))
				quartusPath = temp[1];
			else if(temp[0].equals(""))
			{
				
			}
			else
			{
				
			}
		}
	}
	public void changQuartusPath(String newPath) throws Exception
	{
		quartusPath = newPath;
		MyFileWriter outFile = new MyFileWriter("systemInfo.ini");
		String[] outData = new String[1];
		outData[0] = "[QuartusII Path]=" + quartusPath;
		outFile.writeLines(outData);
	}
	public String getQuartusPath()
	{
		return quartusPath;
	}
}
