package com.korea.plate.dto;

public class MainDTO {

	private String dong;
	private int count;
	
	public MainDTO() {
		
	}

	public MainDTO(String dong, int count) {
		super();
		this.dong = dong;
		this.count = count;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
