package com.genius;

import org.json.JSONException;
import org.json.JSONObject;

import jdbc.Collect;
import jdbc.Goods;
import jdbc.Orderlist;
import jdbc.Shopcar;
import jdbc.Store;
import jdbc.SQLFunction;
import jdbc.Vip;

public class AddInformation {

	public static String add_GoodsInfo(String jsonstr) {
		Goods goods = new Goods();
		String result= null;
		try {
			JSONObject json = new JSONObject(jsonstr);
			goods.setS_id(json.getString("sid"));
			
			//商品id命名
			int i = 1;
			while(SQLFunction.check_goodsid(goods.getS_id() + "-" + i)) {
				i++;
			}
			goods.setG_id(goods.getS_id() + "-" + i);
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
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String vid = goods.getS_id().substring(0, 11);
		goods.setV_id(vid);
		
		if (SQLFunction.add_goods(goods))
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
	
	public static String add_StoreInfo(String jsonstr) {
		Store store = new Store();
		
		String result= null;
		try {
			JSONObject json = new JSONObject(jsonstr);
			store.setV_id(json.getString("vid"));
//			//检查触发器操作是否把卖家信息添加到vendor表中
//			while(!SQLFunction.check_vendorid(store.getV_id()));
			//店铺id命名
			int i = 1;
			while(SQLFunction.check_storeid(store.getV_id() + "-" + i)) {
				i++;
			}
			store.setS_id(store.getV_id() + "-" + i);
			String path = "store/pic_moren.txt";

			store.setS_picture(path);
			store.setS_name("商品名称");
			store.setS_address("店铺地址");
			store.setS_introduce("店铺介绍");
			store.setS_account(store.getV_id());
			store.setS_style("1");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (SQLFunction.add_store(store))
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
	
	public static String add_Orderlist(String jsonstr) {
		Orderlist orderlist = new Orderlist();
		
		String result= null;
		try {
			JSONObject json = new JSONObject(jsonstr);
			orderlist.setV_id(json.getString("vid"));
			orderlist.setC_id(json.getString("cid"));
			orderlist.setO_time(json.getString("time"));
			orderlist.setS_id(json.getString("sid"));
			orderlist.setO_state(json.getString("state"));
			orderlist.setG_name(json.getString("gname"));
			orderlist.setG_price(Double.parseDouble(json.getString("gprice")));
			orderlist.setNumber(Integer.parseInt(json.getString("number")));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//订单id命名
		int i = 1;
		while(SQLFunction.check_orderlistid("orderlist-" + i)) {
			i++;
		}
		orderlist.setO_id("orderlist-" + i);

		if (SQLFunction.add_orderlist(orderlist))
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
	
	public static String add_Shopcar(String jsonstr) {
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
		
		if (SQLFunction.add_shopcar(shopcar))
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
	
	public static String add_Collect(String jsonstr) {
		Collect collect = new Collect();	
		
		String result= null;
		try {
			JSONObject json = new JSONObject(jsonstr);
			collect.setC_id(json.getString("cid"));
			collect.setS_id(json.getString("sid"));
			collect.setG_id(json.getString("gid"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (!collect.getS_id().equals("0")) {//添加商品失败
			result = SQLFunction.add_collect_store(collect)? "success":"storefail";
		} else if (!collect.getC_id().equals("0")) {
			return SQLFunction.add_collect_goods(collect)? "success":"goodsfail";
		} else {
			result = "allfail";
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

	public static String add_Vip(String jsonstr) {
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
		
		result = SQLFunction.add_vip(vip)? "success":"fail";
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
