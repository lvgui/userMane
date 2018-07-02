package com.chinasoft.dao;

import java.util.List;

import com.chinasoft.entity.Types;
import com.chinasoft.entity.Users;

public interface ITypeDao {
	//ɾ���б����
	public boolean deleteByName(String tname); 
	//�޸�״̬
	public boolean changeStateByName(int a,String tname);
	//�޸������
	public boolean renameByName(String tname,String newtname);
	//��������
	public boolean addType(String tname,int state);
	//��ѯ���������
	public List<Types> selectAllType();
	//�ж�������
	public boolean chackByTname(String name);
	//��ѯ�������������
	public String[] selectAllTypeC();
}
