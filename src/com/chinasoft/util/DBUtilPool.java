package com.chinasoft.util;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class DBUtilPool {

	private static final String DRIVER = "oracle.jdbc.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String USER="scott";
	private static final String PASSWORD="tiger";
	
	/**
	 * �������ӳ�BasicDataSource
	 */
	public static BasicDataSource dataSourcePool = new BasicDataSource();
	//��̬�����
	static{
		//�������ӳأ���Ӧ�Ĳ���
		dataSourcePool.setDriverClassName(DRIVER);//����������
		dataSourcePool.setUrl(URL);//ָ�����ӵ����ݿ��ַ
		dataSourcePool.setUsername(USER);//ָ���������ݿ���û���
		dataSourcePool.setPassword(PASSWORD);//ָ���������ݵ�����	
	      }
	/**
	 * �������ӳض���
	 * @return
	 */
	public static DataSource getDataSource()
	{
		return dataSourcePool;
	}
}
