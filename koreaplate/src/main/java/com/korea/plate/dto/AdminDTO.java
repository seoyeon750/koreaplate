package com.korea.plate.dto;

import java.sql.Date;

public class AdminDTO {

	private int cNo, dNo, dSeat, dHit, dRating, dAccpet;
	private String cId, cPw, cName, cNickname, cPhone, cEmail, cGrade, cGender, dPhone, dName, dAddress, dType, dPhoto, dStart, dEnd, dParking, dSaup_no;
	private Date dReg_date;
	
	public AdminDTO() {
			
	}

	public AdminDTO(int cNo, int dNo, int dSeat, int dHit, int dRating, int dAccpet, String cId, String cPw,
			String cName, String cNickname, String cPhone, String cEmail, String cGrade, String cGender, String dPhone,
			String dName, String dAddress, String dType, String dPhoto, String dStart, String dEnd, String dParking,
			String dSaup_no, Date dReg_date) {
		super();
		this.cNo = cNo;
		this.dNo = dNo;
		this.dSeat = dSeat;
		this.dHit = dHit;
		this.dRating = dRating;
		this.dAccpet = dAccpet;
		this.cId = cId;
		this.cPw = cPw;
		this.cName = cName;
		this.cNickname = cNickname;
		this.cPhone = cPhone;
		this.cEmail = cEmail;
		this.cGrade = cGrade;
		this.cGender = cGender;
		this.dPhone = dPhone;
		this.dName = dName;
		this.dAddress = dAddress;
		this.dType = dType;
		this.dPhoto = dPhoto;
		this.dStart = dStart;
		this.dEnd = dEnd;
		this.dParking = dParking;
		this.dSaup_no = dSaup_no;
		this.dReg_date = dReg_date;
	}

	public int getcNo() {
		return cNo;
	}

	public void setcNo(int cNo) {
		this.cNo = cNo;
	}

	public int getdNo() {
		return dNo;
	}

	public void setdNo(int dNo) {
		this.dNo = dNo;
	}

	public int getdSeat() {
		return dSeat;
	}

	public void setdSeat(int dSeat) {
		this.dSeat = dSeat;
	}

	public int getdHit() {
		return dHit;
	}

	public void setdHit(int dHit) {
		this.dHit = dHit;
	}

	public int getdRating() {
		return dRating;
	}

	public void setdRating(int dRating) {
		this.dRating = dRating;
	}

	public int getdAccpet() {
		return dAccpet;
	}

	public void setdAccpet(int dAccpet) {
		this.dAccpet = dAccpet;
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

	public String getdPhone() {
		return dPhone;
	}

	public void setdPhone(String dPhone) {
		this.dPhone = dPhone;
	}

	public String getdName() {
		return dName;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}

	public String getdAddress() {
		return dAddress;
	}

	public void setdAddress(String dAddress) {
		this.dAddress = dAddress;
	}

	public String getdType() {
		return dType;
	}

	public void setdType(String dType) {
		this.dType = dType;
	}

	public String getdPhoto() {
		return dPhoto;
	}

	public void setdPhoto(String dPhoto) {
		this.dPhoto = dPhoto;
	}

	public String getdStart() {
		return dStart;
	}

	public void setdStart(String dStart) {
		this.dStart = dStart;
	}

	public String getdEnd() {
		return dEnd;
	}

	public void setdEnd(String dEnd) {
		this.dEnd = dEnd;
	}

	public String getdParking() {
		return dParking;
	}

	public void setdParking(String dParking) {
		this.dParking = dParking;
	}

	public String getdSaup_no() {
		return dSaup_no;
	}

	public void setdSaup_no(String dSaup_no) {
		this.dSaup_no = dSaup_no;
	}

	public Date getdReg_date() {
		return dReg_date;
	}

	public void setdReg_date(Date dReg_date) {
		this.dReg_date = dReg_date;
	}

}
