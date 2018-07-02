package com.chinasoft.util;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class DBUtilPool {

	private static final String DRIVER = "oracle.jdbc.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String USER="scott";
	private static final String PASSWORD="tiger";
	
	/**
	 * 创建连接池BasicDataSource
	 */
	public static BasicDataSource dataSourcePool = new BasicDataSource();
	//静态代码块
	static{
		//配置连接池，对应的参数
		dataSourcePool.setDriverClassName(DRIVER);//配置驱动类
		dataSourcePool.setUrl(URL);//指定连接的数据库地址
		dataSourcePool.setUsername(USER);//指定连接数据库的用户名
		dataSourcePool.setPassword(PASSWORD);//指定连接数据的密码	
	      }
	/**
	 * 返回连接池对象
	 * @return
	 */
	public static DataSource getDataSource()
	{
		return dataSourcePool;
	}
}
