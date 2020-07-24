package com.korea.plate.command.Board;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.korea.plate.common.Command;
import com.korea.plate.dao.BoardDAO;
import com.korea.plate.dto.DepartmentINFODTO;
import com.korea.plate.dto.MenuDTO;
import com.korea.plate.dto.ReviewDTO;

public class BoardViewCommand implements Command {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String,Object> map = model.asMap();		
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String dSaup_no = request.getParameter("dSaup_no");
		BoardDAO bdao =sqlSession.getMapper(BoardDAO.class);
		
		// 페이지 로딩 시  
		bdao.UpdateHit(dSaup_no); // 조회수 증가
		bdao.DepartRatingUpdate(dSaup_no); // 평점 업데이트
		
		// 보여줄 업체 정보 가져오기 
		DepartmentINFODTO deptDTO = bdao.DepartView(dSaup_no);
		
		// 우측에 보여줄 업체 리스트 가져오기 = 동일한 지역
		String address = deptDTO.getdAddress();
		String[] addr = address.split(" ");
		ArrayList<DepartmentINFODTO> side_list = bdao.getSide_list(addr[1],deptDTO.getdName());
		
		// 총 예약 갯수 가져오기
		int appointmentCount = bdao.appointmentCount(dSaup_no);
		
		// 선택한 맛집의 메뉴 가져오기 
		ArrayList<MenuDTO> menuList =bdao.menuList(dSaup_no);
		
		// 리뷰 가져오기 
		ArrayList<ReviewDTO> reviewList = bdao.reviewList(dSaup_no);
	
		model.addAttribute("deptDTO", deptDTO);
		model.addAttribute("side_list", side_list);
		model.addAttribute("appointmentCount", appointmentCount);
		model.addAttribute("menuList", menuList);
		model.addAttribute("reviewList", reviewList);
		model.addAttribute("reviewCount",reviewList.size());
		
		
	}

}
