package com.chinasoft.dao;

import java.util.List;

import com.chinasoft.entity.Goods;
import com.chinasoft.entity.Types;

public interface IGoodsDao {
	
		//ɾ����Ʒ
		public List<Goods>  selectAllType();
		//�޸�״̬
		public boolean changeStateByName(int state,String gname);
		//�޸���Ʒ��
		public boolean renameByName(String gname,String newgname);
		//�ж���Ʒ������
		public boolean chackByTname(String gname);
		//�޸���Ʒ����
		public boolean changeTnum(String gname,int num);
		//�޸���Ʒ�ĵ���
		public boolean changeGprice(String gname,double Gprice);
		//�����Ʒ
		public boolean addGoods(String gname,double Gprice,String typt,int pnum,int state);
}
