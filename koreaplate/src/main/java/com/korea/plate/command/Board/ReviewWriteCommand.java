package com.korea.plate.command.Board;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.korea.plate.common.Command;
import com.korea.plate.dao.BoardDAO;
import com.korea.plate.dto.DepartmentINFODTO;

public class ReviewWriteCommand implements Command {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String dSaup_no = request.getParameter("dSaup_no");
		int cNo = Integer.parseInt(request.getParameter("cNo"));
		
		BoardDAO bdao = sqlSession.getMapper(BoardDAO.class);
		DepartmentINFODTO deptDTO = bdao.DepartView(dSaup_no);
			
		model.addAttribute("deptDTO", deptDTO);
		model.addAttribute("cNo", cNo);
		
	}

}
