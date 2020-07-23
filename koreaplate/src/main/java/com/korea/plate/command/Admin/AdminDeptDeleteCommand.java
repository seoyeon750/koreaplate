package com.korea.plate.command.Admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.korea.plate.common.Command;
import com.korea.plate.dao.AdminDAO;

public class AdminDeptDeleteCommand implements Command {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int dNo = Integer.parseInt(request.getParameter("dNo"));
		
		AdminDAO aDAO = sqlSession.getMapper(AdminDAO.class);
		aDAO.deptReject(dNo);
		
	}

}
