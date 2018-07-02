package com.chinasoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author Administrator
 * ���ݿ⹤����
 */
public class DBUtil {

	/**
	 * ������
	 */
	private static final String DRIVER = "oracle.jdbc.OracleDriver";
	/**
	 * ���ӵ�ַ
	 */
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	/**
	 * �û���
	 */
	private static final String USER="scott";
	/**
	 * ����
	 */
	private static final String PASSWORD="tiger";
	/**
	 * ��������
	 */
	static {
		    try {
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e) {
				System.out.println("��鿴Oracle JDBC������");
			}
	}
   /**
    * ������ӵķ���
 * @throws SQLException 
    */
	public static Connection getConnection() throws SQLException
	{
		return DriverManager.getConnection(URL,USER,PASSWORD);	
	}
	/**
	 * �ر���Դ�ķ���
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
