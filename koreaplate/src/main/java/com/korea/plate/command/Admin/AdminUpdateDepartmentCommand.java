package com.korea.plate.command.Admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.korea.plate.common.Command;
import com.korea.plate.dao.AdminDAO;
import com.korea.plate.dto.DepartmentINFODTO;

public class AdminUpdateDepartmentCommand implements Command {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String dNo = request.getParameter("dNo");
		
		AdminDAO aDao = sqlSession.getMapper(AdminDAO.class);
		DepartmentINFODTO dDTO = aDao.UpdateDepartmentPage(dNo);
		model.addAttribute("dDTO", dDTO);
	}

}
