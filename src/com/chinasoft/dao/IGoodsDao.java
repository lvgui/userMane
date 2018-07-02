package com.chinasoft.dao;

import java.util.List;

import com.chinasoft.entity.Goods;
import com.chinasoft.entity.Types;

public interface IGoodsDao {
	
		//删除商品
		public List<Goods>  selectAllType();
		//修改状态
		public boolean changeStateByName(int state,String gname);
		//修改商品名
		public boolean renameByName(String gname,String newgname);
		//判断商品名存在
		public boolean chackByTname(String gname);
		//修改商品数量
		public boolean changeTnum(String gname,int num);
		//修改商品的单价
		public boolean changeGprice(String gname,double Gprice);
		//添加商品
		public boolean addGoods(String gname,double Gprice,String typt,int pnum,int state);
}
