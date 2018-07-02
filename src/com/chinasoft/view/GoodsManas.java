package com.chinasoft.view;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.chinasoft.dao.impl.GoodDaoImpl;
import com.chinasoft.dao.impl.TypeDaoImpl;
import com.chinasoft.entity.Goods;
import com.chinasoft.entity.Types;

public class GoodsManas  extends JPanel {


	private JTable jt;
	private JPanel temp;
	
	private JScrollPane js;
	private int id;
	static List<Goods> list = null;
	static DefaultTableModel dtm;
	public GoodsManas(){
		list = new GoodDaoImpl().selectAllType();
		this.setLayout(null);
		JLabel lblNewLabel = new JLabel("��Ʒ��Ϣ�༭");
		lblNewLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		lblNewLabel.setBounds(100, 23, 120, 46);
		this.add(lblNewLabel);
		js = new JScrollPane();
		//�޸����¼�
		JButton btnNewButton = new JButton("�޸����¼�");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 int row = jt.getSelectedRow();
				 if(row == -1) {
					 JOptionPane.showMessageDialog(GoodsManas.this, "����ѡ����Ҫ�޸ĵ����ݣ�", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
				 }else {
					 String gname = (String) jt.getValueAt(row, 1);
					 String state = (String) jt.getValueAt(row, 5); 
					 int temState = 1;
					 String temStateInfo;
					 if(state.equals("��")) {
						 temStateInfo = "�¼�";
						 temState = 0;
					 }else {
						 temStateInfo = "�ϼ�";
						 temState = 1;
					 }
					 int a = JOptionPane.showConfirmDialog(GoodsManas.this, "�Ƿ�" + temStateInfo + "��Ʒ" + gname, "�Ƿ��޸�״̬", JOptionPane.YES_NO_OPTION);
////			    	System.out.println(a);
			    	  if(a==JOptionPane.YES_OPTION){ 
			    		    //System.out.println("ѡ���Ǻ�ִ�еĴ���");
			    		    if(new GoodDaoImpl().changeStateByName(temState, gname)){
			    		    	JOptionPane.showMessageDialog(GoodsManas.this, 
			    		    			"�޸ĳɹ���", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
			    		    	changeJt();
			    		    }else{
				    		    // System.out.println("ѡ����ִ�еĴ���");    //������񡱺�ִ����������
				    		     JOptionPane.showMessageDialog(GoodsManas.this, 
				    		    			"�޸�ʧ�ܣ�", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
				    		} 
			    		    }
				 }
			}
		});
		btnNewButton.setBounds(0, 300, 100, 23);
		this.add(btnNewButton);
		
		//�Զ��¼�
		JButton btnNewButton_1 = new JButton("�޸���Ʒ��");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int row = jt.getSelectedRow();
				 if(row == -1) {
					 JOptionPane.showMessageDialog(GoodsManas.this, "����ѡ����Ҫ�޸ĵ����ݣ�", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
				 }else {
					 String gname = (String) jt.getValueAt(row, 1);
				    String inputValue = JOptionPane.showInputDialog(GoodsManas.this, "���������µ���Ʒ��");
				    	if(inputValue != null && inputValue.equals("")) {
				    		JOptionPane.showMessageDialog(GoodsManas.this, "���������Ʒ��Ϊ��", "ϵͳ��Ϣ", JOptionPane.ERROR_MESSAGE);
				    	}else if(inputValue != null &&inputValue.length() > 20) {
				    		JOptionPane.showMessageDialog(GoodsManas.this, "���������Ʒ��̫����", "ϵͳ��Ϣ", JOptionPane.ERROR_MESSAGE);
				    	}else if(new GoodDaoImpl().chackByTname(inputValue)){
				    		JOptionPane.showMessageDialog(GoodsManas.this, "���������Ʒ���Ѵ���", "ϵͳ��Ϣ", JOptionPane.ERROR_MESSAGE);
				    	
				    	}
				    	else{
				    		int a = JOptionPane.showConfirmDialog(GoodsManas.this, "�Ƿ��޸���Ʒ����" + gname + "Ϊ" + inputValue , "�޸�����", JOptionPane.YES_NO_OPTION);
				    		 if(a==JOptionPane.YES_OPTION){ 
					    		    //System.out.println("ѡ���Ǻ�ִ�еĴ���");
					    		    if(new GoodDaoImpl().renameByName(gname, inputValue)){
					    		    	JOptionPane.showMessageDialog(GoodsManas.this, 
					    		    			"�޸ĳɹ���", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
					    		    	changeJt();
					    		    }else{					    		    // System.out.println("ѡ����ִ�еĴ���");    //������񡱺�ִ����������
						    		     JOptionPane.showMessageDialog(GoodsManas.this, 
						    		    			"�޸�ʧ�ܣ�", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
						    		}
					    		    }
				    	}
				    }
					}
				});
		btnNewButton_1.setBounds(105, 300, 100, 23);
		this.add(btnNewButton_1);
		//�޸�����
		JButton btnNewButton_2 = new JButton("�޸�����");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int row = jt.getSelectedRow();
				 if(row == -1) {
					 JOptionPane.showMessageDialog(GoodsManas.this, "����ѡ����Ҫ�޸ĵ����ݣ�", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
				 }else {
					 String gname = (String) jt.getValueAt(row, 1);
					 String tepnum =JOptionPane.showInputDialog(GoodsManas.this, "�����������ӵ�����");

					 int inputValue = -1;
				     if(tepnum == null || tepnum.equals("") || !tepnum.matches("^[0-9]*$"))  {
				    		JOptionPane.showMessageDialog(GoodsManas.this, "���������������", "ϵͳ��Ϣ", JOptionPane.ERROR_MESSAGE);
				    		return;
				    	}else {
				    		 inputValue = Integer.parseInt(tepnum);
				    	}
				     if(inputValue < 0) {
				    		JOptionPane.showMessageDialog(GoodsManas.this, "�����������Ϊ��", "ϵͳ��Ϣ", JOptionPane.ERROR_MESSAGE);
				    	}else{
				    		int a = JOptionPane.showConfirmDialog(GoodsManas.this, "�Ƿ��޸���Ʒ����" + gname + "������Ϊ" + inputValue , "ϵͳ��Ϣ", JOptionPane.YES_NO_OPTION);
				    		 if(a==JOptionPane.YES_OPTION){ 
					    		    //System.out.println("ѡ���Ǻ�ִ�еĴ���");
					    		    if(new GoodDaoImpl().changeTnum(gname, inputValue)){
					    		    	JOptionPane.showMessageDialog(GoodsManas.this, 
					    		    			"�޸ĳɹ���", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
					    		    	changeJt();
					    		    }else{					    		    // System.out.println("ѡ����ִ�еĴ���");    //������񡱺�ִ����������
						    		     JOptionPane.showMessageDialog(GoodsManas.this, 
						    		    			"�޸�ʧ�ܣ�", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
						    		}
					    		    }
				    	}
				    }
				
				
			}
		});
		btnNewButton_2.setBounds(210, 300, 90, 23);
		this.add(btnNewButton_2);
		
		//�޸ļ۸�
		JButton btnNewButton_3 = new JButton("�޸ļ۸�");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = jt.getSelectedRow();
				 if(row == -1) {
					 JOptionPane.showMessageDialog(GoodsManas.this, "����ѡ����Ҫ�޸ĵ����ݣ�", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
				 }else {
					 String gname = (String) jt.getValueAt(row, 1);
					 String tepnum =JOptionPane.showInputDialog(GoodsManas.this, "���������µļ۸�");

					 double inputValue = -1;
				     if(tepnum == null || tepnum.equals("") || !tepnum.matches("^[0-9/.]*$"))  {
				    		JOptionPane.showMessageDialog(GoodsManas.this, "������ļ۸�����", "ϵͳ��Ϣ", JOptionPane.ERROR_MESSAGE);
				    		return;
				    	}else {
				    		 inputValue = format2(Double.parseDouble(tepnum));
				    	}
				     if(inputValue < 0) {
				    		JOptionPane.showMessageDialog(GoodsManas.this, "������ļ۸�С��0", "ϵͳ��Ϣ", JOptionPane.ERROR_MESSAGE);
				    	}else{
				    		int a = JOptionPane.showConfirmDialog(GoodsManas.this, "�Ƿ��޸���Ʒ����" + gname + "�ĵ���Ϊ" + inputValue , "ϵͳ��Ϣ", JOptionPane.YES_NO_OPTION);
				    		 if(a==JOptionPane.YES_OPTION){ 
					    		    //System.out.println("ѡ���Ǻ�ִ�еĴ���");
					    		    if(new GoodDaoImpl().changeGprice(gname, inputValue)){
					    		    	JOptionPane.showMessageDialog(GoodsManas.this, 
					    		    			"�޸ĳɹ���", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
					    		    	changeJt();
					    		    }else{					    		    // System.out.println("ѡ����ִ�еĴ���");    //������񡱺�ִ����������
						    		     JOptionPane.showMessageDialog(GoodsManas.this, 
						    		    			"�޸�ʧ�ܣ�", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
						    		}
					    		    }
				    	}
				    }
				
			}
		});
		btnNewButton_3.setBounds(305, 300, 90, 23);
		this.add(btnNewButton_3);
		
		//������
		String[] col = {"��Ʒ���","��Ʒ����","����","����","����","�Ƿ��ϼ�"};
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
		
//		jt = new JTable(data,col);
		
		
		dtm = new DefaultTableModel(data,col);//������ģ��

		jt = new JTable(dtm);//���ݱ��ģ�ʹ������
		jt.setRowHeight(30);
		jt.setDefaultEditor(Object.class, new Myedit());
		jt.setRowHeight(1,40);
		js = new JScrollPane(jt);
		js.setBounds(0, 80, 400, 200);
		this.add(js);				
		
	}


	public static void  changeJt(){ //�ػ����
		list = new GoodDaoImpl().selectAllType();
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
		dtm.setDataVector(data, col);
	}
	public static double format2(double value) {
		 
		 DecimalFormat df = new DecimalFormat("0.00");
		 df.setRoundingMode(RoundingMode.HALF_UP);
		 return Double.parseDouble(df.format(value));
		}

}
