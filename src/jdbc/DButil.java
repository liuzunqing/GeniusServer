package jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButil {
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
public static Connection open(){
//	Properties prop=new Properties();
//	try {
//		Reader in=new FileReader("/root/Genius/config.properties");
//		prop.load(in);
//		driver=prop.getProperty("driver");
//		url=prop.getProperty("url");
//		user=prop.getProperty("user");
//		password=prop.getProperty("password");
//		Class.forName(driver);
//		return DriverManager.getConnection(url,user,password);
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	return null;
	driver = "com.mysql.jdbc.Driver";
	url = "jdbc:mysql://39.106.203.224:3306/Genius?characterEncoding=utf-8";
	user = "lzq";
	password = "Lzq66666";
	try {
		Class.forName(driver);
		return DriverManager.getConnection(url,user,password);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
public static void close(Connection c){
	if(c!=null)
	{
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
}
