package com.genius;

import org.json.JSONException;
import org.json.JSONObject;

import jdbc.Customer;
import jdbc.Goods;
import jdbc.Orderlist;
import jdbc.Shopcar;
import jdbc.Store;
import jdbc.SQLFunction;
import jdbc.Vendor;

public class UpdateInformation {

	
	public static String update_VendorInfo(String jsonstr) {
		Vendor vendor = new Vendor();
		String result= null;
		try {
			JSONObject json = new JSONObject(jsonstr);
			vendor.setV_id(json.getString("vid"));
			
			String path = "vendor/pic_" + vendor.getV_id() + ".txt";
			if(PictureHandler.upload_Picture(path, json.getString("picture")).equals("fail")) {
				result = "fail";
				JSONObject json2 = new JSONObject();
				json2.put("result", result);
				return json2.toString();
			}
			
			vendor.setV_picture(path);
			vendor.setV_name(json.getString("name"));
			vendor.setV_email(json.getString("email"));
			vendor.setV_tel(json.getString("tel"));
			vendor.setV_sex(json.getString("sex"));
			vendor.setV_age(Integer.parseInt(json.getString("age")));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//在这里有个test
		testserver.infile(vendor.getV_name());
		
		if (SQLFunction.modi_vendor(vendor))
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
	
	public static String update_CustomerInfo(String jsonstr) {
		Customer customer = new Customer();
		String result= null;
		try {
			JSONObject json = new JSONObject(jsonstr);
			customer.setC_id(json.getString("cid"));
			
			String path = "customer/pic_" + customer.getC_id() + ".txt";
			if(PictureHandler.upload_Picture(path, json.getString("picture")).equals("fail")) {
				result = "fail";
				JSONObject json2 = new JSONObject();
				json2.put("result", result);
				return json2.toString();
			}
			
			customer.setC_picture(path);
			customer.setC_name(json.getString("name"));
			customer.setC_address(json.getString("address"));
			customer.setC_account(json.getString("account"));
			customer.setC_email(json.getString("email"));
			customer.setC_tel(json.getString("tel"));
			customer.setC_sex(json.getString("sex"));
			customer.setC_age(Integer.parseInt(json.getString("age")));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (SQLFunction.modi_customer(customer))
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
	
	public static String update_GoodsInfo(String jsonstr) {
		Goods goods = new Goods();
		
		String result= null;
		try {
			JSONObject json = new JSONObject(jsonstr);
			goods.setG_id(json.getString("gid"));
			
			String path = "goods/pic_" + goods.getG_id() + ".txt";
			if(PictureHandler.upload_Picture(path, json.getString("picture")).equals("fail")) {
				result = "fail";
				JSONObject json2 = new JSONObject();
				json2.put("result", result);
				return json2.toString();
			}
			
			goods.setG_picture(path);
			goods.setG_price(Double.parseDouble(json.getString("price")));
			goods.setG_name(json.getString("name"));
			goods.setG_main_class(json.getString("mainclass"));
			goods.setG_second_class(json.getString("secondclass"));
			goods.setNumber(Integer.parseInt(json.getString("number")));
			goods.setG_introduce(json.getString("introduce"));
			goods.setS_id(json.getString("sid"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		if (SQLFunction.modi_goods(goods))
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
	
	public static String update_StoreInfo(String jsonstr) {
		Store store = new Store();
		
		String result= null;
		try {
			JSONObject json = new JSONObject(jsonstr);
			store.setS_id(json.getString("sid"));
			
			String path = "store/pic_" + store.getS_id() + ".txt";
			if(PictureHandler.upload_Picture(path, json.getString("picture")).equals("fail")) {
				result = "fail";
				JSONObject json2 = new JSONObject();
				json2.put("result", result);
				return json2.toString();
			}
			
			store.setS_picture(path);
			store.setS_name(json.getString("name"));
			store.setS_address(json.getString("address"));
			store.setS_account(json.getString("account"));
			store.setS_introduce(json.getString("introduce"));
			store.setS_style(json.getString("style"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (SQLFunction.modi_store(store))
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
	
	public static String update_Orderlist(String jsonstr) {
		Orderlist orderlist = new Orderlist();
		
		String result= null;
		try {
			JSONObject json = new JSONObject(jsonstr);
			orderlist.setO_id(json.getString("oid"));
			orderlist.setO_state(json.getString("state"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (SQLFunction.modi_orderlist(orderlist))
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
	
	public static String update_Shopcar(String jsonstr) {
		Shopcar shopcar = new Shopcar();
		
		String result= null;
		try {
			JSONObject json = new JSONObject(jsonstr);
			shopcar.setG_id(json.getString("gid"));
			shopcar.setC_id(json.getString("cid"));
			shopcar.setSp_number(Integer.parseInt(json.getString("number")));	
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (SQLFunction.modi_shopcar(shopcar))
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

}
