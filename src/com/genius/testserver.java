package com.genius;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

public class testserver {

	public static String test(String jsonstr) {
		String result = null;
		System.out.println("ÊÕµ½µÄ×Ö·û´®£º" + jsonstr);
		try {
			JSONObject json = new JSONObject(jsonstr);
			String test = json.getString("test");
			if(test.equals("ºº×Ö"))
				result = "ºº×Ö";
			else
				result = "notºº×Ö";
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static void infile(String str) {
		File file = new File("/root/tmp/test.txt");
		try {
			FileOutputStream fop = new FileOutputStream(file);
			if (!file.exists()) {
				file.createNewFile();
			}
			byte[] contentInBytes = str.getBytes();
			fop.write(contentInBytes);
			fop.flush();
			fop.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
