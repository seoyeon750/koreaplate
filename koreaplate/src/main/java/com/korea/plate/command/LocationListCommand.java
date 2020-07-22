package com.korea.plate.command;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.korea.plate.common.Command;
import com.korea.plate.dao.MainDAO;
import com.korea.plate.dto.DepartmentINFODTO;

public class LocationListCommand implements Command {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String,Object> map = model.asMap();
		String location = (String) map.get("location");
		
		MainDAO ldao = sqlSession.getMapper(MainDAO.class);
		ArrayList<DepartmentINFODTO> deptDTO = ldao.location_list(location);
		
		// 지역 맛집 리스트 
		model.addAttribute("location_list", deptDTO);
		
		// 어떤 지역 몇 곳 
		model.addAttribute("location_count", ldao.get_locationCount(location));
		
	}

}
