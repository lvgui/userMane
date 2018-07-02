package com.chinasoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author Administrator
 * 数据库工具类
 */
public class DBUtil {

	/**
	 * 驱动名
	 */
	private static final String DRIVER = "oracle.jdbc.OracleDriver";
	/**
	 * 链接地址
	 */
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	/**
	 * 用户名
	 */
	private static final String USER="scott";
	/**
	 * 密码
	 */
	private static final String PASSWORD="tiger";
	/**
	 * 加载驱动
	 */
	static {
		    try {
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e) {
				System.out.println("请查看Oracle JDBC的驱动");
			}
	}
   /**
    * 获得连接的方法
 * @throws SQLException 
    */
	public static Connection getConnection() throws SQLException
	{
		return DriverManager.getConnection(URL,USER,PASSWORD);	
	}
	/**
	 * 关闭资源的方法
	 */
	public static void release(Statement stmt,Connection conn)
	{
		release(null,stmt,conn);
	}
	public static void release(ResultSet rs,Statement stmt,Connection conn)
	{
		if(rs !=null)
		{
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(stmt !=null)
		{
			try {
				stmt.close();
				stmt = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn !=null)	
		{
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}	
	
}
