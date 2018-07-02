package com.chinasoft.entity;

public class Goods {

	private Integer id;
	private String gname;
	private String gtype;
	private Integer gnum;
	private double gprice;
	private int state;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public String getGtype() {
		return gtype;
	}
	public void setGtype(String gtype) {
		this.gtype = gtype;
	}
	public Integer getGnum() {
		return gnum;
	}
	public void setGnum(Integer gnum) {
		this.gnum = gnum;
	}
	public double getGprice() {
		return gprice;
	}
	public void setGprice(double gprice) {
		this.gprice = gprice;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Goods(Integer id, String gname, String gtype, Integer gnum,
			Integer gprice, int state) {
		super();
		this.id = id;
		this.gname = gname;
		this.gtype = gtype;
		this.gnum = gnum;
		this.gprice = gprice;
		this.state = state;
	}
	public Goods() {
		super();
	}
	@Override
	public String toString() {
		return "Goods [id=" + id + ", gname=" + gname + ", gtyte=" + gtype
				+ ", gnum=" + gnum + ", gprices=" + gprice + ", state="
				+ state + "]";
	}
	
	
}
