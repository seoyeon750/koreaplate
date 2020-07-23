package com.korea.plate.command.Dept;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.korea.plate.common.Command;
import com.korea.plate.dao.BoardDAO;
import com.korea.plate.dao.DepartmentDAO;
import com.korea.plate.dto.DepartmentINFODTO;

public class DepartmentMyPageCommand implements Command {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();
		String dSaup_no = (String) session.getAttribute("dSaup_no");
		
		DepartmentDAO lDAO = sqlSession.getMapper(DepartmentDAO.class);
		BoardDAO bDAO = sqlSession.getMapper(BoardDAO.class);
		
		DepartmentINFODTO bDTO = lDAO.selectBydSaup_no(dSaup_no);
		if(bDTO !=null) {
			String dStart = (bDTO.getdStart().substring(0, 2) +":"+ bDTO.getdStart().substring(2,4));
			String dEnd = (bDTO.getdEnd().substring(0,2) +":"+ bDTO.getdEnd().substring(2,4));
			bDTO.setdStart(dStart);
			bDTO.setdEnd(dEnd);
		}
		
		model.addAttribute("dDTO",bDTO );
		model.addAttribute("bDTO", bDAO.menuList(dSaup_no));
	}

}
