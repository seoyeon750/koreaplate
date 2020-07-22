package com.korea.plate.dao;

import java.util.ArrayList;

import com.korea.plate.dto.DepartmentINFODTO;
import com.korea.plate.dto.MainDTO;

public interface MainDAO {
	
	// 지역 리스트
	public ArrayList<MainDTO> main_list();
	
	public ArrayList<DepartmentINFODTO> location_list(String location);
	
	public MainDTO get_locationCount(String location);
	
	public ArrayList<DepartmentINFODTO> search_list(String query);

		
}
