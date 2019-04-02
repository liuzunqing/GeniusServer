package com.genius;

import org.json.JSONException;
import org.json.JSONObject;

import jdbc.Collect;
import jdbc.Goods;
import jdbc.Orderlist;
import jdbc.Shopcar;
import jdbc.SQLFunction;
import jdbc.Vip;

public class DeleteInfo {

	
	public static String delete_Goods(String jsonstr) {
		Goods goods = new Goods();
		
		String result = null;
		try {
			JSONObject json = new JSONObject(jsonstr);
			goods.setG_id(json.getString("gid"));
			// TODO Auto-generated catch block
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (SQLFunction.del_goods(goods))
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
	
	public static String delete_Orderlist(String jsonstr) {
		Orderlist orderlist = new Orderlist();
		
		String result = null;
		try {
			JSONObject json = new JSONObject(jsonstr);
			orderlist.setO_id(json.getString("oid"));
			// TODO Auto-generated catch block
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		result = SQLFunction.del_orderlist(orderlist)? "success":"fail";
		JSONObject json2 = new JSONObject();
		try {
			json2.put("result", result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json2.toString();
	}
	
	public static String delete_Shopcar(String jsonstr) {
		Shopcar shopcar = new Shopcar();
		
		String result = null;
		try {
			JSONObject json = new JSONObject(jsonstr);
			shopcar.setG_id(json.getString("gid"));
			shopcar.setC_id(json.getString("cid"));
			// TODO Auto-generated catch block
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		result = SQLFunction.del_shopcar(shopcar)? "success":"fail";
		JSONObject json2 = new JSONObject();
		try {
			json2.put("result", result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json2.toString();
	}
	
	public static String delete_Collect(String jsonstr) {
		Collect collect = new Collect();
		
		String result = null;
		try {
			JSONObject json = new JSONObject(jsonstr);
			collect.setG_id(json.getString("gid"));
			collect.setC_id(json.getString("cid"));
			collect.setS_id(json.getString("sid"));
			// TODO Auto-generated catch block
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (!collect.getG_id().equals("0")) {
			result = SQLFunction.del_collect_goods(collect)? "success":"delgoodsfail";
		} else if (!collect.getS_id().equals("0")) {
			result = SQLFunction.del_collect_store(collect)? "success":"delstorefail";
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
	
	public static String delete_Vip(String jsonstr) {
		String result = null;
		Vip vip = new Vip();
		try {
			JSONObject json = new JSONObject(jsonstr);
			vip.setC_id(json.getString("cid"));
			vip.setS_id(json.getString("sid"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		result = SQLFunction.del_vip(vip)? "success":"fail";
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
