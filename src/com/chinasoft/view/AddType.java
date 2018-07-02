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
	private JTextField textField; // 输入类别
	JLabel lblNewLabel_1; //提示
	 String tname;
	 int  state; 
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public AddType(Point point,int partentWidth,int partentHeight,int width,int height,String title) {
		super(point,partentWidth,partentHeight,width,height,title);
		// TODO Auto-generated constructor stub
		JLabel lblNewLabel = new JLabel("输入你要添加的类别");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel.setBounds(20, 29, 188, 27);
		this.add(lblNewLabel);
		//输入框
		textField = new JTextField();
		textField.setBounds(20, 66, 163, 27);
		this.add(textField);
		textField.setColumns(10);
		 //获得焦点事件
		textField.addFocusListener(new FocusListener() {
		    public void focusLost(FocusEvent e) {//失去焦点时
		    	textField.setBorder(BorderFactory.createLineBorder(new Color(146, 146, 146)));
		    }
		    public void focusGained(FocusEvent e) {//获得焦点时
		    	textField.setBorder(BorderFactory.createLineBorder(new Color(11, 85, 209)));
		 
		    }
		   
		});
		//值改变事件
		//用户按键监听
		textField.getDocument().addDocumentListener(new DocumentListener(){
		    public void changedUpdate(DocumentEvent e){
		    }
		    public void removeUpdate(DocumentEvent e){
		    	lblNewLabel_1.setText("");
		    }
		    public void insertUpdate(DocumentEvent e){
		        // 当textField内容增加时触发    
		        // 对应到用户行为是用户键入内容
		    	lblNewLabel_1.setText("");
		    }

		});
		
		JButton btnNewButton = new JButton("检测类别名是否可用");
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
		
		JButton btnNewButton_1 = new JButton("确认");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tep = "";
				if(state == 0){
					tep = "不使用";
				}else{
					tep = "使用";
				}
				if(checkTname()){
//					System.out.println(tname);
				   	int a = JOptionPane.showConfirmDialog(AddType.this, "是否添加类列：" + tname + "默认为" + tep, "是否添加", JOptionPane.YES_NO_OPTION);
//			    	System.out.println(a);
			    	  if(a==JOptionPane.YES_OPTION){ 
			    		    //System.out.println("选择是后执行的代码");
			    		    if(new TypeDaoImpl().addType(tname, state)){
			    		    	JOptionPane.showMessageDialog(AddType.this, 
			    		    			"添加成功！", "系统信息", JOptionPane.INFORMATION_MESSAGE);
			    		    	TypeMana.changeJt();
			    		    }else{
				    		    // System.out.println("选择否后执行的代码");    //点击“否”后执行这个代码块
				    		     JOptionPane.showMessageDialog(AddType.this, 
				    		    			"添加失败！", "系统信息", JOptionPane.INFORMATION_MESSAGE);
				    		} 
			    		    }
				}
			}
		});
		btnNewButton_1.setBounds(20, 211, 66, 23);
		this.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("取消");
		btnNewButton_2.setBounds(110, 211, 66, 23);
		this.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() { //取消事件
			public void actionPerformed(ActionEvent e) {
				AddType.this.setVisible(false);
			}
		});
		
		JLabel lblNewLabel_2 = new JLabel("是否显示");
		lblNewLabel_2.setBounds(20, 103, 54, 23);
		this.add(lblNewLabel_2);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("是");
		rdbtnNewRadioButton.setSelected(true);
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(88, 107, 42, 23);
		this.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("否");
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(132, 107, 42, 23);
		this.add(rdbtnNewRadioButton_1);
		textField.setFocusable(true);
		this.setVisible(true);
	}
	public boolean checkTname(){ //确认用户名是否可用
		//获取设置的使用或者不使用的值
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
				if(chooseUse.equals("是")){
					state = 1;
				}else{
					state = 0;
				}
		tname = textField.getText().trim();
		if(tname == null || tname.equals("")){
			lblNewLabel_1.setText("你输入的类别名为空");
			lblNewLabel_1.setForeground(Color.red);
			textField.requestFocus();
			return false;
		}else if(tname.length() > 10){
			lblNewLabel_1.setText("你输入的类别名太长了");
			lblNewLabel_1.setForeground(Color.red);
			textField.requestFocus();
			return false;
		}else if(new TypeDaoImpl().chackByTname(tname)){
			lblNewLabel_1.setText("你输入的类别名已存在");
			lblNewLabel_1.setForeground(Color.red);	
			textField.requestFocus();
			return false;
		}else{
			lblNewLabel_1.setText("你输入的类别名可以用");
			lblNewLabel_1.setForeground(new Color(23, 133,218));
			return true;
		}
		 
	}

}
