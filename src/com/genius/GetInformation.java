package com.genius;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import jdbc.Vendor;
import jdbc.Vip;
import jdbc.Collect;
import jdbc.Customer;
import jdbc.Goods;
import jdbc.Orderlist;
import jdbc.Shopcar;
import jdbc.Store;
import jdbc.SQLFunction;

public class GetInformation {

	public static String get_VendorInfo(String jsonstr) {
		Vendor vendor = new Vendor();
		String result = null;
		try {
			JSONObject json = new JSONObject(jsonstr);
			vendor.setV_id(json.getString("vid"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//数据库查询，根据vid查询
		Vendor newvendor = new Vendor();
		newvendor = SQLFunction.view_vendor(vendor);
		if (newvendor == null) {
			result = "fail";
		} else {
			result = "success";
		}
		
		JSONObject json2 = new JSONObject();
		try {
			json2.put("result", result);
			if(result.equals("fail")) {
				return json2.toString();
			}
			json2.put("name", newvendor.getV_name());
			json2.put("sex", newvendor.getV_sex());
			json2.put("email", newvendor.getV_email());
			json2.put("tel", newvendor.getV_tel());
			json2.put("age", newvendor.getV_age());
			json2.put("picture", PictureHandler.download_Picture(newvendor.getV_picture()));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return json2.toString();
	}
	
	public static String get_CustomerInfo(String jsonstr) {
		Customer customer = new Customer();
		String result = null;
		try {
			JSONObject json = new JSONObject(jsonstr);
			customer.setC_id(json.getString("cid"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
		}
		
		//数据库查询，根据cid查询
		Customer newcustomer = new Customer();
		newcustomer = SQLFunction.view_customer(customer);
		
		if (newcustomer == null) {
			result = "fail";
		} else {
			result = "success";
		}
		
		JSONObject json2 = new JSONObject();
		try {
			json2.put("result", result);
			if(result.equals("fail")) {
				return json2.toString();
			}
			json2.put("name", newcustomer.getC_name());
			json2.put("sex", newcustomer.getC_sex());
			json2.put("email", newcustomer.getC_email());
			json2.put("tel", newcustomer.getC_tel());
			json2.put("age", newcustomer.getC_age());
			json2.put("address", newcustomer.getC_address());
			json2.put("account", newcustomer.getC_account());
			json2.put("picture", PictureHandler.download_Picture(newcustomer.getC_picture()));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return json2.toString();
	}
	
	public static String get_GoodsInfo(String jsonstr) {
		Goods goods = new Goods();
		String result = null;
		try {
			JSONObject json = new JSONObject(jsonstr);
			goods.setG_id(json.getString("gid"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//数据库查询，根据gid查询
		Goods newgoods = new Goods();
		newgoods = SQLFunction.view_goods(goods);
		
		if (newgoods == null) {
			result = "fail";
		} else {
			result = "success";
		}
		
		JSONObject json2 = new JSONObject();
		try {
			json2.put("result", result);
			if(result.equals("fail")) {
				return json2.toString();
			}
			json2.put("gid", newgoods.getG_id());
			json2.put("vid", newgoods.getV_id());
			json2.put("sid", newgoods.getS_id());
			json2.put("name", newgoods.getG_name());
			json2.put("introduce", newgoods.getG_introduce());
			json2.put("price", newgoods.getG_price());
			json2.put("mainclass", newgoods.getG_main_class());
			json2.put("secondclass", newgoods.getG_second_class());
			json2.put("number", newgoods.getNumber());
			json2.put("picture", PictureHandler.download_Picture(newgoods.getG_picture()));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return json2.toString();
	}
	
	public static String get_StoreInfo(String jsonstr) {
		Store store = new Store();
		String result = null;
		try {
			JSONObject json = new JSONObject(jsonstr);
			store.setS_id(json.getString("sid"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//数据库查询，根据sid查询
		Store newstore = new Store();
		newstore = SQLFunction.view_store(store);
		if (newstore == null) {
			result = "fail";
		} else {
			result = "success";
		}
		
		JSONObject json2 = new JSONObject();
		try {
			json2.put("result", result);
			if(result.equals("fail")) {
				return json2.toString();
			}
			json2.put("name", newstore.getS_name());
			json2.put("style", newstore.getS_style());
			json2.put("address", newstore.getS_address());
			json2.put("account", newstore.getS_account());
			json2.put("introduce", newstore.getS_introduce());
			json2.put("vid", newstore.getV_id());
			json2.put("sid", newstore.getS_id());
			json2.put("picture", PictureHandler.download_Picture(newstore.getS_picture()));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return json2.toString();
	}
	
	public static String get_Orderlist(String jsonstr) {
		String result = null;
		Orderlist orderlist = new Orderlist();
		try {
			JSONObject json = new JSONObject(jsonstr);
			orderlist.setO_id(json.getString("oid"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//数据库查询，根据oid查询
		Orderlist neworderlist = new Orderlist();
		neworderlist = SQLFunction.view_orderlist(orderlist);
		if (neworderlist == null) {
			result = "fail";
		} else {
			result = "success";
		}
		
		JSONObject json2 = new JSONObject();
		try {
			json2.put("result", result);
			if(result.equals("fail")) {
				return json2.toString();
			}
			json2.put("cid", neworderlist.getC_id());
			json2.put("vid", neworderlist.getV_id());
			json2.put("time", neworderlist.getO_time());
			json2.put("name", neworderlist.getG_name());
			json2.put("state", neworderlist.getO_state());
			json2.put("price", neworderlist.getG_price());
			json2.put("number", neworderlist.getNumber());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return json2.toString();
	}
	
	public static String get_Goodsid(String jsonstr) {
		String result = null;
		Goods goods = new Goods();
		try {
			JSONObject json = new JSONObject(jsonstr);
			goods.setG_name(json.getString("name"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Goods> list=new ArrayList<Goods>();
		list = SQLFunction.search_goods(goods);
		if(list == null) {
			result = "fail";
		} else {
			result = "success";
		}
		JSONObject json2 = new JSONObject();
		try {
			json2.put("result", result);
			if(result.equals("fail")) {
				return json2.toString();
			}
			int i = 0;
			Goods goods2 = new Goods();
			while(i < list.size()) {
				goods2 = list.get(i);
				json2.put("" + i, goods2.getG_id());
				i++;
			}
			json2.put("number", "" + list.size());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return json2.toString();
	}
	
	public static String get_Orderlistcid(String jsonstr) {
		String result = null;
		Orderlist orderlist = new Orderlist();
		try {
			JSONObject json = new JSONObject(jsonstr);
			orderlist.setC_id(json.getString("cid"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Orderlist> list = new ArrayList<Orderlist>();
		list = SQLFunction.search_orderlist_c(orderlist);
		if(list == null) {
			result = "fail";
		} else {
			result = "success";
		}
		JSONObject json2 = new JSONObject();
		try {
			json2.put("result", result);
			if(result.equals("fail")) {
				return json2.toString();
			}
			int i = 0;
			Orderlist orderlist2 = new Orderlist();
			while(i < list.size()) {
				orderlist2 = list.get(i);
				json2.put("" + i, orderlist2.getO_id());
				i++;
			}
			json2.put("number", "" + list.size());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return json2.toString();
	}
	
	public static String get_Orderlistvid(String jsonstr) {
		String result = null;
		Orderlist orderlist = new Orderlist();
		try {
			JSONObject json = new JSONObject(jsonstr);
			orderlist.setV_id(json.getString("vid"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Orderlist> list = new ArrayList<Orderlist>();
		list = SQLFunction.search_orderlist_v(orderlist);
		if(list == null) {
			result = "fail";
		} else {
			result = "success";
		}
		JSONObject json2 = new JSONObject();
		try {
			json2.put("result", result);
			if(result.equals("fail")) {
				return json2.toString();
			}
			int i = 0;
			Orderlist orderlist2 = new Orderlist();
			while(i < list.size()) {
				orderlist2 = list.get(i);
				json2.put("" + i, orderlist2.getO_id());
				i++;
			}
			json2.put("number", "" + list.size());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return json2.toString();
	}
	
	public static String get_Collect(String jsonstr) {
		String result = null;
		Collect collect = new Collect();
		try {
			JSONObject json = new JSONObject(jsonstr);
			collect.setC_id(json.getString("cid"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Collect> list = new ArrayList<Collect>();
		list = SQLFunction.search_collect(collect);
		if(list == null) {
			result = "fail";
		} else {
			result = "success";
		}
		JSONObject json2 = new JSONObject();
		try {
			json2.put("result", result);
			if(result.equals("fail")) {
				return json2.toString();
			}
			int i = 0;
			int s = 0;
			int g = 0;
			Collect collect2 = new Collect();
			while(i < list.size()) {
				collect2 = list.get(i);
				if(collect2.getS_id() != null) {
					json2.put("S" + s, collect2.getS_id());
					s++;
				} else if(collect2.getG_id() != null) {
					json2.put("G" + g, collect2.getG_id());
					g++;
				}
				i++;
			}
			json2.put("Snumber", "" + s);
			json2.put("Gnumber", "" + g);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return json2.toString();
	}
	
	public static String get_Shopcar(String jsonstr) {
		String result = null;
		Shopcar shopcar = new Shopcar();
		try {
			JSONObject json = new JSONObject(jsonstr);
			shopcar.setC_id(json.getString("cid"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Shopcar> list = new ArrayList<Shopcar>();
		list = SQLFunction.search_shopcar(shopcar);
		if(list == null) {
			result = "fail";
		} else {
			result = "success";
		}
		JSONObject json2 = new JSONObject();
		try {
			json2.put("result", result);
			if(result.equals("fail")) {
				return json2.toString();
			}
			int i = 0;
			Shopcar shopcar2 = new Shopcar();
			while(i < list.size()) {
				shopcar2 = list.get(i);
				json2.put("gid" + i, shopcar2.getG_id());
				json2.put("num" + i, shopcar2.getSp_number());
				i++;
			}
			json2.put("number", list.size());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return json2.toString();
	}
	
	public static String get_Vip(String jsonstr) {
		String result = null;
		Vip vip = new Vip();
		try {
			JSONObject json = new JSONObject(jsonstr);
			vip.setS_id(json.getString("sid"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Vip> list = new ArrayList<Vip>();
		list = SQLFunction.search_vip(vip);
		if(list == null) {
			result = "fail";
		} else {
			result = "success";
		}
		JSONObject json2 = new JSONObject();
		try {
			json2.put("result", result);
			if(result.equals("fail")) {
				return json2.toString();
			}
			int i = 0;
			Vip newvip = new Vip();
			while(i < list.size()) {
				newvip = list.get(i);
				json2.put("" + i, newvip.getC_id());
				i++;
			}
			json2.put("number", "" + list.size());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return json2.toString();
	}

	public static String get_AllGoods(String jsonstr) {
		String result = null;
		Goods goods = new Goods();
		try {
			JSONObject json = new JSONObject(jsonstr);
			goods.setS_id(json.getString("sid"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Goods> list=new ArrayList<Goods>();
		list = SQLFunction.search_goodsinstore(goods);
		if(list == null) {
			result = "fail";
		} else {
			result = "success";
		}
		JSONObject json2 = new JSONObject();
		try {
			json2.put("result", result);
			if(result.equals("fail")) {
				return json2.toString();
			}
			int i = 0;
			Goods goods2 = new Goods();
			while(i < list.size()) {
				goods2 = list.get(i);
				json2.put("" + i, goods2.getG_id());
				i++;
			}
			json2.put("number", "" + list.size());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return json2.toString();
	}
	
	public static String get_GoodsClass(String jsonstr) {
		String result = null;
		Goods goods = new Goods();
		try {
			JSONObject json = new JSONObject(jsonstr);
			goods.setG_main_class(json.getString("mainclass"));
			goods.setG_second_class(json.getString("secondclass"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Goods> list=new ArrayList<Goods>();
		if(goods.getG_second_class().equals("ALL")) {
			list = SQLFunction.search_goodswithmainclass(goods);
		} else {
			list = SQLFunction.search_goodswithsecondclass(goods);
		}
		
		if(list == null) {
			result = "fail";
		} else {
			result = "success";
		}
		JSONObject json2 = new JSONObject();
		try {
			json2.put("result", result);
			if(result.equals("fail")) {
				return json2.toString();
			}
			int i = 0;
			Goods goods2 = new Goods();
			while(i < list.size()) {
				goods2 = list.get(i);
				json2.put("" + i, goods2.getG_id());
				i++;
			}
			json2.put("number", "" + list.size());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return json2.toString();
	}
	
}
