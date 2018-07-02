package com.chinasoft.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import com.chinasoft.entity.Goods;
import com.chinasoft.entity.Types;

public class GoodsMess extends JPanel {

	private JButton up;
	private JButton down;
	
	private JTable jt;
	private JPanel temp;
	
	private JScrollPane js;
	private int id;
	
	public GoodsMess(List<Goods> list) //��Ʒ��Ϣ
	{
		this.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("��Ʒ��Ϣ");
		lblNewLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		lblNewLabel.setBounds(120, 23, 85, 46);
		this.add(lblNewLabel);
		js = new JScrollPane();
		//������
		String[] col = {"��Ʒ���","��Ʒ����","���","����","����","�Ƿ��ϼ�"};
		Object[][] data = new Object[list.size()][col.length];
		for(int i = 0;i < data.length;i++){
			data[i][0] = list.get(i).getId();
			data[i][1] = list.get(i).getGname();
			data[i][2] = list.get(i).getGtype();
			data[i][3] = list.get(i).getGnum();
			data[i][4] = list.get(i).getGprice();
			int state = list.get(i).getState();
			if(state == 1){
				data[i][5] = "��";
			}else{
				data[i][5] = "��";
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

