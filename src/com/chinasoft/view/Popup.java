package com.chinasoft.view;

import java.awt.Dialog;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;

public class Popup  extends JDialog{
	int width;
	int height;
	String title;
	public Popup(Point point,int partentWidth,int partentHeight,int width,int height,String title) {
		this.width = width;
		this.height = height;
		this.title = title;
		this.setTitle(title);
		this.setSize(width,height);
	      this.setLocation(
	      point.x + partentWidth/2 - width/2, 
	      point.y + partentHeight/2 - height/2);
	      this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
	   // 参数 APPLICATION_MODAL：阻塞同一 Java 应用程序中的所有顶层窗口（它自己的子层次)
	      this.setLayout(null);
	      
	}
}
