package com.korea.plate.command.Admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.korea.plate.command.PageMaker;
import com.korea.plate.common.Command;
import com.korea.plate.dao.AdminDAO;
import com.korea.plate.dao.CustomerDAO;
import com.korea.plate.dto.CustomerDTO;
import com.korea.plate.dto.ReviewDTO;

public class AdminUpdateUserCommand implements Command {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		int cNo =Integer.parseInt(request.getParameter("cNo"));
		
		AdminDAO aDao = sqlSession.getMapper(AdminDAO.class);
		CustomerDTO cDTO = aDao.UpdateUserPage(cNo);
		model.addAttribute("cDTO", cDTO);
		
		String pageStr = request.getParameter("page"); // 현재 페이지
		if (pageStr == null || pageStr.isEmpty()) {
			pageStr = "1";
		}
		int page = Integer.parseInt(pageStr);
		
		// 현재 페이지 번호를 이용해 페이지 시작과 끝의 번호를 구한다
		int recordPerPage = 7; // 1페이지당 보여줄 갯수
		int beginRecord = (page - 1) * recordPerPage + 1;
		int endRecord = recordPerPage * page;
		
		Map<String, Integer> record = new HashMap<String, Integer>();
		record.put("beginRecord", beginRecord);
		record.put("endRecord", endRecord);
		record.put("cNo", cNo);
		
		// 작성한 리뷰 리스트
		ArrayList<ReviewDTO> list = aDao.customerReview(record);
		
		ArrayList<String> dNames = new ArrayList<String>();
		CustomerDAO ldao = sqlSession.getMapper(CustomerDAO.class);
		String pageView = PageMaker.getPageView3("UpdateUserPage", page,cNo, recordPerPage, ldao.customerMyReview(cNo).size());
		for(int i =0 ; i<list.size(); i++) {
			dNames.add( ldao.deptName(list.get(i).getdSaup_no()));
		}
		model.addAttribute("list",list);
		model.addAttribute("dList", dNames);
		model.addAttribute("pageView", pageView);
	}

}
