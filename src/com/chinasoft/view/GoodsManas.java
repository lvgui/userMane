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
		JLabel lblNewLabel = new JLabel("商品信息编辑");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel.setBounds(100, 23, 120, 46);
		this.add(lblNewLabel);
		js = new JScrollPane();
		//修改上下架
		JButton btnNewButton = new JButton("修改上下架");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 int row = jt.getSelectedRow();
				 if(row == -1) {
					 JOptionPane.showMessageDialog(GoodsManas.this, "请你选择需要修改的数据！", "系统信息", JOptionPane.INFORMATION_MESSAGE);
				 }else {
					 String gname = (String) jt.getValueAt(row, 1);
					 String state = (String) jt.getValueAt(row, 5); 
					 int temState = 1;
					 String temStateInfo;
					 if(state.equals("是")) {
						 temStateInfo = "下架";
						 temState = 0;
					 }else {
						 temStateInfo = "上架";
						 temState = 1;
					 }
					 int a = JOptionPane.showConfirmDialog(GoodsManas.this, "是否" + temStateInfo + "商品" + gname, "是否修改状态", JOptionPane.YES_NO_OPTION);
////			    	System.out.println(a);
			    	  if(a==JOptionPane.YES_OPTION){ 
			    		    //System.out.println("选择是后执行的代码");
			    		    if(new GoodDaoImpl().changeStateByName(temState, gname)){
			    		    	JOptionPane.showMessageDialog(GoodsManas.this, 
			    		    			"修改成功！", "系统信息", JOptionPane.INFORMATION_MESSAGE);
			    		    	changeJt();
			    		    }else{
				    		    // System.out.println("选择否后执行的代码");    //点击“否”后执行这个代码块
				    		     JOptionPane.showMessageDialog(GoodsManas.this, 
				    		    			"修改失败！", "系统信息", JOptionPane.INFORMATION_MESSAGE);
				    		} 
			    		    }
				 }
			}
		});
		btnNewButton.setBounds(0, 300, 100, 23);
		this.add(btnNewButton);
		
		//自动下架
		JButton btnNewButton_1 = new JButton("修改商品名");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int row = jt.getSelectedRow();
				 if(row == -1) {
					 JOptionPane.showMessageDialog(GoodsManas.this, "请你选择需要修改的数据！", "系统信息", JOptionPane.INFORMATION_MESSAGE);
				 }else {
					 String gname = (String) jt.getValueAt(row, 1);
				    String inputValue = JOptionPane.showInputDialog(GoodsManas.this, "请你输入新的商品名");
				    	if(inputValue != null && inputValue.equals("")) {
				    		JOptionPane.showMessageDialog(GoodsManas.this, "你输入的商品名为空", "系统消息", JOptionPane.ERROR_MESSAGE);
				    	}else if(inputValue != null &&inputValue.length() > 20) {
				    		JOptionPane.showMessageDialog(GoodsManas.this, "你输入的商品名太长了", "系统消息", JOptionPane.ERROR_MESSAGE);
				    	}else if(new GoodDaoImpl().chackByTname(inputValue)){
				    		JOptionPane.showMessageDialog(GoodsManas.this, "你输入的商品名已存在", "系统消息", JOptionPane.ERROR_MESSAGE);
				    	
				    	}
				    	else{
				    		int a = JOptionPane.showConfirmDialog(GoodsManas.this, "是否修改商品名：" + gname + "为" + inputValue , "修改名称", JOptionPane.YES_NO_OPTION);
				    		 if(a==JOptionPane.YES_OPTION){ 
					    		    //System.out.println("选择是后执行的代码");
					    		    if(new GoodDaoImpl().renameByName(gname, inputValue)){
					    		    	JOptionPane.showMessageDialog(GoodsManas.this, 
					    		    			"修改成功！", "系统信息", JOptionPane.INFORMATION_MESSAGE);
					    		    	changeJt();
					    		    }else{					    		    // System.out.println("选择否后执行的代码");    //点击“否”后执行这个代码块
						    		     JOptionPane.showMessageDialog(GoodsManas.this, 
						    		    			"修改失败！", "系统信息", JOptionPane.INFORMATION_MESSAGE);
						    		}
					    		    }
				    	}
				    }
					}
				});
		btnNewButton_1.setBounds(105, 300, 100, 23);
		this.add(btnNewButton_1);
		//修改数量
		JButton btnNewButton_2 = new JButton("修改数量");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int row = jt.getSelectedRow();
				 if(row == -1) {
					 JOptionPane.showMessageDialog(GoodsManas.this, "请你选择需要修改的数据！", "系统信息", JOptionPane.INFORMATION_MESSAGE);
				 }else {
					 String gname = (String) jt.getValueAt(row, 1);
					 String tepnum =JOptionPane.showInputDialog(GoodsManas.this, "请你输入增加的数量");

					 int inputValue = -1;
				     if(tepnum == null || tepnum.equals("") || !tepnum.matches("^[0-9]*$"))  {
				    		JOptionPane.showMessageDialog(GoodsManas.this, "你输入的数量有误", "系统消息", JOptionPane.ERROR_MESSAGE);
				    		return;
				    	}else {
				    		 inputValue = Integer.parseInt(tepnum);
				    	}
				     if(inputValue < 0) {
				    		JOptionPane.showMessageDialog(GoodsManas.this, "你输入的数量为负", "系统消息", JOptionPane.ERROR_MESSAGE);
				    	}else{
				    		int a = JOptionPane.showConfirmDialog(GoodsManas.this, "是否修改商品名：" + gname + "的数量为" + inputValue , "系统消息", JOptionPane.YES_NO_OPTION);
				    		 if(a==JOptionPane.YES_OPTION){ 
					    		    //System.out.println("选择是后执行的代码");
					    		    if(new GoodDaoImpl().changeTnum(gname, inputValue)){
					    		    	JOptionPane.showMessageDialog(GoodsManas.this, 
					    		    			"修改成功！", "系统信息", JOptionPane.INFORMATION_MESSAGE);
					    		    	changeJt();
					    		    }else{					    		    // System.out.println("选择否后执行的代码");    //点击“否”后执行这个代码块
						    		     JOptionPane.showMessageDialog(GoodsManas.this, 
						    		    			"修改失败！", "系统信息", JOptionPane.INFORMATION_MESSAGE);
						    		}
					    		    }
				    	}
				    }
				
				
			}
		});
		btnNewButton_2.setBounds(210, 300, 90, 23);
		this.add(btnNewButton_2);
		
		//修改价格
		JButton btnNewButton_3 = new JButton("修改价格");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = jt.getSelectedRow();
				 if(row == -1) {
					 JOptionPane.showMessageDialog(GoodsManas.this, "请你选择需要修改的数据！", "系统信息", JOptionPane.INFORMATION_MESSAGE);
				 }else {
					 String gname = (String) jt.getValueAt(row, 1);
					 String tepnum =JOptionPane.showInputDialog(GoodsManas.this, "请你输入新的价格");

					 double inputValue = -1;
				     if(tepnum == null || tepnum.equals("") || !tepnum.matches("^[0-9/.]*$"))  {
				    		JOptionPane.showMessageDialog(GoodsManas.this, "你输入的价格有误", "系统消息", JOptionPane.ERROR_MESSAGE);
				    		return;
				    	}else {
				    		 inputValue = format2(Double.parseDouble(tepnum));
				    	}
				     if(inputValue < 0) {
				    		JOptionPane.showMessageDialog(GoodsManas.this, "你输入的价格小于0", "系统消息", JOptionPane.ERROR_MESSAGE);
				    	}else{
				    		int a = JOptionPane.showConfirmDialog(GoodsManas.this, "是否修改商品名：" + gname + "的单价为" + inputValue , "系统消息", JOptionPane.YES_NO_OPTION);
				    		 if(a==JOptionPane.YES_OPTION){ 
					    		    //System.out.println("选择是后执行的代码");
					    		    if(new GoodDaoImpl().changeGprice(gname, inputValue)){
					    		    	JOptionPane.showMessageDialog(GoodsManas.this, 
					    		    			"修改成功！", "系统信息", JOptionPane.INFORMATION_MESSAGE);
					    		    	changeJt();
					    		    }else{					    		    // System.out.println("选择否后执行的代码");    //点击“否”后执行这个代码块
						    		     JOptionPane.showMessageDialog(GoodsManas.this, 
						    		    			"修改失败！", "系统信息", JOptionPane.INFORMATION_MESSAGE);
						    		}
					    		    }
				    	}
				    }
				
			}
		});
		btnNewButton_3.setBounds(305, 300, 90, 23);
		this.add(btnNewButton_3);
		
		//表格组件
		String[] col = {"商品编号","商品名称","类型","数量","单价","是否上架"};
		Object[][] data = new Object[list.size()][col.length];
		for(int i = 0;i < data.length;i++){
			data[i][0] = list.get(i).getId();
			data[i][1] = list.get(i).getGname();
			data[i][2] = list.get(i).getGtype();
			data[i][3] = list.get(i).getGnum();
			data[i][4] = list.get(i).getGprice();
			int state = list.get(i).getState();
			if(state == 1){
				data[i][5] = "是";
			}else{
				data[i][5] = "否";
			}
		}
		
//		jt = new JTable(data,col);
		
		
		dtm = new DefaultTableModel(data,col);//定义表格模型

		jt = new JTable(dtm);//根据表格模型创建表格
		jt.setRowHeight(30);
		jt.setDefaultEditor(Object.class, new Myedit());
		jt.setRowHeight(1,40);
		js = new JScrollPane(jt);
		js.setBounds(0, 80, 400, 200);
		this.add(js);				
		
	}


	public static void  changeJt(){ //重画表格
		list = new GoodDaoImpl().selectAllType();
		String[] col = {"商品编号","商品名称","类别","数量","单价","是否上架"};
		Object[][] data = new Object[list.size()][col.length];
		for(int i = 0;i < data.length;i++){
			data[i][0] = list.get(i).getId();
			data[i][1] = list.get(i).getGname();
			data[i][2] = list.get(i).getGtype();
			data[i][3] = list.get(i).getGnum();
			data[i][4] = list.get(i).getGprice();
			int state = list.get(i).getState();
			if(state == 1){
				data[i][5] = "是";
			}else{
				data[i][5] = "否";
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
