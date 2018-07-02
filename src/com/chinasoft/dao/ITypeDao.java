package com.chinasoft.dao;

import java.util.List;

import com.chinasoft.entity.Types;
import com.chinasoft.entity.Users;

public interface ITypeDao {
	//删除列表类别
	public boolean deleteByName(String tname); 
	//修改状态
	public boolean changeStateByName(int a,String tname);
	//修改类别名
	public boolean renameByName(String tname,String newtname);
	//添加新类别
	public boolean addType(String tname,int state);
	//查询所有类别名
	public List<Types> selectAllType();
	//判断类别存在
	public boolean chackByTname(String name);
	//查询所有类别名数组
	public String[] selectAllTypeC();
}
