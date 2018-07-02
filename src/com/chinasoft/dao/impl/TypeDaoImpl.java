package com.chinasoft.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.chinasoft.dao.ITypeDao;
import com.chinasoft.entity.Types;
import com.chinasoft.util.DBUtil;
import com.chinasoft.util.DBUtilPool;

public class TypeDaoImpl implements ITypeDao {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	@Override
	public boolean deleteByName(String tname) {
		try {
			conn = DBUtilPool.getDataSource().getConnection();
			if(conn == null){
				System.out.println("数据库连接为空");
			}else{
//				System.out.println("连接成功");
				pstmt = conn.prepareStatement("delete from typeinfo where tname = ?");
				pstmt.setString(1, tname);
				int a = pstmt.executeUpdate();
				if(a != 0)
					{
						return true;
					}
					else
					{
						return false;
					}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.release(pstmt, conn);
		}
		
		return false;
	}

	@Override
	public boolean changeStateByName(int state,String tname) {
		try {
			conn = DBUtilPool.getDataSource().getConnection();
			if(conn == null){
				System.out.println("数据库连接为空");
			}else{
//				System.out.println("连接成功");
				pstmt = conn.prepareStatement("update typeinfo set state = ? where tname = ?");
				pstmt.setInt(1, state);
				pstmt.setString(2, tname);
				int a = pstmt.executeUpdate();
				if(a != 0)
					{
						return true;
					}
					else
					{
						return false;
					}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.release(pstmt, conn);
		}
		
		return false;
	}
	//重命名
	@Override
	public boolean renameByName(String tname,String newtname) {
		try {
			conn = DBUtilPool.getDataSource().getConnection();
			if(conn == null){
				System.out.println("数据库连接为空");
			}else{
//				System.out.println("连接成功");
				pstmt = conn.prepareStatement("update typeinfo set tname = ? where tname = ?");
				pstmt.setString(1, newtname);
				pstmt.setString(2, tname);
				int a = pstmt.executeUpdate();
				if(a != 0)
					{
						return true;
					}
					else
					{
						return false;
					}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.release(pstmt, conn);
		}
		
		return false;
	}

	@Override //增加
	public boolean addType(String tname,int state) {
		try {
			conn = DBUtilPool.getDataSource().getConnection();
			if(conn == null){
				System.out.println("数据库连接为空");
			}else{
//				System.out.println("连接成功");
				pstmt = conn.prepareStatement("insert into  typeinfo(tname,state) values(?,?)");
				pstmt.setString(1, tname);
				pstmt.setInt(2, state);
				int a = pstmt.executeUpdate();
				if(a != 0)
					{
						return true;
					}
					else
					{
						return false;
					}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.release(pstmt, conn);
		}
		
		return false;
	}

	@Override
	public List<Types> selectAllType() {
		QueryRunner runner = new QueryRunner(DBUtilPool.getDataSource());
		String sql = "select * from typeinfo order by tid";	
		Object [] parameter = {};
			List<Types> list = null;
			try {
				list = runner.query(sql,new BeanListHandler<Types>(Types.class),parameter);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("数据库没有启动服务");
				e.printStackTrace();
			}		
		return list;
	}
	//判断类别名是否存在
	@Override
	public boolean chackByTname(String name) {
		try {
			conn = DBUtilPool.getDataSource().getConnection();
//			conn = DBUtil.getConnection();
			if(conn == null){
				System.out.println("数据库连接为空");
			}else{
//				System.out.println("连接成功");
				pstmt = conn.prepareStatement("select * from typeinfo where tname = ?");
				pstmt.setString(1, name);
				rs = pstmt.executeQuery();
				if(rs.next())
					{
						return true;
					}
					else
					{
						return false;
					}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.release(rs, pstmt, conn);
		}
		
		return false;
	}

	@Override
	public String[] selectAllTypeC() {
		QueryRunner runner = new QueryRunner(DBUtilPool.getDataSource());
		String sql = "select * from typeinfo where state = 1";	
		Object [] parameter = {};
			List<Types> list = null;
			try {
				list = runner.query(sql,new BeanListHandler<Types>(Types.class),parameter);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("数据库没有启动服务");
				e.printStackTrace();
			}		

		String [] arrType = new String[list.size()];
		for(int i = 0;i < list.size();i++) {
			arrType[i] = list.get(i).getTname();
		}
		return arrType;
	}

}
