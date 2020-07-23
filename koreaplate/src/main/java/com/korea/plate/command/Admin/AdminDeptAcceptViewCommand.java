package com.korea.plate.command.Admin;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.korea.plate.common.Command;
import com.korea.plate.dao.AdminDAO;
import com.korea.plate.dao.BoardDAO;
import com.korea.plate.dto.DepartmentINFODTO;
import com.korea.plate.dto.MenuDTO;

public class AdminDeptAcceptViewCommand implements Command {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		long dNo = Long.parseLong(request.getParameter("dNo"));
		
		AdminDAO aDAO = sqlSession.getMapper(AdminDAO.class);
		DepartmentINFODTO deptDTO = aDAO.deptAcceptView(dNo);
		
		model.addAttribute("deptDTO", deptDTO);
		
		BoardDAO bdao = sqlSession.getMapper(BoardDAO.class);
		ArrayList<MenuDTO> menuList =bdao.menuList(deptDTO.getdSaup_no());
		
		model.addAttribute("menuList", menuList);

	}

}
