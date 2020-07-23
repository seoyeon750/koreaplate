package com.korea.plate.dao;

import java.util.ArrayList;

import com.korea.plate.dto.AppointmentDTO;
import com.korea.plate.dto.CustomerDTO;
import com.korea.plate.dto.ReviewDTO;

public interface CustomerDAO {
   
	// 일반회원 아이디 중복체크
	public int idCheck(String cId);

	// 일반회원 별명 중복체크
	public int NicknameCheck(String cNickname);
	
	// 일반회원 이메일 중복체크
	public int emailCheck(String cEmail);
	
	// 일반회원 가입
	public void customerSignUp(String cId,String cPw,String cName,String cNickname,String cPhone,String cEmail, String saveFilename, String cGender);
	
	// 일반회원 로그인
	public CustomerDTO customerLogin(String cId, String cPw);
	
	// 일반회원 마이페이지
	public CustomerDTO selectBycNo(int cNo); // 누구인지
	public ArrayList<ReviewDTO> customerMyReview(int cNo); // 작성한 리뷰
	public String deptName(String dSaup_no); // 작성한 리뷰 업체
	public ArrayList<AppointmentDTO> customerMyAppointment(int cNo); // 예약 리스트
	public String deptPhone(String dSaup_no); // 예약 업체 전화번호
	
	// 일반회원 닉네임 변경
	public int nicknameUpdate(String cNickname, int cNo );
	
	// 일반회원 비밀번호 변경
	public int pwUpdate(String cPw, int cNo);
	
	// 일반회원 사진 변경
	public void customerPhotoUpdate(String cNo, String saveFilename);
	
	// 일반회원 탈퇴
	public void customerSignOut1(String cNo);
	public void customerSignOut2(String cNo);
	public void customerSignOut3(String cNo);

	// 일반회원 아이디 찾기 
	public String finduId(String cName,String cEmail);
	
	// 일반회원 비밀번호 찾기 
	public int finduPw(String cId,String cEmail);
	
	// 비밀번호 찾을 때 임시 비밀번호 저장
	public void UpdateTempPw(String cId,String auth);
	
}
