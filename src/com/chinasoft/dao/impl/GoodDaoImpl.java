package com.chinasoft.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.chinasoft.dao.IGoodsDao;
import com.chinasoft.entity.Goods;
import com.chinasoft.entity.Types;
import com.chinasoft.util.DBUtil;
import com.chinasoft.util.DBUtilPool;

public class GoodDaoImpl implements IGoodsDao {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	@Override
	public List<Goods> selectAllType() {
		QueryRunner runner = new QueryRunner(DBUtilPool.getDataSource());
		String sql = "select * from goods order by id";	
		Object [] parameter = {};
			List<Goods> list = null;
			try {
				list = runner.query(sql,new BeanListHandler<Goods>(Goods.class),parameter);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("数据库没有启动服务");
				e.printStackTrace();
			}		
		return list;
	}
	
	@Override
	public boolean changeStateByName(int state, String gname) {
		try {
			conn = DBUtilPool.getDataSource().getConnection();
			if(conn == null){
				System.out.println("数据库连接为空");
			}else{
//				System.out.println("连接成功");
				pstmt = conn.prepareStatement("update goods set state = ? where gname = ?");
				pstmt.setInt(1, state);
				pstmt.setString(2, gname);
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
	public boolean renameByName(String gname, String newgname) {
		try {
			conn = DBUtilPool.getDataSource().getConnection();
			if(conn == null){
				System.out.println("数据库连接为空");
			}else{
//				System.out.println("连接成功");
				pstmt = conn.prepareStatement("update goods set gname = ? where gname = ?");
				pstmt.setString(1, newgname);
				pstmt.setString(2, gname);
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
	public boolean chackByTname(String gname) {
		try {
			conn = DBUtilPool.getDataSource().getConnection();
//			conn = DBUtil.getConnection();
			if(conn == null){
				System.out.println("数据库连接为空");
			}else{
//				System.out.println("连接成功");
				pstmt = conn.prepareStatement("select * from goods where gname = ?");
				pstmt.setString(1, gname);
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
	//修改商品数量
	
	@Override
	public boolean changeTnum(String gname, int num) {
		try {
			conn = DBUtilPool.getDataSource().getConnection();
			if(conn == null){
				System.out.println("数据库连接为空");
			}else{
//				System.out.println("连接成功");
				pstmt = conn.prepareStatement("update goods set gnum = ? where gname = ?");
				pstmt.setInt(1, num);
				pstmt.setString(2, gname);
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
	public boolean changeGprice(String gname, double Gprice) {
		try {
			conn = DBUtilPool.getDataSource().getConnection();
			if(conn == null){
				System.out.println("数据库连接为空");
			}else{
//				System.out.println("连接成功");
				pstmt = conn.prepareStatement("update goods set gprice = ? where gname = ?");
				pstmt.setDouble(1, Gprice);
				pstmt.setString(2, gname);
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
	public boolean addGoods(String gname, double Gprice, String typt, int pnum, int state) {
		try {
			conn = DBUtilPool.getDataSource().getConnection();
			if(conn == null){
				System.out.println("数据库连接为空");
			}else{
//				System.out.println("连接成功");
				pstmt = conn.prepareStatement("insert into goods(gname,gprice,gtype,gnum,state) values(?,?,?,?,?)");
				pstmt.setDouble(2, Gprice);
				pstmt.setString(1, gname);
				pstmt.setString(3, typt);
				pstmt.setInt(4, pnum);
				pstmt.setInt(5, state);
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





}
