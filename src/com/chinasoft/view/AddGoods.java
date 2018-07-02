package com.chinasoft.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.chinasoft.dao.impl.GoodDaoImpl;
import com.chinasoft.dao.impl.TypeDaoImpl;

public class AddGoods extends JPanel {
	private JTextField textField; //��Ʒ��
	private JTextField textField_1; //����
	private JTextField textField_2; //����
	private final ButtonGroup buttonGroup = new ButtonGroup();
	String gname = ""; //��Ʒ��
	JLabel lblNewLabel_3; //��ʾ
	int gnum = -1; //����
	double gprice = -1; //����
	int state; //�Ƿ��ϼ�
	String [] chooseTYpe;//�����б�ֵ
	JComboBox comboBox; //�����б�
	String gtype; //��Ʒ���
	public AddGoods()
	{

		this.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("�����Ʒ");
		lblNewLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		lblNewLabel.setBounds(123, 10, 83, 30);
		this.add(lblNewLabel);
		
		JLabel label = new JLabel("��Ʒ��");
		label.setBounds(46, 71, 54, 15);
		this.add(label);
		
		textField = new JTextField();
		textField.setBounds(158, 68, 154, 21);
		this.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("����");
		lblNewLabel_1.setBounds(46, 110, 54, 15);
		this.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(158, 107, 154, 21);
		this.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_1 = new JLabel("����");
		label_1.setBounds(46, 141, 54, 15);
		this.add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(158, 138, 154, 21);
		this.add(textField_2);
		textField_2.setColumns(10);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("��");
		rdbtnNewRadioButton.setSelected(true);
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(158, 229, 48, 23);
		this.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("��");
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(211, 229, 48, 23);
		this.add(rdbtnNewRadioButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("�Ƿ��ϼ�");
		lblNewLabel_2.setBounds(46, 233, 54, 15);
		this.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("�����Ʒ�Ƿ�����");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkTname();
			}
		});
		btnNewButton.setBounds(29, 274, 141, 23);
		this.add(btnNewButton);
		
		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(180, 278, 200, 15);
		this.add(lblNewLabel_3);

		//�����е����ý��� �ı�ʱ��
		JTextField [] arrJText = {textField,textField_1,textField_2};
		for(int i = 0;i < arrJText.length; i++) {
			arrJText[i].addFocusListener(new FocusListener() {
			    public void focusLost(FocusEvent e) {//ʧȥ����ʱ
			    	textField.setBorder(BorderFactory.createLineBorder(new Color(146, 146, 146)));
			    }
			    public void focusGained(FocusEvent e) {//��ý���ʱ
			    	textField.setBorder(BorderFactory.createLineBorder(new Color(11, 85, 209)));
			 
			    }
			   
			});
			arrJText[i].getDocument().addDocumentListener(new DocumentListener(){
			    public void changedUpdate(DocumentEvent e){
			    }
			    public void removeUpdate(DocumentEvent e){
			    	lblNewLabel_3.setText("");
			    }
			    public void insertUpdate(DocumentEvent e){
			        // ��textField��������ʱ����    
			        // ��Ӧ���û���Ϊ���û���������
			    	lblNewLabel_3.setText("");
			    }

			});
		}
		JButton btnNewButton_1 = new JButton("ȷ��");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkTname()) {
					String tep = "";
					if(state == 0){
						tep = "���ϼ�";
					}else{
						tep = "�ϼ�";
					}
				   	int a = JOptionPane.showConfirmDialog(AddGoods.this, "�Ƿ������Ʒ��" + gname  + tep + "����Ϊ " + gnum + "����Ϊ " + gprice, "�Ƿ����", JOptionPane.YES_NO_OPTION);
//			    	System.out.println(a);
			    	  if(a==JOptionPane.YES_OPTION){ 
			    		    //System.out.println("ѡ���Ǻ�ִ�еĴ���");
			    		    if(new GoodDaoImpl().addGoods(gname, gprice, gtype, gnum, state)){
			    		    	JOptionPane.showMessageDialog(AddGoods.this, 
			    		    			"��ӳɹ���", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
			    		    }else{
				    		    // System.out.println("ѡ����ִ�еĴ���");    //������񡱺�ִ����������
				    		     JOptionPane.showMessageDialog(AddGoods.this, 
				    		    			"���ʧ�ܣ�", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
				    		} 
			    		    }
				}
			}
		});
		btnNewButton_1.setBounds(39, 307, 93, 23);
		this.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("ȡ��");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddGoods.this.setVisible(false);
			}
		});
		btnNewButton_2.setBounds(158, 307, 93, 23);
		this.add(btnNewButton_2);
		 chooseTYpe = new TypeDaoImpl().selectAllTypeC();
		comboBox = new JComboBox();
		if(chooseTYpe == null ||chooseTYpe.length == 0) {
			JOptionPane.showMessageDialog(AddGoods.this, 
	    			"���Ϊ�գ�", "ϵͳ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
		}else {
			comboBox.setModel(new DefaultComboBoxModel(chooseTYpe));	
		}
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(158, 190, 154, 21);
		this.add(comboBox);
		
		JLabel lblNewLabel_4 = new JLabel("��Ʒ���");
		lblNewLabel_4.setBounds(46, 193, 54, 15);
		this.add(lblNewLabel_4);
		
	}
	public boolean checkTname(){ //ȷ���û����Ƿ����
		//��ȡ����
		gname = textField.getText().trim();
		if(gname == null || gname.equals("")){
			lblNewLabel_3.setText("���������Ʒ��Ϊ��");
			lblNewLabel_3.setForeground(Color.red);
			textField.requestFocus();
			return false;
		}else if(gname.length() > 10){
			lblNewLabel_3.setText("������������̫����");
			lblNewLabel_3.setForeground(Color.red);
			textField.requestFocus();
			return false;
		}else if(new GoodDaoImpl().chackByTname(gname)){
			lblNewLabel_3.setText("�������������Ѵ���");
			lblNewLabel_3.setForeground(Color.red);	
			textField.requestFocus();
			return false;
		}
		String tepnum = textField_1.getText().trim();
	     if(tepnum == null || tepnum.equals("") || !tepnum.matches("^[0-9]*$"))  {
	    	 lblNewLabel_3.setText("���������������");
	    	 lblNewLabel_3.setForeground(Color.red);	
	    	 textField_1.requestFocus();
	    	 return false;
	    	}else {
	    		 gnum = Integer.parseInt(tepnum);
	    	}
	     if(gnum < 0) {
	    	 lblNewLabel_3.setText("����ӵ�����Ϊ��");
	    	 lblNewLabel_3.setForeground(Color.red);	
	    	 textField_1.requestFocus();
	    	 return false;
	    	} 
	     
	     String sgprice = textField_2.getText().trim();

	     if(sgprice == null || sgprice.equals("") || !sgprice.matches("^[0-9/.]*$"))  {	 
	    		 lblNewLabel_3.setText("������ļ۸�����");
		    	 lblNewLabel_3.setForeground(Color.red);	
		    	 textField_2.requestFocus();
		    	 return false;
	    	}else {
	    		 gprice = format2(Double.parseDouble(sgprice));
	    	}
	     if(gprice < 0) {
	    	 lblNewLabel_3.setText("������ļ۸�����");
	    	 lblNewLabel_3.setForeground(Color.red);	
	    	 textField_2.requestFocus();
	    	}
	     String chooseUse = "false";
		Enumeration<AbstractButton> radioBtns= buttonGroup.getElements();  
		while (radioBtns.hasMoreElements()) {  
		    AbstractButton btn = radioBtns.nextElement();  
		    if(btn.isSelected()){  
		    	chooseUse = btn.getText();  
		        break;  
		    }  
		} 
//			System.out.println(enable);
		if(chooseUse.equals("��")){
			state = 1;
		}else{
			state = 0;
		}
		gtype = chooseTYpe[comboBox.getSelectedIndex()];
		lblNewLabel_3.setText("����ӵ���Ʒ�������");
   	 	lblNewLabel_3.setForeground(new Color(23, 133,218));	
		return true;
		 
	}
	public static double format2(double value) {
		 
		 DecimalFormat df = new DecimalFormat("0.00");
		 df.setRoundingMode(RoundingMode.HALF_UP);
		 return Double.parseDouble(df.format(value));
		}
}
