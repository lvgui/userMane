package com.chinasoft.view;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.chinasoft.dao.impl.TypeDaoImpl;
import com.chinasoft.entity.Types;

public class TypeMana extends JPanel {

	private JTable jt;
	private JPanel temp;
	
	private JScrollPane js;
	private int id;
	static List<Types> list = null;
	static DefaultTableModel dtm;
	public TypeMana(){
		list = new TypeDaoImpl().selectAllType();
		this.setLayout(null);
		JLabel lblNewLabel = new JLabel("�б���Ϣ�༭");
		lblNewLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		lblNewLabel.setBounds(60, 23, 120, 46);
		this.add(lblNewLabel);
		JButton add_type = new JButton("����·���");
		add_type.setBounds(200, 28, 100, 36);
		this.add(add_type);
		add_type.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Point point  = TypeMana.this.getParent().getLocationOnScreen();
				new AddType(point,TypeMana.this.getWidth() + 200,TypeMana.this.getHeight(),300,300,"������");
			}
		});
		js = new JScrollPane();
		//������
		String[] col = {"�б�����","�Ƿ�ʹ��","ɾ��","�޸�״̬","������"};
		Object[][] data = new Object[list.size()][col.length];
		for(int i = 0;i < data.length;i++){
			data[i][0] = list.get(i).getTname();
			int state = list.get(i).getState();
			if(state == 1){
				data[i][1] = "��";
			}else{
				data[i][1] = "��";
			}
			data[i][2] = "ɾ�������";
			data[i][3] = "�޸�״̬";
			data[i][4] = "���������";
		}
		
//		jt = new JTable(data,col);
		
		
		dtm = new DefaultTableModel(data,col);//������ģ��

		jt = new JTable(dtm);//���ݱ��ģ�ʹ������
		jt.setRowHeight(30);
		jt.setDefaultEditor(Object.class, new Myedit());
		jt.setRowHeight(1,40);
		js = new JScrollPane(jt);
		js.setBounds(0, 80, 400, 260);
		this.add(js);
		
		
		//������¼�
		jt.addMouseListener(new MouseAdapter()
		{ 
			public void mouseClicked(MouseEvent e){
		    int  row =((JTable)e.getSource()).rowAtPoint(e.getPoint()); 
		    //�����λ�� 
		    int  col=((JTable)e.getSource()).columnAtPoint(e.getPoint()); 

		    if(col == 2){ //ɾ��
		    	String tname = (String) jt.getValueAt(row, 0);
		    	int a = JOptionPane.showConfirmDialog(TypeMana.this, "�Ƿ�ɾ�����У�" + tname, "�Ƿ�ɾ��", JOptionPane.YES_NO_OPTION);
//		    	System.out.println(a);
		    	  if(a==JOptionPane.YES_OPTION){ 
		    		    //System.out.println("ѡ���Ǻ�ִ�еĴ���");
		    		    if(new TypeDaoImpl().deleteByName(tname)){
		    		    	JOptionPane.showMessageDialog(TypeMana.this, 
		    		    			"ɾ���ɹ���", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
		    		    	changeJt();
		    		    }else{
			    		    // System.out.println("ѡ����ִ�еĴ���");    //������񡱺�ִ����������
			    		     JOptionPane.showMessageDialog(TypeMana.this, 
			    		    			"ɾ��ʧ�ܣ�", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
			    		} 
		    		    }
//		    	
		    }else if(col == 3){ //�޸�״̬
		    	String tname = (String) jt.getValueAt(row, 0);
		    	String tState = (String) jt.getValueAt(row, 1);
		    	int state = 1; //״̬
		    	String tib = "";
		    	if(tState.equals("��")){
		    		tib = "��ʹ��";
		    		state = 0;
		    	}else{
		    		tib = "ʹ��";
		    		state = 1;
		    	}
		    	int a = JOptionPane.showConfirmDialog(TypeMana.this, "�Ƿ��޸����" + tname + "Ϊ" + tib, "�Ƿ��޸�״̬", JOptionPane.YES_NO_OPTION);
//		    	System.out.println(a);
		    	  if(a==JOptionPane.YES_OPTION){ 
		    		    //System.out.println("ѡ���Ǻ�ִ�еĴ���");
		    		    if(new TypeDaoImpl().changeStateByName(state, tname)){
		    		    	JOptionPane.showMessageDialog(TypeMana.this, 
		    		    			"�޸ĳɹ���", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
		    		    	changeJt();
		    		    }else{
			    		    // System.out.println("ѡ����ִ�еĴ���");    //������񡱺�ִ����������
			    		     JOptionPane.showMessageDialog(TypeMana.this, 
			    		    			"�޸�ʧ�ܣ�", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
			    		} 
		    		    }
		    }else if(col == 4) { //������
		    	String tname = (String) jt.getValueAt(row, 0);
		    	String inputValue = JOptionPane.showInputDialog(TypeMana.this, "���������µ�������");
		    	if(inputValue != null && inputValue.equals("")) {
		    		JOptionPane.showMessageDialog(TypeMana.this, "������������Ϊ��", "ϵͳ��Ϣ", JOptionPane.ERROR_MESSAGE);
		    	}else if(inputValue != null &&inputValue.length() > 20) {
		    		JOptionPane.showMessageDialog(TypeMana.this, "������������̫����", "ϵͳ��Ϣ", JOptionPane.ERROR_MESSAGE);
		    	}else if(new TypeDaoImpl().chackByTname(inputValue)){
		    		JOptionPane.showMessageDialog(TypeMana.this, "�������������Ѵ���", "ϵͳ��Ϣ", JOptionPane.ERROR_MESSAGE);
		    	}else{
		    		int a = JOptionPane.showConfirmDialog(TypeMana.this, "�Ƿ��޸����" + tname + "Ϊ" + inputValue , "�޸�����", JOptionPane.YES_NO_OPTION);
		    		 if(a==JOptionPane.YES_OPTION){ 
//			    		    //System.out.println("ѡ���Ǻ�ִ�еĴ���");
			    		    if(new TypeDaoImpl().renameByName(tname, inputValue)){
			    		    	JOptionPane.showMessageDialog(TypeMana.this, 
			    		    			"�޸ĳɹ���", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
			    		    	changeJt();
			    		    }else{
			    		    // System.out.println("ѡ����ִ�еĴ���");    //������񡱺�ִ����������
			    		     JOptionPane.showMessageDialog(TypeMana.this, 
			    		    			"�޸�ʧ�ܣ�", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
			    		}
			    		    }
		    	}

		    }
			}
		});
		
		
	}


	public static void  changeJt(){ //�ػ����
		list = new TypeDaoImpl().selectAllType();
		String[] col = {"�б�����","�Ƿ�ʹ��","ɾ��","�޸�״̬","������"};
		Object[][] data = new Object[list.size()][col.length];
		for(int i = 0;i < data.length;i++){
			data[i][0] = list.get(i).getTname();
			int state = list.get(i).getState();
			if(state == 1){
				data[i][1] = "��";
			}else{
				data[i][1] = "��";
			}
			data[i][2] = "ɾ�������";
			data[i][3] = "�޸�״̬";
			data[i][4] = "���������";
		}
		dtm.setDataVector(data, col);
	}
}
