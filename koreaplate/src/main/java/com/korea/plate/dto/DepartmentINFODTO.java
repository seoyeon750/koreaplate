package com.korea.plate.dto;

import java.sql.Date;

public class DepartmentINFODTO {

	private int dNo, dSeat, dHit, dAccpet;
	private double dRating;
	private String dSaup_no, dPhone, dName, dAddress, dStart, dEnd, dParking, dType,dPhoto;
	private Date dReg_date;
	
	public DepartmentINFODTO() {
		// TODO Auto-generated constructor stub
	}

	public DepartmentINFODTO(int dNo, int dSeat, int dHit, int dAccpet, double dRating, String dSaup_no, String dPhone,
			String dName, String dAddress, String dStart, String dEnd, String dParking, String dType, String dPhoto,
			Date dReg_date) {
		super();
		this.dNo = dNo;
		this.dSeat = dSeat;
		this.dHit = dHit;
		this.dAccpet = dAccpet;
		this.dRating = dRating;
		this.dSaup_no = dSaup_no;
		this.dPhone = dPhone;
		this.dName = dName;
		this.dAddress = dAddress;
		this.dStart = dStart;
		this.dEnd = dEnd;
		this.dParking = dParking;
		this.dType = dType;
		this.dPhoto = dPhoto;
		this.dReg_date = dReg_date;
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

	public int getdAccpet() {
		return dAccpet;
	}

	public void setdAccpet(int dAccpet) {
		this.dAccpet = dAccpet;
	}

	public double getdRating() {
		return dRating;
	}

	public void setdRating(double dRating) {
		this.dRating = dRating;
	}

	public String getdSaup_no() {
		return dSaup_no;
	}

	public void setdSaup_no(String dSaup_no) {
		this.dSaup_no = dSaup_no;
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

	public Date getdReg_date() {
		return dReg_date;
	}

	public void setdReg_date(Date dReg_date) {
		this.dReg_date = dReg_date;
	}
	
}