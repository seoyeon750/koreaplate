package com.korea.plate.dao;

import java.util.ArrayList;

import com.korea.plate.dto.DepartmentINFODTO;
import com.korea.plate.dto.MenuDTO;
import com.korea.plate.dto.ReviewDTO;

public interface BoardDAO {
   
	// 조회수 증가 
	public void UpdateHit(String dSaup_no);
	
	// 평점 업데이트
	public void DepartRatingUpdate(String dSaup_no);
	
	// 업체 정보 가져오기 
	public DepartmentINFODTO DepartView(String dSaup_no);
	
	// 우측에 보여줄 같은 지역 맛집 리스트 
	public ArrayList<DepartmentINFODTO> getSide_list(String addr,String dName);
	
	//appointment 개수 가져오기 
	public int appointmentCount(String dSaup_no);
	
	// 메뉴 정보 가져오기
	public ArrayList<MenuDTO> menuList(String dSaup_no);
	
	// 리뷰 가져오기 
	public ArrayList<ReviewDTO> reviewList(String dSaup_no);
	
	// 리뷰 가져오기 (전체/비예약자/예약자 필터링)
	public ArrayList<ReviewDTO> reviewAll(String dSaup_no);
	public ArrayList<ReviewDTO> reviewAp(String dSaup_no);
	public ArrayList<ReviewDTO> reviewNp(String dSaup_no);
	
	// 리뷰 누르면 리뷰 창
	public ReviewDTO reivewDetail(int rNo);
	
}
