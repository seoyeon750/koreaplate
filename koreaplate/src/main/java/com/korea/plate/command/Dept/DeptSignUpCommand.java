package com.korea.plate.command.Dept;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.korea.plate.common.Command;
import com.korea.plate.dao.DepartmentDAO;

public class DeptSignUpCommand implements Command {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String dSaup_no = request.getParameter("dSaup_no");
		String dId = request.getParameter("dId");
		String dPw = request.getParameter("dPw");
		
		DepartmentDAO dao = sqlSession.getMapper(DepartmentDAO.class);
		
		dao.deptSignUp(dSaup_no, dId, dPw);
		    
	}

}
