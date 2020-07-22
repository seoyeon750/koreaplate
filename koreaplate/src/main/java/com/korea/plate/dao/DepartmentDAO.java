package com.korea.plate.dao;

import com.korea.plate.dto.DepartmentDTO;

public interface DepartmentDAO {

	// 사업자 아이디 중복체크
	public int deptidCheck(String dId);
	
	// 사업자 번호 중복 체크
	public int dSaup_noCheck(String dSaup_no);
	
	// 업체 회원가입
	public void deptSignUp(String dSaup_no, String dId, String dPw );
	
	// 사업자 로그인
	public DepartmentDTO departmentLogin(String dId,String dPw);
	
}
