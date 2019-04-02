package jdbc;

public class Orderlist {
	private String o_id;//订单id
	private String c_id;//买家id
	private String v_id;//卖家id
	private String o_time;//下单时间
	private String s_id;//店铺id
	private String o_state;//订单状态
	private String g_name;//商品名称
	private double g_price;//商品单价
	private int number;//购买数量
	public String getO_id() {
		return o_id;
	}
	public void setO_id(String o_id) {
		this.o_id = o_id;
	}
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	public String getV_id() {
		return v_id;
	}
	public void setV_id(String v_id) {
		this.v_id = v_id;
	}
	public String getO_time() {
		return o_time;
	}
	public void setO_time(String o_time) {
		this.o_time = o_time;
	}
	public String getS_id() {
		return s_id;
	}
	public void setS_id(String s_id) {
		this.s_id = s_id;
	}
	public String getO_state() {
		return o_state;
	}
	public void setO_state(String o_state) {
		this.o_state = o_state;
	}
	public void setG_name(String g_name) {
		this.g_name = g_name;
	}
	public String getG_name() {
		return g_name;
	}
	public void setG_price(double g_price) {
		this.g_price = g_price;
	}
	public double getG_price() {
		return g_price;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getNumber() {
		return number;
	}
	
}
