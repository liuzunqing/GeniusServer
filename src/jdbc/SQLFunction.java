package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SQLFunction {
		
	/*检测账号是否已注册*/
	public static boolean check_register(User user){
		Connection conn=DButil.open();
		String sql="select id from user where id=?";
		try {
			PreparedStatement pstat=conn.prepareStatement(sql);
			pstat.setString(1, user.getId());
			ResultSet rs=pstat.executeQuery();
			if(rs.next()){
				return true;//账号已存在
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}return false;//账号不存在
		
	}
	
	/*注册账号*/
	public static boolean register(User user){
		Connection conn=DButil.open();
		String sql="insert into user values(?,?,?)";//sql语句
		try {
			PreparedStatement stat=conn.prepareStatement(sql);
			stat.setString(1,user.getId());
			stat.setString(2,user.getPassword());
			stat.setInt(3, user.getFlag());
			stat.execute();
			return true;//注册成功
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			DButil.close(conn);
		}return false;//注册不成功
	}
	
	/*登录账号*/
	public static String login(User user){
		Connection conn=DButil.open();
		String sql="select password,flag from user where id=?";//sql语句
		try {
			PreparedStatement stat=conn.prepareStatement(sql);
			stat.setString(1,user.getId());
			ResultSet re=stat.executeQuery();
			String password=user.getPassword();
			if(re.next())
			{
				String passwd1=re.getString(1);
				if(passwd1.equals(password))
				{
					return re.getString(2);//成功登陆
				}else return "fail";//密码错误
			}else return "fail";//账号不存在
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			DButil.close(conn);
		}
		return "fail";//其他问题
	}
	
	//获取卖家信息
	public static Vendor view_vendor(Vendor vendor) {
		Connection conn=DButil.open();
		String sql="select v_name,v_email,v_tel,v_sex,v_age,v_picture from vendor where v_id=?";
		try {
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setString(1, vendor.getV_id());
			ResultSet rs = pstat.executeQuery();
			if (rs.next()) {
				Vendor return_vendor = new Vendor();
				return_vendor.setV_name(rs.getString(1));
				return_vendor.setV_email(rs.getString(2));
				return_vendor.setV_tel(rs.getString(3));
				return_vendor.setV_sex(rs.getString(4));
				return_vendor.setV_age(rs.getInt(5));
				return_vendor.setV_picture(rs.getString(6));
				return return_vendor;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return null;
	}
	
	//获取买家信息
	public static Customer view_customer(Customer customer) {
		Connection conn=DButil.open();
		String sql="select c_name,c_email,c_tel,c_sex,c_age,c_picture,c_address,c_account from customer where c_id=?";
		try {
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setString(1, customer.getC_id());
			ResultSet rs = pstat.executeQuery();
			if (rs.next()) {
				Customer return_customer = new Customer();
				return_customer.setC_name(rs.getString(1));
				return_customer.setC_email(rs.getString(2));
				return_customer.setC_tel(rs.getString(3));
				return_customer.setC_sex(rs.getString(4));
				return_customer.setC_age(rs.getInt(5));
				return_customer.setC_picture(rs.getString(6));
				return_customer.setC_address(rs.getString(7));
				return_customer.setC_account(rs.getString(8));
				return return_customer;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return null;
	}
	
	//获取店铺信息
	public static Store view_store(Store store) {
		Connection conn=DButil.open();
		String sql="select * from store where s_id=?";
		try {
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setString(1, store.getS_id());
			ResultSet rs = pstat.executeQuery();
			if (rs.next()) {
				Store return_store = new Store();
				return_store.setS_id(rs.getString(1));
				return_store.setS_name(rs.getString(2));
				return_store.setS_style(rs.getString(3));
				return_store.setS_address(rs.getString(4));
				return_store.setS_account(rs.getString(5));
				return_store.setS_introduce(rs.getString(6));
				return_store.setV_id(rs.getString(7));
				return_store.setS_picture(rs.getString(8));
				return return_store;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return null;
	}
	
    /*编辑买家信息*/
	public static boolean modi_customer(Customer customer) {
		Connection conn=DButil.open();
		String sql="update customer  set c_name=?,c_address=?,c_account=?,c_email=?,c_tel=?,c_sex=?,c_age=?,c_picture=? where c_id=?";
		try {
			PreparedStatement pstat=conn.prepareStatement(sql);
			pstat.setString(1, customer.getC_name());
			pstat.setString(2, customer.getC_address());
			pstat.setString(3, customer.getC_account());
			pstat.setString(4, customer.getC_email());
			pstat.setString(5, customer.getC_tel());
			pstat.setString(6, customer.getC_sex());
			pstat.setInt(7, customer.getC_age());
			pstat.setString(8, customer.getC_picture());
			pstat.setString(9, customer.getC_id());
			pstat.execute();
			return true;//编辑买家信息成功
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}return false;//编辑买家信息失败
	}

	/*商品搜索*/
	public static List<Goods> search_goods(Goods goods)
	{
		Connection conn=DButil.open();
		String sql="select g_id from goods where g_name like ?";
		PreparedStatement pstat;
		String s1="%";
		String ss=s1+goods.getG_name()+s1;
		
		List<Goods> list=new ArrayList<Goods>();
		try {
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, ss);
			ResultSet rs=pstat.executeQuery();
			while(rs.next()){
				Goods return_goods=new Goods();
				return_goods.setG_id(rs.getString(1));
				list.add(return_goods);
				}
			return list;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}finally{
					DButil.close(conn);
					}
		return null;
	}
	
	/*店铺收藏*/
	public static boolean add_collect_store(Collect collect){
		Connection conn=DButil.open();
		String sql="insert into collect(c_id,s_id) values(?,?)";
		PreparedStatement pstat;
		try {
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, collect.getC_id());
			pstat.setString(2, collect.getS_id());
			pstat.execute();
			return true;//店铺收藏成功
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}finally{
					DButil.close(conn);
					}return false;//店铺收藏失败
	}	
	
	/*商品收藏*/
	public static boolean add_collect_goods(Collect collect){
			Connection conn=DButil.open();
			String sql="insert into collect(c_id,g_id) values(?,?)";
			PreparedStatement pstat;
			try {
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, collect.getC_id());
				pstat.setString(2, collect.getG_id());                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
				pstat.execute();
				return true;//商品收藏成功
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();	
					}finally{
						DButil.close(conn);
						}return false;//商品收藏失败
		}
	
	/*查看收藏*/
	public static List<Collect> search_collect(Collect collect){
		Connection conn=DButil.open();
		String sql="select * from collect where c_id=?";
		try {
			PreparedStatement pstat=conn.prepareStatement(sql);
			pstat.setString(1, collect.getC_id());
			ResultSet rs=pstat.executeQuery();
			
			List<Collect> list=new ArrayList<Collect>();
			while(rs.next()){
				Collect return_collect=new Collect();
				return_collect.setC_id(rs.getString(1));
				return_collect.setS_id(rs.getString(2));
				return_collect.setG_id(rs.getString(3));
				list.add(return_collect);
			}return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
			}return null;
	}
	
	/*顾客查看订单*/
    public static List<Orderlist> search_orderlist_c(Orderlist orderlist){
    	Connection conn=DButil.open();
		String sql="select o_id from orderlist where c_id=?";
		try {
			PreparedStatement pstat=conn.prepareStatement(sql);
			pstat.setString(1, orderlist.getC_id());
		    ResultSet rs=pstat.executeQuery();
		    List<Orderlist> list=new ArrayList<Orderlist>();
		    while(rs.next()){
		    Orderlist return_orderlist=new Orderlist();
		    return_orderlist.setO_id(rs.getString(1));
		    list.add(return_orderlist);
		    }return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}return null;
    }
    
    /*卖家查看订单*/
    public static List<Orderlist> search_orderlist_v(Orderlist orderlist){
    	Connection conn=DButil.open();
		String sql="select o_id from orderlist where v_id=?";
		try {
			PreparedStatement pstat=conn.prepareStatement(sql);
			pstat.setString(1, orderlist.getV_id());
		    ResultSet rs=pstat.executeQuery();
		    List<Orderlist> list=new ArrayList<Orderlist>();
		    while(rs.next()){
		    Orderlist return_orderlist=new Orderlist();
		    return_orderlist.setO_id(rs.getString(1));
		    list.add(return_orderlist);
		    }return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}return null;
    }

	/*删除收藏店铺*/
	public static boolean del_collect_store(Collect collect){
		Connection conn=DButil.open();
		String sql="delete from collect where c_id=? and s_id=?";
		try {
			PreparedStatement pstat=conn.prepareStatement(sql);
			pstat.setString(1, collect.getC_id());
			pstat.setString(2, collect.getS_id());
			pstat.execute();
			return true;//删除收藏店铺成功
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
			}return false;//删除收藏店铺失败		
	}
	
	/*删除收藏商品*/
	public static boolean del_collect_goods(Collect collect){
			Connection conn=DButil.open();
			String sql="delete from collect where c_id=? and g_id=?";
			try {
				PreparedStatement pstat=conn.prepareStatement(sql);
				pstat.setString(1, collect.getC_id());
				pstat.setString(2, collect.getG_id());
				pstat.execute();
				return true;//删除收藏商品成功
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DButil.close(conn);
				}	return false;//删除收藏商品失败
		}
		
	/*添加到购物车*/
	public static boolean add_shopcar(Shopcar shopcar){
		Connection conn=DButil.open();
		String sql="insert into shopcar values(?,?,?)";
		try {
			PreparedStatement pstat=conn.prepareStatement(sql);
			pstat.setString(1, shopcar.getG_id());
			pstat.setString(2, shopcar.getC_id());
			pstat.setInt(3, shopcar.getSp_number());
			pstat.execute();
			return true;//添加到购物车成功
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
			}return false;//添加到购物车失败
	}

	/*查看购物车*/
	public static List<Shopcar> search_shopcar(Shopcar shopcar){
		Connection conn=DButil.open();
		String sql="select * from shopcar where c_id=?";
		try {
			PreparedStatement pstat=conn.prepareStatement(sql);
			pstat.setString(1, shopcar.getC_id());
			ResultSet rs=pstat.executeQuery();
			List<Shopcar> list=new ArrayList<Shopcar>();
			while(rs.next()){
				Shopcar return_shopcar=new Shopcar();
				return_shopcar.setG_id(rs.getString(1));
				return_shopcar.setSp_number(rs.getInt(3));
				list.add(return_shopcar);
			}return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}return null;
	}
	
	/*删除购物车*/
	public static boolean del_shopcar(Shopcar shopcar){
		Connection conn=DButil.open();
		String sql="delete from shopcar where c_id=? and g_id=?";
		try {
			PreparedStatement pstat=conn.prepareStatement(sql);
			pstat.setString(2, shopcar.getG_id());
			pstat.setString(1, shopcar.getC_id());
		    pstat.execute();
		    return true;//删除购物车成功
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}return false;//删除购物车失败
	}
	
	/*提交订单*/
	public static boolean add_orderlist(Orderlist orderlist){
		Connection conn=DButil.open();
		String sql="insert into orderlist values(?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstat=conn.prepareStatement(sql);
			pstat.setString(1, orderlist.getO_id());
			pstat.setString(2, orderlist.getC_id());
			pstat.setString(3, orderlist.getV_id());
			pstat.setString(4, orderlist.getO_time());
			pstat.setString(5, orderlist.getS_id());
			pstat.setString(6, orderlist.getO_state());
			pstat.setString(7, orderlist.getG_name());
			pstat.setDouble(8, orderlist.getG_price());
			pstat.setInt(9, orderlist.getNumber());
		    pstat.execute();
		    return true;//提交订单成功
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}return false;//提交订单失败
	}
	
	/*删除订单*/
	public static boolean del_orderlist(Orderlist orderlist)
	{
		Connection conn=DButil.open();
		String sql="delete from orderlist where o_id=?";
		try {
			PreparedStatement pstat=conn.prepareStatement(sql);
			pstat.setString(1, orderlist.getO_id());
		    pstat.execute();
		    return true;//删除订单成功
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}return false;//删除订单失败
	}

	/*编辑卖家信息*/
	public static boolean modi_vendor(Vendor vendor){
		Connection conn=DButil.open();
		String sql="update vendor set v_name=?,v_email=?,v_tel=?,v_sex=?,v_age=?,v_picture=? where v_id=?";
		try {
			PreparedStatement pstat=conn.prepareStatement(sql);
			pstat.setString(1, vendor.getV_name());
			pstat.setString(2, vendor.getV_email());
			pstat.setString(3, vendor.getV_tel());
			pstat.setString(4, vendor.getV_sex());
			pstat.setInt(5, vendor.getV_age());
			pstat.setString(6, vendor.getV_picture());
			pstat.setString(7, vendor.getV_id());
		    pstat.execute();
		    return true;//编辑卖家信息成功
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}return false;//编辑卖家信息失败
	}
	
	/*添加商品*/
	public static boolean add_goods(Goods goods){
		Connection conn=DButil.open();
		String sql="insert into goods values(?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstat=conn.prepareStatement(sql);
			pstat.setString(1, goods.getG_id());
			pstat.setString(2, goods.getG_picture());
			pstat.setString(3,goods.getG_introduce());
			pstat.setDouble(4, goods.getG_price());
			pstat.setString(5, goods.getG_name());
			pstat.setString(6, goods.getG_main_class());
			pstat.setString(7, goods.getG_second_class());
			pstat.setString(8, goods.getS_id());
			pstat.setInt(9, goods.getNumber());
			pstat.setString(10, goods.getV_id());
		    pstat.execute();
		    return true;//添加商品成功
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}return false;//添加商品失败
	}
	
	/*修改商品信息*/
	public static boolean modi_goods(Goods goods){
		Connection conn=DButil.open();
		String sql="update goods set g_picture=?,g_introduce=?,g_price=?,g_name=?,g_main_class=?,g_second_class=?,s_id=?,g_number=? where g_id=?";
		try {
			PreparedStatement pstat=conn.prepareStatement(sql);
			pstat.setString(1, goods.getG_picture());
			pstat.setString(2, goods.getG_introduce());
			pstat.setDouble(3, goods.getG_price());
			pstat.setString(4, goods.getG_name());
			pstat.setString(5, goods.getG_main_class());
			pstat.setString(6, goods.getG_second_class());
			pstat.setString(7, goods.getS_id());
			pstat.setInt(8, goods.getNumber());
			pstat.setString(9, goods.getG_id());
		    pstat.execute();
		    return true;//修改商品成功
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}return false;//修改商品失败
	}
	
	/*删除商品*/
	public static boolean del_goods(Goods goods){
		Connection conn=DButil.open();
		String sql="delete from goods where g_id=?";
		try {
			PreparedStatement pstat=conn.prepareStatement(sql);
			pstat.setString(1, goods.getG_id());
		    pstat.execute();
		    return true;//删除商品成功
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}return false;//删除商品失败
	}
	
	/*查看订单*/
	public static Orderlist view_orderlist(Orderlist orderlist){
		Connection conn=DButil.open();
		String sql="select * from orderlist where o_id=?";
		try {
			PreparedStatement pstat=conn.prepareStatement(sql);
			pstat.setString(1, orderlist.getO_id());
		    ResultSet rs=pstat.executeQuery();
		    if(rs.next()){
		    Orderlist return_orderlist=new Orderlist();
		    return_orderlist.setO_id(rs.getString(1));
		    return_orderlist.setC_id(rs.getString(2));
		    return_orderlist.setV_id(rs.getString(3));
		    return_orderlist.setO_time(rs.getString(4));
		    return_orderlist.setS_id(rs.getString(5));
		    return_orderlist.setO_state(rs.getString(6));
		    return_orderlist.setG_name(rs.getString(7));
		    return_orderlist.setG_price(rs.getDouble(8));
		    return_orderlist.setNumber(rs.getInt(9));
		    return return_orderlist;
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return null;
	}
	
	/*添加会员*/
	public static boolean add_vip(Vip vip){
		Connection conn=DButil.open();
		String sql="insert into vip values(?,?)";
		try {
			PreparedStatement pstat=conn.prepareStatement(sql);
			pstat.setString(1, vip.getC_id());
			pstat.setString(2, vip.getS_id());
		    pstat.execute();
		    return true;//添加会员成功
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}return false;//添加会员失败
	}

	/*查看会员*/
	public static List<Vip> search_vip(Vip vip){
			Connection conn=DButil.open();
			String sql="select * from vip where s_id=?";
			try {
				PreparedStatement pstat=conn.prepareStatement(sql);
				pstat.setString(1, vip.getS_id());
			    ResultSet rs=pstat.executeQuery();
			    List <Vip> list=new ArrayList<Vip>();
			    while(rs.next()){
			    	Vip return_vip=new Vip();
			    	return_vip.setC_id(rs.getString(1));
			    	return_vip.setS_id(rs.getString(2));
			    	list.add(return_vip);
			    }return list;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DButil.close(conn);
			} return null;
		}	
	
	/*删除会员*/
	public static boolean del_vip(Vip vip){
		Connection conn=DButil.open();
		String sql="delete from vip where c_id=? and s_id=?";
		try {
			PreparedStatement pstat=conn.prepareStatement(sql);
			pstat.setString(1, vip.getC_id());
			pstat.setString(2, vip.getS_id());
		    pstat.execute();
		    return true;//删除会员成功
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}return false;//删除会员失败
	}
    
    /*查询密码*/
    public static boolean check_password(User user,String password){
    	Connection conn=DButil.open();
    	String check_password;
		String sql="select password from user where id=?";
		try {
			PreparedStatement pstat=conn.prepareStatement(sql);
			pstat.setString(1, user.getId());
		    ResultSet rs=pstat.executeQuery();
		    if(rs.next()){
		    check_password=rs.getString(1);
		    System.out.println(check_password);
		    if(password.equals(check_password))
		    	return true;//密码正确
		    else return false;//密码错误
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return false;//其他问题
    }
    
    /*修改密码*/
    public static boolean modi_password(User user)
    {
    	Connection conn=DButil.open();
		String sql="update user set password=? where id=?";
		try {
			PreparedStatement pstat=conn.prepareStatement(sql);
			pstat.setString(1, user.getPassword());
			pstat.setString(2, user.getId());
		    pstat.execute();
		    return true;//修改密码成功
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error in modi_password");
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}return false;//修改密码失败
    }
    
    /*查看商品*/
    public static Goods view_goods(Goods goods)
    {
    	Connection conn=DButil.open();
    	String sql="select * from goods where g_id=?";
    	try{
    		PreparedStatement pstat=conn.prepareStatement(sql);
    		pstat.setString(1, goods.getG_id());
    		ResultSet rs=pstat.executeQuery();
    		if(rs.next()){
    		Goods return_goods=new Goods();
    		return_goods.setG_id(rs.getString(1));
    		return_goods.setG_picture(rs.getString(2));
    		return_goods.setG_introduce(rs.getString(3));
    		return_goods.setG_price(rs.getDouble(4));
    		return_goods.setG_name(rs.getString(5));
    		return_goods.setG_main_class(rs.getString(6));
    		return_goods.setG_second_class(rs.getString(7));
    		return_goods.setS_id(rs.getString(8));
    		return_goods.setNumber(rs.getInt(9));
    		return_goods.setV_id(rs.getString(10));
    		return return_goods;
    		}
    		} catch (SQLException e){
    			e.printStackTrace();
    			}finally{
    				DButil.close(conn);
    			}
    	return null;
    } 

    /*更改订单状态*/
    public static boolean modi_orderlist(Orderlist orderlist){
    	Connection conn=DButil.open();
		String sql="update orderlist set o_state=?  where o_id=?";
		try {
			PreparedStatement pstat=conn.prepareStatement(sql);
			pstat.setString(1, orderlist.getO_state());
			pstat.setString(2, orderlist.getO_id());
		    pstat.execute();
		    return true;//更改订单状态成功
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error in modi_password");
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}return false;//更改订单状态失败
    }
    
    /*更新店铺信息*/
    public static boolean modi_store(Store store){
    	Connection conn=DButil.open();
		String sql="update store set s_name=?,s_style=?,s_address=?,s_account=?,s_introduce=?,s_picture=? where s_id=?";
		try {
			PreparedStatement pstat=conn.prepareStatement(sql);
			pstat.setString(1, store.getS_name());
			pstat.setString(2, store.getS_style());
			pstat.setString(3, store.getS_address());
			pstat.setString(4, store.getS_account());
			pstat.setString(5, store.getS_introduce());
			pstat.setString(6, store.getS_picture());
			pstat.setString(7, store.getS_id());
		    pstat.execute();
		    return true;//更新店铺信息成功
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error in modi_password");
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}return false;//更新店铺信息失败
    }	
		
    /*更改购物车商品数量*/
    public static boolean modi_shopcar(Shopcar shopcar){
			Connection conn=DButil.open();
			String sql="update shopcar set sp_number=? where g_id=? and c_id=?";
			try {
				PreparedStatement pstat=conn.prepareStatement(sql);
				pstat.setInt(1, shopcar.getSp_number());
				pstat.setString(2, shopcar.getG_id());
				pstat.setString(3, shopcar.getC_id());
			    pstat.execute();
			    return true;//更改购物车数量成功
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DButil.close(conn);
			}	return false;//更改购物车数量失败
		}
	
    /*开店*/
    public static boolean add_store(Store store){
    	Connection conn=DButil.open();
		String sql="insert into store values(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstat=conn.prepareStatement(sql);
			pstat.setString(1, store.getS_id());
			pstat.setString(2, store.getS_name());
			pstat.setString(3, store.getS_style());
			pstat.setString(4, store.getS_address());
			pstat.setString(5, store.getS_account());
			pstat.setString(6, store.getS_introduce());
			pstat.setString(7, store.getV_id());
			pstat.setString(8, store.getS_picture());
		    pstat.execute();
		    return true;//开店成功
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}return false;//开店失败
    }
    
    //检查商品id是否存在
    public static boolean check_goodsid(String g_id) {
		Connection conn=DButil.open();
		String sql="select g_id from goods where g_id=?";
		try {
			PreparedStatement pstat=conn.prepareStatement(sql);
			pstat.setString(1, g_id);
			ResultSet rs=pstat.executeQuery();
			if(rs.next()){
				return true;//存在
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return false;
	}
 
    //检查店铺id是否存在
    public static boolean check_storeid(String s_id) {
		Connection conn=DButil.open();
		String sql="select s_id from store where s_id=?";
		try {
			PreparedStatement pstat=conn.prepareStatement(sql);
			pstat.setString(1, s_id);
			ResultSet rs=pstat.executeQuery();
			if(rs.next()){
				return true;//存在
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return false;
	}
    
    
    //检查订单id是否存在
    public static boolean check_orderlistid(String o_id) {
		Connection conn=DButil.open();
		String sql="select o_id from orderlist where o_id=?";
		try {
			PreparedStatement pstat=conn.prepareStatement(sql);
			pstat.setString(1, o_id);
			ResultSet rs=pstat.executeQuery();
			if(rs.next()){
				return true;//存在
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		return false;
	}
    
    /*根据主分类查找商品*/
    public static List<Goods> search_goodswithmainclass(Goods goods){
    	Connection conn=DButil.open();
		String sql="select g_id from goods where g_main_class=?";
		try {
			PreparedStatement pstat=conn.prepareStatement(sql);
			pstat.setString(1, goods.getG_main_class());
		    ResultSet rs=pstat.executeQuery();
		    List<Goods> list=new ArrayList<Goods>();
		    while(rs.next()){
		    Goods return_goods=new Goods();
		    return_goods.setG_id(rs.getString(1));
		    list.add(return_goods);
		    }return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}return null;
    }
   
    /*根据次分类查找商品*/
    public static List<Goods> search_goodswithsecondclass(Goods goods){
    	Connection conn=DButil.open();
		String sql="select g_id from goods where g_second_class=?";
		try {
			PreparedStatement pstat=conn.prepareStatement(sql);
			pstat.setString(1, goods.getG_second_class());
		    ResultSet rs=pstat.executeQuery();
		    List<Goods> list=new ArrayList<Goods>();
		    while(rs.next()){
		    Goods return_goods=new Goods();
		    return_goods.setG_id(rs.getString(1));
		    list.add(return_goods);
		    }return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}return null;
    }
    
    /*搜索某一商店的所有商品*/
    public static List<Goods> search_goodsinstore(Goods goods){
    	Connection conn=DButil.open();
		String sql="select g_id from goods where s_id=?";
		try {
			PreparedStatement pstat=conn.prepareStatement(sql);
			pstat.setString(1, goods.getS_id());
		    ResultSet rs=pstat.executeQuery();
		    List<Goods> list=new ArrayList<Goods>();
		    while(rs.next()){
		    Goods return_goods=new Goods();
		    return_goods.setG_id(rs.getString(1));
		    list.add(return_goods);
		    }return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}return null;
    }

    
}





