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
	private JTextField textField; //商品名
	private JTextField textField_1; //数量
	private JTextField textField_2; //单价
	private final ButtonGroup buttonGroup = new ButtonGroup();
	String gname = ""; //商品名
	JLabel lblNewLabel_3; //提示
	int gnum = -1; //数量
	double gprice = -1; //单价
	int state; //是否上架
	String [] chooseTYpe;//下拉列表值
	JComboBox comboBox; //下拉列表
	String gtype; //商品类别
	public AddGoods()
	{

		this.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("添加商品");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel.setBounds(123, 10, 83, 30);
		this.add(lblNewLabel);
		
		JLabel label = new JLabel("商品名");
		label.setBounds(46, 71, 54, 15);
		this.add(label);
		
		textField = new JTextField();
		textField.setBounds(158, 68, 154, 21);
		this.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("数量");
		lblNewLabel_1.setBounds(46, 110, 54, 15);
		this.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(158, 107, 154, 21);
		this.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_1 = new JLabel("单价");
		label_1.setBounds(46, 141, 54, 15);
		this.add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(158, 138, 154, 21);
		this.add(textField_2);
		textField_2.setColumns(10);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("是");
		rdbtnNewRadioButton.setSelected(true);
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(158, 229, 48, 23);
		this.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("否");
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(211, 229, 48, 23);
		this.add(rdbtnNewRadioButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("是否上架");
		lblNewLabel_2.setBounds(46, 233, 54, 15);
		this.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("检查商品是否可添加");
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

		//给所有的设置焦点 改变时间
		JTextField [] arrJText = {textField,textField_1,textField_2};
		for(int i = 0;i < arrJText.length; i++) {
			arrJText[i].addFocusListener(new FocusListener() {
			    public void focusLost(FocusEvent e) {//失去焦点时
			    	textField.setBorder(BorderFactory.createLineBorder(new Color(146, 146, 146)));
			    }
			    public void focusGained(FocusEvent e) {//获得焦点时
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
			        // 当textField内容增加时触发    
			        // 对应到用户行为是用户键入内容
			    	lblNewLabel_3.setText("");
			    }

			});
		}
		JButton btnNewButton_1 = new JButton("确认");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkTname()) {
					String tep = "";
					if(state == 0){
						tep = "不上架";
					}else{
						tep = "上架";
					}
				   	int a = JOptionPane.showConfirmDialog(AddGoods.this, "是否添加商品：" + gname  + tep + "数量为 " + gnum + "单价为 " + gprice, "是否添加", JOptionPane.YES_NO_OPTION);
//			    	System.out.println(a);
			    	  if(a==JOptionPane.YES_OPTION){ 
			    		    //System.out.println("选择是后执行的代码");
			    		    if(new GoodDaoImpl().addGoods(gname, gprice, gtype, gnum, state)){
			    		    	JOptionPane.showMessageDialog(AddGoods.this, 
			    		    			"添加成功！", "系统信息", JOptionPane.INFORMATION_MESSAGE);
			    		    }else{
				    		    // System.out.println("选择否后执行的代码");    //点击“否”后执行这个代码块
				    		     JOptionPane.showMessageDialog(AddGoods.this, 
				    		    			"添加失败！", "系统信息", JOptionPane.INFORMATION_MESSAGE);
				    		} 
			    		    }
				}
			}
		});
		btnNewButton_1.setBounds(39, 307, 93, 23);
		this.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("取消");
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
	    			"类别为空！", "系统信息", JOptionPane.INFORMATION_MESSAGE);
		}else {
			comboBox.setModel(new DefaultComboBoxModel(chooseTYpe));	
		}
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(158, 190, 154, 21);
		this.add(comboBox);
		
		JLabel lblNewLabel_4 = new JLabel("商品类别");
		lblNewLabel_4.setBounds(46, 193, 54, 15);
		this.add(lblNewLabel_4);
		
	}
	public boolean checkTname(){ //确认用户名是否可用
		//获取姓名
		gname = textField.getText().trim();
		if(gname == null || gname.equals("")){
			lblNewLabel_3.setText("你输入的商品名为空");
			lblNewLabel_3.setForeground(Color.red);
			textField.requestFocus();
			return false;
		}else if(gname.length() > 10){
			lblNewLabel_3.setText("你输入的类别名太长了");
			lblNewLabel_3.setForeground(Color.red);
			textField.requestFocus();
			return false;
		}else if(new GoodDaoImpl().chackByTname(gname)){
			lblNewLabel_3.setText("你输入的类别名已存在");
			lblNewLabel_3.setForeground(Color.red);	
			textField.requestFocus();
			return false;
		}
		String tepnum = textField_1.getText().trim();
	     if(tepnum == null || tepnum.equals("") || !tepnum.matches("^[0-9]*$"))  {
	    	 lblNewLabel_3.setText("你输入的数量有误");
	    	 lblNewLabel_3.setForeground(Color.red);	
	    	 textField_1.requestFocus();
	    	 return false;
	    	}else {
	    		 gnum = Integer.parseInt(tepnum);
	    	}
	     if(gnum < 0) {
	    	 lblNewLabel_3.setText("你添加的数量为负");
	    	 lblNewLabel_3.setForeground(Color.red);	
	    	 textField_1.requestFocus();
	    	 return false;
	    	} 
	     
	     String sgprice = textField_2.getText().trim();

	     if(sgprice == null || sgprice.equals("") || !sgprice.matches("^[0-9/.]*$"))  {	 
	    		 lblNewLabel_3.setText("你输入的价格有误");
		    	 lblNewLabel_3.setForeground(Color.red);	
		    	 textField_2.requestFocus();
		    	 return false;
	    	}else {
	    		 gprice = format2(Double.parseDouble(sgprice));
	    	}
	     if(gprice < 0) {
	    	 lblNewLabel_3.setText("你输入的价格有误");
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
		if(chooseUse.equals("是")){
			state = 1;
		}else{
			state = 0;
		}
		gtype = chooseTYpe[comboBox.getSelectedIndex()];
		lblNewLabel_3.setText("你添加的商品可以添加");
   	 	lblNewLabel_3.setForeground(new Color(23, 133,218));	
		return true;
		 
	}
	public static double format2(double value) {
		 
		 DecimalFormat df = new DecimalFormat("0.00");
		 df.setRoundingMode(RoundingMode.HALF_UP);
		 return Double.parseDouble(df.format(value));
		}
}
