package jdbc;

public class Store {
	private String s_id;//����id
	private String s_name;//������
	private String s_style;//������ʽ��ֵΪ1,2,3����ͬ������ʽ
	private String s_address;//���̵�ַ
	private String s_account;//�����˺�
	private String s_introduce;//���̽���
	private String v_id;//����id
	private String s_picture;//����ͼƬ
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public String getS_id() {
		return s_id;
	}
	public void setS_id(String s_id) {
		this.s_id = s_id;
	}
	public String getS_style() {
		return s_style;
	}
	public void setS_style(String s_style) {
		this.s_style = s_style;
	}
	public String getS_address() {
		return s_address;
	}
	public void setS_address(String s_address) {
		this.s_address = s_address;
	}
	public String getS_account() {
		return s_account;
	}
	public void setS_account(String s_account) {
		this.s_account = s_account;
	}
	public String getS_introduce() {
		return s_introduce;
	}
	public void setS_introduce(String s_introduce) {
		this.s_introduce = s_introduce;
	}
	public String getV_id() {
		return v_id;
	}
	public void setV_id(String v_id) {
		this.v_id = v_id;
	}
	public String toString(){
		return s_id+":";
	}
	public void setS_picture(String s_picture) {
		this.s_picture = s_picture;
	}
	public String getS_picture() {
		return s_picture;
	}
}
