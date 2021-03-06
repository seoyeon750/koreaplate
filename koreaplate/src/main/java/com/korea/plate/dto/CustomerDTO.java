package com.korea.plate.dto;

import java.sql.Date;

public class CustomerDTO {

	private int cNo;
	private String cId, cPw, cName,cNickname, cPhone, cEmail, cPhoto, cGrade, cGender;
	private Date cRegdate;
	
	public CustomerDTO() {
		// TODO Auto-generated constructor stub
	}

	public CustomerDTO(int cNo, String cId, String cPw, String cName, String cNickname, String cPhone, String cEmail,
			String cPhoto, String cGrade, String cGender, Date cRegdate) {
		super();
		this.cNo = cNo;
		this.cId = cId;
		this.cPw = cPw;
		this.cName = cName;
		this.cNickname = cNickname;
		this.cPhone = cPhone;
		this.cEmail = cEmail;
		this.cPhoto = cPhoto;
		this.cGrade = cGrade;
		this.cGender = cGender;
		this.cRegdate = cRegdate;
	}

	public int getcNo() {
		return cNo;
	}

	public void setcNo(int cNo) {
		this.cNo = cNo;
	}

	public String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}

	public String getcPw() {
		return cPw;
	}

	public void setcPw(String cPw) {
		this.cPw = cPw;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcNickname() {
		return cNickname;
	}

	public void setcNickname(String cNickname) {
		this.cNickname = cNickname;
	}

	public String getcPhone() {
		return cPhone;
	}

	public void setcPhone(String cPhone) {
		this.cPhone = cPhone;
	}

	public String getcEmail() {
		return cEmail;
	}

	public void setcEmail(String cEmail) {
		this.cEmail = cEmail;
	}

	public String getcPhoto() {
		return cPhoto;
	}

	public void setcPhoto(String cPhoto) {
		this.cPhoto = cPhoto;
	}

	public String getcGrade() {
		return cGrade;
	}

	public void setcGrade(String cGrade) {
		this.cGrade = cGrade;
	}

	public String getcGender() {
		return cGender;
	}

	public void setcGender(String cGender) {
		this.cGender = cGender;
	}

	public Date getcRegdate() {
		return cRegdate;
	}

	public void setcRegdate(Date cRegdate) {
		this.cRegdate = cRegdate;
	}
	
}       
  