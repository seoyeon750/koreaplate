package com.korea.plate.dto;

public class DepartmentDTO {
	
	private String dSaup_no, dId, dPw;
	
	public DepartmentDTO() {
		// TODO Auto-generated constructor stub
	}

	public DepartmentDTO(String dSaup_no, String dId, String dPw) {
		super();
		this.dSaup_no = dSaup_no;
		this.dId = dId;
		this.dPw = dPw;
	}

	public String getdSaup_no() {
		return dSaup_no;
	}

	public void setdSaup_no(String dSaup_no) {
		this.dSaup_no = dSaup_no;
	}

	public String getdId() {
		return dId;
	}

	public void setdId(String dId) {
		this.dId = dId;
	}

	public String getdPw() {
		return dPw;
	}

	public void setdPw(String dPw) {
		this.dPw = dPw;
	}
	
}
