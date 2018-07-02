package com.chinasoft.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.chinasoft.dao.IUserQuanDao;
import com.chinasoft.util.DBUtil;
import com.chinasoft.util.DBUtilPool;

public class UserQuanImpl implements IUserQuanDao {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	@Override
	public int queryQuan(String username) {
		try {
			conn = DBUtilPool.getDataSource().getConnection();
			if(conn == null){
				System.out.println("数据库连接为空");
			}else{
//				System.out.println("连接成功");
				pstmt = conn.prepareStatement("select jurisdiction from userquan where username = ?");
				pstmt.setString(1, username);
				rs = pstmt.executeQuery();
				if(rs.next()){
					return rs.getInt(1);
				}
		
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.release(pstmt, conn);
		}
		
		return 0;
	}

}
