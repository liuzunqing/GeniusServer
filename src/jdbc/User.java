package jdbc;

public class User {
private  String id;//用户id
private  String password;//用户密码
private  int flag;//类别
public  String getId() {
	return this.id;
}
public  void setId(String id) {
	this.id = id;
}
public  String getPassword() {
	return this.password;
}
public  void setPassword(String password) {
	this.password = password;
}
public  int getFlag() {
	return this.flag;
}
public  void setFlag(int flag) {
	this.flag = flag;
}
}
