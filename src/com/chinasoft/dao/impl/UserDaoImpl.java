package com.chinasoft.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.chinasoft.dao.IUserDao;
import com.chinasoft.entity.Users;
import com.chinasoft.util.DBUtil;
import com.chinasoft.util.DBUtilPool;

public class UserDaoImpl implements IUserDao {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	@Override
	public boolean LoginByName(Users user) {
		// TODO Auto-generated method stub
		try {
			conn = DBUtilPool.getDataSource().getConnection();

			if(conn == null){
				System.out.println("数据库连接为空");
			}else{
//				System.out.println("连接成功");
				pstmt = conn.prepareStatement("select * from users where username=? and userpass=?");
				pstmt.setString(1, user.getUserName());
				pstmt.setString(2, user.getUserPass());
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
	public boolean chackByName(Users user) {
		try {
			conn = DBUtilPool.getDataSource().getConnection();
//			conn = DBUtil.getConnection();
			if(conn == null){
				System.out.println("数据库连接为空");
			}else{
//				System.out.println("连接成功");
				pstmt = conn.prepareStatement("select * from users where username=?");
				pstmt.setString(1, user.getUserName());
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
//	public static void main(String[] args) {
//		Users u1 = new Users();
//		new UserDaoImpl().LoginByName(u1);
//	}
}
