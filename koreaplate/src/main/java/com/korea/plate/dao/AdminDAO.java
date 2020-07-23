package com.korea.plate.dao;

import java.util.ArrayList;
import java.util.Map;

import com.korea.plate.dto.CustomerDTO;
import com.korea.plate.dto.DepartmentINFODTO;
import com.korea.plate.dto.ReviewDTO;

public interface AdminDAO {

	// 관리자 메인페이지 (회원 리스트)
	public ArrayList<CustomerDTO> getCustomList(Map<String, Integer> record);
	
	// 전체 회원 수
	public int getTotelRecord();
	
	// 수정할 회원 정보
	public CustomerDTO UpdateUserPage(int cNo);
	
	// 수정할 회원이 작성한 리뷰
	public ArrayList<ReviewDTO> customerReview(Map<String, Integer> record);
	
	// 일반회원 정보 수정
	public void UpdateUser(String cGrade,String cNo);
	
	// 일반회원 삭제
	public void deleteReview(String cNo);
	public void deleteUser(String cNo);

	// 한 페이지에 보여줄 업체 리스트
	public ArrayList<DepartmentINFODTO> getDepartmentList(Map<String, Integer> record);

	// 전체 업체 수
	public int getTotelDept();
	
	// 업체회원 정보 수정
	public DepartmentINFODTO UpdateDepartmentPage(String dNo);
	
	// 업체 거절 -> 삭제
	public void deptReject(int dNo);
	
	// 승인 받아야 하는 업체리스트
	public ArrayList<DepartmentINFODTO> deptAcceptList(Map<String, Integer> record);
	
	// 승인 받아야 하는 업체 수
	public int deptAcceptCount();
	
	// 승인 받아야 하는 업체 view
	public DepartmentINFODTO deptAcceptView(long dNo);
	
	// 업체 승인
	public void deptAccept(int dNo);
	
	// 회원리스트에서 검색
	public ArrayList<CustomerDTO> searchQueryCusInfo(Map<String, Object> record);
	
	// 검색한 회원 수
	public int searchQueryCusCount(String query);
	
	// 업체리스트에서 검색
	public ArrayList<DepartmentINFODTO> searchQueryDeptInfo(Map<String, Object> record);
	
	// 검색한 업체리트스 개수
	public int searchQueryDeptCount(String query);
	
	// 업체승인리스트에서 검색
	public ArrayList<DepartmentINFODTO> searchQueryDeptAcceptInfo(Map<String, Object> record);
	
	// 검색한 업체승인리스트 개수
	public int searchQueryDeptAcceptCount(String query);
	
	/*

	public ArrayList<CustomerDTO> getAjaxCutomer(String query); */
	
	
}
