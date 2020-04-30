package com.changping.shiro.pojo;

public class Filter_Chain_Definition {
	private int chain_Id;
	private String chain_Name;
	private String chain_Definition;
	public int getChain_Id() {
		return chain_Id;
	}
	public void setChain_Id(int chain_Id) {
		this.chain_Id = chain_Id;
	}
	public String getChain_Name() {
		return chain_Name;
	}
	public void setChain_Name(String chain_Name) {
		this.chain_Name = chain_Name;
	}
	public String getChain_Definition() {
		return chain_Definition;
	}
	public void setChain_Definition(String chain_Definition) {
		this.chain_Definition = chain_Definition;
	}
	public Filter_Chain_Definition(String chain_Name, String chain_Definition) {
		super();
		this.chain_Name = chain_Name;
		this.chain_Definition = chain_Definition;
	}
	public Filter_Chain_Definition(int chain_Id, String chain_Name, String chain_Definition) {
		super();
		this.chain_Id = chain_Id;
		this.chain_Name = chain_Name;
		this.chain_Definition = chain_Definition;
	}
	public Filter_Chain_Definition() {
		super();
	}
	@Override
	public String toString() {
		return "Filter_Chain_Definition [chain_Id=" + chain_Id + ", chain_Name=" + chain_Name + ", chain_Definition="
				+ chain_Definition + "]";
	}
}
