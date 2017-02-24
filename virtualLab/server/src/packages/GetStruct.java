package packages;
import packages.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GetStruct {
	String inputpath,outputpath;
    public GetStruct(String Inputpath,String Outputpath) throws Exception{
    	inputpath = Inputpath;
    	outputpath = Outputpath;
    	String architecturename;
    	int i =0;
    	String[] a =new MyFileReader(inputpath).readLines();
    	String[] b = null;
    	for(;i < a.length;i++){
    		if(a[i].indexOf("architecture")>-1){
    			b = new String[i+12];
    			for(int j =0;j<=i;j++){
    	    		b[j] = a[j];  //找到architecture XX of XX is 之前的语句并赋给b[]
    	    	}
    			architecturename = a[i].substring(a[i].indexOf("architecture")+13, a[i].indexOf(" of"));
    			b[i+1] = "\r\n";
    			b[i+2] = "\r\n";
    			b[i+3] = "\r\n";
    			b[i+4] = "\r\n";
    			b[i+5] = "begin";
    			b[i+6] = "\r\n";
    			b[i+7] = "\r\n";
    			b[i+8] = "\r\n";
    			b[i+9] = "\r\n";
    			b[i+10] = "\r\n";
    			b[i+11] = "end "+architecturename+";";
       			break; 
    		}
    	}
    	new MyFileWriter(outputpath).writeLines(b);
    }


}

