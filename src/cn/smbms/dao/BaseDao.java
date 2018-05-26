package cn.smbms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.persistence.PrePersist;

/**
 * 数据库工具类
 * 单例
 * 静态
 * @params
 * @author syb
 * @return
 */
public class BaseDao {

	private BaseDao(){};
	/**
	 * 静态内部类
	 * @params
	 * @author syb
	 * @return
	 */
	public static  class single {
		private static BaseDao bd=new BaseDao();
	}
	/**
	 * 恶汉模式
	 * 优点：单例    立即加载  线程安全
	 * 缺点: 浪费资源   ，（不能）延迟加载
	 * 解决：静态内部类
	 * 空间换时间
	 * @return
	 */
	public static BaseDao getInstance(){
		return single.bd;
		
	}
	
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	private static Connection con;
	private static PreparedStatement ps;
	private static ResultSet rs;
	
	
	static{
		ResourceBundle rb=ResourceBundle.getBundle("database");
		driver=rb.getString("driver");
		url=rb.getString("url");
		user=rb.getString("user");
		password=rb.getString("password");
	}
	/**
	 * 建立数据库连接
	 * @return
	 */
	public static Connection getCon(){
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	/**
	 * 查询通用方法
	 * @param sql
	 * @param param
	 * @return
	 */
	public static ResultSet query(String sql,Object...param){
		con=getCon();
		try {
			ps=con.prepareStatement(sql);
			if (param!=null) {
				for (int i = 0; i < param.length; i++) {
					ps.setObject(i+1, param[i]);
				}
			}
			rs=ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * 增删改通用方法
	 * @param sql
	 * @param param
	 * @return
	 */
	public static int CUD(String sql,Object...param){
		con=getCon();
		int count=0;
		try {
			ps=con.prepareStatement(sql);
				for (int i = 0; i < param.length; i++) {
					ps.setObject(i+1, param[i]);
				}
			count=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	/**
	 * 释放资源
	 * @param rs
	 * @param ps
	 * @param con
	 */
	public static void closeAll(ResultSet rs,PreparedStatement ps,Connection con){
		try {
			if (rs!=null) {
				rs.close();
				rs=null;
			}
			if (ps!=null) {
				ps.close();
				ps=null;
			}
			if (con!=null) {
				con.close();
				con=null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
