package com.korea.plate.dao;

import java.util.ArrayList;

import com.korea.plate.dto.DepartmentINFODTO;
import com.korea.plate.dto.MainDTO;

public interface MainDAO {
	
	// 지역 리스트
	public ArrayList<MainDTO> main_list();
	
	// 선택한 지역 맛집 리스트
	public ArrayList<DepartmentINFODTO> location_list(String location);
	
	// 선택한 지역의 맛집 수
	public MainDTO get_locationCount(String location);
	
	// 검색 결과
	public ArrayList<DepartmentINFODTO> search_list(String query);

}
