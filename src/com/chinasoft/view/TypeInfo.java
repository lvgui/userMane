package com.chinasoft.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.chinasoft.entity.Goods;
import com.chinasoft.entity.Types;

public class TypeInfo extends JPanel {
	
	private JTable jt;
	private JPanel temp;
	
	private JScrollPane js;
	private int id;
	public TypeInfo(){
			
	}
	public TypeInfo(List<Types> list){
		this.setLayout(null);
		JLabel lblNewLabel = new JLabel("\u5206\u7C7B\u4FE1\u606F");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel.setBounds(120, 23, 85, 46);
		this.add(lblNewLabel);
		js = new JScrollPane();
		//表格组件
		String[] col = {"列表编号","列表名称","是否显示"};
		Object[][] data = new Object[list.size()][col.length];
		for(int i = 0;i < data.length;i++){
			data[i][0] = list.get(i).getTid();
			data[i][1] = list.get(i).getTname();
			int state = list.get(i).getState();
			if(state == 1){
				data[i][2] = "是";
			}else{
				data[i][2] = "否";
			}
		}
		jt = new JTable(data,col);
		jt.setRowHeight(30);
		jt.setDefaultEditor(Object.class, new Myedit());
		jt.setRowHeight(1,40);
		js = new JScrollPane(jt);
		js.setBounds(0, 80, 400, 260);
		this.add(js);
	}
}
