package com.korea.plate.command.Board;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.korea.plate.common.Command;
import com.korea.plate.dao.BoardDAO;
import com.korea.plate.dto.DepartmentINFODTO;
import com.korea.plate.dto.ReviewDTO;

public class ReviewDetailCommand implements Command {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String,Object> map = model.asMap();
		HttpServletRequest request =(HttpServletRequest) map.get("request");
		
		int rNo= Integer.parseInt(request.getParameter("rNo"));
		
		BoardDAO bdao = sqlSession.getMapper(BoardDAO.class);
		ReviewDTO rdto =bdao.reivewDetail(rNo);
		DepartmentINFODTO deptdto = bdao.DepartView(rdto.getdSaup_no());
		
		model.addAttribute("rdto", rdto);
		model.addAttribute("deptdto", deptdto); 
		
	}

}
