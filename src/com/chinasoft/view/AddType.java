package com.chinasoft.view;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.chinasoft.dao.impl.TypeDaoImpl;
import com.chinasoft.dao.impl.UserDaoImpl;
import com.chinasoft.entity.Users;

public class AddType extends Popup {
	private JTextField textField; // �������
	JLabel lblNewLabel_1; //��ʾ
	 String tname;
	 int  state; 
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public AddType(Point point,int partentWidth,int partentHeight,int width,int height,String title) {
		super(point,partentWidth,partentHeight,width,height,title);
		// TODO Auto-generated constructor stub
		JLabel lblNewLabel = new JLabel("������Ҫ��ӵ����");
		lblNewLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		lblNewLabel.setBounds(20, 29, 188, 27);
		this.add(lblNewLabel);
		//�����
		textField = new JTextField();
		textField.setBounds(20, 66, 163, 27);
		this.add(textField);
		textField.setColumns(10);
		 //��ý����¼�
		textField.addFocusListener(new FocusListener() {
		    public void focusLost(FocusEvent e) {//ʧȥ����ʱ
		    	textField.setBorder(BorderFactory.createLineBorder(new Color(146, 146, 146)));
		    }
		    public void focusGained(FocusEvent e) {//��ý���ʱ
		    	textField.setBorder(BorderFactory.createLineBorder(new Color(11, 85, 209)));
		 
		    }
		   
		});
		//ֵ�ı��¼�
		//�û���������
		textField.getDocument().addDocumentListener(new DocumentListener(){
		    public void changedUpdate(DocumentEvent e){
		    }
		    public void removeUpdate(DocumentEvent e){
		    	lblNewLabel_1.setText("");
		    }
		    public void insertUpdate(DocumentEvent e){
		        // ��textField��������ʱ����    
		        // ��Ӧ���û���Ϊ���û���������
		    	lblNewLabel_1.setText("");
		    }

		});
		
		JButton btnNewButton = new JButton("���������Ƿ����");
		btnNewButton.setBounds(20, 138, 136, 27);
		this.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkTname();
			}
		});
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(20, 186, 204, 15);
		this.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("ȷ��");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tep = "";
				if(state == 0){
					tep = "��ʹ��";
				}else{
					tep = "ʹ��";
				}
				if(checkTname()){
//					System.out.println(tname);
				   	int a = JOptionPane.showConfirmDialog(AddType.this, "�Ƿ�������У�" + tname + "Ĭ��Ϊ" + tep, "�Ƿ����", JOptionPane.YES_NO_OPTION);
//			    	System.out.println(a);
			    	  if(a==JOptionPane.YES_OPTION){ 
			    		    //System.out.println("ѡ���Ǻ�ִ�еĴ���");
			    		    if(new TypeDaoImpl().addType(tname, state)){
			    		    	JOptionPane.showMessageDialog(AddType.this, 
			    		    			"��ӳɹ���", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
			    		    	TypeMana.changeJt();
			    		    }else{
				    		    // System.out.println("ѡ����ִ�еĴ���");    //������񡱺�ִ����������
				    		     JOptionPane.showMessageDialog(AddType.this, 
				    		    			"���ʧ�ܣ�", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
				    		} 
			    		    }
				}
			}
		});
		btnNewButton_1.setBounds(20, 211, 66, 23);
		this.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("ȡ��");
		btnNewButton_2.setBounds(110, 211, 66, 23);
		this.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() { //ȡ���¼�
			public void actionPerformed(ActionEvent e) {
				AddType.this.setVisible(false);
			}
		});
		
		JLabel lblNewLabel_2 = new JLabel("�Ƿ���ʾ");
		lblNewLabel_2.setBounds(20, 103, 54, 23);
		this.add(lblNewLabel_2);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("��");
		rdbtnNewRadioButton.setSelected(true);
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(88, 107, 42, 23);
		this.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("��");
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(132, 107, 42, 23);
		this.add(rdbtnNewRadioButton_1);
		textField.setFocusable(true);
		this.setVisible(true);
	}
	public boolean checkTname(){ //ȷ���û����Ƿ����
		//��ȡ���õ�ʹ�û��߲�ʹ�õ�ֵ
		String chooseUse = "false";
				Enumeration<AbstractButton> radioBtns= buttonGroup.getElements();  
				while (radioBtns.hasMoreElements()) {  
				    AbstractButton btn = radioBtns.nextElement();  
				    if(btn.isSelected()){  
				    	chooseUse = btn.getText();  
				        break;  
				    }  
				} 
//				System.out.println(enable);
				if(chooseUse.equals("��")){
					state = 1;
				}else{
					state = 0;
				}
		tname = textField.getText().trim();
		if(tname == null || tname.equals("")){
			lblNewLabel_1.setText("������������Ϊ��");
			lblNewLabel_1.setForeground(Color.red);
			textField.requestFocus();
			return false;
		}else if(tname.length() > 10){
			lblNewLabel_1.setText("������������̫����");
			lblNewLabel_1.setForeground(Color.red);
			textField.requestFocus();
			return false;
		}else if(new TypeDaoImpl().chackByTname(tname)){
			lblNewLabel_1.setText("�������������Ѵ���");
			lblNewLabel_1.setForeground(Color.red);	
			textField.requestFocus();
			return false;
		}else{
			lblNewLabel_1.setText("������������������");
			lblNewLabel_1.setForeground(new Color(23, 133,218));
			return true;
		}
		 
	}

}
