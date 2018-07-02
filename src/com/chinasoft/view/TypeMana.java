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
		JLabel lblNewLabel = new JLabel("列表信息编辑");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel.setBounds(60, 23, 120, 46);
		this.add(lblNewLabel);
		JButton add_type = new JButton("添加新分类");
		add_type.setBounds(200, 28, 100, 36);
		this.add(add_type);
		add_type.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Point point  = TypeMana.this.getParent().getLocationOnScreen();
				new AddType(point,TypeMana.this.getWidth() + 200,TypeMana.this.getHeight(),300,300,"添加类别");
			}
		});
		js = new JScrollPane();
		//表格组件
		String[] col = {"列表名称","是否使用","删除","修改状态","重命名"};
		Object[][] data = new Object[list.size()][col.length];
		for(int i = 0;i < data.length;i++){
			data[i][0] = list.get(i).getTname();
			int state = list.get(i).getState();
			if(state == 1){
				data[i][1] = "是";
			}else{
				data[i][1] = "否";
			}
			data[i][2] = "删除此类别";
			data[i][3] = "修改状态";
			data[i][4] = "重命名类别";
		}
		
//		jt = new JTable(data,col);
		
		
		dtm = new DefaultTableModel(data,col);//定义表格模型

		jt = new JTable(dtm);//根据表格模型创建表格
		jt.setRowHeight(30);
		jt.setDefaultEditor(Object.class, new Myedit());
		jt.setRowHeight(1,40);
		js = new JScrollPane(jt);
		js.setBounds(0, 80, 400, 260);
		this.add(js);
		
		
		//表单点击事件
		jt.addMouseListener(new MouseAdapter()
		{ 
			public void mouseClicked(MouseEvent e){
		    int  row =((JTable)e.getSource()).rowAtPoint(e.getPoint()); 
		    //获得行位置 
		    int  col=((JTable)e.getSource()).columnAtPoint(e.getPoint()); 

		    if(col == 2){ //删除
		    	String tname = (String) jt.getValueAt(row, 0);
		    	int a = JOptionPane.showConfirmDialog(TypeMana.this, "是否删除类列：" + tname, "是否删除", JOptionPane.YES_NO_OPTION);
//		    	System.out.println(a);
		    	  if(a==JOptionPane.YES_OPTION){ 
		    		    //System.out.println("选择是后执行的代码");
		    		    if(new TypeDaoImpl().deleteByName(tname)){
		    		    	JOptionPane.showMessageDialog(TypeMana.this, 
		    		    			"删除成功！", "系统信息", JOptionPane.INFORMATION_MESSAGE);
		    		    	changeJt();
		    		    }else{
			    		    // System.out.println("选择否后执行的代码");    //点击“否”后执行这个代码块
			    		     JOptionPane.showMessageDialog(TypeMana.this, 
			    		    			"删除失败！", "系统信息", JOptionPane.INFORMATION_MESSAGE);
			    		} 
		    		    }
//		    	
		    }else if(col == 3){ //修改状态
		    	String tname = (String) jt.getValueAt(row, 0);
		    	String tState = (String) jt.getValueAt(row, 1);
		    	int state = 1; //状态
		    	String tib = "";
		    	if(tState.equals("是")){
		    		tib = "不使用";
		    		state = 0;
		    	}else{
		    		tib = "使用";
		    		state = 1;
		    	}
		    	int a = JOptionPane.showConfirmDialog(TypeMana.this, "是否修改类别：" + tname + "为" + tib, "是否修改状态", JOptionPane.YES_NO_OPTION);
//		    	System.out.println(a);
		    	  if(a==JOptionPane.YES_OPTION){ 
		    		    //System.out.println("选择是后执行的代码");
		    		    if(new TypeDaoImpl().changeStateByName(state, tname)){
		    		    	JOptionPane.showMessageDialog(TypeMana.this, 
		    		    			"修改成功！", "系统信息", JOptionPane.INFORMATION_MESSAGE);
		    		    	changeJt();
		    		    }else{
			    		    // System.out.println("选择否后执行的代码");    //点击“否”后执行这个代码块
			    		     JOptionPane.showMessageDialog(TypeMana.this, 
			    		    			"修改失败！", "系统信息", JOptionPane.INFORMATION_MESSAGE);
			    		} 
		    		    }
		    }else if(col == 4) { //重命名
		    	String tname = (String) jt.getValueAt(row, 0);
		    	String inputValue = JOptionPane.showInputDialog(TypeMana.this, "请你输入新的类列名");
		    	if(inputValue != null && inputValue.equals("")) {
		    		JOptionPane.showMessageDialog(TypeMana.this, "你输入的类别名为空", "系统消息", JOptionPane.ERROR_MESSAGE);
		    	}else if(inputValue != null &&inputValue.length() > 20) {
		    		JOptionPane.showMessageDialog(TypeMana.this, "你输入的类别名太长了", "系统消息", JOptionPane.ERROR_MESSAGE);
		    	}else if(new TypeDaoImpl().chackByTname(inputValue)){
		    		JOptionPane.showMessageDialog(TypeMana.this, "你输入的类别名已存在", "系统消息", JOptionPane.ERROR_MESSAGE);
		    	}else{
		    		int a = JOptionPane.showConfirmDialog(TypeMana.this, "是否修改类别：" + tname + "为" + inputValue , "修改名称", JOptionPane.YES_NO_OPTION);
		    		 if(a==JOptionPane.YES_OPTION){ 
//			    		    //System.out.println("选择是后执行的代码");
			    		    if(new TypeDaoImpl().renameByName(tname, inputValue)){
			    		    	JOptionPane.showMessageDialog(TypeMana.this, 
			    		    			"修改成功！", "系统信息", JOptionPane.INFORMATION_MESSAGE);
			    		    	changeJt();
			    		    }else{
			    		    // System.out.println("选择否后执行的代码");    //点击“否”后执行这个代码块
			    		     JOptionPane.showMessageDialog(TypeMana.this, 
			    		    			"修改失败！", "系统信息", JOptionPane.INFORMATION_MESSAGE);
			    		}
			    		    }
		    	}

		    }
			}
		});
		
		
	}


	public static void  changeJt(){ //重画表格
		list = new TypeDaoImpl().selectAllType();
		String[] col = {"列表名称","是否使用","删除","修改状态","重命名"};
		Object[][] data = new Object[list.size()][col.length];
		for(int i = 0;i < data.length;i++){
			data[i][0] = list.get(i).getTname();
			int state = list.get(i).getState();
			if(state == 1){
				data[i][1] = "是";
			}else{
				data[i][1] = "否";
			}
			data[i][2] = "删除此类别";
			data[i][3] = "修改状态";
			data[i][4] = "重命名类别";
		}
		dtm.setDataVector(data, col);
	}
}
