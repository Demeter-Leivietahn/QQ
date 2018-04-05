package Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ReadFriend {
	public static String[] rf(String filepath) throws IOException {
		String[] str = null;
		File file=new File(filepath);
		if(!file.exists()){
			file.createNewFile();
		}
		byte[] by=new byte[8*1024];
		InputStream in=new FileInputStream(file);
		in.read(by, 0, by.length);
		String s=new String(by);
		
		str=s.split(" ");
//		for(int i=0;i<str.length;i++){
//			System.out.println(str[i]);
//		}
		return str;
	}
//	public static void main(String[] args) throws IOException {
//		ReadFriend.rf(".//information//625979754");
//	}
}
