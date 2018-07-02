package com.chinasoft.entity;

public class Types {

	private Integer tid;
	private String tname;
	private int state;
	
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Types [tid=" + tid + ", tname=" + tname + ", state=" + state
				+ "]";
	}
	
}
