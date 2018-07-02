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

	private JTextField user; //�û��������
	private JPasswordField pass; //���������
	private JLabel lblNewLabel_3,lblNewLabel_4; //�û�����ʾ��Ϣ
	private boolean userBool,passBool; //�û�������û����Ƿ���ȷ
	private JButton btnNewButton; //��½��ť
	public Login()
	{
		//���û��ƴ��ڵĴ�С����д����
		this.setBounds(100, 100, 400, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		//�����в�������Ϊ���Բ���
		this.setTitle("�û���¼");
		//���������
		JLabel lblNewLabel = new JLabel("����һ����Ʒ����ϵͳ");
		lblNewLabel.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		lblNewLabel.setBounds(80, 27, 200, 75);
		this.add(lblNewLabel);
		
		//�û���
		JLabel lblNewLabel_1 = new JLabel("�û�����");
		lblNewLabel_1.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(55, 111, 64, 41);
		this.add(lblNewLabel_1);
		
		//����
		JLabel lblNewLabel_2 = new JLabel("���룺");
		lblNewLabel_2.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(55, 181, 64, 31);
		this.add(lblNewLabel_2);
		
		//�û��������
		user = new JTextField(); 
		user.setBounds(129, 122, 137, 30);
		this.add(user);
		user.setColumns(10);
		
		//�û�����ʾ��
		lblNewLabel_3 = new JLabel("");		
		lblNewLabel_3.setBounds(276, 125, 94, 15);
		this.add(lblNewLabel_3);
		
		//������ʾ
		lblNewLabel_4 = new JLabel("");		
		lblNewLabel_4.setBounds(276, 194, 94, 15);
		this.add(lblNewLabel_4);
		
		//���������
		pass = new JPasswordField();
		pass.setBounds(129, 187, 137, 30);
		this.add(pass);
		pass.setColumns(10);
		
		//��½��ť
		btnNewButton = new JButton("��½");
		btnNewButton.setBounds(48, 271, 93, 56);
		btnNewButton.setEnabled(false);
		this.add(btnNewButton);
		
		//�˳���ť
		JButton btnNewButton_1 = new JButton("�˳�");
		btnNewButton_1.setBounds(213, 271, 93, 56);
		this.add(btnNewButton_1);
		
		//�û������ʧȥ���߻�ý����¼�
		user.addFocusListener(new FocusListener() {
		    public void focusLost(FocusEvent e) {//ʧȥ����ʱ
		    	String user_text = user.getText();
		    	Users u1 = new Users();
				u1.setUserName(user_text);
				//�ж��û����Ƿ���ȷ����ʾ��ͬ����ʾ��Ϣ
				boolean flag = new UserDaoImpl().chackByName(u1);
		    	if(user_text.equals("")){
		    		userBool = false;
		    		lblNewLabel_3.setText("�û�������Ϊ��");
		    		lblNewLabel_3.setForeground(Color.RED);
		    	}else if(!flag){
		    		userBool = false;
		    		lblNewLabel_3.setText("�û���������");
		    		lblNewLabel_3.setForeground(Color.RED);
		    	}else{
		    		userBool = true;
		    		lblNewLabel_3.setText("�û���������ȷ");
		    		lblNewLabel_3.setForeground(new Color(23, 133,218));
		    	}
		    	user.setBorder(BorderFactory.createLineBorder(new Color(146, 146, 146)));
		    	judge();
		    }
		    public void focusGained(FocusEvent e) {//��ý���ʱ
		    	lblNewLabel_3.setText("");
		        user.setBorder(BorderFactory.createLineBorder(new Color(11, 85, 209)));
		 
		    }
		   
		});
	
		//������ʾ
		pass.addFocusListener(new FocusListener() {
		    public void focusLost(FocusEvent e) {//ʧȥ����ʱ
		    	char [] arr = pass.getPassword();
				String pass_text = new String(arr);
				//�ж��û����Ƿ���ȷ
		    	if(pass_text.equals("")){
		    		lblNewLabel_4.setText("���벻��Ϊ��");
		    		lblNewLabel_4.setForeground(Color.RED);
		    	}
		    	pass.setBorder(BorderFactory.createLineBorder(new Color(146, 146, 146)));
		    	judge();
		    }
		    public void focusGained(FocusEvent e) {//��ý���ʱ
		    	lblNewLabel_4.setText("");
		    	pass.setBorder(BorderFactory.createLineBorder(new Color(11, 85, 209)));
		 
		    }
		});
		//�û���������
				user.getDocument().addDocumentListener(new DocumentListener(){
				    public void changedUpdate(DocumentEvent e){
				    }
				    public void removeUpdate(DocumentEvent e){
				    	String user_text = user.getText();
				    	Users u1 = new Users();
						u1.setUserName(user_text);
						//�ж��û����Ƿ���ȷ
						boolean flag = new UserDaoImpl().chackByName(u1);
						//�ж��û����Ƿ���ȷ
				    	if(flag){
				    		userBool = true;
				    	}else{
				    		userBool = false;
				    	}
				        judge();
				    }
				    public void insertUpdate(DocumentEvent e){
				        // ��textField��������ʱ����    
				        // ��Ӧ���û���Ϊ���û���������
				    	String user_text = user.getText();
				    	Users u1 = new Users();
						u1.setUserName(user_text);
						//�ж��û����Ƿ���ȷ
						boolean flag = new UserDaoImpl().chackByName(u1);
						//�ж��û����Ƿ���ȷ
				    	if(flag){
				    		userBool = true;
				    	}else{
				    		userBool = false;
				    	}
				        judge(); 
				    }

				});
		//��������
		pass.getDocument().addDocumentListener(new DocumentListener(){
		    public void changedUpdate(DocumentEvent e){
		        // ��attribute�ı�ʱ����
		        // �д�����̽��......
		    }
		    public void removeUpdate(DocumentEvent e){
		       	char [] arr = pass.getPassword();
				String pass_text = new String(arr);
				//�ж��û����Ƿ���ȷ
		    	if(!pass_text.equals("")){
		    		passBool = true;
		    	}else{
		    		passBool = false;
		    	}
		        judge();
		    }
		    public void insertUpdate(DocumentEvent e){
		        // ��textField��������ʱ����    
		        // ��Ӧ���û���Ϊ���û���������
		    	char [] arr = pass.getPassword();
				String pass_text = new String(arr);
				//�ж������Ƿ�Ϊ��
		    	if(!pass_text.equals("")){
		    		passBool = true;
		    	}
		    	judge();  
		    }

		});

		//��½�¼�����
	
		
		btnNewButton.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//�ж��û��������벻��Ϊ��
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
						JOptionPane.showMessageDialog(Login.this,"��������������");
					}
				}
			}
		});	
		//�˳���ť����
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
