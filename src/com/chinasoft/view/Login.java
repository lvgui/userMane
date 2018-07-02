package com.chinasoft.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.chinasoft.dao.impl.UserDaoImpl;
import com.chinasoft.dao.impl.UserQuanImpl;
import com.chinasoft.entity.Users;

public class Login extends JFrame {

	private JTextField user; //用户名输入框
	private JPasswordField pass; //密码输入框
	private JLabel lblNewLabel_3,lblNewLabel_4; //用户名提示信息
	private boolean userBool,passBool; //用户密码和用户名是否正确
	private JButton btnNewButton; //登陆按钮
	public Login()
	{
		//利用绘制窗口的大小坐标写下来
		this.setBounds(100, 100, 400, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		//窗口中布局设置为绝对布局
		this.setTitle("用户登录");
		//面板主标题
		JLabel lblNewLabel = new JLabel("天下一家商品管理系统");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel.setBounds(80, 27, 200, 75);
		this.add(lblNewLabel);
		
		//用户名
		JLabel lblNewLabel_1 = new JLabel("用户名：");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(55, 111, 64, 41);
		this.add(lblNewLabel_1);
		
		//密码
		JLabel lblNewLabel_2 = new JLabel("密码：");
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(55, 181, 64, 31);
		this.add(lblNewLabel_2);
		
		//用户名输入框
		user = new JTextField(); 
		user.setBounds(129, 122, 137, 30);
		this.add(user);
		user.setColumns(10);
		
		//用户名提示框
		lblNewLabel_3 = new JLabel("");		
		lblNewLabel_3.setBounds(276, 125, 94, 15);
		this.add(lblNewLabel_3);
		
		//密码提示
		lblNewLabel_4 = new JLabel("");		
		lblNewLabel_4.setBounds(276, 194, 94, 15);
		this.add(lblNewLabel_4);
		
		//密码输入框
		pass = new JPasswordField();
		pass.setBounds(129, 187, 137, 30);
		this.add(pass);
		pass.setColumns(10);
		
		//登陆按钮
		btnNewButton = new JButton("登陆");
		btnNewButton.setBounds(48, 271, 93, 56);
		btnNewButton.setEnabled(false);
		this.add(btnNewButton);
		
		//退出按钮
		JButton btnNewButton_1 = new JButton("退出");
		btnNewButton_1.setBounds(213, 271, 93, 56);
		this.add(btnNewButton_1);
		
		//用户名添加失去或者获得焦点事件
		user.addFocusListener(new FocusListener() {
		    public void focusLost(FocusEvent e) {//失去焦点时
		    	String user_text = user.getText();
		    	Users u1 = new Users();
				u1.setUserName(user_text);
				//判断用户名是否正确，显示不同的提示消息
				boolean flag = new UserDaoImpl().chackByName(u1);
		    	if(user_text.equals("")){
		    		userBool = false;
		    		lblNewLabel_3.setText("用户名不能为空");
		    		lblNewLabel_3.setForeground(Color.RED);
		    	}else if(!flag){
		    		userBool = false;
		    		lblNewLabel_3.setText("用户名不存在");
		    		lblNewLabel_3.setForeground(Color.RED);
		    	}else{
		    		userBool = true;
		    		lblNewLabel_3.setText("用户名输入正确");
		    		lblNewLabel_3.setForeground(new Color(23, 133,218));
		    	}
		    	user.setBorder(BorderFactory.createLineBorder(new Color(146, 146, 146)));
		    	judge();
		    }
		    public void focusGained(FocusEvent e) {//获得焦点时
		    	lblNewLabel_3.setText("");
		        user.setBorder(BorderFactory.createLineBorder(new Color(11, 85, 209)));
		 
		    }
		   
		});
	
		//密码提示
		pass.addFocusListener(new FocusListener() {
		    public void focusLost(FocusEvent e) {//失去焦点时
		    	char [] arr = pass.getPassword();
				String pass_text = new String(arr);
				//判断用户名是否正确
		    	if(pass_text.equals("")){
		    		lblNewLabel_4.setText("密码不能为空");
		    		lblNewLabel_4.setForeground(Color.RED);
		    	}
		    	pass.setBorder(BorderFactory.createLineBorder(new Color(146, 146, 146)));
		    	judge();
		    }
		    public void focusGained(FocusEvent e) {//获得焦点时
		    	lblNewLabel_4.setText("");
		    	pass.setBorder(BorderFactory.createLineBorder(new Color(11, 85, 209)));
		 
		    }
		});
		//用户按键监听
				user.getDocument().addDocumentListener(new DocumentListener(){
				    public void changedUpdate(DocumentEvent e){
				    }
				    public void removeUpdate(DocumentEvent e){
				    	String user_text = user.getText();
				    	Users u1 = new Users();
						u1.setUserName(user_text);
						//判断用户名是否正确
						boolean flag = new UserDaoImpl().chackByName(u1);
						//判断用户名是否正确
				    	if(flag){
				    		userBool = true;
				    	}else{
				    		userBool = false;
				    	}
				        judge();
				    }
				    public void insertUpdate(DocumentEvent e){
				        // 当textField内容增加时触发    
				        // 对应到用户行为是用户键入内容
				    	String user_text = user.getText();
				    	Users u1 = new Users();
						u1.setUserName(user_text);
						//判断用户名是否正确
						boolean flag = new UserDaoImpl().chackByName(u1);
						//判断用户名是否正确
				    	if(flag){
				    		userBool = true;
				    	}else{
				    		userBool = false;
				    	}
				        judge(); 
				    }

				});
		//按键监听
		pass.getDocument().addDocumentListener(new DocumentListener(){
		    public void changedUpdate(DocumentEvent e){
		        // 当attribute改变时触发
		        // 有待继续探索......
		    }
		    public void removeUpdate(DocumentEvent e){
		       	char [] arr = pass.getPassword();
				String pass_text = new String(arr);
				//判断用户名是否正确
		    	if(!pass_text.equals("")){
		    		passBool = true;
		    	}else{
		    		passBool = false;
		    	}
		        judge();
		    }
		    public void insertUpdate(DocumentEvent e){
		        // 当textField内容增加时触发    
		        // 对应到用户行为是用户键入内容
		    	char [] arr = pass.getPassword();
				String pass_text = new String(arr);
				//判断密码是否为空
		    	if(!pass_text.equals("")){
		    		passBool = true;
		    	}
		    	judge();  
		    }

		});

		//登陆事件监听
	
		
		btnNewButton.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//判断用户名和密码不能为空
				String user_text = user.getText();
				char [] arr = pass.getPassword();
				String pass_text = new String(arr);
				if(!user_text.equals("")&& !pass_text.equals(""))
				{
					Users user = new Users();
					user.setUserName(user_text);
					user.setUserPass(pass_text);
					boolean flag = new UserDaoImpl().LoginByName(user);
					if(flag){
						int a = new UserQuanImpl().queryQuan(user.getUserName());
						new LeftMess(a).setVisible(true);
						setVisible(false);
					}
					else{
						JOptionPane.showMessageDialog(Login.this,"你输入的密码错误");
					}
				}
			}
		});	
		//退出按钮监听
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	public void judge(){
		if(userBool && passBool){
			btnNewButton.setEnabled(true);
		}else{
			btnNewButton.setEnabled(false);
		}
	}
	public static void main(String[] args) {
		new Login().setVisible(true);
	}
}
