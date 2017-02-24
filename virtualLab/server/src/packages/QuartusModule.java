package packages;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Vector;

public class QuartusModule 
{
	private String path;
	private String[] entityName;
	private SystemInfo systemInfo;
	private String compileInfo;
	private String status;
	Vector tmp;
	public void test()
	{
		System.out.print("路径"+path);
		for(int i = 0; i<=entityName.length-1; i++)
			System.out.print(entityName[i]);
		System.out.print("\n");

	}
	public QuartusModule(String bin,String[] entity,String p) throws Exception
	{
		path = p;
		int i=0;
		
		
		entityName = new String[entity.length];
		while(i < entity.length)
		{
			entityName[i] = entity[i];
			i++;
		}
	
		systemInfo = new SystemInfo();
		test();
	} 
	
	//命令行进行编译
	public String runExperiment()
	{
		System.out.println("编译");
		test();
		if(systemInfo.getQuartusPath().equals("null") )
			return "100";
		File dir = new File(path); //括号内为vhd文件所在路径
		String cmd11 = systemInfo.getQuartusPath()+"\\quartus_map ";//quartus前为quartus2文件夹及路径
		String cmd21 = systemInfo.getQuartusPath()+"\\quartus_fit ";//同上
		String cmd[] = new String[4];
		cmd[1] =  systemInfo.getQuartusPath()+"\\quartus_sta ";
		cmd[2] =  systemInfo.getQuartusPath()+"\\quartus_asm ";
		cmd[3] =  systemInfo.getQuartusPath()+"\\quartus_tan ";
		//String cmd12 = " --source="+ entityName + ".vhd --source=" + entityName + ".vwf --family=\"MAX II\"";
		String cmd12 = " --family=\"MAX II\"";
		String cmd22 = " --part=EPM2210F324C5";
		String reply_cmd = "\n"; //命令的反馈信息
		boolean result0 = false;//回传的编译结果 true为成功，false为失败
		boolean result1 = false, result2 = false;//每条指令编译的结果
		boolean result[] = new boolean[4];//同上

		for (int i=1; i<4; i++)
		{
			result[i] = false;
		}
 		try
		{
 			Process proc1 = Runtime.getRuntime().exec(cmd11 + entityName[0] + cmd12, null, dir);
			InputStream stderr1 = proc1.getInputStream();
			InputStreamReader isr1 = new InputStreamReader(stderr1);
			BufferedReader br1 = new BufferedReader(isr1);
			String readLine1 = br1.readLine();
			while(readLine1 != null)
			{
				if (readLine1.indexOf("0 errors") != -1)
					result1 = true;
				if ((readLine1.indexOf("error") != -1) | (readLine1.indexOf("Warning") != -1))
					reply_cmd = reply_cmd + readLine1 + "\n";
				readLine1 = br1.readLine();
			}
			proc1.waitFor();
			int j=0;
			while(j < entityName.length)
			{
	 			Process procvhd = Runtime.getRuntime().exec(cmd11 + entityName[0] + " --source=" + entityName[j] + ".vhd", null, dir);
				InputStream stderrvhd = procvhd.getInputStream();
				InputStreamReader isrvhd = new InputStreamReader(stderrvhd);
				BufferedReader brvhd = new BufferedReader(isrvhd);
				String readLinevhd = brvhd.readLine();
				while(readLinevhd != null)
				{
					if (readLinevhd.indexOf("0 errors") != -1)
						result1 &= true;
					if ((readLinevhd.indexOf("error") != -1) | (readLinevhd.indexOf("Warning") != -1))
						reply_cmd = reply_cmd + readLine1 + "\n";
					readLinevhd = brvhd.readLine();
				}
				procvhd.waitFor();
				j++;
			}
			Process proc2 = Runtime.getRuntime().exec(cmd21 + entityName[0] + cmd22, null, dir);
			InputStream stderr2 = proc2.getInputStream();
			InputStreamReader isr2 = new InputStreamReader(stderr2);
			BufferedReader br2 = new BufferedReader(isr2);
			String readLine2 = br2.readLine();
			while(readLine2 != null)
			{
				if (readLine2.indexOf("0 errors") != -1)
					result2 = true;
				if ((readLine2.indexOf("error") != -1) | (readLine2.indexOf("Warning") != -1))
					reply_cmd = reply_cmd + readLine2 + "\n";
				readLine2 = br2.readLine();
			}
			proc2.waitFor();
			for(int i=1; i<4; i++)
			{
				Process proc = Runtime.getRuntime().exec(cmd[i] + entityName[0], null, dir);
				InputStream stderr = proc.getInputStream();
				InputStreamReader isr = new InputStreamReader(stderr);
				BufferedReader br = new BufferedReader(isr);
				String readLine = br.readLine();
				while(readLine != null)
				{
					if (readLine.indexOf("0 errors") != -1)
						result[i] = true;
					if ((readLine.indexOf("error") != -1) | (readLine.indexOf("Warning") != -1))
						reply_cmd = reply_cmd + readLine + "\n";
					readLine = br.readLine();
				}
				proc.waitFor();
			}
			result0 = result1 & result2 & result[1] & result[2] & result[3];
			
			compileInfo = reply_cmd;
			test();
			if(result0 == false){
				status = "编译失败";
				return "102";
			}
			else{
				status = "编译成功"; 
				return "101";			
			}
		} catch (Throwable t)
		{
			t.printStackTrace();
			return "102";
		}
 		
	}

	//利用命令行进行仿真
	public String Simulation()
	{
		System.out.println("编译");
		test();
		if(systemInfo.getQuartusPath().equals("null") )
			return "100";
		File dir = new File(path); //括号内为vhd文件所在路径
		String cmd11 = systemInfo.getQuartusPath()+"\\quartus_map ";
		String cmd31 = systemInfo.getQuartusPath()+"\\quartus_sim ";
		String cmd32 = " --simulation_results_format=vwf";
		String reply_simulation = "\n"; //命令的反馈信息
		boolean result0 = false;
		boolean result1 = false, result3 = false;//每条指令编译的结果
 		try
		{
			Process procvwf = Runtime.getRuntime().exec(cmd11 + entityName[0] + "--source=" + entityName[0] + ".vwf", null, dir);
			InputStream stderrvwf = procvwf.getInputStream();
			InputStreamReader isrvwf = new InputStreamReader(stderrvwf);
			BufferedReader brvwf = new BufferedReader(isrvwf);
			String readLinevwf = brvwf.readLine();
			while(readLinevwf != null)
			{
				if (readLinevwf.indexOf("0 errors") != -1)
					result1 &= true;
				if ((readLinevwf.indexOf("error") != -1) | (readLinevwf.indexOf("Warning") != -1))
					reply_simulation = reply_simulation + readLinevwf + "\n";
				readLinevwf = brvwf.readLine();
			}
			procvwf.waitFor();
			Process proc3 = Runtime.getRuntime().exec(cmd31 + entityName[0] + cmd32, null, dir);
			InputStream stderr3 = proc3.getInputStream();
			InputStreamReader isr3 = new InputStreamReader(stderr3);
			BufferedReader br3 = new BufferedReader(isr3);
			String readLine3 = br3.readLine();
			while(readLine3 != null)
			{
				if (readLine3.indexOf("0 errors") != -1)
					result3 = true;
				if ((readLine3.indexOf("error") != -1) | (readLine3.indexOf("Warning") != -1))
					reply_simulation = reply_simulation + readLine3 + "\n";
				readLine3 = br3.readLine();
			}
			proc3.waitFor();
			result0 = result1 & result3;
			
			compileInfo = reply_simulation;
			test();
			System.out.println("仿真完成");
            return "101";
		} catch (Throwable t)
		{
			t.printStackTrace();
			return "102";
		}
 		
	}
	
	//添加分频文件并下载
	public String Download()
	{
		System.out.println("下载");
		test();
		if(systemInfo.getQuartusPath().equals("null") )
			return "100";
		File dir = new File(path); //括号内为vhd文件所在路径
		String cmd_download = "quartus_pgm -c \"USB-Blaster [USB-0]\" -m JTAG -o p;";
		String s = "\r\n";
		try
		{
			//修改设置文件添加pin脚
			try{
				FileInputStream fis = new FileInputStream(dir + "\\" + entityName[0] +".txt");
				byte[]b = new byte[1024];
				while(true){
					int i = fis.read(b);
					if(i==-1) break;
					s = s + new String(b, 0, i);
				}
				fis.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			s = s.replaceAll("str","line");
			
			try{
				FileOutputStream fos = new FileOutputStream(dir + "\\" + entityName[0] +".qsf", true);
				fos.write(s.getBytes());
				fos.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
			//修改主VHD文件添加sep接口
			String oldStr = "architecture behav of "+ entityName[0] + " is";
			String newStr1 = "component sep is\r\nport(\r\nclk_in: in std_logic;\r\n	clk_out: out std_logic\r\n);\r\nend component;\r\nSIGNAL clkd: std_logic;";
			String newStr2 = "u1: sep port map(clk_in=>clk, clk_out=>clkd);";
			addTxtByStr(oldStr, newStr1, newStr2, dir + "\\" + entityName[0] + ".vhd");
			
			//编译
			try{
				Process procvhd = Runtime.getRuntime().exec("quartus_map " + entityName[0] + " --source=sep.vhd", null, dir);
				InputStream stderrvhd = procvhd.getInputStream();
				InputStreamReader isrvhd = new InputStreamReader(stderrvhd);
				BufferedReader brvhd = new BufferedReader(isrvhd);
				String readLinevhd = brvhd.readLine();
				procvhd.waitFor();
				
				Process proc4 = Runtime.getRuntime().exec("quartus_map --read_settings_files=on --write_settings_files=off " + entityName[0] + " -c " + entityName[0], null, dir);
				InputStream stderr4 = proc4.getInputStream();
				InputStreamReader isr4 = new InputStreamReader(stderr4);
				BufferedReader br4 = new BufferedReader(isr4);
				String readLine4 = br4.readLine();
				proc4.waitFor();
				System.out.println("编译4");

				Process proc5 = Runtime.getRuntime().exec("quartus_fit --read_settings_files=off --write_settings_files=off " + entityName[0] + " -c " + entityName[0], null, dir);
				InputStream stderr5 = proc5.getInputStream();
				InputStreamReader isr5 = new InputStreamReader(stderr5);
				BufferedReader br5 = new BufferedReader(isr5);
				String readLine5 = br5.readLine();
				proc5.waitFor();
				System.out.println("编译5");
				
				Process proc6 = Runtime.getRuntime().exec("quartus_asm --read_settings_files=off --write_settings_files=off " + entityName[0] + " -c " + entityName[0], null, dir);
				InputStream stderr6 = proc6.getInputStream();
				InputStreamReader isr6 = new InputStreamReader(stderr6);
				BufferedReader br6 = new BufferedReader(isr6);
				String readLine6 = br6.readLine();
				proc6.waitFor();
				System.out.println("编译6");
				
				Process proc7 = Runtime.getRuntime().exec("quartus_tan --read_settings_files=off --write_settings_files=off " + entityName[0] + " -c " + entityName[0], null, dir);
				InputStream stderr7 = proc7.getInputStream();
				InputStreamReader isr7 = new InputStreamReader(stderr7);
				BufferedReader br7 = new BufferedReader(isr7);
				String readLine7 = br7.readLine();
				proc7.waitFor();
				System.out.println("编译7");
			}catch(Exception e){
				e.printStackTrace();
			}
			
			//下载
			try{
				Process procd = Runtime.getRuntime().exec(cmd_download + entityName[0] + ".pof", null, dir);
				InputStream stderrd = procd.getInputStream();
				InputStreamReader isrd = new InputStreamReader(stderrd);
				BufferedReader brd = new BufferedReader(isrd);
				String readLined = brd.readLine();
				procd.waitFor();
				System.out.println("下载完成");
			}catch(Exception e){
				e.printStackTrace();
			}
			return "101";
		} catch (Throwable t)
		{
			t.printStackTrace();
			return "102";
		}
	}
	
	public static void addTxtByStr(String oldStr, String newStr1, String newStr2, String path)
	{
		String temp = "";
		try
		{
			File fr = new File(path);
			FileInputStream fis = new FileInputStream(fr);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			StringBuffer buf = new StringBuffer();

			while((temp = br.readLine()) != null && !temp.equals(oldStr))
			{
				buf = buf.append(temp);
				buf = buf.append(System.getProperty("line.separator"));
			}
			
			buf = buf.append(oldStr+"\r\n");
			buf = buf.append(newStr1);
			
			while((temp = br.readLine()) != null && !temp.equals("begin"))
			{
				buf = buf.append(temp);
				buf = buf.append(System.getProperty("line.separator"));
			}
			
			buf = buf.append("begin\r\n");
			buf = buf.append(newStr2);
			
			while((temp=br.readLine()) != null)
			{
				temp = temp.replaceAll("clk", "clkd");
				buf = buf.append(System.getProperty("line.separator"));
				buf = buf.append(temp);
			}

			br.close();
			FileOutputStream fos = new FileOutputStream(fr);
			PrintWriter pw = new PrintWriter(fos);
			pw.write(buf.toString().toCharArray());
			pw.flush();
			pw.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public String getCompileInfo()
	{
		return compileInfo;
	}
	
	public String getStatus()
	{
		return status;
	}
	public static void main(String[] args) throws Exception
	{

	}
 }
