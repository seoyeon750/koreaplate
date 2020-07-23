package com.korea.plate.command.Dept;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.korea.plate.common.Command;
import com.korea.plate.dao.DepartmentDAO;

public class DeptSignOutCommand implements Command {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String dSaup_no = request.getParameter("dSaup_no");
		
		DepartmentDAO lDAO = sqlSession.getMapper(DepartmentDAO.class);
		
		lDAO.deptSignOut1(dSaup_no);
		lDAO.deptSignOut2(dSaup_no);
		lDAO.deptSignOut3(dSaup_no);
		lDAO.deptSignOut4(dSaup_no);
		lDAO.deptSignOut5(dSaup_no);
		
	}
	
}
