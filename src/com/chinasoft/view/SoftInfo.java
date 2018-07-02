package com.chinasoft.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SoftInfo extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtQq;
	public SoftInfo(){
		this.setSize(400, 400);
		this.setLayout(null);
		JLabel lblNewLabel = new JLabel("\u5929\u4E0B\u4E00\u5BB6\u5546\u54C1\u7BA1\u7406\u4E2D\u5FC3");
		lblNewLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));
		lblNewLabel.setBounds(79, 53, 184, 42);
		this.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u7248\u672C\u53F7\r\n");
		lblNewLabel_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(62, 127, 60, 30);
		this.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setText("1.0");
		textField.setBounds(156, 133, 66, 21);
		this.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u5236\u4F5C\u5C0F\u7EC4");
		lblNewLabel_2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(62, 187, 71, 30);
		this.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setText("\u6218\u5730\u56DB\u9ED1");
		textField_1.setBounds(156, 193, 66, 21);
		this.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("\u7EF4\u62A4\u70ED\u7EBF");
		lblNewLabel_3.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(63, 242, 81, 30);
		this.add(lblNewLabel_3);
		
		txtQq = new JTextField();
		txtQq.setEditable(false);
		txtQq.setText("QQ\uFF1A1319557659");
		txtQq.setBounds(156, 248, 107, 21);
		this.add(txtQq);
		txtQq.setColumns(10);
	}
}
