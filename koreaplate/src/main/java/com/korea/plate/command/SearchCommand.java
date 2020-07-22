package com.korea.plate.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.korea.plate.common.Command;
import com.korea.plate.dao.MainDAO;
import com.korea.plate.dto.DepartmentINFODTO;

public class SearchCommand implements Command {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();		
		HttpServletRequest request = (HttpServletRequest) map.get("request");		
		HttpServletResponse response = (HttpServletResponse) map.get("response");
		
		String query = request.getParameter("main-search");		
		MainDAO lDAO = sqlSession.getMapper(MainDAO.class);		
		ArrayList<DepartmentINFODTO> list = lDAO.search_list(query);
		model.addAttribute("list", list);
		model.addAttribute("size", list.size());
		
	}

}
