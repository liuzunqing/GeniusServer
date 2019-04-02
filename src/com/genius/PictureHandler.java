package com.genius;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PictureHandler {

	public static String upload_Picture(String path, String str) {
		File file = new File("/root/Genius/" + path);
		try {
			FileOutputStream fop = new FileOutputStream(file);
			if (!file.exists()) {
				file.createNewFile();
			}
			byte[] contentInBytes = str.getBytes();
			fop.write(contentInBytes);
			fop.flush();
			fop.close();
			return "success";
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "fail";
	}
	
	public static String download_Picture(String path) {
		String result = null;
		File file = new File("/root/Genius/" + path);
		int len = 0;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		try {
			InputStream in = new FileInputStream(file);
			while((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);	
			}
			in.close();
			out.close();
			result = new String(out.toByteArray());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
