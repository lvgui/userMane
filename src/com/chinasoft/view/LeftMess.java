package com.chinasoft.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.chinasoft.dao.impl.GoodDaoImpl;
import com.chinasoft.dao.impl.TypeDaoImpl;
import com.chinasoft.entity.Types;
import com.chinasoft.util.DBUtilPool;

public class LeftMess extends JFrame {
	JPanel right = null;
	JPanel j = null;
	
	
	private JButton good_manager; //货物管理
	private JButton type_manager; //分类管理
	private JButton add_good; //添加货物
	private JButton type_info; //类型信息
	private JButton good_info; //商品信息
	private JButton soft_info; //主页
	private JButton[] good_all;
	public LeftMess(int a)
	{
		this.setBounds(200, 200, 550, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setTitle("天下一家商品管理中心");
		
		//商品管理按钮
		good_manager = new JButton("商品管理");
		good_manager.setBounds(10, 300, 100, 40);
		this.add(good_manager);
		
		//分类管理按钮
		type_manager = new JButton("分类管理");
		type_manager.setBounds(10, 76, 100, 40);
		this.add(type_manager);
		
		//添加商品按钮
		add_good = new JButton("添加商品");
		add_good.setBounds(10, 132, 100, 40);
		this.add(add_good);
		
		//分类信息按钮
		type_info = new JButton("分类信息");
		type_info.setBounds(10, 188, 100, 40);
		this.add(type_info);
		
		//分类信息按钮
		good_info = new JButton("商品信息");
		good_info.setBounds(10, 244, 100, 40);
		this.add(good_info);
		
		//软件信息按钮
		soft_info = new JButton("软件信息");
		soft_info.setBounds(10, 20, 100, 40);
		this.add(soft_info);
		right = new SoftInfo();
		j = right;
		j.setBounds(120, 0, 400, 400);
		this.add(j);
		if(a == 0){
			this.remove(add_good);
			this.remove(good_manager);
			this.remove(type_manager);
			type_info.setBounds(10, 76, 100, 40);
			good_info.setBounds(10, 132, 100, 40);
			
		}
		good_all = new JButton[]{good_manager,type_manager,add_good,type_info, good_info,soft_info};
		for(int i = 0; i < good_all.length; i++){
			good_all[i].setBackground(new Color(229, 226, 226));
		}
		good_all[5].setBackground(Color.white);
		for(int i = 0; i < good_all.length; i++){
			final JButton temp_jb = good_all[i];
			final int index = i;
			temp_jb.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					switch(index){
					case 5:
						changeUI(new SoftInfo());
						break;
					case 3:
						changeUI(new TypeInfo(new TypeDaoImpl().selectAllType()));
						break;
					case 1:
						changeUI(new TypeMana());
						break;
					case 4:
						changeUI(new GoodsMess(new GoodDaoImpl().selectAllType()));
						break;
					case 0:
						changeUI(new GoodsManas());
						break;
					case 2:
						changeUI(new AddGoods());
						break;
					}
				changeBg(temp_jb);
				}
			});
		}
	}
	public void changeBg(JButton j){
		for(int i = 0; i < good_all.length; i++){
			good_all[i].setBackground(new Color(229, 226, 226));
		}
		j.setBackground(Color.white);
		repaint();
		this.setVisible(true);
	}
	public void changeUI(JPanel p)
	{
		this.remove(j);
		this.add(p);
		p.setBounds(120, 0, 400, 400);
		j = p;
	}
	//查询类别列表
//	public static void main(String[] args) {
//		new LeftMess(0).setVisible(true);
//	}
		
}
