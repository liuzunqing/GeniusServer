package jdbc;

public class Goods {
	private String g_id;//商品id
	private String g_picture;//商品图片
	private String g_introduce;//商品介绍
	private double g_price;//商品价格
	private String g_name;//商品名称
	private String g_main_class;//商品主分类
	private String g_second_class;//商品次分类
	private String s_id;//店铺id
	private int number;//商品库存
	private String v_id;//卖家id
	public String getG_id() {
		return g_id;
	}
	public void setG_id(String g_id) {
		this.g_id = g_id;
	}
	public String getG_picture() {
		return g_picture;
	}
	public void setG_picture(String g_picture) {
		this.g_picture = g_picture;
	}
	public String getG_introduce() {
		return g_introduce;
	}
	public void setG_introduce(String g_introduce) {
		this.g_introduce = g_introduce;
	}
	public double getG_price() {
		return g_price;
	}
	public void setG_price(double g_price) {
		this.g_price = g_price;
	}
	public String getG_name() {
		return g_name;
	}
	public void setG_name(String g_name) {
		this.g_name = g_name;
	}
	public String getG_main_class() {
		return g_main_class;
	}
	public void setG_main_class(String g_main_class) {
		this.g_main_class = g_main_class;
	}
	public String getG_second_class() {
		return g_second_class;
	}
	public void setG_second_class(String g_second_class) {
		this.g_second_class = g_second_class;
	}
	public String getS_id() {
		return s_id;
	}
	public void setS_id(String s_id) {
		this.s_id = s_id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getV_id() {
		return v_id;
	}
	public void setV_id(String v_id) {
		this.v_id = v_id;
	}
}
