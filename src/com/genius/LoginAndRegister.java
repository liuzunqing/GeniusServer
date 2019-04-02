package com.genius;

import org.json.JSONException;
import org.json.JSONObject;

import jdbc.SQLFunction;
import jdbc.User;

public class LoginAndRegister {
	
	public static String do_Login(String jsonstr) {
		User user = new User();
		String result= null;
		try {
			JSONObject json = new JSONObject(jsonstr);
			user.setId(json.getString("id"));
			user.setPassword(json.getString("password"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		result = SQLFunction.login(user);
		switch(result) {
		case "1"://flag为1表示卖家登录
			result = "vendor";
			break;
		case "2"://flag为2表示买家登录
			result = "customer";
			break;
		default:
			result = "fail";
			break;
		}
		JSONObject json2 = new JSONObject();
		try {
			json2.put("result", result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json2.toString();
	}

	public static String do_Register(String jsonstr) {
		User user = new User();
		String result= null;
		try {
			JSONObject json = new JSONObject(jsonstr);
			user.setId(json.getString("id"));
			user.setPassword(json.getString("password"));
			user.setFlag(Integer.parseInt(json.getString("flag")));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (SQLFunction.register(user))
			result = "success";
		else
			result = "fail";
		
		JSONObject json2 = new JSONObject();
		try {
			json2.put("result", result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json2.toString();
	}
	
	public static String do_CheckRegister(String jsonstr) {
		User user = new User();
		String result= null;
		try {
			JSONObject json = new JSONObject(jsonstr);
			user.setId(json.getString("id"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (SQLFunction.check_register(user))
			result = "fail";
		else
			result = "success";//账号不存在时返回为success，表示可以创建该账号
		JSONObject json2 = new JSONObject();
		try {
			json2.put("result", result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json2.toString();
	}
	
	public static String do_ChangePassword(String jsonstr) {
		String result = null;
		String oldpassword = null;
		User user = new User();
		try {
			JSONObject json = new JSONObject(jsonstr);
			user.setId(json.getString("id"));
			user.setPassword(json.getString("newpassword"));
			oldpassword = json.getString("oldpassword");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(SQLFunction.check_password(user, oldpassword)) {
			result = SQLFunction.modi_password(user)? "success":"fail";
		} else {
			result = "fail";
		}
		JSONObject json2 = new JSONObject();
		try {
			json2.put("result", result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json2.toString();
	}
	
	public static String do_ForgetPassword(String jsonstr) {
		String result = null;
		User user = new User();
		try {
			JSONObject json = new JSONObject(jsonstr);
			user.setId(json.getString("id"));
			user.setPassword(json.getString("password"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		result = SQLFunction.modi_password(user)? "success":"fail";
		
		JSONObject json2 = new JSONObject();
		try {
			json2.put("result", result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json2.toString();
	}
}
