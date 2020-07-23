package com.korea.plate.dao;

import com.korea.plate.dto.DepartmentDTO;
import com.korea.plate.dto.DepartmentINFODTO;

public interface DepartmentDAO {

	// 사업자 아이디 중복체크
	public int deptidCheck(String dId);
	
	// 사업자 번호 중복 체크
	public int dSaup_noCheck(String dSaup_no);
	
	// 업체 회원가입
	public void deptSignUp(String dSaup_no, String dId, String dPw );
	
	// 업체 로그인
	public DepartmentDTO departmentLogin(String dId,String dPw);
	
	// 업체회원 마이페이지
	public DepartmentINFODTO selectBydSaup_no(String dSaup_no);
	
	// 업체 비밀번호 변경
	public int deptpwUpdate(String dPw, String dSaup_no);
	
	// 업체 정보수정 승인요청
	public int goDb(String dSaup_no);
	public void departUpdate(String dSeat,String dSaup_no,String dPhone,String dName,String dAddress, String dStart, String dEnd, String dParking, String dType, String amuguna, String dNo );
	public void departInsert(String dSeat,String dSaup_no,String dPhone,String dName,String dAddress, String dStart, String dEnd, String dParking, String dType, String amuguna );
	
	// 업체 메뉴 삭제
	public void menuDelete(String dSaup_no);
	
	// 업체 메뉴 추가
	public void menuInsert(String menu, String price, String dSaup_no);
	
	// 업체회원 탈퇴
	public void deptSignOut1(String dSaup_no); // 리뷰 삭제
	public void deptSignOut2(String dSaup_no); // 예약 삭제
	public void deptSignOut3(String dSaup_no); // 메뉴 삭제
	public void deptSignOut4(String dSaup_no); // 정보 삭제
	public void deptSignOut5(String dSaup_no); // 회원 탈퇴
	
	// 업체 아이디 찾기 
	public String find_dept_id(String dSaup_no,String dPhone);
	
	// 업체 비밀번호 찾기 
	public int findDeptPw(String dId,String dSaup_no);
}
