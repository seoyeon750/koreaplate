package com.korea.plate.dto;

import java.sql.Date;

public class ReviewDTO {

	private int rNo, rPoint, rReportCount, cNo;
	private String rTitle, rContent, rPhoto, rAppointment, dSaup_no;
	private Date rWriter_date;
	private String cPhoto, cNickname;
	
	public ReviewDTO() {
		// TODO Auto-generated constructor stub
	}

	public ReviewDTO(int rNo, int rPoint, int rReportCount, int cNo, String rTitle, String rContent, String rPhoto,
			String rAppointment, String dSaup_no, Date rWriter_date, String cPhoto, String cNickname) {
		super();
		this.rNo = rNo;
		this.rPoint = rPoint;
		this.rReportCount = rReportCount;
		this.cNo = cNo;
		this.rTitle = rTitle;
		this.rContent = rContent;
		this.rPhoto = rPhoto;
		this.rAppointment = rAppointment;
		this.dSaup_no = dSaup_no;
		this.rWriter_date = rWriter_date;
		this.cPhoto = cPhoto;
		this.cNickname = cNickname;
	}



	public int getrNo() {
		return rNo;
	}

	public void setrNo(int rNo) {
		this.rNo = rNo;
	}

	public int getrPoint() {
		return rPoint;
	}

	public void setrPoint(int rPoint) {
		this.rPoint = rPoint;
	}

	public int getrReportCount() {
		return rReportCount;
	}

	public void setrReportCount(int rReportCount) {
		this.rReportCount = rReportCount;
	}

	public int getcNo() {
		return cNo;
	}

	public void setcNo(int cNo) {
		this.cNo = cNo;
	}

	public String getrTitle() {
		return rTitle;
	}

	public void setrTitle(String rTitle) {
		this.rTitle = rTitle;
	}

	public String getrContent() {
		return rContent;
	}

	public void setrContent(String rContent) {
		this.rContent = rContent;
	}

	public String getrPhoto() {
		return rPhoto;
	}

	public void setrPhoto(String rPhoto) {
		this.rPhoto = rPhoto;
	}

	public String getrAppointment() {
		return rAppointment;
	}

	public void setrAppointment(String rAppointment) {
		this.rAppointment = rAppointment;
	}

	public String getdSaup_no() {
		return dSaup_no;
	}

	public void setdSaup_no(String dSaup_no) {
		this.dSaup_no = dSaup_no;
	}

	public Date getrWriter_date() {
		return rWriter_date;
	}

	public void setrWriter_date(Date rWriter_date) {
		this.rWriter_date = rWriter_date;
	}

	public String getcPhoto() {
		return cPhoto;
	}

	public void setcPhoto(String cPhoto) {
		this.cPhoto = cPhoto;
	}

	public String getcNickname() {
		return cNickname;
	}

	public void setcNickname(String cNickname) {
		this.cNickname = cNickname;
	}
	
}

